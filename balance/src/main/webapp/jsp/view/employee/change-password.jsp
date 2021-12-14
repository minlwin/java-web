<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/jsp/includes/error-message.jsp"></jsp:include>

<form method="post" class="form w-480">
	
	<div class="form-group">
		<label>Original Password</label>
		<input type="password" name="old-pass" required="required" placeholder="Enter Old Password" />
	</div>

	<div class="form-group">
		<label>New Password</label>
		<input type="password" name="new-pass" required="required" placeholder="Enter New Password" />
	</div>

	<div class="form-group">
		<label>Confirm Password</label>
		<input type="password" name="confirm-pass" required="required" placeholder="Enter New Password Again" />
	</div>
	
	<div>
		<button class="btn" type="submit">Change Password</button>
	</div>	
</form>