<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container">
	
		<h1>Member Login</h1>
		
		<div class="row">
		
			<div class="col-4">
				
				<c:url value="/signin" var="signIn"></c:url>
				
				<form action="${signIn}">
					
					<div class="mb-3">
						<label for="loginId" class="form-label">Login Id</label>
						<input name="loginId" type="text" id="loginId" class="form-control" placeholder="Enter Login Id" />
					</div>
					
					<div class="mb-3">
						<label for="passsword" class="form-label">Password</label>
						<input name="password" type="password" placeholder="Enter Password" id="password" class="form-control" />
					</div>
					
					<button class="btn btn-primary mr-2">Login</button>
					<a href="#" class="btn btn-secondary">Sign Up</a>
				
				</form>
			
			</div>
		</div>
		
	</div>

</body>
</html>