<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="form-wizard">
		<div class="tab-content" style="background-color: white;">
			<!-- Support coverage start --> 
				<div class="tab-pane active" >
				<div class="Rowtableinfovaltradingapp">
					<div class="ColumnCommonmyprofiletradingapplication" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Search Vendor for Multiple TA Type</a> </div></div>
				</div>
				<div><br/></div>
				 <div class="Row">
					  <div class="ColumnCommonvendor">
						<div class="control-group">
							  <label class="control-label">Asset Class<span class="required">*</span></label>
						</div>
					</div> 
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Equities<span class="required">*</span></label>&nbsp;
							  <input type="checkbox" name="equities" value="Equities"  id="Equities">
						</div>
					</div>
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">FI<span class="required">*</span></label>&nbsp;
							  <input type="checkbox" name="fi" value="FI"  id="FI">
						</div>
					</div>
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Indices<span class="required">*</span></label>&nbsp;
							  <input type="checkbox" name="indices" value="Indices" id="Indices">
						</div>
					</div>
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Derivatives<span class="required">*</span></label>&nbsp;
							  <input type="checkbox" name="derivatives" value="Derivatives" id="Derivatives">
						</div>
					</div>
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">FX<span class="required">*</span></label>&nbsp;
							  <input type="checkbox" name="fx" value="FX"  id="FX">
						</div>
					</div>  
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">AI<span class="required">*</span></label>&nbsp;
							  <input type="checkbox" name="ai" value="AI"  id="AI">
						</div>
					</div> 
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">MISC<span class="required">*</span></label>&nbsp;
							  <input type="checkbox" name="misc" value="MISC"  id="MISC">
						</div>
					</div>    
				</div>
				<div><br/></div>
				<div class="Rowtableinfoval">
					<div class="ColumnCommonmyprofiletradingapplication" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Common fields for all asset class</a> </div></div>
				</div>
				<div class="Row">
					<div class="ColumnCommonvendorTradingApplicationVendor">
						<div class="control-group">
							<label class="control-labelappstra">Vendor Region of Incorp<span class="required">*</span></label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorregionofincorp"  id="vendorregionofincorp" style="width: 227px;">
								     <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regionslist" items="${regionslist}">
								    	<option value="${regionslist.region_id}">${regionslist.name}</option>
								 	 </c:forEach>
								 </select>
								 <div class="selectOptions">Choose one or more options</div>
							</div>
						</div>
							<div class="control-grouptrading">
							<label class="control-labelappstra">Vendor Country of Incorp<span class="required">*</span></label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorcountryofincorp" multiple="multiple" id="vendorcountryofincorp" style="height: 53px; width: 227px;">
						     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="regions" items="${regions}">
							    	<option value="${regions.name}">${regions.name}</option>
							 	 </c:forEach>
						 		</select> 
						 		<div class="selectOptions">Choose one or more options</div>
							</div>
						</div>
						<div class="control-grouptrading">
							<label class="control-labelappstra">Search Keywords</label>
							<div class="controls" style="margin-left: 178px; width: 227px;">
								<input type="text" id="searchkeywordname" placeholder="Data Attribute" name="searchkeywordname" class="m-wrap largevaltradingapp" />
							</div>
						</div>
					</div>
					<div class="ColumnCommonvendorpreTradingApplcationVendormultiasset">
					  <div class="control-grouptrading">
							<label class="control-labelappstra">Vendor Support Coverage Region<span class="required">*</span></label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorsupportregion"  id="vendorsupportregion" style="height: 53px; width: 227px;">
						     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="regions" items="${regions}">
							    	<option value="${regions.name}">${regions.name}</option>
							 	 </c:forEach>
						 		</select> 
							</div>
						</div>
						<div class="control-grouptrading">
							<label class="control-labelappstra">Vendor Profile Freshness<span class="required">*</span></label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorprofilefreshness"  id="vendorprofilefreshness" style="height: 53px; width: 227px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <option>today</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
							</div>
						</div>
						<div class="control-grouptrading">
							<label class="control-labelappstra">Vendor Support Coverage Time<span class="required">*</span></label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorsupporttime"  id="vendorsupporttime" style="height: 53px; width: 227px;">
						     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								<c:forEach var="supports" items="${supports}">
							    	<option value="${supports.support_id}">${supports.name}</option>
							 	</c:forEach>
						 		</select> 
							</div>
						</div>
					</div>
					
					</div>
					<div><br/></div>
					<div class="Row">
					  <div class="ColumnCommontradingapp" id="commonarea">
					  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
					  <div><br/></div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Security Types<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Region<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Country<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Exchange<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Attribute<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Vendor Year of Operation<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Awards<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Acquisition Cost Range<span class="required">*</span></label>
						</div>
						</div>
						<div class="ColumnCommontradingappinterestratemarket" id="equities" >
						<div class="lable_header" style="margin: -9px -11px -8px 14px;">Equities</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="eqsecuritytype" multiple="multiple" id="assetClassEquiSecurityMaps" style="height: 53px;">
						     	    <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="assetClassEquiSecurityMaps" items="${assetClassEquiSecurityMaps}">
									    <option value="${assetClassEquiSecurityMaps.securityType.security_type_id}">${assetClassEquiSecurityMaps.securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="eqdatacoverageregion" multiple="multiple" id="eqdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.region_id}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="eqdatacoveragecountry" multiple="multiple" id="eqdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="eqdatacoverageexchange" multiple="multiple" id="eqdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.exchange_id}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="eqdataattribute" placeholder="Data Attribute" name="eqdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="eqvendoryearoperation" multiple="multiple" id="eqvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqawards" multiple="multiple" id="eqawards" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqacquisitioncostrange" multiple="multiple" id="eqacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.cost_id}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						</div>
						<div class="ColumnCommontradingappinterestrateetfmarket" id="fi">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">Fixed Income</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="fisecuritytype" multiple="multiple" id="assetClassFISecurityMaps" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
								     <c:forEach var="assetClassFISecurityMaps" items="${assetClassFISecurityMaps}">
									    <option value="${assetClassFISecurityMaps.securityType.security_type_id}">${assetClassFISecurityMaps.securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="eqdatacoverageregion" multiple="multiple" id="eqdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.region_id}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="eqdatacoveragecountry" multiple="multiple" id="eqdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="eqdatacoverageexchange" multiple="multiple" id="eqdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.exchange_id}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="eqdataattribute" placeholder="Data Attribute" name="eqdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="eqvendoryearoperation" multiple="multiple" id="eqvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqawards" multiple="multiple" id="eqawards" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqacquisitioncostrange" multiple="multiple" id="eqacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.cost_id}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 </div>
						 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="indices">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">Indices</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="indicessecuritytype" multiple="multiple" id="assetClassIndicesSecurityMaps" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
								     <c:forEach var="assetClassIndicesSecurityMaps" items="${assetClassIndicesSecurityMaps}">
									    <option value="${assetClassIndicesSecurityMaps.securityType.security_type_id}">${assetClassIndicesSecurityMaps.securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="eqdatacoverageregion" multiple="multiple" id="eqdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.region_id}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="eqdatacoveragecountry" multiple="multiple" id="eqdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="eqdatacoverageexchange" multiple="multiple" id="eqdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.exchange_id}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="eqdataattribute" placeholder="Data Attribute" name="eqdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="eqvendoryearoperation" multiple="multiple" id="eqvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqawards" multiple="multiple" id="eqawards" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqacquisitioncostrange" multiple="multiple" id="eqacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.cost_id}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 </div>
						 <div class="ColumnCommontradingappinterestrateetfxfmarket" id="derivatives">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">Derivatives</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
								     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
						     			<option value ="-SELECT-"> Common-Stocks </option>
						     			<option value ="-SELECT-"> Preferreds</option>
						     			<option value ="-SELECT-"> Warrants/Rights </option>
						     			<option value ="-SELECT-"> CFDs </option>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="eqdatacoverageregion" multiple="multiple" id="eqdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.region_id}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="eqdatacoveragecountry" multiple="multiple" id="eqdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="eqdatacoverageexchange" multiple="multiple" id="eqdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.exchange_id}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="eqdataattribute" placeholder="Data Attribute" name="eqdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="eqvendoryearoperation" multiple="multiple" id="eqvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqawards" multiple="multiple" id="eqawards" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqacquisitioncostrange" multiple="multiple" id="eqacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.cost_id}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	
						 </div>
					</div>
					<div><br/></div>
					<div class="Row">
					  <div class="ColumnCommontradingapp" id="commonareainfo">
					  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
					  <div><br/></div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Security Types<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Region<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Country<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Exchange<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Attribute<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Vendor Year of Operation<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Awards<span class="required">*</span></label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Acquisition Cost Range<span class="required">*</span></label>
						</div>
						</div>
						<div class="ColumnCommontradingappinterestratemarket" id="fx" >
						<div class="lable_header" style="margin: -9px -11px -8px 14px;">FX</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="eqsecuritytype" multiple="multiple" id="assetClassEquiSecurityMaps" style="height: 53px;">
						     	    <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="assetClassEquiSecurityMaps" items="${assetClassEquiSecurityMaps}">
									    <option value="${assetClassEquiSecurityMaps.securityType.security_type_id}">${assetClassEquiSecurityMaps.securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="eqdatacoverageregion" multiple="multiple" id="eqdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.region_id}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="eqdatacoveragecountry" multiple="multiple" id="eqdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="eqdatacoverageexchange" multiple="multiple" id="eqdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.exchange_id}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="eqdataattribute" placeholder="Data Attribute" name="eqdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="eqvendoryearoperation" multiple="multiple" id="eqvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqawards" multiple="multiple" id="eqawards" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqacquisitioncostrange" multiple="multiple" id="eqacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.cost_id}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						</div>
						<div class="ColumnCommontradingappinterestrateetfmarket" id="ai">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">AI</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="fisecuritytype" multiple="multiple" id="assetClassFISecurityMaps" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
								     <c:forEach var="assetClassFISecurityMaps" items="${assetClassFISecurityMaps}">
									    <option value="${assetClassFISecurityMaps.securityType.security_type_id}">${assetClassFISecurityMaps.securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="eqdatacoverageregion" multiple="multiple" id="eqdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.region_id}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="eqdatacoveragecountry" multiple="multiple" id="eqdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="eqdatacoverageexchange" multiple="multiple" id="eqdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.exchange_id}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="eqdataattribute" placeholder="Data Attribute" name="eqdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="eqvendoryearoperation" multiple="multiple" id="eqvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqawards" multiple="multiple" id="eqawards" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqacquisitioncostrange" multiple="multiple" id="eqacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.cost_id}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 </div>
						 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="misc">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">MISC</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="indicessecuritytype" multiple="multiple" id="assetClassIndicesSecurityMaps" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" > -SELECT- </option>
								     <c:forEach var="assetClassIndicesSecurityMaps" items="${assetClassIndicesSecurityMaps}">
									    <option value="${assetClassIndicesSecurityMaps.securityType.security_type_id}">${assetClassIndicesSecurityMaps.securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="eqdatacoverageregion" multiple="multiple" id="eqdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.region_id}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="eqdatacoveragecountry" multiple="multiple" id="eqdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="eqdatacoverageexchange" multiple="multiple" id="eqdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.exchange_id}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="eqdataattribute" placeholder="Data Attribute" name="eqdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="eqvendoryearoperation" multiple="multiple" id="eqvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqawards" multiple="multiple" id="eqawards" style="height: 53px;">
							     	 <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="eqacquisitioncostrange" multiple="multiple" id="eqacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.cost_id}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 </div>
					</div>
	<div><br/></div>
	<div class="form-actions clearfix">
		<div class="se" style="padding-left: 150px;">
		<input type="submit" value="Search" class="btn" />
		<input type="reset" value="Reset" class="btn" />
	</div>
	</div>
	</div>
  </div>
</div>