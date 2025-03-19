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
		text-align: center;

		background-color: white;
		color: #7B7B7B;
		font-size: 16px;
		
    }

	#wrap {
		overflow-x: hidden;
		min-width: 1340px;
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
	
/* 	#select_카테고리명 수정필요 */
	#select_order {
		background-color: white;
		color: #64473E;
		font-weight: bold;
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
			<div id="div_content">
				<h2 style="text-align:center; margin-top: 40px; margin-bottom: 40px; ">주문 상세</h2>
				
						<h2 style="text-align: center;">주문 번호:${orderDto.getOrdIndexint()}</h2>

		<div style="margin: auto;">
			<table>
				<tr>
					<td>제품번호</td>
					<td>제품명</td> 
 					<td>주문수량</td>
					<td>단가</td>
					<td>수량*단가</td>

				</tr>
				
				<c:forEach var="orderProduct" items="${orderProductList}">
					<tr>
						<td>${orderProduct.getProductIndexInt()}</td>
						<td>${orderProduct.getProductNameStr()}</td>
						<td><fmt:formatNumber value="${orderProduct.getProductStockInt()}" pattern="#,##0" /></td>
						<td><fmt:formatNumber value="${orderProduct.getProductPriceInt()}" pattern="#,##0" /></td>
						<td><fmt:formatNumber value="${orderProduct.getProductStockInt()
						 * orderProduct.getProductPriceInt()}" pattern="#,##0" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div>
			<h2 style="text-align: center;">주문정보</h2>
			<table>
				<tr>
					<td>주문번호</td>
					<td>${orderDto.getOrdIndexint()}</td>
				</tr>	
				<tr>
					<td>주문일시</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${orderDto.getOrdTime()}"/></td>
				</tr>
				<tr>
					<td>주문액</td>
					<td><fmt:formatNumber value="${orderDto.getTotalPriceInt()}" pattern="#,##0" /></td>
				</tr>
				<tr>
					<td>주문상태</td>
					<td>${orderDto.getStaStatStr()}</td>
				</tr>
			</table>
		</div>
		
				<div>
			<h2 style="text-align: center;">배송정보</h2>
			<table>
				<tr>
					<td>수령자</td>
					<td>${orderDto.getMemNameStr()}</td>
				</tr>	
				<tr>
					<td>주소</td>
					<td>${orderDto.getMemAdd1Str()}</td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td>${orderDto.getMemAdd2Str()}</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center; vertical-align: middle;">
						<div class="btn_style" onclick="history.back();" style="display: inline-block;">
							돌아가기
						</div>
					</td>
				</tr>
			</table>
		</div>
				
				
				
				
				
				
				
				
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">

</script>

</html>