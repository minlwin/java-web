<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="editIcon" value="/assets/svg/pencil.svg"></c:url>
<c:url var="approveIcon" value="/assets/svg/checkmark.svg"></c:url>

<c:url value="/employee/transaction/edit" var="editUrl">
	<c:param name="id" value="${data.id}"></c:param>
	<c:param name="type" value="${data.type}"></c:param>
</c:url>
<c:url value="/manager/transaction/approve" var="approveUrl">
	<c:param name="id" value="${data.id}"></c:param>
</c:url>

<!-- Header Inform -->
<div class="balance-summary">
	
	<label>Date</label>
	<input type="text" disabled="disabled" value="${data.date}" />

	<label>Employee</label>
	<input type="text" disabled="disabled" value="${data.employee.name}" />
	

	<label>Items</label>
	<input type="text" class="right" disabled="disabled" value="${data.items}" />

	<label>Category</label>
	<input type="text" disabled="disabled" value="${data.category}" />

	<label>Status</label>
	<input type="text" disabled="disabled" value="${data.approved ? 'Approved' : 'Not Approved'}" />

	<label>Total</label>
	<input type="text" class="right" disabled="disabled" value="${data.total} MMK" />
	
</div>

<!-- Actions -->
<div class="balance-action" >
	
	<c:if test="${ loginInfo.manager or (not loginInfo.manager and data.approved and data.employee.code eq loginInfo.profile.code) }">
		<a href="${editUrl}" class="btn icon-btn">
			<img src="${editIcon}" class="icon icon-left " />
			Edit
		</a>
	</c:if>
	
	<c:if test="${loginInfo.manager and not data.approved}">
		<a href="${approveUrl}" class="btn icon-btn ml-4">
			<img src="${approveIcon}" class="icon icon-left" />
			Approve
		</a>
	</c:if>
</div>

<!-- Details Information -->
<h3>Expense Details</h3>
<table>
	<thead>
		<tr>
			<th>Item</th>
			<th>Remark</th>
			<th class="right">Unit Price</th>
			<th class="right">Quantity</th>
			<th class="right">Total</th>
		</tr>
	</thead>
	<tbody>
	
	<c:forEach var="details" items="${data.details}">
	
		<tr>
			<td>${details.item}</td>
			<td>${details.remark}</td>
			<td class="right">${details.price} MMK</td>
			<td class="right">${details.quantity}</td>
			<td class="right">${details.total} MMK</td>
		</tr>
		
	</c:forEach>
	
	</tbody>
</table>