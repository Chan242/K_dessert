<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>다과 관리 - 수정하기</title>

<style>
/* 전체 레이아웃 */
.container {
    display: flex;
    height: 100vh;
}

/* 사이드바 스타일 */
.sidebar {
    width: 200px;
    background-color: #5A3A2E;
    color: white;
    padding: 20px;
    height: 100vh;
}

.sidebar h2 {
    font-size: 18px;
    margin-bottom: 20px;
}

.sidebar ul {
    list-style: none;
    padding: 0;
}

.sidebar ul li {
    margin: 35px 0; /* 리스트 간격을 20px로 설정 */
}

.sidebar ul li a {
    color: white;
    text-decoration: none;
}

/* 메인 콘텐츠 스타일 */
.content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    flex-grow: 1; /* 사이드바 제외한 남은 공간을 자동으로 차지 */
    padding: 20px;
    background-color: white;
    box-sizing: border-box;
}

/* 폼 스타일 */
.form-container {
    max-width: 600px;
    background: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 100%;
}

.form-group {
    width: 100%;
    max-width: 400px;
    margin-bottom: 15px;
    text-align: left;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input, textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

textarea {
    height: 100px;
}

.btn {
    background-color: #5A3A2E;
    color: white;
    border: none;
    padding: 10px;
    cursor: pointer;
    border-radius: 4px;
    width: 100%;
}

.btn:hover {
    background-color: #4A2D23;
}

/* 기본 라디오 버튼 숨기기 */
.radio-btn {
    display: none;
}

/* 커스텀 동그라미 스타일 */
.custom-radio {
    position: relative;
    padding-left: 30px;
    cursor: pointer;
    font-weight: bold;
    line-height: 20px;
}

.custom-radio:before {
    content: '';
    position: absolute;
    left: 0;
    top: 0;
    width: 20px;
    height: 20px;
    border: 2px solid #ccc;
    border-radius: 50%;
    background-color: white;
    transition: all 0.3s ease;
}

/* 라디오 버튼 선택 시 동그라미 색상 변화 */
.radio-btn:checked + .custom-radio:before {
    background-color: #5A3A2E;
    border-color: #5A3A2E;
}

/* 라디오 버튼 선택 시 안에 체크 표시 */
.radio-btn:checked + .custom-radio:after {
    content: '';
    position: absolute;
    left: 7px;
    top: 7px;
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background-color: white;
}

/* 조회수 숫자 오른쪽 정렬 */
.view-count {
    margin-left: auto; /* 오른쪽으로 정렬 */
    font-weight: bold;
}
</style>
</head>

<body>

<jsp:include page="/page/admin/commPage/Adm_Header.jsp"/>

<div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
        <ul>
            <li><a href="#">주문관리</a></li>
            <li><a href="#">최종 관리</a></li>
            <li><a href="#">제품 관리</a></li>
            <li><a href="#">다과 관리</a></li>
            <li><a href="#">행사 관리</a></li>
            <li><a href="#">통계</a></li>
        </ul>
    </aside>

    <!-- 콘텐츠 영역 -->
    <div class="content">
        <h2>수정하기</h2>
        <div class="form-container">
            <form action="update_snack.jsp" method="post" enctype="multipart/form-data">
                <div class="form-group" style="display: flex; align-items: center;">
                    <label for="id" style="margin-right: 10px;">다과번호</label>
                    <span>1</span>
                </div>

                <div class="form-group">
                    <label for="name">다과명</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="desc">설명</label>
                    <textarea id="desc" name="desc"></textarea>
                </div>
                <div class="form-group">
                    <label for="image">이미지</label>
                    <input type="file" id="image" name="image">
                </div>
                <!-- 공개여부를 한 줄로 배치: 왼쪽에 '공개여부' 레이블, 오른쪽에 동그라미 체크바와 텍스트 -->
                <div class="form-group" style="display: flex; align-items: center;">
                    <label style="margin-right: 20px;">공개여부</label>
                    <div style="display: flex; align-items: center;">
                        <input type="radio" name="public" value="yes" id="yes" class="radio-btn">
                        <label for="yes" class="custom-radio" style="margin-right: 10px;">공개</label>
                        <input type="radio" name="public" value="no" id="no" class="radio-btn">
                        <label for="no" class="custom-radio">비공개</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="tag">태그</label>
                    <input type="text" id="tag" name="tag">
                </div>
                <!-- 조회수 입력 칸을 제거하고 숫자만 표시 -->
                <div class="form-group" style="display: flex; align-items: center;">
                    <label for="views" style="margin-right: 10px;">조회수</label>
                    <span class="view-count">1</span> <!-- 조회수 숫자만 표시 -->
                </div>
                <button class="btn" type="submit">저장하기</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
