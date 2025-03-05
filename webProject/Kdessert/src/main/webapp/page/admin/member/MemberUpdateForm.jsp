<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">

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
	
	.btn_style {

		width: 200px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: #64473E;
		color: white;
		font-size: 16px;
		
    }

</style>

</head>

<body>

	<form id="form_parameter" action="./update" method="post">
		<table>
			<tr>
				<th scope="row">회원번호</th>
				<td><input type="hidden" name="index" value="${memberDto.memIndexInt}">${memberDto.memIndexInt}</td>
			</tr>
			<tr>
				<th scope="row">이름</th>
				<td>${memberDto.memNameStr}</td>
			</tr>
			<tr>
				<th scope="row">아이디</th>
				<td>${memberDto.memIdStr}</td>
			</tr>
			<tr>
				<th scope="row">이메일</th>
				<td>${memberDto.memEmailStr}</td>
			</tr>
			<tr>
				<th scope="row">생년월일</th>
				<td>${memberDto.memBirthDate}</td>
			</tr>
			<tr>
				<th scope="row">전화번호</th>
				<td>${memberDto.memTelStr}</td>
			</tr>
			<tr>
				<th scope="row">기본주소</th>
				<td>${memberDto.memAddressStr}</td>
			</tr>
			<tr>
				<th scope="row">상세주소</th>
				<td>${memberDto.memAddressSecStr}</td>
			</tr>
			<tr>
				<th scope="row">가입일</th>
				<td>${memberDto.memSignTimeDate}</td>
			</tr>
			<tr>
				<th scope="row">포인트</th>
				<td>${memberDto.memPointInt}</td>
			</tr>
			<tr>
				<th scope="row">비고</th>
				<td>
					<textarea rows="5" cols="22" placeholder="" name="note">${memberDto.memNoteStr}</textarea>
				</td>
			</tr>
		</table>
		<input id="btn_submit" class="btn_style" type="submit" value="저장하기">
	</form>
	

</body>

<script type="text/javascript">

</script>

</html>