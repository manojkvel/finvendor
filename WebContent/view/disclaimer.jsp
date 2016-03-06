<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
	<head>
		<title>FinVendor|Privacy</title>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/reset.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/resources/css/jquery.bxslider.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/superfish.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/tab.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet"/>
		<!-- being referenced - no file found <link href="${pageContext.request.contextPath}/resources/newsingleasset/css/main.css" rel="stylesheet"/> -->
		<script src="${pageContext.request.contextPath}/resources/js/CreateHTML5Elements.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>
		<!-- being referenced - no file found<script src="${pageContext.request.contextPath}/resources/newlogin/js/superfish.js--></script>
		
		<!-- <script>
			
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
						$('#breadcrumb_text').html('<ul><li><a href="${finVen:resolveContextPath(pageContext.request.contextPath)}">HOME</a></li>' + checkElement);
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
					
		</script>	 -->
	</head>
	<%-- <c:set var="username" value="${finVen:decrypt(param.RaYUnA)}"></c:set> --%>
	<body>
		<div class="container">
			<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		</div>
		<jsp:include page="login.jsp"></jsp:include>
			<%-- <div class="inner-breadcrumb">
			  <div class="container" id="breadcrumb_text">
			     <ul>
			      <li><a href="${finVen:resolveContextPath(pageContext.request.contextPath)}">HOME</a></li>
			      <p></p>			      
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
			      		<li>${param.nav}</li>
			      	</c:otherwise>
			      </c:choose> 		      				      
			    </ul> -
			    <div class="clearfix"></div>
			    
			  </div>
			</div> --%>
			<div class="container">
			  
			  	<div class="left-content">
			      <h1> <strong>Restrictions</strong></h1>
			      <p style="font-family:initial;font-style: normal;">
			     
			     <br>
					On FinVendor platform, End-Users(I-banks, Portfolio Managers, Hedge Funds, Research
			       Analyst, University Scholars, etc) can search all available vendors for a certain vendor 
			       offerings (Market Data Vendor, Trading Application Vendor, Financial Analytics
			        Application Vendor, Financial Research Report Providers). End-Users can see the granular
			         level info about the vendor product offerings. End-Users can also initiate a RFP
			        </p><br>
			         <h1> <strong>Privacy Policy</strong></h1><br>
			      <p style="font-family:initial;font-style: normal;">
			       (Request-For-Proposal) with their detailed vendor’s product needs. Such RFPs are made available to all FinVendor listed vendors. End-Users can also track their initiated RFPs, 
			      review vendor detailed response and finalize the vendor in the least possible time.
			      Financial vendors (Data Aggregators, Trading Application Vendor, Financial Analytics Application Vendor,
			       Financial Research Report Providers) can list their offerings in the well-researched FinVendor format.
			        Data vendors can also participate in all open RFPs (Request-For-Proposal) initiated by data End-Users.
			        </p><br>
			        <h1><strong>Disclaimer of Warranty: Limitation of Liability
			        </strong></h1><br>
			        <p style="font-family:initial;font-style: normal;">
			       (Request-For-Proposal) with their detailed vendor’s product needs. Such RFPs are made available to all FinVendor listed vendors. End-Users can also track their initiated RFPs, 
			      review vendor detailed response and finalize the vendor in the least possible time.
			      Financial vendors (Data Aggregators, Trading Application Vendor, Financial Analytics Application Vendor,
			       Financial Research Report Providers) can list their offerings in the well-researched FinVendor format.
			        Data vendors can also participate in all open RFPs (Request-For-Proposal) initiated by data End-Users.
			        </p>
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
			            		<li data-div-id="RESOURCES_blgs"><a href="#">Blogs</a></li>
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
			            <li data-div-id="About"><a href="#">About You</a></li>
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
			            <li class="phone">+91 000 000 0000</li>
			            <li class="message"><a href="mailto:info@finvendor.com">info@finvendor.com</a></li>
			            <li class="location"><span>Tech Park Bangalore</span> <span>Whitefield Road Bangalore </span><span>560 066. Karnataka, India.</span> </li>
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
		<div class="container">
			<jsp:include page="common/footer.jsp"></jsp:include>
		</div>		
	</body>	
</html>