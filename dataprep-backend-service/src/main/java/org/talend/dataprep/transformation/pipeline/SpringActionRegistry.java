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

package org.talend.dataprep.transformation.pipeline;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.talend.dataprep.transformation.actions.ActionDefinition;
import org.talend.dataprep.transformation.actions.ActionRegistry;

@Component
public class SpringActionRegistry implements ActionRegistry { // NOSONAR

    @Autowired(required = false)
    private List<ActionDefinition> actions;

    @Override
    public ActionDefinition get(String name) {
        for (ActionDefinition action : actions) {
            if (action.getName().equals(name)) {
                return action;
            }
        }
        return null;
    }

    @Override
    public Stream<Class<? extends ActionDefinition>> getAll() {
        return actions.stream().map(ActionDefinition::getClass);
    }

    @Override
    public Stream<ActionDefinition> findAll() {
        return actions.stream();
    }
}
