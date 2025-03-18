<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Add View place</title>

<style type="text/css">

	body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	

table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
		margin: auto;
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
	}

	th, td {
		padding: 15px;
	}

</style>


</head>
<body>

	<jsp:include page="../commPage/Adm_Header.jsp"></jsp:include>

	<div style="float: left;">
		<jsp:include page="../commPage/Category_Mgr.jsp"></jsp:include>
	</div>

	<div style="width:800px; margin: auto;">
		<h2 style="text-align: center;">제품등록</h2>
	 	<form action=""method="post"  enctype="multipart/form-data" style="margin: auto;"> 
		<!-- <form action=""method="post"  style="margin: auto;">	 -->		
			<table>
				<tr>
					<td>제품명</td>
					<td><input type="text" size="50" name="proName" placeholder="제품명" required></td>
				</tr>
				<tr>
					<td>제품가격</td>
					<td><input type="number" size="50" name="proPrice" placeholder="제품가격" required></td>
				</tr>
				<tr>
					<td>제품설명</td>
					<td><textarea name="proIntro" rows="4" cols="52" placeholder="제품설명" required></textarea></td>
				</tr>
				<tr>
					<td>재고</td>
					<td><input type="number" size="50" name="proStock" placeholder="재고" required></td>
				</tr>
				<tr>
					<td>공개여부</td>
					<td><input type="radio" name="proOpen" value="0" checked="checked">공개
				<input type="radio" name="proOpen" value="1">비공개</td>
				</tr>
				<tr>
					<td>이미지</td>
					<td><input type="file" name="proImage" accept="image/*" value="png" required>
				</td>
				</tr>
				<tr>
					<td>태그</td>
					<td><input type="text" name="tag" size="50" placeholder="#태그">
				)</td>
				</tr>
				<tr>
					<td><input type="submit" value="입력"></td>
					<td><input type="reset" value="비우기"> </td>
				</tr>
				<tr>
					<td colspan="2"><button onclick="history.back()">뒤로가기</button></td>
				</tr>
	
				
			</table>
		</form>
	
		
			
				
	</div>


</body>
</html>