<%@page import="com.finvendor.util.RequestConstans"%>
<%@page import="org.springframework.security.core.authority.SimpleGrantedAuthority"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>

<c:set var="username" value="${finVen:decrypt(param.RaYUnA)}"></c:set>
<c:set var="myusername" value="${myusername}"></c:set>
<div class="header-container">
	<div class="container">
		<div class="header"> 
			<a href="#" onclick="homePage();" class="logo">
				<img src="${pageContext.request.contextPath}/resources/images/icon-logo.jpg" alt="FinVendor" title="FinVendor" onclick="homepage();"/>
			</a> 
			<div class="pull-right contact-detail">
				<p>
					<c:choose>
						<c:when test="${not empty username || not empty myusername}"></c:when>
						<c:otherwise>
						  	<i class="fa fa-pencil"></i> Financial Vendor? 
							<a class="link" href="#">List your offerings</a>
						</c:otherwise>
					</c:choose>
				</p>
				<ul class="hd-right">
					<c:choose>
						<c:when test="${not empty username || not empty myusername}">
							<li class="block">Welcome <c:out value="${fn:toUpperCase(username)}" /></li>
							<li><a href="<%=request.getContextPath() %>/logout">Logout</a></li>														
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
					<form class="pull-right">
						<div class="form-group">
							<input name="" type="text" class="srch form-control" placeholder="Search">
						</div>
					</form>
					<ul class="sf-menu" id="example">
						<li><a href="#" onclick="homePage();"><i class="fa fa-home"></i></a></li>
						<c:if test="${not empty myusername}">
						<li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${finVen:encrypt(myusername)}">My Home</a></li>
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
								<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=blgs">Blogs</a></li>
								<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=cs">Case Studies</a></li>
								<li><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=RESOURCES&subNav=s">Spotlights</a></li>
								
							</ul>							
					  </li>
					</ul>
				</div>
			</div>
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
	  window.location.href  = "<%= request.getContextPath()%>/<%=RequestConstans.Login.MY_HOME_PAGE%>?RaYUnA=${finVen:encrypt(myusername)}";
  }
</script>