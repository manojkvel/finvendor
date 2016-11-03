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
	<title>Vendor - My Offerings</title>
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
			<jsp:include page="common/progressLoader.jsp"></jsp:include>
			<div class="tab-pane" id="tab1">
				<div id="data_aggregator_top_card">
					<div class="data_aggregator_info">
	
					</div>
					<a class="btn add_more">
						<span class="fa fa-pencil"></span>Add More
					</a>
				</div>
				<div id="data_aggregator" class="custom_form">
					<form name="data_aggregator_form" id="data_aggregator_form">
						<div class="generic_message">
							<div class="alert"></div>
						</div>
						<div class="product_info">
							<h3>Product Info <span class="fa fa-chevron-up"></span></h3>
							<input type="text" name="productId" id="productId" hidden="hidden" />
							<ul>
								<li>
									<input type="text" name="productName" id="productName" required />
									<label>Product Name</label>
								</li>
								<li>
									<input type="text" name="productDescription" id="productDescription" required />
									<label>Product Description</label>
								</li>
								<li>
									<input type="number" name="launchedYear" id="launchedYear" required />
									<label>Launched Year</label>
								</li>
								<li>
									<select class="selectpicker show-tick" id="assetClassId" name="assetClassId">
										<c:forEach var="assetClasses" items="${assetClasses}">
										<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
										</c:forEach>
									</select>
									<label class="default_select">Asset Class</label>
								</li>
								<li>
									<select class="selectpicker select_multiple" id="securityTypes" name="securityTypes" multiple="multiple">
										<c:forEach var="securityType" items="${securityTypes}">
										<option value="${securityType.name}">${securityType.name}</option>
										</c:forEach>
									</select>
									<label class="default_select">Security Type</label>
								</li>
							</ul>
						</div>
						<div class="data_coverage_info">
							<h3>Data Coverage Info <span class="fa fa-chevron-up"></span></h3>
							<ul>
								<li>
									<div>
										<select class="selectpicker show-tick" name="coverageRegion" id="coverageRegion">			
											<c:forEach var="regions" items="${regions}">
											<option value="${regions.region_id}">${regions.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Coverage Region</label>
									</div>
								</li>
								<li>
									<select class="selectpicker select_multiple" name="coverageCountry" 
									id="coverageCountry" multiple="multiple">
										<c:forEach var="countries" items="${countries}">
										<option value="${countries.country_id}" >${countries.name}</option>
										</c:forEach>
									</select>
									<label class="default_select">Coverage Country</label>
								</li>
								<li>
									<select class="selectpicker select_multiple" name="coverageExchange"  multiple="multiple" id="coverageExchange">
										<c:forEach var="exchanges" items="${exchanges}">
										<option value="${exchanges.exchange_id}">${exchanges.name}</option>
										</c:forEach>
									</select>
									<label class="default_select">Coverage Exchange</label>
								</li>
								<li>
									<input type="text" name="costRange" id="costRange" required />
									<label>Vendor Cost Range</label>
								</li>
								<li>
									<input type="text" name="email" id="email" required />
									<label>Primary Email</label>
								</li>
								<li>
									<input type="text" name="phoneNumber" id="phoneNumber" required />
									<label>Phone Number</label>
								</li>
							</ul>
						</div>
						<div class="data_distribution_info">
							<h3>Data Distribution Info <span class="fa fa-chevron-down"></span></h3>
							<ul>
								<li>
									<select class="selectpicker select_multiple" name="feedType" multiple="multiple" id="feedType">
										<option value ="EOD"> EOD </option>
										<option value ="REAL-TIME"> REAL-TIME </option>
										<option value ="HISTORICAL-EOD">HISTORICAL-EOD</option>
									</select>
									<label class="default_select">Feed Type</label>
								</li>

								<li>
									<select class="selectpicker select_multiple" name="feedSubType" multiple="multiple" id="feedSubType">
										<option value ="Full Universe Data Feed"> Full Universe Data Feed </option>
										<option value ="Delta Data Feed"> Delta Data Feed </option>
									</select>
									<label class="default_select">Feed Sub-type</label>
								</li>
								<li>
									<select class="selectpicker select_multiple" name="frequency" multiple="multiple" id="frequency">
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
									<select class="selectpicker select_multiple" name="distributionMethod" multiple="multiple" id="distributionMethod">
										<option value ="Web"> Web </option>
										<option value ="FTP"> FTP </option>
										<option value ="FTP"> Email </option>
										<option value ="Others"> Others </option>
									</select>
									<label class="default_select">Distribution Method</label>
								</li>
								<li>
									<input id="fileName" name="fileName" type="text"/>
									<label>File Name</label>
									<div class="Rowtableinfoval"><font id="myofferingsdatacoveragetabsucessmessage" style="font-size: 14px;font-family:Open Sans, sans-serif; position: absolute; color: #2AABAB; font-weight: bold;margin: -19px 0px 0px 15px;"></font>
										<div class="image-upload" style="margin-left: 252px;">
											<label class="control-label-fileupload" >Bulk Insert Option (using .CSV or .Xls)<span class="required">*</span> 
												<a href="<%=request.getContextPath()%>/<%=RequestConstans.Document.DOCUMENT_DOWNLOAD%>?RaYuL=${'/files/SupportDetails_insurance_sample.csv'}&VeMu=${path}" target="_blank"> <img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/></a>&nbsp;
												OR &nbsp;<a href="<%=request.getContextPath()%>/<%=RequestConstans.Document.DOCUMENT_DOWNLOAD%>?RaYuL=${'/files/SupportDetails_insurance_sample.csv'}&VeMu=${path}" target="_blank"><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /></a> &nbsp;&nbsp;&nbsp;&nbsp; 
											</label>
										</div>
										<div class="ColumnCommonray">
											<a class="#" data-toggle="modal" href="#normalModal"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Bulk Upload here</span></a>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<p class="action_btn">
							<a class="submit_btn save" data-toggle="tab">Save</a>
							<a class="submit_btn next" data-toggle="tab" href="#tab3">Next</a>
						</p>
					</form>
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

										<div id="normalModal" class="modal fade">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4 class="modal-title">Upload Your File</h4>
													</div>
													<div class="modal-body">
														<input type="file" name="fileUpload" class="m-wrap largefileuploadconspopup" id="fileUploadmyoffercoverage">&nbsp;&nbsp;&nbsp;&nbsp;<a class="fileupmyoffercoverage btnpopup btn-default">Upload</a>
														<div id="selectedFiles"></div>
														<div><br></div>
														<a class="btnpopup btn-default" onclick="loadCheckBoxes();">Remove</a> 
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
													</div>
												</div> 
											</div> 
										</div> 
	
  	<script type="text/javascript" 	src="<%=request.getContextPath() %>/resources/js/finvendorValidation.js"></script>
    <link rel="stylesheet" src="<%=request.getContextPath() %>/resources/css/vendor.css"/>
    
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
    <script type="text/javascript">
    	$(document).ready( function () {
    		listDataAggregatorOffering();
    	});
    </script>
</body>
</html>