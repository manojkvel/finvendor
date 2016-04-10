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
								<b><h1>${user.vendor.company}</h1></b>								
								<h5>${country.name}&nbsp;|&nbsp;${user.vendor.companyType}</h5>
							</p>					
								Since<br>
								${user.vendor.firstName}&nbsp;${user.vendor.lastName}<br>
								${user.vendor.designation}<br>
								${user.email}<br>
								${user.vendor.secondaryEmail}<br>
								${user.vendor.telephone}<br>
								${user.vendor.companyUrl}
							</p>
							<br>
							<p><b><h3>Summary</h3></b></p>
							<p>${user.vendor.companyInfo}</p>
							<c:if test="${not empty dataaggregator}">	
								<p><b><h3>Market Data Coverage</h3></b></p>
								<table class="table table-striped table-bordered table-hover table-full-width" 
									id="vendorMarketDataOfferingsTable">
									<thead style="background-color:#7BCCA5;">
										<tr>
											<th>Offering Name</th>
											<th>Offering Description</th>
											<th>Coverage Region</th>
											<th>Coverage Countries</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${marketDataOfferings}" var="tableRowData">
											<tr>
												<td>${tableRowData[1]}</td>
												<td>${tableRowData[2]}</td>
												<td>${tableRowData[3]}</td>
								                <td>${tableRowData[4]}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
							<c:if test="${not empty tradingapplication}">	
								<p><b><h3>Trading Application Coverage</h3></b></p>
							</c:if>
							<c:if test="${not empty analyticsapplication}">	
								<p><b><h3>Analytics Application Coverage</h3></b></p>
							</c:if>
							<c:if test="${not empty researchreport}">	
								<p><b><h3>Research Report Coverage</h3></b></p>
							</c:if>
							<p><b><h3>Award Details</h3></b></p>
							
							<c:set var="firstAssetTable">false</c:set>
							<c:set var="firstVendor">false</c:set>
							<c:set var="awardTableCreated">false</c:set>
							<c:forEach items="${vendorAwardDetails}" var="tableRowData">								
								<c:set var="awardTableCreated">true</c:set>
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
											<p><b><h4>${tableRowData[4]}</h4></b></p>
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
											<p><b><h4>${tableRowData[4]}</h4></b></p>
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
								<b><h1>${user.consumer.company}</h1></b>								
								<h5>${country.name}&nbsp;|&nbsp;${user.consumer.companyType}</h5>
							</p>					
								Since<br>
								${user.consumer.firstName}&nbsp;${user.consumer.lastName}<br>
								${user.consumer.designation}<br>
								${user.email}<br>
								${user.consumer.secondaryEmail}<br>
								${user.consumer.telephone}<br>
								${user.consumer.companyUrl}
							</p>
							<br>
							<p><b><h3>Summary</h3></b></p>
							<p>${user.consumer.companyInfo}</p>	
						</c:when>
					</c:choose>						
				</div>	
			</div>
			<jsp:include page="adminMenu.jsp"></jsp:include>
		</div>
		<jsp:include page="common/footer.jsp"></jsp:include>
	</body>
</html>