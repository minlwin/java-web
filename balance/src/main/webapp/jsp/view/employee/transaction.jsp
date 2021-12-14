<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="plusIcon" value="/assets/svg/plus.svg"></c:url>
<c:url var="searchIcon" value="/assets/svg/search.svg"></c:url>
<c:url var="editIcon" value="/assets/svg/pencil.svg"></c:url>

<c:url var="searchUrl" value="/employee/transaction/search"></c:url>
<c:url var="addNewUrl" value="/employee/transaction/edit">
	<c:param name="type" value="${param.type}"></c:param>
</c:url>

<form action="${searchUrl}" class="form-inline">
	
	<input type="hidden" name="type" value="${param.type}">
	
	<div class="form-group">
		<label>From</label>
		<input type="date" name="from" value="${param.from}" />
	</div>

	<div class="form-group">
		<label>To</label>
		<input type="date" name="to" value="${param.to}" />
	</div>
	
	<div class="form-group">
		<label>Category</label>
		<input type="text" value="${param.category}" placeholder="Search Category" name="category" />
	</div>
	
	<div class="form-group">
		<button type="submit" class="btn mr-4 icon-btn">
			<img src="${searchIcon}" class="icon icon-left" />
			Search
		</button>
		<a class="btn icon-btn" href="${addNewUrl}">
			<img src="${plusIcon}" class="icon icon-left" />
			Add New
		</a>
	</div>

</form>


<table>

	<thead>
		<tr>
			<th>Date</th>
			<th>Employee</th>
			<th>Category</th>
			<th>Approved</th>
			<th class="right">Items</th>
			<th class="right">Amount</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="data" items="${list}">
		<tr>
			<td>
				<c:url var="detailsUrl" value="/employee/transaction/details">
					<c:param name="id" value="${data.id}"></c:param>
				</c:url>
				<a href="${detailsUrl}">${data.date}</a>
			</td>
			<td>${data.employee.name}</td>
			<td>${data.category}</td>
			<td>${data.approved ? "Yes" : "No"}</td>
			<td class="right">${data.items}</td>
			<td class="right">
				<c:out value="${data.total}"></c:out> MMK
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>


