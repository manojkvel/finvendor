//jQuery(document).ready(function($){
var $form_modal = $('.cd-user-modal'),
$form_login = $form_modal.find('#cd-login'),
$form_signup = $form_modal.find('#cd-signup'),
$form_forgot_password = $form_modal.find('#cd-reset-password'),
$form_change_password = $form_modal.find('#cd-change-password'),
$form_modal_tab = $('.cd-switcher'),
$form_modal_tab_forgot_password = $('.cd-switcher-forgot-password'),
$form_modal_tab_change_password = $('.cd-switcher-change-password'),
$form_modal_tab_success_message = $('.cd-switcher-success-message'),
$tab_login = $form_modal_tab.children('li').eq(0).children('a'),
$tab_signup = $form_modal_tab.children('li').eq(1).children('a')
$signup_link = $form_login.find('#sign-up-link'),
$forgot_password_link = $form_login.find('#forgot-password-link'),
$back_to_login_link = $form_forgot_password.find('#login-link'),
$form_reg_success = $form_modal.find('#cd-reg-success-message'),
$main_nav = $('.hd-right');

/*$form_modal.addClass('is-visible');	
	$form_login.addClass('is-selected');
	$tab_login.addClass('selected');
	document.getElementById("nav-srch").style.display="none";*/

//open modal
$('.hd-right a').on('click', function(event){
	if($(this).find('i').hasClass('fa-envelope'))
		return;
	if( $(event.target).is($main_nav) ) {
		// on mobile open the submenu
		$(this).children('ul').toggleClass('is-visible');
	} else {
		// on mobile close submenu
		$main_nav.children('ul').removeClass('is-visible');
		//show modal layer
		$form_modal.addClass('is-visible');	
		document.getElementsByClassName('nav-srch')[0].style.display="none";
		//show the selected form
		( $(event.target).is('.cd-signup') ) ? signup_selected() : login_selected();
	}

});

//close modal
$('.cd-user-modal').on('click', function(event){
	if( $(event.target).is($form_modal) || $(event.target).is('.cd-close-form') || $(event.target).is('#login-close') ) {
		$form_modal.removeClass('is-visible');
		document.getElementsByClassName('nav-srch')[0].style.display="block";
	}	
});
//close modal when clicking the esc keyboard button
$(document).keyup(function(event){
	if(event.which=='27'){
		$form_modal.removeClass('is-visible');
	}
});

//switch from a tab to another
$form_modal_tab.on('click', function(event) {
	event.preventDefault();
	( $(event.target).is( $tab_login ) ) ? login_selected() : signup_selected();
	$('#generic-error-message').html('');
});

//hide or show password
$('.hide-password').on('click', function(){
	var $this= $(this),
	$password_field = $this.prev('input');

	( 'password' == $password_field.attr('type') ) ? $password_field.attr('type', 'text') : $password_field.attr('type', 'password');
	if( $this.hasClass('fa-eye') ) {
		$this.addClass('fa-eye-slash');
		$this.removeClass('fa-eye');
	} else {
		$this.removeClass('fa-eye-slash');
		$this.addClass('fa-eye');
	}
	//( 'Hide' == $this.text() ) ? $this.text('Un-Hide') : $this.text('Hide');
	//focus and move cursor to the end of input field
	$password_field.putCursorAtEnd();
});

//hide or show password
$('.hide-password1').on('click', function(){
	var $this= $(this),
	$password_field = $this.prev('input');

	( 'password' == $password_field.attr('type') ) ? $password_field.attr('type', 'text') : $password_field.attr('type', 'password');
	( 'Hide' == $this.text() ) ? $this.text('Un-Hide') : $this.text('Hide');
	//focus and move cursor to the end of input field
	$password_field.putCursorAtEnd();
});
//show forgot-password form 
$signup_link.on('click', function(event){
	event.preventDefault();
	signup_selected();
	$form_modal_tab.show();
	$form_modal_tab_forgot_password.hide();
});
//show forgot-password form 
$forgot_password_link.on('click', function(event){
	event.preventDefault();
	forgot_password_selected();
});

