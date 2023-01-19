package org.project.websource.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.log4j.Logger;

public class ContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce){
        log.info("Cinema Service was started.");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        log.info("Cinema Service was closed.");
    }
}
