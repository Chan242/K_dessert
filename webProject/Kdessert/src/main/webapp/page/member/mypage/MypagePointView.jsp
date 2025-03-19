<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>마이페이지 | 마이포인트</title>

<style type="text/css">

	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
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
	
	#select_point {
		background-color: white;
		color: #64473E;
		font-weight: bold;
	}
	
	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
		text-align: center;
		font-size: 16px;
	}
	
	table {
		border-top: 2px solid black;
	}
	
	th {
		background-color: #F5F5F5;
	}

	th, td {
		padding: 10px;
	}
	
	#div_pointCharge, #div_pointInfo{
		width: 600px;
		margin: auto;
	}
	
	 #div_pointCharge{
		height: 150px;
	}
	

	#div_pointCharge table, #div_pointInfo table{
		margin: auto;
		font-size: 20px;
		width: 600px;
	}

	#div_pointCharge table th {
		text-align: left;
		width:80px;
	}
	
	
	#div_pointCharge table td {
		width: 200px;
		text-align: right;
	}
	
	#div_pointCharge div {
		text-align: center;
		margin-top: 30px;
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
    
/*     페이징 버튼 관련 css */

	#div_pageBtn {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px; /* 버튼 간격 */
    margin-top: 20px;
	}

	.page-btn {
	    text-decoration: none;
	    color: black;
	    font-size: 16px;
	    padding: 5px 10px;
	    border-radius: 5px;
	    transition: 0.3s;
	}
	
	.page-btn:hover {
	    background-color: #f0f0f0;
	}
	
	.active {
	    text-decoration: underline;
	    font-weight: bold;
	}

</style>

</head>

