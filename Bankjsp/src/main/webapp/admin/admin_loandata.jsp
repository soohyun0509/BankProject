<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<!-- 일단 여기서만 쓸 예정 -->
	<!-- 부트스트랩 css -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	
</head>
<body>
	<%@include file="../header.jsp" %>
	
	<%
		if(account==null){response.sendRedirect("/Bankjsp/index.jsp");}
	%>
	
	<div class="b_box">
		<table class="table loanData_table">
			<tr>
				<th>번호</th><th>대출자</th><th>대출명</th><th>원금</th>
				<th>월 이자</th><th>대출날짜</th><th>이체 계좌</th><th>납부 상태</th>
			</tr>
		</table>
	</div>
	<%@include file="../footer.jsp" %>
	<!-- 부트스트랩 js -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	
	<script type="text/javascript" src="../js/AdminLoandata.js"></script>
</body>
</html>