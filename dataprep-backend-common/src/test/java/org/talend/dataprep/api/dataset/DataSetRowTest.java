//  ============================================================================
//
//  Copyright (C) 2006-2018 Talend Inc. - www.talend.com
//
//  This source code is available under agreement available at
//  https://github.com/Talend/data-prep/blob/master/LICENSE
//
//  You should have received a copy of the agreement
//  along with this program; if not, write to Talend SA
//  9 rue Pages 92150 Suresnes, France
//
//  ============================================================================

package org.talend.dataprep.api.dataset;

import static org.junit.Assert.*;
import static org.talend.dataprep.api.dataset.ColumnMetadata.Builder.column;
import static org.talend.dataprep.api.dataset.row.Flag.*;
import static org.talend.dataprep.api.dataset.row.FlagNames.TDP_INVALID;

import java.util.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.talend.dataprep.api.dataset.row.DataSetRow;
import org.talend.dataprep.api.dataset.row.Flag;
import org.talend.dataprep.api.dataset.row.FlagNames;
import org.talend.dataprep.api.type.Type;

public class DataSetRowTest {

    @Test
    public void should_clear_row() {
        // Given
        DataSetRow row = createRow(defaultValues(), true);
        row = row.diff(createRow(defaultValues(), false));

        // When
        row = row.clear();

        // Then
        assertFalse(row.isDeleted());
        assertTrue(row.values().isEmpty());
    }

    @Test
    public void write_or_not_write_that_is_the_question() {
        // no old value not deleted --> write
        DataSetRow row = createRow(defaultValues(), false);
        assertTrue(row.shouldWrite());

        // no old value and deleted --> don't write
        row = row.setDeleted(true);
        assertFalse(row.shouldWrite());

        // old value and deleted --> write
        row = row.diff(createRow(defaultValues(), false));
        assertTrue(row.shouldWrite());

        // old deleted value and deleted --> don't write
        row = row.diff(createRow(defaultValues(), true));
        assertFalse(row.shouldWrite());

        // old deleted value and not deleted --> don't write
        row = row.setDeleted(false);
        assertTrue(row.shouldWrite());
    }

    /**
     * Test the new flag.
     */
    @Test
    public void diff_with_new_row_flag() {
        DataSetRow row = createRow(defaultValues(), false);
        DataSetRow oldRow = createRow(defaultValues(), true);

        DataSetRow diff = row.diff(oldRow);
        Map<String, Object> actual = diff.values();
        assertEquals(actual.get(FlagNames.ROW_DIFF_KEY), NEW.getValue());
    }

    /**
     * Test the delete flag.
     */
    @Test
    public void diff_with_delete_row_flag() {
        DataSetRow row = createRow(defaultValues(), true);
        DataSetRow oldRow = createRow(defaultValues(), false);

        DataSetRow diff = row.diff(oldRow);
        Map<String, Object> actual = diff.values();
        assertEquals(actual.get(FlagNames.ROW_DIFF_KEY), DELETE.getValue());
    }

    /**
     * test the update flag.
     */
    @Test
    public void diff_with_update_record_flag() {
        DataSetRow row = createRow(defaultValues(), false);

        final Map<String, String> oldValues = new HashMap<>(4);
        oldValues.put("id", "2");
        oldValues.put("firstName", "otoT");
        oldValues.put("lastName", "ataT");
        oldValues.put("age", "81");
        DataSetRow oldRow = createRow(oldValues, false);

        row = row.diff(oldRow);

        Map<String, Object> actual = row.values();
        Map<String, Object> diff = (Map<String, Object>) actual.get(FlagNames.DIFF_KEY);
        diff.values().forEach(value -> assertEquals(value, UPDATE.getValue()));
    }

    /**
     * test the new flag on records.
     */
    @Test
    public void diff_with_new_flag() {
        DataSetRow row = createRow(defaultValues(), false);

        Map<String, String> oldValues = new HashMap<>(2);
        oldValues.put("id", "1");
        oldValues.put("age", "18");
        DataSetRow oldRow = createRow(oldValues, false);

        row = row.diff(oldRow);

        Map<String, Object> diff = (Map<String, Object>) row.values().get(FlagNames.DIFF_KEY);

        // firstName and lastName are new
        List<String> expected = new ArrayList<>(2);
        expected.add("firstName");
        expected.add("lastName");

        for (String expectedKey : expected) {
            assertTrue(diff.containsKey(expectedKey));
            assertEquals(Flag.NEW.getValue(), diff.get(expectedKey));
        }
    }

    /**
     * test the delete flag on records.
     */
    @Test
    public void diff_with_delete_flag() {
        DataSetRow oldRow = createRow(defaultValues(), false);

        final Map<String, String> values = new HashMap<>(4);
        values.put("id", "1");
        values.put("age", "18");
        DataSetRow row = createRow(values, false);

        row = row.diff(oldRow);

        Map<String, Object> processedValues = row.values();
        Map<String, Object> diff = (Map<String, Object>) processedValues.get(FlagNames.DIFF_KEY);

        // firstName and lastName are new
        List<String> expected = new ArrayList<>(2);
        expected.add("firstName");
        expected.add("lastName");

        for (String expectedKey : expected) {
            // check the diff
            assertTrue(diff.containsKey(expectedKey));
            assertEquals(Flag.DELETE.getValue(), diff.get(expectedKey));
        }
    }

