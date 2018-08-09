<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.finvendor.util.RequestConstans"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finven"%>
<%@taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un"%>
<un:useConstants className="com.finvendor.util.RequestConstans" var="requestConstants"/>
<html>
<head>
	<title>Account Settings - Finvendor</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1, maximum-scale=1" />
	<meta name="description" content="" />
	<meta http-equiv="Pragma" content="no-cache">
	<meta name="author" content="" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
	
</head>
<body>
	<jsp:include page="common/head.jsp"></jsp:include>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/vendor_form_new.css">
	<div class="container">
		<div id="account_settings">
			<div id="top-card">
				<div class="profile-top-card top-card">
					<div class="profile-card vcard">
						<div class="profile-overview">
							<div class="profile-overview-content">
								<h2 class="full-name">
									${user.userName}
								</h2>
								<h4 class="contacts">
									${user.email}
								</h4>
								<table class="company-details">
									<tr>
										<th>Comany Type</th>
										<td class="type">
											<c:if test="${user.consumer != null}">
											${user.consumer.companyType}
											</c:if>
											<c:if test="${user.vendor != null}">
											${user.vendor.companyType}
											</c:if>
										</td>
									</tr>
									<!--<c:if test="${user.consumer != null}">
									<tr>
										<th>Vendor Area of Interest</th>
										<td class="tags">										
											<c:if test="${user.consumer != null}">
											${user.consumer.tags}
											</c:if>
										</td>
									</tr>
									</c:if>-->
								</table>
							</div>
						</div>
					</div>
				</div>
				<a class="btn" id="edit-details">
					<span class="fa fa-pencil"></span>Edit Details
				</a>
			</div>
			<div id="account_details" class="custom_form">
				<jsp:include page="common/progressLoader.jsp"></jsp:include>
				<form name="account_settings_form" id="account_settings_form">
					<div class="generic_message">
						<div class="alert"></div>
					</div>
					<ul>
						<li>
							<input type="text" name="userName" id="userName" value="${user.userName}" readonly="readonly" />
							<label class="default_select">Username</label>
						</li>
						<li>
							<input type="text" name="email" id="email" value="${user.email}"  readonly="readonly" />
							<label class="default_select">Email</label>
						</li>
						<li>
							<select class="selectpicker select_multiple" name="companyType" id="companyType">
							<c:if test="${user.consumer != null}">
											${user.consumer.companyType}
											</c:if>
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${user.consumer.companyType == 'Individual Investor'}">
													<option value="Individual Investor" id="1" selected>Individual Investor</option>
												</c:when>
												<c:otherwise>
													<option value="Individual Investor" id="1">Individual Investor</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.companyType == 'University/Phd Student'}">
													<option value="University/Phd Student" id="4" selected>University/Phd Student</option>
												</c:when>
												<c:otherwise>
													<option value="University/Phd Student" id="4">University/Phd Student</option>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${user.vendor != null}">
											<c:choose>
												<c:when test="${user.vendor.companyType == 'Independent Research Analyst'}">
													<option value="Independent Research Analyst" id="2" selected>Independent Research Analyst</option>
												</c:when>
												<c:otherwise>
													<option value="Independent Research Analyst" id="2">Independent Research Analyst</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.vendor.companyType == 'Research Broker'}">
													<option value="Research Broker" id="3" selected>Research Broker</option>
												</c:when>
												<c:otherwise>
													<option value="Research Broker" id="3">Research Broker</option>
												</c:otherwise>
											</c:choose>								
										</c:if>
							</select>
							<label class="default_select">Company Type</label>
						</li>
					</ul>

					<c:if test="${user.consumer != null}">
						<p class="action_btn" data="userConsumer" style="clear:both;">
							<a class="submit_btn save" data-toggle="tab">Save</a>
						</p>
					</c:if>
					<c:if test="${user.vendor != null}">
						<p class="action_btn" data="userVendor" style="clear:both;">
							<a class="submit_btn save" data-toggle="tab">Save</a>
						</p>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
	    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.2/js/bootstrap-select.min.js"></script>
</body>
</html>