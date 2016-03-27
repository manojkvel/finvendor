<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld"%>
<c:set var="personaldetails"
	value="<%=RequestConstans.Vendor.PERSONALDETAILS%>">
</c:set>
<c:set var="supportcoverage"
	value="<%=RequestConstans.Vendor.SUPPORTCOVERAGE%>">
</c:set>
<c:set var="supportdetails"
	value="<%=RequestConstans.Vendor.SUPPORTDETAILS%>">
</c:set>
<c:set var="awarddetails"
	value="<%=RequestConstans.Vendor.AWARDDETAILS%>">
</c:set>
<c:set var="datadistribution"
	value="<%=RequestConstans.Vendor.DATADISTRIBUTION%>">
</c:set>
<c:set var="databuyers"
	value="<%=RequestConstans.Vendor.SEARCHDATABUYERS%>">
</c:set>
<c:set var="myrfp" value="<%=RequestConstans.Vendor.MYRFP%>">
</c:set>

<!DOCTYPE html>
<head>
<meta charset="utf-8" />
<title>Fin Vendor | Vendor</title>
<script
	src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp"></jsp:include>
	<div class="container">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid service-box">
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue" id="form_wizard_1">
								<div class="portlet-title"></div>
								<div class="portlet-body form">
									<form action="#" class="form-horizontal" id="submit_form"
										method="post" enctype="multipart/form-data">
										<div class="form-wizard">
											<div class="Rowtable">
												<div class="ColumnCommonmyprofile" id="change">
													<div class="lable_header" id="interdiv">
														<a id="ancho" href="#tab1" class="lable_header"
															data-toggle="tab"
															onclick="activeMode('${personaldetails}');">Personal Details</a>
													</div>
												</div>
												<!-- 
												<div class="ColumnCommonmyprofile" id="change1">
													<div class="lable_header" id="interdiv1">
														<a id="ancho1" href="#tab2" class="lable_header"
															data-toggle="tab"
															onclick="activeMode('${supportcoverage}');">SupportCoverage</a>
													</div>
												</div>  -->
												<div class="ColumnCommonmyprofile" id="change2">
													<div class="lable_header" id="interdiv2">
														<a id="ancho2" href="#tab3" class="lable_header"
															data-toggle="tab"
															onclick="activeMode('${awarddetails}');">Award Details</a>
													</div>
												</div>
												<%-- <div class="ColumnCommonmyprofile" id="change3"> <div class="lable_header" id="interdiv3"> <a id="ancho3" href="#tab4" class="lable_header" data-toggle="tab" onclick="activeMode('${databuyers}');">Search Data-Buyers</a> </div> </div> --%>
												<div class="ColumnCommonmyprofile" id="change4">
													<div class="lable_header" id="interdiv4">
														<a id="ancho4" href="#tab5" class="lable_header"
															data-toggle="tab" onclick="activeMode('${myrfp}');">My RFP</a>
													</div>
												</div>
											</div>
											<div class="tab-content" style="background-color: white;">
												<div class="tab-pane active" id="tab1">
													<div class="Rowtableinfoval">
														<font id="personaltabsucessmessage"
															style="font-size: 14px; font-family: Open Sans, sans-serif; position: absolute; color: #2AABAB; font-weight: bold;"></font>
														<font id="personaltabfailuremessage"
															style="font-size: 14px; font-family: Open Sans, sans-serif; position: absolute; color: #B94A48; font-weight: bold;"></font>
													</div>
													<div>
														<br />
													</div>
													<div class="Row">
														<span id="vendorProfileSpan">
															<div class="ColumnCommonSingletwo">
																<div class="control-group">
																	<label class="control-labelalign">First Name<span
																		class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="personalvenfirstname"
																			data-mandatory="Y" placeholder="First Name"
																			name="firstname" class="m-wrap"
																			onblur="validateNotNull(this, 'vendorProfileFirstNameErrorMsg')"
																			value="${vendor.firstName}" />
																		<div>
																			<label id="vendorProfileFirstNameErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Last Name</label>
																	<div class="controls">
																		<input type="text" id="personalvenlastname"
																			placeholder="Last Name" name="lastname"
																			class="m-wrap" value="${vendor.lastName}" />
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Designation<span
																		class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="personalvendesignation"
																			data-mandatory="Y" placeholder="Designation"
																			name="designation" class="m-wrap"
																			onblur="validateWithRegularExpression(this, 'vendorProfileDesignationErrorMsg', regularExpressionMap['DESIGNATION'], 'DESIGNATION', true)"
																			value="${vendor.designation}" />
																		<div>
																			<label id="vendorProfileDesignationErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Company<span
																		class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="personalvencompany"
																			data-mandatory="Y" placeholder="Company"
																			name="company" class="m-wrap"
																			onblur="validateNotNull(this, 'vendorProfileCompanyErrorMsg')"
																			value="${vendor.company}" />
																		<div>
																			<label id="vendorProfileCompanyErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Company URL<span
																		class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="personalvencompanyurl"
																			data-mandatory="Y" placeholder="Company URL"
																			name="companyurl" class="m-wrap"
																			onblur="validateWithRegularExpression(this, 'vendorProfileCompanyURLErrorMsg', regularExpressionMap['URL'], 'COMPANY_URL', true)"
																			value="${vendor.companyUrl}" />
																		<div>
																			<label id="vendorProfileCompanyURLErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Company Information<span class="required">*</span>
																	</label>
																	<div class="controls">
																		<textarea id="personalvencompanyinfo"
																			data-mandatory="Y" placeholder="Company Information"
																			name="companyinfo" class="m-wrap large" cols="30"
																			rows="5"
																			onblur="validateNotNull(this, 'vendorProfileCompanyInfoErrorMsg')">${vendor.companyInfo}</textarea>
																		<div>
																			<label id="vendorProfileCompanyInfoErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																
																<label class="control-labelalign">Support Coverage</label>
																
															<div style="padding-left: 175px;">	
																
																 <label class="control-labelalign-checkbox">&nbsp;&nbsp;<input type="radio" name="support" value="24X7" <c:if test="${vendor.vendorSupport.c24by7=='true'}"> checked </c:if>>&nbsp;24x7</label>  
															    <label class="control-labelalign-checkbox">&nbsp;&nbsp;<input type="radio" name="support" value="24by6" <c:if test="${vendor.vendorSupport.c24by6=='true'}"> checked </c:if>>&nbsp;24x6</label> 
															    <label class="control-labelalign-checkbox">&nbsp;&nbsp; <input type="radio" name="support" value="16by7" <c:if test="${vendor.vendorSupport.c16by7=='true'}"> checked </c:if>>16x7&nbsp;</label> 
														    	 <label class="control-labelalign-checkbox"> <input type="radio" name="support" value="16by6" <c:if test="${vendor.vendorSupport.c16by6=='true'}"> checked </c:if>>&nbsp;16x6</label>
															     <label class="control-labelalign-checkbox">&nbsp;&nbsp;<input type="radio" name="support" value="16by5" <c:if test="${vendor.vendorSupport.c16by5=='true'}"> checked </c:if>>16x5&nbsp;</label>
														    	 <label class="control-labelalign-checkbox"> <input type="radio" name="support" value="8by7" <c:if test="${vendor.vendorSupport.c8by7=='true'}"> checked </c:if>> 8x7&nbsp;</label>
															     <label class="control-labelalign-checkbox">&nbsp;<input type="radio" name="support" value="8by6" <c:if test="${vendor.vendorSupport.c8by6=='true'}"> checked </c:if>>8x6&nbsp;</label>
														    	 <label class="control-labelalign-checkbox">&nbsp;&nbsp;<input type="radio" name="support" value="8by5" <c:if test="${vendor.vendorSupport.c8by5=='true'}"> checked </c:if> >&nbsp;8x5</label>
															    
																<label class="control-labelalign-checkbox">&nbsp;&nbsp;<input type="checkbox" name="weekend" id="weekend" value="weekend" <c:if test="${vendor.vendorSupport.weekend=='true'}"> checked </c:if>>Weekend</label>
																<label class="control-labelalign-checkbox">&nbsp;<input type="checkbox" name="publicHolidays" id="publicHolidays" value="publicholidays" <c:if test="${vendor.vendorSupport.publicholidays=='true'}"> checked </c:if>>Public Holidays</label> 
																</div>
																</div>
															</div>
															<div class="ColumnCommonSingletwopersonalinfo">
																<div class="control-group">
																	<label class="control-labelalign">Primary Email<span
																		class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="personalvenprimemail"
																			data-mandatory="Y" placeholder="Primary Email"
																			name="primaryemail" class="m-wrap large"
																			<%--onblur="if (validateWithRegularExpression(this, 'vendorProfilePrimaryEmailErrorMsg', regularExpressionMap['EMAIL'], 'EMAIL', true)) validateAjax(this, 'checkExistingEmail','vendorProfilePrimaryEmailErrorMsg')"--%>
																			readonly value="${vendor.user.email}" />
																		<div>
																			<label id="vendorProfilePrimaryEmailErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Secondary
																		Email</label>
																	<div class="controls">
																		<input type="text" id="personalvensecemail"
																			placeholder="Secondary Email" name="secondaryemail"
																			class="m-wrap large"
																			onblur="if (validateWithRegularExpression(this, 'vendorProfileSecondaryEmailErrorMsg', regularExpressionMap['EMAIL'], 'EMAIL', false)) validateAjax(this, 'checkExistingEmail','vendorProfileSecondaryEmailErrorMsg')"
																			value="${vendor.secondaryEmail}" />
																		<div>
																			<label id="vendorProfileSecondaryEmailErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Phone Number<span
																		class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="personalvenphonenumbercode"
																			data-mandatory="Y" placeholder="Code"
																			name="phonenumber" class="m-wrap smallic"
																			onblur="validateWithRegularExpression(this, 'vendorProfilePhoneNumberCodeErrorMsg', regularExpressionMap['PHONE_CODE'], 'PHONE_CODE', true)" 
																			value="${vendor.telephoneCode}" />
																		<input type="text" id="personalvenphonenumber"
																			data-mandatory="Y" placeholder="Phone Number"
																			name="phonenumber" class="m-wrap largephonenumber"
																			onblur="validateWithRegularExpression(this, 'vendorProfilePhoneNumberErrorMsg', regularExpressionMap['PHONE_NUMBER'], 'PHONE', true)"
																			value="${vendor.telephone}" />
																		<div class="selectOptions">e.g. +91 9869190000</div>
																		<div>
																			<label id="vendorProfilePhoneNumberCodeErrorMsg"
																				class="errorMessage"></label>
																		</div>
																		<div>
																			<label id="vendorProfilePhoneNumberErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Country of
																		Incorporation<span class="required">*</span>
																	</label>
																	<div class="controls">
																		<select name="personalvencountryofincorp" 
																			id="personalvencountryofincorp" data-mandatory="Y"
																			style="width: 200px" onchange="getRegion('personalvencountryofincorp','personalvenregionofincorp')">
																			<c:forEach var="countries" items="${countries}">
																				<c:choose>
																					   <c:when test="${vendor.countryofincorp eq countries.country_id}">
																						        <option value="${countries.country_id}"  selected="${countries.country_id}">${countries.name}</option>
																						    </c:when>    
																						    <c:otherwise>
																						    	<option value="${countries.country_id}">${countries.name}</option>
																						    </c:otherwise>
																				 </c:choose>
																			</c:forEach>
																		</select>
																		<div>
																			<label id="vendorProfileCountryOfIncorpErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																
																<div class="control-group">
																	<label class="control-labelalign">Region of	Incorporation<span class="required">*</span>
																	</label>
																<div class="controls">
																		
																	<input type="text" id="personalvenregionofincorp"
																			name="personalvenregionofincorp" class="m-wrap" readonly="readonly" value="Asia Pacific"
																	 />
																</div>															
																</div>
												
																<div class="control-group">
																	<label class="control-labelalign">Company Logo<span
																		class="required">*</span></label> <font id="invalidfileformat"
																		style="bottom: 1px; font-family: 'Open Sans', sans-serif; font-weight: bold; font-size: 12px; position: absolute; color: #B94A48;"></font>
																	<div class="controls">
																		<input type="file" id="personalvencompanylogo"
																			placeholder="Company Logo" name="companylogo"
																			class="m-wrap largefileuploadcons"
																			onblur="imageValidation();" value="${vendor.logoName}" />
																		<div class="selectOptions">e.g. .jpeg, .jpg,
																			.png, .gif</div>
																			<div id="displayLogo">
																					<img src="<%=request.getContextPath() %>/getfile/logo"  style="height: 61px; width: 115px; margin-top: 10px" />
																					
																			</div>
																	</div>
																</div>
																
															</div>
														</span>
													</div>
													<div>
														<br />
													</div>
													<div class="form-actions clearfix">
														<div class="se" style="padding-left: 177px;">
															<a class="btn" data-toggle="tab" class="step"
																onclick="validateSpanElements('vendorProfileSpan');updateVendorPersonalInfo();">Update</a>
															
															<a
																href="#tab3" class="btn button-next" data-toggle="tab"
																class="step" onclick="activeMode('${awarddetails}');"
																style="margin-left: 5px;">Continue</a>
														</div>
													</div>
												</div>

												<div class="tab-pane" id="tab3">
													<div class="Rowtableinfoval">
														<font id="awardtabsucessmessage"
															style="font-size: 14px; font-family: Open Sans, sans-serif; font-weight: bold; position: absolute; color: #2AABAB; padding-left: 391px; margin-top: -2px;"></font>
														<!-- <div class="ColumnCommonmyprofile"><div class="lable_header">Award Details</div></div> -->
													</div>
													<div>
														<br />
													</div>
													<div class="Row">
											
														<div class="ColumnCommonSingletwo">
															<div class="control-group">
																<label class="control-labelaligndatacoverage">Award
																	Name<span class="required">*</span>
																</label>
																<div class="controls">
																	<input type="text" id="awardname"
																		placeholder="Award Name" name="awardname"
																		class="m-wrap large" />
																<div>
																
																<label id="awardnameErrorMsg" class="errorMessage"></label>
																</div>
																
																</div>
																
																
															</div>
															<div class="control-group">
																<label class="control-labelaligndatacoverage">Award-Main
																	Sponsor<span class="required">*</span>
																</label>
																<div class="controls">
																	<input type="text" id="awardsponsor"
																		placeholder="Award-Main Sponsor" name="awardsponsor"
																		class="m-wrap large" />
																</div>
															</div>
															<div class="control-group">
																<label class="control-labelaligndatacoverage">Awarded
																	year<span class="required">*</span>
																</label>
																<div class="controls">
																	<input type="text" id="awardedyear"
																		placeholder="Awarded year" name="awardedyear"
																		class="m-wrap large" onblur="validateYear(this)"/>
																</div>
															</div>
														</div>
														
														
														
														<div class="ColumnCommonSingletwo">
															<div class="control-group" >
																<label class="control-labelaligndatacoverage"
													style="padding-left: 47px;"><font color="black">Vendor Type</font></label>
												<div class="controls">
													<select name="awardVendorType" id="awardVendorType">
														<option value="Data Aggregator vendor">Data	Aggregator vendor</option>
														<option value="Trading Application vendor">Trading Application vendor</option>
														<option value="Analytics Application vendor">Analytics Application vendor</option>
														<option value="Research Reporting vendor">Research Reporting vendor</option>
													</select>
												</div>
															</div>
															<div class="control-group">
															<div id="assetClassDiv" >
																<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Asset Class</label>
																<div class="controls">
																<select name="awardAssetclass" id="awardAssetclass">
																		<c:forEach var="assetClasses" items="${assetClasses}">
																			<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
																		</c:forEach>
																	</select>
																</div>	
															</div>
															<div id="analyticsSolutionsTypeDiv" style="display:none">	
																<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Analytics Solutions Type</label>
														<div class="controls" >
															<select name="awardAnalyticsSolutionsType"  id="awardAnalyticsSolutionsType" >
															     <option value ="Banking Solutions" > Banking Solutions </option>
															     <option value ="Alternative Investment Solutions" > Alternative Investment Solutions </option>
																<option value ="Backoffice Operations" > Backoffice Operations</option>
																<option value ="Backoffice Operations" > Backoffice Operations </option>
																<option value ="Margining Solutions" > Margining Solutions</option>
																<option value ="Portfolio Management Solutions" > Portfolio Management Solutions</option>
																<option value ="Product Valuation" > Product Valuation</option>
																<option value ="Regulatory, Compliance & Risk Mgm" > Regulatory, Compliance & Risk Mgm</option>
														 	</select>
														</div>	
														</div>			
													<div id="researchAreaDiv" style="display:none">				
														<label class="control-labelaligndatacoverage" style="padding-left: 47px;">Research Area</label>
														<div class="controls">
															<select name="awardResearchArea"  id="awardResearchArea">
															     
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
														</div>
														
													</div>
													<div class="control-group">
														<div class="controls">
															<div class="se" style="margin: 0px 0px 0px 232px;">
																<a class="awardaddtotable"> <span
																	class="lable_header_add" onclick="addVendorAward()">Add More </span></a> 
															</div>
														</div>
													</div>
													<div class="portlet-body">

														<table
															class="table table-striped table-bordered table-hover table-full-width"
															id="awardsample_1">
															<thead style="background-color: #7BCCA5;">
																<tr>
																	<th>Award Name</th>
																	<th>Award-Main Sponsor</th>
																	<th>Awarded year</th>
																	<th>Vendor Type</th>
																	<th>#</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
														<input type="hidden" id="awardjsontable1"
															name="awardjsontable1" />
													</div>
													<div>
														<br />
													</div>
													
												</div>
												<!-- Award Details ENDs -->

												<!-- My RFP start -->

												<div class="tab-pane" id="tab5">

													<div class="tab-content">

														<div class="tab-pane active" id="tab8">
															<div>
																<br />
															</div>
															<div class="Rowtableinfoval">
																<div class="ColumnCommonmyprofinputsearch">
																	<select name="rfpcompany"
																		class="m-wrap largevalra inpubackground">
																		<option value="-SELECT-" class="selectvalues">
																			-Select RFP By Company-</option>
																		<option value="-SELECT-">JP Morgan</option>
																		<option value="-SELECT-">AXA Fin Corp</option>
																		<option value="-SELECT-">MAX Bupa Fin Corp</option>
																	</select>
																</div>
																<div class="ColumnCommonmyprofinputsearch">
																	<select name="rfpassetclass"
																		class="m-wrap largevalra inpubackground">
																		<option value="-SELECT-" class="selectvalues">
																			-Select Asset Class-</option>
																		<c:forEach var="assetClasses" items="${assetClasses}">
																			<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
																		</c:forEach>
																	</select>
																</div>
																<div class="ColumnCommonmyprofinputsearch">
																	<input type="text" placeholder="Enter RFP Issue DT"
																		class="m-wrap largeval inpubackground" id="inputField"
																		name="rfpissuedate" />
																</div>
																<div class="ColumnCommonmyprofinputsearch">
																	<input type="text" placeholder="Enter RFP End DT"
																		class="m-wrap largeval inpubackground"
																		id="inputField1" name="rfpenddate" />
																</div>
																<div class="ColumnCommonmyprofinputsearch">
																	<div class="se">
																		<a href="#" class="btn buttoncheck">Search</a> <input
																			type="reset" value="Clear" class="btn buttoncheck" />
																	</div>
																</div>
															</div>
															<div>
																<br />
															</div>
															<div class="Rowtableaction">
																<div class="ColumnCommonmyprofileaction">
																	<div class="lable_headeractionbuttons">
																		<a href="#tab12"
																			class="lable_headeractionbuttons button-next"
																			data-toggle="tab" class="step">Submit final
																			Response</a>
																	</div>
																</div>
																<div class="ColumnCommonmyprofileaction">
																	<div class="lable_headeractionbuttons">
																		<a href="#tab11"
																			class="lable_headeractionbuttons button-next"
																			data-toggle="tab" class="step"> Ask for More Info</a>
																	</div>
																</div>
																<div class="ColumnCommonmyprofileaction">
																	<div class="lable_headeractionbuttons">
																		<a href="#tab10"
																			class="lable_headeractionbuttons button-next"
																			data-toggle="tab" class="step">Express an
																			Interest</a>
																	</div>
																</div>
																<div class="ColumnCommonmyprofileaction">
																	<div class="lable_headeractionbuttons">
																		<a href="#tab9"
																			class="lable_headeractionbuttons button-next"
																			data-toggle="tab" class="step">See the
																			Description</a>
																	</div>
																</div>
																<!-- <div class="ColumnCommonmyprofileaction"> <div class="lable_headeractions"> Actions </div> </div> -->
															</div>
															<div class="portlet-body">
																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sampledistribution">
																	<thead style="background-color: #7BCCA5; color: #FFF;">
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
																			<td>Fixed Income Constituent Data</td>
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
															<div>
																<br />
															</div>
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 177px;">
																	<a href="#tab3" class="btn button-previous"
																		data-toggle="tab" class="step"
																		onclick="activeMode('${databuyers}');"
																		style="margin-left: -341px;">Back</a> <input
																		type="submit" value="Update" class="btn"
																		style="margin-left: 295px;" /> <input type="reset"
																		value="Reset" class="btn" />
																</div>
															</div>
														</div>

														<div class="tab-pane" id="tab9">
															<div>
																<br />
															</div>
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">RFP Detailed Content
																	</div>
																</div>
															</div>
															<div>
																<br>
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
																<div>
																	<br />
																</div>
																<div class="ramtra" style="font-weight: bolder;">
																	Stability in Bull and Bear Markets:</div>
																Diversified Equity Funds comprise of all markets cap
																stocks. Large cap stocks due to high end market
																capitalization tend to be stable in bear markets and
																show moderate appreciation in bull markets. Mid and
																small cap stocks respond to market stimulations. While,
																they show higher appreciation in bull markets, their
																depreciation is in sync with the bear markets. The
																differences in the performance of these market caps get
																balanced in the Diversified Equity Funds. In a bear
																market the mid and small cap stocks have a tendency to
																be volatile even if the large cap stocks shows moderate
																depreciation, thereby maintaining a steady balance. Due
																to this stability it allows investors with a varying
																risk appetite to park their investments in these funds.
																<div class="ramtra" style="font-weight: bolder;">Reduces
																	the Need to Diversify:</div>
																Financial planners and advisors keep emphasising about
																the need to diversify your investments. It is said that
																diversification in various asset classes determines the
																return of the portfolio and not the individual funds.
																Investing in Diversified Equity Funds reduces the need
																to diversify your portfolio as you choose an already
																diversified fund depending upon your investing needs and
																risk taking ability. As an investor if you are looking
																for stability in your investments, you could allocate a
																larger portion of your investments in Diversified Equity
																Funds and the remaining in Small and Mid Cap Funds.
																However, If you are an aggressive investor and ready to
																take high risk for long term appreciation then Mid and
																Small Cap Funds could be ideal investments for you.
																<div class="ramtra" style="font-weight: bolder;">
																	A universal Appeal:</div>
																The fund has a component to appeal to all kinds of
																investors: the risk takers, the safe player and the
																flexible investor. It also reduces the need to
																diversify. Hence, as an investor if you like to manage
																your own portfolio then this reduces your need to
																diversify to a certain degree. It provides stability to
																your portfolio along with a return range of moderate to
																high.
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> <a onclick="openWindow();">It is easier to find a
																		Web-based vendor that sells the item I wish to
																		purchase.</a>
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> Web-based vendors deliver orders/services in a more
																	timely manner.
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 60px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="submit" value="express an interest"
																		class="btn buttoncheck" style="margin-left: 202px;" />
																	<input type="reset" value="ask for more info"
																		class="btn buttoncheck" /> <a href="#tab13"
																		class="btn button-next" data-toggle="tab" class="step">submit
																		final response</a>
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tab10">
															<div>
																<br />
															</div>
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">RFP Detailed Content
																	</div>
																</div>
															</div>
															<div>
																<br>
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
																<div>
																	<br />
																</div>
																<div class="ramtra" style="font-weight: bolder;">
																	Stability in Bull and Bear Markets:</div>
																Diversified Equity Funds comprise of all markets cap
																stocks. Large cap stocks due to high end market
																capitalization tend to be stable in bear markets and
																show moderate appreciation in bull markets. Mid and
																small cap stocks respond to market stimulations. While,
																they show higher appreciation in bull markets, their
																depreciation is in sync with the bear markets. The
																differences in the performance of these market caps get
																balanced in the Diversified Equity Funds. In a bear
																market the mid and small cap stocks have a tendency to
																be volatile even if the large cap stocks shows moderate
																depreciation, thereby maintaining a steady balance. Due
																to this stability it allows investors with a varying
																risk appetite to park their investments in these funds.
																<div class="ramtra" style="font-weight: bolder;">Reduces
																	the Need to Diversify:</div>
																Financial planners and advisors keep emphasising about
																the need to diversify your investments. It is said that
																diversification in various asset classes determines the
																return of the portfolio and not the individual funds.
																Investing in Diversified Equity Funds reduces the need
																to diversify your portfolio as you choose an already
																diversified fund depending upon your investing needs and
																risk taking ability. As an investor if you are looking
																for stability in your investments, you could allocate a
																larger portion of your investments in Diversified Equity
																Funds and the remaining in Small and Mid Cap Funds.
																However, If you are an aggressive investor and ready to
																take high risk for long term appreciation then Mid and
																Small Cap Funds could be ideal investments for you.
																<div class="ramtra" style="font-weight: bolder;">
																	A universal Appeal:</div>
																The fund has a component to appeal to all kinds of
																investors: the risk takers, the safe player and the
																flexible investor. It also reduces the need to
																diversify. Hence, as an investor if you like to manage
																your own portfolio then this reduces your need to
																diversify to a certain degree. It provides stability to
																your portfolio along with a return range of moderate to
																high.
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> It is easier to find a Web-based vendor that sells the
																	item I wish to purchase.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> Web-based vendors deliver orders/services in a more
																	timely manner.
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 60px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="submit" value="express an interest"
																		class="btn buttoncheck" style="margin-left: 202px;" />
																	<input type="reset" value="ask for more info"
																		class="btn buttoncheck" /> <a href="#tab13"
																		class="btn button-next" data-toggle="tab" class="step">submit
																		final response</a>
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tab11">
															<div>
																<br />
															</div>
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">RFP Detailed Content
																	</div>
																</div>
															</div>
															<div>
																<br>
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
																<div>
																	<br />
																</div>
																<div class="ramtra" style="font-weight: bolder;">
																	Stability in Bull and Bear Markets:</div>
																Diversified Equity Funds comprise of all markets cap
																stocks. Large cap stocks due to high end market
																capitalization tend to be stable in bear markets and
																show moderate appreciation in bull markets. Mid and
																small cap stocks respond to market stimulations. While,
																they show higher appreciation in bull markets, their
																depreciation is in sync with the bear markets. The
																differences in the performance of these market caps get
																balanced in the Diversified Equity Funds. In a bear
																market the mid and small cap stocks have a tendency to
																be volatile even if the large cap stocks shows moderate
																depreciation, thereby maintaining a steady balance. Due
																to this stability it allows investors with a varying
																risk appetite to park their investments in these funds.
																<div class="ramtra" style="font-weight: bolder;">Reduces
																	the Need to Diversify:</div>
																Financial planners and advisors keep emphasising about
																the need to diversify your investments. It is said that
																diversification in various asset classes determines the
																return of the portfolio and not the individual funds.
																Investing in Diversified Equity Funds reduces the need
																to diversify your portfolio as you choose an already
																diversified fund depending upon your investing needs and
																risk taking ability. As an investor if you are looking
																for stability in your investments, you could allocate a
																larger portion of your investments in Diversified Equity
																Funds and the remaining in Small and Mid Cap Funds.
																However, If you are an aggressive investor and ready to
																take high risk for long term appreciation then Mid and
																Small Cap Funds could be ideal investments for you.
																<div class="ramtra" style="font-weight: bolder;">
																	A universal Appeal:</div>
																The fund has a component to appeal to all kinds of
																investors: the risk takers, the safe player and the
																flexible investor. It also reduces the need to
																diversify. Hence, as an investor if you like to manage
																your own portfolio then this reduces your need to
																diversify to a certain degree. It provides stability to
																your portfolio along with a return range of moderate to
																high.
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> It is easier to find a Web-based vendor that sells the
																	item I wish to purchase.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> Web-based vendors deliver orders/services in a more
																	timely manner.
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 60px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="submit" value="express an interest"
																		class="btn buttoncheck" style="margin-left: 202px;" />
																	<input type="reset" value="ask for more info"
																		class="btn buttoncheck" /> <a href="#tab13"
																		class="btn button-next" data-toggle="tab" class="step">submit
																		final response</a>
																</div>
															</div>
														</div>

														<div class="tab-pane" id="tab12">
															<div>
																<br />
															</div>
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">RFP Detailed Content
																	</div>
																</div>
															</div>
															<div>
																<br>
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
																<div>
																	<br />
																</div>
																<div class="ramtra" style="font-weight: bolder;">
																	Stability in Bull and Bear Markets:</div>
																Diversified Equity Funds comprise of all markets cap
																stocks. Large cap stocks due to high end market
																capitalization tend to be stable in bear markets and
																show moderate appreciation in bull markets. Mid and
																small cap stocks respond to market stimulations. While,
																they show higher appreciation in bull markets, their
																depreciation is in sync with the bear markets. The
																differences in the performance of these market caps get
																balanced in the Diversified Equity Funds. In a bear
																market the mid and small cap stocks have a tendency to
																be volatile even if the large cap stocks shows moderate
																depreciation, thereby maintaining a steady balance. Due
																to this stability it allows investors with a varying
																risk appetite to park their investments in these funds.
																<div class="ramtra" style="font-weight: bolder;">Reduces
																	the Need to Diversify:</div>
																Financial planners and advisors keep emphasising about
																the need to diversify your investments. It is said that
																diversification in various asset classes determines the
																return of the portfolio and not the individual funds.
																Investing in Diversified Equity Funds reduces the need
																to diversify your portfolio as you choose an already
																diversified fund depending upon your investing needs and
																risk taking ability. As an investor if you are looking
																for stability in your investments, you could allocate a
																larger portion of your investments in Diversified Equity
																Funds and the remaining in Small and Mid Cap Funds.
																However, If you are an aggressive investor and ready to
																take high risk for long term appreciation then Mid and
																Small Cap Funds could be ideal investments for you.
																<div class="ramtra" style="font-weight: bolder;">
																	A universal Appeal:</div>
																The fund has a component to appeal to all kinds of
																investors: the risk takers, the safe player and the
																flexible investor. It also reduces the need to
																diversify. Hence, as an investor if you like to manage
																your own portfolio then this reduces your need to
																diversify to a certain degree. It provides stability to
																your portfolio along with a return range of moderate to
																high.
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> It is easier to find a Web-based vendor that sells the
																	item I wish to purchase.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors.
																</p>
																<p>
																	<select class="selectquestion selectvalues">
																		<option>Not Answered</option>
																		<option>Strongly Disagree</option>
																		<option>Disagree</option>
																		<option>Neither Disagree/Agree</option>
																		<option>Agree</option>
																		<option>Strongly Agree</option>
																	</select> Web-based vendors deliver orders/services in a more
																	timely manner.
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 60px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="submit" value="express an interest"
																		class="btn buttoncheck" style="margin-left: 202px;" />
																	<input type="reset" value="ask for more info"
																		class="btn buttoncheck" /> <a href="#tab13"
																		class="btn button-next" data-toggle="tab" class="step">submit
																		final response</a>
																</div>
															</div>
														</div>

														<div class="tab-pane" id="tab13">
															<div>
																<br />
															</div>
															<div class="ramtra">
																<div class="ramtra" style="font-weight: bolder;">Benefits
																	of Investing in Diversified Equity Funds:</div>
															</div>
															<br />
															<div class="Rowtable">
																<div class="ColumnCommonmyprofiletabrayu">
																	<div class="lable_headervem">Questionnaires</div>
																</div>
															</div>
															<br />
															<div class="ramtra">
																<p>
																	1) It is easier to find a Web-based vendor that sells
																	the item I wish to purchase? <br> <input
																		type="text" class="m-wrap large">
																</p>
																<p>
																	2) I can quickly gather information about products and
																	services I wish to purchase from Web-based vendors? <br>
																	<input type="text" class="m-wrap large">
																</p>
																<p>
																	3) Web-based vendors deliver orders/services in a more
																	timely manner? <br> <input type="text"
																		class="m-wrap large">
																</p>

															</div>
															<br />
															<div class="form-actions clearfix">
																<div class="se" style="padding-left: 180px;">
																	<a href="#tab8" class="btn button-previous"
																		data-toggle="tab" class="step"
																		style="margin-left: -212px;">Back</a> <input
																		type="reset" value="submit response"
																		class="btn buttoncheck" />
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
	var personalDetails= '<%=RequestConstans.Vendor.PERSONALDETAILS%>';
			if (personalDetails != null && personalDetails.length > 0
					&& personalDetails.match("personaldetails")) {
				document.getElementById('change').style.backgroundColor = '#5CE5E5';
				document.getElementById('interdiv').style.backgroundColor = '#5CE5E5';
				document.getElementById('ancho').style.backgroundColor = '#5CE5E5';
			}
		};
	</script>
	<!-- tab active code end here-->

	<!-- popup window plugins start-->
	<script src="<%=request.getContextPath()%>/resources/js/popup.js"
		type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery-min.js"
		type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/modernizr.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/resources/js/bootstrap-min.js"
		type="text/javascript"></script>
	<!-- popup window plugins ends-->

	<!-- Add to table plugins start-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/jquery.tabletojson.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/jquery.tabletojson.js"></script>
	<!-- Add to table plugins end here-->

	<!-- Date plugins start-->
	<script>
		$(function() {
			$("#inputField").datepicker();
			$("#inputField1").datepicker();
		});
		function loadSecurityTypes(assettypeId) {
		 	
				if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
					assettypeId = encode64(assettypeId);
					$.ajax({
						type: 'GET',
						url:  "loadVendorSecurityTypes?RAyuL="+assettypeId,
						cache:false,
						success : function(output){
							document.getElementById("assetClassVendorSecurityMaps").innerHTML = output;		
						},
						error : function(data, textStatus, jqXHR){
							//alert('Error: '+data+':'+textStatus);
						}
					});
				}
			}
	</script>
	<script src="//code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"
		type="text/javascript"></script>

	<!-- Date plugins start-->

</body>
</html>
