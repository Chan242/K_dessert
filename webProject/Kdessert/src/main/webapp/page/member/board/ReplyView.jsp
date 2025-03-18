<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="user.board.reply.BoardReplyDto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<!-- 댓글란 구현 -->
<style type="text/css">

	.replyText{
		width: 1000px;
		height: 90px;
		resize: none;
		margin-bottom: 10px;
		font-size: 15px;
		
		font-family: sans-serif;
	}
	
	.WriterInfo{
	
		display: flex;
		gap: 15px; /* 간격 */
		justify-content: flex-start;
		align-items: center;
		
		margin-top: 15px;
		
		font-size: 14px;
		color: #888;
	}
	
	.replyList{

		border-bottom: 2px solid #E1E1E1;
		padding-bottom: 15px;
		margin-top: 10px;
		
		white-space:pre; 
	}
	
	.WritedRyply{

		
		min-height: 100px; 
		margin-bottom: 30px;
		margin-top: 10px;
	}
	
	.reply form{
		position:relative;

	}
	.inputBtn{
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
	.reply{
		display: flex;
	}
	
	#writerRe{

		color: #493D26; 
		font-weight: 560; 
		font-size: 15px;
	}
	
	#modifyFin{
		float: right;
	}
</style>
<script type="text/javascript">

    function validateForm() {
        var replyText = document.getElementById('replyText').value;//textarea.value 저장
        
        // 입력값이 비어 있을 경우
        if (replyText == "") {
            alert("댓글을 입력해주세요.");
            return false; // 폼 제출을 막음
        }else{
	        // 입력값이 있으면 폼을 제출
	        return true;
        }
    }
    
    
    function updateReply(replyIndex,brdIndexInt) {
        // 해당 replyIndex 값을 가진 고유요소를 찾아 텍스트 값을 가져옴
        var replyText = document.getElementById("replyList_" + replyIndex).innerText;
        
        var replyinput = document.getElementById("replyList_" + replyIndex);
        
        // 텍스트박스를 동적으로 생성하고 기존 댓글 내용을 넣음
/*         replyinput.innerHTML = '<textarea id="replyText" name="replyTextStr">' 
        						+ replyText 
        						+ '</textarea>'; */
						
        // 수정 form 태그 실행
        replyinput.innerHTML = '<div class="reply">'        					 
        					+ '<form id="updateForm" action="/Kdessert/board/updateReply" method="post">'
                             + '<textarea class="replyText" name="replyEditStr" style="width: 1000px; height: 100px;">' + replyText + '</textarea>'  // 텍스트 수정
                             + '<input type="hidden" name="replyIndexInt" value="' + replyIndex+'">'
                             + '<input type="hidden" name="brdIndexInt" value="' + brdIndexInt+'">'
                             + '<input id="modifyFin" class="inputBtn" type="submit" value="수정 완료">' // 수정 완료 버튼
                             + '</form>';
    
    }
</script>
	
	<!-- 댓글 목록 -->
<div>
	
	<c:forEach var="reply" items="${boardreplyList}">
		<input type="hidden" name="brdIndexInt" value="${boardDto.brdIndexInt}">
		<input type="hidden" name="replyIndexInt" value="${reply.replyIndexInt}">
		<div class="WritedReply">
			<div class="WriterInfo">
		
				<span id="writerRe">
					<c:if test="${empty reply.memberDto.memNameStr}"> 
					(알 수 없는 회원)
					</c:if>
					${reply.memberDto.memNameStr}
				</span>
				<span>작성일: ${reply.replyCreDate}</span>
				<c:if test="${not empty reply.replyCorrDate}"> 
					<span>수정일: ${reply.replyCorrDate}</span>
				</c:if>
				
				
				<c:if test="${reply.memIndexInt==sessionScope.member.memIndexInt}"> 
					<%-- <a href="/Kdessert/board/updateReply?brdIndexInt=${boardDto.brdIndexInt}&replyIndexInt=${reply.replyIndexInt}">수정</a> --%>
					<a onclick="updateReply(${reply.replyIndexInt},${boardDto.brdIndexInt})">수정</a>
				</c:if>
				<c:if test="${reply.memIndexInt==sessionScope.member.memIndexInt || member.getMemAdmCheckInt() == 1}"> 
					<a href="/Kdessert/board/deleteReply?brdIndexInt=${boardDto.brdIndexInt}&replyIndexInt=${reply.replyIndexInt}">삭제</a>
				</c:if>
			</div>
			
			<div class="replyList" id="replyList_${reply.replyIndexInt}">${reply.replyTextStr}</div>
		</div>
	</c:forEach>
	
	<!-- 댓글 작성부분 -->
	<h3>댓글 쓰기</h3>
	<div class= "reply">
		<form action="/Kdessert/board/freeboarddetail" method="post" onsubmit="return validateForm()"><!-- validateForm() 반환값 영향을 받음(return 없으면 반환값(return) 무시) -->
			<input type="hidden" name="memIndexInt" value="${sessionScope.member.memIndexInt}">
			<input type="hidden" name="brdIndexInt" value="${boardDto.brdIndexInt}">
			<textarea class= 'replyText' name="replyTextStr"></textarea>
			<input class = 'inputBtn' type="submit" value="등록">
		</form>
	</div>
</div>
</html>