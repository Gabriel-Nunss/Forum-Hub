package com.forumHub.ForumHub.service;

import com.forumHub.ForumHub.domain.Topico;
import com.forumHub.ForumHub.dto.topico.TopicoRequestDTO;
import com.forumHub.ForumHub.dto.topico.TopicoResponseDTO;
import com.forumHub.ForumHub.dto.topico.TopicoUpdateDTO;
import com.forumHub.ForumHub.repository.CursoRepository;
import com.forumHub.ForumHub.repository.TopicoRepository;
import com.forumHub.ForumHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoResponseDTO criar(TopicoRequestDTO dto) {
        var autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        var topico = new Topico();
        topico.setTitulo(dto.getTitulo());
        topico.setMensagem(dto.getMensagem());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);
        return new TopicoResponseDTO(topico);
    }

    public List<TopicoResponseDTO> listarTodos() {
        return topicoRepository.findAll()
                .stream()
                .map(TopicoResponseDTO::new)
                .toList();
    }

    public TopicoResponseDTO buscarPorId(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        return new TopicoResponseDTO(topico);
    }

    public TopicoResponseDTO atualizar(Long id, TopicoUpdateDTO dto) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        if (dto.getTitulo() != null) topico.setTitulo(dto.getTitulo());
        if (dto.getMensagem() != null) topico.setMensagem(dto.getMensagem());
        if (dto.getStatus() != null) topico.setStatus(dto.getStatus());

        topicoRepository.save(topico);
        return new TopicoResponseDTO(topico);
    }

    public void deletar(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        topicoRepository.delete(topico);
    }
}