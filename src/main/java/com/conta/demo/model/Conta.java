package com.conta.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {

    public enum Status {
        ATIVA,
        INATIVA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aplicacao_id")
    private Aplicacao aplicacao;

    @ManyToOne
    @JoinColumn(name = "email_id")
    private Email email;

    // Defini uma frase dentro dele por padrão
    private String nomeUsuario;

    // Defini uma frase dentro dele por padrão
    private String senha;

    private Status status = Status.ATIVA;

    public Conta() {
    }

    public Conta(Aplicacao aplicacao, Email email, String nomeUsuario, String senha, Status status) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
