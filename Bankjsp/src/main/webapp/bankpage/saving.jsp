<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- SweetAlert2 테마 -->
	<link rel="stylesheet" href="sweetalert2.min.css">
	
	<link rel="stylesheet" href="../css/saving.css">
</head>
<body>


	<%@include file="../header.jsp" %>
			<!-- 로그인 안했으면 로그인 페이지로 먼저 이동 -->
	<%
		if(account==null){response.sendRedirect("/Bankjsp/bankpage/login.jsp");}
	%>
	
	<div class="b_container">
	
		<div class="savingbox">
			<div class="savingcontent">
			<!-- 적금 상품 -->
			</div>
			
			<div class="savingform">
			<h3>적금 가입</h3>
			<div>
				<input  type="text" id="name"placeholder="성함" class="inputType">
			<!-- 	<div class="check"></div> -->
			</div>
			<div>
				<input  type="text" id="s_pay" placeholder="월 이체금액" class="inputType">
			<!-- 	<div class="check"></div> -->
			</div>
		
			<button class="signupBtn" type="button" onclick="savingSignup()">작성완료</button>
	
		</div>
			
		</div>
		<div><button onclick="cal()">적금 정산</button> </div>
		
	
	</div>
	<%@include file="../footer.jsp" %>
	<!-- SweetAlert2 테마 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	
	<script type="text/javascript" src="../js/saving.js"></script>
</body>
</html>