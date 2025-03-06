<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<style type="text/css">
	
	#wrap {
		width:1920px;
	}
	#container {
		width: 1200px;
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
	
	#div_buttons {
		width: 410px;
		margin: auto;
		margin-top: 50px;
	}
	
	#div_buttons input {
		width: 200px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: white;
		color: #7B7B7B;
		font-size: 16px;	
		cursor: pointer;
	}
	
	#div_category {
		float: left;
	}
	#div_content {
		width: 950px;
		float: right;
	}
	#table_form {
		width: 600px;
		height: 500px;
		margin: auto;
	}
	
	#table_form th {
		width: 120px;
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

</style>

</head>

<body>

<div id="wrap">
	<jsp:include page="../commPage/Mem_Header.jsp"/>
	<jsp:include page="../commPage/Category_Main.jsp"/>
	
	<div id="container">
		<div id="div_category">
			<jsp:include page="../commPage/Category_Mypage.jsp"/>
		</div>
		<div id="div_content">
			<h2 style="text-align:center; margin-top: 40px; margin-bottom: 40px; ">개인정보</h2>
			<form action="./update" method="post">
		
				<table id=table_form>
				
					<tr>
						<th scope="row">
							이름
						</th>
						<td>
							${memberDto.memNameStr}
						</td>
					</tr>
					<tr>
						<th scope="row">
							아이디
						</th>
						<td>
							${memberDto.memIdStr}
						</td>
					</tr>
					<tr>
						<th scope="row">
							비밀번호
						</th>
						<td>
							<input type="password" name="password" value="">
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
							<input type="email" name="email" value="${memberDto.memEmailStr}">
						</td>
					</tr>
					<tr>
						<th scope="row">
							생년월일
						</th>
						<td>
							${memberDto.memBirthDate}
						</td>
					</tr>
					<tr>
						<th scope="row">
							전화번호
						</th>
						<td>
							<input type="text" name="tel" value="${memberDto.memTelStr}">
						</td>
					</tr>
					<tr>
						<th scope="row">
							주소
						</th>
						<td>
							<input  type="text" name="address" placeholder="기본주소" value="${memberDto.memAddressStr}">
							<br>
							<input  type="text" name="addressSec" placeholder="상세주소" value="${memberDto.memAddressSecStr}">
						</td>
					</tr>
				</table>
				
				<div id="div_buttons">
					<input id="btn_cancle" class="btn_style" type="button" value="취소" onclick="history.go(-1)">
					<input id="btn_update" class="btn_style" type="submit" value="저장하기" style="background-color: #64473E; color: white;">
				</div>
		</form>
		</div>
	</div>
</div>

</body>

<script type="text/javascript">

</script>

</html>