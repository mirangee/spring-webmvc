package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;

import java.util.List;

/**
 * 성적정보를 잘 저장하고 조회하고 수정하고 삭제하는 역할
 * 데이터베이스와 같은 저장소에 접근하는 객체 (Data Access Object : DAO)
 *
 * 왜 인터페이스로 추상화하는가??
 * 실제로 저장소라는 개념은 구체적이지 않다!!
 * 파일로 저장, 인메모리 저장, 관계형 데이터베이스, NOSQL...
 * 추상화해놓고 DB별로 구체화하도록 하기 위해 인터페이스 선언
 */

public interface ScoreRepository {
    
    // 성적 정보 등록
    boolean save(Score score);

    // 성적 정보 전체 목록 조회
    List<Score> findAll(String sort);

    // 성적 정보 삭제
    boolean delete(int stuNum);

    // 성적 정보 개별 조회
    Score findOne(int stuNum);

    // 성적 정보 업데이트
    void update(Score score);
}
