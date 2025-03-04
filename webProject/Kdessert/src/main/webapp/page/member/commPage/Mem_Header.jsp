<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<style type="text/css">

	#header{
		margin-left: 100px;
		margin-right: 100px;
	}
	
	.login_status{
		display: flex; 
		flex: 1; /* 모든 버튼 크기를 균등하게 */
		
		gap: 15px;  /* 항목 간격 15px */
		
	}
	
	.login_status a{
		text-decoration: none;  /* a태그 밑줄 제거 */
		color: black;

	}
	
	#logo img{
		 width: 150px;
	}

</style>

<div id="header">

	<span id="logo">
		<a href="./index.jsp"><img src="http://localhost:9080/Kdessert/page/admin/imges/logo.png"></a>
	</span>
	
	
	<c:if test="${member.mNameStr ne null}">
		<span id="user_page" class="login_status" style="float:right;">
			<a href="마이페이지 링크">${member.mNameStr}님</a>
			<a href="장바구니 링크">장바구니</a>
			<a href="./auth/logout">로그아웃</a>
		</span>
	</c:if>
	
	<c:if test="${member.mNameStr eq null}">
		<span id="non_user_page" class="login_status" style="float:right;">
			<a href="./auth/login">로그인</a>
			<a href="./auth/signup">회원가입</a>
		</span>
	</c:if>
		
		
</div>