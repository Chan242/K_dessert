<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<mata name="viewport" content="width=device-width,
initial-scale=1.0">
<title>주문 페이지</title>

<style type="text/css">
body {
	font-family: Arial, sans-serif;
}

.container {
	width: 400px;
	margin: 20px auto;
}

.order-summary {
	border: 1px solid #000;
	padding: 10px;
	margin-bottom: 10px;
}

.out-of-stock {
	background-color: #d9c3c3;
	color: black;
	text-align: center;
	padding: 20px;
	margin-bottom: 10px;
}

/* 장바구니 */
.cart-btn {
	margin-top: 20px;
}

.payment-info {
	background-color: lightgreen;
	padding: 10px;
	text-align: center;
	margin-bottom: 10px;
}
.payment-container {
	display: flex;
	justify-content: center;
}

/*결제하기 버튼*/
.pay-btn {
	background-color: red;
	color: yellow;
	border: none;
	padding: 10px;
	width: 200px;
/* 	max-width: 200px; */ /* 버튼 크기 제한 */
	cursor: pointer;
/* 	display: block; */
	margin: auto;
	margin-top: 20px;
	/* text-align: center;  */
}
</style>

<script type="text/javascript">
	
</script>
</head>

<body>
	<div class="container">
		<p style="color: gray;">다과소개-제품 소진으로 구매 불가 시</p>
		<div class="order-summary">
			<h4>주문내역</h4>
			<table border="1" width="100%">
				<tr>
					<th>상품/옵션 정보</th>
					<th>수량</th>
					<th>상품금액</th>
					<th>배송비</th>
				</tr>
				<tr>
					<td colspan="4" align="center" class="out-of-stock">
						죄송합니다.<br>
						재고가 부족한 제품이 있어<br> 
						주문이 불가능합니다.<br>
					<button class="cart-btn">장바구니</button>
					</td>
				</tr>
			</table>
		</div>
		<h5>결제정보</h5>		
		<div class="payment-info">결제수단-무통장, 신용카드, 휴대폰 결제(버튼만 만들고 상세 구현x)
		</div>
		<div class="payment-container">
		<button class="pay-btu">결제하기</button>
	</div>
	</div>
</body>
</html>