<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>다과 소개</title>

<style>

	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}

/* 헤더(로고 포함) */
.header {
	background-color: white;
	padding: 20px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}
/* 네비게이션(메뉴 버튼들) */
.nav {
	display: flex;
	justify-content: center;
	gap: 20px;
	padding: 10px;
}
/* 네비게이션 버튼 스타일 */
.nav a {
	text-decoration: none;
	color: black;
	font-weight: bold;
}
/* 콘텐츠 영역(검색바 + 상품 목록) */
.content {
	width: 70%;
	margin: 20px auto;
	background-color: white;
	padding: 20px;
	box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1);
}
/* 검색바 */
.search-bar {
	text-align: right;
	margin-bottom: 10px;
}
/* 검색 입력창 */
.search-bar input {
	padding: 5px;
	width: 200px;
}
/* 검색 버튼 */
.search-bar button {
	padding: 6px 10px;
	background-color: brown;
	color: white;
	border: none;
	cursor: pointer;
}
/* 상품 목록 (각 제품) */
.product {
	display: flex;
	border-bottom: 1px solid #ddd;
	padding: 15px 0;
	align-items: center;
}
/* 상품 이미지 */
.product img {
	width: 100px;
	height: 100px;
	background-color: #ddd;
	margin-right: 20px;
}
/* 상품 정보 (제목, 설명, 해시태그) */
.product-info {
	flex-grow: 1;
}
/* 자세히 보기 버튼 */
.product-button {
	padding: 5px 10px;
	border: 1px solid #aaa;
	background-color: white;
	cursor: pointer;
}
/* 페이지네이션 (이전/다음 페이지) */
.pagination {
	text-align: center;
	margin-top: 20px;
}
/* 페이지 번호 스타일 */
.pagination a {
	text-decoration: none;
	margin: 0 5px;
	color: black;
	font-weight: bold;
}

.footer {
	background-color: brown;
	height: 50px;
	margin-top: 20px;
}

#select_dessert {
	border-bottom: 2px solid #64473E;
}
</style>

</head>

<body>
	<jsp:include page="/page/member/commPage/Mem_Header.jsp" />
	<jsp:include page="/page/member/commPage/Category_Main.jsp" />

	<div class="content">
		<div class="search-bar">
			<input type="text" placeholder="검색어 입력">
			<button>검색</button>
		</div>

		<div class="product">
			<img src="#" alt="상품 이미지">
			<div class="product-info">
				<div class="product-title">정과</div>
				<p>정과는 식물의 뿌리나 열매를 꿀이나 엿을 사용해 쫄깃하고 윤이 나게 조린 과자이다</p>
				<p>#다과 #전통과자</p>
				<button class="product-button" onclick="location.href='./dagwasogaejasehibogi'">자세히 보기</button>
			</div>
		</div>

		<div class="product">
			<img src="#" alt="상품 이미지">
			<div class="product-info">
				<div class="product-title">다과명</div>
				<p>소개</p>
				<p>#다과 #다식</p>
				<button class="product-button">자세히 보기</button>
			</div>
		</div>

		<div class="product">
			<img src="#" alt="상품 이미지">
			<div class="product-info">
				<div class="product-title">다과명</div>
				<p>소개</p>
				<p>#다과 #다식</p>
				<button class="product-button">자세히 보기</button>
			</div>
		</div>

		<div class="pagination">
			<a href="#">〈</a> <a href="#">1</a> <a href="#">2</a> <a href="#">3</a>
			<a href="#">4</a> <a href="#">5</a> <a href="#">〉</a>
		</div>
	</div>

	<div class="footer"></div>
</body>
</html>
