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

function validateAjax(element, ajaxUrl, errorLabelId) {
	var url = ajaxUrl + "?param="+ $.trim(element.value);
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
	var emailId = $.trim(element.value)
	var validationList = ['@yahoo','@aol','@rediff','@hotmail','@in','@msn'];
	for (i = 0; i < validationList.length; i++) {
	    if(emailId.indexOf(validationList[i]) != -1) {
	    	document.getElementById(errorLabelId).innerHTML = 'Please enter valid Company Email id';
	    	return false;
	    }
	}
	return true;
}

var validationMessageMap = {
		'DESIGNATION' : 'Please enter valid Designation value',
		'EMAIL' : 'Please enter valid Email id',
		'PHONE_CODE' : 'Please enter valid Code value',
		'PHONE' : 'Please enter valid Phone Number',
		'COMPANY_URL' : 'Please enter valid URL'
	}

var regularExpressionMap = {
		'DESIGNATION' :  /^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/,
		'EMAIL' : /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i,
		'PHONE_CODE' : /^\+(?:[1-9] ?){1,6}/,
		'PHONE_NUMBER' : /^([0-9]{8,10})$/,
		'URL' : /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/
	}