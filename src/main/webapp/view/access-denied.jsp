<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<html>
<head>
<title>Authorization Error</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.css">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery.bxslider.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/superfish.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" />
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<section>
		<div id="fin_vendor_access_denied" class="section">
			<div class="container">
				<font color="red">You are not Authorized to access this page</font>
			</div>
		</div>
	</section>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.side-slider.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/mail-functions.js"></script>
</html>