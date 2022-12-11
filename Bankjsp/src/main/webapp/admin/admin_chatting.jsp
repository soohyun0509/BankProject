<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- 일단 여기서만 쓸 예정 -->
	<!-- 부트스트랩 css -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<link rel="stylesheet" href="/Bankjsp/css/admin_chatting.css">

</head>
<body>
	<%@include file="../header.jsp" %>
	
	<%
		if(account==null){response.sendRedirect("/Bankjsp/index.jsp");}
	%>
	<input type="hidden" class="account" value="<%=account %>">
		
	<div class="b_box">
		<div class="row">
			<div class="col-sm-6">
				<h3>상담자 목록</h3>
				<table class="table">
					<tr onclick="openAdminChat()"><td>상담목록</td></tr>
					<tr>
                    	 <th>채팅방</th> <th>고객이름</th>
              		</tr>
					
				</table>
			</div>
			<div class="col-sm-6 adminchat">
				<h3>채팅창</h3>
				<div class="chatbox">
					<div class="msgbox"></div>
					<div class="textsendbox"></div>
				</div>
			</div>
			
		</div>
	</div>
	
	
	
	<%@include file="../footer.jsp" %>
	<!-- 부트스트랩 js -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script type="text/javascript" src="/Bankjsp/js/admin_chatting.js"></script>
	
</body>
</html>