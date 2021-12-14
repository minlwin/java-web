<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<jsp:include page="/jsp/includes/error-message.jsp"></jsp:include>
<c:url var="saveIcon" value="/assets/svg/checkmark.svg"></c:url>

<form method="post" class="form w-480">

	<div class="row">
		<div class="form-group mr-4">
			<label>Role</label>
			<select name="role">
				<option value="Employee" ${employee.role eq 'Employee' ? 'selected' : ''}>Employee</option>
				<option value="Manager" ${employee.role eq 'Manager' ? 'selected' : ''}>Manager</option>
			</select>
		</div>	
		
		

		<div class="form-group">
			<label>Employee Code</label>
			<input name="code" type="text" value="${employee.code}" readonly="readonly" />
		</div>
	</div>

	<div class="form-group">
		<label>Name</label>
		<input type="text" name="name" placeholder="Enter Name" value="${employee.name}" />
	</div>


	<div class="form-group">
		<label>Phone</label>
		<input type="tel" name="phone" placeholder="Enter Phone Number" value="${employee.phone}" />
	</div>

	<div class="form-group">
		<label>Email</label>
		<input type="email" name="email" placeholder="Enter Email" value="${employee.email}" />
	</div>
	
	<div class="row">
		<div class="form-group mr-4">
			<label>Entry Date</label>
			<input type="date" name="registrationDate" value="${employee.registrationDate}" />
		</div>
		<div class="form-group">
			<label>Retire Date</label>
			<input type="date" name="retireDate" value="${employee.retireDate}"  />
		</div>	
	</div>
	
	<div>
		<button type="submit" class="btn icon-btn">
			<img src="${saveIcon}" class="icon icon-left" />
			Save Employee
		</button>
	</div>

</form>