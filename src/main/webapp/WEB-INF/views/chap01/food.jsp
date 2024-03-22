<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
</head>
<body>
    <h1>food.jsp입니다.</h1>

    <form action="/food" method="post">
        # 음식명: <input type="text" name="foodName"> <br>
        # 음식 카테고리:
        <select name="category">
            <option value="KOREAN">한식</option>
            <option value="WESTERN">양식</option>
            <option value="CHINESE">중식</option>
        </select>
        <br>
        # 가격: <input type="text" name="price" /> <br>
        <button type="submit">확인</button>
    </form>
</body>
</html>