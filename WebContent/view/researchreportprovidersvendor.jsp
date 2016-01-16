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
    <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}"> Research Report (RR) Providers</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
        <div class="control-group">
        	<input type="radio" name="radios"  value="savesearchreportprovidersbutton" id="savesearchreportprovidersbutton">
			<label class="control-label" style="margin-left: 21px; margin-top: -15px;">Search Using Saved Searches<span class="required">*</span></label>
		</div>
        <div class="control-groupcheckingforradiobuttons">
        	<input type="radio" name="radios"  value="singlesearchreportprovidersbutton" id="singlesearchreportprovidersbutton">
			<label class="control-label" style="margin-left: 21px; margin-top: -15px;">Search Analyst for Single RR Type<span class="required">*</span></label>
			<input type="radio" name="radios"  value="multisearchreportprovidersbutton" id="multisearchreportprovidersbutton" style="margin-left: 371px; margin-top: -49px;">
			<label class="control-label" style="margin-left: 393px; margin-top: -45px;"> Search Analyst for Multiple RR Type<span class="required">*</span></label>
		</div>
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
							  <div id="savedresearchreportproviderform">
								<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
											<!-- Support coverage start --> 
												<div class="tab-pane active">
												<div><br/></div>
												 <div class="Row">
													<div class="ColumnCommonvendor">
														<div class="control-group">
															<label class="control-label">Analytics Solutions Type<span class="required">*</span></label>
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
								<div id="searchsingleresearchreportprovidersform">
									<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
												<div class="tab-pane active" >
												<div class="Rowtableinfoval">
													<div class="ColumnCommonmyprofiletradingapplication" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Search Analyst for Single RR Type</a> </div></div>
												</div>
												<div><br/></div>
												 <div class="Row">
													<div class="ColumnCommonvendorTradingApplicationVendor">
														<div class="control-group">
															<label class="control-labelappstra">Research Area<span class="required">*</span></label>
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
															<label class="control-labelappstra">Research Sub Area<span class="required">*</span></label>
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
															<label class="control-labelappstra">Regions Covered<span class="required">*</span></label>
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
															<label class="control-labelappstra">Research Applicable Year<span class="required">*</span></label>
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
															<label class="control-labelappstra">Research Applicable Month<span class="required">*</span></label>
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
														<label class="control-labelappstra">Analyst Region of Incorp<span class="required">*</span></label>
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
														<label class="control-labelappstra">Analyst Country of Incorp<span class="required">*</span></label>
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
															<label class="control-labelappstra">Research Pre by CFA?<span class="required">*</span></label>
															<div class="controls" >
																<input type="checkbox" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wraptrading largeval"/>
															</div>
													</div>
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Analyst Year of Exp<span class="required">*</span></label>
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
														<label class="control-labelappstra">Analyst Profile Freshness<span class="required">*</span></label>
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
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Analyst Support Coverage Region<span class="required">*</span></label>
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
															<label class="control-labelappstra">Search Keywords<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px; width: 227px;">
																<input type="text" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wrap largevaltradingapp" />
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Analyst Support Coverage Time<span class="required">*</span></label>
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
															<label class="control-labelappstra">Analyst Awards<span class="required">*</span></label>
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
															<label class="control-labelappstra">Cost Range<span class="required">*</span></label>
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
															<label class="control-labelappstra">Existing User Base<span class="required">*</span></label>
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
								<div id="searchmultiresearchreportprovidersform">
									<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
											<!-- Support coverage start --> 
												<div class="tab-pane active" >
												
												<div class="Rowtableinfovaltradingapp">
													<div class="ColumnCommonmyprofiletradingapplication" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Search Analyst for Multiple RR Type</a> </div></div>
												</div>
												<div><br/></div>
												 <div class="Row">
													  <div class="ColumnCommonvendor">
														<div class="control-group">
															  <label class="control-label" style="margin-left: 60px;">Analytics Solution Type<span class="required">*</span></label>
														</div>
													</div> 
													<div class="ColumnCommonvendortradingapplication">
														<div class="control-group">
															  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Macro economic Analysis<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="MacroEconomicAnalysis" value="MacroEconomicAnalysis"  id="MacroEconomicAnalysis">
														</div>
													</div>
													<div class="ColumnCommonvendortradingapplication">
														<div class="control-group">
															  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Sector/Industry Analysis<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="SectorIndustryAnalysis" value="SectorIndustryAnalysis"  id="SectorIndustryAnalysis">
														</div>
													</div>
													<div class="ColumnCommonvendortradingapplication">
														<div class="control-group">
															  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Commodity Analysis<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="CommodityAnalysis" value="CommodityAnalysis"  id="CommodityAnalysis">
														</div>
													</div>
													<div class="ColumnCommonvendortradingapplication">
														<div class="control-group">
															  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Exchange Rate Analysis<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="ExchangeRateAnalysis" value="ExchangeRateAnalysis"  id="ExchangeRateAnalysis">
														</div>
													</div> 
													<div class="ColumnCommonvendortradingapplication" style="margin-left: 177px; margin-top: -20px;">
														<div class="control-group">
															  <label class="control-labelcheckboxanalytics" style="font-family: Raleway, sans-serif; font-size: 13px;">Interest Rate Analysis<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="InterestRateAnalysis" value="InterestRateAnalysis"  id="InterestRateAnalysis">
														</div>
													</div>
													<div class="ColumnCommonvendortradingapplication" style="margin-left: -50px; margin-top: -20px;">
														<div class="control-group">
															  <label class="control-labelcheckboxanalytics" style="font-family: Raleway, sans-serif; font-size: 13px;">Index/ETF/Fund research<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="IndexETFFundResearch" value="IndexETFFundResearch"  id="IndexETFFundResearch">
														</div>
													</div>
													<div class="ColumnCommonvendortradingapplication" style="margin-left: -69px; margin-top: -20px;">
														<div class="control-group">
															  <label class="control-labelcheckboxanalytics" style="font-family: Raleway, sans-serif; font-size: 13px;">Equity/Debt research<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="EquityDebtResearch" value="EquityDebtResearch"  id="EquityDebtResearch">
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
															<label class="control-labelappstra">Regions Covered<span class="required">*</span></label>
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
															<label class="control-labelappstra">Research Applicable Year<span class="required">*</span></label>
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
														<div class="control-grouptrading">
															<label class="control-labelappstra">Research Applicable Month<span class="required">*</span></label>
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
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Region of Incorp<span class="required">*</span></label>
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
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Country of Incorp<span class="required">*</span></label>
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
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Country of Incorp<span class="required">*</span></label>
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
																<label class="control-labelappstra" style="margin-left: -1px;">Research Pre by CFA?<span class="required">*</span></label>
																<div class="controls" >
																	<input type="checkbox" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wraptrading largeval" style="margin-top: 8px;"/>
																</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Profile Freshness<span class="required">*</span></label>
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
														<div class="control-grouptrading">
															<label class="control-labelappstra">Search Keywords<span class="required">*</span></label>
															<div class="controls" style="margin-left: 174px; width: 227px;">
																<input type="text" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wrap largevaltradingapp" />
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Support Coverage Region<span class="required">*</span></label>
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
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Support Coverage Time<span class="required">*</span></label>
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
														<div class="control-grouptrading">
															<label class="control-labelappstra">Existing User Base<span class="required">*</span></label>
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
													
													</div>
													<div><br/></div>
												 <div class="Row">
													  <div class="ColumnCommontradingapp" id="researchreportprovidermulticommonarea">
													  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
													  <div><br/></div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Research Sub Area<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analyst Year of Exp<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analyst Awards<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Cost Range<span class="required">*</span></label>
														</div>
														</div>
														<div class="ColumnCommontradingappinterestratemarket" id="macroeconomicanalysis" >
														<div class="lable_header" style="margin: -9px -11px 12px 14px;">Macro Eco.. Analysis</div>
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
														<div class="ColumnCommontradingappinterestrateetfmarket" id="sectorindustryanalysis">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Sector Ind.. Analysis</div>
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
														 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="commodityanalysis">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Commodity Analysis</div>
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
														 <div class="ColumnCommontradingappinterestrateetfxfmarket" id="exchangerateanalysis">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Exchange Analysis</div>
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
													  <div class="ColumnCommontradingapp" id="researchreportprovidermulticommonareainformation">
													  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
													  <div><br/></div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Research Sub Area<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analyst Year of Exp<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analyst Awards<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Cost Range<span class="required">*</span></label>
														</div>
														</div>
														<div class="ColumnCommontradingappinterestratemarket" id="interestrateanalysis" >
														<div class="lable_header" style="margin: -9px -11px 12px 14px;">Interest Analysis</div>
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
														<div class="ColumnCommontradingappinterestrateetfmarket" id="indexETFfundresearch">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Index ETF Research</div>
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
														 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="equitydebtresearch">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Equity Research</div>
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
										<input type="reset" value="Search" class="btn" />
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
