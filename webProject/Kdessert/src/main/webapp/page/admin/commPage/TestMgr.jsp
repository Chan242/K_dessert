<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자 페이지 확인용입니다.</title>

<style type="text/css">

	#category-container {
	    float: left; /* Category_Mgr.jsp를 왼쪽에 배치 */
	    width: auto; /* 내용에 맞게 너비 설정 */
	}
	#container{
		float: inherit;
		right: 100px;
		margin: auto;
		width: 500px;
		height: 500px;
		background-color: lightgray;
		
	}
	
	.clearfix::after {
    content: "";
    display: block;
    clear: both;
}


</style>

</head>
<body>

	<jsp:include page="./adm_Header.jsp"/>
	 <div class="clearfix">
	 	<div id="category-container">
			<jsp:include page="./Category_Mgr.jsp"/>
		</div>
		<div id="container">
			할로
		</div>
	</div>
	
	
</body>
</html>