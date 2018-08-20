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
<title>Disclaimer - Finvendor</title>
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
	<div class="container" id="disclaimer">
		<div class="inner_breadcrumb">
			<h5>Disclaimer</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="default_template">
					<div class="content">
						<h3>Disclaimer of Warranty: Limitation of Liability</h3>
						<p>(Request-For-Proposal) with their detailed vendor’s product needs. Such RFPs are made available to all FinVendor listed vendors. End-Users can also track their initiated RFPs, review vendor detailed response and finalize the vendor in the least possible time. Financial vendors (Data Aggregators, Trading Application Vendor, Financial Analytics Application Vendor, Financial Research Report Providers) can list their offerings in the well-researched FinVendor format. Data vendors can also participate in all open RFPs (Request-For-Proposal) initiated by data End-Users.</p>
						<h1>Restrictions</h1>
						<p>On FinVendor platform, End-Users(I-banks, Portfolio Managers, Hedge Funds, Research Analyst, University Scholars, etc) can search all available vendors for a certain vendor offerings (Market Data Vendor, Trading Application Vendor, Financial Analytics Application Vendor, Financial Research Report Providers). End-Users can see the granular level info about the vendor product offerings. End-Users can also initiate a RFP</p>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-3">
				<jsp:include page="common/inner_sidebar.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>