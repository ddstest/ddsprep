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

package org.talend.dataprep.transformation.pipeline.node;

import java.util.function.Predicate;

import org.talend.dataprep.api.dataset.ColumnMetadata;

abstract class ColumnFilteredNode extends BasicNode {

    protected final Predicate<? super ColumnMetadata> filter;

    ColumnFilteredNode(Predicate<? super ColumnMetadata> filter) {
        this.filter = filter;
    }

    public Predicate<? super ColumnMetadata> getFilter() {
        return filter;
    }
}
