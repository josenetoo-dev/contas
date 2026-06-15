package com.conta.demo.dto.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ContaRequest {
    @NotNull
    private Long aplicacaoId;

    @NotNull
    private Long emailId;

    private String nomeDeUsuario;

    private String senha;

    public ContaRequest() {
    }

    public Long getAplicacaoId() {
        return aplicacaoId;
    }

    public void setAplicacaoId(Long aplicacaoId) {
        this.aplicacaoId = aplicacaoId;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
