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
                if ($check.checked) {
                    arr.push($check.value);
                }
            });

            /*
            fetch(URL, {요청에 대한 여러 정보가 담긴 객체})
            여러 정보로는 전송 방식, header에 추가할 내용, body에는 전송할 JSON 데이터를 첨부.
            */

            const fetchResult =
                fetch('/rest/object2', {
                    method: 'POST',
                    headers: {
                        'content-type': 'application/json'
                    },
                    body: JSON.stringify({
                        'name': name,
                        'age': age,
                        'hobby': arr
                    })
                })
                .then(res => res.json())
                .then(value => console.log(value));

            // fetch를 통해 비동기 방식의 요청을 보내면 응답 결과로 Promise 타입의 객체가 리턴됨.
            // Promise안에 PromiseResult라는 이름으로 전체적인 응답 정보가 담겨진 Response라는
            // 객체가 담겨져 있음.
            console.log(fetchResult);

            // Promise 객체는 then() 메서드를 제공합니다.
            // then()은 비동기 방식의 요청이 성공했을 때 해당 Promise 객체 내부의 여러가지 결과를
            // 받아서 처리할 수 있는 콜백 방식의 함수를 매개값으로 전달받습니다.
            // Promise 객체에 담긴 여러 정보 중 PromiseResult에 담긴 Response 객체를 콜백 함수의 매개값으로 받아
            // json() 메서드를 통해 JSON 객체를 JavaScript로 변환해서 리턴받을 수 있습니다.
            const response = fetchResult.then(function(res) {
                return res.json();
            });

            response.then(function(value) {
                console.log(value);
            });
        }
    </script>



</body>

</html>