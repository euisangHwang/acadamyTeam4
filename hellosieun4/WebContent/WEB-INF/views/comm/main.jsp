<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<fmt:requestEncoding value="UTF-8" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Insert title here</title>
</head>
<body>

	<!-- header -->
	<div id="header">
		<t:insertAttribute name="header" />
	</div>

	<!-- top -->
	<div id="top" style="border: 1px solid; height: 150px">
		<t:insertAttribute name="top" />
	</div>

	<!-- body  -->
	<div id="body" style="border: 1px solid; height: 700px">
		<t:insertAttribute name="body" />
	</div>

	<!-- bottom -->
	<div id="bottom" style="border: 1px solid; height: 150px">
		<t:insertAttribute name="bottom" />
	</div>

</body>
</html>