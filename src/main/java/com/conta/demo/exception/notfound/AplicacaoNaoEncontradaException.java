package com.conta.demo.exception.notfound;

public class AplicacaoNaoEncontradaException extends RuntimeException {
    public AplicacaoNaoEncontradaException(String message) {
        super(message);
    }
}
