package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// JdbcTemplate이 SELECT 쿼리를 위한 ResultSet 사용을 편하게 하기 위해 클래스 생성
// JdbcTemplate에게 조회한 내용을 어떻게 가공해야 하는지 알려주는 클래스
// RowMapper 인터페이스를 구현해야 합니다.
// 이렇게 만들어 놓으면 JdbcTemplate이 알아서 조회된 튜플 개수 만큼 호출하기 때문에 반복문이 필요 없다.

// 제네릭 entity class 타입으로 잡으면 됨
public class ScoreMapper implements RowMapper<Score> {
    @Override
    public Score mapRow(ResultSet rs, int rowNum) throws SQLException {

        Score score = new Score(
                rs.getString("stu_name"),
                rs.getInt("kor"),
                rs.getInt("eng"),
                rs.getInt("math"),
                rs.getInt("stu_num"),
                rs.getInt("total"),
                rs.getDouble("average"),
                Grade.valueOf(rs.getString("grade"))
        );
        return score;
    }
}