//back to login from the forgot-password form
$back_to_login_link.on('click', function(event){
	event.preventDefault();
	document.getElementById('signin-password').value = ''
		login_selected();
});

function login_selected(){
	$form_login.addClass('is-selected');
	$form_signup.removeClass('is-selected');
	$form_forgot_password.removeClass('is-selected');
	$form_change_password.removeClass('is-selected');
	$form_reg_success.removeClass('is-selected');
	$tab_login.addClass('selected');
	$tab_signup.removeClass('selected');
	$form_modal_tab.show();
	$form_modal_tab_forgot_password.hide();
	$form_modal_tab_change_password.hide();
	$form_modal_tab_success_message.hide();

	$('#generic-error-message').html('');
}

function signup_selected(){
	$form_login.removeClass('is-selected');
	$form_signup.addClass('is-selected');
	$form_forgot_password.removeClass('is-selected');
	$form_change_password.removeClass('is-selected');
	$form_reg_success.removeClass('is-selected');
	$tab_login.removeClass('selected');
	$tab_signup.addClass('selected');
	$form_modal_tab.show();
	$form_modal_tab_forgot_password.hide();
	$form_modal_tab_change_password.hide();
	$form_modal_tab_success_message.hide();

	$('#generic-error-message').html('');
}

function forgot_password_selected(){
	$form_login.removeClass('is-selected');
	$form_signup.removeClass('is-selected');
	$form_reg_success.removeClass('is-selected');
	$form_change_password.removeClass('is-selected');
	$form_forgot_password.addClass('is-selected');
	$form_modal_tab.hide();
	$form_modal_tab_forgot_password.show();
	$form_modal_tab_change_password.hide();
	$form_modal_tab_success_message.hide();

	$('#generic-error-message').html('');
}

function reg_success_message_selected(){
	$form_login.removeClass('is-selected');
	$form_signup.removeClass('is-selected');
	$form_reg_success.addClass('is-selected');
	$form_forgot_password.removeClass('is-selected');
	$form_change_password.removeClass('is-selected');
	$form_modal_tab.hide();
	$form_modal_tab_forgot_password.hide();
	$form_modal_tab_change_password.hide();
	$form_modal_tab_success_message.show();
}

function change_password_selected(){
	$form_login.removeClass('is-selected');
	$form_signup.removeClass('is-selected');
	$form_reg_success.removeClass('is-selected');
	$form_forgot_password.removeClass('is-selected');
	$form_change_password.addClass('is-selected');
	$form_modal_tab.hide();
	$form_modal_tab_forgot_password.hide();
	$form_modal_tab_change_password.show();
	$form_modal_tab_success_message.hide();

	$('#generic-error-message').html('');
}


//REMOVE THIS - it's just to show error messages 
$form_login.find('input[type="submit"]').on('click', function(event){
	event.preventDefault();
	$form_login.find('input[type="username"]').toggleClass('has-error').next('span').toggleClass('is-visible');
});
$form_signup.find('input[type="submit"]').on('click', function(event){
	event.preventDefault();
	$form_signup.find('input[type="email"]').toggleClass('has-error').next('span').toggleClass('is-visible');
});

//IE9 placeholder fallback
//credits http://www.hagenburger.net/BLOG/HTML5-Input-Placeholder-Fix-With-jQuery.html
/*if(!Modernizr.input.placeholder){
		$('[placeholder]').focus(function() {
			var input = $(this);
			if (input.val() == input.attr('placeholder')) {
				input.val('');
		  	}
		}).blur(function() {
		 	var input = $(this);
		  	if (input.val() == '' || input.val() == input.attr('placeholder')) {
				input.val(input.attr('placeholder'));
		  	}
		}).blur();
		$('[placeholder]').parents('form').submit(function() {
		  	$(this).find('[placeholder]').each(function() {
				var input = $(this);
				if (input.val() == input.attr('placeholder')) {
			 		input.val('');
				}
		  	})
		});
	}*/

