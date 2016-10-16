<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<c:set var="vendormyofferingsdatacoverage" value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATACOVERAGE %>"> </c:set>
<c:set var="vendormyofferingsdatadictionary" value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATADICTIONARY %>"> </c:set>
<c:set var="vendormyofferingsdatadistribution" value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATA_DISTEIBUTION %>"> </c:set>
<c:set var="tradingcapabilitiessupported" value="<%=RequestConstans.Vendor.TRADING_CAPABILITIES_SUPPORTED %>"> </c:set>
<c:set var="tradingsoftwaredetails" value="<%=RequestConstans.Vendor.TRADING_SOFTWARE_DETAILS %>"></c:set>
<c:set var="analyticsfeaturessupported" value="<%=RequestConstans.Vendor.ANALYTICS_FEATURES_SUPPORTED %>"> </c:set>
<c:set var="analyticssoftwaredetails" value="<%=RequestConstans.Vendor.ANALYTICS_SOFTWARE_DETAILS %>"></c:set>
<c:set var="researchcoverage" value="<%=RequestConstans.Vendor.RESEARCH_COVERAGE %>"> </c:set>
<c:set var="researchdetails" value="<%=RequestConstans.Vendor.RESEARCH_DETAILS %>"></c:set>
<c:set var="analystprofile" value="<%=RequestConstans.Vendor.ANALYTST_PROFILE %>"> </c:set>
<c:set var="vendormyofferingsasaggregatorvendor" value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_DATA_AGGREGATOR_VENDOR %>"> </c:set>
<c:set var="vendormyofferingsastradingapplicationvendor" value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_TRADING_APPLICATION_VENDOR %>"> </c:set>
<c:set var="vendormyofferingsasanalyticsapplicationvendor" value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_ANALYTICS_APPLICATION_VENDOR %>"> </c:set>
<c:set var="vendormyofferingsasresearchreportingvendor" value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_RESEARCH_REPORTING_VENDOR %>"> </c:set>
<c:set var="dataaggregator" value="${dataaggregator}" ></c:set>
<c:set var="tradingapplication" value="${tradingapplication}"></c:set>
<c:set var="analyticsapplication" value="${analyticsapplication}"></c:set>
<c:set var="researchreport" value="${researchreport}"></c:set>
<!DOCTYPE html>
<head>
	<title>My Offerings</title>
	
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
    <style type="text/css">
    	#tab2, #tab3, #tab4 {
    		display: none;
    	}
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script type="text/javascript">
    	window.onload = function () {	
    		changeTabMode();
    	}
    </script>
