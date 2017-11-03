<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<fmt:requestEncoding value="UTF-8"/>
<html>
<head>
	<title>${title}</title>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery-ui.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery/jquery.cookie.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery/jquery-ui.css"/>
</head>
<body>
<div id="header">
<t:insertAttribute name="header"/>
</div>

<div id="nav">
 <t:insertAttribute name="nav"/>
</div>

<div id="section">
<t:insertAttribute name="section"/>
</div>

<div id="footer">
 <t:insertAttribute name="footer"/>
</div>
</body>
</html>