<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<fmt:requestEncoding value="UTF-8" />

<h1>sound_main page</h1>

<script>

	$(document).ready(function () {
		
		if("${uploadMsg}" != "")
		alert("uploadMsg : "+"${uploadMsg}");
	});

	function addInputFile() {
		
		var inputFile = "<input type='file' name='sound[]' multiple onChange='addInputFile()'/>";
		var lastInput = $("input[type=file]").last();
		lastInput.after(inputFile);
	}
	
	function checkAndInsert () {
		
		var result = true;
		
		var frm = $("#addSoundFrm");
		var sounds = frm.children("input[type=file]");
		console.log(sounds.length);
		sounds.each(function(index, sound){
			
			var dotLeng = sound.value.lastIndexOf(".");
			var endLeng = sound.value.length;
			var soundTTC = sound.value.substring(dotLeng+1, endLeng).toLowerCase();
			
			if (sound.files.length == 0 || soundTTC != "mp3") {
				
				if(frm.children("input[type=file]").length > 1)
					sound.remove();
			}
		});
		
		if(frm.children("input[type=file]").last()[0].files.length == 0) {
			
			alert($("#addSoundFrm").attr("title")+"할 요소가 없습니다.");
			result = false;
		}
		
		if(result)
			frm.submit();
	}
	
	var soundMange =  {
			
			frm : $("#delSoundFrm"),
			sounds : $("#delSoundFrm input").filter(":checked"),
			
			checkSound : function () {
				
				var result = true;
				if(this.sounds.length == 0) {
					
					alert(this.attr("title")+"할 요소가 없습니다.");
					result = false;
				}
				
				return result;
				
			},
			
			checkAndDeletet	: function () {
				
				var result = this.checkSound();
				
				if(result)
					this.frm.submit();
			},
			
			testSound : function () {
				
				this.checkSound();
				var result = true;
				if(this.sounds.length > 1) {
					
					alert("테스트할 음원을 하나만 선택하세요.")
					result = false;
				}
				
				if(result) {
					/* insertCmd(deviceCode, comandLevel); */
				}
				
			}
	
	}
	
</script>


<!-- 중앙  위 가운데 네모칸 -->
<div style="border: 1px solid; height: 500px">
	<form id="delSoundFrm" action="deleteSound.do" title="음원삭제">
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
		소리 추가 하기(mp3만 허용)<input type="file" name="sound[]" onChange="addInputFile()" multiple onChange="addInputFile()"/>
		<div>
			<input type="button" value="더 등록하기" onClick="addInputFile()">
			<input type="button" value="등록" onClick="checkAndInsert()">
		</div>
	</form>
</div>
<!-- 중앙 왼쪽 네모칸 끝 -->

<!-- 중앙 아래 가운데 네모칸-->
<div style="float: left; border: 1px solid; width: 32%; height: 200px" onClick="soundMange.checkAndDeletet()">소리
	삭제하기</div>
<!-- 중앙 아래 가운데 네모칸 끝 -->

<!-- 중앙 오른쪽 네모칸 -->
<div style="float: right; border: 1px solid; width: 32%; height: 200px" onClick="soundMangetestSound()">소리
	테스트하기</div>
<!-- 중앙 오른쪽 네모칸 끝 -->
