package com.academiacx.handler.exceptions;

public class TamanhoInvalidoException extends RuntimeException {

    public TamanhoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public TamanhoInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
