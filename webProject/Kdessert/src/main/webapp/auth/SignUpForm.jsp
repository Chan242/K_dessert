<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<style type="text/css">

	table, tr, th, td {
		border-bottom: 1px solid gray;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
	}
	
	th {
		background-color: lightgray;
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
    }
    
    #btn_cancle {
		background-color: white;
		color: gray;
    }
    
    #btn_signUp {
    	background-color: maroon;
    	color: white;
    }
    
    #btn_signUp:hover {
    	background-color: black;
    }


</style>

</head>
<body>

	<h1>회원가입</h1>
	
	<form action="./signup" method="post">
	
		<table>
		
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
				<th scope="row" rowspan="2">
					주소
				</th>
				<td>
					<input  type="text" name="address" placeholder="기본주소">
				</td>
			</tr>
			<tr>
				<td>
					<input  type="text" name="addressSec" placeholder="상세주소">
				</td>
			</tr>
		
		</table>
		
		<br>
		
		<input id="btn_cancle" class="btn_style" type="button" value="취소" onclick="history.go(-1)">
		<input id="btn_signUp" class="btn_style" type="submit" value="가입하기">
	
	</form>
	

</body>
</html>