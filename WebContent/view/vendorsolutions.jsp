<<<<<<< HEAD
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.finvendor.util.RequestConstans"%>

<c:set var="vendormyofferingsdatacoverage"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATACOVERAGE %>">
</c:set>
<c:set var="vendormyofferingsdatadistribution"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATA_DISTEIBUTION %>">
</c:set>

<c:set var="tradingcapabilitiessupported"
	value="<%=RequestConstans.Vendor.TRADING_CAPABILITIES_SUPPORTED %>">
</c:set>
<c:set var="tradingsoftwaredetails"
	value="<%=RequestConstans.Vendor.TRADING_SOFTWARE_DETAILS %>"></c:set>

<c:set var="analyticsfeaturessupported"
	value="<%=RequestConstans.Vendor.ANALYTICS_FEATURES_SUPPORTED %>">
</c:set>
<c:set var="analyticssoftwaredetails"
	value="<%=RequestConstans.Vendor.ANALYTICS_SOFTWARE_DETAILS %>"></c:set>

<c:set var="researchcoverage"
	value="<%=RequestConstans.Vendor.RESEARCH_COVERAGE %>">
</c:set>
<c:set var="researchdetails"
	value="<%=RequestConstans.Vendor.RESEARCH_DETAILS %>"></c:set>
<c:set var="analystprofile"
	value="<%=RequestConstans.Vendor.ANALYTST_PROFILE %>">
</c:set>

<c:set var="vendormyofferingsasaggregatorvendor"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_DATA_AGGREGATOR_VENDOR %>">
</c:set>
<c:set var="vendormyofferingsastradingapplicationvendor"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_TRADING_APPLICATION_VENDOR %>">
</c:set>
<c:set var="vendormyofferingsasanalyticsapplicationvendor"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_ANALYTICS_APPLICATION_VENDOR %>">
</c:set>
<c:set var="vendormyofferingsasresearchreportingvendor"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_RESEARCH_REPORTING_VENDOR %>">
</c:set>

<c:set var="dataaggregator" value="${dataaggregator}"></c:set>
<c:set var="tradingapplication" value="${tradingapplication}"></c:set>
<c:set var="analyticsapplication" value="${analyticsapplication}"></c:set>
<c:set var="researchreport" value="${researchreport}"></c:set>

<!DOCTYPE html>
<head>
<meta charset="utf-8" />
<title>finvendor</title>
<link
	href="<%=request.getContextPath() %>/resources/css/singleasset.css"
	type="text/css" rel="stylesheet" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/resources/css/reset.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/css/style-metro.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/resources/css/style-II.css"
	rel="stylesheet" type="text/css" />
<!-- Date Picker CSS Starts -->
<link
	href="<%=request.getContextPath() %>/resources/datepick/jsDatePick_ltr.min.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/css/finvendor.css">
