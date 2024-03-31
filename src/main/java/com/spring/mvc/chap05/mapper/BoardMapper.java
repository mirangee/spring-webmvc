package com.spring.mvc.chap05.mapper;

import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper // mybatis의 SQL 실행을 위한 인터페이스임을 명시
// mybatis는 클래스를 따로 만들지 않고
// xml 파일에 sql을 작성하면 알아서 클래스를 만들어서 실행한다.
// eks, mybatis-config에 명시해 줘야 함
// spring JDBC는 자바를 모르면 작성 못하지만
// mybatis는 xml로 작성하므로 자바를 몰라도 쉽게 작성 가능하다.
public interface BoardMapper {
    // 목록 조회
    List<Board> findAll(Search page);

    // 상세 조회
    Board findOne(int boardNo);

    // 게시물 등록
    void save(Board board);

    // 게시물 삭제
    void delete(int boardNo);

    // 조회수 처리
    void updateViewCount(int boardNo);

    // 총 게시물의 갯수 리턴
    int getCount(Search page);
}
