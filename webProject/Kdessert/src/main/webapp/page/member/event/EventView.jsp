<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>알근달근 | 행사</title>

<style type="text/css">

/* 	기본 레이아웃 */
	body { 
		width: 100%;
		margin: 0px;
		overflow-y:scroll;  
	}
	
	#wrap {
		overflow-x: hidden;
		min-width: 1340px;
	}
	
	#container {
		width: 1280px;
		margin: 0 auto;
		padding-top: 60px;
		padding-bottom: 250px;
	}
	
	#select_event {
		border-bottom: 2px solid #64473E;
	}
	
/* 	달력 */

	#calendar {
		width: 1000px;
		margin: auto;
	}

	#calendar_header {
		height: 50px;
		display: flex;
   		align-items: center;
   		margin: auto;
   		margin-bottom: 15px;
   		justify-content: center;
	}
	
	#calendar_header_day_span {
		font-size: 30px;
		font-weight: 100;
	}
	
	#calendar_header_day_div {
		width: 230px;
		text-align: center;
		margin-left: 10px;
		margin-right: 5px;
	}
	
	.arrow-prev, .arrow-next {
    position: relative;
    
	}
	.arrow-prev:before {
	    position: absolute;
	    left: 0;
	    top: 0;
	    content: '';
	    width: 15px;
	    height: 15px;
	    border-top: 2px solid #000; 
	    border-right: 2px solid #000;
		transform: rotate(225deg); 
	}
	.arrow-next:before {
	    position: absolute;
	    left: 0;
	    top: 0;
	    content: '';
	    width: 15px;
	    height: 15px;
	    border-top: 2px solid #000; 
	    border-right: 2px solid #000;
 	    transform: rotate(45deg); 
	}
	
	.arrow {
		width: 15px;
		height: 15px;
	}
	
	.arrow:hover {
		cursor: pointer;
	}

/* 	#left_btn { */
/* 	    width: 0; */
/* 	    height: 0; */
/* 	    border-top: 10px solid transparent; */
/* 	    border-bottom: 10px solid transparent; */
/* 	    border-right: 10px solid black; */
/* 	    border-left: 10px solid transparent; */
/* 	    float: left; */
	    
/* 	    margin: 10px; */
/* 	} */
	
/* 	#right_btn { */
/* 	    width: 0; */
/* 	    height: 0; */
/* 	    border-top: 10px solid transparent; */
/* 	    border-bottom: 10px solid transparent; */
/* 	    border-right: 10px solid transparent; */
/* 	    border-left: 10px solid black; */
/* 		clear: both;	     */
/* 	    margin: 10px; */
/* 	} */

	#calendar_table {
	    width: 1000px;
	    height: 800px;
	    border-collapse: collapse;
	}
	td, th {
	    width: 30px;
	}
	td {
	    border: 1px solid #d6d6d6;
		vertical-align: top;
		height: 30px;
		padding: 15px;
	}
	td:first-child {
		border-left-style: none;
	}
	td:last-child {
		border-right-style: none;
	}
	
	th {
	    height: 15px;
	    border-top: 2px solid black;
	}
	
	.sunday {
        color: red; /* 일요일은 빨간색으로 표시 */
    }
    .saturday {
        color: blue; /* 토요일은 파란색으로 표시 */
    }
    .today {
        font-weight: bold; /* 오늘 날짜는 볼드체로 강조 */
    }
	
	.day {
    	font-size: 16px;
	}
	
    
	
</style>

</head>

<body>
<div id="wrap">

	<jsp:include page="../commPage/Mem_Header.jsp" />
	<jsp:include page="../commPage/Category_Main.jsp" />
	
	<div id="container">
		<div id="calendar">
		
			<div id="calendar_header">
<!-- 				<div id="left_btn" onclick="goBeforDate()"></div> -->
				<div class="arrow">
					<span class="arrow-prev" onclick="goBeforDate()"></span>
				</div>
				
				<div id="calendar_header_day_div">
					<span id="calendar_header_day_span">${year}년 ${month}월 ${date}일</span>
				</div>
				
				<div class="arrow">
					<span class="arrow-next" onclick="goAfterDate()"></sapn>
				</div>
				
<!-- 				<div id="right_btn" onclick="goAfterDate()"></div> -->
			</div>
			
			<table id="calendar_table">
			    <tr>
			        <c:forEach var="day" items="${daysOfWeek}">
			            <th>${day}</th>
			        </c:forEach>
			    </tr>
			    <c:forEach var="week" items="${calendar}">
			        <tr>
			            <c:forEach var="day" items="${week}">
			                <td>
			                    <c:if test="${day != 0}">
			                    		<c:choose>
				                            <c:when test="${day == date && (firstDayOfWeek + day - 1) % 7 == 1}">
				                               	<span class="day today sunday">${day}</span>
				                            </c:when>
				                            
				                            <c:when test="${day == date && (firstDayOfWeek + day - 1) % 7 == 0}">
				                                <span class="day today saturday">${day}</span> 
				                            </c:when>
				
				                            <c:when test="${(firstDayOfWeek + day - 1) % 7 == 1}">
				                                <span class="day sunday">${day}</span>
				                            </c:when>
				
				                            <c:when test="${(firstDayOfWeek + day - 1) % 7 == 0}">
				                                <span class="day saturday">${day}</span>
				                            </c:when>
				                            
				                            <c:when test="${day == date}">
				                                <span class="day today">${day}</span> 
				                            </c:when>
				
				                            <c:otherwise>
				                                <span class="day">${day}</span> 
				                            </c:otherwise>
			                            </c:choose>
			                    </c:if>
			                </td>
			            </c:forEach>
			        </tr>
			    </c:forEach>
			</table>
		
		</div>
	</div>
	
	<jsp:include page="../commPage/Mem_Footer.jsp"/>
	
</div>


</body>

<script type="text/javascript">

	// 이전 날짜로 가는 함수
	function goBeforDate() {
		var year = Number(${year});
		var month = Number(${month});
		var date = Number(${date});
	
		if (date == 1) {
			if (month == 1) {
				year = year-1;
				month = 12;
				location.href="./list?year=" + year + "&month=" + month + "&date=" + 31;
				return;
			} else {
				month = month - 1;
				var prevMonthLastDay = getLastDayOfMonth(year, month);
				location.href="./list?year=" + year + "&month=" + month + "&date=" + prevMonthLastDay;
				return;
			}
		}
		
		date = date - 1;
		location.href="./list?year=" + year + "&month=" + month + "&date=" +  date;
	}
	
	// 이전 달의 마지막 날을 구하는 함수
	function getLastDayOfMonth(year, month) {
	    var calendar = new Date(year, month, 0);  // 해당 월의 0일은 이전 달의 마지막 날을 의미
	    return calendar.getDate();  // 그 달의 마지막 날짜를 반환
	}
	
	// 다음 날짜로 가는 함수
	function goAfterDate() {
		var year = Number(${year});
		var month = Number(${month});
		var date = Number(${date});
	
		if (date == ${lastDay}){
			if (month == 12) {
				year = year + 1;
				month = 1;
				location.href="./list?year=" + year + "&month=" + month + "&date=" + 1;
				return;
			} else {
				month = month + 1;
				date = 1;
				location.href="./list?year=" + year + "&month=" + month + "&date=" + date;
				return;
			}
		}
		
		date = date + 1 ;
		location.href="./list?year=" + year + "&month=" + month + "&date=" + date;
	}
	
</script>

</html>