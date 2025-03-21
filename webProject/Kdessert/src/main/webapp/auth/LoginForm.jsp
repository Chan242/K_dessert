<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<style type="text/css">

	body { 
	width: 100%;
	margin: 0px;
	overflow-y:scroll;  
	}
	
	#wrap {
		overflow-x: hidden;
		min-width: 1340px;
	}
	
	#container {
		width: 1280px;
		height: 300px;
		margin: 0 auto;
		padding-top: 100px;
		padding-bottom: 200px;
	}
	
	.display_inline {
		display: inline-block;
	}
	
	#container_inner {
		width: 400px;
		margin: auto;
	}
	
	.input_style {
		padding: 10px;
		font-size: 16px;
		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;
		width: 250px;
		margin-top: 3px;
		
    }
	
	#div_loginForm, h1 {
		text-align: center;
		margin-top: 0px;
	}
	
	.btn_style {
		cursor: pointer;
		
		width: 280px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: #64473E;
		color: white ;
		font-size: 16px;
		
		margin-top: 20px;
	}		
	
	#ul_signUp_findAccount {
		padding: 0px;
		text-align: center;
	}

	#ul_signUp_findAccount a {
		text-decoration: none;  /* a태그 밑줄 제거 */
		color: black;
	}
	
	

</style>

</head>

<body>

	<div id="wrap">
		
		<jsp:include page="./Auth_Header.jsp"/>
		
		<div id="container">
		
			<div id="container_inner">
				<h1>로그인</h1>
			
				<form action="./login" method="post">
				
					<div id="div_loginForm">
					
					<input class="input_style" type="text" name="id" placeholder="아이디" value="" required>
					<br>
					<input class="input_style" type="password" name="password" placeholder="비밀번호" value="" required>
					<br>
						<input class="btn_style" type="submit" value="로그인">
					</div>
				
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
	
		<jsp:include page="../page/member/commPage/Mem_Footer.jsp"/>
		
	</div>

</body>

</html>