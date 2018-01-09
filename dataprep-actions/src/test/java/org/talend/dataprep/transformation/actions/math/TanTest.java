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

package org.talend.dataprep.transformation.actions.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.talend.dataprep.transformation.actions.ActionMetadataTestUtils.getRow;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.row.DataSetRow;
import org.talend.dataprep.api.type.Type;
import org.talend.dataprep.transformation.actions.AbstractMetadataBaseTest;
import org.talend.dataprep.transformation.actions.ActionDefinition;
import org.talend.dataprep.transformation.actions.ActionMetadataTestUtils;
import org.talend.dataprep.transformation.actions.common.ActionsUtils;
import org.talend.dataprep.transformation.api.action.ActionTestWorkbench;

/**
 * Unit test for the Tan action.
 *
 * @see Tan
 */
public class TanTest extends AbstractMetadataBaseTest<Tan> {

    /** The action parameters. */
    private Map<String, String> parameters;

    public TanTest() {
        super(new Tan());
    }

    @Before
    public void setUp() throws Exception {
        final InputStream parametersSource = TanTest.class.getResourceAsStream("tanAction.json");
        parameters = ActionMetadataTestUtils.parseParameters(parametersSource);
    }

    @Override
    public CreateNewColumnPolicy getCreateNewColumnPolicy() {
        return CreateNewColumnPolicy.VISIBLE_DISABLED;
    }

    @Test
    public void test_apply_in_newcolumn() {
        // given
        DataSetRow row = getRow("10", "3", "Done !");
        parameters.put(ActionsUtils.CREATE_NEW_COLUMN, "true");

        // when
        ActionTestWorkbench.test(row, factory.create(action, parameters));

        // then
        assertColumnWithResultCreated(row);
        assertEquals("0.6483608274590866", row.get("0003"));
    }

    @Test
    public void tan_with_positive_percentage() {
        // given
        DataSetRow row = getRow("1000%", "3", "Done !");

        // when
        ActionTestWorkbench.test(row, factory.create(action, parameters));

        // then
        assertEquals("0.6483608274590866", row.get("0000"));
    }

    @Test
    public void test_apply_inplace() {
        // given
        DataSetRow row = getRow("10", "3", "Done !");

        // when
        ActionTestWorkbench.test(row, factory.create(action, parameters));
        assertEquals(row.values().size(), 3);

        // then
        DataSetRow expected = getRow("0.6483608274590866", "3", "Done !");
        assertEquals(expected, row);
    }

    @Test
    public void tan_with_negative() {
        // given
        DataSetRow row = getRow("-10", "3", "Done !");
        parameters.put(ActionsUtils.CREATE_NEW_COLUMN, "true");

        // when
        ActionTestWorkbench.test(row, factory.create(action, parameters));

        // then
        assertColumnWithResultCreated(row);
        assertEquals("-0.6483608274590866", row.get("0003"));
    }

    @Test
    public void tan_with_NaN() {
        // given
        DataSetRow row = getRow("beer", "3", "Done !");
        parameters.put(ActionsUtils.CREATE_NEW_COLUMN, "true");
        // when
        ActionTestWorkbench.test(row, factory.create(action, parameters));

        // then
        assertColumnWithResultCreated(row);
        assertEquals(StringUtils.EMPTY, row.get("0003"));
    }

    @Test
    public void should_have_expected_behavior() {
        assertEquals(1, action.getBehavior().size());
        assertTrue(action.getBehavior().contains(ActionDefinition.Behavior.METADATA_CREATE_COLUMNS));
    }

    private void assertColumnWithResultCreated(DataSetRow row) {
        ColumnMetadata expected = ColumnMetadata.Builder.column().id(3).name("0000_tan").type(Type.DOUBLE).build();
        ColumnMetadata actual = row.getRowMetadata().getById("0003");
        assertEquals(expected, actual);
    }

}
