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
	<link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/resources/css/finvendor.css" rel="stylesheet" >	
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/superfish.js"></script>		
	<script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/dataTables.jqueryui.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
	
	<style type="text/css">
	.login_loading {
		min-height: 50px;
		background:  url(<%=request.getContextPath() %>/resources/images/bx_loader.gif) center center no-repeat #fff;
		height: 100%;
		width: 100%;
		position: absolute;
		top: 0;
		left: 0;
		z-index: 2000;
		display: none;
		opacity: 0.6;
		}
	</style>
	
	<script language="javascript">		
		$( document ).ready(function() {
			<c:if test="${user.vendor != null}">
				$('#register_vendor_area_of_interest').hide();
			</c:if>
			$('#sigup-tags-mandatory-check').hide();
	         $('select[name="companytype"]').change(function() {
	            var selectedCompanyType =$(this).val(); 
	            selectedCompanyType = selectedCompanyType + "";
	            if (selectedCompanyType.substr(0, 14) == 'Financial Firm' || 
	            		selectedCompanyType.substr(0, 10) == 'University') {
	            	$('#register_vendor_area_of_interest').show();
	            	$("#register_vendor_area_of_interest option:selected").removeAttr("selected");
	            	$('#sigup-tags-mandatory-check').show();
	            }else{
	            	$('#register_vendor_area_of_interest').hide();
	            	$('#sigup-tags-mandatory-check').hide();
	            }
	            vendorSelected = false;
	            consumerSelected = false;
	            $('#signup-companytype :selected').each(function(i, selectedElement) {
	            	 companyType = $(selectedElement).val();
	            	 companyType = companyType + "";
	            	 if (companyType.substr(0, 14) == 'Financial Firm' || 
	            			 companyType.substr(0, 10) == 'University'){
	            		 consumerSelected = true;
	 	            }else{
	 	            	vendorSelected = true;
	 	            }
	            });
	           	if(vendorSelected && consumerSelected){
	           		document.getElementById('signupCompanyTypeErrorMsg').innerHTML = 'Please select either Vendor or Consumer as Company Type';
	           	}else{
	           		document.getElementById('signupCompanyTypeErrorMsg').innerHTML = '';
	           	}
	        });
	    });
		
	</script>	
