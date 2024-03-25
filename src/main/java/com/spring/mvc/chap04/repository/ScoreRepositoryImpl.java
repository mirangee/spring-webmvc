package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

@Repository // 빈 등록해놔야 Service에 주입 가능
public class ScoreRepositoryImpl implements ScoreRepository{

    @Override
    public boolean save(Score score) {
        return false;
    }
}
