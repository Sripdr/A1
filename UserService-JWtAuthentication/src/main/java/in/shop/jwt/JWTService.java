package in.shop.jwt;


import in.shop.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTService {

    @Value("${jwt.secret}")
    private String secretKey;



    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Users user) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + 1800000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("usrId", user.getUsrId());
        claims.put("email", user.getEmail());
        claims.put("phoneNumber", user.getPhoneNumber());
        claims.put("roles", user.getRoles().stream().map(r->"ROLES_"+r).toList());

        return Jwts.builder()
                .id(user.getUsrId())
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(getSecretKey())
                .compact();

    }

    private Claims allClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String getUsrId(String token) {
        return allClaims(token).getId();
    }
    public String getUsername(String token) {
        return allClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = allClaims(token).getSubject();
            return (username.equals(userDetails.getUsername()))&& allClaims(token).getExpiration().before(new Date());
        }
        catch (Exception e){
            return false;
        }
    }

}
