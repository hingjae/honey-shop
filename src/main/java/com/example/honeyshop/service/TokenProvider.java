package com.example.honeyshop.service;

import com.example.honeyshop.config.web.jwt.JwtProperties;
import com.example.honeyshop.dto.security.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(UserPrincipal user, Duration expiry) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(createExpiry(now, expiry))
                .setSubject(user.getId())
                .claim("nickname", user.getNickname())
                .claim("authorities", user.getAuthoritiesToString())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Date createExpiry(Date now, Duration expiry) {
        return new Date(now.getTime() + expiry.toMillis());
    }

}
