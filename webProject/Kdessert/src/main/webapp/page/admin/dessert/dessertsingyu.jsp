<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자 | 신규 등록</title>
<style>
#wrap {
	width: 1300px;
	margin: auto;
}

.tableType {
	background-color: lightgray;
	width: 200px;
	border: 1px solid black;
	text-align: center;
}

.tableControl {
	width: 500px;
}

table {
	border-top: 5px solid black;
	width: 100%;
	margin: auto;
	min-height: 800px; /* 최소 높이 지정 */
	border-collapse: collapse;
}

td {
	padding: 10px;
	border: 1px solid #ccc;
}

#div_content {
	width: 900px;
	margin-left: 50px;
	float: left;
}

#sidebar {
	float: left;
}

input, textarea, select {
	width: 95%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.btn {
	margin-top: 15px;
	padding: 10px 15px;
	background-color: #28a745;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn {
	margin-top: 30px;
	width: 100px;
	margin: 50px;
	background-color: orange;
}
</style>
</head>
<body>

	<div id="wrap">
		<jsp:include page="/page/admin/commPage/Adm_Header.jsp" />

		<div id="container">
			<div id="sidebar">
				<jsp:include page="/page/admin/commPage/Category_Mgr.jsp" />
			</div>

			<div id="div_content">
				<h2>다과 신규 등록</h2>
				<form action="/Kdessert/RegisterDessertServlet" method="post"
					enctype="multipart/form-data">
					<table border="1">
						<tr>
							<td class="tableType">다과명</td>
							<td class="tableControl"><input type="text" name="name"
								required></td>
						</tr>
						<tr>
							<td class="tableType">설명</td>
							<td class="tableControl"><textarea name="desc"></textarea></td>
						</tr>
						<tr>
							<td class="tableType">이미지</td>
							<td class="tableControl"><input type="file" name="image"></td>
						</tr>
						<tr>
							<td class="tableType">공개여부</td>
							<td class="tableControl">
								<div style="display: inline-block; margin-right: 10px;">
									<input type="radio" name="public" value="yes" id="yes"
										class="radio-btn"> <label for="yes"
										style="display: inline; margin-left: 5px;">공개</label>
								</div>
								<div style="display: inline-block;">
									<input type="radio" name="public" value="no" id="no"
										class="radio-btn"> <label for="no"
										style="display: inline; margin-left: 5px;">비공개</label>
								</div>
							</td>
						</tr>

						<tr>
							<td class="tableType">태그</td>
							<td class="tableControl"><input type="text" name="tag"></td>
						</tr>
					</table>

					<button class="btn" type="submit"
						onclick="location.href='/Kdessert/page/admin/RegisterDessertServlet/modify'">등록하기</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
