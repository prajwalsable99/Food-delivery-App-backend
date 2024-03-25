package com.prajwal.FoodApp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtProvider {

    SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public String generateToken(Authentication authentication){
        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        String roles=populateAuthorities(authorities);

        String jwt= Jwts.builder().setIssuer("foodapp").
                setIssuedAt(new Date()).
                setExpiration(new Date(new Date().getTime() +86400000 )).
                claim("email",authentication.getName()).
                claim("authorities",roles)
                .signWith(key).
                compact();

        return jwt;
    }

    public String populateAuthorities(Collection<?extends GrantedAuthority > authorities) {

        Set<String> auths=new HashSet<>();
        for (GrantedAuthority authority :authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(".",auths);
    }

    public String getEmailFromToken(String token){
        token=token.substring(7);
        Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        String email=String.valueOf(claims.get("email"));
        return  email;


    }
}
