// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprep.transformation.api.action.metadata.text;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.talend.dataprep.api.type.Type.BOOLEAN;
import static org.talend.dataprep.api.type.Type.STRING;
import static org.talend.dataprep.transformation.api.action.metadata.common.ImplicitParameters.*;
import static org.talend.dataprep.transformation.api.action.metadata.text.ReplaceOnValue.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
import org.junit.Test;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.DataSetRow;
import org.talend.dataprep.transformation.api.action.ActionTestWorkbench;
import org.talend.dataprep.transformation.api.action.context.ActionContext;
import org.talend.dataprep.transformation.api.action.context.TransformationContext;
import org.talend.dataprep.transformation.api.action.metadata.common.ImplicitParameters;
import org.talend.dataprep.transformation.api.action.parameters.Parameter;

/**
 * Test class for Replace value action
 */
public class ReplaceOnValueTest {

    private ReplaceOnValue action = new ReplaceOnValue();

    private ActionContext buildPatternActionContext(String regex, String replacement, boolean replace) {
        ActionContext context = new ActionContext(new TransformationContext());
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ReplaceOnValue.CELL_VALUE_PARAMETER, regex);
        parameters.put(ReplaceOnValue.REPLACE_VALUE_PARAMETER, replacement);
        parameters.put(ReplaceOnValue.REPLACE_ENTIRE_CELL_PARAMETER, String.valueOf(replace));
        context.setParameters(parameters);
        action.compile(context);
        return context;
    }

    @Test
    public void should_return_common_and_specific_parameters() {
        // when
        final List<Parameter> actionParams = action.getParameters();

        // then
        assertThat(actionParams, hasSize(7));

        final List<String> paramNames = actionParams.stream().map(Parameter::getName).collect(toList());
        assertThat(paramNames, IsIterableContainingInAnyOrder.containsInAnyOrder(COLUMN_ID.getKey(), //
                ROW_ID.getKey(), //
                SCOPE.getKey(), //
                FILTER.getKey(), //
                CELL_VALUE_PARAMETER, //
                REPLACE_VALUE_PARAMETER, //
                REPLACE_ENTIRE_CELL_PARAMETER));
    }

    @Test
    public void should_accept_string_typed_column() {
        // given
        final ColumnMetadata column = new ColumnMetadata();
        column.setType(STRING.toString());

        // when
        final boolean accepted = action.acceptColumn(column);

        // then
        assertThat(accepted, is(true));
    }

    @Test
    public void should_reject_non_string_typed_column() {
        // given
        final ColumnMetadata column = new ColumnMetadata();
        column.setType(BOOLEAN.toString());

        // when
        final boolean accepted = action.acceptColumn(column);

        // then
        assertThat(accepted, is(false));
    }

    @Test
    public void should_replace_the_value_that_match_on_the_specified_column_entire() {
        // given
        final String columnId = "firstname";

        final Map<String, String> values = new HashMap<>();
        values.put(columnId, "James Hetfield");
        final DataSetRow row = new DataSetRow(values);

        final Map<String, String> parameters = new HashMap<>();
        parameters.put(CELL_VALUE_PARAMETER, generateJson("James", ReplaceOnValue.STARTS_WITH_MODE));
        parameters.put(REPLACE_VALUE_PARAMETER, "Jimmy");
        parameters.put(REPLACE_ENTIRE_CELL_PARAMETER, "true");
        parameters.put(ImplicitParameters.SCOPE.getKey().toLowerCase(), "column");
        parameters.put(ImplicitParameters.COLUMN_ID.getKey().toLowerCase(), columnId);

        // when
        ActionTestWorkbench.test(row, action.create(parameters).getRowAction());

        // then
        assertThat(row.get(columnId), is("Jimmy"));
    }

    /**
     * This test case covers all cases describe in TDP-951 description.
     */
    @Test
    public void test_TDP_951() {
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.EQUALS_MODE), "EN", false), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.EQUALS_MODE), "EN", true), "XXX_FR_YYY"));

        Assert.assertEquals("Barfoo", action.computeNewValue(buildPatternActionContext(generateJson("Foobar", ReplaceOnValue.EQUALS_MODE), "Barfoo", false), "Foobar"));
        Assert.assertEquals("Barfoo", action.computeNewValue(buildPatternActionContext(generateJson("Foobar", ReplaceOnValue.EQUALS_MODE), "Barfoo", true), "Foobar"));


        Assert.assertEquals("XXX_EN_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.CONTAINS_MODE), "EN", false), "XXX_FR_YYY"));
        Assert.assertEquals("EN", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.CONTAINS_MODE), "EN", true), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR_", ReplaceOnValue.CONTAINS_MODE), "", false), "XXX_FR_YYY"));

        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.STARTS_WITH_MODE), "EN", false), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.STARTS_WITH_MODE), "EN", true), "XXX_FR_YYY"));
        Assert.assertEquals("EN_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.STARTS_WITH_MODE), "EN", false), "FR_YYY"));
        Assert.assertEquals("EN", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.STARTS_WITH_MODE), "EN", true), "FR_YYY"));

        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.ENDS_WITH_MODE), "EN", false), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.ENDS_WITH_MODE), "EN", true), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_EN", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.ENDS_WITH_MODE), "EN", false), "XXX_FR"));
        Assert.assertEquals("EN", action.computeNewValue(buildPatternActionContext(generateJson("FR", ReplaceOnValue.ENDS_WITH_MODE), "EN", true), "XXX_FR"));

        // Nothing to do because doesn't match:
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FOO", ReplaceOnValue.EQUALS_MODE), "EN", false), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FOO", ReplaceOnValue.EQUALS_MODE), "EN", true), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FOO", ReplaceOnValue.CONTAINS_MODE), "EN", false), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FOO", ReplaceOnValue.CONTAINS_MODE), "EN", true), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FOO", ReplaceOnValue.STARTS_WITH_MODE), "EN", false), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FOO", ReplaceOnValue.STARTS_WITH_MODE), "EN", true), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FOO", ReplaceOnValue.ENDS_WITH_MODE), "EN", false), "XXX_FR_YYY"));
        Assert.assertEquals("XXX_FR_YYY", action.computeNewValue(buildPatternActionContext(generateJson("FOO", ReplaceOnValue.ENDS_WITH_MODE), "EN", true), "XXX_FR_YYY"));

        Assert.assertEquals("XXX_foobar_YYY", action.computeNewValue(buildPatternActionContext(generateJson("t.t.", ReplaceOnValue.REGEX_MODE), "foobar", false), "XXX_toto_YYY"));
        Assert.assertEquals("XXX_foobar_YYY", action.computeNewValue(buildPatternActionContext(generateJson("t.t.", ReplaceOnValue.REGEX_MODE), "foobar", false), "XXX_titi_YYY"));
        Assert.assertEquals("XXX_ZZ_YYY", action.computeNewValue(buildPatternActionContext(generateJson("_[a-z ]*_", ReplaceOnValue.REGEX_MODE), "_ZZ_", false), "XXX_this is a string_YYY"));
        Assert.assertEquals("foobar", action.computeNewValue(buildPatternActionContext(generateJson("t.t.", ReplaceOnValue.REGEX_MODE), "foobar", true), "XXX_toto_YYY"));
    }

    @Test
    public void should_NOT_replace_the_value_because_the_column_DOES_NOT_exist() {
        // given
        final String columnId = "firstname";

        final Map<String, String> values = new HashMap<>();
        values.put(columnId, "Toto");
        final DataSetRow row = new DataSetRow(values);

        final Map<String, String> parameters = new HashMap<>();
        parameters.put(CELL_VALUE_PARAMETER, generateJson("Toto", ReplaceOnValue.EQUALS_MODE));
        parameters.put(REPLACE_VALUE_PARAMETER, "Jimmy");
        parameters.put(REPLACE_ENTIRE_CELL_PARAMETER, "false");
        parameters.put(ImplicitParameters.SCOPE.getKey().toLowerCase(), "column");
        parameters.put(ImplicitParameters.COLUMN_ID.getKey().toLowerCase(), "no column here");

        // when
        ActionTestWorkbench.test(row, action.create(parameters).getRowAction());

        // then
        assertThat(row.get(columnId), is("Toto"));
    }

    @Test
    public void should_replace_the_value_that_match_on_the_specified_cell() {
        // given
        final String columnId = "firstname";

        final Map<String, String> values = new HashMap<>();
        values.put(columnId, "James");
        final DataSetRow row = new DataSetRow(values);
        row.setTdpId(85L);

        final Map<String, String> parameters = new HashMap<>();
        parameters.put(CELL_VALUE_PARAMETER, generateJson("James", ReplaceOnValue.EQUALS_MODE));
        parameters.put(REPLACE_VALUE_PARAMETER, "Jimmy");
        parameters.put(REPLACE_ENTIRE_CELL_PARAMETER, "false");
        parameters.put(ImplicitParameters.SCOPE.getKey().toLowerCase(), "cell");
        parameters.put(ImplicitParameters.COLUMN_ID.getKey().toLowerCase(), columnId);
        parameters.put(ImplicitParameters.ROW_ID.getKey().toLowerCase(), "85");

        // when
        ActionTestWorkbench.test(row, action.create(parameters).getRowAction());

        // then
        assertThat(row.get(columnId), is("Jimmy"));
    }

    @Test
    public void should_replace_value_based_on_regex() {
        //given
        final String from = "bridge.html?region=FR";
        final String regexp = "bridge.html[?]region=FR";
        final String to = "pont.html?region=FR";

        //when
        final String result = action.computeNewValue(buildPatternActionContext(generateJson(regexp, ReplaceOnValue.REGEX_MODE), to, false), from);

        //then
        assertThat(result, is(to));
    }

    @Test
    public void should_replace_many_values_that_match_on_the_specified_cell() {
        // given
        final String columnId = "firstname";

        final Map<String, String> values = new HashMap<>();
        values.put(columnId, "James Cleveland James");
        final DataSetRow row = new DataSetRow(values);
        row.setTdpId(85L);

        final Map<String, String> parameters = new HashMap<>();
        parameters.put(CELL_VALUE_PARAMETER, generateJson("James", ReplaceOnValue.CONTAINS_MODE));
        parameters.put(REPLACE_VALUE_PARAMETER, "Jimmy");
        parameters.put(REPLACE_ENTIRE_CELL_PARAMETER, "false");
        parameters.put(ImplicitParameters.SCOPE.getKey().toLowerCase(), "cell");
        parameters.put(ImplicitParameters.COLUMN_ID.getKey().toLowerCase(), columnId);
        parameters.put(ImplicitParameters.ROW_ID.getKey().toLowerCase(), "85");

        // when
        ActionTestWorkbench.test(row, action.create(parameters).getRowAction());

        // then
        assertThat(row.get(columnId), is("Jimmy Cleveland Jimmy"));
    }

    @Test
    public void should_NOT_replace_the_value_that_DOESNT_match_on_the_specified_cell() {
        // given
        final String columnId = "firstname";

        final Map<String, String> values = new HashMap<>();
        values.put(columnId, "Toto");
        values.put(DataSetRow.TDP_ID, "85");
        final DataSetRow row = new DataSetRow(values);

        final Map<String, String> parameters = new HashMap<>();
        parameters.put(CELL_VALUE_PARAMETER, generateJson("James", ReplaceOnValue.CONTAINS_MODE));
        parameters.put(REPLACE_VALUE_PARAMETER, "Jimmy");
        parameters.put(REPLACE_ENTIRE_CELL_PARAMETER, "false");
        parameters.put(ImplicitParameters.SCOPE.getKey().toLowerCase(), "cell");
        parameters.put(ImplicitParameters.COLUMN_ID.getKey().toLowerCase(), columnId);
        parameters.put(ImplicitParameters.ROW_ID.getKey().toLowerCase(), "85");

        // when
        ActionTestWorkbench.test(row, action.create(parameters).getRowAction());

        // then
        assertThat(row.get(columnId), is("Toto"));
    }

    @Test
    public void test_TDP_663() {
        for (String op : new String[] { ReplaceOnValue.REGEX_MODE, ReplaceOnValue.EQUALS_MODE, ReplaceOnValue.CONTAINS_MODE,
                ReplaceOnValue.STARTS_WITH_MODE, ReplaceOnValue.ENDS_WITH_MODE }) {
            Assert.assertEquals("password swordfish with Halle Berry", action.computeNewValue(
                    buildPatternActionContext(generateJson("*", op), "replaced", false), "password swordfish with Halle Berry"));
            Assert.assertEquals("password swordfish with Halle Berry", action.computeNewValue(
                    buildPatternActionContext(generateJson("*", op), "replaced", true), "password swordfish with Halle Berry"));
        }
    }

    @Test
    public void test_TDP_958_emptyPattern() {
        for (String op : new String[] { ReplaceOnValue.REGEX_MODE, ReplaceOnValue.EQUALS_MODE, ReplaceOnValue.CONTAINS_MODE,
                ReplaceOnValue.STARTS_WITH_MODE, ReplaceOnValue.ENDS_WITH_MODE }) {
            Assert.assertEquals("password swordfish with Halle Berry", action.computeNewValue(
                    buildPatternActionContext(generateJson("", op), "replaced", false), "password swordfish with Halle Berry"));
            Assert.assertEquals("password swordfish with Halle Berry", action.computeNewValue(
                    buildPatternActionContext(generateJson("", op), "replaced", true), "password swordfish with Halle Berry"));
        }
    }

    @Test
    public void test_TDP_958_invalidPattern() {
        for (String op : new String[] { ReplaceOnValue.REGEX_MODE, ReplaceOnValue.EQUALS_MODE, ReplaceOnValue.CONTAINS_MODE,
                ReplaceOnValue.STARTS_WITH_MODE, ReplaceOnValue.ENDS_WITH_MODE }) {
            Assert.assertEquals("password swordfish with Halle Berry", action.computeNewValue(
                    buildPatternActionContext(generateJson("^(", op), "replaced", false), "password swordfish with Halle Berry"));
            Assert.assertEquals("password swordfish with Halle Berry", action.computeNewValue(
                    buildPatternActionContext(generateJson("^(", op), "replaced", true), "password swordfish with Halle Berry"));
        }
    }


    private String generateJson(String token, String operator) {
        return "{\"" + ReplaceOnValue.TOKEN + "\":\"" + token + "\", \"" + ReplaceOnValue.OPERATOR + "\":\"" + operator + "\"}";
    }

}
