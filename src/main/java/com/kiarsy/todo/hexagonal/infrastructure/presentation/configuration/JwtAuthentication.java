package com.kiarsy.todo.hexagonal.infrastructure.presentation.configuration;

import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.adapter.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class JwtAuthentication implements Authentication {
    private String token;
    private String name;
    private String username;
    private Long id;

    private boolean authenticated = false;

    public JwtAuthentication(JwtTokenUtil jwtTokenUtil, String token) throws Exception {
        this.token = token;
        var userDetail = jwtTokenUtil.validateAndExtractToken(token);
        name = userDetail.getName();
        id = userDetail.getId();
        username = userDetail.getUsername();
        authenticated = true;
    }

    public JwtAuthentication() {
    }

    public static JwtAuthentication getAuthentication() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }


    public String getToken() {
        return token;
    }


    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return name;
    }
}
