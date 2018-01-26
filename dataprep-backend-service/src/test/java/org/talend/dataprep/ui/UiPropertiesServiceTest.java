package org.talend.dataprep.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 * Unit test for the {@link org.talend.dataprep.ui.UiPropertiesService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class UiPropertiesServiceTest {

    private static final String DATASET_UI_CONFIGURATION_REMOTE_ENABLED = "dataprep.ui.configuration.remote.enabled";

    @InjectMocks
    private UiPropertiesService service;

    @Mock
    private ConditionContext context;

    private void givenProperty(String returnValue) {
        Environment environment = mock(Environment.class);
        when(context.getEnvironment()).thenReturn(environment);
        when(environment.getProperty(DATASET_UI_CONFIGURATION_REMOTE_ENABLED)).thenReturn(returnValue);
    }

    @Test
    public void shouldEnableServiceIfRelatedRemotePropertyIsMissing() {
        // given
        givenProperty(null);

        // when
        final boolean matches = service.matches(context, null);

        // then
        assertTrue(matches);
    }

    @Test
    public void shouldEnableServiceIfRelatedRemotePropertyIsFalse() {
        // given
        givenProperty("false");

        // when
        final boolean matches = service.matches(context, null);

        // then
        assertTrue(matches);
    }

    @Test
    public void shouldDisableServiceIfRelatedRemotePropertyIsTrue() {
        // given
        givenProperty("true");

        // when
        final boolean matches = service.matches(context, null);

        // then
        assertFalse(matches);
    }

    @Test
    public void shouldHasTheme() {
        //given
        setField(service, "theme", Boolean.TRUE);

        // when
        final boolean hasTheme = service.hasTheme();

        // then
        assertThat(hasTheme, is(Boolean.TRUE));
    }

    @Test
    public void shouldNotHasTheme() {
        // given
        setField(service, "theme", Boolean.FALSE);

        // when
        final boolean hasTheme = service.hasTheme();

        // then
        assertThat(hasTheme, is(Boolean.FALSE));
    }
}
