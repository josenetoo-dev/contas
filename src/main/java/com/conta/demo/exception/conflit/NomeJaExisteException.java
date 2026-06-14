package com.conta.demo.exception.conflit;

public class NomeJaExisteException extends RuntimeException {
    public NomeJaExisteException(String message) {
        super(message);
    }
}
