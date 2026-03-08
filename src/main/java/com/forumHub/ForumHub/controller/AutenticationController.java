package com.forumHub.ForumHub.controller;

import com.forumHub.ForumHub.domain.Usuario;
import com.forumHub.ForumHub.dto.autenticacao.LoginRequestDTO;
import com.forumHub.ForumHub.dto.autenticacao.TokenResponseDTO;
import com.forumHub.ForumHub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(
            @RequestBody LoginRequestDTO dto) {

        var authToken = new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getSenha()
        );

        var authentication = authenticationManager.authenticate(authToken);
        var usuario = (Usuario) authentication.getPrincipal();
        var token = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}