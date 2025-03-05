<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 제품 목록 페이지</title>

<style type="text/css">
	table{
	margin: auto;
	}

</style>

<script type="text/javascript">

window.onload = function() {
    var inputElement = document.getElementById("numberInput");
    var maxValue = parseInt(inputElement.max);

    inputElement.addEventListener("input", function() {
        var value = parseInt(this.value);
        if (value > maxValue) {
            this.value = maxValue;
        }
    });
};


</script>

</head>
<body>

	<jsp:include page="../commPage/Mem_Header.jsp"/>
	<jsp:include page="../commPage/Category_Main.jsp"/>

	<div>
		<table>
			<thead>
				<tr style="text-align: center;">
					<td>제품번호</td>
					<td>제품명</td>
					<td>제품가격</td>
					<td>재고</td>
					<td>주문</td>
				</tr>
			</thead>
			
			<c:forEach var="userProductDto" items="${userProductList}">
				<tr>
					<td>${userProductDto.getproIndexInt()}</td>
					<td><a href="./select?no=${userProductDto.getproIndexInt()}">${userProductDto.getproNameStr()}</a></td>
					<td>${userProductDto.getproPriceInt()}</td>
					<td>${userProductDto.getproStockInt()}</td>
					<td><form action="">
				<input type="number" id="numberInput" value="" min="1" max=" ${userProductDto.getproStockInt()}">
				<input type="submit" value="담기">
			</form></td>

				</tr>
			</c:forEach>
		</table>
	</div>

<jsp:include page="../commPage/Mem_Footer.jsp"/>

</body>
</html>