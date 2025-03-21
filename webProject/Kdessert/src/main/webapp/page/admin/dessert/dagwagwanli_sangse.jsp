<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,
		initial-scale=1.0">
<title>다과 관리 - 상세</title>
<link rel="styleheet" href="style.css">

<style type="text/css" href="style.css">

	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}

/* 전체 레이아웃 설정 */
/* body, html {
		margin: 0;
		padding: 0;
		font-family: Arial, sans-serif;
		} */
/* 상단 헤더 스타일 */
.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: #f4f4f4;
	padding: 15px 20px;
	font-size: 18px;
	font-weight: bold;
	border-bottom: 2px solid #ddd;
}

.user-info {
	font-size: 14px;
	color: #666;
}
/* 전체 컨테이너 설정 */
.container {
	display: flex;
	height: calc(100vh - 50px); /* 화면 높이에서 헤더 제외 */
}

/* 사이드바 스타일 */
.sidebar {
	width: 220px;
	background-color: #5a3e36;
	padding: 20px;
	display: flex;
	flex-direction: column;
}

.sidebar a {
	color: white;
	text-decoration: none;
	padding: 10px 0;
	display: block
}

.sidebar a: hover {
	background-color: #704b42;
}

/* 메인 컨텐츠 스타일 */
.content {
	flex-grow: 1;
	padding: 30px;
	background-color: #fff;
}

h2 {
	margin-bottom: 20px;
}

/* 상세보기 테이블 스타일 */
.details-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

.details-table th, .details-table td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

.details-table th {
	background-color: #f4f4f4;
	width: 150px;
}

/* 버튼 스타일 */
.buttons {
	text-align: center;
}

.btn {
	padding: 10px 15px;
	margin: 5px;
	border: none;
	cursor: pointer;
	font-size: 16px;
}

.primary {
	background-color: #5a3e36;
	color: white;
}

.primary:hover {
	background-color: #704b42;
}
</style>
</head>

<body>

<div id="wrap">
	<jsp:include page="/page/admin/commPage/Adm_Header.jsp" />
	
	
	<div id="container">
	
		<div id="sidebar">
			<jsp:include page="/page/admin/commPage/Category_Mgr.jsp" />
		</div>
	</div>
		<h2>상세보기</h2>
		<table class="details-table">
			<tr>
				<th>디저트 번호</th>
				<td>1</td>
			</tr>
			<tr>
				<th>디저트명</th>
				<td>몽블랑</td>
			</tr>
			<tr>
				<th>설명</th>
				<td>달콤한 마롱 크림과 부드러운 케이크 조합의 프랑스 디저트입니다.</td>
			</tr>

			<tr>
				<th>이미지</th>
				<td>montblanc.png</td>
			</tr>
			<tr>
				<th>공개여부</th>
				<td>공개<input type="radio" name="public" checked>비공개 
				<input type="radio" name="public"></td>
			</tr>
			<tr>
				<th>태그</th>
				<td>#달콤함</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>19</td>
			</tr>
		</table>

		<!-- 버튼 -->
		<div class="buttons">
			<button class="btn">목록으로</button>
			<button class="btn primary">수정하기</button>

		</div>
	</div>

</body>
</html>