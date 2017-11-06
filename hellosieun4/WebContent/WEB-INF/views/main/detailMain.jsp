<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8"/>

<h1>body default page</h1>

<script>


	function toShowPicturs (deviceCode) {
		
		$("#toShowPictursFrm input").val(deviceCode);
		$("#toShowPictursFrm").submit();
	}
</script>

<div>
	cctv나올 화면
</div>

<form id="toShowPictursFrm" action="toShowPicture.do">
	<input type="hidden" name="deviceCode" value=""/>
</form>

<!-- 중앙 아래 왼쪽 네모칸 -->
<div style="float: left; border: 1px solid; width: 49%; height: 200px" onClick="toShowPicturs('${cctvInfo.deviceCode}')">상세사진보기</div>
<!-- 중앙 왼쪽 네모칸 끝 -->

<!-- 중앙 오른쪽 네모칸 -->
<div style="float: right; border: 1px solid; width: 49%; height: 200px">스피커 테스트하기</div>
<!-- 중앙 오른쪽 네모칸 끝 -->

