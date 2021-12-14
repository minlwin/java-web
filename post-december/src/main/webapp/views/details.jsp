<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Details</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

	<c:import url="/views/includes/menu.jsp"></c:import>
	
	<main class="container pt-4">
	
		<h3>${post.title()}</h3>
		
		<div class="d-flex justify-content-between text-secondary pb-4">
			<span>${post.owner().name()}</span>
			
			<span>${post.creation()}</span>
		</div>
		
		<p>${post.content()}</p>
		
		<div class="pt-4">
			
			<c:url value="/post/delete" var="deleteUrl">
				<c:param name="id" value="${post.id()}"></c:param>
			</c:url>		
			<c:url value="/post/edit" var="editUrl">
				<c:param name="id" value="${post.id()}"></c:param>
			</c:url>		
			
			<c:if test="${ not empty loginUser }">
					
				<c:if test="${loginUser.login() eq post.owner().login() or loginUser.role() eq 'Admin'}">
					<a href="${deleteUrl}" class="btn btn-danger mr-2">Delete</a>
				</c:if>
				
				<c:if test="${loginUser.login() eq post.owner().login()}">
					<a href="${editUrl}" class="btn btn-primary">Edit</a>
				</c:if>
			</c:if>
			
		</div>
	
	</main>

</body>
</html>