<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../commPage/Adm_Header.jsp"></jsp:include>

	<div style="float: left;">
		<jsp:include page="../commPage/Category_Mgr.jsp"></jsp:include>
	</div>

	<div>
		<form action='./update' method='post'>
			인덱스: <input type='text' name='proIndex' value='${productDto.getProIndexInt()}' readonly><br>
			이름: <input type='text' name='proName' value='${productDto.getProNameStr()}'><br>
			제품가격: <input type='number' name='proPrice' value='${productDto.getProPriceInt()}'><br>
			재고: <input type='number' name='proStock' value='${productDto.getProStockInt()}'><br>
			공개여부: <input type="radio" name='proOpen' value='0' ${productDto.getProOpenInt() == 0 ? "checked" : ""}>공개
					<input type="radio" name='proOpen' value='1' ${productDto.getProOpenInt() == 1 ? "checked" : ""}>비공개<br>
			소개: <textarea rows="4" cols="52" name="proIntro">${productDto.getProIntroStr()}</textarea><br>
	
			<input type='submit' value='저장'>
			<input type='button' value='삭제' onclick='location.href="./delete?no=${productDto.getProIndexInt()}"'>
			<input type='button' value='취소' onclick='location.href="list"'>	
		</form>
	</div>
</body>
</html>