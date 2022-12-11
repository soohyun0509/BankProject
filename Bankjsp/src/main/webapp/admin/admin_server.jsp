<%@page import="controller.admin.adminserver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="/Bankjsp/css/admin_loan.css">
</head>
<body>

	<%@include file="../header.jsp" %>
		<!-- 로그인 안했으면 로그인 페이지로 먼저 이동 -->
	<%
		if(account==null){response.sendRedirect("/Bankjsp/index.jsp");}
	%>
	
	<div class="b_box">
		<div class="server_container">
			<h3> AUTO 대출 상환</h3>
			<button onclick="location.href='/Bankjsp/adminserver'"> 서버관리시스템 시작 </button>
			
			<!-- <button onclick="location.href='/Bankjsp/adminserver2'"> 서버관리시스템 끄기 </button> -->
		</div>
	</div>
	
	<%@include file="../footer.jsp" %>
	<script type="text/javascript" src="../js/adminserver.js"></script>
</body>
</html>