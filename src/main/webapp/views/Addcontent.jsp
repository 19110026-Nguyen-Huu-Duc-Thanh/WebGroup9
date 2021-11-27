
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/editprofile_style.css" />

<jsp:useBean id="content" class="model.Content" />
		<%
			String checker = request.getParameter("id");
			if (checker == null) {
				response.sendRedirect("/logout");
			}
			checker = request.getParameter("name");
			if (checker == null) {
				response.sendRedirect("/logout");}
			int id = Integer.parseInt(request.getParameter("id"));
			int idC=0;
			int sort =0;
			String title="";
			String brief="";
			String contentn="";			
			int check = Integer.parseInt(request.getParameter("static"));
			if(check==0 )
			{
				title = request.getParameter("title"); 
				brief = request.getParameter("brief");
				brief = brief.trim();
				contentn = request.getParameter("contentn");
				contentn= contentn.trim();
				idC = Integer.parseInt(request.getParameter("idC"));
				sort = Integer.parseInt(request.getParameter("sort")); 
			}
			
			%>
	<div class="mid">
		<div class="title">
			<h2>
			<%  
					if(check==1){
				%>
			Add Content
			<%}		
		%>
		<%  
					if(check==0){
				%>
			Update Content
			<%}		
		%>
			</h2>
			<hr width="98%" align="center" color="#D5D5D5" size="2px" />
		</div>		
		<%  
					if(check==1){
				%>
		<form method="post"  onsubmit="return validate()" action="AddContent">
		<%}		
		%>
		<%  
					if(check==0){
				%>
		<form method="post" onsubmit="return validate()" action="updateContent">
		<%}		
		%>
		<div class="personal-details">
			<p>Content Form Elements</p>
					<center>	 <a class="text-danger">${mess}</a></center>
			
			<div class="col-6">			
			<span class="error" id="tid" class="text-danger"></span>
				<div class="inputs">
					<label><b>Title</b></label> <input  minlength="10" maxlength="200" id ="tiles" name ="tiles" class="form-control" type="text"
						placeholder="Enter the Title"  value="<%= title %>" onblur="validate1()" onkeyup="validate1()" >
				</div>
				<%  
					if(check==0){
				%>
				<div class="inputs">
					<label><b>Sort</b></label> <input min = "0" id ="sort" name ="sort" class="form-control" type="number"
						placeholder="Enter the sort"  value="<%= sort %>" required>
				</div>
			<%}		
		%>
			
			</div>
			<div class="row">
			<span class="error" id="bid" class="text-danger"></span>
				<div class="inputs">
					<label><b>Brief</b></label>
					<textarea id = "brief2" name ="brief2"  minlength="30"  maxlength="150" class="form-control" type="text"
						placeholder="Enter self-description"onblur="validate2()" onkeyup="validate2()" ><%=brief%></textarea>
				</div>
				<span class="error" id="cid" class="text-danger"></span>
				<div class="inputs">
					<label><b>Content</b></label>
					<textarea id = "content2" name ="content2" minlength="50" maxlength="1000" class="form-controls" type="text"
						placeholder="Enter self-description" onblur="validate3()" onkeyup="validate3()" ><%=contentn %></textarea>
				</div>
			</div>
			
			<div class="justify-content-end">
				<button onclick="myFunction()" type="submit" class="btn">Submit Button</button>
				<button type="reset" class="btn">Reset Button</button></a>
				
			</div>
				<input type="hidden" id="id" name="id" value="<%= id %>">
				<input type="hidden" id="name" name="name" value="<%= checker %>">
				<input type="hidden" id="idC" name="idC" value="<%= idC %>">
				<input type="hidden" id="brief" name ="brief"></input>
				<input type="hidden" id="content" name="content"></input>			
			</div>
			</form>
		</div>
		<script>
		 
function myFunction() {
  var x = document.getElementById("brief2").value;
  document.getElementById("brief").value = x;
  var y = document.getElementById("content2").value;
  document.getElementById("content").value = y;
}
const tiles = document.querySelector("#tiles");
const brief = document.querySelector("#brief2");
const content = document.querySelector("#content2");

function validate1(){
	 
	 if (tiles.value == ""){
		 tiles.style.borderColor = "#e74c3c";
		 tiles.style.background = "#fceae9";
	 }
	 else{
		 tiles.style.borderColor = "#27ae60";
		 tiles.style.background = "#eafaf1";
       }
}
function validate2(){
	 
	 if (brief.value == ""){
		 brief.style.borderColor = "#e74c3c";
		 brief.style.background = "#fceae9";
	 }
	 else{
		 brief.style.borderColor = "#27ae60";
		 brief.style.background = "#eafaf1";
      }
}
function validate3(){
	 
	 if (content.value == ""){
		 content.style.borderColor = "#e74c3c";
		 content.style.background = "#fceae9";
	 }
	 else{
		 content.style.borderColor = "#27ae60";
		 content.style.background = "#eafaf1";
      }
}
function validate()  {
    var t = document.getElementById("tiles").value;
    var b = document.getElementById("brief2").value;
    var c = document.getElementById("content2").value;
    if(t == "") {
   	 document.getElementById("tid").innerHTML = " ** tiles must be fill";
        return false;
    }
    else if(t.length<10 || t.length>200){
   	 document.getElementById("tid").innerHTML = " ** tiles length must be between 10 - 200 ";
        return false;
    }
    if(b == "") {
   	 document.getElementById("bid").innerHTML = " ** Brief must be fill";
        return false;
    }
    else if(b.length<30 || b.length > 150){
   	 document.getElementById("bid").innerHTML = " ** Brief length must be between 30 - 150";
        return false;
    }
    if(c == "") {
      	 document.getElementById("cid").innerHTML = " ** Content must be fill";
           return false;
       }
       else if(c.length<50 || c.length > 1000){
      	 document.getElementById("cid").innerHTML = " ** Content length must be between 50 - 1000";
           return false;
       }

    return true;
}
</script>
