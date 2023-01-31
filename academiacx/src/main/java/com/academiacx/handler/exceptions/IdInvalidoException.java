package com.academiacx.handler.exceptions;

public class IdInvalidoException extends RuntimeException {

    public IdInvalidoException(String mensagem) {
        super(mensagem);
    }

    public IdInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

