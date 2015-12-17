<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/tab.css" />
<script type="text/javascript">
	function ons() {
		window.alert('ccc');
	}
</script>
</head>
<body class="container">
	<div class="container">
	    <jsp:include page="common/header.jsp"></jsp:include>
	</div>
	
	  <div class="container">
	       <span style="color:red">Welcome to admin dashboard page...!<br /> </span>
	  </div>
	
	 <div class="container">
		<jsp:include page="common/footer.jsp"></jsp:include>
	</div>
</body>
</html>