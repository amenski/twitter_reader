package org.interview.util;

public class LoggerFactory {

    public static Logger getLogger() {
        return getLogger("console");
    }
    
    public static Logger getLogger(final String type) {
        switch (type) {
        case "console":
        default:
            return new ConsoleLogger();
        }
    }
}
