package com.spring.mvc.config;

// 브라우저에서는 직접 로컬 경로를 지목해서 데이터를 가져오지 못합니다.
// 로컬에 저장된 이미지 경로를 웹 브라우저에서 불러올 수 있게 URL을 만드는 설정

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LocalResourceConfig implements WebMvcConfigurer {

    @Value("${file.upload.root-path}")
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /*
         하드 디스크에 저장된 rootPath 아래의 파일은
         http url에서 /local/파일경로명으로 요청이 들어오면 접근할 수 있게 하겠다.
         여기서 local은 임의로 바꿀 수 있음
         가상의 URL로 요청이 들어오면 스프링에서 꺼내 주는 기능
        */
        registry
                .addResourceHandler("/local/**")
                .addResourceLocations("file:" + rootPath);
    }
}





