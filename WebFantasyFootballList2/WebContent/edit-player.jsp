<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="editPlayerServlet" method="post">
	Position: <input type="text" name="position" value="${playerToEdit.position}">
	Name: <input type="text" name="name" value="${playerToEdit.name}">
	<input type="hidden" name="id" value="${playerToEdit.id}">
	<input type="submit" value="Save Edited Player">
	</form>
</body>
</html>