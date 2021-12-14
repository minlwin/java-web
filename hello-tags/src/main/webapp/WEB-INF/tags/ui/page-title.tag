<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${empty title}">
		<h1>Hello Tag File</h1>
	</c:when>
	
	<c:otherwise>
		<h1>${title}</h1>	
	</c:otherwise>
</c:choose>