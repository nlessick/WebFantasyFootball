<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create new owner</title>
</head>
<body>
	<form action="createDetailsServlet" method="post">
	Owner Name: <input type="text" name="ownerName"><br />
	Start Date: <input type="text" name="month" placeholder="mm" size="4">
	<input type="text" name="month" placeholder="dd" size="4">,<input type="text" name="year" placeholder="yyyy" size="4">
	Team Name: <input type="text" name="teamName"><br />
	
	Available Players:<br />
	<select name="allDetailsToAdd" multiple size="6">
	<c:forEach items="${requestScope.allDetails}" var="currentitem"><option value="${currentitem.id}">${currentitem.position} | ${currentitem.name}</option>
	</c:forEach>
	</select>
	<br />
	<input type="submit" value="Create Team and Add Players">
	</form>
<a href="index.html">Go add players instead</a>
</body>
</html>