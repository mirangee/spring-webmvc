package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.DTO.request.LoginRequestDTO;
import com.spring.mvc.chap05.DTO.request.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입 양식 화면 요청
    // 응답하고자 하는 화면의 경로가 URL과 동일하다면 void로 처리할 수 있다. (선택사항)
    @GetMapping("/sign-up")
    public void signUp() {
        System.out.println("/members/sign-up: GET!!!");
//        return "members/sign-up";
    }

    // 아이디, 이메일 중복체크 비동기 요청 처리
    @GetMapping("/check/{type}/{keyword}")
    @ResponseBody
    public ResponseEntity<?> check(@PathVariable String type, @PathVariable String keyword) {
        System.out.println("/members/check: async GET!!");
        System.out.println("type = " + type);
        System.out.println("keyword = " + keyword);

        boolean flag = memberService.checkDuplicateValue(type, keyword);
        
        return ResponseEntity.ok().body(flag);
    }
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto) {
        System.out.println("/members/sign-up: POST!");
        System.out.println("dto = " + dto);
        memberService.join(dto);
        return "redirect:/board/list";
    }

    // 로그인 양식 화면 요청 처리
    @GetMapping("/sign-in")
    public void signIn() {
        System.out.println("/members/sign-in : GET!!!");
    }

    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto, RedirectAttributes ra, HttpServletResponse response) {
        System.out.println("/members/sign-in: POST!");
        System.out.println("dto = " + dto);

        LoginResult result = memberService.authenticate(dto);
        System.out.println("result = " + result);

        ra.addFlashAttribute("result", result); //리다이렉트에서 사용하는 객체

        if (result == LoginResult.SUCCESS) { // 로그인 성공 시
            
            // 로그인했다는 정보를 계속 유지하기 위한 수단으로 쿠키를 사용하자.
            makeLoginCookie(dto, response); // 쿠키 만들 때 HttpServletResponse 객체가 필요하다.
            return "redirect:/board/list";
        }
        return "redirect:/members/sign-in"; // 로그인 실패 시
    }

    private void makeLoginCookie(LoginRequestDTO dto, HttpServletResponse response) {
        // 쿠키에 로그인 기록을 저장
        // 쿠키 객체를 생성 -> 생성자의 매개값으로 쿠키의 이름과 저장할 값을 전달(단, 문자열만 저장됨(특수문자나 공백도 안됨). 4KB 용량 제한도 있음)
        // 간단한 것들만 저장하는 용도로 사용
        Cookie cookie = new Cookie("login", dto.getAccount());

        // 쿠키 세부 설정
        cookie.setMaxAge(60); // 쿠키 수명 설정 (초)
        cookie.setPath("/"); // 유효 경로 설정: 이 쿠키는 모든 경로에서 유효

        // 쿠키가 완성됐다면 응답객체(HttpServletResponse)에 쿠키를 태워서 클라이언트로 전송
        response.addCookie(cookie);
    }
}
