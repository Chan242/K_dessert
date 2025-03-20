
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 상세 - 밤양갱</title>
<link rel="stylesheet" href="style.css">
<style type="text/css">
	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}

/* 헤더 스타일 */
header {
	background-color: #ffffff;
	padding: 10px 20px;
	border-bottom: 2px solid #ddd;
	text-align: center;
}

.header-top {
	font-size: 14px;
	color: #666;
	text-align: right;
	margin-bottom: 5px;
}

.header-bottom {
	text-align: center;
	font-size: 20px;
	font-weight: bold;
	padding: 15px 0;
}

.header-bottom h1 {
	font-size: 18px;
	font-weight: bold;
	margin: 0;
	color: #333;
}

/* 내비게이션 스타일 */
nav {
	background-color: #e0d4c3;
	padding: 10px;
	text-align: center;
}

nav ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

nav ul li {
	display: inline;
	margin: 0 15px;
}

nav ul li a {
	text-decoration: none;
	font-size: 16px;
	color: #333;
	font-weight: bold;
}

/* 상품 컨테이너 */
.product-container {
	width: 1100px;
	margin: 30px auto;
	padding: 20px;
	background-color: #ffffff;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
}

/* 상품 정보 */
.product-content {
	display: flex;
	align-items: center;
	justify-content: space-between;
}

/* 상품 이미지 */
.product-image img {
	width: 180px;
	height: auto;
	border-radius: 8px;
}

/* 상품 상세 정보 */
.product-details {
	flex: 1;
	padding-left: 20px;
}

.product-details table {
	width: 100%;
	border-collapse: collapse;
}

.product-details td {
	padding: 8px;
	font-size: 16px;
	width: 300px;
}

/* 수량 선택 박스 */
.quantity-box {
	display: flex;
	align-items: center;
}

}
/* 수량 조절 버튼 */
.quantity-btn {
	background-color: #a67c52;
	color: white;
	border: none;
	cursor: pointer;
	font-size: 14px;
	padding: 5px;
	width: 25px;
	height: 25px;
	display: flex;
	align-items: center;
	justify-content: center;
	border-radius: 5px;
	margin: 0 5px;
}

/* 상품명 제목 중앙 정렬 */
.product-container h2 {
	text-align: center;
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 20px;
	color: #333;
}

/* 상품 설명 */
.product-description {
	margin-top: 20px;
	padding: 10px;
	background-color: #f3f1ee;
	border-left: 5px solid #a67c52;
	font-size: 14px;
	color: #333;
}

/* 장바구니 버튼 */
.cart-button {
	background-color: #a67c52;
	color: #fff;
	border: none;
	padding: 10px 15px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 5px;
	margin: 15px;
	text-align: right;
}

.cart-button:hover {
	background-color: #8b5e34;
}

/* 상품 상세 정보 테이블 스타일 */
.product-details table {
	width: 100%;
	border-collapse: collapse;
}

.product-details td {
	padding: 10px;
	border-bottom: 1px solid #ddd;
	font-size: 16px;
}

/* 첫 번째 열(설명 부분) 스타일 */
.product-details td:first-child {
	text-align: left;
	font-weight: bold;
	width: 100%;
}

/* 두 번째 열(데이터 부분) 스타일 */
,
product-details td:last-child {
	text-align: right;
	width: 100%
}

/* 입력 필드 스타일 */
.product-details input[type="number" "] {
	width: 60px;
	padding: 5px;
	text-align: center;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.quantity-btn:hover {
	background-color: #8b5e34;
}

/* 입력 필드 스타일 */
input[type="number"] {
	width: 50px;
	text-align: center;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 5px;
}
</style>
</head>

<body>

	<jsp:include page="/page/member/commPage/Mem_Header.jsp" />
	<jsp:include page="/page/member/commPage/Category_Main.jsp" />




	<!-- <메인 컨텐츠> -->
	<main class="product-container">
		<h2>상품명 녹차와 어울리는 맛있는 밤양갱</h2>

		<div class="product-content">
			<!-- <상품 이미지> -->
			<div class="product-image">
				<!-- 				<img src="/src/webapp/image/img3.jpg" alt="밤양갱">  -->
				<!-- <img src="image/img3.jpg" alt="밤양갱"> -->
				<img src="<%= request.getContextPath() %>/image/img3.jpg" alt="밤양갱">
			</div>


			<!-- <상품 상세 정보> -->
			<div class="product-details">
				<table>
					<tr>
						<td>구매가</td>
						<td><span id="price">99,999</span>원</td>

					</tr>
					<tr>
						<td>재고</td>
						<td>1,000</td>
					</tr>
					<tr>
						<td> 선택 수량 </td>
						<td>
							<div class="quantity-box">
								<button class="quantity-btn" onclick="changeQuantity(1)">▲</button>
								<input type="number" id="quantity" value="1" min="1" max="1000"
									onchange="calculateTotal()">
								<button class="quantity-btn" onclick="changeQuantity(-1)">▼</button>
							</div>
						</td>
					</tr>
					<tr>
						<td>주문 금액</td>
						<td><span id="total-price">99,999</span>원</td>
					</tr>
				</table>

				<button class="cart-button">장바구니 담기</button>
			</div>

			<!-- <상품 설명> -->
			<div class="product-description"></div>
			<p>100% 국내산 밤과 팥으로 만들어진 밤양갱. 달지 않은 맛으로 전통 녹차와 잘 어울립니다.</p>
		</div>
	</main>

</body>
<script>


    function changeQuantity(amount) {
        let quantityInput = document.getElementById("quantity");
        let currentQuantity = parseInt(quantityInput.value);
        let newQuantity = currentQuantity + amount;

        if (newQuantity >= 1 && newQuantity <= 1000) {
            quantityInput.value = newQuantity;
            calculateTotal();
        }
    }

    function calculateTotal() {
        let price = 9999;
        let quantity = document.getElementById('quantity').value;
        let totalPrice = price * quantity;
        document.getElementById('total-price').innerText = totalPrice.toLocaleString();
    }
</script>
</html>