<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>관리자 페이지 확인용입니다.</title>

<style type="text/css">
	#content-wrapper {
	    display: flex; /* 가로 정렬을 위한 flexbox */
	    top: 20px;
	}
	#container{
		float: inherit;
		right: 100px;
		margin: auto;
		margin-top: 100px;
		width: 500px;
		height: 500px;
		background-color: lightgray;
		
	}
	input{
	position: absolute;

	 bottom: 40px;
		
	}



</style>

</head>
<body>


    <jsp:include page="./Adm_Header.jsp"/>
    <div id="content-wrapper">
        <jsp:include page="./Category_Mgr.jsp"/>
        <div id="container">
            할로
        <input type="button" value="어쩌고">
        </div>
    </div>

</body>
</html>