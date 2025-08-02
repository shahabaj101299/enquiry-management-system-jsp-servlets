<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="menu.jsp"%>
<%@ page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Registrations</title>
</head>
<body>
	<h1>Your Registrations...</h1>
	<table border="2">

		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Course</th>
			<th>Email</th>
			<th>Mobile</th>
			<th>Delete</th>
			<th>Update</th>
		</tr>

		<%
			ResultSet result = (ResultSet) request.getAttribute("allRegistrations");

			while (result.next()) {
		%>

		<tr>
			<td><%=result.getInt(1)%></td>
			<td><%=result.getString(2)%></td>
			<td><%=result.getString(3)%></td>
			<td><%=result.getString(4)%></td>
			<td><%=result.getString(5)%></td>
			<td><a href="deleteRegistration?email=<%=result.getString(4)%>">delete
					a record</a></td>
			<td><a href="updateRegistration?id=<%=result.getInt(1)%>">update
					a record</a></td>







		</tr>

		<%
			}
		%>




	</table>

</body>
</html>