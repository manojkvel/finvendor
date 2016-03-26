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
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<div class="container">  
    <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}"> Analytics Application (AA) Vendor</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
        
        <div class="row-fluid">
	   <div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							  <div class="portlet-body form">
							  
							  <!-- Search using saved search starts here -->
							  <div id="savedsearchanalyticsapplicationform">
								<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
											<!-- Support coverage start --> 
												<div class="tab-pane active">
												<div><br/></div>
												 <div class="Row">
													<div class="ColumnCommonvendor">
														<div class="control-group">
															<label class="control-label">Analytics Solutions Type</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
													</div>
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se" style="padding-left: 150px;">
										<input type="reset" value="Search" class="btn" />
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
										</div>
								</div>
								</form>
								</div>
								<!-- Search using saved search end here -->
								
								<!-- Single Trading Vendor Start here -->
								<div id="searchsingleanalyticsapplicationform">
									<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
												<div class="tab-pane active" >
												<div class="Rowtableinfoval">
													<div class="ColumnCommonmyprofiletradingapplication" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Search Vendor for Single AA Type</a> </div></div>
												</div>
												<div><br/></div>
												 <div class="Row">
													<div class="ColumnCommonvendorTradingApplicationVendor">
														<div class="control-group">
															<label class="control-labelappstra">Analytics Solutions Type</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
															<div class="control-grouptrading">
															<label class="control-labelappstra">Analytics Solutions Sub Type</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Accessibility</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Suitability</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="suitability" multiple="multiple" id="suitability" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <option value ="All Users"> All Users </option>
															     <option value ="Advanced shares traders"> Advanced shares traders </option>
															     <option value ="Technical chart users"> Technical chart users </option>
															     <option value ="Forex specialists"> Forex specialists </option>
															     <option value ="Other"> Other </option>
														 		</select> 
														 		 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Software type</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
														<label class="control-labelappstra">Vendor Region of Incorp</label>
														<div class="controls" style="margin-left: 160px;">
															<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														 	<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-grouptrading">
														<label class="control-labelappstra">Vendor Country of Incorp</label>
														<div class="controls" style="margin-left: 160px;">
															<select name="datacoveragecountry" multiple="multiple" id="datacoveragecountry" style="height: 53px; width: 227px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpreTradingApplcationVendor">
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Customizable Calc. Model?</label>
															<div class="controls" >
																<input type="checkbox" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wraptrading largeval"/>
															</div>
													</div>
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra" style="margin-left: -159px;">Real time Market Data?</label>
															<div class="controls" >
																<input type="checkbox" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wraptrading largeval" style="margin-top: 8px;"/>
															</div>
													</div>
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Vendor Profile Freshness</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
													</div>
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Vendor Year of Operation</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Search Keywords</label>
															<div class="controls" style="margin-left: 160px; width: 227px;">
																<input type="text" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wrap largevaltradingapp" />
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Vendor Support Coverage Region</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Vendor Support Coverage Time</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Awards</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Cost Range</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Existing User Base</label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
													</div>
													
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se" style="padding-left: 150px;">
										<input type="reset" value="Search" class="btn" />
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
										</div>
								</div>
								</form>
								</div>
								<!-- Single Trading Vendor Start here -->
								
								<!-- Multi Trading Vendor Start here -->
								<div id="searchmultianaylticsapplicationform">
								<form action="<%=request.getContextPath()+"/"+RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>?RaYvEmUl=${l:encrypt(username)}" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
											<!-- Support coverage start --> 
												<div class="tab-pane active" >
												
												<div><br/></div>
												 <div class="Row">
												 
												 <div>
												 <div>
												 <label class="control-label" style="margin-left: 30px;">Analytics Solution Type<span class="required">*</span></label>
												 </div>
												 <div>
												 <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Alternative Invest.. Soln&nbsp;
															  <input type="checkbox" name="assetClassChk" value="AlternativeInvestmentSolu"  id="AlternativeInvestmentSolu">
													</label>		  
												 </div>
												 
												 <div>
												  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Backoffice Operations&nbsp;
															  <input type="checkbox" name="assetClassChk" value="BackofficeOperations"  id="BackofficeOperations">
														</label>	  
												 </div>
												 
												  <div>
												 	  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Product Valuation&nbsp;
															  <input type="checkbox" name="assetClassChk" value="ProductValuation"  id="ProductValuation">
															  </label>
												 </div>
												 
												 <div>
												  		<label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Banking Solutions&nbsp;
															  <input type="checkbox" name="assetClassChk" value="BankingSolution"  id="BankingSolution">
															  </label>
												 </div>
												 
												  <div>
												 	  <label class="control-labelcheckboxanalytics" style="font-family: Raleway, sans-serif; font-size: 13px;">Regulatory Compliance &amp; Risk Mgmt
															  <input type="checkbox" name="assetClassChk" value="RegulatoryComplianceRiskMGT"  id="RegulatoryComplianceRiskMGT">
															  </label>
												 </div>
												 
												 	<div>
															  <label class="control-labelcheckboxanalytics" style="font-family: Raleway, sans-serif; font-size: 13px;">Portfolio Management Solutions&nbsp;
															  <input type="checkbox" name="assetClassChk" value="PortfolioManagement"  id="PortfolioManagement">
															  </label>
														</div>
												 
												 </div>
												 
													  
												</div>
												<div><br/></div>
												<div class="Rowtableinfoval">
													<div class="ColumnCommonmyprofiletradingapplication" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Common fields for all asset class</a> </div></div>
												</div>
												<div class="Row">
													<div class="ColumnCommonvendorTradingApplicationVendor">
														<div class="control-group">
															<label class="control-labelappstra">Trading Capability Type</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														
														<div class="control-grouptrading">
															<label class="control-labelappstra">Accessibility</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="accessibility" multiple="multiple" id="accessibility"  style="height: 53px; width: 227px;">
														 	    <option value ="" class="selectvalues"> -SELECT- </option>
														     	<option value ="Web Browser Based" >Web Browser Based</option>
														     	<option value ="Binaries(Executable) Based" > Binaries(Executable) Based </option>
														     	<option value ="Dedicated Desktop" >Dedicated Desktop</option>
														     	<option value ="Mobile/Tablet Apps" >Mobile/Tablet Apps</option>
					
														   </select>
					 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Suitability</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="suitability" multiple="multiple" id="suitability" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															      <option value ="All Users" >All Users</option>
														       <option value ="Risk Managers" >Risk Managers</option>
																<option value ="Backoffice & Middle Office Users" >Backoffice & Middle Office Users</option>
																<option value ="Portfolio Managers" >Portfolio Managers</option>
																<option value ="Retail/Corporat Banking Users" >Retail/Corporat Banking Users</option>
																<option value ="Regulation & Compliance Users" >Regulation & Compliance Users</option>
																<option value ="Research Analysts" >Research Analysts</option>
																<option value ="Others" >Others</option>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														 
															<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Region of Incorp</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
													</div>
													<div class="ColumnCommonvendorpreTradingApplcationVendormultiasset">
													<div class="control-grouptrading">
															<label class="control-labelappstra">Customizable Calc. Model?</label>
															<div class="controls" >
																<input type="checkbox" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wraptrading largeval"/>
															</div>
													</div>
													<div class="control-grouptrading">
															<label class="control-labelappstra">Real time Market Data?</label>
															<div class="controls" >
																<input type="checkbox" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wraptrading largeval" style="margin-top: 8px;"/>
															</div>
													</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Country of Incorp</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Profile Freshness</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="vendorProfileFreshness" id="vendorProfileFreshness" style="height: 53px; width: 227px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     <option>today</option>
															     <option>Last one week</option>
															     <option>Last one month</option>
															     <option>Last one year</option>
														 		</select> 
															</div>
														</div>
														
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Support Coverage Region</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="vendorSupportCoverageRegion" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Support Coverage Time</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="vendorSupportCoverageTime" id="vendorSupportCoverageTime" style="height: 53px; width: 227px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="supports" items="${supports}">
							    									<option value="${supports.support_id}">${supports.name}</option>
							 									</c:forEach>
															     
														 		</select> 
															</div>
														</div>
													</div>
													
													</div>
													<div><br/></div>
													<div class="Row">
													  <div class="ColumnCommontradingapp" id="financialanalyticsmulticommonarea">
													  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
													  <div><br/></div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analytics Solutions Sub Type</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Year of Operation</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Awards</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Cost Range</label>
														</div>
														</div>
														<div class="ColumnCommontradingappinterestratemarket" id="alternativeinvestment" >
														<div class="lable_header" style="margin: -9px -11px 12px 14px;">Alternative Investment</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="analyticsSolutionsSubType" multiple="multiple" id="analyticsSolutionsSubType" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     <option value ="Custody management" > Custody management </option>
															     <option value ="Alternative and institutional investments" > Alternative and institutional investments </option>
															     <option value ="Commodity trading solutions" > Commodity trading solutions </option>
															     <option value ="FCP/CCP clearing suite" > FCP/CCP clearing suite </option>
									
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     
														 	 	</select> 
														 	 </div>
														 	 <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     
														 		</select> 
														    </div>
														</div>
														<div class="ColumnCommontradingappinterestrateetfmarket" id="backofficeoperations">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Backoffice Operations</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	 <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														 </div>
														 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="productvaluation">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Product Valuation</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														 </div>
														 <div class="ColumnCommontradingappinterestrateetfxfmarket" id="bankingsolution">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Banking Solutions</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														 </div>
													</div>
													<div><br/></div>
													<div class="Row">
													  <div class="ColumnCommontradingapp" id="financialanalyticsmulticommonareainformation">
													  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
													  <div><br/></div>
														<div class="control-group"  style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analytics Solutions Sub Type</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Year of Operation</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Awards</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Cost Range</label>
														</div>
														</div>
														<div class="ColumnCommontradingappinterestratemarket" id="regulatorycomplianceriskmgt" >
														<div class="lable_header" style="margin: -9px -11px 12px 14px;">Alternative Investment</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	 <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														</div>
														<div class="ColumnCommontradingappinterestrateetfmarket" id="marginingsolutions">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Backoffice Operations</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	 <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														 </div>
														 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="portfoliomgtsolutions">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Product Valuation</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														 </div>
													</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se" style="padding-left: 150px;">
										<input type="submit" value="Search" class="btn" />
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
										</div>
								</div>
								</form>
								</div>
								<!-- Multi Trading Vendor Start here -->
								
								
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
		<!-- <script>
		  $(function() {
		    $( "#inputField" ).datepicker();
		    $( "#inputField1" ).datepicker();
		  });
	  </script>
	  <script src="//code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script> -->
  
  <!-- Date plugins start-->
  
</body>
</html>
