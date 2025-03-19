<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

	
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자 | 회원상세</title>

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

		width: 150px;
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
    
    #btn_memDelete {
    	background-color: #64473E;
    	color: white;
    }
    
    #div_category {
    	float: left;
    }
    
    #div_form {
    	magin-top: 50px;
    	margin-left: 50px;
    	width: 1300px;
    	float: left;
    }
    
    #div_form h2 {
		width: 1200px;
		margin: auto;
		margin-top: 40px;
		padding-bottom: 20px;
/* 		border-bottom: 2px solid black; */
		text-align: center;
	}
    
    #div_buttons {
    	margin: auto;
    	margin-top: 50px;
    	width: 462px;
    }
    #table_form {
    	margin: auto;
    	width: 500px;
    }
    
    #table_form th{
    	width: 100px;
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
			<div id="div_form">
				<h2>회원상세</h2>
				
				<form action="./delete" id="deleteForm" method="get">
					<table id="table_form">
						<tr>
							<th scope="row">회원번호</th>
							<td><input type="hidden" name="no" value="${memberDto.getMemIndexInt()}">${memberDto.getMemIndexInt()}</td>
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
							<th scope="row">주소</th>
							<td>
								${memberDto.getMemAddressStr()}
								<br>
								${memberDto.getMemAddressSecStr()}
							</td>
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
							<td><fmt:formatNumber value="${memberDtoPoint.getMemPointInt()}" type="number"/></td>
						</tr>
						<tr>
							<th scope="row">관리자</th>
							<td>
								<input type="radio" name='adminCheck' value='1' ${memberDto.getMemAdmCheckInt() == 1 ? "checked" : ""} disabled> 허용
								<input type="radio" name='adminCheck' value='0' ${memberDto.getMemAdmCheckInt() == 0 ? "checked" : ""} disabled> 비허용
							</td>
						</tr>
						<tr>
							<th scope="row">비고</th>
							<td>${memberDto.getMemNoteStr()}</td>
						</tr>
					</table>
					
					<div id="div_buttons">
						<input id="btn_goIndex" class="btn_style" type="button" value="목록으로" onclick="history.go(-1)">
						<input id="btn_goUpdate" class="btn_style" type="button" value="수정하기" onclick="location.href='./update?no=${memberDto.getMemIndexInt()}'">
						<input id="btn_memDelete" class="btn_style" type="button" value="강제탈퇴" onclick="deleteMember()">
					</div>
					
				</form>
			</div>
		</div>
	
	</div>

</body>

<script type="text/javascript">

	function deleteMember() {
		var confirmStr = "주의\n탈퇴는 돌이킬 수 없습니다.\n정말로 실행하겠습니까?"
		if(confirm(confirmStr) == true){
			var form = document.getElementById("deleteForm");
			form.submit();
		}else{
			return false;
		}
	}

</script>

</html>