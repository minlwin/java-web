<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="searchIcon" value="/assets/svg/search.svg"></c:url>

<form class="form-inline">
	
	<div class="form-group">
		<label>From</label>
		<input type="date" name="from" value="<%= request.getAttribute("from") %>" />
	</div>

	<div class="form-group">
		<label>To</label>
		<input type="date" name="to" value="<%= request.getAttribute("to") %>" />
	</div>
	
	<div>
		<button type="submit" class="btn mr-4 icon-btn">
			<img src="${searchIcon}" class="icon icon-left" />
			Search
		</button>
	</div>
</form>

<table>
	<thead>
		<tr>
			<th>Date</th>
			<th>Employee</th>
			<th>Category</th>
			<th class="right">Expense</th>
			<th class="right">Income</th>
			<th class="right">Balance</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="vo" items="${list}">
		<tr>
			<td>
				<c:url value="/employee/transaction/details" var="detaulsUrl">
					<c:param name="id" value="${va.id}"></c:param>
				</c:url>
				<a href="${detaulsUrl}">${vo.date}</a>
			</td>
			<td>${vo.employee.name}</td>
			<td>${vo.category}</td>
			<td class="right">${vo.expense} MMK</td>
			<td class="right">${vo.income} MMK</td>
			<td class="right">${vo.balance} MMK</td>
		</tr>
		</c:forEach>
	</tbody>
	
</table>