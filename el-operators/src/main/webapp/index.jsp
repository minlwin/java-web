<%@page import="com.jdc.data.MyData"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Expressions</title>

</head>
<body>

	<h1>EL Expressions</h1>
	
	
		
	<c:set var="data1" scope="page" value="500" ></c:set>
	<c:set var="data2" scope="page" value="200" ></c:set>	
	
	<c:set var="subjects" scope="page" value="Java,JavaScript,Kotlin,TypeScript"></c:set>
	<c:set var="list" scope="page" value="${subjects.split(',')}" ></c:set>
		
	<div>
		<h1>Before ${subjects}</h1>
	</div>

	<c:remove var="subjects" ></c:remove>
			
	<div>
		<h1>After ${subjects}</h1>
	</div>
	<%
		pageContext.setAttribute("data",new MyData(100, 50));
		request.setAttribute("map", Map.of("Key1", "Servlet", "Key2", "JSP"));
	%>
	
	<h3>Arithmetic Operators</h3>
	
	<table>
		<tr>
			<td>[A + B]</td>
			<td>
				${data.data1} + ${data.data2} = ${data.data1 + data.data2}
			</td>
		</tr>
		<tr>
			<td>[A - B]</td>
			<td>
				${data1} - ${data2} = ${data1 - data2}
			</td>
		</tr>
		<tr>
			<td>[A * B]</td>
			<td>
				${data1} * ${data2} = ${data1 * data2}
			</td>
		</tr>
		<tr>
			<td>[A / B]</td>
			<td>
				${data1} / ${data2} = ${data1 div data2}
			</td>
		</tr>
		<tr>
			<td>[A % B]</td>
			<td>
				${data1} % ${data2} = ${data1 mod data2}
			</td>
		</tr>
	</table>
	
	<h3>Relational Operator</h3>
	
	<table>
		<tr>
			<td>Equal</td>
			<td>
				${(data1 + 100) eq data2 ? "Data 1 is equal to Data 2" : "Data 1 is not equal to Data 2"}
			</td>
		</tr>

		<tr>
			<td>Not Equal</td>
			<td>
				${data1 ne data2 ? "Data 1 is not equal to Data 2" : "Data 1 is equal to Data 2"}
			</td>
		</tr>

		<tr>
			<td>List [0]</td>
			<td>
				${ list[0] }
			</td>
		</tr>

		<tr>
			<td>List</td>
			<td>
				<ul>
					<c:forEach var="data" items="${list}">
						<li>
							<c:out value="${data}"></c:out>
						</li>
					</c:forEach>	
				</ul>
			</td>
		</tr>

		<tr>
			<td>Map ['Key1']</td>
			<td>
				${ map['Key1'] }
			</td>
		</tr>

		<tr>
			<td>Map ['Key2']</td>
			<td>
				${ map['Key2'] }
			</td>
		</tr>

	</table>
	
	<h3>Request Parameters</h3>
	
	<form>
		<div style="display: flex; flex-direction: column; align-items: flex-start;">
			<label>Name</label>
			<input type="text" name="name" />
		</div>
		
		<div style="display: flex; flex-direction: column; margin-top: 10px">
			<label>Courses</label>
			
			<div>
				<input id="Java" value="Java" type="checkbox" name="course" />
				<label for="Java" style="margin-right: 8px">Java</label>

				<input id="JavaScript" value="JavaScript" type="checkbox" name="course" />
				<label for="JavaScript" style="margin-right: 8px">JavaScript</label>

				<input id="Kotlin" value="Kotlin" type="checkbox" name="course" />
				<label for="Kotlin" style="margin-right: 8px">Kotlin</label>

				<input id="TypeScript" value="TypeScript" type="checkbox" name="course" />
				<label for="TypeScript" style="margin-right: 8px">TypeScript</label>
				
			</div>
		</div>
		
		<input type="submit" value="SEND" />
	</form>
	
	<div>
		${param['name']}
	</div>
	
	<div>
		${paramValues['course'][0]}
	</div>

	<div>
		${paramValues['course'][1]}
	</div>
</body>
</html>