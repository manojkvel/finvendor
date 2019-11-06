$(document).ready(function(){
	var validateNotNull = function(element, errorLabelId) {
		if (element.value == null || element.value == '' || $.trim(element.value) == '') {
			$('#' + element.id).addClass('error');
			return false;
		}else {
			$('#' + element.id).removeClass('error');
			return true;
		}
	};

	var validateSelectNotNull = function(selectId, errorLabelId, placeholder) {	
		var selector = '#' + selectId + ' :selected';
		var selectedElementValue = null;
		$(selector).each(function(i, selectedElement) {
			selectedElementValue = $(selectedElement).val();
			return returnValue = false;
		});

		if (selectedElementValue == null) {
			$('#' + errorLabelId).addClass('error');
			return false;
		}else {
			$('#' + errorLabelId).removeClass('error');
			return true;
		}
	}

	var validateAjax = function(element, ajaxUrl, errorLabelId,actionComponent) {
		if (element.value == null || element.value == '' || $.trim(element.value) == '') {
			return false;
		} else {
			var url = ajaxUrl + "?param="+ $.trim(element.value)+"&actionComponent="+actionComponent ;
			$.ajax({
				type: 'POST',
				url: url,
				cache: false,
				success: function(output) {
					if(output !="") {
						$('#generic-error-message').html(output);
						$('#' + element.id).addClass('error');
						$('#' + element.id).focus();
						return false;
					} else {
						$('#' + element.id).removeClass('error');
						$('#generic-error-message').html("");
					}
				}
			});
		}
	}

	var validateWithRegularExpression = function(element, errorLabelId, regularExpression, validationMessageKey, nullValidation) {
		if(nullValidation && !validateNotNull(element, errorLabelId)) {
			return false;
		}
		if(!nullValidation && !validateNotNull(element, errorLabelId)) {
			$('#' + element.id).removeClass('error');
			return true;
		}
		if(!regularExpression.test($.trim(element.value))) {
			$('#' + element.id).addClass('error');
			$('#generic-error-message').html(validationMessageMap[validationMessageKey]);
			$('#' + element.id).focus();
			return false;
		}else{
			$('#' + element.id).removeClass('error');
			$('#generic-error-message').html("");
			return true;
		}
	}

	var validatePersonalEmailId = function(element, errorLabelId) {
		var emailId = $.trim(element.value);
		//var validationList = ['@yahoo','@aol','@rediff','@hotmail','@in','@msn','@gmail'];
		var validationList = ['@msn'];
		for (i = 0; i < validationList.length; i++) {
			if(emailId.indexOf(validationList[i]) != -1) {
				$('#generic-error-message').html('Please enter valid Company Email id');
				$('#' + element.id).addClass('error');
				$('#' + element.id).focus();
				return false;
			}
		}
		$('#generic-error-message').html("");
		return true;
	}


	var validateSpanElements = function(spanName) {
		var selector = '#' + spanName + ' input.error';
		var returnValue = null;
		$(selector).each(function(index){
			if($(this).html() != null && $(this).html() != '') {
				$('#generic-error-message').html('Please remove all validation errors');
				return returnValue = false;
			}
		} );
		if(returnValue != null) {
			return false;
		}
		returnValue = null;
		selector = '#' + spanName + ' input[data-mandatory="Y"]';
		$(selector).each(function(index){
			if($(this).val() == null || $(this).val() == '' || $.trim($(this).val()) == '') {
				$('#generic-error-message').html('Please enter all mandatory fields');
				$(this).focus();
				return returnValue = false;
			}
		} );
		if(returnValue != null) {
			return false;
		}
		selector = '#' + spanName + ' textarea[data-mandatory="Y"]';
		$(selector).each(function(index) {
			if($(this).val() == null || $(this).val() == '' || $.trim($(this).val()) == '') {
				$('#generic-error-message').html('Please enter all mandatory fields');
				$(this).focus();
				return returnValue = false;
			}
		} );
		if(returnValue != null){
			return false;
		}
		selector = '#' + spanName + ' select[data-mandatory="Y"]';
		$(selector).each(function(index){
			if($(this).val() == '-SELECT-' || $(this).val() == 'SELECT') {
				$('#generic-error-message').html('Please enter all mandatory fields');
				$(this).focus();
				return returnValue = false;
			}
		} );
		if(returnValue != null){
			return false;
		}
		selector = '#' + spanName + ' select[multi-data-mandatory="Y"] :selected';
		$(selector).each(function(index, selectedElement) {
			value = $(selectedElement).val();
			value = value + "";
			if(value.substr(0, 8) == '-SELECT-' || value.substr(0, 6) == 'SELECT') {
				$('#generic-error-message').html('Please enter all mandatory fields');
				$(this).focus();
				return returnValue = false;
			}
		} );
		if(returnValue != null){
			return false;
		}
		return true;
	}

	$('#signin-username').on('blur', function(){
		validateNotNull(this,'signinUserNameErrorMsg');
	});

	$('#signin-password').on('blur', function(){
		validateNotNull(this,'signinPasswordErrorMsg');
	});

	$('#loginSpan .submit-btn').on('click', function(){
		if(validateSpanElements('loginSpan')) {
			loginSubmit(false);
		}
	});

	$('#signup-username').on('blur', function(){
		if(validateNotNull(this,'signupUserNameErrorMsg')) {
			validateAjax(this, '/checkExistingUser','signupUserNameErrorMsg');
		};
	});

	$('#signup-email').on('blur', function(){
		if(validateWithRegularExpression(this, 'signupEmailErrorMsg', regularExpressionMap['EMAIL'], 'EMAIL', true) && validatePersonalEmailId(this, 'signupEmailErrorMsg')) {
			validateAjax(this, '/checkExistingUser','signupUserNameErrorMsg');
		};
	});

	$('#signup-password').on('blur', function(){
		validateNotNull(this,'signupPasswordErrorMsg');
	});

	$('#signup-company').on('blur', function(){
		validateNotNull(this,'signupCompanyErrorMsg');
	});

	/*$('#signup-companytype').on('blur', function(){
		validateSelectNotNull(this,'signupCompanyTypeErrorMsg');
	});

	$('#sigup-tags').on('blur', function(){
		validateSelectNotNull(this,'signupVendorAreaOfInterestErrorMsg');
	});*/

	$('#userRegisterSpan .submit-btn').on('click', function(){
		if(validateSpanElements('userRegisterSpan')) {
			userRegisteration();
		}
	});

	$('#forgot-password-email').on('blur', function(){
		if(validateWithRegularExpression(this, 'forgotPasswordEmailErrorMsg', regularExpressionMap['EMAIL'], 'EMAIL', true) && validatePersonalEmailId(this, 'forgotPasswordEmailErrorMsg')) {
			validateAjax(this, '/checkExistingUser','forgotPasswordEmailErrorMsg');
		};
	});

	$('#forgotPasswordSpan .submit-btn').on('click', function(){
		if(validateSpanElements('forgotPasswordSpan')) {
			forgotPasswordSubmit();
		}
	});

	$('#chg-password-username').on('blur', function(){
		validateNotNull(this,'signinUserNameErrorMsg');
	});

	$('#chg-password-old-password').on('blur', function(){
		validateNotNull(this,'signinPasswordErrorMsg');
	});

	$('#chg-password-new-password').on('blur', function(){
		validateNotNull(this,'signinPasswordErrorMsg');
	});

	$('#chg-password-confirm-new-password').on('blur', function(){
		validateNotNull(this,'signinPasswordErrorMsg');
	});

	$('#changePasswordSpan .submit-btn').on('click', function(){
		if(validateSpanElements('changePasswordSpan')) {
			loginSubmit(true);
		}
	});

	$('#userRegisterSpan select[name="companytype"]').change(function() {
		var selectedCompanyType =$(this).val(); 
		selectedCompanyType = selectedCompanyType + "";
		/*if (selectedCompanyType.substr(0, 14) == 'Financial Firm' || 
				selectedCompanyType.substr(0, 10) == 'University') {
			$('#register_vendor_area_of_interest').show();
			$("#register_vendor_area_of_interest option:selected").removeAttr("selected");
			$('#sigup-tags-mandatory-check').show();
		}else{
			$('#register_vendor_area_of_interest').hide();
			$('#sigup-tags-mandatory-check').hide();
		}*/
		vendorSelected = false;
		consumerSelected = false;
		$('#signup-companytype :selected').each(function(i, selectedElement) {
			companyType = $(selectedElement).val();
			companyType = companyType + "";
			if (companyType.substr(0, 14) == 'Individual Investor' || 
					companyType.substr(0, 10) == 'University/Phd Student'){
				consumerSelected = true;
			}else{
				vendorSelected = true;
			}
		});
		if(vendorSelected && consumerSelected){
			$('#generic-error-message').html('Please select either Vendor or Consumer as Company Type');
		}else{;
		$('#generic-error-message').html('');
		}
	});
});
function validateNotNull(element, errorLabelId) {
	if (element.value == null || element.value == '' || $.trim(element.value) == '') {
		document.getElementById(errorLabelId).innerHTML = element.placeholder + ' cannot be blank';
		return false;
	}else {
		document.getElementById(errorLabelId).innerHTML = '';
		return true;
	}
}

