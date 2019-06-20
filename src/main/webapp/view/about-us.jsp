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
<title>About Us - Finvendor</title>
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
	<div class="container" id="about_us">
		<div class="inner_breadcrumb">
			<h5>About Us</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-md-9">
				<div class="default_template">
					<div class="content">
						<p>FinVendor strives for democratizing the inventor's community. FinVendor endeavours to help retail and institutional investors make a well-informed investment decisions.</p> 

						<p>FinVendor provides a unique platform of most up to date equity research reports for public listed companies at indian stock exchanges. Finvendor sources the equity research reports from the registered Brokers and other sell-side independent research Analysts.</p>

						<p><strong>In our current state, we provide 6 main functionalities:</strong></p>
						<ol>
							<li>
								<strong>Search company:</strong> Search any company by name or NSE stock tickers. 
							</li>
							<li>
								<strong>Company Profile access:</strong> See company profile page and quick snapshot of the company. Go to its in "research report" Tab and find how many renowned Brokers are covering this stock and with what target price (%upside potential ) and average target price. 
							</li>
							<li>
								<strong>WatchList:</strong> Put your Favourite stocks under watchlist.
							</li>
							<li>
								<strong>Set Price Alert:</strong> Set the % price alert on any stocks that you want to invest in future (+ve price alert) OR you have invested recently(-ve price alert). If this alert is triggered you will be informed. 
							</li>
							<li>
								<strong>Track New Research Report:</strong> Market, at times, becomes so volatile. In such scenario, you would like to track any new research report which has changed its earlier stock recommendations. Set new research report addition alert on any company that you own or intend to own. If any new reports with revised price target is uploaded on website, you will be informed through email alert.
							</li>
							<li>
								<strong>Find stocks worthy of investment:</strong> Go to research report -> company research menu. you will see 10+ filters to shortlist the stocks to invest. Those companies (tracked by 20+ research Brokers) having upside potential is > 50% and company is Large cap and research date less than 3 months. You can get the results in single click.
							</li>
						</ol>
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