//});

function loginSubmit(changePassword) {
	var username= $("#signin-username").val();
	var password= $("#signin-password").val();
	var chgUsername = $("#changePasswordSpan #username").val();
	var oldPassword = $("#changePasswordSpan #old-password").val();
	var newPassword = $("#changePasswordSpan #new-password").val();
	var confirmNewPassword = $("#changePasswordSpan #confirm-new-password").val();
	var http = location.protocol;
	var slashes = http.concat("//");
	var urlPrefix = slashes.concat(window.location.host).concat("/");
	var actionUrl = null;

	if(changePassword) {		
		if(newPassword != confirmNewPassword) {
			$('#generic-error-message').html('New Password and Confirm New Password do not match');
			return false;
		}
	}

	if(changePassword) {
		actionUrl = urlPrefix + "checkUserLoginValidation?chgUsername="+chgUsername+"&oldPassword="+oldPassword+"&newPassword="+newPassword+"&passChange="+changePassword;
		$('#loadingcp').show();
	}else{
		actionUrl = urlPrefix + "checkUserLoginValidation?VEuMlA="+username+"&RaYulU="+password+"&passChange="+changePassword;
		$('#loadinglg').show();
	}


	$.ajax({
		type: 'POST',
		url:actionUrl,
		cache: false,
		success: function(output) {
			if (output.match("false")) {
				$('#loadinglg').hide();	
				$('#loadingcp').hide();
				var errorMessage = output.split(":")[1];
				if(errorMessage.match('Please Change your password')){
					change_password_selected();
				}else if(errorMessage.match('Change Password is successful')){
					$('#generic-error-message').html(errorMessage);
					login_selected();
					$("form").trigger("reset");
				}else{ 
					if(changePassword) {
						$('#generic-error-message').html(errorMessage);
					}else {
						$('#generic-error-message').html(errorMessage);
					}
					return false;
				}					
			} else {
				$('#loadinglg').hide();
				$('#loadingcp').hide();
				$('#generic-error-message').html("");
				$('.cd-user-modal').removeClass('is-visible');					
				$('form#login-submit').submit();
			}
		}
	});
}

function forgotPasswordSubmit() {
	var email= $("#forgot-password-email").val();
	var http = location.protocol;
	var slashes = http.concat("//");
	var urlPrefix = slashes.concat(window.location.host).concat("/");
	var actionUrl = urlPrefix + "forgotPassword?email="+email;

	$('#loadingfp').show();

	$.ajax({
		type: 'POST',
		url:actionUrl,
		cache: false,
		success: function(output) {
			if (output.match("false")) {
				$('#loadingfp').hide();				
				var errorMessage = output.split(":")[1];
				$('#generic-error-message').html(errorMessage);
			} else {
				$('#loadingfp').hide();
				$('#generic-error-message').html('Password reset done successfully.\nPlease access your email account for Resetting FinVendor Password');
				$("form").trigger("reset");
			}
		}
	});
};

//credits http://css-tricks.com/snippets/jquery/move-cursor-to-end-of-textarea-or-input/
jQuery.fn.putCursorAtEnd = function() {
	return this.each(function() {
		// If this function exists...
		if (this.setSelectionRange) {
			// ... then use it (Doesn't work in IE)
			// Double the length because Opera is inconsistent about whether a carriage return is one character or two. Sigh.
			var len = $(this).val().length * 2;
			this.setSelectionRange(len, len);
		} else {
			// ... otherwise replace the contents with itself
			// (Doesn't work in Google Chrome)
			$(this).val($(this).val());
		}
	});
};

function inner_login(redirectLink){
	$main_nav.children('ul').removeClass('is-visible');
	document.getElementsByClassName('nav-srch')[0].style.display="none";
	$form_modal.addClass('is-visible');	
	if(redirectLink=='LIY') {signup_selected();} else 	{login_selected();}
	//window.alert(redirectlink);
	$('#redirectLink').val(redirectLink);
}