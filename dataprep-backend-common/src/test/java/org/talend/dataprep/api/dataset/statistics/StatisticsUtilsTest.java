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

package org.talend.dataprep.api.dataset.statistics;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.statistics.number.StreamNumberHistogramStatistics;
import org.talend.dataprep.api.type.Type;
import org.talend.dataprep.dataset.StatisticsAdapter;
import org.talend.dataquality.common.inference.Analyzers;
import org.talend.dataquality.common.inference.ValueQualityStatistics;
import org.talend.dataquality.semantic.recognizer.CategoryFrequency;
import org.talend.dataquality.semantic.statistics.SemanticType;
import org.talend.dataquality.statistics.cardinality.CardinalityStatistics;
import org.talend.dataquality.statistics.frequency.DataTypeFrequencyStatistics;
import org.talend.dataquality.statistics.frequency.pattern.PatternFrequencyStatistics;
import org.talend.dataquality.statistics.numeric.quantile.QuantileStatistics;
import org.talend.dataquality.statistics.numeric.summary.SummaryStatistics;
import org.talend.dataquality.statistics.text.TextLengthStatistics;
import org.talend.dataquality.statistics.type.DataTypeEnum;
import org.talend.dataquality.statistics.type.DataTypeOccurences;

public class StatisticsUtilsTest {

    private ColumnMetadata stringColumn;

    private ColumnMetadata integerColumn;

    @Before
    public void setUp() throws Exception {
        stringColumn = ColumnMetadata.Builder.column().type(Type.STRING).name("col0").build();
        integerColumn = ColumnMetadata.Builder.column().type(Type.INTEGER).name("col1").build();
    }

    @Test
    public void testDataType() throws Exception {
        //when
        adaptColumn(stringColumn, DataTypeEnum.INTEGER);

        //then
        assertEquals(Type.INTEGER.getName(), stringColumn.getType());
    }

    @Test
    public void testSemanticType() throws Exception {
        //when
        adaptColumn(stringColumn, DataTypeEnum.STRING);

        //then
        assertEquals("category 1", stringColumn.getDomain());
        assertEquals("category 1", stringColumn.getDomainLabel());
    }

    @Test
    public void testSemanticDomains() throws Exception {
        //when
        adaptColumn(stringColumn, DataTypeEnum.STRING);

        //then
        assertEquals(3, stringColumn.getSemanticDomains().size());
        assertEquals("category 1", stringColumn.getSemanticDomains().get(0).getId());
        assertEquals("category 2", stringColumn.getSemanticDomains().get(1).getId());
        assertEquals("category 3", stringColumn.getSemanticDomains().get(2).getId());
    }

    @Test
    public void testValue() throws Exception {
        //when
        adaptColumn(stringColumn, DataTypeEnum.STRING);

        //then
        assertEquals(10, stringColumn.getStatistics().getEmpty());
        // String columns can't have invalid values but adapter code is not supposed to take account.
        assertEquals(20, stringColumn.getStatistics().getInvalid());
        assertEquals(30, stringColumn.getStatistics().getValid());
    }

    @Test
    public void testCardinality() throws Exception {
        //when
        adaptColumn(stringColumn, DataTypeEnum.STRING);

        //then
        assertEquals(1, stringColumn.getStatistics().getDistinctCount());
        assertEquals(0, stringColumn.getStatistics().getDuplicateCount());
    }

    @Test
    public void testDataFrequency() throws Exception {
        //when
        adaptColumn(stringColumn, DataTypeEnum.STRING);

        //then
        assertEquals("frequentValue2", stringColumn.getStatistics().getDataFrequencies().get(0).data);
        assertEquals(2, stringColumn.getStatistics().getDataFrequencies().get(0).occurrences);
        assertEquals("frequentValue1", stringColumn.getStatistics().getDataFrequencies().get(1).data);
        assertEquals(2, stringColumn.getStatistics().getDataFrequencies().get(1).occurrences);
    }

    @Test
    public void testPatternFrequency() throws Exception {
        //when
        adaptColumn(stringColumn, DataTypeEnum.STRING);

        //then
        assertEquals("999aaaa", stringColumn.getStatistics().getPatternFrequencies().get(0).getPattern());
        assertEquals(2, stringColumn.getStatistics().getPatternFrequencies().get(0).occurrences);
        assertEquals("999a999", stringColumn.getStatistics().getPatternFrequencies().get(1).getPattern());
        assertEquals(2, stringColumn.getStatistics().getPatternFrequencies().get(1).occurrences);
    }

    @Test
    public void testQuantiles() throws Exception {
        //when
        adaptColumn(integerColumn, DataTypeEnum.INTEGER);

        //then
        assertEquals(1.0, integerColumn.getStatistics().getQuantiles().getLowerQuantile(), 0);
        assertEquals(1.5, integerColumn.getStatistics().getQuantiles().getMedian(), 0);
        assertEquals(2.0, integerColumn.getStatistics().getQuantiles().getUpperQuantile(), 0);
    }

