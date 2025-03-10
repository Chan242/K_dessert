<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,
		initial-scale=1.0">
<title>다과 관리 - 수정하기</title>

<style type="text/css">

/* body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f8f8f8;
} */

.container {
	display: flex;
}

.sidebar {
	width: 200px;
	background-color: #5A3A2E;
	color: white;
	padding: 20px;
	height: 100vh;
}


.sidebar h2 {
	font-size: 18px;
	margin-bottom: 20px;
}

.sidebar ul {
	list-style: none;
	padding: 0;
}

.sidebar ul li {
	margin: 10px 0;
}

.sidebar ul li a {
	color: white;
	text-decoration: none;
}

.content {
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	padding: 20px;
	background-color: white;
	width: calc(100% - 220px);
	margin-left: 220px;
}

.form-container {
	max-width: 400px;
	background: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.form-group {
	margin-bottom: 15px;
}	

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input, textarea, select {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.btn {
	background-color: #5A3A2E;
	color: white;
	border: none;
	padding: 10px;
	cursor: pointer;
	border-radius: 4px;
}

.btn:hover {
	background-color: #4A2D23;
}

</style>
</head>

<body>
	<div style="float: left;">
		<jsp:include page="/page/admin/commPage/Category_Mgr.jsp"></jsp:include>
	</div>
	
	<div class="content">
	<h2>수정하기</h2> 
		<div class="form-group">
		<label for="id">다과번호</label>
		<input type="text" id="id" name="id"> 
	</div>
		<div class="form-group">
		<label for="name">다과명</label>
		<input type="text" id="name" name="name"> 
	</div>
	<div class="form-group">
	<label for="desc">설명</label>
	<textarea id="desc" name="desc"></textarea>
</div>
	<div class="form-group">
		<label for="image">이미지</label>
		<input type="file" id="image" name="image"> 	
</div>
	<div class="form-group">
	<label>공개여부</label>
	<input type="radio" name="public" value="yes">예
	<input type="radio" name="public" value="no">아니오
</div> 
	 <div class="form-group">
          <label for="stock">수량</label>
          <input type="number" id="stock" name="stock">
     </div>
		   <button class="btn">저장하기</button>		
	</div>

</body>
</html>