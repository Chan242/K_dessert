<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 제품정보</title>
<style type="text/css">

	body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	
	
	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
		
	}
	
	table {
		border-top: 2px solid black;
	}
	
	th {
		background-color: #F5F5F5;
	}

	th, td {
		padding: 15px;
		text-align: center;
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
		<table style="margin: auto; ">
			<tr>
				<td>인덱스</td>
				<td>${productDto.getProIndexInt()}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${productDto.getProNameStr()}</td>
			</tr>
			<tr>
				<td>제품가격</td>
				<td><fmt:formatNumber value="${productDto.getProPriceInt()}" pattern="#,##0" /></td>
			</tr>
			<tr>
				<td>재고</td>
				<td><fmt:formatNumber value="${productDto.getProStockInt()}" pattern="#,##0" /></td>
			</tr>
			<tr>
				<td>공개여부</td>
				<td>
					<c:choose>
						<c:when test="${productDto.getProOpenInt() eq 0}">공개</c:when>	
						<c:when test="${productDto.getProOpenInt() eq 1}">비공개</c:when>
						<c:otherwise> 에러	 </c:otherwise>		
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>생성일</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${productDto.getProCreDateDate()}"/></td>
			</tr>
			<tr>
				<td>수정일</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${productDto.getProChanDateDate()}"/></td>
			</tr>				
			<tr>
				<td>소개</td>
				<td>${productDto.getProIntroStr()}</td>
			</tr>
			<tr>
				<td>태그</td>
				<td><c:forEach var="tag" items="${productDto.getProTagList()}" varStatus="status">
   					 ${tag}
    				<c:if test="${!status.last}">, </c:if> <!-- 마지막이 아닐 경우에만 쉼표 출력 -->
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td><button class="btn_style" onclick="location.href='./update?no=${productDto.getProIndexInt()}'">수정하기</button></td>
				<td><button class="btn_style" onclick="history.back()">돌아가기</button></td>
				
			</tr>
			
		</table>

		
		
	</div>
	<hr style="clear: both">

</body>
</html>