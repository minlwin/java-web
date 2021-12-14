<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="app" uri="/WEB-INF/custom-tags.tld" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Tags</title>

<app:bootstrap />
</head>
<body>

	<div class="container">
		<app:header1 value='${empty title ? "Custom Tag Demo" : title }' />
		
		<form action="#">
			
			<div class="row">
				<div class="col">
					<app:inputText name="name" label="Customer Name" value="${param.name}" placeholder="Enter Customer Name" >
						<jsp:attribute name="info">Hello Java</jsp:attribute>
					</app:inputText>
				</div>
				
				<div class="col">
					<app:select items="${courses}" var="c" name="course" label="Select Course">
						<option value="${c}">${c}</option>
					</app:select>
				</div>
				
				<div class="col">
					<app:selectOneMenu items="${courses}" var="c" name="myCourse" label="Jsp2 Select">
						<app:selectOption label="${c}" value="${c}"/>
					</app:selectOneMenu>
				</div>
			</div>
		</form>
		
		<h4 class="mt-4 mb-3">Iteration Tag</h4>
		
		<div class="mb-2">
			<a href="load" class="btn btn-primary">Load Data</a>
		</div>
		
		<app:forEach items="${courses}" item="course" title="Courses of JDC">
			<div class="list-group-item">${course}</div>
		</app:forEach>
		
		<hr />
		
		<h3>Simple Tags</h3>
		
		<app:alert message="Hello Jsp 2.0 Simple Tag!" >
			<button class="btn btn-outline-primary">OK!</button>
		</app:alert>
		
		<app:table items="${members}" var="m">
			<app:column name="Id">${m.id()}</app:column>
			<app:column name="Name">${m.name()}</app:column>
			<app:column name="Role">${m.role()}</app:column>
			<app:column name="Phone">${m.phone()}</app:column>
			<app:column name="Email">${m.email()}</app:column>
		</app:table>
		
	</div>

</body>
</html>