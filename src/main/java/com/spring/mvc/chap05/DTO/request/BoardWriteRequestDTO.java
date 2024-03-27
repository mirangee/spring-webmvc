package com.spring.mvc.chap05.DTO.request;

import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BoardWriteRequestDTO {
    private String writer;
    private String title;
    private String content;
}
