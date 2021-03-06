// ============================================================================
//
// Copyright (C) 2006-2018 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// https://github.com/Talend/data-prep/blob/master/LICENSE
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================

package org.talend.dataprep.transformation.actions.net;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.talend.dataprep.api.dataset.ColumnMetadata.Builder.column;
import static org.talend.dataprep.transformation.actions.ActionMetadataTestUtils.getColumn;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.talend.dataprep.api.action.ActionDefinition;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.RowMetadata;
import org.talend.dataprep.api.dataset.row.DataSetRow;
import org.talend.dataprep.api.type.Type;
import org.talend.dataprep.transformation.actions.AbstractMetadataBaseTest;
import org.talend.dataprep.transformation.actions.ActionMetadataTestUtils;
import org.talend.dataprep.transformation.actions.category.ActionCategory;
import org.talend.dataprep.transformation.api.action.ActionTestWorkbench;

/**
 * Test class for ExtractEmailDomain action. Creates one consumer, and test it.
 *
 * @see ExtractEmailDomain
 */
public class ExtractUrlTokensTest extends AbstractMetadataBaseTest<ExtractUrlTokens> {

    private Map<String, String> parameters;

    public ExtractUrlTokensTest() {
        super(new ExtractUrlTokens());
    }

    @Before
    public void init() throws IOException {
        parameters = ActionMetadataTestUtils
                .parseParameters(ExtractUrlTokensTest.class.getResourceAsStream("extractUrlTokensAction.json"));
    }

    @Test
    public void testAdapt() throws Exception {
        assertThat(action.adapt((ColumnMetadata) null), is(action));
        ColumnMetadata column = column().name("myColumn").id(0).type(Type.STRING).build();
        assertThat(action.adapt(column), is(action));
    }

    @Test
    public void testCategory() throws Exception {
        assertThat(action.getCategory(Locale.US), is(ActionCategory.SPLIT.getDisplayName(Locale.US)));
    }

    @Override
    protected  CreateNewColumnPolicy getCreateNewColumnPolicy(){
        return CreateNewColumnPolicy.INVISIBLE_ENABLED;
    }

    @Test
    public void test_apply_inplace() throws Exception {
        // Nothing to test, this action is never applied in place
    }

    @Test
    public void test_apply_in_newcolumn() {
        // given
        final Map<String, String> values = new HashMap<>();
        values.put("0000", "lorem bacon");
        values.put("0001", "http://www.talend.com");
        values.put("0002", "01/01/2015");
        final DataSetRow row = new DataSetRow(values);

        final Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("0000", "lorem bacon");
        expectedValues.put("0001", "http://www.talend.com");
        expectedValues.put("0003", "http");
        expectedValues.put("0004", "www.talend.com");
        expectedValues.put("0005", "");
        expectedValues.put("0006", "");
        expectedValues.put("0007", "");
        expectedValues.put("0008", "");
        expectedValues.put("0009", "");
        expectedValues.put("0010", "");
        expectedValues.put("0002", "01/01/2015");

        // when
        ActionTestWorkbench.test(row, actionRegistry, factory.create(action, parameters));

        // then
        assertEquals(expectedValues, row.values());
    }

    @Test
    public void test_values_port_as_int() {
        // given
        final Map<String, String> values = new HashMap<>();
        values.put("0000", "lorem bacon");
        values.put("0001", "http://stef:pwd@10.42.10.99:80/home/datasets?datasetid=c522a037-7bd8-42c1-a8ee-a0628c66d8c4#frag");
        values.put("0002", "01/01/2015");
        final DataSetRow row = new DataSetRow(values);

        final Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("0000", "lorem bacon");
        expectedValues.put("0001",
                "http://stef:pwd@10.42.10.99:80/home/datasets?datasetid=c522a037-7bd8-42c1-a8ee-a0628c66d8c4#frag");
        expectedValues.put("0003", "http");
        expectedValues.put("0004", "10.42.10.99");
        expectedValues.put("0005", "80");
        expectedValues.put("0006", "/home/datasets");
        expectedValues.put("0007", "datasetid=c522a037-7bd8-42c1-a8ee-a0628c66d8c4");
        expectedValues.put("0008", "frag");
        expectedValues.put("0009", "stef");
        expectedValues.put("0010", "pwd");
        expectedValues.put("0002", "01/01/2015");

        // when
        ActionTestWorkbench.test(row, actionRegistry, factory.create(action, parameters));

        // then
        assertEquals(expectedValues, row.values());
    }

    @Test
    public void test_invalid_values() {
        // given
        final Map<String, String> values = new HashMap<>();
        values.put("0000", "lorem bacon");
        values.put("0001", "http_www.talend.com");
        values.put("0002", "01/01/2015");
        final DataSetRow row = new DataSetRow(values);

        final Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("0000", "lorem bacon");
        expectedValues.put("0001", "http_www.talend.com");
        expectedValues.put("0003", "");
        expectedValues.put("0004", "");
        expectedValues.put("0005", "");
        expectedValues.put("0006", "http_www.talend.com");
        expectedValues.put("0007", "");
        expectedValues.put("0008", "");
        expectedValues.put("0009", "");
        expectedValues.put("0010", "");
        expectedValues.put("0002", "01/01/2015");

        // when
        ActionTestWorkbench.test(row, actionRegistry, factory.create(action, parameters));

        // then
        assertEquals(expectedValues, row.values());
    }

