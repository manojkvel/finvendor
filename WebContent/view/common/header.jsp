<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>
<jsp:include page="head.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
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
<c:set var="username" value="${finVen:decrypt(param.RaYUnA)}"></c:set>
<c:set var="myusername" value="${myusername}"></c:set>
<div class="header-container">
	<div class="container">
		<div class="header"> 
		<a href="#" onclick="homePage()" class="logo">
				<img src="${pageContext.request.contextPath}/resources/images/icon-logo.jpg" alt="FinVendor" title="FinVendor" onclick="homepage()" align="center"/>
			Democratizing The World Of Financial Vendors.
			</a>
		
			
			<div class="pull-right contact-detail">
				<p>
					<c:if test="${sessionScope.loggedInUser == null }">
						<i class="fa fa-pencil"></i> Financial Vendor? 
						<a class="link" href="javascript:inner_login('LIY')">List your offerings</a>
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
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<div class="nav-container">
	<div class="nav-srch">
		<div class="container">
			<div class="nav-srch-cnt ">
				<%-- 
				<form class="pull-right">
					<div class="form-group">
						<input name="" type="text" class="srch form-control" placeholder="Search">
					</div>
				</form>
				--%>
				<ul class="sf-menu" id="example">
					<li><a href="#" onclick="homePage();"><img src="${pageContext.request.contextPath}/resources/images/hm.png" class="fa fa-home"></img></a></li>
					<c:if test="${sessionScope.loggedInUser != null}">
						<li><a href="${pageContext.request.contextPath}/welcometodashboards">My Home</a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS">SOLUTIONS</a>
						<ul>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=mdvad&RaYUnA=${finVen:encrypt(username)}">Market Data Vendors</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=tavd&RaYUnA=${finVen:encrypt(username)}">Trading Application Vendors</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=faavd&RaYUnA=${finVen:encrypt(username)}">Analytics Application Vendors</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=frrpd&RaYUnA=${finVen:encrypt(username)}">Research Report Providers</a></li>
						</ul>
					</li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES">SERVICES</a>
						<ul>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=dadd">Data Aggregator Services</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=tapdd">Trading Application Services</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=aapdd">Analytics Application Services</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=SERVICES&subNav=rrpdd">Research Report Services</a></li>
						</ul>
					</li>
					<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES">RESOURCES</a>
						<ul>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=b">Brochures</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=w">Whitepapers</a></li>
							<!-- ${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=blgs -->
							 <li>
							 <c:choose>
							<c:when test="${sessionScope.loggedInUser != null}">
							<a target="_blank" href="http://blog.finvendor.com">Blogs
							</c:when>
							<c:otherwise>
							<a href="javascript:inner_login('')">Blogs
							</c:otherwise>
							</c:choose> 
							</a>
							</li> 
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=cs">Case Studies</a></li>
							<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=s">Spotlights</a></li>							
						</ul>							
				  	</li>
				  	<c:if test="${sessionScope.loggedInRole == 'ROLE_CONSUMER'}">
				  		<li><a href="${pageContext.request.contextPath}/<%=RequestConstans.Vendor.VENDOR_RFP_INBOX%>">RFP Inbox </a></li>
						<li><a href="${pageContext.request.contextPath}/<%=RequestConstans.Vendor.VENDOR_SEARCH_DATABUYER%>">Search Vendors</a></li>					 
				  	</c:if>
				</ul>
			</div>
		</div>		
		<script type="text/javascript">
		  function userCheck(checktype){
				 if(checktype.match('logout') && checktype != ''){
					 window.location.href  = "${pageContext.request.contextPath}/<%=RequestConstans.Login.LOGOUT%>";
				 }else{
					 window.location.href  = "${pageContext.request.contextPath}/<%=RequestConstans.Login.FORGET%>"; 
				 }
			} 
		  function homePage(){
			  window.location.href  = "${pageContext.request.contextPath}/<%=RequestConstans.Login.MY_HOME_PAGE%>?RaYUnA=${finVen:encrypt(myusername)}";
		  }
		</script>
	</div>
</div>
<c:if test="${sessionScope.loggedInUser != null && !param.hideTabsAfterLogIn == 'true'}">
	<div class="tab-container">
		<div class="container">
			<div class="tab-navigation">  
				<c:choose>
					<c:when test="${sessionScope.loggedInRole == 'ROLE_CONSUMER' }">
						<li><a class="active" href="${pageContext.request.contextPath}/<%=RequestConstans.Consumer.CONSUMER_MY_PROFILE%>">My profile</a></li>
						<li><a class="#" href="#">My Blog</a></li>
						<li><a class="#" href="${pageContext.request.contextPath}/<%=RequestConstans.Consumer.CONSUMER_INVITE_AN_RFP%>">Invite an RFP</a></li>	
						<li><a class="#" href="#">My History</a></li>
						<li><a class="#" href="#">My Statistics</a></li>
						<li><a class="#" href="#">Invite Your Team</a></li>	
					</c:when>
				</c:choose>								 
			</div>
		</div>
	</div>
</c:if>