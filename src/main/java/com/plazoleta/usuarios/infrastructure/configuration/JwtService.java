package com.plazoleta.usuarios.infrastructure.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${app.jwt.llave}")
    private String llave;
    private final long expiracionMinutes;

    public JwtService(@Value("${app.jwt.expiracion}") long expiracionMinutes) {
        this.expiracionMinutes = expiracionMinutes;
    }

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(llave.getBytes());
    }

    public String generarToken(String subject, String role){
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(subject)
                .claims(Map.of("rol",role))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expiracionMinutes*60)))
                .signWith(getKey())
                .compact();
    }

    public Boolean validarToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Claims obtenerClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String obtnerCorreo(String token) {
        return obtenerClaims(token).getSubject();
    }
}
