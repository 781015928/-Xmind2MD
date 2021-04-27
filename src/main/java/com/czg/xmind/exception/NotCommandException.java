package com.czg.xmind.exception;

public class NotCommandException extends RuntimeException {
    public NotCommandException() {
        this("未输入命令");
    }

    public NotCommandException(String message) {
        super(message);
    }
}
