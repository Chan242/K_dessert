<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>충전하기</title>

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
		<form action="./charge" method="post">
			<p>현재 포인트 ${memberDto.memPointInt} P</p>
			<p>최대 1억 포인트까지 충전이 가능합니다</p>
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

	
</script>

</html>