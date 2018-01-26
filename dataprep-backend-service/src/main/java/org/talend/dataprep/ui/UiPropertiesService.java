// ============================================================================
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

package org.talend.dataprep.ui;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.slf4j.LoggerFactory.getLogger;

@Component
@Conditional(UiPropertiesService.class)
public class UiPropertiesService implements UiService, Condition {

    /**
     * This class' logger.
     */
    private static final Logger LOGGER = getLogger(UiPropertiesService.class);

    @Value("${dataprep.ui.theme.enabled:false}")
    private boolean theme;

    @PostConstruct
    public void init() {
        LOGGER.info("UI configuration is retrieved from properties (dataprep.ui.theme.enabled)");
    }

    public boolean hasTheme() {
        return theme;
    }

    /**
     * @return 'dataset.ui.configuration.remote.enabled' not equals 'true'
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final String property = context.getEnvironment().getProperty("dataprep.ui.configuration.remote.enabled");
        return !StringUtils.equals("true", property);
    }
}
