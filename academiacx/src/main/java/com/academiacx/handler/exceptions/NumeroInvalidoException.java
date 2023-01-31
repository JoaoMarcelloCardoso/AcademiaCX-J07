package com.academiacx.handler.exceptions;

public class NumeroInvalidoException extends RuntimeException {

    public NumeroInvalidoException(String mensagem) {
        super(mensagem);
    }

    public NumeroInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

