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
	
		<div class="searchbox">
			<h3>계좌찾기</h3>
			<div class="search_form">
				<input type="text" id="name" placeholder="성함">
				<input type="text" id="phone" placeholder="전화번호">
				
				<button type="button"  onclick="searchAccount()">계좌 찾기</button>
			</div>
			<div class="account_result">
				
				<span id="account"></span>
			</div>
			
			
			
		</div>
		
		
		
	</div>
	<%@include file="../footer.jsp" %>
	
	<script type="text/javascript" src="../js/searchAccount.js"></script>
</body>
</html>