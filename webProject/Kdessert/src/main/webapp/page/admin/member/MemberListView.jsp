<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">

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
 		width: 250px;
		margin: auto;
	}

	#table_info{
		margin: auto;
		width: 1200px;
	}

</style>

</head>

<body>

	<jsp:include page="../commPage/Adm_Header.jsp"/>
	
	<div>
		<div id="div_category">
			<jsp:include page="../commPage/Category_Mgr.jsp"/>
		</div>
		
		<div id="div_content">
			<h2 style="margin: 40px;">회원관리</h2>
			
			<div id="div_search">
				<input type="text">
				<input type="submit" value="검색">
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
					<c:forEach var="member" items="${memberList}">
						<tr>
							<td>${member.memIndexInt}</td>
							<td><a href="./details?no=${member.memIndexInt}">${member.memNameStr}</a></td>
							<td>${member.memIdStr}</td>
							<td>${member.memEmailStr}</td>
							<td>${member.memBirthDate}</td>
							<td>${member.memSignTimeDate}</td>
						</tr>
					</c:forEach>
				</tbody>
				
				
			
			</table>
		
		</div>	
	</div>
	

</body>

<script type="text/javascript">

</script>

</html>