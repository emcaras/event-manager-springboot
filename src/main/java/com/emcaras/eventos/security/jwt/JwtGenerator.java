package com.emcaras.eventos.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtGenerator {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(getSigningKey(), Jwts.SIG.HS512) //HS512 es opcional, es solo un algoritmo de 512 bits
                .compact();
    }

    public String getUsernameFromJwt(String token){
        Claims claims = Jwts.parser()
                .verifyWith( getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        }
        catch (MalformedJwtException e){
            System.out.println("Invalid JWT token: " + e.getMessage());
        }
        catch (ExpiredJwtException e){
            System.out.println("JWT Token is expired: " + e.getMessage());
        }
        catch (UnsupportedJwtException e){
            System.out.println("JWT Token is unsupported:" + e.getMessage());
        }
        catch (IllegalArgumentException e){
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        catch (SignatureException e){
            System.out.println("Signature validation failed: " + e.getMessage());
        }
        return false;
    }
}
