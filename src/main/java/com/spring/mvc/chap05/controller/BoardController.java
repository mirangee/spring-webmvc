package com.spring.mvc.chap05.controller;


import com.spring.mvc.chap05.DTO.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.DTO.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.DTO.response.BoardListResponseDTO;
import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String list(Model model, Page page) {
        System.out.println("/board/list: GET!!!");
        System.out.println("page =" + page);
        List<BoardListResponseDTO> dtoList = service.getList(page);
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
        return "redirect:/board/list";
    }

    // 4. 글 삭제 요청 (/board/delete : GET)
    // 글 번호 전달되면 삭제 진행
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete: GET!!" + bno);
        service.delete(bno);
        return "redirect:/board/list";
    }

    // 5. 글 상세보기 요청 (/board/detail : GET)
    // chap05/detail
    @GetMapping("/detail/{bno}")
    public String detail(@PathVariable("bno") int bno, Model model) { // URL 형태로 묻어오는 경우 변수 받기
        System.out.println("/board/detail: Get! " + bno);
        BoardDetailResponseDTO dto = service.getDetail(bno);
        model.addAttribute("b", dto);
        return "chap05/detail";
    }
}
