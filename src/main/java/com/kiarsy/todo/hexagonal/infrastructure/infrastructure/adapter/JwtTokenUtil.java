package com.kiarsy.todo.hexagonal.infrastructure.infrastructure.adapter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.kiarsy.todo.hexagonal.core.domain.entities.User;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.exception.TokenExpiredException;
import com.kiarsy.todo.hexagonal.infrastructure.presentation.pojo.TokenUser;
import com.kiarsy.todo.hexagonal.infrastructure.infrastructure.utils.TimeUnitHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = TimeUnitHelper.ONE_DAY_MILI;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.jwt.secret}")
    private String secret;


    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getName(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }

    //generate token for user
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        claims.put("id", user.getId());
        return doGenerateToken(claims, user);
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, User user) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public TokenUser validateAndExtractToken(String token) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            final Date expire = claims.getExpiration();
            final String username = claims.getSubject();
            final String name = claims.get("name", String.class);
            final long id = claims.get("id", Long.class);

            return new TokenUser(id, username, name, expire);
        } catch (Exception e) {
            if (e instanceof io.jsonwebtoken.ExpiredJwtException)
                throw new TokenExpiredException();
//            logger.error(e.getMessage());
            e.printStackTrace();
            throw new WrongThreadException();
        }

    }

}
