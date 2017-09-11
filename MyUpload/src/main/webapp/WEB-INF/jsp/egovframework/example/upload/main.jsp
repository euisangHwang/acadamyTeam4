<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../cmmn/common-lib.jsp" %>


<div>
	<button id="uploadFile" style="background-color: gray; border:1px solid thick;">업로드하기</button>
	<button id="uploadBtn" type="submit">업로드버튼</button>
</div>

<script type="text/javascript">

$(document).ready (function () {	
	eObj.onLoad = function() {
		
		
		
		
	};
	
});

	
	var eObj = $("#uploadFile").uploadFile({
		
		url 			: "Fupload.do",
		method			: "POST",
		formData: 		{signTrgNo:'hanq'},
		autoSubmit 		: false,
		
		maxFileCount	: 3,
		showPreview 	: true,
		previewWidth	: "auto",
		previewHeight   : "50px",
		allowedTypes: "zip,txt,jpg,png,gif",
		
			 		onLoad : function () {
			
			$.ajax({
				
				url 		: "onUpload.do",
				type		: "POST",
				dataType	: "json",
				data		: {signTrgNo:'hanq'},
				async		: false,
				success 	: function(JSdata) {
					
					
					$.each(JSdata, function (i, item) {
						
						var originalFileName="";
						var filePath="";
						var imageSize="";
						
						$.each(item, function (key, value) {
							
							if (key == "originalFileName") {
							
								originalFileName = value;
							}

							if (key == "filePath") {
								
								filePath = value;
							}
							
							if (key == "imageSize") {
								
								imageSize = value;
							}
							
						});
						eObj.createProgress(originalFileName, filePath, imageSize);
						
					});
					
				},
				error		: function(data) {
					
				}
				
			});
			
		},
		
		afterUploadAll : function () {
			
			location.reload();
		} 
	}); 
	
	$("#uploadBtn").click(function () {
		
 		eObj.startUpload();  
	});	




</script>