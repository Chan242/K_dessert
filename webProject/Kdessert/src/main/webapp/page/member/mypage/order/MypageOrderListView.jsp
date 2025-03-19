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
	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}

	#wrap {
		width:1895px;
	}

	#container {
		width: 1200px;
		margin: auto;
	}

	#div_category {
		float: left;
	}
	#div_content {
		width: 950px;
		float: right;
	}
	
	#select_order {
		background-color: white;
		color: #64473E;
		font-weight: bold;
	}
	
	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
		margin: auto;
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
	}

	th, td {
		padding: 15px;
	}
	
	#div_pointInfo {
		width: 500px;
		height: 300px;
		margin: auto;
	}

	#div_pointInfo table {
		margin: auto;
		font-size: 20px;
	}
	
	#div_pointInfo table td {
		width: 200px;
		text-align: right;
	}
	
	#div_pointInfo div {
		text-align: center;
		margin-top: 50px;
	}
	
	.btn_style {

		width: 200px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: #64473E;
		color: white;
		font-size: 16px;
		
		cursor: pointer;
		
    }

</style>

</head>

<body>

	<div id="wrap">
		<jsp:include page="../../commPage/Mem_Header.jsp"/>
		<jsp:include page="../../commPage/Category_Main.jsp"/>
		
		<div id="container">
			
			<div id="div_category">
				<jsp:include page="../../commPage/Category_Mypage.jsp"/>
			</div>
			
			
			
		<div style="width: 950px; float: right;">
		<h2 style="text-align: center;">내 주문 목록</h2>

		<div style="margin: auto;">
			<table>
				<tr>
					<td>주문번호</td>
					<td>주문자</td>
					<td>처리현황</td>
					<td>주문일시</td>
					<td>총금액</td>
					<!-- <td>취소</td> -->

				</tr>
				
				<c:forEach var="orderDto" items="${orderList}">
					<tr>
						<td>${orderDto.getOrdIndexint()}</td>
						<td><a href="./order/detail?no=${orderDto.getOrdIndexint()}">${orderDto.getMemNameStr()}</a></td>
						<td>
							${orderDto.getStaStatStr()}
						</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderDto.getOrdTime()}"/></td>
						<td>${orderDto.getTotalPriceInt()}</td>
						
<%-- 						<c:if test="${orderDto.getStaStatStr() eq '접수대기'}">
    						<td>
        						<button>취소</button>
    						</td>
						</c:if>
 --%>
						
					</tr>
				</c:forEach>
			</table>
		</div>
		
		</div>
	</div>

		</div>


</body>

<script type="text/javascript">


</script>

</html>