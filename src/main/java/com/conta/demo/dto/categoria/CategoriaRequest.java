package com.conta.demo.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank
    private String nome;

    public CategoriaRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
