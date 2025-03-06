<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
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
		width: 300px;
    }

</style>

</head>

<body>


	<jsp:include page="../commPage/Mem_Header.jsp"/>
	<jsp:include page="../commPage/Category_Main.jsp"/>
	
	<div id="container">
		<div id="div_category">
			<jsp:include page="../commPage/Category_Mypage.jsp"/>
		</div>
	</div>
	<table>
		<tr>
			<td>제품명</td>
			<td>수량</td>
			<td>가격</td>
		</tr>
		<c:forEach var="basketDto" items="${basketList}">
			<tr>
				<td>${basketDto.getProNameStr()}</td>
				<td>${basketDto.getBasStockInt()}</td>
				<td>${basketDto.getProPriceInt()}</td>
			</tr>		
		</c:forEach>
	</table>
</body>

<script type="text/javascript">

</script>

</html>