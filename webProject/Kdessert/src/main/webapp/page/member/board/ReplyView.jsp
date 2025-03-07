<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<!-- 댓글란 구현 -->
<style type="text/css">
	#replyText{
		width: 1000px;
		height: 90px;
		resize: none;
		margin-bottom: 40px;
		
		font-family: sans-serif;
	}
	#WriterInfo{
	
		display: flex;
		gap: 15px; /* 간격 */
		justify-content: flex-start;
		
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
</style>
	<!-- 댓글 목록 -->
	<h4>댓글</h4>
	<div id="WritedRyply">
		<div id="WriterInfo">
	
			<span style="color: black;">작성자</span>
			<span>작성일</span>
			
			<a>수정</a>
			<a>삭제</a>
		</div>
		<div id="replyList">
			댓글 작성부분
		</div>
	</div>
	
	<!-- 댓글 작성부분 -->
	<form action="./write" method="post">
		<textarea id= 'replyText' name="brdTextStr"></textarea>
		<input type="submit" value="등록">
	</form>

</html>