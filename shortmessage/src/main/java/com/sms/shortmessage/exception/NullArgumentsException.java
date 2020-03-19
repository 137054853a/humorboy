package com.sms.shortmessage.exception;

public class NullArgumentsException extends RuntimeException {
    public NullArgumentsException(String message) {
        super(message);
    }

    public NullArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullArgumentsException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
