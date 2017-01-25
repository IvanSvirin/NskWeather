package com.example.ivansv.nskweather.data.exception;

public class ForecastNotFoundException extends Exception {

    public ForecastNotFoundException() {
        super();
    }

    public ForecastNotFoundException(String message) {
        super(message);
    }

    public ForecastNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForecastNotFoundException(Throwable cause) {
        super(cause);
    }
}
