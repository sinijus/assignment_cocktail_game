package com.ridango.game.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class GameShutdownService {

    @Autowired
    private ConfigurableApplicationContext context;

    public void shutdownApplication() {
        System.out.println("Shutting down application...");
        context.close();
    }
}