<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FinVendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" /> 
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jssor.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jssor-deep-minified.css">
 
<!-- #region Jssor Slider Begin -->
<!-- Generator: Jssor Slider Maker -->
<!-- Source: http://www.jssor.com -->
<!-- This is deep minimized code which works independently. -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jssor_deep_minified.js"></script>

</head>
<body>
	<c:set var="checkingresultpageview" value="${checkingresultpageview}"></c:set>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<section>
		<div id="jssor_1" style="position:relative;margin:0 auto;top:-20px;left:0px;width:1980px;height:650px;overflow:hidden;visibility:hidden;">
			<!-- Loading Screen -->
			<div data-u="loading" class="jssorl-oval" style="position:absolute;top:0px;left:0px;text-align:center;background-color:rgba(0,0,0,0.7);">
				<img style="margin-top:-19.0px;position:relative;top:50%;width:38px;height:38px;" src="img/oval.svg" />
			</div>
			<div data-u="slides" style="cursor:default;position:relative;top:0px;left:0px;width:1980px;height:650px;overflow:hidden;">

				<div>
					<img data-u="image" src="${pageContext.request.contextPath}/resources/images/banner/skyline-banner.jpg" />
					<div class="banner-content1" style="">
						<div>Anything closer to your heart<br><span>Make it possible with Personal Loan</span></div>
						<span class="know-more"><a href="/personal-loan-finance-offers">Know more</a></span>
					</div>
				</div>
				<div>
					<img data-u="image" src="${pageContext.request.contextPath}/resources/images/banner/world-map-banner.jpg" />
					<div class="banner-content2" style="">
						<div>Anything closer to your heart<br><span>Make it possible with Personal Loan</span></div>
						<span class="know-more"><a href="/personal-loan-finance-offers">Know more</a></span>
					</div>
				</div>
				<div>
					<img data-u="image" src="${pageContext.request.contextPath}/resources/images/banner/closeup-pen-banner.jpg" />
					<div class="banner-content" style="font-color: #000;">
						<div>Anything closer to your heart<br><span>Make it possible with Personal Loan</span></div>
						<span class="know-more"><a href="/personal-loan-finance-offers">Know more</a></span>
					</div>
				</div>
				<div>
					<img data-u="image" src="${pageContext.request.contextPath}/resources/images/banner/billion-banner.jpg" />
					<div class="banner-content2" style="">
						<div>Anything closer to your heart<br><span>Make it possible with Personal Loan</span></div>
						<span class="know-more"><a href="/personal-loan-finance-offers">Know more</a></span>
					</div>
				</div>
			</div>
			<!-- Bullet Navigator -->
			<div data-u="navigator" class="jssorb05" style="bottom:16px;right:16px;" data-autocenter="1">
				<!-- bullet navigator item prototype -->
				<div data-u="prototype" style="width:16px;height:16px;"></div>
			</div>
			<!-- Arrow Navigator -->
			<span data-u="arrowleft" class="jssora22l" style="top:0px;left:8px;width:50px;height:58px;" data-autocenter="2"></span>
			<span data-u="arrowright" class="jssora22r" style="top:0px;right:8px;width:50px;height:58px;" data-autocenter="2"></span>
		</div>
		<section id="feature_list">
			<div class="feature_list">
				<h2>Our Solutions</h2>
				<ul>
					<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
						<div class="counter-block text-center">
							<img src="/resources/images/main-img-1-no-search.png" alt="Click here to Search Market Data Vendors" title="Click here to Search Market Data Vendors">
							<span>Market Data Vendors</span>
						</div>
					</li>
					<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
						<div class="counter-block text-center">
							<img src="/resources/images/main-img-2-no-search.png" alt="Click here to Search Trading Application Vendors" title="Click here to Search Trading Application Vendors">
							<span>Trading Application Vendors</span>
						</div>
					</li>
					<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
						<div class="counter-block text-center">
							<img src="/resources/images/main-img-3-no-search.png" alt="Click here to Search Analytics Application Vendors" title="Click here to Search Analytics Application Vendors">
							<span>Financial Analytics Vendors</span>
						</div>
					</li>
					<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
						<div class="counter-block text-center">
							<img src="/resources/images/main-img-4-no-search.png" alt="Click here to Search Research Report Providers" title="Click here to Search Research Report Providers">
							<span>Research Report Vendors</span>
						</div>
					</li>
				</ul>
			</div>			
		</section>
		<div id="fin_vendor_index" class="section">
			<div class="container" style="background:#fff;">
				<jsp:include page="common/banner.jsp"></jsp:include>
			</div>
		</div>
	</section>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
<!--<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.side-slider.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#sideslider').sideSlider();

	});
</script>-->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mail-functions.js"></script>

<script type="text/javascript">jssor_1_slider_init();</script>
</html>