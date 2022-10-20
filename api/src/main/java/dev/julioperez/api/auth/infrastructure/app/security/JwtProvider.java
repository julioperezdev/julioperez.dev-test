package dev.julioperez.api.auth.infrastructure.app.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.parser;

@Service
@Slf4j
public class JwtProvider {
    @Value("${jwt.secretkey.jwtSecret}")
    private String jwtSecret;
    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;
    private boolean after;


    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new IllegalArgumentException();
        }

    }

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        log.info(authentication.getPrincipal().toString());
        Date actualDate = new Date();
        Date expirationDate = new Date(Long.sum(actualDate.getTime(), jwtExpirationInMillis));
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(actualDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setExpiration(expirationDate)
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new IllegalArgumentException();
        }
    }


    public boolean validateToken(String jwt) {
        try{
            parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        }catch (SignatureException exception){
            log.error("Invalid JWT signature");
        }catch (MalformedJwtException exception){
            log.error("Invalid JWT token");
        }catch (ExpiredJwtException exception){
            log.error("Expired JWT token");
        }catch (UnsupportedJwtException exception){
            log.error("Unsupported JWT token");
        }catch (IllegalArgumentException exception){
            log.error("JWT claims string is empty");
        }
        return false;
    }

    public boolean isNotValidTokenToRefresh(String token){
        return (!StringUtils.hasText(token) || !validateToken(token) || isNotExpiredToken(token));
    }

    public String generateTokenWithUserName(String username) {
        log.info("genering token with username");
        Date actualDate = new Date();
        Date expirationDate = new Date(Long.sum(actualDate.getTime(), jwtExpirationInMillis));
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(actualDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setExpiration(expirationDate)
                .compact();
    }


    private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new IllegalArgumentException();
        }
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }

    public Calendar getCalendarWithDateOfExpiration(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.sum(
                System.currentTimeMillis(),
                getJwtExpirationInMillis()));
        return calendar;
    }

    public Boolean isNotExpiredToken(String token){
        Date actualDate = new Date();
        return extractExpiration(token).after(actualDate);
    }
    public Boolean isExpiredToken(String token){
        Date actualDate = new Date();
        return extractExpiration(token).before(actualDate);
    }


    private java.util.Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token , Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}