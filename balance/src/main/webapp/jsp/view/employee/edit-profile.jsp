<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/jsp/includes/error-message.jsp"></jsp:include>
<c:url var="saveIcon" value="/assets/svg/checkmark.svg"></c:url>

<form method="post" class="form w-480">

	<div class="form-group">
		<label>Employee Code</label> 
		<input type="text" value="${loginInfo.profile.code}" 
			readonly="readonly" name="code" />
	</div>

	<div class="form-group">
		<label>Employee Name</label> 
		<input type="text" value="${loginInfo.profile.name}"
			placeholder="Enter Your Name" name="name" />
	</div>

	<div class="form-group">
		<label>Phone Number</label> 
		<input type="tel" value="${loginInfo.profile.phone}"
			placeholder="Enter Phone Number" name="phone" />
	</div>

	<div class="form-group">
		<label>Email Address</label> 
		<input type="email" value="${loginInfo.profile.email}"
			placeholder="Enter Email Address" name="email" />
	</div>

	<div>
		<button class="btn" type="submit">
			<img src="${saveIcon}" class="icon icon-left" /> Save
			Profile
		</button>
	</div>


</form>