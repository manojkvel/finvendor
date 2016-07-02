<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="form-wizard">
	<div class="tab-content" style="background-color: white;">
		<div class="tab-pane active" >
			<div><br/></div>
				<div class="Row">
					<div class="ColumnCommonvendor">
						<div class="control-group">
							  <label class="control-label">Asset Class<span class="required">*</span></label>
						</div>
					</div> 
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Equities</label>&nbsp;
							  <input type="checkbox" name="assetClassChk" value="Equities" id="Equities"/>
						</div>
					</div>
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Fixed Income</label>&nbsp;
							  <input type="checkbox" name="assetClassChk" value="FI" id="FI"/>
						</div>
					</div>
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Indices</label>&nbsp;
							  <input type="checkbox" name="assetClassChk" value="Indices" id="Indices"/>
						</div>
					</div>
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Derivatives</label>&nbsp;
							  <input type="checkbox" name="assetClassChk" value="Derivatives" id="Derivatives"/>
						</div>
					</div>
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">FX</label>&nbsp;
							  <input type="checkbox" name="assetClassChk" value="FX" id="FX"/>
						</div>
					</div>  
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">AI</label>&nbsp;
							  <input type="checkbox" name="assetClassChk" value="AI" id="AI"/>
						</div>
					</div> 
					<div class="ColumnCommonvendormarketingdataaggregators">
						<div class="control-group">
							  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">MISC</label>&nbsp;
							  <input type="checkbox" name="assetClassChk" value="MISC" id="MISC"/>
						</div>
					</div>    
				</div>
				<div><br/></div>
				<div class="Rowtableinfoval">
					<div class="ColumnCommonmyprofiletradingapplication" id="change"> 
						<div class="lable_header" id="interdiv">
							<a id="ancho" href="#tab1" class="lable_header" data-toggle="tab" onclick="activeMode('${personaldetails}');">Common fields for all asset class</a> 
						</div>
					</div>
				</div>
				<div class="Row">
					<div class="ColumnCommonvendorTradingApplicationVendor">
						<div class="control-group">
							<label class="control-labelappstra">Vendor Region of Incorporation</label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorregionofincorp" id="vendorregionofincorp" multiple="multiple" style="width: 227px;">
								     <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regionslist" items="${regionslist}">
								    	<option value="${regionslist.region_id}">${regionslist.name}</option>
								 	 </c:forEach>
								 </select>
								 <div class="selectOptions">Choose one or more options</div>
							</div>
						</div>
							<div class="control-grouptrading">
							<label class="control-labelappstra">Vendor Country of Incorporation</label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorcountryofincorp" multiple="multiple" id="vendorcountryofincorp" style="height: 53px; width: 227px;">
						     	<option value ="" selected="selected" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						 		<div class="selectOptions">Choose one or more options</div>
							</div>
						</div>			
					</div>
					<div class="ColumnCommonvendorpreTradingApplcationVendormultiasset">					  
						<div class="control-grouptrading">
							<label class="control-labelappstra">Vendor Profile Freshness</label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorprofilefreshness"  id="vendorprofilefreshness" style="height: 53px; width: 227px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <option>Today</option>
								     <option>Last One Week</option>
								     <option>Last One Month</option>
								     <option>Last One Year</option>
						 		</select> 
							</div>
						</div>
						<div class="control-grouptrading">
							<label class="control-labelappstra">Vendor Support Coverage Time</label>
							<div class="controls" style="margin-left: 175px;">
								<select name="vendorsupporttime"  id="vendorsupporttime" style="height: 53px; width: 227px;">
						     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								<c:forEach var="supports" items="${supports}">
							    	<option value="${supports.support_id}">${supports.name}</option>
							 	</c:forEach>
						 		</select> 
							</div>
						</div>
					</div>					
					</div>
					<div><br/></div>
					<div class="Row multisearch">
					  <div class="ColumnCommontradingapp" id="commonarea">
					  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
					  <div><br/></div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Security Types</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Region</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Country</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Exchange</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Attribute</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Vendor Year of Operation</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Awards</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Acquisition Cost Range</label>
						</div>
						</div>
						<div class="ColumnCommontradingappinterestratemarket" id="equities" >
						<div class="lable_header" style="margin: -9px -11px -8px 14px;">Equities</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="equitiessecuritytype" multiple="multiple" id="equitiessecuritytype" style="height: 53px;">
						     	    <option selected="selected" value =""> -SELECT- </option>
								    <c:forEach var="securityType" items="${securityTypes}">
									    <option value="${securityType.securityTypeId}">${securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="equitiesdatacoverageregion" multiple="multiple" id="equitiesdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.name}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="equitiesdatacoveragecountry" multiple="multiple" id="equitiesdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value =""> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.name}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="equitiesdatacoverageexchange" multiple="multiple" id="equitiesdatacoverageexchange" style="height: 53px;">
							     	 <option value ="" > -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.name}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="equitiesdataattribute" placeholder="Data Attribute" name="equitiesdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="equitiesvendoryearoperation" multiple="multiple" id="equitiesvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="equitiesawards" multiple="multiple" id="equitiesawards" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="equitiesacquisitioncostrange" multiple="multiple" id="equitiesacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.range}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						</div>
						<div class="ColumnCommontradingappinterestrateetfmarket" id="fi">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">Fixed Income</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="fisecuritytype" multiple="multiple" id="assetClassFISecurityMaps" style="height: 53px;">
							     	<option selected="selected" value ="" > -SELECT- </option>
								     <c:forEach var="securityType" items="${securityTypes}">
									    <option value="${securityType.securityTypeId}">${securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="fidatacoverageregion" multiple="multiple" id="fidatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.name}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="fidatacoveragecountry" multiple="multiple" id="fidatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.name}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="fidatacoverageexchange" multiple="multiple" id="fidatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.name}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="fidataattribute" placeholder="Data Attribute" name="fidataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="fivendoryearoperation" multiple="multiple" id="fivendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="fiawards" multiple="multiple" id="fiawards" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="fiacquisitioncostrange" multiple="multiple" id="fiacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.range}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 </div>
						 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="indices">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">Indices</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="indicesindicessecuritytype" multiple="multiple" id="indicesassetClassIndicesSecurityMaps" style="height: 53px;">
							     	<option selected="selected" value ="" > -SELECT- </option>
								     <c:forEach var="securityType" items="${securityTypes}">
									    <option value="${securityType.securityTypeId}">${securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="indicesdatacoverageregion" multiple="multiple" id="indicesdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.name}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="indicesdatacoveragecountry" multiple="multiple" id="indicesdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.name}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="indicesdatacoverageexchange" multiple="multiple" id="indicesdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.name}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="indicesdataattribute" placeholder="Data Attribute" name="indicesdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="indicesvendoryearoperation" multiple="multiple" id="indicesvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="indicesawards" multiple="multiple" id="indicesawards" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="indicesacquisitioncostrange" multiple="multiple" id="indicesacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.range}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 </div>
						 <div class="ColumnCommontradingappinterestrateetfxfmarket" id="derivatives">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">Derivatives</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="derivativesdatacoverageregion" multiple="multiple" id="derivativesdatacoverageregion" style="height: 53px;">
								     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
						     			<option value =""> Common-Stocks </option>
						     			<option value =""> Preferreds</option>
						     			<option value =""> Warrants/Rights </option>
						     			<option value =""> CFDs </option>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="derivativesdatacoverageregion" multiple="multiple" id="derivativesdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.name}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="derivativesdatacoveragecountry" multiple="multiple" id="derivativesdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.name}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="derivativesdatacoverageexchange" multiple="multiple" id="derivativesdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.name}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="derivativesdataattribute" placeholder="Data Attribute" name="derivativesdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="derivativesvendoryearoperation" multiple="multiple" id="derivativesvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="derivativesawards" multiple="multiple" id="derivativesawards" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="derivativesacquisitioncostrange" multiple="multiple" id="derivativesacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.range}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	
						 </div>
					</div>
					<div><br/></div>
					<div class="Row  multisearch">
					  <div class="ColumnCommontradingapp" id="commonareainfo">
					  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
					  <div><br/></div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Security Types</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Region</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Country</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Coverage Exchange</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Attribute</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Vendor Year of Operation</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Awards</label>
						</div>
						<div class="control-group">
							<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Data Acquisition Cost Range</label>
						</div>
						</div>
						<div class="ColumnCommontradingappinterestratemarket" id="fx" >
						<div class="lable_header" style="margin: -9px -11px -8px 14px;">FX</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="fxsecuritytype" multiple="multiple" id="fxsecuritytype" style="height: 53px;">
						     	    <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="securityType" items="${securityTypes}">
									    <option value="${securityType.securityTypeId}">${securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="fxdatacoverageregion" multiple="multiple" id="fxdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.name}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="fxdatacoveragecountry" multiple="multiple" id="fxdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.name}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="fxdatacoverageexchange" multiple="multiple" id="fxdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.name}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="fxdataattribute" placeholder="Data Attribute" name="fxdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="fxvendoryearoperation" multiple="multiple" id="fxvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="fxawards" multiple="multiple" id="fxawards" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="fxacquisitioncostrange" multiple="multiple" id="fxacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.range}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						</div>
						<div class="ColumnCommontradingappinterestrateetfmarket" id="ai">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">AI</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="aisecuritytype" multiple="multiple" id="aisecuritytype" style="height: 53px;">
							     	<option selected="selected" value ="" > -SELECT- </option>
								    <c:forEach var="securityType" items="${securityTypes}">
									    <option value="${securityType.securityTypeId}">${securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="aidatacoverageregion" multiple="multiple" id="aidatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.name}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="aidatacoveragecountry" multiple="multiple" id="aidatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.name}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="aidatacoverageexchange" multiple="multiple" id="aidatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.name}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="aidataattribute" placeholder="Data Attribute" name="aidataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="aivendoryearoperation" multiple="multiple" id="aivendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="aiawards" multiple="multiple" id="aiawards" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="aiacquisitioncostrange" multiple="multiple" id="aiacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.range}">${costs.range}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 </div>
						 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="misc">
						<div class="lable_header"  style="margin: -9px -11px -8px 14px;">MISC</div>
						<div><br/></div>
						   <div class="control-group">
								<select name="miscsecuritytype" multiple="multiple" id="miscsecuritytype" style="height: 53px;">
							     	<option selected="selected" value ="" > -SELECT- </option>
								    <c:forEach var="securityType" items="${securityTypes}">
									    <option value="${securityType.securityTypeId}">${securityType.name}</option>
									  </c:forEach>
						 		</select> 
							</div>
							<div class="control-group">
								<select name="miscdatacoverageregion" multiple="multiple" id="miscdatacoverageregion" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="regions" items="${regions}">
								    	<option value="${regions.name}">${regions.name}</option>
								 	 </c:forEach>
						 	 	</select> 
						 	 </div>
						 	 <div class="control-group">
								<select name="miscdatacoveragecountry" multiple="multiple" id="miscdatacoveragecountry" style="height: 53px;">
						     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.name}">${countries.name}</option>
								  </c:forEach>
						 		</select> 
						    </div>
						    <div class="control-group">
								<select name="miscdatacoverageexchange" multiple="multiple" id="miscdatacoverageexchange" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <c:forEach var="exchanges" items="${exchanges}">
									    <option value="${exchanges.name}">${exchanges.name}</option>
									  </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<div class="controls" style="margin-left: -2px; width: 227px;">
									<input type="text" id="miscdataattribute" placeholder="Data Attribute" name="miscdataattribute" class="m-wrap largevaltradingappmarketingaggregator" />
								</div>
						 	</div>
						 	<div class="control-group">
								<select name="miscvendoryearoperation" multiple="multiple" id="miscvendoryearoperation" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
								     <option>Date (1970)</option>
								     <option>Last one week</option>
								     <option>Last one month</option>
								     <option>Last one year</option>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="miscawards" multiple="multiple" id="miscawards" style="height: 53px;">
							     	 <option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="awards" items="${awards}">
									     <option value="${awards.award_id}">${awards.name}</option>
									 </c:forEach>
						 		</select> 
						 	</div>
						 	<div class="control-group">
								<select name="miscacquisitioncostrange" multiple="multiple" id="miscacquisitioncostrange" style="height: 53px;">
							     	<option selected="selected" value ="" class="selectvalues"> -SELECT- </option>
									 <c:forEach var="costs" items="${costs}">
									     <option value="${costs.range}">${costs.range}</option>
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