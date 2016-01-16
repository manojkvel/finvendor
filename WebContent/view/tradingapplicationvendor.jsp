<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page import="com.finvendor.util.RequestConstans"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
	<title>Fin Vendor | Vendor</title>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<jsp:include page="common/dashboardheader.jsp" ></jsp:include>
	<div class="container">  
    <div class="text_area"><div class="text_arw"> <a href="<%=request.getContextPath()%>/<%=RequestConstans.Vendor.VENDOR_MY_PROFILE%>?RaYUnA=${l:encrypt(username)}"> Trading Application (TA) Vendor</a> </div><div class="arw"> <img src="<%=request.getContextPath() %>/resources/images/arw.png"  width="22" height="16" /></div> <div class="text_arw">Information</div></div>
        <div class="control-group">
        	<input type="radio" name="radios"  value="savetradingappsearchbutton" id="savetradingappsearchbutton">
			<label class="control-label" style="margin-left: 21px; margin-top: -15px;">Search Using Saved Searches<span class="required">*</span></label>
		</div>
        <div class="control-groupcheckingforradiobuttons">
        	<input type="radio" name="radios"  value="singletradingapplicationbutton" id="singletradingapplicationbutton">
			<label class="control-label" style="margin-left: 21px; margin-top: -15px;">Search Vendor for Single TA Type<span class="required">*</span></label>
			<input type="radio" name="radios"  value="multitradingapplicationbutton" id="multitradingapplicationbutton" style="margin-left: 371px; margin-top: -49px;">
			<label class="control-label" style="margin-left: 393px; margin-top: -45px;">Search Vendor for Multiple TA Type<span class="required">*</span></label>
		</div>
        <div class="row-fluid">
	   <div class="span12">
          <div class="row-fluid service-box">
			<div class="row-fluid">
						<div class="span12">
						<div class="portlet box blue" id="form_wizard_1">
							<div class="portlet-title">
							</div>
							  <div class="portlet-body form">
							  
							  <!-- Search using saved search starts here -->
							  <div id="savedsearchtradingapplicationform">
								<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
											<!-- Support coverage start --> 
												<div class="tab-pane active">
												<div><br/></div>
												 <div class="Row">
													<div class="ColumnCommonvendor">
														<div class="control-group">
															<label class="control-label">Trade Asset Class<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
													</div>
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se" style="padding-left: 150px;">
										<input type="reset" value="Search" class="btn" />
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
										</div>
								</div>
								</form>
								</div>
								<!-- Search using saved search end here -->
								
								<!-- Single Trading Vendor Start here -->
								<div id="searchsingletradingapplicationform">
									<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
									<div class="form-wizard">
										<div class="tab-content" style="background-color: white;">
												<div class="tab-pane active" >
												<div class="Rowtableinfoval">
													<div class="ColumnCommonmyprofiletradingapplication" id="change"> <div class="lable_header" id="interdiv"> <a id="ancho" href="#tab1" class="lable_header" data-toggle="tab"  onclick="activeMode('${personaldetails}');">Search Vendor for Single TA Type</a> </div></div>
												</div>
												<div><br/></div>
												 <div class="Row">
													<div class="ColumnCommonvendorTradingApplicationVendor">
														<div class="control-group">
															<label class="control-labelappstra">Asset Class<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
															<div class="control-grouptrading">
															<label class="control-labelappstra">Trade Asset Sub Class<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Trading Capability Type<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Trade Execution Type<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
														<label class="control-labelappstra">Coverage Region<span class="required">*</span></label>
														<div class="controls" style="margin-left: 160px;">
															<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	</select> 
														 	<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-grouptrading">
														<label class="control-labelappstra">Coverage Country<span class="required">*</span></label>
														<div class="controls" style="margin-left: 160px;">
															<select name="datacoveragecountry" multiple="multiple" id="datacoveragecountry" style="height: 53px; width: 227px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="countries" items="${countries}">
																    <option value="${countries.name}">${countries.name}</option>
																  </c:forEach>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-grouptrading">
														<label class="control-labelappstra"> Coverage Exchange<span class="required">*</span></label>
														<div class="controls" style="margin-left: 160px;">
															<select name="datacoverageexchange"  multiple="multiple" id="datacoverageexchange" style="height: 53px; width: 227px;">
														     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
														     <c:forEach var="exchanges" items="${exchanges}">
															    <option value="${exchanges.name}">${exchanges.name}</option>
															  </c:forEach>
														  </select>
														  <div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													<div class="control-grouptrading">
														<label class="control-labelappstra">Darkpool Access<span class="required">*</span></label>
														<div class="controls" style="margin-left: 160px;">
															<select name="datacoveragecountry" multiple="multiple" id="datacoveragecountry" style="height: 100px;width: 227px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																  <option value ="-SELECT-"> Access to independent Darkpools (?) </option>
																  <option value ="-SELECT-"> Access to Broker-dealer Darkpools (?) </option>
																  <option value ="-SELECT-"> Access to Consortium-owned darkpools (?) </option>
																  <option value ="-SELECT-"> Access to Exchange-owned darkpools (?) </option>
																  <option value ="-SELECT-"> Access to Darkpool Aggregators(?) </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpreTradingApplcationVendor">
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Order Type<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Suitability<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
													<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Software type<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Region of Incorp<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Country of Incorp<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Profile Freshness<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Year of Operation<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Search Keywords<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px; width: 227px;">
																<input type="text" id="dataattribute" placeholder="Data Attribute" name="dataattribute" class="m-wrap largevaltradingapp" />
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Coverage Time<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Awards<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Cost Range<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
														<div class="control-grouptradingapplicationforthirdcolumn">
															<label class="control-labelappstra">Existing User<span class="required">*</span></label>
															<div class="controls" style="margin-left: 160px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
													</div>
													
												</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se" style="padding-left: 150px;">
										<input type="reset" value="Search" class="btn" />
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
										</div>
								</div>
								</form>
								</div>
								<!-- Single Trading Vendor Start here -->
								
								<!-- Multi Trading Vendor Start here -->
								<div id="searchmultitradingapplicationform">
									<form action="#" class="form-horizontal" id="submit_form" method="post" enctype="multipart/form-data">
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
													<div class="ColumnCommonvendortradingapplication">
														<div class="control-group">
															  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Credit/Interest Rate Market<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="CreditRateMarket" value="CreditRateMarket"  id="CreditRateMarket">
														</div>
													</div>
													<div class="ColumnCommonvendortradingapplication">
														<div class="control-group">
															  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Equity/ETF Market<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="etfmarket" value="ETFMarket"  id="ETFMarket">
														</div>
													</div>
													<div class="ColumnCommonvendortradingapplication">
														<div class="control-group">
															  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">Derivative Market<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="derivativemarket" value="DerivativeMarket"  id="DerivativeMarket">
														</div>
													</div>
													<div class="ColumnCommonvendortradingapplication">
														<div class="control-group">
															  <label class="control-labelcheckbox" style="font-family: Raleway, sans-serif; font-size: 13px;">FX Market<span class="required">*</span></label>&nbsp;
															  <input type="checkbox" name="fxmarket" value="FXMarket"  id="FXMarket">
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
															<label class="control-labelappstra">Trading Capability Type<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="assetclass" onchange="loadSecurityTypes(this.value);" id="assetclass" style="width: 227px;">
																     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																     <c:forEach var="assetClasses" items="${assetClasses}">
																    	<option value="${assetClasses.description}">${assetClasses.description}</option>
																 	 </c:forEach>
																 </select>
															</div>
														</div>
															<div class="control-grouptrading">
															<label class="control-labelappstra">Dropdown as Multi select option available<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Order Type<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Accessibility<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Suitability<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
														<label class="control-labelappstra">Darkpool Access<span class="required">*</span></label>
														<div class="controls" style="margin-left: 175px;">
															<select name="datacoveragecountry" multiple="multiple" id="datacoveragecountry" style="height: 100px;width: 227px;">
															     <option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
																  <option value ="-SELECT-"> Access to independent Darkpools (?) </option>
																  <option value ="-SELECT-"> Access to Broker-dealer Darkpools (?) </option>
																  <option value ="-SELECT-"> Access to Consortium-owned darkpools (?) </option>
																  <option value ="-SELECT-"> Access to Exchange-owned darkpools (?) </option>
																  <option value ="-SELECT-"> Access to Darkpool Aggregators(?) </option>
													   		</select>
													   		<div class="selectOptions">Choose one or more options</div>
														</div>
													</div>
													</div>
													<div class="ColumnCommonvendorpreTradingApplcationVendormultiasset">
													  <div class="control-grouptrading">
															<label class="control-labelappstra">Software type<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Region of Incorp<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Country of Incorp<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Profile Freshness<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Support Coverage Region<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
														<div class="control-grouptrading">
															<label class="control-labelappstra">Vendor Support Coverage Time<span class="required">*</span></label>
															<div class="controls" style="margin-left: 175px;">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px; width: 227px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 		<div class="selectOptions">Choose one or more options</div>
															</div>
														</div>
													</div>
													
													</div>
													<div><br/></div>
													<div class="Row">
													  <div class="ColumnCommontradingapp" id="tradingmulticommonarea">
													  <div class="lable_header_tradingapp"  style="margin: -9px -12px -9px -1px;">Common labels</div>
													  <div><br/></div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Trade Asset Sub Class<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Coverage Region<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Coverage Country<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Coverage Exchange<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Year of Operation<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Awards<span class="required">*</span></label>
														</div>
														<div class="control-group">
															<label class="control-labeltradingapp" style="font-family: Raleway, sans-serif; font-size: 13px;">Cost Range<span class="required">*</span></label>
														</div>
														</div>
														<div class="ColumnCommontradingappinterestratemarket" id="creditratemarket" >
														<div class="lable_header" style="margin: -9px -11px -8px 14px;">Credit Rate</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	 <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														</div>
														<div class="ColumnCommontradingappinterestrateetfmarket" id="etfmarket">
														<div class="lable_header"  style="margin: -9px -11px -8px 14px;">ETF Market</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	 <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 		
														 </div>
														 <div class="ColumnCommontradingappinterestrateetderivatefmarket" id="derivativemarket">
														<div class="lable_header"  style="margin: -9px -11px -8px 14px;">Derivative Market</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	 <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 </div>
														 <div class="ColumnCommontradingappinterestrateetfxfmarket" id="fxmarket">
														<div class="lable_header"  style="margin: -9px -11px -8px 14px;">FX Market</div>
														<div><br/></div>
														   <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
															</div>
															<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 	 	</select> 
														 	 </div>
														 	 <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														    </div>
														    <div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 	<div class="control-group">
																<select name="datacoverageregion" multiple="multiple" id="datacoverageregion" style="height: 53px;">
														     	<option value ="-SELECT-" class="selectvalues"> -SELECT- </option>
															     <c:forEach var="regions" items="${regions}">
															    	<option value="${regions.name}">${regions.name}</option>
															 	 </c:forEach>
														 		</select> 
														 	</div>
														 </div>
													</div>
									<div><br/></div>
									<div class="form-actions clearfix">
										<div class="se" style="padding-left: 150px;">
										<input type="reset" value="Search" class="btn" />
										<input type="reset" value="Reset" class="btn" />
									</div>
									</div>
									</div>
										</div>
										</div>
										</form>
								</div>
								
								</div>
								<!-- Multi Trading Vendor Start here -->
								
								
							</div> 
						</div>
					</div>
				</div>
				</div>
				</div>
    </div>
    <!-- END CONTAINER -->
    <jsp:include page="common/footer.jsp"></jsp:include>
  
<!-- popup window plugins start-->
<script src="<%=request.getContextPath() %>/resources/js/popup.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/jquery-min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/modernizr.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/bootstrap-min.js" type="text/javascript"></script>
 <!-- popup window plugins ends-->

<!-- Add to table plugins start-->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.tabletojson.js"></script>
<!-- Add to table plugins end here-->

<!-- Date plugins start-->
		<script>
		  $(function() {
		    $( "#inputField" ).datepicker();
		    $( "#inputField1" ).datepicker();
		  });
	  </script>
	  <script src="//code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
	  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js" type="text/javascript"></script>
  
  <!-- Date plugins start-->
  
</body>
</html>
