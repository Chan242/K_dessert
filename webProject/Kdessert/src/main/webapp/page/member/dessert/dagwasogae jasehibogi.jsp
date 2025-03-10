<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device
-width,initial-scale=1.0">
<title>다과 소개 상세보기</title>

<style type="text/css">
body {
	margin: 0;
	font-family: Arial, sans-serif;
	text-align: center;
}

.header {
	padding: flex;
	justify-content: center;
	gap: 20px;
	padding: 10px;
	border-bottom: 1px solid #ddd;
}

.conter {
	padding: 50px;
}

.image-placeholder {
	width: 300px;
	height: 200px;
	border: 1px solid #ccc;
	display: flex;
	align-items: center;
	margin: 20px auto;
}

.description {
	max-width: 600px;
	margin: auto;
	text-align: justify;
}

.button {
	display: inline-block;
	margin-top: 20px;
	padding: 10px 20px;
	background-color: #ccc;
	text-decoration: none;
	color: black;
	border-radius: 5px;
}
</style>
</head>

<body>
	<div class="header">로고</div>
	<div class="nav">
		<a href="#">다과 소개</a> <a href="#">구매</a> <a href="#">행사</a> <a
			href="#">고객게시판</a>
	</div>
	<div class="content">
		<h2>정과</h2>
		<div class="image-placeholder">이미지</div>
		<p class="description">정과는 한국의 전통 간식으로, 
			과일이나 뿌리채소를 설탕 또는 조청에 조려서
			만든 음식입니다. 
			고유의 단맛과 쫀득한 식감이 특징이며, 
			오랜 보관이 가능하여 예부터 귀한 간식으로 여겨졌습니다. 다양한
			종류의 정과가 있으며, 현대에는 
			건강식으로도 인기가 많습니다.</p>
		<a href="#" class="button">목록으로</a>
	</div>
	<div class="footer"></div>
</body>
</html>