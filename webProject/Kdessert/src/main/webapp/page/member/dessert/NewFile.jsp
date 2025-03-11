<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // 다과 데이터 리스트 (서버에서 받아오는 데이터라고 가정)
    List<Map<String, Object>> snacks = new ArrayList<>();

    Map<String, Object> snack1 = new HashMap<>();
    snack1.put("id", 1);
    snack1.put("name", "밤양갱");
    snack1.put("description", "달콤한 양갱");
    snack1.put("points", 10);
    snacks.add(snack1);

    Map<String, Object> snack2 = new HashMap<>();
    snack2.put("id", 2);
    snack2.put("name", "약과");
    snack2.put("description", "전통 한과");
    snack2.put("points", 15);
    snacks.add(snack2);

    Map<String, Object> snack3 = new HashMap<>();
    snack3.put("id", 3);
    snack3.put("name", "송편");
    snack3.put("description", "쫀득한 떡");
    snack3.put("points", 12);
    snacks.add(snack3);

    request.setAttribute("snacks", snacks); // JSP에서 사용할 데이터 전달
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>다과 관리</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<!-- 헤더 -->
<header>
    <h1>다과 관리</h1>
    <span class="user-info">홍길동 님이 로그인 중입니다.</span>
</header>

<!-- 전체 레이아웃 컨테이너 -->
<div class="container">

    <!-- 사이드바 -->
    <aside class="sidebar">
        <h2>로고</h2>
        <ul>
            <li><a href="#">주문 관리</a></li>
            <li><a href="#">최종 관리</a></li>
            <li><a href="#">제품 관리</a></li>
            <li><a href="#">다과 관리</a></li>
            <li><a href="#">행사 관리</a></li>
            <li><a href="#">통계</a></li>
        </ul>
    </aside>

    <!-- 메인 컨텐츠 -->
    <main class="content">
        <h2>다과 관리</h2>
        <div class="search-bar">
            <form action="index.jsp" method="GET">
                <input type="text" name="search" placeholder="검색어 입력">
                <button type="submit">검색</button>
            </form>
        </div>

        <!-- 테이블 -->
        <table>
            <thead>
                <tr>
                    <th>다과번호</th>
                    <th>다과명</th>
                    <th>설명</th>
                    <th>포인트</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="snack" items="${snacks}">
                    <tr>
                        <td>${snack.id}</td>
                        <td>${snack.name}</td>
                        <td>${snack.description}</td>
                        <td>${snack.points}</td>
                        <td>
                            <form action="delete.jsp" method="POST">
                                <input type="hidden" name="id" value="${snack.id}">
                                <button type="submit">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- 페이지네이션 -->
        <div class="pagination">
            <a href="?page=1">&lt;</a>
            <a href="?page=1">1</a>
            <a href="?page=2">2</a>
            <a href="?page=3">3</a>
            <a href="?page=4">4</a>
            <a href="?page=5">5</a>
            <a href="?page=2">&gt;</a>
        </div>
    </main>

</div>

</body>
</html>
