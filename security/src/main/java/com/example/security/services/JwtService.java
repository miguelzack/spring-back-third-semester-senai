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

        if (tokenAtual != null && expiracaoAtual.after(new Date())) {
            return tokenAtual;
        }
        expiracaoAtual = Date.from(Instant.now().plusSeconds(120));

        tokenAtual = Jwts.builder().setSubject(email).setExpiration(expiracaoAtual).signWith(SignatureAlgorithm.HS256, SECRET).compact();
        return tokenAtual;
    }


    public String pegarEmail(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }
}
