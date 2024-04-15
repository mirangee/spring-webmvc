package com.spring.mvc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // config 설정 활성화
@EnableWebSecurity // 스프링 시큐리티 모듈을 활성화
public class SecurityConfig {
    // 시큐리티 기본 설정(권한처리, 초기 로그인 화면 없애기...)
    @Bean //메서드에 붙이는 @Bean 아노테이션: 메서드가 리턴하는 객체를 Bean 등록하겠다!
    // 내가 만든 클래스가 아니라 라이브러리 클래스의 객체를 빈등록해서 주입 받기 위한 아노테이션
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Spring security에서 제공하는 기본 회원가입 화면과 기능이 있는데 이것을 사용하지 않겠다는 선언.
        http
                .csrf(csrfConfig -> csrfConfig.disable()) // CSRF 토큰 공격을 방지하기 위한 장치 해제.
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/**").permitAll());
                        // 모든 요청에 대해 Security 인증을 요구하지 않겠다. 모든 요청을 허용하겠다.
        return http.build();
    }



    // 비밀번호 암호화 객체를 빈 등록
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화에 핵심적인 객체
    }
}
