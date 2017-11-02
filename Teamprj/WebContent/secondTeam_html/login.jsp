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
				<td rowspan=2><input id="uidcheck" type="button" value="로그인"></td>
			</tr>
			
			<tr>
				<td><input id="upass" type="text" placeholder="패스워드"></td>
				<td></td>
			</tr>
						
			</table>
			
			</center>
			
			<a href="#">아이디/패스워드 찾기</a>
			<a href="signup.jsp">회원가입</a>

		</div>
		<!-- 중앙 화면 끝 -->

		<!-- 하단 고정 화면 -->
		<div style="border: 1px solid; height: 100px">하단 고정 화면</div>
		<!-- 하단 고정 화면 끝 -->

	</div>
	<!-- 전체 화면 끝 -->
</body>
</html>