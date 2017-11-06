<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<fmt:requestEncoding value="UTF-8" />

<script>

	$(document).ready(function () {
		
		//사진이 없으면
		if("${homeimg.pPath == null}" == "true")
			$("#dHomeimg").text("사진이 없습니다.");
			
		
		$("button[name=devCode]").click(function () {
			
			var $this = $(this);
			$("#devDetailFrm input").val($this.attr("title"));
			$("#devDetailFrm").submit();
		});
	});
</script>

<!-- 중앙  위 가운데 네모칸 -->
<div id="dHomeimg" style="border: 1px solid; width: 400px; height:400px; text-align: center; 
		line-height: 400px; background-image: url(${homeimg.pPath}); background-size:cover;">
</div>
<!-- 중앙 위 가운데 네모칸 -->

<!-- 중앙 아래 가운데 네모칸-->
<div style="border: 1px solid; height: 200px">

	<form id="devDetailFrm" action="deviceDetail.do" method="post">
		<input type="hidden" name="deviceCode"/>
	</form>
		<c:if test="${fn:length(devices)} == 0">
			<div>장치가 없습니다.</div>
		</c:if>
		<c:forEach items="${devices}" var="devices">
			<c:if test="${devices.deviceSort =='speaker'}">
				<button name="devCode" title="${devices.deviceCode}">${devices.deviceSort}${devices.deviceCode}</button>
			</c:if>
			<c:if test="${devices.deviceSort =='sensor'}">
				<button name="devCode2" title="${devices.deviceSort}" disabled>${devices.deviceSort}${devices.deviceCode}</button>
			</c:if>
		</c:forEach>
</div>
<!-- 중앙 아래 가운데 네모칸 끝 -->