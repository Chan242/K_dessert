<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	#user_page{
		display: flex; 
		flex: 1; /* 모든 버튼 크기를 균등하게 */
		
		gap: 15px;  /* 항목 간격 15px */
		
	}
	
	#header{
		margin-left: 100px;
		margin-right: 100px;
	}
	
	#user_page a{
		text-decoration: none;  /* a태그 밑줄 제거 */
		color: black;

	}
	
	#logo img{
		 width: 150px;
	}

</style>

<div id="header">
		<span id="logo">
			<a href="./TestFile.jsp"><img src="../imges/logo.png"></a>
		</span>
		<span id="user_page" style="float:right;">
			<a href="마이페이지 링크">'이름'님 페이지</a>
			<a href="장바구니 링크">장바구니</a>
			<a href="로그아웃 링크">로그아웃</a>
		</span>
</div>