    @Test
    public void testSummary() throws Exception {
        //when
        adaptColumn(integerColumn, DataTypeEnum.INTEGER);

        //then
        assertEquals(1.0, integerColumn.getStatistics().getMin(), 0);
        assertEquals(1.5, integerColumn.getStatistics().getMean(), 0);
        assertEquals(2.0, integerColumn.getStatistics().getMax(), 0);
    }

    @Test
    public void testHistogram() throws Exception {
        //when
        adaptColumn(integerColumn, DataTypeEnum.INTEGER);

        //then
        final List<HistogramRange> histogramRanges = integerColumn.getStatistics().getHistogram().getItems();
        assertEquals(2, histogramRanges .size());
        assertEquals(1, histogramRanges.get(0).getOccurrences());
        assertEquals(1, histogramRanges.get(0).getRange().getMin(), 0);
        assertEquals(1, histogramRanges.get(0).getRange().getMax(), 0);
        assertEquals(1, histogramRanges.get(1).getOccurrences());
        assertEquals(2, histogramRanges.get(1).getRange().getMin(), 0);
        assertEquals(2, histogramRanges.get(1).getRange().getMax(), 0);
    }

    @Test
    public void testTextLengthSummary() throws Exception {
        //when
        adaptColumn(stringColumn, DataTypeEnum.STRING);

        //then
        assertEquals(10, stringColumn.getStatistics().getTextLengthSummary().getMinimalLength(), 0);
        assertEquals(30, stringColumn.getStatistics().getTextLengthSummary().getMaximalLength(), 0);
        assertEquals(8, stringColumn.getStatistics().getTextLengthSummary().getAverageLength(), 0);
    }

    private void adaptColumn(final ColumnMetadata column, final DataTypeEnum type) {
        Analyzers.Result result = new Analyzers.Result();

        // Data type
        DataTypeOccurences dataType = new DataTypeOccurences();
        dataType.increment(type);
        result.add(dataType);

        // Semantic type
        SemanticType semanticType = new SemanticType();
        CategoryFrequency category1 = new CategoryFrequency("category 1", "category 1");
        category1.setScore(99);
        semanticType.increment(category1, 1);
        result.add(semanticType);

        // Suggested types
        CategoryFrequency category2 = new CategoryFrequency("category 2", "category 2");
        category2.setScore(81);
        semanticType.increment(category2, 1);
        CategoryFrequency category3 = new CategoryFrequency("category 3", "category 3");
        category3.setScore(50);
        semanticType.increment(category3, 1);

        // Value quality
        ValueQualityStatistics valueQualityStatistics = new ValueQualityStatistics();
        valueQualityStatistics.setEmptyCount(10);
        valueQualityStatistics.setInvalidCount(20);
        valueQualityStatistics.setValidCount(30);
        result.add(valueQualityStatistics);

        // Cardinality
        CardinalityStatistics cardinalityStatistics = new CardinalityStatistics();
        cardinalityStatistics.incrementCount();
        cardinalityStatistics.add("distinctValue");
        result.add(cardinalityStatistics);

        // Data frequency
        DataTypeFrequencyStatistics dataFrequencyStatistics = new DataTypeFrequencyStatistics();
        dataFrequencyStatistics.add("frequentValue1");
        dataFrequencyStatistics.add("frequentValue1");
        dataFrequencyStatistics.add("frequentValue2");
        dataFrequencyStatistics.add("frequentValue2");
        result.add(dataFrequencyStatistics);

        // Pattern frequency
        PatternFrequencyStatistics patternFrequencyStatistics = new PatternFrequencyStatistics();
        patternFrequencyStatistics.add("999a999");
        patternFrequencyStatistics.add("999a999");
        patternFrequencyStatistics.add("999aaaa");
        patternFrequencyStatistics.add("999aaaa");
        result.add(patternFrequencyStatistics);

        // Quantiles
        QuantileStatistics quantileStatistics = new QuantileStatistics();
        quantileStatistics.add(1d);
        quantileStatistics.add(2d);
        quantileStatistics.endAddValue();
        result.add(quantileStatistics);

        // Summary
        SummaryStatistics summaryStatistics = new SummaryStatistics();
        summaryStatistics.addData(1d);
        summaryStatistics.addData(2d);
        result.add(summaryStatistics);

        // Histogram
        StreamNumberHistogramStatistics histogramStatistics = new StreamNumberHistogramStatistics();
        histogramStatistics.setNumberOfBins(2);
        histogramStatistics.add(1);
        histogramStatistics.add(2);
        result.add(histogramStatistics);

        // Text length
        TextLengthStatistics textLengthStatistics = new TextLengthStatistics();
        textLengthStatistics.setMaxTextLength(30);
        textLengthStatistics.setMinTextLength(10);
        textLengthStatistics.setSumTextLength(40);
        textLengthStatistics.setCount(5);
        result.add(textLengthStatistics);

        StatisticsAdapter adapter = new StatisticsAdapter(40);
        adapter.adapt(Collections.singletonList(integerColumn), Collections.singletonList(result));
        adapter.adapt(Collections.singletonList(stringColumn), Collections.singletonList(result));
    }
}
