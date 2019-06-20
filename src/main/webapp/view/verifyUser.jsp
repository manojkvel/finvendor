<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<html>
	<head>
		<title>Verify Registration</title>
		<meta charset="utf-8" />
		<meta name="viewport"
			content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />

		<meta name="description"
			content="Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms">
		<meta name="keywords"
			content="Finvendor, Free Equity Research Reports, Market Data Providers, Trading Platforms, Analytics Report">

		<meta property="og:title"
			content="FinVendor - Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms" />
		<meta property="og:type" content='website' />
		<meta property="og:url" content="https://www.finvendor.com" />
		<meta property="og:description"
			content="Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms" />
		<meta property="og:image"
			content="../resources/images/company-logo-header.jpg" />
		<meta property="og:site_name" content="JioCloud" />
		<meta property="fb:app_id" content="" />

		<meta name="twitter:url" content="https://www.finvendor.com" />
		<meta name="twitter:title"
			content="FinVendor - Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms" />
		<meta name="twitter:image"
			content="../resources/images/company-logo-header.jpg" />
		<meta name="twitter:description"
			content="Search Free and Quality Equity Research Reports, Market Data Providers, Trading Platforms" />
		<meta name="twitter:creator" content="google" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<link href="https://www.finvendor.com" rel="canonical" />

		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">

		<meta http-equiv="Pragma" content="no-cache">
		<meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />


		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
		<!--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.bxslider.css"/>-->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/superfish.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css"/>
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
	</head>
	<body>
		<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>	
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-9">
					<div class="inner-left-wrap">
			<c:choose>
				<c:when test="${resendRegistrationLink == null}">
					<c:choose>
						<c:when test="${errorMessage != null}">
							<p>Error Validating User Account ${username}</p>
							<p><font color="red">${errorMessage}</font></p>
							<c:choose>
								<c:when test="${linkExpired == null}">
									<p>Please Register again!!</p>
								</c:when>
								<c:otherwise>
									<c:if test="${registrationEmail != null}">
										<p>Please click <a href="${pageContext.request.contextPath}/resendRegistrationLink?email=${registrationEmail}&username=${username}"><b>here</b></a> to send a new Registration link</p>
									</c:if>
									<c:if test="${registrationEmail == null}">
										<p>Please Register again!!</p>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<p><font color="green">Successfully Validated User Id ${username}</font></p>
							<p>Please click <a href="javascript:inner_login('')"><b>here</b></a> to login</p>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${resendRegistrationLink == 'success'}">
							<p>Successfully sent Registration link. Please check your email account.</p>
						</c:when>
						<c:otherwise>
							<p>Error sending Registration link. ${errorMessage}</p>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			</div>
				</div>
				<div class="col-xs-12 col-sm-3">
					<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<jsp:include page="login.jsp"></jsp:include>	
		
		<jsp:include page="common/footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.side-slider.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mail-functions.js"></script>
</html>