
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">

<div class="sidenav">
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
	<div style="border-bottom: 1px solid #eaeaea;">
		<form class="search" action="viewContentForm">

			<%
			String search = request.getParameter("search");
			if (search != null) {
			%>
			<input type="text" placeholder="Search.." name="search"
				value="<%= search %>">
			<%
			} else {
			%>
			<input type="text" placeholder="Search.." name="search">
			<%
			}
			%>
			
								<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checker %>">
			<button type="submit">
				<i class="fa fa-search"></i>
			</button>
		</form>
		<br> <br>
	</div>
	 <a href="viewContentForm?id=<%= id %>&name=<%=checker%>"><i class="fa fa-calendar"></i>
		View Content</a> <a href="add-content.tiles?id=<%= id %>&name=<%=checker%>&static=1"><i class="fa fa-edit"></i> Form
		Content</a>
</div>
