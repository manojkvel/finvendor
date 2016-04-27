<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<!-- [if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="SKYPE_TOOLBAR" content="SKYPE_TOOLBAR_PARSER_COMPATIBLE" />
<title>FinVendor</title>
<jsp:include page="common/head.jsp"></jsp:include>
</head>
<body>
<c:set var="checkingresultpageview" value="${checkingresultpageview}"></c:set>
	
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	
	<div class="container">
		<jsp:include page="common/banner.jsp"/>
	</div>
	
	<jsp:include page="login.jsp"></jsp:include>
	
	
	<jsp:include page="common/footer.jsp"></jsp:include>
	<!-- <div class="sideslider" id="sideslider" style="margin-left: -265px;">
    <div class="sideslider-tab">C<br>o<br>n<br>t<br>a<br>c<br>t<br>u<br>s</div>
    <a href="#">
        <div id="sideslider-smartbutton">
            <div id="sideslider-text">
            <style type="text/css">
.contact_loading {
	min-height: 50px;
	background:  url(<%=request.getContextPath() %>/resources/images/bx_loader.gif) center center no-repeat #fff;
	height: 100%;
	width: 100%;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 2000;
	display: none;
	opacity: 0.6;
</style>
	<form action="<%=request.getContextPath() %>/<%=RequestConstans.MAIL.MAIL_SEND%>" method="post" id="contact_us_form">
		<div class="form-group">
			<input type="text" name="contact_us_name" class="form-control" id="contact_us_name" placeholder="Name" size="30"/>
			<span id="contactNameError" style="display: none;">Please enter your Name.</span>
		</div>
		<div class="form-group">
			<input type="text" name="contact_us_phone" class="form-control" id="contact_us_phone" placeholder="Phone" size="30"/>
			<span id="contactPhoneError" style="display: none;">Please enter a valid phone number</span>
		</div>
		<div class="form-group">
			<input type="text" name="contact_us_email" class="form-control" id="contact_us_email" placeholder="Email" size="30"/>
			<span id="contactEmailError" style="display: none;">Please enter a valid email address</span>
		</div>
		<div class="form-group">
			<textarea name="contact_us_message" class="form-control" id="contact_us_message" cols="32" rows="5" placeholder="Message" value="Sample Message"></textarea>
		</div>
		<div class="form-group">
			<input type="button" value="Submit" class="btn info" name="Submit" id="call"/>
		</div>
		<div id="loadingct" class="contact_loading" ></div>
    	<div id="output"></div>
				  			<!-- <button><a href="javascript:document.getElementById('contact_us_form').submit();">Send</a> </button>  ->	    	
	</form> 
				
				
            </div>
            <div class="sideclear"></div>
        </div>

    </a>
    <div class="sideslider-close sideslider-close_en"><i class="fa fa-close"></i></div>
</div>

 -->
</body>
<!--<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.11.0.min.js"></script>-->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.side-slider.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#sideslider').sideSlider();

    });
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mail-functions.js"></script>
<!-- <script type="text/javascript">
        $(document).ready(senAJAXMail());
    </script>
<script type="text/javascript">
window.onload = function(){
	alert('message view---:');
	var check= '${checkingresultpageview}';
	alert('rayulu vemula---:' + check);
	if(personalDetails != null && personalDetails.length > 0 && personalDetails.match("personaldetails")){
		 
	}
};
</script> -->
</html>
