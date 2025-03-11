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
	#listWrap {
		width: 1000px;
		margin: auto;
		/* 푸터 맨 하단으로 고정할 수 있게 추가 */
		min-height: 800px; /* 최소높이 지정 */
	}
	
	#table_info {
		margin: auto;
		width: 1000px;
		
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
		border-top: 2px solid black;

	}
	
	#colum {
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
	
	.boardLists {
		text-align: center;
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
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

	.title {
	    display: block;
	    max-width: 400px; /* 최대 너비 설정 */
	    white-space: nowrap; /* 한 줄로 유지 */
	    overflow: hidden; /* 넘치는 글자 숨김 */
	    text-overflow: ellipsis; /* 말줄임표(...) 추가 */
	}
	
	#writer{

	    max-width: 400px; /* 최대 너비 설정 */
	    white-space: nowrap; /* 한 줄로 유지 */
	    overflow: hidden; /* 넘치는 글자 숨김 */
	    text-overflow: ellipsis; /* 말줄임표(...) 추가 */
	}


</style>

</head>

<body>


	<jsp:include page="../commPage/Mem_Header.jsp" />
	<jsp:include page="../commPage/Category_Main.jsp" />

	<div id="listWrap">
		<div id="topInter">
			<input class="boardListbtn" type="button" value="글쓰기"
				onclick="location.href='/Kdessert/board/write'">
			<div id="boardSearch">
				<form action="/Kdessert/board/search">
					<input id="writeSearchBox" type="text" name= 'searchStr' placeholder="검색창"> 
					<input class="boardListbtn" type="submit" value="검색">
				</form>
			</div>
		</div>


		<table id="table_info">
			<thead id="colum">
				<tr>
					<th scope="col" style="max-width: 660px;">타이틀</th>
					<th scope="col" style="width: 160px;">작성자</th>
					<th scope="col" style="width: 100px;">작성일</th>
					<th scope="col" style="width: 80px;">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr onclick="location.href='./board/freeboarddetail?brdIndexInt=${board.brdIndexInt}'">
						<td>
							<span class='title' style="margin-left: 40px;">${board.brdSubjectStr}</span>
						</td>
						<td class='boardLists' id='writer'>
							<c:if test="${empty board.memberDto.memNameStr}"> 
								(알 수 없는 회원)
							</c:if>
							 ${board.memberDto.memNameStr}
						</td>
						<td class='boardLists'>${board.brdCreDate}</td>
						<td class='boardLists'>${board.brdViewInt}</td>

					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>


	<jsp:include page="../commPage/Mem_Footer.jsp" />
</body>
</html>