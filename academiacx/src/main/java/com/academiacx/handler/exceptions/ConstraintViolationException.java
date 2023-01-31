package com.academiacx.handler.exceptions;

public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(String mensagem) {
        super(mensagem);
    }

    public ConstraintViolationException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

