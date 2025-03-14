<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">

	body { 
	width: 100%;
	margin: 0px;
	}
	
	#wrap {
		overflow-x: hidden;
		min-width: 1340px;
	}
	
	#container {
		width: 1280px;
		margin: 0 auto;
		margin-top: 100px;
	}

	#container p, #container h2 {
		text-align: center;
		margin: 5px;
		margin-bottom: 30px;
	}
	
	#div_btn {
		text-align: center;
	}
	
	.btn_style {
		cursor: pointer;
		
		width: 250px;
		height: 50px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: white;
		color: #7B7B7B;
		font-size: 16px;
	}	
	
	
</style>

<script type="text/javascript">
	
	
</script>

</head>

<body>

<div id="wrap">

	<jsp:include page="./Auth_Header.jsp"/>

	<div id="container">
	
		<h2>임시 비밀번호 발급</h2>
		<p style="margin-bottom: 5px;">회원님의 임시 비밀번호는</p>
		<p>${tempPwd} 입니다.</p>
	
		<div id="div_btn">
			<input class="btn_style" type="button" onclick="location.href='./login'" value="로그인 화면으로 돌아가기">
		</div>

	</div>
	
</div>

</body>


</html>