<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
							class="assetClass" name="assetClassChk" value="Equities"
							id="equities" />
					</div>
				</div>
				<div class="ColumnCommonvendormarketingdataaggregators">
					<div class="control-group">
						<label class="control-labelcheckbox"
							style="font-family: Raleway, sans-serif; font-size: 13px;">Fixed
							Income<span class="required">*</span>
						</label>&nbsp; <input type="checkbox" class="assetClass"
							name="assetClassChk" value="FI" id="fi" />
					</div>
				</div>
				<div class="ColumnCommonvendormarketingdataaggregators">
					<div class="control-group">
						<label class="control-labelcheckbox"
							style="font-family: Raleway, sans-serif; font-size: 13px;">Indices<span
							class="required">*</span></label>&nbsp; <input type="checkbox"
							class="assetClass" name="assetClassChk" value="Indices"
							id="indices" />
					</div>
				</div>
				<div class="ColumnCommonvendormarketingdataaggregators">
					<div class="control-group">
						<label class="control-labelcheckbox"
							style="font-family: Raleway, sans-serif; font-size: 13px;">MISC<span
							class="required">*</span></label>&nbsp; <input type="checkbox"
							class="assetClass" name="assetClassChk" value="MISC" id="misc" />
					</div>
				</div>
				<div class="ColumnCommonvendormarketingdataaggregators">
					<div class="control-group">
						<label class="control-labelcheckbox"
							style="font-family: Raleway, sans-serif; font-size: 13px;">Derivatives<span
							class="required">*</span></label>&nbsp; <input type="checkbox"
							class="assetClass" name="assetClassChk" value="Derivatives"
							id="derivatives" />
					</div>
				</div>
				<div class="ColumnCommonvendormarketingdataaggregators">
					<div class="control-group">
						<label class="control-labelcheckbox"
							style="font-family: Raleway, sans-serif; font-size: 13px;">FX<span
							class="required">*</span></label>&nbsp; <input type="checkbox"
							class="assetClass" name="assetClassChk" value="FX" id="fx" />
					</div>
				</div>
				<div class="ColumnCommonvendormarketingdataaggregators">
					<div class="control-group">
						<label class="control-labelcheckbox"
							style="font-family: Raleway, sans-serif; font-size: 13px;">AI<span
							class="required">*</span></label>&nbsp; <input type="checkbox"
							class="assetClass" name="assetClassChk" value="AI" id="ai" />
					</div>
				</div>
			</div>
			<div class="ColumnCommonvendor">
				<div class="control-group">
					<label class="control-label">Common fields for all asset
						class<span class="required">*</span>
					</label>
				</div>
			</div>
			<div class="Row" id="common-vendor-type">
				<div class="control-group">
					<label>Vendor Region of Incorporation<span class="required">*</span></label>
					<select name="vendorregionofincorp" id="vendorregionofincorp"
						multiple="multiple" style="width: 227px;">
						<option selected="selected" value="" class="selectvalues">
							-SELECT-</option>
						<c:forEach var="regionslist" items="${regionslist}">
							<option value="${regionslist.region_id}">${regionslist.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="control-group">
					<label>Vendor Country of Incorporation<span class="required">*</span></label>
					<select name="vendorcountryofincorp"
						multiple="multiple" id="vendorcountryofincorp"
						style="height: 53px; width: 227px;">
						<option value="" selected="selected" class="selectvalues">
							-SELECT-</option>
						<c:forEach var="countries" items="${countries}">
							<option value="${countries.country_id}">${countries.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="control-group">
					<label>Vendor Profile Freshness<span class="required">*</span></label>
					<select name="vendorprofilefreshness" id="vendorprofilefreshness"
						style="height: 53px; width: 227px;">
						<option selected="selected" value="" class="selectvalues">
							ANY</option>
						<%--
						<option>Today</option>
						<option>Last One Week</option>
						<option>Last One Month</option>
						<option>Last One Year</option>
						--%>
					</select>
				</div>
				<div class="control-group">
					<label>Vendor Support Coverage Time<span class="required">*</span></label>
					<select name="vendorsupporttime" id="vendorsupporttime"
						style="height: 53px; width: 227px;">
						<option selected="selected" value="" class="selectvalues">
							ANY</option>
						<%--
						<c:forEach var="supports" items="${supports}">
							<option value="${supports.support_id}">${supports.name}</option>
						</c:forEach>
						--%>
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
								+ "<label>Security Types</label>"
								+ "<select name='" + assetType + "securitytype' multiple='multiple' id='" + assetType + "securitytype' style='height: 53px;'>"
								+ "<option selected='selected' value =''> -SELECT- </option>"
								+ "<c:forEach var='securityType' items='${securityTypes}'>"
								+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
								+ "</c:forEach>"
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Data Coverage Region</label>"
								+ "<select name='" + assetType + "datacoverageregion' multiple='multiple' id='" + assetType + "datacoverageregion' style='height: 53px;'>"
								+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
								+ "<c:forEach var='regions' items='${regions}'>"
								+ "<option value='${regions.name}'>${regions.name}</option>"
								+ "</c:forEach>"
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Data Coverage Country</label>"
								+ "<select name='" + assetType + "datacoveragecountry' multiple='multiple' id='" + assetType + "datacoveragecountry' style='height: 53px;'>"
								+ "<option selected='selected' value =''> -SELECT- </option>"
								+ "<c:forEach var='countries' items='${countries}'>"
								+ "<option value='${countries.name}'>${countries.name}</option>"
								+ "</c:forEach>"
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Data Coverage Exchange</label>"
								+ "<select name='" + assetType + "datacoverageexchange' multiple='multiple' id='" + assetType + "datacoverageexchange' style='height: 53px;'>"
								+ "<option value =''> -SELECT- </option>"
								+ "<c:forEach var='exchanges' items='${exchanges}'>"
								+ "<option value='${exchanges.name}'>${exchanges.name}</option>"
								+ "</c:forEach>"
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Vendor Year of Operation</label>"
								+ "<select name='" + assetType + "vendoryearoperation' multiple='multiple' id='" + assetType + "vendoryearoperation' style='height: 53px;'>"
								+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
								<%--
								+ "<option>Date (1970)</option>"
								+ "<option>Last one week</option>"
								+ "<option>Last one month</option>"
								+ "<option>Last one year</option>"
								--%>
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Awards</label>"
								+ "<select name='" + assetType + "awards' multiple='multiple' id='" + assetType + "awards' style='height: 53px;'>"
								+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
								<%--
								+ "<c:forEach var='awards' items='${awards}'>"
								+ "<option value='${awards.award_id}'>${awards.name}</option>"
								+ "</c:forEach>"
								--%>
								+ "</select>"
								+ "</div>"
								+ "<div class='control-group'>"
								+ "<label>Data Acquisition Cost Range</label>"
								+ "<select name='" + assetType + "acquisitioncostrange' multiple='multiple' id='" + assetType + "acquisitioncostrange' style='height: 53px;'>"
								+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
								+ "<c:forEach var='costs' items='${costs}'>"
								+ "<option value='${costs.range}'>${costs.range}</option>"
								+ "</c:forEach>"
								+ "</select>"
								+ "</div>"
								<%--
								+ "<div class='control-group'>"
								+ "<label>Data Attribute</label>"
								+ "<div class='controls' style='margin-left: 0;'>"
								+ "<input type='text' id='" + assetType + "dataattribute' placeholder='Data Attribute' name='" + assetType + "dataattribute' class='m-wrap largevaltradingappmarketingaggregator' />"
								+ "</div>" 
								--%>
								+ "</div>" + "</div>" + "</div>";
						$('#singleAssetFields').append(singleAssetData);
					}
				</script>
			</div>

			<div class="multisearch" id="multipleAsset">
				<div class="Row">
					<div class="ColumnCommontradingapp" id="commonarea">
						<div class="control-group">
							<label>Security Types</label>
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
						<%--
						<div class="control-group">
							<label>Data Attribute</label>
						</div>
						--%>
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
									+ "<select name='" + assetType + "securitytype' multiple='multiple' id='" + assetType + "securitytype' style='height: 53px;'>"
									+ "<option selected='selected' value =''> -SELECT- </option>"
									+ "<c:forEach var='securityType' items='${securityTypes}'>"
									+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
									+ "</c:forEach>"
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "datacoverageregion' multiple='multiple' id='" + assetType + "datacoverageregion' style='height: 53px;'>"
									+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
									+ "<c:forEach var='regions' items='${regions}'>"
									+ "<option value='${regions.name}'>${regions.name}</option>"
									+ "</c:forEach>"
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "datacoveragecountry' multiple='multiple' id='" + assetType + "datacoveragecountry' style='height: 53px;'>"
									+ "<option selected='selected' value =''> -SELECT- </option>"
									+ "<c:forEach var='countries' items='${countries}'>"
									+ "<option value='${countries.name}'>${countries.name}</option>"
									+ "</c:forEach>"
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "datacoverageexchange' multiple='multiple' id='" + assetType + "datacoverageexchange' style='height: 53px;'>"
									+ "<option value =''> -SELECT- </option>"
									+ "<c:forEach var='exchanges' items='${exchanges}'>"
									+ "<option value='${exchanges.name}'>${exchanges.name}</option>"
									+ "</c:forEach>"
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "vendoryearoperation' multiple='multiple' id='" + assetType + "vendoryearoperation' style='height: 53px;'>"
									+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
									<%--
									+ "<option>Date (1970)</option>"
									+ "<option>Last one week</option>"
									+ "<option>Last one month</option>"
									+ "<option>Last one year</option>"
									--%>
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "awards' multiple='multiple' id='" + assetType + "awards' style='height: 53px;'>"
									+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
									<%--
									+ "<c:forEach var='awards' items='${awards}'>"
									+ "<option value='${awards.award_id}'>${awards.name}</option>"
									+ "</c:forEach>"
									--%>
									+ "</select>"
									+ "</div>"
									+ "<div class='control-group'>"
									+ "<select name='" + assetType + "acquisitioncostrange' multiple='multiple' id='" + assetType + "acquisitioncostrange' style='hei	ght: 53px;'>"
									+ "<option selected='selected' value ='' class='selectvalues'> -SELECT- </option>"
									+ "<c:forEach var='costs' items='${costs}'>"
									+ "<option value='${costs.range}'>${costs.range}</option>"
									+ "</c:forEach>"
									+ "</select>"
									+ "</div>"
									<%--
									+ "<div class='control-group'>"
									+ "<div class='controls' style='margin-left: 0;'>"
									+ "<input type='text' id='" + assetType + "dataattribute' placeholder='Data Attribute' name='" + assetType + "dataattribute' class='m-wrap largevaltradingappmarketingaggregator' />"
									+ "</div>" 
									--%>
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
					<input type="submit" value="Search" class="btn" id="search_vendor" />
					<input type="reset" value="Reset" class="btn" id="reset_vendor" />
				</div>
			</div>
		</div>
	</div>
</div>