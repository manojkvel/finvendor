<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<%@page import="org.springframework.security.core.authority.SimpleGrantedAuthority"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<c:set var="consumerinviteanrfpmarketdataneeds" value="<%=RequestConstans.Consumer.CONSUMER_INVITE_RFP_MARKET_DATA_NEEDS %>"> </c:set>
<c:set var="myoffering" value="<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>"> </c:set>
<c:set var="solution" value="<%=RequestConstans.Vendor.VENDOR_SOLUTION%>"> </c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="head.jsp"></jsp:include>
<body>
<%-- <%
String usernameCheck = "";
if(SecurityContextHolder.getContext().getAuthentication() != null){
	usernameCheck = SecurityContextHolder.getContext().getAuthentication().getName();
}else{
}
%> --%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<script>	
	$(document).ready(function() {		
		$(".account").click(function() {
			var X=$(this).attr('id');
			if(X==1) {
				$(".profilepicsubmenu").hide();
				$(this).attr('id', '0');
			}else {
				$(".profilepicsubmenu").show();
				$(this).attr('id', '1');
			}
		});
			
		$(".profilepicsubmenu").mouseup(function() {
				return false
		});
			
		$(".account").mouseup(function() {
			return false
		});
			
		$(document).mouseup(function() {
			$(".profilepicsubmenu").hide();
			$(".account").attr('id', '');
		});
	});
</script>
<div class="header-container">
	<div class="container">
		<div class="header">
			<a href="#" onclick="homePage();" class="logo">
				<img src="${pageContext.request.contextPath}/resources/images/icon-logo.jpg" alt="FinVendor" title="FinVendor" onclick="homepage();"/>
			</a>
			<div class="pull-right contact-detail">
				<p>
					<c:if test="${sessionScope.loggedInUser == null }">
						<i class="fa fa-pencil"></i> Financial Vendor? 
						<a class="link" href="#">List your offerings</a>
					</c:if>
				</p>
				<c:choose>
					<c:when test="${sessionScope.loggedInUser != null }">
						<ul>
					</c:when>
					<c:otherwise>
						<ul class="hd-right">
					</c:otherwise>
				</c:choose>
					<c:choose>
						<c:when test="${sessionScope.loggedInUser != null }">
							<div class="dropdown">
								<a href="#" class="account">
									<img src="${pageContext.request.contextPath}/displayCompanyLogo/${sessionScope.loggedInUser.username}" class="profile-circle" border="0"/>
								</a>
								<div class="profilepicsubmenu" style="display:none">
									<ul class="root">
										<%-- 
										<li><a href="#">Settings</a></li>
										--%>
										<li><a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${sessionScope.loggedInUser.username}">Profile</a></li>
										<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>	
									</ul>
								</div>
							</div>												
						</c:when>
						<c:otherwise>
							<li><a class="cd-signin" href="#"><i class="fa fa-user"></i> Login</a></li>
							<li><a class="cd-signup" href="#"><i class="fa fa-user-plus"></i> Register</a></li>
							<li><span><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=CONTACT"><i class="fa fa-envelope"></i> Contact</a></span></li> 
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</div>
	<!-- Main Tabs Starts  -->
<div class="nav-container">
	<div class="nav-srch" id="nav-srch">
		<div class="container">
			<div class="nav-srch-cnt">
				<ul class="sf-menu" id="example">
					<li><a href="#" onclick="homePage();"><img src="${pageContext.request.contextPath}/resources/images/hm.png" class="fa fa-home"></img></a></li>
					<%-- <li><a href="${pageContext.request.contextPath}/view/index.jsp?RaYUnA=${l:encrypt(username)}" style="padding: 6px 2px; font-size: 11px;">My Home</a></li> --%>
					<li><a href="#">SOLUTIONS</a>
						<ul>
							<%-- <li><a
								href="<%=request.getContextPath()%>/<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>?RaYUnA=${l:encrypt(username)}">Market
									Data Vendors (Aggregators) Directory</a></li>
							<li><a href="<%=request.getContextPath()%>/<%=RequestConstans.TradingApplication.TRADING_APPLICATION_INDEX_PAGE%>?RaYUnA=${l:encrypt(username)}">Trading Application Vendors Directory</a></li> --%>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=mdvad&RaYUnA=${l:encrypt(username)}">Market Data Vendors (Aggregators) Directory</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=tavd&RaYUnA=${l:encrypt(username)}">Trading Application Vendors Directory</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=faavd&RaYUnA=${l:encrypt(username)}">Financial Analytics Application Vendors Directory</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=frrpd&RaYUnA=${l:encrypt(username)}">Financial Research Report Providers Directory</a></li>
							<%-- <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=afvd&RaYUnA=${l:encrypt(username)}">Advanced Financial Vendors Directory</a></li> --%>
						</ul>
				  </li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES">SERVICES</a>
						<ul>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=dadd">Data Aggregator Due diligence</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=tapdd">Trading Application providers due diligence</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=aapdd">Analytics Application providers due diligence</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=rrpdd">Research Report Providers due diligence</a></li>
							<%-- <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=ic">IT Consulting</a></li> --%>
						</ul>
					</li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES">RESOURCES</a>
		       			<ul>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=b">Brochures</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=w">Whitepapers</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=blgs">Blogs</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=cs">Case Studies</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=s">Spotlights</a></li>
						</ul>
					</li>
					 
							<li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_RFP_INBOX%>?RaYUnA=${l:encrypt(username)}">RFP Inbox </a></li>
							<li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER%>?RaYUnA=${l:encrypt(username)}">Search Data-Buyers</a></li>
					 
				</ul>
				<!-- <input name="" type="text" class="srch" value="Search"> -->
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>
	<!--  Vendor Dashboard Tabs--- -->
	
