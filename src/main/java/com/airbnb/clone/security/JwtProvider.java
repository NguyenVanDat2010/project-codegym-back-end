package com.airbnb.clone.security;

import com.airbnb.clone.exception.AppException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

@Service
public class JwtProvider {

    private KeyStore keyStore;
    @Value("${jwt.keystore.password}")
    private String keyStorePassword;


    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream inputStream = getClass().getResourceAsStream("/projectbackend.jks");
            keyStore.load(inputStream,keyStorePassword.toCharArray());
        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new AppException("Exception occured while loading keystore");
        }
    }
    public String generateToken(Authentication authentication) {
        User principle = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principle.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("redditclone",keyStorePassword.toCharArray());
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new AppException("Exception occured while retrieving public key from " +
                    "key store");
        }
    }

    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("projectbackend").getPublicKey();
        } catch (KeyStoreException e) {
            throw new AppException("Exception occured while retrieving public key");
        }
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }

    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }


}
