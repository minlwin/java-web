<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container pt-4">
	
		<h1>Security Demo</h1>

		<c:url var="adminHome" value="/admin"></c:url>
		<c:url var="memberHome" value="/member"></c:url>
		<c:url var="logout" value="/logout"></c:url>
		
		<c:if test="${not empty userName}">
			<p>Hello ${userName}!</p>
		</c:if>
		
		<c:choose>
			
			<c:when test="${not empty loginUser}">
				<a href="${memberHome}" class="btn btn-primary">Member Home</a>
				
				<c:if test="${isAdmin}">
					<a href="${adminHome}" class="btn btn-primary">Admin Home</a>
				</c:if>
								
				<a href="${logout}" class="btn btn-primary">Logout</a>
			</c:when>
			
			<c:otherwise>
				<a href="${memberHome}" class="btn btn-primary">Member Home</a>
				<a href="${adminHome}" class="btn btn-primary">Admin Home</a>
			</c:otherwise>
		
		</c:choose>		
		
				
	
	</div>

</body>
</html>