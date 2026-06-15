package com.conta.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aplicacao_id", nullable = false)
    private Aplicacao aplicacao;

    @ManyToOne
    @JoinColumn(name = "email_id", nullable = false)
    private Email email;

    private String nomeUsuario;

    private String senha;

    public Conta() {
    }

    public Conta(Aplicacao aplicacao, Email email, String nomeUsuario, String senha) {
        this.aplicacao = aplicacao;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public Aplicacao getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(Aplicacao aplicacao) {
        this.aplicacao = aplicacao;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
