<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<style type="text/css">

	#wrap {
		width:1920px;
	}

	body {
		margin: 0px;
	}
	
	#div_signup {
		width: 700px;
		padding-bottom: 100px;
		padding-top: 50px;
	}
	
	#div_buttons {
		width: 410px;
	}
	
	#div_signup, #table_in_form, #div_buttons {
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
	
	
	input {
		padding: 10px;
		font-size: 16px;
		border: 1px solid #ccc;
		border-radius: 4px;
		outline: none;
		transition: border-color 0.3s;
		width: 300px;
    }
    
    .btn_style{
		width: 200px;
		cursor: pointer;
    }
    
    #btn_cancle {
		background-color: white;
		color: #7B7B7B;
    }
    
    #btn_signUp {
    	background-color: #64473E;
    	color: white;
    }
    
    #idCheck, #emailCheck {
    	width: 100px;
    	cursor: pointer;
    }


</style>

</head>
<body>

<div id="wrap">

	<jsp:include page="./Auth_Header.jsp"/>

	<div id="div_signup">
	
		<h1 style="text-align: center;">회원가입</h1>
		
		<form id="signupForm" action="./signup" method="post" onsubmit="return validateForm()">
		
			<table id=table_in_form>
			
				<tr>
					<th scope="row">
						이름
					</th>
					<td>
						<input type="text" name="mname" required>
					</td>
				</tr>
				<tr>
					<th scope="row">
						아이디
					</th>
					<td>
						<input type="text" id="id" name="id" required oninput="resetIdCheck()">
						<input id="idCheck" type="button" value="중복확인" onclick="checkIdAvailability()">
						<div id="idStatus"></div> <!-- 중복 여부 메시지가 표시될 곳 -->
					</td>
				</tr>
				<tr>
					<th scope="row">
						비밀번호
					</th>
					<td>
						<input id="password" type="password" name="password" required oninput="pwdCheck()">
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
						<input type="email" id="email" name="email" required oninput="resetEmailCheck()">
						<input id="emailCheck" type="button" value="중복확인" onclick="checkEmailAvailability()">
						<div id="emailStatus"></div> <!-- 이메일 중복 여부 메시지가 표시될 곳 -->
					</td>
				</tr>
				<tr>
					<th scope="row">
						생년월일
					</th>
					<td>
						<input  type="text" name="birth" placeholder="YYYY-MM-DD" required>
					</td>
				</tr>
				<tr>
					<th scope="row">
						전화번호
					</th>
					<td>
						<input type="text" name="tel" required>
					</td>
				</tr>
				<tr>
					<th scope="row">
						주소
					</th>
					<td>
						<input  type="text" name="address" placeholder="기본주소" required>
						<br>
						<input  type="text" name="addressSec" placeholder="상세주소" required>
					</td>
				</tr>
			</table>
			
			<br>
			
			<div id="div_buttons">
				<input id="btn_cancle" class="btn_style" type="button" value="취소" onclick="history.go(-1)">
				<input id="btn_signUp" class="btn_style" type="submit" value="가입하기" >
			</div>
		</form>
	
	</div>
	
	<jsp:include page="../page/member/commPage/Mem_Footer.jsp"/>

</div>

</body>

<script type="text/javascript">

	var isIdChecked = false;  // 아이디 중복확인 실행 여부를 체크하는 변수
	var ableId = false; //사용가능 아이디
	var isEmailChecked = false;  // 이메일 중복확인 실행 여부를 체크하는 변수
	var ableEmail = false; //사용가능 이메일
	
	
	////////아이디 중복확인///////
	function checkIdAvailability() {
	    var id = document.getElementById('id').value;  // 입력된 아이디 가져오기
	
	    if (id.trim() === "") {
	        alert("아이디를 입력해주세요.");
	        return;
	    }
	    
	    var xhr = new XMLHttpRequest();
	    xhr.open('POST', './idcheck', true);  // 서버 URL로 요청 보냄
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    
	    xhr.onload = function () {
	        if (xhr.status === 200) {
	            // 서버로부터 받은 결과를 #idStatus div에 표시
	            var response = xhr.responseText;
	            document.getElementById('idStatus').innerHTML = response;
	            isIdChecked = true; // 중복확인 실행함
	            
	            // 중복된 아이디
	            if (response.includes("이미 사용 중인 아이디입니다")) {
	                ableId = false;
	            } else {
	                ableId = true;
	                
	            }
	        } else {
	            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
	        }
	    };
	    
	    xhr.send('id=' + encodeURIComponent(id));  // 아이디를 서버에 전송
	}
	
	function resetIdCheck() {
	    isIdChecked = false;  // 아이디 중복 확인을 다시 해야 하므로 false로 설정
	    document.getElementById('idStatus').innerHTML = '';  // 상태 메시지도 초기화
	}
	
	
	
	////////이메일 중복확인///////
	function checkEmailAvailability() {
	    var email = document.getElementById('email').value;  // 입력된 이메일 가져오기
	
	    if (email.trim() === "") {
	        alert("이메일을 입력해주세요.");
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
	    	return;
	    }else {
	    	pwdStatusObj.innerHTML = '입력된 비밀번호가 일치합니다.';
	    	pwdStatusObj.style.color = 'green';
	    	return;
	    }
	    
	}
	
	
	//회원가입 폼을 제출하기 전에 중복확인 여부를 체크하는 함수
	function validateForm() {

		//아이디 중복확인
	    if (!isIdChecked) {
	        alert("아이디 중복을 확인하세요");
	        return false;  // 폼 제출을 막음
	    }
	    if (!ableId) {
			alert("이미 사용 중인 아이디로는 가입할 수 없습니다");
			return false; 
	    }
	    
	    //이메일 중복확인
	    if (!isEmailChecked) {
	        alert("이메일 중복을 확인하세요");
	        return false; 
	    }
	    if (!ableEmail) {
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
	    
	    return true;  // 폼을 정상적으로 제출
	}
</script>



</html>