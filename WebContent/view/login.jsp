<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/finvendor.css">	
	
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
		
	<script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
		
	<script language="javascript">		
		$( document ).ready(function() {
			$('#register_vendor_area_of_interest').hide();
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
		
		function clearErrMessageForTermsAndConditions(obj) {
			if (obj.checked) {
				document.getElementById('errMessageForTermsAndConditions').innerHTML = '';
			}
		}
	</script>
</head>
<body>
	<div class="cd-user-modal" > <%-- this is the entire modal form, including the background --%>
		<div class="cd-user-modal-container" style="position: relative;height: 100;"> <%-- this is the container wrapper --%>
			<div class="modal-wrapper" style="top:0">
				<span class="logmod__close" id="login-close">Close</span>
				<ul class="cd-switcher" >
					<li style="height:15px"><a href="#0" style="line-height:30px;height:35px;border: 3px solid #AAAAAA;"><b>Login</b></a></li>
					<li style="height:15px"><a href="#0" style="line-height:30px;height:35px;border: 3px solid #AAAAAA;"><b>Register</b></a></li>
				</ul>
			
				<%-- login form --%>
				<div id="cd-login"> 
				<span id="loginSpan">
					<form class="cd-form" action="j_spring_security_check" method="post" id="login-submit">
					<input type="hidden" id="redirectLink" name="redirectLink"/>
					<div class="form-wrapper">
						<div class="control-group-row">
							<div class="form-group medium half-width">
								<label>UserName</label>
								<input class="form-control" id="signin-username" type="email" placeholder="UserName*" name="username" data-mandatory="Y"
								onblur="validateNotNull(this, 'loginUsernameErrorMsg')">
								<div><label id="loginUsernameErrorMsg" class="errorMessage"></label></div>
							</div>
						</div>
						<div class="control-group-row">
							<div class="form-group medium half-width" style="position: relative">
								<label>Password</label>
								<input class="form-control" id="signin-password" type="password"  placeholder="Password*" name="password" data-mandatory="Y"
								onblur="validateNotNull(this, 'loginPasswordErrorMsg')">
								<a href="#0" class="hide-password">Un-Hide</a>
								<div><label id="loginPasswordErrorMsg" class="errorMessage"></label></div>
							</div>
						</div>
						<div class="form-group">
							<label for="remember-me"><input type="checkbox" id="remember-me" checked> Remember me</label>
							<br>
							<div><label id="errMsgValidate" class="errorMessage"></label></div>
						</div>			
						<div class="button-group">
							<input class="btn info" type="submit" value="Login" onclick="document.getElementById('errMsgValidate').innerHTML = ''; if (validateSpanElements('loginSpan')) loginSubmit(false)">
						</div>
						<div id="loadinglg" class="login_loading" ></div>
						<div><br></div>
						</div>
					</form>
				</span>
					<p class="cd-form-bottom-message" style="margin-bottom: 9px;"><a href="#0" style="color: black;">Forgot Password?</a></p>
				</div> <%-- cd-login --%>			
	 			<%-- sign up form --%>
				<div id="cd-signup" >
					<span id="userRegisterSpan">
						<form class="cd-form" action="registration" id="user_submit_form">
							<div class="form-wrapper" style="line-height: 0;padding-top:30px">							
								<div class="control-group-row">
									<div class="form-group medium half-width"> 
										<label for="signup-username" >UserName</label>
										<input type="text" id="signup-username" placeholder="Username*" name="username" 
											class="form-control" onblur="if (validateNotNull(this, 'signupUserNameErrorMsg')) validateAjax(this, 'checkExistingUser','signupUserNameErrorMsg')"> &nbsp;&nbsp;&nbsp;
										<div><label id="signupUserNameErrorMsg" class="errorMessage"></label></div>
									</div>
									<div class="form-group medium half-width">
										<label for="signup-email">E-mail</label>
										<input type="text" id="signup-email" data-mandatory="Y" placeholder="E-mail*" name="email"
											class="form-control" 
											onblur="if (validateWithRegularExpression(this, 'signupEmailErrorMsg', regularExpressionMap['EMAIL'], 'EMAIL', true) && validatePersonalEmailId(this, 'signupEmailErrorMsg')) validateAjax(this, 'checkExistingEmail','signupEmailErrorMsg')">
										<div><label id="signupEmailErrorMsg" class="errorMessage"></label></div>
									</div>
									
								</div>
								<div class="control-group-row" >
									<div class="form-group medium half-width" style="position: relative">
										<label for="signup-password">Password</label>
										<input type="password" id="signup-password" data-mandatory="Y" placeholder="Password*" name="password"
											class="form-control" onblur="validateNotNull(this, 'signupPasswordErrorMsg')">
											<a href="#0" class="hide-password1" style="line-height:1">Hide</a>
										<div><label id="signupPasswordErrorMsg" class="errorMessage"></label></div>
									</div>
									<div class="form-group medium half-width">
										<label for="signup-company">Company</label>
										<input type="text" id="signup-company" data-mandatory="Y" placeholder="Company*" name="company"
											class="form-control" onblur="validateNotNull(this, 'signupCompanyErrorMsg')">
										<div><label id="signupCompanyErrorMsg" class="errorMessage"></label></div>
									</div>
								</div>
							<div>
							<div class="control-group-row">
								<div class="form-group medium half-width" id="register_company_type">
									<label for="signup-companytype">Company Type</label>
									<select id="signup-companytype" multi-data-mandatory="Y" name="companytype" class="form-control"   
										multiple="multiple" onblur="validateSelectNotNull(this.id, 'signupCompanyTypeErrorMsg', 'Company Type')"> 
										<option value ="-SELECT-" id="1" selected>-Select Company Type-</option>
										<option value="Financial Firm - Sell side" id="2">Financial Firm - Sell side</option>
										<option value="Financial Firm - Buy side" id="3" >Financial Firm - Buy side</option>
										<option value="Financial Firm - Others" id="4">Financial Firm - Others</option>
										<option value="Data Aggregator" id="5">Financial Vendor - Data Aggregators</option>
										<option value="Trading Application" id="">Financial Vendor - Trading Applications</option>
										<option value="Analytics Application" id="7">Financial Vendor - Analytics Applications</option>
										<option value="Research Report" id="8">Financial Vendor - Research report Providers</option>
										<option value="University/College" id="9">University/College</option>
										<option value="Other Firm" id="10">Other firm</option>
									</select>
									<span class="help-test">Choose one or more options</span>
									<div><label id="signupCompanyTypeErrorMsg" class="errorMessage"></label></div>
								</div>
								<div class="form-group medium half-width" id="register_vendor_area_of_interest">
									<label for="signup-vendorareaofinterest">Vendor Area of Interest</label>
									<select id="sigup-tags" name="tags" class="form-control" multiple="multiple">
										<option value ="-SELECT-" selected>-Select Vendor area of Interest-</option>
										<option value="Data Aggregator">Data Aggregator</option>
										<option value="Trading Application">Trading Application</option>
										<option value="Analytics Application">Analytics Application</option>
										<option value="Research Report">Research Report</option>
									</select> 
									<span class="help-test">Choose one or more options</span>
									<div><label id="signupVendorAreaOfInterestErrorMsg" class="errorMessage"></label></div>
								</div>	
							</div>								
							</div>
							<div >				
								<input type="checkbox" id="accept-terms" name="acceptterms" value="acceptterms" style="float:left;" onChange="clearErrMessageForTermsAndConditions(this)"/> 
								
								<label for="accept-terms" >
									I agree to the 
									<a href="#" onClick="openPopupCenter('${pageContext.request.contextPath}/view/termsAndConditions.jsp', 'termsAndConditions', 800, 600)">
										Terms & Conditions
									</a>
								</label>
								
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
								<div><label id="errMessageForTermsAndConditions" class="errorMessage"></label></div>
								<div><label id="sucessMessage" class="errorMessage" style="color:green"></label></div>
								<div><label id="errorMessage" class="errorMessage"></label></div>
								<div class="btn-group" style="padding-top:0">
									<input class="btn info block" type="submit" value="Create account" onclick="if (validateSpanElements('userRegisterSpan')) userRegisteration()">
								</div>
								<div id="loadingrg" class="login_loading" ></div>
							</div>
						</form>
					</span>
				</div> <%-- cd-signup --%>			
				<%-- reset password form --%>
				<div id="cd-reset-password"> 
					<form class="cd-form" style="padding-top: 2em;">
						<div class="form-wrapper">
							<p>Lost your password? Please enter your email address. You will receive a link to create a new password.</p>
							<div class="form-group medium medium-width">
								<label for="reset-email">E-mail</label>
								<input class="form-control" id="reset-email" type="email" placeholder="E-mail">
								<span class="cd-error-message">Error message here!</span>
							</div>
							<div class="btn-group">
								<input class="btn info block" type="submit" value="Reset password">
							</div>
						</div>
					</form>
					<p class="cd-form-bottom-message"><a href="#0" style="color: black;">Back to log-in</a></p>
				</div> <%-- cd-reset-password --%>	
				<div id="cd-change-password"> 
				<span id="changePasswordSpan">
					<form class="cd-form" style="padding: 7em;" method="post" id="change-password-form">
						<div style="border: 1px solid #41BFDA; border-radius:22px; padding: 0px 30px 0px 30px;">
							<div><br></div>
							<p class="fieldset">
								<label class="image-replace cd-username" for="chg-password-username">UserName*</label>
								<input class="full-width has-padding has-border" id="chg-password-username" type="text" 
								placeholder="UserName*" name="chg-password-username" data-mandatory="Y"
								onblur="validateNotNull(this, 'changePasswordUsernameErrorMsg')">
								<div><label id="changePasswordUsernameErrorMsg" class="errorMessage"></label></div>						
							</p>
							<p class="fieldset">
								<label class="image-replace cd-password" for="signin-password-old-password">Old Password*</label>
								<input class="full-width has-padding has-border" id="old_password" type="password" 
								placeholder="Old Password*" name="old_password" data-mandatory="Y"
								onblur="validateNotNull(this, 'changePasswordOldPasswordErrorMsg')">
								<div><label id="changePasswordOldPasswordErrorMsg" class="errorMessage"></label></div>							
							</p>
							<p class="fieldset">
								<label class="image-replace cd-password" for="signin-password-new-password">New Password*</label>
								<input class="full-width has-padding has-border" id="new_password" type="password" 
								placeholder="New Password*" name="new_password" data-mandatory="Y"
								onblur="validateNotNull(this, 'changePasswordNewPasswordErrorMsg')">
								<div><label id="changePasswordNewPasswordErrorMsg" class="errorMessage"></label></div>						
							</p>
							<p class="fieldset">
								<label class="image-replace cd-password" for="signin-password-old-password">Confirm New Password*</label>
								<input class="full-width has-padding has-border" id="confirm_new_password" type="password"  
								placeholder="Confrim New Password*" name="confirm_new_password" data-mandatory="Y" 
								onblur="validateNotNull(this, 'changePasswordConfirmNewPasswordErrorMsg')">	
								<div><label id="changePasswordConfirmNewPasswordErrorMsg" class="errorMessage"></label></div>	
								<div><label id="errMsgValidateChangePassword" class="errorMessage"></label></div>				
							</p>
							<p class="fieldset">
								<input class="full-width" type="button" value="Change Password" onclick="document.getElementById('errMsgValidateChangePassword').innerHTML = ''; if (validateSpanElements('changePasswordSpan')) loginSubmit(true)">
							</p>
							<div><br></div>
						</div>
					</form>
				</span>
			</div> <%-- cd-change-password --%>			
				<div id="cd-reg-success-message">
					<p class="cd-form-message" style="color:green">
						You have successfully registered on FinVendor<br>
						You will shortly receive a validation link in registered email id.<br>
						Please verify email account with validation link to activate your account
					</p>
					<div><br></div>
					<div><br></div>
				</div>
				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div>		
		</div> <%-- cd-user-modal-container --%>
	</div> <%-- cd-user-modal --%>
</body>
</html>