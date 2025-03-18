<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	

#container {
	margin: auto;
	width: 1200px;
	height: 1200px;

}

	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
		margin: auto;
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
	}

	th, td {
		padding: 15px;
	}
	
	a {
    text-decoration: none; /* 밑줄 해제 */
    color: inherit;        /* 텍스트 색상 상속 */
    cursor: pointer;       /* 클릭 커서 유지 */
}
	
</style>

<script type="text/javascript">


</script>
</head>
<body>

	<jsp:include page="../commPage/Adm_Header.jsp"></jsp:include>

	<div style="float: left;">
		<jsp:include page="../commPage/Category_Mgr.jsp"></jsp:include>
	</div>
	<div id="container">
		<div>
			<h1 style="text-align: center;">검색결과</h1>
			<table>
				<tr>
					<td><button style="float: left; margin: auto;"
				onclick="location.href='/Kdessert/admin/product/add'">신규등록</button>
					</td>
					<td colspan="6">
						<form action="query" method="get" style="float: right;">
							<input type="search" name="search" placeholder="검색어 입력창" value="">
							<input type="hidden" name="no" value="1">
							<input type="submit" value="검색">
						</form>
					</td>
				</tr>
							
				<tr>
					<td>제품번호</td>
					<td>제품명</td>
					<td>제품가격</td>
					<td>재고</td>
					<td>공개여부</td>
					<td>수정</td>
					<td>삭제</td>

				</tr>
				<c:forEach var="productDto" items="${productList}">
					<tr>
						<td>${productDto.getProIndexInt()}</td>
						<td><a href="./select?no=${productDto.getProIndexInt()}">${productDto.getProNameStr()}</a></td>
						<td>${productDto.getProPriceInt()}</td>
						<td>${productDto.getProStockInt()}</td>
						<td><c:choose>
								<c:when test="${productDto.getProOpenInt() == 0}">공개</c:when>
								<c:when test="${productDto.getProOpenInt() == 1}">비공개</c:when>
								<c:otherwise>에러: ${productDto.getProOpenInt()}</c:otherwise>
							</c:choose></td>
						<td><a href="./update?no=${productDto.getProIndexInt()}">수정</a></td>
						<td><a href="./delete?no=${productDto.getProIndexInt()}">삭제</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
			<!-- 페이지네이션 네비게이션 -->
		<div style="text-align: center;">
		    <!-- 이전 페이지 -->
		    <c:if test="${no > 1}">
		        <a href="./query?search=${param.search}&no=${no - 1}"><</a>
		    </c:if>
		
		    <!-- 페이지 번호 -->
		    <c:forEach var="i" begin="${start}" end="${maxEnd}">
		        <a href="./query?search=${param.search}&no=${i}">${i}</a>
		    </c:forEach>
		
		    <!-- 다음 페이지 -->
		    <c:if test="${no < totalPageInt}">
		        <a href="./query?search=${param.search}&no=${no + 1}">></a>
		    </c:if>
		</div>
		
		
		
	</div>
</body>
</html>