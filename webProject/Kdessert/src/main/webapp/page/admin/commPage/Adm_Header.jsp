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
			<a href="/Kdessert/page/admin"><img src="http://localhost:9080/Kdessert/page/admin/imges/logo.png"></a>
		</span>
		
		
		<span id="user_page" class="login_status" style="float:right;">
			<a href="/Kdessert/page/member/mypage/info">${member.memNameStr}님</a>
			<a href="/Kdessert/auth/logout">로그아웃</a>
			<a href="/Kdessert">나가기</a>
		</span>
		
</div>