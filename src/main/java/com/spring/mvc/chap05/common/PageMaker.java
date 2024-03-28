package com.spring.mvc.chap05.common;

// 페이지 알고리즘 적용 객체
public class PageMaker {
    private int begin, end, finalPage; // begin과 end는 시작번호와 끝번호, finalPage는 보정된 끝 페이지 번호 의미
    
    private boolean prev, next; // 이전, 다음 버튼 활성화 여부
    
    private Page page; // 현재 사용자가 요청한 페이지 정보
    
    private int totalCount; // 총 게시물 수(이걸 알아야 finalPage를 보정할 수 있음)

    public PageMaker(Page page, int totalCount) { // 이 두 가지 필드 외엔 로직을 작성해야 함.
        this.page = page;
        this.totalCount = totalCount;

        makePageInfo();
    }

    // 한 화면에 보여질 페이지 버튼 개수
    private static final int PAGE_COUNT = 10;

    private void makePageInfo() {
        // 끝 페이지 번호(end) 계산


        // 시작 페이지 번호(begin) 계산

        // 이전 버튼(prev) 활성화 여부 판단


        // 다음 버튼(next) 활성화 여부 판단


        // 필요하다면 end값 보정(finalPage)
    }
}
