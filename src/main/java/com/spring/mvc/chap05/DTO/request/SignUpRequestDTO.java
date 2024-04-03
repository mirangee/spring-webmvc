package com.spring.mvc.chap05.DTO.request;

import com.spring.mvc.chap05.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequestDTO {

    @NotBlank
    @Size(min = 4, max = 14)
    private String account;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 2, max = 6)
    private String name;

    @NotBlank
    @Email // 이메일 형식인지 검증해 주는 아노테이션
    private String email;

    // dto를 엔터티로 변환하는 유틸메서드
    public Member toEntity(PasswordEncoder encoder) {
        return Member.builder()
                .account(this.account)
                .password(encoder.encode(this.password)) // 암호화된 50자의 Password가 세팅됨
                .name(this.name)
                .email(this.email)
                .build();
    }
}
