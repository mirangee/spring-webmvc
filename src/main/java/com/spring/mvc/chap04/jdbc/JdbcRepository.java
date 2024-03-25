package com.spring.mvc.chap04.jdbc;

import com.spring.mvc.chap04.entity.Person;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JdbcRepository {

    // db 연결 설정 정보 (한 번 세팅해 놓으면 두고두고 쓰기 때문에 외우지 말 것)
    private String url = "jdbc:mysql://localhost:3306/spring_db?serverTimezone=Asia/Seoul"; // DB url 주소
    // 참고로, oracle은 url이 jdbc:oracle:thin://localhost:1521:xe 이런식으로 됨.
    private String username = "spring"; // 계정명
    private String password = "spring"; // 비밀번호


    // JDBC 드라이버 로딩(과거 유산 방법)
    // 자바 프로그램과 데이터베이스간에 객체가 드나들 길을 뚫어놓는다 생각하면 됨.
    public JdbcRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Driver 클래스를 강제 구동해서 자바 프로그램과 데이터베이스 연결
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 데이터베이스 커넥션 얻기 (Connection 객체 얻기 -> 데이터 베이스 접속을 담당하는 객체)
    public Connection getConnection() throws SQLException {
        //위에 준비한 url, username, password를 전달해서 DB 접속을 전담하는 Connection을 받아오기
        return DriverManager.getConnection(url, username, password);
        // DriverManager는 Driver 커넥터가 연결되었다면 부를 수 있는 객체다.
    } // 여기까지 오면 DB 접속 완료한 것. 이제 쿼리를 메서드 별로 선언해야 함


    // INSERT 기능 -> Test 클래스에서 테스트할 예정
    public void save(Person person) {
        Connection conn = null;
        // 1. DB 연결하고 연결 정보를 얻어와야 합니다.
        try {
            conn = getConnection();

            //2. 트랜잭션을 시작
            conn.setAutoCommit(false); // 자동 커밋 비활성화

            //3. 실행할 sql 완성 (문자열)
            // 들어갈 값이 위치할 곳에 ?를 채워준다. 이 ? 값을 나중에 채워주면 된다.
            String sql = "INSERT INTO person (person_name, person_age) " +
                         "VALUES (?,?)";
            
            //4. SQL을 실행시켜주는 객체를 얻어옵니다.
            // preparedStatement의 매개값으로 미완성된 sql 전달
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // 5. ? 채우기 (물음표 순서, 값의 타입을 고려해서 채우기)
            // ? 채울 때 지목하는 인덱스는 1번부터
            pstmt.setString(1, person.getPersonName());
            pstmt.setInt(2, person.getPersonAge());
 
            // 6. SQL 실행 명령
            // INSERT, UPDATE, DELETE -executeUpdate();
            // SELECT - executeQuery();
            int result = pstmt.executeUpdate(); // 리턴한 result 값은 성공한 쿼리문의 개수
            
            // 7. 트랜잭션 처리
            if(result == 1) conn.commit(); // 성공했으면 commit하고
            else conn.rollback(); // 실패했으면 rollback
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                // 8. 접속 해제
                conn.close(); // 접속 객체 닫아주기
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


}
