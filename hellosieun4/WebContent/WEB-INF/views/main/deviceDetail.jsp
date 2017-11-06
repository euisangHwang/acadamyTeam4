<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<fmt:requestEncoding value="UTF-8" />

<script>
	
	$(document).ready(function () {
		
		//매칭정보가 있으면
		if("${matchInfo.musicCode == null}" == "false") {
			
			$("#matchFrm").attr("action", "updateMatch.do");
			$("#matchFrm input[type=submit]").val("변경");
		} else {
			
			$("#matchFrm").attr("action", "matchSpeack_Music.do");
			$("#matchFrm input[type=submit]").val("등록");
		}
	})

</script>

<center>
	<form id="matchFrm" action="matchSpeack_Music.do" method="post">
		<input type="hidden" name="speakerCode" value="${matchInfo.deviceCode}"/>
		<div>장치명 : ${matchInfo.deviceName}</div>	
		<div>		
			<c:if test="${fn:length(musics)} == 0">
					<div>음원이 없습니다.</div>
			</c:if> 
			<select name="musicCode">
				<c:forEach items="${musics}" var="musics">
					<c:if test="${matchInfo.musicCode eq musics.musicCode}">
						<option value="${musics.musicCode}" selected>${musics.mOriName}</option>
					</c:if>
					<c:if test="${matchInfo.musicCode ne musics.musicCode}">
						<option value="${musics.musicCode}">${musics.mOriName}</option>
					</c:if>
				</c:forEach>
			</select> <input type="button" value="소리 테스트하기">
		</div>
		<input type="submit" value="저장">
	</form>
</center>