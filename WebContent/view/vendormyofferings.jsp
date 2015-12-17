<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>

<c:set var="vendormyofferingsdatacoverage" value="<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATACOVERAGE %>"> </c:set>
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
    <meta charset="utf-8" />
	<title>finvendor</title>
    <link href="<%=request.getContextPath() %>/resources/css/singleasset.css" type="text/css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/resources/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/resources/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/resources/css/reset.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/resources/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/resources/css/style-II.css" rel="stylesheet" type="text/css"/>
    <!-- Date Picker CSS Starts -->
    <link href="<%=request.getContextPath() %>/resources/datepick/jsDatePick_ltr.min.css" rel="stylesheet" type="text/css"/>
    <!-- Date Picker CSS Ends -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div class="top_header">
			<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
		</div>
		<div><br/></div>
		<div class="wrapper">
			<div class="Rowtable"> 
			     <c:if test="${not empty dataaggregator}"><div class="ColumnCommonmyprofile" id="changeraymyofferings1"> <div class="lable_header" id="interdivraymyofferings1"> <a id="anchoraymyooferings1" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeTabModeForMyOfferings('${vendormyofferingsasaggregatorvendor}');">As Data Aggregator vendor</a> </div></div></c:if>
				 <c:if test="${not empty tradingapplication}"> <div class="ColumnCommonmyprofile" id="changeraymyofferings2"> <div class="lable_header" id="interdivraymyofferings2">  <a id="anchoraymyooferings2" href="#tab2" class="lable_header" data-toggle="tab" onclick="activeTabModeForMyOfferings('${vendormyofferingsastradingapplicationvendor}');">As Trading Application vendor</a> </div> </div></c:if>
				 <c:if test="${not empty analyticsapplication}"><div class="ColumnCommonmyprofile" id="changeraymyofferings3"> <div class="lable_header" id="interdivraymyofferings3">  <a id="anchoraymyooferings3" href="#tab3" class="lable_header" data-toggle="tab" onclick="activeTabModeForMyOfferings('${vendormyofferingsasanalyticsapplicationvendor}');">As Analytics Application vendor</a> </div> </div></c:if>
				 <c:if test="${not empty researchreport}"><div class="ColumnCommonmyprofile" id="changeraymyofferings4"> <div class="lable_header" id="interdivraymyofferings4">  <a id="anchoraymyooferings4" href="#tab4" class="lable_header" data-toggle="tab" onclick="activeTabModeForMyOfferings('${vendormyofferingsasresearchreportingvendor}');">As Research Reporting vendor</a> </div> </div></c:if>
			</div>
	   </div>
		 
    <div class="wrapper">  
        <div class="row-fluid">
					<div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							<input type="hidden" name="dataaggregator" id="dataaggregator" value="${dataaggregator}">
							<input type="hidden" name="tradingapplication" id="tradingapplication" value="${tradingapplication}">
							<input type="hidden" name="analyticsapplication" id="analyticsapplication" value="${analyticsapplication}">
							<input type="hidden" name="researchreport" id="researchreport" value="${researchreport}">
							<div class="portlet-body form" id="asdataaggregatorvendor">
								<form action="#" class="form-horizontal" id="submit_form" method="post">
									<div class="form-wizard">
										<div class="Rowtable">
											<div class="ColumnCommonmyofferings" id="changeoffer"> <div class="lable_header" id="interdivoffer"> <a id="anchooffer" href="#tab5" class="lable_header" data-toggle="tab"  onclick="activeModeVendorMyofferings('${vendormyofferingsdatacoverage}');">Data Coverage</a> </div></div>
											<div class="ColumnCommonmyofferings" id="changeoffer1"> <div class="lable_header" id="interdivoffer1">  <a id="anchooffer1" href="#tab6" class="lable_header" data-toggle="tab" onclick="activeModeVendorMyofferings('${vendormyofferingsdatadistribution}');">Data Distribution</a> </div> </div>
										</div>
										  <div class="tab-content" style="background-color: white;">
										<!-- vendor data coverage start here -->
										<div class="tab-pane active" id="tab5">
												<div><br/> </div>
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
												<div id="normalModal" class="modal fade">
													  <div class="modal-dialog">
													    <div class="modal-content">
													      <div class="modal-header">
													        <button type="button" class="close" data-dismiss="modal">&times;</button>
													        <h4 class="modal-title btpopup">Upload Your File</h4>
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
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Coverage Region<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoverageregion" multiple="multiple" id="supportcoverageregion">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														 	<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 45px;">Coverage Country<span class="required">*</span></label>
														<div class="controls">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Support Timings<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<c:forEach var="supports" items="${supports}">
																    	<option value="${supports.name}">${supports.name}</option>
																 	</c:forEach>
														</select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 32px;">Vendor Cost Range<span class="required">*</span></label>
														<div class="controls">
															<select name="vendorcostrange" multiple="multiple" id="vendorcostrange">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<c:forEach var="costs" items="${costs}">
																    	<option value="${costs.range}">${costs.range}</option>
																 	</c:forEach>
														</select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpretab3">
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 66px;">Phone Number<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="phonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 68px;">Email<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="email" placeholder="Email" name="email" class="m-wrap largeval"/>
														</div>
													</div>
													</div> 
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Coverage Region</th>
															<th>Coverage Country</th>
															<th>Support Timings</th>
															<th>Vendor Cost Range</th>
											                <th>Phone Number</th>
															<th>Email</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a class="btn" data-toggle="tab" class="step" onclick="updateVendorOfferingDataCoverageInfo();" style="margin-left: 157px;">Update</a>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab6" class="btn button-next" data-toggle="tab" class="step" onclick="activeModeVendorMyofferings('${vendormyofferingsdatadistribution}');">Continue</a>
									</div>
									</div>
											</div>
											
											 <!-- Vendor Data Distribution start here -->
											 
												<div class="tab-pane" id="tab6">
												<div><br/> </div>
												<div class="Rowtableinfoval">
												 <div class="image-upload" style="margin-left: 252px;">
												    <label class="control-label-fileupload" >Bulk Insert Option (using .CSV or .Xls)<span class="required">*</span> 
												        <a href="<%=request.getContextPath()%>/<%=RequestConstans.Document.DOCUMENT_DOWNLOAD%>?RaYuL=${'/files/SupportDetails_insurance_sample.csv'}&VeMu=${path}" target="_blank"> <img src="<%=request.getContextPath() %>/resources/images/csv.png" style="padding:0px 0px 4px 0px;"/></a>&nbsp;
												         OR &nbsp;<a href="<%=request.getContextPath()%>/<%=RequestConstans.Document.DOCUMENT_DOWNLOAD%>?RaYuL=${'/files/SupportDetails_insurance_sample.csv'}&VeMu=${path}" target="_blank"><img src="<%=request.getContextPath() %>/resources/images/xls.png" style="padding:0px 0px 4px 0px;" /></a> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												</div>
												<div class="ColumnCommonray">
												<a class="#" data-toggle="modal" href="#normalModalMyOfferings"><span class="lable_headeractions"><img src="<%=request.getContextPath() %>/resources/images/attachment.png"/>Bulk Upload here</span></a>
												</div>
												<div id="normalModalMyOfferings" class="modal fade">
													  <div class="modal-dialog">
													    <div class="modal-content">
													      <div class="modal-header">
													        <button type="button" class="close" data-dismiss="modal">&times;</button>
													        <h4 class="modal-title btpopup">Upload Your File</h4>
													      </div>
													      <div class="modal-body">
													         <input type="file" name="fileUploadmyofferdistribution" class="m-wrap largefileuploadconspopup" id="fileUploadmyofferdistribution">&nbsp;&nbsp;&nbsp;&nbsp;<a class="fileupmyofferdistribution btnpopup btn-default">Upload</a>
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
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendordist">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 76px;">Asset Class<span class="required">*</span></label>
														<div class="controls">
															<select name="datadistassetclass" onchange="loadSecurityDistriTypes(this.value);" id="datadistassetclass">
															     <option value ="-SELECT-"> -SELECT- </option>
															     <c:forEach var="assetClasses" items="${assetClasses}">
															    	<option value="${assetClasses.description}">${assetClasses.description}</option>
															 	 </c:forEach>
															 </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 73px;">Security type<span class="required">*</span></label>
														<div class="controls" style="margin-left:175px;">
															<select name="datadissecuritytype"  id="assetClassVendorSecurityDistriMaps" multiple="multiple">
															  	 <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="assetClassVendorSecurityMaps" items="${assetClassVendorSecurityDistriMaps}">
																    <option value="${assetClassVendorSecurityMaps.securityType.name}">${assetClassVendorSecurityMaps.securityType.name}</option>
																  </c:forEach>
															 </select>
															 <div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 75px;">File-name<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 74px;">File-Brief-Desc<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="filebriefdesc" placeholder="File-Brief-Desc" name="filebriefdesc" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpage">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 51px;">Feed Type<span class="required">*</span></label>
														<div class="controls">
															<select name="feedtype"  id="feedtype">
															<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="EOD"> EOD </option>
															     <option value ="REAL-TIME"> REAL-TIME </option>
															     <option value ="HISTORICAL-EOD">HISTORICAL-EOD</option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 50px;">Feed Sub-type<span class="required">*</span></label>
														<div class="controls">
															<select name="feedsubtype"  id="feedsubtype">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="Full Universe Data Feed"> Full Universe Data Feed </option>
															     <option value ="Delta Data Feed"> Delta Data Feed </option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 45px;">Distribution Method<span class="required">*</span></label>
														<div class="controls">
															<select name="distributionmethod"  id="distributionmethod">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="Web"> Web </option>
															     <option value ="FTP"> FTP </option>
															     <option value ="FTP"> Email </option>
															     <option value ="Others"> Others </option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Frequency<span class="required">*</span></label>
														<div class="controls">
															<select name="frequency"  id="frequency">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="intra-day"> intra-day </option>
															     <option value ="Daily"> Daily </option>
															     <option value ="Weekly"> Weekly </option>
															     <option value ="Semi-Monthly"> Semi-Monthly </option>
															     <option value ="Monthly"> Monthly </option>
															     <option value ="Yearly"> Yearly </option>
														</select>
														</div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpre">
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 44px;">Coverage Region<span class="required">*</span></label>
														<div class="controls">
															<select name="coverageregion" multiple="multiple" id="coverageregion">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														 	<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Coverage Country<span class="required">*</span></label>
														<div class="controls">
															<select name="coveragecountry" multiple="multiple" id="coveragecountry">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 41px;">Coverage Exchange<span class="required">*</span></label>
														<div class="controls">
															<select name="coverageexchange"  multiple="multiple" id="coverageexchange">
														     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														     <c:forEach var="exchanges" items="${exchanges}">
															    <option value="${exchanges.name}">${exchanges.name}</option>
															  </c:forEach>
														  </select>
														  <div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													 
													</div> 
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotabledistribution"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="sampledistribution">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Asset Class</th>
															<th>Security type</th>
															<th>File Name</th>
											                <th>File Brief Desc</th>
															<th>Feed Type</th>
															<th>Feed Sub-type</th>
															<th>Distribution Method</th>
															<th>Frequency</th>
															<th>Coverage Region</th>
															<th>Coverage Country</th>
															<th>Coverage Exchange</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablevendorofferingdistribution" name="jsontablevendorofferingdistribution"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab5" class="btn button-previous" data-toggle="tab" class="step" onclick="activeModeVendorMyofferings('${vendormyofferingsdatacoverage}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
									
										</div> 
										
								</div>
								
								
								</form>
							</div>
							
							<div class="portlet-body form" id="astradingapplicationvendor">
							<form action="#" class="form-horizontal" id="submit_form" method="post">
								<div class="form-wizard">
									<div class="Rowtable">
										<div class="ColumnCommonmyofferings" id="changetradingvendoroffer"> <div class="lable_header" id="intertradingvendodivoffer"> <a id="anchootradingvendoffer" href="#tab7" class="lable_header" data-toggle="tab"  onclick="activeTradingVendorMyofferings('${tradingcapabilitiessupported}');">Trading Capabilities Supported</a> </div></div>
										<div class="ColumnCommonmyofferings" id="changetradingvendoroffer1"> <div class="lable_header" id="intertradingvendodivoffer1">  <a id="anchootradingvendoffer1" href="#tab8" class="lable_header" data-toggle="tab" onclick="activeTradingVendorMyofferings('${tradingsoftwaredetails}');">Trading Software Details</a> </div> </div>
									</div>
									<div class="tab-content" style="background-color: white;">
									
										<!-- vendor Trading Capabilities Supported start here -->
										<div class="tab-pane active" id="tab7">
												<div><br/> </div>
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
												<div id="normalModal" class="modal fade">
													  <div class="modal-dialog">
													    <div class="modal-content">
													      <div class="modal-header">
													        <button type="button" class="close" data-dismiss="modal">&times;</button>
													        <h4 class="modal-title btpopup">Upload Your File</h4>
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
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3tradingapplication">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Asset Class<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 252px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Trade Asset Class1- </option>
															     <option value ="-SELECT-" > -Trade Asset Class2- </option>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Sub Asset Class<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry" style="width: 252px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Trade Sub Asset Class1- </option>
															     <option value ="-SELECT-" > -Trade Sub Asset Class2- </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Trade coverage region<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime" style="width: 252px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																<c:forEach var="regions" items="${regions}">
														    	<option value="${regions.name}">${regions.name}</option>
														 	 	</c:forEach>
														   </select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Trade Coverage country<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="vendorcostrange" multiple="multiple" id="vendorcostrange" style="width: 252px;">
														       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																<c:forEach var="countries" items="${countries}">
													    		<option value="${countries.name}">${countries.name}</option>
													  			</c:forEach>
														</select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Trade Coverage country<span class="required">*</span></label>
															<div class="controlsfortradingapp">
																<select name="vendorcostrange" multiple="multiple" id="vendorcostrange" style="width: 252px;">
															       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<c:forEach var="countries" items="${countries}">
														    		<option value="${countries.name}">${countries.name}</option>
														  			</c:forEach>
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpretab3tradinapp">
													    <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Trading Capabilities Type<span class="required">*</span></label>
															<div class="controlsfortradingapp">
																<select name="vendorcostrange" multiple="multiple" id="vendorcostrange" style="width: 252px;">
															       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															       <option value ="-SELECT-" > -Order placement/execution- </option>
															       <option value ="-SELECT-" > -Order allocation- </option>
															       <option value ="-SELECT-" > -Trade settlement- </option>
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Trade Executions Type<span class="required">*</span></label>
															<div class="controlsfortradingapp">
																<select name="vendorcostrange" multiple="multiple" id="vendorcostrange" style="width: 252px;">
															       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<option value ="-SELECT-" > -Algorithmic & Program Trading- </option>
															       <option value ="-SELECT-" > -Direct Market Access (DMA)- </option>
															       <option value ="-SELECT-"> -FX -DMA- </option>
															       <option value ="-SELECT-"> -Smart Order Routing (Sequential SOR)- </option>
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													 </div>
													 <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Algorithmic Trade Type<span class="required">*</span></label>
															<div class="controlsfortradingapp">
																<select name="vendorcostrange" multiple="multiple" id="vendorcostrange" style="width: 252px;">
															       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<option value ="-SELECT-" > -VWAP - (Volume Weighted Average Price) - </option>
															       <option value ="-SELECT-" > -TWAP - (Time Weighted Average Price) - </option>
															       <option value ="-SELECT-"> -PVOL - (Percentage Of Volume) - </option>
															       <option value ="-SELECT-"> -IMSH - (Implementation Shortfall) - </option>
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													 </div>
													    <div class="control-group">
															<label class="control-labelaligndatacoverage" style="padding-left: 32px;">Darkpool Access<span class="required">*</span></label>
															<div class="controlsfortradingapp" style="margin-left: 219px;">
																<select name="datacoveragecountry" multiple="multiple" id="datacoveragecountry" style="height: 100px;width: 252px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	  <option value ="-SELECT-"> Access to independent Darkpools (?) </option>
																	  <option value ="-SELECT-"> Access to Broker-dealer Darkpools (?) </option>
																	  <option value ="-SELECT-"> Access to Consortium-owned darkpools (?) </option>
																	  <option value ="-SELECT-"> Access to Exchange-owned darkpools (?) </option>
																	  <option value ="-SELECT-"> Access to Darkpool Aggregators(?) </option>
														   		</select>
														   		<div class="selectOptions">Choose one or more options</div>
															</div>
														 </div>
														 </div> 
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Asset Class</th>
															<th>Sub Asset Class</th>
															<th>Trade coverage region</th>
															<th>Trade Coverage country</th>
															<th>Trade coverage exchange</th>
															<th>Trading Capabilities Type</th>
															<th>Trade Executions Type</th>
											                <th>Algorithmic Trade Type</th>
															<th>Darkpool Access</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a class="btn" data-toggle="tab" class="step" onclick="updateVendorOfferingDataCoverageInfo();" style="margin-left: 157px;">Update</a>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab8" class="btn button-next" data-toggle="tab" class="step" onclick="activeTradingVendorMyofferings('${vendormyofferingsdatadistribution}');">Continue</a>
									</div>
									</div>
											</div>
											
											 <!-- Vendor Trading Capabilities Supported ends here -->
											 
											 <!-- Vendor Trading Software Details starts here -->
												<div class="tab-pane" id="tab8">
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendordist">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Asset Class<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 163px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Trade Asset Class1- </option>
															     <option value ="-SELECT-" > -Trade Asset Class2- </option>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 71px;">Sub Asset Class<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry" style="width: 163px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Trade Sub Asset Class1- </option>
															     <option value ="-SELECT-" > -Trade Sub Asset Class2- </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">App.. Name<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">App. Desc<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 71px;">Tradable regions<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="vendorsupporttime" multiple="multiple" id="vendorsupporttime" style="width: 163px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																<c:forEach var="regions" items="${regions}">
														    	<option value="${regions.name}">${regions.name}</option>
														 	 	</c:forEach>
														   </select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Tradable Markets<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Accessibility (?)<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="accessibility"  id="accessibility" style="width: 163px;">
															<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="Web/Browser Based"> Web/Browser Based </option>
															     <option value ="Binaries(Executable) Based"> Binaries(Executable) Based </option>
															     <option value ="Dedicated Desktop">Dedicated Desktop</option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Suitability<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="feedsubtype"  id="feedsubtype" style="width: 163px;">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="All Users"> All Users </option>
															     <option value ="Advanced shares traders"> Advanced shares traders </option>
															     <option value ="Technical chart users"> Technical chart users </option>
															     <option value ="Forex specialists"> Forex specialists </option>
														</select>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpage" style="margin: 0px 0px 0px 56px;">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Cost Type<span class="required">*</span></label>
														<div class="controls">
															<select name="distributionmethod"  id="distributionmethod" style="width: 163px;">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="Free and Min Balance Not required"> Free and Min Balance Not required </option>
															     <option value ="Min Balance Required"> Min Balance Required </option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Platform  CCY<span class="required">*</span></label>
														<div class="controls">
															<select name="frequency"  id="frequency" style="width: 163px;">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="GBP "> GBP  </option>
															     <option value ="USD"> USD </option>
															     <option value ="EUR"> EUR </option>
															     <option value ="INR"> INR </option>
															     <option value ="SGD"> SGD </option>
															     <option value ="AUD"> AUD </option>
															     <option value ="CHF"> CHF </option>
															     <option value ="CAD"> CAD </option>
															     <option value ="JPY"> JPY </option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Platform  Cost<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													  <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Platform type<span class="required">*</span></label>
														<div class="controls">
															<select name="frequency"  id="frequency" style="width: 163px;">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="Per Month "> Per Month  </option>
															     <option value ="Per week"> Per week </option>
															     <option value ="Per Yea"> Per Yea </option>
															     <option value ="Per 6 months"> Per 6 months </option>
															     <option value ="Others"> Others </option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Exchange fees<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Stre.. news?<span class="required">*</span></label>
														<div class="controls">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">charts avai?<span class="required">*</span></label>
														<div class="controls">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Order Type <span class="required">*</span></label>
														<div class="controls">
															<select name="frequency"  id="frequency" multiple="multiple" style="width: 163px;">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="Limits"> Limits  </option>
															     <option value ="market orders"> market orders </option>
															     <option value ="tranche (iceberg) orders"> tranche (iceberg) orders </option>
															     <option value ="fill or kill"> fill or kill </option>
															     <option value ="execute and eliminate"> execute and eliminate </option>
															     <option value ="date"> date  </option>
															     <option value ="day"> day  </option>
															     <option value ="good for auction"> good for auction  </option>
														</select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Price Alerts?<span class="required">*</span></label>
														<div class="controls">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Watchlist?<span class="required">*</span></label>
														<div class="controls">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													</div>
													
													 <div class="ColumnCommonvendorpre">
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Trading Cap..<span class="required">*</span></label>
														<div class="controls">
															<select name="coveragecountry"  id="coveragecountry" style="width: 163px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Trade Exec..<span class="required">*</span></label>
														<div class="controls">
															<select name="coverageregion"  id="coverageregion" style="width: 163px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															    <option value ="Trade Executions Supported" > -Trade Executions Supported- </option> 
															    <option value ="Trade Executions Supported" > -Trade Executions Supported- </option>
														 	</select> 
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Trade Type<span class="required">*</span></label>
														<div class="controls">
															<select name="coverageexchange"  id="coverageexchange" style="width: 163px;">
														     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														      <option value ="Algorithmic Trade Type" > -Algorithmic Trade Type- </option>
														      <option value ="Algorithmic Trade Type" > -Algorithmic Trade Type- </option>
														  </select>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Dark venues<span class="required">*</span></label>
														<div class="controls">
															<select name="coverageexchange"  id="coverageexchange" style="width: 163px;">
														     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														      <option value ="Supported Darkpool venues" > -Supported Darkpool venues- </option>
														      <option value ="Supported Darkpool venues" > -Supported Darkpool venues- </option>
														  </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Add-Ons<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Ope.. system<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Launched Year<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Client Base<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													</div> 
													
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab7" class="btn button-previous" data-toggle="tab" class="step" onclick="activeTradingVendorMyofferings('${tradingcapabilitiessupported}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
									<!-- Vendor Trading Software Details ends here -->
								</div> 
										
								</div>
							</form>
							</div>
							<div class="portlet-body form" id="asanalyticsapplicationvendor">
								  <form action="#" class="form-horizontal" id="submit_form" method="post">
									<div class="form-wizard">
										<div class="Rowtable">
											<div class="ColumnCommonmyofferings" id="changeAnalyticsvendoroffer"> <div class="lable_header" id="interAnalyticsvendodivoffer"> <a id="anchooAnalyticsvendoffer" href="#tab9" class="lable_header" data-toggle="tab"  onclick="activeAnalyticsVendorMyofferings('${analyticsfeaturessupported}');">Analytics features Supported</a> </div></div>
											<div class="ColumnCommonmyofferings" id="changeAnalyticsvendoroffer1"> <div class="lable_header" id="interAnalyticsvendodivoffer1">  <a id="anchooAnalyticsvendoffer1" href="#tab10" class="lable_header" data-toggle="tab" onclick="activeAnalyticsVendorMyofferings('${analyticssoftwaredetails}');">Analytics Software Details</a> </div> </div>
										</div>
										<div class="tab-content" style="background-color: white;">
									
										<!-- vendor Analytics features Supported start here -->
										<div class="tab-pane active" id="tab9">
												<div><br/> </div>
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
												<div id="normalModal" class="modal fade">
													  <div class="modal-dialog">
													    <div class="modal-content">
													      <div class="modal-header">
													        <button type="button" class="close" data-dismiss="modal">&times;</button>
													        <h4 class="modal-title btpopup">Upload Your File</h4>
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
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3tradingapplication">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Analytics Solutions Type<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 252px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Alternative Investment Solutions- </option>
															     <option value ="-SELECT-" > -Banking Solutions- </option>
														 	</select> 
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendortab3analyticssolutiontab2">
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Analytics Solutions Sub Type<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry" style="width: 252px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Alternative and institutional investments- </option>
															     <option value ="-SELECT-" > -Commodity trading solutions- </option>
															     <option value ="-SELECT-" > -Custody management- </option>
															     <option value ="-SELECT-" > -FCP/CCP clearing suite- </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Analytics Solutions Type</th>
															<th>Analytics Solutions Sub Type</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a class="btn" data-toggle="tab" class="step" onclick="updateVendorOfferingDataCoverageInfo();" style="margin-left: 157px;">Update</a>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab10" class="btn button-next" data-toggle="tab" class="step" onclick="activeAnalyticsVendorMyofferings('${analyticssoftwaredetails}');">Continue</a>
									</div>
									</div>
											</div>
											
											 <!-- Vendor Analytics features Supported ends here -->
											 
											 <!-- Vendor Analytics Software Details starts here -->
												<div class="tab-pane" id="tab10">
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3analyticssolution">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Analytics Solutions Type<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 223px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Analytics Solutions Type- </option>
															     <option value ="-SELECT-" > -Analytics Solutions Type- </option>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Analytics Solutions Sub Type<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry" style="width: 223px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Analytics Solutions Sub Type- </option>
															     <option value ="-SELECT-" > -Analytics Solutions Sub Type- </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Application Name<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Application Brief Desc<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Accessibility (?)<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="vendorsupporttime" id="vendorsupporttime" style="width: 223px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														     	<option value ="-SELECT-" >-Web/Browser Based-</option>
														     	<option value ="-SELECT-" > -Binaries(Executable) Based- </option>
														   </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Suitability<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
														       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															   <option value ="-SELECT-" >-All Users-</option>
														       <option value ="-SELECT-" > -Risk Managers- </option>
														</select>
														</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Application Cost Type<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange"  id="vendorcostrange" style="width: 223px;">
															       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<option value ="-SELECT-" >-Free and Min Balance Not required-</option>
														            <option value ="-SELECT-" > -Min Balance Required- </option>
																</select>
															</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Application Subscription CCY<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<option value ="-SELECT-" >-GBP-</option>
														            <option value ="-SELECT-" > -USD- </option>
														            <option value ="-SELECT-" > -EUR- </option>
															</select>
															</div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpretab3analyticsapp">
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Application Subscription Cost<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													    <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Application Subscription type <span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															        <option value ="Per Month "> Per Month  </option>
																     <option value ="Per week"> Per week </option>
																     <option value ="Per Yea"> Per Yea </option>
																     <option value ="Per 6 months"> Per 6 months </option>
																     <option value ="Others"> Others </option>
															</select>
															</div>
													  </div>
													  <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Real time Market Data?<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Customizable Calculation Models(?)<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Add-Ons<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Operating system<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Software specifications<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Launched Year<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div> 
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Existing User Base<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>    
													</div>
													
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab9" class="btn button-previous" data-toggle="tab" class="step" onclick="activeAnalyticsVendorMyofferings('${analyticsfeaturessupported}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
									<!-- Vendor Analytics Software Details ends here -->
								</div> 
									</div>
								</form>
							</div>
							<div class="portlet-body form" id="asresearchreportingvendor">
								<form action="#" class="form-horizontal" id="submit_form" method="post">
									<div class="form-wizard">
										<div class="Rowtable">
											<div class="ColumnCommonmyofferings" id="changeresearchreportvendoroffer"> <div class="lable_header" id="interresearchreportvendodivoffer"> <a id="anchooresearchreportvendoffer" href="#tab11" class="lable_header" data-toggle="tab"  onclick="activeVendorAnalyticsResearchMyofferings('${researchcoverage}');">Research  Coverage</a> </div></div>
											<div class="ColumnCommonmyofferings" id="changeresearchreportvendoroffer1"> <div class="lable_header" id="interresearchreportvendodivoffer1">  <a id="anchooresearchreportvendoffer1" href="#tab12" class="lable_header" data-toggle="tab" onclick="activeVendorAnalyticsResearchMyofferings('${researchdetails}');">Research Details</a> </div> </div>
											<div class="ColumnCommonmyofferings" id="changeresearchreportvendoroffer2"> <div class="lable_header" id="interresearchreportvendodivoffer2">  <a id="anchooresearchreportvendoffer2" href="#tab13" class="lable_header" data-toggle="tab" onclick="activeVendorAnalyticsResearchMyofferings('${analystprofile}');">Analyst Profile</a> </div> </div>
										</div>
										<div class="tab-content" style="background-color: white;">
									
										<!-- vendor Research Coverage start here -->
										<div class="tab-pane active" id="tab11">
												<div><br/> </div>
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
												<div id="normalModal" class="modal fade">
													  <div class="modal-dialog">
													    <div class="modal-content">
													      <div class="modal-header">
													        <button type="button" class="close" data-dismiss="modal">&times;</button>
													        <h4 class="modal-title btpopup">Upload Your File</h4>
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
												</div>
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3tradingapplication">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Area<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 252px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Research Area- </option>
															     <option value ="-SELECT-" > -Research Area- </option>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Regions Covered<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 252px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Regions Covered- </option>
															     <option value ="-SELECT-" > -Regions Covered- </option>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Sub Area<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 252px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Research Sub Area- </option>
															     <option value ="-SELECT-" > -Research Sub Area- </option>
														 	</select> 
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendortab3analyticssolutiontab2">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Total Research Analyst<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div> 
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Prepared by CFA?<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div> 
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Existing Client Base<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>   
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotablesupport"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="samplesupport">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Research Area</th>
															<th>Regions Covered</th>
															<th>Research Sub Area</th>
															<th>Research Prepared by CFA?</th>
															<th>Total Research Analyst</th>
															<th>Existing Client Base</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="jsontablesupport" name="jsontablesupport"/>
											</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a class="btn" data-toggle="tab" class="step" onclick="updateVendorOfferingDataCoverageInfo();" style="margin-left: 157px;">Update</a>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab12" class="btn button-next" data-toggle="tab" class="step" onclick="activeVendorAnalyticsResearchMyofferings('${researchdetails}');">Continue</a>
									</div>
									</div>
											</div>
											
											 <!-- Vendor Research  Coverage ends here -->
											 
											 <!-- Vendor Research Details starts here -->
												<div class="tab-pane" id="tab12">
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3analyticssolution">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Area<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 223px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Research Area- </option>
															     <option value ="-SELECT-" > -Research Area- </option>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Research Sub Area<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry" style="width: 223px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Research Sub Area- </option>
															     <option value ="-SELECT-" > -Research Sub Area- </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Report Name<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Report Desc<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Regions Covered<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="vendorsupporttime" id="vendorsupporttime" style="width: 223px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														     	<option value ="-SELECT-" >-Regions Covered-</option>
														     	<option value ="-SELECT-" > -Regions Covered- </option>
														   </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Accessibility (?)<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="vendorsupporttime" id="vendorsupporttime" style="width: 223px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														     	<option value ="-SELECT-" >-Web/Browser Based-</option>
														     	<option value ="-SELECT-" > -Email- </option>
														   </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Suitability<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
														       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															   <option value ="-SELECT-" >-All Users-</option>
														       <option value ="-SELECT-" > -Risk Managers- </option>
														</select>
														</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Cost Type<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange"  id="vendorcostrange" style="width: 223px;">
															       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<option value ="-SELECT-" >-Free and Min Balance Not required-</option>
														            <option value ="-SELECT-" > -Min Balance Required- </option>
																</select>
															</div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpretab3analyticsapp">
													 <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Subscription CCY<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															       <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																	<option value ="-SELECT-" >-GBP-</option>
														            <option value ="-SELECT-" > -USD- </option>
														            <option value ="-SELECT-" > -EUR- </option>
															</select>
															</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Report Subscription Cost <span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													    <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Subscription type<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															        <option value ="Per Month "> Per Month  </option>
																     <option value ="Per week"> Per week </option>
																     <option value ="Per Yea"> Per Yea </option>
																     <option value ="Per 6 months"> Per 6 months </option>
																     <option value ="Others"> Others </option>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Format<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															        <option value ="Per Month "> PDF  </option>
																     <option value ="Per week"> DOC </option>
																     <option value ="Per Yea"> EXCEL </option>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Research Applicable Year<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															        <option value ="Per Month "> Research Applicable Year  </option>
																     <option value ="Per week"> Research Applicable Year </option>
																     <option value ="Per Yea"> Research Applicable Year </option>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Research Applicable Month<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															        <option value ="Per Month "> Research Applicable Month  </option>
																     <option value ="Per week"> Research Applicable Month </option>
																     <option value ="Per Yea"> Research Applicable Month </option>
															</select>
															</div>
													  </div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Existing User Base<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>    
													</div>
													
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab11" class="btn button-previous" data-toggle="tab" class="step" onclick="activeVendorAnalyticsResearchMyofferings('${researchcoverage}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step" onclick="activeVendorAnalyticsResearchMyofferings('${analystprofile}');">Continue</a>
									</div>
									</div>
									</div>
									<!-- Vendor Research Details ends here -->
									
									<!-- Vendor Analyst Profile starts here -->
									<div class="tab-pane" id="tab13">
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3analyticssolution">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Area<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="supportcoverageregion"  id="supportcoverageregion" style="width: 223px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Research Area- </option>
															     <option value ="-SELECT-" > -Research Area- </option>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Research Sub Area<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="supportcoveragecountry" multiple="multiple" id="supportcoveragecountry" style="width: 223px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="-SELECT-" > -Research Sub Area- </option>
															     <option value ="-SELECT-" > -Research Sub Area- </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Analyst Name<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Analyst with CFA?<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="filename" placeholder="File-name" name="filename" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													</div>
													
													 <div class="ColumnCommonvendorpretab3analyticsapp">
													    <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Analyst Region of Incorp<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="regions" items="${regions}">
																    	<option value="${regions.name}">${regions.name}</option>
																 	 </c:forEach>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Analyst Country of Incorp<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															         <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Analyst Year of Exp<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															         <option>Date (1970)</option>
																     <option>Last one week</option>
																     <option>Last one month</option>
																     <option>Last one year</option>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Analyst Awards<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="vendorcostrange" id="vendorcostrange" style="width: 223px;">
															        <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															        <c:forEach var="awards" items="${awards}">
																     <option value="${awards.award_id}">${awards.name}</option>
																 </c:forEach>
															</select>
															</div>
													  </div>
													</div>
													
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se">
										<a  href="#tab12" class="btn button-previous" data-toggle="tab" class="step" onclick="activeVendorAnalyticsResearchMyofferings('${researchdetails}');">Back</a>
										<input type="submit" value="Update" class="btn"/>
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
									<!-- Vendor Analyst Profileends here -->
								</div> 
								
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				</div>
				</div>
    </div>
    </div>
    <!-- END CONTAINER -->
    <div class="footer_area">
	  <jsp:include page="common/dashboardfooter.jsp" ></jsp:include>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
<script type="text/javascript">
 $(document).ready(function() {
	 $("#asdataaggregatorvendor").slideDown("slow");
	$("#astradingapplicationvendor").hide();
	$("#asanalyticsapplicationvendor").hide();
	$("#asresearchreportingvendor").hide();
	
	var vendorMyOfferingsDataCoverage= '<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATACOVERAGE %>';
	
	if(vendorMyOfferingsDataCoverage != null && vendorMyOfferingsDataCoverage.length > 0 && vendorMyOfferingsDataCoverage.match("vendormyofferingsdatacoverage")){
		document.getElementById('changeoffer').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivoffer').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooffer').style.backgroundColor = '#5CE5E5';
	}
	
	  var vendormyofferingsasaggregatorvendor = '<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_DATA_AGGREGATOR_VENDOR %>';
	  if(vendormyofferingsasaggregatorvendor != null && vendormyofferingsasaggregatorvendor.length > 0 && vendormyofferingsasaggregatorvendor.match("vendormyofferingsasaggregatorvendor")){
			document.getElementById('changeraymyofferings1').style.backgroundColor = '#5CE5E5';
			document.getElementById('interdivraymyofferings1').style.backgroundColor = '#5CE5E5';  
			document.getElementById('anchoraymyooferings1').style.backgroundColor = '#5CE5E5';
		}
});
	function activeTabModeForMyOfferings(tabname){
		var dataaggregator = document.getElementById("dataaggregator").value;
		var tradingapplication = document.getElementById("tradingapplication").value;
		var analyticsapplication = document.getElementById("analyticsapplication").value;
		var researchreport = document.getElementById("researchreport").value;
		if(tabname != '' && tabname.length > 0 && tabname.match("vendormyofferingsasaggregatorvendor")){
			$("#asdataaggregatorvendor").slideDown("slow");
			$("#astradingapplicationvendor").hide();
			$("#asanalyticsapplicationvendor").hide();
			$("#asresearchreportingvendor").hide();
			if(dataaggregator != '' && dataaggregator.length >0 && dataaggregator.match('Data Aggregator')){
				document.getElementById('changeraymyofferings1').style.backgroundColor = '#5CE5E5';
				document.getElementById('interdivraymyofferings1').style.backgroundColor = '#5CE5E5';  
				document.getElementById('anchoraymyooferings1').style.backgroundColor = '#5CE5E5';	
			}
			if(tradingapplication != '' && tradingapplication.length >0 && tradingapplication.match('Trading Application')){
				document.getElementById('changeraymyofferings2').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings2').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings2').style.backgroundColor = '';	
			}
			if(analyticsapplication != '' && analyticsapplication.length >0 && analyticsapplication.match('Analytics Application')){
				document.getElementById('changeraymyofferings3').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings3').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings3').style.backgroundColor = '';
			}
			if(researchreport != '' && researchreport.length >0 && researchreport.match('Research Report')){
				document.getElementById('changeraymyofferings4').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings4').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings4').style.backgroundColor = '';	
			}
			document.getElementById('changeoffer').style.backgroundColor = '#5CE5E5';
			document.getElementById('interdivoffer').style.backgroundColor = '#5CE5E5';  
			document.getElementById('anchooffer').style.backgroundColor = '#5CE5E5';
			
		}else if(tabname != '' && tabname.length > 0 && tabname.match("vendormyofferingsastradingapplicationvendor")){
			$("#asdataaggregatorvendor").hide();
			$("#astradingapplicationvendor").slideDown("slow");
			$("#asanalyticsapplicationvendor").hide();
			$("#asresearchreportingvendor").hide();
			
			if(dataaggregator != '' && dataaggregator.length >0 && dataaggregator.match('Data Aggregator')){
				document.getElementById('changeraymyofferings1').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings1').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings1').style.backgroundColor = '';	
			}
			if(tradingapplication != '' && tradingapplication.length >0 && tradingapplication.match('Trading Application')){
				document.getElementById('changeraymyofferings2').style.backgroundColor = '#5CE5E5';
				document.getElementById('interdivraymyofferings2').style.backgroundColor = '#5CE5E5';  
				document.getElementById('anchoraymyooferings2').style.backgroundColor = '#5CE5E5';
			}
			if(analyticsapplication != '' && analyticsapplication.length >0 && analyticsapplication.match('Analytics Application')){
				document.getElementById('changeraymyofferings3').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings3').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings3').style.backgroundColor = '';	
			}
			if(researchreport != '' && researchreport.length >0 && researchreport.match('Research Report')){
				document.getElementById('changeraymyofferings4').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings4').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings4').style.backgroundColor = '';
			}
			document.getElementById('changetradingvendoroffer').style.backgroundColor = '#5CE5E5';
			document.getElementById('intertradingvendodivoffer').style.backgroundColor = '#5CE5E5';  
			document.getElementById('anchootradingvendoffer').style.backgroundColor = '#5CE5E5';
			
		}else if(tabname != '' && tabname.length > 0 && tabname.match("vendormyofferingsasanalyticsapplicationvendor")){
			$("#asdataaggregatorvendor").hide();
			$("#astradingapplicationvendor").hide();
			$("#asanalyticsapplicationvendor").slideDown("slow");
			$("#asresearchreportingvendor").hide();
			
			if(dataaggregator != '' && dataaggregator.length >0 && dataaggregator.match('Data Aggregator')){
				document.getElementById('changeraymyofferings1').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings1').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings1').style.backgroundColor = '';
			}
			if(tradingapplication != '' && tradingapplication.length >0 && tradingapplication.match('Trading Application')){
				document.getElementById('changeraymyofferings2').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings2').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings2').style.backgroundColor = '';	
			}
			if(analyticsapplication != '' && analyticsapplication.length >0 && analyticsapplication.match('Analytics Application')){
				document.getElementById('changeraymyofferings3').style.backgroundColor = '#5CE5E5';
				document.getElementById('interdivraymyofferings3').style.backgroundColor = '#5CE5E5';  
				document.getElementById('anchoraymyooferings3').style.backgroundColor = '#5CE5E5';	
			}
			if(researchreport != '' && researchreport.length >0 && researchreport.match('Research Report')){
				document.getElementById('changeraymyofferings4').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings4').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings4').style.backgroundColor = '';
			}
			document.getElementById('changeAnalyticsvendoroffer').style.backgroundColor = '#5CE5E5';
			document.getElementById('interAnalyticsvendodivoffer').style.backgroundColor = '#5CE5E5';  
			document.getElementById('anchooAnalyticsvendoffer').style.backgroundColor = '#5CE5E5';
			
		}else if(tabname != '' && tabname.length > 0 && tabname.match("vendormyofferingsasresearchreportingvendor")){
			$("#asdataaggregatorvendor").hide();
			$("#astradingapplicationvendor").hide();
			$("#asanalyticsapplicationvendor").hide();
			$("#asresearchreportingvendor").slideDown("slow");
			
			if(dataaggregator != '' && dataaggregator.length >0 && dataaggregator.match('Data Aggregator')){
				document.getElementById('changeraymyofferings1').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings1').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings1').style.backgroundColor = '';	
			}
			if(tradingapplication != '' && tradingapplication.length >0 && tradingapplication.match('Trading Application')){
				document.getElementById('changeraymyofferings2').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings2').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings2').style.backgroundColor = '';	
			}
			if(analyticsapplication != '' && analyticsapplication.length >0 && analyticsapplication.match('Analytics Application')){
				document.getElementById('changeraymyofferings3').style.backgroundColor = '';
				document.getElementById('interdivraymyofferings3').style.backgroundColor = '';  
				document.getElementById('anchoraymyooferings3').style.backgroundColor = '';	
			}
			if(researchreport != '' && researchreport.length >0 && researchreport.match('Research Report')){
				document.getElementById('changeraymyofferings4').style.backgroundColor = '#5CE5E5';
				document.getElementById('interdivraymyofferings4').style.backgroundColor = '#5CE5E5';  
				document.getElementById('anchoraymyooferings4').style.backgroundColor = '#5CE5E5';	
			}
			document.getElementById('changeresearchreportvendoroffer').style.backgroundColor = '#5CE5E5';
			document.getElementById('interresearchreportvendodivoffer').style.backgroundColor = '#5CE5E5';  
			document.getElementById('anchooresearchreportvendoffer').style.backgroundColor = '#5CE5E5';
		}
		
	}
</script>

</body>
</html>
