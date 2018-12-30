var Vendor = function () {
	return {
		onSubmit: function(){},
		//main function to initiate the module
		init: function () {
			alert('vemula--');
			$("#vendor_submit_form").validate({
				doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
				errorElement: 'span', //default input error message container
				errorClass: 'validate-inline', // default input error message class
				focusInvalid: false, // do not focus the last invalid input
				rules: {
					"uname": {
						required : true
					},
					"password" : {
						required : true
					},
					"fname" : {
						required : true
					},
					"lname": {
						required : true
					},
					"email" : {
						required : true
					},
					"telephone" : {
						required : true
					},
					"company" : {
						required : true
					},
					"companytype" : {
						required :{depends: function(element) {
							if($('#select_field').val() == '' ||  $("#companytype").val() == '-SELECT-'){
								//Set predefined value to blank.
								$('#companytype').val('');
							}
							return true;
						}}
					},
					"tags" : {
						required : true
					}
				},
				messages: {
					"uname": {
						required : "This field is required"
					},
					"password" : {
						required : "This field is required"
					},
					"fname" : {
						required : "This field is required"
					},
					"lname": {
						required : "This field is required"
					},
					"email" : {
						required : "This field is required"
					},
					"telephone" : {
						required : "This field is required"
					},
					"company" : {
						required : "This field is required"
					},
					"companytype" : {
						required : "This field is required"
					},
					"tags" : {
						required : "This field is required"
					}
				},

				errorPlacement : function(error, element) {
					$(element).closest('.input').addClass('error');
					error.insertAfter(element);
				},
				highlight : function(element) {
					// hightlight error inputs
					$(element).closest('.help-inline').removeClass('ok');
					// display OK icon
					$(element).closest('.input').removeClass('success').addClass('error');
					// set error class to the control group
				},
				unhighlight : function(element) {
					// revert the change done by hightlight
					$(element).closest('.input').removeClass('error');
					$('.alert-error', $(this)).hide();
					// set error class to the control group
				},
				success : function(form) {
					$(this).find('label.success, label.error').remove();
					$(this).find('error').remove();
					$('.alert-error', $(this)).hide();
				},
				submitHandler: function (form) {
					alert('rayulu vemula---:');
					document.getElementById("vendor_submit_form").submit();
					//add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
				}

			});
			$('#form_wizard_2').find('.button-previous').hide();
			$('#form_wizard_2 .button-submit').click(function () {
				alert('Finished! Hope you like it :)');
			}).hide();

			$('#form_wizard_1').find('.button-previous').hide();
			$('#form_wizard_1 .button-submit').click(function () {
				//   alert('Finished! Hope you like it :)');
			}).hide();
			return this;
		}

	};

}();


function validateUsername() {
	var username= $("#signup-username").val();
	if(username !=null && username.length > 0){
		//uname = encode64(username);
		uname = username;
		$.ajax({
			type: 'POST',
			url:  "checkregisteruservalidation?VEuLA="+uname,
			cache: false,
			success: function(output) {
				if (output.match("true")) {
					document.getElementById("errMessage").innerHTML = "Username already registered.";
				} else {
					document.getElementById("errMessage").innerHTML = "";
				}
			}
		});
	}
}
function validateEmailId() {
	var emailId = document.getElementById("signup-email").value;
	if(emailId !='' && emailId.length > 0){
		//emailId = encode64(emailId);
		var url = "emailvalidation?VeM="+emailId;
		$.ajax({
			type: 'POST',
			url: url,
			cache: false,
			success: function(output) {
				if (output.match("success")) {	
					document.getElementById("emailerrMsg").innerHTML = "Please enter company mails with valid emailId.";
				} else if (output.match("failed")) {
					document.getElementById("emailerrMsg").innerHTML = "";
					$.ajax({
						type: 'POST',
						url: "checkemailvalidationexist?RAyVE="+emailId,
						cache: false,
						success: function(output) {
							if (output.match("true")) {	
								document.getElementById("emailexistMsg").innerHTML = "Email already registered.";
							} else {
								document.getElementById("emailexistMsg").innerHTML = "";
							}
						}
					});
				}
			}
		});
	}

}

