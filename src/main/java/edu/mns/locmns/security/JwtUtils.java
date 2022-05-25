package edu.mns.locmns.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class JwtUtils {

    @Value("${secret}") //Récupère dans application properties la valeur secret
    private String secret;

    public Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(PersonneDetailsLocMns personneDetails){

        Map<String, Object> data = new HashMap<>();
        data.put("id", personneDetails.getPersonne().getId()); //Ajouter id dans token
        data.put("email", personneDetails.getUsername()); //Ajouter mail dans token

        String listeDroit = personneDetails.getAuthorities()
                .stream()
                .map(role -> role.getAuthority())//Transforme les authorities en chaine de texte
                .collect(Collectors.joining(","));//Recupére chaine jointe par une virgule

        data.put("droits", listeDroit); //Ajoute les droits dans le token

        return Jwts.builder()
                .setClaims(data)
                .setSubject(personneDetails.getUsername()) //Générer le token
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean tokenValide(String token, UserDetails userDetails){
        Claims claims = getTokenBody(token);

        return claims.getSubject().equals(userDetails.getUsername());
    }
}