</head>
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container">
		<div id="vendor_my_offerings">
			<ul class="nav nav-tabs">
				<li class="active">
					<a id="myofferings1" href="#tab1" data-toggle="tab" >
						Data Aggregator
					</a>
				</li>
				<li>
					<a id="myofferings2" href="#tab2" data-toggle="tab">
						Trading Application
					</a>
				</li>
				<li>
					<a id="myofferings3" href="#tab3" data-toggle="tab" >
						Analytics Application
					</a>
				</li>
				<li>
					<a id="myofferings4" href="#tab4" data-toggle="tab" >
						Research Reporting
					</a>
				</li>
			</ul>
			<div class="tab-pane" id="tab1">
				<div id="data_aggregator_top_card">
					<div class="data_aggregator_info">
					</div>
					<a class="btn" id="edit-details">
						<span class="fa fa-pencil"></span>Add More
					</a>
				</div>
				<div id="data_aggregator" class="custom_form">
					<div class="generic_message">
						<font id="personaltabsucessmessage"
						style="font-size: 14px; font-family: Open Sans, sans-serif; position: absolute; color: #2AABAB; font-weight: bold;"></font>
						<font id="personaltabfailuremessage"
						style="font-size: 14px; font-family: Open Sans, sans-serif; position: absolute; color: #B94A48; font-weight: bold;"></font>
					</div>
					<div class="product_info">
						<h3>Product Info <span class="fa fa-chevron-down"></span></h3>
						<ul>
							<li>
								<input type="text" name="productname" id="productname" required />
								<label>Product Name</label>
							</li>
							<li>
								<input type="text" name="productdescription" id="productdescription" required />
								<label>Product Description</label>
							</li>
							<li>
								<input type="text" name="tdsLaunchedYear" id="tdsLaunchedYear" required />
								<label>Launched Year</label>
							</li>
							<li>
								<select id="assetClassForVenderOffering" name="assetClassForVenderOffering">
									<c:forEach var="assetClasses" items="${assetClasses}">
									<option value="${assetClasses.description}">${assetClasses.description}</option>
									</c:forEach>
								</select>
								<label class="default_select">Asset Class</label>
							</li>
							<li>
								<select class="select_multiple" id="securityType" name="securityType" multiple="multiple">
									<c:forEach var="securityType" items="${securityTypes}">
									<option value="${securityType.name}">${securityType.name}</option>
									</c:forEach>
								</select>
								<label class="default_select">Security Type</label>
							</li>
						</ul>
					</div>
					<div class="data_coverage_info">
						<h3>Data Coverage Info <span class="fa fa-chevron-down"></span></h3>
						<ul>
							<li>
								<div>
									<select name="supportcoverageregion" id="supportcoverageregion" data-mandatory="Y" onchange="getCountries('supportcoverageregion','supportcoverageregion')">
										<option value="Select">-Select-</option>			
										<c:forEach var="regions" items="${regions}">
										<option value="${regions.name}">${regions.name}</option>
										</c:forEach>
									</select>
									<label class="default_select">Coverage Region</label>
								</div>
								<div style="margin-top:20px;">
									<select name="attributessupported" id="attributessupported">
										<c:forEach var="exchanges" items="${exchanges}">
										<option value="${exchanges.name}">${exchanges.name}</option>
										</c:forEach>
									</select>
									<label class="default_select">Attributes Supported</label>
								</div>
							</li>
							<li>
								<select class="select_multiple" name="supportcoveragecountry" 
								id="supportcoveragecountry" multiple="multiple">
									<c:forEach var="countries" items="${countries}">
									<option value="${countries.name}" >${countries.name}</option>
									</c:forEach>
								</select>
								<label class="default_select">Coverage Country</label>
							</li>
							<li>
								<select class="select_multiple" name="coverageexchange"  multiple="multiple" id="coverageexchange">
									<c:forEach var="exchanges" items="${exchanges}">
									<option value="${exchanges.name}">${exchanges.name}</option>
									</c:forEach>
								</select>
								<label class="default_select">Coverage Exchange</label>
							</li>
							<li>
								<input type="text" name="vendorcostrange" id="vendorcostrange" required />
								<label>Vendor Cost Range</label>
							</li>
							<li>
								<input type="text" name="email" id="email" required />
								<label>Primary Email</label>
							</li>
							<li>
								<input type="text" name="phonenumber" id="phonenumber" required />
								<label>Phone Number</label>
							</li>
						</ul>
					</div>
					<div class="data_distribution_info">
						<h3>Data Distribution Info <span class="fa fa-chevron-down"></span></h3>
						<ul>
							<li>
								<select class="select_multiple" name="feedtype" multiple="multiple" id="feedtype">
									<option value ="-SELECT-"> -SELECT- </option>
									<option value ="EOD"> EOD </option>
									<option value ="REAL-TIME"> REAL-TIME </option>
									<option value ="HISTORICAL-EOD">HISTORICAL-EOD</option>
								</select>
								<label class="default_select">Feed Type</label>
							</li>

							<li>
								<select class="select_multiple" name="feedsubtype" multiple="multiple" id="feedsubtype">
									<option value ="-SELECT-"> -SELECT- </option>
									<option value ="Full Universe Data Feed"> Full Universe Data Feed </option>
									<option value ="Delta Data Feed"> Delta Data Feed </option>
								</select>
								<label class="default_select">Feed Sub-type</label>
							</li>
							<li>
								<select class="select_multiple" name="frequency" multiple="multiple" id="frequency">
									<option value ="intra-day"> intra-day </option>
									<option value ="Daily"> Daily </option>
									<option value ="Weekly"> Weekly </option>
									<option value ="Semi-Monthly"> Semi-Monthly </option>
									<option value ="Monthly"> Monthly </option>
									<option value ="Yearly"> Yearly </option>
								</select>
								<label class="default_select">Frequency</label>
							</li>
							<li>
								<select class="select_multiple" name="distributionmethod" multiple="multiple" id="distributionmethod">
									<option value ="Web"> Web </option>
									<option value ="FTP"> FTP </option>
									<option value ="FTP"> Email </option>
									<option value ="Others"> Others </option>
								</select>
								<label class="default_select">Distribution Method</label>
							</li>
						</ul>
					</div>
					<p class="action_btn">
						<a class="submit_btn save" data-toggle="tab">Save</a>
						<a class="submit_btn next" data-toggle="tab" href="#tab3">Next</a>
					</p>
				</div>
			</div>
			<div class="tab-pane" id="tab2">
				Coming Soon
			</div>
			<div class="tab-pane" id="tab3">
				Coming Soon
			</div>
			<div class="tab-pane" id="tab4">
				Coming Soon
			</div>
		</div>
	</div>



    <!-- END CONTAINER -->
 
    <jsp:include page="common/footer.jsp"></jsp:include>
	
  	<script type="text/javascript" 	src="<%=request.getContextPath() %>/resources/js/finvendorValidation.js"></script>
    <link rel="stylesheet" src="<%=request.getContextPath() %>/resources/css/vendor.css"/>

</body>
</html>