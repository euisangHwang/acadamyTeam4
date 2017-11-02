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
      <div style="border: 1px solid; height: 100px">상단 고정 화면</div>
      <!-- 상단 고정 화면 끝 -->

      <!-- 중앙 화면  -->
      <div style="border: 1px solid; height: 700px">

         <!-- 중앙  위 가운데 네모칸 -->
         <div style="border: 1px solid; height:500px">등록된 소리 리스트 <br> 1.개 휘슬소리<br> 2.다른 동물 소리 <br> 3.천둥소리 <br> 4.천둥소리 <br> 5.천둥소리 <br> 6.천둥소리</div>
         <!-- 중앙 위 가운데 네모칸 -->
		<div style="border: 1px solid; height:200px"><input type="button" value="추가하기" onClick="uploadmp3.jsp"></div>


      </div>
      <!-- 중앙 화면 끝 -->

      <!-- 하단 고정 화면 -->
      <div style="border: 1px solid; height: 100px">하단 고정 화면</div>
      <!-- 하단 고정 화면 끝 -->

   </div>
   <!-- 전체 화면 끝 -->
</body>
</html>