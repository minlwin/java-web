<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

	<c:import url="/views/includes/menu.jsp"></c:import>
	
	<main class="container pt-4">
	
		<div class="row">
			<div class="col-4">
				<h3>Sign In</h3>
				
				<c:url value="/sign-in" var="singInUrl"></c:url>
				<form action="${singInUrl}" method="post">
					
					<div class="mb-3">			
						<label for="loginId">Login Id</label>
						<input name="login" type="text" placeholder="Enter Login Id" class="form-control mt-1" />
					</div>
				
					<div class="mb-3">			
						<label for="loginId">Password</label>
						<input name="password" type="password" placeholder="Enter Password" class="form-control mt-1" />
					</div>
					
					<div>
						<button class="btn btn-primary mr-2">Sign In</button>
						
						<c:url value="/sign-up" var="url"></c:url>
						<a href="${url}" class="btn btn-secondary">Sign Up</a>
					</div>

				</form>
			</div>
		</div>
	</main>

</body>
</html>