package com.spring.mvc.chap05.DTO.request;

import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LoginRequestDTO {
    private String account;
    private String password;
    private boolean autoLogin; // 자동 로그인 체크 여부
}
