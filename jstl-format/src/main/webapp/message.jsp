<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internationalization</title>
</head>
<body>
	
	<c:if test="${ not empty param.lan }">
		<fmt:setLocale value="${param.lan}"/>
	</c:if>
	
	<fmt:setBundle basename="com.jdc.resources.messages" var="messages"/>

	<h1>
		<fmt:message bundle="${messages}" key="app.title"></fmt:message>
	</h1>
	
	<p>
		<fmt:message bundle="${messages}" key="app.message"></fmt:message>
	</p>

	<p>
		<fmt:message bundle="${messages}" key="app.greeting">
			<fmt:param value="Min Lwiin"></fmt:param>
			<fmt:param value="JDC"></fmt:param>
		</fmt:message>
	</p>
	
	<fmt:setBundle basename="com.jdc.resources.language" var="languages"/>
	
	<ul>
		<li>
			<a href="?lan=my">
				<fmt:message bundle="${languages}" key="lan.burmese" ></fmt:message>
			</a>
		</li>
		<li>
			<a href="?lan=en">
				<fmt:message bundle="${languages}" key="lan.english" ></fmt:message>
			</a>
		</li>
		<li>
			<a href="?lan=ja">
				<fmt:message bundle="${languages}" key="lan.japanese" ></fmt:message>
			</a>
		</li>
	</ul>
	

</body>
</html>