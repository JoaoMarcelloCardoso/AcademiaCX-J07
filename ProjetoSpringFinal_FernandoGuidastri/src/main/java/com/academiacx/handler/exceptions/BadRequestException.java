package com.academiacx.handler.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String mensagem){
        super(mensagem);
    }

    public BadRequestException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
