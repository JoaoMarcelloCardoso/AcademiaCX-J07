package com.example.academiacx2.handler.exceptions;

public class AcessoNaoAutorizadoException extends RuntimeException {

    public AcessoNaoAutorizadoException(String mensagem){
        super(mensagem);
    }

    public AcessoNaoAutorizadoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
