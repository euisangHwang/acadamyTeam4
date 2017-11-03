<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />

스피커1
<center>
	<table border=1>
		<tr>
			<td><select>
					<option>개휘슬소리</option>
					<option>알람소리</option>
					<option>무슨소리</option>
			</select> <input type="button" value="소리 테스트하기"></td>
		</tr>
	</table>
</center>

<input type="button" value="저장">