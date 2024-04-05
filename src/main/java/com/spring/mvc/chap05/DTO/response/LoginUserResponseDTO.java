package com.spring.mvc.chap05.DTO.response;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserResponseDTO {
    private String account;
    private String name;
    private String email;
    private String auth;
    private String profile;
}
