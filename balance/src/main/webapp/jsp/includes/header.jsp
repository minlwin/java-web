<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="homeUrl" value="/employee/home"></c:url>
<c:url var="homeIcon" value="/assets/svg/home.svg"></c:url>
<c:url var="homeActivity" value="${activeMenu eq 'home' ? 'active' : ''}"></c:url>

<c:url var="expenseUrl" value="/employee/transaction/search">
	<c:param name="type" value="Expense"></c:param>
</c:url>
<c:url var="expenseIcon" value="/assets/svg/folder-minus.svg"></c:url>
<c:url var="expenseActivity" value="${activeMenu eq 'expenses' ? 'active' : ''}"></c:url>

<c:url var="incomeUrl" value="/employee/transaction/search">
	<c:param name="type" value="Income"></c:param>
</c:url>
<c:url var="incomeIcon" value="/assets/svg/folder-plus.svg"></c:url>
<c:url var="incomeActivity" value="${activeMenu eq 'incomes' ? 'active' : ''}"></c:url>

<c:url var="balanceUrl" value="/manager/balance"></c:url>
<c:url var="balanceIcon" value="/assets/svg/stats-dots.svg"></c:url>
<c:url var="balanceActivity" value="${activeMenu eq 'reports' ? 'active' : ''}"></c:url>

<c:url var="employeeUrl" value="/manager/employee/search"></c:url>
<c:url var="employeeIcon" value="/assets/svg/users.svg"></c:url>
<c:url var="employeeActivity" value="${activeMenu eq 'employees' ? 'active' : ''}"></c:url>

<c:url var="logoutUrl" value="/logout"></c:url>
<c:url var="logoutIcon" value="/assets/svg/exit.svg"></c:url>

<header class="shadow">
	<div class="brand-logo">
		<img src="${homeIcon}" class="icon icon-left" />
		Balance
	</div>
	
	<nav>
		<a class='${homeActivity}' href="${homeUrl}">
			<img class="icon icon-left" src="${homeIcon}"> 
			Dashboard
		</a>
		<a class="${expenseActivity}" href="${expenseUrl}">
			<img src="${expenseIcon}" class="icon icon-left" />
			Expenses
		</a>
		<a class='${incomeActivity}' href="${incomeUrl}">
			<img src="${incomeIcon}" class="icon icon-left" />
			Incomes
		</a>

		<c:if test="${loginInfo.manager}">
			<a class="${balanceActivity}" href="${balanceUrl}">
				<img src="${balanceIcon}" class="icon icon-left" />
				Balance Report
			</a>
			<a class="${employeeActivity}" href="${employeeUrl}">
				<img src="${employeeIcon}" class="icon icon-left" />
				Employees
			</a>
		</c:if>

		<a href="${logoutUrl}">
			<img src="${logoutIcon}" class="icon icon-left" />
			Log Out
		</a>
	</nav>
</header>