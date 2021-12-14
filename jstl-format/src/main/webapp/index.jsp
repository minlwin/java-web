<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formatting</title>
</head>
<body>



	<h1>Formatting</h1>
	
	<c:choose>
		
		<c:when test="${ empty number }">
			<ul>
				<li>
					<c:url value="/format" var="formatUrl"></c:url>
					<a href="${formatUrl}">Format Tag Demo</a>
				</li>
				<li>
					<c:url value="/message.jsp" var="message"></c:url>
					<a href="${message}">Internationalization</a>
				</li>
			</ul>
		</c:when>
		
		<c:otherwise>
		
			<table>
			
				<tr>
					<td>Number</td>
					<td>
						<fmt:formatNumber value="${number}" 
							pattern="#,##0.00 MMK" ></fmt:formatNumber>
					</td>
				</tr>
			
				<tr>
					<td>Floating Number</td>
					<td>
						<fmt:formatNumber value="${numberFloat}" 
							maxFractionDigits="5" minFractionDigits="5"
							groupingUsed="false" ></fmt:formatNumber>
					</td>
				</tr>

				<tr>
					<td>Currency</td>
					<td>
						<fmt:formatNumber value="${number}" 
							type="currency" currencySymbol="Ks" currencyCode="MMK"
							groupingUsed="true" ></fmt:formatNumber>
					</td>
				</tr>

				<tr>
					<td>Persent</td>
					<td>
						<fmt:formatNumber value="${persentValue / 100}" 
							type="percent"
							groupingUsed="true" ></fmt:formatNumber>
					</td>
				</tr>

				<tr>
					<td>Date</td>
					<td>
						<fmt:formatDate value="${date}" dateStyle="full" />
					</td>
				</tr>

				<tr>
					<td>Time</td>
					<td>
						<fmt:formatDate value="${date}" type="time" timeStyle="full" />
					</td>
				</tr>

				<tr>
					<td>Date Time</td>
					<td>
						<fmt:formatDate value="${date}" type="both" dateStyle="short" timeStyle="short"/>
					</td>
				</tr>
				
				<tr>
					<td>Custom Pattern</td>
					<td>
						<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm" />
					</td>
				</tr>

				<tr>
					<td>New York</td>
					<td>
						<fmt:formatDate value="${date}" timeZone="America/New_York" pattern="yyyy-MM-dd HH:mm" />
					</td>
				</tr>

				<tr>
					<td>Japan</td>
					<td>
						<fmt:timeZone value="Japan">
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm" />
						</fmt:timeZone>
					</td>
				</tr>

				<tr>
					<td>Singapore</td>
					<td>
						<fmt:timeZone value="Singapore">
							<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm" />
						</fmt:timeZone>
					</td>
				</tr>
			</table>
			
			<div>
				<h3>Switch Time Zone</h3>
				
				<ul>
					<li>
						<a href="?tz=America/New_York">New York</a>
					</li>
					<li>
						<a href="?tz=Singapore">Singapore</a>
					</li>
					<li>
						<a href="?tz=Japan">Japan</a>
					</li>
				</ul>
			</div>
			
			<c:set value="Asia/Rangoon" var="yangonTime"></c:set>
			
			<fmt:setTimeZone value="${empty param.tz? yangonTime : param.tz}"/>
			
			<h4>
				<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</h4>
		
		</c:otherwise>
	</c:choose>
	

	
</body>
</html>