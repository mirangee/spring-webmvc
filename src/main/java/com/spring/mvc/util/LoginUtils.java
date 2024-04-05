package com.spring.mvc.util;

import com.spring.mvc.chap05.DTO.response.LoginUserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.util.WebUtils;


// 회원 인증, 인가와 관련된 상수와 메서드를 가진 객체
// 메서드를 static으로 선언해 객체 생성 없이 바로 호출 가능하도록 디자인
public class LoginUtils {

    // 로그인 세션 키
    // 상수로 선언해 절대불변이라는 상징성 부여, 외부에서 값 변경 불가
    public static final String LOGIN_KEY = "login";
    
    // 자동 로그인 쿠키 이름
    public static final String AUTO_LOGIN_COOKIE = "auto";

    // 로그인 여부 확인
    // 이제 직접 session을 호출하지 않고 isLogin 메서드를 호출하면
    // "login" 여부를 알 수 있다.
    public static boolean isLogin(HttpSession session) {
        return session.getAttribute(LOGIN_KEY) != null;
    }

    // 로그인한 사람의 계정명을 반환해 주는 메서드
    public static String getCurrentLoginMemberAccount(HttpSession session) {
        // session.getAttribute의 리턴타입이 Object이기 때문에
        // 자식의 고유 기능과 속성을 사용하기 위해 자식 타입으로 형 변환
        LoginUserResponseDTO loginUser = (LoginUserResponseDTO) session.getAttribute(LOGIN_KEY);
        return loginUser.getAccount();
    }

    // 내가 쓴 게시물인지 확인해 주는 메서드
    public static boolean isMine(HttpSession session, String targetAccount) {
        return targetAccount.equals(getCurrentLoginMemberAccount(session));
    }

    // 관리자인지 확인해주는 메서드
    public static boolean isAdmin(HttpSession session) {
        LoginUserResponseDTO loginUser = (LoginUserResponseDTO) session.getAttribute(LOGIN_KEY);
        return loginUser.getAuth().equals("ADMIN");
    }

    // 자동 로그인 여부 확인
    public static boolean isAutoLogin(HttpServletRequest request) {
        // auto라는 이름의 쿠키가 존재한다면 true, 없다면 false
        return WebUtils.getCookie(request, AUTO_LOGIN_COOKIE) != null;
    }
}
