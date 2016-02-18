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
											    <a class="addMoreSolution"> <span
																		class="lable_header_add" onclick="createOfferingFile()">Submit </span></a>
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
															 <select id="tcsSolution" name="tcsSolution" onchange="tradingCapabilitiesSupportedOffering()">
															     
														    </select>
														</div>
													</div>
												
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Offerings<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															     <select id="tcsOffering" name="tcsOffering" >
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 35px;">Trade coverage region<span class="required">*</span></label>
														<div class="controlsfortradingapp">
															<select name="tcsTradeCoverageRegion" multiple="multiple" id="tcsTradeCoverageRegion" style="width: 252px;">
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
															<select name="tcsTradeCoverageCountry" multiple="multiple" id="tcsTradeCoverageCountry" style="width: 252px;">
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
																<select name="tcsTradingCapabilitiesType" multiple="multiple" id="tcsTradingCapabilitiesType" style="width: 252px;">
															       <option value ="Order placement/execution" > -Order placement/execution- </option>
															       <option value ="Order allocation" > -Order allocation- </option>
															       <option value ="Trade settlement" > -Trade settlement- </option>
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Trade Executions Type<span class="required">*</span></label>
															<div class="controlsfortradingapp">
																<select name="tcsTradeExecutionsType" multiple="multiple" id="tcsTradeExecutionsType" style="width: 252px;">
																	<option value ="Algorithmic & Program Trading" > -Algorithmic & Program Trading- </option>
															       <option value ="Direct Market Access (DMA)" > -Direct Market Access (DMA)- </option>
															       <option value ="FX -DMA"> -FX -DMA- </option>
															       <option value ="Smart Order Routing (Sequential SOR)"> -Smart Order Routing (Sequential SOR)- </option>
															</select>
															<div class="selectOptions">Choose one or more options</div>
															</div>
													 </div>
													 <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Algorithmic Trade Type<span class="required">*</span></label>
															<div class="controlsfortradingapp">
																<select name="tcsAlgorithmicTradeType" multiple="multiple" id="tcsAlgorithmicTradeType" style="width: 252px;">
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
														   		</select>
														   		<div class="selectOptions">Choose one or more options</div>
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
															<th>Trade coverage region</th>
															<th>Trade Coverage country</th>
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
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Asset Class<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="tdsAssetClass"  id="tdsAssetClass" style="width: 163px;">
														     	    <c:forEach var="assetClasses" items="${assetClasses}">
															    		<option value="${assetClasses.description}">${assetClasses.description}</option>
													  				</c:forEach>
														 	</select> 
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">App.. Name<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<input type="text" id="tdsAppName" placeholder="App.. Name" name="filename" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">App. Desc<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<input type="text" id="tdsAppDesc" placeholder="App. Desc" name="tdsAppDesc" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" class="selectvalues" style="padding-left: 71px;">Tradable regions<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="tdsTradableRegions" multiple="multiple" id="tdsTradableRegions" style="width: 163px;">
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
															<input type="text" id="tdsTradableMarkets" placeholder="Tradable Markets" name="tdsTradableMarkets" class="m-wrap largeval"/>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Accessibility (?)<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="tdsAccessibility"  id="tdsAccessibility" style="width: 163px;">
															     <option value ="Web/Browser Based"> Web/Browser Based </option>
															     <option value ="Binaries(Executable) Based"> Binaries(Executable) Based </option>
															     <option value ="Dedicated Desktop">Dedicated Desktop</option>
														</select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Suitability<span class="required">*</span></label>
														<div class="controlsfortradingappsoftware">
															<select name="tdsSuitability"  id="tdsSuitability" style="width: 163px;">
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
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Offering<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="tdsOffering" placeholder="Offering" name="tdsOffering" class="m-wrap largeval"/>
														</div>
													</div>
													
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Cost Type<span class="required">*</span></label>
														<div class="controls">
															<select name="tdsCostType"  id="tdsCostType" style="width: 163px;">
															     <option value ="Free and Min Balance Not required"> Free and Min Balance Not required </option>
															     <option value ="Min Balance Required"> Min Balance Required </option>
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
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Platform  Cost<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="tdsPlatformCost" placeholder="Platform  Cost" name="tdsPlatformCost" class="m-wrap largeval"/>
														</div>
													</div>
													  <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Platform type<span class="required">*</span></label>
														<div class="controls">
															<select name="tdsPlatformType"  id="tdsPlatformType" style="width: 163px;">
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
															<input type="text" id="tdsExchangeFees" placeholder="Exchange fees" name="tdsExchangeFees" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Stre.. news?<span class="required">*</span></label>
														<div class="controls">
															<input type="checkbox" id="tdsStreNews" placeholder="Stre.. news?" name="tdsStreNews" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">charts avai?<span class="required">*</span></label>
														<div class="controls">
															<input type="checkbox" id="tdsChartsAvai" placeholder="charts avai?" name="tdsChartsAvai" class="m-wrap largeval"/>
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
														</select>
														<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Price Alerts?<span class="required">*</span></label>
														<div class="controls">
															<input type="checkbox" id="tdsPriceAlerts" placeholder="Price Alerts?" name="tdsPriceAlerts" class="m-wrap largeval"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Watchlist?<span class="required">*</span></label>
														<div class="controls">
															<input type="checkbox" id="tdsWatchlist" placeholder="Watchlist" name="tdsWatchlist" class="m-wrap largeval"/>
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
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Trading Cap..<span class="required">*</span></label>
														<div class="controls">
															<select name="tdsTradingCap"  id="tdsTradingCap" style="width: 163px;">
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Trade Exec..<span class="required">*</span></label>
														<div class="controls">
															<select name="tdsTradeExec"  id="tdsTradeExec" style="width: 163px;">
															    <option value ="Trade Executions Supported" > -Trade Executions Supported- </option> 
															    <option value ="Trade Executions Supported" > -Trade Executions Supported- </option>
														 	</select> 
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
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Dark venues<span class="required">*</span></label>
														<div class="controls">
															<select name="tdsDarkVenues"  id="tdsDarkVenues" style="width: 163px;">
														      <option value ="Supported Darkpool venues" > -Supported Darkpool venues- </option>
														      <option value ="Supported Darkpool venues" > -Supported Darkpool venues- </option>
														  </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Add-Ons<span class="required">*</span></label>
														<div class="controls">
															<input type="text" id="tdsAddOns" placeholder="Add-Ons" name="tdsAddOns" class="m-wrap largeval"/>
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
															<select name="asdAccessibility" id="asdAccessibility" style="width: 223px;">
														     	<option value ="Web Browser Based" >-Web Browser Based-</option>
														     	<option value ="Binaries(Executable) Based" > -Binaries(Executable) Based- </option>
														   </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Suitability<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="asdSuitability" id="asdSuitability" style="width: 223px;">
															   <option value ="All Users" >-All Users-</option>
														       <option value ="Risk Managers" > -Risk Managers- </option>
														</select>
														</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Application Cost Type<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="asdApplicationCostType"  id="asdApplicationCostType" style="width: 223px;">
																	<option value ="Free and Min Balance Not required" >-Free and Min Balance Not required-</option>
														            <option value ="Min Balance Required" > -Min Balance Required- </option>
																</select>
															</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Application Subscription CCY<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="asdApplicationSubscriptionCCY" id="asdApplicationSubscriptionCCY" style="width: 223px;">
																	<option value ="GBP" >-GBP-</option>
														            <option value ="USD" > -USD- </option>
														            <option value ="EUR" > -EUR- </option>
															</select>
															</div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpretab3analyticsapp">
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Application Subscription Cost<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="asdApplicationSubscriptionCost" placeholder="Application Subscription Cost" name="asdApplicationSubscriptionCost" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													    <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Application Subscription type <span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="asdApplicationSubscriptionType" id="asdApplicationSubscriptionType" style="width: 223px;">
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
															<input type="checkbox" id="asdRealtimeMarketData" placeholder="Real time Market Data?" name="asdRealtimeMarketData" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Customizable Calculation Models(?)<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="asdCustomizableCalculationModels" placeholder="Customizable Calculation Models" name="asdCustomizableCalculationModels" class="m-wrap largevalforanalytics"/>
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
															<th>Application Subscription CCY</th>
															<th>Application Subscription Cost</th>
															<th>Application Subscription Type</th>
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
											<div class="ColumnCommonmyofferings" id="changeresearchreportvendoroffer2"> <div class="lable_header" id="interresearchreportvendodivoffer2">  <a id="anchooresearchreportvendoffer2" href="#tab13" class="lable_header" data-toggle="tab" onclick="activeVendorAnalyticsResearchMyofferings('${analystprofile}');">Analyst Profile</a> </div> </div>
											<div class="ColumnCommonmyofferings" id="changeresearchreportvendoroffer"> <div class="lable_header" id="interresearchreportvendodivoffer"> <a id="anchooresearchreportvendoffer" href="#tab11" class="lable_header" data-toggle="tab"  onclick="activeVendorAnalyticsResearchMyofferings('${researchcoverage}');">Research  Coverage</a> </div></div>
											<div class="ColumnCommonmyofferings" id="changeresearchreportvendoroffer1"> <div class="lable_header" id="interresearchreportvendodivoffer1">  <a id="anchooresearchreportvendoffer1" href="#tab12" class="lable_header" data-toggle="tab" onclick="activeVendorAnalyticsResearchMyofferings('${researchdetails}');">Research Details</a> </div> </div>
										</div>
										<div class="tab-content" style="background-color: white;">
									
										<!-- vendor Research Coverage start here -->
										<div class="tab-pane" id="tab11">
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
															<select name="rcSolution"  id="rcSolution" style="width: 252px;" onchange="researchReportingVendorOffering('rcSolution','rcOffering')">
															<option value ="-SELECT-"> -SELECT- </option>
															    <c:forEach var="solution" items="${solutions}">
																	<option value="${solution.solution_id}">${solution.name}</option>
													  			</c:forEach>
														</select>
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
													</div>
													<div class="ColumnCommonvendortab3analyticssolutiontab2">
													
														<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Offering<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
																<select name="rcOffering"  id="rcOffering" style="width: 200px;">
															     
														 	</select> 
														</div>
													</div>
													
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Total Research Analyst<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="rcTotalResearchAnalyst" placeholder="Total Research Analyst" name="rcTotalResearchAnalyst" class="m-wrap largevalforanalytics"/>
														</div>
													</div> 
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Prepared by CFA?<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="rcResearchPreparedbyCFA" placeholder="Research Prepared by CFA" name="rcResearchPreparedbyCFA" class="m-wrap largevalforanalytics"/>
														</div>
													</div> 
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Existing Client Base<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="rcExistingClientBase" placeholder="Existing Client Base" name="rcExistingClientBase" class="m-wrap largevalforanalytics"/>
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
														<select name="rdSolution"  id="rdSolution" style="width: 163px;" onchange="researchReportingVendorOffering('rdSolution','rdOffering')">
															
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
															<select name="rdSuitability" id="rdSuitability" style="width: 223px;">
															   <option value ="All Users" >-All Users-</option>
														       <option value ="Risk Managers" > -Risk Managers- </option>
														</select>
														</div>
													</div>
													<div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Cost Type<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="rdReportCostType"  id="rdReportCostType" style="width: 223px;">
																	<option value ="Free and Min Balance Not required" >-Free and Min Balance Not required-</option>
														            <option value ="Min Balance Required" > -Min Balance Required- </option>
																</select>
															</div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpretab3analyticsapp">
													 <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Subscription CCY<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="rdReportSubscriptionCCY" id="rdReportSubscriptionCCY" style="width: 223px;">
																	<option value ="GBP" >-GBP-</option>
														            <option value ="USD" > -USD- </option>
														            <option value ="EUR" > -EUR- </option>
															</select>
															</div>
													</div>
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Report Subscription Cost <span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="rdReportSubscriptionCost" placeholder="Report Subscription Cost" name="rdReportSubscriptionCost" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													    <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Report Subscription type<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="rdReportSubscriptionType" id="rdReportSubscriptionType" style="width: 223px;">
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
																<select name="rdReportFormat" id="rdReportFormat" style="width: 223px;">
															        <option value ="PDF"> PDF  </option>
																     <option value ="DOC"> DOC </option>
																     <option value ="EXCEL"> EXCEL </option>
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Research Applicable Year<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="rdResearchApplicableYear" id="rdResearchApplicableYear" style="width: 223px;">
															        <option value ="Research Applicable Year"> Research Applicable Year  </option>
																     
															</select>
															</div>
													  </div>
													  <div class="control-group">
															<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Research Applicable Month<span class="required">*</span></label>
															<div class="controlsforanalyticsapplcation">
																<select name="rdResearchApplicableMonth" id="rdResearchApplicableMonth" style="width: 223px;">
															        <option value ="Research Applicable Month"> Research Applicable Month  </option>
																     
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
									<div class="tab-pane active" id="tab13">
												<div><br/> </div>
												 <div class="Row">
													<div class="ColumnCommonvendortab3analyticssolution">
													
													
													 <div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Solution<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="apSolution"  id="apSolution" style="width: 223px;">
														</select>
														</div>
													</div>

													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Offering<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="apOffering" placeholder="Offering" name="apOffering" class="m-wrap largeval" style="width: 223px;"/>
														</div>
													</div>
													
													
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Area<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="apResearchArea"  id="apResearchArea" style="width: 223px;">
															     <option value ="Research Area1" > -Research Area1- </option>
															     <option value ="Research Area2" > -Research Area2- </option>
														 	</select> 
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 35px;">Research Sub Area<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<select name="apResearchSubArea" multiple="multiple" id="apResearchSubArea" style="width: 223px;">
															     <option value ="Research Sub Area1" > -Research Sub Area1- </option>
															     <option value ="Research Sub Area2" > -Research Sub Area2- </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Analyst Name<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="text" id="apAnalystName" placeholder="Analyst Name" name="apAnalystName" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 35px;">Research Analyst with CFA?<span class="required">*</span></label>
														<div class="controlsforanalyticsapplcation">
															<input type="checkbox" id="apResearchAnalystWithCFA" placeholder="Research Analyst with CFA" name="apResearchAnalystWithCFA" class="m-wrap largevalforanalytics"/>
														</div>
													</div>
													</div>
													
													 <div class="ColumnCommonvendorpretab3analyticsapp">
													 
													 	<div class="control-group">
																	<label class="control-labelaligndatacoverage"  style="padding-left: 32px;">Offering Desc<span class="required">*</span>
																	</label>
																	<div class="controls">
																		<textarea id="apOfferingDesc"
																			data-mandatory="Y" placeholder="Offering Desc"
																			name="companyinfo"  cols="50" style="width: 223px;"
																			rows="3"></textarea>
																		<div>
																			<label id="tdsOfferingDescErrorMsg"
																				class="errorMessage"></label>
																		</div>
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
																<select name="apAnalystAwards" id="apAnalystAwards" style="width: 223px;">
															        <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
															</select>
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
														<th>Solution</th>
															<th>Offering</th>
															<th>Research Area</th>
															<th>Asset Class</th>
															<th>Research Sub Area</th>
															<th>Analyst Name</th>
															<th>Research Analyst With CFA</th>
															<th>Offering Desc</th>
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
		document.getElementById('changeresearchreportvendoroffer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('interresearchreportvendodivoffer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooresearchreportvendoffer2').style.backgroundColor = '#5CE5E5';
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
