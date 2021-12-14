<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="addIcon" value="/assets/svg/plus.svg"></c:url>
<c:url var="saveIcon" value="/assets/svg/checkmark.svg"></c:url>

<c:url var="pageScript" value="/assets/js/transaction-edit.js"></c:url>
<script type="text/javascript" src="${pageScript}"></script>

<!-- Header Inform -->
<c:url var="editForm" value="/employee/transaction/edit"></c:url>
<form action="${editForm}" method="post">
	
	<input type="hidden" name="id" value="${data.id}" />
	<input type="hidden" name="type" value="${data.type}" />
	<input type="hidden" name="employeeCode" value="${data.employee.code}" />
	
	<div class="balance-summary">
		
		<label>Date</label>
		<input name="date" type="date" value="${data.date}" required="required" />
	
		<label>Employee</label>
		<input type="text" disabled="disabled" value="${data.employee.name}" />
		
	
		<label>Items</label>
		<input name="allCount" type="text" class="right" disabled="disabled" value="0" />
	
		<label>Category</label>
		<input name="category" type="text" value="${data.category}" placeholder="Enter Category" required="required" />
	
		<label>Status</label>
		<input disabled="disabled" value="${data.approved ? 'Approved' : 'Not Approved'}" />
	
		<label>Total</label>
		<input name="allTotal" class="right" disabled="disabled" value="${data.total}" />
		
	</div>
	
	<!-- Actions -->
	<div class="balance-action" >
		<a href="#" onclick="addDetails(); return false;" class="btn icon-btn mr-4">
			<img src="${addIcon}" class="icon icon-left " />
			Add Details
		</a>
		<button type="submit" class="btn icon-btn">
			<img src="${saveIcon}" class="icon icon-left" />
			Save
		</button>
	</div>
	
	<!-- Details Information -->
	<h3>Expense Details</h3>
	<table>
		<thead>
			<tr>
				<th>Item</th>
				<th>Remark</th>
				<th class="right w-number-input">Unit Price</th>
				<th class="right w-number-input">Quantity</th>
				<th class="right w-number-input">Total</th>
			</tr>
		</thead>
		<tbody id="detailsWrapper">
		
			<c:forEach var="details" items="${data.details}">
			
			<tr>
				<td>
					<input name="item" value="${details.item}" type="text" placeholder="Enter Item" />
				</td>
				<td>
					<input name="remark" value="${details.remark}" type="text" placeholder="Enter Remark" />
				</td>
				<td class="right">
					<input name="price" type="number" value="${details.price}" />
				</td>
				<td class="right">
					<input name="count" type="number" value="${details.quantity}" />
				</td>
				<td class="right">
					<input name="total" type="number" value="${details.total}" readonly="readonly" />
				</td>
			</tr>
			
			</c:forEach>
		
		</tbody>
	</table>
</form>
