<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/views/system/error/defaultErrorPage.jsp"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<!DOCTYPE html>
<!--
  - Copyright(c)2007 by codelabor.org
  -
  - Licensed under the Apache License, Version 2.0 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     http://www.apache.org/licenses/LICENSE-2.0
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->
<!--
  - Author(s): SHIN Sang-jae
  -->
<html>
<head>
<meta charset="UTF-8" />
<title><spring:message code="title.account.create" /> <system:properties key="servlet.container.id" /></title>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
<%@ include file="/WEB-INF/jspf/style.jspf"%>
<%@ include file="/WEB-INF/jspf/system/favicon.jspf"%>
</head>
<body class="composite">
	<%@ include file="/WEB-INF/jspf/system/header.jspf"%>
	<div id="breadcrumbs">
		<%@ include file="/WEB-INF/jspf/system/breadcrumb.jspf"%>
	</div>
	<div id="leftColumn">
		<div id="navcolumn">
			<%@ include file="/WEB-INF/jspf/system/navigation.jspf"%>
		</div>
	</div>
	<div id="bodyColumn">
		<div id="contentBox">
			<div class="section">
				<h2>
					<spring:message code="heading.account.read" />
				</h2>
				<hr />

				<!-- success message area -->
				<div class="successMessages">
					<c:forEach var="message" items="${messages}">
						<c:out value="${message}" />
					</c:forEach>
				</div>
				<table class="bodyTable">
					<tr class="b">
						<th><label for="username"><spring:message code="label.account.username" /></label></th>
						<td><span id="username"><c:out value="${accountDto.username}" /></span></td>
					</tr>
					<tr class="b">
						<th><label for="password"><spring:message code="label.account.password" /></label></th>
						<td><span id="password"><c:out value="${accountDto.password}" /></td>
					</tr>
					<tr class="b">
						<th><label for="givenName"><spring:message code="label.account.givenName" /></label></th>
						<td><span id="givenName"><c:out value="${accountDto.givenName}" /></td>
					</tr>
					<tr class="b">
						<th><label for="graceLoginsRemaining"><spring:message code="label.account.graceLoginsRemaining" /></label></th>
						<td><span id="graceLoginsRemaining"><c:out value="${accountDto.graceLoginsRemaining}" /></td>
					</tr>
					<tr class="b">
						<th><label for="mail"><spring:message code="label.account.mail" /></label></th>
						<td><span id="mail"><c:out value="${accountDto.mail}" /></td>
					</tr>
										<tr class="b">
						<th><label for="mobile"><spring:message code="label.account.mobile" /></label></th>
						<td><span id="mobile"><c:out value="${accountDto.mobile}" /></td>
					</tr>
										<tr class="b">
						<th><label for="graceLoginsRemaining"><spring:message code="label.account.graceLoginsRemaining" /></label></th>
						<td><span id="graceLoginsRemaining"><c:out value="${accountDto.graceLoginsRemaining}" /></td>
					</tr>
				</table>
				<hr />
				<a href="${pageContext.request.contextPath}/system/account/listAccount"><spring:message code="button.list" /></a> <a
					href="${pageContext.request.contextPath}/system/account/updateAccount?username=<c:out value="${accountDto.username}" />"><spring:message code="button.update" /></a>

			</div>
		</div>
	</div>
	<div class="clear">
		<hr />
	</div>
	<%@ include file="/WEB-INF/jspf/system/footer.jspf"%><%@ include file="/WEB-INF/jspf/dump.jspf"%>
</body>
</html>