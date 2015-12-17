<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<div class="banner" id="banner">
	<div class="container">
		<div id="slider2_container"
			style="position: relative; top: 0px; left: 0px; width: 1000px; height: 391px; overflow: hidden;">
			<div u="slides"
				style="width: 1000px; height: 391px; overflow: hidden;">
				<div>
					<div style="overflow: hidden;" class="hm-slide-1">
						<div class="home-slider-wrap">
							<div class="content">
								<h2>
									<span>List Your</span><span>Offerings Here</span>
								</h2>
								<h3>Shared Platform For<br>All Financial Vendors</h3>
								<div class="para">
									<p>Market Data Vendors (Aggregators)</p>
									<p>Trading Application Vendors</p>
									<p>Analytical Application Vendors</p>
									<p>Research Report Providers</p>
								</div>
							</div>
						</div>
					</div>
					<div u="thumb">
						<img
							src="<%=request.getContextPath()%>/resources/images/slected-ico.png"
							alt="" title="" /><a href="#view1">Vendor -<span>Hassle-Free Product Listing</span></a>
					</div>
				</div>
				<div>
					<div style="overflow: hidden;" class="hm-slide-2">
						<div class="home-slider-wrap">
							<div class="content">
								<h2>
									<span>Find Your</span><span>Optimal</span><span>Vendor</span>
								</h2>
								<h3>Well Researched Search Platform</h3>
								<div class="para">
									<p>Search Our Pre-Screened Vendor Database</p>
									<p>in a very interactive way</p>
								</div>
							</div>
						</div>
					</div>
					<div u="thumb">
						<img
							src="<%=request.getContextPath()%>/resources/images/slected-ico.png"
							alt="" title="" /><i></i><a href="#view2">EndUsers -<span>Searching Fin vendor made Easy</span></a>
					</div>
				</div>
				<div>
					<div style="overflow: hidden;" class="hm-slide-3">
						<div class="home-slider-wrap">
							<div class="content">
								<h2>
									<span>Helps Increasing</span><span>Your Sales</span>
								</h2>
								<h3>Send Your Response to<br>End-User's RFP</h3>
								<div class="para">
									<p>Authenticated RFPs at FinVendor (as initiated by End-Users)</p>
									<p>are open for application for all qualified vendors</p>
								</div>
							</div>
						</div>
					</div>
					<div u="thumb">
						<img
							src="<%=request.getContextPath()%>/resources/images/slected-ico.png"
							alt="" title="" /><i></i><a href="#view3">Vendor -<span>Keep Track of New RFPs/Sales Leads</span></a>
					</div>
				</div>
				<div>
					<div style="overflow: hidden;" class="hm-slide-4">
						<div class="home-slider-wrap">
							<div class="content">
								<h2>
									<span>Post Your Vendor</span><span>Needs as a RFP</span>
								</h2>
								<h3>Helping You Going Public<br>With Your Vendor Needs</h3>
								<div class="para">
									<p>An Easy-to-use medium to share your vendor needs,</p>
									<p>track the response and shortlist the vendors</p>
								</div>
							</div>
						</div>
					</div>
					<div u="thumb">
						<img
							src="<%=request.getContextPath()%>/resources/images/slected-ico.png"
							alt="" title="" /><i></i><a href="#view4">EndUsers -<span>Post Your Fin Vendor Needs as a RFP</span></a>
					</div>
				</div>
			</div>
			<div u="thumbnavigator" class="jssort12">
				<div u="slides">
					<div u="prototype" class="p">
						<div class=w>
							<div u="thumbnailtemplate" class="c"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="clearfix"></div>
