package planIt.planIt.common.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "my-secret-key";

    /**
     * JWT 토큰 생성
     * @param userId
     * @return
     */
    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 만료시간 : 1시간
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact(); // jwt 문자열로 변환 후 반환
    }

    /**
     * 토큰에서 userId 추출
     * @param token
     * @return
     */
    public String extractUserId(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // subject(userId) 반환
    }

    public boolean vaildateToken(String token, UserDetails userDetails) {
        return extractUserId(token).equals(userDetails.getUsername());
    }


}
