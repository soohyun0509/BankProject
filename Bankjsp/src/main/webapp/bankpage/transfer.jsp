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
 	<%
		if(account==null){response.sendRedirect("/Bankjsp/bankpage/login.jsp");}
	%>
	
	
	<div class="b_box">
		<div class="transferBox">
			<div class="transfer_banner"></div>
			<div class="transfer_container">
				<h3>이체 페이지</h3>
				<div class="transfer_form">
					<div class="transfer_money">
						<input type="text" id="transferM" placeholder="이체할 금액을 입력해주세요"> <br>
						<input type="text" id="otherAccount" placeholder="받으실 분의 계좌번호를 입력해주세요"><br>
					</div>
			     	<button type="button" onclick="test(2)"> 이체하기 </button>
				  	<div class="transfer_testBox">
				  	 	<h6>Inform</h6>
					  <div class="test1"></div>
				      <div class="transferResult"></div>	
			      </div>
		      </div>
	      </div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
	<script type="text/javascript" src="../js/working.js"></script>
</body>
</html>