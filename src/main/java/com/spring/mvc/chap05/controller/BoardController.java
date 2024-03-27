package com.spring.mvc.chap05.controller;


import com.spring.mvc.chap05.DTO.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.DTO.response.BoardListResponseDTO;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(Model model) {
        System.out.println("/board/list: GET!!!");
        List<BoardListResponseDTO> dtoList = service.getList();
        model.addAttribute("bList", dtoList);
        return "chap05/list";
    }

    // 2. 글쓰기 화면요청 (/board/write : GET)
    // chap05/write.jsp로 이동
    @GetMapping("/write")
    public String write() {
        System.out.println("/board/write: GET!!!");
        return "chap05/write";
    }

    // 3. 글쓰기 등록요청 (/board/write : POST)
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto) {
        System.out.println("/board/write: POST!!");
        System.out.println("dto = " + dto);
        service.register(dto);
        return "redirect:/chap05/list";
    }

    // 4. 글 삭제 요청 (/board/delete : GET)
    // 글 번호 전달되면 삭제 진행
    @GetMapping("/delete")
    public String delete() {
        return null;
    }

    // 5. 글 상세보기 요청 (/board/detail : GET)
    // chap05/detail
}
