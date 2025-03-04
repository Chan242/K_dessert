<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action='./update' method='post'>
		인덱스: <input type='text' name='proIndex' value='${productDto.getproIndexInt()}' readonly><br>
		이름: <input type='text' name='proName' value='${productDto.getproNameStr()}'><br>
		제품가격: <input type='text' name='proPrice' value='${productDto.getproPriceInt()}'><br>
		재고: <input type='text' name='proStock' value='${productDto.getproStockInt()}'><br>
		공개여부: <input type="radio" name='proOpen' value='0' checked="checked">공개
				<input type="radio" name='proOpen' value='1'>비공개<br>
		소개: <textarea rows="4" cols="52" name="proIntro">${productDto.getproIntroStr()}</textarea><br>

		<input type='submit' value='저장'>
		<input type='button' value='삭제' onclick='location.href="./delete?no=${productDto.getproIndexInt()}"'>
		<input type='button' value='취소' onclick='location.href="list"'>	
	</form>
</body>
</html>