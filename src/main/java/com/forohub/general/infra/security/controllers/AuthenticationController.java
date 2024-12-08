package com.forohub.general.infra.security.controllers;

import com.forohub.general.infra.security.dto.AuthenticationDto;
import com.forohub.general.infra.security.dto.JWTDto;
import com.forohub.general.infra.security.service.AuthenticationService;
import com.forohub.user.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<JWTDto> authenticate(@Valid @RequestBody AuthenticationDto authenticationDto) {
        Authentication TOKEN = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        Authentication authenticated = authenticationManager.authenticate(TOKEN);

        var tokenJWT = authenticationService.getToken((User) authenticated.getPrincipal());

        return ResponseEntity.ok(new JWTDto(tokenJWT));
    }
}
