<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<style type="text/css">
#container {
	width: 1200px;
	margin: auto;
}

table, tr, th, td {
	border-bottom: 1px solid #BEBEBE;
	border-collapse: collapse;
}

table {
	border-top: 2px solid black;
	margin: auto;
	width: 900px;
}

th {
	background-color: #F5F5F5;
	text-align: center;
}

th, td {
	padding: 15px;
}

#div_buttons {
	width: 410px;
	margin: auto;
	margin-top: 50px;
}

#div_buttons input {
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

#div_category {
	float: left;
	clear: both;
}

#div_content {
	width: 950px;
	float: right;
}

#table_form {
	width: 600px;
	height: 500px;
	margin: auto;
}

#table_form th {
	width: 120px;
}

#select_basket {
	background-color: white;
	color: #64473E;
	font-weight: bold;
}

input {
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 4px;
	outline: none;
	transition: border-color 0.3s;
	width: 60px;
}
</style>

<script type="text/javascript">
	function cfmClearFnc() {
		return confirm("정말로 장바구니를 비우시겠습니까?");
	}

	window.onload = function() {
		document.getElementById("clearBasket").onclick = cfmClearFnc;
	};
</script>

</head>

<body>


	<jsp:include page="../commPage/Mem_Header.jsp" />
	<jsp:include page="../commPage/Category_Main.jsp" />

	<div id="container">
		<div id="div_category">
			<jsp:include page="../commPage/Category_Mypage.jsp" />
		</div>
		
		<div style="width: 950px; float: right;">
		<c:if test="${basketList.size() == 0}">
			<br><h2 style="text-align: center;">장바구니가 비었습니다.</h2>
		</c:if>
		
		
		
		<c:if test="${basketList.size()>0}">
			<h2 style="text-align:center; margin-top: 40px; margin-bottom: 40px;">장바구니</h2>
			<table>
				
				<tr>
					<td>제품명</td>
					<td>수량</td>
					<td>가격</td>
					<td>총계</td>
					<td></td>
				</tr>
				<c:forEach var="basketDto" items="${basketList}">
					<tr>
						<td><a
							href="../../../product/select?no=${basketDto.getProIndexInt()}">${basketDto.getProNameStr()}</a></td>
						<td>
							<form action="./basket/update?no=${basketDto.getProIndexInt()}" method="post">
								<input type="number" name="basStock" value="${basketDto.getBasStockInt()}" min="1">
								<input type="submit" value="변경" width="40px;">
							</form>
						</td>
						<td><fmt:formatNumber value="${basketDto.getProPriceInt()}" pattern="#,##0" /></td>
						<td><fmt:formatNumber value="${basketDto.getBasStockInt() * basketDto.getProPriceInt()}" pattern="#,##0" /></td>
						<td><a href="./basket/delete?no=${basketDto.getProIndexInt()}">삭제</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" style="text-align: right;">총계: 
	                   <c:set var="total" value="0" />
	                    <c:forEach var="basketDto" items="${basketList}">
	                        <c:set var="itemTotal" value="${basketDto.getBasStockInt() * basketDto.getProPriceInt()}" />
	                        <c:set var="total" value="${total + itemTotal}" />
	                    </c:forEach>
	                   	 <fmt:formatNumber value="${total}" pattern="#,##0" /> 원
					</td>
				</tr>
				<tr>
					<td><a href="./order" id="order">주문하기</a></td>
					<td colspan="3" style="text-align: right;"><a href="./basket/clear" id="clearBasket">장바구니 비우기</a></td>
				</tr>
			</table>
		</c:if>
		<div style="margin: auto; clear: both;">
			
		</div>

	</div>
	</div>
</body>

<script type="text/javascript">
	
</script>

</html>