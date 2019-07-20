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
		<input type="hidden" name="loggedInUser" value="${sessionScope.loggedInUser.username}" />
		<div class="inner_breadcrumb">
			<h5>Pricing</h5>
		</div>
		<div class="row">
			<div id='progressLoader'>
				<jsp:include page="common/progressLoader.jsp"></jsp:include>
			</div>
			<div id="steps_update">
				 
				 <a data-toggle="tooltip" title="" id="prev" href="javascript:void(0)" data-original-title="Previous">Go to Back</a>
				 <span>
				 	Step 1 of 3
				 </span>
			</div>

			<div id="pricing_plan">
				<div class="content">
					<div class="col-xs-12 col-sm-4 col-md-4">
						<div class="pricing_table">
							<button id="general_investors" data-value="FREE">
								<div class="hd">
									<h3>General</h3>
									<div class="price-block">
										<div class="price-block-wrapper">
											<div class="price-block__bigno">
												FREE
											</div>
										</div>
									</div>
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
									<div class="btnSubscribe" data-value="0">
										<a href="javascript:void(0);">Free</a>
									</div>
								</div>
							</button>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4 col-md-4">
						<div class="pricing_table">
							<button id="smart_investors" data-value="SMART">
								<div class="hd">
									<h3>Smart Investors</h3>
									<div class="price-block">
										<div class="price-block-wrapper">
											<div class="price-block__currency">
												₹
											</div>
											<div class="price-block__bigno">
												499
											</div>
											<div class="price-block__right-holder price-block__right-holder">
												<div class="price-block-right-holder__cents">
													00
												</div>
												<div class="price-block-right-holder__unit">
													<div class="price-block-right-holder-unit__currency"></div>
													<div class="price-block-right-holder-unit__unit">
														/mo
													</div>
												</div>
											</div>
										</div>
									</div>
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
									<div class="btnSubscribe" data-value="499">
										<a href="javascript:void(0);">Free trial 2 days</a>
									</div>
								</div>
							</button>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4 col-md-4">
						<div class="pricing_table">
							<button id="sage_investors" data-value="SAGE">
								<div class="hd">
									<h3>Sage Investors</h3>
									<div class="price-block">
										<div class="price-block-wrapper">
											<div class="price-block__currency">
												₹
											</div>
											<div class="price-block__bigno">
												999
											</div>
											<div class="price-block__right-holder price-block__right-holder">
												<div class="price-block-right-holder__cents">
													00
												</div>
												<div class="price-block-right-holder__unit">
													<div class="price-block-right-holder-unit__currency"></div>
													<div class="price-block-right-holder-unit__unit">
														/mo
													</div>
												</div>
											</div>
										</div>
									</div>
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
									<div class="btnSubscribe" data-value="999">
										<a href="javascript:void(0);">Free trial 2 days</a>
									</div>
								</div>
							</button>
						</div>
					</div>
				</div>
			</div>

			<div id="pricing_account_info">
				<p><strong>Please follow below steps to complete the Subscription step:</strong></p>
				<h2 id='selected_plan_name'></h2>
				<ol>
					<li>
						<p>Please do the online transfer to our company account using your online banking account.</p>
						<ul>
							<li>
								<strong>Payee Name: </strong>MTAR Vendor Consulting Private Limited
							</li>
							<li>
								<strong>Account no: </strong><>
							</li>
							<li>
								<strong>IFSC code: </strong><>
							</li>
							<li>
								<strong>Account type: </strong>Current (Resident)
							</li>
							<li>
								<strong>Bank Address: </strong><>
							</li>
							<li>
								<strong>Amount to transfer: <span id='selected_plan_amount'></span></strong>
							</li>
						</ul>
					</li>
					<li>
						<p>Once you are done with transferring the amount to our account, please fill up the details using below button.</p>
						<div class="pricing_form_btn">
							<a href="javascript:void(0);">Click Here</a>
						</div>
					</li>
					<li>
						<p>
							Once we receive the payment in our bank account, we will take maximum 24 hrs to activate your profile for subscription type opted by you. 
						</p>
					</li>
				</ol>
			</div>

			<div id="pricing_bank_form">
				<div class="row">
					<div class="col-xs-12 col-sm-6">
						<div class="pricing_bank_content">
							<p><strong>Please complete below bank form to complete the Subscription step:</strong></p>
							<img src="${pageContext.request.contextPath}/resources/images/pricing_bank_form.png" alt="" />
						</div>
					</div>
					<div class="col-xs-12 col-sm-6">
						<div id="bank_form">
							<ul>
								<li>
									<label>Finvendor user ID <sup>*</sup></label>
									<input type="text" maxlength="300" name="userId" id="userId" value="${sessionScope.loggedInUser.username}" readonly="readonly" />
								</li>

								<li>
									<label class="default_select">Subscription Type</label>
									<!--<select class="selectpicker show-tick" id="subscriptionType" name="subscriptionType">
										<option selected="selected" value="SAGE">Sage investors</option>
										<option value="SMART">Smart investors</option>
									</select>-->
									<input type="text" name="subscriptionType" id="subscriptionType" readonly="readonly" />
								</li>

								<li>
									<label class="default_select">Mode of payment</label>
									<select class="selectpicker show-tick" id="paymentMode" name="paymentMode">
										<option selected="selected">NEFT/RTGS/IMPS</option>
										<!--<option>Cheque</option>
										<option>Debit/Credit Card</option>-->
									</select>
								</li>

								<li>
									<label>Transaction Date <sup>*</sup></label>
									<input type="text" id="transactionDate" name="transactionDate" required />
								</li>
								<li>
									<label>Transaction Reference Number <sup>*</sup></label>
									<input type="text" name="transactionRefNumber" id="transactionRefNumber" required />
								</li>
								<li>
									<label>Amount Transferred <sup>*</sup></label>
									<input type="number" name="amountTransferred" id="amountTransferred" readonly="readonly" />
								</li>
								<li>
									<label>Bank Name <sup>*</sup></label>
									<input type="text" name="bankName" id="bankName" required />
								</li>
								<li>
									<label>Bank Holder Name <sup>*</sup></label>
									<input type="text" name="bankHolderName" id="bankHolderName" required />
								</li>
								<li>
									<div class="pricing_bank_form_btn">
										<a href="javascript:void(0);">Submit</a>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			
			<div id="pricing_bank_form_result">
				<div class="row">
					<div class="col-xs-12">
						<p></p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
	<script>
    	$( function() {
    		$( "#transactionDate" ).datepicker({
    			dateFormat: 'dd/mm/yy'
    		});
    	} );
    </script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pricing.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
</body>
</html>