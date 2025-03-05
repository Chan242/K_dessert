<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	background-color: lightgray;
}
	

</style>

<script type="text/javascript">


</script>

</head>
<body>

	<jsp:include page="../commPage/Mem_Header.jsp"/>
	<jsp:include page="../commPage/Category_Main.jsp"/>
	
	<div id="container">
	
		<h1 style="text-align: center;">${userProductDto.getproNameStr()}</h1>
	
		<div style="width: 300px; height: 300px; border: 1px solid black; float: left; margin: auto; text-align: center;">
			이미지 자리
		</div>
		<div style="width: 200px; height: 200px; border: 1px solid black; float: left; margin-left: 100px; text-align: left;">
			가격: ${userProductDto.getproPriceInt()}<br>
			재고: ${userProductDto.getproStockInt()}<br>
			<form action="">
				<input type="number" id="numberInput" value="" min="1" max=" ${userProductDto.getproStockInt()}">
				<input type="submit" value="담기">
			</form>
		</div>
				<table style="margin: auto;">
				<tr>
					<td>소개</td>
					<td>${userProductDto.getproIntroStr()}</td>
				</tr>
				
			</table>
	</div>

</body>
</html>