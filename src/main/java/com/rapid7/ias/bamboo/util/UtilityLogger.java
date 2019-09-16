package com.rapid7.ias.bamboo.util;

import com.atlassian.bamboo.build.logger.BuildLogger;
import org.apache.log4j.Logger;

public class UtilityLogger {

    private Logger logger;
    private BuildLogger buildLogger;

    public UtilityLogger(Logger logger) {
        this.logger = logger;
    }

    public UtilityLogger(Logger logger, BuildLogger buildlogger) {
        this.logger = logger;
        this.buildLogger = buildlogger;
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void info(String message) {
        logger.info(message);
        if (this.buildLogger != null) buildLogger.addBuildLogEntry(message);
    }

    public void error(String message) {
        logger.error(message);
        if (this.buildLogger != null) buildLogger.addErrorLogEntry(message);
    }
}