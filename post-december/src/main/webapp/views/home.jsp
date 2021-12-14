<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post December</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

	<c:import url="/views/includes/menu.jsp"></c:import>
	
	<!-- Post List -->
	<main class="container pt-4">
		
		<h3>Post December</h3>
		
		<div class="text-secondary pb-4">
			Search Keyword : ${ empty param.keyword ? 'Search All' : param.keyword }
		</div>
		
		<c:forEach items="${list}" var="post">
			<div class="card mb-4">
				<div class="card-body">
					${post.title()}
				</div>
				<div class="card-footer d-flex justify-content-between align-items-center">
					<span>${post.creation()}</span>
					<c:url value="/details" var="detailsUrl">
						<c:param name="id" value="${post.id()}" />
					</c:url>
					<a href="${detailsUrl}" class="btn">Show Details</a>
				</div>
			</div>
		</c:forEach>
	

	</main>


</body>
</html>	