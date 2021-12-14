<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Balance | ${pageTitle}</title>

<c:url var="cssLink" value="/assets/css/style.css"></c:url>

<link rel="stylesheet" href="${cssLink}" />
</head>
<body>
	
	<c:choose>
	
		<c:when test="${loginInfo.login}">
			<!-- Employee Template -->
			<main class="employee">	
				
				<jsp:include page="/jsp/includes/header.jsp"></jsp:include>
				<jsp:include page="/jsp/includes/side-bar.jsp"></jsp:include>
							
				<section class="box shadow">
					<div class="view-title">${viewTitle}</div>
					<jsp:include page="${content}"></jsp:include>
				</section>
			</main>		
		</c:when>
		
		<c:otherwise>
			<main class="anonymous">
				<!-- Anonymous Template -->
				<jsp:include page="${content}"></jsp:include>
			</main>
		</c:otherwise>
	
	</c:choose>
	

</body>
</html>