<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8"/>

	<%-- <c:if test="${sessId ne null}">
	<h1 style="float: right;" onClick="pageSubmit('toSetting.do')">설정</h1>
</c:if> --%>

  <header class="header" style="margin-bottom: 60px;">
	<!-- Start Logo -->
	<div class="logo">
		<a href="#" onclick="javascript:header.pageSubmitFn('port0')"
		class="logo-color-bg"> <img alt="" src="assets/images/logo.png" />
		</a>
	</div>
	<!-- End Logo -->
	<!-- Start User Buttons -->
	<div class="user-buttons">
		<a href="#" class="add-listing" onclick="javascript:enrollUserCheck()">모꼬지 등록</a>	
	</div>
	<div class="user-buttons afterLogin">
		<a href="#" class="user-login" onclick="javascript:header.pageSubmitFn('port2')"></a>
		<a href="#" class="user-logout" onclick="javascript:header.pageSubmitFn('userLogout');"></a>
	</div>
	<!-- End User Buttons -->
	<!-- Start Navbar -->
	<div class="navbar navbar-inverse" role="navigation" id="slide-nav">
		<div class="container">
			<div id="slidemenu">
				<ul class="nav navbar-nav navbar-right" id="targetMenu">
					<!-- <li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Home<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Youtube Hero Video</a></li>
							<li><a href="#">Youtube Hero Video Light</a></li>
							<li><a href="#">Vimeo Hero Video</a></li>
							<li><a href="#">Self Hosted Video In Color</a></li>
							<li><a href="#">Hero Slideshow</a></li>
							<li><a href="#">Hero Image</a></li>
							<li><a href="#">Hero Image Masked</a></li>
							<li><a href="#">Hero Image Dark</a></li>
							<li><a href="#">Hero Image Texture</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Sign In<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Login/Register</a></li>
							<li><a href="#">Recover Password</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">About<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">How it Works</a></li>
							Button trigger modal
							<li><a href="#" class="btn btn-primary" data-toggle="modal" data-target="#Join_Modal">회원가입</a></li>
							<li><a class="btn btn-primary" onclick="javscript:header.modal('Login_Modal2')">로그인</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('LogReg','headerfrm')">LogReg</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('news','headerfrm')">news</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('main','headerfrm')">search</a></li>
							<li><a href="#" data-toggle="modal" data-target="#Login_Modal" id="activeModal">modal</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('notice','headerfrm')">notice</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('port','headerfrm')">mo_enroll</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('port2','headerfrm')">mypage</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('Eboard','headerfrm')">Eboard</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('contact','headerfrm')">contact</a></li>
							<li><a href="#" onclick="javascript:header.pageSubmitFn('reviewBoard','headerfrm')">reviewBoard</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Listings<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Map View as Default</a></li>
							<li><a href="#">Listing View Grid 1</a></li>
							<li><a href="#">Listing View Grid 2</a></li>
							<li><a href="#">Listing View Grid 3</a></li>
							<li><a href="#">Listing View Grid 4</a></li>
							<li><a href="#">Listing View Grid 5</a></li>
							<li><a href="#">Listing Detail</a></li>
							<li><a href="#">Cities Option 1</a></li>
							<li><a href="#">Cities Option 2</a></li>
							<li><a href="#">Cities Option 3</a></li>
							<li><a href="#">Cities Option 4 - Leaf Shaped</a></li>
							<li><a href="#">Not Found</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Blog<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Blog Grid 1</a></li>
							<li><a href="#">Blog Grid 2</a></li>
							<li><a href="#">Blog Grid 3</a></li>
							<li><a href="#">Blog Grid 4 - No Sidebar</a></li>
							<li><a href="#">Single Article</a></li>
							<li><a href="#">Single Article - No Sidebar</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Contact<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Default Contact</a></li>
							<li><a href="#">Support</a></li>
						</ul></li> -->
				</ul>
			</div>
		</div>
	</div>
	
	<!-- End Navbar -->
	<!-- Header Search Button -->
	<div class="header-search-button"></div>
</header>