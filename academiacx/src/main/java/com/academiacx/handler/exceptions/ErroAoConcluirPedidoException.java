package com.academiacx.handler.exceptions;

public class ErroAoConcluirPedidoException extends RuntimeException {

    public ErroAoConcluirPedidoException(String mensagem) {
        super(mensagem);
    }

    public ErroAoConcluirPedidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
