<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<script>

	$(document).ready(function () {
		
		$("#homeimg").change(function () {
			
			var $that = $(this);
		
			if(this.files.length != 0) {
				if(window.FileReader) {
					
					$("form[name=signUP] input[name=fileExist]").val("Y");
					
					alert($("input[name=fileExist]").val());
					
					var reader = new FileReader();
					width = "400px";
					height = "400px";
					
					reader.onload = function(e) {
						
						$(".imgMirror").remove();
						var src = e.target.result;
						$that.parent().prepend('<img src="'+src+'" class="imgMirror" width="'+width+'" height="'+height+'">');
					}
					
					reader.readAsDataURL(this.files[0]);
				}
			}
		});
	});

   $(function() {
      
      $("signUp").submit(
            function() {
               var tel_pattern = /^01[016789]-\d{4}-\d{4}$/;
               var email_pattern = /^([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
               if ($("#id").val() ==""){
                  alert("아이디를 입력해 주세요.");
                  $("#id").focus();
                  return false;
                  
               }else if($("#pw").val() == ""){
                  alert("비밀번호를 입력해 주세요.");
                  $("#pw").focus();
                  return false;
                  
               }else if($("#pw2").val() == ""){
                  alert("비밀번호확인를 입력해 주세요.");
                  $("#pw2").focus();
                  return false;
                  
               }else if($("#pw").val() != $("#pw").val() ) {
                  alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                  $("#pw").focus();
                  return false;
                  
               }else if($("#tel").val() == "" ) {
                  alert("핸드폰번호를 입력해주세요");
                  $("#tel").focus();
                  return false;
                  
/*                }else if(tel_pattern.text($(#"tel").val()) != true ) {
                  alert("핸드폰번호를 다시 입력해주세요");
                  $("#tel").focus();
                  return false; */
                  
               }else if($("#uname").val() == "" ) {
                  alert("이름을 입력해주세요");
                  $("#uname").focus();
                  return false;
                  
               }
            })
   })
</script>

<c:if test="${member != null}">         
	<form name="signUP" action="updateMember.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="fileExist" value="N"/>
		<input type="hidden" name="beforeImgCode" value="<c:out value='${member.homeimg}' default='0'/>" />
		<input type="hidden" name="beforeImgPath" value="<c:out value='${member.pPath}' default='x'/>" />
</c:if>
<c:if test="${member == null}">
	<form name="signUP" action="insertMember.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="fileExist" value="N"/>
</c:if>
	         <div>
	            <input id="id" type="text" placeholder="아이디" name="id" value="${member.id}">
	            <c:if test="${member == null}">
	            	<input id="idcheck" type="button" value="중복확인">
	            </c:if>
	         </div>
	         <div><input id="pw" type="text" placeholder="패스워드" name="pw" value="${member.pw}"></div>
	         <c:if test="${member == null}">
		         <div><input id="pw2" type="text" placeholder="패스워드 확인"></div>
	         </c:if>
	         <div>
	            <input id="tel" type="text" placeholder="휴대폰" name="tel" value="${member.tel}">
	            <input id="telcheck" type="button" value="인증번호">
	         </div>
	         <div>
	            <input id="uname" type="text" placeholder="이름" name="name" value="${member.name}">
	         </div>
	         <div>
	               <input type="text" id="zipcode" size="7"> 
	               <input type="button" value="우편번호찾기" id="btnZip">
	         </div>
	         <div>
	            <input type="text" id="address" placeholder="주소" name="address" value="${member.address}">
	         </div>
	         <div>
	         	<c:if test="${member.homeimg != null}">
	         		<img class="imgMirror" src="${member.pPath}" width="400px" height="400px"/>
	         	</c:if>
	         	<c:if test="${member.homeimg == null}">
	         		<div class="imgMirror" style="display:inline-block; width:400px; height:400px; background-color: gray; line-height: 400px; text-align: center;">도면사진 없음</div>
	         	</c:if>
	            <input type="file" id="homeimg" name="homeimg">
	         </div>
		 <c:if test="${member != null}">
		 	<input type="submit" value="변경하기">
		 </c:if>
		 <c:if test="${member == null}">         
         	<input type="submit" value="회원가입">
         </c:if>
         	<input type="reset" value="취소">
 </form>
   <!-- 전체 화면 끝 -->
