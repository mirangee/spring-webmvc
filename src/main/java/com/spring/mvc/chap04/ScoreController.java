package com.spring.mvc.chap04;


/*
    # 컨트롤러
    - 클라이언트의 요청을 받아서 처리하고 응답을 제공하는 객체

    # 요청 URL Endpoint(내 목적에 따라 사용하는 경로)
    1. 학생의 성적정보 등록폼 화면을 보여주고
       동시에 지금까지 저장되어 있는 성적 정보 목록을 조회
    - /score/list   :   GET

    2. 학생의 입력된 성적정보를 데이터베이스에 저장하는 요청
    - /score/register   :  POST

    3. 성적정보를 삭제 요청
    - /score/remove    :  GET or POST

    4. 성적정보 상세 조회 요청
    - /score/detail  :   GET
 */

import com.spring.mvc.chap04.DTO.ScoreRequestDTO;
import com.spring.mvc.chap04.DTO.ScoreResponseDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor // final이 붙은 필드를 초기화하는 생성자 선언
public class ScoreController {
    // DB에 데이터를 저장하기 위해 컨트롤러는 서비스 객체가 꼭 필요하다.(의존관계)
    // 그렇기 때문에 ScoreController 객체를 생성할 때 자동으로 Service 객체가 생성되도록 해야 한다.
    // 의존성 주입하는 방법 2가지

    /*
    1. FM 방식으로 처리
    private ScoreService service; // ScoreController 클래스는 ScoreService에 의존하므로 전역변수로 선언

    @Autowired // ScoreController를 생성할 때 자동으로 ScoreService 객체도 생성되도록 세팅
    public ScoreController(ScoreService service) {
        this.service = service;
    }
    */

    // 2. Lombok으로 대체. 생성자가 단 하나라면 autowired를 생략할 수 있다.
    private final ScoreService service;
    //final 변수는 직접 초기값을 선언해 주거나 생성자를 통해 초기화 해야 한다.
    //lombok의 @RequiredArgsConstructor를 사용해 자동으로 생성자를 생성하도록 한다.


    // 1. 성적 입력폼 띄우기 및 성적 목록 조회
    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "sort", defaultValue = "num") String sort) {
        System.out.println("/score/list: GET!!");
        List<ScoreResponseDTO> dtoList = service.findAll(sort);
        model.addAttribute("sList", dtoList);
        return "chap04/score-list";
    }


    // 2. 학생의 입력된 성적정보를 데이터베이스에 저장(service 객체에 dto 넘기기)
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {
        System.out.println("/score/register: POST!!");
        System.out.println("dto = " + dto);

        service.insertScore(dto);

        // 등록이 완료되었다면 목록 화면으로 데이터를 전달해서 목록 화면을 보여주고 싶다.
        // redirect(리다이렉트)로 요청이 자동으로 다시 들어오게 한다.
        /*
            # forward vs redirect
            - 포워드는 요청 리소스를 그대로 전달해줌.
            - 따라서 URL이 변경되지 않고 한번의 요청과 한번의 응답만 이뤄짐
            - 포워딩할 JSP 파일 경로를 작성한다. (예: /views/chap04/score-list.jsp)

            - 리다이렉트는 요청 후에 자동응답이 나가고
              2번째 자동요청이 들어오면서 2번째 응답을 내보냄
            - 따라서 2번째 요청의 URL로 자동 변경됨
            - 클라이언트에서 다시 요청을 보냈으면 하는 요청 URL을 작성한다. (예: /score/list -> 목록 요청)
         */
        return "redirect:/score/list";
    }
    
    // 3. 성적 삭제 요청
    @PostMapping("/remove")
    public String remove(int stuNum) {
        System.out.println("/score/remove: POST!!");
        System.out.println("stuNum = " + stuNum);

        service.remove(stuNum);
        return "redirect:/score/list";
    }

    // 4. 성적 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int stuNum, Model model){
        Score pickedOne = service.findStudent(stuNum);
        model.addAttribute("stu", pickedOne);
        // 상세보기이기 때문에 DTO가 아닌 Entity를 담아서 jsp로 보냅니다.
        return "chap04/score-detail";
    }
}

