package org.talend.dataprep.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.talend.dataprep.exception.ErrorCode;

public enum APIErrorCodes implements ErrorCode {
    UNABLE_TO_DELETE_PREPARATION(400),
    UNABLE_TO_CREATE_DATASET(400),
    UNABLE_TO_CREATE_OR_UPDATE_DATASET(400),
    UNABLE_TO_CERTIFY_DATASET(400),
    UNABLE_TO_DELETE_DATASET(400, "dataSetId", "preparations"),
    UNABLE_TO_RETRIEVE_DATASET_CONTENT(400, "id"),
    UNABLE_TO_RETRIEVE_DATASET_CONTENT_DATASET_NOT_READY(400, "id"),
    UNABLE_TO_RETRIEVE_DATASET_METADATA(400),
    UNABLE_TO_LIST_DATASETS(400),
    UNABLE_TO_LIST_ERRORS(500),
    UNABLE_TO_ACTIONS_TO_PREPARATION(400, "id"),
    UNABLE_TO_CREATE_PREPARATION(500), // can also be 400 ?
    UNABLE_TO_RETRIEVE_PREPARATION_LIST(400),
    UNABLE_TO_RETRIEVE_PREPARATION_CONTENT(400),
    UNABLE_TO_UPDATE_PREPARATION(400, "id"),
    UNABLE_TO_RETRIEVE_SUGGESTED_ACTIONS(400, "columnName", "dataSetId"),
    UNABLE_TO_FIND_COMMAND(500, "class", "args"),
    UNABLE_TO_GET_PREPARATION_DETAILS(400),
    UNABLE_TO_TRANSFORM_DATASET(400, "dataSetId"),
    UNABLE_TO_UPDATE_ACTION_IN_PREPARATION(400, "id"),
    UNABLE_TO_EXPORT_CONTENT(400),
    DATASET_REDIRECT(301),
    UNABLE_TO_GET_DYNAMIC_ACTION_PARAMS(400);

    /** The http status to use. */
    private int httpStatus;

    /** Expected entries to be in the context. */
    private List<String> expectedContextEntries;

    /**
     * default constructor.
     * 
     * @param httpStatus the http status to use.
     */
    APIErrorCodes(int httpStatus) {
        this.httpStatus = httpStatus;
        this.expectedContextEntries = Collections.emptyList();
    }

    /**
     * default constructor.
     * 
     * @param httpStatus the http status to use.
     * @param contextEntries expected context entries.
     */
    APIErrorCodes(int httpStatus, String... contextEntries) {
        this.httpStatus = httpStatus;
        this.expectedContextEntries = Arrays.asList(contextEntries);
    }

    /**
     * @return the product.
     */
    @Override
    public String getProduct() {
        return "TDP"; //$NON-NLS-1$
    }

    /**
     * @return the group.
     */
    @Override
    public String getGroup() {
        return "API"; //$NON-NLS-1$
    }

    /**
     * @return the http status.
     */
    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return the expected context entries.
     */
    @Override
    public Collection<String> getExpectedContextEntries() {
        return expectedContextEntries;
    }
}