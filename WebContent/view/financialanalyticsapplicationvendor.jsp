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
    		getAnalyticalSolutionTypeList();
    		getAnalyticalSolutionSubTypeList();
    	});
    </script>
    <script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/financialanalyticsvendor.js"></script>
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
					Analytics Application (AA) Vendor</a>
			</div>
			<div class="arw">></div>
			<div class="text_arw">Search Vendor</div>
		</div>
		<div id="searchmultiform">
			<form
			action="<%=request.getContextPath()+"/"+RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>?RaYvEmUl=${l:encrypt(username)}"
			class="form-horizontal" id="submit_form" method="post"
			enctype="multipart/form-data">
			<div id="financial_anyltics_application_search" class="consumer_search custom_form">
				<div class="generic_message">
					<div class="alert"></div>
				</div>
				<div class="asset_class">
					<h3>Analytics Solutions Type <span class="fa fa-chevron-up"></span></h3>
					<ul>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" data-id="1" value="Alternative Investment Solutions"
							id="alternative_investment_solutions" />
							<label>Alternative Invest.. Soln</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" data-id="2" value="Backoffice Operations"
							id="back_office_operations" />
							<label>Backoffice Operations</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" data-id="3" value="Banking Solution" id="banking_solution" />
							<label>Banking Solution</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" data-id="4" value="Margining Solution" id="margining_solution" />
							<label>Margining Solution</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" data-id="6" value="Portfolio Management Solutions" id="portfolio_management" />
							<label>Portfolio Management Solutions</label>
						</li>
						<li>
							<input type="checkbox" class="assetClass"
							name="assetClassChk" data-id="7" value="Product Valuation" id="product_valuation" />
							<label>Product Valuation</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" data-id="8" value="Regulatory Compliance & Risk Mgmt" id="regulatory_compliance_risk_mgmt" />
							<label>Regulatory Cmpl. &amp; Risk Mgmt</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" data-id="5" value="Others" id="others" />
							<label>Others</label>
						</li>
					</ul>
				</div>

				<div class="common_fields_for_asset_class">
					<h3>Common Fields for all Solution Type <span class="fa fa-chevron-up"></span></h3>
					<ul>
						<li>
							<select class="selectpicker" name="region_of_incorp"  id="region_of_incorp">
								<c:forEach var="regions" items="${regions}">
								<option value="${regions.region_id}">${regions.name}</option>
								</c:forEach>
							</select>
							<label class="default_select">Region of Incorp</label>
						</li>
						<li>
							<select class="selectpicker" name="country_of_incorp" id="country_of_incorp">
								<c:forEach var="countries" items="${countries}">
								<option value="${countries.name}">${countries.name}</option>
								</c:forEach>
							</select>
							<label class="default_select">Country of Incorp</label>
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