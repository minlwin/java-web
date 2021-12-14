<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="var" required="true" rtexprvalue="false" %>
<%@ variable name-from-attribute="var" alias="output" scope="AT_BEGIN" %>

<c:set value="Variable Value from Tag" var="output" />

<div>
	<h4>In Tag File : ${output}</h4>
	
	<jsp:doBody />
</div>
