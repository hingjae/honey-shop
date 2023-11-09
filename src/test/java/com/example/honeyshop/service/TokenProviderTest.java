package com.example.honeyshop.service;

import com.example.honeyshop.config.web.jwt.JwtProperties;
import com.example.honeyshop.dto.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Duration;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class TokenProviderTest {

    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    JwtProperties jwtProperties;

    @DisplayName("generateToken : 토큰 생성")
    @Test
    void test() {
        String authoritiesString = "USER";
        //given
        UserPrincipal user = createUserPrincipal(authoritiesString);

        //when
        String token = tokenProvider.generateToken(user, Duration.ofDays(2));
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();

        String id = claims.getSubject();
        String nickname = claims.get("nickname", String.class);
        String authorities = claims.get("authorities", String.class);

        //then
        assertThat(id).isEqualTo(user.getId());
        assertThat(nickname).isEqualTo(user.getNickname());
        assertThat(authorities).isEqualTo(authoritiesString);
    }

    private static UserPrincipal createUserPrincipal(String authoritiesString) {
        return UserPrincipal.builder()
                .id("honey")
                .password("test")
                .nickname("testnickname")
                .authorities(Set.of(new SimpleGrantedAuthority(authoritiesString)))
                .build();
    }


    @DisplayName("validToken : 만료된 토큰 유효성 검사")
    @Test
    void test2() {
        String authoritiesString = "USER";
        //given
        UserPrincipal user = createUserPrincipal(authoritiesString);
        Date now = new Date();
        Date beforeDay = new Date(now.getTime() - Duration.ofDays(7).toMillis());
        log.info("beforeDay : {}", beforeDay);
        String token = getToken(now, beforeDay, user);

        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("validToken : 정상 토큰 유효성 검사")
    @Test
    void test3() {
        String authoritiesString = "USER";
        //given
        UserPrincipal user = createUserPrincipal(authoritiesString);
        Date now = new Date();
        Date expiry = new Date(now.getTime() + Duration.ofDays(7).toMillis());
        log.info("beforeDay : {}", expiry);
        String token = getToken(now, expiry, user);

        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isTrue();
    }

    private String getToken(Date now, Date expiry, UserPrincipal user) {
        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getId())
                .claim("nickname", user.getNickname())
                .claim("authorities", user.getAuthoritiesToString())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }
}