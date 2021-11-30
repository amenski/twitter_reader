package org.interview.util;

import java.io.OutputStreamWriter;

public abstract class Logger {

    protected OutputStreamWriter writter;

    public abstract void info(final String message);

    public abstract void error(final String message);
}
