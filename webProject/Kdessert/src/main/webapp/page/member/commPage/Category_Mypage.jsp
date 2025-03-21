<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<style type="text/css">

	
	#side_MenuBar {
	
		width: 250px; 
		height: 100%;
		min-height: 1100px;
		margin: 0px;
		padding-top: 55px;

		background-color: #EF9157;
	}

	#side_MenuBar ul{
		padding: 0;
	}
	
	.side_Menues {
		list-style: none;
		font-size: 24px;
	}
	
	.side_Menues a {
		padding-left: 60px;
	}
	
	
	.side_Menues a:hover {
		background-color: white;
		color: #64473E;
	}
	
	.side_Menues a, h1{

/* 	    width: 120px; */
	    height: 80px;

		display: flex;  /* flex 정렬 */
	    flex: 1;
	    
	    text-decoration: none;  /* a태그 밑줄 제거 */
	    color: white;  /* a태그 텍스트 흰색 */
	    
		justify-content : flex-start;/* 글자 가로 왼쪽 정렬 */
		align-items : center;/* 글자 세로 중앙정렬 */
		
	}
	
	h1 {
		font-size: 32px;
		margin: 0;
		justify-content : center;/* 글자 가로 가운데 정렬 */
	}
	


	
	


</style>



<div id="side_MenuBar">
		<h1>마이페이지</h1>
	<ul>
		<li class='side_Menues'>
			<a id="select_profile" href="/Kdessert/page/member/mypage/info">개인정보</a>
		</li>
		<li class='side_Menues'>
			<a id="select_order" href="/Kdessert/page/member/mypage/orderlist"><span>주문정보</span></a>
		</li>
		<li class='side_Menues'>
			<a id="select_basket" href="/Kdessert/page/member/mypage/basket"><span>장바구니</span></a>
		</li>
		<li class='side_Menues'>
			<a id="select_point" href="/Kdessert/page/member/mypage/point"><span>마이포인트</span></a>
		</li>
	</ul>
</div>


</body>

<script type="text/javascript">

</script>

</html>