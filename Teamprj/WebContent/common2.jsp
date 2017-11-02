<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="vie
wport" content="width=device-width">
<title>Insert title here</title>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<script>
   function addPicturePath () {
      
      $("#addPicturePathPop").modal();
   }
</script>
</head>
<body>
	<form>
		<!-- level-2(공통헤더) -->
		<div
			style="position: fixed; width: 100%; overflow: hidden; z-index: 2;">
			<div
				style="width: 100%; overflow: hidden; background-color: red; text-align: center;">


				<!-- 문제 구간 왼쪽 -->
				<div id="menu" style="float: left; width: 5.6em; max-width: 120px;">
					<ul id="nav">
						<li><a href="#">사용법</a></li>
						<li><a href="#">여행지 상세 검색</a></li>
						<li><a href="#">게시판</a></li>
						<script>
						$("#nav").addClass("js");
						$("#nav").addClass("js").before('<div id="menu">&#9776;</div>');
						$("#menu").click(function(){
							$("#nav").toggle();
						});
						</script>
					</ul>
				</div>
				<!-- 문제 구간 왼쪽 끝 -->

				<!-- 중앙 사각 로고 모양 상관 x -->
				<div style="width: 5.5em; max-width: 120px; display: inline-block;">
					<img src="square.png" style="width: 100%;">
				</div>
				<!-- 중앙 사각 로고 모양 끝 상관 x -->


				<!-- 문제 구간 오른쪽 -->
				<div id="menu2" style="float: right; width: 5.6em; max-width: 120px;">
					<ul id="nav2">
					
						<li><a href="#">로그인</a></li>
						<li><a href="#">회원 가입</a></li>
						<script>
						$("#nav2").addClass("js");
						$("#nav2").addClass("js").before('<div id="menu2">&#9776;</div>');
						$("#menu2").click(function(){
							$("#nav2").toggle();
						});
						</script>
					</ul>
				</div>
				<!-- 문제 구간 오른쪽 끝 -->

			</div>
			<!-- level-3(검색바) -->
			<div
				style="width: 100%; margin: 0px; background-color: orange; overflow: hidden;">
				<div
					style="background-color: yellow; width: 85%; margin: 15px 10%; height: 45px; text-align: center; padding: 4% auto;">
					<img src="searchCategoryIcon.png" height="45"> <input
						type="text"
						style="overflow: hidden; line-height: 39px; width: 60%;" /> <input
						type="button" value="검색"
						style="overflow: hidden; height: 45px; margin-left: 2px; vertical-align: top;" />
				</div>
			</div>
		</div>
		<!-- level-1 -->
		<div
			style="position: absolute; width: 100%; padding-top: 10.67em; border: 2px solid;">
			<!-- level-2(컨텐츠) -->
			<div style="overflow: hidden;">

				<!-- level-4 (페이지 제목 / 필터링) -->
				<div
					style="position: fixed; width: 100%; background-color: white; text-align: center;">
					<div style="line-height: 25px; font-size: 20px; font-weight: bold;">사진경로
						리스트</div>
					<select style="height: 25px; margin-top: 10px;">
						<option>전체리스트</option>
						<option>계획1</option>
						<option>계획2</option>
						<option>게획3</option>
						<option>게획3</option>
					</select>
				</div>

				<!-- level-3(중앙컨텐츠) -->
				<div
					style="padding-top: 5em; overflow: hidden; background-color: green;">
					<div style="margin: 5px 15px 70px 15px; background-color: blue;">
						<!-- level-4 (컨텐츠 리스트 컨테이너) -->
						<div
							style="text-align: center; overflow: hidden; background-color: white;">
							<c:forEach var="i" begin="1" end="4" varStatus="status">
								<!-- level-5 (컨텐츠 단위 [반복대상]-inline-block) -->
								<div>
									<div
										style="width: 45.5%; max-width: 280px; display: inline-block; margin: 7px; background-color: gray;">
										<!-- 사진경로 소제목 -->
										<div style="text-align: center;">사진경로${status.index} 소제목</div>
										<!-- 경로사진 -->
										<div style="width: 100%;">
											<img src="square.png" style="width: 100%;" />
										</div>
										<!-- 하단캡션(소속 여행계획) -->
										<div style="text-align: right; font-size: 12px;">소속 여행계획</div>
									</div>
									<div
										style="width: 45.5%; max-width: 280px; display: inline-block; margin: 7px; background-color: gray;">
										<!-- 사진경로 소제목 -->
										<div style="text-align: center;">사진경로${status.index} 소제목</div>
										<!-- 경로사진 -->
										<div style="width: 100%;">
											<img src="square.png" style="width: 100%;" />
										</div>
										<!-- 하단캡션(소속 여행계획) -->
										<div style="text-align: right; font-size: 12px;">소속 여행계획</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- level-4 (컨텐츠 조작버튼) -->
		<div
			style="position: fixed; width: 100%; bottom: 0; background-color: white; text-align: right;">
			<input style="padding: 16px; margin-right: 10px;" type="button"
				value="등s록" onClick="javascript:addPicturePath();" /> <input
				style="padding: 16px; margin-right: 10px;" type="button" value="삭제" />
		</div>
		

	</form>
	<!-- 사진경로 추가 팝업 -->

	<div id="addPicturePathPop"
		style="margin-top: 300px; text-align: center; display: none;"
		class="modal fade" aria-hidden="true" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div>사진경로 추가</div>
				<form>
					<div>
						<input type="text" placeholder="소제목을 입력하세요." />
					</div>
					<div>
						<select id="picturePathSelect">
							<option>소속 여행계획</option>
						</select>
					</div>
					<div>
						<input type="button" value="추가" /> <input type="button"
							value="취소" data-dismiss="modal" />
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>