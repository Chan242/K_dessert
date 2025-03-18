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
		color: #64473E;    
	}
	
	#div_content h1 {
		 margin-top: 40px; 
		 padding-bottom: 20px;
		 border-bottom: 1px solid black;
	}
	
	.lineArr{
		display: flex;
		align-items:center;
	}
	
	.lineArr h2{
		padding-right: 25px; 
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
				<h1>관리자 페이지 설명</h1>
				<div class='lineArr'>
					<h2>주문 관리</h2>
					<p>주문을 관리하는 페이지입니다.</p>
				</div>
				<div class='lineArr'>
					<h2>회원 관리</h2>
					<p>회원을 관리하는 페이지입니다.</p>
				</div>
				<div class='lineArr'>
					<h2>제품 관리</h2>
					<p>상품을 관리하는 페이지입니다.</p>
				</div>
				<div class='lineArr'>
					<h2>행사 관리</h2>
					<p>행사 및 예약을 관리하는 페이지입니다.</p>
				</div>
				<br>
				<a href="/Kdessert/">메인으로 돌아가기</a>
				
			</div>	
		</div>
	</div>	

</body>

<script type="text/javascript">

</script>

</html>