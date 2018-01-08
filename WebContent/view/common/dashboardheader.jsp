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
<header>
	<div class="container-fluid">
		<div class="row">
			
					<div class="header">
						<a href="/" onclick="homePage()" class="logo"> <img
							src="${pageContext.request.contextPath}/resources/images/company-logo-header.jpg"
							alt="FinVendor" title="FinVendor" onclick="homepage()"
							align="middle" /> 
							<span class="hide">Democratizing The World Of Financial Vendors.</span>
						</a>
						 <nav>
                                        <div class="header-nav">
										<div class="pull-left">
                                                <div class="nav-container">
                                                    <div class="nav-srch">
                                                        <div class="nav-srch-cnt ">

                                                            <ul class="sf-menu hidden-xs" id="example">
																<li>
                                                                        <a href="#">Research Reports</a>
                                                                        <ul>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportTyepe=Equity/Company Research">
                                                                                    Equity/Company Research
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportTyepe=Sector Research">
                                                                                    Sector Research
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportTyepe=Macro Research">
                                                                                    Macro Research
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportTyepe=Debt Mkt Research">
                                                                                    Debt Mkt Research
                                                                                </a>
                                                                            </li>
                                                                            <li>
                                                                                <a href="${pageContext.request.contextPath}/view/equity_research_report_vendor.jsp?researchReportTyepe=Fund/ETF Research">
                                                                                    Fund/ETF Research
                                                                                </a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>
                                                                    <li>
                                                                        <a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=Brokers_Analysts">Brokers/Analysts</a>
                                                                    </li>
                                                                <!--<li><a href="#">SOLUTIONS</a>
                                                                    <ul>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=mdvad&RaYUnA=${l:encrypt(username)}">Market
														Data Vendors</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=tavd&RaYUnA=${l:encrypt(username)}">Trading
															Application Vendors</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=faavd&RaYUnA=${l:encrypt(username)}">Analytics
																Application Vendors</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=frrpd&RaYUnA=${l:encrypt(username)}">Research
																	Report Providers</a>
                                                                        </li>
                                                                    </ul>
                                                                </li>
                                                                <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES">SERVICES</a>
                                                                    <ul>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=dadd">Data
																			Aggregator Services</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=tapdd">Trading
																				Application Services</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=aapdd">Analytics
																					Application Services</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=rrpdd">Research
																						Report Services</a>
                                                                        </li>
                                                                    </ul>
                                                                </li>-->
                                                                <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE">More</a>
                                                                    <ul>

                                                                        <c:if test="${sessionScope.loggedInRole == 'ROLE_CONSUMER'}">
                                                                            <li><a href="${pageContext.request.contextPath}/<%=RequestConstans.Vendor.VENDOR_RFP_INBOX%>">RFP
																								Inbox </a>
                                                                            </li>
                                                                            <li><a href="${pageContext.request.contextPath}/<%=RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER%>">Search
																								Vendors</a>
                                                                            </li>
                                                                        </c:if>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE&subNav=b">Brochures</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE&subNav=w">Whitepapers</a>
                                                                        </li>

                                                                        <li>
                                                                            <a target="_blank" href="http://blog.finvendor.com">Blogs</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE&subNav=cs">Case
																							Studies</a>
                                                                        </li>
                                                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE&subNav=s">Spotlights</a>
                                                                        </li>
                                                                    </ul>
                                                                </li>
                                                                <li class="user_activity">
                                                                    <c:choose>
                                                                        <c:when test="${sessionScope.loggedInUser != null }">
                                                                    <a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${sessionScope.loggedInUser.username}">
                                                                        <img src="${pageContext.request.contextPath}/displayCompanyLogo/${sessionScope.loggedInUser.username}" class="profile-circle" border="0" />
                                                                        <span>${sessionScope.loggedInUser.username}</span>
                                                                    </a>
                                                                    <ul>
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/welcometodashboards" id='my_account'>My Profile</a>
                                                                        </li>
                                                                        <li>
                                                                            <a href="#" id='my_subscription'>Subscribe</a>
                                                                        </li>
                                                                        <li>
                                                                            <a href="#" id='my_portfolio'>My Portfolio</a>
                                                                        </li>
                                                                        <!--<li>
                                                                        	<a class="settings" href="${pageContext.request.contextPath}/displayAccountSettings?userName=${sessionScope.loggedInUser.username}">Settings</a>
                                                                        </li>-->
                                                                        <li>
                                                                            <a href="${pageContext.request.contextPath}/logout" id="logout-confirm">Logout</a>
                                                                        </li>
                                                                    </ul>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                    <div class="hd-right">
                                                                        <a class="cd-signin" href="#">
                                                                            <i class="fa fa-user"></i> Login
                                                                        </a>
                                                                    </div>
                                                                    </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                            </ul>
                                                            
                                                             <ul class="sf-menu visible-xs">
                                                                <li>
                                                                    <a id="sidemenu" title="Menu" class="mobile_menu" href="javascript:void(0);">
                                                                        <i class="fa fa-navicon"></i>
                                                                    </a>
                                                                </li>
                                                             </ul>
                                                            </div>
                                                            <script type="text/javascript">
                                                                function userCheck(checktype) {
                                                                    if (checktype.match('logout') && checktype != '') {
                                                                        window.location.href = "${pageContext.request.contextPath}/<%=RequestConstans.Login.LOGOUT%>";
                                                                    } else {
                                                                        window.location.href = "${pageContext.request.contextPath}/<%=RequestConstans.Login.FORGOT_PASSWORD%>";
                                                                    }
                                                                }

                                                                function homePage() {
                                                                    window.location.href = "${pageContext.request.contextPath}/<%=RequestConstans.Login.MY_HOME_PAGE%>?RaYUnA=${l:encrypt(myusername)}";
                                                                }
                                                            </script>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div id="fv_sub_header">
			                                        <form action="#" class="elastic-search desktop_variant" method="POST">
			                                                <div class="input-group">
			                                                    <input id="txtSearchBox" class="form-control" autocomplete="off" name="query" type="text" placeholder="Free text hint company name">
			                                                    <input type="hidden" name="form_submitted_from_search" value="1">
			                                                    <div class="input-group-addon"><span class="glyphicon glyphicon-search"><input class="submit-button" type="submit"></span></div>
			                                                    <div class="input-group-addon close-btn"><span class="glyphicon glyphicon-remove"><a href="javascript:;"></a></span></div>
			                                                </div>
			                                                <div class="suggestions" style="display:none" id="tblSuggestions">
			                                                    <div id="tableBodyAutocomplete">
			                                                    </div>
			                                                </div>
			                                            </form>
			                                    </div>
                                                <div class="clearfix"></div>
                                        </div>
                          </nav>
					</div>
		</div>
	</div>
