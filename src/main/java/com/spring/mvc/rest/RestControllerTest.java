package com.spring.mvc.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest")
public class RestControllerTest {

    @GetMapping("/view")
    public String viewPage() {
        return "ajax/test02";
    }

    /*
    @RequestBody
    - 클라이언트 쪽에서 전송하는 JSON 데이터를
     서버에서 사용하는 자바 언어에 맞게 변환하여 받을 수 있는 아노테이션.

    @ResponseBody
    - 메서드가 리턴하는 데이터를 viewResolver에게 전달하지 않고,
    클라이언트에게 해당 데이터를 바로 응답하게 합니다.
    비동기 통신에서 주로 많이 사용합니다.
     */


    @PostMapping("/object")
    @ResponseBody
    public Person object(@RequestBody Person p) {
        System.out.println("비동기 방식의 요청이 들어옴!");
        System.out.println("p = " + p);

        // Person 객체 내부의 데이터를 변경해서 화면단으로 전송.
        p.setName("변경된 춘식이");
        p.setAge(100);

        return p; // 화면단에 Person객체를 바로 던져버리기~ (JSON으로 변환해서 리턴)
    }

    @PostMapping("/object2")
    @ResponseBody
    public Person object2(@RequestBody Person p) {
        System.out.println("fetch 요청이 들어옴!");
        System.out.println("p = " + p);

        // Person 객체 내부의 데이터를 변경해서 화면단으로 전송.
        p.setName("변경된 춘식이");
        p.setAge(100);

        return p; // 화면단에 Person객체를 바로 던져버리기~ (JSON으로 변환해서 리턴)
    }
}