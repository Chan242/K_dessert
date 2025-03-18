<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자 | 행사관리</title>

<style type="text/css">
body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	
	#wrap {
		overflow-x: hidden;
		min-width: 1340px;
	}

	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
		text-align: center;
	}
	
	table {
		border-top: 2px solid black;
	}
	
	th {
		background-color: #F5F5F5;
	}

	th, td {
		padding: 15px;
	}
	
	a {
		color: black;
	}
	
	#div_category{
		float: left
	}
	
	#div_content{
		width: 1300px;
		margin-left: 50px; 
		float: left;
	}
	
	#div_addButton {
		float: left;
		margin-left: 50px;
	}
	
	#addBtn {
		width: 90px;
		height: 32px;
		font-size: 16px;
		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;
		margin-top: 3px;
		line-height: 5px;
		cursor: pointer;
	}

	#div_search{
 		width: 290px;
 		margin-right: 50px;
 		margin-bottom: 10px;
		float: right; 
	}
	
	#search_text {
		width: 200px;
		height: 10px;
		padding: 10px;
		font-size: 16px;
		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;
		margin-top: 3px;
		line-height: 5px;
	}
	
	#search_submit {
		width: 60px;
		height: 32px;
		font-size: 16px;
		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;
		margin-top: 3px;
		line-height: 5px;
		cursor: pointer;
	}

	#table_info{
		margin: auto;
		width: 1200px;
	}
	
	#select_event {
		background-color: white;
		color: #64473E
	}
	
	#div_content h2 {
		width: 1200px;
		margin: auto;
		margin-top: 40px;
		margin-bottom: 40px; 
		padding-bottom: 20px;
		border-bottom: 2px solid black;
	}
	
	
/* 	페이징 버튼 관련 css */
	
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
		<jsp:include page="../commPage/Adm_Header.jsp"/>
		
		<div id="container">
			<div id="div_category">
				<jsp:include page="../commPage/Category_Mgr.jsp"/>
			</div>
			
			<div id="div_content">
				<h2>　행사관리</h2>
				
				<div id="div_addButton">
					<input id="addBtn" type="button" value="신규 등록" onclick="location.href='./add'" >
				</div>
				
				<div id="div_search">
					<form action="./search" method="get">
						<input id="search_text" name="searchText" type="text" placeholder="행사번호, 이름">
						<input id="search_submit" type="submit" value="검색">
					</form>
				</div>
				
				<table id="table_info">
					<thead>
						<tr>
							<th scope="col" style="width: 150px;">행사번호</th>
							<th scope="col" style="width: 250px;">행사명</th>
							<th scope="col" style="width: 200px;">행사날짜</th>
							<th scope="col" style="width: 200px;">공개여부</th>
							<th scope="col" style="width: 200px;">생성일</th>
							<th scope="col" style="width: 200px;">수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty eventList}">
								<tr>
									<td colspan="6">등록된 행사가 없습니다</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="event" items="${eventList}">
									<tr>
										<td>${event.getEveIndexInt()}</td>
										<td><a href="./details?no=${event.getEveIndexInt()}">${event.getEveNameStr()}</a></td>
										<td>${event.getEveEventDate()}</td>
										<c:choose>
											<c:when test="${event.getEveOpenInt() == 0}">
												<td>공개</td>
											</c:when>
											<c:otherwise>
												<td>비공개</td>
											</c:otherwise>
										</c:choose>
										<td>${event.getEveCreDate()}</td>
										<td>${event.getEveCorrDate()}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
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
											end="${pageNum + 2 > totalPage ? totalPage : pageNum + 2}" var="i">
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

</body>

<script type="text/javascript">

</script>

</html>