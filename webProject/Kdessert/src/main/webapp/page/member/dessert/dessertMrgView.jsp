<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>다과 관리</title>

<style type="text/css">

</style>


</head>

<body>
<header>
	<%-- <jsp:include page="/page/member/commPage/Mem_Header.jsp"/>
	<jsp:include page="/page/member/commPage/Category_Main.jsp"/> --%>
	<jsp:include page="/page/admin/commPage/Move_MrgPage.jsp"/>
	
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
            <input type="text" placeholder="검색어 입력">
            <button>검색</button>
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
                <tr>
                    <td>1</td>
                    <td>밤양갱</td>
                    <td>달콤한 양갱</td>
                    <td>10</td>
                    <td><button>삭제</button></td>
                </tr>
            </tbody>
        </table>

        <!-- 페이지네이션 -->
        <div class="pagination">
            <a href="#">&lt;</a>
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">&gt;</a>
        </div>
    </main>

</div>

</body>


</body>
</html>