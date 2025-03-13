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

</script>

</head>

<body>


	<jsp:include page="../../commPage/Mem_Header.jsp" />
	<jsp:include page="../../commPage/Category_Main.jsp" />

	<div id="container">
		<div id="div_category">
			<jsp:include page="../../commPage/Category_Mypage.jsp" />
		</div>

		<table>
			<h2 style="text-align: center;">주문정보</h2>
			<tr>
				<td>제품명</td>
				<td>수량</td>
				<td>가격</td>
				<td>총계</td>
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


		</table>
		<hr>
		<h2 style="text-align: center;">배송 정보</h2>
		<table>
			<tr>
				<td>받을 사람</td>
				<td>${orderInfo.getMemNameStr() }</td>
			</tr>
			<tr>
				<td>받을 주소</td>
				<td>${orderInfo.getMemAddressStr() }</td>
			</tr>
			<tr>
				<td>상세 주소</td>
				<td>${orderInfo.getMemAddressSecStr() }</td>
			</tr>
			<tr>
				<td>연락처</td>
				<td>${orderInfo.getMemTelStr() }</td>
			</tr>
		</table>
		<hr>
		<h2 style="text-align: center;">결제 정보</h2>
		<table>
			<tr>
				<td>보유 포인트</td>
				<td style="text-align: right"><fmt:formatNumber value="${pointInfo.getMemPointInt()}" pattern="#,##0" />
					<c:if test="${total > pointInfo.getMemPointInt()}">
						<br>포인트가 부족합니다.
					</c:if>
					<c:if test="${total <= pointInfo.getMemPointInt()}">
						<br><form action="./order/sucess" method="post">
						<input type="submit" value="구매">
						</form>
					</c:if>

					
				</td>
			</tr>
		</table>

		
		<div style="margin: auto; clear: both;">

		</div>

	</div>
</body>

<script type="text/javascript">
	
</script>

</html>