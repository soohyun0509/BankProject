<%@page import="controller.admin.paybackmain"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>떙떙 은행</title>
	<!-- font awesome -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
	<!-- 뷰포트 -->
	<meta name="viewport" content="width=device-width , initial-scale=1.0">
	<link rel="stylesheet" href="/Bankjsp/css/header.css">
</head>
<body>

	
	
	<div class="hd_box">
		<div class="hd">
			<div class="logo">
				<a class="nav-link" href="/Bankjsp/index.jsp">땡땡 은행</a>
			</div>
			<ul class="menu">
			
			<% //세션 호출
				String account=(String)session.getAttribute("account");
				// int a_no=(Integer)session.getAttribute("a_no");
			%>
			
			<%if(account==null){%>
				<li><a class="nav-link" href="/Bankjsp/bankpage/signup.jsp">계좌생성</a></li>
				<li><a class="nav-link" href="/Bankjsp/bankpage/login.jsp">계좌 로그인</a></li>
				<li><a class="nav-link" href="/Bankjsp/bankpage/searchaccount.jsp">내 계좌 찾기</a></li>
	
	
			<%}else if(account.equals("1")){ %>
				<li><a class="nav-link" href="/Bankjsp/admin/admin_saving.jsp">관리자 적금 메뉴</a></li>
				<li><a class="nav-link" href="/Bankjsp/admin/admin_loan.jsp">관리자 대출 메뉴</a></li>
				<li><a class="nav-link" href="/Bankjsp/admin/admin_loandata.jsp">대출 상환 내역</a></li>
				<li><a class="nav-link" href="/Bankjsp/admin/admin_server.jsp">서버관리</a></li>
				<li><a class="nav-link" href="/Bankjsp/admin/admin_chatting.jsp">대출 상담 관리</a></li>
				<li><a class="nav-link" href=/Bankjsp/bankpage/logout.jsp>로그아웃</a></li>
			<%}else { %>
				<li><a class="nav-link" href="/Bankjsp/bankpage/deposit.jsp">입금</a></li>
				<li><a class="nav-link" href="/Bankjsp/bankpage/withdraw.jsp">출금</a></li>
				<li><a class="nav-link" href="/Bankjsp/bankpage/transfer.jsp">계좌이체</a></li>
				<li><a class="nav-link" href="/Bankjsp/bankpage/saving.jsp">적금</a></li>
				
				<li><a class="nav-link" href="/Bankjsp/bankpage/loan.jsp">대출</a></li>
				<li><a class="nav-link" href=/Bankjsp/bankpage/logout.jsp>로그아웃</a></li>
			<% } %>
				
			</ul>
		</div>
	</div>
	
	
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</body>
</html>