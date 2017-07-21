<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0"
	prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans"
	var="requestConstants" />
<html>
<head>
<title>Equity Research Report Vendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
</head>
<body>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<section>
		<div class="container">
			<div id="fv_equity_research_report_vendor" class="section">
				<ul class="nav nav-tabs">
					<li class="active">
						<a id="myofferings1" href="#tab1" data-toggle="tab" >
							Basic Search
						</a>
					</li>
					<li>
						<a id="myofferings2" href="#tab2" data-toggle="tab">
							Advanced Search
						</a>
					</li>
				</ul>
				<div class="equity_research_report_vendor_info custom_form">
					<ul id='tab1'>
						<li>
							<select class="selectpicker show-tick" id="eqrrv_research_area" name="eqrrv_research_area">
								<option value ="Equity research" >Equity research</option>
							</select>
							<label class="default_select">Research Area</label>
						</li>
						<li>
							<select class="selectpicker" name="eqrrv_report_for" id="eqrrv_report_for" data-live-search="true">
								<option value ="PDF"> PDF  </option>
								<option value ="DOC"> DOC </option>
								<option value ="EXCEL"> EXCEL </option>
							</select>
							<label class="default_select">Research Report For<sup>*</sup></label>
						</li>
					</ul>
					<ul id='tab2' style="display: none;">
						<li>
							<select class="selectpicker show-tick" id="eqrrv_research_area" name="eqrrv_research_area">
								<option value ="Equity research" >Equity research</option>
							</select>
							<label class="default_select">Research Area</label>
						</li>

						<li>
							<select class="selectpicker select_multiple" id="eqrrv_research_sub_area" name="eqrrv_research_sub_area" multiple="multiple">
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
							<label class="default_select">Research Sub Area <sup>*</sup></label>
						</li>
						<li>
							<select class="selectpicker" name="eqrrv_report_for" id="eqrrv_report_for">
								<option value ="all">All</option>
								<option value ="any">Any</option>
							</select>
							<label class="default_select">Research Report For<sup>*</sup></label>
						</li>
						<li>
							<select class="selectpicker show-tick" id="eqrrv_research_broker" name="eqrrv_research_broker" data-live-search="true">
								<option value ="Capital Market" >Capital Market</option>
								<option value ="Zerodha" >Zerodha</option>
								<option value ="HDFC" >HDFC</option>
								<option value ="ICICI" >ICICI</option>
							</select>
							<label class="default_select">Research Broker</label>
						</li>
						<li>
							<select class="selectpicker" name="eqrrv_recommendation_type" id="eqrrv_recommendation_type">
								<option value ="all">All</option>
								<option value ="any">Any</option>
							</select>
							<label class="default_select">Recommendation Type</label>
						</li>
						<li>
							<select class="selectpicker" name="eqrrv_upside_potential" id="eqrrv_upside_potential">
								<option value ="0">< 10%</option>
								<option value ="1">10-20%</option>
								<option value ="2">20-30%</option>
								<option value ="3">30-40%</option> 
								<option value ="4">40-50%</option>
							</select>
							<label class="default_select">Upside Potential</label>
						</li>
					</ul>
					<p class="action_btn">
						<a class="submit_btn save" data-toggle="tab">Save</a>
						<a class="submit_btn next" data-toggle="tab">RESET</a>
					</p>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="login.jsp"></jsp:include>
	<jsp:include page="common/footer.jsp"></jsp:include>
	   <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
    <script type='text/javascript'>
    	$('#myofferings1').on('click', function() {
    		$('#tab1').show();
    		$('#tab2').hide();
    	});

    	$('#myofferings2').on('click', function() {
    		$('#tab2').show();
    		$('#tab1').hide();
    	});

    </script>
</body>
</html>