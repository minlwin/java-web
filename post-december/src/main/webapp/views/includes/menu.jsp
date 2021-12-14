<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
   
<nav class="bg-success">

	<div class="container d-flex justify-content-between align-items-center text-white pt-2 pb-2">
		<!-- TItle -->
		<h4>Post December</h4>
		
		<c:url value="/home" var="searchUrl"></c:url>
		<form action="${searchUrl}" class="row w-75">
			<div class="col-8">
				<!-- Search -->
				<input name="keyword" value="${param['keyword']}" type="text" placeholder="Search Keyword" class="form-control" />
			</div>
			
			<!-- Search Button -->
			<button type="submit" class="btn text-white col">Search</button>
			
			<c:choose>
				<c:when test="${ not empty loginUser }">
				
					<c:if test="${loginUser.role() ne 'Admin'}">
						<!-- Add New -->
						<c:url value="/post/edit" var="addNew"></c:url>
						<a href="${addNew}" class="btn text-white col">Add New</a>
					</c:if>
				
					<c:url value="/sign-out" var="signOut"></c:url>
					<a href="${signOut}" class="btn text-white col">Sign Out</a>
				</c:when>
				
				<c:otherwise>
					<!-- Add New -->
					<c:url value="/sign-in" var="signIn"></c:url>
					<a href="${signIn}" class="btn text-white col">Sign In</a>
				
					<c:url value="/sign-up" var="signUp"></c:url>
					<a href="${signUp}" class="btn text-white col">Sign up</a>
				</c:otherwise>
			
			</c:choose>

		</form>
	
	</div>

</nav>