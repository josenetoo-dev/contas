package com.conta.demo.exception.conflit;

public class CategoriaJaExisteException extends RuntimeException {
    public CategoriaJaExisteException(String message) {
        super(message);
    }
}
