package com.academiacx.handler.exceptions;

public class EmailInvalidoException extends RuntimeException {

    public EmailInvalidoException(String mensagem) {
        super(mensagem);
    }

    public EmailInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
