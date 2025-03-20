<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		overflow-y: scroll;
	}
	
	#wrap {
		overflow-x: hidden;
		min-width: 1340px;
	}
	
	#container {
		width: 1280px;
		margin: 0 auto;
		padding-top: 60px;
		padding-bottom: 200px;
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

	.eventList_fun {
		text-decoration-line: none;
		font-weight: bold;
		color: black;
	}
	
	#calendar_table {
		width: 1000px;
		height: 800px;
		border-collapse: collapse;
	}
	
	#calendar_table td, #calendar_table th {
		width: 30px;
	}
	
	#calendar_table td {
		border: 1px solid #d6d6d6;
		vertical-align: top;
		height: 30px;
		padding: 15px;
	}
	
	#calendar_table td:first-child {
		border-left-style: none;
	}
	
	#calendar_table td:last-child {
		border-right-style: none;
	}
	
	#calendar_table th {
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
	
	
	/* 	행사 조회 테이블 관련 css */
	#eventList {
	width: 1000px;
	margin: auto;
	margin-top: 30px;
	}
	
	#eventList_table {
		width: 1000px;
		height: 200px;
		margin: auto;
		margin-bottom: 10px;
		border-collapse: collapse;
	}
	
	#eventList_table th, #eventList_table td {
		border: 1px solid black;
	}
	
	#eventList_table th {
		width: 100px;
	}
	
	#eventList_table td {
		padding: 15px;
	}
	
	.image_td {
		width: 180px;
	}
	
	/* 	페이징 버튼 관련 css */
	#div_pageBtn {
		display: flex;
		justify-content: center;
		align-items: center;
		gap: 10px; /* 버튼 간격 */
		margin-top: 20px;
	}
	
	.page-btn {
		text-decoration: none;
		color: black;
		font-size: 16px;
		padding: 5px 10px;
		border-radius: 5px;
		transition: 0.3s;
	}
	
	.page-btn:hover {
		background-color: #f0f0f0;
	}
	
	.active {
		text-decoration: underline;
		font-weight: bold;
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
						<span id="calendar_header_day_span">${year}년 ${month}월
							${date}일</span>
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
								<td><c:if test="${day != 0}">
										<c:set var="eventProcessed" value="false" />
										<c:choose>
											<c:when test="${day == date && (firstDayOfWeek + day - 1) % 7 == 1}">
												<span class="day today sunday">${day}</span>
												
												<c:forEach var="event" items="${eventMonthList}">
													<c:set var="eventDay" value="${fn:substring(event.eveEventDate, 8, 10)}" />
				                                  	<c:if test="${eventDay == day && !eventProcessed}">
							                            <a class="eventList_fun" href="./list?year=${year}&month=${month}&date=${day}&pageNum=1&pageSize=${pageSize}"><!--체크용  -->
							                            	행사보기▽
							                            </a>
							                            <c:set var="eventProcessed" value="true" />
							                        </c:if>
												</c:forEach>
												
											</c:when>
	
											<c:when test="${day == date && (firstDayOfWeek + day - 1) % 7 == 0}">
												<span class="day today saturday">${day}</span>
												
												<c:forEach var="event" items="${eventMonthList}">
													<c:set var="eventDay" value="${fn:substring(event.eveEventDate, 8, 10)}" />
				                                    <c:if test="${eventDay == day && !eventProcessed}">
							                            <a class="eventList_fun" href="./list?year=${year}&month=${month}&date=${day}&pageNum=1&pageSize=${pageSize}">
							                            	행사보기▽
							                            </a>
							                            <c:set var="eventProcessed" value="true" />
							                        </c:if>
												</c:forEach>
											</c:when>
	
											<c:when test="${(firstDayOfWeek + day - 1) % 7 == 1}">
												<span class="day sunday">${day}</span>
												
												<c:forEach var="event" items="${eventMonthList}">
													<c:set var="eventDay" value="${fn:substring(event.eveEventDate, 8, 10)}" />
				                                    <c:if test="${eventDay == day && !eventProcessed}">
				                                    	<a class="eventList_fun" href="./list?year=${year}&month=${month}&date=${day}&pageNum=1&pageSize=${pageSize}">
							                            	행사보기▽
							                            </a>
							                            <c:set var="eventProcessed" value="true" />
							                        </c:if>
												</c:forEach>
											</c:when>
	
											<c:when test="${(firstDayOfWeek + day - 1) % 7 == 0}">
												<span class="day saturday">${day}</span>
												
												<c:forEach var="event" items="${eventMonthList}">
													<c:set var="eventDay" value="${fn:substring(event.eveEventDate, 8, 10)}" />
				                                    <c:if test="${eventDay == day && !eventProcessed}">
							                            <a class="eventList_fun" href="./list?year=${year}&month=${month}&date=${day}&pageNum=1&pageSize=${pageSize}">
							                            	행사보기▽
							                            </a>
							                            <c:set var="eventProcessed" value="true" />
							                        </c:if>
												</c:forEach>
											</c:when>
	
											<c:when test="${day == date}">
												<span class="day today">${day}</span>
												
												<c:forEach var="event" items="${eventMonthList}">
													<c:set var="eventDay" value="${fn:substring(event.eveEventDate, 8, 10)}" />
				                                   <c:if test="${eventDay == day && !eventProcessed}">
							                            <a class="eventList_fun" href="./list?year=${year}&month=${month}&date=${day}&pageNum=1&pageSize=${pageSize}">
							                            	행사보기▽
							                            </a>
							                            <c:set var="eventProcessed" value="true" />
							                        </c:if>
												</c:forEach>
											</c:when>
	
											<c:otherwise>
												<span class="day">${day}</span>
												
												<c:forEach var="event" items="${eventMonthList}">
													<c:set var="eventDay" value="${fn:substring(event.eveEventDate, 8, 10)}" />
				                                    <c:if test="${eventDay == day && !eventProcessed}">
							                            <a class="eventList_fun" href="./list?year=${year}&month=${month}&date=${day}&pageNum=1&pageSize=${pageSize}">
							                            	행사보기▽
							                            </a>
							                            <c:set var="eventProcessed" value="true" />
							                        </c:if>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</c:if></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>

			</div>
				<div id="eventList">
					<c:forEach var="event" items="${eventDateList}">
						<c:set var="eventDay" value="${fn:substring(event.eveEventDate, 8, 10)}" />
		                <c:if test="${eventDay == date}">
							<div id="eventList_table_div"></div>
								<table id="eventList_table">
									<tr>
										<td rowspan="3" class="image_td" style="text-align: center;"> 이미지 </td>
										<th scope="row">행사명</th>
										<td>${event.getEveNameStr()}</td>
									</tr>
									<tr>
										<th scope="row">날짜</th>
										<td>${event.getEveEventDate()}</td>
									</tr>
									<tr>
										<th scope="row">설명</th>
										<td>${event.getEveExplainStr()}</td>
									</tr>
								</table>
		                </c:if>
					</c:forEach>
			
					<div id="eventList_pageBtn">
						<!------------------ 페이지 버튼 ------------------>
						<div id="div_pageBtn">
							<c:if test="${pageNum > 1}">
								<a href="?year=${year}&month=${month}&date=${date}&pageNum=${pageNum - 1}&pageSize=${pageSize}" class="page-btn">&lt;</a>
							</c:if>
							
							<c:choose>
								<c:when test="${totalPage == 1}">
									<span class="page-btn active">1</span>
								</c:when>
								<c:when test="${totalPage <= 4}">
									<c:forEach begin="1" end="${totalPage}" var="i">
										<c:choose>
											<c:when test="${i == pageNum}">
												<span class="page-btn active">${i}</span>
											</c:when>
											<c:otherwise>
												<a href="?year=${year}&month=${month}&date=${date}&pageNum=${i}&pageSize=${pageSize}" class="page-btn">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:when test="${totalPage >= 5}">
									<c:choose>
										<c:when test="${pageNum == 1}">
											<c:forEach begin="1" end="5" var="i">
												<c:choose>
													<c:when test="${i == pageNum}">
														<span class="page-btn active">${i}</span>
													</c:when>
													<c:otherwise>
														<a href="?year=${year}&month=${month}&date=${date}&pageNum=${i}&pageSize=${pageSize}" class="page-btn">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:when test="${pageNum == 2}">
											<c:forEach begin="1" end="5" var="i">
												<c:choose>
													<c:when test="${i == pageNum}">
														<span class="page-btn active">${i}</span>
													</c:when>
													<c:otherwise>
														<a href="?year=${year}&month=${month}&date=${date}&pageNum=${i}&pageSize=${pageSize}" class="page-btn">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:when test="${pageNum == totalPage - 1}">
											<c:forEach begin="${totalPage - 4 < 1 ? 1 : totalPage - 4}" end="${totalPage}" var="i">
												<c:choose>
													<c:when test="${i == pageNum}">
														<span class="page-btn active">${i}</span>
													</c:when>
													<c:otherwise>
														<a href="?year=${year}&month=${month}&date=${date}&pageNum=${i}&pageSize=${pageSize}" class="page-btn">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:when test="${pageNum == totalPage}">
											<c:forEach begin="${totalPage - 4 < 1 ? 1 : totalPage - 4}" end="${totalPage}" var="i">
												<c:choose>
													<c:when test="${i == pageNum}">
														<span class="page-btn active">${i}</span>
													</c:when>
													<c:otherwise>
														<a href="?year=${year}&month=${month}&date=${date}&pageNum=${i}&pageSize=${pageSize}" class="page-btn">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach begin="${pageNum - 2 < 1 ? 1 : pageNum - 2}" end="${pageNum + 2 > totalPage ? totalPage : pageNum + 2}" var="i">
												<c:choose>
													<c:when test="${i == pageNum}">
														<span class="page-btn active">${i}</span>
													</c:when>
													<c:otherwise>
														<a href="?year=${year}&month=${month}&date=${date}&pageNum=${i}&pageSize=${pageSize}" class="page-btn">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:when>
							</c:choose>
							
							<c:if test="${pageNum < totalPage}">
								<a href="?year=${year}&month=${month}&date=${date}&pageNum=${pageNum + 1}&pageSize=${pageSize}" class="page-btn">&gt;</a>
							</c:if>

						</div>
	
						<!------------------ 페이지 버튼 ------------------>
					</div>
				</div>
		</div>

		<jsp:include page="../commPage/Mem_Footer.jsp" />

	</div>


</body>

<script type="text/javascript">

//이전 날짜로 가는 함수
	function goBeforDate() {
		var year = Number(${year});
		var month = Number(${month});
		var date = Number(${date});
	
		if (date == 1) {
			if (month == 1) {
				year = year - 1;
				month = 12;
				location.href = "./list?year=" + year + "&month=" + month + "&date=" + 31 + "&pageNum=1&pageSize=" + ${pageSize};
				return;
			} else {
				month = month - 1;
				var prevMonthLastDay = getLastDayOfMonth(year, month);
				location.href = "./list?year=" + year + "&month=" + month + "&date=" + prevMonthLastDay + "&pageNum=1&pageSize=" + ${pageSize};
				return;
			}
		}
	
		date = date - 1;
		location.href = "./list?year=" + year + "&month=" + month + "&date=" + date + "&pageNum=1&pageSize=" + ${pageSize};
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
				location.href="./list?year=" + year + "&month=" + month + "&date=" + 1 + "&pageNum=1&pageSize=" + ${pageSize};
				return;
			} else {
				month = month + 1;
				date = 1;
				location.href="./list?year=" + year + "&month=" + month + "&date=" + date + "&pageNum=1&pageSize=" + ${pageSize};
				return;
			}
		}
		
		date = date + 1 ;
		location.href="./list?year=" + year + "&month=" + month + "&date=" + date + "&pageNum=1&pageSize=" + ${pageSize};
	}
	
</script>

</html>