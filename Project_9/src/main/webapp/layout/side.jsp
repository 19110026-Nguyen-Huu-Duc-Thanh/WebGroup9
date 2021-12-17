
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
			if (search != null ) {
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
	<form id="view" method="post" action="viewContentForm">
								<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checker %>">
	 <a onclick="document.getElementById('view').submit();"><i class="fa fa-calendar"></i>View Content</a>
</form>

	<form id="add" method="post" action="add-content.tiles">	
								<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checker %>">
				<input type="hidden" id="static" name="static" value="1">
 <a  onclick="document.getElementById('add').submit();"><i class="fa fa-edit"></i> Form
		Content</a>
		</form>
</div>
