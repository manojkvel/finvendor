<!DOCTYPE html>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
   	<title>Consumer Dashboard</title>
   	<script src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
</head>
<%--
<c:set var="companydetails" value="<%=RequestConstans.Consumer.COMPANYDETAILS %>"></c:set>
<c:set var="mybusinessneeds" value="<%=RequestConstans.Consumer.MY_BUSINESS_NEEDS %>"></c:set>
<c:set var="marketdataneeds" value="<%=RequestConstans.Consumer.MARKET_DATA_NEEDS %>"></c:set>
<c:set var="tradingapplicationneeds" value="<%=RequestConstans.Consumer.TRADING_APPLICATION_NEEDS %>"></c:set>
<c:set var="analyticsapplicationneeds" value="<%=RequestConstans.Consumer.ANALYTICS_APPLICATION_NEEDS %>"></c:set>
<c:set var="researchreportneeds" value="<%=RequestConstans.Consumer.RESEARCH_REPORT_NEEDS %>"></c:set>
<c:set var="myvendorpreference" value="<%=RequestConstans.Consumer.MYVENDORPREFERENCE %>"></c:set>
<c:set var="mypreferencemarketdataneeds" value="<%=RequestConstans.Consumer.MY_PREFERENCE_MARKET_DATA_NEEDS %>"></c:set>
<c:set var="mypreferencetradingapplicationneeds" value="<%=RequestConstans.Consumer.MY_PREFERENCE_TRADING_APPLICATION_NEEDS %>"></c:set>
<c:set var="mypreferenceanalyticsapplicationneeds" value="<%=RequestConstans.Consumer.MY_PREFERENCE_ANALYTICS_APPLICATION_NEEDS %>"></c:set>
<c:set var="mypreferenceresearchreportneeds" value="<%=RequestConstans.Consumer.MY_PREFERENCE_RESEARCH_REPORT_NEEDS %>"></c:set>
<c:set var="costpreference" value="<%=RequestConstans.Consumer.DATADELIVERY_COSTPREFERENCE %>"></c:set>
<c:set var="costpreferencemarketdataneeds" value="<%=RequestConstans.Consumer.COST_PREFERENCE_MARKET_DATA_NEEDS %>"></c:set>
<c:set var="costpreferenceapplicationneeds" value="<%=RequestConstans.Consumer.COST_PREFERENCE_TRADING_APPLICATION_NEEDS %>"></c:set>
<c:set var="costpreferenceanalyticsapplicationneeds" value="<%=RequestConstans.Consumer.COST_PREFERENCE_ANALYTICS_APPLICATION_NEEDS %>"></c:set>
<c:set var="costpreferencesearchreportneeds" value="<%=RequestConstans.Consumer.COST_PREFERENCE_RESEARCH_REPORT_NEEDS %>"></c:set>
--%>
<body>
	<jsp:include page="common/header.jsp"></jsp:include>	
	<div class="container">  
        <div class="row-fluid">
			<div class="span12">
          		<div class="row-fluid service-box">
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue" id="form_wizard_1">
								<div class="portlet-title"></div>
								<div class="portlet-body form">
									<form action="${pageContext.request.contextPath}/updateConsumerProfileCompanyDetails" class="form-horizontal" id="submit_form" method="post"
										enctype="multipart/form-data">
										<div class="form-wizard">
											<ul class="nav nav-tabs">
											  <li class="active"><a data-toggle="tab" href="#consumerProfileCompanyDetailsTab">Company Details</a></li>
											  <li><a data-toggle="tab" href="#consumerProfileMyBusinessNeedsTab">My Business Needs</a></li>
											  <%--
											  <li><a data-toggle="tab" href="#consumerProfileMyVendorPreferenceTab">My Vendor Preference</a></li>
											  <li><a data-toggle="tab" href="#consumerProfileDeliveryAndCostPreferenceTab">Delivery & Cost Preference</a></li>
											  --%>
											</ul>
											<%--  
											<div class="Rowtable">
												<div class="ColumnCommonmyprofile" id="columnconsumer1"> 
													<div class="lable_header" id="headerconsumer1"> 
														<a id="anchortagconsumer1" href="#tab1" class="lable_header" data-toggle="tab" onclick="consumerMyProfileActiveMode('consumerProfileCompanyDetailsTab');">Company Details</a> 
													</div>
												</div>
												<div class="ColumnCommonmyprofile" id="columnconsumer2"> 
													<div class="lable_header" id="headerconsumer2">  
														<a id="anchortagconsumer2" href="#tab2" class="lable_header" data-toggle="tab" onclick="consumerMyProfileActiveMode('${mybusinessneeds}');">My Business Needs</a> 
													</div> 
												</div>
												<div class="ColumnCommonmyprofile" id="columnconsumer3"> 
													<div class="lable_header" id="headerconsumer3">  
														<a id="anchortagconsumer3" href="#tab3" class="lable_header" data-toggle="tab" onclick="consumerMyProfileActiveMode('${myvendorpreference}');">My Vendor Preference</a> 
													</div> 
												</div>
												<div class="ColumnCommonmyprofile" id="columnconsumer4"> 
													<div class="lable_header" id="headerconsumer4"> 
														<a id="anchortagconsumer4" href="#tab4" class="lable_header" data-toggle="tab" onclick="consumerMyProfileActiveMode('${costpreference}');">Delivery & Cost Preference</a> 
													</div> 
												</div>
											</div>
											--%>
											<div class="tab-content" style="background-color:white">
												<%-- My Profile - Company Details --%>
												<div class="tab-pane active" id="consumerProfileCompanyDetailsTab">
													<span id="consumerProfileCompanyDetailsSpan">	
														<div class="Row">
															<div class="ColumnCommonSingletwo">
																<div class="control-group">
																	<label class="control-labelalign">First Name<span class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="consumerProfileFirstName" name="consumerProfileFirstName" 
																			placeholder="First Name" data-mandatory="Y" class="m-wrap large"																			 
																			onblur="validateNotNull(this, 'consumerProfileFirstNameErrorMsg')" 
																			value="${consumer.firstName}"/>
																		<div>
																			<label id="consumerProfileFirstNameErrorMsg" class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Last Name</label>
																	<div class="controls">
																		<input type="text" id="consumerProfileLastName" name="consumerProfileLastName"  
																			placeholder="Last Name" class="m-wrap large" value="${consumer.lastName}"/>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Designation<span class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="consumerProfileDesignation" name="consumerProfileDesignation"
																			placeholder="Designation" data-mandatory="Y" class="m-wrap large"
																			onblur="validateNotNull(this, 'consumerProfileDesignationErrorMsg')"
																			value="${consumer.designation}"/>
																		<div>
																			<label id="consumerProfileDesignationErrorMsg" class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Company<span class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="consumerProfileCompany" name="consumerProfileCompany" 
																			placeholder="Company Name" data-mandatory="Y" class="m-wrap large" readonly
																			onblur="validateNotNull(this, 'consumerProfileCompanyErrorMsg')"
																			value="${consumer.company}"/>
																		<div>
																			<label id="consumerProfileCompanyErrorMsg" class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Company URL<span class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="consumerProfileCompanyUrl" name="consumerProfileCompanyUrl" 
																			data-mandatory="Y" class="m-wrap large" placeholder="Company URL"
																			onblur="validateWithRegularExpression(this, 'consumerProfileCompanyUrlErrorMsg', regularExpressionMap['URL'], 'COMPANY_URL', true)"
																			value="${consumer.companyUrl}"/>
																		<div>
																			<label id="consumerProfileCompanyUrlErrorMsg" class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Company Information<span class="required">*</span></label>
																	<div class="controls">
																		<textarea id="consumerProfileCompanyInfo" name="consumerProfileCompanyInfo"
																			data-mandatory="Y" placeholder="Company Information"
																			name="companyinfo" class="m-wrap large" cols="30"
																			rows="5"
																			onblur="validateNotNull(this, 'consumerProfileCompanyInfoErrorMsg')">${consumer.companyInfo}</textarea>
																		<div>																			
																			<label id="consumerProfileCompanyInfoErrorMsg" class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Primary Email<span class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="consumerProfilePrimaryEmail" name="consumerProfilePrimaryEmail"
																			data-mandatory="Y" placeholder="Primary Email" class="m-wrap large" 
																			readonly value="${consumer.user.email}"/>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Secondary Email</label>
																	<div class="controls">
																		<input type="text" id="consumerProfileSecondaryEmail" name="consumerProfileSecondaryEmail"
																			placeholder="Secondary Email" class="m-wrap large" 
																			onblur="if (validateWithRegularExpression(this, 'consumerProfileSecondaryEmailErrorMsg', regularExpressionMap['EMAIL'], 'EMAIL', false)) validateAjax(this, 'checkExistingEmail','consumerProfileSecondaryEmailErrorMsg')"
																			value="${consumer.secondaryEmail}"/>
																		<div>
																			<label id="consumerProfileSecondaryEmailErrorMsg" class="errorMessage"></label>
																		</div>
																	</div>
																</div>
															</div>
															<div class="ColumnCommonSingletwo">
																<div class="control-group">
																	<label class="control-labelalign">Phone Number<span
																		class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="consumerProfilePhoneNumberCode" name="consumerProfilePhoneNumberCode"
																			data-mandatory="Y" placeholder="Code"
																			class="m-wrap smallic"
																			onblur="validateWithRegularExpression(this, 'consumerProfilePhoneNumberCodeErrorMsg', regularExpressionMap['PHONE_CODE'], 'PHONE_CODE', true)" 
																			value="${telephoneCode}" />
																		<input type="text" id="consumerProfilePhoneNumber" name="consumerProfilePhoneNumber"
																			data-mandatory="Y" placeholder="Phone Number"
																			class="m-wrap largephonenumber"
																			onblur="validateWithRegularExpression(this, 'consumerProfilePhoneNumberErrorMsg', regularExpressionMap['PHONE_NUMBER'], 'PHONE', true)"
																			value="${telephoneNumber}" />
																		<div class="selectOptions">e.g. +91 9869190000</div>
																		<div>
																			<label id="consumerProfilePhoneNumberCodeErrorMsg"
																				class="errorMessage"></label>
																		</div>
																		<div>
																			<label id="consumerProfilePhoneNumberErrorMsg"
																				class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">City</label>
																	<div class="controls">
																		<input type="text" id="consumerProfileCity" name="consumerProfileCity" placeholder="City"
																		class="m-wrap large" value="${consumer.city}"/>
																	</div>
																</div>																
																<div class="control-group">
																	<label class="control-labelalign">Country of
																		Incorporation<span class="required">*</span>
																	</label>
																	<div class="controls">
																		<select  
																			id="consumerProfileCountryOfIncorporation" name="consumerProfileCountryOfIncorporation" 
																			style="width:200px" onchange="#">
																			<c:forEach var="countries" items="${countries}">
																				<c:choose>
																					   <c:when test="${consumer.countryOfIncorporation eq countries.country_id}">
																						        <option value="${countries.country_id}"  selected="${countries.country_id}">${countries.name}</option>
																						    </c:when>    
																						    <c:otherwise>
																						    	<option value="${countries.country_id}">${countries.name}</option>
																						    </c:otherwise>
																				 </c:choose>
																			</c:forEach>
																		</select>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Year of Incorporation<span class="required">*</span></label>										
																	<div class="controls">
																		<input type="text" id="consumerProfileYearOfIncorporation" name="consumerProfileYearOfIncorporation" 
																			placeholder="Year Of Incorporation" data-mandatory="Y" class="m-wrap large" 																		
																			onblur="validateWithRegularExpression(this, 'consumerProfileYearOfIncorporationErrorMsg', regularExpressionMap['YEAR'], 'YEAR', true)"
																			value="${consumer.yearOfIncorporation}"/>
																		<div>
																			<label id="consumerProfileYearOfIncorporationErrorMsg" class="errorMessage"></label>
																		</div>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Company Type</label>
																	<div class="controls">
																		<input type="text" id="consumerProfileCompanyType" name="consumerProfileCompanyType" 
																			placeholder="Company Type" class="m-wrap large" readonly value="${displayCompanyType}"/>																		
																	</div>
																</div>	
																<div class="control-group">
																	<label class="control-labelalign">Company Subtype<span class="required">*</span>
																	</label>
																	<div class="controls">
																		<select  
																			id="consumerProfileCompanySubType" name="consumerProfileCompanySubType"
																				style="width:200px" onchange="#">
																			<c:forEach var="subType" items="${companySubType}">
																				<c:choose>
																				   <c:when test="${consumer.companySubType.id == subType.id}">
																				        <option value="${subType.id}" selected>${subType.type}</option>
																				    </c:when>    
																				    <c:otherwise>
																				    	<option value="${subType.id}">${subType.type}</option>
																				    </c:otherwise>
																				 </c:choose>
																			</c:forEach>
																		</select>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-labelalign">Company Logo<span
																		class="required">*</span></label>
																	<div class="controls">
																		<input type="file" id="consumerProfileCompanyLogo"
																			placeholder="Company Logo" name="consumerProfileCompanyLogo"
																			class="m-wrap largefileuploadcons"
																			onblur="validateImageType(this, 'consumerProfileCompanyLogoErrorMsg')"/>
																		<div class="selectOptions">e.g. .jpeg, .jpg,
																			.png, .gif</div>
																			<div id="displayLogo">
																				<img src="${pageContext.request.contextPath}/displayCompanyLogo/${consumer.user.userName}" style="height:61px;width:115px;margin-top:10px"/>																					
																			</div>
																			<div>
																				<label id="consumerProfileCompanyLogoErrorMsg" class="errorMessage"></label>
																			</div>
																	</div>
																</div>
															</div>
														</div>
														<div><br/></div>
														<div class="form-actions clearfix">
															<div class="se">
																<input type="submit" value="Update" class="btn" onclick="if(!validateSpanElements('consumerProfileCompanyDetailsSpan')) return false"/>
																<%--<input type="reset" value="Reset" class="btn"/>
																<a href="#consumerProfileMyBusinessNeedsTab" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${mybusinessneeds}');">Continue</a>
																--%>
															</div>
														</div>
													</span>
												</div>
												<%-- My Profile - My Business Needs --%>
												<div class="tab-pane" id="consumerProfileMyBusinessNeedsTab">								
										 				<div class="Rowtable">
															<ul class="nav nav-tabs">
																<c:set var="consumerProfileMyBusinessNeedsTabActive" value="false"/>
																<c:if test="${consumer.marketDataPreference == true}">
																	<c:set var="consumerProfileMyBusinessNeedsTabActive" value="true"/>
																	<c:set var="consumerProfileMyBusinessNeedsMarketDataTabActive" value="true"/>
																	<li class="active"><a data-toggle="tab" href="#consumerProfileMyBusinessNeedsMarketDataTab">Market Data</a></li>
																</c:if>
																<c:if test="${consumer.tradingAppPreference == true}">
																	<c:choose>
																		<c:when test="${consumerProfileMyBusinessNeedsTabActive == true}">
																			<li><a data-toggle="tab" href="#consumerProfileMyBusinessNeedsTradingPlatformTab">Trading Platform</a></li>
																		</c:when>
																		<c:otherwise>
																			<c:set var="consumerProfileMyBusinessNeedsTabActive" value="true"/>
																			<c:set var="consumerProfileMyBusinessNeedsTradingPlatformTabActive" value="true"/>
																			<li class="active"><a data-toggle="tab" href="#consumerProfileMyBusinessNeedsTradingPlatformTab">Trading Platform</a></li>
																		</c:otherwise>
																	</c:choose>						
																</c:if>
																<c:if test="${consumer.analyticsAppPreference == true}">
																	<c:choose>
																		<c:when test="${consumerProfileMyBusinessNeedsTabActive == true}">
																			<li><a data-toggle="tab" href="#consumerProfileMyBusinessNeedsAnalyticsSoftwareTab">Analytics Software</a></li>
																		</c:when>
																		<c:otherwise>
																			<c:set var="consumerProfileMyBusinessNeedsTabActive" value="true"/>
																			<c:set var="consumerProfileMyBusinessNeedsAnalyticsSoftwareTabActive" value="true"/>
																			<li class="active"><a data-toggle="tab" href="#consumerProfileMyBusinessNeedsAnalyticsSoftwareTab">Analytics Software</a></li>
																		</c:otherwise>
																	</c:choose>																	
																</c:if>
																<c:if test="${consumer.researchReportPreference == true}">
																	<c:choose>
																		<c:when test="${consumerProfileMyBusinessNeedsTabActive == true}">
																			<li><a data-toggle="tab" href="#consumerProfileMyBusinessNeedsResearchReportTab">Research Report</a></li>
																		</c:when>
																		<c:otherwise>
																			<c:set var="consumerProfileMyBusinessNeedsTabActive" value="true"/>
																			<c:set var="consumerProfileMyBusinessNeedsResearchReportTabActive" value="true"/>
																			<li class="active"><a data-toggle="tab" href="#consumerProfileMyBusinessNeedsResearchReportTab">Research Report</a></li>
																		</c:otherwise>
																	</c:choose>
																</c:if>
															</ul>
														</div>
														<div class="tab-content">
															<c:choose>
																<c:when test="${consumerProfileMyBusinessNeedsMarketDataTabActive == true}">
																	<div class="tab-pane active" id="consumerProfileMyBusinessNeedsMarketDataTab">
																</c:when>
																<c:otherwise>
																	<div class="tab-pane" id="consumerProfileMyBusinessNeedsMarketDataTab">
																</c:otherwise>
															</c:choose>														
																<div class="Rowtableinfoval">
																	<div class="ColumnCommon"> 
																		<label class="control-label-fileupload">File Upload consumerProfileMyBusinessNeedsMarketDataTab(excel or csv)<span class="required">*</span></label>
																		<div class="controlsfileupload">
																			 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
																		</div>
																		<div class="controlsfileuploadreadonly">
																			 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
																		</div>
																	</div>
																	<div class="ColumnCommonray">
																		<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
																	</div>
																</div>
														<div><br/> </div>
														<div class="Row">
															<div class="ColumnCommonvendor">
																<div class="control-group">
																	<label class="control-label">Asset Class<span class="required">*</span></label>
																	<div class="controls">
																		<select name="assetclass" onchange="loadConsumerSecurityTypes(this.value);" id="consumerassetclass">
																		     <option value ="-SELECT-"> -SELECT- </option>
																		     <c:forEach var="assetClasses" items="${assetClasses}">
																		    	<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
																		 	 </c:forEach>
																		 </select>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-label">Security type<span class="required">*</span></label>
																	<div class="controls">
																		<select name="securitytype"  id="assetClassConsumerSecurityMaps" multiple="multiple">
																		  	 <option value ="-SELECT-"> -SELECT- </option>
																		     <c:forEach var="assetClassConsumerSecurityMaps" items="${assetClassConsumerSecurityMaps}">
																			    <option value="${assetClassConsumerSecurityMaps.securityType.name}">${assetClassConsumerSecurityMaps.securityType.name}</option>
																			  </c:forEach>
																		 </select>
																	</div>
																</div>
															</div>
															<div class="ColumnCommonvendorpage">
																<div class="control-group">
																	<label class="control-label">Data Coverage Region<span class="required">*</span></label>
																	<div class="controls">
																		<select name="datacoverageregion" multiple="multiple" id="consumerdatacoverageregion">
																	     	<option value ="-SELECT-"> -SELECT- </option>
																		     <c:forEach var="regions" items="${regions}">
																		    	<option value="${regions.name}">${regions.name}</option>
																		 	 </c:forEach>
																	 	</select> 
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-label">Data Coverage Country<span class="required">*</span></label>
																	<div class="controls">
																		<select name="datacoveragecountry" multiple="multiple" id="consumerdatacoveragecountry">
																		     <option value ="-SELECT-"> -SELECT- </option>
																		     <c:forEach var="countries" items="${countries}">
																			    <option value="${countries.name}">${countries.name}</option>
																			  </c:forEach>
																   		</select>
																	</div>
																</div>
															</div>
															<div class="ColumnCommonvendorpre">
																<div class="control-group">
																	<label class="control-label">Data Coverage Exchange<span class="required">*</span></label>
																	<div class="controls">
																		<select name="datacoverageexchange"  multiple="multiple" id="consumerdatacoverageexchange">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	     <c:forEach var="exchanges" items="${exchanges}">
																		    <option value="${exchanges.name}">${exchanges.name}</option>
																		  </c:forEach>
																	  </select>
																	</div>
																</div>
																<div class="control-group">
																	<label class="control-label">Data Attribute<span class="required">*</span></label>
																	<div class="controls">
																		<input type="text" id="consumerdataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wrap largeval"/>
																	</div>
																</div>
															</div> 
														</div>
														<div class="control-group">
															<div class="controls">
																<div class="se" style="  margin: 0px 0px 0px 232px;">
															 	  <a class="consumeraddtotable"> <span class="lable_header_add">Add More </span></a> 
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
																</tbody>
															</table>
															<input type="hidden" id="consumerjsontable1" name="consumerjsontable1"/>
														</div>
														<div><br/></div>
														<div class="form-actions clearfix">
															<div class="se">
															<%-- 
															<a  href="#tab1" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${companydetails}');">Back</a>					
															<input type="reset" value="Reset" class="btn" />
															<a  href="#tab6" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${tradingapplicationneeds}');">Continue</a>
															--%>
															<input type="submit" value="Update" class="btn"/>
														</div>
													</div>									
												</div>								
												<c:choose>
													<c:when test="${consumerProfileMyBusinessNeedsTradingPlatformTabActive == true}">
														<div class="tab-pane active" id="consumerProfileMyBusinessNeedsTradingPlatformTab">
													</c:when>
													<c:otherwise>
														<div class="tab-pane" id="consumerProfileMyBusinessNeedsTradingPlatformTab">
													</c:otherwise>
												</c:choose>
													<div class="Rowtableinfoval">
														<div class="ColumnCommon"> 
															<label class="control-label-fileupload">File Upload consumerProfileMyBusinessNeedsTradingPlatformTab(excel or csv)<span class="required">*</span></label>
															<div class="controlsfileupload">
																 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
															</div>
															<div class="controlsfileuploadreadonly">
																 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
															</div>
														</div>
														<div class="ColumnCommonray">
															<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
														</div>
													</div>
													<div><br/> </div>												
													<div class="Row">
														<div class="ColumnCommonvenconsapplication">
															<div class="control-group">
																<label class="control-label">Trade Asset Class<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradeassetclass"  id="tradeassetclass">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	     <c:forEach var="assetClasses" items="${assetClasses}">
																	    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																	 	 </c:forEach>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Trade Sub Asset Class<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradesubassetclass"  multiple="multiple" id="tradesubassetclass">
																	  	 <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="Sub1"> Sub1 </option>
																	      <option value ="Sub2"> Sub2 </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Trading Capability Type<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradecapabilitytype"  multiple="multiple" id="tradecapabilitytype">
																	  	 <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="Capabil1"> Capabil1 </option>
																	      <option value ="Capabil2"> Capabil2 </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Trade Execution Type<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradeexecutiontype"  multiple="multiple" id="tradeexecutiontype">
																	  	 <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="Trade1"> Trade1 </option>
																	      <option value ="Trade2"> Trade2 </option>
																	 </select>
																</div>
															</div>
														</div>
														<div class="ColumnCommonvendorpage">
															<div class="control-group">
																<label class="control-label">Data Coverage Region<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradedatacoverageregion" multiple="multiple" id="tradedatacoverageregion">
																     	<option value ="-SELECT-"> -SELECT- </option>
																	     <c:forEach var="regions" items="${regions}">
																	    	<option value="${regions.name}">${regions.name}</option>
																	 	 </c:forEach>
																 	</select> 
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Data Coverage Country<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradedatacoveragecountry" multiple="multiple" id="tradedatacoveragecountry">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	     <c:forEach var="countries" items="${countries}">
																		    <option value="${countries.name}">${countries.name}</option>
																		  </c:forEach>
															   		</select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Data Coverage Exchange<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradedatacoverageexchange"  multiple="multiple" id="tradedatacoverageexchange">
																     <option value ="-SELECT-"> -SELECT- </option>
																     <c:forEach var="exchanges" items="${exchanges}">
																	    <option value="${exchanges.name}">${exchanges.name}</option>
																	  </c:forEach>
																  </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">My Darkpool venues<span class="required">*</span></label>
																<div class="controls">
																	<select name="trademydarkpoolvenues"  multiple="multiple" id="trademydarkpoolvenues">
																     <option value ="-SELECT-"> -SELECT- </option>
																     <option value ="Darkpool1"> Darkpool1 </option>
																     <option value ="-Darkpool2"> Darkpool2 </option>
																  </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Order Type<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradeordertype"  id="tradeordertype">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="Order1"> Order1 </option>
																	      <option value ="Order2"> Order2 </option>
																	 </select>
																</div>
															</div>
														</div>
														<div class="ColumnCommonvendorpredatampodelapplication">
															<div class="control-group">
																<label class="control-label">Accessibility<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradeaccessibility"  id="tradeaccessibility">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="Access1"> Access1 </option>
																	      <option value ="Access2"> Access2- </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Suitability<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradesuitability"  id="tradesuitability">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	     <option value ="Subility2">Suitabi1 </option>
																		<option value ="Subility1"> Suitabi2 </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Software type<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradesoftwaretype"  id="tradesoftwaretype">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="Software1"> Software1 </option>
																	      <option value ="Software2"> Software2 </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label"> User Base<span class="required">*</span></label>
																<div class="controls">
																	<select name="tradeexistinguserbase"  id="tradeexistinguserbase">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="ExiUse1"> ExisUse1 </option>
																	      <option value ="ExiUse2"> ExisUse2 </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Cost Range<span class="required">*</span></label>
																<div class="controls">
																<select name="tradecostrange" id="tradecostrange">
																     <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																	 <c:forEach var="costs" items="${costs}">
																	     <option value="${costs.cost_id}">${costs.range}</option>
																	 </c:forEach>
																</select>
																</div>
															</div>
														</div> 
													</div>
													<div class="control-group">
														<div class="controls">
															<div class="se" style="  margin: 0px 0px 0px 232px;">
														 	  <a class="tradeapplicationconsumeraddmore"> <span class="lable_header_add">Add More </span></a> 
														 	</div>
														</div>
													</div> 
													<div class="portlet-body">
														<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1tradeapplicationconsu">
															<thead style="background-color:#7BCCA5;">
																<tr>
																	<th>Asset</th>
																	<th>Sub Asset</th>
																	<th>Ca-bility</th>
																	<th>Execution</th>
													                <th>Region</th>
																	<th>Country</th>
																	<th>Exchange</th>
																	<th>Darkpool</th>
																	<th>Order</th>
																	<th>Ac-bility</th>
																	<th>Su-bility</th>
																	<th>Software</th>
																	<th>User</th>
																	<th>Cost</th>
																	<th>Actions</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
														<input type="hidden" id="tradeapplicationconsujsontable" name="tradeapplicationconsujsontable"/>
													</div>
													<div><br/></div>
													<div class="form-actions clearfix">
														<div class="se">
															<%-- 
															<a  href="#tab5" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${marketdataneeds}');">Back</a>		
															<input type="reset" value="Reset" class="btn" />
															<a  href="#tab7" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${analyticsapplicationneeds}');">Continue</a>
															--%>
															<input type="submit" value="Update" class="btn"/>
														</div>
													</div>									
												</div>
												<c:choose>
													<c:when test="${consumerProfileMyBusinessNeedsAnalyticsSoftwareTabActive == true}">
														<div class="tab-pane active" id="consumerProfileMyBusinessNeedsAnalyticsSoftwareTab">
													</c:when>
													<c:otherwise>
														<div class="tab-pane" id="consumerProfileMyBusinessNeedsAnalyticsSoftwareTab">
													</c:otherwise>
												</c:choose>									
													<div class="Rowtableinfoval">
														<div class="ColumnCommon"> 
															<label class="control-label-fileupload">File Upload consumerProfileMyBusinessNeedsAnalyticsSoftwareTab(excel or csv)<span class="required">*</span></label>
															<div class="controlsfileupload">
																 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
															</div>
															<div class="controlsfileuploadreadonly">
																 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
															</div>
														</div>
														<div class="ColumnCommonray">
															<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
														</div>
													</div>
													<div><br/></div>
													<div class="Row">
														<div class="ColumnCommonvenconsumer">
															<div class="control-group">
																<label class="control-label">Analytics Solutions Type<span class="required">*</span></label>
																<div class="controls">
																	<select name="analyticssolutiontype"  id="analyticssolutiontype">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	     <option value ="Analy Solu type1">Analy Solu type1 </option>
																		<option value ="Analy Solu type2"> Analy Solu type2 </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Analytics Solutions Sub Type<span class="required">*</span></label>
																<div class="controls">
																	<select name="analysolutionsubtype"  id="analysolutionsubtype">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="Analy Solu Sub Type1"> Analy Solu Sub Type </option>
																	      <option value ="Analy Solu Sub Type2"> Analy Solu Sub Type </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Customizable Calc. Model?<span class="required">*</span></label>
																<div class="controls">
																	<span class="check"> <input type="checkbox" name="customizablecal" value="Customizable Calc"  id="customizablecal" class="input"> </span>
																</div>
															</div>
														</div>
														<div class="ColumnCommonvendorpageapplicationneeds">
															<div class="control-group" style="padding-left: 62px;">
																<label class="control-label">Real time Market Data?<span class="required">*</span></label>
																<div class="controls">
																	<span class="check"> <input type="checkbox" name="realtimemarket" value="Real time Market"  id="realtimemarket" class="input"> </span>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Accessibility<span class="required">*</span></label>
																<div class="controls">
																	<select name="analyaccessibility"  id="analyaccessibility">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="ac-bility1"> Accessibility1 </option>
																	      <option value ="ac-bility2"> Accessibility2 </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Suitability<span class="required">*</span></label>
																<div class="controls">
																	<select name="analysuitability"  id="analysuitability">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	     <option value ="su-bility1">Suitability1 </option>
																	     <option value ="su-bility2"> Suitability2 </option>
																	 </select>
																</div>
															</div>
														</div>
														<div class="ColumnCommonvendorpre">
															<div class="control-group">
																<label class="control-label">Software type<span class="required">*</span></label>
																<div class="controls">
																	<select name="analysoftwaretype"  id="analysoftwaretype">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	     <option value ="sofware1">Software type1 </option>
																	     <option value ="sofware2"> Software type2 </option>
																	 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Cost Range<span class="required">*</span></label>
																<div class="controls">
															<select name="analycostrange" id="analycostrange">
															     <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																 <c:forEach var="costs" items="${costs}">
																     <option value="${costs.cost_id}">${costs.range}</option>
																 </c:forEach>
															</select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Existing User Base<span class="required">*</span></label>
																<div class="controls">
																	<select name="analyexistuserbase"  id="analyexistuserbase">
																	     <option value ="-SELECT-"> -SELECT- </option>
																	      <option value ="Ex-User1">Existing User Base1 </option>
																	      <option value ="Ex-User2"> Existing User Base2 </option>
																	 </select>
																</div>
															</div>
														</div> 
													</div>
													<div class="control-group">
														<div class="controls">
															<div class="se" style="  margin: 0px 0px 0px 232px;">
														 	  <a class="analyticsApplicationAdd"> <span class="lable_header_add">Add More </span></a> 
														 	</div>
														</div>
													</div> 
													<div class="portlet-body">											
														<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1analyticsApplic">
															<thead style="background-color:#7BCCA5;">
																<tr>
																	<th>Analytics Solutions Type</th>
																	<th>Analytics Solutions Sub Type</th>
																	<th>Customizable Calc. Model?</th>
																	<th>Real time Market Data?</th>
													                <th>Accessibility</th>
																	<th>Suitability</th>
																	<th>Software type</th>
																	<th>Cost Range</th>
																	<th>Existing User Base</th>
																	<th>Actions</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
														<input type="hidden" id="analyticsApplicationAddJsontable" name="analyticsApplicationAddJsontable"/>
													</div>											
													<div><br/></div>
													<div class="form-actions clearfix">
														<div class="se">
															<%-- 
															<a  href="#tab6" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${tradingapplicationneeds}');">Back</a>							
															<input type="reset" value="Reset" class="btn" />
															<a  href="#tab8" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${researchreportneeds}');">Continue</a>
															--%>
															<input type="submit" value="Update" class="btn"/>
														</div>
													</div>									
												</div>									
												<c:choose>
													<c:when test="${consumerProfileMyBusinessNeedsResearchReportTabActive == true}">
														<div class="tab-pane active" id="consumerProfileMyBusinessNeedsResearchReportTab">
													</c:when>
													<c:otherwise>
														<div class="tab-pane" id="consumerProfileMyBusinessNeedsResearchReportTab">
													</c:otherwise>
												</c:choose>
													<div class="Rowtableinfoval">
														<div class="ColumnCommon"> 
															<label class="control-label-fileupload">File Upload #consumerProfileMyBusinessNeedsResearchReportTab(excel or csv)<span class="required">*</span></label>
															<div class="controlsfileupload">
																 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
															</div>
															<div class="controlsfileuploadreadonly">
																 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
															</div>
														</div>
														<div class="ColumnCommonray">
															<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
														</div>
													</div>
													<div><br/></div>											
													<div><br/></div>
													<div class="form-actions clearfix">
														<div class="se">
															<%-- <a  href="#tab7" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${analyticsapplicationneeds}');">Back</a>															
															<input type="reset" value="Reset" class="btn" />
															--%>
															<input type="submit" value="Update" class="btn"/>
														</div>
													</div>
												</div>
											</div>
										</div>									
												<%-- My Profile - My Vendor Preference --%>
												<div class="tab-pane" id="consumerProfileMyVendorPreferenceTab">
													<div><br/></div>
													<div class="Rowtable">
														<c:if test="${consumer.marketDataPreference == true}">
															<div class="ColumnCommonmyprofile" id="columnmarketmyvendorpreference1"> 
																<div class="lable_header_consumer" id="headermarketmyvendorpreference1"> 
																	<a id="anchortagmarketmyvendorpreference1" href="#tab9" class="lable_header_consumer" data-toggle="tab"  onclick="consumerMyProfileActiveMode('${mypreferencemarketdataneeds}')">
																		Market Data Needs
																	</a> 
																</div>
															</div>
														</c:if>
														<c:if test="${consumer.tradingAppPreference == true}">
															<div class="ColumnCommonmyprofile" id="columnmarketmyvendorpreference2"> 
																<div class="lable_header_consumer" id="headermarketmyvendorpreference2">  
																	<a id="anchortagmarketmyvendorpreference2" href="#tab10" class="lable_header_consumer" data-toggle="tab" onclick="consumerMyProfileActiveMode('${mypreferencetradingapplicationneeds}')">
																		Trading Application Needs
																	</a> 
																</div> 
															</div>
														</c:if>
														<c:if test="${consumer.analyticsAppPreference == true}">
															<div class="ColumnCommonmyprofile" id="columnmarketmyvendorpreference3"> 
																<div class="lable_header_consumer" id="headermarketmyvendorpreference3">  
																	<a id="anchortagmarketmyvendorpreference3" href="#tab11" class="lable_header_consumer" data-toggle="tab" onclick="consumerMyProfileActiveMode('${mypreferenceanalyticsapplicationneeds}')">
																		Analytics Application Needs
																	</a> 
																</div> 
															</div>
														</c:if>
														<c:if test="${consumer.researchReportPreference == true}">
															<div class="ColumnCommonmyprofile" id="columnmarketmyvendorpreference4"> 
																<div class="lable_header_consumer" id="headermarketmyvendorpreference4"> 
																	<a id="anchortagmarketmyvendorpreference4" href="#tab12" class="lable_header_consumer" data-toggle="tab" onclick="consumerMyProfileActiveMode('${mypreferenceresearchreportneeds}')">
																		Research Report Needs
																	</a> 
																</div> 
															</div>
														</c:if>
													</div>
													<div><br/></div>
													<div class="tab-content">
														<div class="tab-pane active" id="tab9">
															<div class="Rowtableinfoval">
																<div class="ColumnCommon"> 
																	<label class="control-label-fileupload">File Upload (excel or csv)<span class="required">*</span></label>
																	<div class="controlsfileupload">
																		 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
																	</div>
																	<div class="controlsfileuploadreadonly">
																		 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
																	</div>
																</div>
																<div class="ColumnCommonray">
																	<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
																</div>
															</div>
															<div><br/></div>
															<div class="Row">
																<div class="ColumnCommonvenmyconsumerpreference">
																	<div class="control-group">
																		<label class="control-label">Data Coverage Region<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myvenprefercoverageregion" multiple="multiple" id="myvenprefercoverageregion">
																		     	<option value ="-SELECT-"> -SELECT- </option>
																			     <c:forEach var="regions" items="${regions}">
																			    	<option value="${regions.name}">${regions.name}</option>
																			 	 </c:forEach>
																		 	</select> 
																		</div>
																	</div>
																	<div class="control-group">
																		<label class="control-label">Data Coverage Country<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myvenprefercoveragecountry" multiple="multiple" id="myvenprefercoveragecountry">
																			     <option value ="-SELECT-"> -SELECT- </option>
																			     <c:forEach var="countries" items="${countries}">
																				    <option value="${countries.name}">${countries.name}</option>
																				  </c:forEach>
																	   		</select>
																		</div>
																	</div>
																</div>
																<div class="ColumnCommonvendorpageconsumerdatapreference">
																	<div class="control-group">
																		<label class="control-label">Vendor Year of Operation<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myvenpreferyearoperation" id="myvenpreferyearoperation" multiple="multiple">
																		      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																		     	<option>Date (1970)</option>
																		     	<option>Last one week</option>
																		     	<option>Last one month</option>
																		     	<option>Last one year</option>
																	     	</select>
																		</div>
																	</div>
																	<div class="control-group">
																		<label class="control-label">Vendor Support Coverage Region<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myvenprefersupportregion" id="myvenprefersupportregion" multiple="multiple">
																		      <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																			     <c:forEach var="regions" items="${regions}">
																			    	<option value="${regions.name}">${regions.name}</option>
																			 	 </c:forEach>
																			</select>
																		</div>
																	</div>
																</div>
																<div class="ColumnCommonvendorpreference">
																	<div class="control-group">
																		<label class="control-label" style="padding-left: 11px;">Support Coverage Time<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myvenprefersupporttime" id="myvenprefersupporttime" multiple="multiple">
																		      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																				<c:forEach var="supports" items="${supports}">
																			    	<option value="${supports.name}">${supports.name}</option>
																			 	</c:forEach>
																			</select>
																		</div>
																	</div>
																	<div class="control-group">
																		<label class="control-label">Awards<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myvenpreferawards" id="myvenpreferawards" multiple="multiple">
																			     <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																				 <c:forEach var="awards" items="${awards}">
																				     <option value="${awards.name}">${awards.name}</option>
																				 </c:forEach>
																			</select>
																		</div>
																	</div>
																</div> 
															</div>
															<div class="control-group">
																<div class="controls">
																	<div class="se" style="  margin: 0px 0px 0px 232px;">
																 	  <a class="myVendorPreferMarkerDataUpload"> <span class="lable_header_add">Add More </span></a> 
																 	</div>
																</div>
															</div> 
															<div class="portlet-body">												
																<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1myVenPreferenceMarketData">
																	<thead style="background-color:#7BCCA5;">
																		<tr>
																			<th>Data Coverage Region</th>
																			<th>Data Coverage Country</th>
																			<th>Vendor Year of Operation</th>
																			<th>Vendor Support Coverage Region</th>
															                <th>Vendor Support Coverage Time</th>
																			<th>Awards</th>
																			<th>Actions</th>
																		</tr>
																	</thead>
																	<tbody>
																	</tbody>
																</table>
																<input type="hidden" id="myVendorPreferenceMarketDataAddJsontable" name="myVendorPreferenceMarketDataAddJsontable"/>
															</div>
															<div><br/></div>
															<div class="form-actions clearfix">
																<div class="se">
																	<a  href="#tab2" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${mybusinessneeds}');">Back</a>
																	<input type="submit" value="Update" class="btn"/>
																	<input type="reset" value="Reset" class="btn" />
																	<a  href="#tab10" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${mypreferencetradingapplicationneeds}');">Continue</a>
																</div>
															</div>
														</div>
														<div class="tab-pane" id="tab10">
													   		<div><br></div>
																<div class="Rowtableinfoval">
																	<div class="ColumnCommon"> 
																		<label class="control-label-fileupload">File Upload (excel or csv)<span class="required">*</span></label>
																		<div class="controlsfileupload">
																			 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
																		</div>
																		<div class="controlsfileuploadreadonly">
																			 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
																		</div>
																	</div>
																	<div class="ColumnCommonray">
																		<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
																	</div>
																</div>
																<div><br/></div>
																<div class="Row">
																	<div class="ColumnCommonvenmyconsumerpreference">
																		<div class="control-group">
																			<label class="control-label">Data Coverage Region<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenTradingcoverageregion" multiple="multiple" id="myvenTradingcoverageregion">
																			     	<option value ="-SELECT-"> -SELECT- </option>
																				     <c:forEach var="regions" items="${regions}">
																				    	<option value="${regions.name}">${regions.name}</option>
																				 	 </c:forEach>
																			 	</select> 
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">Data Coverage Country<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenTradingcoveragecountry" multiple="multiple" id="myvenTradingcoveragecountry">
																				     <option value ="-SELECT-"> -SELECT- </option>
																				     <c:forEach var="countries" items="${countries}">
																					    <option value="${countries.name}">${countries.name}</option>
																					  </c:forEach>
																		   		</select>
																			</div>
																		</div>
																	</div>
																	<div class="ColumnCommonvendorpageconsumerdatapreference">
																		<div class="control-group">
																			<label class="control-label">Vendor Year of Operation<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenTradingyearoperation" id="myvenTradingyearoperation" multiple="multiple">
																			      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																			     	<option>Date (1970)</option>
																			     	<option>Last one week</option>
																			     	<option>Last one month</option>
																			     	<option>Last one year</option>
																		     	</select>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">Vendor Support Coverage Region<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenTradingsupportregion" id="myvenTradingsupportregion" multiple="multiple">
																			      <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																				     <c:forEach var="regions" items="${regions}">
																				    	<option value="${regions.name}">${regions.name}</option>
																				 	 </c:forEach>
																				</select>
																			</div>
																		</div>
																	</div>
																	<div class="ColumnCommonvendorpreference">
																		<div class="control-group">
																			<label class="control-label" style="padding-left: 11px;">Support Coverage Time<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenTradingsupporttime" id="myvenTradingsupporttime" multiple="multiple">
																			      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																					<c:forEach var="supports" items="${supports}">
																				    	<option value="${supports.name}">${supports.name}</option>
																				 	</c:forEach>
																				</select>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">Awards<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenTradingawards" id="myvenTradingawards" multiple="multiple">
																				     <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																					 <c:forEach var="awards" items="${awards}">
																					     <option value="${awards.name}">${awards.name}</option>
																					 </c:forEach>
																				</select>
																			</div>
																		</div>
																	</div> 
																</div>
																<div class="control-group">
																	<div class="controls">
																		<div class="se" style="  margin: 0px 0px 0px 232px;">
																	 	  <a class="myVendorPreferTradngApplicationUpload"> <span class="lable_header_add">Add More </span></a> 
																	 	</div>
																		</div>
																	</div> 
																<div class="portlet-body">												
																	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1myVenPreferenceTradingApplication">
																		<thead style="background-color:#7BCCA5;">
																			<tr>
																				<th>Data Coverage Region</th>
																				<th>Data Coverage Country</th>
																				<th>Vendor Year of Operation</th>
																				<th>Vendor Support Coverage Region</th>
																                <th>Vendor Support Coverage Time</th>
																				<th>Awards</th>
																				<th>Actions</th>
																			</tr>
																		</thead>
																		<tbody>
																		</tbody>
																	</table>
																	<input type="hidden" id="myVendorPreferenceTradingApplicationAddJsontable" name="myVendorPreferenceTradingApplicationAddJsontable"/>
																</div>
																<div><br/></div>
																<div class="form-actions clearfix">
																	<div class="se">
																		<a  href="#tab9" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${mypreferencemarketdataneeds}');">Back</a>
																		<input type="submit" value="Update" class="btn"/>
																		<input type="reset" value="Reset" class="btn" />
																		<a  href="#tab11" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${mypreferenceanalyticsapplicationneeds}');">Continue</a>
																	</div>
																</div>									
															</div>									
															<div class="tab-pane" id="tab11">									
																<div class="Rowtableinfoval">
																	<div class="ColumnCommon"> 
																		<label class="control-label-fileupload">File Upload (excel or csv)<span class="required">*</span></label>
																		<div class="controlsfileupload">
																			 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
																		</div>
																		<div class="controlsfileuploadreadonly">
																			 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
																		</div>
																	</div>
																	<div class="ColumnCommonray">
																		<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
																	</div>
																</div>
																<div><br/></div>
																<div class="Row">
																	<div class="ColumnCommonvenmyconsumerpreference">
																		 <div class="control-group">
																			<label class="control-label">Data Coverage Region<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenAnalAppliccoverageregion" multiple="multiple" id="myvenAnalAppliccoverageregion">
																			     	<option value ="-SELECT-"> -SELECT- </option>
																				     <c:forEach var="regions" items="${regions}">
																				    	<option value="${regions.name}">${regions.name}</option>
																				 	 </c:forEach>
																			 	</select> 
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">Data Coverage Country<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenAnalAppliccoveragecountry" multiple="multiple" id="myvenAnalAppliccoveragecountry">
																				     <option value ="-SELECT-"> -SELECT- </option>
																				     <c:forEach var="countries" items="${countries}">
																					    <option value="${countries.name}">${countries.name}</option>
																					  </c:forEach>
																		   		</select>
																			</div>
																		</div>
																	</div>
																	<div class="ColumnCommonvendorpageconsumerdatapreference">
																		<div class="control-group">
																			<label class="control-label">Vendor Year of Operation<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenAnalApplicyearoperation" id="myvenAnalApplicyearoperation" multiple="multiple">
																			      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																			     	<option>Date (1970)</option>
																			     	<option>Last one week</option>
																			     	<option>Last one month</option>
																			     	<option>Last one year</option>
																		     	</select>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">Vendor Support Coverage Region<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenAnalApplicsupportregion" id="myvenAnalApplicsupportregion" multiple="multiple">
																			      <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																				     <c:forEach var="regions" items="${regions}">
																				    	<option value="${regions.name}">${regions.name}</option>
																				 	 </c:forEach>
																				</select>
																			</div>
																		</div>
																	</div>
																	<div class="ColumnCommonvendorpreference">
																		<div class="control-group">
																			<label class="control-label" style="padding-left: 11px;">Support Coverage Time<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenAnalApplicsupporttime" id="myvenAnalApplicsupporttime" multiple="multiple">
																			      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																					<c:forEach var="supports" items="${supports}">
																				    	<option value="${supports.name}">${supports.name}</option>
																				 	</c:forEach>
																				</select>
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">Awards<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myvenAnalApplicawards" id="myvenAnalApplicawards" multiple="multiple">
																				     <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																					 <c:forEach var="awards" items="${awards}">
																					     <option value="${awards.name}">${awards.name}</option>
																					 </c:forEach>
																				</select>
																			</div>
																		</div>
																	</div> 
																</div>
																<div class="control-group">
																	<div class="controls">
																		<div class="se" style="  margin: 0px 0px 0px 232px;">
																	 	  <a class="myVendorPreferAnalyticsApplicationUpload"> <span class="lable_header_add">Add More </span></a> 
																	 	</div>
																	</div>
																</div> 
																<div class="portlet-body">												
																	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1myVenPreferenceAnalyticsApplication">
																		<thead style="background-color:#7BCCA5;">
																			<tr>
																				<th>Data Coverage Region</th>
																				<th>Data Coverage Country</th>
																				<th>Vendor Year of Operation</th>
																				<th>Vendor Support Coverage Region</th>
																                <th>Vendor Support Coverage Time</th>
																				<th>Awards</th>
																				<th>Actions</th>
																			</tr>
																		</thead>
																		<tbody>
																		</tbody>
																	</table>
																	<input type="hidden" id="myVendorPreferenceAnalyticsAppliAddJsontable" name="myVendorPreferenceAnalyticsAppliAddJsontable"/>
																</div>
																<div><br/></div>
																<div class="form-actions clearfix">
																	<div class="se">
																		<a  href="#tab10" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${mypreferencetradingapplicationneeds}');">Back</a>
																		<input type="submit" value="Update" class="btn"/>
																		<input type="reset" value="Reset" class="btn" />
																		<a  href="#tab12" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${mypreferenceresearchreportneeds}');">Continue</a>
																	</div>
																</div>									
															</div>								
															<div class="tab-pane" id="tab12">
																<div class="Rowtableinfoval">
																	<div class="ColumnCommon"> 
																		<label class="control-label-fileupload">File Upload (excel or csv)<span class="required">*</span></label>
																		<div class="controlsfileupload">
																			 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
																		</div>
																		<div class="controlsfileuploadreadonly">
																			 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
																		</div>
																	</div>
																	<div class="ColumnCommonray">
																		<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
																	</div>
																</div>
																<div><br/></div>
																<div class="Row">
																	<div class="ColumnCommonvenmyconsumerpreference">
																		 <div class="control-group">
																			<label class="control-label">Data Coverage Region<span class="required">*</span></label>
																			<div class="controls">
																				<select name="myVendoResearchRepocoverageregion" multiple="multiple" id="myVendoResearchRepocoverageregion">
																			     	<option value ="-SELECT-"> -SELECT- </option>
																				     <c:forEach var="regions" items="${regions}">
																				    	<option value="${regions.name}">${regions.name}</option>
																				 	 </c:forEach>
																			 	</select> 
																			</div>
																		</div>
																	<div class="control-group">
																		<label class="control-label">Data Coverage Country<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myVendoResearchRepocoveragecountry" multiple="multiple" id="myVendoResearchRepocoveragecountry">
																			     <option value ="-SELECT-"> -SELECT- </option>
																			     <c:forEach var="countries" items="${countries}">
																				    <option value="${countries.name}">${countries.name}</option>
																				  </c:forEach>
																	   		</select>
																		</div>
																	</div>
																</div>
																<div class="ColumnCommonvendorpageconsumerdatapreference">
																	<div class="control-group">
																		<label class="control-label">Vendor Year of Operation<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myVendoResearchRepoyearoperation" id="myVendoResearchRepoyearoperation" multiple="multiple">
																		      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																		     	<option>Date (1970)</option>
																		     	<option>Last one week</option>
																		     	<option>Last one month</option>
																		     	<option>Last one year</option>
																	     	</select>
																		</div>
																	</div>
																	<div class="control-group">
																		<label class="control-label">Vendor Support Coverage Region<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myVendoResearchReposupportregion" id="myVendoResearchReposupportregion" multiple="multiple">
																		      <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																			     <c:forEach var="regions" items="${regions}">
																			    	<option value="${regions.name}">${regions.name}</option>
																			 	 </c:forEach>
																			</select>
																		</div>
																	</div>
																</div>
																<div class="ColumnCommonvendorpreference">
																	<div class="control-group">
																		<label class="control-label" style="padding-left: 11px;">Support Coverage Time<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myVendoResearchReposupporttime" id="myVendoResearchReposupporttime" multiple="multiple">
																		      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																				<c:forEach var="supports" items="${supports}">
																			    	<option value="${supports.name}">${supports.name}</option>
																			 	</c:forEach>
																			</select>
																		</div>
																	</div>
																	<div class="control-group">
																		<label class="control-label">Awards<span class="required">*</span></label>
																		<div class="controls">
																			<select name="myVendoResearchRepoawards" id="myVendoResearchRepoawards" multiple="multiple">
																			     <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																				 <c:forEach var="awards" items="${awards}">
																				     <option value="${awards.name}">${awards.name}</option>
																				 </c:forEach>
																			</select>
																		</div>
																	</div>
																</div> 
															</div>
															<div class="control-group">
																<div class="controls">
																<div class="se" style="  margin: 0px 0px 0px 232px;">
															 	  <a class="myVendorPreferResearchReportUpload"> <span class="lable_header_add">Add More </span></a> 
															 	</div>
																</div>
															</div> 
															<div class="portlet-body">											
																<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1myVenPreferenceResearchReport">
																	<thead style="background-color:#7BCCA5;">
																		<tr>
																			<th>Data Coverage Region</th>
																			<th>Data Coverage Country</th>
																			<th>Vendor Year of Operation</th>
																			<th>Vendor Support Coverage Region</th>
															                <th>Vendor Support Coverage Time</th>
																			<th>Awards</th>
																			<th>Actions</th>
																		</tr>
																	</thead>
																	<tbody>
																	</tbody>
																</table>
																<input type="hidden" id="myVendorPreferenceResearchReportAddJsontable" name="myVendorPreferenceResearchReportAddJsontable"/>
															</div>
															<div><br/></div>
															<div class="form-actions clearfix">
																<div class="se">
																<a  href="#tab11" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${mypreferenceanalyticsapplicationneeds}');">Back</a>
																<input type="submit" value="Update" class="btn"/>
																<input type="reset" value="Reset" class="btn" />
															</div>
														</div>
													</div>
												</div>  
												</div>	
												<%-- My Profile - My Vendor Delivery & Cost Preference --%>
												<div class="tab-pane" id="consumerProfileDeliveryAndCostPreferenceTab">									
												<div><br/></div>
												<div class="Rowtable">
													<c:if test="${consumer.marketDataPreference == true}">
														<div class="ColumnCommonmyprofile" id="columnmyvencostpreference1"> 
															<div class="lable_header_consumer" id="headermyvencostpreference1"> 
																<a id="anchortagcostpreference1" href="#tab13" class="lable_header_consumer" data-toggle="tab"  onclick="consumerMyProfileActiveMode('${costpreferencemarketdataneeds}')">
																	Market Data Needs
																</a> 
															</div>
														</div>
													</c:if>
													<c:if test="${consumer.tradingAppPreference == true}">
														<div class="ColumnCommonmyprofile" id="columnmyvencostpreference2"> 
															<div class="lable_header_consumer" id="headermyvencostpreference2">  
																<a id="anchortagcostpreference2" href="#tab14" class="lable_header_consumer" data-toggle="tab" onclick="consumerMyProfileActiveMode('${costpreferenceapplicationneeds}')">
																	Trading Application Needs
																</a> 
															</div> 
														</div>
													</c:if>
													<c:if test="${consumer.analyticsAppPreference == true}">
														<div class="ColumnCommonmyprofile" id="columnmyvencostpreference3"> 
															<div class="lable_header_consumer" id="headermyvencostpreference3">  
																<a id="anchortagcostpreference3" href="#tab15" class="lable_header_consumer" data-toggle="tab" onclick="consumerMyProfileActiveMode('${costpreferenceanalyticsapplicationneeds}')">
																	Analytics Application Needs
																</a> 
															</div> 
														</div>
													</c:if>
													<c:if test="${consumer.researchReportPreference == true}">
														<div class="ColumnCommonmyprofile" id="columnmyvencostpreference4"> 
															<div class="lable_header_consumer" id="headermyvencostpreference4"> 
																<a id="anchortagcostpreference4" href="#tab16" class="lable_header_consumer" data-toggle="tab" onclick="consumerMyProfileActiveMode('${costpreferencesearchreportneeds}')">
																	Research Report Needs
																</a> 
															</div> 
														</div>
													</c:if>
												</div>
												<div><br/></div>
												<div class="tab-content">
													<div class="tab-pane active" id="tab13">
														<div class="Rowtableinfoval">
															<div class="ColumnCommon"> 
																<label class="control-label-fileupload">File Upload (excel or csv)<span class="required">*</span></label>
																<div class="controlsfileupload">
																	 <input type="text" id="orgname" value="C:\ConsumerUpload\MyDatapreference_FV.CSV" class="m-wrap largefileuploadreadonly" readonly="readonly"  /> 
																</div>
																<div class="controlsfileuploadreadonly">
																	 <input type="file" id="consumerfileUpload" placeholder="Company Logo" name="fileUpload" class="m-wrap largefileupload" style="margin: -47px 0px 3px 503px;"/>
																</div>
															</div>
															<div class="ColumnCommonray">
															<a class="consumerfileupload"> <span class="lable_header_add">Upload</span></a>
															</div>
														</div>
														<div><br/></div>
														<div class="Row">
														<div class="ColumnCommonvenmyconsumerpreference">
															<div class="control-group">
																<label class="control-label">Asset Class<span class="required">*</span></label>
																<div class="controls">
																	<select name="costpreferenceassetclass"  id="costpreferenceassetclass" onchange="loadCostPreferenceSecurityTypes(this.value);">
																     <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Security type<span class="required">*</span></label>
																<div class="controls">
																	<select name="securitytype"  id="assetClassCostPreferenceSecurityDistriMaps" multiple="multiple">
																  	 <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																     <c:forEach var="assetClassCostPreferenceSecurityDistriMaps" items="${assetClassCostPreferenceSecurityDistriMaps}">
																	    <option value="${assetClassCostPreferenceSecurityDistriMaps.securityType.name}">${assetClassCostPreferenceSecurityDistriMaps.securityType.name}</option>
																	  </c:forEach>
																  </select>
																</div>
															</div>
														</div>
														<div class="ColumnCommonvendorpageconsumerdatapreference">
															<div class="control-group">
																<label class="control-label">Delivery method<span class="required">*</span></label>
																<div class="controls">
																	<select name="costpreferencedeliverymethod" id="costpreferencedeliverymethod" multiple="multiple">
																      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																     	<option value="Datafeed">Datafeed</option>
																     	<option value="Web">Web</option>
																     	<option value="FTP">FTP</option>
																     	<option value="Email">Email</option>
															     	</select>
																</div>
															</div>
															<div class="control-group">
																<label class="control-label">Feed Type<span class="required">*</span></label>
																<div class="controls">
																	<select name="costpreferencefeedtype" id="costpreferencefeedtype" multiple="multiple">
																      	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
																     	<option value="EOD">EOD</option>
																     	<option value="REAL-TIME">REAL-TIME</option>
																     	<option value="HISTORICAL-EOD">HISTORICAL-EOD</option>
															     	</select>
																</div>
															</div>
														</div>
														<div class="ColumnCommonvendorpreference">
															<div class="control-group">
																<label class="control-label" style="padding-left: 11px;">Data cost Range<span class="required">*</span></label>
																<div class="controls">
																	<select name="costpreferencecotrange"  id="costpreferencecotrange" multiple="multiple">
																	      <option selected="selected" value ="-SELECT-" > -SELECT- </option>
																		 <c:forEach var="costs" items="${costs}">
																		     <option value="${costs.range}">${costs.range}</option>
																		 </c:forEach>
																</select>
																</div>
															</div>
														</div> 
													</div>
													<div class="control-group">
														<div class="controls">
															<div class="se" style="  margin: 0px 0px 0px 232px;">
														 	  <a class="myVendorCostPreferMarkerDataUpload"> <span class="lable_header_add">Add More </span></a> 
														 	</div>
														</div>
													</div> 
													<div class="portlet-body">												
														<table class="table table-striped table-bordered table-hover table-full-width" id="sample_1myVenCostPreferenceMarketData">
															<thead style="background-color:#7BCCA5;">
																<tr>
																	<th>Asset Class</th>
																	<th>Security Type</th>
																	<th>Delivery method</th>
																	<th>Feed Type</th>
													                <th>Data cost Range</th>
																	<th>Actions</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
														<input type="hidden" id="myVendorCostPreferenceMarketDataAddJsontable" name="myVendorCostPreferenceMarketDataAddJsontable"/>
													</div>
													<div><br/></div>
													<div class="form-actions clearfix">
														<div class="se">
															<a  href="#tab3" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${myvendorpreference}');">Back</a>
															<input type="submit" value="Update" class="btn"/>
															<input type="reset" value="Reset" class="btn" />
															<a  href="#tab14" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${costpreferenceapplicationneeds}');">Continue</a>
														</div>
													</div>
												</div>
													<div class="tab-pane" id="tab14">
														<div><br></div>												
														<div><br/></div>
														<div class="form-actions clearfix">
															<div class="se">
																<a  href="#tab13" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${costpreferencemarketdataneeds}');">Back</a>
																<input type="submit" value="Update" class="btn"/>
																<input type="reset" value="Reset" class="btn" />
																<a  href="#tab15" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${costpreferenceanalyticsapplicationneeds}');">Continue</a>
															</div>
														</div>
													</div>									
													<div class="tab-pane" id="tab15">
														<div><br/></div>
														<div class="form-actions clearfix">
															<div class="se">
																<a  href="#tab14" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${costpreferenceapplicationneeds}');">Back</a>
																<input type="submit" value="Update" class="btn"/>
																<input type="reset" value="Reset" class="btn" />
																<a  href="#tab16" class="btn button-next" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${costpreferencesearchreportneeds}');">Continue</a>
															</div>
														</div>									
													</div>
													<div class="tab-pane" id="tab16">											
														<div><br/></div>
														<div class="form-actions clearfix">
															<div class="se">
															<a  href="#tab15" class="btn button-previous" data-toggle="tab" class="step" onclick="consumerMyProfileActiveMode('${costpreferenceanalyticsapplicationneeds}');">Back</a>
															<input type="submit" value="Update" class="btn"/>
															<input type="reset" value="Reset" class="btn" />
														</div>
														</div>
													</div>
												</div>  
											</div>
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
	<jsp:include page="common/footer.jsp"></jsp:include>
	<%--
	<script type="text/javascript">
		window.onload = function(){
			var companyDetails= '<%=RequestConstans.Consumer.COMPANYDETAILS %>';
			if(companyDetails != null && companyDetails.length > 0 && companyDetails.match("companydetails")){
				document.getElementById('columnconsumer1').style.backgroundColor = '#5CE5E5';
				document.getElementById('headerconsumer1').style.backgroundColor = '#5CE5E5';  
				document.getElementById('anchortagconsumer1').style.backgroundColor = '#5CE5E5';
			}
		};
	</script>
	--%>	
	<c:if test="${lastActionStatus != null}">
		<script>alert('${lastActionStatus}')</script>
	</c:if>
	<c:if test="${lastActionError != null}">
		<script>alert('${lastActionError}')</script>
	</c:if>
</body>
</html>