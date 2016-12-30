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
	<title>Vendor - My Offerings</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
    <style type="text/css">
    	#tab2, #tab3, #tab4 {
    		display: none;
    	}
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script type="text/javascript">
    	window.onload = function () {	
    		changeTabMode();
    	}
    </script>
</head>
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container">
		<div id="vendor_my_offerings">
			<ul class="nav nav-tabs">
				<c:if test="${not empty dataaggregator}">
					<li>
						<a id="myofferings1" href="#tab1" data-toggle="tab" >
							Data Aggregator
						</a>
					</li>
				</c:if>
				<c:if test="${not empty tradingapplication}">
					<li>
						<a id="myofferings2" href="#tab2" data-toggle="tab">
							Trading Application
						</a>
					</li>
				</c:if>
				<c:if test="${not empty analyticsapplication}">
					<li>
						<a id="myofferings3" href="#tab3" data-toggle="tab" >
							Analytics Application
						</a>
					</li>
				</c:if>
				<c:if test="${not empty researchreport}">
					<li>
						<a id="myofferings4" href="#tab4" data-toggle="tab" >
							Research Reporting
						</a>
					</li>
				</c:if>
			</ul>
			<jsp:include page="common/progressLoader.jsp"></jsp:include>
			<c:if test="${not empty dataaggregator}">
				<div class="tab-pane" id="tab1">
					<div id="data_aggregator_top_card" class="top_card">
						<div class="data_aggregator_info">
		
						</div>
						<a class="btn add_more">
							<span class="fa fa-pencil"></span>Add More
						</a>
					</div>
					<div id="data_aggregator" class="custom_form">
						<form name="data_aggregator_form" id="data_aggregator_form">
							<div class="generic_message">
								<div class="alert"></div>
							</div>
							<div class="product_info">
								<h3>Product Info <span class="fa fa-chevron-up"></span></h3>
								<input type="text" name="productId" id="productId" hidden="hidden" />
								<ul>
									<li>
										<input type="text" maxlength="300" name="productName" id="productName" required />
										<label>Product Name</label>
									</li>
									<li>
										<input type="text" maxlength="1000" name="productDescription" id="productDescription" required />
										<label>Product Description</label>
									</li>
									<li>
										<input type="number" name="launchedYear" id="launchedYear" required />
										<label>Launched Year</label>
									</li>
									<li>
										<select class="selectpicker show-tick" id="assetClassId" name="assetClassId">
											
										</select>
										<label class="default_select">Asset Class</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" id="securityTypes" name="securityTypes" multiple="multiple">
											<c:forEach var="securityType" items="${securityTypes}">
											<option value="${securityType.name}">${securityType.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Security Type</label>
									</li>
								</ul>
							</div>
							<div class="data_coverage_info">
								<h3>Data Coverage Info <span class="fa fa-chevron-up"></span></h3>
								<ul>
									<li>
										<select class="selectpicker select_multiple" name="coverageRegion" id="coverageRegion" multiple="multiple">
											
										</select>
										<label class="default_select">Coverage Region</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="coverageCountry" id="coverageCountry" multiple="multiple">
											<c:forEach var="countries" items="${countries}">
											<option value="${countries.country_id}" >${countries.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Coverage Country</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="coverageExchange"  multiple="multiple" id="coverageExchange">
											<c:forEach var="exchanges" items="${exchanges}">
											<option value="${exchanges.exchange_id}">${exchanges.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Coverage Exchange</label>
									</li>
									<li>
										<input type="number" name="costRange" id="costRange" required />
										<label>License Cost Range ($ per annum)</label>
									</li>
									<li>
										<input type="text" name="email" id="email" required />
										<label>Primary Email</label>
									</li>
									<li>
										<input type="text" name="phoneNumber" id="phoneNumber" required />
										<label>Phone Number</label>
									</li>
								</ul>
							</div>
							<div class="data_distribution_info">
								<h3>Data Distribution Info <span class="fa fa-chevron-down"></span></h3>
								<ul>
									<li>
										<select class="selectpicker select_multiple" name="feedType" multiple="multiple" id="feedType">
											<option value ="EOD"> EOD </option>
											<option value ="REAL-TIME"> REAL-TIME </option>
											<option value ="HISTORICAL-EOD">HISTORICAL-EOD</option>
										</select>
										<label class="default_select">Feed Type</label>
									</li>

									<li>
										<select class="selectpicker select_multiple" name="feedSubType" multiple="multiple" id="feedSubType">
											<option value ="Full Universe Data Feed"> Full Universe Data Feed </option>
											<option value ="Delta Data Feed"> Delta Data Feed </option>
										</select>
										<label class="default_select">Feed Sub-type</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="frequency" multiple="multiple" id="frequency">
											<option value ="intra-day"> intra-day </option>
											<option value ="Daily"> Daily </option>
											<option value ="Weekly"> Weekly </option>
											<option value ="Semi-Monthly"> Semi-Monthly </option>
											<option value ="Monthly"> Monthly </option>
											<option value ="Yearly"> Yearly </option>
										</select>
										<label class="default_select">Frequency</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="distributionMethod" multiple="multiple" id="distributionMethod">
											<option value ="Web"> Web </option>
											<option value ="FTP"> FTP </option>
											<option value ="FTP"> Email </option>
											<option value ="Others"> Others </option>
										</select>
										<label class="default_select">Distribution Method</label>
									</li>
								</ul>
							</div>
							<p class="action_btn">
								<a class="submit_btn save" data-toggle="tab">Save</a>
								<a class="submit_btn next" data-toggle="tab">RESET</a>
							</p>
						</form>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty tradingapplication}">
				<div class="tab-pane" id="tab2">
					<div id="trading_application_top_card" class="top_card">
						<div class="trading_application_info">
		
						</div>
						<a class="btn add_more">
							<span class="fa fa-pencil"></span>Add More
						</a>
					</div>
					<div id="trading_application" class="custom_form">
						<form name="trading_application_form" id="trading_application_form">
							<div class="generic_message">
								<div class="alert"></div>
							</div>
							<div class="product_info">
								<h3>Product Info <span class="fa fa-chevron-up"></span></h3>
								<input type="text" name="productId" id="productId" hidden="hidden" />
								<ul>
									<li>
										<input type="text" maxlength="300" name="productName" id="productName" required />
										<label>Product Name</label>
									</li>
									<li>
										<input type="text" maxlength="1000" name="productDescription" id="productDescription" required />
										<label>Product Description</label>
									</li>
									<li>
										<input type="number" name="launchedYear" id="launchedYear" required />
										<label>Launched Year</label>
									</li>
									<li>
										<select class="selectpicker show-tick" id="assetClassId" name="assetClassId">
											
										</select>
										<label class="default_select">Asset Class</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" id="securityTypes" name="securityTypes" multiple="multiple">
											<c:forEach var="securityType" items="${securityTypes}">
											<option value="${securityType.name}">${securityType.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Security Type</label>
									</li>
								</ul>
							</div>
							<div class="software_details_info">
								<h3>Software Details <span class="fa fa-chevron-up"></span></h3>
								<ul>
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
										<select class="selectpicker select_multiple" name="tdsCostType"  multiple="multiple" id="tdsCostType">
											<option value ="Free and Min Balance Not required"> Free and Min Balance Not required </option>
											<option value ="Min Balance Required"> Min Balance Required </option>
											<option value ="Subscription based"> Subscription based </option>
											<option value ="Any"> Any </option>
										</select>
										<label class="default_select">Cost Type</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="tdsPlatformCCY"  multiple="multiple" id="tdsPlatformCCY">
											<option value ="GBP "> GBP  </option>
											<option value ="USD"> USD </option>
											<option value ="EUR"> EUR </option>
											<option value ="INR"> INR </option>
											<option value ="SGD"> SGD </option>
											<option value ="AUD"> AUD </option>
											<option value ="CHF"> CHF </option>
											<option value ="CAD"> CAD </option>
											<option value ="JPY"> JPY </option>
											<option value ="Any"> Any </option>
										</select>
										<label class="default_select">Platform  CCY</label>
									</li>
									<li>
										<input type="text" name="tdsPlatformCost" id="tdsPlatformCost" readonly />
										<label>Platform cost (USD per month)</label>
									</li>
									<li>
										<input type="text" name="tdsPlatformType" id="tdsPlatformType" readonly />
										<label>Platform cost (USD per annum)</label>
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
										<input type="text" name="tdsAddSoftwareSpecifications" id="tdsAddSoftwareSpecifications" required />
										<label>Software specifications</label>
									</li>
									<li>
										<input type="text" name="tdsAddOns" id="tdsAddOns" required />
										<label>Add-Ons</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="tdsOpeSystem"  multiple="multiple" id="tdsOpeSystem">
											<option value ="Windows">Windows</option>
											<option value ="MAC OS X">MAC OS X</option>
											<option value ="Unix">Unix</option>
											<option value ="Linux">Linux</option>
											<option value ="Solaris">Solaris</option>
											<option value ="Others">Others</option>
										</select>
										<label class="default_select">Operating system</label>
									</li>
									<li>
										<input type="text" name="tdsClientBase" id="tdsClientBase" required />
										<label>Client Base</label>
									</li>
									<li style="clear:both;">
										<input type="checkbox" name="tdsPriceAlerts" id="tdsPriceAlerts" required />
										<label class="default_checkbox">Price Alerts?</label>

										<input type="checkbox" name="tdsWatchlist" id="tdsWatchlist" required />
										<label class="default_checkbox">Watchlist?</label>
									</li>
									<li>
										<input type="checkbox" name="tdsStreNews" id="tdsStreNews" required />
										<label class="default_checkbox">Streaming news?</label>
										<input type="checkbox" name="tdsChartsAvai" id="tdsChartsAvai" required />
										<label class="default_checkbox">Trade using charts?</label>
									</li>
								</ul>
							</div>
							<div class="trading_capability_info">
								<h3>Trading Capability <span class="fa fa-chevron-down"></span></h3>
								<ul>
									<li>
										<select class="selectpicker select_multiple" name="tcsTradeCoverageRegion" multiple="multiple" id="tcsTradeCoverageRegion">
											<c:forEach var="regions" items="${regions}">
											<option value="${regions.name}">${regions.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Tradable region</label>
									</li>

									<li>
										<select class="selectpicker select_multiple" name="tcsTradeCoverageCountry" multiple="multiple" id="tcsTradeCoverageCountry">
											<c:forEach var="countries" items="${countries}">
											<option value="${countries.name}">${countries.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Tradable Country</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="tcsTradableMarkets" multiple="multiple" id="tcsTradableMarkets">
											<c:forEach var="exchanges" items="${exchanges}">
											<option value="${exchanges.name}">${exchanges.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Tradable Exchange</label>
									</li>
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
										<label class="default_select">Trading Capabilities Type</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="tcsTradeExecutionsType" multiple="multiple" id="tcsTradeExecutionsType">
											<option value ="Algorithmic & Program Trading" > Algorithmic & Program Trading </option>
											<option value ="Direct Market Access (DMA)" > Direct Market Access (DMA) </option>
											<option value ="FX -DMA"> FX -DMA </option>
											<option value ="Smart Order Routing (Sequential SOR)"> Smart Order Routing (Sequential SOR) </option>

											<option value ="Smart Order Routing (Multi-Posting SOR)"> Smart Order Routing (Multi-Posting SOR) </option>
											<option value ="Swap Execution Facility"> Swap Execution Facility </option>
										</select>
										<label class="default_select">Trade Executions Type</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="tcsAlgorithmicTradeType" multiple="multiple" id="tcsAlgorithmicTradeType">
											<option value ="VWAP - (Volume Weighted Average Price)" > -VWAP - (Volume Weighted Average Price) - </option>
											<option value ="TWAP - (Time Weighted Average Price)" > -TWAP - (Time Weighted Average Price) - </option>
											<option value ="PVOL - (Percentage Of Volume)"> -PVOL - (Percentage Of Volume) - </option>
											<option value ="IMSH - (Implementation Shortfall)"> -IMSH - (Implementation Shortfall) - </option>
										</select>
										<label class="default_select">Algorithmic Trade Type</label>
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
										<select class="selectpicker select_multiple" name="tcsSupportedDarkpoolVenues" multiple="multiple" id="tcsSupportedDarkpoolVenues">
											<option value ="Instinet"> Instinet  </option>
											<option value ="Liquidnet"> Liquidnet </option>
											<option value ="NYFIX"> NYFIX </option>
											<option value ="Posit/MatchNow"> Posit/MatchNow </option>
											<option value ="BlockCross"> BlockCross  </option>
											<option value ="RiverCross"> RiverCross </option>
											<option value ="TORA Crosspoint"> TORA Crosspoint </option> 
											<option value =" Codestreet Dealer Pool for Corporate Bonds"> Codestreet Dealer Pool for Corporate Bonds </option>
											<option value ="JPMorgan Chase Bank - JPMX"> JPMorgan Chase Bank - JPMX </option>
											<option value ="Barclays Capital - LX Liquidity Cross"> Barclays Capital - LX Liquidity Cross </option>
											<option value ="BNP Paribas - BNP Paribas Internal eXchange (BIX)"> BNP Paribas - BNP Paribas Internal eXchange (BIX) </option>
											<option value ="BNY ConvergEx Group (an affiliate of Bank of New York Mellon)"> BNY ConvergEx Group (an affiliate of Bank of New York Mellon) </option>
											<option value ="Cantor Fitzgerald - Aqua Securities"> Cantor Fitzgerald - Aqua Securities </option>
											<option value ="Citi - Citi Match, Citi Cross"> Citi - Citi Match, Citi Cross </option>
											<option value ="Credit Agricole Cheuvreux - BLINK"> Credit Agricole Cheuvreux - BLINK </option>
											<option value ="Credit Suisse - CrossFinder"> Credit Suisse - CrossFinder </option>
											<option value ="Deutsche Bank Global Markets - DBA (Europe), SuperX ATS (U.S.)"> Deutsche Bank Global Markets - DBA (Europe), SuperX ATS (U.S.) </option>
											<option value ="Fidelity Capital Markets"> Fidelity Capital Markets </option>
											<option value ="GETCO - GETMatched"> GETCO - GETMatched </option>
											<option value ="Goldman Sachs SIGMA X"> Goldman Sachs SIGMA X </option>
											<option value ="Knight Capital Group - Knight Link, Knight Match"> Knight Capital Group - Knight Link, Knight Match </option>
											<option value ="Merrill Lynch - Instinct-X"> Merrill Lynch - Instinct-X </option>
											<option value ="Morgan Stanley - NightVision"> Morgan Stanley - NightVision </option>
											<option value ="Nomura - Nomura NX, CHI-X"> Nomura - Nomura NX, CHI-X </option>
											<option value ="UBS Investment Bank - UBS ATS, UBS MTF, UBS PIN"> UBS Investment Bank - UBS ATS, UBS MTF, UBS PIN </option>
											<option value ="Societe Generale - ALPHA Y"> Societe Generale - ALPHA Y </option>
											<option value ="Daiwa - DRECT"> Daiwa - DRECT </option>
											<option value ="Wells Fargo Securities LLC - WELX"> Wells Fargo Securities LLC - WELX </option> 
											<option value ="BIDS Trading - BIDS ATS"> BIDS Trading - BIDS ATS </option>
											<option value ="LeveL ATS"> LeveL ATS </option>
											<option value ="International Securities Exchange"> International Securities Exchange </option>
											<option value ="NYSE Euronext"> NYSE Euronext </option>
											<option value ="BATS Trading"> BATS Trading </option>
											<option value ="Turquoise"> Turquoise </option>
											<option value ="Swiss Block"> Swiss Block </option>
											<option value ="Nordic@Mid"> Nordic@Mid </option>
											<option value ="Fidessa - Spotlight"> Fidessa - Spotlight </option>
											<option value ="Bloomberg Tradebook"> Bloomberg Tradebook </option>
											<option value ="Liquidnet LN Dark"> Liquidnet LN Dark </option>
											<option value ="Credit Suisse Crossfinder Plus"> Credit Suisse Crossfinder Plus </option>
											<option value ="SuperX+ - Deutsche Bank"> SuperX+ - Deutsche Bank </option>
											<option value ="ASOR - Quod Financial"> ASOR - Quod Financial </option>
											<option value ="Progress Apama"> Progress Apama </option>
											<option value ="ONEPIPE - Weeden & Co. & Pragma Financial"> ONEPIPE - Weeden & Co. & Pragma Financial </option>
											<option value ="Xasax Corporation"> Xasax Corporation </option>
											<option value ="Crossfire - Credit Agricole Cheuvreux"> Crossfire - Credit Agricole Cheuvreux </option>
											<option value ="Instinet - Nighthawk"> Instinet - Nighthawk </option>
										</select>
										<label class="default_select">Supported Darkpool Venues</label>
									</li>
								</ul>
							</div>
							<p class="action_btn">
								<a class="submit_btn save" data-toggle="tab">Save</a>
								<a class="submit_btn next" data-toggle="tab">RESET</a>
							</p>
						</form>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty analyticsapplication}">
				<div class="tab-pane" id="tab3">
					<div id="analytic_application_top_card" class="top_card">
						<div class="analytic_application_info">
		
						</div>
						<a class="btn add_more">
							<span class="fa fa-pencil"></span>Add More
						</a>
					</div>
					<div id="analytic_application" class="custom_form">
						<form name="analytic_application_form" id="analytic_application_form">
							<div class="generic_message">
								<div class="alert"></div>
							</div>
							<div class="product_info">
								<h3>Product Info <span class="fa fa-chevron-up"></span></h3>
								<input type="text" name="productId" id="productId" hidden="hidden" />
								<ul>
									<li>
										<input type="text" maxlength="300" name="productName" id="productName" required />
										<label>Product Name</label>
									</li>
									<li>
										<input type="text" maxlength="1000" name="productDescription" id="productDescription" required />
										<label>Product Description</label>
									</li>
									<li>
										<input type="number" name="launchedYear" id="launchedYear" required />
										<label>Launched Year</label>
									</li>
									<li>
										<select class="selectpicker show-tick" id="analyticsSolutionType" name="analyticsSolutionType">
											<option value ="Banking Solutions" > Banking Solutions </option>
											<option value ="Alternative Investment Solutions" > Alternative Investment Solutions </option>
											<option value ="Backoffice Operations" > Backoffice Operations</option>
											<option value ="Backoffice Operations" > Backoffice Operations </option>
											<option value ="Margining Solutions" > Margining Solutions</option>
											<option value ="Portfolio Management Solutions" > Portfolio Management Solutions</option>
											<option value ="Product Valuation" > Product Valuation</option>
											<option value ="Regulatory, Compliance & Risk Mgm" > Regulatory, Compliance & Risk Mgm</option>
										</select>
										<label class="default_select">Analytics Solutions Type</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" id="analyticsSolutionSubType" name="analyticsSolutionSubType" multiple="multiple">
											<option value ="Custody management" > Custody management </option>
											<option value ="Alternative and institutional investments" > Alternative and institutional investments </option>
											<option value ="Commodity trading solutions" > Commodity trading solutions </option>
											<option value ="FCP/CCP clearing suite" > FCP/CCP clearing suite </option>
										</select>
										<label class="default_select">Analytics Solutions Sub Type</label>
									</li>
								</ul>
							</div>
							<div class="software_details_info">
								<h3>Software Details <span class="fa fa-chevron-up"></span></h3>
								<ul>
									<li>
										<select class="selectpicker select_multiple" name="asdAccessibility"  multiple="multiple" id="asdAccessibility">
											<option value ="Web/Browser Based"> Web/Browser Based </option>
											<option value ="Binaries(Executable) Based"> Binaries(Executable) Based </option>
											<option value ="Dedicated Desktop">Dedicated Desktop</option>
											<option value ="Mobile/Tablet Apps">Mobile/Tablet Apps</option>
										</select>
										<label class="default_select">Accessibility (?)</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="asdSuitability1"  multiple="multiple" id="asdSuitability1">
											<option value ="All Users" >All Users</option>
											<option value ="Risk Managers" >Risk Managers</option>
											<option value ="Backoffice & Middle Office Users" >Backoffice & Middle Office Users</option>
											<option value ="Portfolio Managers" >Portfolio Managers</option>
											<option value ="Retail/Corporat Banking Users" >Retail/Corporat Banking Users</option>
											<option value ="Regulation & Compliance Users" >Regulation & Compliance Users</option>
											<option value ="Research Analysts" >Research Analysts</option>
											<option value ="Others" >Others</option>
										</select>
										<label class="default_select">Suitability</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="asdCostType"  multiple="multiple" id="asdCostType">
											<option value ="Min Balance Required" > Min Balance Required </option>
											<option value ="Free and Min Balance Not required" >Free and Min Balance Not required</option>
											<option value ="Subscription based" > Subscription based </option>
										</select>
										<label class="default_select">Application Cost Type</label>
									</li>
									<li>
										<input type="text" name="asdApplicationSubscriptionCost" id="asdApplicationSubscriptionCost" readonly />
										<label>Subscription cost (USD per month)</label>
									</li>
									<li>
										<input type="text" name="asdApplicationSubscriptionAnnum" id="asdApplicationSubscriptionAnnum" readonly />
										<label>Subscription cost (USD per annum)</label>
									</li>
									<li>
										<input type="text" name="asdAddOns" id="asdAddOns" required />
										<label>Add-Ons</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="asdOperatingSystem"  multiple="multiple" id="asdOperatingSystem">
											<option value ="Windows">Windows</option>
											<option value ="MAC OS X">MAC OS X</option>
											<option value ="Unix">Unix</option>
											<option value ="Linux">Linux</option>
											<option value ="Solaris">Solaris</option>
											<option value ="Others">Others</option>
										</select>
										<label class="default_select">Operating system</label>
									</li>
									<li>
										<input type="text" name="asdSoftwareSpecifications" id="asdSoftwareSpecifications" required />
										<label>Software specifications</label>
									</li>
									<li>
										<input type="text" name="asdExistingUserBase" id="asdExistingUserBase" required />
										<label>Existing User Base</label>
									</li>
									<li>
										<input type="checkbox" name="asdCustomizableCalculationModels" id="asdCustomizableCalculationModels" required />
										<label class="default_checkbox">Customizable Calculation Models(?)</label>
									</li>
									<li>
										<input type="checkbox" name="asdRealtimeMarketData" id="asdRealtimeMarketData" required />
										<label class="default_checkbox">Real time Market Data?</label>
									</li>
								</ul>
							</div>
							<p class="action_btn">
								<a class="submit_btn save" data-toggle="tab">Save</a>
								<a class="submit_btn next" data-toggle="tab">RESET</a>
							</p>
						</form>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty researchreport}">
				<div class="tab-pane" id="tab4">
					<div id="research_application_top_card" class="top_card">
						<div class="research_application_info">
		
						</div>
						<a class="btn add_more">
							<span class="fa fa-pencil"></span>Add More
						</a>
					</div>
					<div id="research_application" class="custom_form">
						<form name="research_application_form" id="research_application_form">
							<div class="generic_message">
								<div class="alert"></div>
							</div>
							<div class="product_info">
								<h3>Product Info <span class="fa fa-chevron-up"></span></h3>
								<input type="text" name="productId" id="productId" hidden="hidden" />
								<ul>
									<li>
										<input type="text" maxlength="300" name="productName" id="productName" required />
										<label>Research Report Name</label>
									</li>
									<li>
										<input type="text" maxlength="1000" name="productDescription" id="productDescription" required />
										<label>Research Report Description</label>
									</li>
									<li>
										<input type="number" name="launchedYear" id="launchedYear" required />
										<label>Launched Year</label>
									</li>
									<li>
										<select class="selectpicker show-tick" id="rcResearchArea" name="rcResearchArea">
											<option value ="Commodity Analysis" >Commodity Analysis</option>
											<option value ="Exchange Rate Analysis" >Exchange Rate Analysis</option>
											<option value ="Interest Rate Analysis" >Interest Rate Analysis</option>
											<option value ="Equity research" >Equity research</option>
											<option value ="Debt Market research" >Debt Market research</option>
											<option value ="Index research" >Index research</option>
											<option value ="Fund/ETF research" >Fund/ETF research</option>
										</select>
										<label class="default_select">Research Area</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" id="rcResearchSubArea" name="rcResearchSubArea" multiple="multiple">
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
										<label class="default_select">Research Sub Area</label>
									</li>
								</ul>
							</div>
							<div class="coverage_info">
								<h3>Coverage <span class="fa fa-chevron-up"></span></h3>
								<ul>
									<li>
										<select class="selectpicker" name="rcRegionsCovered" id="rcRegionsCovered">
											<c:forEach var="regions" items="${regions}">
												<option value="${regions.name}">${regions.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Regions Covered</label>
									</li>
									<li>
										<input type="text" name="rcTotalResearchAnalyst" id="rcTotalResearchAnalyst" required />
										<label>Total Research Analyst</label>
									</li>
									<li>
										<input type="text" name="rcExistingClientBase" id="rcExistingClientBase" readonly />
										<label>Existing Client Base</label>
									</li>
								</ul>
							</div>

							<div class="research_details">
								<h3>Research Details <span class="fa fa-chevron-up"></span></h3>
								<ul>
									<li>
										<select class="selectpicker" name="rdAccessibility" id="rdAccessibility">
											<option value ="Web-Browser Based" >-Web-Browser Based-</option>
											<option value ="Email" > -Email- </option>
										</select>
										<label class="default_select">Accessibility (?)</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="rdSuitability"  multiple="multiple" id="rdSuitability">
											<option value ="All Users" >All Users</option>
											<option value ="Equity Fund Manager" >Equity Fund Manager</option>
											<option value ="Fixed Income Fund Manager" >Fixed Income Fund Manager</option>
											<option value ="Hedge Fund" >Hedge Fund</option>
											<option value ="Private Equity Fund" >Private Equity Fund</option>
											<option value ="Advanced shares traders" >Advanced shares traders</option>
											<option value ="Corporate Creditors (Banks, Lending Institutions, etc)" >Corporate Creditors (Banks, Lending Institutions, etc)</option>
											<option value ="Technical chart users" >Technical chart users</option>
											<option value ="Forex specialists" >Forex specialists</option>
											<option value ="Others" >Others</option> 
										</select>
										<label class="default_select">Suitability</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="rdReportCostType"  multiple="multiple" id="rdReportCostType">
											<option value ="Free and Min Balance Not required" >Free and Min Balance Not required</option>
											<option value ="Min Balance Required" > Min Balance Required </option>
											<option value ="Subscription based" > Subscription based </option>
										</select>
										<label class="default_select">Report Cost Type</label>
									</li>
									<li>
										<input type="text" name="rdSubsriptionCostUSDpermonth" id="rdSubsriptionCostUSDpermonth" readonly />
										<label>Subscription cost (USD per month)</label>
									</li>
									<li>
										<input type="text" name="rdSubsriptionCostUSDperannum" id="rdSubsriptionCostUSDperannum" readonly />
										<label>Subscription cost (USD per annum)</label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="rdReportFormat"  multiple="multiple" id="rdReportFormat">
											<option value ="PDF"> PDF  </option>
											<option value ="DOC"> DOC </option>
											<option value ="EXCEL"> EXCEL </option>
										</select>
										<label class="default_select">Report Format</label>
									</li>
									<li>
										<select class="selectpicker" name="rdResearchApplicableMonth" id="rdResearchApplicableMonth">
											<option value ="January">January</option>
											<option value ="February">February</option>
											<option value ="March">March</option>
											<option value ="April">April</option>
											<option value ="May">May</option>
											<option value ="June">June</option>
											<option value ="July">July</option>
											<option value ="August">August</option>
											<option value ="September">September</option>
											<option value ="October">October</option>
											<option value ="November">November</option>
											<option value ="December">December</option>
										</select>
										<label class="default_select">Research Period - Month</label>
									</li>
									<li>
										<select class="selectpicker" name="rdResearchApplicableYear" id="rdResearchApplicableYear">
											<option value ="2006">2006</option>
											<option value ="2007">2007</option>
											<option value ="2008">2008</option>
											<option value ="2009">2009</option>
											<option value ="2010">2010</option>
											<option value ="2011">2011</option>
											<option value ="2012">2012</option>
											<option value ="2013">2013</option>
											<option value ="2014">2014</option>
											<option value ="2015">2015</option>
											<option value ="2016">2016</option>
										</select>
										<label class="default_select">Research Period - Year</label>
									</li>
								</ul>
							</div>
							<div class="analyst_profile_info">
								<h3>Analyst Profile <span class="fa fa-chevron-up"></span></h3>
								<ul>
									<li>
										<input type="text" name="apAnalystName" id="apAnalystName" required />
										<label>Analyst Name</label>
									</li>
									<li>
										<select class="selectpicker" name="apAnalystRegionofIncorp"  id="apAnalystRegionofIncorp">
											<c:forEach var="regions" items="${regions}">
												<option value="${regions.name}">${regions.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Analyst Region of Incorp</label>
									</li>
									<li>
										<select class="selectpicker" name="apAnalystCountryofIncorp" id="apAnalystCountryofIncorp">
											<c:forEach var="countries" items="${countries}">
												<option value="${countries.name}">${countries.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Analyst Country of Incorp</label>
									</li>
									<li>
										<select class="selectpicker" name="apAnalystYearofExp" id="apAnalystYearofExp">
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
										<label class="default_select">Analyst Year of Exp</label>
									</li>
									<li>
										<select class="selectpicker" name="apAnalystAwards" id="apAnalystAwards">
											<c:forEach var="awards" items="${awards}">
												<option value="${awards.name}">${awards.name}</option>
											</c:forEach>
											<option value="Other">Other</option>
										</select>
										<label class="default_select">Analyst Awards</label>
									</li>
									<li>
										<input type="checkbox" name="apResearchAnalystWithCFA" id="apResearchAnalystWithCFA" required />
										<label class="default_checkbox">Research Analyst with CFA Charter?</label>
									</li>
								</ul>
							</div>
							<p class="action_btn">
								<a class="submit_btn save" data-toggle="tab">Save</a>
								<a class="submit_btn next" data-toggle="tab">RESET</a>
							</p>
						</form>
					</div>
				</div>
			</c:if>
		</div>
	</div>



    <!-- END CONTAINER -->
 
    <jsp:include page="common/footer.jsp"></jsp:include>
	
  	<script type="text/javascript" 	src="<%=request.getContextPath() %>/resources/js/finvendorValidation.js"></script>
    <link rel="stylesheet" src="<%=request.getContextPath() %>/resources/css/vendor.css"/>
    
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
    <script type="text/javascript">
    	$(document).ready( function () {
    		$("#vendor_my_offerings .nav-tabs li:first").addClass("active");
    		var selected = $("#vendor_my_offerings .nav-tabs li:first a").text();
    		if(selected.indexOf("Data Aggregator") != -1) {
    			listDataAggregatorOffering();
    		} else if(selected.indexOf("Trading Application") != -1) {
    			listTradeApplicationOffering();
    		} else if(selected.indexOf("Analytics Application") != -1) {
    			listAnalyticsApplicationOffering();
    		} else if(selected.indexOf("Research Reporting") != -1) {
    			listResearchReportProviderOffering();
    		}
    	});
    </script>
</body>
</html>