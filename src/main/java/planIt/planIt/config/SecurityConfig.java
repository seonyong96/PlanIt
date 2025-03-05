package planIt.planIt.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import planIt.planIt.common.auth.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())   // csrf 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안함
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(/*"/signIn", "/register"*/"/**").permitAll()   // 인증없이 허용되는 범위
                        .anyRequest().authenticated()   // 나머지는 인증 필요
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)   // JWT 필터 추가
                .build();

        //        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                    .requestMatchers("/**").permitAll() // 누구나 접근 가능
//                    .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 가능
//                    .anyRequest().authenticated() // 나머지 요청은 인증 필요
//                .and()
//                .formLogin()
//                    .loginPage("/login") // 커스텀 로그인 페이지 경로
//                    .defaultSuccessUrl("/home") // 로그인 성공 시 이동할 경로
//                    .permitAll() // 로그인 페이지는 누구나 접근 가능
//                .and()
//                .logout()
//                    .logoutUrl("/logout") // 로그아웃 처리 URL
//                    .logoutSuccessUrl("/login") // 로그아웃 성공 시 이동할 경로
//                    .permitAll();
//
//        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}