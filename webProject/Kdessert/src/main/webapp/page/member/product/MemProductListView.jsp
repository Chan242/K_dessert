<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 제품 목록 페이지</title>

<style type="text/css">
table {
	margin: auto;
	border: 1px solid black;
}


#container {
	margin: auto;
	padding: 50px;
	width: 1200px;
}



</style>


</head>
<body>

	<jsp:include page="../commPage/Mem_Header.jsp" />
	<jsp:include page="../commPage/Category_Main.jsp" />

	<div id="container">



		<c:forEach var="userProductDto" items="${userProductList}">
			
			<table style="text-align:center; width:300px; float: left">
				<thead>
					<tr style="width:300px; height:400px; text-align: center;">
						<td>이미지</td>
					</tr>
				</thead>
				<tr>
					<td><a href="./select?no=${userProductDto.getProIndexInt()}">${userProductDto.getProNameStr()}</a></td>
				</tr>
				<tr>
					<td>가격: ${userProductDto.getProPriceInt()}</td>
				</tr>
				<tr>
					<td>재고: ${userProductDto.getProStockInt()}</td>
				</tr>
<%-- 				<tr> 기술이 모자라서 일단 주석처리.
					<td><form action="">
							<input type="number" id="numberInput" value="" min="1"
								max=" ${userProductDto.getProStockInt()}"> <input
								type="submit" value="담기">
						</form></td>

				</tr> --%>
			</table>
		</c:forEach>
		<hr style="clear: both;">
	</div>

	<jsp:include page="../commPage/Mem_Footer.jsp" />

</body>
</html>