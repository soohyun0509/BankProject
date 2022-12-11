<%@page import="controller.admin.paybackmain"%>
<%@page import="controller.admin.adminloan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	
	<link rel="stylesheet" href="/Bankjsp/css/index.css">
</head>
<body>

	<%@include file="../header.jsp" %>
	
	<!-- 회원번호 가져오기 -->
	<div class="index_box">
	<input type="hidden" class="account" value="<%=account %>">
	
		<div class="index_container">
			<div class="index_banner">
			</div>
			
			<div class="index_links">
				<div class="index_link">
					<div class="index_link1_img"></div>
					<div class="index_text">
						<a href="/Bankjsp/bankpage/deposit.jsp">입금 Go!</a>
					</div>
					
				</div>
				<div class="index_link">
					<div class="index_link2_img"></div>
					<div class="index_text">
						<a href="/Bankjsp/bankpage/transfer.jsp">이체 Go!</a>
					</div>
				</div>
				<div class="index_link">
					<div class="index_link3_img"></div>
					<div class="index_text">
						<a href="/Bankjsp/bankpage/withdraw.jsp">출금 Go!</a>
					</div>
				</div>
				<div class="index_link">
					<div class="index_link4_img"></div>
					<div class="index_text">
						<a href="/Bankjsp/bankpage/loan.jsp">대출 Go!</a>
					</div>
				</div>
			</div>
		</div>
		<div>
			<!-- 플로팅 버튼 -->
			<a href="#" class="floatBtn"  onclick="openModal()">
				<i class="fa fa-envelope my-float"></i>
			</a>
			<div class="label-container">
				<div class="label-text">1:1채팅</div>
				<i class="fa fa-play label-arrow"></i>
			</div>
			
	
			<div class="i_modal">
		  		<div class="popup-wrap" id="popup">
		  		</div>
			</div>
		</div>
	</div>
	
	<%@include file="../footer.jsp" %>
	
	<script type="text/javascript" src="/Bankjsp/js/chatting.js"></script>
</body>
</html>