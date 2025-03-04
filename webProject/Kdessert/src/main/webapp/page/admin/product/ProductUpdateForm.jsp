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
		인덱스: <input type='text' name='pIndex' value='${productDto.getpIndexInt() }' readonly><br>
		이름: <input type='text' name='pName' value='--------'><br>
		이메일: <input type='text' name='email' value='--------'><br>
		가입일: ${requestScope.memberDto.getCreatedDate()}<br>
		<input type='submit' value='저장'>
		<input type='button' value='삭제' 
			onclick='location.href="./delete?no=--------";'>
		<input type='button' value='취소' onclick='location.href="list"'>	
	</form>
</body>
</html>