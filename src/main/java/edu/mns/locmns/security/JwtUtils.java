package edu.mns.locmns.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

@Service
public class JwtUtils {

    @Value("${secret}")
    private String secret;

    //fonction pour récupérer le corp du token
    public Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey("azerty123")
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean tokenValid(String token, UserDetails userDetails) {

        Claims claims = getTokenBody(token);
        return (claims.getSubject().equals(userDetails.getUsername()));
    }
}