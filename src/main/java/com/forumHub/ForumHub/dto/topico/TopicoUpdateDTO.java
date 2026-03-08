package com.forumHub.ForumHub.dto.topico;

import com.forumHub.ForumHub.domain.StatusTopico;
import jakarta.validation.constraints.Size;

public class TopicoUpdateDTO {

    @Size(min = 3, message = "título deve ter no mínimo 3 caracteres")
    private String titulo;

    @Size(min = 10, message = "mensagem deve ter no mínimo 10 caracteres")
    private String mensagem;

    private StatusTopico status;

    public TopicoUpdateDTO() {}

    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public StatusTopico getStatus() { return status; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setStatus(StatusTopico status) { this.status = status; }
}