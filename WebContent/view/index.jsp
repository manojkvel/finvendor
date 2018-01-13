<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FinVendor - Market Place for Financial Research Report
	Providers, Market Data Vendors and Trading Platform.</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />

<meta name="description"
	content="Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms">
<meta name="keywords"
	content="Finvendor, Free Equity Research Reports, Market Data Providers, Trading Platforms, Analytics Report">

<meta property="og:title"
	content="FinVendor - Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms" />
<meta property="og:type" content='website' />
<meta property="og:url" content="https://www.finvendor.com" />
<meta property="og:description"
	content="Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms" />
<meta property="og:image"
	content="../resources/images/company-logo-header.jpg" />
<meta property="og:site_name" content="JioCloud" />
<meta property="fb:app_id" content="" />

<meta name="twitter:url" content="https://www.finvendor.com" />
<meta name="twitter:title"
	content="FinVendor - Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms" />
<meta name="twitter:image"
	content="../resources/images/company-logo-header.jpg" />
<meta name="twitter:description"
	content="Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms" />
<meta name="twitter:creator" content="google" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<link href="https://www.finvendor.com" rel="canonical" />

<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

<meta http-equiv="Pragma" content="no-cache">
<meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
<!--<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jssor.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jssor-deep-minified.css">-->

<!-- #region Jssor Slider Begin -->
<!-- Generator: Jssor Slider Maker -->
<!-- Source: http://www.jssor.com -->
<!-- This is deep minimized code which works independently. -->
<!--<script type="text/javascript"
	src="</%=request.getContextPath()%>/resources/js/jssor_deep_minified.js"></script> -->
<script async
	src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
	(adsbygoogle = window.adsbygoogle || []).push({
		google_ad_client : "ca-pub-9715733329489506",
		enable_page_level_ads : true
	});
