package com.spring.mvc.interceptor;

import com.spring.mvc.util.LoginUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 - 인터셉터: 컨트롤러에 요청이 들어가기 전, 후에
            공통적으로 처리할 코드나 검사할 일들을 정의해 놓는 클래스
            1. 인터셉터 클래스 선언: HandlerInterceptor를 구현해야 하며 @Configuration 등록한다.
            2. preHandle 메서드를 오버라이딩한다.
            3. 메서드 내부 작성
            4. 필요에 따라 공통 로직을 수행하는 클래스 생성
            5. interceptorConfig 클래스에 이 인터셉터 클래스 등록 진행
*/
@Configuration
@Slf4j
public class BoardInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 로그인하지 않았다면 글쓰기, 글 수정, 글 삭제 요청을 튕겨내기
//        if (session.getAttribute("login") != null)  -> LoginUtils라는 공통 로직을 생성해 코드 명료, 간편화
          if (!LoginUtils.isLogin(session)) {
            log.info("권한 없음! 요청 거부 - {}", request.getRequestURI());
            response.sendRedirect("/members/sign-in");
            return false;
        }
          return true;
    }
}
