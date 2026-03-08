package com.forumHub.ForumHub.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoErros {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroDTO> tratarErroGenerico(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErroDTO(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroValidacao(
            MethodArgumentNotValidException ex) {
        var erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(org.springframework.web.servlet
            .resource.NoResourceFoundException.class)
    public ResponseEntity<ErroDTO> tratarErro404() {
        return ResponseEntity
                .notFound()
                .build();
    }

    public record ErroDTO(String mensagem) {}
}