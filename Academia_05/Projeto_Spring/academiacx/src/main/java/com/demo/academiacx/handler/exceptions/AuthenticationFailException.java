package com.demo.academiacx.handler.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
