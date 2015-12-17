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
<link href="<%=request.getContextPath() %>/resources/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/jquery.bxslider.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/superfish.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/jquery-ui.css" />


</head>
<body>
<c:set var="checkingresultpageview" value="${checkingresultpageview}"></c:set>
	<div class="container">
		<jsp:include page="common/header.jsp"></jsp:include>
	</div>
	
	<div class="container">
		<jsp:include page="common/banner.jsp"/>
	</div>
	
	<jsp:include page="login.jsp"></jsp:include>
	
	<div class="container">
		<jsp:include page="common/footer.jsp"></jsp:include>
	</div>
	<div class="sideslider" id="sideslider" style="margin-left: -265px;">
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
							<p>
								<input type="text" name="contact_us_name" id="contact_us_name" placeholder="Name" size="30"/>
						<span id="contactNameError" style="display: none;">Please enter your Name.</span>
							</p>
							<p>
								<input type="text" name="contact_us_phone" id="contact_us_phone" placeholder="Phone" size="30"/>
								<span id="contactPhoneError" style="display: none;">Please enter a valid phone number</span>
							</p>		
							<p>
								<input type="text" name="contact_us_email" id="contact_us_email" placeholder="Email" size="30"/>
								<span id="contactEmailError" style="display: none;">Please enter a valid email address</span>
							</p>						
							<p>
								<textarea name="contact_us_message" id="contact_us_message" cols="32" rows="5" placeholder="Message" value="Sample Message"></textarea>
							</p>
							<br>
							<input type="button" value="Submit" name="Submit" id="call"/>
							<div id="loadingct" class="contact_loading" ></div>
    							<div id="output"></div>
				  			<!-- <button><a href="javascript:document.getElementById('contact_us_form').submit();">Send</a> </button>  -->	    	
						
				</form> 
				
				
            </div>
            <div class="sideclear"></div>
        </div>

    </a>
    <div class="sideslider-close sideslider-close_en">Close&nbsp;</div>
</div>


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
