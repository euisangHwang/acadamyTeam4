<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    

<script>
	$(document).ready(function () {
		
		console.log("footer");
	});
</script>

<footer>
	<div class="row footer-info">
		<div class="footer-col footer-terms">
			<a href="#">Terms of Use</a>
		</div>
		<div class="footer-col footer-privacy">
			<a href="#">Privacy Policy</a>
		</div>
		<div class="footer-col social-networks">
			<a href="#" class="fa fa-facebook"></a> <a href="#"
				class="fa fa-twitter"></a> <a href="#" class="fa fa-instagram"></a>
			<a href="#" class="fa fa-pinterest"></a>
		</div>
		<div class="footer-col footer-links">
			<a href="#">Home</a> <a href="#">About</a> <a href="#"
				class="sign-in">Sign In</a> <a href="#">Pricing</a> <a href="#">Blog</a>
			<a href="#">Contact</a>
		</div>
		<div class="footer-col footer-contact">
			<div class="footer-address">Your Business Address Here</div>
			<div class="footer-contact-data">(99) 989-745-9922</div>
		</div>
	</div>
	<div class="row footer-logo">
		<a href="#"> <img alt="" src="assets/images/logo.png" />
		</a>
	</div>
	<div class="row footer-credits">
		<div class="copyright">(C) 2017 Your Company, All right reserved
		</div>
	</div>
</footer>