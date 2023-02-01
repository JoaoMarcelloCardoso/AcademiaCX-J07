package com.academiacx.handler.exceptions;

public class SenhaInvalidaException extends Exception{

    public SenhaInvalidaException(String mensagem){
        super(mensagem);
    }

    public SenhaInvalidaException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
