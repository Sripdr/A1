package in.shop.jwtutil;

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
public class JWTService {

    @Value("${jwt.secret}")
    private static  String JWT_SECRET ;

    public Key getJWTKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600 * 1000);

        String token = Jwts.builder()
                          .subject(username)
                          .claims()
                          .issuedAt(now)
                          .expiration(expiryDate)
                          .and()
                          .signWith(getJWTKey())
                          .compact();
return token;
    }


    public Claims claimsFromToken(String token) {
    return  Jwts.parser()
            .verifyWith((SecretKey) getJWTKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public void validateToken(String token) {
        claimsFromToken(token).getExpiration();
    }

    public String getUsernameFromToken(String token) {
     return    claimsFromToken(token).getSubject();
    }

}
