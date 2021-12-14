<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:url var="plusIcon" value="/assets/svg/plus.svg"></c:url>
<c:url var="searchIcon" value="/assets/svg/search.svg"></c:url>
<c:url var="editIcon" value="/assets/svg/pencil.svg"></c:url>
<c:url var="addNewLink" value="/manager/employee/edit"></c:url>

<form class="form-inline">
	
	<div class="form-group">
		<label>Role</label>
		<select name="role" >
			<option value="">All</option>
			<option ${param.role eq 'Employee' ? 'selected' : ''} value="Employee">Employee</option>
			<option ${param.role eq 'Manager' ? 'selected' : ''} value="Manager">Manager</option>
		</select>
	</div>

	<div class="form-group">
		<label>Name</label>
		<input value="${param.name}" type="text" name="name" placeholder="Search Name" />
	</div>

	<div class="form-group">
	
		<button type="submit" class="btn mr-4 icon-btn">
			<img src="${searchIcon}" class="icon icon-left" />
			Search
		</button>
		
		
		<a class="btn icon-btn" href="${addNewLink}">
			<img src="${plusIcon}" class="icon icon-left" />
			Add New
		</a>
	</div>

</form>

<table>
	<thead>
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Role</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Registration</th>
			<th>Retire</th>
			<th></th>
		</tr>
	</thead>
	
	<tbody>
	
	<c:forEach items="${list}" var="emp">
		<tr>
			<td>${emp.code}</td>
			<td>${emp.name}</td>
			<td>${emp.role}</td>
			<td>${emp.email}</td>
			<td>${emp.phone}</td>
			<td>${emp.registrationDate}</td>
			<td>${emp.retireDate}</td>
			<td>
			
				<c:url var="editLink" value="/manager/employee/edit">
					<c:param name="code" value="${emp.code}"></c:param>
				</c:url>
				
				<a href="${editLink}">
					<img src="${editIcon}" class="icon icon-left" />
				</a>
			</td>
		</tr>	
	</c:forEach>
		
	</tbody>
</table>