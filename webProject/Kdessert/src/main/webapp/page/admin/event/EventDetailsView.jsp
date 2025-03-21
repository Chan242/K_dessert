<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자 | 행사상세</title>

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
		width: 1905px;
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
    
    #btn_eventDelete {
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
    
    #select_event {
		background-color: white;
		color: #64473E
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
				<h2>행사상세</h2>
				
				<form action="./delete" id="deleteForm" method="get">
					<table id="table_form">
						<tr>
							<th scope="row">행사번호</th>
							<td><input type="hidden" name="no" value="${eventDto.getEveIndexInt()}">${eventDto.getEveIndexInt()}</td>
						</tr>
						<tr>
							<th scope="row">행사 명</th>
							<td>${eventDto.getEveNameStr()}</td>
						</tr>
						<tr>
							<th scope="row">이미지</th>
							<td>${eventDto.getEveImageStr()}</td>
						</tr>
						<tr>
							<th scope="row">행사날짜</th>
							<td>${eventDto.getEveEventDate()}</td>
						</tr>
						<tr>
							<th scope="row">설명</th>
							<td>${eventDto.getEveExplainStr()}</td>
						</tr>
						<tr>
							<th scope="row">공개여부</th>
							<td>
								<input type="radio" name='openCheck' value='1' ${eventDto.getEveOpenInt() == 0 ? "checked" : ""} disabled> 공개
								<input type="radio" name='openCheck' value='0' ${eventDto.getEveOpenInt() == 1 ? "checked" : ""} disabled> 비공개
							</td>
						</tr>
						<tr>
							<th scope="row">생성일</th>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${eventDto.getEveCreDate()}"/></td>
						</tr>
						<tr>
							<th scope="row">수정일</th>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${eventDto.getEveCorrDate()}"/></td>
						</tr>
						<tr>
							<th scope="row">비고</th>
							<td>${eventDto.getEveNoteStr()}</td>
						</tr>
					</table>
					
					<div id="div_buttons">
						<input id="btn_goIndex" class="btn_style" type="button" value="목록으로" onclick="history.go(-1)">
						<input id="btn_goUpdate" class="btn_style" type="button" value="수정하기" onclick="location.href='./update?no=${eventDto.getEveIndexInt()}'">
						<input id="btn_eventDelete" class="btn_style" type="button" value="삭제" onclick="deleteEvent()">
					</div>
					
				</form>
			</div>
		</div>
	
	</div>

</body>

<script type="text/javascript">

function deleteEvent() {
	var confirmStr = "행사를 삭제하시겠습니까?"
	if(confirm(confirmStr) == true){
		var form = document.getElementById("deleteForm");
		form.submit();
	}else{
		return false;
	}
}
</script>

</html>