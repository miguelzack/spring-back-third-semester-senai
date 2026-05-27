package com.example.security.services;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

@Service
public class JwtService {

    private String tokenAtual;
    private Date expiracaoAtual;
    private final String SECRET = "minhachavesupersecretacommaisde32caracteresparadarcerto";


    public String gerarToken(String email) {
        Date expiracao = Date.from(Instant.now().plusSeconds(120));

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiracao)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String pegarEmail(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }
}
