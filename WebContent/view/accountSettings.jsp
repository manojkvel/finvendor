<<<<<<< HEAD
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
<head>
	<title>Account Settings</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
	
</head>
<body>
	<jsp:include page="common/head.jsp"></jsp:include>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container">
		<div id="account_settings">
			<div id="top-card">
				<div class="profile-top-card top-card">
					<div class="profile-card vcard">
						<div class="profile-overview">
							<div class="profile-overview-content">
								<h2 class="full-name">
									${user.userName}
								</h2>
								<h4 class="contacts">
									${user.email}
								</h4>
								<table class="company-details">
									<tr>
										<th>Comany Type</th>
										<td class="type">
											<c:if test="${user.consumer != null}">
											${user.consumer.companyType}
											</c:if>
											<c:if test="${user.vendor != null}">
											${user.vendor.companyType}
											</c:if>
										</td>
									</tr>
									<c:if test="${user.consumer != null}">
									<tr>
										<th>Vendor Area of Interest</th>
										<td class="tags">										
											<c:if test="${user.consumer != null}">
											${user.consumer.tags}
											</c:if>
										</td>
									</tr>
									</c:if>
								</table>
							</div>
						</div>
					</div>
				</div>
				<a class="btn" id="edit-details">
					<span class="fa fa-pencil"></span>Edit Details
				</a>
			</div>
			<div id="account_details" class="custom_form">
				<jsp:include page="common/progressLoader.jsp"></jsp:include>
				<form name="account_settings_form" id="account_settings_form">
					<div class="generic_message">
						<div class="alert"></div>
					</div>
					<ul>
						<li>
							<input type="text" name="userName" id="userName" value="${user.userName}" readonly="readonly" />
							<label class="default_select">Username</label>
						</li>
						<li>
							<input type="text" name="email" id="email" value="${user.email}"  readonly="readonly" />
							<label class="default_select">Email</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="companyType" multiple="multiple" id="companyType">
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${finConsumerCompanySellSide != null}">
													<option value="Financial Firm - Sell side" id="2" selected>Financial Firm - Sell side</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Sell side" id="2">Financial Firm - Sell side</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${finConsumerCompanyBuySide != null}">
													<option value="Financial Firm - Buy side" id="3" selected>Financial Firm - Buy side</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Buy side" id="3" >Financial Firm - Buy side</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${finConsumerCompanyOther != null}">
													<option value="Financial Firm - Others" id="4" selected>Financial Firm - Others</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Others" id="4">Financial Firm - Others</option>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${user.vendor != null}">
											<c:choose>
												<c:when test="${dataaggregator != null}">
													<option value="Data Aggregator" id="5" selected>Financial Vendor - Data Aggregators</option> 
												</c:when>
												<c:otherwise>
													<option value="Data Aggregator" id="5">Financial Vendor - Data Aggregators</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${tradingapplication != null}">
													<option value="Trading Application" id="6" selected>Financial Vendor - Trading Applications</option>
												</c:when>
												<c:otherwise>
													<option value="Trading Application" id="6">Financial Vendor - Trading Applications</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${analyticsapplication != null}">
													<option value="Analytics Application" id="7" selected>Financial Vendor - Analytics Applications</option>
												</c:when>
												<c:otherwise>
													<option value="Analytics Application" id="7">Financial Vendor - Analytics Applications</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${researchreport != null}">
													<option value="Research Report" id="8" selected>Financial Vendor - Research report Providers</option>
												</c:when>
												<c:otherwise>
													<option value="Research Report" id="8">Financial Vendor - Research report Providers</option>
												</c:otherwise>
											</c:choose>										
										</c:if>							
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${consumerCompanyUniversity != null}">
													<option value="University/College" id="9" selected>University/College</option>
												</c:when>
												<c:otherwise>
													<option value="University/College" id="9">University/College</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${consumerCompanyOtherFirm != null}">
													<option value="Other Firm" id="10" selected>Other firm</option>
												</c:when>
												<c:otherwise>
													<option value="Other Firm" id="10">Other firm</option>
												</c:otherwise>
											</c:choose>										
										</c:if>	
							</select>
							<label class="default_select">Company Type</label>
						</li>
						<c:if test="${user.consumer != null}">
						<li id="vendor_area_of_interest">
							<select class="selectpicker select_multiple" name="vendorAreaOfInterest" multiple="multiple" id="vendorAreaOfInterest">
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${user.consumer.marketDataPreference == 'true'}">
													<option value="Data Aggregator" selected>Data Aggregator</option>
												</c:when>
												<c:otherwise>
													<option value="Data Aggregator">Data Aggregator</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.tradingAppPreference == 'true'}">
													<option value="Trading Application" selected>Trading Application</option>
												</c:when>
												<c:otherwise>
													<option value="Trading Application">Trading Application</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.analyticsAppPreference == 'true'}">
													<option value="Analytics Application" selected>Analytics Application</option>
												</c:when>
												<c:otherwise>
													<option value="Analytics Application">Analytics Application</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.researchReportPreference == 'true'}">
													<option value="Research Report" selected>Research Report</option>
												</c:when>
												<c:otherwise>
													<option value="Research Report">Research Report</option>
												</c:otherwise>
											</c:choose>										
										</c:if>
							</select>
							<label class="default_select">Vendor Area of Interest</label>
						</li>
						</c:if>
					</ul>
					<div id="newslettersAndAlerts1">
						<input type="checkbox" id="newslettersAndAlerts" name="newslettersAndAlerts" value="newslettersAndAlerts" style="float:left;"/> 							
							<p>
								&nbsp;I wish to get regular 
								Newsletters & Alerts
							</p>
					</div>

					<c:if test="${user.consumer != null}">
						<p class="action_btn" data="userConsumer">
							<a class="submit_btn save" data-toggle="tab">Save</a>
						</p>
					</c:if>
					<c:if test="${user.vendor != null}">
						<p class="action_btn" data="userVendor">
							<a class="submit_btn save" data-toggle="tab">Save</a>
						</p>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
