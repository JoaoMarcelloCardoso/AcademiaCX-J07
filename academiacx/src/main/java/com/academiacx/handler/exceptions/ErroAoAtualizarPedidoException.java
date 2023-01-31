package com.academiacx.handler.exceptions;

public class ErroAoAtualizarPedidoException extends RuntimeException {

    public ErroAoAtualizarPedidoException(String mensagem) {
        super(mensagem);
    }

    public ErroAoAtualizarPedidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
