<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="url" type="java.lang.String" %>
<%@ attribute name="post" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="${url}" var="action"></c:url>

<form action="${action}" method="${empty post or not post ? 'get' : 'post'}">
	<jsp:doBody />
</form>
