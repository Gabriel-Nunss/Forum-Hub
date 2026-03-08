package com.forumHub.ForumHub.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicoRequestDTO {

    @NotBlank(message = "título é obrigatório")
    private String titulo;

    @NotBlank(message = "mensagem é obrigatória")
    private String mensagem;

    @NotNull(message = "autorId é obrigatório")
    private Long autorId;

    @NotNull(message = "cursoId é obrigatório")
    private Long cursoId;

    public TopicoRequestDTO() {}

    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public Long getAutorId() { return autorId; }
    public Long getCursoId() { return cursoId; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
}