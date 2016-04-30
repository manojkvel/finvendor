<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
	<head>
		<title>FinVendor</title>
		<jsp:include page="head.jsp"></jsp:include>
		<!-- being referenced - no file found <link href="${pageContext.request.contextPath}/resources/newsingleasset/css/main.css" rel="stylesheet"/> -->
		<script src="${pageContext.request.contextPath}/resources/js/CreateHTML5Elements.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>
		<!-- being referenced - no file found<script src="${pageContext.request.contextPath}/resources/newlogin/js/superfish.js </script>-->
		
		<script>
			
			( function( $ ) {
				$( document ).ready(function() {
					$('#sidelinks a').click(function(e) {						
						$('.inner-left-wrap').hide();
						var checkElement = $(this).text();
						var divId = $(this).parent().data("div-id");
						if(divId.indexOf("_") > 0){
							var breadcrumbLink = '<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=' + divId.substring(0, divId.indexOf("_")) + '">' + divId.substring(0, divId.indexOf("_")) + '</a></li>';
							checkElement =  '<li class="li-spr">></li>' + breadcrumbLink + '<li class="li-spr">></li><li>' + checkElement + '</li></ul>';
						}else{
							checkElement = '<li class="li-spr">></li><li>' + checkElement + '</li></ul>';
						}
						divId = '#' + divId;
						$(divId).show();
						$('#sidelinks li').removeClass('li-ico');
						$(this).closest('li').addClass('li-ico');
						$('#breadcrumb_text').html('<ul><li><a href="${finven:resolveContextPath(pageContext.request.contextPath)}">HOME</a></li>' + checkElement);
						checkElement = $(this).next();
						if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
						  $(this).closest('li').removeClass('active');
						  checkElement.slideUp('normal');
						}
						if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
						  $('#sidelinks ul ul:visible').slideUp('normal');
						  checkElement.slideDown('normal');
						}
					});
				});
			} )( jQuery );
					
		</script>	
	</head>
	<body>
		<jsp:include page="header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		<jsp:include page="../login.jsp"></jsp:include>
			<div class="inner-breadcrumb">
			  <div class="container" id="breadcrumb_text">
			    <ul>
			      <li><a href="${finven:resolveContextPath(pageContext.request.contextPath)}">HOME</a></li>			      
			      <c:choose>
			      	<c:when test="${param.subNav != null}">
			      		<li class="li-spr">></li>		      
			      		<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=${param.nav}">${param.nav}</a></li>
			      		<li class="li-spr">></li>
				      	<c:set var="requestMapKey" value="${param.nav}_${param.subNav}"/>
				      	<li>${requestConstants.reqParamDescriptionMap[requestMapKey]}</li>
			      	</c:when>
			      	<c:otherwise>
			      		<li class="li-spr">></li>		      
			      		<li>${finven:replaceCharacter(param.nav, "_", " ")}</li>
			      	</c:otherwise>
			      </c:choose>			      				      
			    </ul>
			    <div class="clearfix"></div>
			  </div>
			</div>
			<div class="container">
			  <div class="inner-content">
			  	<div class="inner-left-wrap" id="SOLUTIONS">
			      <h1>SOLUTIONS</h1>
			      <p>On FinVendor platform, End-Users(I-banks, Portfolio Managers, Hedge Funds, Research Analyst, University Scholars, etc) can search all available vendors for a certain vendor offerings (Market Data Vendor, Trading Application Vendor, Financial Analytics Application Vendor, Financial Research Report Providers). End-Users can see the granular level info about the vendor product offerings. End-Users can also initiate a RFP (Request-For-Proposal) with their detailed vendor’s product needs. Such RFPs are made available to all FinVendor listed vendors. End-Users can also track their initiated RFPs, review vendor detailed response and finalize the vendor in the least possible time.</p>
			      <p>Financial vendors (Data Aggregators, Trading Application Vendor, Financial Analytics Application Vendor, Financial Research Report Providers) can list their offerings in the well-researched FinVendor format. Data vendors can also participate in all open RFPs (Request-For-Proposal) initiated by data End-Users.</p>
		    	</div>
				<div class="inner-left-wrap" id="SOLUTIONS_mdvad">
					<h1>Market Data Vendors (Aggregators)</h1>
					<p>
						<c:choose>
							<c:when test="${sessionScope.loggedInUser != null && (sessionScope.loggedInRole=='ROLE_CONSUMER' || sessionScope.loggedInRole=='ROLE_ADMIN')}">
								<a href="${pageContext.request.contextPath}/<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>">
								<img src="${pageContext.request.contextPath}/resources/images/main-img-1.png" style="float:right" alt="Click here to Search Market Data Vendors" title="Click here to Search Market Data Vendors">
								</a>
							</c:when>
							<c:otherwise>
								<!-- <a href="javascript:inner_login('<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>')"> -->
								<img src="${pageContext.request.contextPath}/resources/images/main-img-1-no-search.png" style="float:right" alt="Click here to Search Market Data Vendors" title="Click here to Search Market Data Vendors">
								
							</c:otherwise>
						</c:choose>						
							
						
						In this ever changing and vast financial market data world, one could be a smaller sized player or a medium sized player or a bigger sized player. But all are here with a common goal -
						<br>
						<span style="display: list-item; display:relative; margin-left: 3em">Making one’s presence meaningful in this market.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Drawing the attention of quality customer towards one’s data offerings.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Compete with bigger sized competitor on equal-footing, Gain Market Share and add value for your stakeholders and shareholders.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Minimizing the customer acquisition time as less as possible.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Keeping oneself abreast with current trends in Market-Data world.</span>
					</p>					
					<h2>How FinVendor Helps You achieve Your Goal</h2>
					<p>FinVendor provides a platform to list your vendor offerings in most granular form. This platform is being used by Data End Users to search the data vendors for their varied data needs. The more detailed info you provide, the better end users would be able to understand you</p>
					<p>Only End users can access your listing info. None of your competitors would be able to see your data offerings. One of the examples of Data End user requirement</p>
					<p style="background-color:lightgray"><b>Reference Bank</b> is looking for a data vendor who provides Bangladesh Market’s Corporate Bond market data having attributes such as A1, A2, A3, A4, A5, A6, A7, A8, A9 and A10. The vendor should make this file available through FTP at every day after local market closure. Subscription free for this file should not exceed $100 per month. Vendor should have 24x7 support coverage in APAC market hour. Vendor should be in this business since more than 3 years. Vendor’s company should be either LLP or private limited</p>
					<p>Data end user can either come to FinVendor, use its search Market data vendor (aggregator) functionality and search the desired vendor or Data end user can initiate a RFP (Request for Proposal) for this using FinVendor RFP section. Vendor can make its presence felt by listing on FinVendor platform. Once listed, vendor can apply for any open RFPs. Based on the vendor’s response on RFI, vendor will be shortlisted and subsequently, vendor will be contacted by Data End users</p>										
					</div>							
		    	<div class="inner-left-wrap" id="SOLUTIONS_tavd">
			      <h1>Trading Application Vendors</h1>
			      <p>
			      	<c:choose>
						<c:when test="${sessionScope.loggedInUser != null && (sessionScope.loggedInRole=='ROLE_CONSUMER' || sessionScope.loggedInRole=='ROLE_ADMIN')}">
							<a href="${pageContext.request.contextPath}/<%=RequestConstans.TradingApplication.TRADING_APPLICATION_INDEX_PAGE%>">
							<img src="${pageContext.request.contextPath}/resources/images/main-img-2.png" style="float:right" alt="Click here to Search Trading Application Vendors" title="Click here to Search Trading Application Vendors">
							</a>
						</c:when>
						<c:otherwise>
							<!-- <a href="javascript:inner_login('<%=RequestConstans.TradingApplication.TRADING_APPLICATION_INDEX_PAGE%>')"> -->
							<img src="${pageContext.request.contextPath}/resources/images/main-img-2-no-search.png" style="float:right" alt="Click here to Search Trading Application Vendors" title="Click here to Search Trading Application Vendors">
						</c:otherwise>
					</c:choose>	
			      		
					
			      	There are multitudes of trading application vendors across the world. One could be a smaller sized player or a medium sized player or a bigger sized player. But all are here with a common goal -
			      	<br>
					<span style="display: list-item; display:relative; margin-left: 3em">Making one’s presence meaningful in this market.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Drawing the attention of quality customer towards one’s trading application offerings.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Compete with bigger sized competitor on equal-footing, Gain Market Share and add value for your stakeholders and shareholders.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Minimizing the customer acquisition time as less as possible.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Keeping oneself abreast with current trends in trading application world.</span>
				  </p>  
			      <p>FinVendor provides a platform to list your vendor offerings in most granular form. This platform is being used by End Users to search the trading application vendors for their varied trading needs. The more detailed info you provide, the better end users would be able to understand you.</p>
			      <p>Only End users can access your listing info. None of your competitors would be able to see your product offerings. One of the examples of End user requirement</p>
			      <p style="background-color:lightgray"><b>TradeNow</b> bank is expanding its operations and opening a trading desk in South-east Asian Government Bond market. For this purpose, they are looking for a trading application vendor who supports trade execution for Government Bond for South-East Asia regions. Trading application should support other functions of trading - Real Time Quotes, Real Time Financial News, Trade Analytics, Collateral Management, Smart Order Routing (Sequential SOR) execution facility, ‘good for auction’ Order type, Ability to connect to liquidity providers, Trading Strategy Builder, Algorithmic Signal Generation. The vendor should make this application accessible through Binaries (Executable) with dedicated rate, broker and revaluation engines. Underlying software program should be written in open-source programming language. Subscription fee for this application should not exceed $1000 per month. Vendor should have 24x7 support coverage in APAC market hour. Vendor should be in this business since more than 3 years. Vendor’s company should be either LLP or private limited.</p>
			      <p>Trading Application End user can either come to FinVendor, use its search Trading Application Vendor functionality and search the desired vendor or End user can initiate a RFP (Request for Proposal) for this on FinVendor RFP section. Vendor, on the other hand, can make its presence felt by listing on FinVendor platform. Once listed, vendor can apply for any open RFPs. Based on the vendor’s response on RFP, vendor will be shortlisted and subsequently, vendor will be contacted by End users.</p>
		    	  <br>
		    	</div>
		    	<div class="inner-left-wrap" id="SOLUTIONS_faavd">
			      <h1>Analytics Application Vendors</h1>
			      <p>
			      	<c:choose>
						<c:when test="${sessionScope.loggedInUser != null && (sessionScope.loggedInRole=='ROLE_CONSUMER' || sessionScope.loggedInRole=='ROLE_ADMIN')}">
							<a href="${pageContext.request.contextPath}/<%=RequestConstans.FinancialAnalyticsApplication.FINANCIAL_ANALYTICS_APPLICATION_INDEX_PAGE%>">
							<img src="${pageContext.request.contextPath}/resources/images/main-img-3.png" style="float:right" alt="Click here to Search Analytics Application Vendors" title="Click here to Search Analytics Application Vendors">
							</a>
						</c:when>
						<c:otherwise>
							<!-- <a href="javascript:inner_login('<%=RequestConstans.FinancialAnalyticsApplication.FINANCIAL_ANALYTICS_APPLICATION_INDEX_PAGE%>')"> -->
							<img src="${pageContext.request.contextPath}/resources/images/main-img-3-no-search.png" style="float:right" alt="Click here to Search Analytics Application Vendors" title="Click here to Search Analytics Application Vendors">
						</c:otherwise>
					</c:choose>
			      		
					
			      	Each new financial regulation is proving to be an opportunity as well as huge challenge for Financial Analytics (such as, Risk Analytics or Trade analytics) application vendors. One could be a smaller sized player or a medium sized player or a bigger sized player. But all are here with a common goal -
			      	<br>
					<span style="display: list-item; display:relative; margin-left: 3em">Making one’s presence meaningful in this market.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Drawing the attention of quality customer towards one’s Financial Analytics application offerings.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Compete with bigger sized competitor on equal-footing, Gain Market Share and add value for your stakeholders and shareholders.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Minimizing the customer acquisition time as less as possible.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Keeping oneself abreast with current trends in Financial Analytics application world.</span>
				   </p>
				   <p>FinVendor provides a platform to list your vendor offerings in most granular form. This platform is being used by End Users to search the financial analytics application vendors for their varied day-to-day needs. The more detailed info you provide, the better end users would be able to understand you.</p>
				   <p>Only End users can access your listing info. None of your competitors would be able to see your product offerings. One of the examples of End user requirement</p>
				   <p style="background-color:lightgray"><b>HedgeNow</b>, a Macro-strategy based hedge fund has opened its operations few months back. They are looking for a financial analytics application vendor who supports Hedge accounting, collateral margining, historical analytics, stress testing, counterparty risk –CVA, Liquidity risk management, OTC products pricing and valuation facilities, risk reporting. The vendor should make this application accessible through Binaries (Executable) with dedicated rate, broker and revaluation engines. Underlying software program should be written in open-source programming language. Subscription fee for this application should not exceed $1000 per month. Vendor should have 24x7 support coverage in APAC market hour. Vendor should be in this business since more than 3 years. Vendor’s company should be either LLP or private limited.</p>
				   <p>Application End user can either come to FinVendor, use its search Analytics Application Vendor functionality and search the desired vendor or End user can initiate a RFP (Request for Proposal) for this on FinVendor RFP section. Vendor, on the other hand, can make its presence felt by listing on FinVendor platform. Once listed, vendor can apply for any open RFPs. Based on the vendor’s response on RFP, vendor will be shortlisted and subsequently, vendor will be contacted by End users.</p>
		    	</div>
		    	<div class="inner-left-wrap" id="SOLUTIONS_frrpd">
			      <h1>Research Report Providers</h1>
			      <p>
			      	<c:choose>
						<c:when test="${sessionScope.loggedInUser != null && (sessionScope.loggedInRole=='ROLE_CONSUMER' || sessionScope.loggedInRole=='ROLE_ADMIN')}">
							<a href="${pageContext.request.contextPath}/<%=RequestConstans.ResearchReportProviders.RESEARCH_REPORT_PROVIDERS_INDEX_PAGE%>">
							<img src="${pageContext.request.contextPath}/resources/images/main-img-4.png" style="float:right" alt="Click here to Search Research Report Providers" title="Click here to Search Research Report Providers">
							</a>
						</c:when>
						<c:otherwise>
							<!-- <a href="javascript:inner_login('<%=RequestConstans.ResearchReportProviders.RESEARCH_REPORT_PROVIDERS_INDEX_PAGE%>')"> -->
							<img src="${pageContext.request.contextPath}/resources/images/main-img-4-no-search.png" style="float:right" alt="Click here to Search Research Report Providers" title="Click here to Search Research Report Providers">
						</c:otherwise>
					</c:choose>
			      		
					
			      	The presence of multitudes of research analysts creates a conundrum of some sorts- which analyst’s research report is better than others. One could be a smaller sized player or a medium sized player or a bigger sized player. But all are here with a common goal -
				    <br>
					<span style="display: list-item; display:relative; margin-left: 3em">Making one’s presence meaningful in this market.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Drawing the attention of quality customer towards one’s research coverage.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Compete with bigger sized competitor on equal-footing, Gain Market Share and add value for your stakeholders and shareholders.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Minimizing the customer acquisition time as less as possible.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Keeping oneself abreast with current trends in financial research world.</span>
				  </p>
			      <p>FinVendor provides a platform to list your vendor offerings in most granular form. This platform is being used by End Users to search the financial research report providers for their varied day-to-day needs. The more detailed info you provide, the better end users would be able to understand you.</p>
			      <p>Only End users can access your listing info. None of your competitors would be able to see your product offerings. One of the examples of End user requirement</p>
			      <p style="background-color:lightgray"><b>MyPortfolio</b>, a fund management house, has launched its new emerging market centric fund few months back. They are looking for a research report providers who performs the Macro economic Analysis, Sector Analysis, Industry Analysis, Commodity Analysis, Exchange Rate Analysis, Interest Rate Analysis, Equity research, Debt Market research, Index research, Fund/ETF research for Indian markets, Indonesian markets, Philippines markets, Vietnam Markets. The vendor should distribute the report in PDF format through email. Subscription fee for this application should not exceed $1000 per month. Vendor should have 24x7 support coverage in APAC market hour. Vendor should be in this business since more than 3 years. Vendor’s company should be either LLP or private limited</p>
			      <p>End user can either come to FinVendor, use its search Research Report Providers functionality and search the desired vendor or End user can initiate a RFP (Request for Proposal) for this on FinVendor RFP section. Vendor, on the other hand, can make its presence felt by listing on FinVendor platform. Once listed, vendor can apply for any open RFPs. Based on the vendor’s response on RFP, vendor will be shortlisted and subsequently, vendor will be contacted by End users.</p>
		    	</div>
		    	<div class="inner-left-wrap" id="SERVICES">
			      <h1>SERVICES</h1>
			      <p>FinVendor wishes to help end users in more interactive way as well. End users can avail FinVendor consultancy services for variety of purposes – finding data aggregator, trading application vendor, analytics application or research report providers, perform due- diligence on vendor shortlisting (RFP reviewing), Data feed implementation inside the banking system, trading/analytics application setup, data-feed migration support, trading/analytics application migration support, writing RFP on end user behalf, etc.</p>
			      <p>FinVendor wishes to help Financial vendors (Data Aggregators, Trading Application Vendor, Financial Analytics Application Vendor, Financial Research Report Providers) in increasing their sales by more effective product offerings and more frequent engagement with their target end users</p>
		    	</div>
		    	<div class="inner-left-wrap" id="SERVICES_dadd">
			      <h1>Data Aggregator Services</h1>
			      <p>In this ever changing and vast financial market data world, one could be a smaller sized player or a medium sized player or a bigger sized player. But all are here with a common goal -
						<br>
						<span style="display: list-item; display:relative; margin-left: 3em">Making one’s presence meaningful in this market.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Drawing the attention of quality customer towards one’s data offerings.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Compete with bigger sized competitor on equal-footing, Gain Market Share and add value for your stakeholders and shareholders.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Minimizing the customer acquisition time as less as possible.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Keeping oneself abreast with current trends in Market-Data world.</span>
					</p>
					<h2>How FinVendor Helps You achieve Your Goal</h2>
					<p>FinVendor provides a platform to list your vendor offerings in most granular form. This platform is being used by Data End Users to search the data vendors for their varied data needs. The more detailed info you provide, the better end users would be able to understand you</p>
					<p>Only End users can access your listing info. None of your competitors would be able to see your data offerings. One of the examples of Data End user requirement</p>
					<p style="background-color:lightgray"><b>Reference Bank</b> is looking for a data vendor who provides Bangladesh Market’s Corporate Bond market data having attributes such as A1, A2, A3, A4, A5, A6, A7, A8, A9 and A10. The vendor should make this file available through FTP at every day after local market closure. Subscription free for this file should not exceed $100 per month. Vendor should have 24x7 support coverage in APAC market hour. Vendor should be in this business since more than 3 years. Vendor’s company should be either LLP or private limited</p>
					<p>Data end user can either come to FinVendor, use its search Market data vendor (aggregator) functionality and search the desired vendor or Data end user can initiate a RFP (Request for Proposal) for this using FinVendor RFP section. Vendor can make its presence felt by listing on FinVendor platform. Once listed, vendor can apply for any open RFPs. Based on the vendor’s response on RFI, vendor will be shortlisted and subsequently, vendor will be contacted by Data End users</p>										
				</div>
		    	<div class="inner-left-wrap" id="SERVICES_tapdd">
			      <h1>Trading Application Services</h1>
			      <p>There are multitudes of trading application vendors across the world. One could be a smaller sized player or a medium sized player or a bigger sized player. But all are here with a common goal -
			      	<br>
					<span style="display: list-item; display:relative; margin-left: 3em">Making one’s presence meaningful in this market.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Drawing the attention of quality customer towards one’s trading application offerings.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Compete with bigger sized competitor on equal-footing, Gain Market Share and add value for your stakeholders and shareholders.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Minimizing the customer acquisition time as less as possible.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Keeping oneself abreast with current trends in trading application world.</span>
				  </p> 
			      <p>FinVendor provides a platform to list your vendor offerings in most granular form. This platform is being used by End Users to search the trading application vendors for their varied trading needs. The more detailed info you provide, the better end users would be able to understand you.</p>
			      <p>Only End users can access your listing info. None of your competitors would be able to see your product offerings. One of the examples of End user requirement</p>
			      <p style="background-color:lightgray"><b>TradeNow</b> bank is expanding its operations and opening a trading desk in South-east Asian Government Bond market. For this purpose, they are looking for a trading application vendor who supports trade execution for Government Bond for South-East Asia regions. Trading application should support other functions of trading - Real Time Quotes, Real Time Financial News, Trade Analytics, Collateral Management, Smart Order Routing (Sequential SOR) execution facility, ‘good for auction’ Order type, Ability to connect to liquidity providers, Trading Strategy Builder, Algorithmic Signal Generation. The vendor should make this application accessible through Binaries (Executable) with dedicated rate, broker and revaluation engines. Underlying software program should be written in open-source programming language. Subscription fee for this application should not exceed $1000 per month. Vendor should have 24x7 support coverage in APAC market hour. Vendor should be in this business since more than 3 years. Vendor’s company should be either LLP or private limited.</p>
			      <p>Trading Application End user can either come to FinVendor, use its search Trading Application Vendor functionality and search the desired vendor or End user can initiate a RFP (Request for Proposal) for this on FinVendor RFP section. Vendor, on the other hand, can make its presence felt by listing on FinVendor platform. Once listed, vendor can apply for any open RFPs. Based on the vendor’s response on RFP, vendor will be shortlisted and subsequently, vendor will be contacted by End users.</p>
		    	</div>
		    	<div class="inner-left-wrap" id="SERVICES_aapdd">
			      <h1>Analytics Application Services</h1>
			      <p>Each new financial regulation is proving to be an opportunity as well as huge challenge for Financial Analytics (such as, Risk Analytics or Trade analytics) application vendors. One could be a smaller sized player or a medium sized player or a bigger sized player. But all are here with a common goal -
			      	<br>
					<span style="display: list-item; display:relative; margin-left: 3em">Making one’s presence meaningful in this market.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Drawing the attention of quality customer towards one’s Financial Analytics application offerings.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Compete with bigger sized competitor on equal-footing, Gain Market Share and add value for your stakeholders and shareholders.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Minimizing the customer acquisition time as less as possible.</span>
					<span style="display: list-item; display:relative; margin-left: 3em">Keeping oneself abreast with current trends in Financial Analytics application world.</span>
				   </p>
				   <p>FinVendor provides a platform to list your vendor offerings in most granular form. This platform is being used by End Users to search the financial analytics application vendors for their varied day-to-day needs. The more detailed info you provide, the better end users would be able to understand you.</p>
				   <p>Only End users can access your listing info. None of your competitors would be able to see your product offerings. One of the examples of End user requirement</p>
				   <p style="background-color:lightgray"><b>HedgeNow</b>, a Macro-strategy based hedge fund has opened its operations few months back. They are looking for a financial analytics application vendor who supports Hedge accounting, collateral margining, historical analytics, stress testing, counterparty risk –CVA, Liquidity risk management, OTC products pricing and valuation facilities, risk reporting. The vendor should make this application accessible through Binaries (Executable) with dedicated rate, broker and revaluation engines. Underlying software program should be written in open-source programming language. Subscription fee for this application should not exceed $1000 per month. Vendor should have 24x7 support coverage in APAC market hour. Vendor should be in this business since more than 3 years. Vendor’s company should be either LLP or private limited.</p>
				   <p>Application End user can either come to FinVendor, use its search Analytics Application Vendor functionality and search the desired vendor or End user can initiate a RFP (Request for Proposal) for this on FinVendor RFP section. Vendor, on the other hand, can make its presence felt by listing on FinVendor platform. Once listed, vendor can apply for any open RFPs. Based on the vendor’s response on RFP, vendor will be shortlisted and subsequently, vendor will be contacted by End users.</p>
		    	</div>
		    	<div class="inner-left-wrap" id="SERVICES_rrpdd">
			      <h1>Research Report Services</h1>
			       <p>The presence of multitudes of research analysts creates a conundrum of some sorts- which analyst’s research report is better than others. One could be a smaller sized player or a medium sized player or a bigger sized player. But all are here with a common goal -
				      <br>
						<span style="display: list-item; display:relative; margin-left: 3em">Making one’s presence meaningful in this market.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Drawing the attention of quality customer towards one’s research coverage.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Compete with bigger sized competitor on equal-footing, Gain Market Share and add value for your stakeholders and shareholders.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Minimizing the customer acquisition time as less as possible.</span>
						<span style="display: list-item; display:relative; margin-left: 3em">Keeping oneself abreast with current trends in financial research world.</span>
				  </p>
			      <p>FinVendor provides a platform to list your vendor offerings in most granular form. This platform is being used by End Users to search the financial research report providers for their varied day-to-day needs. The more detailed info you provide, the better end users would be able to understand you.</p>
			      <p>Only End users can access your listing info. None of your competitors would be able to see your product offerings. One of the examples of End user requirement</p>
			      <p style="background-color:lightgray"><b>MyPortfolio</b>, a fund management house, has launched its new emerging market centric fund few months back. They are looking for a research report providers who performs the Macro economic Analysis, Sector Analysis, Industry Analysis, Commodity Analysis, Exchange Rate Analysis, Interest Rate Analysis, Equity research, Debt Market research, Index research, Fund/ETF research for Indian markets, Indonesian markets, Philippines markets, Vietnam Markets. The vendor should distribute the report in PDF format through email. Subscription fee for this application should not exceed $1000 per month. Vendor should have 24x7 support coverage in APAC market hour. Vendor should be in this business since more than 3 years. Vendor’s company should be either LLP or private limited</p>
			      <p>End user can either come to FinVendor, use its search Research Report Providers functionality and search the desired vendor or End user can initiate a RFP (Request for Proposal) for this on FinVendor RFP section. Vendor, on the other hand, can make its presence felt by listing on FinVendor platform. Once listed, vendor can apply for any open RFPs. Based on the vendor’s response on RFP, vendor will be shortlisted and subsequently, vendor will be contacted by End users.</p>
		    	</div>
		    	<div class="inner-left-wrap" id="RESOURCES">
			      <h1>RESOURCES</h1>
			      <h2>To Be Available soon</h2>
			    </div>
		    	<div class="inner-left-wrap" id="RESOURCES_b">
			      <h1>Brochures</h1>
			      <h2>To Be Available soon</h2>
		    	</div>
		    	<div class="inner-left-wrap" id="RESOURCES_w">
			      <h1>Whitepapers</h1>
			      <h2>To Be Available soon</h2>
		    	</div>
		    	<div class="inner-left-wrap" id="RESOURCES_blgs">
			      <!-- <h1>Blogs</h1>
			      <h2>To Be Available soon</h2> 
			      -->
			      <!-- <script type="text/javascript" src="http://output44.rssinclude.com/output?type=js&amp;id=1074829&amp;hash=90399e3e339b82e23f89699456e75505"></script></div> --><div>
		    	</div>
		    	<div class="inner-left-wrap" id="RESOURCES_cs">
			      <h1>Case Studies</h1>
			      <h2>To Be Available soon</h2>
		    	</div>
		    	<div class="inner-left-wrap" id="RESOURCES_s">
			      <h1>Spotlights</h1>
			      <h2>To Be Available soon</h2>
		    	</div>
		    	<div class="inner-left-wrap" id="About">
			      <h1>About Us</h1>
			      <p>FinVendor acts as a bridge between Financial Vendors and its End Users. Our platform supports 4 different vendor types– Financial Market Data vendors (Aggregators), Financial Trading Application vendors, Financial Analytics application vendors and Financial Research Report providers.</p>
			      <p>Our goal is to provide a common platform to small, medium and large size vendor to showcase their product offerings in a uniform and comprehensive way. This, in term, creates a win-win situation for both financial vendors as well as for End-Users. Financial vendors are given a level -playing field to compete with other vendors on equal-footing. Financial End users find a hassle-free way to choose a right vendor and perform due-diligence on the financial vendor for their product needs. FinVendor founders saw this gap in the financial industry and thus FinVendor idea took its shape.</p>
			      <p><b>Our Target users are Financial Vendors and Financial Institutions or University scholars (we address them as End users).</b></p>
		    	  <p>Among different type of Financial Institutions are Investment banks, Buy-Side Institutions, Sell-side Institutions and University Students or Research faculty</p>
		    	</div>
		    	<div class="inner-left-wrap" id="Advisory">
			      <h1>Advisory Team</h1>
			    </div>
		    	<div class="inner-left-wrap" id="Management">
			      <h1>Management Team</h1>
			      <p>FinVendor has been founded by three individuals having more than 10 years of industry experience at Big League Global I-banks. Founding members possess experience across various vendor types - Reference data vendors, Trading application vendors and Financial analytical application vendors and Research report providers. FinVendor team understands the different nature of financial vendor products that is needed by End Users and the challenges involved in doing the due-diligence process of selecting a right financial vendor.</p>
			    </div>
		    	<div class="inner-left-wrap" id="Vision">
			      <h1>Vision Statement</h1>
			      <p>FinVendor endeavors assiduously to strengthen the ‘Not-So-Strong’ link between vendors and financial end-users and create a win-win situation for both vendors as well as financial institutions.</p>
			     </div>
			     <div class="inner-left-wrap" id="Privacy_Policy">
			      <h1>Privacy Policy</h1>
			      <p><b>What information do we collect?</b></p>
			      <p>We collect information from you when you register on our site.</p>
			      <p>When registering on our site, as appropriate, you may be asked to enter your: name, e-mail address, mailing address or phone number. You may, however, visit our many areas of our website anonymously.</p>	      
			      <br>
			      <p><b>What do we use your information for?</b></p>
			      <p>Any of the information we collect from you may be used in one of the following ways:
			      	<br>
			      	<span style="display: list-item; display:relative; margin-left: 3em">To personalize your experience (your information helps us to better respond to your individual needs)</span>
			      	<span style="display: list-item; display:relative; margin-left: 3em">To improve customer service (your information helps us to more effectively respond to your customer service requests and support needs)</span>
			      	<span style="display: list-item; display:relative; margin-left: 3em">
			      		To process transactions<br>
			      		Your information, whether public or private, will not be sold, exchanged, transferred, or given to any other company for any reason whatsoever, without your consent, other than for the express purpose of delivering the purchased product or service requested			      		
			      	</span>
			      	<span style="display: list-item; display:relative; margin-left: 3em">
			      		To send periodic emails<br>
			      		If you decide to opt-in to our mailing list, you will receive emails that may include company news, updates, related product or service information, etc.<br>		      		
			      		Note: If at any time you would like to unsubscribe from receiving future emails, we include detailed unsubscribe instructions at the bottom of each email.
			      	</span>
			      </p>
			      <p><b>Do we use cookies?</b></p>
			      <p>Yes (Cookies are small files that a site or its service provider transfers to your computers hard drive through your Web browser (if you allow) that enables the sites or service providers systems to recognize your browser and capture and remember certain information</p>
			      <p>We use cookies to understand and save your preferences for future visits and compile aggregate data about site traffic and site interaction so that we can offer better site experiences and tools in the future.</p>
			      <p><b>Do we disclose any information to outside parties?</b></p>
			      <p>We do not sell, trade, or otherwise transfer to outside parties your personally identifiable information. This does not include trusted third parties who assist us in operating our website, conducting our business, or servicing you, so long as those parties agree to keep this information confidential. We may also release your information when we believe release is appropriate to comply with the law, enforce our site policies, or protect ours or others rights, property, or safety. However, non-personally identifiable visitor information may be provided to other parties for marketing, advertising, or other uses.</p>
			      <p><b>Third party links</b></p>
			      <p>Occasionally, at our discretion, we may include or offer third party products or services on our website. These third party sites have separate and independent privacy policies. We therefore have no responsibility or liability for the content and activities of these linked sites. Nonetheless, we seek to protect the integrity of our site and welcome any feedback about these sites.</p>
			      <p><b>Childrens Online Privacy Protection Act Compliance</b></p>
			      <p>We are in compliance with the requirements of COPPA (Childrens Online Privacy Protection Act), we do not collect any information from anyone under 13 years of age. Our website, products and services are all directed to people who are at least 13 years old or older.</p>
			      <p><b>Online Privacy Policy Only</b></p>
			      <p>This online privacy policy applies only to information collected through our website and not to information collected offline.</p>
			      <p><b>Your Consent</b></p>
			      <p>By using our site, you consent to our websites privacy policy.</p>
			      <p><b>Changes to our Privacy Policy</b></p>
			      <p>If we decide to change our privacy policy, we will post those changes on this page.<br>
					This policy was last modified on 04/17/2016
				  </p>
				  <p><b>Contacting Us</b></p>
				  <p>If there are any questions regarding this privacy policy you may contact us using the information below.<br>
				  <a href="mailto:sales@finvendor.com" target="_top">sales@finvendor.com</a><br></p>
			     </div>
			     
			     <div class="inner-left-wrap" id="Disclaimer">
			     <h1>Disclaimer of Warranty: Limitation of Liability</h1>
			      <p>(Request-For-Proposal) with their detailed vendor’s product needs. Such RFPs are made available to all FinVendor listed vendors. End-Users can also track their initiated RFPs, review vendor detailed response and finalize the vendor in the least possible time. Financial vendors (Data Aggregators, Trading Application Vendor, Financial Analytics Application Vendor, Financial Research Report Providers) can list their offerings in the well-researched FinVendor format. Data vendors can also participate in all open RFPs (Request-For-Proposal) initiated by data End-Users.</p>
			     <h1>Restrictions</h1>
			      <p>On FinVendor platform, End-Users(I-banks, Portfolio Managers, Hedge Funds, Research Analyst, University Scholars, etc) can search all available vendors for a certain vendor offerings (Market Data Vendor, Trading Application Vendor, Financial Analytics Application Vendor, Financial Research Report Providers). End-Users can see the granular level info about the vendor product offerings. End-Users can also initiate a RFP</p>
			     </div>
			     <div class="inner-left-wrap" id="CONTACT">
			      <h1>Leave us a message...</h1>
			      	 <br>
				     <div id="container">
					<style type="text/css">
					.contact_loading {
						min-height: 50px;
						background: url(<%=request.getContextPath()%>/resources/images/bx_loader.gif)
							center center no-repeat #fff;
						height: 100%;
						width: 100%;
						position: absolute;
						top: 0;
						left: 0;
						z-index: 2000;
						display: none;
						opacity: 0.6
					}
					</style>
					<form action="<%=request.getContextPath() %>/<%=RequestConstans.MAIL.MAIL_SEND%>" method="post" id="contact_us_form" style="width: 350px"> 
							<div class="form-group">
								<label>Name</label>
								<input type="text" name="contact_us_name" class="form-control" id="contact_us_name" placeholder="Name" size="30"/>
							<span id="contactNameError" style="display: none;">Please enter your Name.</span>
							</div>
							<div class="form-group">
							<label>Phone</label>
								<input type="text" name="contact_us_phone" class="form-control" id="contact_us_phone" placeholder="Phone" size="30"/>
								<span id="contactPhoneError" style="display: none;">Please enter a valid phone number</span>
							</div>
							<div class="form-group">
								<label>Email</label>
								<input type="text" name="contact_us_email" class="form-control" id="contact_us_email" placeholder="Email" size="30"/>
								<span id="contactEmailError" style="display: none;">Please enter a valid email address</span>
							</div>	
							<div class="form-group">
								<label>Message</label>
								<textarea name="contact_us_message" class="form-control" id="contact_us_message" cols="32" rows="5" placeholder="Message" value="Sample Message"></textarea>
							</div>
							<br>
							<input type="button" value="Submit" name="Submit" id="call" class="btn primary"/>
							<div id="loadingct" class="contact_loading"></div>
    							<div id="output"></div>
				  			<!-- <button><a href="javascript:document.getElementById('contact_us_form').submit();">Send</a> </button>  -->	    	
						
				</form> 
					</div>
				</div>		
				<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mail-functions.js"></script>    	
			    <div class="inner-sidebar-wrap">
			      <div class="sidebar-ctn-wrap cnt-ctn-wrap">
			        <div class="head">
			          <h3>PRODUCTS</h3>
			        </div>
			        <div class="content" id="sidelinks">
			          <ul>
			            <li data-div-id="SOLUTIONS"><a href="#">SOLUTIONS</a>
			            	<ul id="SOLUTIONS_ul">
			            		<li data-div-id="SOLUTIONS_mdvad"><a href="#">Market Data Vendors</a></li>
			            		<li data-div-id="SOLUTIONS_tavd"><a href="#">Trading Application Vendors</a></li>
			            		<li data-div-id="SOLUTIONS_faavd"><a href="#">Analytics Application Vendors</a></li>
			            		<li data-div-id="SOLUTIONS_frrpd"><a href="#">Research Report Providers</a></li>
			            	</ul>
			            </li>
			            <li data-div-id="SERVICES"><a href="#">SERVICES</a>
			            	<ul id="SERVICES_ul">
			            		<li data-div-id="SERVICES_dadd"><a href="#">Data Aggregator Services</a></li>
			            		<li data-div-id="SERVICES_tapdd"><a href="#">Trading Application Services</a></li>
			            		<li data-div-id="SERVICES_aapdd"><a href="#">Analytics Application Services</a></li>
			            		<li data-div-id="SERVICES_rrpdd"><a href="#">Research Report Services</a></li>
			            	</ul>
			            </li>
			            <li data-div-id="RESOURCES"><a href="#">RESOURCES</a>
			            	<ul id="RESOURCES_ul">
			            		<li data-div-id="RESOURCES_b"><a href="#">Brochures</a></li>
			            		<li data-div-id="RESOURCES_w"><a href="#">Whitepapers</a></li>
			            		<!-- data-div-id="RESOURCES_blgs" --><li ><a href="i">Blogs</a></li>
			            		<li data-div-id="RESOURCES_cs"><a href="#">Case Studies</a></li>
			            		<li data-div-id="RESOURCES_s"><a href="#">Spotlights</a></li>
			            	</ul>
			            </li>
			          </ul>
			        </div>
			      </div>
			      <div class="sidebar-ctn-wrap cnt-ctn-wrap">
			        <div class="head">
			          <h3>ABOUT</h3>
			        </div>
			        <div class="content" id="sidelinks">
			          <ul>
			            <li data-div-id="About"><a href="#">About Us</a></li>
			            <li data-div-id="Advisory"><a href="#">Advisory Team</a></li>
			            <li data-div-id="Management"><a href="#">Management Team</a></li>
			            <li data-div-id="Vision"><a href="#">Vision Statement</a></li>
			          </ul>
			        </div>
			      </div>
			      <div class="sidebar-ctn-wrap cnt-ctn-wrap">
			        <div class="head">
			          <h3>CONTACT</h3>
			        </div>
			        <div class="content">
			          <ul class="contact">
			            <li class="phone">+65 869 633 21</li>
			            <li class="message"><a href="mailto:enquiry@finvendor.com">enquiry@finvendor.com</a></li>
			            <li class="location"><%--<span>Tech Park Bangalore</span> <span>Whitefield Road Bangalore </span><span>560 066. Karnataka, India.</span>--%></li>
			          </ul>
			        </div>
			      </div>
			       <script>
				    $('.inner-left-wrap').hide();
				    $('#SOLUTIONS_ul').slideUp();
				    $('#SERVICES_ul').slideUp();
				    $('#RESOURCES_ul').slideUp();
				    <c:choose>
				      	<c:when test="${param.subNav != null}">
				      		$('#${param.nav}_${param.subNav}').show();
				      		$('li').filter(function() { return $.text([this]) === '${requestConstants.reqParamDescriptionMap[requestMapKey]}'; }).addClass('li-ico');
				      	</c:when>
				      	<c:otherwise>
				      		$('#${param.nav}').show();
				      		$( "#sidelinks li:contains('${param.nav}')").addClass('li-ico');
				      	</c:otherwise>
				      </c:choose>
				      $('#${param.nav}_ul').slideDown();				      
					</script>  
			    </div>
			    <div class="clearfix"></div>
			  </div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>		
	</body>
	
	
</html>