package com.forohub.general.infra.security.dto;

public record AuthenticationDto(
        String username,
        String password
) {
}
