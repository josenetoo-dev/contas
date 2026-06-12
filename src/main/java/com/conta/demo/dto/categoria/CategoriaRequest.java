package com.conta.demo.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank
    private String nome;

    public CategoriaRequest() {
    }

    public CategoriaRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
