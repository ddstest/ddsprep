//  ============================================================================
//
//  Copyright (C) 2006-2018 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// https://github.com/Talend/data-prep/blob/master/LICENSE
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.dataprep.transformation;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

import org.talend.dataprep.api.action.Action;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.row.DataSetRow;
import org.talend.dataprep.transformation.actions.common.AbstractActionMetadata;
import org.talend.dataprep.transformation.actions.common.CellAction;
import org.talend.dataprep.transformation.actions.common.ColumnAction;
import org.talend.dataprep.transformation.actions.common.DataSetAction;
import org.talend.dataprep.transformation.actions.context.ActionContext;

/**
 * A unit test action: only use to test unexpected action failures.
 */
@Action(FailedAction.FAILED_ACTION)
public class FailedAction extends AbstractActionMetadata implements ColumnAction, CellAction, DataSetAction {

    public static final String FAILED_ACTION = "testfailedaction";

    @Override
    public String getName() {
        return FAILED_ACTION;
    }

    @Override
    public String getCategory(Locale locale) {
        return "TEST";
    }

    @Override
    public boolean acceptField(ColumnMetadata column) {
        return true;
    }

    @Override
    public Collection<DataSetRow> applyOnCell(DataSetRow row, ActionContext context) {
        throw new RuntimeException("On purpose unchecked exception");
    }

    @Override
    public Collection<DataSetRow> applyOnColumn(DataSetRow row, ActionContext context) {
        throw new RuntimeException("On purpose unchecked exception");
    }

    @Override
    public Collection<DataSetRow> applyOnDataSet(DataSetRow row, ActionContext context) {
        throw new RuntimeException("On purpose unchecked exception");
    }

    @Override
    public Set<Behavior> getBehavior() {
        return EnumSet.allOf(Behavior.class);
    }
}
