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
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<div class="container">
			<div class="Rowtable"> 
			     <c:if test="${not empty dataaggregator}"><div class="ColumnCommonmyprofile" id="changeraymyofferings1"> <div class="lable_header" id="interdivraymyofferings1"> <a id="anchoraymyooferings1" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeTabModeForMyOfferings('${vendormyofferingsasaggregatorvendor}');">As Data Aggregator vendor</a> </div></div></c:if>
				 <c:if test="${not empty tradingapplication}"> <div class="ColumnCommonmyprofile" id="changeraymyofferings2"> <div class="lable_header" id="interdivraymyofferings2">  <a id="anchoraymyooferings2" href="#tab2" class="lable_header" data-toggle="tab" onclick="activeTabModeForMyOfferings('${vendormyofferingsastradingapplicationvendor}');">As Trading Application vendor</a> </div> </div></c:if>
				 <c:if test="${not empty analyticsapplication}"><div class="ColumnCommonmyprofile" id="changeraymyofferings3"> <div class="lable_header" id="interdivraymyofferings3">  <a id="anchoraymyooferings3" href="#tab3" class="lable_header" data-toggle="tab" onclick="activeTabModeForMyOfferings('${vendormyofferingsasanalyticsapplicationvendor}');">As Analytics Application vendor</a> </div> </div></c:if>
				 <c:if test="${not empty researchreport}"><div class="ColumnCommonmyprofile" id="changeraymyofferings4"> <div class="lable_header" id="interdivraymyofferings4">  <a id="anchoraymyooferings4" href="#tab4" class="lable_header" data-toggle="tab" onclick="activeTabModeForMyOfferings('${vendormyofferingsasresearchreportingvendor}');">As Research Reporting vendor</a> </div> </div></c:if>
			</div>
	   </div>
		 
    <div class="container">  
        <div class="row-fluid">
					<div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
						
							<input type="hidden" name="dataaggregator" id="dataaggregator" value="${dataaggregator}">
							<input type="hidden" name="tradingapplication" id="tradingapplication" value="${tradingapplication}">
							<input type="hidden" name="analyticsapplication" id="analyticsapplication" value="${analyticsapplication}">
							<input type="hidden" name="researchreport" id="researchreport" value="${researchreport}">
							<div class="portlet-body form" id="asdataaggregatorvendor">
								<form action="#" class="form-horizontal" id="submit_form" method="post">
									<div class="form-wizard">
										<div class="Rowtable">
											<div class="ColumnCommonmyofferings" id="changeoffer2"> <div class="lable_header" id="interdivoffer2"> <a id="anchooffer2" href="#tab20" class="lable_header" data-toggle="tab"  onclick="activeModeVendorMyofferings('${vendormyofferingsdatadictionary}');">Data Dictionary</a> </div></div>
											<div class="ColumnCommonmyofferings" id="changeoffer"> <div class="lable_header" id="interdivoffer"> <a id="anchooffer" href="#tab5" class="lable_header" data-toggle="tab"  onclick="activeModeVendorMyofferings('${vendormyofferingsdatacoverage}');">Data Coverage</a> </div></div>
											<div class="ColumnCommonmyofferings" id="changeoffer1"> <div class="lable_header" id="interdivoffer1">  <a id="anchooffer1" href="#tab6" class="lable_header" data-toggle="tab" onclick="activeModeVendorMyofferings('${vendormyofferingsdatadistribution}');">Data Distribution</a> </div> </div>
										</div>
										  <div class="tab-content" style="background-color: white;">
										  
										  
										  <!-- Start Data Dictionary  -->
										  <div class="tab-pane active" id="tab20" >
												
									     	<div id="container" style="float: left; width: 200px;">
												<ul>
												<c:forEach var="solution" items="${solutions}">
												   <li> <label  onclick="createOfferings('${solution.solution_id}')" class=""> ${solution.name}</label>
												    <ul>
													<c:forEach var="vendorOffering" items="${solution.vendorOffering}">
										 				<li> <label  onclick="displayOfferingFile('${vendorOffering.vendor_offering_id}')"> ${vendorOffering.name}</label> 
															<ul>
																<c:forEach var="offeringFiles" items="${vendorOffering.offeringFiles}">
																	
																	<li> <label onclick="displayFileFields(${offeringFiles.offeringFilesId})" > ${offeringFiles.fileName}</label></li>  
																	
																</c:forEach>
															</ul>
														</li>
													</c:forEach>
												  </ul>
												</li>
												</c:forEach>
												</ul>
											</div>
											<input type="hidden" name="selectedId" id="selectedId">
										            <br>
										 				<div class="col-md-9" id="solutionDetailList">
															<table class="table table-striped" id="vendorSolutionTable">
																<thead class="lable_header">
																	<tr>
																		<th>Solution Name</th>
																		<th>Solution type</th>
																		<th>Description</th>
																	</tr>
																</thead>
																<tbody>
															<c:forEach var="solution" items="${solutions}">
												   
															<tr>
																<td>${solution.name}</td>
																<td>${solution.solutionTypes.name}</td>
																<td>${solution.description}</td>
																
															</tr>
														</c:forEach>
																
																</tbody>
															</table>
															
														</div>	
										 
										 
										<div id="createOfferingDiv"  class="form-group">
										<fieldset>
										 <legend>Create Offerings</legend>
												<div class="form-group">
																								  
												  <label class="col-md-3 control-label" for="offeringName"><span class="required">*</span>Offering Name</label>  
												  <div class="col-md-3">
												  	<input id="offeringName" name="offeringName" type="text" placeholder="Offering Name" class="form-control input-md">
												  </div>
												  <label class="col-md-3 control-label" for="assetClassForVenderOffering">Asset Class</label>
												  <div class="col-md-3">
												    <select id="assetClassForVenderOffering" name="assetClassForVenderOffering" class="form-control">
												      <c:forEach var="assetClasses" items="${assetClasses}">
															    	<option value="${assetClasses.description}">${assetClasses.description}</option>
													  </c:forEach>
												    </select>
												  </div>
												</div>
												 
											    <div class="form-group">
												  
												   <label class="col-md-3 control-label" for="descriptionForVendorOffering">Description</label>
												  <div class="col-md-3">                     
												    <textarea class="form-control" id="descriptionForVendorOffering" name="descriptionForVendorOffering">Description</textarea>
												  </div>
												  
												  <label class="col-md-3 control-label" for="launchedYear">Launched Year</label>  
												  <div class="col-md-3">
												  	<input id="launchedYear" name="launchedYear" type="text" placeholder="Launched Year" class="form-control input-md" onblur="validateYear(this)">
												  </div>
												  
												 </div> 
											
												
												
											<div class="form-group">
											  <label class="col-md-3 control-label" for="button1id"></label>
											  <div class="col-md-8">
											    <a class="addMoreSolution"> <span class="lable_header_add" onclick="addVendorOfferingInfo()">Submit </span></a>
											    
											  </div>
											</div>
									
											<div class="col-md-11">
												<table class="table table-striped" id="vendorofferingTable">
													<thead class="lable_header">
														<tr>
															<th>Offering Name</th>
															<th>Description</th>
															<th>Asset Class</th>
															<th>Launched Year</th>
															
															<th>#</th>
									
														</tr>
													</thead>
													<tbody>
									
														<%-- <c:forEach var="vendorOfferinglist" items="${listOfferings}">
									
															<tr>
																<td>${vendorOfferinglist.solutions.name}</td>
																<td>${vendorOfferinglist.name}</td>
																<td>${vendorOfferinglist.description}</td>
																<td>${vendorOfferinglist.assetClass.description}</td>
																<td><a class="deleteButton"><Span class="lable_header_delete"
																	onclick="deleteRecordOffering(${vendorOfferinglist.vendor_offering_id})">Remove</Span></a></td>
																	
															</tr>
														</c:forEach>
									 --%>
													</tbody>
												</table>
											</div>
										</fieldset>
										</div>
										<div id="createfileDiv">
										<fieldset>
										 <legend>Create Files</legend>
												<div class="form-group">
												  
												  <label class="col-md-3 control-label" for="fileName"><span class="required">*</span>File Name</label>  
												  <div class="col-md-3">
												  	<input id="fileName" name="fileName" type="text" placeholder="File Name">
												  </div>
												 
										 <label class="col-md-3 control-label" for="securityType">Security Type</label>
												  <div class="col-md-3">
												    <select id="securityType" name="securityType" class="form-control">
												      
												      <c:forEach var="securityType" items="${securityTypes}">
														    <option value="${securityType.name}">${securityType.name}</option>
												    </c:forEach>
												      
												    </select>
												  </div>
											</div>	 
										
										     
										     <div class="form-group">
												  <label class="col-md-3 control-label" for="description">Description</label>
												  <div class="col-md-3">                     
												    <textarea class="form-control" id="description" name="description">Description</textarea>
												  </div>
												
										     </div>
										    
										   	<div class="form-group">
											  <label class="col-md-3 control-label" for="button1id"></label>
											  <div class="col-md-8">
											    <a class="addMoreSolution"> <span class="lable_header_add" onclick="createOfferingFile()">Submit </span></a>
											  </div>
											</div>
									 
									 
											<div class="col-md-11">
												<table class="table table-striped" id="offeringFilesTable">
													<thead class="lable_header">
														<tr>
															<th>File Name</th>
															<th>Description</th>
															<th>Security Type</th>
															<th>#</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
									     </fieldset>
										</div>
										
									
										
									
									<div id="createFieldsDiv">
									<fieldset>
										 <legend>Create Fields</legend>
												<div class="form-group">
													<label class="col-md-3 control-label" for="fieldName"><span class="required">*</span>Field Name</label>  
												  <div class="col-md-3">
												  	<input id="fieldName" name="fieldName" type="text" placeholder="Field Name" class="form-control input-md">
												  </div>
												  
												  	  <label class="col-md-3 control-label" for="fieldDescription">Description</label>
												  <div class="col-md-3">                     
												    <textarea class="form-control" id="fieldDescription" name="fieldDescription">Description</textarea>
												  </div>
											
												 </div>
												 
												 <div class="form-group">
													<label class="col-md-3 control-label" for="fieldIndex">Index</label>  
												  <div class="col-md-3">
												  	<input id="fieldIndex" name="fieldIndex" type="text" placeholder="Index" class="form-control input-md">
												  </div>
												  
												  	<label class="col-md-3 control-label" for="fieldMaxLength">Max Length</label>  
												  <div class="col-md-3">
												  	<input id="fieldMaxLength" name="fieldMaxLength" type="text" placeholder="Max Length" class="form-control input-md">
												  </div>
												  
												 </div>
										
												<div class="form-group">
													<label class="col-md-3 control-label" for="fieldFormat">Format</label>  
												  <div class="col-md-3">
												  	<input id="fieldFormat" name="fieldFormat" type="text" placeholder="Format" class="form-control input-md">
												  </div>
												  
												  	<label class="col-md-3 control-label" for="fieldDataType">Data Type</label>  
												  <div class="col-md-3">
												  	<input id="fieldDataType" name="fieldDataType" type="text" placeholder="Max Length" class="form-control input-md">
												  </div>
												  
												 </div>
									
									
												<div class="form-group">
											  <label class="col-md-3 control-label" for="button1id"></label>
											  <div class="col-md-8">
											    <a class="addMoreSolution"> <span class="lable_header_add" onclick="addFileFields()">Submit </span></a>
											  </div>
											</div>
									 
											<div class="col-md-11">
									
											<table class="table table-striped" id="offeringFilesFieldTable">
									    <thead class="lable_header">
									      <tr>
									        <th>Field Name</th>
									        <th>Description</th>
									        <th>Index</th>
									        <th>Max Length</th>
									        <th>Format</th>
									        <th>Data Type</th>
									        <th>#</th>
									      </tr>
									    </thead>
									    <tbody>
									    
									    </tbody>
									  </table>
											
											
										</div>
										</fieldset>									
									</div>	
							    </div>
										  
										<!-- vendor data coverage start here -->
										<div class="tab-pane" id="tab5">
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
													
													<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Solution</label>
												  <div class="controls">
												    <select id="solutionDataCoverage" name="solutionDataCoverage" onchange="onChangeSolution()">
									      		<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
												      <c:forEach var="solution" items="${solutions}">
															<option value="${solution.solution_id}">${solution.name}</option>
													  </c:forEach>
												      
												    </select>
												  </div>
													</div>
													
													
													<div class="control-group">
													
														<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Coverage Region<span class="required">*</span></label>
														<div class="controls">
														 		<input type="text" id="supportcoverageregion"	name="supportcoverageregion" class="m-wrap large" readonly="readonly" value="Asia Pacific"/>		
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 45px;">Coverage Country<span class="required">*</span></label>
														<div class="controls">
													   		
													          <select name="supportcoveragecountry" 
																			id="supportcoveragecountry" data-mandatory="Y"
																			style="width: 200px" onchange="getRegion('supportcoveragecountry','supportcoverageregion')">
																			<c:forEach var="countries" items="${countries}">
																		        <option value="${countries.country_id}" >${countries.name}</option>
																			</c:forEach>
																		</select>
													   		
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpagetab3">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 32px;" >Offerings</label>
												  <div class="controls">
												    <select id="offeringsDataCoverage" name="offeringsDataCoverage" >
												      
												    </select>
												  </div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 32px;">Vendor Cost Range<span class="required">*</span></label>
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
												<table class="table table-striped table-bordered table-hover table-full-width" id="dataCoverageTable">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Solution</th>
															<th>Offering</th>
															<th>Coverage Region</th>
															<th>Coverage Country</th>
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
														<label class="control-labelaligndatacoverage" style="padding-left: 76px;">Solution<span class="required">*</span></label>
														<div class="controls">
															 <select id="solutionDataDistribution" name="solutionDataDistribution" onchange="onSolutionDataDistribution()">
															 <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															      <c:forEach var="solution" items="${solutions}">
																	  	<option value="${solution.solution_id}">${solution.name}</option>
																  </c:forEach>
														    </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 76px;">Offerings<span class="required">*</span></label>
														<div class="controls">
															 <select id="offeringDataDistribution" name="offeringDataDistribution" onchange="onVendorOfferingChange()">
												              
												    </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 76px;">Files</label>
												  <div class="controls">
												    <select id="fileDataCoverage" name="fileDataCoverage" >
												      
												    </select>
												  </div>
													</div>
												
												<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Frequency<span class="required">*</span></label>
														<div class="controls">
															<select name="frequency" multiple="multiple" id="frequency">
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
													<div class="ColumnCommonvendorpage">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 51px;">Feed Type<span class="required">*</span></label>
														<div class="controls">
														<select name="feedtype" multiple="multiple" id="feedtype">
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
															<select name="feedsubtype" multiple="multiple" id="feedsubtype">
																<option value ="-SELECT-"> -SELECT- </option>
															     <option value ="Full Universe Data Feed"> Full Universe Data Feed </option>
															     <option value ="Delta Data Feed"> Delta Data Feed </option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 45px;">Distribution Method<span class="required">*</span></label>
														<div class="controls">
															<select name="distributionmethod" multiple="multiple" id="distributionmethod">
															     <option value ="Web"> Web </option>
															     <option value ="FTP"> FTP </option>
															     <option value ="FTP"> Email </option>
															     <option value ="Others"> Others </option>
														</select>
														</div>
													</div>
													
													</div>
													 <div class="ColumnCommonvendorpre">
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 44px;">Coverage Region<span class="required">*</span></label>
														<div class="controls">
															
														 	<input type="text" id="coverageregion"	name="coverageregion" class="m-wrap large" readonly="readonly" value="Asia Pacific"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Coverage Country<span class="required">*</span></label>
														<div class="controls">
															
													   		<select name="coveragecountry" 
																			id="coveragecountry" data-mandatory="Y"
																			style="width: 200px" onchange="getRegion('coveragecountry','coverageregion')">
																			<c:forEach var="countries" items="${countries}">
																						    	<option value="${countries.country_id}">${countries.name}</option>
																				
																			</c:forEach>
																		</select>
													   	</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 41px;">Coverage Exchange<span class="required">*</span></label>
														<div class="controls">
															<select name="coverageexchange"  multiple="multiple" id="coverageexchange">
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
												<table class="table table-striped table-bordered table-hover table-full-width" id="dataDistributionTable">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Solution</th>
															<th>Offering</th>
															<th>Offering File</th>
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
									
									</div>
									
										</div> 
										
								</div>
								
								
								</form>
							</div>
							
							<div class="portlet-body form" id="astradingapplicationvendor">
							<form action="#" class="form-horizontal" id="submit_form" method="post">
								<div class="form-wizard">
									<div class="Rowtable">
										<div class="ColumnCommonmyofferings" id="changetradingvendoroffer1"> <div class="lable_header" id="intertradingvendodivoffer1">  <a id="anchootradingvendoffer1" href="#tab8" class="lable_header" data-toggle="tab" onclick="activeTradingVendorMyofferings('${tradingsoftwaredetails}');">Trading Software Details</a> </div> </div>
										<div class="ColumnCommonmyofferings" id="changetradingvendoroffer"> <div class="lable_header" id="intertradingvendodivoffer"> <a id="anchootradingvendoffer" href="#tab7" class="lable_header" data-toggle="tab"  onclick="activeTradingVendorMyofferings('${tradingcapabilitiessupported}');">Trading Capabilities Supported</a> </div></div>
									</div>
									<div class="tab-content" style="background-color: white;">
									
										<!-- vendor Trading Capabilities Supported start here -->
										<div class="tab-pane" id="tab7">
												<div><br/> </div>
												
												 <div class="Row">
													<div class="ColumnCommonvendortab3tradingapplication">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Solution<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															 <select id="tcsSolution" name="tcsSolution" onchange="tradingCapabilitiesSupportedOffering()" style="width: 252px;">
															     
														    </select>
														</div>
													</div>
												
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Offerings<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															     <select id="tcsOffering" name="tcsOffering" style="width: 252px;">
													   		</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Tradable region<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="tcsTradeCoverageRegion" id="tcsTradeCoverageRegion" style="width: 252px;">
																<c:forEach var="regions" items="${regions}">
														    	<option value="${regions.name}">${regions.name}</option>
														 	 	</c:forEach>
														   </select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Tradable exchange<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="tcsTradeCoverageCountry" multiple="multiple" id="tcsTradeCoverageCountry" style="width: 252px;">
																<c:forEach var="countries" items="${countries}">
													    		<option value="${countries.name}">${countries.name}</option>
													  			</c:forEach>
														</select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													
													
													
													<div class="control-group">
	
													<label class="control-labelaligndatacoverage" style="padding-left: 32px;">Tradable Markets</label>
													<div class="controlsfortradingapp">
													<select name="tcsTradableMarkets"  multiple="multiple" id="tcsTradableMarkets" style="width: 252px;">
														     <c:forEach var="exchanges" items="${exchanges}">
															    <option value="${exchanges.name}">${exchanges.name}</option>
															  </c:forEach>
														  </select>
													    </div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpretab3tradinapp">
													    <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Trading Capabilities Type<span class="required">*</span></label>
															<div class="controlsfortradingapp">
																<select name="tcsTradingCapabilitiesType" multiple="multiple" id="tcsTradingCapabilitiesType" style="width: 252px;">
															       <option value ="Order placement/execution" > Order placement/execution </option>
															       <option value ="Order allocation" > Order allocation </option>
															       <option value ="Trade settlement" > Trade settlement </option>
																	<option value ="Trade Clearance" > Trade Clearance</option>
																	<option value ="Real Time Quotes" > Real Time Quotes</option>
																	<option value ="Real Time Financial News" > Real Time Financial News</option>
																	<option value ="Trade Analytics" > Trade Analytics</option>
																	<option value ="Collateral Management" > Collateral Management</option>
																	<option value ="Listed stocks/contracts" > Listed stocks/contracts</option>
																	<option value ="Electronic Trading for OTC" > Electronic Trading for OTC</option>
																	<option value ="Order Management" > Order Management</option>
																	<option value ="Trade Confirmation" > Trade Confirmation</option>
																	<option value ="Trade Reporting" > Trade Reporting</option>
																	<option value ="Trading Strategy Builder" > Trading Strategy Builder</option>
																	<option value ="Liquidity Aggregation" > Liquidity Aggregation</option>
																	<option value ="Market Surveillance & Compliance" > Market Surveillance & Compliance</option>
																	<option value ="Ability to connect to liquidity providers" > Ability to connect to liquidity providers</option>
																	<option value ="Algorithmic Signal Generation" > Algorithmic Signal Generation</option>
																	<option value ="Algorithmic Order Execution& Mgmt" > Algorithmic Order Execution& Mgmt</option>
																	<option value ="Auto Hedging & Risk Mgmt" > Auto Hedging & Risk Mgmt</option>
																	<option value ="Time Series Modelling" > Time Series Modelling</option>
																	<option value ="Market Data Distribution" > Market Data Distribution</option>
																	<option value ="Pricing and Rates Engine" > Pricing and Rates Engine</option>
																	<option value ="Quoting & Price Distribution" > Quoting & Price Distribution</option>
															       
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Trade Executions Type<span class="required">*</span></label>
															<div class="controlsfortradingapp">
																<select name="tcsTradeExecutionsType" multiple="multiple" id="tcsTradeExecutionsType" style="width: 252px;">
																	<option value ="Algorithmic & Program Trading" > Algorithmic & Program Trading </option>
															       <option value ="Direct Market Access (DMA)" > Direct Market Access (DMA) </option>
															       <option value ="FX -DMA"> FX -DMA </option>
															       <option value ="Smart Order Routing (Sequential SOR)"> Smart Order Routing (Sequential SOR) </option>
															       
															       <option value ="Smart Order Routing (Multi-Posting SOR)"> Smart Order Routing (Multi-Posting SOR) </option>
															       <option value ="Swap Execution Facility"> Swap Execution Facility </option>
															      							       
															       
															       
			       
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													 </div>
													 <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Algorithmic Trade Type</label>
															<div class="controlsfortradingapp">
																<select name="tcsAlgorithmicTradeType" multiple="multiple" disabled="disabled" id="tcsAlgorithmicTradeType" style="width: 252px;" >
																	<option value ="VWAP - (Volume Weighted Average Price)" > -VWAP - (Volume Weighted Average Price) - </option>
															       <option value ="TWAP - (Time Weighted Average Price)" > -TWAP - (Time Weighted Average Price) - </option>
															       <option value ="PVOL - (Percentage Of Volume)"> -PVOL - (Percentage Of Volume) - </option>
															       <option value ="IMSH - (Implementation Shortfall)"> -IMSH - (Implementation Shortfall) - </option>
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													 </div>
													    <div class="control-group">
															<label class="control-labelaligndatacoverage" style="padding-left: 32px;">Darkpool Access<span class="required">*</span></label>
															<div class="controlsfortradingapp" style="margin-left: 219px;">
																<select name="tcsDarkpoolAccess" multiple="multiple" id="tcsDarkpoolAccess" style="height: 100px;width: 252px;">
																	  <option value ="Access to independent Darkpools"> Access to independent Darkpools (?) </option>
																	  <option value ="Access to Broker-dealer Darkpools"> Access to Broker-dealer Darkpools (?) </option>
																	  <option value ="Access to Consortium-owned darkpools"> Access to Consortium-owned darkpools (?) </option>
																	  <option value ="Access to Exchange-owned darkpools"> Access to Exchange-owned darkpools (?) </option>
																	  <option value ="Access to Darkpool Aggregators"> Access to Darkpool Aggregators(?) </option>
																	  <option value ="No Darkpool Access">No Darkpool Access </option>
																	  
														   		</select>
														   		<div class="selectOptions">Choose one or more options</div>
															</div>
														 </div>
														 
														 <div class="control-group">
															<label class="control-labelaligndatacoverage" style="padding-left: 32px;">Supported Darkpool Venues</label>
															<div class="controlsfortradingapp" style="margin-left: 219px;">
																<select name="tcsSupportedDarkpoolVenues" disabled="disabled" id="tcsSupportedDarkpoolVenues" style="width: 252px;">
																	  <option value ="-Select-"> -Select-  </option>
																		<option value ="Instinet"> Instinet  </option>
																		<option value ="Liquidnet"> Liquidnet </option>
																		<option value ="NYFIX"> NYFIX </option>
																		<option value ="Posit/MatchNow"> Posit/MatchNow </option>
																		<option value ="BlockCross"> BlockCross  </option>
																		<option value ="RiverCross"> RiverCross </option>
																		<option value ="TORA Crosspoint"> TORA Crosspoint </option> 
																		<option value =" Codestreet Dealer Pool for Corporate Bonds"> Codestreet Dealer Pool for Corporate Bonds </option>
																		<option value ="JPMorgan Chase Bank - JPMX"> JPMorgan Chase Bank - JPMX </option>
																		<option value ="Barclays Capital - LX Liquidity Cross"> Barclays Capital - LX Liquidity Cross </option>
																		<option value ="BNP Paribas - BNP Paribas Internal eXchange (BIX)"> BNP Paribas - BNP Paribas Internal eXchange (BIX) </option>
																		<option value ="BNY ConvergEx Group (an affiliate of Bank of New York Mellon)"> BNY ConvergEx Group (an affiliate of Bank of New York Mellon) </option>
																		<option value ="Cantor Fitzgerald - Aqua Securities"> Cantor Fitzgerald - Aqua Securities </option>
																		<option value ="Citi - Citi Match, Citi Cross"> Citi - Citi Match, Citi Cross </option>
																		<option value ="Credit Agricole Cheuvreux - BLINK"> Credit Agricole Cheuvreux - BLINK </option>
																		<option value ="Credit Suisse - CrossFinder"> Credit Suisse - CrossFinder </option>
																		<option value ="Deutsche Bank Global Markets - DBA (Europe), SuperX ATS (U.S.)"> Deutsche Bank Global Markets - DBA (Europe), SuperX ATS (U.S.) </option>
																		<option value ="Fidelity Capital Markets"> Fidelity Capital Markets </option>
																		<option value ="GETCO - GETMatched"> GETCO - GETMatched </option>
																		<option value ="Goldman Sachs SIGMA X"> Goldman Sachs SIGMA X </option>
																		<option value ="Knight Capital Group - Knight Link, Knight Match"> Knight Capital Group - Knight Link, Knight Match </option>
																		<option value ="Merrill Lynch - Instinct-X"> Merrill Lynch - Instinct-X </option>
																		<option value ="Morgan Stanley - NightVision"> Morgan Stanley - NightVision </option>
																		<option value ="Nomura - Nomura NX, CHI-X"> Nomura - Nomura NX, CHI-X </option>
																		<option value ="UBS Investment Bank - UBS ATS, UBS MTF, UBS PIN"> UBS Investment Bank - UBS ATS, UBS MTF, UBS PIN </option>
																		<option value ="Societe Generale - ALPHA Y"> Societe Generale - ALPHA Y </option>
																		<option value ="Daiwa - DRECT"> Daiwa - DRECT </option>
																		<option value ="Wells Fargo Securities LLC - WELX"> Wells Fargo Securities LLC - WELX </option> 
																		<option value ="BIDS Trading - BIDS ATS"> BIDS Trading - BIDS ATS </option>
																		<option value ="LeveL ATS"> LeveL ATS </option>
																		<option value ="International Securities Exchange"> International Securities Exchange </option>
																		<option value ="NYSE Euronext"> NYSE Euronext </option>
																		<option value ="BATS Trading"> BATS Trading </option>
																		<option value ="Turquoise"> Turquoise </option>
																		<option value ="Swiss Block"> Swiss Block </option>
																		<option value ="Nordic@Mid"> Nordic@Mid </option>
																		<option value ="Fidessa - Spotlight"> Fidessa - Spotlight </option>
																		<option value ="Bloomberg Tradebook"> Bloomberg Tradebook </option>
																		<option value ="Liquidnet LN Dark"> Liquidnet LN Dark </option>
																		<option value ="Credit Suisse Crossfinder Plus"> Credit Suisse Crossfinder Plus </option>
																		<option value ="SuperX+ - Deutsche Bank"> SuperX+ - Deutsche Bank </option>
																		<option value ="ASOR - Quod Financial"> ASOR - Quod Financial </option>
																		<option value ="Progress Apama"> Progress Apama </option>
																		<option value ="ONEPIPE - Weeden & Co. & Pragma Financial"> ONEPIPE - Weeden & Co. & Pragma Financial </option>
																		<option value ="Xasax Corporation"> Xasax Corporation </option>
																		<option value ="Crossfire - Credit Agricole Cheuvreux"> Crossfire - Credit Agricole Cheuvreux </option>
																		<option value ="Instinet - Nighthawk"> Instinet - Nighthawk </option>
																	  
														   		</select>
															</div>
														 </div>
														 
														 
														 </div> 
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a id="tcsSubmit"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="tcsTable">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Solution</th>
															<th>Offering</th>
															<th>Tradable Region</th>
															<th>Tradable country</th>
															<th>Tradable Markets</th>
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
											</div>
									<div><br/></div>
								
											</div>
											
											 <!-- Vendor Trading Capabilities Supported ends here -->
											 
											 <!-- Vendor Trading Software Details starts here -->
												<div class="tab-pane active" id="tab8">
												<div><br/> </div>
												<div class="Rowtableinfoval"><font id="myofferingsdatacoveragetabsucessmessage" style="font-size: 14px;font-family:Open Sans, sans-serif; position: absolute; color: #2AABAB; font-weight: bold;margin: -19px 0px 0px 15px;"></font>
													<div class="image-upload" style="margin-left: 252px;">
												    <label class="control-label-fileupload">Bulk Insert Option (using .CSV or .Xls)<span class="required">*</span> 
												        <a href="/finvendor/downloaddocument?RaYuL=/files/SupportDetails_insurance_sample.csv&amp;VeMu=" target="_blank"> <img src="/finvendor/resources/images/csv.png" style="padding:0px 0px 4px 0px;"></a>&nbsp;
												         OR &nbsp;<a href="/finvendor/downloaddocument?RaYuL=/files/SupportDetails_insurance_sample.csv&amp;VeMu=" target="_blank"><img src="/finvendor/resources/images/xls.png" style="padding:0px 0px 4px 0px;"></a> &nbsp;&nbsp;&nbsp;&nbsp; 
												    </label>
												</div>
												<div class="ColumnCommonray">
												<a class="#" data-toggle="modal" href="#normalModal"><span class="lable_headeractions"><img src="/finvendor/resources/images/attachment.png">Bulk Upload here</span></a>
												</div>
												<div id="normalModal" class="modal fade">
													  <div class="modal-dialog">
													    <div class="modal-content">
													      <div class="modal-header">
													        <button type="button" class="close" data-dismiss="modal">×</button>
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
												
												<br>
												
												 <div class="Row">
													<div class="ColumnCommonvendordist">
													
														 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Solution<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="tsdSolution"  id="tsdSolution" style="width: 163px;">
															<option value ="-SELECT-"> -SELECT- </option>
															    <c:forEach var="solution" items="${solutions}">
																	<option value="${solution.solution_id}">${solution.name}</option>
													  			</c:forEach>
														</select>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Application Name<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<input type="text" id="tdsAppName" placeholder="Application Details" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Application Details<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<input type="text" id="tdsAppDesc" placeholder="Application Details" name="tdsAppDesc" class="m-wrap largeval"/>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Asset Class Supported<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="tdsAssetClass"  id="tdsAssetClass" style="width: 163px;">
														     	    <c:forEach var="assetClasses" items="${assetClasses}">
															    		<option value="${assetClasses.description}">${assetClasses.description}</option>
													  				</c:forEach>
														 	</select> 
														</div>
													</div>
													
													
													
												
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Accessibility (?)<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="tdsAccessibility"  multiple="multiple" id="tdsAccessibility" style="width: 163px;">
															     <option value ="Web/Browser Based"> Web/Browser Based </option>
															     <option value ="Binaries(Executable) Based"> Binaries(Executable) Based </option>
															     <option value ="Dedicated Desktop">Dedicated Desktop</option>
															      <option value ="Mobile/Tablet Apps">Mobile/Tablet Apps</option>
		
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Suitability<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="tdsSuitability"  multiple="multiple" id="tdsSuitability" style="width: 163px;">
															     <option value ="All Users"> All Users </option>
															     <option value ="Advanced shares traders"> Advanced shares traders </option>
															     <option value ="Technical chart users"> Technical chart users </option>
															     <option value ="Forex specialists"> Forex specialists </option>
															     <option value ="Other"> Other </option>
														</select>
														</div>
														<div class="controlsfortradingappsoftware">
														<input type="text" id="tdsSuitabilityOther"  placeholder="Suitability Other" name="tdsSuitabilityOther" style="visibility: hidden;" />
														</div>
													</div>
													
													  <div class="control-group">
														<label class="control-labelalign" >Streaming news available? &nbsp;&nbsp;&nbsp; <input type="checkbox" id="tdsStreNews"  name="tdsStreNews" /></label>
														
													</div>
													<div class="control-group">
														<label class="control-labelalign" >Trade using charts available? <input type="checkbox" id="tdsChartsAvai" name="tdsChartsAvai" /></label>
														
													</div>
													
													
													</div>
													<div class="ColumnCommonvendorpage" style="margin: 0px 0px 0px 56px;">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Offering<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="tdsOffering" placeholder="Offering" name="tdsOffering" class="m-wrap largeval" onblur="validateAjax(this, 'checkExistingValue','addTradingSoftwareDetailsErrorMsg','addTradingSoftwareDetails')"/>
														</div>
														<div>
															<label id="addTradingSoftwareDetailsErrorMsg" class="errorMessage"></label>
														</div>
													</div>
													
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Cost Type<span class="required">*</span></label>
														<div class="controls">
															<select name="tdsCostType"  id="tdsCostType" style="width: 163px;">
															     <option value ="Free and Min Balance Not required"> Free and Min Balance Not required </option>
															     <option value ="Min Balance Required"> Min Balance Required </option>
															     <option value ="Subscription based"> Subscription based </option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Platform  CCY<span class="required">*</span></label>
														<div class="controls">
															<select name="tdsPlatformCCY"  id="tdsPlatformCCY" style="width: 163px;">
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
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Platform cost (USD ; per month)</label>
														<div class="controls">
															<input type="text" id="tdsPlatformCost" placeholder="Platform cost (USD ; per month)" name="tdsPlatformCost" readonly="readonly" class="m-wrap largeval"/>
														</div>
													</div>
													  <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Platform cost (USD ; per annum)</label>
														<div class="controls">
														<input type="text" id="tdsPlatformType" placeholder="Platform cost (USD ; per annum)" name="tdsPlatformType" readonly="readonly" class="m-wrap largeval"/>
															
														</div>
													</div>
												
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Order Type <span class="required">*</span></label>
														<div class="controls">
															<select name="tdsOrderType"  id="tdsOrderType" multiple="multiple" style="width: 163px;">
															     <option value ="Limits"> Limits  </option>
															     <option value ="market orders"> market orders </option>
															     <option value ="tranche (iceberg) orders"> tranche (iceberg) orders </option>
															     <option value ="fill or kill"> fill or kill </option>
															     <option value ="execute and eliminate"> execute and eliminate </option>
															     <option value ="date"> date  </option>
															     <option value ="day"> day  </option>
															     <option value ="good for auction"> good for auction  </option>
															     <option value ="Good till cancel"> Good till cancel  </option>
															     <option value ="Good till cancel"> Good till cancel </option>
															     <option value ="At the auction"> At the auction </option>
															     <option value ="At the open"> At the open </option>
															     <option value ="At the close"> At the close </option>
															     <option value ="At best"> At best  </option>
															     <option value ="Trailing stops"> Trailing stops  </option>
															     <option value ="Market if touched">Market if touched  </option>
   	      													    <option value ="One cancels the other"> One cancels the other </option>
															     <option value ="One enables the others"> One enables the others  </option>
															     <option value ="Stop-loss"> Stop-loss  </option>
															     <option value ="Take profit">Take profit </option> 
	
														</select>
														</div>
													</div>
											
											     
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Price Alerts?</label>
														<div class="controls">
															<input type="checkbox" id="tdsPriceAlerts"  name="tdsPriceAlerts" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Watchlist?</label>
														<div class="controls">
															&nbsp;&nbsp;&nbsp;<input type="checkbox" id="tdsWatchlist"  name="tdsWatchlist" class="m-wrap largeval"/>
														</div>
													</div>
											
											
											
											
											
													</div>
													
													 <div class="ColumnCommonvendorpre">
													 
													 		<div class="control-group">
																	<label class="control-labelalign">Offering Desc<span class="required">*</span>
																	</label>
																	<div class="controls">
																		<textarea id="tdsOfferingDesc"
																			data-mandatory="Y" placeholder="Offering Desc"
																			name="companyinfo"  cols="50"
																			rows="3"></textarea>
																		<div>
																			<label id="tdsOfferingDescErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
													 
													 
													 
													 
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Trade Type<span class="required">*</span></label>
														<div class="controls">
															<select name="tdsTradeType"  id="tdsTradeType" style="width: 163px;">
														      <option value ="Algorithmic Trade Type" > -Algorithmic Trade Type- </option>
														      <option value ="Algorithmic Trade Type" > -Algorithmic Trade Type- </option>
														  </select>
														</div>
													</div>
												    <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Software specifications</label>
														<div class="controls">
															<input type="text" id="tdsAddSoftwareSpecifications" placeholder="Software specifications" name="tdsAddOns" class="m-wrap largeval"/>
														</div>
													</div>
												    
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Add-Ons<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="tdsAddOns" placeholder="Add-Ons" name="tdsAddOns" class="m-wrap largeval"/>
															<div class="selectOptions">Separate 2 items by comma</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Ope.. system<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="tdsOpeSystem" placeholder="Ope.. system" name="tdsOpeSystem" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Launched Year<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="tdsLaunchedYear" placeholder="Launched Year" name="tdsLaunchedYear" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Client Base<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="tdsClientBase" placeholder="Client Base" name="tdsClientBase" class="m-wrap largeval"/>
															<div class="selectOptions">No. of Different Banks Already Using this Product</div>
														</div>
													</div>
													</div> 
													
												</div>
									<div><br/></div>
									<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a id="tdsSubmit"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="tdsTable">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Solution</th>
															<th>Offering</th>
															<th>Offering Desc</th>
															<th>Asset Class</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
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
											<div class="ColumnCommonmyofferings" id="changeAnalyticsvendoroffer1"> <div class="lable_header" id="interAnalyticsvendodivoffer1">  <a id="anchooAnalyticsvendoffer1" href="#tab10" class="lable_header" data-toggle="tab" onclick="activeAnalyticsVendorMyofferings('${analyticssoftwaredetails}');">Analytics Software Details</a> </div> </div>
										</div>
										<div class="tab-content" style="background-color: white;">
									
										<!-- vendor Analytics features Supported start here
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
										 -->	
											 <!-- Vendor Analytics features Supported ends here -->
											 
											 <!-- Vendor Analytics Software Details starts here -->
												<div class="tab-pane active" id="tab10">
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3analyticssolution">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Solutions<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="asdSolution"  id="asdSolution" style="width: 223px;">
															<option value ="-SELECT-"> -SELECT- </option>
															    <c:forEach var="solution" items="${solutions}">
																	<option value="${solution.solution_id}">${solution.name}</option>
													  			</c:forEach>
														</select>
														</div>
													</div>
												<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Offering<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdOffering" placeholder="Offering" name="asdOffering" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													
													<div class="control-group">
																	<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Offering Desc<span class="required">*</span>
																	</label>
																	<div class="controlsforanalyticsapplcation">
																		<textarea id="asdOfferingDesc"
																			data-mandatory="Y" placeholder="Offering Desc"
																			name="companyinfo"  cols="50"
																			rows="3"></textarea>
																		<div>
																			<label id="tdsOfferingDescErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
													
													
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Application Name<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdApplicationName" placeholder="Application Name" name="asdApplicationName" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Application Brief Desc<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdApplicationBriefDesc" placeholder="Application Brief Desc" name="asdApplicationBriefDesc" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Accessibility (?)<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="asdAccessibility" multiple="multiple" id="asdAccessibility" style="width: 223px;">
														     	<option value ="Web Browser Based" >Web Browser Based</option>
														     	<option value ="Binaries(Executable) Based" > Binaries(Executable) Based </option>
														     	<option value ="Dedicated Desktop" >Dedicated Desktop</option>
														     	<option value ="Mobile/Tablet Apps" >Mobile/Tablet Apps</option>
					
														   </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Suitability<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="asdSuitability" id="asdSuitability" style="width: 223px;">
															   <option value ="All Users" >All Users</option>
														       <option value ="Risk Managers" >Risk Managers</option>
																<option value ="Backoffice & Middle Office Users" >Backoffice & Middle Office Users</option>
																<option value ="Portfolio Managers" >Portfolio Managers</option>
																<option value ="Retail/Corporat Banking Users" >Retail/Corporat Banking Users</option>
																<option value ="Regulation & Compliance Users" >Regulation & Compliance Users</option>
																<option value ="Research Analysts" >Research Analysts</option>
																<option value ="Others" >Others</option>
														       
														</select>
														<input type="text" id="asdSuitabilityOthers" placeholder="Others Suitability" name="asdSuitabilityOthers" class="m-wrap largevalforanalytics" style="visibility: hidden;"/>
														</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Application Cost Type<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="asdApplicationCostType"  id="asdApplicationCostType" style="width: 223px;">
																	<option value ="Free and Min Balance Not required" >-Free and Min Balance Not required-</option>
														            <option value ="Min Balance Required" > Min Balance Required </option>
														            <option value ="Subscription based" > Subscription based </option>
														            
																</select>
															</div>
													</div>
												
													</div>
													 <div class="ColumnCommonvendorpretab3analyticsapp">
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Subsription cost (USD ; per month)<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdApplicationSubscriptionCost" readonly="readonly" placeholder="Subsription cost (USD ; per month)" name="asdApplicationSubscriptionCost" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Subsription cost (USD ; per annum)<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdApplicationSubscriptionAnnum" readonly="readonly" placeholder="Subsription cost (USD ; per annum)" name="asdApplicationSubscriptionAnnum" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
											
											
											
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Add-Ons<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdAddOns" placeholder="Add-Ons" name="asdAddOns" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Operating system<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdOperatingSystem" placeholder="Operating system" name="asdOperatingSystem" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Software specifications<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdSoftwareSpecifications" placeholder="Software specifications" name="asdSoftwareSpecifications" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Launched Year<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdLaunchedYear" placeholder="Launched Year" name="asdLaunchedYear" class="m-wrap largevalforanalytics"/>
														</div>
													</div> 
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Existing User Base<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdExistingUserBase" placeholder="Existing User Base" name="asdExistingUserBase" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													
															  <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Real time Market Data?</label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="asdRealtimeMarketData" placeholder="Real time Market Data?" name="asdRealtimeMarketData" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Customizable Calculation Models(?)</label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="asdCustomizableCalculationModels" placeholder="Customizable Calculation Models" name="asdCustomizableCalculationModels" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													    
													</div>
													
												</div>
									<div><br/></div>
									<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a id="asdSubmit"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="asdTable">
													<thead style="background-color: #7BCCA5;">
														<tr>

															<th>Solution</th>
															<th>Offering</th>
															<th>Offering Desc</th>
															<th>Application Name</th>
															<th>Accessibility</th>
															<th>Application Cost Type</th>
															<th>Application Subscription Cost</th>
															<th>Realtime Market Data</th>
															<th>Operating System</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
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
										<div class="tab-pane  active" id="tab11">
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
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Solution<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="rcSolution"  id="rcSolution" style="width: 252px;" >
															<option value ="-SELECT-"> -SELECT- </option>
															    <c:forEach var="solution" items="${solutions}">
																	<option value="${solution.solution_id}">${solution.name}</option>
													  			</c:forEach>
														</select>
														</div>
													</div>
													
														 	<div class="control-group">
																	<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Offering Desc<span class="required">*</span>
																	</label>
																	<div class="controlsfortradingapp">
																		<textarea id="rcOfferingDesc"
																			data-mandatory="Y" placeholder="Offering Desc"
																			name="rcOfferingDesc"  cols="50" style="width: 223px;"
																			rows="3"></textarea>
																		<div>
																			<label id="tdsOfferingDescErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
													
													
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Regions Covered<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="rcRegionsCovered"  id="rcRegionsCovered" style="width: 252px;">
															     <c:forEach var="regions" items="${regions}">
														    	<option value="${regions.name}">${regions.name}</option>
														 	 	</c:forEach>
														 	</select> 
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Area<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="apResearchArea"  id="apResearchArea" style="width: 252px;">
															     
																<option value ="Commodity Analysis" >Commodity Analysis</option>
																<option value ="Exchange Rate Analysis" >Exchange Rate Analysis</option>
																<option value ="Interest Rate Analysis" >Interest Rate Analysis</option>
																<option value ="Equity research" >Equity research</option>
																<option value ="Debt Market research" >Debt Market research</option>
																<option value ="Index research" >Index research</option>
																<option value ="Fund/ETF research" >Fund/ETF research</option>
															     
														 	</select> 
														</div>
													</div>
													
													
													
													
													
													</div>
													<div class="ColumnCommonvendortab3analyticssolutiontab2">
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Offering<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															     <input type="text" id="rcOffering" placeholder="Offering" name="rcOffering" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Research Sub Area<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="apResearchSubArea" multiple="multiple" id="apResearchSubArea" style="width: 223px;">
															     
																	<option value ="Economic Data analysis" >Economic Data analysis</option>
																	<option value ="Country Economy Analysis" >Country Economy Analysis</option>
																	<option value ="World economy Analysis" >World economy Analysis</option>
																	<option value ="Oil & Gas" >Oil & Gas</option>
																	<option value ="Basic Materials" >Basic Materials</option>
																	<option value ="Industrials" >Industrials</option>
																	<option value ="Consumer Goods" >Consumer Goods</option>
																	<option value ="Health Care" >Health Care</option>
																	<option value ="Consumer Services" >Consumer Services</option>
																	<option value ="Telecommunications" >Telecommunications</option>
																	<option value ="Utilities" >Utilities</option>
																	<option value ="Financials" >Financials</option>
																	<option value ="Technology" >Technology</option>
																	<option value ="Agriculture and allied" >Agriculture and allied</option> 
																	<option value ="Mining" >Mining</option>
																	<option value ="Utilities" >Utilities</option>
																	<option value ="Construction" >Construction</option>
																	<option value ="Manufacturing" >Manufacturing</option>
																	<option value ="Wholesale Trade" >Wholesale Trade</option>
																	<option value ="Retail Trade" >Retail Trade</option>
																	<option value ="Transportation and warehousing" >Transportation and warehousing</option>
																	<option value ="Information" >Information</option>
																	<option value ="Finance and Insurance" >Finance and Insurance</option>
																	<option value ="Real Estate and Rental/Leasing" >Real Estate and Rental/Leasing</option>
																	<option value ="Professional, Scientific & Technical Services" >Professional, Scientific & Technical Services</option>
																	<option value ="Education Services" >Education Services</option>
																	<option value ="Healthcare & Social Assistance" >Healthcare & Social Assistance</option>
																	<option value ="Arts, Entertainment & Recreation" >Arts, Entertainment & Recreation</option>
																	<option value ="Accomodation & Food services" >Accomodation & Food services</option>
																	<option value ="Other Services" >Other Services</option>
																	<option value ="Energy" >Energy</option>
																	<option value ="Industrial Metals" >Industrial Metals</option>
																	<option value ="Precious Metals" >Precious Metals</option>
																	<option value ="Agricultural Commodities" >Agricultural Commodities</option>
																	<option value ="European Euro to US Dollar EUR/USD" >European Euro to US Dollar EUR/USD</option>
																	<option value ="British Pound to US Dollar GBP/USD" >British Pound to US Dollar GBP/USD</option>
																	<option value ="Swiss Franc to US Dollar USD/CHF" >Swiss Franc to US Dollar USD/CHF</option>
																	<option value ="Japanese Yen to US Dollar USD/JPY" >Japanese Yen to US Dollar USD/JPY</option>
																	<option value ="Chinese Yuan to US Dollar USD/CNY" >Chinese Yuan to US Dollar USD/CNY</option>
																	<option value ="Taiwan Dollar to US Dollar USD/TWD" >Taiwan Dollar to US Dollar USD/TWD</option>
																	<option value ="South Korean Won to US Dollar USD/KRW" >South Korean Won to US Dollar USD/KRW</option>
																	<option value ="Australian Dollar to US Dollar AUD/USD" >Australian Dollar to US Dollar AUD/USD</option>
																	<option value ="Singapore Dollar to US Dollar USD/SGD" >Singapore Dollar to US Dollar USD/SGD</option>
																	<option value ="India Rupee to US Dollar USD/INR" >India Rupee to US Dollar USD/INR</option>
																	<option value ="New Zealand Dollar to US Dollar NZD/USD" >New Zealand Dollar to US Dollar NZD/USD</option>
																	<option value ="Canadian Dollar to US Dollar USD/CAD" >Canadian Dollar to US Dollar USD/CAD</option>
																	<option value ="Mexican Peso to US Dollar USD/MXN" >Mexican Peso to US Dollar USD/MXN</option>
																	<option value ="Govt Bond rate" >Govt Bond rate</option>
																	<option value ="Prime Lending Rate" >Prime Lending Rate</option>
																	<option value ="Investment Grade Spread" >Investment Grade Spread</option>
																	<option value ="High Yield Spread" >High Yield Spread</option>
																	<option value ="Govt Bond rate" >Govt Bond rate</option>
																	<option value ="Prime Lending Rate" >Prime Lending Rate</option>
																	<option value ="Investment Grade Spread" >Investment Grade Spread</option>
																	<option value ="High Yield Spread" >High Yield Spread</option>
																	<option value ="Govt Bond rate" >Govt Bond rate</option>
																	<option value ="Prime Lending Rate" >Prime Lending Rate</option>
																	<option value ="Investment Grade Spread" >Investment Grade Spread</option>
																	<option value ="High Yield Spread" >High Yield Spread</option>
																	<option value ="LIBOR Rate" >LIBOR Rate</option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Total Research Analyst<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="rcTotalResearchAnalyst" placeholder="Total Research Analyst" name="rcTotalResearchAnalyst" class="m-wrap largevalforanalytics"/>
														</div>
													</div> 
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Existing Client Base<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="rcExistingClientBase" placeholder="Existing Client Base" name="rcExistingClientBase" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Prepared by CFA?<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="rcResearchPreparedbyCFA" placeholder="Research Prepared by CFA" name="rcResearchPreparedbyCFA" class="m-wrap largevalforanalytics"/>
														</div>
													</div> 
													   
													</div>
												</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a id="rcSubmit"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="rcTable">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Solution</th>
															<th>Offering</th>
															<th>Regions Covered</th>
															<th>Research Prepared by CFA?</th>
															<th>Total Research Analyst</th>
															<th>Existing Client Base</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
									<div><br/></div>
								
											</div>
											
											 <!-- Vendor Research  Coverage ends here -->
											 
											 <!-- Vendor Research Details starts here -->
												<div class="tab-pane" id="tab12">
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3analyticssolution">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Solution<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
														<select name="rdSolution"  id="rdSolution" style="width: 223px;" onchange="researchReportingVendorOffering('rdSolution','rdOffering')">
															
														</select>
															
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Offering<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="rdOffering"  id="rdOffering" style="width: 223px;">
															     
													   		</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Report Name<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="rdResearchReportName" placeholder="Research Report Name" name="rdResearchReportName" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Report Desc<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="rdResearchReportDesc" placeholder="Research Report Desc" name="rdResearchReportDesc" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Accessibility (?)<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="rdAccessibility" id="rdAccessibility" style="width: 223px;">
														     	<option value ="Web-Browser Based" >-Web-Browser Based-</option>
														     	<option value ="Email" > -Email- </option>
														   </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Suitability<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="rdSuitability" id="rdSuitability" multiple="multiple"  style="width: 223px;">
															  		<option value ="All Users" >All Users</option>
														       		<option value ="Equity Fund Manager" >Equity Fund Manager</option>
																	<option value ="Fixed Income Fund Manager" >Fixed Income Fund Manager</option>
																	<option value ="Hedge Fund" >Hedge Fund</option>
																	<option value ="Private Equity Fund" >Private Equity Fund</option>
																	<option value ="Advanced shares traders" >Advanced shares traders</option>
																	<option value ="Corporate Creditors (Banks, Lending Institutions, etc)" >Corporate Creditors (Banks, Lending Institutions, etc)</option>
																	<option value ="Technical chart users" >Technical chart users</option>
																	<option value ="Forex specialists" >Forex specialists</option>
																	<option value ="Others" >Others</option>     
														       
														</select>
														<input type="text" id="rdSuitabilityOthers" placeholder="Others Suitability" name="rdSuitabilityOthers" class="m-wrap largevalforanalytics" style="visibility: hidden;"/>
														</div>
													</div>
													
												
													</div>
													 <div class="ColumnCommonvendorpretab3analyticsapp">
														<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Cost Type<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="rdReportCostType"  id="rdReportCostType" style="width: 223px;">
																	<option value ="Free and Min Balance Not required" >Free and Min Balance Not required</option>
														            <option value ="Min Balance Required" > Min Balance Required </option>
														            <option value ="Subscription based" > Subscription based </option>
														            
																</select>
															</div>
													</div> 
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Subsription cost (USD ; per month) </label>
														<br>
														<div >
															<input type="text" id="rdReportSubscriptionCost" readonly="readonly" placeholder="Subsription cost (USD ; per month)" name="rdReportSubscriptionCost" />
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Subsription cost (USD ; per annum) </label>
														<br>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="rdReportSubscriptionCostAnnum" readonly="readonly" placeholder="Subsription cost (USD ; per annum)" name="rdReportSubscriptionCostAnnum" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													   
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Format<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="rdReportFormat" id="rdReportFormat" multiple="multiple" style="width: 223px;">
															        <option value ="PDF"> PDF  </option>
																     <option value ="DOC"> DOC </option>
																     <option value ="EXCEL"> EXCEL </option>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Research Period<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
															<select name="rdResearchApplicableMonth" id="rdResearchApplicableMonth" style="width: 123px;">
															        		<option value ="January">January</option>
																			<option value ="February">February</option>
																			<option value ="March">March</option>
																			<option value ="April">April</option>
																			<option value ="May">May</option>
																			<option value ="June">June</option>
																			<option value ="July">July</option>
																			<option value ="August">August</option>
																			<option value ="September">September</option>
																			<option value ="October">October</option>
																			<option value ="November">November</option>
																			<option value ="December">December</option>
																																			     
															</select>
																<select name="rdResearchApplicableYear" id="rdResearchApplicableYear" style="width: 100px;">
															        		<option value ="2006">2006</option>
																			<option value ="2007">2007</option>
																			<option value ="2008">2008</option>
																			<option value ="2009">2009</option>
																			<option value ="2010">2010</option>
																			<option value ="2011">2011</option>
																			<option value ="2012">2012</option>
																			<option value ="2013">2013</option>
																			<option value ="2014">2014</option>
																			<option value ="2015">2015</option>
																			<option value ="2016">2016</option>
																																			     
															</select>
															
															</div>
													  </div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Existing User Base<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="rdExistingUserBase" placeholder="Existing User Base" name="rdExistingUserBase" class="m-wrap largevalforanalytics"/>
														</div>
													</div>    
													</div>
													
												</div>
									<div><br/></div>
									
									
											<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a id="rdSubmit"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="rdTable">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Solution</th>
															<th>Offering</th>
															<th>Research Report Name</th>
															<th>Research Report Desc</th>
															<th>Accessibility</th>
															<th>Suitability</th>
															<th>Report Cost Type</th>
															<th>Report Subscription CCY</th>
															<th>Report Subscription Cost</th>
															<th>Report Subscription Type</th>
															<th>Report Format</th>
															<th>Research Applicable Year</th>
															<th>Research Applicable Month</th>
															<th>Existing User Base</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
									
									</div>
									<!-- Vendor Research Details ends here -->
									
									<!-- Vendor Analyst Profile starts here -->
									<div class="tab-pane" id="tab13">
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3analyticssolution">
																								
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Analyst Name<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="apAnalystName" placeholder="Analyst Name" name="apAnalystName" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Analyst Region of Incorp<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="apAnalystRegionofIncorp" id="apAnalystRegionofIncorp" style="width: 223px;">
																     <c:forEach var="regions" items="${regions}">
																    	<option value="${regions.name}">${regions.name}</option>
																 	 </c:forEach>
															</select>
															</div>
													  </div>
													
													<div class="control-group">
														<label class="control-labelalign" style="padding-left: 35px;">Research Analyst with CFA Charter?&nbsp;&nbsp;<input type="checkbox" id="apResearchAnalystWithCFA" placeholder="Research Analyst with CFA" name="apResearchAnalystWithCFA" /></label>
														
													</div>
													</div>
													
													 <div class="ColumnCommonvendorpretab3analyticsapp">
													    
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Analyst Country of Incorp<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="apAnalystCountryofIncorp" id="apAnalystCountryofIncorp" style="width: 223px;">
															         <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Analyst Year of Exp<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="apAnalystYearofExp" id="apAnalystYearofExp" style="width: 223px;">
															         <option value="Date (1970)">Date (1970)</option>
																     <option value="Last one week">Last one week</option>
																     <option value="Last one month">Last one month</option>
																     <option value="Last one year">Last one year</option>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Analyst Awards<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="apAnalystAwards" multiple="multiple" id="apAnalystAwards" style="width: 223px;">
															        <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
																 <option value="Other">Other</option>
															</select>
															<input type="text" id="apAnalystAwardsOthers" placeholder="Other" name="apAnalystAwardsOthers" class="m-wrap largevalforanalytics" style="visibility: hidden;"/>
															
															</div>
													  </div>
													</div>
													
												</div>
									<div><br/></div>
									
											<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a id="rpSubmit"> <span class="lable_header_add">Add More </span></a> 
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="rpTable">
													<thead style="background-color: #7BCCA5;">
														<tr>
															<th>Analyst Name</th>
															<th>Research Analyst With CFA</th>
															<th>Analyst Region of Incorp</th>
															<th>Analyst Year of Exp</th>
														    <th>Analyst Awards</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
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
    <jsp:include page="common/footer.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
  	<script type="text/javascript" 	src="<%=request.getContextPath() %>/resources/js/finvendorValidation.js"></script>

	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jstree/3.0.9/themes/default/style.min.css" />
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jstree/3.0.9/jstree.min.js"></script>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


