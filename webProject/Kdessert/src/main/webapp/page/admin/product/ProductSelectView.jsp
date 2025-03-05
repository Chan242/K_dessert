<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품정보</title>
<style type="text/css">
	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
	}

	th, td {
		padding: 15px;
	}
	
	.btn_style {

		width: 200px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: white;
		color: #7B7B7B;
		font-size: 16px;
		
    }

</style>
</head>
<body>

	<jsp:include page="../commPage/Adm_Header.jsp"></jsp:include>

	<div style="float: left;">
		<jsp:include page="../commPage/Category_Mgr.jsp"></jsp:include>
	</div>

	
	<div>
		<h1 style="text-align: center;">제품 정보</h1>
		<table style="margin: auto;">
			<tr>
				<td>인덱스</td>
				<td>${productDto.getproIndexInt()}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${productDto.getproNameStr()}</td>
			</tr>
			<tr>
				<td>제품가격</td>
				<td>${productDto.getproPriceInt()}</td>
			</tr>
			<tr>
				<td>재고</td>
				<td>${productDto.getproStockInt()}</td>
			</tr>
			<tr>
				<td>공개여부</td>
				<td>
					<c:choose>
						<c:when test="${productDto.getproOpenInt() eq 0}">공개</c:when>	
						<c:when test="${productDto.getproOpenInt() eq 1}">비공개</c:when>
						<c:otherwise> 에러	 </c:otherwise>		
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>소개</td>
				<td>${productDto.getproIntroStr()}</td>
			</tr>
			
		</table>
		<button class="btn_style" onclick="location.href='./update?no=${productDto.getproIndexInt()}'">수정하기</button>
		<button class="btn_style" onclick="history.back()">돌아가기</button>
		
		
	</div>
	<hr style="clear: both">

</body>
</html>