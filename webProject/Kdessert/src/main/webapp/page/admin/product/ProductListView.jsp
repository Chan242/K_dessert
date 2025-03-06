<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#container {
	margin: auto;
	width: 1200px;
	height: 1200px;
	background-color: lightgray;
}

#topBar{
	margin: auto;
}
</style>
</head>
<body>

	<jsp:include page="../commPage/Adm_Header.jsp"></jsp:include>

	<div style="float: left;">
		<jsp:include page="../commPage/Category_Mgr.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="topBar">
		
			<button style="float: left; margin: auto;"
				onclick="location.href='/Kdessert/admin/product/add'">신규등록</button>

			<form action="query" method="get" style="float: right;">
				<input type="search" name="search" placeholder="검색어 입력창" value="">
				<input type="submit" value="검색">
			</form>
		
		<br><!-- 칸을 구분하기 위해서 어거지로 박았음. 쓰기 싫음. flex같은 것으로 수정하고 싶음. -->
		</div>
	
		<div>
			<table>
				<tr>
					<td>제품번호</td>
					<td>제품명</td>
					<td>제품가격</td>
					<td>재고</td>
					<td>공개여부</td>
					<td>수정</td>
					<td>삭제</td>

				</tr>
				<c:forEach var="productDto" items="${productList}">
					<tr>
						<td>${productDto.getProIndexInt()}</td>
						<td><a href="./select?no=${productDto.getProIndexInt()}">${productDto.getProNameStr()}</a></td>
						<td>${productDto.getProPriceInt()}</td>
						<td>${productDto.getProStockInt()}</td>
						<td><c:choose>
								<c:when test="${productDto.getProOpenInt() == 0}">공개</c:when>
								<c:when test="${productDto.getProOpenInt() == 1}">비공개</c:when>
								<c:otherwise>에러: ${productDto.getProOpenInt()}</c:otherwise>
							</c:choose></td>
						<td><a href="./update?no=${productDto.getProIndexInt()}">수정</a></td>
						<td><a href="./delete?no=${productDto.getProIndexInt()}">삭제</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>