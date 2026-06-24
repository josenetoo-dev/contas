package com.conta.demo.exception.conflit;

public class UsuarioJaExiste extends RuntimeException {
    public UsuarioJaExiste(String message) {
        super(message);
    }
}
