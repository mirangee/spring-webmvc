package com.spring.mvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 컨트롤러: 클라이언트의 요청을 받아서 처리 후 응답을 결정하는 역할
@Controller // @Component로 해도 되지만 controller 역할을 할 것이므로 @Controller로 빈 등록한다.
// 이 클래스의 객체 생성 및 관리는 스프링 컨테이너가 처리한다.
public class ControllerV1 {

    // Spring에서는 메서드 별로 url을 매핑하여 처리한다. 이전에는 jsp에서 확장자 패턴을 활용해 mapping했던 것과 비교되는 방법..
//    @RequestMapping(value = "/food", method = RequestMethod.GET)
    @GetMapping("/food")
    public String food() {
        // 리턴문에는 어떤 jsp로 포워딩할지 경로를 적습니다.
        // 경로를 작성할 때는 view resolver 세팅에 작성된 접두어, 접미어를 고려하여 씁니다.
        // 현재 application.properties 파일을 보면 view resolver에 접두어 /WEB-INF/views/, 접미어 .jsp로 세팅되어 있음.
        // 그러므로 경로 /WEB-INF/views/뭐시기.jsp 중 뭐시기 부분을 return 해주면 된다.
        return "chap01/food";
    }

    // ==================== 요청 파라미터 읽기 (클라이언트가 보낸 데이터) ======================
    // 1. HttpServletRequest 객체 이용하기 -> 전통적 방식

    // URL이 같아도 전송 요청 방식에 따라 다르게 처리를 할 수 있다.
//    @RequestMapping(value = "/food", method = RequestMethod.POST)
    // RequestMapping을 아래와 같이 간단하게 바꿀 수 있음.
    /*
    @PostMapping("/food")
    public String foodReg(HttpServletRequest request) {
        String foodName = request.getParameter("foodName");
        String category = request.getParameter("category");
        System.out.println("foodName = " + foodName);
        System.out.println("category = " + category);
        return null;
    }
    */

    // 2. @RequestParam 사용하기
    // 매개변수로 '@RequestParam("파라미터 이름") 이 값을 받을 매개변수'를 작성한다.
    // 1번 방법은 getParameter했을 시 무조건 문자열로 반환되어서 경우에 따라 ParseInt 등을 해야 했다.
    // 하지만 이 방식은 내가 원하는 타입을 미리 지정 가능해 타입변환할 필요가 없다.
    // 심지어, 파라미터 이름과 매개변수 이름이 같으면 @RequestParam을 아예 생략할 수 있다.
    /*  
    @PostMapping("/food")
    public String food(@RequestParam("foodName") String name,
                       String category,
                       int price) {
        System.out.println("name = " + name);
        System.out.println("category = " + category);
        System.out.println("price = " + price);
        return null;
    }
    */
    
    // 3. DTO (Data Transfer Object) 객체 사용 -> 커맨드 객체를 활용한 파라미터 처리
    // 처리해야 할 파라미터가 많거나 혹은 서로 연관되어 있는 데이터인 경우 사용한다.
    @PostMapping("/food")
    public String food(FoodOrderDTO orderDTO){
        System.out.println("orderDTO = " + orderDTO);
        return null;
    }
}
