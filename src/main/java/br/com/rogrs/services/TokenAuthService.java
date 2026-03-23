package br.com.rogrs.services;

import org.springframework.stereotype.Service;

import br.com.rogrs.config.SecurityProperties;
import br.com.rogrs.service.dto.AuthRequest;
import br.com.rogrs.service.dto.AuthResponse;
import br.com.rogrs.web.rest.errors.BadRequestAlertException;

@Service
public class TokenAuthService {

    private final SecurityProperties securityProperties;

    public TokenAuthService(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public AuthResponse authenticate(AuthRequest request) {
        boolean validClient = securityProperties.getClientId().equals(request.getClientId());
        boolean validSecret = securityProperties.getClientSecret().equals(request.getClientSecret());

        if (!validClient || !validSecret) {
            throw new BadRequestAlertException("Credenciais inválidas", "auth", "invalid_credentials");
        }

        return new AuthResponse(securityProperties.getApiToken());
    }
}
