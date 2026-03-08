package com.forumHub.ForumHub.dto.topico;

import com.forumHub.ForumHub.domain.StatusTopico;
import com.forumHub.ForumHub.domain.Topico;
import java.time.LocalDateTime;

public class TopicoResponseDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String nomeAutor;
    private String nomeCurso;

    public TopicoResponseDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();
        this.nomeAutor = topico.getAutor().getNome();
        this.nomeCurso = topico.getCurso().getNome();
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public StatusTopico getStatus() { return status; }
    public String getNomeAutor() { return nomeAutor; }
    public String getNomeCurso() { return nomeCurso; }
}