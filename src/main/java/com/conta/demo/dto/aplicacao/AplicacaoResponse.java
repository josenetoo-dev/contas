package com.conta.demo.dto.aplicacao;

import com.conta.demo.model.Aplicacao;

public class AplicacaoResponse {
    private Long id;
    private String nome;
    private Long categoriaId;
    private String categoriaNome;

    public AplicacaoResponse(Aplicacao aplicacao) {
        this.id = aplicacao.getId();
        this.nome = aplicacao.getNome();
        this.categoriaId = aplicacao.getCategoria().getId();
        this.categoriaNome = aplicacao.getCategoria().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }
}
