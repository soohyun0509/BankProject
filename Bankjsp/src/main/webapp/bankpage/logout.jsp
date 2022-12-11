<%@page import="org.apache.tomcat.jni.Local"%>
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
			session.setAttribute("account", null);
			response.sendRedirect("/Bankjsp/index.jsp");
		
		%>
		
		
	
</body>
</html>