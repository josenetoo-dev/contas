package com.conta.demo.dto.email;

import com.conta.demo.model.Email;

public class EmailResponse {
    private Long id;
    private String email;
    private String senha;

    public EmailResponse(Email email) {
        this.id = email.getId();
        this.email = email.getEmail();
        this.senha = email.getSenha();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
