<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="app" uri="/WEB-INF/app-tags.tld" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAVEN Web</title>
</head>
<body>

	<c:set value="Hello, Java, Servlet, JSP, JSTL" var="csv"></c:set>
	
	<c:set value='${ f:split(csv, ",") }' var="array"></c:set>
	
	<fmt:setBundle basename="messages" var="appBundle"/>
	
	<h1>
		<fmt:message bundle="${appBundle}" key="app.title"></fmt:message>
	</h1>
	
	<ul>
		<c:forEach items="${array}" var="item">
			<li><c:out value="${ f:trim(item) }"></c:out> </li>
		</c:forEach>
	</ul>
	
	<p>
		<c:out value="${ f:join(array, ',') }"></c:out>
	</p>
	
	<app:hello></app:hello>
	
	<form action="#">
		<app:inputText name="message"  label="Message" inputValue="${param.message}" placeholder="Enter Message">
			<jsp:attribute name="disabled"></jsp:attribute>
		</app:inputText>
	</form>
	
	<hr />
	
	<h3>Body Tag</h3>
	
	<c:url var="listUrl" value="/list" />

	<a href="${listUrl}">Load List</a>
	
	<app:table items="${list}" var="item">
		<app:column name="ID">${item.id}</app:column>
		<app:column name="Name">${item.name}</app:column>
		<app:column name="Phone">${item.phone}</app:column>
		<app:column name="Email">${item.email}</app:column>
	</app:table>

</body>
</html>