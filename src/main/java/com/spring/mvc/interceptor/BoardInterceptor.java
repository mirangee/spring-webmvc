package com.spring.mvc.interceptor;

import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.mapper.BoardMapper;
import com.spring.mvc.util.LoginUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

import static com.spring.mvc.util.LoginUtils.isAdmin;
import static com.spring.mvc.util.LoginUtils.isMine;

/*
 - 인터셉터: 컨트롤러에 요청이 들어가기 전, 후에
            공통적으로 처리할 코드나 검사할 일들을 정의해 놓는 클래스
            1. 인터셉터 클래스 선언: HandlerInterceptor를 구현해야 하며 @Configuration 등록한다.
            2. preHandle 메서드를 오버라이딩한다.
            3. 메서드 내부 작성
            4. 필요에 따라 공통 로직을 수행하는 클래스 생성
            5. interceptorConfig 클래스에 이 인터셉터 클래스 등록 진행
*/
@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {
    // 여기 인터셉터에서 Board쪽 DB 조회가 필요해서 주입받겠다.
    private final BoardMapper boardMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 로그인하지 않았다면 글쓰기, 글 수정, 글 삭제 요청을 튕겨내기
       // if (session.getAttribute("login") != null)  -> LoginUtils라는 공통 로직을 생성해 코드 명료, 간편화
          if (!LoginUtils.isLogin(session)) {
            log.info("권한 없음! 요청 거부 - {}", request.getRequestURI());
            response.sendRedirect("/members/sign-in");
            return false;
        }
        //삭제 요청이 들어올 때 서버에서 다시 한 번 내가 쓴 글인지를 체크
        // 현재 요청이 삭제 요청인지 확인
        String uri = request.getRequestURI();
          if (uri.contains("delete")) { // URI에 'delete'가 포함되어 있다면

              // 관리자 계정이라면 -> 삭제 허용
              if(isAdmin(session)) return true;

              // 삭제 요청이 들어온 글 번호를 확인 -> 쿼리 파라미터로 글번호가 넘어오고 있음
              String bno = request.getParameter("bno");

              // 글 번호를 통해 해당 글을 누가 작성했는지 알아내기
              Board foundBoard = boardMapper.findOne(Integer.parseInt(bno));
              String writer = foundBoard.getWriter();

              // 만약 내가 쓴 글이 아니라면? --> 접근 권한이 없다는 피드백을 주어야 한다.
              if(!isMine(session, writer)) {
                  response.setContentType("text/html; charset=UTF-8");
                  PrintWriter w = response.getWriter();
                  String htmlCode = "<script>\n" +
                          "    alert('본인이 작성한 게시글만 삭제가 가능합니다.');\n" +
                          "    location.href='/board/list';\n" +
                          "</script>";
                  w.write(htmlCode);
                  w.flush();
              }
          }

        return true;
    }
}
