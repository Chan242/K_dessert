<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<style type="text/css">
	
	
	#wrap {
		width:1920px;
	}
	
	.display_inline {
		display: inline-block;
	}
	
	#ul_signUp_findAccount {
		padding: 0px;
		text-align: center;
	}

	#ul_signUp_findAccount a {
		text-decoration: none;  /* a태그 밑줄 제거 */
		color: black;
	}
	
	#div_loginForm {
		width: 400px;
		margin: auto;
		margin-top: 100px;
	}
	
	.input_style {
		padding: 10px;
		font-size: 16px;
		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;
		width: 300px;
		margin-top: 3px;
		
    }
	
	#div_center, h1 {
	
		text-align: center;
	
	}
	
	.btn_style {
		cursor: pointer;
		
		width: 300px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: #64473E;
		color: white ;
		font-size: 16px;
		
		margin-top: 30px;
	}		
	

</style>

</head>

<body>

	<div id="wrap">
		
		<jsp:include page="./Auth_Header.jsp"/>
		
		<div id="container">
		
			<div id="div_loginForm">
				<h1>로그인</h1>
			
				<form action="./login" method="post" >
				
					<div id="div_center">
					
					<input class="input_style" type="text" name="id" placeholder="아이디" value="">
					<br>
					<input class="input_style" type="password" name="password" placeholder="비밀번호" value="" value="">
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
	
	</div>

</body>

</html>