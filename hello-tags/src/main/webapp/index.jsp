<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>  
<%@ taglib prefix="form" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Tag | Tag File</title>

<ui:bootstrap />

</head>
<body>

	<div class="container mt-4">
	
		<ui:page-title title="Student Registration" />	
		
		<form:form post="true" url="/students">
			
			<div class="row">
				<form:select name="course" items="Java Basic, Spring, Angular, Flutter, React" var="course" label="Course">
					<option value="${course}">${course}</option>
				</form:select>
				<form:input name="name" label="Student Name" placeholder="Enter Student Name" />
			</div>
			
			<div class="row">
				<form:input name="phone" label="Phone Number" placeholder="Enter Phone Number" />
				<form:input name="email" label="Email Address" placeholder="Enter Email Address" />
			</div>		
		</form:form>
			
	</div>
	
	<h3>Variable Scope</h3>
	
	<ui:scope-demo var="message">
		<h4>In Tag Body : ${message}</h4>
	</ui:scope-demo>
	
	<h4>After Tag Body : ${message}</h4>

</body>
</html>