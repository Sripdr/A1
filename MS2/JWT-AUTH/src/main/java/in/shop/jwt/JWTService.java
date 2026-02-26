package in.shop.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;

    private Key getKey() throws UnsupportedEncodingException {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public  String generateToken(String username) throws UnsupportedEncodingException {
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + 3600000); // Token valid for 1 hour

        String token = Jwts.builder()
                .subject(username)
                .claims()
                .issuedAt(now)
                .expiration(expiryDate)
                .and()
                .signWith(getKey())
                .compact();
        return token;
    }

    public Claims parseToken(String token) throws UnsupportedEncodingException {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsernameFromToken(String token) throws UnsupportedEncodingException {
        return parseToken(token).getSubject();
    }

    public void validateToken(String token) {
        try {
       parseToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
        }
    }
}