<div class="main-wrap" id="main-wrap">
	<div class="main-content-wrap">
		<c:choose>
			<c:when test="${sessionScope.loggedInUser != null}">
				<a href="${pageContext.request.contextPath}/<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>">
			</c:when>
			<c:otherwise>
				<a href="javascript:inner_login('<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>')">
			</c:otherwise>
		</c:choose>
			<img src="${pageContext.request.contextPath}/resources/images/main-img-1.png" style="float:right" alt="Click here to Search Market Data Vendors" title="Click here to Search Market Data Vendors">
		</a>
		<h2>
			Market Data <span>Vendors</span>
		</h2>
		<p> FinvVendor provides an intuitive interface to search and select the market data vendors
		 best suited for you needs, and a platform to interact and collaborate with them.
		
		</p>
		<a href="#"><img
			src="<%=request.getContextPath()%>/resources/images/rd-mr.png"
			alt="" title="" class="read-more" /></a>
	</div>
	<div class="main-content-wrap">
		<c:choose>
			<c:when test="${sessionScope.loggedInUser != null}">
				<a href="${pageContext.request.contextPath}/<%=RequestConstans.TradingApplication.TRADING_APPLICATION_INDEX_PAGE%>">
			</c:when>
			<c:otherwise>
				<a href="javascript:inner_login('<%=RequestConstans.TradingApplication.TRADING_APPLICATION_INDEX_PAGE%>')">
			</c:otherwise>
		</c:choose>	
			<img src="${pageContext.request.contextPath}/resources/images/main-img-2.png" style="float:right" alt="Click here to Search Trading Application Vendors" title="Click here to Search Trading Application Vendors">
		</a>
		<h2>
			Trading Application <span>Vendors</span>
		</h2>
		<p>Finding the right application for your trading needs is no easy feat. FinVendor provides you a platform to 
		filter various vendor solutions by specialities listed to a granular level. </p>
		<a href="#"><img
			src="<%=request.getContextPath()%>/resources/images/rd-mr.png"
			alt="" title="" class="read-more" /></a>
	</div>
	<div class="main-content-wrap">
		<c:choose>
			<c:when test="${sessionScope.loggedInUser != null}">
				<a href="${pageContext.request.contextPath}/<%=RequestConstans.FinancialAnalyticsApplication.FINANCIAL_ANALYTICS_APPLICATION_INDEX_PAGE%>">
			</c:when>
			<c:otherwise>
				<a href="javascript:inner_login('<%=RequestConstans.FinancialAnalyticsApplication.FINANCIAL_ANALYTICS_APPLICATION_INDEX_PAGE%>')">
			</c:otherwise>
		</c:choose>
			<img src="${pageContext.request.contextPath}/resources/images/main-img-3.png" style="float:right" alt="Click here to Search Analytics Application Vendors" title="Click here to Search Analytics Application Vendors">
		</a>
		<h2>
			Financial Analytics <span>Vendors</span>
		</h2>
		<p>In this competitive industry Analytics is the most sought after service for business and risk insights.
		FinVendor helps you choose the right analytics vendor befitting your business.</p>
		<a href="#"><img
			src="<%=request.getContextPath()%>/resources/images/rd-mr.png"
			alt="" title="" class="read-more" /></a>
	</div>
	<div class="main-content-wrap last-child">
		<c:choose>
			<c:when test="${sessionScope.loggedInUser != null}">
				<a href="${pageContext.request.contextPath}/<%=RequestConstans.ResearchReportProviders.RESEARCH_REPORT_PROVIDERS_INDEX_PAGE%>">
			</c:when>
			<c:otherwise>
				<a href="javascript:inner_login('<%=RequestConstans.ResearchReportProviders.RESEARCH_REPORT_PROVIDERS_INDEX_PAGE%>')">
			</c:otherwise>
		</c:choose>
			<img src="${pageContext.request.contextPath}/resources/images/main-img-4.png" style="float:right" alt="Click here to Search Research Report Providers" title="Click here to Search Research Report Providers">
		</a>
		<h2>
			Financial Research <span>Report Vendors</span>
		</h2>
		<p>Be it macro economic analysis, Sector analysis or Analysis on Market pulse,a good research report is the key to a successful business.
		And FinVendor can help you find the best research vendors. </p>
		<a href="#"><img
			src="<%=request.getContextPath()%>/resources/images/rd-mr.png"
			alt="" title="" class="read-more" /></a>
	</div>
	<div class="clearfix"></div>
</div>
<div class="slider-home" id="slider-home">
	<img src="<%=request.getContextPath()%>/resources/images/hear.png"
		alt="" title="" class="slider-hr" />
	<h2>Interactive and Collaborative Services</h2>
	<div class="slider2">
		<div class="slide">
		<p class="signature">
				<strong>White papers</strong> <span>Informative, Inspective and Inspirational</span>
			</p>
			<br>
			<p>An inventory of white papers on different topics in our industry, aimed 
			to help to provide continued learning on current trends, innovative markets,
			regulatory mandates and adherence etc.,</p>
			
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
<div class="clearfix"></div> 
