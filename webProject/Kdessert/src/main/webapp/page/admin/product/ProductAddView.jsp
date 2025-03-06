<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Add View place</title>
</head>
<body>

	<jsp:include page="../commPage/Adm_Header.jsp"></jsp:include>

	<div style="float: left;">
		<jsp:include page="../commPage/Category_Mgr.jsp"></jsp:include>
	</div>

	<div>
		<form action=""method="post">
			제품명<input type="text" size="50" name="proName" placeholder="제품명" required><br>
			제품가격<input type="number" size="50" name="proPrice" placeholder="제품가격" required><br>
			제품설명<textarea name="proIntro" rows="4" cols="52" placeholder="제품설명" required></textarea><br>
			재고<input type="number" size="50" name="proStock" placeholder="재고" required><br>
			공개여부<input type="radio" name="proOpen" value="0" checked="checked">공개
			<input type="radio" name="proOpen" value="1">비공개<br>			
			<input type="file" accept=".png" value="png"><br><!-- 추후구현 -->
			#PNG 파일만 가능/추후 구현 예정(현재 받는 곳이 없음)<br>
			<input type="text" size="50" placeholder="#태그"><br><!-- 추후구현 -->
			추후 구현 예정(현재 받는 곳이 없음)
			<input type="submit" value="입력">
			<input type="reset" value="비우기"> <br>
		</form>
		
		<button onclick="history.back()">뒤로가기</button>
			
				
	</div>


</body>
</html>