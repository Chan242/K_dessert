<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.board.main.FreeBoardDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>고객 게시판 목록</title>

<script type="text/javascript">
	
</script>

<style type="text/css">
#listBox {
	width: 1000px;
	display: 0;
	margin: auto;
	/* 푸터 맨 하단으로 고정할 수 있게 추가 */
	min-height: 800px; /* 최소높이 지정 */
}

	#table_info{
		margin: auto;
		width: 1000px;
		
		border-top: 2px solid black;
		table-layout: fixed;
	}
	#colum{
		text-align: center;
	}
	
	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
	}

	th, td {
		padding: 15px;
	}
	

.boardListbtn {
	font-size: 12pt;
	width: 80px;
	height: 28px;
	text-align: center;
	color: white;
	background-color: #64473E;
	border: none;
}

#writeSearchBox {
	width: 200px;
	height: 23px;
}

#boardSearch {
	float: right;
}

#topInter {
	margin-bottom: 10px;
	margin-top: 70px;
	padding: 10px;
}

#boardLine a {
	border-bottom: 1px solid lightgray;
	display: flex; /* a 태그 안에 flex 적용 */
	justify-content: space-between;
	align-items: center; /* 수직 정렬 */
	padding-top: 10px;
	padding-bottom: 10px;
	text-decoration: none; /* a태그 밑줄 제거 */
	color: black; /* a태그 텍스트 검은색 */
}

.writeType{
	text-align: center;
	margin-left: 30px;
	margin-right: 5px;
}

.writer {
	text-align: center;
	margin-left: 20px;
	margin-right: 15px;
}

#title{
	width: 600px;
}

</style>

</head>

<body>


	<jsp:include page="../commPage/Mem_Header.jsp" />
	<jsp:include page="../commPage/Category_Main.jsp" />


	<div id="listBox">
		<div id="topInter">
			<input class="boardListbtn" type="button" value="글쓰기"
				onclick="location.href='/Kdessert/board/write'">
			<div id="boardSearch">
				<form action="/Kdessert/board">
					<input id="writeSearchBox" type="text" placeholder="검색창"> <input
						class="boardListbtn" type="submit" value="검색">
				</form>
			</div>
		</div>
<!-----상단컬럼---->
		<h4>
			<span style="margin-left: 200px;">타이틀</span> 
			<span style="margin-right: 40px"> 
			<span class="writeType">작성자</span>
				<span class="writeType">작성일</span> 
				<span class="writeType">조회수</span>
			</span>
		</h4>

		<!--  보드 리스트가 비어있지 않다면(null이 아니라면) -->
		<c:forEach var="board" items="${boardList}">

			<div id="boardLine">
				<a href="./board/freeboarddetail?brdIndexInt=${board.brdIndexInt}">
					<span id='title' style="margin-left: 40px;">${board.brdSubjectStr}</span>
					<span style="margin-right: 40px;"> <span class="writer">
							<c:if test="${empty board.memberDto.memNameStr}"> 
							(알 수 없는회원)
						</c:if> ${board.memberDto.memNameStr}
					</span> <span class="writer">${board.brdCreDate}</span> <span
						class="writer">${board.brdViewInt}</span>
				</span>
				</a>

			</div>
		</c:forEach>

		<table id="table_info">
			<thead id="colum">
				<tr>
					<th scope="col" style="width: 700px;">타이틀</th>
					<th scope="col" style="width: 100px;">작성자</th>
					<th scope="col" style="width: 100px;">작성일</th>
					<th scope="col" style="width: 100px;">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
					
						<td >
							<a href="./board/freeboarddetail?brdIndexInt=${board.brdIndexInt}">
								<span id='title' style="margin-left: 40px;">${board.brdSubjectStr}</span>
							</a>
						</td>
						<td>${board.brdSubjectStr}</td>
						<td>
							<c:if test="${empty board.memberDto.memNameStr}"> 
								(알 수 없는회원)
							</c:if>
							 ${board.memberDto.memNameStr}
						</td>
						<td>${board.brdCreDate}</td>
						<td>${board.brdViewInt}</td>

					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>


	<jsp:include page="../commPage/Mem_Footer.jsp" />
</body>
</html>