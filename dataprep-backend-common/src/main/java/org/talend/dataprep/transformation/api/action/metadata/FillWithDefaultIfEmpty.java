package org.talend.dataprep.transformation.api.action.metadata;

import javax.annotation.Nonnull;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.type.Type;
import org.talend.dataprep.transformation.api.action.parameters.Parameter;

@Component(FillWithDefaultIfEmpty.ACTION_BEAN_PREFIX + FillWithDefaultIfEmpty.FILL_EMPTY_ACTION_NAME)
public class FillWithDefaultIfEmpty extends AbstractDefaultIfEmpty {

    public static final String FILL_EMPTY_ACTION_NAME = "fillemptywithdefault"; //$NON-NLS-1$

    private FillWithDefaultIfEmpty() {
    }

    @Override
    public String getName() {
        return FILL_EMPTY_ACTION_NAME;
    }

    @Override
    @Nonnull
    public Parameter[] getParameters() {
        return new Parameter[] { COLUMN_ID_PARAMETER, COLUMN_NAME_PARAMETER,
                new Parameter(DEFAULT_VALUE_PARAMETER, Type.STRING.getName(), StringUtils.EMPTY) };
    }

    /**
     * @see ActionMetadata#accept(ColumnMetadata)
     */
    @Override
    public boolean accept(ColumnMetadata column) {
        return Type.STRING.equals(Type.get(column.getType()));
    }

}