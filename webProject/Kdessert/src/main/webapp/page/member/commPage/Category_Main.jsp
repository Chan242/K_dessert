<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style type="text/css">
	#top_MenuBar{
		border-bottom: solid 1px #E1E1E1; /* 헤더 경계선 */

		margin: 0 auto;
		width: 1650px;
		height: 80px;
		text-align: center;
	}
	
	
	
	#top_MenuBar ul{
		display: flex;  /* flex 정렬 */
	    justify-content: center;  /* 가운데 정렬 */
	    gap: 65px;  /* 항목 간격 65px */
		margin-top: 0px;
	    list-style: none; /* 불필요한 리스트 스타일 제거 */
		
	}
	
	.top_Menues a{
		display: flex;
		flex: 1;  /* 모든 버튼 크기를 균등하게 */
	
    	width: 200px;  /* 가로 크기 고정 */
	    height: 80px;  /* 세로 크기 고정 */
	    text-align: center;
	    font-size: 23px;

	    	    
	    align-items: center;  /* 세로 중앙 정렬 */
	    justify-content: center;  /* 가로 중앙 정렬 */
	    
/* 	    background-color: orange; */
	    
	}
	
	#top_MenuBar a{
		text-decoration: none;  /* a태그 밑줄 제거 */
	    color: black;  /* a태그 텍스트 검은색 */
	}
	
	#top_MenuBar a:hover{
		border-bottom: 2px solid #64473E;
	}



</style>

	<div id="top_MenuBar">
		<ul>
			<li class='top_Menues'>
				<a id="select_dessert" href="다과링크"><span>다과 소개</span></a>
			</li>
			<li class='top_Menues'>
				<a id="select_produt" href="/Kdessert/product/list"><span>구매</span></a>
			</li>
			<li class='top_Menues'>
				<a id="select_event" href="행사링크"><span>행사</span></a>
			</li>
			<li class='top_Menues'>
				<a id="select_board" href="/Kdessert/board"><span>고객 게시판</span></a>
			</li>
		</ul>
	</div>