<script	src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
<!-- Date Picker CSS Ends -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div class="top_header">
		<jsp:include page="common/dashboardheader.jsp"></jsp:include>
	</div>
	<div>
		<br />
	</div>

	<div class="wrapper">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid service-box">
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue" id="form_wizard_1">
								<div class="portlet-title">


									<div class="Row">
										<div id="dataCoverageErrorMsg" class="errorMessage"></div>
										<div class="ColumnCommonvendortab3"
											style="padding-left: 40px;">
											<div class="control-group">
												<label class="control-labelaligndatacoverage"><font color="black">Solution Name</font><span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="solutionName"
														placeholder="Solution Name" name="solutionName"
														class="m-wrap largeval" style="padding-left: 47px;" onblur="validateAjax(this, 'checkExistingValue','signupSolutionErrorMsg','vendorsolutions')"/>
													<div>
													<label id="signupSolutionErrorMsg" class="errorMessage"></label>
													</div>	
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelaligndatacoverage"
													class="selectvalues"><font color="black">Description</font><span
													class="required">*</span></label>
												<div class="controls">
													<textarea id="vendorSolutionSescription" data-mandatory="Y"
														placeholder="Description" name="filebriefdesc" cols="10"
														rows="3" style="width: 160px;"validateNotNull(this, 'vendorfilebriefdescErrorMsg')" ></textarea>
													<div>
														<label id="vendorfilebriefdescErrorMsg"
															class="errorMessage"></label>
													</div>
												</div>

											</div>
										</div>


										<div class="ColumnCommonvendorpagetab3">

											<div class="control-group">
												<label class="control-labelaligndatacoverage"
													style="padding-left: 47px;"><font color="black">Solution
														Type</font><span class="required">*</span></label>
												<div class="controls">
													<select name="vendorSolutionTypes" id="vendorSolutionTypes">
														<option value="Data Aggregator vendor">Data
															Aggregator vendor</option>
														<option value="Trading Application vendor">Trading
															Application vendor</option>
														<option value="Analytics Application vendor">Analytics
															Application vendor</option>
														<option value="Research Reporting vendor">Research
															Reporting vendor</option>
													</select>
												</div>
											</div>


										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<div class="se" style="margin: 0px 0px 0px 232px;">
												<a class="addMoreSolution"> <span
													class="lable_header_add" onclick="addVendorSolution()">Add More </span></a>
											</div>
										</div>
									</div>
								</div>
								<div class="portlet-body">
									<table class="table table-striped" id="vendorSolutionTable">
										<thead style="background-color: #7BCCA5;">
											<tr>
												<th>Solution Name</th>
												<th>Solution type</th>
												<th>Description</th>
												<th>#</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>


									<input type="hidden" id="jsontablevendorofferingdistribution"
										name="jsontablevendorofferingdistribution" />
								</div>
								<!-- Vendor Data Distribution start here -->



							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<!-- END CONTAINER -->
		<div class="footer_area">
			<jsp:include page="common/footer.jsp"></jsp:include>
		</div>
		<script type="text/javascript"
			src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
		<script type="text/javascript">
 $(document).ready(function() {
	 debugger;
	/*  $("#asdataaggregatorvendor").slideDown("slow");
	$("#astradingapplicationvendor").hide();
	$("#asanalyticsapplicationvendor").hide();
	$("#asresearchreportingvendor").hide();
	 */
	changeTabMode();
	listVendorSolution();
	 
});
	
</script>
</body>
</html>
=======
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.finvendor.util.RequestConstans"%>

<c:set var="vendormyofferingsdatacoverage"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATACOVERAGE %>">
</c:set>
<c:set var="vendormyofferingsdatadistribution"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATA_DISTEIBUTION %>">
</c:set>

<c:set var="tradingcapabilitiessupported"
	value="<%=RequestConstans.Vendor.TRADING_CAPABILITIES_SUPPORTED %>">
</c:set>
<c:set var="tradingsoftwaredetails"
	value="<%=RequestConstans.Vendor.TRADING_SOFTWARE_DETAILS %>"></c:set>

<c:set var="analyticsfeaturessupported"
	value="<%=RequestConstans.Vendor.ANALYTICS_FEATURES_SUPPORTED %>">
</c:set>
<c:set var="analyticssoftwaredetails"
	value="<%=RequestConstans.Vendor.ANALYTICS_SOFTWARE_DETAILS %>"></c:set>

<c:set var="researchcoverage"
	value="<%=RequestConstans.Vendor.RESEARCH_COVERAGE %>">
</c:set>
<c:set var="researchdetails"
	value="<%=RequestConstans.Vendor.RESEARCH_DETAILS %>"></c:set>
<c:set var="analystprofile"
	value="<%=RequestConstans.Vendor.ANALYTST_PROFILE %>">
</c:set>

<c:set var="vendormyofferingsasaggregatorvendor"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_DATA_AGGREGATOR_VENDOR %>">
</c:set>
<c:set var="vendormyofferingsastradingapplicationvendor"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_TRADING_APPLICATION_VENDOR %>">
</c:set>
<c:set var="vendormyofferingsasanalyticsapplicationvendor"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_ANALYTICS_APPLICATION_VENDOR %>">
</c:set>
<c:set var="vendormyofferingsasresearchreportingvendor"
	value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_RESEARCH_REPORTING_VENDOR %>">
</c:set>

<c:set var="dataaggregator" value="${dataaggregator}"></c:set>
<c:set var="tradingapplication" value="${tradingapplication}"></c:set>
<c:set var="analyticsapplication" value="${analyticsapplication}"></c:set>
<c:set var="researchreport" value="${researchreport}"></c:set>

