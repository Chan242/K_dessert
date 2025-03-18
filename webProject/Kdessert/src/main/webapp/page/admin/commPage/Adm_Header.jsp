<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<style type="text/css">
	#header{
		padding: 20px;
		padding-left: 50px;
	}
	
	#user_page{
		display: flex; 
		flex: 1; /* 모든 버튼 크기를 균등하게 */
		
		gap: 15px;  /* 항목 간격 15px */
		
	}
	
	#user_page a{
		text-decoration: none;  /* a태그 밑줄 제거 */
		color: black;
	}
	
	#logo img{
		 width: 150px;
	}

</style>

	<c:if test="${member.getMemAdmCheckInt() ne 1}">
	    <script type="text/javascript">
	        alert('권한이 없습니다. 메인 페이지로 이동합니다.');
	        window.location.href = '/Kdessert'; // 메인 페이지로 리다이렉트
	    </script>
	</c:if>
	
	<div id="header">
		<span id="logo">
			<a href="/Kdessert/page/admin">
				<img src="http://localhost:9080/Kdessert/page/admin/imges/logo.png">
			</a>
		</span>
		
		<span id="user_page" class="login_status" style="float:right;">
			<a href="/Kdessert/page/member/mypage/info">${member.memNameStr}님</a>
			<a href="/Kdessert/auth/logout">로그아웃</a>
			<a href="/Kdessert">나가기</a>
		</span>
		
	</div>