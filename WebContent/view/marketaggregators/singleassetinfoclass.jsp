<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="form-wizard" style="background-color: #ECF0F1;">
	<div class="tab-content" >
			<div class="tab-pane" >
			
			<div><br/></div>
			 <div class="Row" style="width: 90%;">
				<div class="ColumnCommonvendorTradingApplicationVendor">
					<div class="control-group">
						<label class="control-labelappstra">Asset Class<span class="required">*</span></label>
						<div class="controls" style="margin-left: 160px;">
							<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
							     	<option value ="-SELECT-" class="selectvalues" selected="selected"> -SELECT- </option>
								     <c:forEach var="assetClasses" items="${assetClasses}">
								    	<option value="${assetClasses.asset_class_id}">${assetClasses.description}</option>
								 	 </c:forEach>
							 </select>
						</div>
					</div>
						<div class="control-grouptrading">
						<label class="control-labelappstra">Security type<span class="required">*</span></label>
						<div class="controls" style="margin-left: 160px;">
							<select name="securitytype"  id="assetClassSecurityMaps" multiple="multiple" style="height: 53px; width: 227px;">
						     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="assetClassSecurityMaps" items="${assetClassSecurityMaps}">
								    <option value="${assetClassSecurityMaps.securityType.security_type_id}">${assetClassSecurityMaps.securityType.name}</option>
								  </c:forEach>
					 		</select> 
					 		<div class="selectOptions">Choose one or more options</div>
						</div>
					</div>
					<div class="control-grouptrading">
						<label class="control-labelappstra">Data Attribute</label>
						<div class="controls" style="margin-left: 160px; width: 227px;">
							<input type="text" placeholder="Data Attribute" name="name" class="m-wrap largevaltradingapp" />
						</div>
					</div>
					<div class="control-grouptrading">
						<label class="control-labelappstra">Data Coverage Region</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="datacoverageregion"  onchange="loadCountryList(this.value);" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
					     	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
 											<c:forEach var="regions" items="${regions}">
							    	<option value="${regions.region_id}">${regions.name}</option>
							 	 </c:forEach>
						 		</select> 
					 		<div class="selectOptions">Choose one or more options</div>
						</div>
					</div>
					<div class="control-grouptrading">
						<label class="control-labelappstra">Data Coverage Country</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="datacoveragecountry" multiple="multiple" id="datacoveragecountry" style="height: 53px; width: 227px;">
					     	 	<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
					 		</select> 
					 		<div class="selectOptions">Choose one or more options</div>
						</div>
					</div>
					<div class="control-grouptrading">
					<label class="control-labelappstra">Data Coverage Exchange</label>
					<div class="controls" style="margin-left: 160px;">
						<select name="datacoverageexchange"  multiple="multiple" id="datacoverageexchange" style="height: 53px; width: 227px;">
					     	<option selected="selected" value ="-SELECT-"  class="selectvalues"> -SELECT- </option>
						     <c:forEach var="exchanges" items="${exchanges}">
							    <option value="${exchanges.exchange_id}">${exchanges.name}</option>
							  </c:forEach>
					 	</select> 
					 	<div class="selectOptions">Choose one or more options</div>
					</div>
				</div>
				</div>
				<div class="ColumnCommonvendorpreTradingApplcationVendor">
				<div class="control-grouptradingapplicationforthirdcolumn">
						<label class="control-labelappstra">Vendor Region of Incorp</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="vendorregionofincorp"   multiple="multiple" id="vendorregionofincorp" style="width: 227px;">
							     <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="regionslist" items="${regionslist}">
							    	<option value="${regionslist.region_id}">${regionslist.name}</option>
							 	 </c:forEach>
							 </select>
						</div>
					</div>
					<div class="control-grouptradingapplicationforthirdcolumn">
						<label class="control-labelappstra">Vendor Country of Incorp</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="vendorcountryofincorp" id="regionCountryMapsinfo" multiple="multiple" style="width: 227px;">
							     <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <c:forEach var="countries" items="${countries}">
								    <option value="${countries.country_id}">${countries.name}</option>
								  </c:forEach>
							 </select>
						</div>
					</div>
				<div class="control-grouptradingapplicationforthirdcolumn">
						<label class="control-labelappstra">Vendor Profile Freshness</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="vendorprofilefreshness"  id="vendorprofilefreshness" style="width: 227px;">
							     <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <option>today</option>
							     <option>Last one week</option>
							     <option>Last one month</option>
							     <option>Last one year</option>
							 </select>
						</div>
					</div>
					<div class="control-grouptradingapplicationforthirdcolumn">
						<label class="control-labelappstra">Vendor Year of Operation</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="vendoryearoperation"  id="vendoryearoperation" style="width: 227px;">
							     <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
							     <option>Date (1970)</option>
							     <option>Last one week</option>
							     <option>Last one month</option>
							     <option>Last one year</option>
							 </select>
						</div>
					</div>
					<div class="control-grouptradingapplicationforthirdcolumn">
						<label class="control-labelappstra">Vendor Support Coverage Time</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="vendorsupporttime"  id="vendorsupporttime" style="width: 227px;">
								<option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								<c:forEach var="supports" items="${supports}">
							    	<option value="${supports.support_id}">${supports.name}</option>
							 	</c:forEach>
							 </select>
						</div>
					</div>
					<div class="control-grouptradingapplicationforthirdcolumn">
						<label class="control-labelappstra">Search Keywords</label>
						<div class="controls" style="margin-left: 160px; width: 227px;">
							<input type="text" id="name" placeholder="Data Attribute" name="name" class="m-wrap largevaltradingapp" />
						</div>
					</div>
					<div class="control-grouptradingapplicationforthirdcolumn">
						<label class="control-labelappstra">Awards</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="awards"  id="awards" style="width: 227px;">
							     <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								 <c:forEach var="awards" items="${awards}">
								     <option value="${awards.award_id}">${awards.name}</option>
								 </c:forEach>
							 </select>
						</div>
					</div>
					<div class="control-grouptradingapplicationforthirdcolumn">
						<label class="control-labelappstra">Data Acquisition Cost Range</label>
						<div class="controls" style="margin-left: 160px;">
							<select name="acquisitioncostrange" id="acquisitioncostrange" style="width: 227px;">
							     <option selected="selected" value ="-SELECT-" class="selectvalues"> -SELECT- </option>
								 <c:forEach var="costs" items="${costs}">
								     <option value="${costs.cost_id}">${costs.range}</option>
								 </c:forEach>
							 </select>
						</div>
					</div>
				</div>
				
			</div>
	<div><br/></div>
	<div class="form-actions clearfix">
		<div class="se" style="padding-left: 150px;">
			<input type="submit" value="Search" class="btn" />
			<input type="reset" value="Reset" class="btn" />
		</div>
		<br>
		<div class="se" style="padding-left: 150px;">
			<input type="submit" value="Save Search" class="btn" />
			<input type="text" value="Search Name" name="saveSearchName" id="saveSearchName" />
		</div>
		</div>
		</div>
  </div>
</div>