<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 상태 변경 결과</title>
    <style>
        .message {
            padding: 20px;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
            margin-top: 20px;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>

    <div class="message">
        <!-- request에서 메시지 가져와서 출력 -->
        <c:if test="${not empty message}">
            <div class="${message eq '요청이 성공하였습니다.' ? 'success' : 'error'}">
                ${message}
            </div>
        </c:if>
    </div>

    <div style="text-align: center;">
        <button onclick="window.close();">닫기</button>
    </div>

</body>
</html>
