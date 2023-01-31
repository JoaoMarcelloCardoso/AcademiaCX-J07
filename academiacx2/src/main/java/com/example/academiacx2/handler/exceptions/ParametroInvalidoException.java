package com.example.academiacx2.handler.exceptions;

public class ParametroInvalidoException extends RuntimeException{

    public ParametroInvalidoException(String mensagem){
        super(mensagem);
    }

    public ParametroInvalidoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