function validateSelectNotNull(selectId, errorLabelId, placeholder) {	
	var selector = '#' + selectId + ' :selected';
	var selectedElementValue = null;
	$(selector).each(function(i, selectedElement) {
		selectedElementValue = $(selectedElement).val();
		return returnValue = false;
	});

	if (selectedElementValue == null) {
		document.getElementById(errorLabelId).innerHTML = placeholder + ' cannot be blank';
		return false;
	}else {
		document.getElementById(errorLabelId).innerHTML = '';
		return true;
	}
}

function validateWithRegularExpression(element, errorLabelId, regularExpression, validationMessageKey, nullValidation) {
	if(nullValidation && !validateNotNull(element, errorLabelId)) {
		return false;
	}
	if(!nullValidation && !validateNotNull(element, errorLabelId)) {
		document.getElementById(errorLabelId).innerHTML = '';
		return true;
	}
	if(!regularExpression.test($.trim(element.value))) {
		document.getElementById(errorLabelId).innerHTML = validationMessageMap[validationMessageKey];
		return false;
	}else{
		document.getElementById(errorLabelId).innerHTML = '';
		return true;
	}
}

function validateAjax(element, ajaxUrl, errorLabelId,actionComponent) {
	var url = ajaxUrl + "?param="+ $.trim(element.value)+"&actionComponent="+actionComponent ;
	$.ajax({
		type: 'POST',
		url: url,
		cache: false,
		success: function(output) {
			document.getElementById(errorLabelId).innerHTML = output;
		}
	});
}

