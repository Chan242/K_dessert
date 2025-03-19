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
	body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	#listWrap {
		width: 1000px;
		margin: auto;
		/* 푸터 맨 하단으로 고정할 수 있게 추가 */
		min-height: 800px; /* 최소높이 지정 */
		margin-bottom: 300px;
	}
	
	.table_info {
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

	    max-width: 100px; /* 최대 너비 설정 */
	    white-space: nowrap; /* 한 줄로 유지 */
	    overflow: hidden; /* 넘치는 글자 숨김 */
	    text-overflow: ellipsis; /* 말줄임표(...) 추가 */
	}
	
	/* 페이지 버튼 */
	#div_pageBtn { /* 전체 페이지 버튼란 */
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    gap: 10px; /* 버튼 간격 */
	    margin-top: 20px;
	}
	
	.active{/* 현재 페이지 */
		text-decoration: underline;
	    font-weight: bold;
	
	}
	
	.pageBtn{/* 선택x 페이지 */
		text-decoration: none;
	    color: black;
	    font-size: 16px;
	    padding: 5px 10px;
	    border-radius: 5px;
	    transition: 0.3s;
	}
	
	.pageBtn:hover{/* 마우스 올릴 경우 */
		background-color: #f0f0f0;
	}

	#select_board {
		border-bottom: 2px solid #64473E;
	}

</style>

</head>
<jsp:useBean id="boardDao"
	scope="session"
	class="user.board.main.FreeBoardDao"/>
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

