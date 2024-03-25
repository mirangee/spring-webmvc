<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert Your Title</title>
    <style>
        label {
            display: block;
            margin-bottom: 20px;
        }
        .wrap {
            width: 800px;
            margin: 100px auto;
            border: 1px dashed #000;
        }
        .wrap h1 {
            margin: 0 auto;
            padding-bottom: 20px;
            width: fit-content;
            border-bottom: 1px solid #000;
        }
        .wrap .menu {
            font-size: 24px;
            width: 80%;
            margin: 20px auto;
        }
        .wrap .menu select {
            width: 200px;
            height: 50px;
            font-size: 28px;
            margin-top: 10px;
        }
        .clearfix::after {
            content: '';
            display: block;
            clear: both;
        }
    </style>
</head>
<body>
    <div class="wrap">
        <h1>커피 주문서</h1>
    
        <div class="menu">
            <form action="/coffee/result" method="post">
                <label>
                    # 주문 목록 <br>
                    <select id="menu-sel" name="menu">
                        <option value="americano">아메리카노</option>
                        <option value="cafeLatte">카페라떼</option>
                        <option value="macchiato">카라멜 마끼아또</option>
                    </select>
                </label>
                <label class="price"># 가격: <span class="price-value">3000</span>원</label>
    
                <!-- 화면에 렌더링은 안되지만 서버로 보낼 수 있음 -->
                <input id="price-tag" type="hidden" name="price" value="3000">
    
    
                <label>
                    <button type="submit">주문하기</button>
                </label>
            </form>
    
    
    
        </div>
    </div>

    <script>
        const coffeePrice = {
            americano : 3000,
            cafeLatte : 4500,
            macchiato: 5000
        };

        const $menu = document.getElementById('menu-sel');

        // change : input이나 select 태그의 값이 변했을 때
        $menu.onchange = e => {
            // 커피를 선택하면 가격이 바뀌어야 함
            // console.log(e.target.value); -> option에 value가 잘 오고 있다는 걸 확인

            const price = coffeePrice[e.target.value]; // 객체에서 선택된 커피의 가격을 얻어옴
            
            // span 태그에 사용자가 선택한 커피 가격을 화면으로 노출
            document.querySelector(".price-value").textContent = price;

            // input hidden 태그에 가격을 넣어놓기 -> form을 이용해 값을 서버로 전달
            document.getElementById("price-tag").value = price;
        }
    </script>
</body>
</html>