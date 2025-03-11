<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="user.board.reply.BoardReplyDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<!-- 댓글란 구현 -->
<style type="text/css">

	#replyText{
		width: 1000px;
		height: 90px;
		resize: none;
		margin-bottom: 10px;
		
		font-family: sans-serif;
	}
	
	.WriterInfo{
	
		display: flex;
		gap: 15px; /* 간격 */
		justify-content: flex-start;
		
		margin-top: 15px;
		
		font-size: 14px;
		color: #888;
	}
	
	#replyList{

		border-bottom: 2px solid #E1E1E1;
		padding-bottom: 15px;
		margin-top: 10px;
	}
	
	.WritedRyply{

		
		min-height: 100px; 
		margin-bottom: 30px;
		margin-top: 10px;
	}
	
	#reply form{
		position:relative;

	}
	#inputBtn{
		display: flex;
		
		float: right;

		margin-bottom: 50px;
		justify-content: flex-end;
		font-size: 12pt;
		width: 80px;
		height: 28px;
		text-align: center;
		color: white;
		background-color: #64473E;
		border: none;	
		
	}
	#reply{
		display: flex;
	}
</style>

	
	<!-- 댓글 목록 -->
<div>
	
	<c:forEach var="reply" items="${boardreplyList}">
		<input type="hidden" name="brdIndexInt" value="${boardDto.brdIndexInt}">
		<div class="WritedReply">
			<div class="WriterInfo">
		
				<span style="color: black; font-weight: bold; font-size: 15px;">
					<c:if test="${empty reply.memberDto.memNameStr}"> 
					(알 수 없는 회원)
				</c:if>
				${reply.memberDto.memNameStr}
				</span>
				<span>작성일: ${reply.replyCreDate}</span>
				
				<a>수정</a>
				<a href="/Kdessert/board/deleteReply?brdIndexInt=${boardDto.brdIndexInt}&replyIndexInt=${reply.replyIndexInt}">삭제</a>

			</div>
			
			<div id="replyList">
				${reply.replyTextStr}
			</div>
		</div>
	</c:forEach>
	
	<!-- 댓글 작성부분 -->
	<h4>댓글 쓰기</h4>
	<div id= "reply">
		<form action="/Kdessert/board/freeboarddetail" method="post">
			<input type="hidden" name="memIndexInt" value="${sessionScope.member.memIndexInt}">
			<input type="hidden" name="brdIndexInt" value="${boardDto.brdIndexInt}">
			<textarea id= 'replyText' name="replyTextStr"></textarea>
			<input id = 'inputBtn' type="submit" value="등록">
		</form>
	</div>
</div>
</html>