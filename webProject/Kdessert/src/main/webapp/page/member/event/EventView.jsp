<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>알근달근 | 행사</title>

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
		width: 1280px;
		height: 300px;
		margin: 0 auto;
		padding-top: 60px;
		padding-bottom: 250px;
	}
	
	#select_event {
		border-bottom: 2px solid #64473E;
	}
	
</style>

</head>

<body>
<div id="wrap">

	<jsp:include page="../commPage/Mem_Header.jsp" />
	<jsp:include page="../commPage/Category_Main.jsp" />
	
	<div id="container">
	
	</div>
	
	<jsp:include page="../commPage/Mem_Footer.jsp"/>
	
</div>


</body>

<script type="text/javascript">

</script>

</html>