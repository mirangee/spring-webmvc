<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
</head>
<body>

    이름: <input type="text" name="name"> <br>

    나이: <input type="text" name="age"> <br>

    취미:
    <input type="checkbox" name="hobby" value="soccer"> 축구
    <input type="checkbox" name="hobby" value="music"> 음악감상
    <input type="checkbox" name="hobby" value="game"> 게임 <br>

    <button type="button" id="send">요청 보내기!</button>

    <script>

        const $sendBtn = document.getElementById('send');

        $sendBtn.onclick = e => {

            const name = document.querySelector('input[name=name]').value;
            const age = document.querySelector('input[name=age]').value;
            const $hobby = document.querySelectorAll('input[name=hobby]');

            const arr = []; // 체크가 된 요소값만을 넣기위한 배열.

            // 여러개의 체크박스 중 체크가 된 요소의 값만 arr에 추가
            [...$hobby].forEach($check => {
                if($check.checked) {
                    arr.push($check.value);
                }
            });

            console.log(name);
            console.log(age);
            console.log(arr);

            // # http 요청을 서버로 보내는 방법 (비동기 방식)
            // 1. XMLHttpRequest 객체를 생성.
            const xhr = new XMLHttpRequest();

            /*
                2. http 요청 설정 (요청 방식, 요청 URL)
                - 요청 방식
                a. GET - 조회
                b. POST - 등록
                c. PUT - 수정
                d. DELETE - 삭제
            */
           xhr.open('POST', '/rest/object');

           // 3. 서버로 전송할 데이터를 제작합니다.
           // 제작하는 데이터의 형식은 JSON 형태여야 합니다.
           // 자바 서버는 자바스크립트 형태의 데이터를 알 수가 없음 -> 공통된 표기방식으로 바꿔서 전송해야 함!
           const data = {
            'name': name,
            'age' : age,
            'hobby' : arr
           }; // 이 객체는 아직 JSON이 아님! JavaScript 객체이다.

           // JS -> JSON으로 변환: JSON.stringify(obj)
           const sendData = JSON.stringify(data);

           // 전송할 데이터의 형태가 어떠한지를 요청 헤더에 지정.
           xhr.setRequestHeader('content-type', 'application/json');

           // 4. 서버에 요청 전송
           xhr.send(sendData);

           // 5. 응답된 정보 확인
           xhr.onload = () => {
            // 응답 코드
            console.log(xhr.status);
            // 응답 데이터 확인
            console.log(xhr.response);
           }
        };
    </script>

</body>
</html>