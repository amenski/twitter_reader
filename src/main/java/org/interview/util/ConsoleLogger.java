package org.interview.util;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class ConsoleLogger extends Logger {
    
    private static final Map<String, OutputStreamWriter> WRITTERS = new HashMap<>();
    
    public ConsoleLogger() {}

    @Override
    public void info(final String message) {
        try {
            if(!WRITTERS.containsKey("info")) {
                writter = new OutputStreamWriter(System.out, "UTF-8");
                WRITTERS.put("info", writter);
            }
            writter = WRITTERS.get("info");
            writter.write(message + "\n");
            writter.flush();
        } catch (Exception e) {
           System.err.println("Unable to write log to console.");
        }
    }

    @Override
    public void error(final String message) {
        try {
            if (!WRITTERS.containsKey("error")) {
                writter = new OutputStreamWriter(System.out, "UTF-8");
                WRITTERS.put("error", writter);
            }
            writter = WRITTERS.get("error");
            writter.write(message + "\n");
            writter.flush();
        } catch (Exception e) {
            System.err.println("Unable to write log to console.");
        }
    }
}