</body>
=======
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
<head>
	<title>Account Settings</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
	
</head>
<body>
	<jsp:include page="common/head.jsp"></jsp:include>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container">
		<div id="account_settings">
			<div id="top-card">
				<div class="profile-top-card top-card">
					<div class="profile-card vcard">
						<div class="profile-overview">
							<div class="profile-overview-content">
								<h2 class="full-name">
									${user.userName}
								</h2>
								<h4 class="contacts">
									${user.email}
								</h4>
								<table class="company-details">
									<tr>
										<th>Comany Type</th>
										<td class="type">
											<c:if test="${user.consumer != null}">
											${user.consumer.companyType}
											</c:if>
											<c:if test="${user.vendor != null}">
											${user.vendor.companyType}
											</c:if>
										</td>
									</tr>
									<c:if test="${user.consumer != null}">
									<tr>
										<th>Vendor Area of Interest</th>
										<td class="tags">										
											<c:if test="${user.consumer != null}">
											${user.consumer.tags}
											</c:if>
										</td>
									</tr>
									</c:if>
								</table>
							</div>
						</div>
					</div>
				</div>
				<a class="btn" id="edit-details">
					<span class="fa fa-pencil"></span>Edit Details
				</a>
			</div>
			<div id="account_details" class="custom_form">
				<jsp:include page="common/progressLoader.jsp"></jsp:include>
				<form name="account_settings_form" id="account_settings_form">
					<div class="generic_message">
						<div class="alert"></div>
					</div>
					<ul>
						<li>
							<input type="text" name="userName" id="userName" value="${user.userName}" readonly="readonly" />
							<label class="default_select">Username</label>
						</li>
						<li>
							<input type="text" name="email" id="email" value="${user.email}"  readonly="readonly" />
							<label class="default_select">Email</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="companyType" multiple="multiple" id="companyType">
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${finConsumerCompanySellSide != null}">
													<option value="Financial Firm - Sell side" id="2" selected>Financial Firm - Sell side</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Sell side" id="2">Financial Firm - Sell side</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${finConsumerCompanyBuySide != null}">
													<option value="Financial Firm - Buy side" id="3" selected>Financial Firm - Buy side</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Buy side" id="3" >Financial Firm - Buy side</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${finConsumerCompanyOther != null}">
													<option value="Financial Firm - Others" id="4" selected>Financial Firm - Others</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Others" id="4">Financial Firm - Others</option>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${user.vendor != null}">
											<c:choose>
												<c:when test="${dataaggregator != null}">
													<option value="Data Aggregator" id="5" selected>Financial Vendor - Data Aggregators</option> 
												</c:when>
												<c:otherwise>
													<option value="Data Aggregator" id="5">Financial Vendor - Data Aggregators</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${tradingapplication != null}">
													<option value="Trading Application" id="6" selected>Financial Vendor - Trading Applications</option>
												</c:when>
												<c:otherwise>
													<option value="Trading Application" id="6">Financial Vendor - Trading Applications</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${analyticsapplication != null}">
													<option value="Analytics Application" id="7" selected>Financial Vendor - Analytics Applications</option>
												</c:when>
												<c:otherwise>
													<option value="Analytics Application" id="7">Financial Vendor - Analytics Applications</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${researchreport != null}">
													<option value="Research Report" id="8" selected>Financial Vendor - Research report Providers</option>
												</c:when>
												<c:otherwise>
													<option value="Research Report" id="8">Financial Vendor - Research report Providers</option>
												</c:otherwise>
											</c:choose>										
										</c:if>							
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${consumerCompanyUniversity != null}">
													<option value="University/College" id="9" selected>University/College</option>
												</c:when>
												<c:otherwise>
													<option value="University/College" id="9">University/College</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${consumerCompanyOtherFirm != null}">
													<option value="Other Firm" id="10" selected>Other firm</option>
												</c:when>
												<c:otherwise>
													<option value="Other Firm" id="10">Other firm</option>
												</c:otherwise>
											</c:choose>										
										</c:if>	
							</select>
							<label class="default_select">Company Type</label>
						</li>
						<c:if test="${user.consumer != null}">
						<li id="vendor_area_of_interest">
							<select class="selectpicker select_multiple" name="vendorAreaOfInterest" multiple="multiple" id="vendorAreaOfInterest">
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${user.consumer.marketDataPreference == 'true'}">
													<option value="Data Aggregator" selected>Data Aggregator</option>
												</c:when>
												<c:otherwise>
													<option value="Data Aggregator">Data Aggregator</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.tradingAppPreference == 'true'}">
													<option value="Trading Application" selected>Trading Application</option>
												</c:when>
												<c:otherwise>
													<option value="Trading Application">Trading Application</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.analyticsAppPreference == 'true'}">
													<option value="Analytics Application" selected>Analytics Application</option>
												</c:when>
												<c:otherwise>
													<option value="Analytics Application">Analytics Application</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.researchReportPreference == 'true'}">
													<option value="Research Report" selected>Research Report</option>
												</c:when>
												<c:otherwise>
													<option value="Research Report">Research Report</option>
												</c:otherwise>
											</c:choose>										
										</c:if>
							</select>
							<label class="default_select">Vendor Area of Interest</label>
						</li>
						</c:if>
					</ul>
					<div id="newslettersAndAlerts1">
						<input type="checkbox" id="newslettersAndAlerts" name="newslettersAndAlerts" value="newslettersAndAlerts" style="float:left;"/> 							
							<p>
								&nbsp;I wish to get regular 
								Newsletters & Alerts
							</p>
					</div>

					<c:if test="${user.consumer != null}">
						<p class="action_btn" data="userConsumer">
							<a class="submit_btn save" data-toggle="tab">Save</a>
						</p>
					</c:if>
					<c:if test="${user.vendor != null}">
						<p class="action_btn" data="userVendor">
							<a class="submit_btn save" data-toggle="tab">Save</a>
						</p>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
</body>
>>>>>>> origin/master
</html>