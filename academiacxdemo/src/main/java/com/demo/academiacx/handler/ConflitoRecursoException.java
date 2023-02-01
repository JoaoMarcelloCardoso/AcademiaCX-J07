package com.demo.academiacx.handler;

public class ConflitoRecursoException extends RuntimeException{

    public ConflitoRecursoException(String mensagem){
        super(mensagem);
    }

    public ConflitoRecursoException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
