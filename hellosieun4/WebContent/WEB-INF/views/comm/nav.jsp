<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8"/>
menu<br/>
<div id='divnav' class="divnav">
<form action="login.do" method="post">
<table>
<col width="50px"/>
<tr>
<td>아이디</td>
<td><input type="text" name="id" size="10"></td>
</tr>
<tr>
<td>패스워드</td>
<td><input type="password" name="pwd" size="10"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="로그인"></td>
</tr>
<tr>
<td colspan="2"><a href='regiform.do'>회원가입</a></td>
</tr>
</table>
</form>
</div>

