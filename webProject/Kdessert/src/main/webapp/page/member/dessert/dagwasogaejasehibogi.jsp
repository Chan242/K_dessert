<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>다과 소개 상세보기</title>

<style type="text/css">

/* 기본 설정 */
body {
    width: 100%;
    margin: 0;
    overflow-y: scroll;
    font-family: Arial, sans-serif;
}

/* 헤더 */
.header {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
    padding: 10px;
    border-bottom: 1px solid #ddd;
}

/* 메인 컨텐츠 영역 */
.content {
    display: flex;
    flex-direction: column;
    align-items: center; /* 내부 요소 가운데 정렬 */
    text-align: center;
    padding: 50px;
}

/* 제목 */
h2 {
    margin: 0 0 20px 0; /* 제목 아래 간격 추가 */
    font-size: 28px;
}

/* 이미지 스타일 */
.content img {
    width: 300px; /* 원하는 크기로 조절 */
    height: auto;
    border-radius: 10px; /* 모서리 둥글게 */
    margin-bottom: 20px;
}

/* 설명 */
.description {
    max-width: 600px;
    margin: 20px auto;
    text-align: justify;
    line-height: 1.6;
}

/* 버튼 */
.button {
    display: block;
    width: 150px;
    margin: 20px auto;
    padding: 10px 20px;
    background-color: #ccc;
    text-decoration: none;
    color: black;
    border-radius: 5px;
    text-align: center;
    font-weight: bold;
}

.button:hover {
    background-color: #bbb;
}

/* 푸터 */
.footer {
    text-align: center;
    padding: 20px;
    border-top: 1px solid #ddd;
    margin-top: 50px;
}
</style>
</head>

<body>

	<!-- 공통 헤더 -->
	<%-- 
 		<jsp:include page="/page/member/commPage/Mem_Header.jsp"/>
		<jsp:include page="/page/member/commPage/Category_Main.jsp"/> 
	    <jsp:include page="/page/admin/commPage/Move_MrgPage.jsp"/> 
		<jsp:include page="/page/member/commPage/Mem_Footer.jsp"/>
	--%>

	<!-- 메인 컨텐츠 -->
	<div class="content">
		<h2>정과</h2>
		<img src="<%= request.getContextPath() %>/image/img3.jpg" alt="정과">
		<p class="description">
			정과는 한국의 전통 간식으로, 과일이나 뿌리채소를 설탕 또는 조청에 조려서 만든 음식입니다.
			고유의 단맛과 쫀득한 식감이 특징이며, 오랜 보관이 가능하여 예부터 귀한 간식으로 여겨졌습니다.
			다양한 종류의 정과가 있으며, 현대에는 건강식으로도 인기가 많습니다.
		</p>
		<a href="/Kdessert/dessert/list"class="button">목록으로</a>
			
		
	</div>

	<!-- 푸터 -->
	<div class="footer">
		<p>© 2025 전통 과자 소개. All Rights Reserved.</p>
	</div>

</body>
</html>
