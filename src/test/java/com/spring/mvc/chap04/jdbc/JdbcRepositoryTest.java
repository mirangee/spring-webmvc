package com.spring.mvc.chap04.jdbc;

import com.spring.mvc.chap04.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcRepositoryTest {
    @Autowired
    JdbcRepository repository;

    @Test
    @DisplayName("Person 객체 정보를 데이터베이스에 정상 삽입해야 한다") // 이 테스트의 목표 명시
    void saveTest() {
        Person p = new Person(1, "홍길동", 50);
        repository.save(p);
    }

    @Test
    @DisplayName("id가 2번인 회원의 이름과 나이를 수정한다")
    void updateTest() {
        // 테스트의 세 단계 (given, when, then)
        //1. given: 테스트를 위해 데이터를 세팅
        int id = 2;
        String newName = "개구리";
        int newAge = 15;
        Person p = new Person(id, newName, newAge);
        //2. when: 테스트 목표를 확인하여 실제 테스트가 진행되는 구간
        repository.update(p);
        //3. then: 테스트 결과 확인 구간 (단언 기법 -> Assertion이라는 객체 사용)
    }

    @Test
    @DisplayName("id가 1번인 회원 정보를 삭제해야 한다")
    void deleteTest() {
        // 테스트의 세 단계 (given, when, then)
        //1. given: 테스트를 위해 데이터를 세팅
        int id = 1;
        //2. when: 테스트 목표를 확인하여 실제 테스트가 진행되는 구간
        repository.delete(id);
        //3. then: 테스트 결과 확인 구간 (단언 기법 -> Assertion이라는 객체 사용)
    }

    @Test
    @DisplayName("더미 데이터 10개를 삽입한다.")
    void vulkInsert() {
        for (int i = 0; i < 10; i++) {
            Person p = new Person(0, "랄랄" + i, i +10);
            repository.save(p);
        }
    }

    @Test
    @DisplayName("전체 회원을 조회하면 회원 리스트의 사이즈는 11일 것이다.")
    void findAllTest() {
        List<Person> people = repository.findAll();
        for (Person person : people) {
            System.out.println(person);
        }

        // 나는 people의 사이즈가 11과 같은 것임을 단언하겠다!
        Assertions.assertEquals(11, people.size());
        // static으로 import 하면 AssertEquals(11, people.size()); 이렇게 씀

        // 나는 people이 Null이 아니라는 걸 단언하겠다!
        assertNotNull(people);
    }

    @Test
    @DisplayName("특정 회원을 조회하면 하나의 회원만 조회되고, 없는 id를 전달하면 null이 리턴될 것이다.")
    void findOneTest() {
        int id = 600;
        Person p = repository.findOne(id);
        System.out.println("p = " + p);

        // p가 null이 아닐 것임을 단언하겠다!
//        assertNotNull(p);
        // p가 null임을 단언하겠다!
        assertNull(p);
    }
}