<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Summary -->
<div id="dash-summary">

	<div>
		<h3>Expenses</h3>
		<span>${expenseSummary}</span>
	</div>

	<div>
		<h3>Incomes</h3>
		<span>${incomeSummary}</span>
	</div>

	<div>
		<h3>Balance</h3>
		<span>${balanceSummary}</span>
	</div>

	<div>
		<h3>Employee</h3>
		
		<span>${empCount}</span>
	</div>
</div>

<!-- Top Categories -->
<div id="dash-tops">
	
	<div>
		
		<h3>Tops Expenses</h3>
		
		<c:choose>
			
			<c:when test="${empty expenseCategory}">
				<span>There is No Expense Data.</span>
			</c:when>
			
			<c:otherwise>
				<table>
					
					<thead>
						<tr>
							<th>No</th>
							<th>Category</th>
							<th>Min</th>
							<th>Max</th>
							<th>Quantity</th>
							<th>Total</th>
						</tr>
					</thead>
		
					<tbody>
						
						<c:forEach var="c" varStatus="sts" items="${expenseCategory}">
							<tr>
								<td>${sts.index + 1}</td>
								<td>${c.name()}</td>
								<td>${c.min()}</td>
								<td>${c.max()}</td>
								<td>${c.count()}</td>
								<td>${c.total()}</td>
							</tr>
						</c:forEach>
						
						
					</tbody>
				</table>			
			</c:otherwise>
		
		</c:choose>
		

	</div>

	<div>
		
		<h3>Tops Incomes</h3>
		
		<c:choose>
		
			<c:when test="${ empty incomeCategory }">
				<span>There is no Income Data.</span>
			</c:when>
			
			<c:otherwise>
				<table>
					
					<thead>
						<tr>
							<th>No</th>
							<th>Category</th>
							<th>Min</th>
							<th>Max</th>
							<th>Quantity</th>
							<th>Total</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="c" varStatus="sts" items="${incomeCategory}">
							<tr>
								<td>${sts.index + 1}</td>
								<td>${c.name()}</td>
								<td>${c.min()}</td>
								<td>${c.max()}</td>
								<td>${c.count()}</td>
								<td>${c.total()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>			
			</c:otherwise>
		
		</c:choose>

	</div>
</div>