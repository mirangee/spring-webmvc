package com.spring.mvc.chap05.mapper;

import com.spring.mvc.chap05.entity.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    // 댓글 등록
    void save(Reply reply);

    // 댓글 수정 (내용)
    void modify(Reply reply);

    // 댓글 삭제
    void delete(int replyNo);

    // 댓글 개별 조회
    Reply findOne(int replyNo);

    // 댓글 전체 목록 조회
    List<Reply> findAll(int boardNo);

    // 댓글 총 개수 조회
    int count(int boardNo);
}
