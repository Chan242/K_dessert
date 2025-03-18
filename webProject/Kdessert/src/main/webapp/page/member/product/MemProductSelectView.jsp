<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}
	
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
	
		<h1 style="text-align: center;">${productDto.getProNameStr()}</h1>
		<hr>
		<div style="width: 300px; height: 300px; float: left; margin: auto; text-align: center;">
			<img src="/Kdessert/image/${productDto.getProImageStr()}"  style="width: 200px; height: 180px;">
		</div>
		<div style="width: 200px; height: 200px; float: left; margin-left: 100px; text-align: left; padding-left: 30px;">
			가격: <fmt:formatNumber value="${productDto.getProPriceInt()}" pattern="#,##0" /><br>
			재고: <fmt:formatNumber value="${productDto.getProStockInt()}" pattern="#,##0" /><br>
			<form action="/Kdessert/basket" method="post">
				<input type="number" id="basStock" name="basStock" value="1" min="1" max="${productDto.getProStockInt()}">
				<input type="hidden" id="proIndex" name="proIndex" value="${productDto.getProIndexInt()}">
				<input type="hidden" id="maxStock" name="maxStock" value="${productDto.getProStockInt()}">
				<input type="submit" value="담기">
			</form>
		</div>
		<div>
		태그
		<c:forEach var="tag" items="${productDto.getProTagList()}" varStatus="status">
   					 ${tag}
    				<c:if test="${!status.last}">, </c:if> <!-- 마지막이 아닐 경우에만 쉼표 출력 -->
					</c:forEach>
		
		</div>
		<hr style="clear: both;">
		
		<div>
		
				<table style="width: 1100px; margin: auto;">
				<tr>
					<td>소개</td>
				</tr>
				<tr>
					<td style="width: 30">${productDto.getProIntroStr()}</td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>