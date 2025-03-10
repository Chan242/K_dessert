<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<style type="text/css">

	#wrap {
		width:1895px;
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
	}
	
	#select_point {
		background-color: white;
		color: #64473E;
		font-weight: bold;
	}
	
	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
	}

	th, td {
		padding: 15px;
	}
	
	#div_pointInfo {
		width: 500px;
		height: 300px;
		margin: auto;
	}

	#div_pointInfo table {
		margin: auto;
		font-size: 20px;
	}
	
	#div_pointInfo table td {
		width: 200px;
		text-align: right;
	}
	
	#div_pointInfo div {
		text-align: center;
		margin-top: 50px;
	}
	
	.btn_style {

		width: 200px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: #64473E;
		color: white;
		font-size: 16px;
		
		cursor: pointer;
		
    }

</style>

</head>

<body>

	<div id="wrap">
		<jsp:include page="../commPage/Mem_Header.jsp"/>
		<jsp:include page="../commPage/Category_Main.jsp"/>
		
		<div id="container">
			<div id="div_category">
				<jsp:include page="../commPage/Category_Mypage.jsp"/>
			</div>
			<div id="div_content">
				<h2 style="text-align:center; margin-top: 40px; margin-bottom: 40px; ">마이포인트</h2>
				<div id="div_pointInfo">
					<table>
						<tr>
							<th>현재 포인트</th>
							<td>${memberDto.memPointInt} P</td>
						</tr>
					</table>
					<div>
						<input class="btn_style" type="button" value="충전하기" onclick="chargePopup()">
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">

	function chargePopup() {
		
		var popupSetStr = "";
		
		popupSetStr += "width=480px, height=300px, left=720px, top=300px";
		
		window.open('/Kdessert/page/member/mypage/point/charge', 'chargePop', popupSetStr);
		
	}

</script>

</html>