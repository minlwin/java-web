<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="profileImage" value="/assets/images/${loginInfo.profileImage}"></c:url>
<c:url var="profileImageUpload" value="/employee/profile-image"></c:url>
<c:url var="scriptSrc" value="/assets/js/side-bar.js"></c:url>
<c:url var="editProfileUrl" value="/employee/edit-profile"></c:url>
<c:url var="changePassUrl" value="/employee/change-pass"></c:url>

<aside class="box shadow">

	<!-- Profile Image -->
	<img class="profile-image" src="${profileImage}" alt="" />
	
	<!-- Personal Info -->
	<div class="user-name">${loginInfo.name}</div>
	
	<div class="user-role">${loginInfo.role}</div>
	
	<!-- User Actions -->
	<div class="user-actions">
		<a class="btn" onclick="openUploadFile(); return false;" href="#">Upload Photo</a>
		<a class="btn" href="${editProfileUrl}">Edit Profile</a>
		<a class="btn" href="${changePassUrl}">Change Password</a>
	</div>
	
	<form id="profileImageForm" action="${profileImageUpload}" method="post" enctype="multipart/form-data">
		<input accept="image/png" id="profileImageInput" onchange="uploadImage()" type="file" name="profileImage"  />
	</form>
	
	<script src="${scriptSrc}"></script>
	
</aside>