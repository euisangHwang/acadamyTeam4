<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Insert title here</title>
</head>
<body>

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
</body>
</html>