function validateVendorPrimaryEmailId() {
	var emailId = document.getElementById("personalvenprimemail").value;
	if(emailId !='' && emailId.length > 0){
		//emailId = encode64(emailId);
		var url = "emailvalidation?VeM="+emailId;
		$.ajax({
			type: 'POST',
			url: url,
			cache: false,
			success: function(output) {
				if (output.match("success")) {	
					document.getElementById("emailerrPrimaryMsg").innerHTML = "Please enter valid E-mail Id";
				} else if (output.match("failed")) {
					document.getElementById("emailerrPrimaryMsg").innerHTML = "";
					$.ajax({
						type: 'POST',
						url: "checkemailvalidationexist?RAyVE="+emailId,
						cache: false,
						success: function(output) {
							if (output.match("true")) {	
								document.getElementById("emailPrimaryexistMsg").innerHTML = "Email already registered.";
							} else {
								document.getElementById("emailPrimaryexistMsg").innerHTML = "";
							}
						}
					});
				}
			}
		});
	}

}

function validateVendorSecondaryEmailId() {
	var emailId = document.getElementById("personalvensecemail").value;
	if(emailId !='' && emailId.length > 0){
		//emailId = encode64(emailId);
		var url = "emailvalidation?VeM="+emailId;
		$.ajax({
			type: 'POST',
			url: url,
			cache: false,
			success: function(output) {
				if (output.match("success")) {	
					document.getElementById("emailerrSecondaryMsg").innerHTML = "Please enter valid E-mail Id";
				} else if (output.match("failed")) {
					document.getElementById("emailerrSecondaryMsg").innerHTML = "";
					$.ajax({
						type: 'POST',
						url: "checkemailvalidationexist?RAyVE="+emailId,
						cache: false,
						success: function(output) {
							if (output.match("true")) {	
								document.getElementById("emailSecondaryexistMsg").innerHTML = "Email already registered.";
							} else {
								document.getElementById("emailSecondaryexistMsg").innerHTML = "";
							}
						}
					});
				}
			}
		});
	}

}
function validateCompanyURL() {
	var comapnyURL = document.getElementById("personalvencompanyurl").value;
	var regex = /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/;
	if(comapnyURL !='' && comapnyURL.length > 0){
		if(!regex.test(comapnyURL)){
			document.getElementById("companyURLValidMsg").innerHTML = "Please enter valid URL"; 
		}else{
			document.getElementById("companyURLValidMsg").innerHTML = "";
		}
	}

}

/*
function validatePhoneNumber() {
	var phoneNum = document.getElementById("personalvenphonenumber").value;
	var numericReg = /^([0-9]{8,10})$/;
	if(!numericReg.test(phoneNum)) {
		document.getElementById("phoneNumberMsg").innerHTML = "Please enter valid Phone Number";
		return;
    }
	if(phoneNum !='' && phoneNum.length > 0){
		phoneNum = encode64(phoneNum);
		var url = "phoneNumValidation?RaYuLu="+phoneNum;
		$.ajax({
			type: 'POST',
			url: url,
			cache: false,
			success: function(output) {
				if (output.match("success")) {	
					document.getElementById("phoneNumberMsg").innerHTML = "Please enter valid Phone Number";
				} else if (output.match("failed")) {
					document.getElementById("phoneNumberMsg").innerHTML = "";
				}
			}
		});
	}

}
function validatePhoneNumberCode() {
	var phoneNum = document.getElementById("personalvenphonenumbervalue").value;
	var numericReg = /^((\+){1}91){1}[0-9]{2,3}$/;
	if(!numericReg.test(phoneNum)) {
		document.getElementById("phoneNumberMsg").innerHTML = "Please enter valid Phone Number";
		return;
    }
}
 */
/*
function validateDesignation() {
	var designation = document.getElementById("personalvendesignation").value;
	var regex = /^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/;
	if(designation !='' && designation.length > 0){
		if(!regex.test(designation)){
			 document.getElementById("designationMsg").innerHTML = "Please enter valid Designation value";
		 }else{
			 document.getElementById("designationMsg").innerHTML = "";
		 }
		/*designation = encode64(designation);
		var url = "validateDesignation?ChEnGaLrAy="+designation;
		$.ajax({
			type: 'POST',
			url: url,
			cache: false,
			success: function(output) {
				if (output.match("success")) {	
					document.getElementById("designationMsg").innerHTML = "Please enter valid string.";
				} else if (output.match("failed")) {
					document.getElementById("designationMsg").innerHTML = "";
				}
			}
		});*/
/*	}

}
 */

