package com.conta.demo.dto.conta;

import com.conta.demo.model.Aplicacao;
import com.conta.demo.model.Categoria;
import com.conta.demo.model.Conta;
import com.conta.demo.model.Email;

public class ContaResponse {
    private Long id;
    private Aplicacao aplicacaoId;
    private Email email;
    private Categoria categoria;
    private String nomeDeUsuario;
    private String senha;
    private Conta.Status status;

    public ContaResponse(Conta conta) {
        this.id = conta.getId();
        this.aplicacaoId = conta.getAplicacao();
        this.email = conta.getEmail();
        this.categoria = conta.getAplicacao().getCategoria();
        this.nomeDeUsuario = conta.getNomeUsuario();
        this.senha = conta.getSenha();
        this.status = conta.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Aplicacao getAplicacaoId() {
        return aplicacaoId;
    }

    public Email getEmail() {
        return email;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public Conta.Status getStatus() {
        return status;
    }
}
