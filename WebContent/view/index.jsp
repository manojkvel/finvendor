<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />
<title>FinVendor</title>
</head>
<body>
	<c:set var="checkingresultpageview" value="${checkingresultpageview}"></c:set>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<section>
		<div id="fin_vendor_index" class="section">
			<div class="container">
				<div class="row">
					<jsp:include page="common/banner.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery.side-slider.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#sideslider').sideSlider();

	});
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/mail-functions.js"></script>
</html>