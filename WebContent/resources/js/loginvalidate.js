$(function() {
	$("#").validate(
			{
						doNotHideMessage : true, // this option enables to show the error success messages on tab switch.
						errorElement : 'div', // default input error message container
						errorClass : 'validate-inline', // default input error message class
						focusInvalid : false, // do not focus the last invalid input
						// Specify the validation rules
						   rules : {
								"username": {
									required : true
								},
								"password" : {
									required : true
								}
							},
							messages : {
								"username": {
									required : "This field is required"
								},
								"password" : {
									required : "This field is required"
								} 
							},
							errorPlacement : function(error, element) {
								$(element).closest('.login-form').addClass('error');
								error.insertAfter(element);
							},
							highlight : function(element) {
								// hightlight error inputs
								$(element).closest('.help-inline').removeClass('ok');
								// display OK icon
								$(element).closest('.login-form').removeClass('success').addClass('error');
								// set error class to the control group
							},
							unhighlight : function(element) {
								// revert the change done by hightlight
								$(element).closest('.login-form').removeClass('error');
								$('.alert-error', $(this)).hide();
								// set error class to the control group
							},
							success : function(form) {
								$(this).find('label.success, label.error').remove();
								$(this).find('error').remove();
								$('.alert-error', $(this)).hide();
							},
							submitHandler : function(form) {
								document.getElementById("login-submit").submit();
						}
					});
});
