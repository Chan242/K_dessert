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
	
	#div_btn {
		width: 410px;
		margin: auto;
		margin-top: 50px;
	}
	
	#div_btn input {
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
	
	#select_profile {
		background-color: white;
		color: #64473E;
		font-weight: bold;
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
				<table id="table_form">
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
						<th scope="row">주소</th>
						<td>
							${memberDto.memAddressStr}
							<br>
							${memberDto.memAddressSecStr}
						</td>
					</tr>
				</table>
				<div id="div_btn">
					<input type="button" value="수정하기" onclick="location.href='./info/update?no=${memberDto.memIndexInt}'">	
					<input type="button" value="탈퇴하기" style="background-color: #64473E;color: white;" onclick="deleteMember()">
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	function deleteMember() {

		var confirmStr = "주의\n탈퇴는 돌이킬 수 없습니다.\n정말로 실행하겠습니까?"
				
		// JSP에서 전달된 memberDto 객체에서 no 값을 추출 (EL 표현식 사용)
        var memberIndex = "${memberDto.memIndexInt}";

		if(confirm(confirmStr) == true){
			// 'no' 값을 URL에 추가하여 삭제 요청을 보냄
            window.location.href = "./info/delete?no=" + memberIndex;
		}else{
			return false;
		}
	}
</script>

</html>