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
															<label class="control-labelappstra">Research Area</label>
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
															<label class="control-labelappstra">Research Sub Area</label>
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
															<label class="control-labelappstra">Regions Covered</label>
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
															<label class="control-labelappstra">Research Applicable Year</label>
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
															<label class="control-labelappstra">Research Applicable Month</label>
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
														<label class="control-labelappstra">Analyst Region of Incorp</label>
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
														<label class="control-labelappstra">Analyst Country of Incorp</label>
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
															<label class="control-labelappstra">Research Pre by CFA?</label>
															<div class="controls" >
																<input type="checkbox" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wraptrading largeval"/>
															</div>
													</div>
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Analyst Year of Exp</label>
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
														<label class="control-labelappstra">Analyst Profile Freshness</label>
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
															<label class="control-labelappstra">Analyst Support Coverage Region</label>
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
															<label class="control-labelappstra">Analyst Support Coverage Time</label>
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
															<label class="control-labelappstra">Analyst Awards</label>
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
								<div id="searchmultiresearchreportprovidersform">
									<form action="<%=request.getContextPath()+"/"+RequestConstans.ResearchReportProviders.MULTI_ASSET_CLASS_SEARCH_RESULT%>?RaYvEmUl=${l:encrypt(username)}" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
											<!-- Support coverage start --> 
												<div class="tab-pane active" >
												
												<div><br/></div>
												 <div class="Row">
													  <div class="ColumnCommonvendor">
														<div class="control-group">
															  <label class="control-label" style="margin-left: 60px;">Research Area</label>
														</div>
													</div> 
													<div>
														<div class="childasset">
														 <label style="font-family: Raleway, sans-serif; font-size: 13px;">Commodity Analysis&nbsp;
														 	  <input type="checkbox" name="assetClassChk" value="Commodity Analysis"  id="MacroEconomicAnalysis" />
														 </label>	  
														</div>
														<div class="childasset">
														 <label style="font-family: Raleway, sans-serif; font-size: 13px;">Exchange Rate Analysis&nbsp;
															  <input type="checkbox" name="assetClassChk" value="Exchange Rate Analysis"  id="SectorIndustryAnalysis" />
															</label>  
														</div>
														<div class="childasset">
														 <label style="font-family: Raleway, sans-serif; font-size: 13px;">Interest Rate Analysis &nbsp;
															  <input type="checkbox" name="assetClassChk" value="Interest Rate Analysis"  id="CommodityAnalysis" />
														</label>	  
														</div>
														<div class="childasset">
														 <label style="font-family: Raleway, sans-serif; font-size: 13px;">Equity research&nbsp;
															  <input type="checkbox" name="assetClassChk" value="Equity research"  id="ExchangeRateAnalysis" />
														</label>	  
														</div>
														<div class="childasset">
														 <label style="font-family: Raleway, sans-serif; font-size: 13px;">Debt Market research&nbsp;
															  <input type="checkbox" name="assetClassChk" value="Debt Market research"  id="InterestRateAnalysis">
														</label>	  
														</div>
														<div class="childasset">
														 <label style="font-family: Raleway, sans-serif; font-size: 13px;">Index research&nbsp;
															  <input type="checkbox" name="assetClassChk" value="Index research"  id="MacroEconomicAnalysis">
														</label>	  
														</div>
														<div class="childasset">
															  <label style="font-family: Raleway, sans-serif; font-size: 13px;">Fund/ETF research&nbsp;
															  <input type="checkbox" name="assetClassChk" value="Fund/ETF research"  id="IndexETFFundResearch">
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
															<label class="control-labelappstra">Regions Covered</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="regionsCovered"  id="regionsCovered" style="width: 227px;">
																    <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
																    
																 </select>
															</div>
														</div>
															
														
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Country of Incorp</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="analystCountryofIncorp" multiple="multiple" id="analystCountryofIncorp" style="height: 53px; width: 227px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														
														<div class="control-group">
														<label class="control-labelalign" style="padding-left: 35px;">Research Analyst with CFA Charter?&nbsp;&nbsp;<input type="checkbox" id="researchAnalystWithCFA" placeholder="Research Analyst with CFA" name="researchAnalystWithCFA"></label>
														
													</div>
														
													</div>
													<div class="ColumnCommonvendorpreTradingApplcationVendormultiasset">
														
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Profile Freshness</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="analystProfileFreshness" id="analystProfileFreshness" style="height: 53px; width: 227px;">
															     	<option value ="" class="selectvalues"> -SELECT- </option>
																    <option>today</option>
																     <option>Last one week</option>
																     <option>Last one month</option>
																     <option>Last one year</option>
														 		</select> 
															</div>
														</div>
														
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Support Coverage Region</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="analystSupportCoverageRegion" id="analystSupportCoverageRegion" style="height: 53px; width: 227px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Analyst Support Coverage Time</label>
															<div class="controls" style="margin-left: 175px;">
																<select name="analystSupportCoverageTime" id="analystSupportCoverageTime" style="height: 53px; width: 227px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															     
																	<c:forEach var="supports" items="${supports}">
							    										<option value="${supports.support_id}">${supports.name}</option>
							 										</c:forEach>
														 		</select> 
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Existing User Base</label>
															<div class="controls" style="margin-left: 175px;">
																<input type="text" id="existingUserBase" placeholder="Existing User Base" name="existingUserBase" class="m-wrap largevaltradingapp">
															</div>
														</div>
													</div>
													
													</div>
													<div><br/></div>
												 <div class="Row">
													  <div class="ColumnCommontradingapp" id="researchreportprovidermulticommonarea">
													  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
													  <div><br/></div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Research Sub Area</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analyst Year of Exp</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analyst Awards</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Cost Range</label>
														</div>
														</div>
														<div class="ColumnCommontradingappinterestratemarket" id="macroeconomicanalysis" >
														<div  style="margin: -9px -11px 12px 14px;">Commodity Analysis</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="commodityAnalysisResearchSubArea" multiple="multiple" id="commodityAnalysisResearchSubArea" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
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
															</div>
															<div class="control-group">
																<select name="commodityAnalysisAnalystYearofExp" id="commodityAnalysisAnalystYearofExp" style="height: 53px;">
														     	    <option value ="" class="selectvalues"> -SELECT- </option>
															         <option value="Less than 1">Less than 1</option>
																     <option value="1-3">1-3</option>
																     <option value="3-5">3-5</option>
																     <option value="5-10">5-10</option>
																     <option value="10-15">10-15</option>
																     <option value="15-25">15-25</option>
																     <option value="25-35">25-35</option>
																     <option value="35-50">35-50</option>
																     <option value="More than 50">More than 50</option>
															</select>
															    
														 	 </div>
														 	 <div class="control-group">
																<select name="commodityAnalysisAnalystAwards" multiple="multiple" id="commodityAnalysisAnalystAwards" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															       <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="commodityAnalysisCostRange" id="commodityAnalysisCostRange" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															    </select> 
														    </div>
														</div>
														<div class="ColumnCommontradingappinterestrateetfmarket" id="sectorindustryanalysis">
														<div  style="margin: -9px -11px 12px 14px;">Exchange Rate Analysis</div>
														<div><br/></div>
														  
														  <div class="control-group">
																<select name="exchangeRateAnalysisResearchSubArea" multiple="multiple" id="commodityAnalysisResearchSubArea" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
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
															</div>
															<div class="control-group">
																<select name="exchangeRateAnalysisAnalystYearofExp" id="commodityAnalysisAnalystYearofExp" style="height: 53px;">
														     	    <option value ="" class="selectvalues"> -SELECT- </option>
															         <option value="Less than 1">Less than 1</option>
																     <option value="1-3">1-3</option>
																     <option value="3-5">3-5</option>
																     <option value="5-10">5-10</option>
																     <option value="10-15">10-15</option>
																     <option value="15-25">15-25</option>
																     <option value="25-35">25-35</option>
																     <option value="35-50">35-50</option>
																     <option value="More than 50">More than 50</option>
															</select>
															    
														 	 </div>
														 	 <div class="control-group">
																<select name="exchangeRateAnalysisAnalystAwards" multiple="multiple" id="commodityAnalysisAnalystAwards" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															       <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="exchangeRateAnalysisCostRange" id="commodityAnalysisCostRange" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															    </select> 
														    </div>
														  
														 </div>
														 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="commodityanalysis">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Commodity Analysis</div>
														<div><br/></div>
														  <div class="control-group">
																<select name="exchangeRateAnalysisResearchSubArea" multiple="multiple" id="commodityAnalysisResearchSubArea" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
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
															</div>
															<div class="control-group">
																<select name="exchangeRateAnalysisAnalystYearofExp" id="commodityAnalysisAnalystYearofExp" style="height: 53px;">
														     	    <option value ="" class="selectvalues"> -SELECT- </option>
															         <option value="Less than 1">Less than 1</option>
																     <option value="1-3">1-3</option>
																     <option value="3-5">3-5</option>
																     <option value="5-10">5-10</option>
																     <option value="10-15">10-15</option>
																     <option value="15-25">15-25</option>
																     <option value="25-35">25-35</option>
																     <option value="35-50">35-50</option>
																     <option value="More than 50">More than 50</option>
															</select>
															    
														 	 </div>
														 	 <div class="control-group">
																<select name="exchangeRateAnalysisAnalystAwards" multiple="multiple" id="commodityAnalysisAnalystAwards" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															       <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="exchangeRateAnalysisCostRange" id="commodityAnalysisCostRange" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															    </select> 
														    </div>
														 </div>
														 <div class="ColumnCommontradingappinterestrateetfxfmarket" id="exchangerateanalysis">
														<div style="margin: -9px -11px 12px 14px;">Exchange Analysis</div>
														<div><br/></div>
														  <div class="control-group">
																<select name="exchangeAnalysisResearchSubArea" multiple="multiple" id="exchangeAnalysisResearchSubArea" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
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
															</div>
															<div class="control-group">
																<select name="exchangeAnalysisAnalystYearofExp" id="exchangeAnalysisAnalystYearofExp" style="height: 53px;">
														     	    <option value ="" class="selectvalues"> -SELECT- </option>
															         <option value="Less than 1">Less than 1</option>
																     <option value="1-3">1-3</option>
																     <option value="3-5">3-5</option>
																     <option value="5-10">5-10</option>
																     <option value="10-15">10-15</option>
																     <option value="15-25">15-25</option>
																     <option value="25-35">25-35</option>
																     <option value="35-50">35-50</option>
																     <option value="More than 50">More than 50</option>
															</select>
															    
														 	 </div>
														 	 <div class="control-group">
																<select name="exchangeAnalysisAnalystAwards" multiple="multiple" id="exchangeAnalysisAnalystAwards" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															       <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="exchangeAnalysisAnalysisCostRange" id="exchangeAnalysisAnalysisCostRange" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															    </select> 
														    </div>
														 </div>
													</div>  
													<div><br/></div>
												 <div class="Row">
													  <div class="ColumnCommontradingapp" id="researchreportprovidermulticommonareainformation">
													  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
													  <div><br/></div>
														<div class="control-group"  style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Research Sub Area</label>
														</div>
														<div class="control-group"  style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analyst Year of Exp</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Analyst Awards</label>
														</div>
														<div class="control-group" style="height: 68px;">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Cost Range</label>
														</div>
														</div>
														<div class="ColumnCommontradingappinterestratemarket" id="interestrateanalysis" >
														<div style="margin: -9px -11px 12px 14px;">Debt Market research</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="debtMarketResearchResearchSubArea" multiple="multiple" id="debtMarketResearchResearchSubArea" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
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
															</div>
															<div class="control-group">
																<select name="debtMarketResearchAnalystYearofExp" id="debtMarketResearchAnalystYearofExp" style="height: 53px;">
														     	    <option value ="" class="selectvalues"> -SELECT- </option>
															         <option value="Less than 1">Less than 1</option>
																     <option value="1-3">1-3</option>
																     <option value="3-5">3-5</option>
																     <option value="5-10">5-10</option>
																     <option value="10-15">10-15</option>
																     <option value="15-25">15-25</option>
																     <option value="25-35">25-35</option>
																     <option value="35-50">35-50</option>
																     <option value="More than 50">More than 50</option>
															</select>
															    
														 	 </div>
														 	 <div class="control-group">
																<select name="debtMarketResearchAnalystAwards" multiple="multiple" id="debtMarketResearchAnalystAwards" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															       <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="debtMarketResearchCostRange" id="debtMarketResearchCostRange" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															    </select> 
														    </div>
														</div>
														<div class="ColumnCommontradingappinterestrateetfmarket" id="indexETFfundresearch">
														<div   style="margin: -9px -11px 12px 14px;">Fund/ETF research</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="fundETFresearchResearchSubArea" multiple="multiple" id="fundETFresearchAnalystAwards" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
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
															</div>
															<div class="control-group">
																<select name="fundETFresearchAnalystYearofExp" id="fundETFresearchAnalystAwards" style="height: 53px;">
														     	    <option value ="" class="selectvalues"> -SELECT- </option>
															         <option value="Less than 1">Less than 1</option>
																     <option value="1-3">1-3</option>
																     <option value="3-5">3-5</option>
																     <option value="5-10">5-10</option>
																     <option value="10-15">10-15</option>
																     <option value="15-25">15-25</option>
																     <option value="25-35">25-35</option>
																     <option value="35-50">35-50</option>
																     <option value="More than 50">More than 50</option>
															</select>
															    
														 	 </div>
														 	 <div class="control-group">
																<select name="fund/ETFresearchAnalystAwards" multiple="multiple" id="fundETFresearchAnalystAwards" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															       <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="fund/ETFresearchCostRange" id="fund/ETFresearchCostRange" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															    </select> 
														    </div>
														 </div>
														 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="equitydebtresearch">
														<div class="lable_header"  style="margin: -9px -11px 12px 14px;">Equity Research</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="exchangeRateAnalysisResearchSubArea" multiple="multiple" id="commodityAnalysisResearchSubArea" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
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
															</div>
															<div class="control-group">
																<select name="exchangeRateAnalysisAnalystYearofExp" id="commodityAnalysisAnalystYearofExp" style="height: 53px;">
														     	    <option value ="" class="selectvalues"> -SELECT- </option>
															         <option value="Less than 1">Less than 1</option>
																     <option value="1-3">1-3</option>
																     <option value="3-5">3-5</option>
																     <option value="5-10">5-10</option>
																     <option value="10-15">10-15</option>
																     <option value="15-25">15-25</option>
																     <option value="25-35">25-35</option>
																     <option value="35-50">35-50</option>
																     <option value="More than 50">More than 50</option>
															</select>
															    
														 	 </div>
														 	 <div class="control-group">
																<select name="exchangeRateAnalysisAnalystAwards" multiple="multiple" id="commodityAnalysisAnalystAwards" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
															       <c:forEach var="awards" items="${awards}">
																     <option value="${awards.name}">${awards.name}</option>
																 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="exchangeRateAnalysisCostRange" id="commodityAnalysisCostRange" style="height: 53px;">
														     	<option value ="" class="selectvalues"> -SELECT- </option>
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
