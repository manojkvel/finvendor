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
					Analytics Application (AA) Vendor</a>
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
																<label class="control-label">Analytics Solutions
																	Type<span class="required">*</span>
																</label>
															</div>
														</div>
														<div class="Row" id="asset-class-type">
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox">
																		Alternative Invest.. Soln&nbsp; <span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="alternative_investment_solutions"
																		value="Alternative Investment Solutions"
																		id="alternative_investment_solutions" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox">
																		Backoffice Operations<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="back_office_operations"
																		value="Backoffice Operations"
																		id="back_office_operations" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Product
																		Valuation<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="product_valuation" value="Product Valuation"
																		id="product_valuation" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Banking
																		Solution<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="banking_solution" value="Banking Solution"
																		id="banking_solution" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox">
																		Regulatory Compliance &amp; Risk Mgmt<span
																		class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="regulatory_compliance_risk_mgmt"
																		value="Regulatory Compliance & Risk Mgmt"
																		id="regulatory_compliance_risk_mgmt" />
																</div>
															</div>
															<div class="ColumnCommonvendormarketingdataaggregators">
																<div class="control-group">
																	<label class="control-labelcheckbox"> Portfolio
																		Management Solutions<span class="required">*</span>
																	</label>&nbsp; <input type="checkbox" class="assetClass"
																		name="portfolio_management"
																		value="Portfolio Management Solutions"
																		id="portfolio_management" />
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
																	onchange="loadSecurityTypes(this.value);"
																	id="trading_capability_type">
																	<option value="-SELECT-" class="selectvalues">
																		-SELECT-</option>
																	<c:forEach var="assetClasses" items="${assetClasses}">
																		<option value="${assetClasses.description}">${assetClasses.description}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Vendor Profile Freshness<span
																	class="required">*</span></label> <select
																	name="vendor_profile_freshness"
																	id="vendor_profile_freshness">
																	<option selected="selected" value=""
																		class="selectvalues">-SELECT-</option>
																	<option>Today</option>
																	<option>Last One Week</option>
																	<option>Last One Month</option>
																	<option>Last One Year</option>
																</select>
															</div>

															<div class="control-group">
																<label>Vendor Support Coverage Region<span
																	class="required">*</span></label> <select
																	name="vendor_support_coverage_region"
																	id="vendor_support_coverage_region">
																	<option value="-SELECT-" class="selectvalues"
																		selected="selected">-SELECT-</option>
																	<c:forEach var="regions" items="${regions}">
																		<option value="${regions.name}">${regions.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Accessibility<span class="required">*</span></label>
																<select name="accessibility" multiple="multiple"
																	id="accessibility">
																	<option value="" class="selectvalues"
																		selected="selected">-SELECT-</option>
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
																<label>Vendor Region of Incorporation<span
																	class="required">*</span></label> <select
																	name="vendor_region_of_incorporation"
																	multiple="multiple" id="vendor_region_of_incorporation">
																	<option value="-SELECT-" class="selectvalues"
																		selected="selected">-SELECT-</option>
																	<c:forEach var="regions" items="${regions}">
																		<option value="${regions.name}">${regions.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Vendor Country of Incorporation<span
																	class="required">*</span></label> <select
																	name="vendor_country_of_incorporation"
																	multiple="multiple"
																	id="vendor_country_of_incorporation">
																	<option value="" class="selectvalues"
																		selected="selected">-SELECT-</option>
																	<c:forEach var="countries" items="${countries}">
																		<option value="${countries.name}">${countries.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group">
																<label>Suitability<span class="required">*</span></label>
																<select name="suitability" multiple="multiple"
																	id="suitability">
																	<option value="" class="selectvalues"
																		selected="selected">-SELECT-</option>
																	<option value="All Users">All Users</option>
																	<option value="Risk Managers">Risk Managers</option>
																	<option value="Backoffice & Middle Office Users">Backoffice
																		& Middle Office Users</option>
																	<option value="Portfolio Managers">Portfolio
																		Managers</option>
																	<option value="Retail/Corporat Banking Users">Retail/Corporat
																		Banking Users</option>
																	<option value="Regulation & Compliance Users">Regulation
																		& Compliance Users</option>
																	<option value="Research Analysts">Research
																		Analysts</option>
																	<option value="Others">Others</option>
																</select>
															</div>
															<div class="control-group" style="margin-top: 20px;">
																<label style="float: left; width: 274px;">Customizable
																	Calc. Model?<span class="required">*</span>
																</label> <input type="checkbox" id="data_attribute"
																	placeholder="Data Attribute" name="data_attribute"
																	class="m-wraptrading largeval"
																	style="width: auto !important; margin: 0;" />
															</div>
															<div class="control-group">
																<label>Vendor Support Coverage Time<span
																	class="required">*</span></label> <select
																	name="vendor_support_time" id="vendor_support_time">
																	<option selected="selected" value=""
																		class="selectvalues">-SELECT-</option>
																	<c:forEach var="supports" items="${supports}">
																		<option value="${supports.support_id}">${supports.name}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="control-group" style="margin-top: 10px;">
																<label style="float: left; width: 274px;">Real
																	time Market Data?<span class="required">*</span>
																</label> <input type="checkbox" id="real_time_market_data"
																	placeholder="Data Attribute"
																	name="real_time_market_data"
																	class="m-wraptrading largeval"
																	style="width: auto !important; margin: 0;" />
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
								+ "<label>Analytics Solutions Sub Type</label>"
								+ "<select name='" + assetType + "_analytics_solution_sub_type' multiple='multiple' id='" + assetType + "_analytics_solution_sub_type' style='height: 53px;'>"
								+ "<option selected='selected' value =''> -SELECT- </option>"
								+ "<c:forEach var='securityType' items='${securityTypes}'>"
								+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
								+ "</c:forEach>"
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Year of Operation</label>"
								+ "<select name='" + assetType + "_year_operation' multiple='multiple' id='" + assetType + "_year_operation'>"
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
								+ "<label>Cost Range</label>"
								+ "<select name='" + assetType + "_acquisition_cost_range' multiple='multiple' id='" + assetType + "_acquisition_cost_range'>"
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
																		<label>Analytics Solutions Sub Type</label>
																	</div>
																	<div class="control-group">
																		<label>Year of Operation</label>
																	</div>
																	<div class="control-group">
																		<label>Awards</label>
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
									+ "<select name='" + assetType + "_analytics_solution_sub_type' multiple='multiple' id='" + assetType + "_analytics_solution_sub_type'>"
									+ "<option selected='selected' value =''> -SELECT- </option>"
									+ "<c:forEach var='securityType' items='${securityTypes}'>"
									+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
									+ "</c:forEach>"
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "_year_operation' multiple='multiple' id='" + assetType + "_year_operation'>"
									+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
									+ "<option>Date (1970)</option>"
									+ "<option>Last one week</option>"
									+ "<option>Last one month</option>"
									+ "<option>Last one year</option>"
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
									+ "<select name='" + assetType + "_acquisition_cost_range' multiple='multiple' id='" + assetType + "_acquisition_cost_range'>"
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
	<script src="<%=request.getContextPath() %>/resources/js/popup.js"
		type="text/javascript"></script>
	<script src="<%=request.getContextPath() %>/resources/js/jquery-min.js"
		type="text/javascript"></script>
	<script src="<%=request.getContextPath() %>/resources/js/modernizr.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath() %>/resources/js/bootstrap-min.js"
		type="text/javascript"></script>
	<!-- popup window plugins ends-->

	<!-- Add to table plugins start-->
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
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