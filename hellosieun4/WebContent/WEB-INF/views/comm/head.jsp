<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8"/>

  <head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="">

    <!-- jQuery -->
    <script src="assets/lib/jquery.min.js"></script>
    <script src="assets/lib/jquery-ui.js"></script>
    <script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
    
   	<script>
	
		$(document).ready(function () {
			
			console.log("head");
		});
		
	</script>
    
    <script src="https://unpkg.com/leaflet@1.0.3/dist/leaflet.js"
	integrity="sha512-A7vV8IFfih/D732iSSKi20u/ooOfj/AGehOKq0f4vLT1Zr2Y+RX7C+w8A1gaSasGtRUZpF/NZgzSAu4/Gc41Lg=="
	crossorigin=""></script>

    <title>City Listing - Directory Template</title>
    <!-- Google Font(s) -->
    <link href="https://fonts.googleapis.com/css?family=Capriola|Roboto" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
    <!-- Bootstrap-->
    <link href="assets/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Awesome Icons Font -->
    <link href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- Listing Filter -->
    <link href="assets/lib/bootstrap-select-master/dist/css/bootstrap-select.min.css" rel="stylesheet">
    <!-- Lightbox -->
    <link href="assets/lib/lightbox2-master/dist/css/lightbox.min.css" rel="stylesheet">
    <!-- Map -->
    <link href="assets/lib/Leaflet-1.0.2/dist/leaflet.css" rel="stylesheet">
   	<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.0.3/dist/leaflet.css"
	integrity="sha512-07I2e+7D8p6he1SIM+1twR5TIrhUQn9+I6yjqD53JQjFiMf8EtC93ty0/5vJTZGF8aAocvHYNEDJajGdNx1IsQ=="
	crossorigin="" />
    <!-- City Listing Icons -->
    <link href="assets/fonts/icons/css/import-icons.css" rel="stylesheet">
    <!-- Main CSS -->
    <link href="assets/css/style.css" rel="stylesheet">
   	<!-- upload css -->
    <link href="assets/css/uploadfile.css" rel="stylesheet" type="text/css" />
    <!-- jquery-ui css -->
    <link href="assets/css/jquery-ui.css" rel="stylesheet" type="text/css" />
    
    
    <!-- Daum map -->
    <script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=10d44bdc22885a555686cd67fdb5b69b&libraries=services,clusterer"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9] for blog-option>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 9] for cities-option>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--[if lt IE 9] for contact, fag, forgot-password, etc...>
      <script src="assets/lib/html5shiv-master/dist/html5shiv.min.js"></script>
      <script src="assets/lib/Respond-master/dest/respond.min.js"></script>
    <![endif]-->
  </head>

<script src="<%=request.getContextPath()%>/assets/lib/jquery-1.5.js"></script>
<script>

	function pageSubmit (pageName) {
		
		$("#pSubmit").attr("action",pageName);
		$("#pSubmit").submit();
	}

</script>

<form id="pSubmit" method="post">

</form>