<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<%@include file="../header.jsp" %>
	
	<%
		if(account==null){response.sendRedirect("/Bankjsp/index.jsp");}
	%>

	



	<%@include file="../footer.jsp" %>
</body>
</html>