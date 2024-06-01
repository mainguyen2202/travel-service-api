package com.nlu.mainguyen.travelserviceapi.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nlu.mainguyen.travelserviceapi.entities.Users;
import com.nlu.mainguyen.travelserviceapi.jwt.repository.TokenRepository;

import javax.crypto.SecretKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.access-token-expiration}")
    private long accessTokenExpire;

    @Value("${application.security.jwt.refresh-token-expiration}")
    private long refreshTokenExpire;

    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);

        boolean validToken = tokenRepository
                .findByAccessToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
    }

    public boolean isValidRefreshToken(String token, Users user) {
        String username = extractUsername(token);

        boolean validRefreshToken = tokenRepository
                .findByRefreshToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validRefreshToken;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateAccessToken(Users user) {
        return generateToken(user, accessTokenExpire);
    }

    public String generateRefreshToken(Users user) {
        return generateToken(user, refreshTokenExpire);
    }

    private String generateToken(Users user, long expireTime ) {

        // Map<String, Object> info = new HashMap<>();
        // info.put("JWT_CLAIM_USER_INFO", user);
        // info.put("JWT_CLAIM_USER_EMAIL", user.getEmail());
        // info.put("JWT_CLAIM_USER_FULLNAME", user.getName());
        // info.put("scopes", authentication.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
        // info.put("organization", authentication.getName());

        ArrayList<String> hoaQua = new ArrayList<>();
        hoaQua.add("Táo");
        hoaQua.add("Cam");
        hoaQua.add("Nho");

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("USER_ID", user.getId());
        extraClaims.put("USER_ROLE", user.getRole());
        extraClaims.put("USER_EMAIL", user.getEmail());
        extraClaims.put("PERMISIONS", hoaQua);

        String token = Jwts
                .builder()
                .claims(extraClaims)// custom info theo nghiệm vụ role, email, permision, v.v
                .subject(user.getUsername())// lưu username
                // .id(Long.toString(user.getId()))// userid
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
