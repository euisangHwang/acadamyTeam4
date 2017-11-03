<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />

<h1>sound_main page</h1>


<!-- 중앙  위 가운데 네모칸 -->
<div style="border: 1px solid; height: 500px">
	<table align="center">
		<tr>
			<td>등록된 소리 리스트 <br>1.개 휘슬소리 <br>2.다른 동물 소리 <br>3.천둥
				소리<br> <input type="button" value="더 보기">
			</td>
		</tr>
	</table>
</div>
<!-- 중앙 위 가운데 네모칸 -->

<!-- 중앙 아래 왼쪽 네모칸 -->
<div style="float: left; border: 1px solid; width: 33%; height: 200px">소리
	추가 하기</div>
<!-- 중앙 왼쪽 네모칸 끝 -->

<!-- 중앙 아래 가운데 네모칸-->
<div style="float: left; border: 1px solid; width: 32%; height: 200px">소리
	삭제하기</div>
<!-- 중앙 아래 가운데 네모칸 끝 -->

<!-- 중앙 오른쪽 네모칸 -->
<div style="float: right; border: 1px solid; width: 32%; height: 200px">소리
	테스트하기</div>
<!-- 중앙 오른쪽 네모칸 끝 -->
