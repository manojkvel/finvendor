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
<title>Pricing - Finvendor</title>
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
	<div class="container_fluid" id="pricing">
		<div class="inner_breadcrumb">
			<h5>Pricing</h5>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<div class="default_template">
					<div class="content">
						<div class="col-xs-12 col-sm-4 col-md-4">
							<div class="pricing_table">
								<button>
									<div class="hd">
										<h3>General (Free)</h3>
									</div>
									<div class="bd">
										<ol>
											<li>
												<p>Access to Unlimited Company profile search</p>
											</li>
											<li>
												<p>Access to Today's Market Summary Page</p>
											</li>
											<li>
												<p>Access to Today's performance of All indices & its constituents.</p>
											</li>
											<li>
												<p>Access to Stock Screener - based on Research Analyst's Recommendations (Upto 50 search results per months)</p>
											</li>
											<li>
												<p>Access to Sector Screener - based on Research Analyst's Recommendations (Upto 50 searches results per months)</p>
											</li>
											<li>
												<p>Set Price alerts(daily, weekly, monthly, within any time frame price movement) on unlimited no. of stocks.</p>
											</li>
											<li>
												<p>Set alerts on any companies if any new research analyst's report is available.</p>
											</li>
											<li>
												<p>Get Daily market summary report through email.</p>
											</li>
										</ol>
									</div>
								</button>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4 col-md-4">
							<div class="pricing_table">
								<button>
									<div class="hd">
										<h3>Smart Investors( INR 499 p.m.) - Free trial 2 days</h3>
									</div>
									<div class="bd">
										<ol>
											<li>
												<p>Access to Unlimited Company profile search</p>
											</li>
											<li>
												<p>Access to Today's Market Summary Page</p>
											</li>
											<li>
												<p>Access to Today's performance of All indices & its constituents.</p>
											</li>
											<li>
												<p>Access to Stock Screener - based on Research Analyst's Recommendations (unlimited search results per months)</p>
											</li>
											<li>
												<p>Access to Sector Screener - based on Research Analyst's Recommendations (unlimited search results per months)</p>
											</li>
											<li>
												<p>Set Price alerts(daily, weekly, monthly, within any time frame price movement) on unlimited no. of stocks.</p>
											</li>
											<li>
												<p>Set alerts on any companies if any new research analyst's report is available.</p>
											</li>
											<li>
												<p>Track your shortlisted companies in your watchlist.</p>
											</li>
											<li>
												<p>Get Daily market summary report through email.</p>
											</li>
											<li>
												<p>Get Daily Sectoral Performance Summary Report through email.</p>
											</li>
											<li>
												<p>Financial Results Calendar for coming week for NSE listed stocks everyday through email.</p>
											</li>
											<li>
												<p>Corporate Actions for company under the watchlist everyday through email.</p>
											</li>
										</ol>
									</div>
								</button>
							</div>
						</div>
						<div class="col-xs-12 col-sm-4 col-md-4">
							<div class="pricing_table">
								<button>
									<div class="hd">
										<h3>Sage Investors( INR 999 p.m.) - Free trial 2 days</h3>
									</div>
									<div class="bd">
										<ol>
											<li>
												<p>Access to Stock Screener "Celebrity Investors' Strategies" & "Do It Yourself" (CUSTOM Stock Screener).</p>
											</li>
											<li>
												<p>Access to Unlimited Company profile search</p>
											</li>
											<li>
												<p>Access to Today's Market Summary Page.</p>
											</li>
											<li>
												<p>Access to Today's performance of All indices & its constituents.</p>
											</li>
											<li>
												<p>Access to Stock Screener - based on Research Analyst's Recommendations (unlimited search results per months)</p>
											</li>
											<li>
												<p>Access to Sector Screener - based on Research Analyst's Recommendations (unlimited search results per months)</p>
											</li>
											<li>
												<p>Set Price alerts(daily, weekly, monthly, within any time frame price movement) on unlimited no. of stocks.</p>
											</li>
											<li>
												<p>Set alerts on any companies if any new research analyst's report is available.</p>
											</li>
											<li>
												<p>Track your shortlisted companies in your watchlist.</p>
											</li>
											<li>
												<p>Get Daily market summary report through email.</p>
											</li>
											<li>
												<p>Get Daily Sectoral Performance Summary Report through email.</p>
											</li>
											<li>
												<p>Financial Results Calendar for coming week for NSE listed stocks everyday through email. </p>
											</li>
											<li>
												<p>Corporate Actions for company under the watchlist everyday through email. </p>
											</li>
											<li>
												<p>Financial results summary for company under the watchlist.</p>
											</li>
										</ol>
									</div>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>