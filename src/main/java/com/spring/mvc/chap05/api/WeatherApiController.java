package com.spring.mvc.chap05.api;

import com.spring.mvc.chap05.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/weather")
@Slf4j
@RequiredArgsConstructor
public class WeatherApiController {
    private final WeatherService weatherService;

    @GetMapping("/view")
    public ModelAndView viewPage() {
        // RestController는 Controller와 달리 View Resolover를 거치지 않고
        // 화면으로 바로 return값을 보낸다.
        // 그러므로 return 값에 jsp 경로를 작성하지 않고
        // ModelAndView 객체에 남은 후 이 객체를 return한다.
        ModelAndView mv = new ModelAndView();
        mv.setViewName("api-test/api-form");
        return mv;
    }

    @GetMapping("/api-req/{area1}/{area2}")
    public void apiRequest(@PathVariable("area1") String area1,
                           @PathVariable("area2") String area2) {
        log.info("/api-req: GET!, area1: {}, area2: {}", area1, area2);

        weatherService.getShortTermForecast(area1, area2);
    }
}
