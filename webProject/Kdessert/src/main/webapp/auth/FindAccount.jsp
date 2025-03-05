<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">
	
	#container {
		width:860px;
		margin: auto;
	}
	
	.form_div {
		width: 200px;
	}
	
	.find_div {
		width: 350px;
	}
	
	#find_id {
		float: left;
	}
	
	#find_pwd {
		float: right;
	}
	
	
</style>

</head>

<body>

	<jsp:include page="./Auth_Header.jsp"/>
	
	<div id="container">
	
		<div id="find_id" class="find_div">
			<p>아이디 찾기</p>
			<form action="./findid" method="get">
				<div class="form_div">
					<input type="text" name="name" placeholder="이름 입력">
					<input type="email" name="email" placeholder="이메일 입력">
				</div>
				<input type="submit" value="아이디 찾기"> 
			</form>
		</div>
		
		<div id="find_pwd" class="find_div">
			<p>비밀번호 찾기</p>
			<form action="/findpwd" method="get">
				<div class="form_div">
					<input type="email" name="email" placeholder="이메일 입력">
				</div>
				<input type="button" onclick="location.href='./TempPasswordInfo.jsp'" value="임시비밀번호 발송">
			</form>
		</div>
		
		</div>

</body>

<script type="text/javascript">

</script>

</html>