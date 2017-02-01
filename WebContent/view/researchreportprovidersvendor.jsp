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
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/researchreportprovidervendor.js"></script>
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
					Research Report (RR) Providers</a>
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
					<h3>Research Area <span class="fa fa-chevron-up"></span></h3>
					<ul>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Commodity Analysis"
							id="commodity_analysis" />
							<label>Commodity Analysis</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Exchange Rate Analysis"
							id="exchange_rate_analysis" />
							<label>Exchange Rate Analysis</label>
						</li>
						<li>
							<input type="checkbox" class="assetClass"
							name="assetClassChk" value="Interest Rate Analysis" id="interest_rate_analysis" />
							<label>Interest Rate Analysis</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Equity research" id="equity_research" />
							<label>Equity research</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Debt Market research" id="debt_market_research" />
							<label>Debt Market research</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Index research" id="index_research" />
							<label>Index research</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Fund/ETF research" id="fund_etf_research" />
							<label>Fund/ETF research</label>
						</li>
					</ul>
				</div>

				<div class="common_fields_for_asset_class">
					<h3>Common Fields for all research area <span class="fa fa-chevron-up"></span></h3>
					<ul>
						<li>
							<select class="selectpicker" name="rdAnalystRegionofIncorp"  id="rdAnalystRegionofIncorp">
								<c:forEach var="regions" items="${regions}">
								<option value="${regions.region_id}">${regions.name}</option>
								</c:forEach>
							</select>
							<label class="default_select">Analyst Region of Incorp</label>
						</li>
						<li>
							<select class="selectpicker" name="rdAnalystCountryofIncorp" id="rdAnalystCountryofIncorp">
								<c:forEach var="countries" items="${countries}">
								<option value="${countries.name}">${countries.name}</option>
								</c:forEach>
							</select>
							<label class="default_select">Analyst Country of Incorp</label>
						</li>
						<li>
							<select class="selectpicker show-tick" id="rdVendorProfileFreshness" name="rdVendorProfileFreshness">
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
							<select class="selectpicker" name="rdAnalystYearofExp" id="rdAnalystYearofExp">
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
							<select class="selectpicker" name="rcExistingClientBase" id="rcExistingClientBase">
								<option value="Any">Any</option>
								<option value="Upto 10">Upto 10</option>
								<option value="Upto 25">Upto 25</option>
								<option value="Upto 100">Upto 100</option>
								<option value="More than 100">More than 100</option>
							</select>
							<label class="default_select">Existing Client Base</label>
						</li>

						<li>
							<input type="checkbox" name="rdResearchAnalystWithCFA" id="rdResearchAnalystWithCFA" required />
							<label class="default_checkbox">Research Analyst with CFA Charter?</label>
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
			action="<%=request.getContextPath()+"/"+RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>?RaYvEmUl=${l:encrypt(username)}"
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