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
<style>
	.left-box {
		background : red;
		float : left;
	}
	.right-box {
		background : green;
		float : right;
	}


</style>
</head>
<body>
	<form>
		<!-- level-1 -->
		<div style="margin: 5px 5px; border: 2px solid;">
			<!-- level-2(공통헤더) -->
			<div style="overflow: hidden; background-color: red;">
				<img src="menu.png" style="vertical-align: top;" height="80">
				<div
					style="border: 3px solid; display: inline-block; width: 122px; margin: 2px 53px; line-height: 65px; text-align: center; overflow: hidden;">로고</div>
				<img src="menu.png" style="vertical-align: top;" height="80">
			</div>

			<!-- level-2( 중앙 컨텐츠) -->
			<div style="margin-bottom overflow: hidden;">
				<!-- level-3(해쉬태그 입력 창) -->
				<div
					style="margin: 0px; background-color: orange; overflow: hidden;">
					<div
						style="background-color: yellow; margin: 15px 40px; height: 45px; text-align: center;">
						<TEXTAREA
							style="overflow: hidden; height: 45px; margin-left: 2px; vertical-align: top;">#스톡홀름..#겨울</TEXTAREA>
					</div>
				</div>

				<!-- level-3(중앙컨텐츠) -->
				<div
					style="height: 568px; overflow: hidden; background-color: green;">
					<div
						style="overflow: hidden; margin: 20px 15px; background-color: blue;">
						<!-- level-4 (페이지 제목 / 필터링) -->
						<div style="height: 60px; text-align: center;">
							<div style="line-height: 25px; font-size: 20px; font-weight: bold;">
								작성 게시판
							</div>
						</div>
						<div>
					<textarea>
					추억을 공유하세요
					</textarea>
					</div>
						<div class="left-box"><input type="button" value="사진"></div>
						<div class="right-box"><input type="button" value="동영상"></div>
					</div>
					
				</div>
			</div>
		</div>
	</form>

</body>
</html>