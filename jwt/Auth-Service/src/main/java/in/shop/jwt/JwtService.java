package in.shop.jwt;

import in.shop.entity.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
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
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    private SecretKey getSignInKey() {

            return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));

    }

/**
 * Generates a JWT token for the authenticated user with specified claims
 * @param user The authenticated user object containing necessary information
 * @return JWT token as a string
 */
    //generating token
    public String generateToken(AuthUser user) {
    // Create a claims map to store additional token information

        Map<String, Object> claims = new HashMap<>();
       claims.put("roles", user.getRoles().stream().map(r->"ROLES_"+r).toList());
       claims.put("authId", user.getAuthId());
       claims.put("phoneNumber",user.getPhoneNumber());

        return Jwts
                .builder()
                .id(user.getAuthId())
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 20))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public Claims allClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //extracting username from token


    //extracting claims from token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = allClaims(token).getSubject();
            return (username.equals(userDetails.getUsername()))&& allClaims(token).getExpiration().after(new Date());
        }
        catch (Exception e){
            return false;
        }
    }

}
