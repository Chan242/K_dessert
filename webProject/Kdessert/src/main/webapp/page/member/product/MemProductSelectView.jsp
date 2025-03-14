<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	
	<div id="container">
	
		<h1 style="text-align: center;">${userProductDto.getProNameStr()}</h1>
		<hr>
		<div style="width: 300px; height: 300px; border: 1px solid black; float: left; margin: auto; text-align: center;">
			이미지 자리
			<img src="/Kdessert/image/${userProductDto.getProImageStr()}">
		</div>
		<div style="width: 200px; height: 200px; float: left; margin-left: 100px; text-align: left; padding-left: 30px;">
			가격: <fmt:formatNumber value="${userProductDto.getProPriceInt()}" pattern="#,##0" /><br>
			재고: <fmt:formatNumber value="${userProductDto.getProStockInt()}" pattern="#,##0" /><br>
			<form action="/Kdessert/basket" method="post">
				<input type="number" id="basStock" name="basStock" value="1" min="1" max="${userProductDto.getProStockInt()}">
				<input type="hidden" id="proIndex" name="proIndex" value="${userProductDto.getProIndexInt()}">
				<input type="hidden" id="maxStock" name="maxStock" value="${userProductDto.getProStockInt()}">
				<input type="submit" value="담기">
			</form>
		</div>
				<table style="margin: auto;">
				<tr>
					<td>소개</td>
					<td style="width: 30">${userProductDto.getProIntroStr()}</td>
				</tr>
				
			</table>
			<div style="clear: both;"></div>
	</div>

</body>
</html>