package com.spring.mvc.chap05.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Page 클래스를 상속해 Page가 가진 자원들을 활용
@Setter @Getter @ToString
@EqualsAndHashCode
public class Search extends Page{

    // 검색 조건, 검색어
    private String type, keyword;

    public Search() {
        this.type = "";
        this.keyword = "";
    }
}
