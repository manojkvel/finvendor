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
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp"></jsp:include>
	<div class="container">
		<div class="text_area">
			<div class="text_arw">
				<a
					href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}">
					Research Report (RR) Providers</a>
			</div>
			<div class="arw">></div>
			<div class="text_arw">Search Vendor</div>
		</div>

		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid service-box">
					<div class="row-fluid">
						<div class="span12">
							<div class="portlet box blue" id="form_wizard_1">
								<div class="portlet-title"></div>
								<div class="portlet-body form">
									<!-- Multi Trading Vendor Start here -->
									<div id="searchmultiform">
										<form
											action="<%=request.getContextPath()+"/"+RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>?RaYvEmUl=${l:encrypt(username)}"
											class="form-horizontal" id="submit_form" method="post"
											enctype="multipart/form-data">

											<div class="form-wizard">
												<div class="tab-content" style="background-color: white;">
													<div class="tab-pane active">
														<div class="ColumnCommonvendor">
															<div class="control-group">
																<label class="control-label">Research Area<span
																	class="required">*</span></label>
															</div>
														</div>
														<div class="Row" id="asset-class-type">
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Commodity
																		Analysis&nbsp; <span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="commodity_analysis" value="Commodity Analysis"
																		id="commodity_analysis" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Exchange
																		Rate Analysis<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="exchange_rate_analysis"
																		value="Exchange Rate Analysis"
																		id="exchange_rate_analysis" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Interest
																		Rate Analysis<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="interest_rate_analysis"
																		value="Interest Rate Analysis"
																		id="interest_rate_analysis" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Equity
																		research<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="equity_research" value="Equity research"
																		id="equity_research" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Debt
																		Market research<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="debt_market_research"
																		value="Debt Market research" id="debt_market_research" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Index
																		research<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="index_research" value="Index research"
																		id="index_research" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Fund/ETF
																		research<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="fund_etf_research" value="Fund/ETF research"
																		id="fund_etf_research" />
																</div>
															</div>
														</div>
														<div class="ColumnCommonvendor">
															<div class="control-group">
																<label class="control-label">Common fields for
																	all asset class<span class="required">*</span>
																</label>
															</div>
														</div>
														<div class="Row" id="common-vendor-type">
															<div class="control-group">
																<label>Regions Covered<span class="required">*</span></label>
																<select name="regions_covered" id="regions_covered">
																	<option value="-SELECT-" class="selectvalues">
																		-SELECT-</option>
																	<c:forEach var="regions" items="${regions}">
																		<option value="${regions.name}">${regions.name}</option>
																	</c:forEach>

																</select>
															</div>
															<div class="control-group">
																<label>Analyst Profile Freshness<span
																	class="required">*</span></label> <select
																	name="analyst_profile_freshness"
																	id="analyst_profile_freshness">
																	<option selected="selected" value=""
																		class="selectvalues">-SELECT-</option>
																	<option>Today</option>
																	<option>Last One Week</option>
																	<option>Last One Month</option>
																	<option>Last One Year</option>
																</select>
															</div>

															<div class="control-group">
																<label>Analyst Support Coverage Region<span
																	class="required">*</span></label> <select
																	name="analyst_support_coverage_region"
																	id="analyst_support_coverage_region">
																	<option value="-SELECT-" class="selectvalues"
																		selected="selected">-SELECT-</option>
																	<c:forEach var="regions" items="${regions}">
																		<option value="${regions.name}">${regions.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Analyst Country of Incorporation<span
																	class="required">*</span></label> <select
																	name="analyst_country_of_incorporation"
																	multiple="multiple"
																	id="analyst_country_of_incorporation">
																	<option value="" class="selectvalues"
																		selected="selected">-SELECT-</option>
																	<c:forEach var="countries" items="${countries}">
																		<option value="${countries.name}">${countries.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Analyst Support Coverage Time<span
																	class="required">*</span></label> <select
																	name="analyst_support_coverage_time"
																	id="analyst_support_coverage_time">
																	<option selected="selected" value=""
																		class="selectvalues">-SELECT-</option>
																	<c:forEach var="supports" items="${supports}">
																		<option value="${supports.support_id}">${supports.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Existing User Base<span class="required">*</span></label>
																<input type="text" id="existing_user_base"
																	placeholder="Existing User Base"
																	name="existing_user_base"
																	class="m-wrap largevaltradingapp">
															</div>
															<div class="control-group" style="margin-top: 10px;">
																<label style="float: left;">Research Analyst
																	with CFA Charter?<span class="required">*</span>
																</label> <input type="checkbox"
																	id="research_analyst_with_cfa_charter"
																	placeholder="Research Analyst with CFA"
																	name="research_analyst_with_cfa_charter"
																	style="width: 120px !important; margin: 0;">
															</div>
														</div>
														<div>
															<br />
														</div>
														<div class="multisearch" id="singleAsset">
															<div id="singleAssetFields"></div>
															<script type="text/javascript">
					function singleAssetClass(assetType) {
						console.log("assetType : " + assetType);
						$('#singleAssetFields').html('');
						var singleAssetData = "<div class='lable_header'>"
								+ $("#" + assetType).siblings().html()
								+ "</div>"
								+ "<div class='Row'>"
								+ "<div class='ColumnCommontradingappinterestratemarket'>"
								+ "<div class='control-group'>"
								+ "<label>Research Sub Area</label>"
								+ "<select name='" + assetType + "_research_sub_area' multiple='multiple' id='" + assetType + "'_research_sub_area'>"
								+ "<option value ='' class='selectvalues' selected> -SELECT- </option>"
								+ "<option value ='Economic Data analysis'>Economic Data analysis</option>"
								+ "<option value ='Country Economy Analysis'>Country Economy Analysis</option>"
								+ "<option value ='World economy Analysis'>World economy Analysis</option>"
								+ "<option value ='Oil & Gas'>Oil & Gas</option>"
								+ "<option value ='Basic Materials'>Basic Materials</option>"
								+ "<option value ='Industrials'>Industrials</option>"
								+ "<option value ='Consumer Goods'>Consumer Goods</option>"
								+ "<option value ='Health Care'>Health Care</option>"
								+ "<option value ='Consumer Services'>Consumer Services</option>"
								+ "<option value ='Telecommunications'>Telecommunications</option>"
								+ "<option value ='Utilities'>Utilities</option>"
								+ "<option value ='Financials'>Financials</option>"
								+ "<option value ='Technology'>Technology</option>"
								+ "<option value ='Agriculture and allied'>Agriculture and allied</option>"
								+ "<option value ='Mining'>Mining</option>"
								+ "<option value ='Utilities'>Utilities</option>"
								+ "<option value ='Construction'>Construction</option>"
								+ "<option value ='Manufacturing'>Manufacturing</option>"
								+ "<option value ='Wholesale Trade'>Wholesale Trade</option>"
								+ "<option value ='Retail Trade'>Retail Trade</option>"
																	+ "<option value ='Transportation and warehousing' >Transportation and warehousing</option>"
																	+ "<option value ='Information' >Information</option>"
																	+ "<option value ='Finance and Insurance' >Finance and Insurance</option>"
																	+ "<option value ='Real Estate and Rental/Leasing' >Real Estate and Rental/Leasing</option>"
																	+ "<option value ='Professional, Scientific & Technical Services' >Professional, Scientific & Technical Services</option>"
																	+ "<option value ='Education Services' >Education Services</option>"
																	+ "<option value ='Healthcare & Social Assistance' >Healthcare & Social Assistance</option>"
																	+ "<option value ='Arts, Entertainment & Recreation' >Arts, Entertainment & Recreation</option>"
																	+ "<option value ='Accomodation & Food services' >Accomodation & Food services</option>"
																	+ "<option value ='Other Services' >Other Services</option>"
																	+ "<option value ='Energy' >Energy</option>"
																	+ "<option value ='Industrial Metals' >Industrial Metals</option>"
																	+ "<option value ='Precious Metals' >Precious Metals</option>"
																	+ "<option value ='Agricultural Commodities' >Agricultural Commodities</option>"
																	+ "<option value ='European Euro to US Dollar EUR/USD' >European Euro to US Dollar EUR/USD</option>"
																	+ "<option value ='British Pound to US Dollar GBP/USD' >British Pound to US Dollar GBP/USD</option>"
																	+ "<option value ='Swiss Franc to US Dollar USD/CHF' >Swiss Franc to US Dollar USD/CHF</option>"
																	+ "<option value ='Japanese Yen to US Dollar USD/JPY' >Japanese Yen to US Dollar USD/JPY</option>"
																	+ "<option value ='Chinese Yuan to US Dollar USD/CNY' >Chinese Yuan to US Dollar USD/CNY</option>"
																	+ "<option value ='Taiwan Dollar to US Dollar USD/TWD' >Taiwan Dollar to US Dollar USD/TWD</option>"
																	+ "<option value ='South Korean Won to US Dollar USD/KRW' >South Korean Won to US Dollar USD/KRW</option>"
																	+ "<option value ='Australian Dollar to US Dollar AUD/USD' >Australian Dollar to US Dollar AUD/USD</option>"
																	+ "<option value ='Singapore Dollar to US Dollar USD/SGD' >Singapore Dollar to US Dollar USD/SGD</option>"
																	+ "<option value ='India Rupee to US Dollar USD/INR' >India Rupee to US Dollar USD/INR</option>"
																	+ "<option value ='New Zealand Dollar to US Dollar NZD/USD' >New Zealand Dollar to US Dollar NZD/USD</option>"
																	+ "<option value ='Canadian Dollar to US Dollar USD/CAD' >Canadian Dollar to US Dollar USD/CAD</option>"
																	+ "<option value ='Mexican Peso to US Dollar USD/MXN' >Mexican Peso to US Dollar USD/MXN</option>"
																	+ "<option value ='Govt Bond rate' >Govt Bond rate</option>"
																	+ "<option value ='Prime Lending Rate' >Prime Lending Rate</option>"
																	+ "<option value ='Investment Grade Spread' >Investment Grade Spread</option>"
																	+ "<option value ='High Yield Spread' >High Yield Spread</option>"
																	+ "<option value ='Govt Bond rate' >Govt Bond rate</option>"
																	+ "<option value ='Prime Lending Rate' >Prime Lending Rate</option>"
																	+ "<option value ='Investment Grade Spread' >Investment Grade Spread</option>"
																	+ "<option value ='High Yield Spread' >High Yield Spread</option>"
																	+ "<option value ='Govt Bond rate' >Govt Bond rate</option>"
																	+ "<option value ='Prime Lending Rate' >Prime Lending Rate</option>"
																	+ "<option value ='Investment Grade Spread' >Investment Grade Spread</option>"
																	+ "<option value ='High Yield Spread' >High Yield Spread</option>"
																	+ "<option value ='LIBOR Rate' >LIBOR Rate</option>"
														 		+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Analyst Year of Exp</label>"
								+ "<select name='" + assetType + "_analyst_year_of_exp' id='" + assetType + "_analyst_year_of_exp' style='height: auto !important;'>"
								+ "<option value='' class='selectvalues'> -SELECT- </option>"
								+ "<option value='Less than 1'>Less than 1</option>"
								+ "<option value='1-3'>1-3</option>"
								+ "<option value='3-5'>3-5</option>"
								+ "<option value='5-10'>5-10</option>"
								+ "<option value='10-15'>10-15</option>"
								+ "<option value='15-25'>15-25</option>"
								+ "<option value='25-35'>25-35</option>"
								+ "<option value='35-50'>35-50</option>"
								+ "<option value='More than 50'>More than 50</option>"
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Analyst Awards</label>"
								+ "<select name='" + assetType + "_awards' multiple='multiple' id='" + assetType + "_awards' style='height: 53px;'>"
								+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
								+ "<c:forEach var='awards' items='${awards}'>"
								+ "<option value='${awards.award_id}'>${awards.name}</option>"
								+ "</c:forEach>"
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Cost Range</label>"
								+ "<select name='" + assetType + "_acquisition_cost_range' multiple='multiple' id='" + assetType + "_acquisition_cost_range' style='height: 53px;'>"
								+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
								+ "<c:forEach var='costs' items='${costs}'>"
								+ "<option value='${costs.range}'>${costs.range}</option>"
								+ "</c:forEach>"
								+ "</select>"
								+ "</div>" + "</div>" + "</div>";
						$('#singleAssetFields').append(singleAssetData);
					}
				</script>
														</div>


														<div class="multisearch" id="multipleAsset">
															<div class="Row">
																<div class="ColumnCommontradingapp" id="commonarea">
																	<div class="control-group">
																		<label>Research Sub Area</label>
																	</div>
																	<div class="control-group"
																		style='height: auto !important;'>
																		<label>Analyst Year of Exp</label>
																	</div>
																	<div class="control-group">
																		<label>Analyst Awards</label>
																	</div>
																	<div class="control-group">
																		<label>Cost Range</label>
																	</div>
																</div>
																<script type="text/javascript">
						var multipleAssetData = '';
						function multipleAssetClass(assetType) {
							console.log("assetType : " + assetType);
							$('#multipleAsset #multipleAssetFields').html('');
							multipleAssetData = multipleAssetData
									+ "<div class='ColumnCommontradingappinterestratemarket'>"
									+ "<div class='lable_header'>"
									+ $("#" + assetType).siblings().html()
									+ "</div>"
									+ "<div class='control-group'>"
								+ "<select name='" + assetType + "_research_sub_area' multiple='multiple' id='" + assetType + "'_research_sub_area'>"
								+ "<option value ='' class='selectvalues' selected> -SELECT- </option>"
								+ "<option value ='Economic Data analysis'>Economic Data analysis</option>"
								+ "<option value ='Country Economy Analysis'>Country Economy Analysis</option>"
								+ "<option value ='World economy Analysis'>World economy Analysis</option>"
								+ "<option value ='Oil & Gas'>Oil & Gas</option>"
								+ "<option value ='Basic Materials'>Basic Materials</option>"
								+ "<option value ='Industrials'>Industrials</option>"
								+ "<option value ='Consumer Goods'>Consumer Goods</option>"
								+ "<option value ='Health Care'>Health Care</option>"
								+ "<option value ='Consumer Services'>Consumer Services</option>"
								+ "<option value ='Telecommunications'>Telecommunications</option>"
								+ "<option value ='Utilities'>Utilities</option>"
								+ "<option value ='Financials'>Financials</option>"
								+ "<option value ='Technology'>Technology</option>"
								+ "<option value ='Agriculture and allied'>Agriculture and allied</option>"
								+ "<option value ='Mining'>Mining</option>"
								+ "<option value ='Utilities'>Utilities</option>"
								+ "<option value ='Construction'>Construction</option>"
								+ "<option value ='Manufacturing'>Manufacturing</option>"
								+ "<option value ='Wholesale Trade'>Wholesale Trade</option>"
								+ "<option value ='Retail Trade'>Retail Trade</option>"
																	+ "<option value ='Transportation and warehousing' >Transportation and warehousing</option>"
																	+ "<option value ='Information' >Information</option>"
																	+ "<option value ='Finance and Insurance' >Finance and Insurance</option>"
																	+ "<option value ='Real Estate and Rental/Leasing' >Real Estate and Rental/Leasing</option>"
																	+ "<option value ='Professional, Scientific & Technical Services' >Professional, Scientific & Technical Services</option>"
																	+ "<option value ='Education Services' >Education Services</option>"
																	+ "<option value ='Healthcare & Social Assistance' >Healthcare & Social Assistance</option>"
																	+ "<option value ='Arts, Entertainment & Recreation' >Arts, Entertainment & Recreation</option>"
																	+ "<option value ='Accomodation & Food services' >Accomodation & Food services</option>"
																	+ "<option value ='Other Services' >Other Services</option>"
																	+ "<option value ='Energy' >Energy</option>"
																	+ "<option value ='Industrial Metals' >Industrial Metals</option>"
																	+ "<option value ='Precious Metals' >Precious Metals</option>"
																	+ "<option value ='Agricultural Commodities' >Agricultural Commodities</option>"
																	+ "<option value ='European Euro to US Dollar EUR/USD' >European Euro to US Dollar EUR/USD</option>"
																	+ "<option value ='British Pound to US Dollar GBP/USD' >British Pound to US Dollar GBP/USD</option>"
																	+ "<option value ='Swiss Franc to US Dollar USD/CHF' >Swiss Franc to US Dollar USD/CHF</option>"
																	+ "<option value ='Japanese Yen to US Dollar USD/JPY' >Japanese Yen to US Dollar USD/JPY</option>"
																	+ "<option value ='Chinese Yuan to US Dollar USD/CNY' >Chinese Yuan to US Dollar USD/CNY</option>"
																	+ "<option value ='Taiwan Dollar to US Dollar USD/TWD' >Taiwan Dollar to US Dollar USD/TWD</option>"
																	+ "<option value ='South Korean Won to US Dollar USD/KRW' >South Korean Won to US Dollar USD/KRW</option>"
																	+ "<option value ='Australian Dollar to US Dollar AUD/USD' >Australian Dollar to US Dollar AUD/USD</option>"
																	+ "<option value ='Singapore Dollar to US Dollar USD/SGD' >Singapore Dollar to US Dollar USD/SGD</option>"
																	+ "<option value ='India Rupee to US Dollar USD/INR' >India Rupee to US Dollar USD/INR</option>"
																	+ "<option value ='New Zealand Dollar to US Dollar NZD/USD' >New Zealand Dollar to US Dollar NZD/USD</option>"
																	+ "<option value ='Canadian Dollar to US Dollar USD/CAD' >Canadian Dollar to US Dollar USD/CAD</option>"
																	+ "<option value ='Mexican Peso to US Dollar USD/MXN' >Mexican Peso to US Dollar USD/MXN</option>"
																	+ "<option value ='Govt Bond rate' >Govt Bond rate</option>"
																	+ "<option value ='Prime Lending Rate' >Prime Lending Rate</option>"
																	+ "<option value ='Investment Grade Spread' >Investment Grade Spread</option>"
																	+ "<option value ='High Yield Spread' >High Yield Spread</option>"
																	+ "<option value ='Govt Bond rate' >Govt Bond rate</option>"
																	+ "<option value ='Prime Lending Rate' >Prime Lending Rate</option>"
																	+ "<option value ='Investment Grade Spread' >Investment Grade Spread</option>"
																	+ "<option value ='High Yield Spread' >High Yield Spread</option>"
																	+ "<option value ='Govt Bond rate' >Govt Bond rate</option>"
																	+ "<option value ='Prime Lending Rate' >Prime Lending Rate</option>"
																	+ "<option value ='Investment Grade Spread' >Investment Grade Spread</option>"
																	+ "<option value ='High Yield Spread' >High Yield Spread</option>"
																	+ "<option value ='LIBOR Rate' >LIBOR Rate</option>"
														 		+ "</select>"
									+ "</div>"
									+ "<div class='control-group' style='height: auto !important;'>"
									+ "<select name='" + assetType + "_analyst_year_of_exp' id='" + assetType + "_analyst_year_of_exp' style='height: auto !important;'>"
									+ "<option value='' class='selectvalues'> -SELECT- </option>"
									+ "<option value='Less than 1'>Less than 1</option>"
									+ "<option value='1-3'>1-3</option>"
									+ "<option value='3-5'>3-5</option>"
									+ "<option value='5-10'>5-10</option>"
									+ "<option value='10-15'>10-15</option>"
									+ "<option value='15-25'>15-25</option>"
									+ "<option value='25-35'>25-35</option>"
									+ "<option value='35-50'>35-50</option>"
									+ "<option value='More than 50'>More than 50</option>"
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "_awards' multiple='multiple' id='" + assetType + "_awards' style='height: 53px;'>"
									+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
									+ "<c:forEach var='awards' items='${awards}'>"
									+ "<option value='${awards.award_id}'>${awards.name}</option>"
									+ "</c:forEach>"
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "_acquisition_cost_range' multiple='multiple' id='" + assetType + "_acquisition_cost_range' style='height: 53px;'>"
									+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
									+ "<c:forEach var='costs' items='${costs}'>"
									+ "<option value='${costs.range}'>${costs.range}</option>"
									+ "</c:forEach>"
									+ "</select>"
									+ "</div>" + "</div>";
							$('#multipleAssetFields').append(multipleAssetData);
						}
					</script>
																<div id="multipleAssetFields"></div>
															</div>
														</div>
														<div>
															<br />
														</div>
														<div class="form-actions clearfix">
															<div class="se" style="padding-left: 150px;">
																<input type="submit" value="Search" class="btn"
																	id="search_vendor" /> <input type="reset"
																	value="Reset" class="btn" id="reset_vendor" />
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