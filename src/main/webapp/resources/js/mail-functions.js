

        $(document).ready(function(){
        	$('#call').click(function ()
            {
        		var count=0;
            	if(!validateContactEmail($('#contact_us_email').val())){ 
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
            	
            	$('#loadingct').show();
            	var datastr = "contact_us_name=" +$('#contact_us_name').val()+"&contact_us_phone="+$('#contact_us_phone').val()+"&contact_us_email="+$('#contact_us_email').val()+"&contact_us_message="+$('#contact_us_message').val();
            		var encoded = encodeURIComponent(datastr);
                $.ajax({
                    type: "post",
                    contentType: "text/xml",
                    url: $('#contact_us_form').attr('action'), 
                    data: encoded,
                    success: function(msg){ 
                    	
                            $('#output').html("<h1>"+msg+"</h1>");
                            
                            $("#loadingct").hide();
                    }
                });
            });

        });
       
        function validateContactEmail(email){
        	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        	  if(re.test(email)){
        		 
        	    document.getElementById('contact_us_email').style.background ='#ccffcc';
        	    document.getElementById('contactEmailError').style.display = "none";
        	    return true;
        	  }else{
        		 
        	    document.getElementById('contact_us_email').style.borderBottom ='1px solid #e35152';
        	    document.getElementById('contactEmailError').style.display = "block";
                document.getElementById('contactEmailError').style.color = "#e35152";
        	    return false;
        	  }
        }
        
        function validateContactNumber(phone){
        	var regex = /^([0-9]{8,10})$/;
        	if(regex.test(phone)){
        		
        	    document.getElementById('contact_us_phone').style.background ='#ccffcc';
        	    document.getElementById('contactPhoneError').style.display = "none";
        	    return true;
        	  }else{
        		 
        	    document.getElementById('contact_us_phone').style.borderBottom ='1px solid #e35152';
        	    document.getElementById('contactPhoneError').style.display = "block";
                document.getElementById('contactPhoneError').style.color = "#e35152";
        	    return false;
        	  }
        }
        
        function validateContactName(name){
        	
        	if (name != "") {
        		
        	    document.getElementById('contact_us_name').style.background ='#ccffcc';
        	    document.getElementById('contactNameError').style.display = "none";
        	    return true;
        	  }else{
        		  
        	    document.getElementById('contact_us_name').style.borderBottom ='1px solid #e35152';
        	    document.getElementById('contactNameError').style.display = "block";
                document.getElementById('contactNameError').style.color = "#e35152";
        	    return false;
        	  } 
        }
        
        
        
        
 