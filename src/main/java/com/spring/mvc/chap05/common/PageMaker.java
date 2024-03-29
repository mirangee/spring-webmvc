package com.spring.mvc.chap05.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

// 페이지 알고리즘 적용 객체
@Getter @ToString
@EqualsAndHashCode
public class PageMaker {
    private int begin, end, finalPage; // begin과 end는 시작번호와 끝번호, finalPage는 보정된 끝 페이지 번호 의미
    
    private boolean prev, next; // 이전, 다음 버튼 활성화 여부
    
    private Page page; // 현재 사용자가 요청한 페이지 정보(현재 위치할 페이지 No와 한 화면에 보여질 페이지 수 정보가 포함됨)
    
    private int totalCount; // 총 게시물 수(이걸 알아야 finalPage를 보정할 수 있음)

    public PageMaker(Page page, int totalCount) { // 이 두 가지 필드 외엔 로직을 작성해야 함.
        this.page = page;
        this.totalCount = totalCount;

        makePageInfo();
    }

    // 한 화면에 보여질 페이지 버튼 개수
    private static final int PAGE_COUNT = 10;

    private void makePageInfo() {
        // 끝 페이지 번호 (end) 계산
        this.end = (int) (Math.ceil((double)page.getPageNo() / PAGE_COUNT) * PAGE_COUNT);

        // 시작 페이지 번호 (begin) 계산
        this.begin = end - PAGE_COUNT + 1;

        // 이전 버튼 활성화 여부 (prev)
//        this.prev = begin == 1 ? false : true;
        this.prev = begin > 1;

        // 찐막 페이지를 먼저 구하기
        this.finalPage = (int) Math.ceil((double) totalCount / page.getAmount());

        // 마지막 페이지 구간에서 end값을 finalPage 값으로 변경
        if (this.finalPage < this.end) {
            this.end = this.finalPage;
        }

        // 다음 버튼 활성화 여부 (next)
        this.next = this.end < this.finalPage;


        /* 다른 방법(next 여부 구하고 찐막 페이지를 end 값에 바로 넣기)

        // next 여부 구하기
        this.next = this.end * page.getAmount() >= totalCount ? false : true;

        if(!next) {
            this.end = (int) Math.ceil((double) totalCount / page.getAmount());
        }

        */
    }
}
