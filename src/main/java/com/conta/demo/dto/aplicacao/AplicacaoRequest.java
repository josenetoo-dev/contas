package com.conta.demo.dto.aplicacao;

import com.conta.demo.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AplicacaoRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long categoriaId;

    public AplicacaoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
