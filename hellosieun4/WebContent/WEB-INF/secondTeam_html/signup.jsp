<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />

<h1>sign up page</h1>


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
