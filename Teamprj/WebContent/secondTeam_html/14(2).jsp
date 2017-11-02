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
		<div style="border: 1px solid; height: 100px">LOGO</div>
		<!-- 상단 고정 화면 끝 -->

		<!-- 중앙 화면  -->
		<div style="border: 1px solid; height: 700px; text-align: center;">

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

		</div>
		<!-- 중앙 화면 끝 -->

		<!-- 하단 고정 화면 -->
		<div style="border: 1px solid; height: 100px">하단 고정 화면</div>
		<!-- 하단 고정 화면 끝 -->

	</div>
	<!-- 전체 화면 끝 -->
</body>
</html>