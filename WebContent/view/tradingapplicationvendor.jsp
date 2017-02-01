<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld"%>
<!DOCTYPE html>
<head>
<title>Fin Vendor | Vendor</title>	
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
    	$(document).ready( function () {
    		getAssetClassList();
    		getSecurityTypeList();
    		getRegionList();
    		getCountryList();
    		getExchangeList();
    	});
    </script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
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
		<div id="searchmultiform">
			<div id="trading_application_search" class="consumer_search custom_form">
				<div class="generic_message">
					<div class="alert"></div>
				</div>
				<div class="asset_class">
					<h3>Asset Class <span class="fa fa-chevron-up"></span></h3>
					<ul>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Equities"
							id="equities" />
							<label>Equities</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Derivatives"
							id="derivatives" />
							<label>Derivatives</label>
						</li>
						<li>
							<input type="checkbox" class="assetClass"
							name="assetClassChk" value="FI" id="fi" />
							<label>Fixed Income</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="FX" id="fx" />
							<label>FX</label>
						</li>
					</ul>
				</div>

				<div class="common_fields_for_asset_class">
					<h3>Common Fields for all asset class <span class="fa fa-chevron-up"></span></h3>
					<ul>
						<li>
							<select class="selectpicker select_multiple" name="tcsTradingCapabilitiesType" multiple="multiple" id="tcsTradingCapabilitiesType">
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
							<label class="default_select">Trading Capability Type</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="tdsOrderType"  multiple="multiple" id="tdsOrderType">
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
							<label class="default_select">Order Type</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="tdsAccessibility"  multiple="multiple" id="tdsAccessibility">
								<option value ="Web/Browser Based"> Web/Browser Based </option>
								<option value ="Binaries(Executable) Based"> Binaries(Executable) Based </option>
								<option value ="Dedicated Desktop">Dedicated Desktop</option>
								<option value ="Mobile/Tablet Apps">Mobile/Tablet Apps</option>
							</select>
							<label class="default_select">Accessibility (?)</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="tdsSuitability1"  multiple="multiple" id="tdsSuitability1">
								<option value ="All Users"> All Users </option>
								<option value ="Advanced shares traders"> Advanced shares traders </option>
								<option value ="Technical chart users"> Technical chart users </option>
								<option value ="Forex specialists"> Forex specialists </option>
								<option value ="Other"> Other </option>
							</select>
							<label class="default_select">Suitability</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="tcsDarkpoolAccess" multiple="multiple" id="tcsDarkpoolAccess">
								<option value ="Access to independent Darkpools"> Access to independent Darkpools (?) </option>
								<option value ="Access to Broker-dealer Darkpools"> Access to Broker-dealer Darkpools (?) </option>
								<option value ="Access to Consortium-owned darkpools"> Access to Consortium-owned darkpools (?) </option>
								<option value ="Access to Exchange-owned darkpools"> Access to Exchange-owned darkpools (?) </option>
								<option value ="Access to Darkpool Aggregators"> Access to Darkpool Aggregators(?) </option>
								<option value ="No Darkpool Access">No Darkpool Access </option>
							</select>
							<label class="default_select">Darkpool Access</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="vendorregionofincorp" id="vendorregionofincorp">
								<c:forEach var="regionslist" items="${regionslist}">
									<option value="${regionslist.region_id}">${regionslist.name}</option>
								</c:forEach>
							</select>
							<label class="default_select">Region of Incorporation</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="vendorcountryofincorp" id="vendorcountryofincorp" multiple="multiple">
								<c:forEach var="countries" items="${countries}">
									<option value="${countries.country_id}">${countries.name}</option>
								</c:forEach>
							</select>
							<label class="default_select">Country of Incorporation</label>
						</li>
						<li>
							<select class="selectpicker show-tick" id="vendorprofilefreshness" name="vendorprofilefreshness">
								<option selected="selected" value="" class="selectvalues">
									ANY</option>
								<%--
								<option>Today</option>
								<option>Last One Week</option>
								<option>Last One Month</option>
								<option>Last One Year</option>
								--%>
							</select>
							<label class="default_select">Profile Freshness</label>
						</li>
						<li>
							<select class="selectpicker show-tick" id="vendorsupporttime" name="vendorsupporttime">
								<option selected="selected" value="" class="selectvalues">
									ANY</option>
								<%--
								<c:forEach var="supports" items="${supports}">
									<option value="${supports.support_id}">${supports.name}</option>
								</c:forEach>
								--%>
							</select>
							<label class="default_select">Support Coverage Time</label>
						</li>
					</ul>
				</div>
				<div class="multisearch" id="singleAsset">
					<div id="singleAssetFields"></div>
					<script type="text/javascript">
						
					</script>
				</div>
				<div class="multisearch" id="multipleAsset">
					<script type="text/javascript">
						var multipleAssetData = '';
						
					</script>
					<div id="multipleAssetFields"></div>
				</div>

				<div class="form-actions clearfix">
					<div class="se">
						<input type="submit" value="Search" class="btn" id="search_vendor" />
						<input type="reset" value="Reset" class="btn" id="reset_vendor" />
					</div>
				</div>
			</div>
			<form
			action="<%=request.getContextPath()+"/"+RequestConstans.TradingApplication.MULTI_ASSET_CLASS_SEARCH_RESULT %>?RaYvEmUl=${l:encrypt(username)}"
			class="form-horizontal" id="submit_form" method="post"
			enctype="multipart/form-data">
			</form>
		</div>
	</div>
	<!-- END CONTAINER -->
	<jsp:include page="common/footer.jsp"></jsp:include>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
</body>
</html>