package com.academiacx.handler.exceptions;

public class TelefoneInvalidoException extends RuntimeException {

    public TelefoneInvalidoException(String mensagem) {
        super(mensagem);
    }

    public TelefoneInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
