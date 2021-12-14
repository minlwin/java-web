<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>December Edit</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

	<!-- Menu -->
	<c:import url="/views/includes/menu.jsp"></c:import>
	
	<main class="container pt-4">
	
		<h1>${ empty post ? 'Add New' : 'Edit' } Post</h1>
		
		
		<c:url value="/post/edit" var="savePost"></c:url>
		<form action="${savePost}" method="post">
		
			<input type="hidden" name="id" value="${post.id()}">
			
			<div class="row">
				
				<div class="col-9">
				
					<label for="title" class="form-label">Title</label>
					<input name="title" value="${post.title()}" type="text" placeholder="Enter Title" class="form-control" />
				</div>
				
				<div class="mt-2">
					<label for="message" class="form-label">Post</label>
					<textarea name="content" id="" cols="30" rows="10" class="form-control">${post.content()}</textarea>
				
				</div>
				
				<div class="mt-2">
					<button type="reset" class="btn btn-secondary mr-2">Clear</button>
					<button type="submit" class="btn btn-primary">Save</button>
				</div>
			
			</div>
		
		</form>
	</main>
</body>
</html>