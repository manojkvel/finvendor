<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@taglib uri="/WEB-INF/finvendor.tld" prefix="finVen"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.css">
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
</head>
<body>
	<div class="cd-user-modal" > <%-- this is the entire modal form, including the background --%>
		<div class="cd-user-modal-container" > <%-- this is the container wrapper --%>
			<div class="modal-wrapper" >
				<span class="logmod__close" id="login-close">X</span>
				<ul class="cd-switcher" >
					<li><a href="#0">Login</a></li>
					<li><a href="#0">Register</a></li>
				</ul>
				<ul class="cd-switcher-forgot-password">
					<li><span>Forgot Password</span></li>
				</ul>
				<ul class="cd-switcher-change-password">
					<li><span>Change Password</span></li>
				</ul>
				<ul class="cd-switcher-success-message">
					<li><span>Thank You</span></li>
				</ul>
				
				<p id="generic-error-message" class="errorMessage"></p>
				<%-- login form --%>
				<div id="cd-login"> 
				<div id="loginSpan">
					<form class="cd-form" action="j_spring_security_check" method="post" id="login-submit">
						<input type="hidden" id="redirectLink" name="redirectLink"/>
						<div class="form-wrapper">
							<ul>
								<li>
									<label for='username'>UserName</label>
									<input class="form-control" id="signin-username" data-mandatory="Y" type="email" placeholder="UserName*" name="username" />
								</li>
								<li>
									<label for='password'>Password</label>
									<div style="position: relative; display: inline;">
										<input class="form-control" id="signin-password" data-mandatory="Y" type="password"  placeholder="Password*" name="password" />
										<span class="hide-password fa fa-eye"></span>
									</div>
								</li>
								<li>
									<label for='password'>&nbsp;</label>
									<div class="bold" style="width: 50%;float: left;">
										<p id="checkbox" style="display:block;">
											<span>
												<input type="checkbox" id="remember-me" class="user-checkbox" style="top: 0px;" />&nbsp;Remember me
											</span>
											<a href="#0" style="float: right;" id="forgot-password-link">Forgot Password?</a>
										</p>
									</div>
								</li>
								<li>
									<div class="submit-btn-container">
										<input class="btn info submit-btn" type="submit" value="Login" />
									</div>
								</li>
								<li>
									<div class="align-center bold">
										Not a member as yet? <a id="sign-up-link" href="#0">Register Now</a>
									</div>
								</li>
							</ul>
							<div id="loadinglg" class="login_loading" ></div>
							<div><br></div>
						</div>
					</form>
				</div>
				</div> <%-- cd-login --%>			
	 			<%-- sign up form --%>
				<div id="cd-signup" >
					<div id="userRegisterSpan">
						<form class="cd-form" action="registration" id="user_submit_form">
							<div class="form-wrapper">
								<ul>
									<li>
										<label for='username'>UserName</label>
										<input class="form-control" id="signup-username" data-mandatory="Y" type="text" placeholder="UserName*" name="username" />
									</li>
									<li>
										<label for='email'>E-mail</label>
										<input class="form-control" id="signup-email" data-mandatory="Y" type="email" placeholder="E-mail*" name="email" />
									</li>
									<li>
										<label for='password'>Password</label>
										<div style="position: relative; display: inline;">
											<input class="form-control" id="signup-password" data-mandatory="Y" type="password"  placeholder="Password*" name="password" />
											<span class="hide-password fa fa-eye"></span>
										</div>
									</li>
									<li>
										<label for='company'>Company</label>
										<input class="form-control" id="signup-company" data-mandatory="Y" type="text"  placeholder="Company*" name="company" />
									</li>
									<li class="normal-line">
										<label for='companytype'>Company Type</label>
										<select id="signup-companytype" multi-data-mandatory="Y" name="companytype" class="form-control"> 
											<option value="Individual Investor" id="1" title='Individual Investor' selected="selected">Individual Investor</option>
											<!--<option value="Independent Research Analyst" id="2" title='Independent Research Analyst'>Independent Research Analyst</option>
											<option value="Research Broker" id="3" title='Vendor'>Research Broker</option>-->
											<option value="University/Phd Student" id="4" title='University/Phd Student'>University/Phd Student</option>
										</select>
									</li>
									<li style="clear: both;text-align: center; width: 100%;">
										<div id="checkbox">
											<input type="checkbox" id="accept-terms" class="user-checkbox" name="acceptterms" value="acceptterms" /> 
											<label for="accept-terms" >
												&nbsp;I agree to the  <a href="#" onClick="openPopupCenter('${pageContext.request.contextPath}/view/termsAndConditions.jsp', 'termsAndConditions', 800, 600)">Terms & Conditions</a> governing the use of Finvendors.
											</label>
											<!--<p style="clear: both;">
												<input type="checkbox" id="newslettersAndAlerts" class="user-checkbox" name="newslettersAndAlerts" value="newslettersAndAlerts" /> 
												<label for="newslettersAndAlerts" >
													&nbsp;I wish to get regular newsletters & alerts
												</label>
											</p>-->											
										</div>
									</li>
									<li style="width:100%;">
										<div class="submit-btn-container">
											<input class="btn info submit-btn" type="submit" value="Create account" />
										</div>
									</li>
								</ul>
								<div><label id="sucessMessage" class="errorMessage" style="color:green"></label></div>
								<div><label id="errorMessage" class="errorMessage"></label></div>
								<div id="loadingrg" class="login_loading" ></div>
							</div>
						</form>
					</div>
				</div> <%-- cd-signup --%>			
				<%-- reset password form --%>
				<div id="cd-reset-password"> 
					<span id="forgotPasswordSpan">
						<form class="cd-form" id="reset-password-form">
							<div class="form-wrapper">
								<p style="padding: 0 30px; line-height: 30px; padding-bottom: 20px; font-weight: bold;">Lost your password?<br>Please enter your email address. You will receive a new Password to login.</p>
								<ul>									
									<li>
										<label for='email'>E-mail</label>
										<input class="form-control" id="forgot-password-email" data-mandatory="Y" type="text" placeholder="E-mail*" name="email" />
									</li>
									<li>
										<div class="submit-btn-container">
											<input class="btn info submit-btn" type="button" value="Reset Password" />
										</div>
									</li>
									<li>
										<div class="align-center bold">
											<a id="login-link" href="#0">Back to Login</a>
										</div>
									</li>
								</ul>
								<div id="loadingfp" class="login_loading" ></div>
								<div><br></div>
							</div>
						</form>
					</span>
				</div> <%-- cd-reset-password --%>	
				<div id="cd-change-password"> 
				<div id="changePasswordSpan">
					<form class="cd-form" id="change-password-form">
						<div class="form-wrapper">
							<ul>
								<li>
									<label for='username'>UserName</label>
									<input class="form-control" id="username" data-mandatory="Y" type="text" placeholder="UserName*" name="username" />
								</li>
								<li>
									<label for='password'>Old Password</label>
									<div style="position: relative; display: inline;">
										<input class="form-control" id="old-password" data-mandatory="Y" type="password"  placeholder="Password*" name="password" />
										<span class="hide-password fa fa-eye"></span>
									</div>
								</li>
								<li>
									<label for='password'>New Password</label>
									<div style="position: relative; display: inline;">
										<input class="form-control" id="new-password" data-mandatory="Y" type="password"  placeholder="Password*" name="password" />
										<span class="hide-password fa fa-eye"></span>
									</div>
								</li>
								<li>
									<label for='password'>Confirm New Password</label>
									<div style="position: relative; display: inline;">
										<input class="form-control" id="confirm-new-password" data-mandatory="Y" type="password"  placeholder="Password*" name="password" />
										<span class="hide-password fa fa-eye"></span>
									</div>
								</li>
								<li>
									<div class="submit-btn-container">
										<input class="btn info submit-btn" type="button" value="Change Password" />
									</div>
								</li>
							</ul>
							<div id="loadingcp" class="login_loading" ></div>
							<div><br></div>
						</div>
					</form>
				</div>
			</div> <%-- cd-change-password --%>			
				<div id="cd-reg-success-message">
					<p class="cd-form-message">
						You have successfully registered on FinVendor<br>
						You will shortly receive a validation link in registered email id.<br>
						Please verify email account with validation link to activate your account
					</p>
				</div>
			</div>		
		</div> <%-- cd-user-modal-container --%>
	</div> <%-- cd-user-modal --%>
</body>
</html>