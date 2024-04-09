package com.spring.mvc.chap05.service;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class SnsLoginService {
    private final ParameterNamesModule parameterNamesModule;

    public SnsLoginService(ParameterNamesModule parameterNamesModule) {
        this.parameterNamesModule = parameterNamesModule;
    }

    public void kakaoLogin(Map<String, String> params) {
        getKakaoAccessToken(params);

    }

    private void getKakaoAccessToken(Map<String, String> requestParam) {
        // API 문서 보면서 하면 된다.
        // 요청 URI
        String requestUri = "https://kauth.kakao.com/oauth/token";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        // 요청 바디에 파라미터 세팅
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", requestParam.get("appKey"));
        params.add("redirect_uri", requestParam.get("redirect"));
        params.add("code", requestParam.get("code"));

        // 카카오 인증 서버로 POST 요청 날리기
        // 서버에서 비동기 요청을 보낼 수 있음
        RestTemplate template = new RestTemplate();

        // 헤더 정보와 파라미터를 하나로 묶기
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(params, headers);

        /*
            - RestTemplate객체가 REST API 통신을 위한 API인데 (자바스크립트 fetch역할)
            - 서버에 통신을 보내면서 응답을 받을 수 있는 메서드가 exchange
            param1: 요청 URL
            param2: 요청 방식 (get, post, put, patch, delete...)
            param3: 요청 헤더와 요청 바디 정보 - HttpEntity로 포장해서 줘야 함
            param4: 응답결과(JSON)를 어떤 타입으로 받아낼 것인지 (ex: DTO로 받을건지 Map으로 받을건지)
         */

        ResponseEntity<Map> responseEntity
                = template.exchange(requestUri, HttpMethod.POST, requestEntity, Map.class);

        // 응답 데이터에서 JSON 추출

    }
}
