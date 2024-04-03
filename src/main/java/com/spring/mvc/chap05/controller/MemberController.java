package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.DTO.request.LoginRequestDTO;
import com.spring.mvc.chap05.DTO.request.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
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
    public String signIn(LoginRequestDTO dto, RedirectAttributes ra) {
        System.out.println("/members/sign-in: POST!");
        System.out.println("dto = " + dto);

        LoginResult result = memberService.authenticate(dto);
        System.out.println("result = " + result);

        ra.addFlashAttribute("result", result); //리다이렉트에서 사용하는 객체

        if (result == LoginResult.SUCCESS) { // 로그인 성공 시
            return "redirect:/board/list";
        }
        return "redirect:/members/sign-in"; // 로그인 실패 시
    }
}
