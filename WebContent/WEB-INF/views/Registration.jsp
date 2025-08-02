<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="menu.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register here</title>
</head>
<body>

	<h1>Student Registration</h1>



	<form action="registrationControllerAction" method="post">
		<pre>
Name : <input type="text" name="name">
Course : <input type="text" name="course">
Email : <input type="text" name="email">
Mobile : <input type="text" name="mobile">

<input type="submit" value="register here" />
</pre>


	</form>
	<%
		if (request.getAttribute("msg") != null) {

			out.print(request.getAttribute("msg"));
		}
	%>



</body>
</html>