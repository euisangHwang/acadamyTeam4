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
/* 			$("#devDetailFrm input").val($this.attr("title"));
			$("#devDetailFrm").submit();
			 */
			$.ajax({
				
				url 	: "deviceDetail2.do",
				type	: "post",
				data	: {"deviceCode":$this.attr("title")},
				async	: false,
				success : function (result) {
					//matchInfo - deviceCode,deviceName,musicCode/musics(musicCode,mOriName)
					var toResult = JSON.parse(result);
					var matchInfo = toResult.matchInfo;
					var musics = toResult.musics;
					
					//action,버튼 설정
					if(matchInfo.musicCode) {
						
						$("#matchFrm").attr("action", "updateMatch.do");
						$("#matchFrm input[type=submit]").val("변경");
					} else {
						
						$("#matchFrm").attr("action", "matchSpeack_Music.do");
						$("#matchFrm input[type=submit]").val("등록");
					}
					
					//matchInfo 셋팅
					$("#matchFrm input[type=hidden]").val(matchInfo.deviceCode);
					$("#matchFrm div[title=deviceName]").text("장치명 : "+matchInfo.deviceName);
					$("#matchFrm input[type=button]").attr("onClick","insertCmd("+matchInfo.deviceCode+",2)");
					
					if(musics.length == 0)
						$("#matchFrm div[title=musicList]").text("음원이 없습니다.");
					else {
						
						var options = "";
						musics.forEach(function (music, index) {
							
							options += "<option value='"+music.musicCode+"'";
							if(matchInfo.musicCode == music.musicCode)
							{options += "selected"};
							options +=  ">"+music.mOriName+"</option>";
						});
						
						$("#matchFrm div[title=musicList] select").append(options);
					}
					
					$("#dDetailModel").modal();
					
				},error : function () {
					
				}
				
				
			})
		});
	});
</script>

<!-- 중앙  위 가운데 네모칸 -->
<div id="dHomeimg" style="border: 1px solid; width: 400px; height:400px; text-align: center; 
		line-height: 400px; background-image: url(${homeimg.pFullName}); background-size:cover;">
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
			<c:choose>
				<c:when test="${devices.deviceSort == 'speaker'}">
					<button type="button" name="devCode" title="${devices.deviceCode}">(${devices.deviceSort})${devices.deviceName}</button>
				</c:when>
				<c:otherwise>
					<button type="button" name="devCode2" title="${devices.deviceSort}" disabled>(${devices.deviceSort})${devices.deviceName}</button>
				</c:otherwise>
			</c:choose>
		</c:forEach>
</div>

<div>
	<form id="insertDevice" action="insertDevice.do" method="post">
		<input type="text" name="deviceIP" placeholder="Input_deviceIP">
		<input type="text" name="deviceName" placeholder="Input_deviceName">
		<select name="deviceSort">
			<option value="speaker">speaker</option>
			<option value="speaker">cctv</option>
		</select>
		<input type="hidden" name="sensorCode"/>
		<button>addDevice</button>
	</form>
</div>
<!-- 중앙 아래 가운데 네모칸 끝 -->