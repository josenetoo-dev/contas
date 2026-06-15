package com.conta.demo.exception.conflit;

public class ContaJaExisteException extends RuntimeException {
    public ContaJaExisteException(String message) {
        super(message);
    }
}
