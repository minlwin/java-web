<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="enterIcon" value="/assets/svg/enter.svg"></c:url>
<c:url var="saveIcon" value="/assets/svg/checkmark2.svg"></c:url>
<c:url var="loginUrl" value="/login"></c:url>

<section class="card w-480">
	<h3 class="form-title"> 
		<img class="icon icon-left" src="${enterIcon}" />
		Member Login
	</h3>
	
	<jsp:include page="/jsp/includes/error-message.jsp"></jsp:include>
	
	<form class="form" action="${loginUrl}" method="post" >
		<!-- Email Address -->
		<div class="form-group">
			<label>Login Id</label>
			<input name="loginId" placeholder="Enter Employee Code" required="required" />
		</div>
		
		<!-- Password -->
		<div class="form-group">
			<label>Password</label>
			<input name="password" type="password" placeholder="Enter Password" required="required" />
		</div>		
		<!-- Login Button -->
		<div>
			<button class="btn icon-btn" type="submit">
				<img src="${saveIcon}" class="icon icon-left" />
				Login
			</button>
		</div>
		
	</form>
</section>