<!DOCTYPE html>
<head>
<meta charset="utf-8" />
<title>finvendor</title>
<link
	href="<%=request.getContextPath() %>/resources/css/singleasset.css"
	type="text/css" rel="stylesheet" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/resources/css/reset.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath() %>/resources/css/style-metro.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/resources/css/style-II.css"
	rel="stylesheet" type="text/css" />
<!-- Date Picker CSS Starts -->
<link
	href="<%=request.getContextPath() %>/resources/datepick/jsDatePick_ltr.min.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="<%=request.getContextPath() %>/resources/css/finvendor.css">
<script	src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
<!-- Date Picker CSS Ends -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div class="top_header">
		<jsp:include page="common/dashboardheader.jsp"></jsp:include>
	</div>
	<div>
		<br />
	</div>

	<div class="wrapper">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid service-box">
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue" id="form_wizard_1">
								<div class="portlet-title">


									<div class="Row">
										<div id="dataCoverageErrorMsg" class="errorMessage"></div>
										<div class="ColumnCommonvendortab3"
											style="padding-left: 40px;">
											<div class="control-group">
												<label class="control-labelaligndatacoverage"><font color="black">Solution Name</font><span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="solutionName"
														placeholder="Solution Name" name="solutionName"
														class="m-wrap largeval" style="padding-left: 47px;" onblur="validateAjax(this, 'checkExistingValue','signupSolutionErrorMsg','vendorsolutions')"/>
													<div>
													<label id="signupSolutionErrorMsg" class="errorMessage"></label>
													</div>	
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelaligndatacoverage"
													class="selectvalues"><font color="black">Description</font><span
													class="required">*</span></label>
												<div class="controls">
													<textarea id="vendorSolutionSescription" data-mandatory="Y"
														placeholder="Description" name="filebriefdesc" cols="10"
														rows="3" style="width: 160px;"validateNotNull(this, 'vendorfilebriefdescErrorMsg')" ></textarea>
													<div>
														<label id="vendorfilebriefdescErrorMsg"
															class="errorMessage"></label>
													</div>
												</div>

											</div>
										</div>


										<div class="ColumnCommonvendorpagetab3">

											<div class="control-group">
												<label class="control-labelaligndatacoverage"
													style="padding-left: 47px;"><font color="black">Solution
														Type</font><span class="required">*</span></label>
												<div class="controls">
													<select name="vendorSolutionTypes" id="vendorSolutionTypes">
														<option value="Data Aggregator vendor">Data
															Aggregator vendor</option>
														<option value="Trading Application vendor">Trading
															Application vendor</option>
														<option value="Analytics Application vendor">Analytics
															Application vendor</option>
														<option value="Research Reporting vendor">Research
															Reporting vendor</option>
													</select>
												</div>
											</div>


										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<div class="se" style="margin: 0px 0px 0px 232px;">
												<a class="addMoreSolution"> <span
													class="lable_header_add" onclick="addVendorSolution()">Add More </span></a>
											</div>
										</div>
									</div>
								</div>
								<div class="portlet-body">
									<table class="table table-striped" id="vendorSolutionTable">
										<thead style="background-color: #7BCCA5;">
											<tr>
												<th>Solution Name</th>
												<th>Solution type</th>
												<th>Description</th>
												<th>#</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>


									<input type="hidden" id="jsontablevendorofferingdistribution"
										name="jsontablevendorofferingdistribution" />
								</div>
								<!-- Vendor Data Distribution start here -->



							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<!-- END CONTAINER -->
		<div class="footer_area">
			<jsp:include page="common/footer.jsp"></jsp:include>
		</div>
		<script type="text/javascript"
			src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
		<script type="text/javascript">
 $(document).ready(function() {
	 debugger;
	/*  $("#asdataaggregatorvendor").slideDown("slow");
	$("#astradingapplicationvendor").hide();
	$("#asanalyticsapplicationvendor").hide();
	$("#asresearchreportingvendor").hide();
	 */
	changeTabMode();
	listVendorSolution();
	 
});
	
</script>
</body>
</html>
>>>>>>> origin/master
