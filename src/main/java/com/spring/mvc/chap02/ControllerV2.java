package com.spring.mvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/model") // 공통 url 맵핑 -> 매서드마다 /model을 작성할 필요 없음
public class ControllerV2 {
        /*
        /model/hobbies : GET
        -> hobbies.jsp파일로 사용자 이름정보와 취미목록을 뿌려주고 싶다.
        == 1. Model 객체 사용
        -> 자바가 갖고 있는 데이터를 JSP로 넘겨줄 때 사용하는 바구니같은 역할
       */

    @GetMapping("/hobbies")
    public String hobbies(Model model) {
        System.out.println("/model/hobbies: GET으로 요청됨!");
        String name = "짹짹이";
        List<String> hobbyList = new ArrayList<>();
        Collections.addAll(hobbyList, "전깃줄 위에 앉아있기", "짹짹거리기", "좁쌀 훔쳐먹기", "멍때리기");

        // Model 객체에 데이터 담기 ("이름", 값)
        // 담아놓기만 하면 model에 알아서 담아서 jsp로 던진다.
        model.addAttribute("userName", name);
        model.addAttribute("hobbies", hobbyList);

        return "chap02/hobbies";
    }

    // 2. ModelAndView 객체 사용
    @GetMapping("/hobbies2")
    public ModelAndView hobbies2(){
        System.out.println("/model/hobbies2: GET 요청!!");

        // jsp로 보낼 데이터 생성
        String name = "냥냥이";
        List<String> hobbyList = List.of("낮잠 자기", "캣타워 올라가기", "털뭉치 굴리기");
        // List.of로 리스트를 바로 세팅할 수 있다. 하지만 이 리스트는 불변 리스트가 된다(변경, 삽입, 수정 불가).
        
        // jsp로 보낼 데이터를 ModelAndView에 담기
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName", name);
        mv.addObject("hobbies", hobbyList);
        
        // view의 데이터를 따로 담아줌
        mv.setViewName("chap02/hobbies");
        
        return mv; // 문자열이 아닌 mv 객체를 리턴
    }

    // 처음에는 1번 방식이 더 쉬워보이지만 2번 방법도 꼭 필요할 때(Rest API 쪽)가 있으므로 알아둬야 한다.
    // 그리고 1번도 결국 dispatcher가 ModelAndView 객체로 만들어 보내는 것이므로 방식은 둘이 같다고 볼 수 있다.
}
