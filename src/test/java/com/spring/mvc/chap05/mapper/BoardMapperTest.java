package com.spring.mvc.chap05.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("test")
    void testSelect() {
        // given

        // when
//        mapper.findAll(page).forEach(board -> System.out.println(board));
        // then
    }

}