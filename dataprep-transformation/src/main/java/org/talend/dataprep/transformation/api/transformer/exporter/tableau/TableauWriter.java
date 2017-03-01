package org.talend.dataprep.transformation.api.transformer.exporter.tableau;


import java.io.*;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.DataSetRow;
import org.talend.dataprep.api.dataset.RowMetadata;
import org.talend.dataprep.transformation.api.transformer.TransformerWriter;

import com.tableausoftware.DataExtract.*;
import com.tableausoftware.TableauException;

/**
 * Tableau implementation of the TransformerWriter.
 */
public class TableauWriter implements TransformerWriter {

    /** This class' logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TableauWriter.class);

    /** A SecureRandom used to generate tableau id. */
    private static final SecureRandom random = new SecureRandom();

    /** The tableau default type. */
    private static org.talend.dataprep.api.type.Type DEFAULT_TYPE = org.talend.dataprep.api.type.Type.ANY;

    /** List of column metadata. */
    private ColumnMetadata[] columnMetadatas;

    /** Where to write the tableau. */
    private OutputStream outputStream;

    /** Name of the tableau file to wirte.. */
    private String fileName;

    /** Tableau data extract. */
    private Extract extract;

    /** The tableau table object. */
    private Table table;

    /**
     * Constructor.
     *
     * @param output Where this writer should write.
     * @throws IOException if an unexpected error occurs.
     */
    public TableauWriter(final OutputStream output) throws IOException {
        this.outputStream = output;

        long n = random.nextLong();
        if (n == Long.MIN_VALUE) {
            n = 0;
        } else {
            n = Math.abs(n);
        }

        this.fileName = "tableauExport" + Long.toString(n) + ".tde";

        try {
            this.extract = new Extract(fileName);
        } catch (TableauException e) {
            LOGGER.error("Unable to create Tableau export.", e);
        }
    }

    /**
     * @param dataPrepType the data-prep type.
     * @return Return the tableau type from the data-prep one.
     */
    private static Type getTableauType(org.talend.dataprep.api.type.Type dataPrepType) {
        switch (dataPrepType) {
        case ANY:
        case STRING:
            return Type.UNICODE_STRING;
        case NUMERIC:
        case INTEGER:
            return Type.INTEGER;
        case DOUBLE:
        case FLOAT:
            return Type.DOUBLE;
        case BOOLEAN:
            return Type.BOOLEAN;
        case DATE:
            return Type.UNICODE_STRING;
        default:
            return Type.UNICODE_STRING;
        }
    }

    /**
     * @see TransformerWriter#write(RowMetadata)
     */
    @Override
    public void write(final RowMetadata rowMetadata) throws IOException {
        columnMetadatas = rowMetadata.getColumns().stream().map(columnMetadata -> columnMetadata).toArray(ColumnMetadata[]::new);
        try {
            TableDefinition tableDef = new TableDefinition();
            tableDef.setDefaultCollation(Collation.EN_GB);

            for (ColumnMetadata col : columnMetadatas) {
                if (col.getQuality().getInvalid() == 0) {
                    tableDef.addColumn(col.getName(), getTableauType(org.talend.dataprep.api.type.Type.get(col.getType())));
                } else {
                    tableDef.addColumn(col.getName(), getTableauType(DEFAULT_TYPE));
                }
            }

            this.table = this.extract.addTable("Extract", tableDef);
        } catch (TableauException e) {
            LOGGER.error("Unable to write column information.", e);
        }
    }

    /**
     * @see TransformerWriter#write(DataSetRow)
     */
    @Override
    public void write(final DataSetRow row) throws IOException {
        if (columnMetadatas == null) {
            throw new UnsupportedOperationException("Write columns should be called before to init column list");
        }

        try {
            TableDefinition tableDef = table.getTableDefinition();
            Row tableauRow = new Row(tableDef);
            for (int i = 0; i < columnMetadatas.length; i++) {
                String value = row.get(columnMetadatas[i].getId());
                org.talend.dataprep.api.type.Type typeCol;
                if (columnMetadatas[i].getQuality().getInvalid() == 0) {
                    typeCol = org.talend.dataprep.api.type.Type.get(columnMetadatas[i].getType());
                } else {
                    typeCol = DEFAULT_TYPE;
                }

                switch (typeCol) {
                case ANY:
                    tableauRow.setString(i, value);
                    break;
                case STRING:
                    tableauRow.setString(i, value);
                    break;
                case NUMERIC:
                case INTEGER:
                    if (value != null && !value.isEmpty()) {
                        tableauRow.setInteger(i, Integer.parseInt(value));
                    } else {
                        tableauRow.setNull(i);
                    }
                    break;
                case DOUBLE:
                case FLOAT:
                    if (value != null && !value.isEmpty()) {
                        tableauRow.setDouble(i, Double.parseDouble(value));
                    } else {
                        tableauRow.setNull(i);
                    }
                    break;
                case BOOLEAN:
                    if (value != null && !value.isEmpty()) {
                        tableauRow.setBoolean(i, Boolean.parseBoolean(value));
                    } else {
                        tableauRow.setNull(i);
                    }
                    break;
                case DATE:
                    tableauRow.setString(i, value);
                    break;
                default:
                    row.get(columnMetadatas[i].getId());
                }
            }
            table.insert(tableauRow);
        } catch (TableauException e) {
            LOGGER.error("Unable to write data set row.", e);
        }

    }

    /**
     * @see TransformerWriter#endObject()
     */
    @Override
    public void endObject() throws IOException {
        this.extract.close();
        File file = new File(this.fileName);
        file.deleteOnExit();
        InputStream is = new FileInputStream(file);

        byte[] buf = new byte[8192];

        int c;

        while ((c = is.read(buf, 0, buf.length)) > 0) {
            outputStream.write(buf, 0, c);
            outputStream.flush();
        }
        is.close();
        outputStream.close();
        file.delete();
    }

    @Override
    public boolean requireMetadataForHeader() {
        return true;
    }

}