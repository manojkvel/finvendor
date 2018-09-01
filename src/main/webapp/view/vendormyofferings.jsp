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
    	#tab2, #tab3 {
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
										<label>Product Name <sup>*</sup></label>
									</li>
									<li>
										<input type="text" maxlength="1000" name="productDescription" id="productDescription" required />
										<label>Product Description <sup>*</sup></label>
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
										<label class="default_select">Security Type <sup>*</sup></label>
									</li>
								</ul>
							</div>
							<div class="data_coverage_info">
								<h3>Data Coverage Info <span class="fa fa-chevron-up"></span></h3>
								<ul>
									<li>
										<select class="selectpicker select_multiple" name="coverageRegion" id="coverageRegion" multiple="multiple">
											
										</select>
										<label class="default_select">Coverage Region <sup>*</sup></label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="coverageCountry" id="coverageCountry" multiple="multiple">
											<c:forEach var="countries" items="${countries}">
											<option value="${countries.country_id}" >${countries.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Coverage Country <sup>*</sup></label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="coverageExchange"  multiple="multiple" id="coverageExchange">
											<c:forEach var="exchanges" items="${exchanges}">
											<option value="${exchanges.exchange_id}">${exchanges.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Coverage Exchange <sup>*</sup></label>
									</li>
									<li>
										<input type="number" name="costRange" id="costRange" required />
										<label>License Cost Range ($ per annum) <sup>*</sup></label>
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
										<label>Product Name <sup>*</sup></label>
									</li>
									<li>
										<input type="text" maxlength="1000" name="productDescription" id="productDescription" required />
										<label>Product Description <sup>*</sup></label>
									</li>
									<li>
										<input type="number" name="launchedYear" id="launchedYear" required />
										<label>Launched Year <sup>*</sup></label>
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
										<label class="default_select">Security Type <sup>*</sup></label>
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
										<label class="default_select">Accessibility (?) <sup>*</sup></label>
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
										<select class="selectpicker select_multiple" name="tdsCostType" id="tdsCostType">
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
										<input type="number" name="tdsPlatformCost" id="tdsPlatformCost" readonly required />
										<label>Platform cost (USD per month)</label>
									</li>
									<li>
										<input type="number" name="tdsPlatformType" id="tdsPlatformType" readonly required />
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
										<label>Software specifications <sup>*</sup></label>
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
										<label class="default_select">Tradable region <sup>*</sup></label>
									</li>

									<li>
										<select class="selectpicker select_multiple" name="tcsTradeCoverageCountry" multiple="multiple" id="tcsTradeCoverageCountry">
											<c:forEach var="countries" items="${countries}">
											<option value="${countries.name}">${countries.name}</option>
											</c:forEach>
										</select>
										<label class="default_select">Tradable Country <sup>*</sup></label>
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
										<label class="default_select">Trading Capabilities Type <sup>*</sup></label>
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
										<label class="default_select">Trade Executions Type <sup>*</sup></label>
									</li>
									<li>
										<select class="selectpicker select_multiple" name="tcsAlgorithmicTradeType" multiple="multiple" id="tcsAlgorithmicTradeType">
											<option value ="VWAP - (Volume Weighted Average Price)" > -VWAP - (Volume Weighted Average Price) - </option>
											<option value ="TWAP - (Time Weighted Average Price)" > -TWAP - (Time Weighted Average Price) - </option>
											<option value ="PVOL - (Percentage Of Volume)"> -PVOL - (Percentage Of Volume) - </option>
											<option value ="IMSH - (Implementation Shortfall)"> -IMSH - (Implementation Shortfall) - </option>
										</select>
										<label class="default_select">Algorithmic Trade Type <sup>*</sup></label>
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
										<label class="default_select">Darkpool Access <sup>*</sup></label>
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
										<label class="default_select">Supported Darkpool Venues <sup>*</sup></label>
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
										<label>Product Name <sup>*</sup></label>
									</li>
									<li>
										<input type="text" maxlength="1000" name="productDescription" id="productDescription" required />
										<label>Product Description <sup>*</sup></label>
									</li>
									<li>
										<input type="number" name="launchedYear" id="launchedYear" required />
										<label>Launched Year <sup>*</sup></label>
									</li>
									<li>
										<select class="selectpicker show-tick" id="analyticsSolutionType" name="analyticsSolutionType">
											<option value ="1" > Banking Solutions </option>
											<option value ="2" > Alternative Investment Solutions </option>
											<option value ="3" > Backoffice Operations</option>
											<option value ="4" > Backoffice Operations </option>
											<option value ="5" > Margining Solutions</option>
											<option value ="6" > Portfolio Management Solutions</option>
											<option value ="7" > Product Valuation</option>
											<option value ="8" > Regulatory, Compliance & Risk Mgm</option>
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
										<label class="default_select">Analytics Solutions Sub Type <sup>*</sup></label>
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
										<label class="default_select">Accessibility (?) <sup>*</sup></label>
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
										<select class="selectpicker select_multiple" name="asdApplicationCostType"  id="asdApplicationCostType">
											<option value ="Min Balance Required" > Min Balance Required </option>
											<option value ="Free and Min Balance Not required" >Free and Min Balance Not required</option>
											<option value ="Subscription based" > Subscription based </option>
										</select>
										<label class="default_select">Application Cost Type <sup>*</sup></label>
									</li>
									<li>
										<input type="number" name="asdApplicationSubscriptionCost" id="asdApplicationSubscriptionCost" readonly required />
										<label>Subscription cost (USD per month)</label>
									</li>
									<li>
										<input type="number" name="asdApplicationSubscriptionAnnum" id="asdApplicationSubscriptionAnnum" readonly required />
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
										<label>Software specifications <sup>*</sup></label>
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
										<label>Research Report Name <sup>*</sup></label>
									</li>
									<li>
										<input type="text" maxlength="1000" name="productDescription" id="productDescription" required />
										<label>Research Report Description <sup>*</sup></label>
									</li>
									<li>
										<input type="number" name="launchedYear" id="launchedYear" required />
										<label>Launched Year <sup>*</sup></label>
									</li>
									<li>
										<select class="selectpicker show-tick" id="rcResearchArea" name="rcResearchArea">
										</select>
										<label class="default_select">Research Area</label>
									</li>

									<li>
										<select class="selectpicker select_multiple" id="rcResearchSubArea" name="rcResearchSubArea" multiple="multiple">
										</select>
										<label class="default_select">Research Sub Area</label>
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
										<input type="number" name="rdSubsriptionCostUSDperannum" id="rdSubsriptionCostUSDperannum"  required />
										<label>Subscription cost per Annum</label>
									</li>
								</ul>
							</div>

							<div id="research_report_for_info" class="research_report_for_info">
								<h3>Research Report For <span class="fa fa-chevron-up"></span></h3>
								<div class="detail_container">
									<div class="summary_details">
										<table width="100%" border="0" cellpadding="1" cellspacing="0">
											
										</table>
									</div>
									<ul>
										<li>
											<select class="selectpicker" data-live-search="true" name="vo_rr_report_for" id="vo_rr_report_for">
											</select>
											<label class="default_select">Research Report For<sup>*</sup></label>
										</li>
										<li>
											<input type="number" name="vo_price_at_recomm" id="vo_price_at_recomm" required />
											<label>Price at Recomm.<sup>*</sup></label>
										</li>
										<li style="display:none;">
											<input type="number" name="vo_target_price" id="vo_target_price" required />
											<label>Target Price <sup>*</sup></label>
										</li>									
										<li>
											<select class="selectpicker" name="vo_eqrrv_recommendation_type" id="vo_eqrrv_recommendation_type">
												<option value="none">None</option>
												<option value="buy">Buy</option>
												<option value="sell">Sell</option>
												<option value="accumulate">Accumulate</option>
												<option value="overweight">Overweight</option>
												<option value="underweight">Underweight</option>
												<option value="reduce">Reduce</option>
												<option value="neutral">Neutral</option>
												<option value="hold">Hold</option>
												<option value="target_hit">Target Hit</option>
												<option value="bullish">Bullish</option>
												<option value="bearish">Bearish</option>
											</select>
											<label class="default_select">Recommendation Type<sup>*</sup></label>
										</li>
										<li>
											<input type="text" id="vo_datepicker" name="vo_datepicker" required />
											<label>Report Date <sup>*</sup></label>
										</li>
										<li>
											<input type="text" name="vo_eqrrv_report_desc" id="vo_eqrrv_report_desc" maxlength="1000" required />
											<label>Report Desc. <sup>*</sup></label>
										</li>								
										<li>
											<select class="selectpicker" name="vo_eqrrv_report_access" id="vo_eqrrv_report_access">
												<option value="buy">Free</option>
												<option value="sell">Paid</option>
											</select>
											<label class="default_select">Report Access</label>
										</li>
										<li>
											<input type="text" name="vo_analystName" id="vo_analystName" required />
											<label>Analyst Name</label>
										</li>
										<li>
											<div class="upload_report_container">
												<input type="text" name="vo_upload_report_file_name" id="vo_upload_report_file_name" readonly="readonly" />
												<span class="fa fa-upload">
													<input type="file" name="vo_upload_report" id="vo_upload_report" required />
												</span>
											</div>
											<label class="default_select">Upload Report</label>
										</li>
										<li style='margin-top: 22px;'>
											<input type="checkbox" name="vo_analystCfaCharter" id="vo_analystCfaCharter" />
											<label class="default_checkbox">Analyst with CFA Charter</label>
										</li>
										<li style='margin-top: 22px;'>
											<input type="checkbox" name="vo_analystwithawards" id="vo_analystwithawards" />
											<label class="default_checkbox">Analyst with Awards</label>
										</li>
									</ul>
									<!--<p id='research_report_for_info_add_more_btn'>
										<span class="add-more"><a href="#">Add</a></span>
									</p>-->
								</div>
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

<script src="<%=request.getContextPath() %>/resources/js/vendor.js"></script>

    <script type="text/javascript">
    	$(document).ready( function () {
    		$("#vendor_my_offerings .nav-tabs li:first").addClass("active");
    		var selected = $("#vendor_my_offerings .nav-tabs li:first a").text();
    		if(selected.indexOf("Research Reporting") != -1) {
    			listResearchReportsOffering();
    		}

    	});
    </script>

    <script>
    	$( function() {
    		$( "#vo_datepicker" ).datepicker({
    			dateFormat: 'dd/mm/yy'
    		});
    	} );
    </script>
    <!--<script src="<%=request.getContextPath() %>/resources/js/research_report_for_vendor.js"></script>-->
</body>
</html>