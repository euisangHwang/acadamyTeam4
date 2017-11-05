<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<fmt:requestEncoding value="UTF-8" />

<h1>sound_main page</h1>

<script>
	function addInputFile() {
		
		var inputFile = "<input type='file' name='sound[]' multiple onChange='addInputFile()'/>";
		$("#addSoundFrm").append(inputFile);
	}
	
	function checkAndInsert (frmE) {
		
		var result = true;
		
		var sounds = $(frmE).children("input[type=file]");
		sounds.each(function(index, sound){
			
			if(sound.files.length == 0) 
				sound.remove();
		});
		
		if(sounds.length == 0) {
			alert($(frmE).attr("title")+"할 요소가 없습니다.");
			result = false;
		}
		
		if(result)
		$("#addSoundFrm").submit();
	}
	
	function checkAndDeletet (frmE) {
		
		var sounds = $(frmE).children("input[type=file]");
		sounds.each(function(index, sound){
			
			if(sound.files.length == 0) 
				sound.remove();
		});
		
		if(sounds.length == 0) {
			alert($(frmE).attr("title")+"할 요소가 없습니다.");
			result = false;
		}
		
		if(result)
		$("#addSoundFrm").submit();
	}
</script>


<!-- 중앙  위 가운데 네모칸 -->
<div style="border: 1px solid; height: 500px">
	<form id="delSoundFrm">
		<c:if test="${fn:length(musics)} == 0">
			<div>음원이 없습니다.</div>
		</c:if>
		<c:forEach items="${musics}" var="musics">
			<div><input type="checkbox" name="delMusics" value="${musics.musicCode}"/>${musics.mOriName}</div>
		</c:forEach>
	</form> 
</div>
<!-- 중앙 위 가운데 네모칸 -->

<!-- 중앙 아래 왼쪽 네모칸 -->
<div style="float: left; border: 1px solid; width: 33%; height: 200px">
	<!-- 멀티 업로드 -->
	<form id="addSoundFrm" title="음원추가" action="insertSound.do" method="post" enctype="multipart/form-data">
		소리 추가 하기<input type="file" name="sound[]" onChange="addInputFile()" multiple onChange="addInputFile()"/>
		<input type="button" value="더 등록하기" onClick="addInputFile()">
		<input type="button" value="등록" onClick="checkAndInsert()">
	</form>
</div>
<!-- 중앙 왼쪽 네모칸 끝 -->

<!-- 중앙 아래 가운데 네모칸-->
<div style="float: left; border: 1px solid; width: 32%; height: 200px" onClick="checkAndDeletet($('#delSoundFrm'))">소리
	삭제하기</div>
<!-- 중앙 아래 가운데 네모칸 끝 -->

<!-- 중앙 오른쪽 네모칸 -->
<div style="float: right; border: 1px solid; width: 32%; height: 200px">소리
	테스트하기</div>
<!-- 중앙 오른쪽 네모칸 끝 -->
