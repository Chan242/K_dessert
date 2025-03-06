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
	
	#container {
		width:860px;
		margin: auto;
	}
	
	.div_input_class {
	    display: flex;
	    justify-content: center;  /* 가로로 가운데 정렬 */
	    align-items: center;      /* 세로로 가운데 정렬 */
	    height: 100px;            /* 부모 요소의 높이를 설정 */
	    flex-direction: column;   /* 자식 요소들을 세로로 배치 */
	}
	
	.div_find_class {
		width: 350px;
		text-align: center;
		margin-top: 30px;
	}
	
	#find_id {
		float: left;
	}
	
	#find_pwd {
		float: right;
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
	
	
	.btn_style {
		cursor: pointer;
		
		width: 200px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: #64473E;
		color: white;
		font-size: 16px;
		
		margin-top: 30px;
	}
	
	
	
</style>

</head>

<body>

	<div id=wrap>
	
		<jsp:include page="./Auth_Header.jsp"/>
		
		<div id="container">
		
			<div id="find_id" class="div_find_class">
				<p>아이디 찾기</p>
				<form action="./findid" method="get">
					<div class="div_input_class">
						<input class="input_style" type="text" name="name" placeholder="이름">
						<input class="input_style" type="email" name="email" placeholder="이메일">
					</div>					
					<input class="btn_style" type="submit" value="아이디 찾기"> 
				</form>
			</div>
			
			<div id="find_pwd" class="div_find_class">
				<p>비밀번호 찾기</p>
				<form action="/findpwd" method="get">
					<div class="div_input_class">
						<input class="input_style" type="email" name="email" placeholder="이메일">
					</div>
					<input class="btn_style" type="button" onclick="location.href='./TempPasswordInfo.jsp'" value="임시비밀번호 발송">
				</form>
			</div>
			
			</div>
	</div>
	
</body>

<script type="text/javascript">

</script>

</html>