package com.perspective.restwebservices.model;

public class Greet {

    private String message;

    public Greet(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Greet{" +
                "message='" + message + '\'' +
                '}';
    }
}