<div class="inner-breadcrumb">
<c:if test="${not empty myprofiletab }">
		  <div class="container" id="vd_breadcrumb">
			  <c:choose>
				  <c:when test="${breadcrum eq solution}">
				    <ul>
					   <li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_SOLUTION%>?RaYUnA=${l:encrypt(username)}">Solution</a></li>			      
					</ul>
				  </c:when>
				  <c:when test="${breadcrum eq myoffering}">
				    		    <ul>
							      <li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>?RaYUnA=${l:encrypt(username)}">My Offerings</a></li>	
							      <li class="li-spr">></li>		      
							      <li>As Data Aggregator vendor</li>
							      <li class="li-spr">></li>		      
							      <li>Data Dictionary</li>		      
							    </ul>
					
				  </c:when>
				  <c:otherwise>
				    		    <ul>
							      <li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}">My Profile</a></li>			      
							      		<li class="li-spr">></li>		      
							      		<li>Personal Details</li>
							    </ul>
					
				  </c:otherwise>
			</c:choose>
	  </div>
	</c:if> 
</div>
	
<!-- 	
<div class="bread-crumb-contatiner">
	<div class="container">
		<c:if test="${not empty myprofiletab }">
		 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}">My Profile</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
		</c:if>  
		<c:if test="${not empty myofferingstab }">
		 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>?RaYUnA=${l:encrypt(username)}">My Offerings</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
		</c:if>
		<c:if test="${not empty RFPInbox}">
		 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_RFP_INBOX%>?RaYUnA=${l:encrypt(username)}">RFP Inbox</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
		</c:if>
		<c:if test="${not empty searchDataBuyers}">
		 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER%>?RaYUnA=${l:encrypt(username)}">Search Data-Buyers</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
		</c:if>
		
		<c:if test="${not empty consumerMyProfiletab }">
		 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_PROFILE%>?RaYVeMu=${l:encrypt(username)}">My Profile</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
		</c:if>
		
		<c:if test="${not empty consumerMyOfferingstab }">
		 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_OFFERINGS%>?RaYVeMu=${l:encrypt(username)}">My Offerings</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
		</c:if>
		<c:if test="${not empty consumerInviteAnRFP }">
		 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Invite An RFP</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
		</c:if>
	</div>
</div>
 -->

