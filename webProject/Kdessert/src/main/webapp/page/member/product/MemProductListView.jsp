<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 제품 목록 페이지</title>

<style type="text/css">
	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}
table {
	margin: auto;
}


#container {
	margin: auto;
	padding: 50px;
	width: 1200px;
}

a{
	text-decoration: none;
}


.btn{
		font-size: 12pt;
		width: 80px;
		height: 28px;
		text-align: center;
		color: white;
		background-color: #64473E;
		border: none;
	}

#SearchBox {
		width: 208px;
		height: 29px;
	}

#select_produt {
	border-bottom: 2px solid #64473E;
}

</style>


</head>
<body>

	<jsp:include page="../commPage/Mem_Header.jsp" />
	<jsp:include page="../commPage/Category_Main.jsp" />

	<div id="container">
	
		<div>
			<form action="query" method="get" style="float: right;">
				<input type="search" id="SearchBox" name="search" placeholder="검색창" value="">
				<input type="hidden" name="no" value="1">
				<input type="submit" class="btn" value="검색">
			</form>
		</div>
		
		<hr style="clear: both;">
		
		<c:forEach var="userProductDto" items="${userProductList}">
			
			<table style="text-align:center; width:300px; float: left">
				<thead>
					<tr style="width:300px; height:400px; text-align: center;">
						<td>
							<img src="/Kdessert/image/${userProductDto.getProImageStr()}" style="width: 200px; height: 180px;">
						</td>
					</tr>
				</thead>
				<tr>
					<td><a href="./select?no=${userProductDto.getProIndexInt()}">${userProductDto.getProNameStr()}</a></td>
				</tr>
				<tr>
					<td>가격: <fmt:formatNumber value="${userProductDto.getProPriceInt()}" pattern="#,##0" /></td>
				</tr>
				<tr>
					<td>재고: <fmt:formatNumber value="${userProductDto.getProStockInt()}" pattern="#,##0" /></td>
				</tr>

			</table>
		</c:forEach>
		<hr style="clear: both;">
			<!-- 페이지네이션 네비게이션 -->
		<div style="text-align: center;">
	<!-- 이전 페이지 -->
	  		  <c:if test="${no > 1}">
	   		     <a href="./list?no=${no - 1}"><</a>
	   		 </c:if>	
	    
	    
	 <!-- 페이지 번호 -->
	   		 <c:forEach var="i" begin="${start}" end="${maxEnd}">
	   	    	 <a href="./list?no=${i}" <c:if test="${i==no}">style="font-weight: bolder;"</c:if>>${i}</a>
	   	 	</c:forEach>
    
   	 <!-- 다음 페이지 -->
	    	<c:if test="${no < totalPageInt}">
	        	<a href="./list?no=${no + 1}">></a>
	    	</c:if>
		</div>
		
	</div>

	<jsp:include page="../commPage/Mem_Footer.jsp" />

</body>
</html>