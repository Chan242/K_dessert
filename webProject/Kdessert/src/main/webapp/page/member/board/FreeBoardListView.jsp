<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>자유게시판 목록</title>

<script type="text/javascript">
	
</script>

<style type="text/css">
	#wrap{/* 푸터 맨 하단으로 고정할 수 있게 추가 */
		min-height: 100%; /* 최소높이 지정 */
	
	}

	#listBox {
		width: 1000px;
		display: 0;
		margin: auto;

	}
	
	#listBox h4{
		border-top: 2px solid black;
		border-bottom: 1px solid gray;

		display: flex;/* a 태그 안에 flex 적용 */
		justify-content: space-between;
		align-items: center; /* 수직 정렬 */
		padding-top: 10px;
		padding-bottom: 10px;
		margin: 0;
	}
	
	.boardListbtn{
		font-size: 12pt;


		width: 80px;
		height: 28px;
		
		align-content:center;
		align-items : center;
		
		color: white;
		background-color: #64473E;
		border: none;
		
	}
	
	#writeSearchBox{
		width: 200px;
		height: 23px;
		
	}
	
	#boardSearch{
		float: right;
	}
	
	#topInter{
		margin-bottom: 10px;
		margin-top: 70px;
		padding: 10px;
	}
	
</style>

</head>

<body>
	<div id="wrap"><!--  푸터 맨 하단으로 고정할 수 있게 wrap div 추가  -->
	
		<jsp:include page="../commPage/Mem_Header.jsp"/>
		<jsp:include page="../commPage/Category_Main.jsp"/>
		
		
		<div id="listBox">
			<div id="topInter">
				<input class="boardListbtn" type="button" value="글쓰기">
				<div id="boardSearch">
					<input id="writeSearchBox" type="text" value="검색창">
					<input class="boardListbtn" type="button" value="검색">
				</div>
			</div>
			<h4>
				<span style="margin-left:200px;">타이틀</span>
				<span style="margin-right:40px">
				<span class="writer">작성자</span>
				<span class="writer">작성일</span>
				<span class="writer">조회수</span>
				</span>
			</h4>
			<%for(int i=0; i<6; i++) {%>
				<jsp:include page="./BoardLine.jsp"/>
		
			<%} %>
		</div>
		
		<jsp:include page="../commPage/Mem_Footer.jsp"/>
	</div>
</body>
</html>