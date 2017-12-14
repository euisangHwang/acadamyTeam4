<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<script>

	$(document).ready(function () {
		
		//폼 동적활용
		if("${member != null}" == "true"){
			
			$(document.signUP).attr("action", "updateMember.do");
			$(document.signUP.sSubmit).text("변경하기");
		} else {
			$(document.signUP).attr("onsubmit",'return signUp.checkCertify()')
		}
		
		//도면사진 미리보기
		$("#homeimg").change(function () {
			
			var $that = $(this);
		
			if(this.files.length != 0) {
				if(window.FileReader) {
					
					$("form[name=signUP] input[name=fileExist]").val("Y");
					
					alert($("input[name=fileExist]").val());
					
					var reader = new FileReader();
					width = "400px";
					height = "400px";
					
					reader.onload = function(e) {
						
						$(".imgMirror").remove();
						var src = e.target.result;
						$that.parent().prepend('<img src="'+src+'" class="imgMirror" width="'+width+'" height="'+height+'">');
					}
					
					reader.readAsDataURL(this.files[0]);
				}
			}
		});
	});
	
   $(function() {
      
      $("signUp").submit(
            function() {
               var tel_pattern = /^01[016789]-\d{4}-\d{4}$/;
               var email_pattern = /^([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
               if ($("#id").val() ==""){
                  alert("아이디를 입력해 주세요.");
                  $("#id").focus();
                  return false;
                  
               }else if($("#pw").val() == ""){
                  alert("비밀번호를 입력해 주세요.");
                  $("#pw").focus();
                  return false;
                  
               }else if($("#pw2").val() == ""){
                  alert("비밀번호확인를 입력해 주세요.");
                  $("#pw2").focus();
                  return false;
                  
               }else if($("#pw").val() != $("#pw").val() ) {
                  alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                  $("#pw").focus();
                  return false;
                  
               }else if($("#tel").val() == "" ) {
                  alert("핸드폰번호를 입력해주세요");
                  $("#tel").focus();
                  return false;
                  
/*                }else if(tel_pattern.text($(#"tel").val()) != true ) {
                  alert("핸드폰번호를 다시 입력해주세요");
                  $("#tel").focus();
                  return false; */
                  
               }else if($("#uname").val() == "" ) {
                  alert("이름을 입력해주세요");
                  $("#uname").focus();
                  return false;
                  
               }
            })
   })
</script>

<div class="legi_container" style="">
	<div id="legi7" class="legi_section">
	<form name="signUP" action="insertMember.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="fileExist" value="N"/>
		<input type="hidden" name="beforeImgCode" value="<c:out value='${member.homeimg}' default='0'/>" />
		<input type="hidden" name="beforeImgPath" value="<c:out value='${member.pPath}' default='x'/>" />
         <div>
            <input id="id" title="아이디" type="text" placeholder="아이디" name="id" value="${member.id}">
            <c:if test="${member == null}">
            	<input id="idcheck" type="button" value="중복확인" onClick="signUp.checkDuplicate(this)">
            </c:if>
         </div>
         <div><input id="pw" type="text" placeholder="패스워드" name="pw" value="${member.pw}"></div>
         <c:if test="${member == null}">
	         <div><input id="pw2" type="text" placeholder="패스워드 확인"></div>
         </c:if>
         <div>
            <input id="tel" title="휴대폰" type="text" placeholder="휴대폰" name="tel" value="${member.tel}">
            <c:if test="${member == null}">
            	<button type="button" onClick="signUp.sendSMSMsg(this)">인증</button>
            </c:if>
         </div>
         <div>
            <input id="uname" type="text" placeholder="이름" name="name" value="${member.name}">
         </div>
         
         
		<div class="legi_box2">
			<div class="question-answer">
				<div class="inner-btn-wrap">
					<div class="row" style="margin:0;padding:0;">
						<input type='hidden' name='address' value='${member.address}'/>
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12" style="padding: 0 1px;">
							<input type="text" class="form-control inner-btn-input" placeholder="장소를 검색하세요." value="${member.address}"
							id="search-location-input" autocomplete="off"/>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12" style="padding: 0 1px;">
							<div class="inner-btn-button">
								<button type="button" class="btn" id="search-location-btn" onClick="javascript:searchA()">검색</button>
							</div>
						</div>
					</div>
					<ul class="auto-complete-container" id="location-auto-complete" style="display: none;">
					</ul>
				</div>
				<ul id="selected-location-container"></ul>
				<div id="enrollMap" style="width: 100%; height: 500px;"></div>
			</div>
		</div> 	  
         <div>
         	<c:if test="${member.homeimg != null}">
         		<img class="imgMirror" src="${member.pFullName}" width="400px" height="400px"/>
         	</c:if>
         	<c:if test="${member.homeimg == null}">
         		<div class="imgMirror" style="display:inline-block; width:400px; height:400px; background-color: gray; line-height: 400px; text-align: center;">도면사진 없음</div>
         	</c:if>
            <input type="file" id="homeimg" name="homeimg">
         </div>
         <div>
			 <input name="sSubmit" type="submit" value="등록하기">
			 <input type="reset" value="취소">
		 </div>
 	</form>
	</div>
</div>
   <!-- 전체 화면 끝 -->

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8f1633045c166248833b28b13610fc07&libraries=services"></script>
<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new daum.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('enrollMap'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

//좌표 값에 해당하는 행정동, 법정동 정보를 얻는다.
var geocoder = new daum.maps.services.Geocoder();

// 장소 검색 객체를 생성합니다
var ps = new daum.maps.services.Places(); 

// 키워드로 장소를 검색합니다
function searchA () {
	var	keyword = $(".inner-btn-input").val();
	
	if (keyword != "") {
		
		ps.keywordSearch(keyword, placesSearchCB); 
		return;
	} else {
		
		alert("장소를 입력해주세요!");
		return;
	}	
}

function placesSearchCB (data, status, pagination) {
    if (status === daum.maps.services.Status.OK) {
    	
    	console.log("data[i].road_address_name : "+data[0].address_name);
    	
        var li = "";
        for (var i=0; i<data.length; i++) {

   	    	//장소그룹명 출력
   	    	console.log("category_group_code : "+data[i].category_group_code);
   	    	console.log("category_group_name : "+data[i].category_group_name);
        	
        	geocoder.coord2RegionCode((data[i].x*1).toFixed(7)*1, (data[i].y*1).toFixed(7)*1, 
       			function(result, status) {
        	   		if (status === daum.maps.services.Status.OK) {

        	   	   		//행정구역 단위 출력
        	   	   		console.log(i+"번째시작********************************************************");
    	    	        console.log('지역 명칭 11: ' + result[0].address_name);
    	    	       	console.log("region_1depth_name (시,도 단위) : "+result[0].region_1depth_name);
    	    	    	console.log("region_2depth_name (구 단위) : "+result[0].region_2depth_name);
    	    	    	console.log("region_3depth_name (동 단위) : "+result[0].region_3depth_name);
    	    	    	console.log('행정구역 코드 : ' + result[0].code);
        	   	   		console.log(i+"번째끝********************************************************");
        	    	};
        		}
            );
        	
        	var road_address_name = ((data[i].road_address_name != "") ? data[i].road_address_name : data[i].address_name);
        	var place_name = data[i].place_name;
        	li += "<li onClick='manageMarker.setBounds("+data[i].y+","+data[i].x+",\""+road_address_name+"\")'>"+place_name+":"+road_address_name+"</li>";	
        }
        
        $("#location-auto-complete").attr("style", "display: block");
        $("#location-auto-complete").append(li); 
    } 
}

var manageMarker = {
		
		Marker : "",
		Bounds : "",
		
		setBounds : function (Lat, Lng, juso) {
			
			// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
			// LatLngBounds 객체에 좌표를 추가합니다
			var bounds = new daum.maps.LatLngBounds();
			bounds.extend(new daum.maps.LatLng(Lat, Lng));
		    map.setBounds(bounds);
		    this.Bounds = bounds;
		    $("#location-auto-complete li").remove();
		    $("#location-auto-complete").attr("style", "display: none");
		    $("input[name=address]").val(juso);
		    
		    var li = "";
			    	li +=   "<li class='location-list'>"
			    	   +		"<span class='main_location'>(대표위치)</span>"
			    	   +		juso+"</span>"
			    	   +		"<button type='button' style='background-color: rgba(0,0,0,0);' class='btn btn-large removeSchedule' onClick='javascript:manageMarker.removeLocation(this)'><em class='fa fa-close'></em></button>"
			    	   +	"</li>";
			    	   
		    $("#selected-location-container").text("");
		    $("#selected-location-container").append(li);
		    
		    manageMarker.displayMarker(Lat, Lng, juso);
		},
		
		displayMarker : function (Lat, Lng, juso) {
		
			// 마커를 생성하고 지도에 표시합니다
		    var marker = new daum.maps.Marker({
		        map: map,
		        position: new daum.maps.LatLng(Lat, Lng) 
		    });
			
			this.Marker = marker;
		    
		    var title = juso.substring(0,juso.indexOf(':'));
		    
		    // 마커에 클릭이벤트를 등록합니다
		    daum.maps.event.addListener(marker, 'click', function() {
		    	
		    	var which = marker.getPosition();
		        var detailAddr = '<div>'+title+'</div>';
		            detailAddr += '<div>지번 주소 : ' + juso + '</div>';
				var content = '<div class="bAddr">' + detailAddr + '</div>';
		        infowindow.setContent(content);
		        infowindow.open(map, marker);
		    });
		},
		
		removeLocation : function (HTMLBTNElement) {
			
			$this = $(HTMLBTNElement);
			$this.parent().remove();
			$("#selected-location-container li").remove();
			this.Marker.setMap(null);
			
			this.Bounds.extend(new daum.maps.LatLng(37.566826, 126.9786567));
			map.setBounds(this.Bounds);
		}
}  
</script>   