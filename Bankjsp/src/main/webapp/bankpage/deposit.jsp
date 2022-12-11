<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="../css/working.css">
</head>
<body>
	<%@include file="../header.jsp" %>
	
	<!-- 로그인 안했으면 로그인 페이지로 먼저 이동 -->
	<% if(account==null){response.sendRedirect("/Bankjsp/bankpage/login.jsp");}%>
	
	
	<div class="b_box">
		<div class="depositBox">
			<div class="deposit_banner"></div>
			
			<div class="deposit_container">
				<h3>입금 페이지</h3>
				<div class="deposit_form">
					<div class="deposit_money">
						<input type="text" id="plusM" placeholder="입금 금액을 입력해주세요">
					</div>
					<button type="button" onclick="deposit()">입금하기</button>
					<div class="depositResult">
						<h6>Inform</h6>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<%@include file="../footer.jsp" %>
	
	<script type="text/javascript" src="../js/working.js"></script>
</body>
</html>