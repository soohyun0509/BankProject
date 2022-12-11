<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- SweetAlert2 테마 -->
	<link rel="stylesheet" href="sweetalert2.min.css">
	<link rel="stylesheet" href="../css/loan.css">
	<link rel="stylesheet" href="../css/signup.css">
	
</head>
<body>

	<!-- 로그인세션없으면 메인페이지로 이동시켜야함 -->

	<%@include file="../header.jsp" %>
		<!-- 로그인 안했으면 로그인 페이지로 먼저 이동 -->
	<% if(account==null){response.sendRedirect("/Bankjsp/bankpage/login.jsp");}%>
	
	<div class=b_container>
		
		<div class="loanbox">
			<div class="loancontent">
			<!-- 대출 상품 -->
			</div>
			
			<div class="loanform">
				<!-- 대출신청폼 -->
				<!-- display none으로 해놓고 js에서 풀 예정! -->
				<h3>대출 신청서</h3>
				<div>
					<input  type="text" id="name" onkeyup="nameCheck()" placeholder="성함" class="inputType">
					<div class="check"></div>
				</div>
				<div>
					<input type="text" id="phone" onkeyup="phoneCheck()" placeholder="전화번호" class="inputType">
					<div class="check"></div>
				</div>
				<div>
					<input type="text" id="account" onkeyup="accountCheck()" placeholder="계좌번호" class="inputType">
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
				<div>
					<input type="text" id="amount" onkeyup="amountCheck()" placeholder="대출신청금액" class="inputType">
					<div class="check"></div>
				</div>
				
				
				<button class="signupBtn" type="button" onclick="loanSignup()">작성완료</button>
			</div>
			
		</div>
		
		
		
	</div>
	
	<%@include file="../footer.jsp" %>
	
	
	<!-- SweetAlert2 테마 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<!-- 카카오우편 API -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript" src="../js/signup.js"></script><!-- 카카오우편API & 유효성 같이쓰려고 -->
	<script type="text/javascript" src="../js/service.js"></script>
	
	
</body>
</html>