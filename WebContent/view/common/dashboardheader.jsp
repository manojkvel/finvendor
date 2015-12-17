<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<%@page import="org.springframework.security.core.authority.SimpleGrantedAuthority"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<c:set var="consumerinviteanrfpmarketdataneeds" value="<%=RequestConstans.Consumer.CONSUMER_INVITE_RFP_MARKET_DATA_NEEDS %>"> </c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <link href="<%=request.getContextPath() %>/resources/css/style.css" type="text/css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/resources/css/superfish.css" type="text/css" rel="stylesheet" />
<body>
<%-- <%
String usernameCheck = "";
if(SecurityContextHolder.getContext().getAuthentication() != null){
	usernameCheck = SecurityContextHolder.getContext().getAuthentication().getName();
}else{
}
%> --%>
<div class="wrapper">
	<div class="logo" onclick="homePage();"><img src="<%=request.getContextPath() %>/resources/images/logo-new.png"  style="height: 61px; width: 115px; margin-top: 10px" /></div>
	<div class="area">
	
	   <div class="Rowtableinfovaluserpngbefore">
	   <div class="ColumnCommonrayuserlogoutbefore">
	       <a href="<%=request.getContextPath() %>/logout"><span class="lable_headeractionsuserpng"><img src="<%=request.getContextPath() %>/resources/images/sign-out.png" width="25" height="25"/>Logout</span></a>
	       </div>
	      <div class="ColumnCommonrayuserpngbefore">
	       <a href="#normalModal"><span class="lable_headeractionsuserpng"><img src="<%=request.getContextPath() %>/resources/images/user.png" width="30" height="30"/> Welcome <c:out value="${fn:toUpperCase(username)}" /></span></a>
	       </div>
	   </div> 
	</div>
	<div class="area_1">
	<div class="ic">
	<%-- <img src="<%=request.getContextPath() %>/resources/singleasset/images/fn.png" width="19" height="19" /> --%></div>
	  <!-- <div class="ic_text"><span><a href="#">sdfsadfsadfasdfsadf</a></span> 
	</div> -->
	<c:choose>
				<c:when test="${not empty username || not empty myusername}"></c:when>
				<c:otherwise>
				  	 <div class="ic"><img src="${pageContext.request.contextPath}/resources/images/fn.png" alt="" title=""/><i>FINANCIAL VENDOR?</i></div> 
					<div class="ic_text"><a class="cd-signin" href="${pageContext.request.contextPath}/#0">LIST YOUR OFFERINGS</a></div>
				</c:otherwise>
			</c:choose>
	  <%-- <div class="ic"> <img src="<%=request.getContextPath() %>/resources/singleasset/images/msg.png" width="20" height="13" /></div>
	<div class="ic_text">CONTACT</div>     --%>
	</div>
	
	<!-- Main Tabs Starts  -->
	<div class="nav-srch" id="nav-srch" style="padding: 79px 0 18px 0;">
	
	<div class="nav-srch-cnt">
		<ul class="sf-menu" id="example">
			<li onclick="homePage();"><img src="<%=request.getContextPath()%>/resources/images/hm.png" alt="" title=""/></li>
			<%-- <li><a href="${pageContext.request.contextPath}/view/index.jsp?RaYUnA=${l:encrypt(username)}" style="padding: 6px 2px; font-size: 11px;">My Home</a></li> --%>
			<li><a href="#" style="padding: 6px 30px;">SOLUTIONS</a>
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
			<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES" style="padding: 6px 30px;">SERVICES</a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=dadd">Data Aggregator Due diligence</a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=tapdd">Trading Application providers due diligence</a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=aapdd">Analytics Application providers due diligence</a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=rrpdd">Research Report Providers due diligence</a></li>
					<%-- <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=ic">IT Consulting</a></li> --%>
				</ul>
			</li>
			<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES" style="padding: 6px 30px;">RESOURCES</a>
       			<ul>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=b">Brochures</a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=w">Whitepapers</a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=blgs">Blogs</a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=cs">Case Studies</a></li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=s">Spotlights</a></li>
				</ul>
			</li>
			 
					<li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_RFP_INBOX%>?RaYUnA=${l:encrypt(username)}" style="padding: 6px 30px;">RFP Inbox </a></li>
					<li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER%>?RaYUnA=${l:encrypt(username)}" style="padding: 6px 30px;">Search Data-Buyers</a></li>
			 
		</ul>
		<!-- <input name="" type="text" class="srch" value="Search"> -->
		<div class="clearfix"></div>
	</div>
</div>
	
	<!--  Vendor Dashboard Tabs--- -->
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
	<!-- Consumer Dashboard info--- -->
	
	<c:if test="${not empty consumerMyProfiletab }">
	 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_PROFILE%>?RaYVeMu=${l:encrypt(username)}">My Profile</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
	</c:if>
	
	<c:if test="${not empty consumerMyOfferingstab }">
	 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_MY_OFFERINGS%>?RaYVeMu=${l:encrypt(username)}">My Offerings</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
	</c:if>
	<c:if test="${not empty consumerInviteAnRFP }">
	 <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP%>?RaYVeMu=${l:encrypt(username)}" onclick="consumerInviteRFPModes('${consumerinviteanrfpmarketdataneeds}');">Invite An RFP</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
	</c:if>
	<div class="right_nav_area">
		  <div class="right_nav">  
		 <ul>
		 <!-- Vendor Dashboard tab's -->
		  <c:if test="${not empty myprofiletab }">
		   <li><a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}">My Profile</a></li>
		   <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>?RaYUnA=${l:encrypt(username)}">My Offerings</a></li>
		   <li><a class="#" href="#">My Stats</a></li>
		   <li><a class="#" href="#">My Blog</a></li>
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