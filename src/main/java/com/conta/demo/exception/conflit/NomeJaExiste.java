package com.conta.demo.exception.conflit;

public class NomeJaExiste extends RuntimeException {
    public NomeJaExiste(String message) {
        super(message);
    }
}
