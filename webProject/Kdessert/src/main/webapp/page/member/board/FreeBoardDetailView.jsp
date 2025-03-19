<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>고객 게시판 | ${boardDto.getBrdSubjectStr()}</title>

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

	}
	
	#boardWrap a{
		
		text-decoration: none;
		color: #888;
	}

	/* 제목 스타일 */
	#title {

		padding: 20px;
		padding-bottom: 0px;
		color: #493D26;
	}
	
	/* 상단 헤더 */
	#head {
		display: flex;
		align-items: center; /* 수직 가운데 정렬 */
		
		font-size: 15px; /* 폰트 크기 조정 */
		color: #555; /* 색상 */
	}

	/* 작성자 왼쪽 정렬 */
	.writer {
		flex: 1;
		font-weight: 600;
	}

	/* 작성일, 조회수 오른쪽 정렬 */
	.dateView {
		display: flex;
		gap: 15px; /* 간격 */
		justify-content: flex-end;
	}



	/* 콘텐츠 영역 스타일 */
	#context {
		font-size: 16px;
		line-height: 1.5;
		
		margin: 20px;
		margin-bottom: 70px;
		
		border-bottom: 1px solid gray;
		border-top: 2px solid black;
		
		padding: 20px;
		padding-top: 5px;
	}
	
	#context p{
		min-height: 150px;
		font-size: 14pt;
		white-space:pre-line; 
		overflow-wrap: break-word; /* 넘칠 시 강제로 줄바꿈 */
		
		width: 920px;
		

	}
	
	#contentBtn{
		display: flex;
		gap: 15px; /* 간격 */
		justify-content: flex-end;
		color: #888;
	
	}
	
	/* 제목 넘칠 경우 */
	#subject{
		display: block; 
	    max-width: 1000px;  /* 최대 너비 설정 */
	    white-space: normal; /* 기본적으로 줄바꿈 허용 */
	    overflow-wrap: break-word; /* 강제로 줄바꿈 */
	    
	    font-size: 30pt;
	    font-weight: 550;
    
	    margin-bottom: 20px;
	}

</style>

</head>

<script type="text/javascript">
function deleteDetail() {
    var replyText = document.getElementById('contentBtn');//textarea.value 저장
    var deleteBtn = replyText.getElementsByTagName('a')[1];
    var findelete = confirm("게시글을 삭제하겠습니까?");
    // 입력값이 비어 있을 경우
    return findelete;
}
</script>

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
			<p id='subject'>${boardDto.getBrdSubjectStr()}</p>
			<div id="head">
				<span class="writer">작성자: 
					<c:if test="${empty boardDto.getMemberDto().memNameStr}"> 
						(알 수 없는 회원)
					</c:if>
					${boardDto.getMemberDto().memNameStr}</span> <!-- 작성자 텍스트 -->
				
				<div class="dateView">
					<c:if test="${not empty boardDto.getBrdCorrDate()}">
						<span>수정일: ${boardDto.getBrdCorrDate()}</span> <!-- 수정일 텍스트 -->
					</c:if>
					<span>작성일: ${boardDto.getBrdCreDate()}</span> <!-- 작성일 텍스트 -->
					<span>조회수: ${boardDto.getBrdViewInt()}</span> <!-- 조회수 텍스트 -->
				</div>
			</div>
		</div>
			<div id="context">
				<p>${boardDto.getBrdTextStr()}</p> <!-- 내용 텍스트 -->
				 <!-- 관리자이거나, 접속 번호와 글쓴이 인덱스 번호가 같을 경우 수정 삭제페이지가 보임-->
				<c:if test="${boardDto.getMemIndexInt()==sessionScope.member.memIndexInt || member.getMemAdmCheckInt() == 1}"> 
					<div id='contentBtn'>
						<a href="./update?brdIndexInt=${boardDto.getBrdIndexInt()}">수정</a>
						<a href="./delete?brdIndexInt=${boardDto.getBrdIndexInt()}" onclick='return deleteDetail()'>삭제</a>
					</div>
				</c:if>
		</div>
		
<!-- 댓글부분 -->
		<h4 style="margin-bottom: 10px;">댓글</h4>
	
		<jsp:include page="./ReplyView.jsp"/>
	</div>
	<jsp:include page="../commPage/Mem_Footer.jsp" />
</body>
</html>
