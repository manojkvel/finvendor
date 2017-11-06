<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<c:set var="personaldetails" value="<%=RequestConstans.Vendor.PERSONALDETAILS %>"> </c:set>
<c:set var="supportcoverage" value="<%=RequestConstans.Vendor.SUPPORTCOVERAGE %>"> </c:set>
<c:set var="supportdetails" value="<%=RequestConstans.Vendor.SUPPORTDETAILS %>"> </c:set>
<c:set var="awarddetails" value="<%=RequestConstans.Vendor.AWARDDETAILS %>"> </c:set>
<c:set var="datadistribution" value="<%=RequestConstans.Vendor.DATADISTRIBUTION %>"> </c:set>
<c:set var="databuyers" value="<%=RequestConstans.Vendor.SEARCHDATABUYERS %>"> </c:set>
<c:set var="myrfp" value="<%=RequestConstans.Vendor.MYRFP %>"> </c:set>

<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
	<title>Fin Vendor | Vendor</title>
	<jsp:include page="common/head.jsp" ></jsp:include>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div class="container">
		<jsp:include page="common/header.jsp" ></jsp:include>
	</div>
		<div><br/></div>
   <div class="container">  
        <div class="row-fluid">
					<div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							<div class="portlet-body form">
								<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="Rowtable">
										<div class="ColumnCommonmyprofile" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Personal Details</a> </div></div>
										<div class="ColumnCommonmyprofile" id="change1"> <div class="lable_header" id="interdiv1">  <a id="ancho1" href="#tab2" class="lable_header" data-toggle="tab" onclick="activeMode('${supportcoverage}');">SupportCoverage</a> </div> </div>
										<div class="ColumnCommonmyprofile" id="change2"> <div class="lable_header" id="interdiv2">  <a id="ancho2" href="#tab3" class="lable_header" data-toggle="tab" onclick="activeMode('${awarddetails}');">Award Details</a> </div> </div>
										 <div class="ColumnCommonmyprofile" id="change3"> <div class="lable_header" id="interdiv3"> <a id="ancho3" href="#tab4" class="lable_header" data-toggle="tab" onclick="activeMode('${databuyers}');">Search Data-Buyers</a> </div> </div>
										<div class="ColumnCommonmyprofile" id="change4"> <div class="lable_header" id="interdiv4"> <a id="ancho4" href="#tab5" class="lable_header" data-toggle="tab" onclick="activeMode('${myrfp}');">My RFP</a> </div> </div>
										</div>
										<div class="tab-content" style="background-color: white;">
										<div class="tab-pane active" id="tab1">
												<div class="Rowtableinfoval"> <font id="personaltabsucessmessage" style="font-size: 14px;font-family:Open Sans, sans-serif; position: absolute; color: #2AABAB; font-weight: bold;"></font>
												                              <font id="personaltabfailuremessage" style="font-size: 14px;font-family:Open Sans, sans-serif; position: absolute; color: #B94A48; font-weight: bold;"></font>
												</div>
												<div><br/> </div>
											<div class="Row">
											<div class="ColumnCommonSingletwo">
											<div class="control-group">
												<label class="control-labelalign">First Name<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="personalvenfirstname" placeholder="First Name" name="firstname" class="m-wrap large"  value="${vendor.firstName}" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelalign">Last Name</label>
												<div class="controls">
													<input type="text" id="personalvenlastname" placeholder="Last Name" name="lastname" class="m-wrap large" value="${vendor.lastName}"/>
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelalign">Designation<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="personalvendesignation" placeholder="Designation" name="designation" class="m-wrap large" onblur="validateDesignation();" value="${vendor.designation}"/>
													<div><label id="designationMsg" class="errorMessage"></label></div>
												</div>												
											</div>
											<div class="control-group">
												<label class="control-labelalign">Company<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="personalvencompany" placeholder="Company" name="company" class="m-wrap large" value="${vendor.company}"/>
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelalign">Company URL<span class="required">*</span></label>  
												<div class="controls">
													<input type="text" id="personalvencompanyurl" placeholder="Company URL" name="companyurl" class="m-wrap large" onblur="validateCompanyURL();" value="${vendor.company_url}"/>
													<div><label id="companyURLValidMsg" class="errorMessage"></label></div>
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelalign">Company Info<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="personalvencompanyinfo" placeholder="Company Info" name="companyinfo" class="m-wrap large"  style="height: 85px;" value="${vendor.company_info}"/>
												</div>
											</div>
											
											</div>
											<div class="ColumnCommonSingletwopersonalinfo">
											<div class="control-group">
												<label class="control-labelalign">Primary Email<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="personalvenprimemail" placeholder="Primary Email" name="primaryemail" class="m-wrap large"  onblur="validateVendorPrimaryEmailId();" value="${vendor.email}"/>
													<div><label id="emailerrPrimaryMsg" class="errorMessage"></label></div>
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelalign">Secondary Email</label>
												<div class="controls">
													<input type="text" id="personalvensecemail" placeholder="Secondary Email" name="secondaryemail" class="m-wrap large"  onblur="validateVendorSecondaryEmailId();" value="${vendor.secondary_email}"/>
													<div><label id="emailerrSecondaryMsg" class="errorMessage"></label></div>
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelalign">Phone Number<span class="required">*</span></label>
												<div class="controls">
												<input type="text" id="personalvenphonenumbervalue" placeholder="+ 91" name="phonenumber" class="m-wrap smallic" onblur="validatePhoneNumberCode();"/>
													<input type="text" id="personalvenphonenumber" placeholder="Phone Number" name="phonenumber" class="m-wrap largephonenumber"  onblur="validatePhoneNumber();" value="${vendor.telephone}"/>
													<div class="selectOptions">e.g. +91-9869190000</div>
													<div><label id="phoneNumberMsg" class="errorMessage"></label></div>
												</div>
												
											</div>
											<div class="control-group">
												<label class="control-labelalign">Vendor Region of Incorp<span class="required">*</span></label>
												<div class="controls">
													<select name="personalvenregionofincorp"  id="personalvenregionofincorp" style="width: 200px;">
												     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
													     <c:forEach var="regions" items="${regions}">
													    	<option value="${regions.name}">${regions.name}</option>
													 	 </c:forEach>
												 	</select> 
												</div>
										 </div>
											<div class="control-group">
												<label class="control-labelalign">Vendor Country of Incorp<span class="required">*</span></label>
												<div class="controls">
													<select name="personalvencountryofincorp" id="personalvencountryofincorp" style="width: 200px;">
													     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
													     <c:forEach var="countries" items="${countries}">
														    <option value="${countries.name}">${countries.name}</option>
														  </c:forEach>
											   		</select>
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelalign">Company Logo<span class="required">*</span></label>
												<font id="invalidfileformat" style="bottom: 1px; font-family: 'Open Sans', sans-serif; font-weight:bold; font-size: 12px; position: absolute; color: #B94A48;"></font>
												<div class="controls">
													<input type="file" id="personalvencompanylogo" placeholder="Company Logo" name="companylogo" class="m-wrap largefileuploadcons"  onblur="imageValidation();"/>
													<div class="selectOptions">e.g. .jpeg, .jpg, .png, .gif</div>
												</div>
											</div>
											</div>
											</div>
											<div><br/> </div>
												<div class="form-actions clearfix">
												<div class="se" style="padding-left: 177px;">
													<a class="btn" data-toggle="tab" class="step" onclick="updateVendorPersonalInfo();">Update</a>
													<input type="reset" value="Reset" class="btn" />
													<a  href="#tab2" class="btn button-next" data-toggle="tab" class="step" onclick="activeMode('${supportcoverage}');" style="margin-left:375px;">Continue</a>
												</div>
												</div>
											</div>
											
											<!-- Support coverage start --> 
											
												<div class="tab-pane" id="tab2">
												<div><br/></div>
												<div class="Rowtableinfoval"><font id="supportcoveragetabsucessmessage" style="font-size: 14px;font-family:Open Sans, sans-serif; position: absolute; font-weight: bold; color: #2AABAB; padding-left: 191px; margin-top: -21px;"></font>
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
													         <input type="file" name="fileUpload" class="m-wrap largefileuploadconspopup" id="fileUpload">&nbsp;&nbsp;&nbsp;&nbsp;<a class="fileupload btnpopup btn-default">Upload</a>
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
												<span class="lable_header_add_actionitem_upload">Action item for upload data into table</span> 
												 <div class="Row">
													<div class="ColumnCommonvendor">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 73px;">Asset Class<span class="required">*</span></label>
														<div class="controls" style="margin-left: 175px;">
															<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="assetClasses" items="${assetClasses}">
															    	<option value="${assetClasses.description}">${assetClasses.description}</option>
															 	 </c:forEach>
															 </select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 71px;">Security type<span class="required">*</span></label>
														<div class="controls" style="margin-left:175px;">
															<select name="securitytype"  id="assetClassVendorSecurityMaps" multiple="multiple">
															  	 <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="assetClassVendorSecurityMaps" items="${assetClassVendorSecurityMaps}">
																    <option value="${assetClassVendorSecurityMaps.securityType.name}">${assetClassVendorSecurityMaps.securityType.name}</option>
																  </c:forEach>
															 </select>
															  <div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpage">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Coverage Region<span class="required">*</span></label>
														<div class="controls" style="margin-left: 175px;">
															<select name="datacoverageregion" multiple="multiple" id="datacoverageregion">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														 	<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage"  style="padding-left: 45px;">Coverage Country<span class="required">*</span></label>
														<div class="controls" style="margin-left: 175px;">
															<select name="datacoveragecountry" multiple="multiple" id="datacoveragecountry">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													</div>
													 <div class="ColumnCommonvendorpre">
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 36px;"> Coverage Exchange<span class="required">*</span></label>
														<div class="controls" style="margin-left: 175px;">
															<select name="datacoverageexchange"  multiple="multiple" id="datacoverageexchange">
														     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														     <c:forEach var="exchanges" items="${exchanges}">
															    <option value="${exchanges.name}">${exchanges.name}</option>
															  </c:forEach>
														  </select>
														  <div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-group">
														<label class="control-labelaligndatacoverage" style="padding-left: 50px;">Data Attribute<span class="required">*</span></label>
														<div class="controls" style="margin-left: 175px;">
															<input type="text" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wrap largeval"/>
														</div>
													</div>
													</div> 
												</div>
												<div class="control-group">
														<div class="controls" style="margin-left: 175px;">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="addtotable"> <span class="lable_header_add">Add More </span></a>  <span class="lable_header_add_ActionItem">Action item for pulling data into table</span> 
													 	  <font id="supportceoveragevalidationforaddmore" style="font-size: 14px;font-family:Open Sans, sans-serif; position: absolute; font-weight: bold; color: #B94A48; padding-left: 0px; margin-top: -21px;"></font>
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												
												<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1">
													<thead style="background-color:#7BCCA5;">
														<tr>
															<th>Asset Class</th>
															<th>Security type</th>
															<th>Data Coverage Region</th>
															<th>Data Coverage Country</th>
											                <th>Data Coverage Exchange</th>
															<th>Data Attribute</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach var="vendorSupportCoverageDetailsList" items="${vendorSupportCoverageDetailsList}">
														<tr>
													   		<td>${vendorSupportCoverageDetailsList.assetClass}</td>
															<td>${vendorSupportCoverageDetailsList.securityType}</td>
															<td>${vendorSupportCoverageDetailsList.supportRegion}</td>
															<td>${vendorSupportCoverageDetailsList.supportCountry}</td>
											                <td>${vendorSupportCoverageDetailsList.supportExchange}</td>
															<td>${vendorSupportCoverageDetailsList.dataattribute}</td>
															<td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
												<input type="hidden" id="jsontable1" name="jsontable1"/>
											</div>
											
											
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se" style="padding-left: 150px;">
										<a  href="#tab1" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${personaldetails}');" style="margin-left: -310px;">Back</a>
										<a class="btn" data-toggle="tab" class="step" onclick="updateVendorSupportCoverageInfo();" style="margin-left: 315px;">Update</a>
										<input type="reset" value="Reset" class="btn" />
										<a  href="#tab3" class="btn button-next" data-toggle="tab" class="step" onclick="activeMode('${awarddetails}');" style="margin-left:335px;">Continue</a>
									</div>
									</div>
									</div>
									
									<!-- Support coverage End -->
									<!-- Award Detail Start -->
									  <div class="tab-pane" id="tab3">
											<div class="Rowtableinfoval"><font id="awardtabsucessmessage" style="font-size: 14px;font-family:Open Sans, sans-serif; font-weight: bold; position: absolute; color: #2AABAB; padding-left: 391px; margin-top: -2px;"></font>
											<!-- <div class="ColumnCommonmyprofile"><div class="lable_header">Award Details</div></div> -->
											</div>
											<div><br/> </div>
											<div class="Row">
											<div class="ColumnCommonSingletwo">
											<div class="control-group">
												<label class="control-labelaligndatacoverage">Asset Class<span class="required">*</span></label>
												<div class="controls">
													<select name="awardassetclass" onchange="loadSecurityAwardTypes(this.value);" id="awardassetclass" style="width: 220px;">
													     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
													     <c:forEach var="assetClasses" items="${assetClasses}">
													    	<option value="${assetClasses.description}">${assetClasses.description}</option>
													 	 </c:forEach>
													 </select>
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelaligndatacoverage">Security type<span class="required">*</span></label>
												<div class="controls">
													<select name="awardsecuritytype"  id="assetClassVendorSecurityAwardMaps" multiple="multiple" style="width: 220px;">
													  	 <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
													     <c:forEach var="assetClassVendorSecurityMaps" items="${assetClassVendorSecurityAwardMaps}">
														    <option value="${assetClassVendorSecurityMaps.securityType.name}">${assetClassVendorSecurityMaps.securityType.name}</option>
														  </c:forEach>
													 </select>
													 <div class="selectOptions">Choose one or more options</div>
												</div>
											</div>
											</div>
											<div class="ColumnCommonSingletwo">
											<div class="control-group">
												<label class="control-labelaligndatacoverage">Award Name<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="awardname" placeholder="Award Name" name="awardname" class="m-wrap large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelaligndatacoverage">Award-Main Sponsor<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="awardsponsor" placeholder="Award-Main Sponsor" name="awardsponsor" class="m-wrap large" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-labelaligndatacoverage">Awarded year<span class="required">*</span></label>
												<div class="controls">
													<input type="text" id="awardedyear" placeholder="Awarded year" name="awardedyear" class="m-wrap large" />
												</div>
											</div>
											</div>
											</div>
												<div class="control-group">
														<div class="controls">
														<div class="se" style="  margin: 0px 0px 0px 232px;">
													 	  <a class="awardaddtotable"> <span class="lable_header_add">Add More </span></a>  <span class="lable_header_add_ActionItem">Action item for pulling data into table</span> 
													 	  <font id="awardvalidationforaddmore" style="font-size: 14px;font-family:Open Sans, sans-serif; position: absolute; font-weight: bold; color: #B94A48; padding-left: 191px; margin-top: -21px;"></font>
													 	</div>
														</div>
													</div> 
												<div class="portlet-body">
												
												<table class="table table-striped table-bordered table-hover table-full-width" id="awardsample_1">
													<thead style="background-color:#7BCCA5;">
														<tr>
															<th>Asset Class</th>
															<th>Security type</th>
															<th>Award Name</th>
															<th>Award-Main Sponsor</th>
											                <th>Awarded year</th>
															<th>Actions</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
												<input type="hidden" id="awardjsontable1" name="awardjsontable1"/>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se" style="padding-left: 150px;">
												<a  href="#tab2" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${supportcoverage}');" style="margin-left: -310px;">Back</a>
													<a class="btn" data-toggle="tab" class="step" onclick="updateVendorAwardDetails();" style="margin-left: 315px;">Update</a>
													<input type="reset" value="Reset" class="btn"/>
													<a  href="#tab5" class="btn button-next" data-toggle="tab" class="step" onclick="activeMode('${myrfp}');" style="margin-left:335px;">Continue</a>
												</div>
												</div>
											</div>  
									<!-- Award Details ENDs -->
									
									<!-- My RFP start --> 
											
									<div class="tab-pane" id="tab5">

									<div class="tab-content">
									
									<div class="tab-pane active" id="tab8">
									<div><br/></div>
									<div class="Rowtableinfoval" >
									   <div class="ColumnCommonmyprofinputsearch"> 
											<select name="rfpcompany" class="m-wrap largevalra inpubackground">
											     <option value ="-SELECT-" class="selectvalues"> -Select RFP By Company- </option>
											      <option value ="-SELECT-"> JP Morgan </option>
											      <option value ="-SELECT-"> AXA Fin Corp </option>
											      <option value ="-SELECT-"> MAX Bupa Fin Corp </option>
											</select>
										</div>
										<div class="ColumnCommonmyprofinputsearch"> 
											<select name="rfpassetclass" class="m-wrap largevalra inpubackground">
											     <option value ="-SELECT-" class="selectvalues"> -Select Asset Class- </option>
											     <c:forEach var="assetClasses" items="${assetClasses}">
											    	<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
											 	 </c:forEach>
											</select>
										</div> 
										<div class="ColumnCommonmyprofinputsearch"> 
										  <input type="text"  placeholder="Enter RFP Issue DT" class="m-wrap largeval inpubackground"  id="inputField" name="rfpissuedate"/>
										 </div> 
										<div class="ColumnCommonmyprofinputsearch"> 
											<input type="text"  placeholder="Enter RFP End DT" class="m-wrap largeval inpubackground"  id="inputField1" name="rfpenddate"/>
										</div>
										<div class="ColumnCommonmyprofinputsearch">
											<div class="se">
												<a  href="#" class="btn buttoncheck">Search</a>
													<input type="reset" value="Clear" class="btn buttoncheck"/>
												</div>
										</div>
									</div>
									<div><br/></div>
										  <div class="Rowtableaction" >  
												<div class="ColumnCommonmyprofileaction">  <div class="lable_headeractionbuttons"> <a href="#tab12" class="lable_headeractionbuttons button-next" data-toggle="tab" class="step">Submit final Response</a> </div></div>
												<div class="ColumnCommonmyprofileaction">  <div class="lable_headeractionbuttons"> <a href="#tab11" class="lable_headeractionbuttons button-next" data-toggle="tab" class="step"> Ask for More Info</a> </div></div>
												<div class="ColumnCommonmyprofileaction"> <div class="lable_headeractionbuttons"> <a href="#tab10" class="lable_headeractionbuttons button-next" data-toggle="tab" class="step">Express an Interest</a> </div></div>
												<div class="ColumnCommonmyprofileaction"><div class="lable_headeractionbuttons"> <a href="#tab9" class="lable_headeractionbuttons button-next" data-toggle="tab" class="step">See the Description</a> </div> </div>
												<!-- <div class="ColumnCommonmyprofileaction"> <div class="lable_headeractions"> Actions </div> </div> -->
										  </div> 
										 <div class="portlet-body">
												<table class="table table-striped table-bordered table-hover table-full-width" id="sampledistribution">
													<thead style="background-color: #7BCCA5; color:#FFF;">
														<tr>
															<th>RFP By Company</th>
															<th>RFP Title</th>
															<th>Asset-Class</th>
															<th>Region</th>
															<th>RFP Issue DT</th>
															<th>RFP End DT</th>
														</tr>
													</thead>
													<tbody>
													   <tr>
															<td>JP Morgan</td>
															<td>Equity Index Constituent Data</td>
															<td>Equity</td>
															<td>Asia</td>
															<td>09/09/2019</td>
															<td>11/11/2021</td>
														</tr>
														<tr>
															<td>AXA Fin Corp</td>
															<td>Fixed Income Constituent Data </td>
															<td>Fixed Income</td>
															 <td>Africa</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
														</tr>
														<tr>
															<td>American Fina pvt.ltd</td>
															<td>Indices Constituent Data</td>
															<td>Indices</td>
															 <td>North America</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
														</tr>
														<tr>
															<td>MAX Bupa Fin corp ltd</td>
															<td>Derivates Constituent Data</td>
															<td>Derivates</td>
															 <td>South America</td>
															 <td>12/07/2015</td>
															 <td>13/09/2015</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div><br/></div>
												<div class="form-actions clearfix">
												<div class="se" style="padding-left: 177px;">
												<a  href="#tab3" class="btn button-previous" data-toggle="tab" class="step" onclick="activeMode('${databuyers}');" style="margin-left: -341px;">Back</a>
													<input type="submit" value="Update" class="btn" style="margin-left:295px;"/>
													<input type="reset" value="Reset" class="btn"/>
												</div>
												</div>
											</div>  
											
									<div class="tab-pane" id="tab9">
									 		 <div><br/></div>
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">RFP Detailed Content </div> </div>
											  </div>
											  <div><br></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											   	<div> <br/> </div>
												<div class="ramtra" style="font-weight: bolder;"> Stability in Bull and Bear Markets: </div>
											 		Diversified Equity Funds comprise of all markets cap stocks. Large cap stocks due to high end market capitalization tend to be stable in bear markets and show moderate appreciation in bull markets. Mid and small cap stocks respond to market stimulations. While, they show higher appreciation in bull markets, their depreciation is in sync with the bear markets. The differences in the performance of these market caps get balanced in the Diversified Equity Funds. In a bear market the mid and small cap stocks have a tendency to be volatile even if the large cap stocks shows moderate depreciation, thereby maintaining a steady balance. Due to this stability it allows investors with a varying risk appetite to park their investments in these funds.
												<div class="ramtra" style="font-weight: bolder;">Reduces the Need to Diversify: </div>
													Financial planners and advisors keep emphasising about the need to diversify your investments. It is said that diversification in various asset classes determines the return of the portfolio and not the individual funds. Investing in Diversified Equity Funds reduces the need to diversify your portfolio as you choose an already diversified fund depending upon your investing needs and risk taking ability. As an investor if you are looking for stability in your investments, you could allocate a larger portion of your investments in Diversified Equity Funds and the remaining in Small and Mid Cap Funds. However, If you are an aggressive investor and ready to take high risk for long term appreciation then Mid and Small Cap Funds could be ideal investments for you.
												<div class="ramtra" style="font-weight: bolder;"> A universal Appeal: </div>
												The fund has a component to appeal to all kinds of investors: the risk takers, the safe player and the flexible investor. It also reduces the need to diversify. Hence, as an investor if you like to manage your own portfolio then this reduces your need to diversify to a certain degree. It provides stability to your portfolio along with a return range of moderate to high.
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    <a onclick="openWindow();">It is easier to find a Web-based vendor that sells the item I wish to purchase.</a> 
												</p>
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    I can quickly gather information about products and services I wish to purchase from Web-based vendors.
												</p>
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    Web-based vendors deliver orders/services in a more timely manner.
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se" style="padding-left: 60px;">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step" style="margin-left: -212px;">Back</a> 
														<input type="submit" value="express an interest" class="btn buttoncheck" style="margin-left: 202px;"/>
														<input type="reset" value="ask for more info" class="btn buttoncheck"/>
														<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step">submit final response</a>
													</div>
												</div>  
										</div>  
										<div class="tab-pane" id="tab10">
											 <div><br/></div>
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">RFP Detailed Content </div> </div>
											  </div>
											  <div><br></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											   	<div> <br/> </div>
												<div class="ramtra" style="font-weight: bolder;"> Stability in Bull and Bear Markets: </div>
											 		Diversified Equity Funds comprise of all markets cap stocks. Large cap stocks due to high end market capitalization tend to be stable in bear markets and show moderate appreciation in bull markets. Mid and small cap stocks respond to market stimulations. While, they show higher appreciation in bull markets, their depreciation is in sync with the bear markets. The differences in the performance of these market caps get balanced in the Diversified Equity Funds. In a bear market the mid and small cap stocks have a tendency to be volatile even if the large cap stocks shows moderate depreciation, thereby maintaining a steady balance. Due to this stability it allows investors with a varying risk appetite to park their investments in these funds.
												<div class="ramtra" style="font-weight: bolder;">Reduces the Need to Diversify: </div>
													Financial planners and advisors keep emphasising about the need to diversify your investments. It is said that diversification in various asset classes determines the return of the portfolio and not the individual funds. Investing in Diversified Equity Funds reduces the need to diversify your portfolio as you choose an already diversified fund depending upon your investing needs and risk taking ability. As an investor if you are looking for stability in your investments, you could allocate a larger portion of your investments in Diversified Equity Funds and the remaining in Small and Mid Cap Funds. However, If you are an aggressive investor and ready to take high risk for long term appreciation then Mid and Small Cap Funds could be ideal investments for you.
												<div class="ramtra" style="font-weight: bolder;"> A universal Appeal: </div>
												The fund has a component to appeal to all kinds of investors: the risk takers, the safe player and the flexible investor. It also reduces the need to diversify. Hence, as an investor if you like to manage your own portfolio then this reduces your need to diversify to a certain degree. It provides stability to your portfolio along with a return range of moderate to high.
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    It is easier to find a Web-based vendor that sells the item I wish to purchase.
												</p>
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    I can quickly gather information about products and services I wish to purchase from Web-based vendors.
												</p>
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    Web-based vendors deliver orders/services in a more timely manner.
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se" style="padding-left: 60px;">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step" style="margin-left: -212px;">Back</a> 
														<input type="submit" value="express an interest" class="btn buttoncheck" style="margin-left: 202px;"/>
														<input type="reset" value="ask for more info" class="btn buttoncheck"/>
														<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step">submit final response</a>
													</div>
												</div>  
											</div> 
											<div class="tab-pane" id="tab11">
													<div><br/></div>
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">RFP Detailed Content </div> </div>
											  </div>
											  <div><br></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											   	<div> <br/> </div>
												<div class="ramtra" style="font-weight: bolder;"> Stability in Bull and Bear Markets: </div>
											 		Diversified Equity Funds comprise of all markets cap stocks. Large cap stocks due to high end market capitalization tend to be stable in bear markets and show moderate appreciation in bull markets. Mid and small cap stocks respond to market stimulations. While, they show higher appreciation in bull markets, their depreciation is in sync with the bear markets. The differences in the performance of these market caps get balanced in the Diversified Equity Funds. In a bear market the mid and small cap stocks have a tendency to be volatile even if the large cap stocks shows moderate depreciation, thereby maintaining a steady balance. Due to this stability it allows investors with a varying risk appetite to park their investments in these funds.
												<div class="ramtra" style="font-weight: bolder;">Reduces the Need to Diversify: </div>
													Financial planners and advisors keep emphasising about the need to diversify your investments. It is said that diversification in various asset classes determines the return of the portfolio and not the individual funds. Investing in Diversified Equity Funds reduces the need to diversify your portfolio as you choose an already diversified fund depending upon your investing needs and risk taking ability. As an investor if you are looking for stability in your investments, you could allocate a larger portion of your investments in Diversified Equity Funds and the remaining in Small and Mid Cap Funds. However, If you are an aggressive investor and ready to take high risk for long term appreciation then Mid and Small Cap Funds could be ideal investments for you.
												<div class="ramtra" style="font-weight: bolder;"> A universal Appeal: </div>
												The fund has a component to appeal to all kinds of investors: the risk takers, the safe player and the flexible investor. It also reduces the need to diversify. Hence, as an investor if you like to manage your own portfolio then this reduces your need to diversify to a certain degree. It provides stability to your portfolio along with a return range of moderate to high.
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												   It is easier to find a Web-based vendor that sells the item I wish to purchase.
												</p>
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    I can quickly gather information about products and services I wish to purchase from Web-based vendors.
												</p>
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    Web-based vendors deliver orders/services in a more timely manner.
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se" style="padding-left: 60px;">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step" style="margin-left: -212px;">Back</a> 
														<input type="submit" value="express an interest" class="btn buttoncheck" style="margin-left: 202px;"/>
														<input type="reset" value="ask for more info" class="btn buttoncheck"/>
														<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step">submit final response</a>
													</div>
												</div>  
											</div>  
											
											<div class="tab-pane" id="tab12">
													<div><br/></div>
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">RFP Detailed Content </div> </div>
											  </div>
											  <div><br></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											   	<div> <br/> </div>
												<div class="ramtra" style="font-weight: bolder;"> Stability in Bull and Bear Markets: </div>
											 		Diversified Equity Funds comprise of all markets cap stocks. Large cap stocks due to high end market capitalization tend to be stable in bear markets and show moderate appreciation in bull markets. Mid and small cap stocks respond to market stimulations. While, they show higher appreciation in bull markets, their depreciation is in sync with the bear markets. The differences in the performance of these market caps get balanced in the Diversified Equity Funds. In a bear market the mid and small cap stocks have a tendency to be volatile even if the large cap stocks shows moderate depreciation, thereby maintaining a steady balance. Due to this stability it allows investors with a varying risk appetite to park their investments in these funds.
												<div class="ramtra" style="font-weight: bolder;">Reduces the Need to Diversify: </div>
													Financial planners and advisors keep emphasising about the need to diversify your investments. It is said that diversification in various asset classes determines the return of the portfolio and not the individual funds. Investing in Diversified Equity Funds reduces the need to diversify your portfolio as you choose an already diversified fund depending upon your investing needs and risk taking ability. As an investor if you are looking for stability in your investments, you could allocate a larger portion of your investments in Diversified Equity Funds and the remaining in Small and Mid Cap Funds. However, If you are an aggressive investor and ready to take high risk for long term appreciation then Mid and Small Cap Funds could be ideal investments for you.
												<div class="ramtra" style="font-weight: bolder;"> A universal Appeal: </div>
												The fund has a component to appeal to all kinds of investors: the risk takers, the safe player and the flexible investor. It also reduces the need to diversify. Hence, as an investor if you like to manage your own portfolio then this reduces your need to diversify to a certain degree. It provides stability to your portfolio along with a return range of moderate to high.
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    It is easier to find a Web-based vendor that sells the item I wish to purchase.
												</p>
												<p> 
												   <select class="selectquestion selectvalues">
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    I can quickly gather information about products and services I wish to purchase from Web-based vendors.
												</p>
												<p> 
												   <select class="selectquestion selectvalues" >
												      <option>Not Answered</option>
												      <option>Strongly Disagree</option>
												      <option>Disagree</option>
												      <option>Neither Disagree/Agree</option>
												      <option>Agree</option>
												      <option>Strongly Agree</option>
												   </select>
												    Web-based vendors deliver orders/services in a more timely manner.
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se" style="padding-left: 60px;">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step" style="margin-left: -212px;">Back</a> 
														<input type="submit" value="express an interest" class="btn buttoncheck" style="margin-left: 202px;"/>
														<input type="reset" value="ask for more info" class="btn buttoncheck"/>
														<a  href="#tab13" class="btn button-next" data-toggle="tab" class="step">submit final response</a>
													</div>
												</div>  
											</div>  
											
											<div class="tab-pane" id="tab13">
													<div><br/></div>
											  <div class="ramtra">
											  	<div class="ramtra" style="font-weight: bolder;">Benefits of Investing in Diversified Equity Funds:</div>
											  </div>
											    <br/> 
											  <div class="Rowtable" >
											  <div class="ColumnCommonmyprofiletabrayu"> <div class="lable_headervem">Questionnaires</div> </div>
											  </div>
												 <br/> 
												<div class="ramtra">
												<p> 
												   1) It is easier to find a Web-based vendor that sells the item I wish to purchase?
												    <br>
												    <input type="text" class="m-wrap large">
												</p>
												<p> 
												   2) I can quickly gather information about products and services I wish to purchase from Web-based vendors?
												    <br>
												    <input type="text" class="m-wrap large">
												</p>
												<p> 
												   3) Web-based vendors deliver orders/services in a more timely manner?
												   <br>
												    <input type="text" class="m-wrap large">
												</p>
												
												</div>
												<br/>
												  <div class="form-actions clearfix">
													<div class="se" style="padding-left: 180px;">
													  <a  href="#tab8" class="btn button-previous" data-toggle="tab" class="step" style="margin-left: -212px;">Back</a> 
														<input type="reset" value="submit response" class="btn buttoncheck"/>
													</div>
												</div>  
											</div>  
											
											</div>
											
											
									
									</div>
									<!-- My RFP  ENds-->
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

<!-- tab active code starts here-->
<script type="text/javascript">
window.onload = function(){
	var personalDetails= '<%=RequestConstans.Vendor.PERSONALDETAILS %>';
	if(personalDetails != null && personalDetails.length > 0 && personalDetails.match("personaldetails")){
		document.getElementById('change').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdiv').style.backgroundColor = '#5CE5E5';  
		document.getElementById('ancho').style.backgroundColor = '#5CE5E5';
	}
};
</script>
<!-- tab active code end here-->
  
<!-- popup window plugins start-->
<script src="<%=request.getContextPath() %>/resources/js/popup.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/modernizr.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/bootstrap-min.js" type="text/javascript"></script>
 <!-- popup window plugins ends-->

<!-- Add to table plugins start-->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
<!-- Add to table plugins end here-->

<!-- Date plugins start-->
		<script>
		  $(function() {
		    $( "#inputField" ).datepicker();
		    $( "#inputField1" ).datepicker();
		  });
	  </script>
	  <script src="//code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script>
  
  <!-- Date plugins start-->
  
</body>
</html>
