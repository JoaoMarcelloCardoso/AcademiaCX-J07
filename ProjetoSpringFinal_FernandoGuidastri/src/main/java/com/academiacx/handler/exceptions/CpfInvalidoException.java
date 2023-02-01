package com.academiacx.handler.exceptions;

public class CpfInvalidoException extends Exception{

    public CpfInvalidoException(String mensagem){
        super(mensagem);
    }

    public CpfInvalidoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }


}
