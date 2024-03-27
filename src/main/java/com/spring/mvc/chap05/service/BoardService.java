package com.spring.mvc.chap05.service;


import com.spring.mvc.chap05.DTO.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.DTO.response.BoardListResponseDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepositoryImpl repository;

    public void register(BoardWriteRequestDTO dto) {
        Board board = new Board(dto); // dto를 entity로 변환
        repository.save(board);
    }

    public List<BoardListResponseDTO> getList() {
        List<BoardListResponseDTO> dtoList = new ArrayList<>();
        List<Board> boardList = repository.findAll();
        for (Board board : boardList) {
            BoardListResponseDTO dto = new BoardListResponseDTO(board);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
