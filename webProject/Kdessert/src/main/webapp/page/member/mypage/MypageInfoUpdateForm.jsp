<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>마이페이지 | 개인정보</title>

<style type="text/css">
	
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
		width: 1200px;
		margin: auto;
	}
	
	table, tr, th, td {
		border-bottom: 1px solid #BEBEBE;
		border-collapse: collapse;
	}
	
	table {
		border-top: 2px solid black;
	}
	
	th {
		background-color: #F5F5F5;
		text-align: left;
	}

	th, td {
		padding: 15px;
	}
	
	#div_buttons {
		width: 410px;
		margin: auto;
		margin-top: 50px;
	}
	
	#div_buttons input {
		width: 200px;
		padding: 10px;

		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;

		background-color: white;
		color: #7B7B7B;
		font-size: 16px;	
		cursor: pointer;
	}
	
	#div_category {
		float: left;
	}
	#div_content {
		width: 950px;
		float: right;
	}
	#table_form {
		width: 600px;
		height: 500px;
		margin: auto;
	}
	
	#table_form th {
		width: 120px;
	}
	
	input {
		padding: 10px;
		font-size: 16px;
		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;
		width: 300px;
    }
    
    #emailCheck {
        width: 90px;
    	cursor: pointer;
    }
    
    #select_profile {
		background-color: white;
		color: #64473E;
		font-weight: bold;
	}

</style>

</head>

<body>

<div id="wrap">
	<jsp:include page="../commPage/Mem_Header.jsp"/>
	<jsp:include page="../commPage/Category_Main.jsp"/>
	
	<div id="container">
		<div id="div_category">
			<jsp:include page="../commPage/Category_Mypage.jsp"/>
		</div>
		<div id="div_content">
			<h2 style="text-align:center; margin-top: 40px; margin-bottom: 40px; ">개인정보</h2>
			<form action="./update" method="post" onsubmit="return validateForm()">
		
				<table id=table_form>
				
					<tr>
						<th scope="row">
							이름
						</th>
						<td>
							<input id="name" type="text" name="name" value="${memberDto.memNameStr}" required >
						</td>
					</tr>
					<tr>
						<th scope="row">
							아이디
						</th>
						<td>
							${memberDto.memIdStr}
						</td>
					</tr>
					<tr>
						<th scope="row">
							비밀번호
						</th>
						<td>
							<input id="password" type="password" name="password" value="" required oninput="pwdCheck()" onkeyup="valiCheckPwd()">
							<div id="pwdStatus2"></div> <!-- 비밀번호 확인 메시지가 표시될 곳 -->
						</td>
					</tr>
					<tr>
						<th scope="row">
							비밀번호 확인
						</th>
						<td>
							<input id="passwordCheck" type="password" name="passwordCheck" required oninput="pwdCheck()">
							<div id="pwdStatus"></div> <!-- 비밀번호 확인 메시지가 표시될 곳 -->
						</td>
					</tr>
					<tr>
						<th scope="row">
							이메일
						</th>
						<td>
							<input type="email" id="email" name="email" value="${memberDto.memEmailStr}" oninput="resetEmailCheck()" required>
							<input id="emailCheck" type="button" value="중복확인" onclick="checkEmailAvailability()">
							<div id="emailStatus"></div> <!-- 이메일 중복 여부 메시지가 표시될 곳 -->
						</td>
					</tr>
					<tr>
						<th scope="row">
							생년월일
						</th>
						<td>
							${memberDto.memBirthDate}
						</td>
					</tr>
					<tr>
						<th scope="row">
							전화번호
						</th>
						<td>
							<input type="text" name="tel" value="${memberDto.memTelStr}">
						</td>
					</tr>
					<tr>
						<th scope="row">
							주소
						</th>
						<td>
							<input  type="text" name="address" placeholder="기본주소" value="${memberDto.memAddressStr}">
							<br>
							<input  type="text" name="addressSec" placeholder="상세주소" value="${memberDto.memAddressSecStr}">
						</td>
					</tr>
				</table>
				
				<div id="div_buttons">
					<input id="btn_cancle" class="btn_style" type="button" value="취소" onclick="history.go(-1)">
					<input id="btn_update" class="btn_style" type="submit" value="저장하기" style="background-color: #64473E; color: white;">
				</div>
		</form>
		</div>
	</div>
</div>

</body>

