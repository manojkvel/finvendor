<<<<<<< HEAD
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<html>
	<head>
		<title>Verify Registration</title>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.bxslider.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/superfish.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css"/>
	</head>
	<body>
		<div class="container">
			<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		</div>	
		<div class="container">
			<c:choose>
				<c:when test="${resendRegistrationLink == null}">
					<c:choose>
						<c:when test="${errorMessage != null}">
							Error Validating User Account ${username}<br>
							<font color="red">${errorMessage}</font>
							<c:choose>
								<c:when test="${linkExpired == null}">
									<br>Please Register again!!
								</c:when>
								<c:otherwise>
									<c:if test="${registrationEmail != null}">
										<br>Please click <a href="${pageContext.request.contextPath}/resendRegistrationLink?email=${registrationEmail}&username=${username}"><b>here</b></a> to send a new Registration link
									</c:if>
									<c:if test="${registrationEmail == null}">
										<br>Please Register again!!
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<font color="green">Successfully Validated User Id ${username}</font><br>
							Please click <a href="javascript:inner_login('')"><b>here</b></a> to login
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${resendRegistrationLink == 'success'}">
							Successfully sent Registration link. Please check your email account.
						</c:when>
						<c:otherwise>
							Error sending Registration link. ${errorMessage}
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>	
		<jsp:include page="login.jsp"></jsp:include>	
		<div class="container">
			<jsp:include page="common/footer.jsp"></jsp:include>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.side-slider.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mail-functions.js"></script>
=======
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<html>
	<head>
		<title>Verify Registration</title>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.bxslider.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/superfish.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css"/>
	</head>
	<body>
		<div class="container">
			<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		</div>	
		<div class="container">
			<c:choose>
				<c:when test="${resendRegistrationLink == null}">
					<c:choose>
						<c:when test="${errorMessage != null}">
							Error Validating User Account ${username}<br>
							<font color="red">${errorMessage}</font>
							<c:choose>
								<c:when test="${linkExpired == null}">
									<br>Please Register again!!
								</c:when>
								<c:otherwise>
									<c:if test="${registrationEmail != null}">
										<br>Please click <a href="${pageContext.request.contextPath}/resendRegistrationLink?email=${registrationEmail}&username=${username}"><b>here</b></a> to send a new Registration link
									</c:if>
									<c:if test="${registrationEmail == null}">
										<br>Please Register again!!
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<font color="green">Successfully Validated User Id ${username}</font><br>
							Please click <a href="javascript:inner_login('')"><b>here</b></a> to login
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${resendRegistrationLink == 'success'}">
							Successfully sent Registration link. Please check your email account.
						</c:when>
						<c:otherwise>
							Error sending Registration link. ${errorMessage}
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>	
		<jsp:include page="login.jsp"></jsp:include>	
		<div class="container">
			<jsp:include page="common/footer.jsp"></jsp:include>
		</div>
	</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.side-slider.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mail-functions.js"></script>
>>>>>>> origin/master
</html>