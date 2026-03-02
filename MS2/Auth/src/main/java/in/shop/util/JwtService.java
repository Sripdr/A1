package in.shop.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key getKeY() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {

        Date now = new Date(System.currentTimeMillis());
        Date expire = new Date(now.getTime() + 60000 * 30);

        String token = Jwts.builder()
                .subject(username)
                .claims()
                .issuedAt(now)
                .expiration(expire)
                .and()
                .signWith(getKeY())
                .compact();
        return token;
    }

    public Claims parseClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKeY())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String usernameFromToken(String token) {
        return parseClaimsFromToken(token).getSubject();
    }

    public void validateToken(String token) {
        parseClaimsFromToken(token).getExpiration().before(new Date());
    }
}