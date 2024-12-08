package com.forohub.general.infra.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.forohub.user.models.User;
import com.forohub.user.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthenticationService implements UserDetailsService {

    @Value("${api.security.secret}")
    private String secret;

    @Autowired
    private UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRespository.findByUsername(username);
    }

    public String getToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("Foro hub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withClaim("creator", "wilmer200520t")
                    .withExpiresAt(getExpiration())
                    .sign(algorithm);

        } catch (JWTCreationException ex){
            throw new RuntimeException(ex);
        }
    }

    private Instant getExpiration() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubjectFromToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is null or empty");
        }

        DecodedJWT decodedJWT = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            decodedJWT = JWT.require(algorithm)
                    .withIssuer("Foro hub")
                    .build()
                    .verify(token);

        } catch (JWTVerificationException ex){
            throw new RuntimeException(ex);
        }

        if (decodedJWT == null) {
            throw new RuntimeException("Token verification failed.");
        }

        return decodedJWT.getSubject();
    }

}
