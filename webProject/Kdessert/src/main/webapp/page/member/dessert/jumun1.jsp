<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>InsertTitle</title>

<style type="text/css">
	body {
		width: 100%;
		margin: 0px;
		overflow-y: scroll;
	}
body {
	font-family: Arial, sans-serif;
}

.container {
	width: 60%;
	margin: auto;
	border: 1px solid #ddd;
	padding: 20px;
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #000;
	padding: 10px;
	text-align: center;
}

.total, .shipping {
	text-align: right;
	margin-top: 10px;
}

.warning {
	color: red;
	cursor: pointer;
}

.warning:hover {
	text-decoration: underline;
}

.button-container {
	text-align: center;
	margin-top: 20px;
}

.btn {
	background-color: #4a4a4a;
	color: white;
	padding: 10px 20px;
	border: none;
	cursor: pointer;
	font-size: 16px;
}

.btn:hover {
	background-color: #333;
}

css

/*기본 스타일*/

.menu nav {
    display: flex;
    justify-content: center; /* 가로 가운데 정렬 */
}

.menu ul {
    display: flex; /* 리스트를 가로로 배치 */
    list-style: none; /* 기본 리스트 스타일 제거 */
    padding: 0;
    gap: 20px; /* 메뉴 간격 조절 */
}

.menu li {
    display: inline-block; /* 인라인 요소처럼 배치 */
}

.menu a {
    text-decoration: none; /* 밑줄 제거 */
    color: #333;
    font-size: 18px;
    padding: 10px 15px;
    background-color: #f4f4f4;
    border-radius: 5px;
}

.menu a:hover {
    background-color: #ddd;
}

/* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	
} */

body{
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	color: #333;
	
}

header {
	background-color: #2c3e50;
	padding: 10px;
	color: white;
	display: flex;
	justify-content: space-between;
	align-items: center;
	
}

header .logo {
	font-size: 24px;
	
}

header .user-info a {
	color:white;
	text-decoration: none;
	margin-left: 10px;
	
}

header .user-info span {
	margin-right: 10px;
	
}

/* 장바구니 부분*/
main {
	padding: 20px;
	
}

h2 {
	font-size: 28px;
	margin-bottom: 20px;
	
}

.cart-item {
	display: flex;
	margin-bottom: 20px;
	padding: 10px;
	background-color: #64473E;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	
}

.cart-item img {
	width: 100px;
	height: 100px;
	margin-right: 20px;
	
}

.item-info p {
	margin: 5px 0;
	
}

.shipping-cost, .total-price, .points {
	margin-top: 20px;
	background-color: white;
	padding: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.delivery-info {
	text-align: left;
}


.pay-button {
	margin-top: 20px;
	padding: 10px 20px;
	font-size: 16px;
	background-color: #2c3e50;
	color: white;
	border: none;
	cursor: pointer;
	width: 100%;
	
}

.pay-button:hover {
	background-color: #34495e;
	
}

.insufficient-points {
	color: red;
	font-weight: bold;
	margin-top: 10px;
	
}

</style>

<script type="text/javascript">


</script>


</head>

<body>

	<jsp:include page="/page/member/commPage/Mem_Header.jsp"/>
	<jsp:include page="/page/member/commPage/Category_Main.jsp"/>

	<div class="container">
		<h2>주문내역</h2>
		<table>  <!-- ✅ 오타 수정 (talbe → table) -->
		<tr>
			<th>상품명</th>
			<th>개수</th>
			<th>금액</th>
			<th>총 금액</th>
		</tr>
		<tr>
			<td>
				<img src="../../../image/img1.jpg" alt="밤양갱" width="80"><br>
				녹차의 어울리는 맛있는 밤양갱
			</td>
			<td>99개</td>
			<td>999,999</td>
			<td>999,999</td>
		</tr>
		<tr>
			<td>
				<img src="/Kdessert/image/img2.jpg" alt="꼬치 다과" width="80"><br>
				쌀로 만든 전통 꼬치 다과
			</td>
			<td>99개</td>
			<td>999,999</td>
			<td>999,999</td>
		</tr>
		</table>
		
	<div class="delivery-info">
	<p>배송정보</p>
	<p>주문자명: 홍길동</p>
	<p>휴대폰번호: 010-1234-5678</p>
	</div>
		
		<p class="shipping">배송비: <b>99,999</b></p>
		<p class="total">총 결제 금액(제품+ 배송비):<b>9,999,999</b></p>
		
		<!-- ✅ 클래스명 오타 수정 (waring → warning) -->
		<p class="warning" id="chargeLink"> #포인트가 부족합니다. 클릭시 충전창으로 이동합니다.</p>
		
		<div class="button-container">
			<button class="pay-button" onclick="pay()">
					결제하기</button>	
		</div>
	</div>	
	
	<script>
		// ✅ 올바른 이벤트 리스너 방식으로 변경
		document.getElementById("chargeLink").addEventListener("click", function() {
			alert("충전 페이지로 이동합니다.");
	        // location.href = 'charge.html'; // 실제 충전 페이지 링크
		});
	</script>



</body>
</html>