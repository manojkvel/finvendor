<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="l" uri="/WEB-INF/finvendor.tld"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<c:set var="zerovalue"
	value="<%=RequestConstans.ConstantValues.ZERO_VALUE%>">
</c:set>
<c:set var="marketAggregators"
	value="<%=RequestConstans.MarketAggregators.MULTI_ASSET_CLASS_SEARCH_RESULT%>">
</c:set>
<c:set var="researchReportProviders"
	value="<%=RequestConstans.ResearchReportProviders.MULTI_ASSET_CLASS_SEARCH_RESULT%>">
</c:set>
<c:set var="financialAnalytics"
	value="<%=RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>">
</c:set>
<c:set var="tradingApplication"
	value="<%=RequestConstans.TradingApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>">
</c:set>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>finvendor</title>
<style type="">
tbody:before {
	content: "-";
	display: block;
	line-height: 2em;
	color: transparent;
}
</style>
</head>
<body>
	<jsp:include page="common/dashboardheader.jsp"></jsp:include>
	<div class="container">
		<div class="text_area">
			<div class="text_arw ${result}">
			<c:choose>
				<c:when test="${result eq marketAggregators}">
					<a
					href="<%=request.getContextPath()%>/<%=RequestConstans.MarketAggregators.MARKETAGGREGATORS%>?RaYUnA=${l:encrypt(username)}">Market
					Data Vendors</a>
				</c:when>
				<c:when test="${result eq tradingApplication}">
					<a
					href="<%=request.getContextPath()%>/<%=RequestConstans.TradingApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>?RaYUnA=${l:encrypt(username)}">Trading Application Vendors</a>
				</c:when>
				<c:when test="${result eq financialAnalytics}">
					<a
					href="<%=request.getContextPath()%>/<%=RequestConstans.FinancialAnalyticsApplication.MULTI_ASSET_CLASS_SEARCH_RESULT%>?RaYUnA=${l:encrypt(username)}">Financial Analytics Vendors</a>
				</c:when>
				<c:when test="${result eq researchReportProviders}">
					<a
					href="<%=request.getContextPath()%>/<%=RequestConstans.ResearchReportProviders.MULTI_ASSET_CLASS_SEARCH_RESULT%>?RaYUnA=${l:encrypt(username)}">Research Reporting Vendors</a>
				</c:when>
			</c:choose>
			</div>
			<div class="arw">></div>
			<div class="text_arw">Result</div>
		</div>
		<div class="right_nav_area_1">
			<div class="right_nav_1">
				<ul>
					<!-- 
	 <li><a href="#">INDEX</a></li>
	 <li style="padding-left: 880px;"><a class="#" data-toggle="modal" href="#normalModal">Modify Search</a></li>
	  -->
					<li><a href="#" onclick="window.history.back()">Modify
							Search</a></li>
				</ul>
			</div>
			<div id="normalModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title btpopup">Single Asset Search</h4>
						</div>
						<div class="modal-body">
							<input type="file" name="fileUpload"
								class="m-wrap largefileuploadconspopup" id="fileUpload">&nbsp;&nbsp;&nbsp;&nbsp;<a
								class="fileupload btnpopup btn-default">Upload</a>
								<div id="selectedFiles"></div>
								<div>
									<br>
								</div> <a class="btnpopup btn-default" onclick="loadCheckBoxes();">Remove</a>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="portlet-body">
			<div id="assetResult">
				<c:choose>
					<c:when test="${not empty marketDataAggregatorsVendorSearchs && result eq marketAggregators}">
						<c:forEach var="marketDataAggregatorsVendorSearch"
							items="${marketDataAggregatorsVendorSearchs}">
							<div class="portlet box blue">
								<div class="portlet-title"></div>
								<div class="portlet-body">
									<div class="portlet-content">
										<div class="vendor-logo">
											<a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${marketDataAggregatorsVendorSearch.vendorName}">
											<img src="${pageContext.request.contextPath}/displayCompanyLogo/${marketDataAggregatorsVendorSearch.vendorName}" 
												alt="${assetCountries}"
												title="${marketDataAggregatorsVendorSearch.vendorCompany}" width="60" height="60" />
											</a>
										</div>
										<div class="vendor-overview">
											<h2><a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${marketDataAggregatorsVendorSearch.vendorName}">${marketDataAggregatorsVendorSearch.vendorCompany}</a></h2>
											<p>${marketDataAggregatorsVendorSearch.vendorCountry} | ${marketDataAggregatorsVendorSearch.vendorType} | <%-- Year Since
												Operations--%></p>
											<div class="vendor-coverage">
												<label>Coverage Details:</label>
												<table cellpadding="0" cellspacing="0" border="1">
													<tr>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Equities"/>
														<c:set var="exchangeCount" value="${l:getCount(functionMapKey, requestScope.assetExchanges)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${exchangeCount > 0}">
															<td><label>Equity :</label> ${exchangeCount} exchange, ${countriesCount} countries</td>
														</c:if>														
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_FI"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${countriesCount > 0}">
															<td><label>FI :</label> ${countriesCount} countries</td>
														</c:if>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Equity_Indices"/>
														<c:set var="equityExchangeCount" value="${l:getCount(functionMapKey, requestScope.assetExchanges)}"/>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_FI_Indices"/>
														<c:set var="fiExchangeCount" value="${l:getCount(functionMapKey, requestScope.assetExchanges)}"/>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Other_Indices"/>
														<c:set var="otherExchangeCount" value="${l:getCount(functionMapKey, requestScope.assetExchanges)}"/>
														<c:if test="${equityExchangeCount > 0 || fiExchangeCount > 0 || otherExchangeCount > 0}">
															<td>
																<label>Indices :</label> 
																<c:if test="${equityExchangeCount > 0}">
																	${equityExchangeCount} equity
																</c:if>
																<c:if test="${fiExchangeCount > 0}">
																	, ${fiExchangeCount} FI
																</c:if>
																<c:if test="${otherExchangeCount > 0}">
																	, ${otherExchangeCount} Other
																</c:if>
															</td>
														</c:if>
													</tr>
													<tr>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Derivative"/>
														<c:set var="exchangeCount" value="${l:getCount(functionMapKey, requestScope.assetExchanges)}"/>
														<c:if test="${exchangeCount > 0}">
															<td><label>Derivatives :</label> ${exchangeCount} F&O exchanges</td>
														</c:if>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_FX"/>
														<c:set var="exchangeCount" value="${l:getCount(functionMapKey, requestScope.assetExchanges)}"/>		
														<c:if test="${exchangeCount > 0}">
															<td><label>FX :</label> ${exchangeCount} pairs</td>
														</c:if>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
													<c:set var="awardsList" value="${awardsMap[marketDataAggregatorsVendorSearch.vendorId]}"/>
													<c:forEach var="awardRecord" items="${awardsList}" varStatus="counter">
														<c:if test="${counter.count == 1}">
															<tr><td colspan="4"><label>Awards Won:</label></td></tr>
														</c:if>
														<tr>
															<td colspan="4">${awardRecord.awardName} by ${awardRecord.awardSponsor} (${awardRecord.awardYear})</td>
														</tr>
													</c:forEach>
													
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>						
						</c:forEach>
					</c:when>
					<%-- Research Report Vendor Search --%>
					<c:when test="${not empty marketDataAggregatorsVendorSearchs && result eq researchReportProviders}">
						<c:forEach var="marketDataAggregatorsVendorSearch"
							items="${marketDataAggregatorsVendorSearchs}">
							<div class="portlet box blue">
								<div class="portlet-title"></div>
								<div class="portlet-body">
									<div class="portlet-content">
										<div class="vendor-logo">
											<a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${marketDataAggregatorsVendorSearch.vendorName}">
											<img src="${pageContext.request.contextPath}/displayCompanyLogo/${marketDataAggregatorsVendorSearch.vendorName}" 
												alt="${assetCountries}"
												title="${marketDataAggregatorsVendorSearch.vendorCompany}" width="60" height="60" />
											</a>
										</div>
										<div class="vendor-overview">
											<h2><a href="${pageContext.request.contextPath}/adminUserSummaryProfile?userName=${marketDataAggregatorsVendorSearch.vendorName}">${marketDataAggregatorsVendorSearch.vendorCompany}</a></h2>
											<p>${marketDataAggregatorsVendorSearch.vendorCountry} | ${marketDataAggregatorsVendorSearch.vendorType} | <%-- Year Since
												Operations--%></p>
											<div class="vendor-coverage">
												<label>Research Coverage</label>
												<table cellpadding="0" cellspacing="0" border="1">
													<tr>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Macro economic Analysis"/>
														<c:set var="regionsCount" value="${l:getCount(functionMapKey, requestScope.assetRegions)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${regionsCount > 0}">
															<td><label>Macroeconomic :</label> ${regionsCount} regions, ${countriesCount} countries</td>
														</c:if>														
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Sector Analysis"/>
														<c:set var="sectorCount" value="${l:getCount(functionMapKey, requestScope.researchSubAreas)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${sectorCount > 0}">
															<td><label>Sector :</label> ${sectorCount} sectors, ${countriesCount} countries</td>
														</c:if>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Industry Analysis"/>
														<c:set var="sectorCount" value="${l:getCount(functionMapKey, requestScope.researchSubAreas)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${sectorCount > 0}">
															<td><label>Industry :</label> ${sectorCount} Ind, ${countriesCount} countries</td>
														</c:if>
													</tr>
													<tr>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Commodity Analysis"/>
														<c:set var="sectorCount" value="${l:getCount(functionMapKey, requestScope.researchSubAreas)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${sectorCount > 0}">
															<td><label>Commodity :</label> ${sectorCount} comdty, ${countriesCount} countries</td>
														</c:if>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Exchange Rate Analysis"/>
														<c:set var="regionsCount" value="${l:getCount(functionMapKey, requestScope.assetRegions)}"/>
														<c:set var="sectorCount" value="${l:getCount(functionMapKey, requestScope.researchSubAreas)}"/>
														<c:if test="${regionsCount > 0}">
															<td><label>Forex :</label> ${regionsCount} regions, ${sectorCount} CCYs</td>
														</c:if>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Interest Rate Analysis"/>
														<c:set var="regionsCount" value="${l:getCount(functionMapKey, requestScope.assetRegions)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${regionsCount > 0}">
															<td><label>Interest Rate :</label> ${regionsCount} regions, ${countriesCount} countries</td>
														</c:if>	
													</tr>
													<tr>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Equity research"/>
														<c:set var="sectorCount" value="${l:getCount(functionMapKey, requestScope.researchSubAreas)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${sectorCount > 0}">
															<td><label>Equity :</label> ${sectorCount} stocks, ${countriesCount} countries</td>
														</c:if>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Debt Market research"/>
														<c:set var="sectorCount" value="${l:getCount(functionMapKey, requestScope.researchSubAreas)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${sectorCount > 0}">
															<td><label>Debt :</label> ${sectorCount} issuers, ${countriesCount} countries</td>
														</c:if>
														<c:set var="functionMapKey" value="${marketDataAggregatorsVendorSearch.vendorId}_Index/Fund/ETF research"/>
														<c:set var="sectorCount" value="${l:getCount(functionMapKey, requestScope.researchSubAreas)}"/>
														<c:set var="countriesCount" value="${l:getCount(functionMapKey, requestScope.assetCountries)}"/>
														<c:if test="${sectorCount > 0}">
															<td><label>Index :</label> ${sectorCount} issuers, ${countriesCount} countries</td>
														</c:if>	
													</tr>
													<c:set var="awardsList" value="${awardsMap[marketDataAggregatorsVendorSearch.vendorId]}"/>
													<c:forEach var="awardRecord" items="${awardsList}" varStatus="counter">
														<c:if test="${counter.count == 1}">
															<tr><td colspan="4"><label>Awards Won:</label></td></tr>
														</c:if>
														<tr>
															<td colspan="4">${awardRecord.awardName} by ${awardRecord.awardSponsor} (${awardRecord.awardYear})</td>
														</tr>
													</c:forEach>
													
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>						
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="error_section clearfix append_bottom15">
							<div class="text-center mascot_error">
								<img src="resources/images/mascot_invld_link.png" alt="mascot" />
							</div>
							<div class="error_message_align">
								<p class="error_txt append_bottom12">Hey! there is no data
									match.</p>
								<p class="error_txt_3 append_bottom15">We suggest you to modify
									your search and try again or use Market Vendor to search across
									multiple asset class.</p>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				
				
				
				
				
				
				
				
				
				
				
				
				<%-- 
				
				
				<div class="error_section clearfix append_bottom15">
					<div class="text-center mascot_error">
						<img src="resources/images/mascot_invld_link.png" alt="mascot" />
					</div>
					<div class="error_message_align">
						<p class="error_txt append_bottom12">Hey! there is no data
							match.</p>
						<p class="error_txt_3 append_bottom15">We suggest you to modify
							your search and try again or use Market Vendor to search across
							multiple asset class.</p>
					</div>
				</div>
				<div class="portlet box blue">
					<div class="portlet-title"></div>
					<div class="portlet-body">
						<div class="portlet-content">
							<div class="vendor-logo">
								<img src="resources/images/user.png" alt="Company Name"
									title="Company Name" width="60" height="60" />
							</div>
							<div class="vendor-overview">
								<h2>Company Name</h2>
								<p>Country of Incorporation | Vendor Type | Year Since
									Operations</p>
								<div class="vendor-coverage">
									<label>Coverage Details:</label>
									<table cellpadding="0" cellspacing="0" border="1">
										<tr>
											<td><label>Equity :</label> 20 exchange, 30 countries</td>
											<td><label>FI :</label> 20 countries</td>
											<td><label>Indices :</label> 105 equity, 20 FI indices</td>
											<td><label>Derivatives :</label> 20 F&O exchanges</td>
											<td>
										</tr>
										<tr>
											<td><label>Derivatives :</label> 20 F&O exchanges</td>
											<td><label>FX :</label> Gro ccv pairs</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td colspan="4"><label>Award Won :</label> Best Equity
												Vendor by Bloomberg(2015)</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="portlet box blue">
					<div class="portlet-title"></div>
					<div class="portlet-body">
						<div class="portlet-content">
							<div class="vendor-logo">
								<img src="resources/images/user.png" alt="Company Name"
									title="Company Name" width="60" height="60" />
							</div>
							<div class="vendor-overview">
								<h2>Company Name</h2>
								<p>Country of Incorporation | Vendor Type | Year Since
									Operations</p>
								<div class="vendor-coverage">
									<label>Coverage Details:</label>
									<table cellpadding="0" cellspacing="0" border="1">
										<tr>
											<td><label>Equity :</label> 20 exchange, 30 countries</td>
											<td><label>FI :</label> 20 countries</td>
											<td><label>Indices :</label> 105 equity, 20 FI indices</td>
											<td><label>Derivatives :</label> 20 F&O exchanges</td>
											<td>
										</tr>
										<tr>
											<td><label>Derivatives :</label> 20 F&O exchanges</td>
											<td><label>FX :</label> Gro ccv pairs</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td colspan="4"><label>Award Won :</label> Best Equity
												Vendor by Bloomberg(2015)</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			--%>
			
			<%-- 
			
			<table
				class="table table-striped table-borderedsearch table-hover table-full-width"
				id="sample_1">
				<c:if test="${result eq marketAggregators}">
					<thead style="background-color: #7BCCA5; color: #FFF;">
						<tr>
							<th></th>
							<th>Asset Class</th>
							<th>Coverage Region</th>
							<th>Coverage Country</th>
							<th>Coverage Exchange</th>
							<th>Cost</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${not empty marketDataAggregatorsVendorSearchs}">

								<c:forEach var="marketDataAggregatorsVendorSearch"
									items="${marketDataAggregatorsVendorSearchs}">
									<tr>
										<td><div id="companyLogo">
												<img
													src="${pageContext.request.contextPath}/displayCompanyLogo/${marketDataAggregatorsVendorSearch.userName}"
													width="175" height="400"
													style="float: left; margin-right: 10px"
													alt="${marketDataAggregatorsVendorSearch.vendorCompany}"
													title="${marketDataAggregatorsVendorSearch.vendorCompany}" />
											</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.vendorCountry}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.vendorCompany}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.vendorType}</div></td>
									</tr>
								</c:forEach>

							</c:when>
							<c:otherwise>
								<div class="error_section clearfix append_bottom15">
									<div class="text-center mascot_error">
										<img src="resources/images/mascot_invld_link.png" alt="mascot" />
									</div>
									<div class="error_message_align">
										<p class="error_txt append_bottom12">Hey! there is no data
											match.</p>
										<p class="error_txt_3 append_bottom15">We suggest you to
											modify your search and try again or use Market Vendor to
											search across multiple asset class.</p>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</tbody>
				</c:if>
				<c:if test="${result eq researchReportProviders}">
					<thead style="background-color: #7BCCA5; color: #FFF;">
						<tr>

							<th>Vendor Name</th>
							<th>Research Area</th>
							<th>Analyst Awards</th>
							<th>Analyst Name</th>
							<th>Coverage Country</th>
							<th>Coverage Region</th>

						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${not empty marketDataAggregatorsVendorSearchs}">

								<c:forEach var="marketDataAggregatorsVendorSearch"
									items="${marketDataAggregatorsVendorSearchs}">
									<tr>

										<td><div>${marketDataAggregatorsVendorSearch.vendor}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.researchArea}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.analystAwards}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.analystName}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.countryName}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.region}</div></td>

									</tr>
								</c:forEach>

							</c:when>
							<c:otherwise>
								<div class="error_section clearfix append_bottom15">
									<div class="text-center mascot_error">
										<img src="resources/images/mascot_invld_link.png" alt="mascot" />
									</div>
									<div class="error_message_align">
										<p class="error_txt append_bottom12">Hey! there is no data
											match.</p>
										<p class="error_txt_3 append_bottom15">We suggest you to
											modify your search and try again or use Market Vendor to
											search across multiple asset class.</p>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</tbody>
				</c:if>
				<c:if test="${result eq financialAnalytics}">
					<thead style="background-color: #7BCCA5; color: #FFF;">
						<tr>

							<th>Vendor Name</th>
							<th>Analytics Solutions Type</th>
							<th>Suitability</th>
							<th>Accessibility</th>
							<th>Coverage Country</th>
							<th>Coverage Region</th>

						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${not empty marketDataAggregatorsVendorSearchs}">

								<c:forEach var="marketDataAggregatorsVendorSearch"
									items="${marketDataAggregatorsVendorSearchs}">
									<tr>

										<td><div>${marketDataAggregatorsVendorSearch.vendor}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.analyticssolutionsType}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.suitability}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.accessibility}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.dataCoverageCountry}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.dataCoverageExchange}</div></td>
									</tr>
								</c:forEach>

							</c:when>
							<c:otherwise>
								<div class="error_section clearfix append_bottom15">
									<div class="text-center mascot_error">
										<img src="resources/images/mascot_invld_link.png" alt="mascot" />
									</div>
									<div class="error_message_align">
										<p class="error_txt append_bottom12">Hey! there is no data
											match.</p>
										<p class="error_txt_3 append_bottom15">We suggest you to
											modify your search and try again or use Market Vendor to
											search across multiple asset class.</p>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</tbody>
				</c:if>
				<c:if test="${result eq tradingApplication}">
					<thead style="background-color: #7BCCA5; color: #FFF;">
						<tr>

							<th>Vendor Name</th>
							<th>Asset Class</th>
							<th>Suitability</th>
							<th>Accessibility</th>
							<th>Order Type</th>
							<th>Coverage Region</th>
							<th>Coverage Country</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${not empty marketDataAggregatorsVendorSearchs}">

								<c:forEach var="marketDataAggregatorsVendorSearch"
									items="${marketDataAggregatorsVendorSearchs}">
									<tr>

										<td><div>${marketDataAggregatorsVendorSearch.vendor}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.assetClass}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.accessibility}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.suitability}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.orderType}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.coverageRegion}</div></td>
										<td><div>${marketDataAggregatorsVendorSearch.coverageCountry}</div></td>
									</tr>
								</c:forEach>

							</c:when>
							<c:otherwise>
								<div class="error_section clearfix append_bottom15">
									<div class="text-center mascot_error">
										<img src="resources/images/mascot_invld_link.png" alt="mascot" />
									</div>
									<div class="error_message_align">
										<p class="error_txt append_bottom12">Hey! there is no data
											match.</p>
										<p class="error_txt_3 append_bottom15">We suggest you to
											modify your search and try again or use Market Vendor to
											search across multiple asset class.</p>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</tbody>
				</c:if>

			</table>
		
		--%>
		
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	<script src="<%=request.getContextPath()%>/resources/js/popup.js"
		type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery-min.js"
		type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/resources/js/modernizr.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath() %>/resources/js/bootstrap-min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/resources/singleasset/js/script.js"></script>
</body>
</html>