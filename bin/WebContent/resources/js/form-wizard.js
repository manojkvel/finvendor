var FormWizard = function () {
    return {
		onSubmit: function(){},
        //main function to initiate the module
        init: function (copy_status) {
            if (!jQuery().bootstrapWizard) {
                return;
            }
            var form = $('#submit_form');
            var error = $('.alert-error', form);
            var success = $('.alert-success', form);
            var $thisForm = this;
            $thisForm.hardcopy_status = copy_status;
            form.validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'validate-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    //account
                    username: {
                        minlength: 5,
                        required: true
                    },
                    "technical_information.channelOthers" : {
                    	required : true
                    }
                    	
                },
                messages: { // custom messages for radio buttons and checkboxes
					"card_design.user_id" : {
						required:""
					}
                },

                errorPlacement: function (error, element) { // render error placement for each input type
                	
                    if (element.attr("name") == "gender") { // for uniform radio buttons, insert the after the given container
                        error.addClass("no-left-padding").insertAfter("#form_gender_error");
                    } else if (element.attr("name") == "payment[]") { // for uniform radio buttons, insert the after the given container
                        error.addClass("no-left-padding").insertAfter("#form_payment_error");
                    }
					else if (element.attr("name") == "protocol") { // for uniform radio buttons, insert the after the given container
                        error.addClass("no-left-padding").insertAfter("#form_protocol_error");
                    }
					else if (element.attr("name") == "cardtype") { // for uniform radio buttons, insert the after the given container
                        error.addClass("no-left-padding").insertAfter("#form_cardtype_error");
                    } else if (element.attr("name") == "site") {
                    	  error.insertAfter("#form_site_error");
                    } else if (element.attr("name") == "technical_information.platform") {
                    	error.insertAfter("#form_platform_error");
                    } else if (element.attr("name") == "technical_information.protocol") {
                    	error.insertAfter("#form_prot_error");
                    } else if (element.attr("name") == "technical_information.cardType") {
                    	error.insertAfter("#form_card_error");
                    } else if (element.attr("name") == "technical_information.productType") {
                    	error.insertAfter("#form_producttype_error");
                    } else if (element.attr("name") == "technical_information.channel") {
                    	error.insertAfter("#form_channel_error");
                    } else {
                        error.insertAfter(element); // for other inputs, just perform default behavior
                    }
					
                },
				
				 

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success.hide();
                    error.show();
                    App.scrollTo(error, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.help-inline').removeClass('ok'); // display OK icon
                    $(element)
                        .closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.control-group').removeClass('error'); // set error class to the control group
                },

                success: function (form) {
					form.find('label.success, label.error').remove();
                    /*if (label.attr("for") == "gender" || label.attr("for") == "payment[]") { // for checkboxes and radio buttons, no need to show OK icon
                        label
                            .closest('.control-group').removeClass('error').addClass('success');
                        label.remove(); // remove error label here
                    } else { // display success icon for other inputs
                        label
                            .addClass('valid ok') // mark the current input as valid and display OK icon
                        .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group
                    }*/
                },

                submitHandler: function (form) {
                    success.show();
                    error.hide();
                    $thisForm.onSubmit(form)
                    //add here some ajax code to submit your form or just call form.submit() if you want to submit the form without ajax
                }

            });

            var displayConfirm = function() {
                $('.display-value', form).each(function(){
                    var input = $('[name="'+$(this).attr("data-display")+'"]', form);
                    if (input.is(":text") || input.is("textarea")) {
						if($(this).attr("data-display") == "mobileno" || $(this).attr("data-display") == "card_issuer_details.mobileNumber"){
							$(this).html('+91-'+input.val());
						} else if($(this).attr("data-display") == "card_issuer_details.landLineNumber") {
							var otherInput = $('[name="card_issuer_details.stdCode"]', form);
							$(this).html('+91-'+ otherInput.val() + '-' +input.val());
						} else {
							$(this).html(input.val());
						}
                    } else if (input.is("select")) {
                        $(this).html(input.find('option:selected').text());
                    } else if (input.is(":radio") && input.is(":checked")) {
                    	if (input.parent().parent().find('input:checked').attr("data-title") === 'others') {
                    		var otherInput = $('[name="technical_information.prodcutTypeOthers"]', form);
                    		 $(this).html("Others(" +otherInput.val() +")");
                    	} else {
                    		$(this).html(input.parent().parent().find('input:checked').attr("data-title"));
                    	}
					} else if (input.is(":checkbox")) {
						var message = "";
						for (i = 0; i < input.length; i++){
						  if ($(input[i]).is(':checked')) {
							  if ($(input[i]).attr("data-title") === 'Others') {
								  var otherInput = $('[name="technical_information.channelOthers"]', form);
								  message = message + "Others(" + otherInput.val() +")" + "<br>";
							  } else  {
								  message = message + $(input[i]).attr("data-title") + "<br>";
							  }
						  }
						}
						$(this).html(message);
                    } else if ($(this).attr("data-display") == 'card_expiry') {
                        $(this).html($('[name="card_expiry_mm"]', form).val() + '/' + $('[name="card_expiry_yyyy"]', form).val());
                    } else if ($(this).attr("data-display") == 'payment') {
                        var payment = [];
                        $('[name="payment[]"]').each(function(){
                            payment.push($(this).attr('data-title'));
                        });
                        $(this).html(payment.join("<br>"));
                    } else if($(this).attr("data-display") == "carddesign"){
						$(this).html(input[0].files[0].name);
					} else if ($(this).attr("data-display") == "carddesignfile") {
						if (input.val() !="")
							$(this).html(input[0].files[0].name);
						else
							$(this).html("No file is uploaded, Already uploaded file will taken as consideration");
					}
                });
                    if($("#jsontable1").val()!= "" && $("#jsontable1").val()){
						$("#summary tbody").children().remove();
						var user = $('#sample_1').tableToJSON();
						for(var i=0; i<user.length; i++){
							$("#summary").append('<tr><td>'+user[i]["First name"]+'</td><td>'+user[i]["Middle Name"]+'</td>\
							<td>'+ user[i]["Last Name"] +'</td><td>'+user[i].Gender+'</td><td>'+user[i].Dob+'</td><td>'+user[i].Email+'</td><td>'+user[i].Phone+'</td></tr>');
						}
					}
					
					
					
					if($("#jsontable2").val()!= "" && $("#jsontable2").val()){
						$("#summary tbody").children().remove();
						var user = $.parseJSON($("#jsontable2").val());
						for(var i=0; i<user.length; i++){
							$("#summary").append('<tr><td>'+user[i]["Audited/Unaudited Site"]+'<td>'+user[i]["Address1"]+'</td><td>'+user[i]["Address2"]+'</td>\
							<td>'+ user[i]["City"] +'</td><td>'+user[i]["State / Province"]+'</td><td>'+user[i].Country+'</td><td>'+user[i].Pincode+'</td></tr>');
						}
					}
					
					if($("#jsontable3").val()!= "" && $("#jsontable3").val()){
						$("#summary tbody").children().remove();
						var user = $('#sample_3').tableToJSON();
						for(var i=0; i<user.length; i++){
							$("#summary").append('<tr><td>'+user[i]["Audit"]+'</td></tr>');
						}
					}
            
            }
            
            // default form wizard
            $('#form_wizard_1').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                onTabClick: function (tab, navigation, index) {
                  //  alert('on tab click disabled');
                    return false;
                },
                onNext: function (tab, navigation, index) {
                    success.hide();
                    error.hide();
                    
                    if($('#tab4').is(':visible')){
						if($("#sample_1").find('tr').length < 2){
							form.valid();
							return false;
						}
					}
                    else if($('#showSelectedAuditSite').is(':visible')){
                    	
                    	if(($("#show_Address1").text()=="" || $("#show_Address1").text()==null) && $("#sample_2").is(':visible') ){
                    		if($("#sample_2").find('tr').length < 2){
    							form.valid();
    							return false;
    						}
                    	}                    	
                    	else if($("#show_Address1").text()=="" || $("#show_Address1").text()==null ){
   							form.valid();
   							return false;
   						}
                    }else if(
                    		((!$('#audited').is(':visible') && !$('#unaudited').is(':visible')) || (($('#audited').is(':visible') && ($("#show_Address1").text()=="" || $("#show_Address1").text()==null)) || ($('#unaudited').is(':visible') && !($("#unaddress1").val()!="" || $("#unstate").val()!="-SELECT-" || $("#uncity").val()!="" || $("#unpincode").val()!="")))) && $("#sample_2").is(':visible') ){
   						
						if($("#sample_2").find('tr').length < 2){
							form.valid();
							return false;
						}
                	}
                    else if(form.valid() == false) {
                    	
                    		return false;
                    	
                    }else if(form.valid() == true ){
                    	if($('#audittab1').is(':visible') && $('[value="unauditedsite"]').is(':checked')){
                    	    var r = confirm("Please note your site is not audited by NPCI.\nThe design approval will be provided only post successful completion of audit.\nDo you want to continue with card Design Approval?");
                    	    if (r == true) {
                    	    	loadUnauditedSite();
                    	    } else {
                    	    	return false;
                    	    }
                    	}
                    }


                    var total = navigation.find('li').length;
                    var current = index + 1;
                    // set wizard title
                    $('.step-title', $('#form_wizard_1')).text('Step ' + (index + 1) + ' of ' + total);
                    // set done steps
                    jQuery('li', $('#form_wizard_1')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_1').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_1').find('.button-previous').show();
                    }



                    if (current >= total) {
                        $('#form_wizard_1').find('.button-next').hide();
                        $('#form_wizard_1').find('.button-submit').show();
                        displayConfirm();
                    } else {
                        $('#form_wizard_1').find('.button-next').show();
                        $('#form_wizard_1').find('.button-submit').hide();
                    }
                    App.scrollTo($('.page-title'));
                },
                onPrevious: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    var total = navigation.find('li').length;
                    var current = index + 1;
                    // set wizard title
                    $('.step-title', $('#form_wizard_1')).text('Step ' + (index + 1) + ' of ' + total);
                    // set done steps
                    jQuery('li', $('#form_wizard_1')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_1').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_1').find('.button-previous').show();
                    }

                    if (current >= total) {
                        $('#form_wizard_1').find('.button-next').hide();
                        $('#form_wizard_1').find('.button-submit').show();
                    } else {
                        $('#form_wizard_1').find('.button-next').show();
                        $('#form_wizard_1').find('.button-submit').hide();
                    }

                    App.scrollTo($('.page-title'));
                },
                onTabShow: function (tab, navigation, index) {
                	var wz = $('#form_wizard_1').data()['bootstrapWizard'];
                	var action = $(tab).find('a').attr('href');
                	if(wz && wz.hardcopy_status && action == '#hardcopytab'){
                		wz.nextTab();
                	}
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    $('#form_wizard_1').find('.bar').css({
                        width: $percent + '%'
                    });
                }
            });

