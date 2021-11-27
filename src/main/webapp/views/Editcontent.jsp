<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Content</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/editprofile_style.css" />
</head>
<body>
	<div class="mid">
		<div class="title">
			<h2>Edit Content</h2>
			<hr width="98%" align="center" color="#D5D5D5" size="2px" />
		</div>
		<div class="personal-details">
			<p>Content Form Elements</p>
			<div class="col-6">
				<div class="inputs">
					<label><b>Title</b></label> <input class="form-control" type="text"
						placeholder="Enter the Title" required>
				</div>
				<div class="inputs">
					<label><b>Sort</b></label> <input class="form-control" type="text"
						placeholder="Enter " required>
				</div>
			</div>
			<div class="row">
				<div class="inputs">
					<label><b>Brief</b></label>
					<textarea class="form-control" type="text"
						placeholder="Enter self-description"required> </textarea>
				</div>
				<div class="inputs">
					<label><b>Content</b></label>
					<textarea class="form-controls" type="text"
						placeholder="Enter self-description"required> </textarea>
				</div>
			</div>
			<div class="justify-content-end">
				<button class="btn">Submit Button</button>
				<button class="btn">Reset Button</button>
			</div>
		</div>
	</div>	
</body>
</html>