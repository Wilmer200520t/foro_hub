package com.forohub.general.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public class TokenService {
    @Value("{${api.security.secret}")
    private String secret;
}
