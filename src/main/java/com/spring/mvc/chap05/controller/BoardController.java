package com.spring.mvc.chap05.controller;


import com.spring.mvc.chap05.DTO.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.DTO.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.DTO.response.BoardListResponseDTO;
import com.spring.mvc.chap05.common.PageMaker;
import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(Model model, @ModelAttribute("s") Search page) { // page 정보도 처리 가능하며 검색 조건, 검색어를 받을 수 있는 Search 객체를 매개변수로 받음
        System.out.println("/board/list: GET!!!");
        System.out.println("Search =" + page);
        List<BoardListResponseDTO> dtoList = service.getList(page); // search 객체가 넘어감

        // 페이징 버튼 알고리즘 적용 -> 사용자가 요청한 페이지 정보, 총 게시물 개수를 전달
        // 생성자에 의해 페이징 알고리즘 자동 호출
        int totalCount = service.getCount(page);
        PageMaker pageMaker = new PageMaker(page, service.getCount(page));

        // model에 글 목록 뿐만 아니라 페이지 버튼 정보도 같이 담아서 전달하자.
        model.addAttribute("bList", dtoList);
        model.addAttribute("maker", pageMaker);

        // 매서드의 파라미터값을 model 객체에 바로 추가하려면 @ModelAttribute로 바로 모델에 담을 수 있다.
//        model.addAttribute("search", page);
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
    public String detail(@PathVariable("bno") int bno, @ModelAttribute("s") Search search, Model model) { // URL 형태로 묻어오는 경우 변수 받기
        System.out.println("/board/detail: Get! " + bno);
        BoardDetailResponseDTO dto = service.getDetail(bno);
        model.addAttribute("b", dto);
        return "chap05/detail";
    }


}
