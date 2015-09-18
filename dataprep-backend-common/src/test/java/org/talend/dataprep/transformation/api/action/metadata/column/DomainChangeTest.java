package org.talend.dataprep.transformation.api.action.metadata.column;

import org.junit.Before;
import org.junit.Test;
import org.talend.dataprep.api.dataset.ColumnMetadata;
import org.talend.dataprep.api.dataset.DataSetRow;
import org.talend.dataprep.api.dataset.RowMetadata;
import org.talend.dataprep.api.type.Type;
import org.talend.dataprep.transformation.api.action.context.TransformationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.talend.dataprep.transformation.api.action.metadata.column.DomainChange.*;

public class DomainChangeTest {
    private DomainChange domainChange;
    private ColumnMetadata columnMetadata;
    private TransformationContext transformationContext;

    @Before
    public void init() {
        domainChange = new DomainChange();
        transformationContext = new TransformationContext();
        columnMetadata = ColumnMetadata.Builder.column() //
                .type(Type.INTEGER) //
                .computedId("0002") //
                .domain("FR_BEER") //
                .domainFrequency(1) //
                .domainLabel("French Beer") //
                .build();
    }

    @Test
    public void should_change_domain() throws Exception {
        // given
        final Map<String, String> values = new HashMap<>();
        values.put("0001", "David Bowie");
        values.put("0002", "");
        values.put("0003", "Something");

        final RowMetadata rowMetadata = new RowMetadata();
        rowMetadata.setColumns(singletonList(columnMetadata));

        final DataSetRow row = new DataSetRow(values);
        row.setRowMetadata(rowMetadata);

        final Map<String, String> parameters = new HashMap<>();
        parameters.put(NEW_DOMAIN_FREQUENCY_PARAMETER_KEY, "98");
        parameters.put(NEW_DOMAIN_ID_PARAMETER_KEY, "AUS_BEER");
        parameters.put(NEW_DOMAIN_LABEL_PARAMETER_KEY, "Aussie Beer");

        // when
        domainChange.applyOnColumn(row, transformationContext, parameters, "0002");

        // then
        final ColumnMetadata column = row.getRowMetadata().getColumns().get(0);
        assertThat(column.getDomain()).isEqualTo("AUS_BEER");
        assertThat(column.getDomainLabel()).isEqualTo("Aussie Beer");
        assertThat(column.getDomainFrequency()).isEqualTo(98);
    }

    @Test
    public void should_set_column_in_forced_columns() throws Exception {
        // given
        final Map<String, String> values = new HashMap<>();
        values.put("0001", "David Bowie");
        values.put("0002", "");
        values.put("0003", "Something");

        final RowMetadata rowMetadata = new RowMetadata();
        rowMetadata.setColumns(singletonList(columnMetadata));

        final DataSetRow row = new DataSetRow(values);
        row.setRowMetadata(rowMetadata);

        final Map<String, String> parameters = new HashMap<>();
        parameters.put(NEW_DOMAIN_FREQUENCY_PARAMETER_KEY, "98");
        parameters.put(NEW_DOMAIN_ID_PARAMETER_KEY, "AUS_BEER");
        parameters.put(NEW_DOMAIN_LABEL_PARAMETER_KEY, "Aussie Beer");

        // when
        domainChange.applyOnColumn(row, transformationContext, parameters, "0002");

        // then
        final Set<String> forcedColumns = domainChange.getForcedColumns(transformationContext);
        assertThat(forcedColumns).isNotNull().isNotEmpty().hasSize(1).contains("0002");
    }

    @Test
    public void should_not_accept_any_type_to_avoid_transformation_to_be_in_transfo_list() {
        //given
        final DomainChange domainChange = new DomainChange();
        for (final Type type : Type.values()) {

            final ColumnMetadata columnMetadata = ColumnMetadata.Builder.column() //
                    .type(type) //
                    .computedId("0002") //
                    .domain("FR_BEER") //
                    .domainFrequency(1) //
                    .domainLabel("French Beer") //
                    .build();

            //when
            final boolean accepted = domainChange.acceptColumn(columnMetadata);

            //then
            assertThat(accepted).isFalse();
        }
    }
}
