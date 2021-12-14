<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL DEMO</title>

<style>
	form, .output {
		display: grid; 
		grid-template-columns: 120px 350px;
		grid-row-gap: 8px;
	}
</style>
</head>
<body>

	<fmt:setLocale value="my"/>
	<fmt:setBundle basename="messages" scope="session" var="messages"/>
	
	<h1><fmt:message bundle="${messages}" key="app.title"></fmt:message></h1>
	
	<c:catch var="e">
		<jsp:useBean id="member" class="com.jdc.demo.Member"></jsp:useBean>
	
		<c:set target="${member}" property="name" value="${param.name}"></c:set>	
		<c:set target="${member}" property="phone" value="${param.phone}"></c:set>	
		<c:set target="${member}" property="email" value="${param.email}"></c:set>	
		
		<c:set var="message" value="${empty member.name ? 'No Name' : member.name}"></c:set>
	</c:catch>
	
	<c:if test="${not empty e}">
		<h3>There is Error!</h3>
	</c:if>
	
	<!-- Form -->
	<form method="post">
		
		<label>Name</label>
		<input type="text" name="name" />

		<label>Phone</label>
		<input type="tel" name="phone" />

		<label>Email</label>
		<input type="email" name="email" />
		
		<span></span>
		<button type="submit">Send</button>
	</form>
	
	<!-- Output -->
	<div>
		<h3>Output</h3>
		
		<div class="output">
			<label>Name</label>
			<span>
				<c:out default="No Name" value="${member.name}"></c:out>
			</span>
	
			<label>Phone</label>
			<span>
				<c:out default="No Phone" value="${member.phone}"></c:out>
			</span>
	
			<label>Email</label>
			<span>
				<c:out default="No Email" value="${member.email}"></c:out>
			</span>
			
			<label>Message</label>
			<span>
				${message}
			</span>
		
		</div>
	</div>
	
	<hr />
	
	<h3>Formatting</h3>
	
	
	
	<fmt:message key="app.title" bundle="${messages}" var="hello" scope="page">
	</fmt:message>

</body>
</html>