    @Test
    public void testOrder() throws Exception {
        DataSetRow row = createRow(defaultValues(), false);
        // Asserts on original order
        String[] expectedKeyOrder = { "age", "firstName", "id", "lastName" };
        final Iterator<String> keys = row.values().keySet().iterator();
        for (String expectedKey : expectedKeyOrder) {
            assertThat(keys.hasNext(), CoreMatchers.is(true));
            assertThat(keys.next(), CoreMatchers.is(expectedKey));
        }
        // ReorderColumn
        List<ColumnMetadata> newOrder = new ArrayList<ColumnMetadata>() {

            {
                add(column().computedId("firstName").type(Type.STRING).build());
                add(column().computedId("lastName").type(Type.STRING).build());
                add(column().computedId("id").type(Type.STRING).build());
                add(column().computedId("age").type(Type.STRING).build());
            }
        };
        final DataSetRow newRow = row.order(newOrder);
        String[] expectedNewKeyOrder = { "firstName", "lastName", "id", "age" };
        final Iterator<String> newOrderKeys = newRow.values().keySet().iterator();
        for (String expectedKey : expectedNewKeyOrder) {
            assertThat(newOrderKeys.hasNext(), CoreMatchers.is(true));
            assertThat(newOrderKeys.next(), CoreMatchers.is(expectedKey));
        }
    }

    @Test
    public void testNoOrder() throws Exception {
        DataSetRow row = createRow(defaultValues(), false);
        // ReorderColumn with 0 column is equivalent to clone().
        final DataSetRow order = row.order(Collections.emptyList());
        assertTrue(row == order);
        assertThat(row.values(), CoreMatchers.is(order.values()));
    }

    @Test
    public void testIncorrectOrder() throws Exception {
        DataSetRow row = createRow(defaultValues(), false);
        // ReorderColumn with 1 column should fail (not enough column, expected 4 columns).
        List<ColumnMetadata> newOrder = new ArrayList<ColumnMetadata>() {

            {
                add(column().type(Type.STRING).build());
            }
        };
        try {
            row.order(newOrder);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }
        // ReorderColumn with null column should fail
        try {
            row.order(null);
            fail();
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void should_return_values_with_tdp_id() {
        //given
        final DataSetRow row = createRow(defaultValues(), false).setTdpId(1L);

        //when
        final Map<String, Object> values = row.valuesWithId();

        //then
        assertThat(values.get("tdpId"), CoreMatchers.is(1L));
    }

    /**
     * @param values the values of the row to return.
     * @param isDeleted true if the row is deleted.
     * @return a new dataset row with the given values.
     */
    private DataSetRow createRow(final Map<String, String> values, final boolean isDeleted) {
        return new DataSetRow(values).setDeleted(isDeleted);
    }

    /**
     * @return some default values.
     */
    private Map<String, String> defaultValues() {
        final Map<String, String> values = new HashMap<>(4);
        values.put("id", "1");
        values.put("firstName", "Toto");
        values.put("lastName", "Tata");
        values.put("age", "18");
        return values;
    }

    @Test
    public void testToArrayNoFilter() throws Exception {
        final Map<String, String> values = new HashMap<>(4);
        values.put("column0", "1");
        values.put("column1", "2");
        final DataSetRow row = new DataSetRow(values);
        final String[] strings = row.toArray();
        assertEquals(2, strings.length);
        assertEquals("1", strings[0]);
        assertEquals("2", strings[1]);
    }

    @Test
    public void testToArrayFilterTdpId() throws Exception {
        final Map<String, String> values = new HashMap<>(4);
        values.put(FlagNames.TDP_ID, "1");
        values.put("column0", "2");
        values.put("column1", "3");
        final DataSetRow row = new DataSetRow(values);
        final String[] strings = row.toArray(DataSetRow.SKIP_TDP_ID);
        assertEquals(2, strings.length);
        assertEquals("2", strings[0]);
        assertEquals("3", strings[1]);
    }

    @Test
    public void testToArrayMultipleFilters() throws Exception {
        final Map<String, String> values = new HashMap<>(4);
        values.put(FlagNames.TDP_ID, "1");
        values.put("column0", "2");
        values.put("column1", "3");
        final DataSetRow row = new DataSetRow(values);
        final String[] strings = row.toArray(DataSetRow.SKIP_TDP_ID, e -> !"column1".equals(e.getKey()));
        assertEquals(1, strings.length);
        assertEquals("2", strings[0]);
    }

    @Test
    public void testFilter() throws Exception {
        DataSetRow row = createRow(defaultValues(), false);
        List<ColumnMetadata> filter = new ArrayList<ColumnMetadata>() {

            {
                add(column().computedId("age").type(Type.STRING).build());
                add(column().computedId("firstName").type(Type.STRING).build());
            }
        };
        assertThat(row.values().size(), CoreMatchers.is(4));
        final DataSetRow newRow = row.filter(filter);
        assertThat(newRow.values().size(), CoreMatchers.is(2));
    }

    @Test
    public void should_set_invalid_column() throws Exception {
        // given
        final Map<String, String> values = new HashMap<>();
        DataSetRow row = new DataSetRow(values);

        // when
        row = row.setInvalid("0001").setInvalid("0004");

        // then
        assertThat(row.values().get(TDP_INVALID), CoreMatchers.is("0004,0001"));
    }

    @Test
    public void should_unset_invalid_column() throws Exception {
        // given
        final Map<String, String> values = new HashMap<>();
        DataSetRow row = new DataSetRow(values).setInvalid("0001").setInvalid("0004");

        // when
        row = row.unsetInvalid("0004");

        // then
        assertThat(row.values().get(TDP_INVALID), CoreMatchers.is("0001"));
    }

    @Test
    public void should_parse_invalid_columns_on_creation() throws Exception {
        // given
        final Map<String, String> values = new HashMap<>();
        values.put(TDP_INVALID, "0001,0004");

        // when
        final DataSetRow row = new DataSetRow(values);

        // then
        assertThat(row.values().get(TDP_INVALID), CoreMatchers.is("0004,0001"));
    }
}
