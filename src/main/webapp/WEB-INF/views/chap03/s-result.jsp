<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
</head>
<body>
    <script>
        // javascript단에서 model 데이터를 활용하는 법
        const msg = '${result}'; // 문자열로 el을 사용해서 변수에 할당 가능
        if(msg !== null) {
            alert(msg);
        }
    </script>
</body>
</html>