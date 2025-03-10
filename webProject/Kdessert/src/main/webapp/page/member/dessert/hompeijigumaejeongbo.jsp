<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>마이페이지 - 구매정보</title>

<style type="text/css">
/* 전체 페이지 스타일 */
/* 	body {
	margin: 0;
	font-family: Arial, sans-serif;
	text-align: center;
	} */
/* 헤더(로고) 스타일 */
	.header {
	padding: 20px;
	font-size: 24px;
	font-weight: bold;
	text-align: left;
	}
/* 네비게이션 바 스타일 */
	.nav {
	display: flex;
	justify-content: center;
	gap: 20px;
	padding: 10px;
	border-bottom: 1px solid #ddd;
	}
/* 전체 컨텐츠를 감싸는 컨테이너 */
	.container {
	display: flex;
	max-width: 1000px;
	margin: 20px auto;
	}
/* 사이드바(마이페이지 메뉴) 스타일 */
	.sidebar {
	width: 200px;
	background-color: #f08b42;
	padding: 20px;
	color: white;
	text-align: left;
	}
	.sidebar a {
	display: block;
	color: white;
	text-decoration: none;
	padding: 10px 0;
	}
/* 본문(주문 정보) 스타일 */
	.content {
	flex-grow: 1;
	padding: 20px;
	background-color: #fff;
	}
/* 테이블 스타일 */	
	table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	}
	table, th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: center;
	}
	th {
	background-color: #eee;
	}
/* 페이지네이션 스타일 */
	.pagination {
	margin-top: 20px;
	}
/* 푸터 스타일 */	
	.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	margin-top: 50px;
	background: #5a3e36;
	height: 100px;
	}
</style>
</head>

<body>
	<jsp:include page="/page/member/commPage/Mem_Header.jsp"/>
	<jsp:include page="/page/member/commPage/Category_Main.jsp"/>
	<div class="wrapper"></div>
		<!-- 헤더 -->
		<!-- 네비게이션 바 -->


		<!-- 메인 컨테이너 -->
	<div class="container">
	

</div>
	<!-- 본문 컨텐츠 -->
	<div class="content">
	<h2>주문정보</h2>
	<!-- 주문정보 테이블 -->
	<table>
	<tr>
		<th>주문번호</th>
		<th>주문자</th>
		<th>주문일자</th>
		<th>배송상태</th>
		<th>금액</th>
	</tr>
	<tr>
		<td>100001</td>
		<td>홍길동</td>
		<td>2023-05-01</td>
		<td>배송완료</td>
		<td>₩25,000</td>
	</tr>
	<tr>
	<td>100002</td>
	<td>김영희</td>
	<td>2023-05-02</td>
	<td>배송중</td>
	<td>₩30,000</td>
	</tr>
	<tr>
	<td>100003</td>
	<td>박철수</td>
	<td>2023-05-03</td>
	<td>배송 준비중</td>
	<td>₩15,000</td>
	</tr>
	</table>
	
	<!-- 페이지네이션 -->
	<div class="pagination">
		&lt; 1 2 3 4 5 &gt;	
		</div>
	</div>
	</div>
	
	<!-- 푸터 -->
	<div class="footer"></div>
</body>
</html>