$('#form_wizard_2').bootstrapWizard({
                'nextSelector': '.button-next',
                'previousSelector': '.button-previous',
                onTabClick: function (tab, navigation, index) {
                 //   alert('on tab click disabled');
                    return false;
                },
                onNext: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    if (form.valid() == false) {
                        return false;
                    }

                    var total = navigation.find('li').length;
                    var current = index + 1;
                    // set wizard title
                    $('.step-title', $('#form_wizard_2')).text('Step ' + (index + 1) + ' of ' + total);
                    // set done steps
                    jQuery('li', $('#form_wizard_2')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_2').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_2').find('.button-previous').show();
                    }

                    if (current >= total) {
                        $('#form_wizard_2').find('.button-next').hide();
                        $('#form_wizard_2').find('.button-submit').show();
                        displayConfirm();
                    } else {
                        $('#form_wizard_2').find('.button-next').show();
                        $('#form_wizard_2').find('.button-submit').hide();
                    }
                    App.scrollTo($('.page-title'));
                },
                onPrevious: function (tab, navigation, index) {
                    success.hide();
                    error.hide();

                    var total = navigation.find('li').length;
                    var current = index + 1;
                    // set wizard title
                    $('.step-title', $('#form_wizard_2')).text('Step ' + (index + 1) + ' of ' + total);
                    // set done steps
                    jQuery('li', $('#form_wizard_2')).removeClass("done");
                    var li_list = navigation.find('li');
                    for (var i = 0; i < index; i++) {
                        jQuery(li_list[i]).addClass("done");
                    }

                    if (current == 1) {
                        $('#form_wizard_2').find('.button-previous').hide();
                    } else {
                        $('#form_wizard_2').find('.button-previous').show();
                    }

                    if (current >= total) {
                        $('#form_wizard_2').find('.button-next').hide();
                        $('#form_wizard_2').find('.button-submit').show();
                    } else {
                        $('#form_wizard_2').find('.button-next').show();
                        $('#form_wizard_2').find('.button-submit').hide();
                    }

                    App.scrollTo($('.page-title'));
                },
                onTabShow: function (tab, navigation, index) {
                    var total = navigation.find('li').length;
                    var current = index + 1;
                    var $percent = (current / total) * 100;
                    $('#form_wizard_2').find('.bar').css({
                        width: $percent + '%'
                    });
                }
            });

 $('#form_wizard_2').find('.button-previous').hide();
            $('#form_wizard_2 .button-submit').click(function () {
             //   alert('Finished! Hope you like it :)');
            }).hide();

            $('#form_wizard_1').find('.button-previous').hide();
            $('#form_wizard_1 .button-submit').click(function () {
                //alert('Finished! Hope you like it :)');
            }).hide();
        return this;
        }

    };

}();


