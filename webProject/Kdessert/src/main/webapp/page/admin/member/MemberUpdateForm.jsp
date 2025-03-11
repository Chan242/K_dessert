<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">

	#wrap {
		width:1895px;
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
		
		cursor: pointer;
    }
    
    #div_category {
    	float: left;
    }
	
	#div_content {
		width: 1300px;
		margin-left: 50px;
		float: left;
	}
	
	#table_update {
		margin: auto;
		width: 500px;
	}
	
	#table_update th {
		width: 100px;
	}
	
	#div_button {
		margin: auto;
		margin-top: 50px;
		width: 200px;
	}
	
</style>

</head>

<body>

	<div id="wrap">
	
		<jsp:include page="../commPage/Adm_Header.jsp"/>
		
		<div id="container">
			
			<div id="div_category">
				<jsp:include page="../commPage/Category_Mgr.jsp"/>
			</div>
			
			<div id="div_content">
				<h2 style="margin: 40px;">회원수정</h2>
				<form id="form_parameter" action="./update" method="post">
					<table id="table_update">
						<tr>
							<th scope="row">회원번호</th>
							<td><input type="hidden" name="index" value="${memberDto.getMemIndexInt()}">${memberDto.getMemIndexInt()}</td>
						</tr>
						<tr>
							<th scope="row">이름</th>
							<td>${memberDto.getMemNameStr()}</td>
						</tr>
						<tr>
							<th scope="row">아이디</th>
							<td>${memberDto.getMemIdStr()}</td>
						</tr>
						<tr>
							<th scope="row">이메일</th>
							<td>${memberDto.getMemEmailStr()}</td>
						</tr>
						<tr>
							<th scope="row">생년월일</th>
							<td>${memberDto.getMemBirthDate()}</td>
						</tr>
						<tr>
							<th scope="row">전화번호</th>
							<td>${memberDto.getMemTelStr()}</td>
						</tr>
						<tr>
							<th scope="row">기본주소</th>
							<td>${memberDto.getMemAddressStr()}</td>
						</tr>
						<tr>
							<th scope="row">상세주소</th>
							<td>${memberDto.getMemAddressSecStr()}</td>
						</tr>
						<tr>
							<th scope="row">가입일</th>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${memberDto.getMemSignTimeDate()}"/></td>
						</tr>
						<tr>
							<th scope="row">수정일</th>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${memberDto.getMemCorrDate()}"/></td>
						</tr>
						<tr>
							<th scope="row">포인트</th>
							<td>${memberDtoPoint.getMemPointInt()}</td>
						</tr>
						<tr>
							<th scope="row">관리자</th>
							<td>
								<input type="radio" name='adminCheck' value='1' ${memberDto.getMemAdmCheckInt() == 1 ? "checked" : ""} > 허용
								<input type="radio" name='adminCheck' value='0' ${memberDto.getMemAdmCheckInt() == 0 ? "checked" : ""} > 비허용
							</td>
						</tr>
						<tr>
							<th scope="row">비고</th>
							<td>
								<textarea rows="5" cols="40" placeholder="" name="note" style="resize: none;">${memberDto.getMemNoteStr()}</textarea>
							</td>
						</tr>
					</table>
					<div id="div_button">
						<input id="btn_submit" class="btn_style" type="submit" value="저장하기">
					</div>
				</form>
			</div>
			
		</div>

	</div>
	
	
	

</body>

<script type="text/javascript">

</script>

</html>