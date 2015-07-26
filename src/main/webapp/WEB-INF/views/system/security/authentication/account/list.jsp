<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/views/system/error/defaultErrorPage.jsp"%>
<%@ page import="org.codelabor.system.web.taglib.PaginationConstants, java.util.regex.Pattern, java.util.regex.Matcher"%>
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
<title><spring:message code="title.system.security.authentication.account.list" /> <system:properties key="servlet.container.id" /></title>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
<%@ include file="/WEB-INF/jspf/style.jspf"%>
<%@ include file="/WEB-INF/jspf/system/favicon.jspf"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/system/security/authorization/account/account.js"></script>
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
					<spring:message code="heading.system.security.authentication.account.list" />
				</h2>

				<!-- failure message area -->
				<div class="failureMessages">
					<ul>
						<c:forEach var="message" items="${failureMessages}">
							<li><c:out value="${message}" /></li>
						</c:forEach>
					</ul>
				</div>

				<!-- success message area -->
				<div class="successMessages">
					<ul>
						<c:forEach var="message" items="${successMessages}">
							<li><c:out value="${message}" /></li>
						</c:forEach>
					</ul>
				</div>

				<!-- search condition area -->
				<c:url var="listAccountUrl" value="/system/security/authorization/account/listAccount" />
				<form:form action="${listAccountUrl}" commandName="accountSearchConditionDto" method="get">
					<fieldset>
						<legend>
							<spring:message code="label.searchCondition" />
						</legend>
						<table class="bodyTable">
							<tr class="b">
								<th><spring:message code="label.system.security.authentication.account.givenName" /></th>
								<td><form:input path="givenName" /><form:errors path="givenName" /></td>
								<th><spring:message code="label.system.security.authentication.account.surname" /></th>
								<td><form:input path="surname" /><form:errors path="surname" /></td>
							</tr>
							<tr class="b">
								<th><spring:message code="label.system.security.authentication.account.mail" /></th>
								<td><form:input path="mail" /><form:errors path="mail" /></td>
								<th><spring:message code="label.system.security.authentication.account.mobile" /></th>
								<td><form:input path="mobile" /><form:errors path="mobile" /></td>
							</tr>
						</table>
						<input type="reset" value="<spring:message code="button.reset" />" /><input type="submit" value="<spring:message code="button.search" />" />
					</fieldset>
				</form:form>

				<!-- import / export area -->
				<c:url var="importAccountListUrl" value="/system/security/authorization/account/importAccountList" />
				<form action="${importAccountListUrl}" method="post" enctype="multipart/form-data">
					<fieldset>
						<legend>
							<spring:message code="label.importExport" />
						</legend>
						<table class="bodyTable">
							<tr class="b">
								<th><spring:message code="label.excelFile" /></th>
								<td><input type="file" name="file" /> <input type="submit" value="<spring:message code="button.import" />"
									onclick="importFileWithSearchCondition()" /> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></td>
								<td><c:choose>
										<c:when test="${not empty pageContext.request.queryString}">
											<a
												href="${pageContext.request.contextPath}/system/security/authorization/account/exportAccountListOnCurrentPage?<c:out value="${pageContext.request.queryString}" />">
												<spring:message code="button.export.current.page" />
											</a>
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/system/security/authorization/account/exportAccountListOnCurrentPage"> <spring:message
													code="button.export.current.page" />
											</a>
										</c:otherwise>
									</c:choose> &nbsp; <%
 	String queryString = request.getQueryString();
 	String replacedQueryString = null;
 	if (queryString != null) {
 		StringBuilder sb = new StringBuilder();
 		sb.append(PaginationConstants.PAGE_NO_PARAM_NAME).append("=[0-9]*&");
 		sb.append(PaginationConstants.MAX_ROW_PER_PAGE_PARAM_NAME).append("=[0-9]*&?");
 		Pattern pattern = Pattern.compile(sb.toString());
 		Matcher matcher = pattern.matcher(queryString);
 		replacedQueryString = matcher.replaceAll("");
 	}
 	pageContext.setAttribute("replacedQueryString", replacedQueryString);
 %> <c:choose>
										<c:when test="${not empty replacedQueryString}">
											<a href="${pageContext.request.contextPath}/system/security/authorization/account/exportAccountList?<c:out value="${replacedQueryString}" />"> <spring:message
													code="button.export.all.page" />
											</a>
										</c:when>
										<c:otherwise>
											<a href="${pageContext.request.contextPath}/system/security/authorization/account/exportAccountList"> <spring:message
													code="button.export.all.page" />
											</a>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</table>
					</fieldset>
				</form>

				<!-- list area -->
				<c:url var="deleteAccountListUrl" value="/system/security/authorization/account/deleteAccountList" />
				<form:form action="${deleteAccountListUrl}" commandName="integerIdListDto">

					<!-- failure messages area -->
					<div class="failureMessages">
						<codelaborForm:errors path="*" />
					</div>

					<table class="bodyTable">
						<tr class="a">
							<th><spring:message code="label.check" /></th>
							<th><spring:message code="label.system.security.authentication.account.username" /></th>
							<th><spring:message code="label.system.security.authentication.account.givenName" /></th>
							<th><spring:message code="label.system.security.authentication.account.surname" /></th>
							<th><spring:message code="label.system.security.authentication.account.mail" /></th>
							<th><spring:message code="label.system.security.authentication.account.mobile" /></th>
							<th><spring:message code="label.system.security.authentication.account.authorities" /></th>
							<th><spring:message code="label.system.security.authentication.account.enabled" /></th>
							<th><spring:message code="label.system.security.authentication.account.accountNonLocked" /></th>
							<th><spring:message code="label.system.security.authentication.account.accountNonExpired" /></th>
							<th><spring:message code="label.system.security.authentication.account.credentialsNonExpired" /></th>
							<th><spring:message code="label.system.security.authentication.account.graceLoginsRemaining" /></th>
						</tr>
						<c:forEach var="accountDto" items="${accountDtoList}">
							<tr class="b">

								<td><form:checkbox path="id" value="${accountDto.accountNo}" /></td>
								<td><a href="${pageContext.request.contextPath}/system/security/authorization/account/readAccount?username=${accountDto.username}"> <c:out
											value="${accountDto.username}" />
								</a></td>
								<td><a href="${pageContext.request.contextPath}/system/security/authorization/account/readAccount?username=${accountDto.username}"> <c:out
											value="${accountDto.username}" />
								</a></td>
								<td><c:out value="${accountDto.givenName}" /></td>
								<td><c:out value="${accountDto.surname}" /></td>
								<td><c:out value="${accountDto.mail}" /></td>
								<td><c:out value="${accountDto.mobile}" /></td>
								<td><c:out value="${accountDto.authorities}" /></td>
								<td><c:out value="${accountDto.enabled}" /></td>
								<td><c:out value="${accountDto.accountNonLocked}" /></td>
								<td><c:out value="${accountDto.accountNonExpired}" /></td>
								<td><c:out value="${accountDto.credentialsNonExpired}" /></td>
								<td><c:out value="${accountDto.graceLoginsRemaining}" /></td>
							</tr>
						</c:forEach>
					</table>
					<hr />
					<a href="${pageContext.request.contextPath}/system/security/authorization/account/createAccount"><spring:message code="button.create" /></a>
					<input type="submit" value="<spring:message code="button.delete" />" onclick="deleteBySearchCondition()" />
				</form:form>

				<!--  pagination -->
				<pg:pagination cssClass="pageArea" numberOfRow="${numberOfRow}">
					<pg:firstIndex cssClass="btn_prev">
						<!--
						<img src="/images/btn/btn_first.png" alt="<spring:message code="alternate.first" />" />
						-->
						|<
					</pg:firstIndex>
					<pg:previousIndex cssClass="btn_prev">
						<!--
						<img src="/images/btn/btn_prev.png" alt="<spring:message code="alternate.prev" />" />
						-->
						<
					</pg:previousIndex>
					<pg:currentIndex />
					<pg:nextIndex cssClass="btn_next">
						<!--
						<img src="/images/btn/btn_next.png" alt="<spring:message code="alternate.next" />" />
						-->
						>
					</pg:nextIndex>
					<pg:lastIndex cssClass="btn_next">
						<!--
						<img src="/images/btn/btn_last.png" alt="<spring:message code="alternate.last" />" />
						-->
						>|
					</pg:lastIndex>
				</pg:pagination>
			</div>
		</div>
	</div>
	<div class="clear">
		<hr />
	</div>
	<%@ include file="/WEB-INF/jspf/system/footer.jspf"%><%@ include file="/WEB-INF/jspf/dump.jspf"%>
</body>
</html>