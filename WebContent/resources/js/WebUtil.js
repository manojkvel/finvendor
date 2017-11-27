/**
 * 
 */
function doAjaxPost() {  
	  // get the form values  
	  var name = $('#name').val();
	  var education = $('#education').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/AddUser.htm",  
	    data: "name=" + name + "&education=" + education,  
	    success: function(response){
	      // we have the response 
	      if(response.status == "SUCCESS"){
	    	  userInfo = "<ol>";
	    	  for(i =0 ; i < response.result.length ; i++){
	    		  userInfo += "<br><li><b>Name</b> : " + response.result[i].name + 
	    		  ";<b> Education</b> : " + response.result[i].education;
	    	  }
	    	  userInfo += "</ol>";
	    	  $('#info').html("User has been added to the list successfully. " + userInfo);
	    	  $('#name').val('');
		      $('#education').val('');
		      $('#error').hide('slow');
		      $('#info').show('slow');
	      }else{
	    	  errorInfo = "";
	    	  for(i =0 ; i < response.result.length ; i++){
	    		  errorInfo += "<br>" + (i + 1) +". " + response.result[i].code;
	    	  }
	    	  $('#error').html("Please correct following errors: " + errorInfo);
	    	  $('#info').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      alert('Error: ' + e);  
	    }  
	  });  
	}  
	
	function doAjaxCall(url,param,componentId) {  
	   var responseValue = "";
	  $.ajax({  
	    type: "POST",  
	    url: url,  
	    data: param, 
	    async: false, 
	    success: function(response){
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
			if(componentId == "responseValue"){
	    	  	for(i =0 ; i < response.result.length ; i++){
	    		  	responseValue +=  response.result[i]+",";
	    	  	}
	    	}else{	      
	    	  userInfo = "<option value=''>Select</option>";
	    	  	for(i =0 ; i < response.result.length ; i++){
	    		  	userInfo += "<option value='" + response.result[i]+"'>"+ response.result[i]+"</option>";
	    	  	}
	    		$('#'+componentId+'').html(userInfo);
	    	}
	    	
	      }else{
	    // alert("   Errroorrr... ");
	      }	      
	    },  
	    error: function(e){  
	      alert('Error: ' + e);  
	    }  
	  });
	  return responseValue;  
	}  
	
	function checkNull(compId){

	var compValue = $('#'+compId+'').val();
	var altValue = $('#'+compId+'').attr("alt");
	if(null == compValue || ""==compValue || compValue.length == 0){
		$('#'+compId+'_spn').empty();
		$('#'+compId+'_spn').html("<font color='red'>Please Enter "+altValue+".</font>");
		$('#'+compId+'').focus();
			return false;
		}else{
			$('#'+compId+'_spn').empty();
			return true;
		}
	}
	
function validateAlphaNumric(compId){

	 if(!checkNull(compId)){
	  	return false;
	 }
    var TCode = $('#'+compId+'').val();
    if( /[^a-zA-Z0-9]/.test( TCode ) ) {
        $('#'+compId+'_spn').empty();
		$('#'+compId+'_spn').html("<font color='red'>Input is not alphanumeric</font>");
		$('#'+compId+'').focus();
       return false;
    }else{
	    $('#'+compId+'_spn').empty();
	    return true;
    }
}	
	
function validateEmail(comp){
    var compId = comp.id;
		var sEmail = $('#'+compId+'').val();
        if (!emailFilter(sEmail)) {
	        $('#'+compId+'_err_div').empty();
			$('#'+compId+'_err_div').html("Invalid Email Address");
			$('#'+compId).focus();
			return false;
        }else{
        	$('#'+compId+'_err_div').empty();
        	return true;
        }
}
	
function emailFilter(sEmail){
	debugger;
	if(checkNullValue(sEmail)){
		var emailReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		if(sEmail.match(emailReg)){
			return true;	
		}else{
			return false;
		}	
	}else{
		return false;
	}	
}	

	


function checkMandotrySelectValue(val){
	return true;
	/*if(val != null && val != undefined && val != '-SELECT-' ){
		return true;
	}else{
		return false;
	}*/
	
}

function checkNullValue(val){
	if(val != null && !(val === undefined) && !(val === "undefined") && val.length > 0 ){
		return true;
	}else{
		return false;
	}
	
}

function validatePhoneNoFromFile(phone){
	if(checkNullValue(phone)){
		var phonenoReg = /^\d{8,10}$/;
		if(phone.match(phonenoReg)){
			return true;	
		}else{
			return false;
		}	
	}else{
		return false;
	}
	
}

function validatePhoneNo(phone){
	
	var phId = phone.id; 
	var phValue = phone.value
	if(checkNullValue(phValue)){
		var phonenoReg = /^\d{8,10}$/;
		if(phValue.match(phonenoReg)){
			$("#"+phId+"_err_div").html("");
			return true;	
		}else{
			$("#"+phId+"_err_div").html("Invalid Phone Number");
            return false;
		}	
	}else{
		$("#"+phId+"_err_div").html("Invalid Phone Number");
        return false;
	}
}	
	
function validatePwd(pwdId){
	var pwd1= $("#"+pwdId+"").val();
	if(pwd1.length<8){
		$("#"+pwdId+"_spn").empty();
		$("#"+pwdId+"_spn").html("<font color='red'>Length should not less than 8 character </font>");
		return false;
		}else{
		$("#"+pwdId+"_spn").empty();
	return true;
	}
}

function confPwdCheck(pwdId1,pwdId2){
    var pwd1= $("#"+pwdId1+"").val();
    var pwd2= $("#"+pwdId2+"").val();
    if(pwd2 != pwd1) {
	    $("#"+pwdId2+"_spn").empty();
		$("#"+pwdId2+"_spn").html("<font color='red'>Mismatch password</font>");
	       return false;
	  }
    $("#"+pwdId2+"_spn").empty();
    return true;
}

	

		