<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/views/example/error/defaultErrorPage.jsp"%>
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
<title><spring:message code="title.emp.create" /> <system:properties key="servlet.container.id" /></title>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
<%@ include file="/WEB-INF/jspf/style.jspf"%>
<%@ include file="/WEB-INF/jspf/example/favicon.jspf"%>
</head>
<body class="composite">
	<%@ include file="/WEB-INF/jspf/example/header.jspf"%>
	<div id="breadcrumbs">
		<%@ include file="/WEB-INF/jspf/example/breadcrumb.jspf"%>
	</div>
	<div id="leftColumn">
		<div id="navcolumn">
			<%@ include file="/WEB-INF/jspf/example/navigation.jspf"%>
		</div>
	</div>
	<div id="bodyColumn">
		<div id="contentBox">
			<div class="section">
				<h2>Spring Security Taglibs</h2>
				<hr />
				<ul>
				<sec:authorize access="hasRole('ADMINISTRATOR')">
					<li>You have ROLE_ADMINISTRATOR.</li>
				</sec:authorize>
				<sec:authorize access="!hasRole('ADMINISTRATOR')">
					<li>You don't have ROLE_ADMINISTRATOR.</li>
				</sec:authorize>
				<sec:authorize access="hasRole('DEVELOPER')">
					<li>You have ROLE_DEVELOPER.</li>
				</sec:authorize>
				<sec:authorize access="!hasRole('DEVELOPER')">
					<li>You don't have ROLE_DEVELOPER.</li>
				</sec:authorize>
				<sec:authorize access="hasRole('USER')">
					<li>You have ROLE_USER.</li>
				</sec:authorize>
				<sec:authorize access="!hasRole('USER')">
					<li>You don't have ROLE_USER.</li>
				</sec:authorize>
				<sec:authorize access="isFullyAuthenticated()">
					<li>You are fully authenticated.</li>
				</sec:authorize>
				<sec:authorize access="!isFullyAuthenticated()">
					<li>You are not fully authenticated.</li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li>You are authenticated.</li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li>You are not authenticated.</li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li>You are anonymous.</li>
				</sec:authorize>
				<sec:authorize access="!isAnonymous()">
					<li>You are not anonymous.</li>
				</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear">
		<hr />
	</div>
	<%@ include file="/WEB-INF/jspf/example/footer.jspf"%>
</body>
</html>