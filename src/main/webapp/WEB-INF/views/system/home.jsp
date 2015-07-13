<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>System Home <system:properties key="servlet.container.id" /></title>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
<%@ include file="/WEB-INF/jspf/style.jspf"%>
<%@ include file="/WEB-INF/jspf/example/favicon.jspf"%>
</head>
<body>
	<h1>System Home</h1>
	<hr />
	${serverTime}
	<br />
	<table>
		<tr>
			<th>Principal</th>
			<td><sec:authentication property="principal" /></td>
		</tr>
		<tr>
			<th>Authenticated Type</th>
			<td><sec:authorize access="isFullyAuthenticated()">by login-form</sec:authorize>
				<sec:authorize access="isAuthenticated()">by remember-me</sec:authorize></td>
		</tr>
	</table>
</body>
</html>