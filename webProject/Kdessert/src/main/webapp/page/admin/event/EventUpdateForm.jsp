<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자 | 행사수정</title>

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
    	width: 200px;
    }
    #table_update {
    	margin: auto;
    	width: 500px;
    }
    
    #table_update th{
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
				<h2>행사수정</h2>
				
				<form action="./update" id="form_parameter" method="post">
					<table id="table_update">
						<tr>
							<th scope="row">행사번호</th>
							<td><input type="hidden" name="no" value="${eventDto.getEveIndexInt()}">${eventDto.getEveIndexInt()}</td>
						</tr>
						<tr>
							<th scope="row">행사 명</th>
							<td><input name="name" type="text" value="${eventDto.getEveNameStr()}"></td>
						</tr>
						<tr>
							<th scope="row">이미지</th>
							<td>${eventDto.getEveImageStr()}</td>
						</tr>
						<tr>
							<th scope="row">행사날짜</th>
							<td>
								<input name="date" type="date" value="${eventDto.getEveEventDate()}"/>
							</td>
						</tr>
						<tr>
							<th scope="row">설명</th>
							<td>
								<textarea rows="5" cols="40" placeholder="" name="explain" style="resize: none;">${eventDto.getEveExplainStr()}</textarea>
							</td>
						</tr>
						<tr>
							<th scope="row">공개여부</th>
							<td>
								<input type="radio" name='openCheck' value='0' ${eventDto.getEveOpenInt() == 0 ? "checked" : ""} > 공개
								<input type="radio" name='openCheck' value='1' ${eventDto.getEveOpenInt() == 1 ? "checked" : ""} > 비공개
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
							<td>
								<textarea rows="5" cols="40" placeholder="" name="note" style="resize: none;">${eventDto.getEveNoteStr()}</textarea>
							</td>
						</tr>
					</table>
					
					<div id="div_buttons">
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