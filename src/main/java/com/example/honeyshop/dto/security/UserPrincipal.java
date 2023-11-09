package com.example.honeyshop.dto.security;

import com.example.honeyshop.entity.user.RoleType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserPrincipal implements UserDetails {

    private String id;
    private String password;
    private String nickname;
    private Set<SimpleGrantedAuthority> authorities;

    @Builder
    private UserPrincipal(String id, String password, String nickname, Set<SimpleGrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.authorities = authorities;
    }

    public String getAuthoritiesToString() {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .sorted()
                .collect(Collectors.joining(","));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
