<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>

	function toDetailMain2 (deviceCode) {
		
		$("#toDetailMainFrm2 input").val(deviceCode);
		$("#toDetailMainFrm2").submit();
	}

</script>

<form id="toDetailMainFrm2" action="toDetailMain.do" method="post" style="display: none;">
	<input type="hidden" name="deviceCode" value=""/>
</form> 

<h4>사진보기</h4>
 
<c:forEach items="${pictures}" var="pictures" varStatus="status">
	<img src="${pictures.pFullName}" height=150 width=150>
</c:forEach>

<div style="width: 30%;" onClick="toDetailMain2('${pictures[0].deviceCode}')">뒤로가기</div>
