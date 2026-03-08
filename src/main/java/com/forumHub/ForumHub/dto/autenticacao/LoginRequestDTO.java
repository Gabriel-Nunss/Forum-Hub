package com.forumHub.ForumHub.dto.autenticacao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "email é obrigatório")
    @Email(message = "formato de email inválido")
    private String email;

    @NotBlank(message = "senha é obrigatória")
    private String senha;

    public LoginRequestDTO() {}

    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
}