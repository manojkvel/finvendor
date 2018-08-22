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
	Providers, Sector Research, Macro Research, Debt Market Research, Fund/ETF Research.</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />

<meta name="description"
	content="Search Free and Quality Equity Research Reports, Sector Research, Macro Research, Debt Market Research, Fund/ETF Research">
<meta name="keywords"
	content="Finvendor, Free Equity Research Reports, Sector Research, Macro Research, Debt Market Research, Fund/ETF Research">

<meta property="og:title"
	content="FinVendor - Search Free and Quality Equity Research Reports, Sector Research, Macro Research, Debt Market Research, Fund/ETF Research" />
<meta property="og:type" content='website' />
<meta property="og:url" content="https://www.finvendor.com" />
<meta property="og:description"
	content="Search Free and Quality Equity Research Reports, Sector Research, Macro Research, Debt Market Research, Fund/ETF Research" />
<meta property="og:image"
	content="../resources/images/company-logo-header.jpg" />
<meta property="og:site_name" content="JioCloud" />
<meta property="fb:app_id" content="" />

<meta name="twitter:url" content="https://www.finvendor.com" />
<meta name="twitter:title"
	content="FinVendor - Search Free and Quality Equity Research Reports, Sector Research, Macro Research, Debt Market Research, Fund/ETF Research" />
<meta name="twitter:image"
	content="../resources/images/company-logo-header.jpg" />
<meta name="twitter:description"
	content="Search Free and Quality Equity Research Reports, Sector Research, Macro Research, Debt Market Research, Fund/ETF Research" />
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
	<div id="fin_vendor_index" class="section">
		
		<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		<div id="splash_jumbotron">
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
									<form id="home_page_main_search" class="navbar-form navbar-input-group input-group p0" data-bind="enterkey: submit" action="/view/company-profile.jsp" method="GET">
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
													data-placeholder="Search stocks & its quotes" class="active">Stocks</a></li>
												<li class="dropdown-item"><a data-context="sectors"
													data-placeholder="Sectors">Sectors</a></li>
												<li class="dropdown-item"><a data-context="macroeconomy"
													data-placeholder="Macroeconomy">Macroeconomy</a></li>
												<li class="dropdown-item"><a data-context="research brokers/analysts"
													data-placeholder="Research brokers/analysts">Research Brokers/Analysts</a></li>
											</ul>
										</div>
										<input
											class="form-control mform-control navbar-ac phcenter ui-autocomplete-input"
											name="homepagesearch"
											placeholder="Search stocks & its quotes"
											 type="text"
											autocomplete="off"/>
										<div class="input-group-addon splash-search-addon nobdr">
											<i class="fa fa-search">
												<input name="txtSearchBox" class="submit-button" type="submit" disabled="disabled"/>
											</i>
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
		</div>
		<section>
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
		<section>
			<div class="container">
				<h2>Search Free and Highly Informative Sector Research Reports</h2>
				<div class="row">
					<div class="col-md-12 col-md-6">
						<div class="index-icon fa fa-search"></div>
						<div class="index-list-wrapper">
							<strong>Search the Sector</strong> <br />Find how each particular sector will perform and choose the sector which is having a bullish forecast. This will help you to do the top-bottom equity stock picking.
						</div>
					</div>
					<div class="col-md-12 col-md-6">
						<div class="index-icon fa fa-bolt"></div>
						<div class="index-list-wrapper">
							<strong>Free Access</strong> <br />Get a free access to 100+ high quality sector research reports.
						</div>
					</div>
					<div class="col-md-12 col-md-6">
						<div class="index-icon fa fa-bank"></div>
						<div class="index-list-wrapper">
							<strong>Premium access</strong> <br />Subscribe to a premium sector research reports provided by some well-acclaimed analysts /economists.
						</div>
					</div>
					<div class="col-md-12 col-md-6">
						<div class="index-icon fa fa-handshake-o"></div>
						<div class="index-list-wrapper">
							<strong>Aimed at sophisticated investors</strong> <br />Report is aimed at sophisticated investors but any ordinary investors can also get benefitted by the report.
						</div>
					</div>
				</div>
				<p>
					<span class="know-more"><a
						href="${pageContext.request.contextPath}/view/sector-research.jsp">Get Started</a></span>
				</p>
			</div>
		</section>
		<section>
			<div class="container">
				<h2>Search Free and All-encompassing Macroeconomic Research Reports</h2>
				<div class="row">
					<div class="col-md-12 col-md-6">
						<div class="index-icon fa fa-search"></div>
						<div class="index-list-wrapper">
							<strong>Search the Macroeconomic Indicator</strong> <br />Find how each particular Macroeconomic Indicator will perform and choose the Indicator and read the report. This will help you to do the top-bottom equity stock picking.
						</div>
					</div>
					<div class="col-md-12 col-md-6">
						<div class="index-icon fa fa-bolt"></div>
						<div class="index-list-wrapper">
							<strong>Free Access</strong> <br />Get a free access to 50+ high quality macroeconomic research reports.
						</div>
					</div>
					<div class="col-md-12 col-md-6">
						<div class="index-icon fa fa-bank"></div>
						<div class="index-list-wrapper">
							<strong>Premium access</strong> <br />Subscribe to a premium macroeconomic research reports provided by some well-acclaimed analysts /economists.
						</div>
					</div>
					<div class="col-md-12 col-md-6">
						<div class="index-icon fa fa-handshake-o"></div>
						<div class="index-list-wrapper">
							<strong>Aimed at sophisticated investors</strong> <br />Report is aimed at sophisticated investors but any ordinary investors can also get benefitted by the report.

						</div>
					</div>
				</div>
				<p>
					<span class="know-more"><a
						href="${pageContext.request.contextPath}/view/macro-research.jsp">Get Started</a></span>
				</p>
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
				<div class="col-xs-12 col-sm-12 slider-home" id="slider-home">
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
			</div>
		</section>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mail-functions.js"></script>

</html>