package com.spring.mvc.chap05.service;


import com.spring.mvc.chap05.DTO.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.DTO.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.DTO.response.BoardListResponseDTO;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.entity.Board;


import lombok.RequiredArgsConstructor;
import com.spring.mvc.chap05.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
//    private final BoardRepositoryImpl repository;

    // mybatis가 우리가 만든 xml을 클래스로 변환해서 객체를 주입해 줌.
    private final BoardMapper mapper;

    public void register(BoardWriteRequestDTO dto) {
        Board board = new Board(dto); // dto를 entity로 변환
        mapper.save(board);
    }

    public List<BoardListResponseDTO> getList(Search page) {
        List<BoardListResponseDTO> dtoList = new ArrayList<>();
        List<Board> boardList = mapper.findAll(page);
        for (Board board : boardList) {
            BoardListResponseDTO dto = new BoardListResponseDTO(board);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public BoardDetailResponseDTO getDetail(int boardNo) {
        // 상세보기니까 조회수를 하나 올려주는 처리를 해야 한다.
        mapper.updateViewCount(boardNo);

        // 조회수가 하나 올라간 상태의 board 찾아오기
        Board board = mapper.findOne(boardNo);
        return new BoardDetailResponseDTO(board);
    }

    public void delete(int boardNo) {
        mapper.delete(boardNo);
    }

    public int getCount(Search page) {
        return mapper.getCount(page);

    }
}
