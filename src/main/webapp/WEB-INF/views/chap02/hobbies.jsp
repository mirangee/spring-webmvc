<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
</head>
<body>
    <h1> ${userName}님 취미 목록</h1>

    <ol>
        <c:forEach var = "h" items = "${hobbies}">
            <li> ${h} </li>
        </c:forEach>
    </ol>

</body>
</html>