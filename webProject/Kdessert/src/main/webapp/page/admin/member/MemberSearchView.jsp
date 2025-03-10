<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자페이지 | 회원관리</title>

<style type="text/css">

	#wrap {
		width:1920px;
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
	
	#select_member {
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
		
		<div>
			<div id="div_category">
				<jsp:include page="../commPage/Category_Mgr.jsp"/>
			</div>
			
			<div id="div_content">
				<h2>　회원관리</h2>
				
				<div id="div_search">
					<form action="./search" method="get">
						<input id="search_text" name="searchText" type="text">
						<input id="search_submit" type="submit" value="검색">
					</form>
				</div>
			
				<table id="table_info">
					<thead>
						<tr>
							<th scope="col">회원번호</th>
							<th scope="col">이름</th>
							<th scope="col">아이디</th>
							<th scope="col">이메일</th>
							<th scope="col">생년월일</th>
							<th scope="col">가입일자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="search" items="${memberList}">
							<tr>
								<td>${search.getMemIndexInt()}</td>
								<td><a href="./details?no=${search.getMemIndexInt()}">${search.getMemNameStr()}</a></td>
								<td>${search.getMemIdStr()}</td>
								<td>${search.getMemEmailStr()}</td>
								<td>${search.getMemBirthDate()}</td>
								<td>${search.getMemSignTimeDate()}</td>
							</tr>
						</c:forEach>
					</tbody>
					
				</table>
				
				<div id="div_pageBtn">
				    <c:if test="${pageNum > 1}">
				        <a href="?pageNum=${pageNum - 1}&pageSize=${pageSize}" class="page-btn">&lt;</a>
				    </c:if>
				
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
				
				    <c:if test="${pageNum < totalPage}">
				        <a href="?pageNum=${pageNum + 1}&pageSize=${pageSize}" class="page-btn">&gt;</a>
				    </c:if>
				</div>
			
			</div>	
		</div>
	</div>	

</body>

<script type="text/javascript">

</script>

</html>