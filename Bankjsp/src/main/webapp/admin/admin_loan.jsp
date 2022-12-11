<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="/Bankjsp/css/admin_loan.css">
	<!-- 부트스트랩 css -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	
</head>
<body>

	<%@include file="../header.jsp" %>
	
	<%
		if(account==null){response.sendRedirect("/Bankjsp/index.jsp");}
	%>
	

		<div class="a_container">
			<h3>대출상품 관리페이지 </h3>
			
			
			<table class="btable table">
					<tr>
						<th> 번호 </th>   <th> 대출명 </th>      <th>대출내용</th> 
						<th> 이자율 </th>   <th> 대출한도 </th>   <th> 대출기간 </th>
					</tr>
			</table>
			<div class="addBtnBox"></div>
			<div> 
				<table class="laonaddinput">
				</table>
			</div>
		</div>
	<%@include file="../footer.jsp" %>
	<script type="text/javascript" src="../js/admin_loan.js"></script>
</body>
</html>