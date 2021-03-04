<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit An Existing Team</title>
</head>
<body>
	<form action="editDetailsServlet" method="post">
	<input type="hidden" name="id" value="${listToEdit.id}">
	Owner Name: <input type="text" name="ownerName" value="${listToEdit.ownerName}"><br />
	Start Date: <input type="text" name="month" placeholder="mm" size="4" value="${month}">
	<input tye="text" name="day" placeholder="dd" size="4" value="${date}">,<input type="text" name="year" placeholder="yyyy" size="4" value="${year}">
	Team Name:<input type="text" name="teamName" value="${listToEdit.team.teamName}"><br />
	
	Available Players:<br />
	<select name="allDetailsToAdd" multiple size="6">
	<c:forEach items="${requestScope.allDetails}" var="currentitem">
	<option value="${currentitem.id}">${currentitem.position} | ${currentitem.name}</option>
	</c:forEach>
	</select>
	<br />
	<input type="submit" value="edit Team and Add Players">
	</form>
	<a href="index.html">Go add players</a>

</body>
</html>