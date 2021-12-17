<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta charset="UTF-8" />
<title>Insert title here</title>

<link rel="stylesheet" href="style\register_style.css" />
</head>
<body>
<div class="container text-center">
<center>
<%
int id = Integer.parseInt(request.getParameter("static"));		
		
		if(id==0){	
			%>
		<h3>You have signed up successfully!</h3>
		<p>Please check your email to verify your account.</p>	
		<h4><a href="${pageContext.request.contextPath}/loginForm">Click here to Login</a></h4>		
		 <%} 	
		 if(id==1)	{		%>
	<h3>Congratulations, your account has been verified.</h3>
			<h4><a href="${pageContext.request.contextPath}/loginForm">Click here to Login</a></h4>	
		 <%} 
		if(id==2){	
			%>
		<h3>Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.</h3>
		 <% } %>
			
		</center>
	</div>
</body>
</html>