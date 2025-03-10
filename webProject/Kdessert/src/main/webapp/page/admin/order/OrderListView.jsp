<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문목록</title>

<style type="text/css">
	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
		width: 900px;
		margin: auto;
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
	}

	th, td {
		padding: 15px;
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
    
    #container{
    	margin: auto;
    	width: 1200px;
    	height: 800px;
    }

</style>


</head>
<body>



	<jsp:include page="../commPage/Adm_Header.jsp"></jsp:include>

	<div style="float: left;">
		<jsp:include page="../commPage/Category_Mgr.jsp"></jsp:include>
	</div>
	<div id="container">
		<h2 style="text-align: center;">주문 목록</h2>

		<div style="margin: auto;">
			<table>
				<tr>
					<td>주문번호</td>
					<td>주문자</td>
					<td>처리현황</td>
					<td>주문일시</td>
					<td>총금액</td>
					<td>삭제</td>

				</tr>
				
				<c:forEach var="orderDto" items="${orderList}">
					<tr>
						
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>