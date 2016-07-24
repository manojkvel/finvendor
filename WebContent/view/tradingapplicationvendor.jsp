<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld"%>
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
					Trading Application (TA) Vendor</a>
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
											action="<%=request.getContextPath()+"/"+RequestConstans.TradingApplication.MULTI_ASSET_CLASS_SEARCH_RESULT %>?RaYvEmUl=${l:encrypt(username)}"
											class="form-horizontal" id="submit_form" method="post"
											enctype="multipart/form-data">

											<div class="form-wizard">
												<div class="tab-content" style="background-color: white;">
													<div class="tab-pane active">
														<div class="ColumnCommonvendor">
															<div class="control-group">
																<label class="control-label">Asset Class<span
																	class="required">*</span></label>
															</div>
														</div>
														<div class="Row" id="asset-class-type">
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"
																		style="font-family: Raleway, sans-serif; font-size: 13px;">Equities<span
																		class="required">*</span></label>&nbsp; <input type="checkbox"
																		class="assetClass" name="equities" value="Equities"
																		id="equities" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"
																		style="font-family: Raleway, sans-serif; font-size: 13px;">Derivatives<span
																		class="required">*</span></label>&nbsp; <input type="checkbox"
																		class="assetClass" name="derivatives"
																		value="Derivatives" id="derivatives" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"
																		style="font-family: Raleway, sans-serif; font-size: 13px;">Fixed
																		Income<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="fi" value="FI" id="fi" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"
																		style="font-family: Raleway, sans-serif; font-size: 13px;">FX<span
																		class="required">*</span></label>&nbsp; <input type="checkbox"
																		class="assetClass" name="fx" value="FX" id="fx" />
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
																<label>Trading Capability Type<span
																	class="required">*</span></label> <select
																	name="trading_capability_type"
																	id="trading_capability_type">
																	<option value="">-Select-</option>
																	<option value="Order placement/execution">
																		Order placement/execution</option>
																	<option value="Order allocation">Order
																		allocation</option>
																	<option value="Trade settlement">Trade
																		settlement</option>
																	<option value="Trade Clearance">Trade
																		Clearance</option>
																	<option value="Real Time Quotes">Real Time
																		Quotes</option>
																	<option value="Real Time Financial News">Real
																		Time Financial News</option>
																	<option value="Trade Analytics">Trade
																		Analytics</option>
																	<option value="Collateral Management">
																		Collateral Management</option>
																	<option value="Listed stocks/contracts">
																		Listed stocks/contracts</option>
																	<option value="Electronic Trading for OTC">
																		Electronic Trading for OTC</option>
																	<option value="Order Management">Order
																		Management</option>
																	<option value="Trade Confirmation">Trade
																		Confirmation</option>
																	<option value="Trade Reporting">Trade
																		Reporting</option>
																	<option value="Trading Strategy Builder">
																		Trading Strategy Builder</option>
																	<option value="Liquidity Aggregation">
																		Liquidity Aggregation</option>
																	<option value="Market Surveillance & Compliance">
																		Market Surveillance & Compliance</option>
																	<option
																		value="Ability to connect to liquidity providers">
																		Ability to connect to liquidity providers</option>
																	<option value="Algorithmic Signal Generation">
																		Algorithmic Signal Generation</option>
																	<option value="Algorithmic Order Execution& Mgmt">
																		Algorithmic Order Execution& Mgmt</option>
																	<option value="Auto Hedging & Risk Mgmt">Auto
																		Hedging & Risk Mgmt</option>
																	<option value="Time Series Modelling">Time
																		Series Modelling</option>
																	<option value="Market Data Distribution">
																		Market Data Distribution</option>
																	<option value="Pricing and Rates Engine">
																		Pricing and Rates Engine</option>
																	<option value="Quoting & Price Distribution">
																		Quoting & Price Distribution</option>

																</select>
															</div>
															<div class="control-group">
																<label>Order Type<span class="required">*</span></label>
																<select name="order_type" id="order_type">
																	<option value="">-Select-</option>
																	<option value="Limits">Limits</option>
																	<option value="market orders">market orders</option>
																	<option value="tranche (iceberg) orders">
																		tranche (iceberg) orders</option>
																	<option value="fill or kill">fill or kill</option>
																	<option value="execute and eliminate">execute
																		and eliminate</option>
																	<option value="date">date</option>
																	<option value="day">day</option>
																	<option value="good for auction">good for
																		auction</option>
																	<option value="Good till cancel">Good till
																		cancel</option>
																	<option value="Good till cancel">Good till
																		cancel</option>
																	<option value="At the auction">At the auction</option>
																	<option value="At the open">At the open</option>
																	<option value="At the close">At the close</option>
																	<option value="At best">At best</option>
																	<option value="Trailing stops">Trailing stops</option>
																	<option value="Market if touched">Market if
																		touched</option>
																	<option value="One cancels the other">One
																		cancels the other</option>
																	<option value="One enables the others">One
																		enables the others</option>
																	<option value="Stop-loss">Stop-loss</option>
																	<option value="Take profit">Take profit</option>

																</select>
															</div>
															<div class="control-group">
																<label>Vendor Profile Freshness<span
																	class="required">*</span></label> <select
																	name="vendor_profile_freshness"
																	id="vendor_profile_freshness">
																	<option value="" class="selectvalues">
																		-SELECT-</option>
																	<option>today</option>
																	<option>Last one week</option>
																	<option>Last one month</option>
																	<option>Last one year</option>
																</select>
															</div>
															<div class="control-group">
																<label>Accessibility<span class="required">*</span></label>
																<select name="accessibility" multiple="multiple"
																	id="accessibility">
																	<option value="" class="selectvalues">
																		-SELECT-</option>
																	<option value="Web Browser Based">Web Browser
																		Based</option>
																	<option value="Binaries(Executable) Based">
																		Binaries(Executable) Based</option>
																	<option value="Dedicated Desktop">Dedicated
																		Desktop</option>
																	<option value="Mobile/Tablet Apps">Mobile/Tablet
																		Apps</option>

																</select>
															</div>
															<div class="control-group">
																<label>Suitability<span class="required">*</span></label>
																<select name="suitability" multiple="multiple">
																	<option value="-SELECT-" class="selectvalues">
																		-SELECT-</option>
																	<option value="All Users">All Users</option>
																	<option value="Advanced shares traders">
																		Advanced shares traders</option>
																	<option value="Technical chart users">
																		Technical chart users</option>
																	<option value="Forex specialists">Forex
																		specialists</option>
																	<option value="Other">Other</option>
																</select>
															</div>
															<div class="control-group">
																<label>Darkpool Access<span class="required">*</span></label>
																<select name="dark_pool_access" multiple="multiple">
																	<option value="">-SELECT-</option>
																	<option value="Access to independent Darkpools">
																		Access to independent Darkpools (?)</option>
																	<option value="Access to Broker-dealer Darkpools">
																		Access to Broker-dealer Darkpools (?)</option>
																	<option value="Access to Consortium-owned darkpools">
																		Access to Consortium-owned darkpools (?)</option>
																	<option value="Access to Exchange-owned darkpools">
																		Access to Exchange-owned darkpools (?)</option>
																	<option value="Access to Darkpool Aggregators">
																		Access to Darkpool Aggregators(?)</option>
																	<option value="No Darkpool Access">No Darkpool
																		Access</option>

																</select>
															</div>
															<div class="control-group">
																<label>Coverage Country<span class="required">*</span></label>
																<select name="coverage_country" multiple="multiple"
																	id="coverage_country">
																	<option value="-SELECT-" class="selectvalues">
																		-SELECT-</option>
																	<c:forEach var="countries" items="${countries}">
																		<option value="${countries.name}">${countries.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Coverage Region<span class="required">*</span></label>
																<select name="coverage_region" id="coverage_region">
																	<option value="" class="selectvalues">
																		-SELECT-</option>
																	<c:forEach var="regions" items="${regions}">
																		<option value="${regions.name}">${regions.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Vendor Support Coverage Time<span
																	class="required">*</span></label> <select
																	name="vendor_support_coverage_time"
																	id="vendor_support_coverage_time">
																	<option value="" class="selectvalues">
																		-SELECT-</option>
																	<c:forEach var="supports" items="${supports}">
																		<option value="${supports.support_id}">${supports.name}</option>
																	</c:forEach>

																</select>
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
																			+ "<label>Trade Asset Sub Class</label>"
																			+ "<select name='" + assetType + "_trade_asset_sub_class' multiple='multiple' id='" + assetType + "_trade_asset_sub_class'>"
																			+ "<option selected='selected' value =''> -SELECT- </option>"
																			+ "<c:forEach var='securityType' items='${securityTypes}'>"
																			+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
																			+ "</c:forEach>"
																			+ "</select>"
																			+ "</div>"
																			+ "<div class='control-group'>"
																			+ "<label>Data Coverage Region</label>"
																			+ "<select name='" + assetType + "_data_coverage_region' multiple='multiple' id='" + assetType + "_data_coverage_region'>"
																			+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
																			+ "<c:forEach var='regions' items='${regions}'>"
																			+ "<option value='${regions.name}'>${regions.name}</option>"
																			+ "</c:forEach>"
																			+ "</select>"
																			+ "</div>"
																			+ "<div class='control-group'>"
																			+ "<label>Data Coverage Country</label>"
																			+ "<select name='" + assetType + "_data_coverage_country' multiple='multiple' id='" + assetType + "_data_coverage_country'>"
																			+ "<option selected='selected' value =''> -SELECT- </option>"
																			+ "<c:forEach var='countries' items='${countries}'>"
																			+ "<option value='${countries.name}'>${countries.name}</option>"
																			+ "</c:forEach>"
																			+ "</select>"
																			+ "</div>"
																			+ "<div class='control-group'>"
																			+ "<label>Data Coverage Exchange</label>"
																			+ "<select name='" + assetType + "_data_coverage_exchange' multiple='multiple' id='" + assetType + "_data_coverage_exchange'>"
																			+ "<option value =''> -SELECT- </option>"
																			+ "<c:forEach var='exchanges' items='${exchanges}'>"
																			+ "<option value='${exchanges.name}'>${exchanges.name}</option>"
																			+ "</c:forEach>"
																			+ "</select>"
																			+ "</div>"
																			+ "<div class='control-group'>"
																			+ "<label>Vendor Year of Operation</label>"
																			+ "<select name='" + assetType + "_vendor_year_operation' multiple='multiple' id='" + assetType + "_vendor_year_operation'>"
																			+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
																			+ "<option>Date (1970)</option>"
																			+ "<option>Last one week</option>"
																			+ "<option>Last one month</option>"
																			+ "<option>Last one year</option>"
																			+ "</select>"
																			+ "</div>"
																			+ "<div class='control-group'>"
																			+ "<label>Awards</label>"
																			+ "<select name='" + assetType + "_awards' multiple='multiple' id='" + assetType + "_awards'>"
																			+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
																			+ "<c:forEach var='awards' items='${awards}'>"
																			+ "<option value='${awards.award_id}'>${awards.name}</option>"
																			+ "</c:forEach>"
																			+ "</select>"
																			+ "</div>"
																			+ "<div class='control-group'>"
																			+ "<label>Data Acquisition Cost Range</label>"
																			+ "<select name='" + assetType + "_acquisition_cost_range' multiple='multiple' id='" + assetType + "_acquisition_cost_range'>"
																			+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
																			+ "<c:forEach var='costs' items='${costs}'>"
																			+ "<option value='${costs.range}'>${costs.range}</option>"
																			+ "</c:forEach>"
																			+ "</select>"
																			+ "</div>"
																			+ "<div class='control-group'>"
																			+ "<label>Data Attribute</label>"
																			+ "<div class='controls' style='margin-left: 0;'>"
																			+ "<input type='text' id='" + assetType + "_data_attribute' placeholder='Data Attribute' name='" + assetType + "_data_attribute' class='m-wrap largevaltradingappmarketingaggregator' />"
																			+ "</div>"
																			+ "</div>"
																			+ "</div>"
																			+ "</div>";
																	$('#singleAssetFields').append(singleAssetData);
																}
															</script>
														</div>
														<div class="multisearch" id="multipleAsset">
															<div class="Row">
																<div class="ColumnCommontradingapp" id="commonarea">
																	<div class="control-group">
																		<label>Trade Asset Sub Class</label>
																	</div>
																	<div class="control-group">
																		<label>Data Coverage Region</label>
																	</div>
																	<div class="control-group">
																		<label>Data Coverage Country</label>
																	</div>
																	<div class="control-group">
																		<label>Data Coverage Exchange</label>
																	</div>
																	<div class="control-group">
																		<label>Vendor Year of Operation</label>
																	</div>
																	<div class="control-group">
																		<label>Awards</label>
																	</div>
																	<div class="control-group">
																		<label>Data Acquisition Cost Range</label>
																	</div>
																	<div class="control-group">
																		<label>Data Attribute</label>
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
																				+ "<select name='" + assetType + "_trade_asset_sub_class' multiple='multiple' id='" + assetType + "_trade_asset_sub_class'>"
																				+ "<option selected='selected' value =''> -SELECT- </option>"
																				+ "<c:forEach var='securityType' items='${securityTypes}'>"
																				+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
																				+ "</c:forEach>"
																				+ "</select>"
																				+ "</div>"
																				+ "<div class='control-group'>"
																				+ "<select name='" + assetType + "_data_coverage_region' multiple='multiple' id='" + assetType + "_data_coverage_region'>"
																				+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
																				+ "<c:forEach var='regions' items='${regions}'>"
																				+ "<option value='${regions.name}'>${regions.name}</option>"
																				+ "</c:forEach>"
																				+ "</select>"
																				+ "</div>"
																				+ "<div class='control-group'>"
																				+ "<select name='" + assetType + "_data_coverage_country' multiple='multiple' id='" + assetType + "_data_coverage_country'>"
																				+ "<option selected='selected' value =''> -SELECT- </option>"
																				+ "<c:forEach var='countries' items='${countries}'>"
																				+ "<option value='${countries.name}'>${countries.name}</option>"
																				+ "</c:forEach>"
																				+ "</select>"
																				+ "</div>"
																				+ "<div class='control-group'>"
																				+ "<select name='" + assetType + "_data_coverage_exchange' multiple='multiple' id='" + assetType + "_data_coverage_exchange'>"
																				+ "<option value =''> -SELECT- </option>"
																				+ "<c:forEach var='exchanges' items='${exchanges}'>"
																				+ "<option value='${exchanges.name}'>${exchanges.name}</option>"
																				+ "</c:forEach>"
																				+ "</select>"
																				+ "</div>"
																				+ "<div class='control-group'>"
																				+ "<select name='" + assetType + "_vendor_year_operation' multiple='multiple' id='" + assetType + "_vendor_year_operation'>"
																				+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
																				+ "<option>Date (1970)</option>"
																				+ "<option>Last one week</option>"
																				+ "<option>Last one month</option>"
																				+ "<option>Last one year</option>"
																				+ "</select>"
																				+ "</div>"
																				+ "<div class='control-group'>"
																				+ "<select name='" + assetType + "_awards' multiple='multiple' id='" + assetType + "_awards'>"
																				+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
																				+ "<c:forEach var='awards' items='${awards}'>"
																				+ "<option value='${awards.award_id}'>${awards.name}</option>"
																				+ "</c:forEach>"
																				+ "</select>"
																				+ "</div>"
																				+ "<div class='control-group'>"
																				+ "<select name='" + assetType + "_acquisition_cost_range' multiple='multiple' id='" + assetType + "_acquisition_cost_range'>"
																				+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
																				+ "<c:forEach var='costs' items='${costs}'>"
																				+ "<option value='${costs.range}'>${costs.range}</option>"
																				+ "</c:forEach>"
																				+ "</select>"
																				+ "</div>"
																				+ "<div class='control-group'>"
																				+ "<div class='controls' style='margin-left: 0;'>"
																				+ "<input type='text' id='" + assetType + "_data_attribute' placeholder='Data Attribute' name='" + assetType + "_data_attribute' class='m-wrap largevaltradingappmarketingaggregator' />"
																				+ "</div>"
																				+ "</div>"
																				+ "</div>";
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

								</div>
								<!-- Multi Trading Vendor Start here -->


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTAINER -->
	<jsp:include page="common/footer.jsp"></jsp:include>

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
	</script>
	<script src="//code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"
		type="text/javascript"></script>

	<!-- Date plugins start-->

</body>
</html>