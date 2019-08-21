<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<!DOCTYPE html>
<head>
	<title>Manage User Subscriptions - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container" id="fund_etf_research">
		<div class="inner_breadcrumb">
			<h5></h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="default_template">
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
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>

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