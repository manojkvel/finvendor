<%@page import="com.finvendor.util.RequestConstans"%>
<%@page import="org.springframework.security.core.authority.SimpleGrantedAuthority"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>

<c:set var="username" value="${finVen:decrypt(param.RaYUnA)}"></c:set>
<c:set var="myusername" value="${myusername}"></c:set>
<div class="header"> 
	<img src="${pageContext.request.contextPath}/resources/images/logo-new.png" style="height:61px;width:115px" alt="FinVendor" title="FinVendor" onclick="homepage();"/> 
	<div class="hd-right">
		<p>
			<c:choose>
				<c:when test="${not empty username || not empty myusername}"></c:when>
				<c:otherwise>
				  	<img src="${pageContext.request.contextPath}/resources/images/fn.png" alt="" title=""/><i>FINANCIAL VENDOR?</i> 
					<a class="cd-signup" href="#">LIST YOUR OFFERINGS</a>
				</c:otherwise>
			</c:choose>
		</p>
		<ul>
			<c:choose>
				<c:when test="${not empty username || not empty myusername}">
					<div class="area">
						<div class="Rowtableinfovaluserpng">
							<div class="ColumnCommonrayuserlogout">
								<a href="<%=request.getContextPath() %>/logout"><span class="lable_headeractionsuserpng"><img src="<%=request.getContextPath() %>/resources/images/sign-out.png" width="25" height="25"/>Logout</span></a>
							</div>  
							<div class="ColumnCommonrayuserpng">
								<a href="#normalModal"><span class="lable_headeractionsuserpng"><img src="<%=request.getContextPath() %>/resources/images/user.png" width="30" height="30"/> Welcome <c:out value="${fn:toUpperCase(username)}" /></span></a>
							</div>  
						</div> 
					</div>
				</c:when>
				<c:otherwise>
					<li class="login"><img src="${pageContext.request.contextPath}/resources/images/lg.png" alt="" title="" /><span><a class="cd-signin" href="#">Login</a>/<a class="cd-signup" href="#">Register</a></span></li>
					<li><span></span></li>
					<li><img src="${pageContext.request.contextPath}/resources/images/msg.png" alt="" title=""/><span><a href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=CONTACT">CONTACT</a></span></li> 
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div class="clearfix"></div>
</div>
<div class="nav-srch">
	<div class="container">
		<div class="nav-srch-cnt">
			<ul class="sf-menu" id="example">
				<li><a href="#" onclick="homePage();"><img src="${pageContext.request.contextPath}/resources/images/hm.png" alt="" title="" /></a></li>
				<c:if test="${not empty myusername}">
				<li><a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${finVen:encrypt(myusername)}" style="padding: 9px 6px; font-size: 11px;">My Home</a></li>
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
			<input name="" type="text" class="srch" value="Search">
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