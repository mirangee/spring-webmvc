package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.DTO.request.LoginRequestDTO;
import com.spring.mvc.chap05.DTO.request.SignUpRequestDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 처리 서비스
    public void join(SignUpRequestDTO dto) {

        // 클라이언트가 보낸 회원가입 데이터를 패스워드 인코딩하여 엔터티로 변환해서 전달
    //    String encodedPw = encoder.encode(dto.getPassword()); // 암호화해서 50자의 코드로 만들어 준다.
    //    dto.setPassword(encodedPw); // dto의 Password를 인코딩된 Password로 세팅

        memberMapper.save(dto.toEntity(encoder));

    }

    // 로그인 검증 처리
    public LoginResult authenticate(LoginRequestDTO dto) {

        Member foundMember = memberMapper.findMember(dto.getAccount());

        // mybatis는 select 결과가 없으면 null을 주므로 검증 필요
        if(foundMember == null) { // 회원가입 안 한 상태
            System.out.println(dto.getAccount() + "은(는) 없는 아이디입니다.");
            return LoginResult.NO_ACC; // enum 값 리턴
        }

        // 비밀번호 일치 검사
        String inputPassword = dto.getPassword(); // 사용자 입력 패스워드
        String realPassword = foundMember.getPassword(); // 실제 패스워드

        // matches(입력 비번, 암호화된 비번) -> 둘이 일치하면 true, 일치하지 않으면 false
        // equals로 비교하면 안 된다!!
        if (!encoder.matches(inputPassword, realPassword)) {
            System.out.println("비밀번호가 다르다!");
            return  LoginResult.NO_PW;
        }

        System.out.println(dto.getAccount() + "님 로그인 성공하셨드아");

        return LoginResult.SUCCESS;
    }

}
