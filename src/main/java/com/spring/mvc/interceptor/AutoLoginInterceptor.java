package com.spring.mvc.interceptor;

import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.mapper.MemberMapper;
import com.spring.mvc.chap05.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;

import static com.spring.mvc.util.LoginUtils.AUTO_LOGIN_COOKIE;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AutoLoginInterceptor implements HandlerInterceptor {
    private final MemberMapper memberMapper; //DB 조회해야 하므로 mapper 필요
    private final MemberService memberService; // 세션 생성 메서드 사용을 위해 필요

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
        // 1. 사이트에 들어오면 자동 로그인 쿠키를 가진 클라이언트인지를 체크
        // spring에서 쿠키를 쉽게 꺼내는 기능을 제공하는 클래스 WebUtils 이용
        // WebUtils.getCookie(request, 쿠키 이름);
        Cookie autoLoginCookie = WebUtils.getCookie(request, AUTO_LOGIN_COOKIE);
        
        // 2. 자동 로그인 쿠키가 있다면
        if(autoLoginCookie != null) {
            //3. 지금 읽어들인 쿠키가 가지고 있는 session_id를 꺼내자.
            String sessionId = autoLoginCookie.getValue();
            
            //4. DB에서 쿠키가 가지고 있던 session_id와 동일한 값을 가진 회원 조회
            Member member = memberMapper.findMemberByCookie(sessionId);

            // 5. 회원이 정상적으로 조회가 됐다면 and 자동로그인 만료 시간 이전이면
            if (member != null && LocalDateTime.now().isBefore(member.getLimitTime())) {
                // 6. 세션을 자동으로 생성
                memberService.maintainLoginState(request.getSession(), member.getAccount());
                
                //7.interceptor.config에 생성한 인터셉터 등록
            }
        }
        // 자동 로그인을 신청했던 사람이든 아니든
        // 요청은 무조건 Controller로 전달이 되어야 하니까
        // return 값은 true가 고정이다.
        return true;
    }
}
