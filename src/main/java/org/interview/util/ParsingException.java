package org.interview.util;

public class ParsingException extends Exception {
    private static final long serialVersionUID = -2563467072291202170L;

    public ParsingException() {
        super();
    }

    public ParsingException(final String message) {
        super(message);
    }

    public ParsingException(final String message, final Throwable t) {
        super(message, t);
    }

    public ParsingException(final Throwable t) {
        super(t);
    }
}
