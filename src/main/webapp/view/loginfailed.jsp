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
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
  <![endif]-->
 <link href="<%=request.getContextPath() %>/resources/css/style.css" rel="stylesheet" />
<!--<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/jquery.bxslider.css" />-->
<!--<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/superfish.css" />-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/tab.css" /> 
<!-- <link href="http://mottie.github.io/tablesorter/css/theme.default.css" rel="stylesheet">
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.9.1/jquery.tablesorter.min.js"></script> -->
<script>
    $(function(){
      $("#myDummyTable").tablesorter({widgets: ['zebra']});
    });
</script>
<script type="text/javascript">
	function ons() {
		window.alert('ccc');
	}
</script>
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
</head>
<body>
	<div class="container">
		<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	</div>
    	<form id="login-submit" method="post" action="<c:url value='j_spring_security_check' />" id="login-submit">
		<div class="container">
			 <div>
				<div>
					<div>
						<div>
							<c:if test="${not empty sucess}">
								<div id="error" style="font-size:13px;">
								<span style="color:#2AABAB">You've registered successfully..!<br /> </span>
								</div>
							</c:if>
						</div>
						<div>
							<c:if test="${not empty error}">
								<div id="error" style="font-size:13px;">
								<span style="color:red">Your login attempt was not successful, try again.<br /> </span>
								</div>
							</c:if>
						</div>
						<div class="clearfix"><br/></div>
						<h4>Existing User Login</h4>
						<div class="clearfix"><br/></div>
						<div class="login-form-content">
							<div class="login-form">
								<div class="label">UserName</div>
								<input name="username" type="text" class="input">
								<div class="clearfix"></div>
							</div>
							<div class="login-form">
								<div class="label">Password</div>
								<input name="password" type="password" class="input">
								<div class="clearfix"></div>
							</div>
						</div>
						<div class="login-ft">
							<p>
								<input type="button" value="login" class="login"
									onclick="loginSubmit();" />
							</p>
							<p>
								<input type="reset" value="Reset" class="login" />
							</p>
							<div class="clearfix"></div>
						</div>
						<%-- <div class="login-ft">
							<br /> Yet Not Registered!! <a
								href="<%=request.getContextPath()%>/<%=RequestConstans.Register.REGISTER%>">Register 
								Here</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Forgot Password? <a
								href="<%=request.getContextPath() %>/forgotPassword">Click
								Here</a>
							<div class="clearfix"></div>
						</div> --%>

					</div>
				</div>
			</div> 
		</div>
	</form>
	<div class="container">
		<jsp:include page="common/footer.jsp"></jsp:include>
	</div>
	<div id="submitMsg" class="submitMsg" style="display:none;"></div>
</body>
<script type="text/javascript">
function movetoRegister(){
	document.getElementById("submitMsg").innerHTML = "Do you want to continue?";

	var buttonNames = ["Yes", "No"];
	newConfirmMsg("submitMsg", buttonNames).then(function(confirmed) {
	   if (confirmed) {
	 		window.location.href = '<%=request.getContextPath()%>/<%=RequestConstans.Register.REGISTER%>';
	   }
	});
	
}
</script>
</html>