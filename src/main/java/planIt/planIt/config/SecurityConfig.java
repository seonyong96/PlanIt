package planIt.planIt.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import planIt.planIt.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;

    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

//    @Override
//    protected void configuer(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailService);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/", "/home", "/login", "/register", "/test").permitAll() // 누구나 접근 가능
                    .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 가능
                    .anyRequest().authenticated() // 나머지 요청은 인증 필요
                .and()
                .formLogin()
                    .loginPage("/login") // 커스텀 로그인 페이지 경로
                    .defaultSuccessUrl("/home") // 로그인 성공 시 이동할 경로
                    .permitAll() // 로그인 페이지는 누구나 접근 가능
                .and()
                .logout()
                    .logoutUrl("/logout") // 로그아웃 처리 URL
                    .logoutSuccessUrl("/login") // 로그아웃 성공 시 이동할 경로
                    .permitAll();

        return http.build();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
