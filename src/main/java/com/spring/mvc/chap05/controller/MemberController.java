package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.DTO.request.LoginRequestDTO;
import com.spring.mvc.chap05.DTO.request.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j // 이 클래스 안에서 log 찍겠다는 선언
public class MemberController {
    private final MemberService memberService;

    // 회원가입 양식 화면 요청
    // 응답하고자 하는 화면의 경로가 URL과 동일하다면 void로 처리할 수 있다. (선택사항)
    @GetMapping("/sign-up")
    public void signUp() {
        log.info("/members/sign-up: GET"); //sysout 대신 이렇게 출력(파일로도 출력 가능)
//        return "members/sign-up";
    }

    // 아이디, 이메일 중복체크 비동기 요청 처리
    @GetMapping("/check/{type}/{keyword}")
    @ResponseBody
    public ResponseEntity<?> check(@PathVariable String type, @PathVariable String keyword) {
        log.info("/members/check: async GET!!");
        log.debug("type: {}, keyword: {}", type, keyword); // 변수가 들어갈 자리를 중괄호로 지정하고 변수를 알려주면 됨.

        boolean flag = memberService.checkDuplicateValue(type, keyword);
        
        return ResponseEntity.ok().body(flag);
    }
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto) {
        log.info("/members/sign-up: POST, dot: {}", dto);
        memberService.join(dto);
        return "redirect:/board/list";
    }

    // 로그인 양식 화면 요청 처리
    @GetMapping("/sign-in")
    public void signIn() {
        log.info("/members/sign-in : GET!!!");
    }

    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto, RedirectAttributes ra, HttpServletResponse response, HttpServletRequest request) {
        log.info("/members/sign-in: POST!, dto: {}", dto);

        LoginResult result = memberService.authenticate(dto);
        log.debug("result: {}", result);

        ra.addFlashAttribute("result", result); //리다이렉트에서 사용하는 객체

        if (result == LoginResult.SUCCESS) { // 로그인 성공 시
            
            // 로그인했다는 정보를 계속 유지하기 위한 수단으로 쿠키를 사용하자.
//            makeLoginCookie(dto, response); // 쿠키 만들 때 HttpServletResponse 객체가 필요하다.

            // 세션으로 로그인 유지
            memberService.maintainLoginState(request.getSession(), dto.getAccount());
            // 세션을 만들 때 HttpServletRequest 객체가 필요하다.
            return "redirect:/board/list";
        }
        return "redirect:/members/sign-in"; // 로그인 실패 시
    }

    private void makeLoginCookie(LoginRequestDTO dto, HttpServletResponse response) {
        // 쿠키에 로그인 기록을 저장
        // 쿠키 객체를 생성 -> 생성자의 매개값으로 쿠키의 이름과 저장할 값을 전달(단, 문자열만 저장됨(특수문자나 공백도 안됨). 4KB 용량 제한도 있음)
        // 쿠키는 클라이언트의 하드디스크에 파일 형태로 저장됨. 갈취 가능성이 있음.
        // 간단한 것들만 저장하는 용도로 사용
        Cookie cookie = new Cookie("login", dto.getAccount());

        // 쿠키 세부 설정
        cookie.setMaxAge(60); // 쿠키 수명 설정 (초)
        cookie.setPath("/"); // 유효 경로 설정: 이 쿠키는 모든 경로에서 유효

        // 쿠키가 완성됐다면 응답객체(HttpServletResponse)에 쿠키를 태워서 클라이언트로 전송
        response.addCookie(cookie);
    }

    // 로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        log.info("members/sign-out: Get");

        // 로그아웃 처리 2가지 방법
        // 로그인 정보 외에 다른 정보도 세션에 포함하고 있다면 1번 사용
        // 세션 모든 정보를 초기화해도 된다면 2번 사용
        // 1. 세션에서 로그인 정보 기록 삭제
        session.removeAttribute("login");

        // 2. 세션 전체 무효화(초기화)
        session.invalidate();

        return "redirect:/";
    }
}
