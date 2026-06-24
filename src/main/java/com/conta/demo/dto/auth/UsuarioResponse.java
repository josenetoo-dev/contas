package com.conta.demo.dto.auth;

import com.conta.demo.model.Usuario;

public class UsuarioResponse {

    private Long id;
    private String username;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}