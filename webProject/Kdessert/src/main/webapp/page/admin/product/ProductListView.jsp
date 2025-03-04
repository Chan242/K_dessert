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



	<div id="container">
		<button style="float: left;" onclick="location.href='/Kdessert/admin/product/add'">신규등록</button>
	
		<form action="" method="get" style="float:right;">
			<input type="text" name="" placeholder="검색어 입력창" value="">
			<button>
				검색
			</button>
		</form>
		
		
		<hr style="clear: both;">
	
		<table>
			<tr>
				<td>상품번호</td>
				<td>상품명</td>
				<td>상품가격</td>
				<td>재고</td>
				<td>공개여부</td>
				<td>삭제</td>
			
			</tr>
			<c:forEach var="productDto" items="${productList}">
			<tr>
				<td>${productDto.getpIndexInt()}</td>
				<td><a href="./update?no=${productDto.getpIndexInt()}">${productDto.getpNameStr()}</a></td>
				<td>${productDto.getpPriceInt()}</td>
				<td>${productDto.getpStockInt()}</td>
				<td>
					<c:choose>
						<c:when test="${productDto.getpOpenInt() == 0}">공개</c:when>
						<c:when test="${productDto.getpOpenInt() == 1}">비공개</c:when>
						<c:otherwise>에러: ${productDto.getpOpenInt()}</c:otherwise>
					</c:choose>
				</td>
				<td><button onclick="location.href='./delete?no=${productDto.getpIndexInt()}'">삭제</button></td>
			</tr>
			</c:forEach>
		
		
		</table>
	</div>

	
</body>
</html>