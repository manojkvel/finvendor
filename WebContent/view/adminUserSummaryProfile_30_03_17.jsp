<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
	<head>
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/superfish.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/tab.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" rel="stylesheet"/>
		<link href="${pageContext.request.contextPath}/resources/css/dataTables.jqueryui.min.css" rel="stylesheet"/>
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>		
		<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/dataTables.jqueryui.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
	</head>
	<body>
		<jsp:include page="common/head.jsp"></jsp:include>
		<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
		<div class="container">
			<div class="inner-content">
				<div class="inner-left-wrap" id="${finven:replaceCharacter(nav, ' ', '_')}_${finven:replaceCharacter(subNav, ' ', '_')}">
					<div><b><label class="errorMessage" style="color:red">${lastActionError}</label></b></div>
		    		<c:choose>
						<c:when test="${user.vendor != null}">
							<p>
								<div id="companyLogo">
									<img src="${pageContext.request.contextPath}/displayCompanyLogo/${user.userName}" width="175" height="400" 
										style="float:left;margin-right:10px" alt="${user.vendor.company}" title="${user.vendor.company}"/>										
								</div>
								<b><h1><a href="${finven:rectifyCompanyUrl(user.vendor.companyUrl)}" target="_blank">${user.vendor.company}</a></h1></b>								
								<h5>
									<c:if test="${country != null}">
										${country.name}&nbsp;|&nbsp;
									</c:if>
									${user.vendor.companyType}
								</h5>
							</p>					
								<%--Since<br> --%>
								${user.vendor.firstName}&nbsp;${user.vendor.lastName}<br>
								${user.vendor.designation}<br>
								<a href="mailto:${user.email}" target="_top">${user.email}</a><br>
								<a href="mailto:${user.vendor.secondaryEmail}" target="_top">${user.vendor.secondaryEmail}</a><br>
								<%--
								${user.email}<br>
								${user.vendor.secondaryEmail}<br>
								--%>
								${user.vendor.telephone}
								<%--${user.vendor.companyUrl}--%>
							</p>
							<br>
							<p><b><h3>Summary</h3></b></p>
							<p>${user.vendor.companyInfo}</p>
							<c:if test="${not empty dataaggregator}">	
								
								<c:set var="firstMarketDataOfferingTable">false</c:set>
								<c:set var="firstMarketDataOfferingYearTable">false</c:set>
								<c:set var="marketDataOfferingTableCreated">false</c:set>
								<c:forEach items="${marketDataOfferings}" var="tableRowData">								
									<c:set var="marketDataOfferingTableCreated">true</c:set>
									<c:set var="currentMarketDataOfferingAssetType">${tableRowData[0]}</c:set>
									<c:set var="currentMarketDataOfferingYear">${tableRowData[5]}</c:set>
									<c:if test="${lastMarketDataOfferingAssetType != currentMarketDataOfferingAssetType}">
										<c:set var="lastMarketDataOfferingYear"></c:set>
										<c:if test="${firstMarketDataOfferingTable == false}">
											<c:set var="firstMarketDataOfferingTable">true</c:set>
											<p><b><h3>Market Data Coverage</h3></b></p>
										</c:if>														
									</c:if>
									<c:if test="${lastMarketDataOfferingYear != currentMarketDataOfferingYear}">
										<c:choose>
											<c:when test="${firstMarketDataOfferingYearTable == false}">
												<c:set var="firstMarketDataOfferingYearTable">true</c:set>
												<p><b><h4>${currentMarketDataOfferingAssetType} (Launch Year: ${currentMarketDataOfferingYear})</h4></b></p>
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5">
														<tr>
															<th>Offering Name</th>
															<th>Offering Description</th>
															<th>Region</th>
															<th>Country</th>
														</tr>
													</thead>
													<tbody>
											</c:when>
											<c:otherwise>
												</tbody>
												</table>
												<p><b><h4>${currentMarketDataOfferingAssetType} (Launch Year: ${currentMarketDataOfferingYear})</h4></b></p>
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5">
														<tr>
															<th>Offering Name</th>
															<th>Offering Description</th>
															<th>Region</th>
															<th>Country</th>
														</tr>
													</thead>
													<tbody>
											</c:otherwise>
										</c:choose>									
										<tr>
											<td>${tableRowData[1]}</td>
											<td>${tableRowData[2]}</td>
											<td>${tableRowData[3]}</td>
											<td>${tableRowData[4]}</td>
										</tr>
									</c:if>
									<c:if test="${lastMarketDataOfferingYear == currentMarketDataOfferingYear}">
										<tr>
											<td>${tableRowData[1]}</td>
											<td>${tableRowData[2]}</td>
											<td>${tableRowData[3]}</td>
											<td>${tableRowData[4]}</td>
										</tr>
									</c:if>
									<c:set var="lastMarketDataOfferingAssetType">${currentMarketDataOfferingAssetType}</c:set>
									<c:set var="lastMarketDataOfferingYear">${currentMarketDataOfferingYear}</c:set>
								</c:forEach>
								<c:if test="${marketDataOfferingTableCreated == true}">
									</tbody>
									</table>
								</c:if>	
								
								
								
								
								
																
							</c:if>
							
							
							<c:if test="${not empty tradingapplication}">	
								<c:set var="firstTradingApplicationOfferingTable">false</c:set>
								<c:set var="firstTradingApplicationOfferingYearTable">false</c:set>
								<c:set var="tradingApplicationOfferingTableCreated">false</c:set>
								<c:forEach items="${tradingApplicationOfferings}" var="tableRowData">								
									<c:set var="tradingApplicationOfferingTableCreated">true</c:set>
									<c:set var="currentTradingApplicationOfferingAssetType">${tableRowData[0]}</c:set>
									<c:set var="currentTradingApplicationOfferingYear">${tableRowData[1]}</c:set>
									<c:if test="${lastTradingApplicationOfferingAssetType != currentTradingApplicationOfferingAssetType}">
										<c:set var="lastTradingApplicationOfferingYear"></c:set>
										<c:if test="${firstTradingApplicationOfferingTable == false}">
											<c:set var="firstTradingApplicationOfferingTable">true</c:set>
											<p><b><h3>Trading Application Coverage</h3></b></p>
										</c:if>															
									</c:if>
									<c:if test="${lastTradingApplicationOfferingYear != currentTradingApplicationOfferingYear}">
										<c:choose>
											<c:when test="${firstTradingApplicationOfferingYearTable == false}">
												<c:set var="firstTradingApplicationOfferingYearTable">true</c:set>
												<p><b><h4>${currentTradingApplicationOfferingAssetType} (Launch Year: ${currentTradingApplicationOfferingYear})</h4></b></p>
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5">
														<tr>
															<th>Offering Name</th>
															<th>Offering Description</th>
															<th>Region</th>
															<th>Market</th>
															<th>Trading Capability Type</th>
															<th>Trade Execution Type</th>
														</tr>
													</thead>
													<tbody>
											</c:when>
											<c:otherwise>
												</tbody>
												</table>
												<p><b><h4>${currentTradingApplicationOfferingAssetType} (Launch Year: ${currentTradingApplicationOfferingYear})</h4></b></p>
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5">
														<tr>
															<th>Offering Name</th>
															<th>Offering Description</th>
															<th>Region</th>
															<th>Country</th>
															<th>Trading Capability Type</th>
															<th>Trade Execution Type</th>
														</tr>
													</thead>
													<tbody>
											</c:otherwise>
										</c:choose>									
										<tr>
											<td>${tableRowData[2]}</td>
											<td>${tableRowData[3]}</td>
											<td>${tableRowData[4]}</td>
											<td>${tableRowData[5]}</td>
											<td>${tableRowData[6]}</td>
											<td>${tableRowData[7]}</td>
										</tr>
									</c:if>
									<c:if test="${lastTradingApplicationOfferingYear == currentTradingApplicationOfferingYear}">
										<tr>
											<td>${tableRowData[2]}</td>
											<td>${tableRowData[3]}</td>
											<td>${tableRowData[4]}</td>
											<td>${tableRowData[5]}</td>
											<td>${tableRowData[6]}</td>
											<td>${tableRowData[7]}</td>
										</tr>
									</c:if>
									<c:set var="lastTradingApplicationOfferingAssetType">${currentTradingApplicationOfferingAssetType}</c:set>
									<c:set var="lastTradingApplicationOfferingYear">${currentTradingApplicationOfferingYear}</c:set>
								</c:forEach>
								<c:if test="${tradingApplicationOfferingTableCreated == true}">
									</tbody>
									</table>
								</c:if>	
							</c:if>
							
							
							
							
							
							<c:if test="${not empty analyticsapplication}">	
								<c:set var="firstAnalyticsApplicationOfferingTable">false</c:set>
								<c:set var="analyticsApplicationOfferingTableCreated">false</c:set>
								<c:forEach items="${analyticsApplicationOfferings}" var="tableRowData">								
									<c:set var="analyticsApplicationOfferingTableCreated">true</c:set>
									<c:set var="currentAnalyticsApplicationOfferingType">${tableRowData[0]}</c:set>
									<c:if test="${lastAnalyticsApplicationOfferingType != currentAnalyticsApplicationOfferingType}">
										<c:choose>
											<c:when test="${firstAnalyticsApplicationOfferingTable == false}">
												<c:set var="firstAnalyticsApplicationOfferingTable">true</c:set>
												<p><b><h3>Analytics Application Coverage</h3></b></p>
												<p><b><h4>${currentAnalyticsApplicationOfferingType}</h4></b></p>
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5">
														<tr>
															<th>Offering Description</th>
															<th>Solution Sub Type</th>
															<th>Launch Year</th>
															<th>Subscription Cost</th>
														</tr>
													</thead>
													<tbody>
											</c:when>
											<c:otherwise>
												</tbody>
												</table>
												<p><b><h4>${currentAnalyticsApplicationOfferingType}</h4></b></p>
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5">
														<tr>
															<th>Offering Description</th>
															<th>Solution Sub Type</th>
															<th>Launch Year</th>
															<th>Subscription Cost</th>
														</tr>
													</thead>
													<tbody>
											</c:otherwise>
										</c:choose>
										<tr>
											<td>${tableRowData[1]}</td>
											<td>${tableRowData[2]}</td>
											<td>${tableRowData[3]}</td>
											<td>${tableRowData[4]}</td>
										</tr>															
									</c:if>								
									<c:if test="${lastAnalyticsApplicationOfferingType == currentAnalyticsApplicationOfferingType}">
										<tr>
											<td>${tableRowData[1]}</td>
											<td>${tableRowData[2]}</td>
											<td>${tableRowData[3]}</td>
											<td>${tableRowData[4]}</td>
										</tr>
									</c:if>
									<c:set var="lastAnalyticsApplicationOfferingType">${currentAnalyticsApplicationOfferingType}</c:set>
								</c:forEach>
								<c:if test="${analyticsApplicationOfferingTableCreated == true}">
									</tbody>
									</table>
								</c:if>								
							</c:if>
							
							
							
							<c:if test="${not empty researchreport}">	
								<c:set var="firstResearchReportOfferingTable">false</c:set>
								<c:set var="researchReportOfferingTableCreated">false</c:set>
								<c:forEach items="${researchReportOfferings}" var="tableRowData">								
									<c:set var="researchReportOfferingTableCreated">true</c:set>
									<c:set var="currentResearchReportOfferingType">${tableRowData[0]}</c:set>
									<c:if test="${lastResearchReportOfferingType != currentResearchReportOfferingType}">
										<c:choose>
											<c:when test="${firstResearchReportOfferingTable == false}">
												<c:set var="firstResearchReportOfferingTable">true</c:set>
												<p><b><h3>Research Coverage</h3></b></p>
												<p><b><h4>${currentResearchReportOfferingType}</h4></b></p>
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5">
														<tr>
															<th>Offering Name</th>
															<th>Offering Description</th>
															<th>Research Sub Area</th>
															<th>Regions Covered</th>
															<th>Analyst Name</th>
															<th>Annual Cost</th>
														</tr>
													</thead>
													<tbody>
											</c:when>
											<c:otherwise>
												</tbody>
												</table>
												<p><b><h4>${currentResearchReportOfferingType}</h4></b></p>
												<table class="table table-striped table-bordered table-hover table-full-width">
													<thead style="background-color:#7BCCA5">
														<tr>
															<th>Offering Name</th>
															<th>Offering Description</th>
															<th>Research Sub Area</th>
															<th>Regions Covered</th>
															<th>Analyst Name</th>
															<th>Annual Cost</th>
														</tr>
													</thead>
													<tbody>
											</c:otherwise>
										</c:choose>
										<tr>
											<td>${tableRowData[1]}</td>
											<td>${tableRowData[2]}</td>
											<td>${tableRowData[3]}</td>
											<td>${tableRowData[4]}</td>
											<td>${tableRowData[6]}</td>
											<td>${tableRowData[7]}</td>
										</tr>															
									</c:if>								
									<c:if test="${lastResearchReportOfferingType == currentResearchReportOfferingType}">
										<tr>
											<td>${tableRowData[1]}</td>
											<td>${tableRowData[2]}</td>
											<td>${tableRowData[3]}</td>
											<td>${tableRowData[4]}</td>
											<td>${tableRowData[6]}</td>
											<td>${tableRowData[7]}</td>
										</tr>
									</c:if>
									<c:set var="lastResearchReportOfferingType">${currentResearchReportOfferingType}</c:set>
								</c:forEach>
								<c:if test="${researchReportOfferingTableCreated == true}">
									</tbody>
									</table>
								</c:if>		
							
							
							
							
							
							
							
							
							</c:if>
							
							
							
							
							
							
							<c:set var="firstAssetTable">false</c:set>
							<c:set var="firstVendor">false</c:set>
							<c:set var="awardTableCreated">false</c:set>
							<c:forEach items="${vendorAwardDetails}" var="tableRowData">								
								<c:if test="${awardTableCreated == false}">
									<c:set var="awardTableCreated">true</c:set>
									<p><b><h3>Award Details</h3></b></p>
								</c:if>								
								<c:set var="currentVendorType">${tableRowData[5]}</c:set>
								<c:set var="currentAssetType">${tableRowData[4]}</c:set>
								<c:if test="${lastVendorType != currentVendorType}">
									<c:choose>
										<c:when test="${firstVendor == false}">
											<c:set var="firstVendor">true</c:set>
											<c:choose>
												<c:when test="${tableRowData[5] == 1}">
													<p><b><h4>Market Data Awards</h4></b></p>																							
												</c:when>
												<c:when test="${tableRowData[5] == 2}">
													<p><b><h4>Trading Application Awards</h4></b></p>																							
												</c:when>
												<c:when test="${tableRowData[5] == 3}">
													<p><b><h4>Analytics Application Awards</h4></b></p>																							
												</c:when>
												<c:when test="${tableRowData[5] == 4}">
													<p><b><h4>Research Report Awards</h4></b></p>																							
												</c:when>
											</c:choose>	
										</c:when>
										<c:otherwise>
											</tbody>
											</table>
											<c:choose>
												<c:when test="${tableRowData[5] == 1}">
													<p><b><h4>Market Data Awards</h4></b></p>																							
												</c:when>
												<c:when test="${tableRowData[5] == 2}">
													<p><b><h4>Trading Application Awards</h4></b></p>																							
												</c:when>
												<c:when test="${tableRowData[5] == 3}">
													<p><b><h4>Analytics Application Awards</h4></b></p>																							
												</c:when>
												<c:when test="${tableRowData[5] == 4}">
													<p><b><h4>Research Report Awards</h4></b></p>																							
												</c:when>
											</c:choose>	
										</c:otherwise>
									</c:choose>															
								</c:if>
								<c:if test="${lastAssetType != currentAssetType}">
									<c:choose>
										<c:when test="${firstAssetTable == false}">
											<c:set var="firstAssetTable">true</c:set>
											<p><b><h4>${currentAssetType}</h4></b></p>
											<table class="table table-striped table-bordered table-hover table-full-width">
												<thead style="background-color:#7BCCA5">
													<tr>
														<th>Award Name</th>
														<th>Award Main Sponsor</th>
														<th>Awarded Year</th>
													</tr>
												</thead>
												<tbody>
										</c:when>
										<c:otherwise>
											</tbody>
											</table>
											<p><b><h4>${currentAssetType}</h4></b></p>
											<table class="table table-striped table-bordered table-hover table-full-width">
												<thead style="background-color:#7BCCA5">
													<tr>
														<th>Award Name</th>
														<th>Award Main Sponsor</th>
														<th>Awarded Year</th>
													</tr>
												</thead>
												<tbody>
										</c:otherwise>
									</c:choose>									
									<tr>
										<td>${tableRowData[0]}</td>
										<td>${tableRowData[1]}</td>
										<td>${tableRowData[2]}</td>
									</tr>
								</c:if>
								<c:if test="${lastAssetType == currentAssetType}">
									<tr>
										<td>${tableRowData[0]}</td>
										<td>${tableRowData[1]}</td>
										<td>${tableRowData[2]}</td>
									</tr>
								</c:if>
								<c:set var="lastAssetType">${currentAssetType}</c:set>
								<c:set var="lastVendorType">${currentVendorType}</c:set>
							</c:forEach>
							<c:if test="${awardTableCreated == true}">
								</tbody>
								</table>
							</c:if>						
						</c:when>
						<c:when test="${user.consumer != null}">
							<p>
								<div id="companyLogo">
									<img src="${pageContext.request.contextPath}/displayCompanyLogo/${user.userName}" width="175" height="400" 
										style="float:left;margin-right:10px" alt="${user.consumer.company}" title="${user.consumer.company}"/>										
								</div>
								<b><h1><a href="${finven:rectifyCompanyUrl(user.consumer.companyUrl)}" target="_blank">${user.consumer.company}</a></h1></b>
								<h5>
									<c:if test="${country != null}">
										${country.name}&nbsp;|&nbsp;
									</c:if>
									${user.consumer.companyType}
								</h5>
							</p>					
								<%--Since<br>--%>
								${user.consumer.firstName}&nbsp;${user.consumer.lastName}<br>
								${user.consumer.designation}<br>
								<a href="mailto:${user.email}" target="_top">${user.email}</a><br>
								<a href="mailto:${user.consumer.secondaryEmail}" target="_top">${user.consumer.secondaryEmail}</a><br>
								<%-- 
								${user.email}<br>
								${user.consumer.secondaryEmail}<br>
								--%>
								${user.consumer.telephone}<br>
								<%--${user.consumer.companyUrl}--%>
							</p>
							<br>
							<p><b><h3>Summary</h3></b></p>
							<p>${user.consumer.companyInfo}</p>	
						</c:when>
					</c:choose>						
				</div>	
			</div>
			<c:if test="${sessionScope.loggedInRole == 'ROLE_ADMIN'}">
				<jsp:include page="adminMenu.jsp"></jsp:include>
			</c:if>
		</div>
		<jsp:include page="common/footer.jsp"></jsp:include>
	</body>
</html>