<%@ page contentType="text/html; charset=utf-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<script>
</script>

<form id="loginFrm" action="isUser.do" method="post">
	<table>
		<tr>
			<td><input id="uid" name="uid" type="text" placeholder="아이디"></td>
		</tr>
		<tr>
			<td><input id="upass" name="upass" type="text" placeholder="패스워드"></td>
		</tr>
		<tr>
			<td><input type="submit" value="로그인"><input type="button" value="회원가입하기" onclick="pageSubmit('signUp.do')"/></td>
			<td></td>
		</tr>
	</table>
</form>

