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

	body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	
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

	#select_order {
		background-color: white;
		color: #64473E
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
					<td colspan="5" style="text-align: right;">
					    <form action="./list" method="get">
					    <input type="hidden" name="no" value="1">
					        <select name="filter">
					            <option value="all">전체</option>
					            <c:forEach var="orderStatus" items="${orderStatusList}">
					                <option value="${orderStatus.getStaStatusStr()}" <c:if test="${filter eq orderStatus.getStaStatusStr()}">selected</c:if>>${orderStatus.getStaStatusStr()}</option>
					                					                                          
					            </c:forEach>
					        </select>
					        <input type="submit" value="변경">
					    </form>
					</td>

				</tr>
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
							${orderDto.getStaStatStr()}
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
   		     <a href="./list?no=${no - 1}&filter=${filter}"><</a>
   		 </c:if>
    
    <!-- 페이지 번호 -->
   	 <c:forEach var="i" begin="${start}" end="${maxEnd}">
   	     
   	     <a href="./list?no=${i}&filter=${filter}" <c:if test="${i eq no}">style="font-weight: bolder;"</c:if> >${i}</a>
   	 </c:forEach>
    
    <!-- 다음 페이지 -->
    <c:if test="${no < totalPageInt}">
        <a href="./list?no=${no + 1}&filter=${filter}">></a>
    </c:if>
	</div>


		
	</div>

</body>


</html>