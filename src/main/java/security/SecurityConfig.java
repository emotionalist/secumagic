package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/main", "/css/**", "/js/**", "/images/**", "/createAccount", "/register", "/Select","/generateInviteCode").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/main")
                                .permitAll()
                                .defaultSuccessUrl("/main_menu", true)  // 로그인 성공 시 리다이렉트할 URL
                                .failureUrl("/main?error=true")  // 로그인 실패 시 리다이렉트할 URL
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/main")  // 로그아웃 성공 시 리다이렉트할 URL
                                .permitAll()
                )
                .rememberMe(rememberMe ->
                        rememberMe
                                .key("uniqueAndSecret")  // 쿠키를 생성할 때 사용할 키
                                .tokenValiditySeconds(86400)  // Remember-Me 쿠키 유효 시간 (하루)
                )
                .sessionManagement(session ->
                        session
                                .maximumSessions(1)  // 최대 허용 세션 수
                                .maxSessionsPreventsLogin(true)  // 이미 로그인된 상태에서 추가 로그인을 막음
                );

        return http.build();
    }
}




