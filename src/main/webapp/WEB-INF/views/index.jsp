<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
</head>
<body>

    <%
        String userName = "방문자";
        
        // client가 쿠키 검사 
        Cookie[] cookies = request.getCookies();
        for(Cookie c: cookies) {
            if (c.getName().equals("login")) {
                userName = c.getValue();
            }
        }
    %>

    <h1> <%= userName %>님, 안녕하세요!</h1>
    
</body>
</html>