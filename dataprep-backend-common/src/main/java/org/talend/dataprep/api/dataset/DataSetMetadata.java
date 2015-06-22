package org.talend.dataprep.api.dataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.talend.dataprep.schema.SchemaParserResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Represents all information needed to look for a data set ({@link #getId()} as well as information inferred from data
 * set content:
 * <ul>
 * <li>Metadata information: see {@link #getRow()}</li>
 * <li>Current progress on content processing:: see {@link #getLifecycle()}</li>
 * </ul>
 * 
 * @see DataSetMetadata.Builder
 */
public class DataSetMetadata {

    /** The dataset id. */
    @Id
    private String id;

    /** Row description. */
    @JsonIgnore(true)
    private RowMetadata rowMetadata;

    /** Dataset life cycle status. */
    @JsonProperty("lifecycle")
    private final DataSetLifecycle lifecycle = new DataSetLifecycle();

    @JsonProperty("content")
    @JsonUnwrapped
    private DataSetContent content = new DataSetContent();

    /** Dataset governance. */
    @JsonProperty("governance")
    @JsonUnwrapped
    private final DataSetGovernance governance = new DataSetGovernance();

    /** Dataset name. */
    @JsonProperty("name")
    private String name;

    /** Dataset author. */
    @JsonProperty("author")
    private String author;

    @JsonProperty("created")
    private long creationDate;

    /** Sheet number in case of excel source. */
    @JsonProperty("sheetName")
    private String sheetName;

    /**
     * if <code>true</code> this dataset is still a draft as we need more informations from the user
     */
    @JsonProperty("draft")
    private boolean draft = false;

    /**
     * available only when draft is <code>true</code> i.e until some informations has been confirmed by the user
     */
    @JsonProperty("schemaParserResult")
    private SchemaParserResult schemaParserResult;

    /**
     * flag to tell the dataset is one of the favorites for the current user this value is sent back to front but not
     * stored because it stored in another user related storage
     */
    @JsonProperty("favorite")
    private transient boolean favorite;

    public DataSetMetadata() {
        // no op
    }

    /**
     * Constructor.
     * 
     * @param id dataset id.
     * @param name dataset name.
     * @param author dataset author.
     * @param creationDate dataset creation date.
     * @param rowMetadata row metadata.
     */
    public DataSetMetadata(String id, String name, String author, long creationDate, RowMetadata rowMetadata) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.creationDate = creationDate;
        this.rowMetadata = rowMetadata;
    }

    /**
     * @return the dataset id.
     */
    public String getId() {
        return id;
    }

    /**
     * @return the dataset row description.
     */
    @JsonIgnore(true)
    public RowMetadata getRow() {
        return rowMetadata;
    }

    public void setRowMetadata(RowMetadata rowMetadata) {
        this.rowMetadata = rowMetadata;
    }

    /**
     * @return the dataset lifecycle.
     */
    public DataSetLifecycle getLifecycle() {
        return lifecycle;
    }

    /**
     * @return the dataset content summary.
     */
    public DataSetContent getContent() {
        return content;
    }

    public void setContent(DataSetContent content) {
        this.content = content;
    }

    /**
     * @return the dataset governance.
     */
    public DataSetGovernance getGovernance() {
        return this.governance;
    }

    /**
     * @return the dataset name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the dataset name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dataset author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the sheet name
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * @param sheetName the new sheet name
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * @return the dataset creation date.
     */
    public long getCreationDate() {
        return creationDate;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public SchemaParserResult getSchemaParserResult() {
        return schemaParserResult;
    }

    public void setSchemaParserResult(SchemaParserResult schemaParserResult) {
        this.schemaParserResult = schemaParserResult;
    }

    /**
     * Getter for favorite.
     * 
     * @return the favorite
     */
    public boolean isFavorite() {
        return this.favorite;
    }

    /**
     * Sets the favorite.
     * 
     * @param favorite the favorite to set
     */
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * Dataset builder.
     */
    public static class Builder {

        private String id;

        private ColumnMetadata.Builder[] columnBuilders;

        private String author = "anonymous";

        private String name = "";

        private long createdDate = System.currentTimeMillis();

        private int size;

        private int headerSize;

        private int footerSize;

        private boolean contentAnalyzed;

        private boolean schemaAnalyzed;

        private boolean qualityAnalyzed;

        private String sheetName;

        private boolean draft = true;

        private String formatGuessId;

        private String mediaType;

        private boolean isFavorite;

        public static DataSetMetadata.Builder metadata() {
            return new Builder();
        }

        public DataSetMetadata.Builder id(String id) {
            this.id = id;
            return this;
        }

        public DataSetMetadata.Builder author(String author) {
            this.author = author;
            return this;
        }

        public DataSetMetadata.Builder name(String name) {
            this.name = name;
            return this;
        }

        public DataSetMetadata.Builder created(long createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public DataSetMetadata.Builder row(ColumnMetadata.Builder... columns) {
            columnBuilders = columns;
            return this;
        }

        public DataSetMetadata.Builder size(int size) {
            this.size = size;
            return this;
        }

        public DataSetMetadata.Builder headerSize(int headerSize) {
            this.headerSize = headerSize;
            return this;
        }

        public DataSetMetadata.Builder footerSize(int footerSize) {
            this.footerSize = footerSize;
            return this;
        }

        public Builder contentAnalyzed(boolean contentAnalyzed) {
            this.contentAnalyzed = contentAnalyzed;
            return this;
        }

        public Builder schemaAnalyzed(boolean schemaAnalyzed) {
            this.schemaAnalyzed = schemaAnalyzed;
            return this;
        }

        public Builder qualityAnalyzed(boolean qualityAnalyzed) {
            this.qualityAnalyzed = qualityAnalyzed;
            return this;
        }

        public Builder sheetName(String sheetName) {
            this.sheetName = sheetName;
            return this;
        }

        public Builder draft(boolean draft) {
            this.draft = draft;
            return this;
        }

        public Builder formatGuessId(String formatGuessId) {
            this.formatGuessId = formatGuessId;
            return this;
        }

        public Builder mediaType(String mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public DataSetMetadata.Builder isFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
            return this;
        }

        public DataSetMetadata build() {
            if (id == null) {
                throw new IllegalStateException("No id set for dataset.");
            }
            List<ColumnMetadata> columns;
            if (columnBuilders != null) {
                columns = new ArrayList<>();
                for (ColumnMetadata.Builder columnBuilder : columnBuilders) {
                    columns.add(columnBuilder.build());
                }
            } else {
                columns = Collections.emptyList();
            }
            RowMetadata row = new RowMetadata(columns);
            DataSetMetadata metadata = new DataSetMetadata(id, name, author, createdDate, row);
            metadata.sheetName = this.sheetName;
            metadata.draft = this.draft;
            metadata.setFavorite(this.isFavorite);
            // Content information
            DataSetContent currentContent = metadata.getContent();
            currentContent.setNbRecords(size);
            currentContent.setNbLinesInHeader(headerSize);
            currentContent.setNbLinesInFooter(footerSize);

            if (formatGuessId != null) {
                currentContent.setFormatGuessId(formatGuessId);
            }
            currentContent.setMediaType(mediaType);
            // Lifecycle information
            DataSetLifecycle metadataLifecycle = metadata.getLifecycle();
            metadataLifecycle.contentIndexed(contentAnalyzed);
            metadataLifecycle.schemaAnalyzed(schemaAnalyzed);
            metadataLifecycle.qualityAnalyzed(qualityAnalyzed);
            return metadata;
        }
    }

}
