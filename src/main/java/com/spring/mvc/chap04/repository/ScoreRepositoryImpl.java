package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository // 빈 등록해놔야 Service에 주입 가능
@RequiredArgsConstructor
public class ScoreRepositoryImpl implements ScoreRepository{
    //내부(중첩) 클래스 (inner class)
    //두 클래스가 굉장히 긴밀한 관계가 있을 때 주로 선언. 내부클래스는 public 쓰지 않는다.
    //해당 클래스 안에서만 사용할 클래스를 굳이 새 파일로 선언하지 않고 만들 수 있습니다.
    class ScoreMapper implements RowMapper<Score> {
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

    // spring-jdbc의 핵심 객체인 JdbcTemplate의 의존성 주입(생성자 주입)
    // 데이터베이스 접속 객체(Connection)을 바로 활용하는 것이 가능
    // ->  gradle.build에 히카리 뭐시기, application.properties에 이미 세팅 해놨기 때문
    private final JdbcTemplate jdbcTemplate; 
    
    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO tbl_score" +
                     "(stu_name, kor, eng, math, total, average, grade)" +
                     "VALUES(?,?,?,?,?,?,?)";
        int result = jdbcTemplate.update(sql, score.getName(), score.getKor(), score.getEng(), score.getMath(),
                                         score.getTotal(), score.getAverage(), score.getGrade().toString());
        return result == 1 ? true: false;
    }

    @Override
    public List<Score> findAll(String sort) {
        String sql = "SELECT * FROM tbl_score";

        switch (sort) {
            case "num":
                sql += " ORDER BY stu_num"; //꼭 맨 처음에 한 칸 띄어야 한다.
                break;
            case "name":
                sql += " ORDER BY stu_name";
                break;
            case "avg":
                sql += " ORDER BY average DESC";
                break;
        }


        List<Score> scoreList = jdbcTemplate.query(sql, new ScoreMapper());//query 메서드 import 시 rowMapper import 해야 함
        return scoreList;
    }

    @Override
    public boolean delete(int stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num = ?";
        int result = jdbcTemplate.update(sql,stuNum);
        return result == 1? true: false;
    }

    @Override
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";

        // query 메서드는 list를 반환하고, queryForObject는 객체 하나를 반환한다.
        // 조회 결과가 여러 개면 query를 사용하고 하나이면 queryForObjcet를 사용한다.
        // queryForObject(쿼리문, rowMapper 객체, ? 채울 변수)
        // queryForObject는 조회 결과가 없으면 예외가 발생하므로 예외처리가 필요하다.
        try {
            Score score = jdbcTemplate.queryForObject(sql, new ScoreMapper(), stuNum);
            return score;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
