<%--
  Created by IntelliJ IDEA.
  Author: 冯奕骅
  Date: 15/1/2
  Time: 18:11
--%>
<!DOCTYPE HTML>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${empty requestScope.commonInclude and empty param.commonInclude}">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="X-UA-Compatible" content="IE=IE8">

  <link type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-ui.min.css" rel="stylesheet"/>
  <link type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-ui.structure.min.css" rel="stylesheet"/>
  <link type="text/css" href="${pageContext.request.contextPath}/css/jquery/jquery-ui.theme.min.css" rel="stylesheet"/>

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-migrate-1.2.1.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-ui-1.11.2.min.js"></script>

  <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/DWRMessenger.js"></script>

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common-tools.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common-core.js"></script>

  <c:set var="commonInclude" value="1" scope="request"/>
</c:if>