<!-- 		공지용 -->
		<table class="table_info">
			<thead id="colum">
				<tr>
					<th scope="col" style="max-width:690px;">타이틀</th>
					<th scope="col" style="width: 130px;">작성자</th>
					<th scope="col" style="width: 100px;">작성일</th>
					<th scope="col" style="width: 80px;">조회수</th>
				</tr>
			</thead>
			<tbody id = 'content'>
				<c:forEach var="boardNotice" items="${boardNotiList}">

						<tr onclick="location.href='/Kdessert/board/freeboarddetail?brdIndexInt=${boardNotice.brdIndexInt}' "
					                style="color: #5c5c0a; background-color: #f5f5dc ">
							<td>
								<span class='title' style="margin-left: 40px;"> 🥮${boardNotice.brdSubjectStr}</span>
							</td>
							<td class='boardLists' id='writer'>
								<c:if test="${empty boardNotice.memberDto.memNameStr}"> 
									(알 수 없는 회원)
								</c:if>
								 ${boardNotice.memberDto.memNameStr}
							</td>
							<td class='boardLists'>${boardNotice.brdCreDate}</td>
							<td class='boardLists'>${boardNotice.brdViewInt}</td>
						</tr>
				</c:forEach>
				
				<!-- 일반회원용 -->
				<c:forEach var="board" items="${boardList}">

						<tr onclick="location.href='/Kdessert/board/freeboarddetail?brdIndexInt=${board.brdIndexInt}'  "
							<c:if test="${board.brdNoticeInt}=1">
								style="color: #5c5c0a; background-color: #f5f5dc "
							</c:if>
						>
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


		
		<!------------ 페이지 버튼 ------------>
		<div id="div_pageBtn">
					<!-- 1보다 클 경우 이전버튼(<) 생성 -->
		    <c:if test="${pageNum > 1}">
				<!-- 현재 페이지보다 1 작은 페이지로 이동+ pageSize로 한 페이지당 항목의 수 유지-->
				<!-- &lt;는 이전버튼 -->
		        <a href="?searchStr=${param.searchStr}&pageNum=${pageNum - 1}&pageSize=${pageSize}" class="pageBtn">&lt;</a>
		    </c:if>
				
				<!-- 여러가지 경우의 수 생성 -->
				<c:choose>
				    <%-- 페이지가 1개일 경우, 1번 페이지만 표시 --%>
				    <c:when test="${totalPage == 1}">
				        <span class="pageBtn active">1</span>
				    </c:when>
				    <c:when test="${totalPage <= 4}">
				        <%-- 전체 페이지가 4 이하일 경우 --%>
				        <c:forEach begin="1" end="${totalPage}" var="i">
				            <c:choose>
				                <%-- i가 현재 선택중인 페이지의 수치라면 버튼 비활성(span) --%>
				                <c:when test="${i == pageNum}">
				                    <span class="pageBtn active">${i}</span>
				                </c:when>
				                <%-- 그 외의 경우 a태그로 페이지 이동 활성화 --%>
				                <c:otherwise>
				                    <a href="?searchStr=${param.searchStr}&pageNum=${i}&pageSize=${pageSize}" class="pageBtn">${i}</a>
				                </c:otherwise>
				            </c:choose>
				        </c:forEach>
				    </c:when>
				    <c:when test="${totalPage >= 5}">
				        <%-- 전체 페이지가 5개 이상일 때 --%>
				        <c:choose>
				            <c:when test="${pageNum == 1}">
				                <%-- 페이지 번호가 1일 때 (최대 5개까지) --%>
				                <c:forEach begin="1" end="5" var="i">
				                    <c:choose>
				                        <%-- i가 현재 선택중인 페이지의 수치라면 버튼 비활성(span) --%>
				                        <c:when test="${i == pageNum}">
				                            <span class="pageBtn active">${i}</span>
				                        </c:when>
				                        <%-- 그 외의 경우 a태그로 페이지 이동 활성화 --%>
				                        <c:otherwise>
				                            <a href="?searchStr=${param.searchStr}&pageNum=${i}&pageSize=${pageSize}" class="pageBtn">${i}</a>
				                        </c:otherwise>
				                    </c:choose>
				                </c:forEach>
				            </c:when>
				            <c:when test="${pageNum == 2}">
				                <%-- 페이지 번호가 2~6까지(총 5개) --%>
				                <c:forEach begin="1" end="5" var="i">
				                    <c:choose>
				                        <c:when test="${i == pageNum}">
				                            <span class="pageBtn active">${i}</span>
				                        </c:when>
				                        <c:otherwise>
				                            <a href="?searchStr=${param.searchStr}&pageNum=${i}&pageSize=${pageSize}" class="pageBtn">${i}</a>
				                        </c:otherwise>
				                    </c:choose>
				                </c:forEach>
				            </c:when>
				            <c:when test="${pageNum == totalPage - 1}">
				                <%-- 현재 페이지가 전체페이지수-1일 때 (마지막 번호-1 일때) --%>
				                <c:forEach begin="${totalPage - 4 < 1 ? 1 : totalPage - 4}"
				                    end="${totalPage}" var="i">
				                    <c:choose>
				                        <c:when test="${i == pageNum}">
				                            <span class="pageBtn active">${i}</span>
				                        </c:when>
				                        <c:otherwise>
				                            <a href="?searchStr=${param.searchStr}&pageNum=${i}&pageSize=${pageSize}" class="pageBtn">${i}</a>
				                        </c:otherwise>
				                    </c:choose>
				                </c:forEach>
				            </c:when>
				            <c:when test="${pageNum == totalPage}">
				                <%-- 페이지 번호가 마지막일 때 (최대 5개까지) --%>
				                <c:forEach begin="${totalPage - 4 < 1 ? 1 : totalPage - 4}"
				                    end="${totalPage}" var="i">
				                    <c:choose>
				                        <c:when test="${i == pageNum}">
				                            <span class="pageBtn active">${i}</span>
				                        </c:when>
				                        <c:otherwise>
				                            <a href="?searchStr=${param.searchStr}&pageNum=${i}&pageSize=${pageSize}" class="pageBtn">${i}</a>
				                        </c:otherwise>
				                    </c:choose>
				                </c:forEach>
				            </c:when>
				            <c:otherwise>
				                <%-- 그 외 페이지 번호가 중간일 때 --%>
				                <c:forEach begin="${pageNum - 2 < 1 ? 1 : pageNum - 2}"
				                    end="${pageNum + 2 > totalPage ? totalPage : pageNum + 2}"
				                    var="i">
				                    <c:choose>
				                        <c:when test="${i == pageNum}">
				                            <span class="pageBtn active">${i}</span>
				                        </c:when>
				                        <c:otherwise>
				                            <a href="?searchStr=${param.searchStr}&pageNum=${i}&pageSize=${pageSize}" class="pageBtn">${i}</a>
				                        </c:otherwise>
				                    </c:choose>
				                </c:forEach>
				            </c:otherwise>
				        </c:choose>
				    </c:when>
				</c:choose>

				<!-- 다음 페이지 버튼 (>) -->
				<c:if test="${pageNum < totalPage}">
		        <a href="?searchStr=${param.searchStr}&pageNum=${pageNum + 1}&pageSize=${pageSize}" class="pageBtn">&gt;</a>
		    </c:if>
		</div>
				
	</div>


	<jsp:include page="../commPage/Mem_Footer.jsp" />
</body>
</html>