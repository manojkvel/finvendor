<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
	<head>
		<title>Fin Vendor | User Profile</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
		<meta name="description" content="" />
		<meta http-equiv="Pragma" content="no-cache">
		<meta name="author" content="" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
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

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
		<div class="row" style="margin: -20px 0;">
			<img src="${pageContext.request.contextPath}/resources/images/banner/profile-banner.svg" />
		</div>
		<div class="container" id="user_profile_details">
			<div class="row">
				<div class="col-xs-12 col-sm-8 col-md-9">
					<c:choose>
						<c:when test="${user.consumer != null}">
							<div class="user_profile">
								<div class="profile_icon">
									<img src="${pageContext.request.contextPath}/resources/images/banner/default_profile.png" />
									<img src="<%=request.getContextPath() %>/getfile/logo" alt="${user.vendor.company}" title="${user.consumer.company}"/>	
								</div>
								<h1><a href="${finven:rectifyCompanyUrl(user.consumer.companyUrl)}" target="_blank">${user.consumer.company}</a></h1>								
							</div>
							<div class="user_info">
								<h4>About Me</h4>
								<table class="company-details">
									<tr>
										<c:if test="${user.consumer.firstName != null}">
											<th>Name</th>
											<td class="url">
												${user.consumer.firstName}&nbsp;${user.consumer.lastName}
											</td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${user.consumer.designation != null}">
											<th>Designation</th>
											<td class="url">
												${user.consumer.designation}
											</td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${user.email != null}">
											<th>Primary Email</th>
											<td class="url">
												<a href="mailto:${user.email}" target="_top">${user.email}</a>
											</td>
										</c:if>
									</tr>

									<tr>
										<c:if test="${user.consumer.secondaryEmail != null}">
											<th>Secondary Email</th>
											<td class="url">
												<a href="mailto:${user.consumer.secondaryEmail}" target="_top">${user.consumer.secondaryEmail}</a>
											</td>
										</c:if>
									</tr>
									
									<tr>
										<c:if test="${user.consumer.telephone != null}">
											<th>Phone</th>
											<td class="url">
												${user.consumer.telephone}
											</td>
										</c:if>
									</tr>
								</table>
							</div>
							<div class="company_info">
								<h4>Company Info</h4>
								<table class="company-details">
									<tr>
										<c:if test="${user.consumer.companyType != null}">
											<th>Comany Type</th>
											<td class="url">
												${user.consumer.companyType}
											</td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${consumer.companyUrl != null}">
											<th>Comany Url</th>
											<td class="url">
												${consumer.companyUrl}
											</td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${user.consumer.companyInfo != null}">
											<th>Summary</th>
											<td class="info">
												${user.consumer.companyInfo}
											</td>
										</c:if>
									</tr>
								</table>
							</div>
						</c:when>
						<c:when test="${user.vendor != null}">
							<div class="user_profile">
								<div class="profile_icon">
									<img src="${pageContext.request.contextPath}/resources/images/banner/default_profile.png" />
									<img src="<%=request.getContextPath() %>/getfile/logo" width="48" height="48" alt="${user.vendor.company}" title="${user.vendor.company}"/>	
								</div>
								<h1><a href="${finven:rectifyCompanyUrl(user.vendor.companyUrl)}" target="_blank">${user.vendor.company}</a></h1>								
							</div>
							<div class="user_info">
								<h4>About Me</h4>
								<table class="company-details">
									<tr>
										<c:if test="${user.vendor.firstName != null}">
											<th>Name</th>
											<td class="url">
												${user.vendor.firstName}&nbsp;${user.vendor.lastName}
											</td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${user.vendor.designation != null}">
											<th>Designation</th>
											<td class="url">
												${user.vendor.designation}
											</td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${user.email != null}">
											<th>Primary Email</th>
											<td class="url">
												${consumer}
												<a href="mailto:${user.email}" target="_top">${user.email}</a>
											</td>
										</c:if>
									</tr>

									<tr>
										<c:if test="${user.vendor.secondaryEmail != null}">
											<th>Secondary Email</th>
											<td class="url">
												<a href="mailto:${user.vendor.secondaryEmail}" target="_top">${user.vendor.secondaryEmail}</a>
											</td>
										</c:if>
									</tr>
									
									<tr>
										<c:if test="${user.vendor.telephone != null}">
											<th>Phone</th>
											<td class="url">
												${user.vendor.telephone}
											</td>
										</c:if>
									</tr>
								</table>
							</div>
							<div class="company_info">
								<h4>Company Info</h4>
								<table class="company-details">
									<tr>
										<c:if test="${user.vendor.companyType != null}">
											<th>Comany Type</th>
											<td class="url">
												${user.vendor.companyType}
											</td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${vendor.companyUrl != null}">
											<th>Comany Url</th>
											<td class="url">
												${vendor.companyUrl}
											</td>
										</c:if>
									</tr>
									<tr>
										<c:if test="${user.vendor.companyInfo != null}">
											<th>Summary</th>
											<td class="info">
												${user.vendor.companyInfo}
											</td>
										</c:if>
									</tr>
								</table>
							</div>
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

											<div class="offerings_info">
												<c:set var="firstMarketDataOfferingTable">true</c:set>
												<h4>Market Data Coverage</h4>
										</c:if>														
									</c:if>
									<div class="data_aggregator_list list">
										<h3>${tableRowData[1]}</h3>
										<h4>${currentMarketDataOfferingAssetType} | ${currentMarketDataOfferingYear}</h4>
										<p>${tableRowData[2]}</p>
									</div>
								</c:forEach>
								<c:if test="${marketDataOfferingTableCreated == true}">	
									</div>
								</c:if>	
							</c:if>

							<c:if test="${not empty researchreport}">
								<c:set var="firstResearchReportOfferingTable">false</c:set>
								<c:set var="researchReportOfferingTableCreated">false</c:set>
								<c:forEach items="${researchReportOfferings}" var="tableRowData">								
									<c:set var="researchReportOfferingTableCreated">true</c:set>
									<c:set var="currentResearchReportOfferingType">${tableRowData[0]}</c:set>
									<c:set var="currentMarketDataOfferingYear">${tableRowData[5]}</c:set>
									<c:if test="${lastResearchReportOfferingType != currentResearchReportOfferingType}">
										<c:if test="${firstResearchReportOfferingTable == false}">

											<div class="offerings_info">
												<c:set var="firstResearchReportOfferingTable">true</c:set>
												<h4>Research Coverage</h4>
										</c:if>														
									</c:if>
									<div class="research_coverage_list list">
										<h3>${tableRowData[1]}</h3>
										<h4>${currentResearchReportOfferingType} | ${currentResearchReportOfferingYear}</h4>
										<p>${tableRowData[2]}</p>
									</div>
								</c:forEach>
								<c:if test="${researchReportOfferingTableCreated == true}">	
									</div>
								</c:if>	
							</c:if>

							<c:if test="${not empty vendorAwardDetails}">
								<c:set var="firstAssetTable">false</c:set>
								<c:set var="firstVendor">false</c:set>
								<c:set var="awardTableCreated">false</c:set>
								<c:forEach items="${vendorAwardDetails}" var="tableRowData">
									<c:if test="${awardTableCreated == false}">
										<c:set var="awardTableCreated">true</c:set>
										<div class="offerings_info">
											<h4>Award Details</h4>
									</c:if>
									<div class="awards_info_list list">
										<h3>${tableRowData[0]}</h3>
										<h4>${tableRowData[1]} | ${tableRowData[3]} | ${tableRowData[2]}</h4>
									</div>
								</c:forEach>
								<c:if test="${awardTableCreated == true}">	
									</div>
								</c:if>	
							</c:if>
						</c:when>
					</c:choose>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-3">
					<div class="inner-sidebar-wrap" style="display: none;margin-top: 23px;">
						<div class="sidebar-ctn-wrap cnt-ctn-wrap">
							<div class="head">
								<c:if test="${user.consumer != null}">
									<h3>Search New Vendor</h3>
								</c:if>
								<c:if test="${user.vendor != null}">
									<h3>Review Vendor Offerings</h3>
								</c:if>
							</div>
							<div class="content" id="sidelinks">
								<c:if test="${user.consumer != null}">
									<input type="hidden" user-type="consumer" value="${user.consumer.tags}" />
								</c:if>
								<c:if test="${user.vendor != null}">
									<input type="hidden" user-type="vendor" value="${user.vendor.companyType}" />
								</c:if>
								
								<ul id="SOLUTIONS_ul">

								</ul>
							</div>
						</div>
						<div class="sidebar-ctn-wrap cnt-ctn-wrap">
							<div class="head">
								<h3>Need More Info</h3>
							</div>
							<div class="content">
								<ul>
									<li><a
									href="${pageContext.request.contextPath}/view/common/inner.jsp?nav=CONTACT">
										<span class="fa fa-chevron-right"></span> Contact Us
								</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<c:if test="${sessionScope.loggedInRole == 'ROLE_ADMIN'}">
				<jsp:include page="adminMenu.jsp"></jsp:include>
			</c:if>
		</div>
		<jsp:include page="common/footer.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
		progressLoader(false);
		var userType = $(".inner-sidebar-wrap #sidelinks input").attr('user-type');
		var tags = $(".inner-sidebar-wrap #sidelinks input").val().split(',');
		var li = '';
		var link = '#';
		for(i in tags) {
			if(tags[i] == 'Data Aggregator') {
				if (userType == 'consumer') {
					link = '/marketaggregators';
				} else if (userType == 'vendor') {
					link = '/vendormyofferings?RaYUnA=YW1pdF92ZW5kb3I=';
				} else {
					link = '${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=mdvad&RaYUnA=YW1pdF92ZW5kb3I='
				}
			} else if(tags[i] == 'Trading Application') {
				if (userType == 'consumer') {
					link = '/tradingapplicationvendor';
				} else if (userType == 'vendor') {
					link = '/vendormyofferings?RaYUnA=YW1pdF92ZW5kb3I=';
				} else {
					link = '${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=tavd&RaYUnA=YW1pdF92ZW5kb3I='
				}
			} else if(tags[i] == 'Analytics Application') {
				if (userType == 'consumer') {
					link = '/financialanalyticsapplicationvendor';
				} else if (userType == 'vendor') {
					link = '/vendormyofferings?RaYUnA=YW1pdF92ZW5kb3I=';
				} else {
					link = '${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=faavd&RaYUnA=YW1pdF92ZW5kb3I='
				}
			} else if(tags[i] == 'Research Report') {
				if (userType == 'consumer') {
					link = '/researchreportprovidersvendor';
				} else if (userType == 'vendor') {
					link = '/vendormyofferings?RaYUnA=YW1pdF92ZW5kb3I=';
				} else {
					link = '${pageContext.request.contextPath}/view/common/inner.jsp?nav=SOLUTIONS&subNav=frrpd&RaYUnA=YW1pdF92ZW5kb3I='
				}
			}
			li = li + "<li data-div-id='SOLUTIONS_frrpd'>"
					+	"<a href='" + link + "''><span class='fa fa-chevron-right'></span> " + tags[i] + "</a>"
					+ "</li>";			
		}
		$("#SOLUTIONS_ul").append(li);

		$(".inner-sidebar-wrap").css({"display": "block"});
	</script>
</html>