package com.dm.ecommerce.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    private String tokenAtual;
    private Date expiracaoAtual;
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration-seconds:7200}")
    private long expirationSeconds;

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(String email) {
        if (tokenAtual != null && expiracaoAtual.after(new Date())) {
            return tokenAtual;
        }
        expiracaoAtual = Date.from(Instant.now().plusSeconds(expirationSeconds));

        tokenAtual = Jwts.builder()
                .setSubject(email)
                .setExpiration(expiracaoAtual)
                .signWith(signingKey(), SignatureAlgorithm.HS256)
                .compact();
        return tokenAtual;
    }

    public String pegarEmail(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