<div class="tab-container">
	<div class="container">
			<div class="tab-navigation">  
		 		<ul>
					<!-- Vendor Dashboard tab's -->
					<c:if test="${not empty myprofiletab }">
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}" id="myProfile" onclick="changeTabMode(this)" >My Profile</a></li>
						 <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_SOLUTION%>?RaYUnA=${l:encrypt(username)}" id="solution" onclick="changeTabMode(this)" >Solution</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>?RaYUnA=${l:encrypt(username)}" id="myOfferings" onclick="changeTabMode(this)">My Offerings</a></li>
						<li><a class="#" href="${pageContext.request.contextPath}/vendorMyStats" id="myStats" onclick="changeTabMode(this)" >My Stats</a></li>
						<li><a class="#" href="${pageContext.request.contextPath}/vendorMyBlogs" id="myBlog" onclick="changeTabMode(this)">My Blog</a></li>
					</c:if>
					<c:if test="${not empty myofferingstab }">
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}">My Profile</a></li>
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>?RaYUnA=${l:encrypt(username)}">My Offerings</a></li>
						<li><a class="#" href="#">My Stats</a></li>
						<li><a class="#" href="#">My Blog</a></li>
					</c:if>
					<c:if test="${not empty RFPInbox}">
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_RFP_INBOX%>?RaYUnA=${l:encrypt(username)}">RFP Inbox</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}">My Profile</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>?RaYUnA=${l:encrypt(username)}">My Offerings</a></li>
						<li><a class="#" href="#">My Stats</a></li>
						<li><a class="#" href="#">My Blog</a></li>
					</c:if>
					<c:if test="${not empty searchDataBuyers}">
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER%>?RaYUnA=${l:encrypt(username)}">Search Data-Buyers</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}">My Profile</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>?RaYUnA=${l:encrypt(username)}">My Offerings</a></li>
						<li><a class="#" href="#">My Stats</a></li>
						<li><a class="#" href="#">My Blog</a></li>
					</c:if>
			<!-- Consumer Dashboard tab's -->
			
					<c:if test="${not empty consumerMyProfiletab }">
						 <li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_PROFILE%>?RaYVeMu=${l:encrypt(username)}">My profile</a></li>
						 <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_OFFERINGS%>?RaYVeMu=${l:encrypt(username)}">My Subscription</a></li>
						 <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Invite an RFP</a></li>
						 <li><a class="#" href="#">Search vendors</a></li>
						 <li><a class="#" href="#">Newsletters & Alerts</a></li>
						 <li><a class="#" href="#">My Blog</a></li>
						 <li><a class="#" href="#">My History</a></li>
						 <li><a class="#" href="#">My Statistics</a></li>
						 <li><a class="#" href="#">Invite Your team</a></li>
					</c:if>
					<c:if test="${not empty consumerMyOfferingstab }">
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_PROFILE%>?RaYVeMu=${l:encrypt(username)}">My profile</a></li>
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_OFFERINGS%>?RaYVeMu=${l:encrypt(username)}">My Subscription</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Invite an RFP</a></li>
						<li><a class="#" href="#">Search vendors</a></li>
						<li><a class="#" href="#">Newsletters & Alerts</a></li>
						<li><a class="#" href="#">My Blog</a></li>
						<li><a class="#" href="#">My History</a></li>
						<li><a class="#" href="#">My Statistics</a></li>
						<li><a class="#" href="#">Invite Your team</a></li>
					</c:if>
					<c:if test="${not empty consumerInviteAnRFP }">
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_PROFILE%>?RaYVeMu=${l:encrypt(username)}">My profile</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_OFFERINGS%>?RaYVeMu=${l:encrypt(username)}">My Subscription</a></li>
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Invite an RFP</a></li>
						<li><a class="#" href="#">Search vendors</a></li>
						<li><a class="#" href="#">Newsletters & Alerts</a></li>
						<li><a class="#" href="#">My Blog</a></li>
						<li><a class="#" href="#">My History</a></li>
						<li><a class="#" href="#">My Statistics</a></li>
						<li><a class="#" href="#">Invite Your team</a></li>
					</c:if>
					<c:if test="${not empty consumerInviteAnRFP && not empty consumermarketdataneedsInviteAnRFP }">
						<br/><br/>
						 <li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MARKET_DATANEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Market Data Needs</a></li>
						 <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_TRADING_APPLICATION_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Trading Application Needs</a></li>
						 <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_ANALYTICS_APPLICATION_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Analytics Application Needs</a></li>
						 <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_RESEARCG_REPORT_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Research Report Needs</a></li>
					</c:if>
		   
					<c:if test="${not empty consumerInviteAnRFP && not empty consumertradingapplicationInviteAnRFP }">
						<br/><br/>
						 <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MARKET_DATANEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Market Data Needs</a></li>
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_TRADING_APPLICATION_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Trading Application Needs</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_ANALYTICS_APPLICATION_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Analytics Application Needs</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_RESEARCG_REPORT_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Research Report Needs</a></li>
					</c:if>
		   
					<c:if test="${not empty consumerInviteAnRFP && not empty consumeranalyticsapplicationInviteAnRFP }">
						<br/><br/>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MARKET_DATANEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Market Data Needs</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_TRADING_APPLICATION_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Trading Application Needs</a></li>
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_ANALYTICS_APPLICATION_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Analytics Application Needs</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_RESEARCG_REPORT_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Research Report Needs</a></li>
					</c:if>
					
					<c:if test="${not empty consumerInviteAnRFP && not empty consumerresearchreportInviteAnRFP }">
						<br/><br/>
						 <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MARKET_DATANEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Market Data Needs</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_TRADING_APPLICATION_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}">Trading Application Needs</a></li>
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_ANALYTICS_APPLICATION_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Analytics Application Needs</a></li>
						<li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_RESEARCG_REPORT_NEEDS_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Research Report Needs</a></li>
					</c:if>
				</ul>
			</div> 
	</div>
</div>
	<script type="text/javascript">
  function userCheck(checktype){
		 if(checktype.match('logout') && checktype != ''){
			 window.location.href  = "<%= request.getContextPath()%>/<%=RequestConstans.Login.LOGOUT%>";
		 }else{
			 window.location.href  = "<%= request.getContextPath()%>/<%=RequestConstans.Login.FORGET%>"; 
		 }
	} 
  function homePage(){
	  window.location.href  = "<%= request.getContextPath()%>/<%=RequestConstans.Login.MY_HOME_PAGE%>?RaYUnA=${l:encrypt(username)}";
  }

  
  
	</script>
</body>
</html>