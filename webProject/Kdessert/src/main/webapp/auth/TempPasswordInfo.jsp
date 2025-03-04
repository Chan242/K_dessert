<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">

	#div_info {
		width: 300px;
		margin: auto;
	}
	
	
</style>

<script type="text/javascript">
	
	
</script>

</head>

<body>

	<jsp:include page="./Auth_Header.jsp"/>

	<div id="div_info">
	
		<h2>임시 비밀번호를<br>메일로 전송했습니다</h2>
	
		<input id="btn_go_Login"type="button" onclick="location.href='./login'" value="로그인 페이지로 바로가기">

	</div>

</body>


</html>