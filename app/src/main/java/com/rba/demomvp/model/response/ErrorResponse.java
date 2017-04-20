package com.rba.demomvp.model.response;

/**
 * Created by Ricardo Bravo on 30/01/17.
 */

public class ErrorResponse {

    /**
     * message : No existe información
     */

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
