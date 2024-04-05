package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.DTO.request.LoginRequestDTO;
import com.spring.mvc.chap05.DTO.request.SignUpRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spring.mvc.chap05.service.LoginResult.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원정보를 전달하면 비밀번호가 암호화되어 DB에 저장될 것이다.")
    void joinTest() {
        // given
        SignUpRequestDTO dto = SignUpRequestDTO.builder()
                .account("hong1234")
                .password("hhh4321!")
                .name("홍길동")
                .email("hong@naver.com")
                .build();
        // when
        memberService.join(dto, savePath);
        // then
    }

    @Test
    @DisplayName("계정명이 hong1234인 회원의 로그인 시도 결과를 상황별로 검증한다.")
    void loginTest() {
        // given
        LoginRequestDTO dto = LoginRequestDTO.builder()
                .account("hong1234")
                .password("hhh4321!")
                .build();
        // when
        LoginResult result = memberService.authenticate(dto, request.getSession(), response);
        // then
        Assertions.assertEquals(SUCCESS, result);
    }
}