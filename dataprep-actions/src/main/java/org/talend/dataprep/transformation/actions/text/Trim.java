// ============================================================================
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// https://github.com/Talend/data-prep/blob/master/LICENSE
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.dataprep.transformation.actions.text;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.talend.dataprep.api.type.Type.STRING;
import static org.talend.dataprep.parameters.Parameter.parameter;
import static org.talend.dataprep.parameters.SelectParameter.selectParameter;
import static org.talend.dataprep.transformation.api.action.context.ActionContext.ActionStatus.CANCELED;
import static org.talend.dataprep.transformation.api.action.context.ActionContext.ActionStatus.OK;

import java.util.*;

import org.talend.dataprep.api.action.Action;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.row.DataSetRow;
import org.talend.dataprep.api.type.Type;
import org.talend.dataprep.parameters.Parameter;
import org.talend.dataprep.parameters.ParameterType;
import org.talend.dataprep.transformation.actions.category.ActionCategory;

import org.talend.dataprep.transformation.actions.common.*;

import org.talend.dataprep.transformation.api.action.context.ActionContext;
import org.talend.dataquality.converters.StringTrimmer;

/**
 * Trim leading and trailing characters.
 */
@Action(AbstractActionMetadata.ACTION_BEAN_PREFIX + Trim.TRIM_ACTION_NAME)
public class Trim extends AbstractDataSetAction {

    /**
     * The action name.
     */
    public static final String TRIM_ACTION_NAME = "trim"; //$NON-NLS-1$

    /** Padding Character. */
    public static final String PADDING_CHAR_PARAMETER = "padding_character"; //$NON-NLS-1$

    /** Custom Padding Character. */
    public static final String CUSTOM_PADDING_CHAR_PARAMETER = "custom_padding_character"; //$NON-NLS-1$

    /** String Converter help class. */
    public static final String STRING_TRIMMER = "string_trimmer"; //$NON-NLS-1$

    /**
     * Keys used in the values of different parameters:
     */
    public static final String CUSTOM = "custom"; //$NON-NLS-1$

    public static final String WHITESPACE = "whitespace"; //$NON-NLS-1$

    protected static final String NEW_COLUMN_SUFFIX = "_trim";

    private static final boolean CREATE_NEW_COLUMN_DEFAULT = false;

    @Override
    public String getName() {
        return TRIM_ACTION_NAME;
    }

    @Override
    public String getCategory(Locale locale) {
        return ActionCategory.STRINGS.getDisplayName(locale);
    }

    @Override
    public boolean acceptField(ColumnMetadata column) {
        return Type.STRING.equals(Type.get(column.getType()));
    }

    protected List<ActionsUtils.AdditionalColumn> getAdditionalColumns(ActionContext context) {
        return singletonList(ActionsUtils.additionalColumn().withName(context.getColumnName() + NEW_COLUMN_SUFFIX).withType(STRING));
    }

    @Override
        public List<Parameter> getParameters(Locale locale) {
        final List<Parameter> parameters = super.getParameters(locale);
        parameters.add(ActionsUtils.getColumnCreationParameter(locale, CREATE_NEW_COLUMN_DEFAULT));

        // @formatter:off
        parameters.add(selectParameter(locale)
                .name(PADDING_CHAR_PARAMETER)
                .item(WHITESPACE,WHITESPACE)
                .item(CUSTOM, CUSTOM, parameter(locale).setName(CUSTOM_PADDING_CHAR_PARAMETER).setType(ParameterType.STRING).setDefaultValue(EMPTY).build(this))
                .canBeBlank(true)
                .defaultValue(WHITESPACE)
                .build(this ));
        // @formatter:on
        return parameters;
    }

    @Override
    public void compile(ActionContext actionContext) {
        super.compile(actionContext);
        if (ActionsUtils.doesCreateNewColumn(actionContext.getParameters(), CREATE_NEW_COLUMN_DEFAULT)) {
            try {
                ActionsUtils.createNewColumn(actionContext, getAdditionalColumns(actionContext));
            } catch (NullPointerException e) {
                actionContext.setActionStatus(CANCELED);
            }
        }
        if (actionContext.getActionStatus() == OK) {
            actionContext.get(STRING_TRIMMER, p -> new StringTrimmer());
        }
    }

    @Override
    public void apply(DataSetRow row, String targetColumnId, ActionContext context) {
        String columnId = context.getColumnId();
        String toTrim = row.get(columnId);
        row.set(targetColumnId, doTrim(toTrim, context));
    }

    public String doTrim(String toTrim, ActionContext context) {
        if (toTrim != null) {
            final Map<String, String> parameters = context.getParameters();
            final StringTrimmer stringTrimmer = context.get(STRING_TRIMMER);
            if (CUSTOM.equals(parameters.get(PADDING_CHAR_PARAMETER))) {
                return stringTrimmer.removeTrailingAndLeading(toTrim, parameters.get(CUSTOM_PADDING_CHAR_PARAMETER));
            } else {
                return stringTrimmer.removeTrailingAndLeadingWhitespaces(toTrim);
            }
        } else {
            return toTrim;
        }
    }

    @Override
    public Set<Behavior> getBehavior() {
        return EnumSet.of(Behavior.VALUES_COLUMN);
    }

}
