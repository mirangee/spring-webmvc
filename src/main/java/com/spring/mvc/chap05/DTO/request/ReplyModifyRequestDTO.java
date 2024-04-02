package com.spring.mvc.chap05.DTO.request;

import com.spring.mvc.chap05.entity.Reply;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@ToString @NoArgsConstructor
@AllArgsConstructor @EqualsAndHashCode
@Builder
public class ReplyModifyRequestDTO {
    
    @NotNull
    private int rno; // 수정할 댓글 번호
    
    @NotBlank
    private String text; // 수정할 댓글 내용

    // dto를 entity로 바꾸는 매서드
    public Reply toEntity() {
        return Reply.builder()
                .replyNo(rno)
                .replyText(text)
                .build();
    }
}
