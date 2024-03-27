package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.DTO.request.BoardWriteRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

/* 테이블 속성 값들
   board_no INT PRIMARY KEY AUTO_INCREMENT,
   title VARCHAR(100) NOT NULL,
   content VARCHAR(2000),
   view_count INT,
   reg_date DATETIME DEFAULT current_timestamp, # 자동으로 현재 시간이 들어감
   writer VARCHAR(50) NOT NULL
   */

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Board {
    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private int viewCount; // 조회수
    private LocalDateTime regDate; // 작성일자 시간
    private String writer; // 작성자

    public Board(BoardWriteRequestDTO dto) {
        title = dto.getTitle();
        content = dto.getContent();
        writer = dto.getWriter();
    }
}
