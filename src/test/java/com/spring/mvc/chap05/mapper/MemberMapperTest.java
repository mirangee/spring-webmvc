package com.spring.mvc.chap05.mapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.spring.mvc.chap05.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberMapperTest {
    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void saveMemberTest() {
        // given
        Member member = Member.builder()
                .account("abc1234")
                .password("abc1234")
                .name("김춘식")
                .email("abc1234@naver.com")
                .build();
        // when
        memberMapper.save(member);
        // then
    }
    
    
    @Test
    @DisplayName("아이디가 abc1234인 계정을 조회하면 그 회원의 이름은 김춘식이어야 한다.")
    void findMemberTest() {
        // given
        
        // when
        Member m = memberMapper.findMember("abc1234");
        // then
        Assertions.assertEquals("김춘식", m.getName());
    }

    @Test
    @DisplayName("계정이 abc1234일 경우 중복 확인 결과값이 true여야 한다.")
    void duplicateAccountTest() {
        // given
        String id = "abc1234";
        // when
        boolean result = memberMapper.isDuplicate("account", id);
        // then
        Assertions.assertTrue(result);
    }
    
    @Test
    @DisplayName("이메일이 abc@naver.com이 경우 중복 확인 결과값이 false여야 한다.")
    void duplicateEmailTest() {
        // given
        String email = "abc@naver.com";
        // when
        boolean result = memberMapper.isDuplicate("email", email);
        // then
        Assertions.assertFalse(result);
    }
}