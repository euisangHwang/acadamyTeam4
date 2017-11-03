<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8"/>

<h1>login page</h1>

<!-- 입력값 실시간 유효성 검사 부분 작성 -->
<!-- 중복확인 시, 스크립트로 실시간 중복확인 되는 프로그램 -->
<!-- 인증번호 이벤트 시, 인증번호 발송 (보류) -->
<!-- 취소 버튼 시, 메인페이지로 이동 -->

<!-- 회원가입 시 - 유효성x면 alert / 유호성o면 로그인페이지로 이동 -->


<form>
<!-- 1. 인증 번호 시 팝업 창 생성 -->
   <!-- 전체 화면  -->
   <div style="width: 100%; height: 900px; border: 1px solid">
      <!-- 상단 고정 화면 -->
      <div style="border: 1px solid; height: 100px">
      LOGO
      </div>
      <!-- 상단 고정 화면 끝 -->
      <!-- 중앙 화면  -->
      <div style="border: 1px solid; height: 700px; text-align:center;">
         <center>
            <table>
            <tr>
               <td><input id="uid" type="text" placeholder="아이디"></td>
               <td><input id="uidcheck" type="button" value="중복확인"></td>
            </tr>
            <tr>
               <td><input id="upass" type="text" placeholder="패스워드"></td>
               <td></td>
            </tr>
            <tr>
               <td><input id="upass2" type="text" placeholder="패스워드 확인"></td>
               <td></td>
            </tr>
            <tr>
               <td><input id="umobile" type="text" placeholder="휴대폰"></td>
               <td><input id="umobilecheck" type="button" value="인증번호"></td>
            </tr>
            <tr>
               <td><input id="checknum" type="text" placeholder="휴대폰 인증번호"></td>
               <td></td>
            </tr>
            <tr>
               <td><input id="email" type="text" placeholder="이메일"></td>
               <td></td>
            </tr>
            </table>
         </center>
         <input type="submit" value="회원가입">
         <input type="reset" value="취소">
      </div>
      <!-- 중앙 화면 끝 -->
      <!-- 하단 고정 화면 -->
      <div style="border: 1px solid; height: 100px">하단 고정 화면</div>
      <!-- 하단 고정 화면 끝 -->
   </div>
   <!-- 전체 화면 끝 -->
</form>