package com.conta.demo.exception;

public class ErroResponse {
    private String mensagem;
    private int status;

    public ErroResponse(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
