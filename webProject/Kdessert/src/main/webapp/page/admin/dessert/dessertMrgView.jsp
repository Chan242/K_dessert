<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자페이지 | 다과 관리</title>

<style>
body {
	width: 100%;
	margin: 0px;
	overflow-y: scroll;
}

#wrap {
	width: 1895px;
}

table, tr, th, td {
	border-bottom: 1px solid #BEBEBE;
	border-collapse: collapse;
}

table {
	border-top: 2px solid black;
	margin: auto;
	width: 1200px;
}

th {
	background-color: #F5F5F5;
	text-align: left;
}

th, td {
	padding: 15px;
}

#sidebar {
	float: left;
}

.div_search {
	text-align: right;
	float: right;
}

#search_text {
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
	margin-right: 10px;
}

#search_submit {
	padding: 5px 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

#search_submit:hover {
	background-color: #0056b3;
}

#table_info {
	margin-right: auto;
	width: 1200px;
}

#content h2 {
	width: 1200px;
	margin-left: 1050px;
}

.pagination {
	text-align: center;
}

#div_pagBtn {
	gap: 10px;
}

#index {
	width: 10px;
}

#addBtn {
	width: 100px;
	float: left;
	margin: auto;
}
</style>

<script type="text/javascript">

		function confirmSave() {
		return confirm("삭제하시겠습니까?");
}
</script>


</head>

<body>

	<%-- 공통 헤더 포함 --%>
	<div id="wrap">
		<jsp:include page="/page/admin/commPage/Adm_Header.jsp" />
		<div>
			<div id="sidebar">
				<jsp:include page="/page/admin/commPage/Category_Mgr.jsp" />
			</div>
			<div id="content">
				<div>
					<h2>다과 관리</h2>
					<!-- 검색창 -->
					<table id="table_info">
						<tr>
							<th colspan="6"></th>
						</tr>
						<tr>
							<td colspan="6">
							  <div id="addBtn">
									<button
										onclick="location.href='/Kdessert/page/admin/RegisterDessert'">신규등록</button>
								</div>
								<div class="div_search" style="width: 300px;">
									<form action="./search" method="get">
										<input id="search_text" name="searchText" type="text"
											placeholder="다과명, 다과번호를 입력해 주세요."> <input
											id="search_submit" type="submit" value="검색">
									</form>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="col" style="width: 150px;">다과번호</th>
							<th scope="col" style="width: 150px;">다과명</th>
							<th scope="col" style="width: 160px;">공개여부</th>
							<th scope="col" style="width: 170px;">조회수</th>
							<th scope="col" style="width: 170px">삭제</th>
							<th scope="col" style="width: 170px">수정</th>
						</tr>
						<tbody>
							<tr id="index">
								<td style="width: 10px;">1</td>
								<td>정과</td>
								<td>공개</td>
								<td>1</td>
								<td><button onclick="deletDagwa(1)">삭제</button></td>
								<td><button
										onclick="location.href='/Kdessert/page/admin/DaquaList/modify'">수정</button></td>
								</tr>
							<tr>
								<td>2</td>
								<td>밤양갱</td>
								<td>비공개</td>
								<td>2</td>
								<td><button class="action-btn" onclick="deletDagwa(2)">삭제</button></td>
								<td><button
										onclick="location.href='/Kdessert/page/admin/DaquaList/modify'">수정</button></td>
							</tr>
						</tbody>
					</table>

					<!-- 페이지네이션 -->
					<div class="pagination">
						<a href="#">&lt;</a> <a href="#">1</a> <a href="#">2</a> <a
							href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">&gt;</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>