<body>

	<div id="wrap">
		<jsp:include page="../commPage/Mem_Header.jsp"/>
		<jsp:include page="../commPage/Category_Main.jsp"/>
		
		<div id="container">
			<div id="div_category">
				<jsp:include page="../commPage/Category_Mypage.jsp"/>
			</div>
			<div id="div_content">
				<h2 style="text-align:center; margin-top: 40px; margin-bottom: 40px; ">마이포인트</h2>
				<div id="div_pointCharge">
					<table>
						<tr>
							<th>현재 포인트</th>
							<td><fmt:formatNumber value="${memberDto.memPointInt}" type="number"/> P</td>
						</tr>
					</table>
					<div>
						<input class="btn_style" type="button" value="충전하기" onclick="chargePopup()">
					</div>
				</div>
				<h2 style="text-align:center; margin-top: 40px; margin-bottom: 40px; ">포인트 내역</h2>
				<div id="div_pointInfo">
					<table>
						<thead>
						<tr>
							<th scope="col" style="width: 120px;">날짜</th>
							<th scope="col" style="width: 120px;">충전/사용</th>
							<th scope="col" style="width: 110px;">남은 포인트</th>
						</tr>
						</thead>
						<tbody>
						
						<c:if test="${not empty memberPoint}">
							<c:forEach var="point" items="${memberPoint}" varStatus="status">
								<tr>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${point.getMemPointDate()}"/></td>
									
									<td>
										<c:choose>
							                <c:when test="${point.getMemPointInt() > 0}">
							                    <span style="color: green;"><fmt:formatNumber value="${point.getMemPointInt()}" type="number"/> P</span>
							                </c:when>
							                <c:when test="${point.getMemPointInt() < 0}">
							                    <span style="color: red;"><fmt:formatNumber value="${point.getMemPointInt()}" type="number"/> P</span>
							                </c:when>
							                <c:otherwise>
							                    <span><fmt:formatNumber value="${point.getMemPointInt()}" type="number"/> P</span>
							                </c:otherwise>
						            	</c:choose>
									</td>
	           						<td><fmt:formatNumber value="${point.getMemBalancePointInt()}" type="number"/> P</td>	
								</tr>
							</c:forEach>
						</c:if>
						
						<c:if test="${empty memberPoint}">
							<tr><td colspan="4">포인트 내역 없음</td></tr>
						</c:if>
						</tbody>
					</table>
					
					
					
				<!------------------ 페이지 버튼 ------------------>
				<div id="div_pageBtn">
				    <c:if test="${pageNum > 1}">
				        <a href="?pageNum=${pageNum - 1}&pageSize=${pageSize}" class="page-btn">&lt;</a>
				    </c:if>

						<c:choose>
							<c:when test="${totalPage == 1}">
								<!-- 페이지가 1개일 경우, 1번 페이지만 표시 -->
								<span class="page-btn active">1</span>
							</c:when>
							<c:when test="${totalPage <= 4}">
								<!-- 전체 페이지가 4 이하일 경우 -->
								<c:forEach begin="1" end="${totalPage}" var="i">
									<c:choose>
										<c:when test="${i == pageNum}">
											<span class="page-btn active">${i}</span>
										</c:when>
										<c:otherwise>
											<a href="?pageNum=${i}&pageSize=${pageSize}" class="page-btn">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when test="${totalPage >= 5}">
								<!-- 전체 페이지가 5개 이상일 때 -->
								<c:choose>
									<c:when test="${pageNum == 1}">
										<!-- 페이지 번호가 1일 때 (최대 5개까지) -->
										<c:forEach begin="1" end="5" var="i">
											<c:choose>
												<c:when test="${i == pageNum}">
													<span class="page-btn active">${i}</span>
												</c:when>
												<c:otherwise>
													<a href="?pageNum=${i}&pageSize=${pageSize}"
														class="page-btn">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:when test="${pageNum == 2}">
										<!-- 페이지 번호가 2일 때 (최대 5개까지) -->
										<c:forEach begin="1" end="5" var="i">
											<c:choose>
												<c:when test="${i == pageNum}">
													<span class="page-btn active">${i}</span>
												</c:when>
												<c:otherwise>
													<a href="?pageNum=${i}&pageSize=${pageSize}"
														class="page-btn">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:when test="${pageNum == totalPage - 1}">
										<!-- 페이지 번호가 마지막에서 두 번째일 때 (최대 5개까지) -->
										<c:forEach begin="${totalPage - 4 < 1 ? 1 : totalPage - 4}"
											end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == pageNum}">
													<span class="page-btn active">${i}</span>
												</c:when>
												<c:otherwise>
													<a href="?pageNum=${i}&pageSize=${pageSize}"
														class="page-btn">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:when test="${pageNum == totalPage}">
										<!-- 페이지 번호가 마지막일 때 (최대 5개까지) -->
										<c:forEach begin="${totalPage - 4 < 1 ? 1 : totalPage - 4}"
											end="${totalPage}" var="i">
											<c:choose>
												<c:when test="${i == pageNum}">
													<span class="page-btn active">${i}</span>
												</c:when>
												<c:otherwise>
													<a href="?pageNum=${i}&pageSize=${pageSize}"
														class="page-btn">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<!-- 페이지 번호가 중간일 때 (최대 5개까지) -->
										<c:forEach begin="${pageNum - 2 < 1 ? 1 : pageNum - 2}"
											end="${pageNum + 2 > totalPage ? totalPage : pageNum + 2}"
											var="i">
											<c:choose>
												<c:when test="${i == pageNum}">
													<span class="page-btn active">${i}</span>
												</c:when>
												<c:otherwise>
													<a href="?pageNum=${i}&pageSize=${pageSize}"
														class="page-btn">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>

						<c:if test="${pageNum < totalPage}">
				        <a href="?pageNum=${pageNum + 1}&pageSize=${pageSize}" class="page-btn">&gt;</a>
				    </c:if>
				</div>
				
				<!------------------ 페이지 버튼 ------------------>

					
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">

	function chargePopup() {
		
		var popupSetStr = "";
		
		popupSetStr += "width=480px, height=300px, left=720px, top=300px";
		
		window.open('/Kdessert/page/member/mypage/point/charge', 'chargePop', popupSetStr);
		
	}

</script>

</html>