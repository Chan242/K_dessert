<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">
	body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	#boardWrap {
		width: 1000px;
		min-height: 800px;
		margin: auto;
		margin-top: 20px;
		margin-bottom: 90px;
		

	}
	
	h1{
		border-bottom: 2px solid black;
		padding: 20px;
	}
	
	h3{
		margin: 10px;
		margin-top: 15px;
	}
	
	.textBox{
		border: 1px solid #adb5bd;
		border-radius: 5px;
		font-size: 17px;
	}
	
	#title{
		width: 1000px;
		height: 30px;
	}
	
	#mainText{
		width: 1000px;
		height: 500px;
		resize: none;
		margin-bottom: 40px;
		
		font-family: sans-serif;
	}
	
	#boardBtn{
		display: flex;
		justify-content: flex-end;
		gap:20px;
	}
	
	.inputBtn{
		width: 100px;
		height: 40px;
		font-size: 17px;
		
	}
	#context{
		margin: auto;
	}
	
	#wrap{
		width: 1920px;
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
<div id="wrap">

	<div id="boardWrap">
		<form action="./update" method="post">
			<div id="inputHead">
				<h1>게시글 수정</h1>
				
				<input type="hidden" name="brdIndexInt" value="${boardDto.brdIndexInt}" />
				<h3>제목</h3>
				<input class="textBox" id='title' type="text" name="brdSubjectStr" 
					value="${boardDto.getBrdSubjectStr()}">
			</div>
			
			<div id="context">
				<h3>본문</h3> <!-- 내용 텍스트 -->
				<textarea class="textBox" id='mainText' name="brdTextStr">${boardDto.getBrdTextStr()}</textarea>
				
				<div id="boardBtn">
					<input class='inputBtn' type="button" value="작성 취소" 
							onclick="history.go(-1)">
					<input class='inputBtn' type="submit" value="수정하기">
				</div>
				
			</div>
		</form>
		
	</div>
</div>

<jsp:include page="../commPage/Mem_Footer.jsp"/>
</body>
</html>
