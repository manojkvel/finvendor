<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
	<head>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/superfish.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/tab.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/dataTables.jqueryui.min.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>		
		<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/dataTables.jqueryui.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
	</head>
	<body>
		<jsp:include page="common/head.jsp"></jsp:include>
		<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		<div class="container">
			<div class="inner-content">
				<div class="inner-left-wrap" id="${finven:replaceCharacter(nav, ' ', '_')}_${finven:replaceCharacter(subNav, ' ', '_')}">
					<div><b><label class="errorMessage" style="color:red">${lastActionError}</label></b></div>
		    		<c:choose>
						<c:when test="true">
							<p>
								<div id="companyLogo">
									<img src="${pageContext.request.contextPath}/displayCompanyLogo/${user.userName}" width="175" height="400" 
										style="float:left;margin-right:10px" alt="${user.vendor.company}" title="${user.vendor.company}"/>										
								</div>
								<b><h1>${user.vendor.company}</h1></b>								
								<h5>${country.name}&nbsp;|&nbsp;${user.vendor.companyType}</h5>
							</p>					
								Since<br>
								${user.vendor.firstName}&nbsp;${user.vendor.lastName}<br>
								${user.vendor.designation}<br>
								${user.email}<br>
								${user.vendor.secondaryEmail}<br>
								${user.vendor.telephone}<br>
								${user.vendor.companyUrl}
							</p>
							<br>
							<p><b><h3>Summary</h3></b></p>
							<p>${user.vendor.companyInfo}</p>	
						</c:when>
					</c:choose>						
				</div>	
			</div>
			<jsp:include page="adminMenu.jsp"></jsp:include>
		</div>
		<jsp:include page="common/footer.jsp"></jsp:include>
	</body>
</html>