    @Test
    public void test_empty_values() {
        // given
        final Map<String, String> values = new HashMap<>();
        values.put("0000", "lorem bacon");
        values.put("0001", "");
        values.put("0002", "01/01/2015");
        final DataSetRow row = new DataSetRow(values);

        final Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("0000", "lorem bacon");
        expectedValues.put("0001", "");
        expectedValues.put("0003", "");
        expectedValues.put("0004", "");
        expectedValues.put("0005", "");
        expectedValues.put("0006", "");
        expectedValues.put("0007", "");
        expectedValues.put("0008", "");
        expectedValues.put("0009", "");
        expectedValues.put("0010", "");

        expectedValues.put("0002", "01/01/2015");

        // when
        ActionTestWorkbench.test(row, actionRegistry, factory.create(action, parameters));

        // then
        assertEquals(expectedValues, row.values());
    }

    @Test
    public void test_metadata() {
        // given
        final List<ColumnMetadata> input = new ArrayList<>();
        input.add(createMetadata("0000", "recipe"));
        input.add(createMetadata("0001", "url"));
        input.add(createMetadata("0002", "last update"));
        final DataSetRow row = new DataSetRow(new RowMetadata(input));

        final List<ColumnMetadata> expected = new ArrayList<>();
        expected.add(createMetadata("0000", "recipe"));
        expected.add(createMetadata("0001", "url"));
        expected.add(createMetadata("0003", "url_protocol"));
        expected.add(createMetadata("0004", "url_host"));
        expected.add(createMetadata("0005", "url_port", Type.INTEGER));
        expected.add(createMetadata("0006", "url_path"));
        expected.add(createMetadata("0007", "url_query"));
        expected.add(createMetadata("0008", "url_fragment"));
        expected.add(createMetadata("0009", "url_user"));
        expected.add(createMetadata("0010", "url_password"));
        expected.add(createMetadata("0002", "last update"));

        // when
        ActionTestWorkbench.test(row, actionRegistry, factory.create(action, parameters));

        // then
        assertEquals(expected, row.getRowMetadata().getColumns());
    }

    @Override
    protected ColumnMetadata.Builder columnBaseBuilder() {
        return super.columnBaseBuilder().headerSize(12).valid(5).invalid(2).empty(0);
    }

    @Test
    public void should_accept_column() {
        ColumnMetadata column = getColumn(Type.STRING);
        column.setDomain("url");
        assertTrue(action.acceptField(column));
    }

    @Test
    public void should_not_accept_column() {
        assertFalse(action.acceptField(getColumn(Type.STRING)));
        assertFalse(action.acceptField(getColumn(Type.DATE)));
        assertFalse(action.acceptField(getColumn(Type.BOOLEAN)));
        assertFalse(action.acceptField(getColumn(Type.NUMERIC)));
        assertFalse(action.acceptField(getColumn(Type.INTEGER)));
        assertFalse(action.acceptField(getColumn(Type.DOUBLE)));
        assertFalse(action.acceptField(getColumn(Type.FLOAT)));

        ColumnMetadata column = getColumn(Type.STRING);
        column.setDomain("not an url");
        assertFalse(action.acceptField(column));
    }

    @Test
    public void testProtocolExtractor() throws URISyntaxException {
        assertEquals("http", UrlTokenExtractors.PROTOCOL_TOKEN_EXTRACTOR.extractToken(new URI("http://www.yahoo.fr")));
        assertEquals("mailto", UrlTokenExtractors.PROTOCOL_TOKEN_EXTRACTOR.extractToken(new URI("mailto:smallet@talend.com")));
        assertEquals("ftp",
                UrlTokenExtractors.PROTOCOL_TOKEN_EXTRACTOR.extractToken(new URI("ftp://server:21/this/is/a/resource")));
        assertEquals("http", UrlTokenExtractors.PROTOCOL_TOKEN_EXTRACTOR.extractToken(new URI("HTTP://www.yahoo.fr")));
        assertEquals("http", UrlTokenExtractors.PROTOCOL_TOKEN_EXTRACTOR
                .extractToken(new URI("http:10.42.10.99:80/home/datasets?datasetid=c522a037")));
        assertEquals("file",
                UrlTokenExtractors.PROTOCOL_TOKEN_EXTRACTOR.extractToken(new URI("file://server:21/this/is/a/resource")));
        assertEquals("mvn",
                UrlTokenExtractors.PROTOCOL_TOKEN_EXTRACTOR.extractToken(new URI("mvn://server:21/this/is/a/resource")));
        assertEquals("tagada",
                UrlTokenExtractors.PROTOCOL_TOKEN_EXTRACTOR.extractToken(new URI("tagada://server:21/this/is/a/resource")));
    }

    @Test
    public void should_have_expected_behavior() {
        assertEquals(1, action.getBehavior().size());
        assertTrue(action.getBehavior().contains(ActionDefinition.Behavior.METADATA_CREATE_COLUMNS));
    }

}
