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
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/jquery.bxslider.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/superfish.css" />
<script type="text/javascript">
	function ons() {
		//window.alert('ccc');
	}
</script>
</head>
<body>
	<div class="container">
		<jsp:include page="view/common/header.jsp"></jsp:include>
	</div>
    <jsp:include page="view/login.jsp"></jsp:include>
	<jsp:include page="view/common/banner.jsp" />
	<div class="container">
		<jsp:include page="view/common/footer.jsp"></jsp:include>
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