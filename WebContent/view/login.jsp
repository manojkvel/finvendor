<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>

<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/finvendor.css">	
	<script src="${pageContext.request.contextPath}/resources/js/finvendorCommon.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/finvendorValidation.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
	
	<script language="javascript">
		
	$( document ).ready(function() {
			$('#register_vendor_area_of_interest').hide();
	         $('select[name="companytype"]').change(function(){
	            var selectedCompanyType =$(this).val(); 
	            selectedCompanyType = selectedCompanyType + "";
	            if (selectedCompanyType.substr(0, 14) == 'Financial Firm' || 
	            		selectedCompanyType.substr(0, 10) == 'University') {
	            	$('#register_vendor_area_of_interest').show();
	            	$("#register_vendor_area_of_interest option:selected").removeAttr("selected");
	            }else{
	            	$('#register_vendor_area_of_interest').hide();
	            }
	            vendorSelected = false;
	            consumerSelected = false;
	            $('#signup-companytype :selected').each(function(i, selectedElement) {
	            	 companyType = $(selectedElement).val();
	            	 companyType = companyType + "";
	            	 if (companyType.substr(0, 14) == 'Financial Firm' || 
	            			 companyType.substr(0, 10) == 'University'){
	            		 vendorSelected = true;
	 	            }else{
	 	            	consumerSelected = true;
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
	<div class="cd-user-modal"> <!-- this is the entire modal form, including the background -->
		<div class="cd-user-modal-container"> <!-- this is the container wrapper -->
		<span class="logmod__close" id="login-close" style="padding: 37px 37px 78px 8px; margin: 29px -375px 0 0;">Close</span>
			<ul class="cd-switcher">
				<li><a href="#0">Login</a></li>
				<li><a href="#0">Register</a></li>
			</ul>
			<div id="cd-login"> <!-- login form -->
				<form class="cd-form" style="padding: 7em;" action="j_spring_security_check" method="post" id="login-submit">
					<%--<font id="sucessMessage" style="padding:1px 2px 21px 160px; bottom: 306px; font-weight:bold; font-size: 12px; position: absolute; color: #2AABAB;"></font>--%>
				  <input type="hidden" id="redirectLink" name="redirectLink"/>
				  <div style="border: 1px solid #41BFDA; padding: 0px 44px 0px 44px; border-radius:22px;">
				  <div><br></div>
					<p class="fieldset">
						<label class="image-replace cd-username" for="signin-username">UserName*</label> 
						<%--<font id="errMsgValidate" style="bottom: -15px;font-size: 12px; position: absolute; color: #B94A48;"></font>--%>
						<input class="full-width has-padding has-border" id="signin-username" type="email" placeholder="UserName*" name="username">
						<!-- <span class="cd-error-message">Error message here!</span> -->
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signin-password">Password*</label>
						<input class="full-width has-padding has-border" id="signin-password" type="password"  placeholder="Password*" name="password">
						<a href="#0" class="hide-password">Un-Hide</a>
						<!-- <span class="cd-error-message">Error message here!</span> -->
					</p>

					<p class="fieldset"> <!-- <font id="sucessMessage" style="padding:1px 2px 21px 160px; bottom: -23px; font-size: 12px; position: absolute; color: #2AABAB;"></font> -->
						<input type="checkbox" id="remember-me" checked>
						<label for="remember-me">Remember me</label>
						<br>
						<div><label id="errMsgValidate" class="errorMessage"></label></div>
					</p>
					
					<p class="fieldset">
						<input class="full-width" type="submit" value="Login" onclick="loginSubmit()">
						<%-- '${finVen:resolveContextPath(pageContext.request.contextPath)}' --%>
					</p>
					<div><br></div>
					</div>
				</form>
				<p class="cd-form-bottom-message" style="margin-bottom: 9px;"><a href="#0" style="color: black;">Forgot Password?</a></p>
				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div> <!-- cd-login -->
 			<!-- sign up form -->
			<div id="cd-signup">
				<span id="userRegisterSpan">
					<form class="cd-form" action="registration" id="user_submit_form">
						<div style="border: 1px solid #41BFDA; border-radius:22px; padding-left: 8px;">
						<div><br></div>
						<div><br></div>
						<div><br></div>
						<div style="float:left">
							<p class="fieldset"> 
								<label class="image-replace cd-username" for="signup-username" >UserName</label>
								<input type="text" id="signup-username" placeholder="Username*" name="username" 
									class="full-width1 has-padding1 has-border" onblur="if (validateNotNull(this, 'signupUserNameErrorMsg')) validateAjax(this, 'checkExistingUser','signupUserNameErrorMsg')"> &nbsp;&nbsp;&nbsp;
								<div><label id="signupUserNameErrorMsg" class="errorMessage"></label></div>
							</p>
							<br>
							<p class="fieldset">
								<label class="image-replace cd-email" for="signup-email">E-mail</label>
								<input type="text" id="signup-email" data-mandatory="Y" placeholder="E-mail*" name="email"
									class="full-width1 has-padding1 has-border" 
									onblur="if (validateWithRegularExpression(this, 'signupEmailErrorMsg', regularExpressionMap['EMAIL'], 'EMAIL', true) && validatePersonalEmailId(this, 'signupEmailErrorMsg')) validateAjax(this, 'checkExistingEmail','signupEmailErrorMsg')">
								<div><label id="signupEmailErrorMsg" class="errorMessage"></label></div>
							</p>
						</div>
						<div style="float:left">
							<p class="fieldset">
								<label class="image-replace cd-password" for="signup-password">Password</label>
								<input type="password" id="signup-password" data-mandatory="Y" placeholder="Password*" name="password"
									class="full-width1 has-padding1 has-border" onblur="validateNotNull(this, 'signupPasswordErrorMsg')">
									<a href="#0" class="hide-password1">Hide</a>
								<div><label id="signupPasswordErrorMsg" class="errorMessage"></label></div>
							</p>
							<br>
							<p class="fieldset">
								<label class="image-replace cd-company" for="signup-company">Company</label>
								<input type="text" id="signup-company" data-mandatory="Y" placeholder="Company*" name="company"
									class="full-width1 has-padding2 has-border" onblur="validateNotNull(this, 'signupCompanyErrorMsg')">
								<div><label id="signupCompanyErrorMsg" class="errorMessage"></label></div>
							</p> 
						</div>
						<br><br>
						<div>
							<ul>
								<li style="float:left;" id="register_company_type">
									<p class="fieldset">
										<label class="image-replace cd-company" for="signup-companytype">Company Type</label>
										<select id="signup-companytype" multi-data-mandatory="Y" name="companytype" class="full-width1 has-padding1 has-border row-select1"   
											multiple="multiple" style="border: 1px solid #d0d0d0; height: 92px; padding: 0 10px"
											onblur="validateSelectNotNull(this.id, 'signupCompanyTypeErrorMsg', 'Company Type')"> 
											<option value ="-SELECT-" id="1" style="font-size: 13px; color: #529ECC" selected>-Select Company Type-</option>
											<option value="Financial Firm - Sell side" id="2">Financial Firm - Sell side</option>
											<option value="Financial Firm - Buy side" id="3" >Financial Firm - Buy side</option>
											<option value="Financial Firm - Others" id="4">Financial Firm - Others</option>
											<option value="Data Aggregator" id="5">Financial Vendor - Data Aggregators</option>
											<option value="Trading Application" id="">Financial Vendor - Trading Applications</option>
											<option value="Analytics Application" id="7">Financial Vendor - Analytics Applications</option>
											<option value="Research Report" id="8">Financial Vendor - Research report Providers</option>
											<option value="University/College" id="9">University/College</option>
											<option value="Other Firm" id="10">Other firm</option>
										</select>&nbsp;&nbsp;&nbsp;&nbsp;  
										<div><label id="signupCompanyTypeErrorMsg" class="errorMessage"></label></div>
									</p>
								</li>						
								<li style="float:left;" id="register_vendor_area_of_interest">
								 	<p class="fieldset">
										<label class="image-replace cd-company" for="signup-vendorareaofinterest">Vendor Area of Interest</label>
										<select id="sigup-tags" name="tags" class="full-width1 has-padding1 has-border" multiple 
											style="border: 1px solid #d0d0d0; border-radius: 5px; color: #7f7f7f; float: left; height: 93px; padding: 0 10px; width: 107%">
											<option value ="-SELECT-" style="font-size: 13px; color: #529ECC;">-Select Vendor area of Interest-</option>
											<option value="Data Aggregator">Data Aggregator</option>
											<option value="Trading Application">Trading Application</option>
											<option value="Analytics Application">Analytics Application</option>
											<option value="Research Report">Research Report</option>
										</select> 
										<div><label id="signupVendorAreaOfInterestErrorMsg" class="errorMessage"></label></div>
									</p>					  
								</li>
							</ul>
							<div class="clearfix"></div>
						</div>				
						<div>
							<ul>
								<li style="float:left">
									<p class="fieldset" style="padding: 25px 0px 0px 2px; font-size:14px; color: #529ECC">Choose one or more options</p>
								</li>
								<li style="float:left">
								 <p class="fieldset" style="padding: 10px 0px 0px 319px; font-size:14px; color: #529ECC">Choose one or more options</p>  
								</li>
							</ul>
						</div>
						<br>
						<p class="fieldset"> 
							<input type="checkbox" id="accept-terms" name="acceptterms" value="acceptterms" onChange="clearErrMessageForTermsAndConditions(this)"/> 
							<label for="accept-terms">
								I agree to the 
								<a href="#" onClick="openPopupCenter('${pageContext.request.contextPath}/view/termsAndConditions.jsp', 'termsAndConditions', 800, 600)">
									Terms & Conditions
								</a>
							</label>
							<br>
							<input type="checkbox" id="newslettersAndAlerts" name="newslettersAndAlerts" value="newslettersAndAlerts"/> 
							<label for="newslettersAndAlerts">
								I wish to get regular 
								<a href="#" onClick="openPopupCenter('${pageContext.request.contextPath}/view/newslettersAndAlerts.jsp', 'newslettersAndAlerts', 800, 600)">
									Newsletters & Alerts
								</a>
							</label>				
							<div><label id="errMessageForTermsAndConditions" class="errorMessage"></label></div>
							<div><label id="sucessMessage" class="errorMessage" style="color:green"></label></div>
							<div><label id="errorMessage" class="errorMessage"></label></div>
						</p>
						<p class="fieldset">  
							<input class="full-width has-padding" type="submit" value="Create account" onclick="if (validateSpanElements('userRegisterSpan')) userRegisteration()">
						</p> 
						<div><br></div>
						</div>
					</form>
				</span>
			</div> <!-- cd-signup -->
			<div id="cd-reset-password"> <!-- reset password form -->
				<p class="cd-form-message">Lost your password? Please enter your email address. You will receive a link to create a new password.</p>
				<form class="cd-form" style="padding: 7em;">
					<div style="border: 1px solid #41BFDA; border-radius:22px; padding: 0px 30px 0px 30px;">
					<div><br></div>
					<p class="fieldset">
						<label class="image-replace cd-email" for="reset-email">E-mail</label>
						<input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
						<span class="cd-error-message">Error message here!</span>
					</p>
					<p class="fieldset">
						<input class="full-width has-padding" type="submit" value="Reset password">
					</p>
				<div><br></div>
					</div>
				</form>
				<p class="cd-form-bottom-message"><a href="#0" style="color: black;">Back to log-in</a></p>
			</div> <!-- cd-reset-password -->
			<div id="cd-reg-success-message">
				<p class="cd-form-message" style="color:green">
					You have successfully registered on FinVendor<br>
					You will shortly receive a validation link in registered email id.<br>
					Please verify email account with validation link to activate your account
				</p>
				<div><br></div>
				<div><br></div>
			</div>
			<a href="#0" class="cd-close-form">Close</a>
			
		</div> <!-- cd-user-modal-container -->
	</div> <!-- cd-user-modal -->
</body>
</html>