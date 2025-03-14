package planIt.planIt.common.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import planIt.planIt.service.CustomUserDetailsService;


import java.util.Date;

@Component
public class JwtTokenProvider {

//    private final String SECRET_KEY = "my-secret-key";

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    private final CustomUserDetailsService customUserDetailsService;

    public JwtTokenProvider(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public Authentication getAuthentication(String token) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.extractUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    }

    /**
     * JWT 토큰 생성
     *
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
     *
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
