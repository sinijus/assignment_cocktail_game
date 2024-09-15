package com.ridango.game.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class GameShutdownServiceTest {

    @Mock
    private ConfigurableApplicationContext context;

    @InjectMocks
    private GameShutdownService gameShutdownService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShutdownApplication() {
        gameShutdownService.shutdownApplication();
        verify(context, times(1)).close();
    }
}
