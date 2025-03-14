<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">

	body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	
	#container{
		/* 푸터 맨 하단으로 고정할 수 있게 추가 */
		min-height: 800px; /* 최소높이 지정 */
		width: 900px;
		margin: auto;
		overflow: hidden;
	}
	
	
	#rolling{
		width: 3600px;
		height: 500px;
/* 		margin-top: 100px; */
		background-color: lightgray;
		position: relative;
		display: flex;
	}
	
	#rolling img{
	
		width: 900px;	
		height: 506px;
	}

	   
</style>
<meta charset="UTF-8">
<title>알근달근 | 한국 전통 과자 전문점</title>
</head>
<script type="text/javascript">

	function moveOn() {
		 //rolling이라는 아이디를 가진 객체 생성
		 var rollingObj =  document.getElementById("rolling");
		 var position = 0;
		 
		 
		 // 이미지 너비 (900px x 3개 이미지 크기)
		    var maxPosition = 2700; // 3개의 이미지 크기
		    var imageWidth = 900; // 각 이미지의 크기 (가로)
		 
		 function frame() {
			 if (position >= maxPosition) {
		            setTimeout(function() {
		                // 1초 대기 후 위치를 0으로 초기화하고 애니메이션 다시 시작
		                position = 0;
		                rollingObj.style.right = position + 'px';  // 위치 초기화
		                requestAnimationFrame(frame);  // 애니메이션 다시 시작
		            }, 4000);
		        }else if(position>=900 && position < 901){
		        	// position 값이 90 이상이고 maxPosition 미만일 때 멈췄다가 이동
		        	setTimeout(function() {
		                // 1초 대기 후 위치를 0으로 초기화하고 애니메이션 다시 시작
		                position += 30;
		                rollingObj.style.right = position + 'px';  // 위치 초기화
		                requestAnimationFrame(frame);  // 애니메이션 다시 시작
		            }, 4000);
		        }else if(position>=1800 && position < 1801){
		        	// position 값이 90 이상이고 maxPosition 미만일 때 멈췄다가 이동
		        	setTimeout(function() {
		                // 1초 대기 후 위치를 0으로 초기화하고 애니메이션 다시 시작
		                position += 30;
		                rollingObj.style.right = position + 'px';  // 위치 초기화
		                requestAnimationFrame(frame);  // 애니메이션 다시 시작
		            }, 4000);
		        }else{
		            // position 값이 0 이상이고 maxPosition 미만일 때 계속해서 이동
		            
		            position += 30;

		            // position이 maxPosition을 넘어가지 않도록 제한
		            if (position > maxPosition) {
		                position = maxPosition;
		            }

		            rollingObj.style.right = position + 'px';  // right값을 사용하여 이동

		            // 1ms 후 애니메이션을 계속 반복
		            setTimeout(function() {
		                requestAnimationFrame(frame);  // 애니메이션 계속 실행
		            }, 1);
		        }
			 
		}
 
		 requestAnimationFrame(frame);
	}
	
	window.onload = moveOn;
</script>
<body>

	<c:if test="${member.getMemAdmCheckInt() == 1}">
		<jsp:include page="./page/admin/commPage/Move_MrgPage.jsp"/>
	</c:if>
	
	<jsp:include page="./page/member/commPage/Mem_Header.jsp"/>
	<jsp:include page="./page/member/commPage/Category_Main.jsp"/>
	
	<div id="container">
		<div id="rolling">
			<a href="/Kdessert/dessert/list">
				<img src="./bannerImg/img0.jpg">
			</a>
			<a href="/Kdessert/product/list">
				<img src="./bannerImg/img1.jpg">
			</a>
			<a href="/Kdessert/board">	
				<img src="./bannerImg/img2.jpg">
			</a>
			<a href="/Kdessert/dessert/list">	
				<img src="./bannerImg/img0.jpg">
			</a>
		</div>
	</div>
	<jsp:include page="./page/member/commPage/Mem_Footer.jsp"/>
	

</body>
</html>