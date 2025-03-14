<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>로그인 | 계정찾기</title>

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
		margin: 0 auto;
		margin-left: 280px;
	}
	
	#find_pwd {
		float: right;
		margin: 0 auto;
		margin-right: 280px;
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
	
	.btn_style {
		cursor: pointer;
		
		width: 280px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: #64473E;
		color: white;
		font-size: 16px;
		
		margin-top: 20px;
	}
	
	
	
</style>

</head>

<body>

	<div id=wrap>
	
		<jsp:include page="./Auth_Header.jsp"/>
		
		<div id="container">
		
			<div id="find_id" class="div_find_class">
				<h3>아이디 찾기</h3>
				<form action="./findid" method="get">
					<div class="div_input_class">
						<input class="input_style" type="text" name="name" placeholder="이름" required>
						<input class="input_style" type="email" name="email" placeholder="이메일" required>
					</div>					
					<input class="btn_style" type="submit" value="아이디 찾기"> 
				</form>
			</div>
			
			<div id="find_pwd" class="div_find_class">
				<h3>비밀번호 찾기</h3>
				<form action="./findpwd" method="get">
					<div class="div_input_class">
						<input class="input_style" type="text" name="id" placeholder="아이디" required>
<!-- 						<input class="input_style" type="email" name="email" placeholder="이메일" required> -->
					</div>
					<input class="btn_style" type="submit" value="임시비밀번호 발급">
				</form>
			</div>
			
			<div style="clear: both;"></div>
			
			</div>
	
		<jsp:include page="../page/member/commPage/Mem_Footer.jsp"/>
		
	</div>
	
</body>

<script type="text/javascript">

</script>

</html>