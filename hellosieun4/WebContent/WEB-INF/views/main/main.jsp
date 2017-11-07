<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8"/>

<h1>body default page</h1>

<script>
	function toDetailMain (deviceCode) {
		
		$("#toDetailMainFrm input").val(deviceCode);
		$("#toDetailMainFrm").submit();
	}
</script>

<form id="toDetailMainFrm" action="toDetailMain.do" method="post" style="display: none;">
	<input type="hidden" name="deviceCode" value=""/>
</form>

<c:forEach items="${devices}" var="devices">
	<div style="width: 30%;" onClick="toDetailMain('${devices.deviceCode}')">
		${devices.deviceName}
	</div>
</c:forEach>

<!-- 중앙 아래 왼쪽 네모칸 -->
<div style="float: left; border: 1px solid; width: 49%; height: 200px" onClick="pageSubmit('toSound_main.do')">소리
	관리</div>
<!-- 중앙 왼쪽 네모칸 끝 -->

<!-- 중앙 오른쪽 네모칸 -->
<div style="float: right; border: 1px solid; width: 49%; height: 200px" onClick="pageSubmit('deviceSet.do')">제한구역 조회</div>
<!-- 중앙 오른쪽 네모칸 끝 -->

<!-- Main Javascript -->
<script src="assets/js/main.js"></script>
<script src="assets/js/test.js" type="dumb"></script>
<script src="assets/js/jquery.uploadfile.js" type="text/javascript"></script>