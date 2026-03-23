package br.com.rogrs.web.rest;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rogrs.service.dto.AuthRequest;
import br.com.rogrs.service.dto.AuthResponse;
import br.com.rogrs.services.TokenAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private final TokenAuthService tokenAuthService;

    public AuthResource(TokenAuthService tokenAuthService) {
        this.tokenAuthService = tokenAuthService;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> getToken(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(tokenAuthService.authenticate(request));
    }
}
