package com.academiacx.handler.exceptions;

public class ForeignKeyException extends RuntimeException {

    public ForeignKeyException(String mensagem) {
        super(mensagem);
    }

    public ForeignKeyException(String mensagem, Throwable causa) {
        super(mensagem, causa);

    }
}
