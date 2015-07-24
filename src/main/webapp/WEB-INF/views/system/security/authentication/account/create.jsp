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
  - Unless by applicable law or agreed to in writing, software
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
					<spring:message code="heading.account.create" />
				</h2>
				<hr />
				<form:form commandName="accountDto">
					<!-- failure message area -->
					<div class="failureMessages">
						<!--
						<form:errors path="*" />
						-->
					</div>
					<table class="bodyTable">
						<tr class="b">
							<th><label for="username"><spring:message code="label.account.username" /></label></th>
							<td><input name="username" value="${accountDto.username}" placeholder="User ID" required="required" form="accountDto" /><span><form:errors
										path="username" /></span></td>
						</tr>
						<tr class="b">
							<th><label for="password"><spring:message code="label.account.password" /></label></th>
							<td><input type="password" name="password" value="${accountDto.password}" placeholder="" required="required" form="accountDto" /><span><form:errors
										path="password" /></span></td>
						</tr>
						<tr class="b">
							<th><label for="passwordConfirm"><spring:message code="label.account.passwordConfirm" /></label></th>
							<td><input type="password" name="passwordConfirm" value="${accountDto.passwordConfirm}" placeholder="" required="required" form="accountDto" /><span><form:errors
										path="passwordConfirm" /></span></td>
						</tr>
						<tr class="b">
							<th><label for="givenName"><spring:message code="label.account.givenName" /></label></th>
							<td><input name="givenName" value="${accountDto.givenName}" placeholder="" required="required" form="accountDto" /><span><form:errors
										path="givenName" /></span></td>
						</tr>
						<tr class="b">
							<th><label for="surname"><spring:message code="label.account.surname" /></label></th>
							<td><input name="surname" value="${accountDto.surname}" placeholder="" required="required" form="accountDto" /><span><form:errors
										path="surname" /></span></td>
						</tr>
						<tr class="b">
							<th><label for="mail"><spring:message code="label.account.mail" /></label></th>
							<td><input name="mail" value="${accountDto.mail}" placeholder="xxxx@xxxx.xxxx" required="required" form="accountDto" /><span><form:errors
										path="mail" /></span></td>
						</tr>
						<tr class="b">
							<th><label for="mobile"><spring:message code="label.account.mobile" /></label></th>
							<td><input name="mobile" value="${accountDto.mobile}" placeholder="" required="required" form="accountDto" /><span><form:errors
										path="mobile" /></span></td>
						</tr>
						<sec:authorize access="hasRole('ADMINISTRATOR')">
							<tr class="b">
								<th><label for="authorities"><spring:message code="label.account.authorities" /></label></th>
								<td><form:select path="authorities" items="${authoritiesMap}" /><span><form:errors path="authorities" /></span></td>
							</tr>
							<tr class="b">
								<th><label for="enabled"><spring:message code="label.account.enabled" /></label></th>
								<td><spring:message code="label.account.enabled" /> <form:radiobutton path="enabled" value="true" />&nbsp; <spring:message
										code="label.account.disabled" /> <form:radiobutton path="enabled" value="false" /> <span><form:errors path="enabled" /></span></td>
							</tr>
							<tr class="b">
								<th><label for="accountNonLocked"><spring:message code="label.account.accountNonLocked" /></label></th>
								<td><spring:message code="label.account.enabled" /> <form:radiobutton path="accountNonLocked" value="true" />&nbsp; <spring:message
										code="label.account.disabled" /> <form:radiobutton path="accountNonLocked" value="false" /> <span><form:errors path="accountNonLocked" /></span></td>
							</tr>
							<tr class="b">
								<th><label for="accountNonExpired"><spring:message code="label.account.accountNonExpired" /></label></th>
								<td><spring:message code="label.account.enabled" /> <form:radiobutton path="accountNonExpired" value="true" />&nbsp; <spring:message
										code="label.account.disabled" /> <form:radiobutton path="accountNonExpired" value="false" /> <span><form:errors path="accountNonExpired" /></span></td>
							</tr>
							<tr class="b">
								<th><label for="credentialsNonExpired"><spring:message code="label.account.credentialsNonExpired" /></label></th>
								<td><spring:message code="label.account.enabled" /> <form:radiobutton path="credentialsNonExpired" value="true" />&nbsp; <spring:message
										code="label.account.disabled" /> <form:radiobutton path="credentialsNonExpired" value="false" /> <span><form:errors
											path="credentialsNonExpired" /></span></td>
							</tr>
							<tr class="b">
								<th><label for="graceLoginsRemaining"><spring:message code="label.account.graceLoginsRemaining" /></label></th>
								<td><input name="graceLoginsRemaining" value="${accountDto.graceLoginsRemaining}" placeholder="User ID" required="required" form="accountDto" /><span><form:errors
											path="graceLoginsRemaining" /></span></td>
							</tr>
						</sec:authorize>
					</table>
					<hr />
					<sec:authorize access="hasRole('ADMINISTRATOR')">
						<a href="${pageContext.request.contextPath}/system/security/authentication/account/listAccount"><spring:message code="button.list" /></a>
					</sec:authorize>
					<input type="submit" value="<spring:message code='button.save'/>" form="accountDto" />
					<input type="reset" value="<spring:message code='button.reset'/>" form="accountDto" />
				</form:form>
			</div>
		</div>
	</div>
	<div class="clear">
		<hr />
	</div>
	<%@ include file="/WEB-INF/jspf/system/footer.jspf"%><%@ include file="/WEB-INF/jspf/dump.jspf"%>
</body>
</html>