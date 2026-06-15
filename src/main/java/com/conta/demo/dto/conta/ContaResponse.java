package com.conta.demo.dto.conta;

import com.conta.demo.model.Conta;

public class ContaResponse {
    private Long id;
    private Long aplicacaoId;
    private Long emailId;
    private String nomeDeUsuario;
    private String senha;

    private String categoriaNome;
    private String aplicacaoNome;
    private String email;

    public ContaResponse(Conta conta) {
        this.id = conta.getId();
        this.aplicacaoId = conta.getAplicacao().getId();
        this.email = conta.getEmail().getEmail();
        this.nomeDeUsuario = conta.getNomeUsuario();
        this.senha = conta.getSenha();
        this.categoriaNome = conta.getAplicacao().getCategoria().getNome();
        this.aplicacaoNome = conta.getAplicacao().getNome();
        this.emailId = conta.getEmail().getId();
    }

    public Long getId() {
        return id;
    }

    public Long getAplicacaoId() {
        return aplicacaoId;
    }

    public Long getEmailId() {
        return emailId;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public String getAplicacaoNome() {
        return aplicacaoNome;
    }

    public String getEmail() {
        return email;
    }
}
