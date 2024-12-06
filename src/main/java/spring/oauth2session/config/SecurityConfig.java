package spring.oauth2session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import spring.oauth2session.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf 해제
        http
                .csrf((csrf) -> csrf.disable());

        // form 로그인 해제
        http
                .formLogin((login) -> login.disable());

        // httpBasic 해제
        http
                .httpBasic((basic) -> basic.disable());

        // OAuth2 설정
        // oauth2Login과 oauth2Client가 있는데 Client는 custom 해줘야 됨
        // 현재는 커스텀 설정을 default로 설정 나중에 userDetailsService를 등록하면 람다식으로 등록 가능
        // EndPoint는 데이터를 받을 수 있는 UserDetailsService를 등록해준다는 뜻
        http
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint((userInfoEndpointConfig) ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)));

        // 각각의 경로에 대한 인가 작업
        // 전역, oauth2, login 관련 주소는 누구든 들어올 수 있도록 설정 나머지는 로그인된 사용자만 접근하도록 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
