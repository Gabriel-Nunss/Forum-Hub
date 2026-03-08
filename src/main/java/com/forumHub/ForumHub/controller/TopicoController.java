package com.forumHub.ForumHub.controller;

import com.forumHub.ForumHub.dto.topico.TopicoRequestDTO;
import com.forumHub.ForumHub.dto.topico.TopicoResponseDTO;
import com.forumHub.ForumHub.dto.topico.TopicoUpdateDTO;
import com.forumHub.ForumHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoResponseDTO> criar(
            @RequestBody @Valid TopicoRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        var topico = topicoService.criar(dto);
        var uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(topico);
    }

    @GetMapping
    public ResponseEntity<List<TopicoResponseDTO>> listarTodos() {
        var topicos = topicoService.listarTodos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> buscarPorId(@PathVariable Long id) {
        var topico = topicoService.buscarPorId(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TopicoUpdateDTO dto) {

        var topico = topicoService.atualizar(id, dto);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        topicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}