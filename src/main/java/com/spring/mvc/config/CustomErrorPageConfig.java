package com.spring.mvc.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

// 실제 에러가 발생하면 미리 만들어 놓은 에러 페이지로 안내하는 설정
// ErrorPageRegistrar 인터페이스를 꼭 구현해야 한다.
@Configuration // 설정용 bean 등록
public class CustomErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        // 내장 객체인 ErrorPage를 생성해 매개변수로 HttpStatus Enum 값과 ErrorController가 받을 경로를 지정한다.
        // ErrorPage([HttpStatus상태], [경로])
        // 지정한 경로로 요청이 가면서 ErrorController로 전달된다.
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/error404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/error500");

        // 이 메서드가 매개변수로 받은 ErrorPageRegistry 객체에 등록을 진행한다.
        registry.addErrorPages(errorPage404, errorPage500);
    }
}
