<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<fmt:requestEncoding value="UTF-8" />

<html>

<!-- head -->
<div id="head">
	<t:insertAttribute name="head" />
</div>
<body>
	<div class="body-wrapper">
		<!-- header -->
		<t:insertAttribute name="header" />
		<!-- body  -->
		<div style="margin-top: 60px;">
			<t:insertAttribute name="body" />
		</div>
		<!-- bottom -->
		<t:insertAttribute name="bottom" />
	</div>
</body>
</html>