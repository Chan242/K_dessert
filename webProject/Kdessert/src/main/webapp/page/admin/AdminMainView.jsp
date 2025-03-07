<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">

	#wrap {
		width:1920px;
	}
	
	#div_category{
		float: left
	}
	
	#div_content{
		width: 1300px;
		margin-left: 50px; 
		float: left;
	}
	
	#div_content h2 {
		 margin: 40px; 
		 padding-bottom: 20px;
		 border-bottom: 2px solid black;
	}

</style>

</head>

<body>

	<div id="wrap">
		<jsp:include page="/page/admin/commPage/Adm_Header.jsp"/>
		
		<div>
			<div id="div_category">
				<jsp:include page="/page/admin/commPage/Category_Mgr.jsp"/>
			</div>
			
			<div id="div_content">
				<h2>　관리자 페이지</h2>
				
			</div>	
		</div>
	</div>	

</body>

<script type="text/javascript">

</script>

</html>