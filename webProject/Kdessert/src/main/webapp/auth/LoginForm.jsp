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

	#ul_signUp_findAccount a {
		text-decoration: none;  /* a태그 밑줄 제거 */
		color: black;
	}
	
	#div_loginForm {
		width: 300px;
		margin: auto;
		margin-top: 100px;
	}
	

</style>

</head>

<body>
	
	<jsp:include page="./Auth_Header.jsp"/>
	
	<div id="container">
	
		<div id="div_loginForm">
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
					<a href="./FindAccount.jsp">아이디/비밀번호 찾기</a>
				</li>
			</ul>
	
		</div>
	
	</div>

</body>

</html>