package com.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/error404")
    public String error404() {
        return "error/error404";
    }

    @GetMapping("/error500")
    public String error500() {
        return "error/error500";
    }
}
