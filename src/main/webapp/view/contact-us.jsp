<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0"
	prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans"
	var="requestConstants" />
<html>
<head>
<title>Contact Us - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container" id="contact_us">
		<div class="inner_breadcrumb">
			<h5>Contact Us</h5>
		</div>

		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="default_template">
					<form action="<%=request.getContextPath()%>/<%=RequestConstans.MAIL.MAIL_SEND%>" method="post" id="contact_us_form">
						<div class="form-group">
							<input type="text" name="contact_us_name" class="form-control" id="contact_us_name" placeholder="Name" size="30" /> 
							<span id="contactNameError" style="display: none;">Please
							enter your Name.</span>
						</div>
						<div class="form-group">
							<input type="text" name="contact_us_phone" class="form-control" id="contact_us_phone" placeholder="Phone" size="30" /> 
							<span id="contactPhoneError" style="display: none;">Please
							enter a valid phone number</span>
						</div>
						<div class="form-group">
							<input type="text" name="contact_us_email" class="form-control" id="contact_us_email" placeholder="Email" size="30" /> 
							<span id="contactEmailError" style="display: none;">Please
							enter a valid email address</span>
						</div>
						<div class="form-group">
							
							<textarea name="contact_us_message" class="form-control" id="contact_us_message" cols="32" rows="5" placeholder="Message" value="Sample Message"></textarea>
						</div>
						<br> <input type="button" value="Submit" name="Submit" id="call" class="btn primary" />
						<div id="loadingct" class="contact_loading"></div>
						<div id="output"></div>
						<!-- <button><a href="javascript:document.getElementById('contact_us_form').submit();">Send</a> </button>  -->

					</form>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mail-functions.js"></script>	
</body>
</html>