</script>
</head>
<body id="home">
	<c:set var="checkingresultpageview" value="${checkingresultpageview}"></c:set>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<section>
	<div class="container-fluid">
		<div class="row">
			<div class="splash-content">
				<div class="splash-title">Track Stocks, Sectors &amp;
					Macroeconomy</div>
				<div class="splash-subtitle">Access the quality Research
					Reports of India, UK &amp; US Markets with Just few clicks</div>
				<div class="splash-search-bar">
					<div class="row m0">
						<div class="col-md-2 col-sm-2 hiddenn-sm-down col-lg-2"></div>
						<div class="col-md-8 col-sm-8 hiddenn-sm-down col-lg-8">
							<form class="navbar-form navbar-input-group input-group p0"
								data-bind="enterkey: submit">
								<div class="input-group-btn ">
									<button type="button"
										class="norbdr pr05r splash-search-addon btn  dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false">
										<span class="htmlvalue">Stocks</span>
										<span class="fa fa-caret-down" style="padding-left: 5px;"></span>
									</button>
									<ul class="dropdown-menu ddselect"
										data-function="searchValueChange">
										<li class="dropdown-item"><a data-context="stock"
											data-placeholder="stocks" class="active">Stocks</a></li>
										<li class="dropdown-item"><a data-context="sectors"
											data-placeholder="sectors">Sectors</a></li>
										<li class="dropdown-item"><a data-context="macroeconomy"
											data-placeholder="macroeconomy">Macroeconomy</a></li>
										<li class="dropdown-item"><a data-context="researchbrokers/analysts"
											data-placeholder="researchbrokers/analysts">Research Brokers/Analysts</a></li>
									</ul>
								</div>
								<input
									class="form-control mform-control navbar-ac phcenter ui-autocomplete-input"
									name="search"
									placeholder="Search for a Stock, Sector or Interest area"
									 type="text"
									autocomplete="off">
								<div class="input-group-addon splash-search-addon nobdr">
									<i class="fa fa-search  "></i>
								</div>
							</form>
						</div>
						<div class="col-md-2 col-sm-2 hiddenn-sm-down col-lg-2"></div>
					</div>
				</div>
				<div class="card-group splash-card-group">
					<div class="card splash-card"></div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<section id="what_we_do">
	<div class="container">
		<h2>Search Free and Quality Equity Research Reports</h2>
		<div class="row">
			<div class="col-md-12 col-md-6">
				<div class="index-icon fa fa-search"></div>
				<div class="index-list-wrapper">
					<strong>Search the company</strong> <br />Find the stock worthy of investment of your hard-earned money.
				</div>
			</div>
			<div class="col-md-12 col-md-6">
				<div class="index-icon fa fa-bolt"></div>
				<div class="index-list-wrapper">
					<strong>Free Access</strong> <br />Get a free access to
				more than 1000 high quality broker research reports covered for
				Indian Stock Market.
				</div>
			</div>
			<div class="col-md-12 col-md-6">
				<div class="index-icon fa fa-bank"></div>
				<div class="index-list-wrapper">
					<strong>Premium access</strong> <br />Subscribe to a premium research reports provided by some trusted brokers/analysts.
				</div>
			</div>
			<div class="col-md-12 col-md-6">
				<div class="index-icon fa fa-handshake-o"></div>
				<div class="index-list-wrapper">
					<strong>Beneficial for all investors</strong> <br />Equally
				beneficial for both group of investors – newbie and astute
				investors.
				</div>
			</div>
		</div>
		<p>
			<span class="know-more"><a
				href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp">Get Started</a></span>
		</p>
	</div>
	</section>
	<section id="feature_list">
	<div class="feature_list">
		<h2>Our Solutions</h2>
		<ul>
			<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3"
				style="visibility: hidden;">
				<div class="counter-block">
					<div class="content">
						<img src="/resources/images/main-img-3-no-search.png"
							alt="Click here to Search Analytics Application Vendors"
							title="Click here to Search Analytics Application Vendors">
						<span>Financial Analytics Vendors</span>
					</div>
					<div class="feature_list_tooltip">
						In this competitive industry Analytics is the most sought after
						service for business and risk insights. FinVendor helps you choose
						the right analytics vendor befitting your business. <span><a
							href="#">See More</a></span>
					</div>
				</div>
			</li>
			<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
				<div class="counter-block">
					<div class="content">
						<img src="/resources/images/main-img-1-no-search.png"
							alt="Click here to Search Market Data Vendors"
							title="Click here to Search Market Data Vendors">
					</div>
					<div class="feature_list_tooltip">
						<strong>Market Data Providers</strong> FinVendor provides an
						intuitive interface to search and select the market data vendors
						best suited for you needs, and a platform to interact and
						collaborate with them. <span><a
							href="/view/common/inner.jsp?nav=SOLUTIONS&subNav=mdvad&RaYUnA=">See
								More</a></span>
					</div>
				</div>
			</li>
			<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
				<div class="counter-block">
					<div class="content">
						<img src="/resources/images/main-img-2-no-search.png"
							alt="Click here to Search Trading Application Vendors"
							title="Click here to Search Trading Application Vendors">
					</div>
					<div class="feature_list_tooltip">
						<strong>Trading Platforms</strong> Finding the right application
						for your trading needs is no easy feat. FinVendor provides you a
						platform to filter various vendor solutions by specialities listed
						to a granular level. <span><a
							href="/view/common/inner.jsp?nav=SOLUTIONS&subNav=tavd&RaYUnA=">See
								More</a></span>
					</div>
				</div>
			</li>
			<li class="col-xs-12 col-sm-3 col-md-3 col-lg-3"
				style="visibility: hidden;">
				<div class="counter-block">
					<div class="content">
						<img src="/resources/images/main-img-4-no-search.png"
							alt="Click here to Search Research Report Providers"
							title="Click here to Search Research Report Providers"> <span>Research
							Report Vendors</span>
					</div>
					<div class="feature_list_tooltip">
						Be it macro economic analysis, Sector analysis or Analysis on
						Market pulse,a good research report is the key to a successful
						business. And FinVendor can help you find the best research
						vendors. <span><a href="#">See More</a></span>
					</div>
				</div>
			</li>
		</ul>
	</div>
	</section>

	<section id="faq_list">
	<div class="container">
		<h2>FAQs section</h2>
		<ul>
			<li><strong>I don't see the research report for a
					specific Company or specific Broker. What to do?</strong> <br />Shoot us an
				email with all details at <a href="mailto: enquiry@finvendor.com">enquiry@finvendor.com</a></li>

			<li><strong>How can I publish the reports on FinVendor?</strong>
				<br />Shoot us an email with all details at <a
				href="mailto: sales@finvendor.com">sales@finvendor.com</a></li>

			<li><strong>I am finding some difficulty in using the
					Research report search page.</strong> <br />Take the screen shot and shoot
				us an email with all details at <a
				href="mailto: enquiry@finvendor.com">enquiry@finvendor.com</a></li>

			<li><strong>Do you support entitlement? Our report
					should be accessible to only selected group of clients?</strong> <br />Yes,
				we do. It is customizable to individual broker/publisher level.</li>

			<li><strong>We need more paying clients, can you help
					with increasing our sales?</strong> <br />Yes, we can help you increase
				your sales. We can also provide you valuation leads.</li>
		</ul>
	</div>
	</section>

	<section id="user_interest_info">
	<div class="container">
		<div class="col-xs-12 col-sm-7 slider-home" id="slider-home">
			<!--<img src="<%=request.getContextPath()%>/resources/images/hear.png"
					alt="" title="" class="slider-hr" />-->
			<h2>Interactive and Collaborative Services</h2>
			<div class="slider2">
				<div class="slide">
					<p class="signature">
						<strong>White papers</strong> <span>Informative, Inspective
							and Inspirational</span>
					</p>
					<br />
					<p>An inventory of white papers on different topics in our
						industry, aimed to help to provide continued learning on current
						trends, innovative markets, regulatory mandates and adherence etc.
					</p>
				</div>
				<div class="slide">
					<p class="signature">
						<strong>Spotlights</strong> <span> Insightful, Focused and
							Adaptive</span>
					</p>
					<br>
					<p>Focus on emerging trends, success stories, user recommended
						research and highlights from across the industry to help our
						customers be updated on the current industry spotlight</p>

				</div>
				<div class="slide">
					<p class="signature">
						<strong>Case studies</strong> <span>Observe, Identify and
							Implement</span>
					</p>
					<br>
					<p>Information on case studies conducted on various topics
						relevant to our industry aimed to provide insights to our
						customers for them to identify similar circumstances and adopt the
						implementation if applicable.</p>

				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-5 fv_signup_newsletter">
			<h2>Signup Newsletter</h2>
			<p>
				Want to keep upto date with all our latest news and information? <br />Enter
				your email below to be added to our mailing list.
			</p>
			<form name="form_signup_newsletter">
				<ul>
					<li><input type="email" name="signup_newsletter"
						placeholder="Email Address" /></li>
					<li><a class="submit_btn">Signup</a></li>
				</ul>
			</form>
		</div>
	</div>
	</section>
	<div id="fin_vendor_index" class="section"></div>
	</section>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mail-functions.js"></script>

<script type="text/javascript">
	/*jssor_1_slider_init();*/
</script>

</html>