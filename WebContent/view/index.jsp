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
		<div id="jssor_1" style="position:relative;margin:0 auto;top:-20px;left:0px;width:1980px;height:550px;overflow:hidden;visibility:hidden;">
			<!-- Loading Screen -->
			<div data-u="loading" class="jssorl-oval" style="position:absolute;top:0px;left:0px;text-align:center;background-color:rgba(0,0,0,0.7);">
				<img style="margin-top:-19.0px;position:relative;top:50%;width:38px;height:38px;" src="img/oval.svg" />
			</div>
			<div data-u="slides" style="cursor:default;position:relative;top:0px;left:0px;width:1980px;height:650px;overflow:hidden;">

				<div>
					<img data-u="image" src="${pageContext.request.contextPath}/resources/images/banner/skyline-banner.jpg" />
					<div class="banner-content1" style="">
						<div>Shared Platform For All Financial Vendors<br><span>Hassle Free Product Listing</span></div>
						<span class="know-more"><a href="#">Know more</a></span>
					</div>
				</div>
				<div>
					<img data-u="image" src="${pageContext.request.contextPath}/resources/images/banner/world-map-banner.jpg" />
					<div class="banner-content2" style="">
						<div>Well-Researched Search Platform<br><span>Searching Financial vendor made Easy</span></div>
						<span class="know-more"><a href="#">Know more</a></span>
					</div>
				</div>
				<div>
					<img data-u="image" src="${pageContext.request.contextPath}/resources/images/banner/closeup-pen-banner.jpg" />
					<div class="banner-content1" style="color: #000;">
						<div>Helping You Going Public with your vendor<br><span>Post Your Financial Vendor Needs as a RFP</span></div>
						<span class="know-more"><a href="#">Know more</a></span>
					</div>
				</div>
				<div>
					<img data-u="image" src="${pageContext.request.contextPath}/resources/images/banner/billion-banner.jpg" />
					<div class="banner-content2" style="color: #000;">
						<div>Keep Track of New RFPs/Sales Leads<br><span>Send Your Response to
End-User's RFP</span></div>
						<span class="know-more"><a href="#">Know more</a></span>
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
						<div class="counter-block">
							<div class="content">
								<img src="/resources/images/main-img-1-no-search.png" alt="Click here to Search Market Data Vendors" title="Click here to Search Market Data Vendors">
								<span>Market Data Vendors</span>
							</div>
							<div class="feature_list_tooltip">
								FinVendor provides an intuitive interface to search and select the market data vendors best suited for you needs, and a platform to interact and collaborate with them.
								<span><a href="#">See More</a></span>
							</div>							
						</div>
					</li>
					<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
						<div class="counter-block">
							<div class="content">
								<img src="/resources/images/main-img-2-no-search.png" alt="Click here to Search Trading Application Vendors" title="Click here to Search Trading Application Vendors">
							<span>Trading Application Vendors</span>
							</div>
							<div class="feature_list_tooltip">
								Finding the right application for your trading needs is no easy feat. FinVendor provides you a platform to filter various vendor solutions by specialities listed to a granular level.
							<span><a href="#">See More</a></span>
							</div>							
						</div>
					</li>
					<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
						<div class="counter-block">
							<div class="content">
								<img src="/resources/images/main-img-3-no-search.png" alt="Click here to Search Analytics Application Vendors" title="Click here to Search Analytics Application Vendors">
								<span>Financial Analytics Vendors</span>
							</div>
							<div class="feature_list_tooltip">
								In this competitive industry Analytics is the most sought after service for business and risk insights. FinVendor helps you choose the right analytics vendor befitting your business.
							<span><a href="#">See More</a></span>
							</div>							
						</div>
					</li>
					<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
						<div class="counter-block">
							<div class="content">
								<img src="/resources/images/main-img-4-no-search.png" alt="Click here to Search Research Report Providers" title="Click here to Search Research Report Providers">
								<span>Research Report Vendors</span>
							</div>
							<div class="feature_list_tooltip">
								Be it macro economic analysis, Sector analysis or Analysis on Market pulse,a good research report is the key to a successful business. And FinVendor can help you find the best research vendors.
							<span><a href="#">See More</a></span>
							</div>							
						</div>
					</li>
				</ul>
			</div>			
		</section>

		<div class="slider-home" id="slider-home" style="background: #fff;">
			<img src="<%=request.getContextPath()%>/resources/images/hear.png"
			alt="" title="" class="slider-hr" />
			<h2>Interactive and Collaborative Services</h2>
			<div class="slider2">
				<div class="slide">
					<p class="signature">
						<strong>White papers</strong> 
						<span>Informative, Inspective and Inspirational</span>
					</p>
					<br />
					<p>
						An inventory of white papers on different topics in our industry, aimed 
						to help to provide continued learning on current trends, innovative markets,
						regulatory mandates and adherence etc.
					</p>
				</div>
					<div class="slide">
						<p class="signature">
							<strong>Spotlights</strong> <span> Insightful, Focused and Adaptive</span>
						</p>
						<br>
						<p>Focus on emerging trends, success stories, user recommended research 
							and highlights from across the industry to help our customers be updated on the current
							industry spotlight </p>

						</div>
						<div class="slide">
							<p class="signature">
								<strong>Case studies</strong> <span>Observe, Identify and Implement</span>
							</p>
							<br>
							<p>Information on case studies conducted on various topics relevant to our industry aimed to provide insights
								to our customers for them to identify similar circumstances and adopt the implementation if applicable.</p>

							</div>

						</div>
					</div>
		<div id="fin_vendor_index" class="section">
			
		</div>
	</section>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mail-functions.js"></script>

<script type="text/javascript">jssor_1_slider_init();</script>
</html>