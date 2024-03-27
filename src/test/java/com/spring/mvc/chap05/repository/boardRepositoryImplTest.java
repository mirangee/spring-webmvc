package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class boardRepositoryImplTest {

    @Autowired
    private boardRepository repository;
    
    @Test
    @DisplayName("게시글이 300개 만들어져야 한다")
    void bulkInsertTest() {
        // given
        // 반복문 이용하여 저장하는데 작성자, 제목, 내용에 제어변수 값을 붙여서 insert할 것
        for (int i = 1; i < 301; i++) {
            Board board = new Board();
            board.setWriter("김테스트"+i);
            board.setTitle("테스트 제목" +i);
            board.setContent("테스트 내용" +i);
            repository.save(board);
        }
        // when

        // then
    }
    
    @Test
    @DisplayName("게시글 전체 조회하면 300개의 글이 조회되어야 한다.")
    void findAllTest() {
        // given
        
        // when
        List<Board> boardList = repository.findAll();
        // then
        assertEquals(300, boardList.size());
    }
    
    @Test
    @DisplayName("30번 게시물을 단일 조회하면 제목에 30이 포함되어 있어야 한다.")
    void findOneTest() {
        // given
        int boardNo = 30;
        // when
        Board board = repository.findOne(boardNo);
        // then
        assertTrue(board.getTitle().contains("30"));
    }
    
    @Test
    @DisplayName("29번 게시물을 삭제하고 다시 조회하면 조회되지 않아야 한다.")
    void deleteTest() {
        // given
        int boardNo = 29;
        // when
        repository.delete(boardNo);

        // then
        assertNull(repository.findOne(29));
    }
}