function userRegisteration() {

	var username = $("#signup-username").val();
	var password = $("#signup-password").val();
	var email = $("#signup-email").val();
	var company = $("#signup-company").val();
	var companytype = $("#signup-companytype").val();
	var tags = $("#sigup-tags").val();
	var termsAndCondition = $('#accept-terms').val();

	consumerSelected = false;
	$('#signup-companytype :selected').each(function(i, selectedElement) {
		selectedCompanyType = $(selectedElement).val();
		selectedCompanyType = selectedCompanyType + "";
		if (selectedCompanyType.substr(0, 14) == 'Financial Firm' || 
				selectedCompanyType.substr(0, 10) == 'University') {
			consumerSelected = true;
			return false;
		}
	});
	/*vendorSelected = false;
	if(consumerSelected) {
		$('#sigup-tags :selected').each(function(i, selectedElement) {
			vendorSelected = true;
			return false;
		});
		if(!vendorSelected) {
			$('#generic-error-message').html('Please select vendor area of interest');
			return false;
		}
	}*/

	if(termsAndCondition != null && termsAndCondition != '' && 
			$('#accept-terms').prop('checked') && 
			termsAndCondition.match('acceptterms')) {	
		$('#loadingrg').show();
		$.ajax({
			type: 'POST',
			//url:  "registration?VEuMlA="+username+"&RaYulU="+password+"&ChEnGA="+email+"&LaKS="+company+"&ZaB="+companytype+"&NoR="+tags,
			url:  "/registration?VEuMlA="+username+"&RaYulU="+password+"&ChEnGA="+email+"&LaKS="+company+"&ZaB="+companytype,
			cache: false,
			success: function(output) {
				$('#loadingrg').hide();

				if (output.match("true")) {
					$('#generic-error-message').html(""); //You've registered successfully..!
					reg_success_message_selected();
					$("form").trigger("reset");
				}else {
					$('#generic-error-message').html("Error registering user. Please contact <a href='mailto:support@finvendor.com'>Fin Vendor support</a>");
				}
			}
		});
	}else {
		$('#generic-error-message').html('Please accept the terms and conditions');
		$('#loadingrg').hide();

	}	
}


function updateUserRegisteration() {
	progressLoader(true);
	var username = $("#userName").val().trim();
	var email = $("#email").val().trim();
	var companytype = $("#companyType").selectpicker('val');
	//var tags = $("#vendorAreaOfInterest").selectpicker('val');

	var userRole = $("#account_details .action_btn").attr("data");

	if(email != '') {
		$("#account_details #email").removeClass("error_field");
	} else {
		$("#account_details #email").addClass("error_field");
	}

	if(companytype != null) {
		//companytype = companytype.join();
		$("#account_details #companyType").parent().find("button").removeClass("error_field");
	} else {
		$("#account_details #companyType").parent().find("button").addClass("error_field");
	}

	/*if(userRole == "userConsumer") {
		if(tags != null) {
			tags = tags.join();
			$("#account_details #vendorAreaOfInterest").parent().find("button").removeClass("error_field");
		} else {
			$("#account_details #vendorAreaOfInterest").parent().find("button").addClass("error_field");		
			$("#account_details .generic_message .alert").removeClass("alert-success").addClass("alert-danger").text('Please enter mandatory fields').show();
			return false;
		}
	}*/
	tags = null;

	if(email != '' && companytype != null) {

		$.ajax({
			type: 'POST',
			url:  "updateAccountSettings?userName="+username+"&companyType="+companytype+"&tags="+tags+"&email="+email,
			cache: false,
			success: function(output) {
				progressLoader(false);		
				$("#account_details .generic_message .alert").removeClass("alert-danger").addClass("alert-success").text('').hide();


				$("#account_details").slideUp();
				$("#top-card").slideDown();
				$(".profile-card .full-name").html(username);
				$(".profile-card .contacts").html(email);
				$(".profile-card .company-details .type").html(companytype);
				$(".profile-card .company-details .tags").html(tags);
			}
		});
	} else {
		progressLoader(false);	
		$("#account_details .generic_message .alert").removeClass("alert-success").addClass("alert-danger").text('Please enter mandatory fields').show();
	}
}


$(document).ready(function() {
	$("#account_settings #top-card #edit-details").on("click", function() {
		progressLoader(false);
		$("#account_details").slideDown();
		$("#top-card").slideUp();
	});

	$("#account_settings #account_details .save").on("click", function() {
		if(!validateSpanElements('account_details')) {
			progressLoader(false);
			return false;
		}
		if(!updateUserRegisteration()) {
			progressLoader(false);
			return false;
		}
	});

	$("#account_settings select[name=companyType]").on('change', function() {
		var userRole = $("#account_details .action_btn").attr("data");
		if(userRole == "userConsumer") {
			$('#vendor_area_of_interest .selectpicker').selectpicker('deselectAll');
			$('#vendor_area_of_interest .selectpicker').selectpicker('refresh');
		}
	});
});