</header>

                    <nav class="side-menu">
                        <ul class="side-menu-list">
                            <li class="user_activity">
                                <c:choose>
                                    <c:when test="${sessionScope.loggedInUser != null }">
                                        <a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${sessionScope.loggedInUser.username}" class="account">
                                            <img src="${pageContext.request.contextPath}/displayCompanyLogo/${sessionScope.loggedInUser.username}" class="profile-circle" border="0" />
                                            <span title="${sessionScope.loggedInUser.username}">${sessionScope.loggedInUser.username}</span>

                                            <i class="fa fa-chevron-down"></i>
                                        </a>
                                        <ul class="child-main-menu">
                                            <li><a href="${pageContext.request.contextPath}/welcometodashboards" id='my_account'>My Dashboard</a>
                                            </li>
                                            <li><a class="settings" href="${pageContext.request.contextPath}/displayAccountSettings?userName=${sessionScope.loggedInUser.username}">Settings</a></h4>
                                            </li>
                                            <li><a href="${pageContext.request.contextPath}/logout" id="logout-confirm">Logout</a>
                                            </li>
                                        </ul>
                                        </c:when>
                                        <c:otherwise>
                                        <div class="hd-right">
                                            <a class="cd-signin" href="#"> 
                                                Login
                                                <i class="fa fa-user"></i>
                                            </a>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li>
                                <a href="#">SOLUTIONS <i class="fa fa-chevron-down"></i></a>
                                <ul class="child-main-menu">
                                    <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=mdvad&RaYUnA=${l:encrypt(username)}">Market
                                        Data Vendors</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=tavd&RaYUnA=${l:encrypt(username)}">Trading
                                        Application Vendors</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=faavd&RaYUnA=${l:encrypt(username)}">Analytics
                                        Application Vendors</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=frrpd&RaYUnA=${l:encrypt(username)}">Research
                                        Report Providers</a>
                                    </li>
                                </ul>
                            </li>           
                            <li>
                                <a href="javascript:void(0);">SERVICES <i class="fa fa-chevron-down"></i></a>
                                <ul class="child-main-menu">
                                    <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=dadd">Data
                                        Aggregator Services</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=tapdd">Trading
                                        Application Services</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=aapdd">Analytics
                                        Application Services</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=rrpdd">Research
                                        Report Services</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="javascript:void(0);">MORE <i class="fa fa-chevron-down"></i></a>
                                <ul class="child-main-menu">
                                    <c:if test="${sessionScope.loggedInRole == 'ROLE_CONSUMER'}">
                                        <li><a href="${pageContext.request.contextPath}/<%=RequestConstans.Vendor.VENDOR_RFP_INBOX%>">RFP
                                                        Inbox </a>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/<%=RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER%>">Search
                                                        Vendors</a>
                                        </li>
                                    </c:if>
                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE&subNav=b">Brochures</a>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE&subNav=w">Whitepapers</a>
                                        </li>

                                        <li>
                                            <a target="_blank" href="http://blog.finvendor.com">Blogs</a>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE&subNav=cs">Case
                                        Studies</a>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=MORE&subNav=s">Spotlights</a>
                                        </li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                    <div class="screen-block-hamburger"></div>
                    <script type="text/javascript">
                        $(document).ready(function() {
                            $('.screen-block-hamburger').on('click', function() {
                                $('.screen-block-hamburger').hide();
                                $('.side-menu').removeClass('side-menu-open');
                            });

                            $('#sidemenu').click(function() {
                                $('.screen-block-hamburger').show();
                                $('.side-menu').addClass('side-menu-open');
                            });

                            $('.side-menu-list li').on('click', function() {
                                if($(this).hasClass('active')) {
                                    $(this).removeClass('active');
                                } else {
                                    $(this).addClass('active');
                                }
                            });
                        });
                    </script>

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
			<div class="tab-navigation">  
		 		<ul>
					<!-- Vendor Dashboard tab's -->
					<c:if test="${not empty myprofiletab }">
						<li>
                            <a class="active" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}" id="myProfile" onclick="changeTabMode(this)">
                                <span>My Profile</span>
                            </a>
                        </li>
						 <!-- <li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_SOLUTION%>?RaYUnA=${l:encrypt(username)}" id="solution" onclick="changeTabMode(this)" >Solution</a></li> -->
						<li><a class="#" href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_OFFERINGS%>?RaYUnA=${l:encrypt(username)}" id="myOfferings" onclick="changeTabMode(this)"><span>My Offerings</span></a></li>
						<li><a class="#" href="${pageContext.request.contextPath}/vendorMyStats" id="myStats" onclick="changeTabMode(this)" ><span>My Stats</span></a></li>
						<li><a class="#" href="${pageContext.request.contextPath}/vendorMyBlogs" id="myBlog" onclick="changeTabMode(this)"><span>My Blog</span></a></li>
                        <li style="display: none;"><a class="#" href="${pageContext.request.contextPath}/vendorMyRFP" id="myRfp" onclick="changeTabMode(this)"><span>My RFP</span></a></li>
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
	<script type="text/javascript">
  function userCheck(checktype){
		 if(checktype.match('logout') && checktype != ''){
			 window.location.href  = "<%= request.getContextPath()%>/<%=RequestConstans.Login.LOGOUT%>";
		 }else{
			 window.location.href  = "<%= request.getContextPath()%>/<%=RequestConstans.Login.FORGOT_PASSWORD%>"; 
		 }
	} 
  function homePage(){
	  window.location.href  = "<%= request.getContextPath()%>/<%=RequestConstans.Login.MY_HOME_PAGE%>?RaYUnA=${l:encrypt(username)}";
  }

  
  
	</script>
</body>
</html>