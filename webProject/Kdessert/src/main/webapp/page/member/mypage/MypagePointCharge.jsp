<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>포인트 충전</title>

<style type="text/css">

	#input_charge_point {
		width: 200px;
		height: 30px;
		font-size: 18px;
		margin-top: 20px;
		margin-bottom: 20px;
	}

	.btn_style {

		width: 100px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: white;
		color: #7B7B7B;
		font-size: 16px;
		
		cursor: pointer;
    }
    
    #btn_charge {
    	background-color: #64473E;
    	color: white;
    }
    
    .div_center {
    	width: 210px;
    	margin: auto;
    	
    }
    
    #wrap {
    	text-align: center;
    	margin-top: 30px;
    }
    
  	p {
  		margin: 5px;
  	}
    
    
</style>

</head>

<body>


	<div id="wrap">
		<h2>포인트 충전</h2>
		<form action="./charge" method="post" onsubmit="return validateForm()">
			<p>현재 포인트 <fmt:formatNumber value="${memberDto.memPointInt}" type="number"/> P</p>
			<p>포인트는 최대 1억까지만 충전이 가능합니다</p>
			<p>충전할 금액을 입력해주세요 (단위: 1,000 P)</p>
			<div class="div_center">
				<input id="input_charge_point" name="point" type="number" min="1000" max="100000000" value="1000" step="1000" required>
			</div>
			<div class="div_center">
				<input id="btn_cancle" class="btn_style" type="button" value="취소" onclick="window.close()">
				<input id="btn_charge" class="btn_style" type="submit" value="충전">
			</div>
		</form>
	</div>


</body>

<script type="text/javascript">

	function validateForm() {
		var inputChargePoint = Number(document.getElementById("input_charge_point").value); // 숫자로 변환
	    var currentPoint = Number(${memberDto.memPointInt}); // 숫자로 변환

	    if ((currentPoint + inputChargePoint) > 100000000) {
	        alert("충전 한도를 넘어섰습니다.");
	        return false;
	    }
	    return true;  // 폼을 정상적으로 제출
	}
	
</script>

</html>