<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message Editor</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container">
	
		<h1>Create Message</h1>
		
		<div class="row">
		
			<div class="col-8">
				
				<form action="#">
					
					<div class="mb-3">
						<label for="title" class="form-label">Title</label>
						<input name="title" type="text" id="title" class="form-control" placeholder="Enter Title" />
					</div>
					
					<div class="mb-3">
						<label for="message" class="form-label">Message</label>
						<textarea name="message" id="message" cols="30" rows="10" class="form-control" row="3"></textarea>
					</div>
					
					<button class="btn btn-primary mr-2">Save</button>
				
				</form>
			
			</div>
		</div>
		
	</div>

</body>
</html>