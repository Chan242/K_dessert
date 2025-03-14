<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		
	}

	th, td {
		padding: 15px;
		text-align: center;
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
   a{
    text-decoration: none; /* 밑줄 제거 */
    color: inherit;        /* 기본 텍스트 색상 상속 */
    font-weight: normal;   /* 강조 해제 */
}

</style>


</head>
<body>



	<jsp:include page="../commPage/Adm_Header.jsp"></jsp:include>

	<div style="float: left;">
		<jsp:include page="../commPage/Category_Mgr.jsp"></jsp:include>
	</div>
	<div id="container">
		<h2 style="text-align: center;">주문 관리</h2>

		<div style="margin: auto;">
			<table>
				<tr>
					<td>주문번호</td>
					<td>주문자</td>
					<td>처리현황</td>
					<td>주문일시</td>
					<td>총금액</td>
					

				</tr>
				
				<c:forEach var="orderDto" items="${orderList}">
					<tr>
						<td>${orderDto.getOrdIndexint()}</td>
						<td><a href="./detail?no=${orderDto.getOrdIndexint()}">${orderDto.getMemNameStr()}</a></td>
						<td>
							<select id="status" style="width: 80px;" onchange="changeFnc()">
								<c:forEach var="orderStatusDto" items="${orderStatusList}">
									<option value="${orderStatusDto.getStaStatusStr()}"
									 <c:if test="${orderStatusDto.getStaStatusStr() eq orderDto.getStaStatStr()}">selected="selected"</c:if>>
										${orderStatusDto.getStaStatusStr()}
									</option>
								</c:forEach>
							</select>
						</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderDto.getOrdTime()}"/></td>
						<td>${orderDto.getTotalPriceInt()}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		

		
<!-- 페이지네이션 네비게이션 -->
		<div style="text-align: center;">
    <!-- 이전 페이지 -->
  		  <c:if test="${no > 1}">
   		     <a href="./list?no=${no - 1}"><</a>
   		 </c:if>
    
    <!-- 페이지 번호 -->
   	 <c:forEach var="i" begin="${start}" end="${maxEnd}">
   	     <a href="./list?no=${i}">${i}</a>
   	 </c:forEach>
    
    <!-- 다음 페이지 -->
    <c:if test="${no < totalPageInt}">
        <a href="./list?no=${no + 1}">></a>
    </c:if>
	</div>


		
	</div>

</body>

<script type="text/javascript">

function changeFnc() {
	let status = document.getElementById("status");
	confirm("상태를 바꾸시겠습니까?: " + status.value);
	window.open('/admin/order/popup','상태변경',
			'width=600,height=400,resizable=yes,scrollbars=yes,top=100,left=100'		
	);

	
	
}

</script>
</html>