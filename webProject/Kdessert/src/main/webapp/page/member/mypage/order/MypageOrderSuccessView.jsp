<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<style type="text/css">
	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}

	#wrap {
		width:1920px;
	}

	#container {
		width: 1200px;
		margin: auto;
	}

	#div_category {
		float: left;
	}
	#div_content {
		width: 950px;
		float: right;
		text-align: center;
	}
	
/* 	#select_카테고리명 수정필요 */
	#select_point {
		background-color: white;
		color: #64473E;
		font-weight: bold;
	}

</style>

</head>

<body>

	<div id="wrap">
		<jsp:include page="../../commPage/Mem_Header.jsp"/>
		<jsp:include page="../../commPage/Category_Main.jsp"/>
		
		<div id="container">
			<div id="div_category">
				<jsp:include page="../../commPage/Category_Mypage.jsp"/>
			</div>
			<div id="div_content">
				<h2 style="text-align:center; margin-top: 40px; margin-bottom: 40px; ">주문 완료</h2>
				<span>주문이 완료되었습니다.</span> 
			</div>
		</div>
	</div>
	
</body>

<script type="text/javascript">

</script>

</html>