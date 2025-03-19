<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">

<title>관리자 | 다과관리</title>
<style>

	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}

/* --------------------------------------- */
.tableType {
	background-color: lightgray;
	width: 200px;
	border: 1px solid black;
}

.tableControl {
	width: 500px;
}

table {
	border-top: 5px;
	width: 1200px;
	margin: auto;
	/* 푸터 맨 하단으로 고정할 수 있게 추가 */
	min-height: 800px; /* 최소높이 지정 */

}


#div_content{
  width: 1300px;
  margin-left: 50px;
  float: left;
}

#sidebar {
	float: left;
}

	#select_dessert {
		background-color: white;
		color: #64473E
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
			<h2>수정하기</h2>
			<table border="1">
	
				<tr>
					<td class="tableType">다과번호</td>
					<td><span class="view-count">1</span></td>
				</tr>
				<tr>
					<td class="tableType">다과명</td>
					<td class="tableControl"><input type="text" id="name"
						name="name" required></td>
				</tr>
				<tr>
					<td class="tableType">설명</td>
					<td class="tableControl"><textarea id="desc" name="desc"></textarea></td>
				</tr>
				<tr>
					<td class="tableType">이미지</td>
					<td class="tableControl"><input type="file" id="image"
						name="image"></td>
				</tr>
				<tr>
					<td class="tableType">공개여부</td>
					<td class="tableControl"><div
							style="display: flex; align-items: center;">
							<input type="radio" name="public" value="yes" id="yes"
								class="radio-btn"> <label for="yes"
								class="custom-radio" style="margin-right: 10px;">공개</label> <input
								type="radio" name="public" value="no" id="no" class="radio-btn">
							<label for="no" class="custom-radio">비공개</label>
						</div></td>
				</tr>
				<tr>
					<td class="tableType">태그</td>
					<td class="tableControl"><input type="text" id="tag"
						name="tag"></td>
				</tr>
				<tr>
					<td class="tableType">조회수</td>
					<td class="tableControl"><span class="view-count">10</span></td>
				</tr>
	
			</table>
	
			<button class="btn" type="submit">저장하기</button>
		</div>
	</div>
</div>

</body>
</html>
