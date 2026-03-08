package com.forumHub.ForumHub.domain;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean solucao = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    public Resposta() {}

    public Long getId() { return id; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public Boolean getSolucao() { return solucao; }
    public Topico getTopico() { return topico; }
    public Usuario getAutor() { return autor; }

    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setSolucao(Boolean solucao) { this.solucao = solucao; }
    public void setTopico(Topico topico) { this.topico = topico; }
    public void setAutor(Usuario autor) { this.autor = autor; }
}