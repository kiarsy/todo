package com.kiarsy.todo.hexagonal.infrastructure.presentation.configuration;

import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.adapter.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class WebSecurityConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    ExceptionHandlerControllerAdvice exceptionHandlerControllerAdvice;

    public WebSecurityConfig( ExceptionHandlerControllerAdvice exceptionHandlerControllerAdvice) {

        this.exceptionHandlerControllerAdvice = exceptionHandlerControllerAdvice;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .logout().disable()
                .cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(it -> it.requestMatchers(new RegexRequestMatcher("/user/.*", null, true)).permitAll())
                .authorizeHttpRequests(it -> it.requestMatchers(new RegexRequestMatcher("/health/.*", null, true)).permitAll())
                .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
                .addFilterBefore(new CustomCorsFilter(), ChannelProcessingFilter.class)
                .authorizeHttpRequests(it -> it.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    JwtAuthenticationFilter authenticationFilter() throws Exception {
        final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtTokenUtil,exceptionHandlerControllerAdvice);
        filter.setAuthenticationManager(new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return authentication;
            }
        });
        //filter.setAuthenticationSuccessHandler(successHandler());
        return filter;
    }

}
