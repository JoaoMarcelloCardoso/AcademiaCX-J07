package br.com.commerce.academiacx.handler.exceptions;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String mensagem){
        super(mensagem);
    }

    public InternalServerErrorException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}
