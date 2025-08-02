<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMS Login</title>
</head>
<body>

	<form action=loginAction method="post">

		Enter Email : <input type="text" name="email" /> Enter Password : <input
			type="password" name="password" /> <input type="submit"
			value="Login" />


	</form>
	<%
		if (request.getAttribute("status") != null) {
			out.print(request.getAttribute("status"));
		}
	%>


</body>
</html>