<script type="text/javascript">

	var isEmailChecked = false;  // 이메일 중복확인 실행 여부를 체크하는 변수
	var ableEmail = false; //사용가능 이메일
	
	var initialEmail = document.getElementById('email').value;  // 이메일 초기값 저장
	
	////////이메일 중복확인///////
	function checkEmailAvailability() {
	    var email = document.getElementById('email').value;  // 입력된 이메일 가져오기
	
	    if (email.trim() === "") {
	        alert("이메일을 입력해주세요.");
	        return;
	    }
	    
	    if (email == initialEmail){
			return;
	    }
	    
	    var xhr = new XMLHttpRequest();
	    xhr.open('POST', './emailcheck', true);  // 서버 URL로 요청 보냄
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    
	    xhr.onload = function () {
	        if (xhr.status === 200) {
	            // 서버로부터 받은 결과를 #emailStatus div에 표시
	            var response = xhr.responseText;
	            document.getElementById('emailStatus').innerHTML = response;
	            
	            isEmailChecked = true; // 중복확인 실행함
	            
	            // 중복된 이메일
	            if (response.includes("이미 사용 중인 이메일입니다")) {
	                ableEmail = false;
	            } else {
	                ableEmail = true;
	            }
	            
	        } else {
	            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
	        }
	    };
	    
	    xhr.send('email=' + encodeURIComponent(email));  // 아이디를 서버에 전송
	}
	
	function resetEmailCheck() {
	    isEmailChecked = false;  // 이메일 중복 확인을 다시 해야 하므로 false로 설정
	    document.getElementById('emailStatus').innerHTML = '';  // 상태 메시지도 초기화
	}
	
	
	////////비밀번호 확인///////
	function pwdCheck() {
	    var password = document.getElementById("password").value;
	    var passwordCheck = document.getElementById("passwordCheck").value;
		var pwdStatusObj = document.getElementById('pwdStatus');
		
	    //비번과 비번확인이 같지 않을 때
	    if (password !== passwordCheck) {
	    	pwdStatusObj.innerHTML = '입력된 비밀번호가 일치하지 않습니다.';
	    	pwdStatusObj.style.color = 'red';
	    	pwdStatusObj.style.fontSize = '12px';
	    	return;
	    }else if(password == passwordCheck && password !== ''){
	    	pwdStatusObj.innerHTML = '입력된 비밀번호가 일치합니다.';
	    	pwdStatusObj.style.color = 'green';
	    	pwdStatusObj.style.fontSize = '12px';
	    	return;
	    }
	}
	
	var validationPwd = false; //유효한 비밀번호
	
	//비밀번호 유효성 검사
	function valiCheckPwd() {
		var pwd = document.getElementById("password").value;
		var pwdStatusObj = document.getElementById('pwdStatus');
		var pwdStatus2Obj = document.getElementById('pwdStatus2');
		
		//조건 불충족 시 메시지가 출력
		if(!(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(pwd))){
			pwdStatus2Obj.innerHTML = '비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 하며 8자리 이상이어야 합니다.';
			pwdStatus2Obj.style.color = 'red';
			pwdStatus2Obj.style.fontSize = '12px';
			validationPwd = false;
		}
		
		//조건 충족 시 또는 값이 없을 때 초기화
		if(pwd == '' || /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(pwd) ){
			pwdStatus2Obj.innerHTML = '';
			validationPwd = true;
		}
		
		if (pwd == '') {
			pwdStatusObj.innerHTML = '';
		}
		
	}
	
	// 폼을 제출하기 전에 중복확인 여부를 체크하는 함수
	function validateForm() {
	    
		var email = document.getElementById('email').value;  // 입력된 이메일 가져오기
		
	    //이메일 중복확인
	      if (email.trim() !== initialEmail && !isEmailChecked) {
	        alert("이메일 중복을 확인하세요");
	        return false; 
	    }
	    if (email.trim() !== initialEmail && !ableEmail) {
			alert("이미 사용 중인 이메일로는 가입할 수 없습니다");
			return false;
	    }
	    
	    var password = document.getElementById("password").value;
	    var passwordCheck = document.getElementById("passwordCheck").value;
	    
	    //비번과 비번확인이 같지 않을 때
	    if (password !== passwordCheck) {
			alert("비밀번호를 확인해주세요");
			return false;
	    }
	    
	    if (!validationPwd) {
			alert("유효한 비밀번호를 입력해주세요");
			return false;
	    }
	    
	    return true;  // 폼을 정상적으로 제출
	}




</script>

</html>