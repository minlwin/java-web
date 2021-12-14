<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<div class="container pt-4">
	
		<h1>Member Login</h1>
		
		<div class="row mt-4">
			
			<div class="col-4">
				<c:url value="/login" var="login"></c:url>
				<form action="${login}" method="post" >
				
					<div class="form-group">
						<label class="form-label" for="loginId">Login Id</label>	
						<input name="loginId" class="form-control" type="text" id="loginId" placeholder="Enter Login Id" />
					</div>
				
					<div class="form-group mt-2">
						<label class="form-label" for="pass">Password</label>	
						<input name="password" class="form-control" type="password" id="pass" placeholder="Enter Password" />
					</div>
					
					<div class="mt-2">
						
						<button class="btn btn-primary" type="submit">
							Login
						</button>
					</div>

				</form>
			</div>	
			
					
		</div>
		
		
	</div>

</body>
</html>