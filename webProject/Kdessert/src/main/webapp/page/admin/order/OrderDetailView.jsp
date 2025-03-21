<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 주문관리</title>

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
		text-align: center;

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
		<h2 style="text-align: center;">주문 관리</h2>
		<h2 style="text-align: center;">주문 번호:${orderDto.getOrdIndexint()}</h2>
		
		<div style="overflow-x: auto; white-space: nowrap;">
		
			<div style=" margin: auto;">
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
	

</body>

<script type="text/javascript">
function changeFnc() {
    let status = document.getElementById("status").value; // 선택된 값 가져오기
    let no = "${orderDto.getOrdIndexint()}"; // JSP에서 주문 번호 가져오기

    // 상태 변경 확인
    if (confirm("상태를 바꾸시겠습니까?: " + status)) {
        // 새 창 열기
        let popupWindow = window.open("", "popupWindow", "width=600,height=400,resizable=yes,scrollbars=yes,top=100,left=100");

        // POST 방식으로 데이터를 보내기 위한 form 생성
        let form = document.createElement("form");
        form.method = "POST";
        form.action = "/Kdessert/admin/order/popup"; // 팝업 URL

        // 주문 번호와 상태 값을 form에 append
        let inputNo = document.createElement("input");
        inputNo.type = "hidden";
        inputNo.name = "no";
        inputNo.value = no;
        form.appendChild(inputNo);

        let inputStatus = document.createElement("input");
        inputStatus.type = "hidden";
        inputStatus.name = "status";
        inputStatus.value = status;
        form.appendChild(inputStatus);

        // form을 팝업 창에 제출
        document.body.appendChild(form); // form을 body에 append
        form.target = "popupWindow"; // 팝업 창에 데이터를 전송하기 위한 target 설정
        form.submit(); // 폼 전송
    }
}
</script>




</html>