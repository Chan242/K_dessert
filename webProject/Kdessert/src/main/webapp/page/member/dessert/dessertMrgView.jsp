<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>다과 관리</title>

<style type="text/css">

/* body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
} */

.sidebar {
    width: 200px;
    background-color: #5c3b1e;
    color: white;
    height: 100vh;
    float: left;
    padding: 20px;
}

.sidebar ul {
    list-style: none;
    padding: 0;
}

.sidebar ul li {
    margin: 20px 0;
}

.sidebar ul li a {
    color: white;
    text-decoration: none;
}

.main-content {
    margin-left: 220px;
    padding: 20px;
}

.search-bar {
	text-align: right; /* 🔹 검색창을 오른쪽 정렬 */
	margin-bottom: 10px;
}

.search-bar input {
	padding: 5px;
	width: 200px;
}

.search-bar button {
	padding: 5px 10px;
	background-color: #a67c52;
	color: white;
	border: none;
	cursor: pointer;
}

table {
    width: 100%;
    border-collapse: collapse;
    background: white;
}

th, td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
}

th {
    background-color: #a67c52;
    color: white;
}

.pagination {
	text-align: center; /* 🔹 페이지네이션 가운데 정렬 */
	margin-top: 20px;
}

.pagination a {
	display: inline-block;
	padding: 8px 12px;
	margin: 0 5px;
	text-decoration: none;
	background-color: #ddd;
	color: black;
	border-radius: 5px;
}

.pagination a:hover {
	background-color: #a67c52;
	color: white;
}


</style>


</head>

<body>
<!-- <header> -->
		<jsp:include page="/page/admin/commPage/Adm_Header.jsp"/>
		<jsp:include page="/page/admin//commPage/Category_Mgr.jsp"/> 
		

    <!-- 메인 컨텐츠 -->
    <main class="content">
        <h2>다과 관리</h2>
        
        <!-- 검색창 -->
        <div class="search-bar">
            <input type="text" placeholder="검색어 입력">
            <button>검색</button>
        </div>

        <!-- 테이블 -->
        <table>
            <thead>
                <tr>
                    <th>다과번호</th>
                    <th>다과명</th>
                    <th>공개여부</th>
                    <th>조회수</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>정과</td>
                    <td>공개</td>
                    <td>10</td>
                    <td><button>삭제</button></td>
                </tr>
            </tbody>
        </table>

        <!-- 페이지네이션 -->
        <div class="pagination">
            <a href="#">&lt;</a>
            <a href="#">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">&gt;</a>
        </div>
    </main>

</body>
</body>
</html>  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>다과 관리</title>




<style>
/* 전체 스타일 */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f8f8f8;
}

/* 사이드바 */
.sidebar {
    width: 200px;
    background-color: #5c3b1e;
    color: white;
    height: 100vh;
    float: left;
    padding: 20px;
}

.sidebar ul {
    list-style: none;
    padding: 0;
}

.sidebar ul li {
    margin: 20px 0;
}

.sidebar ul li a {
    color: white;
    text-decoration: none;
    font-weight: bold;
}

/* 메인 컨텐츠 */
.main-content {
    margin-left: 220px;
    padding: 20px;
}

/* 검색창 */
.search-bar {
    text-align: right;
    margin-bottom: 10px;
}

.search-bar input {
    padding: 5px;
    width: 200px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.search-bar button {
    padding: 5px 10px;
    background-color: #a67c52;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 4px;
}

/* 테이블 스타일 */
table {
    width: 100%;
    border-collapse: collapse;
    background: white;
}

th, td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
}

th {
    background-color: #a67c52;
    color: white;
}

/* 페이지네이션 */
.pagination {
    text-align: center;
    margin-top: 20px;
}

.pagination a {
    display: inline-block;
    padding: 8px 12px;
    margin: 0 5px;
    text-decoration: none;
    background-color: #ddd;
    color: black;
    border-radius: 5px;
}

.pagination a:hover {
    background-color: #a67c52;
    color: white;
}
</style>

<script>

function deletDagwa(dagwId) {
	if (confirm("정말 삭제하시겠습니까?")){
		location.href = "dagwa_delete.jsp?dawaId="
		+ dagwId;
	}
}

</script>


</head>

<body>
		<%-- 공통 헤더 포함 --%>
		<jsp:include page="/page/admin/commPage/Adm_Header.jsp"/>
		<jsp:include page="/page/member/commPage/Mem_Header.jsp"/>
		<jsp:include page="/page/member/commPage/Category_Main.jsp"/>
<%-- 	<jsp:include page="/page/admin/commPage/Adm_Header.jsp"/>
		<jsp:include page="/page/admin/commPage/Category_Mgr.jsp"/>
 --%>
		
	
<!-- 사이드바 -->
<!-- <div class="sidebar">
    <h2>다과 관리</h2>
    <ul>
        <li><a href="#">추천 관리</a></li>
        <li><a href="#">최종 관리</a></li>
        <li><a href="#">제품 관리</a></li>
        <li><a href="#">다과 관리</a></li>
        <li><a href="#">행사 관리</a></li>
        <li><a href="#">통계</a></li>
    </ul>
</div> -->

<!-- 메인 컨텐츠 -->
<div class="main-content">
  
    
    <!-- 검색창 -->
    <div class="search-bar">
        <input type="text" placeholder="검색어 입력">
        <button>검색</button>
    </div>

    <!-- 테이블 -->
    <table>
        <thead>
            <tr>
                <th>다과번호</th>
                <th>다과명</th>
                <th>공개여부</th>
                <th>조회수</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
			<tr>                       
                <td>1</td>
                <td>정과</td>
                <td>공개</td>
                <td>10</td>
                <td><button>삭제</button></td>
            </tr>
        </tbody>
    </table>

    <!-- 페이지네이션 -->
    <div class="pagination">
        <a href="#">&lt;</a>
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">&gt;</a>
    </div>
</div>

</body>
</html>