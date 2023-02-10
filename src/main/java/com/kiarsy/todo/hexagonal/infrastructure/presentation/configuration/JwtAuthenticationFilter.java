package com.kiarsy.todo.hexagonal.infrastructure.presentation.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.adapter.JwtTokenUtil;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.exception.BaseException;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.exception.InternalServerException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    JwtTokenUtil jwtTokenUtil;
    ExceptionHandlerControllerAdvice exceptionHandlerControllerAdvice;

    JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil,ExceptionHandlerControllerAdvice exceptionHandlerControllerAdvice) {
        super("/**");
        this.jwtTokenUtil = jwtTokenUtil;
        this.exceptionHandlerControllerAdvice = exceptionHandlerControllerAdvice;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException, BaseException {
//        logger.info("attemptAuthentication");
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        if (token != null) {
            token = token.replace("Bearer", "").trim();

            try {
                Authentication requestAuthentication = new JwtAuthentication(jwtTokenUtil, token);
                return getAuthenticationManager().authenticate(requestAuthentication);
            } catch (Exception e) {
                BaseException error;

                if (e instanceof BaseException) {
                    error = ((BaseException) e);
                } else {
                    error = new InternalServerException(e);
                }
                var responsePair = exceptionHandlerControllerAdvice.convertExceptionToErrorResponse(error);
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setStatus(responsePair.getSecond());
                httpServletResponse.getOutputStream().println(convertObjectToJson(responsePair.getFirst()));
                return null;
            }
        }
        return getAuthenticationManager().authenticate(new JwtAuthentication());
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
//        logger.info("successfulAuthentication:" + authResult.getCredentials());
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    //
//    @Override
//    @Autowired
//    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//        super.setAuthenticationManager(authenticationManager);
//    }

}
