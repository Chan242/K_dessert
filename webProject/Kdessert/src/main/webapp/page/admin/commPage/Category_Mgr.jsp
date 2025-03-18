<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<style type="text/css">

	.side_Menues {
		list-style: none;
		
		font-size: 27px;
	}
	
	#side_MenuBar ul{
		width: 320px; 
		height: 1300px;
		

		margin: 0px;
		padding-top: 55px;

		background-color: #64473E;
	
	}
	
	.side_Menues a{
		display: flex;  /* flex 정렬 */
	    gap: 65px;
	    flex: 1;
	    
	    text-decoration: none;  /* a태그 밑줄 제거 */
	    color: white;  /* a태그 텍스트 흰색 */
	    
		justify-content : flex-start;/* 글자 가로 왼쪽 정렬 */
		align-items : center;/* 글자 세로 중앙정렬 */
	    
	    padding-left: 90px; /* 왼쪽 여백 추가 */
	    
	    width: 230px;
	    height: 90px;
	    
	    margin: 0px;
	    
	    font-weight: bold;/* 폰트 두께 */
	    
	}
	
	#header {
		border-bottom: solid 1px #E1E1E1; /* 헤더 경계선 */
	}

	ul {
		padding-left: 0px;
	}
	
	.side_Menues a:hover {
		background-color: white;
		color: #64473E
	}
	

</style>


<div id="side_MenuBar">
	<ul>
		<li class='side_Menues'>
			<a id="select_order" href="/Kdessert/admin/order/list?no=1"><span>주문 관리(개발중)</span></a>
		</li>
		<li class='side_Menues'>
			<a id="select_member" href="/Kdessert/page/admin/member/list"><span>회원 관리</span></a>
		</li>
		<li class='side_Menues'>
			<a id="select_product" href="/Kdessert/admin/product/list?no=1"><span>제품 관리</span></a>
		</li>
		<li class='side_Menues'>
			<a id="select_event" href="/Kdessert/page/admin/event/list"><span>행사 관리</span></a>
		</li>	
 		<li class='side_Menues'>
			<a id="select_dessert" href="/Kdessert/page/admin/DaquaListServlet"><span>다과 관리(미개발)</span></a>
		</li>
		<!-- 
		<li class='side_Menues'>
			<a href="통계_관리_링크"><span>통계(미개발)</span></a>
		</li> -->
	</ul>
</div>
