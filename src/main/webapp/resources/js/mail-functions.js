

        $(document).ready(function(){
            $('#call').click(function ()
            {
                var gToken = grecaptcha.getResponse();

                var count=0;

                if(!validateCaptcha(gToken)) {

                    if(!validateContactEmail($('#contact_us_email').val())){ 
                        if(!validateContactNumber($('#contact_us_phone').val())) { 
                            if(!validateContactName($('#contact_us_name').val())) return;
                            else return;
                        }else {
                            if(!validateContactName($('#contact_us_name').val())) return;
                            else return;
                        }
                    } else {
                        if(!validateContactNumber($('#contact_us_phone').val())) { 
                            if(!validateContactName($('#contact_us_name').val())) return;
                            else return;
                        } else {
                            if(!validateContactName($('#contact_us_name').val())) return;
                            else return;
                        }
                    }
                }
                else if(!validateContactEmail($('#contact_us_email').val())){ 
                    if(!validateContactNumber($('#contact_us_phone').val())) { 
                        if(!validateContactName($('#contact_us_name').val())) return;
                        else return;
                    }else {
                        if(!validateContactName($('#contact_us_name').val())) return;
                        else return;
                    }
                }
                else if(!validateContactNumber($('#contact_us_phone').val())) {
                    if(!validateContactName($('#contact_us_name').val())) return;
                    else return;
                } else if(!validateContactName($('#contact_us_name').val())) return;
                
                $('#progressLoader').show();
                var jsonData = {
                  "email": $('#contact_us_email').val(),
                  "message": $('#contact_us_message').val(),
                  "name": $('#contact_us_name').val(),
                  "phone": $('#contact_us_phone').val()
                };
                contactUsObj.submitContactUsForm(jsonData);
                /*var datastr = "contact_us_name=" +$('#contact_us_name').val()+"&contact_us_phone="+$('#contact_us_phone').val()+"&contact_us_email="+$('#contact_us_email').val()+"&contact_us_message="+$('#contact_us_message').val();
                    var encoded = encodeURIComponent(datastr);
                $.ajax({
                    type: "post",
                    contentType: "text/xml",
                    url: $('#contact_us_form').attr('action'), 
                    data: encoded,
                    success: function(msg){ 
                        $("#contact_us_form").trigger("reset");
                        $('#output').html("<h1>"+msg+"</h1>");
                            
                        $("#progressLoader").hide();
                    },
                    error: function() {
                        $("#progressLoader").hide();
                    }
                });*/
            });

        });

        function validateCaptcha(gToken){
            
            if (gToken != "") {
                document.getElementById('gRecaptcha').style.background ='#ccffcc';
                document.getElementById('contactReCaptchaError').style.display = "none";
                return true;
              }else{
                document.getElementById('contactReCaptchaError').style.display = "block";
                document.getElementById('contactReCaptchaError').style.color = "#e35152";
                return false;
              } 
        }
       
        function validateContactEmail(email){
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
              if(re.test(email)){
                 
                document.getElementById('contact_us_email').style.background ='#ccffcc';
                document.getElementById('contactEmailError').style.display = "none";
                document.getElementById('contact_us_email').style.borderBottom ='1px solid #d3d6d5';
                return true;
              } else {
                  
                document.getElementById('contact_us_email').style.borderBottom ='1px solid #e35152';
                document.getElementById('contactEmailError').style.display = "block";
                document.getElementById('contactEmailError').style.color = "#e35152";
                return false;
              }
        }
        
        function validateContactNumber(phone){
            if(phone == "") {
                document.getElementById('contact_us_phone').style.borderBottom ='1px solid #e35152';
                document.getElementById('contactPhoneError').style.display = "block";
                document.getElementById('contactPhoneError_data').style.display = "none";
                document.getElementById('contactPhoneError').style.color = "#e35152";
                return false;
            }

            var regex = /^([-+0-9]{8,15})$/;
            if(regex.test(phone)){
                
                document.getElementById('contact_us_phone').style.background ='#ccffcc';
                document.getElementById('contact_us_phone').style.borderBottom ='1px solid #d3d6d5';
                document.getElementById('contactPhoneError').style.display = "none";
                document.getElementById('contactPhoneError_data').style.display = "none";
                return true;
              }else{
                 
                document.getElementById('contact_us_phone').style.borderBottom ='1px solid #e35152';
                document.getElementById('contactPhoneError').style.display = "none";
                document.getElementById('contactPhoneError_data').style.display = "block";
                document.getElementById('contactPhoneError').style.color = "#e35152";
                document.getElementById('contactPhoneError_data').style.color = "#e35152";
                return false;
              }
        }
        
        function validateContactName(name){
            
            if (name != "") {
                
                document.getElementById('contact_us_name').style.background ='#ccffcc';
                document.getElementById('contactNameError').style.display = "none";
                document.getElementById('contact_us_name').style.borderBottom ='1px solid #d3d6d5';
                return true;
              }else{
                  
                document.getElementById('contact_us_name').style.borderBottom ='1px solid #e35152';
                document.getElementById('contactNameError').style.display = "block";
                document.getElementById('contactNameError').style.color = "#e35152";
                return false;
              } 
        }
        
        var contactUsObj = {
            init: function() {

            },

            submitContactUsForm: function(jsonData) {
                this.postContactUsApi(jsonData).then(function(response) {
                    var response = JSON.parse(response);
                    $('#progressLoader').hide();
                    $('#output').html("<h1>"+response.message+"</h1>");
                    $("#contact_us_form").trigger("reset");
                }, function(error) {
                    $('#progressLoader').hide();
                });
            },

            postContactUsApi: function(jsonData) {

                var url = "/api/contactUs";
                return new Promise(function(resolve, reject) {
                    var httpRequest = new XMLHttpRequest({
                        mozSystem: true
                    });

                    httpRequest.open('POST', url, true);
                    httpRequest.setRequestHeader('Content-Type',
                        'application/json; charset=UTF-8');
                    httpRequest.ontimeout = function () {
                        reject("" + httpRequest.responseText);
                    };
                    httpRequest.onreadystatechange = function () {
                        if (httpRequest.readyState === XMLHttpRequest.DONE) {
                            if (httpRequest.status === 200) {
                                resolve(httpRequest.response);
                            } else {
                                reject(httpRequest.responseText);
                            }
                        } else {
                        }
                    };

                    httpRequest.send(JSON.stringify(jsonData));
                });
            }
        }

        contactUsObj.init();
        
        
 