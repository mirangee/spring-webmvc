package com.spring.mvc.chap05.DTO.request;

import com.spring.mvc.chap05.entity.Reply;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyPostRequestDTO {

    // 검증 역할을 하는 validation 라이브러리 사용
    // NotNull: null만 안됨! 빈문자열은 됨!
    // NotBlank: null 안됨! 빈문자열도 안됨!

    @NotBlank
    @Size(min = 1, max = 300)
    private String text; // 댓글 내용

    @NotBlank
    @Size(min = 2, max = 8) // 최소 2글자, 8글자 넘어가면 error 발생
    private String author; // 댓글 작성자

    @NotNull
    private int bno; // 원본 글번호


    // dto를 entity로 바꾸는 변환 메서드
    public Reply toEntity() {
        /*
        Reply reply = new Reply();
        reply.setReplyText(this.text);
        reply.setReplyWriter(this.author);
        reply.setBoardNo(this.bno);
        return reply;
         */
        return Reply.builder()
                .replyText(this.text)
                .replyWriter(this.author)
                .boardNo(this.bno)
                .build();
    }

}