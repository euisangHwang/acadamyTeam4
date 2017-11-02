<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<title>Insert title here</title>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">

</head>
<body>
<form>
	<!-- level-1 -->
	<div style="margin: 5px 5px; border:2px solid;">
		<!-- level-2(공통헤더) -->
		<div style="overflow: hidden; background-color: red;">
			<img src="menu.png" style="vertical-align: top;" height="80">
				<div style="border: 3px solid; display:inline-block; width:122px; margin:2px 53px; line-height: 65px; text-align:center; overflow: hidden;">로고</div>
			<img src="menu.png" style="vertical-align: top;" height="80">
		</div>
		<!-- level-2(컨텐츠) -->
		<div style="margin-bottom overflow: hidden;">
			<!-- level-3(검색바) -->
			<div style="margin:0px; background-color: orange; overflow: hidden;">
				<div style=" background-color:yellow; margin:15px 40px; height: 45px; text-align: center;">
					<img src="searchCategoryIcon.png" height="45">
					<input type="text" style="overflow:hidden; line-height: 39px; vertical-align: top;"/>
					<input type="button" value="검색" style="overflow:hidden; height:45px; margin-left: 2px; vertical-align: top;"/>
				</div>
			</div>
			<!-- level-3(중앙컨텐츠) -->
			<div style="height:568px; overflow: hidden; background-colo
			r: green;">
				<div style="overflow:hidden; margin:20px 15px; background-color: blue;">
					<!-- level-4 (페이지 제목 / 필터링) -->
					<div style="height:60px; text-align: center;">
						<div style="line-height: 25px; font-size: 20px; font-weight: bold;">사진경로 리스트</div>
					</div>
					<!--level-4 (지도 칸 시작) -->
					<div id="map_place" style="overflow:hidden; height:250px; width:300px; border:1px solid; margin:0 auto;">
					</div>
					<!--level-4 (지도 칸 끝) -->
					<!--level-4 (버튼 칸 시작) -->
					<div id="button_place" style="padding-top:30px; float:right;">
					<input type="button" value="여행경로 적용"><input type="button" value="선택">
					</div>
					<!--level-4 (버튼 칸 끝) -->
				</div>
			</div>
		</div>
	</div>
</form>

</body>
</html>][]