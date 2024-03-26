package com.spring.mvc.chap04.service;

/*
* 컨트롤러와 레파지토리 사이에 위치하여
* 중간 로직을 처리하는 역할
* 컨트롤러 <-> 서비스 <-> 레파지토리
* */

import com.spring.mvc.chap04.DTO.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // Service 객체로 빈등록, 별칭 달고 싶으면 @Service("별칭") 하면 됨
@RequiredArgsConstructor
public class ScoreService {
    
    // 서비스는 레파지토리 계층과 의존관계가 있으므로 객체가 생성될 떄 자동 주입 세팅
    private final ScoreRepository repository;  
    
    // 성적 입력 중간 처리
    // 컨트롤러가 DTO를 넘김 -> 서비스는 값을 정제하고 Entity class로 변환 -> 레파지토리 계층에게 넘기자.
    public boolean insertScore(ScoreRequestDTO dto) {
        Score score = new Score(dto); // Score 클래스가 Entity class임
        return repository.save(score);
    }
}
