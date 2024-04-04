package com.spring.mvc.config;

import com.spring.mvc.interceptor.AfterLoginInterceptor;
import com.spring.mvc.interceptor.AutoLoginInterceptor;
import com.spring.mvc.interceptor.BoardInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//내가 만든 인터셉터들을 스프링 컨텍스트에 등록하는 설정 파일
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final AfterLoginInterceptor afterLoginInterceptor;
    private final BoardInterceptor boardInterceptor;
    private final AutoLoginInterceptor autoLoginInterceptor; //1. 의존성 주입 등록

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 2. 인터셉터 등록
        // 로그인 후 비회원 전용 페이지 접근 차단 인터셉터 등록
        registry
                .addInterceptor(afterLoginInterceptor) // 어떤 인터셉터를 등록할 것인가
                .addPathPatterns("/members/sign-up", "/members/sign-in" ); // 어떤 요청에서 인터셉터를 동작하게 할 것인가

        registry
                .addInterceptor(boardInterceptor)
                .addPathPatterns("/board/*") // board로 들어오는 모든 요청에 인터셉터 동작
                .excludePathPatterns("/board/list", "/board/detail"); // 인터셉터 발동을 제외할 패턴

        // 자동 로그인 인터셉터 설정
        registry
                .addInterceptor(autoLoginInterceptor)
                .addPathPatterns("/**"); // * 한 개와 두 개의 차이는? 한 개면 슬래시(/) 하나만 허용, 두 개면 어떤 URL이든 허용
    }
}
