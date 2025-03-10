<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>고객 게시판 | ${boardDto.getBrdSubjectStr()}</title>

<style type="text/css">

	#boardWrap {
		width: 1000px;
		min-height: 800px;
		margin: auto;
		margin-top: 20px;
	

	}
	
	#boardWrap a{
		
		text-decoration: none;
		color: #888;
	}

	/* 제목 스타일 */
	#title {

		padding: 20px;
		padding-bottom: 0px;
	}
	
	
	#context {
		margin: 20px;
	}
	
	/* 상단 헤더 */
	#head {
		display: flex;
		align-items: center; /* 수직 가운데 정렬 */
		
		font-size: 14px; /* 폰트 크기 조정 */
		color: #555; /* 색상 */
	}

	/* 작성자 왼쪽 정렬 */
	.writer {
		flex: 1;
		font-weight: bold;
	}

	/* 작성일, 조회수 오른쪽 정렬 */
	.date-view {
		display: flex;
		gap: 15px; /* 간격 */
		justify-content: flex-end;
	}

	/* 작성일, 조회수 항목 스타일 */
	.date-view span {
		font-size: 14px;
		
	}

	/* 콘텐츠 영역 스타일 */
	#context {
		font-size: 16px;
		line-height: 1.5;
		margin-bottom: 70px;
		border-bottom: 1px solid gray;
		border-top: 2px solid black;
		
		padding: 20px;
		padding-top: 5px;
	}
	
	#contentBtn{
		display: flex;
		gap: 15px; /* 간격 */
		justify-content: flex-end;
		color: #888;
	
	}
	
	/* 댓글부분 */
	
	
	#replyText{
		width: 1000px;
		height: 90px;
		resize: none;
		margin-bottom: 10px;
		
		font-family: sans-serif;
	}
	
	#WriterInfo{
	
		display: flex;
		gap: 15px; /* 간격 */
		justify-content: flex-start;
		
		margin-top: 15px;
		
		font-size: 14px;
		color: #888;
	}
	
	#replyList{

		border-bottom: 2px solid #E1E1E1;
		padding-bottom: 20px;
		padding-top: 10px;
	}
	
	#WritedRyply{

		
		min-height: 100px; 
		margin-bottom: 30px;
	}
	
	.reply form{
		position:relative;

	}
	#inputBtn{
		
		position: absolute;
		right: 0px;
	
		font-size: 12pt;
		width: 80px;
		height: 28px;
		text-align: center;
		color: white;
		background-color: #64473E;
		border: none;	
		
	}

</style>

</head>
<jsp:useBean id="boardDto"
	scope="session"
	class="user.board.main.FreeBoardDto"/>
	
<jsp:useBean id="memberDto"
	scope="session"
	class="admin.member.MemberDto"/>
<body>
<jsp:include page="../commPage/Mem_Header.jsp"/>
<jsp:include page="../commPage/Category_Main.jsp"/>

<div id="boardWrap">
	<div id="title">
		<h1>${boardDto.getBrdSubjectStr()}</h1>
		<div id="head">
			<span class="writer">작성자: ${boardDto.getMemberDto().memNameStr}</span> <!-- 작성자 텍스트 -->
			
			<div class="date-view">
				<span>작성일: ${boardDto.getBrdCreDate()}</span> <!-- 작성일 텍스트 -->
				<span>조회수: ${boardDto.getBrdViewInt()}</span> <!-- 조회수 텍스트 -->
			</div>
		</div>
	</div>
		<div id="context">
			<p>${boardDto.getBrdTextStr()}</p> <!-- 내용 텍스트 -->
			<div id='contentBtn'>
				<a href="./update?brdIndexInt=${boardDto.getBrdIndexInt()}">수정</a>
				<a href="./delete?brdIndexInt=${boardDto.getBrdIndexInt()}">삭제</a>
			</div>

		</div>
		
<!-- 댓글부분 -->
	<h4>댓글</h4>
	${boardDto.brdIndexInt}
	
	<c:forEach var="reply" items="${boardreplyList}">
		<div id="WritedReply">
			<div id="WriterInfo">
		
				<span style="color: black;">작성자</span>
				<span>작성일: ${reply.replyCreDate}</span>
				
				<a>수정</a>
				<a>삭제</a>
			</div>
			<div id="replyList">
				${reply.replyTextStr}
			</div>
		</div>
	</c:forEach>
	
	<!-- 댓글 작성부분 -->
	<div class= "reply">
		<h4>댓글 쓰기</h4>
		<form action="./freeboarddetail?brdindexint=$" method="post">
			<textarea id= 'replyText' name="brdTextStr"></textarea>
			<input id = 'inputBtn' type="submit" value="등록">
		</form>
	</div>
</div>

<jsp:include page="../commPage/Mem_Footer.jsp"/>
</body>
</html>
