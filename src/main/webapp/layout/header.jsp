<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">




	<div class="topnav">
		<font size= 5px><a>CMS</a></font> 
<jsp:useBean id="user" class="model.User" />
			<%
			String checker = request.getParameter("id");
			if (checker == null) {
				response.sendRedirect("/logout");
			}
			checker = request.getParameter("name");
			if (checker == null) {
				response.sendRedirect("/logout");}
			int id = Integer.parseInt(request.getParameter("id"));
			
			%>
		<div class="topnav-right">
			<div class="dropdown" style="float:right;">
			<font size= 6px><a><c:out value="<%=checker%>" /></a></font> 
				<button class="dropbtn">
					<i class="fa fa-user"></i> <i class="fa fa-caret-down"></i>
				</button>
				<div class="dropdown-content">
					<a href="editProfileForm?id=<%= id %>&name=<%=checker%>"><i class="fa fa-user"></i> User Profile</a>
					<a href="logout"><i class="fa fa-sign-out"></i> Logout    &emsp;</a>
				</div>
			</div>
		</div>
	</div>