<script type="text/javascript">

window.onload = function ()
{
	
	$("#asdataaggregatorvendor").slideDown("slow");
	$("#astradingapplicationvendor").hide();
	$("#asanalyticsapplicationvendor").hide();
	$("#asresearchreportingvendor").hide();
	changeTabMode();
	var vendormyofferingsdatadictionary= '<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_DATADICTIONARY %>';
	
	if(vendormyofferingsdatadictionary != null && vendormyofferingsdatadictionary.length > 0 && vendormyofferingsdatadictionary.match("vendorMyOfferingsDataDictionary")){
		debugger;
		document.getElementById('changeoffer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivoffer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooffer2').style.backgroundColor = '#5CE5E5';
		$('#container').jstree();
		$("#createfileDiv").hide();
		$("#createOfferingDiv").hide();
		$("#createFieldsDiv").hide();
	}
	
	  var vendormyofferingsasaggregatorvendor = '<%=RequestConstans.Vendor.VENDOR_MYOFFERINGS_AS_DATA_AGGREGATOR_VENDOR %>';
	  if(vendormyofferingsasaggregatorvendor != null && vendormyofferingsasaggregatorvendor.length > 0 && vendormyofferingsasaggregatorvendor.match("vendormyofferingsasaggregatorvendor")){
			document.getElementById('changeraymyofferings1').style.backgroundColor = '#5CE5E5';
			document.getElementById('interdivraymyofferings1').style.backgroundColor = '#5CE5E5';  
			document.getElementById('anchoraymyooferings1').style.backgroundColor = '#5CE5E5';
		}
	
}

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
		document.getElementById('changeoffer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivoffer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooffer2').style.backgroundColor = '#5CE5E5';
		var result = vendorTypeSolutions('<%=RequestConstans.Vendor.DATA_AGGREGATOR %>');
		$('#solutionDataCoverage').html(result);
		$('#solutionDataDistribution').html(result);
		
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
		document.getElementById('changetradingvendoroffer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('intertradingvendodivoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchootradingvendoffer1').style.backgroundColor = '#5CE5E5';
		debugger;
		var result = vendorTypeSolutions('<%=RequestConstans.Vendor.TRADING_APPLICATION%>');
		$('#tsdSolution').html(result);
		$('#tcsSolution').html(result);
		debugger;
		listTradingSoftwareDetails();
		
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
		document.getElementById('changeAnalyticsvendoroffer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('interAnalyticsvendodivoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooAnalyticsvendoffer1').style.backgroundColor = '#5CE5E5';
		var result = vendorTypeSolutions('<%=RequestConstans.Vendor.ANALYTICS_APPLICATION %>');
		$('#asdSolution').html(result);
		listAnalyticsSoftwareDetails();
		
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
		var result = vendorTypeSolutions('<%=RequestConstans.Vendor.RESEARCH_REPORT %>');
		$('#apSolution').html(result);
		$('#rcSolution').html(result);
		$('#rdSolution').html(result);
		listAnalystProfile();
	}
	
}

</script>
</body>
</html>
