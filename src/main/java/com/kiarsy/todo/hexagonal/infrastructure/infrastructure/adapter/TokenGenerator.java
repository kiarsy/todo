package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.adapter;

import com.kiarsy.todo.hexagonal.core.domain.entities.User;
import com.kiarsy.todo.hexagonal.core.domain.ports.driver.ITokenGenerator;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator implements ITokenGenerator {
    private final JwtTokenUtil jwtTokenUtil;

    public TokenGenerator(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public String generateToken(User user) {
        return jwtTokenUtil.generateToken(user);
    }
}
