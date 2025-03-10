<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
#container{
	/* 푸터 맨 하단으로 고정할 수 있게 추가 */
	min-height: 800px; /* 최소높이 지정 */

}
</style>
<meta charset="UTF-8">
<title>알근달근: 한국 전통 과자 전문점</title>
</head>
<body>

	<jsp:include page="./page/member/commPage/Mem_Header.jsp"/>
	<jsp:include page="./page/member/commPage/Category_Main.jsp"/>
	
	<c:if test="${member.getMemAdmCheckInt() == 1}">
		<jsp:include page="./page/admin/commPage/Move_MrgPage.jsp"/>
	</c:if>
	
	<div id="container">
		hello everyone
	
	</div>
	<jsp:include page="./page/member/commPage/Mem_Footer.jsp"/>
	

</body>
</html>