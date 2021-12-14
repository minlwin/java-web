<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web JDBC</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>

</head>
<body>

	<div class="container pt-4">
		
		<h1>Web JDBC</h1>	
		<h3>Maven Project : Resource on Server</h3>
		
		<c:choose>
			
			<c:when test="${empty list}">
				<c:url var="url" value="/locations"></c:url>
				<a href="${url}" class="btn btn-primary">Load Data</a>
			</c:when>
			
			<c:otherwise>
				
				<table class="table table-striped">
					
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Burmese</th>
							<th>Capital</th>
							<th>Region</th>
							<th>Type</th>
						</tr>
					</thead>
					
					<tbody>
						
						<c:forEach var="s" items="${list}">
						
							<tr>
								<td>${s.id()}</td>
								<td>${s.name()}</td>
								<td>${s.burmese()}</td>
								<td>${s.capital()}</td>
								<td>${s.region()}</td>
								<td>${s.type()}</td>
							</tr>
						
						
						</c:forEach>
					
					</tbody>
				
				</table>
				
			</c:otherwise>
		
		</c:choose>
	
	</div>

</body>
</html>