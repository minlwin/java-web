<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="label" required="true" type="java.lang.String" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="items" required="true" type="java.lang.String" %>

<%@ attribute name="var" required="true" rtexprvalue="false" type="java.lang.String" %>
<%@ variable name-from-attribute="var" alias="output" %>

<div class="col mb-3">
	<label class="form-label">${label}</label>
	
	<select name="${name}" class="form-select">
		<c:forTokens items="${items}" delims="," var="item">
			<c:set value="${item}" var="output"></c:set>
			<jsp:doBody></jsp:doBody>
		</c:forTokens>
	</select>
</div>
