<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



<<style>
.text
{
	color: #04B404
}
</style>

	<div class="topnav">
		<font size= 5px><a>CMS</a></font> 
<jsp:useBean id="user" class="model.User" />
			<%
			String checker = request.getParameter("id");
			if (checker == null) {
				response.sendRedirect("/logout");
			}
			String check = request.getParameter("static");
			if (check == null  || check.length()==1) {
				check="";
			}
			checker = request.getParameter("name");
			if (checker == null) {
				response.sendRedirect("/logout");}
			int id = Integer.parseInt(request.getParameter("id"));
			
			%>
		<div class="topnav-right">
			<div class="dropdown" style="float:right;">
			
			<font class="text" size= 6px><a><c:out value="<%=check%>" /></a></font> 
			<font size= 6px><a><c:out value="<%=checker%>" /></a></font> 
				<button class="dropbtn">
					<i class="fa fa-user"></i> <i class="fa fa-caret-down"></i>
				</button>
				<div class="dropdown-content">
				<form id="pro" method="post" action="editProfileForm">
								<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checker %>">
	 <a onclick="document.getElementById('pro').submit();"><i class="fa fa-user"></i> User Profile</a>
</form>
					<a href="logout"><i class="fa fa-sign-out"></i> Logout    &emsp;</a>
				</div>
			</div>
		</div>
	</div>

