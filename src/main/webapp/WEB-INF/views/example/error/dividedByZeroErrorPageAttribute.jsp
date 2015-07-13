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
  - Author(s): Sang Jae Shin
  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/WEB-INF/views/example/error/defaultErrorPage.jsp"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Divided By Zero <system:properties key="servlet.container.id" /></title>
<%@ include file="/WEB-INF/jspf/script.jspf"%>
<%@ include file="/WEB-INF/jspf/style.jspf"%>
<%@ include file="/WEB-INF/jspf/example/favicon.jspf"%>
</head>
<body>
	<%=1 / 0%>
</body>
</html>