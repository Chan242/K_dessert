<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<style type="text/css">

	#wrap {
		width:1920px;
	}

	body {
		margin: 0px;
	}
	
	#div_signup {
		width: 700px;
		padding-bottom: 100px;
		padding-top: 50px;
	}
	
	#div_buttons {
		width: 410px;
	}
	
	#div_signup, #table_in_form, #div_buttons {
		margin: auto;
	}
	

	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
	}

	th, td {
		padding: 15px;
	}
	
	
	input {
		padding: 10px;
		font-size: 16px;
		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;
		width: 300px;
    }
    
    .btn_style{
		width: 200px;
		cursor: pointer;
    }
    
    #btn_cancle {
		background-color: white;
		color: #7B7B7B;
    }
    
    #btn_signUp {
    	background-color: #64473E;
    	color: white;
    }
    


</style>

</head>
<body>

<div id="wrap">

	<jsp:include page="./Auth_Header.jsp"/>

	<div id="div_signup">
	
		<h1 style="text-align: center;">회원가입</h1>
		
		<form action="./signup" method="post">
		
			<table id=table_in_form>
			
				<tr>
					<th scope="row">
						이름
					</th>
					<td>
						<input type="text" name="mname">
					</td>
				</tr>
				<tr>
					<th scope="row">
						아이디
					</th>
					<td>
						<input type="text" name="id">
					</td>
				</tr>
				<tr>
					<th scope="row">
						비밀번호
					</th>
					<td>
						<input type="password" name="password">
					</td>
				</tr>
				<tr>
					<th scope="row">
						비밀번호 확인
					</th>
					<td>
						<input type="password" name="passwordCheck">
					</td>
				</tr>
				<tr>
					<th scope="row">
						이메일
					</th>
					<td>
						<input type="email" name="email">
					</td>
				</tr>
				<tr>
					<th scope="row">
						생년월일
					</th>
					<td>
						<input  type="text" name="birth" placeholder="YYYY-MM-DD">
					</td>
				</tr>
				<tr>
					<th scope="row">
						전화번호
					</th>
					<td>
						<input type="text" name="tel">
					</td>
				</tr>
				<tr>
					<th scope="row">
						주소
					</th>
					<td>
						<input  type="text" name="address" placeholder="기본주소">
						<br>
						<input  type="text" name="addressSec" placeholder="상세주소">
					</td>
				</tr>
			</table>
			
			<br>
			
			<div id="div_buttons">
				<input id="btn_cancle" class="btn_style" type="button" value="취소" onclick="history.go(-1)">
				<input id="btn_signUp" class="btn_style" type="submit" value="가입하기">
			</div>
		</form>
	
	</div>
	
	<jsp:include page="../page/member/commPage/Mem_Footer.jsp"/>

</div>


</body>
</html>