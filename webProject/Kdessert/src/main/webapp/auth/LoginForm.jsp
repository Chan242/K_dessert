<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<style type="text/css">

	.display_inline {
		display: inline-block;
	}
	
	#ul_signUp_findAccount {
		padding: 0px;
	}

</style>

</head>

<body>
	
	<h1>로그인</h1>
	
	<form action="./login" method="post" >
	
		<input type="text" name="id" placeholder="아이디" value="">
		<br>
		<input type="password" name="password" placeholder="비밀번호" value="" value="">
		<br>
		<input type="submit" value="로그인">
	
	</form>
	
	<ul id="ul_signUp_findAccount">
		<li class="display_inline">
			<a href="./signup">회원가입</a>
		</li>
		<li class="display_inline"> ｜ </li>
		<li class="display_inline">
			<a>아이디/비밀번호 찾기</a>
		</li>
	</ul>

</body>

</html>