<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 왜 WEB-INF 이하 주소로 SRC하면 안먹는지 질문 -->
<script>
	//사용자 정보 유효성 검사 모듈
	//각 탭별로 입력이 끝났을 시 유효성 검사
	//탭들의 유형 input(text, checkbox) / select
	//각  탭의 이벤트 - change / submit
	//검사유형 - 필수값 체크(text+select, checkbox), 형식 체크(name별로 따로)
	
	//필수체크 - 공통 : 값의 유무로 체크 (select랑 check도 기본값을 ""로 하면 ok)
	//필수체크 - 차이점 : 값을 다른 방식으로 추출해야 함 (text.val() / chk.child("cheked") -얘는 게다가 복수값임 / select("option:selected"))
	//input[text, select]-change => 필수값 체크 -단일
	//input[checkbox]-chamge => 필수값 체크          -복수
	
	//형식체크 - 공통 : 체크로직
	//형식체크 - 차이점 : name별 체크포맷 
	//inputs 형식  체크 - 네임별
	//

	
	$(document).ready(function () {
		
		alert("hello");
		
		//이벤트처리
		/*
			1. 태그에 직접처리 (on이벤트=핸들러함수())
				<body onLoad="handler1();">
			2. 스크립트에서 처리 : 이벤트소스 객체마다 처리 (이벤트소스.on이벤트=핸들러함수())
				$(body).onLoad=handler1
			3. 스크립트에서 처리 : 특정 태그의 자식들 (특정태그.on("이벤트", "자식특정",이벤트함수();))	
				1) $(body input).이벤트명(핸들러함수())		
				2) $(body).on("keyUp", "input", function () {alert("ddd")}) 
		*/
		
		
		$(document).on("keypress",function () {
			
			if(event.keyCode == 13) {return false;}
		});
		
		//유효성 체크를 위한 통합 이벤트 처리
		//$("form input").keyUp(function () {
		$("form[name=loginFrm]").on("change", "input",function () {
				
			var inputs = $("form[name=loginFrm] input").not("[type=submit]");
			var indexByNow = inputs.index($(this));
			userInfoCheck.checkInfoCommon(inputs, indexByNow);
		}); 
		
		$("form[name=loginFrm]").on("submit", function () {
		
			alert("온서브밋");
			var inputs = $("form[name=loginFrm] input").not("input[type=submit]");
			alert(inputs.length);
			var indexByNow = inputs.length-1;
			return userInfoCheck.checkInfoCommon(inputs, indexByNow);
		}); 
		
	});

	//유저정보 유효성 검사 통합 포맷			
	
	var userInfoCheck = {
			
			checkResult : false,

			//개별 검사 공통 포맷
			checkInfoCommon : function (inputs, indexByNow) {
				
				alert("시작");
					
					//에러메시지 초기화
					this.checkResult = true;
					$("form .error").remove();
					
					//멈추지 않고 모든 오류 검사
					for(var i=0; i<=indexByNow; i++) {
						
						alert("전체오류검사 시작");
						//각 단위
						var eachInfo = inputs.eq(i);
						//각 단위별 체크 컨트롤
						var eachResult = true;
						
						if(eachResult) {
						//현재까지 필수체크	
							var $thisValue = this.valOfInput(inputs.eq(i));
							if($thisValue == "") {
								
								var errorTag = this.errorTagOut("empty");
								eachInfo.parents(".inputGroup").find(".error").remove();
								eachInfo.parents(".inputGroup").append(errorTag);
								eachResult = false;
							//필수 정상
							} else {
								
							//각 단위별 형식검사
								var regFrm = this.checkObjControll(eachInfo);
								if(regFrm == null || !regFrm.test(eachInfo.val())) {
									
									var errorTag = this.errorTagOut("regFrm");
									eachInfo.parents(".inputGroup").append(errorTag);
									eachResult = false;
								}
							}
						}
						
						if(!eachResult) {this.checkResult = false;}
					}
					
					return this.checkResult;
			},
			
			
			
			
			
			valOfInput : function ($this) {
				
				var $tagNm = $this[0].tagName;
				var $thisName  = $this.attr("name");
				var valOfTag = "";
				
				if($tagNm == "INPUT") { 
					if ($this.attr("type") == "checkbox") {
						
						valOfTag = $("input[name="+$thisName+"]:checked").val();
						valOfTag = (valOfTag != undefined) ? valOfTag : "";
					} else {
						
						valOfTag = $this.val().trim();
					}
					
				} else if ($tagNm == "SELECT") 
					valOfTag = $this.children("option:selected").val().trim();
				else 
					valOfTag = "";
				
				return valOfTag;
			},
			
			//공통 포맷 - 형식 체크 컨트롤
			checkObjControll : function ($this) {
				
				alert("단위 형식검사 시작");
				
				$name = $this.attr("name");
				if ($name == "id") {
					
					alert("아이디 형식 검사");
					return /^[a-zA-Z0-9]{6,12}@[a-zA-Z0-9]{0,10}.[a-zA-Z0-9]{1,4}$/;
				} else if ($name == "pw") {
					
					return /^[a-zA-Z0-9]{5,12}$/;
				} else if ($name == "chk") {
					
					return /^[a-zA-Z0-9]{5,12}$/;
				} else {
					
					alert("포맷이 없거나 서브밋 버튼입니다.");
					return /^$/;
				}
			},
			
			errorTagOut : function (error) {
				
				var errorTag = $(document.createElement("div"));
				if(error == "empty") {
					
					errorTag.prop("class", "error").text("필수검사 에러");
				} else if (error == "regFrm") {
					
					errorTag.prop("class", "error").text("입력 형식에 알맞지 않습니다.");
				} else {}
				
				return errorTag;
			}
	}
	
</script>

<h2>로그인 페이지</h2>

<form name="loginFrm" action="#">
	<table>
		<tr class="inputGroup">
			<td>아이디(이메일)</td>
			<td><input type="text" name="id" title="아이디" placeholder="아이디를 입력하세요."/></td>
		</tr>
		<tr class="inputGroup">
			<td>비밀번호</td>
			<td><input type="password" title="비밀번호" name="pw" placeholder="비밀번호를 입력하세요."/></td>
		</tr>
		<tr>
			<td><input type="submit" value="전송"/></td>
		</tr>
	</table>
</form>

</body>
</html>