<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="label" required="true" type="java.lang.String" %>
<%@ attribute name="name" required="true" type="java.lang.String" %>
<%@ attribute name="type" type="java.lang.String" %>
<%@ attribute name="placeholder" type="java.lang.String" %>

<div class="mb-3 col">
	<label class="form-label">${label}</label>
	<input type="${empty type ? 'text' : type}" name="${name}" placeholder="${placeholder}" class="form-control" />
</div>