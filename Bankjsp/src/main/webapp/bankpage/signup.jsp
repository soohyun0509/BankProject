<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<link href="../css/signup.css" rel="stylesheet">
</head>
<body>
	
	<%@include file="../header.jsp" %>
	
	<div class=b_container>
		<form class="signup">
			<h1 class="singupHead">Create Account</h1>
			<div class="inform" id="signup_refresh">
				<div>
					<input  type="text" id="name" onkeyup="nameCheck()" placeholder="성함" class="inputType">
					<div class="check"></div>
				</div>
				<div>
					<input type="text" id="phone" onkeyup="phoneCheck()" placeholder="전화번호" class="inputType">
					<div class="check"></div>
				</div>
				<div>
					<input type="password" id="password" onkeyup="passwordCheck()" placeholder="비밀번호" class="inputType"><br>
					<div class="check"></div>
				</div>
				
				<div class="addressInfo">
					<h5 class="addressHead"> 주소 정보 입력 </h5>
					<div>
						<!-- 카카오 우편 API -->
						<input type="text" id="sample4_postcode" placeholder="우편번호" class="addressType">
						<span><input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="addressBtn"></span>
					</div>
					<div>
						<input type="text" id="sample4_roadAddress" placeholder="도로명주소" class="addressType">
						<input type="text" id="sample4_jibunAddress" placeholder="지번주소" class="addressType">
						
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="sample4_detailAddress" placeholder="상세주소" class="addressType">
						
					</div>
					
				</div>
				<div class="check address"></div>	
				<button type="button" class="signupBtn" onclick="signup()">계좌 생성하기</button>
			</div>
		</form>
	
		<div class="resultInfo">
			<div class="resultAccount">
			</div>
			<div class="securecard">				
			</div>
		</div>
	
		
		

	</div>
	<%@include file="../footer.jsp" %>
	
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript" src="../js/signup.js"></script>
	
</body>
</html>