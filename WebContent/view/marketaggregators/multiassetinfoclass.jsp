<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div id="market_data_aggregrator_search" class="custom_form">
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
							<input type="checkbox" class="assetClass"
							name="assetClassChk" value="FI" id="fi" />
							<label>Fixed Income</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Indices"
							id="indices" />
							<label>Indices</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="MISC" id="misc" />
							<label>Misc</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="Derivatives"
							id="derivatives" />
							<label>Derivatives</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="FX" id="fx" />
							<label>FX</label>
						</li>
						<li>
							<input type="checkbox"
							class="assetClass" name="assetClassChk" value="AI" id="ai" />
							<label>AI</label>
						</li>
					</ul>
				</div>

				<div class="common_fields_for_asset_class">
					<h3>Common Fields for all asset class <span class="fa fa-chevron-up"></span></h3>
					<ul>
						<li>
							<select class="selectpicker select_multiple" name="vendorregionofincorp" id="vendorregionofincorp"
							multiple="multiple">
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
						function singleAssetClass(assetType) {
							console.log("assetType : " + assetType);
							$('#singleAssetFields').html('');
							var singleAssetData = "<h3>"
							+ $("#" + assetType).siblings().html()
							+ "</h3>"
							+ "<ul>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "securitytype' id='" + assetType + "securitytype' multiple='multiple'>"
							+ "<c:forEach var='securityType' items='${securityTypes}'>"
							+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Security Types</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "datacoverageregion' id='" + assetType + "datacoverageregion' multiple='multiple'>"
							+ "<c:forEach var='regions' items='${regions}'>"
							+ "<option value='${regions.name}'>${regions.name}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Data Coverage Region</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "datacoveragecountry' id='" + assetType + "datacoveragecountry' multiple='multiple'>"
							+ "<c:forEach var='countries' items='${countries}'>"
							+ "<option value='${countries.name}'>${countries.name}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Data Coverage Country</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "datacoverageexchange' id='" + assetType + "datacoverageexchange' multiple='multiple'>"
							+ "<c:forEach var='exchanges' items='${exchanges}'>"
							+ "<option value='${exchanges.name}'>${exchanges.name}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Data Coverage Exchange</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "vendoryearoperation' id='" + assetType + "vendoryearoperation' multiple='multiple'>"
							+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
							<%--
							+ "<option>Date (1970)</option>"
							+ "<option>Last one week</option>"
							+ "<option>Last one month</option>"
							+ "<option>Last one year</option>"
							--%>
							+ "</select>"
							+ "<label class='default_select'>Vendor Year of Operation</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "awards' id='" + assetType + "awards' multiple='multiple'>"
							+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
							<%--
							+ "<c:forEach var='awards' items='${awards}'>"
							+ "<option value='${awards.award_id}'>${awards.name}</option>"
							+ "</c:forEach>"
							--%>
							+ "</select>"
							+ "<label class='default_select'>Awards</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "acquisitioncostrange' id='" + assetType + "acquisitioncostrange' multiple='multiple'>"
							+ "<c:forEach var='costs' items='${costs}'>"
							+ "<option value='${costs.range}'>${costs.range}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Data Acquisition Cost Range</label>"
							+ "</li>"
							+ "</ul>"
							$('#singleAssetFields').append(singleAssetData);
						}
					</script>
				</div>
				<div class="multisearch" id="multipleAsset">
					<script type="text/javascript">
						var multipleAssetData = '';
						function multipleAssetClass(assetType) {
							console.log("assetType : " + assetType);
							$('#multipleAsset #multipleAssetFields').html('');
							multipleAssetData = multipleAssetData
									+ "<h3>"
							+ $("#" + assetType).siblings().html()
							+ "</h3>"
							+ "<ul>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "securitytype' id='" + assetType + "securitytype' multiple='multiple'>"
							+ "<c:forEach var='securityType' items='${securityTypes}'>"
							+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Security Types</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "datacoverageregion' id='" + assetType + "datacoverageregion' multiple='multiple'>"
							+ "<c:forEach var='regions' items='${regions}'>"
							+ "<option value='${regions.name}'>${regions.name}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Data Coverage Region</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "datacoveragecountry' id='" + assetType + "datacoveragecountry' multiple='multiple'>"
							+ "<c:forEach var='countries' items='${countries}'>"
							+ "<option value='${countries.name}'>${countries.name}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Data Coverage Country</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "datacoverageexchange' id='" + assetType + "datacoverageexchange' multiple='multiple'>"
							+ "<c:forEach var='exchanges' items='${exchanges}'>"
							+ "<option value='${exchanges.name}'>${exchanges.name}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Data Coverage Exchange</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "vendoryearoperation' id='" + assetType + "vendoryearoperation' multiple='multiple'>"
							+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
							<%--
							+ "<option>Date (1970)</option>"
							+ "<option>Last one week</option>"
							+ "<option>Last one month</option>"
							+ "<option>Last one year</option>"
							--%>
							+ "</select>"
							+ "<label class='default_select'>Vendor Year of Operation</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "awards' id='" + assetType + "awards' style='height: 53px;' multiple='multiple'>"
							+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
							<%--
							+ "<c:forEach var='awards' items='${awards}'>"
							+ "<option value='${awards.award_id}'>${awards.name}</option>"
							+ "</c:forEach>"
							--%>
							+ "</select>"
							+ "<label class='default_select'>Awards</label>"
							+ "</li>"
							+ "<li><select class='selectpicker select_multiple' name='" 
							+ assetType + "acquisitioncostrange' id='" + assetType + "acquisitioncostrange' multiple='multiple'>"
							+ "<c:forEach var='costs' items='${costs}'>"
							+ "<option value='${costs.range}'>${costs.range}</option>"
							+ "</c:forEach>"
							+ "</select>"
							+ "<label class='default_select'>Data Acquisition Cost Range</label>"
							+ "</li>"
							+ "</ul>"
							$('#multipleAssetFields').append(multipleAssetData);
						}
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