function validateSpanElements(spanName) {
	var selector = '#' + spanName + ' label.errorMessage';
	var returnValue = null;
	$(selector).each(function(index){
		if($(this).html() != null && $(this).html() != '') {
			alert("Please remove all validation errors");
			return returnValue = false;
		}
	} );
	if(returnValue != null) {
		return false;
	}
	returnValue = null;
	selector = '#' + spanName + ' input[data-mandatory="Y"]';
	$(selector).each(function(index){
		if($(this).val() == null || $(this).val() == '' || $.trim($(this).val()) == '') {
			alert("Please enter all mandatory fields");
			$(this).focus();
			return returnValue = false;
		}
	} );
	if(returnValue != null) {
		return false;
	}
	selector = '#' + spanName + ' textarea[data-mandatory="Y"]';
	$(selector).each(function(index) {
		if($(this).val() == null || $(this).val() == '' || $.trim($(this).val()) == '') {
			alert("Please enter all mandatory fields");
			$(this).focus();
			return returnValue = false;
		}
	} );
	if(returnValue != null){
		return false;
	}
	selector = '#' + spanName + ' select[data-mandatory="Y"]';
	$(selector).each(function(index){
		if($(this).val().toUpperCase() == '-SELECT-' || $(this).val().toUpperCase() == 'SELECT') {
			alert("Please enter all mandatory fields");
			$(this).focus();
			return returnValue = false;
		}
	} );
	if(returnValue != null){
		return false;
	}
	selector = '#' + spanName + ' select[multi-data-mandatory="Y"] :selected';
	$(selector).each(function(index, selectedElement) {
		value = $(selectedElement).val();
		value = value + "";
		if(value.substr(0, 8).toUpperCase() == '-SELECT-' || value.substr(0, 6).toUpperCase() == 'SELECT') {
			alert("Please enter all mandatory fields");
			$(this).focus();
			return returnValue = false;
		}
	} );
	if(returnValue != null){
		return false;
	}
	return true;
}

function validatePersonalEmailId(element, errorLabelId) {
	var emailId = $.trim(element.value);
	var validationList = ['@yahoo','@aol','@rediff','@hotmail','@in','@msn','@gmail'];
	for (i = 0; i < validationList.length; i++) {
		if(emailId.indexOf(validationList[i]) != -1) {
			document.getElementById(errorLabelId).innerHTML = 'Please enter valid Company Email id';
			return false;
		}
	}
	return true;
}

function validateImageType(element, errorLabelId) {
	var fileName = element.value;
	if(fileName != null) {
		fileName = $.trim(fileName);
		if(fileName != '') {
			if(!regularExpressionMap['IMAGE'].test(fileName)) {
				document.getElementById(errorLabelId).innerHTML = validationMessageMap['IMAGE'];
				return false;
			}else{
				document.getElementById(errorLabelId).innerHTML = '';
				return true;
			}
		}
	}
}

var validationMessageMap = {
		'DESIGNATION' : 'Please enter valid Designation value',
		'EMAIL' : 'Please enter valid Email id',
		'PHONE_CODE' : 'Please enter valid Code value',
		'PHONE' : 'Please enter valid Phone Number',
		'COMPANY_URL' : 'Please enter valid URL',
		'YEAR' : 'Please enter valid YEAR',
		'IMAGE' : 'Please upload valid image file'
}

var regularExpressionMap = {
		'DESIGNATION' :  /^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/,
		'EMAIL' : /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i,
		'PHONE_CODE' : /^\+(?:[1-9] ?){1,6}/,
		'PHONE_NUMBER' : /^([0-9]{8,10})$/,
		'URL' : /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/,
		'YEAR' : /^\d{4}$/,
		'IMAGE' : /^([a-zA-Z0-9\s_\\.\-:])+(.jpeg|.jpg|.png| .gif)$/
}