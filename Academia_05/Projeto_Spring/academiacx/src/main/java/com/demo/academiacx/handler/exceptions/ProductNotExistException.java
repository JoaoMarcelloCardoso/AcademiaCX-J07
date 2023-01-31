package com.demo.academiacx.handler.exceptions;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
