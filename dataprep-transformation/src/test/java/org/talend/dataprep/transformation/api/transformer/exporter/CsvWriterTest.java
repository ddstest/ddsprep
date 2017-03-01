package org.talend.dataprep.transformation.api.transformer.exporter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.DataSetRow;
import org.talend.dataprep.api.dataset.RowMetadata;
import org.talend.dataprep.api.type.Type;
import org.talend.dataprep.transformation.api.transformer.exporter.csv.CsvWriter;

public class CsvWriterTest {

    private CsvWriter writer;

    private OutputStream outputStream;

    @Before
    public void init() {
        outputStream = new ByteArrayOutputStream();
        writer = new CsvWriter(outputStream, ';');
    }

    @Test
    public void write_should_write_columns() throws Exception {
        // given
        List<ColumnMetadata> columns = new ArrayList<>(2);
        columns.add(ColumnMetadata.Builder.column().id(1).name("id").type(Type.STRING).build());
        columns.add(ColumnMetadata.Builder.column().id(2).name("firstname").type(Type.STRING).build());

        // when
        writer.write(new RowMetadata(columns));
        writer.flush();

        // then
        assertThat(outputStream.toString()).isEqualTo("\"id\";\"firstname\"\n");
    }

    @Test
    public void write_should_throw_exception_when_write_columns_have_not_been_called() throws Exception {
        // given
        final DataSetRow row = new DataSetRow();

        // when
        try {
            writer.write(row);
            fail("should have thrown UnsupportedOperationException");
        }
        // then
        catch (final UnsupportedOperationException e) {
            assertThat(e.getMessage()).isEqualTo("Write columns should be called before to init column list");
        }
    }

    @Test
    public void write_should_write_row() throws Exception {
        // given
        final ColumnMetadata column1 = ColumnMetadata.Builder.column().id(1).name("id").type(Type.STRING).build();
        final ColumnMetadata column2 = ColumnMetadata.Builder.column().id(2).name("firstname").type(Type.STRING).build();
        final List<ColumnMetadata> columns = Arrays.asList(column1, column2);

        final DataSetRow row = new DataSetRow();
        row.set("0001", "64a5456ac148b64524ef165");
        row.set("0002", "Superman");

        final String expectedCsv = "\"id\";\"firstname\"\n" + "\"64a5456ac148b64524ef165\";\"Superman\"\n";

        // when
        writer.write(new RowMetadata(columns));
        writer.write(row);
        writer.flush();

        // then
        assertThat(outputStream.toString()).isEqualTo(expectedCsv);
    }

}