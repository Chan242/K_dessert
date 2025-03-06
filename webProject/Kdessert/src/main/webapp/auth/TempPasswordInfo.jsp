<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">

	#wrap {
		width:1920px;
	}

	#div_info {
		width: 300px;
		margin: auto;
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

	<div id="div_info">
	
		<h2 style="text-align: center;">임시 비밀번호를<br>메일로 전송했습니다</h2>
	
		<div id="div_btn">
			<input class="btn_style" type="button" onclick="location.href='./login'" value="로그인 화면으로 돌아가기">
		</div>

	</div>
	
</div>

</body>


</html>