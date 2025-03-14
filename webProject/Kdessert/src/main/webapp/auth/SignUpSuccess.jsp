<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원가입 완료</title>

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
	
	.btn_style {
		cursor: pointer;
		
		width: 300px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: white;
		color: #7B7B7B;
		font-size: 16px;
	}	
	
	#container_inner {
		text-align: center;
	}
	

</style>

</head>

<body>

	<div id="wrap">
	
		<jsp:include page="./Auth_Header.jsp"/>
		
		<div id="container">
			<div id="container_inner">
				<h1>가입이 완료되었습니다</h1>
				<input class="btn_style" type="button" onclick="location.href='./login'" value="로그인 화면으로 돌아가기">
			</div>
		</div>
		
		<jsp:include page="../page/member/commPage/Mem_Footer.jsp"/>
		
	</div>
	
</body>
</html>