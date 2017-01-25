package com.example.ivansv.nskweather.domain.exception;

public class DefaultErrorBundle implements ErrorBundle {

    private static final String DEFAULT_ERROR_MSG = "Unknown Error";
    private final Exception exception;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        return (exception != null) ? exception.getMessage() : DEFAULT_ERROR_MSG;
    }
}
