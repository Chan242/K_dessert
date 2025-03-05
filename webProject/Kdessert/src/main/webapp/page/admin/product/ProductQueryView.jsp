<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		#container{

		margin: auto;
		width: 1200px;
		height: 1200px;
		background-color: lightgray;
		
	}
	</style>
</head>
<body>

검색결과

	<div id="container">
		<button style="float: left;" onclick="location.href='/Kdessert/admin/product/add'">신규등록</button>
	
		<form action="query" method="get" style="float:right;">
			<input type="search" name="search" placeholder="검색어 입력창">
			<input type="submit" value="검색">
		</form>
		
		
		<hr style="clear: both;">
	
		<table>
			<tr>
				<td>상품번호</td>
				<td>상품명</td>
				<td>상품가격</td>
				<td>재고</td>
				<td>공개여부</td>
				<td>수정</td>
				<td>삭제</td>
			
			</tr>
			<c:forEach var="productDto" items="${productList}">
			<tr>
				<td>${productDto.getproIndexInt()}</td>
				<td>${productDto.getproNameStr()}</td>
				<td>${productDto.getproPriceInt()}</td>
				<td>${productDto.getproStockInt()}</td>
				<td>
					<c:choose>
						<c:when test="${productDto.getproOpenInt() == 0}">공개</c:when>
						<c:when test="${productDto.getproOpenInt() == 1}">비공개</c:when>
						<c:otherwise>에러: ${productDto.getproOpenInt()}</c:otherwise>
					</c:choose>
				</td>
				<td><a href="./update?no=${productDto.getproIndexInt()}">수정</a></td>
				<td><a href="./delete?no=${productDto.getproIndexInt()}">삭제</a></td>
			</tr>
			</c:forEach>
		
		
		</table>
	</div>

	
</body>
</html>