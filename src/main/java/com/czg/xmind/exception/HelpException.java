package com.czg.xmind.exception;

public class HelpException extends RuntimeException {
    public HelpException() {
        this("");
    }

    public HelpException(String message) {
        super(message);
    }
}
