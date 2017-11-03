<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />

<h1>sound_addSound page</h1>

<!-- 중앙  위 가운데 네모칸 -->
<div style="border: 1px solid; height: 500px">
	등록된 소리 리스트 <br> 1.개 휘슬소리<br> 2.다른 동물 소리 <br> 3.천둥소리 <br>
	4.천둥소리 <br> 5.천둥소리 <br> 6.천둥소리
</div>

<!-- 중앙 위 가운데 네모칸 -->
<div style="border: 1px solid; height: 200px">
	<input type="button" value="추가하기" onClick="uploadmp3.jsp">
</div>

