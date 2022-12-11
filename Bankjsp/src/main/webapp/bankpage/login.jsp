<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<link href="../css/login.css" rel="stylesheet">

</head>
<body>
	<%@include file="../header.jsp" %>
	
	<div class="b_box">
		<div class="loginbox">
			<span id="slider">
				<div id="slide-holder">
				 	<div class="slide"><img src="../image/login2.png" alt="" /> </div>
				 	<div class="slide"><img src="../image/login3.png" alt="" /> </div>
				 	<div class="slide"><img src="../image/login4.png" alt="" /> </div>
				 </div>
			</span>
			<span class="logininput" id="logininput">
				<h3> 간편하게 땡땡은행 로그인하기</h3>
				<div> <input type="text" id="account" placeholder="  계좌번호 21-000-000">		</div> 
				<div> <input type="password" id="password" placeholder="  비밀번호 _ _ _ _"> 	</div>
				<div id="loginresult">	</div>
				<div> <button onclick="login()" class="loginbtn"> 로 그 인 </button>			</div>
				<span class="finded"> 
					  <a href="http://localhost:8080/Bankjsp/bankpage/searchaccount.jsp">비밀번호 찾기 ㅣ</a>
					  <a href="http://localhost:8080/Bankjsp/bankpage/searchaccount.jsp">내 계좌 찾기</a>
				</span>
				
						
					
			</span>

		</div>
	</div>
	
	<%@include file="../footer.jsp" %>

	
	
	<script type="text/javascript" src="../js/login.js"></script>
</body>
</html>