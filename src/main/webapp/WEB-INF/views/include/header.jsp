<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header -->
<header>
    <div class="inner-header">
        <h1 class="logo">
            <a href="/board/list">
                <img src="/assets/img/logo.png" alt="로고이미지">
            </a>
        </h1>

        <!-- 프로필 사진 -->
        <div class="profile-box">

                <img src="/assets/img/anonymous.jpg" alt="프사">
            
        </div>


        <h2 class="intro-text"> 
            Welcome ${sessionScope.login == null? '': login.name} 
            <!-- session 객체의 login 데이터를 꺼내달라는 의미. scope를 쓰지 않으면 자동으로 model 객체, session 객체를 뒤져서 찾아온다.-->
        </h2>
        <a href="#" class="menu-open">
            <span class="menu-txt">MENU</span>
            <span class="lnr lnr-menu"></span>
        </a>
    </div>

    <nav class="gnb">
        <a href="#" class="close">
            <span class="lnr lnr-cross"></span>
        </a>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="/board/list">Board</a></li>
            <li><a href="#">Contact</a></li>

            <c:if test="${empty login}"> <!-- el 문법임. login이 null이라면 ${login == null}-->
                <li><a href="/members/sign-up">Sign Up</a></li>
                <li><a href="/members/sign-in">Sign In</a></li>
            </c:if>

            <c:if test="${not empty login}">
                <li><a href="#">My Page</a></li>
                <li><a href="/members/sign-out">Sign Out</a></li>
            </c:if>

        </ul>
    </nav>

</header>
<!-- //header -->