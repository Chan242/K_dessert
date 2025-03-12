<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
		<h2 style="text-align: center;">제품정보수정</h2>
		<form action='./update' method='post'>
			<table>
				<tr>
					<td>인덱스: </td>
					<td><input type='text' name='proIndex' value='${productDto.getProIndexInt()}' readonly></td>
				</tr>
				<tr>
					<td>이름: </td>
					<td><input type='text' name='proName' value='${productDto.getProNameStr()}'></td>
				</tr>
				<tr>
					<td>제품가격: </td>
					<td><input type='number' name='proPrice' value='${productDto.getProPriceInt()}'></td>
				</tr>
				<tr>
					<td>재고: </td>
					<td><input type='number' name='proStock' value='${productDto.getProStockInt()}'></td>
				</tr>
				<tr>
					<td>공개여부: </td>
					<td><input type="radio" name='proOpen' value='0' ${productDto.getProOpenInt() == 0 ? "checked" : ""}>공개
					<input type="radio" name='proOpen' value='1' ${productDto.getProOpenInt() == 1 ? "checked" : ""}>비공개</td>
				</tr>
				<tr>
					<td>소개: </td>
					<td><textarea rows="4" cols="52" name="proIntro">${productDto.getProIntroStr()}</textarea></td>
				</tr>
				<tr>
					<td><input type='submit' value='저장'></td>
					<td><input type='button' value='삭제' onclick='location.href="./delete?no=${productDto.getProIndexInt()}"'></td>
					<td><input type='button' value='취소' onclick='location.href="list"'>	</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>