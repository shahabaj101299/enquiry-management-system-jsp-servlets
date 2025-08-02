<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make Updage</title>
</head>
<body>



	<form action="updateRegistration" method="post">
		<pre>

Id : <input type="text" name="id"
				value="<%=request.getAttribute("registrationId")%>" />
Name : <input type="text" name="name"
				value="<%=request.getAttribute("name")%>" />
Course : <input type="text" name="course"
				value="<%=request.getAttribute("course")%>" />
Email : <input type="text" name="email"
				value="<%=request.getAttribute("email")%>" />
Mobile : <input type="text" name="mobile"
				value="<%=request.getAttribute("mobile")%>" />

<input type="submit" value="change it" />

</pre>
	</form>



</body>
</html>