</head>
<body>
	<jsp:include page="common/head.jsp"></jsp:include>
	<jsp:include page="common/header.jsp?hideTabsAfterLogIn=true"></jsp:include>
	<div class="container">
		<div class="inner-content">
			<div><label id="sucessMessage" class="errorMessage" style="color:green"></label></div>
			<div><label id="errorMessage" class="errorMessage"></label></div>
			<span id="userRegisterSpan">
				<form class="cd-form">												
					<div class="form-wrapper" style="line-height: 0;padding-top:30px">							
						<div class="control-group-row">
							<div class="form-group medium half-width"> 
								<label for="signup-username" >UserName</label>
								<input type="text" id="signup-username" placeholder="Username*" name="username" 
									class="form-control" readonly value="${user.userName}"> &nbsp;&nbsp;&nbsp;
								<div><label id="signupUserNameErrorMsg" class="errorMessage"></label></div>
							</div>
							<div class="form-group medium half-width">
								<label for="signup-email">E-mail</label>
								<input type="text" id="signup-email" data-mandatory="Y" placeholder="E-mail*" name="email"
									class="form-control" value="${user.email}"
									onblur="if (validateWithRegularExpression(this, 'signupEmailErrorMsg', regularExpressionMap['EMAIL'], 'EMAIL', true) && validatePersonalEmailId(this, 'signupEmailErrorMsg')) validateAjax(this, 'checkExistingEmail','signupEmailErrorMsg')">
								<div><label id="signupEmailErrorMsg" class="errorMessage"></label></div>
							</div>								
						</div>
						<div>
							<div class="control-group-row">
								<div class="form-group medium half-width" id="register_company_type">
									<label for="signup-companytype">Company Type</label>
									<select id="signup-companytype" multi-data-mandatory="Y" name="companytype" class="form-control"   
										multiple="multiple" onblur="validateSelectNotNull(this.id, 'signupCompanyTypeErrorMsg', 'Company Type')"> 
										<%-- 
										<c:if test="${user.vendor != null}">
											<option value="Financial Firm - Sell side" id="2">Financial Firm - Sell side</option>
											<option value="Financial Firm - Buy side" id="3" >Financial Firm - Buy side</option>
											<option value="Financial Firm - Others" id="4">Financial Firm - Others</option>
										</c:if>
										--%>
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${finConsumerCompanySellSide != null}">
													<option value="Financial Firm - Sell side" id="2" selected>Financial Firm - Sell side</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Sell side" id="2">Financial Firm - Sell side</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${finConsumerCompanyBuySide != null}">
													<option value="Financial Firm - Buy side" id="3" selected>Financial Firm - Buy side</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Buy side" id="3" >Financial Firm - Buy side</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${finConsumerCompanyOther != null}">
													<option value="Financial Firm - Others" id="4" selected>Financial Firm - Others</option>
												</c:when>
												<c:otherwise>
													<option value="Financial Firm - Others" id="4">Financial Firm - Others</option>
												</c:otherwise>
											</c:choose>
										</c:if>									
										<%--
										<c:if test="${user.consumer != null}">
											<option value="Data Aggregator" id="5">Financial Vendor - Data Aggregators</option>
											<option value="Trading Application" id="6">Financial Vendor - Trading Applications</option>
											<option value="Analytics Application" id="7">Financial Vendor - Analytics Applications</option>
											<option value="Research Report" id="8">Financial Vendor - Research report Providers</option>
										</c:if>
										--%>
										<c:if test="${user.vendor != null}">
											<c:choose>
												<c:when test="${dataaggregator != null}">
													<option value="Data Aggregator" id="5" selected>Financial Vendor - Data Aggregators</option> 
												</c:when>
												<c:otherwise>
													<option value="Data Aggregator" id="5">Financial Vendor - Data Aggregators</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${tradingapplication != null}">
													<option value="Trading Application" id="6" selected>Financial Vendor - Trading Applications</option>
												</c:when>
												<c:otherwise>
													<option value="Trading Application" id="6">Financial Vendor - Trading Applications</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${analyticsapplication != null}">
													<option value="Analytics Application" id="7" selected>Financial Vendor - Analytics Applications</option>
												</c:when>
												<c:otherwise>
													<option value="Analytics Application" id="7">Financial Vendor - Analytics Applications</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${researchreport != null}">
													<option value="Research Report" id="8" selected>Financial Vendor - Research report Providers</option>
												</c:when>
												<c:otherwise>
													<option value="Research Report" id="8">Financial Vendor - Research report Providers</option>
												</c:otherwise>
											</c:choose>										
										</c:if>	
										<%-- 
										<c:if test="${user.vendor != null}">
											<option value="University/College" id="9">University/College</option>
											<option value="Other Firm" id="10">Other firm</option>
										</c:if>	
										--%>							
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${consumerCompanyUniversity != null}">
													<option value="University/College" id="9" selected>University/College</option>
												</c:when>
												<c:otherwise>
													<option value="University/College" id="9">University/College</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${consumerCompanyOtherFirm != null}">
													<option value="Other Firm" id="10" selected>Other firm</option>
												</c:when>
												<c:otherwise>
													<option value="Other Firm" id="10">Other firm</option>
												</c:otherwise>
											</c:choose>										
										</c:if>	
									</select>
									<span class="help-test">Choose one or more options</span>
									<div><label id="signupCompanyTypeErrorMsg" class="errorMessage"></label></div>
								</div>
								<div class="form-group medium half-width" id="register_vendor_area_of_interest">
									<label for="signup-vendorareaofinterest">Vendor Area of Interest</label>
									<select id="sigup-tags" name="tags" class="form-control" multiple="multiple">
										<c:if test="${user.vendor != null}">
											<option value="Data Aggregator">Data Aggregator</option>
											<option value="Trading Application">Trading Application</option>
											<option value="Analytics Application">Analytics Application</option>
											<option value="Research Report">Research Report</option>
										</c:if>
										<c:if test="${user.consumer != null}">
											<c:choose>
												<c:when test="${user.consumer.marketDataPreference == 'true'}">
													<option value="Data Aggregator" selected>Data Aggregator</option>
												</c:when>
												<c:otherwise>
													<option value="Data Aggregator">Data Aggregator</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.tradingAppPreference == 'true'}">
													<option value="Trading Application" selected>Trading Application</option>
												</c:when>
												<c:otherwise>
													<option value="Trading Application">Trading Application</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.analyticsAppPreference == 'true'}">
													<option value="Analytics Application" selected>Analytics Application</option>
												</c:when>
												<c:otherwise>
													<option value="Analytics Application">Analytics Application</option>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${user.consumer.researchReportPreference == 'true'}">
													<option value="Research Report" selected>Research Report</option>
												</c:when>
												<c:otherwise>
													<option value="Research Report">Research Report</option>
												</c:otherwise>
											</c:choose>										
										</c:if>
									</select> 
									<span class="help-test">Choose one or more options</span>
									<div><label id="signupVendorAreaOfInterestErrorMsg" class="errorMessage"></label></div>
								</div>	
							</div>								
						</div>
						<div>				
							<input type="checkbox" id="newslettersAndAlerts" name="newslettersAndAlerts" value="newslettersAndAlerts" style="float:left;"/> 							
							<label for="newslettersAndAlerts" >
								I wish to get regular 
								Newsletters & Alerts
								<%-- 
								<a href="#" onClick="openPopupCenter('${pageContext.request.contextPath}/view/newslettersAndAlerts.jsp', 'newslettersAndAlerts', 800, 600)">
									Newsletters & Alerts
								</a>
								--%>
							</label>				
						</div>		
						<div class="btn-group" style="padding-top:0">
							<input class="btn info block" type="button" value="Update Account" onclick="if (validateSpanElements('userRegisterSpan')) updateUserRegisteration()">
						</div>
						<div id="loadingrgupdate" class="login_loading" ></div>
					</div>
				</form>
			</span>
		</div>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
