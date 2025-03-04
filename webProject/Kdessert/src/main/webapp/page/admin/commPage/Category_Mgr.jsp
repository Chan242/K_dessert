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
	    
	    padding-left: 80px; /* 왼쪽 여백 추가 */
	    
	    width: 230px;
	    height: 90px;
	    
	    margin: 0px;
	    
	    font-weight: bold;/* 폰트 두께 */
	    
	}
	
	.side_Menues span{

	}
	
	#header {
		border-bottom: solid 1px #E1E1E1; /* 헤더 경계선 */
		
	}


</style>


<div id="side_MenuBar">
	<ul>
		<li class='side_Menues'>
			<a href="주문_관리_링크"><span>주문 관리</span></a>
		</li>
		<li class='side_Menues'>
			<a href="회원_관리_링크"><span>회원 관리</span></a>
		</li>
		<li class='side_Menues'>
			<a href="제품_관리_링크"><span>제품 관리</span></a>
		</li>
		<li class='side_Menues'>
			<a href="다과_관리_링크"><span>다과 관리</span></a>
		</li>
		<li class='side_Menues'>
			<a href="행사_관리_링크"><span>행사 관리</span></a>
		</li>
		<li class='side_Menues'>
			<a href="통계_관리_링크"><span>통계</span></a>
		</li>
	</ul>
</div>
