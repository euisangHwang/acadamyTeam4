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
		<div style="border: 1px solid; height: 700px">

			<!-- 중앙  위 가운데 네모칸 -->
			<div style="border: 1px solid; height: 500px">도면</div>
			<!-- 중앙 위 가운데 네모칸 -->

			<!-- 중앙 아래 가운데 네모칸-->
			<div style=" border: 1px solid;height: 200px">
				
				<input type="button" value="센서1">
				<input type="button" value="센서2">
				<input type="button" value="센서3">
				<input type="button" value="센서4"><br>
				<input type="button" value="센서5">	
				<input type="button" value="센서6">	
				<input type="button" value="센서7">	
				<input type="button" value="센서8"><br>	
				<input type="button" value="센서9">	
				<input type="button" value="스피커1" onclick="location.href='view4.jsp'">			
				<input type="button" value="스피커2">			
				<input type="button" value="스피커3">			
				<input type="button" value="스피커4">			
			
			</div>
			<!-- 중앙 아래 가운데 네모칸 끝 -->

		</div>
		<!-- 중앙 화면 끝 -->

		<!-- 하단 고정 화면 -->
		<div style="border: 1px solid; height: 100px">하단 고정 화면</div>
		<!-- 하단 고정 화면 끝 -->

	</div>
	<!-- 전체 화면 끝 -->
</body>
</html>