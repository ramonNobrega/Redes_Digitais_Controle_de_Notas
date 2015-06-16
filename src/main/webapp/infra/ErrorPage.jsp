<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" var="bundle" />
<c:url value="/" var="home" />
<%@ page isErrorPage="true"%>
<html>
<head>
	<title><fmt:message key="main.app.title" bundle="${bundle}" /></title>
	<link href="<c:out value="${home}"/>img/favicon.ico" type="image/x-icon" rel="shortcut icon" />
	<link href="<c:out value="${home}"/>img/favicon.ico" type="image/x-icon" rel="icon" />
	<link href="<c:out value="${home}"/>css/themes/metro/default.css" type="text/css" rel="stylesheet" />
	<link href="<c:out value="${home}"/>css/themes/metro/theme.css" type="text/css" rel="stylesheet" />
	<script src="<c:out value="${home}"/>javax.faces.resource/jquery/jquery.js.jsf?ln=primefaces" type="text/javascript"></script>
	<script src="<c:out value="${home}"/>javax.faces.resource/primefaces.js.jsf?ln=primefaces" type="text/javascript"></script>
	<script src="<c:out value="${home}"/>javax.faces.resource/printer/printer.js.jsf?ln=primefaces" type="text/javascript"></script>
</head>
<body>
	<div id="maincontainer">
		<header id="mainheader">
			<div class="pagemargin">
				<a id="mainheadertitle" href="<c:out value="${home}"/>system/home.jsf"><fmt:message key="main.app.title" bundle="${bundle}" /></a>
				<div id="mainlogincontrol"></div>
			</div>
		</header>
		<div id="mainmenu">
			<div class="pagemargin"></div>
		</div>
		<div id="maincontent" class="pagemargin">
			<form id="errorpageform">
				<div id="errorpage">
					<div class="pagetitle">
						<fmt:message key="error.app.title" bundle="${bundle}" />
					</div>
					<div class="formrow" style="text-align:right;">
						<div class="formcell">
							<jsp:useBean id="now" class="java.util.Date"/>
							<fmt:formatDate value="${now}" type="both" dateStyle="medium" timeStyle="medium" />
						</div>
					</div>
					<div class="formrow">
						<div class="formcell">
							<div id="listTitle" class="blocktitle"><fmt:message key="error.app.message" bundle="${bundle}" /></div>
							<div><c:out value="${requestScope['javax.servlet.error.status_code']}"/></div>
							<div><c:out value="${requestScope['javax.servlet.error.exception']}"/></div>
							<div><c:out value="${requestScope['javax.servlet.error.message']}"/></div>
						</div>
					</div>
				</div>
			</form>
			<div style="margin-top: 20px;">
				<div style="display: inline-block; width: 100%; text-align: right;">
					<button id="errorpageprint" name="errorpageprint" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left" onclick="$(PrimeFaces.escapeClientId('errorpageform')).jqprint();return false;;;" type="button"><span class="ui-button-icon-left ui-icon ui-icon-print"></span><span class="ui-button-text"><fmt:message key="button.print" bundle="${bundle}" /></span></button>				
					<button id="errorpageback" name="errorpageback" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left" onclick="javascript:history.go(-1);;" type="button"><span class="ui-button-icon-left ui-icon ui-icon-arrowthick-1-w"></span><span class="ui-button-text"><fmt:message key="button.back" bundle="${bundle}" /></span></button>
				</div>
			</div>
		</div>
		<footer id="mainfooter">
			<div id="mainfootercontainer" class="pagemargin">
				<div id="mainfooterleft">
					<fmt:message key="main.app.mainfooterleft" bundle="${bundle}" />
				</div>
				<div id="mainfootercenter">
					<fmt:message key="main.app.mainfootercenter" bundle="${bundle}" />
				</div>
				<div id="mainfooterright">
					<a href="http://www.egen.com.br/"><img src="<c:out value="${home}"/>img/logo.png" border="0" height="18px" width="75px" /></a>
				</div>
			</div>
		</footer>
	</div>
</body>
</html>
