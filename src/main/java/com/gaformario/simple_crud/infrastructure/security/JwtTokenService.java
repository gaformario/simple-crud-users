package com.gaformario.simple_crud.infrastructure.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

@Service
public class JwtTokenService {
    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret-key}")
    private String secretKey;

    public String create(String id, Date notBefore, Date expiration) {
        SecretKey key = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secretKey)
        );
        return Jwts.builder()
                .header()
                .and()
                .id(id)
                .issuer(issuer)
                .signWith(key)
                .notBefore(notBefore)
                .expiration(expiration)
                .compact();
    }

    public String getId(String token) {
        try {
            Claims claims = resolveClaims(token);

            Date now = new Date();

            if (claims.getExpiration().before(now) || claims.getNotBefore().after(now)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
            }

            return claims.getId();

        } catch (JwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
        }
    }

    private Claims resolveClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        JwtParser parser = Jwts.parser().verifyWith(key).build();
        return parser.parseSignedClaims(token).getPayload();
    }
}