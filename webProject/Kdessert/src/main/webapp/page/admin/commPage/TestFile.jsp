<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>메인 페이지 확인용입니다.</title>

<script type="text/javascript">

</script>

<style type="text/css">
	#container{

		margin: auto;
		width: 1200px;
		height: 1200px;
		background-color: lightgray;
		
	}


</style>

</head>

<body>

	<jsp:include page="./Adm_Header.jsp"/>

	
	<div id="container"></div>

	<jsp:include page="./Move_MrgPage.jsp"/>
	<jsp:include page="./Adm_Footer.jsp"/>
	

</body>
</html>