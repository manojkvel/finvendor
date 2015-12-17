$(document).ready(
		function() {
	$(".auditbtn").click(function () {
	      $(this).text(function(i, text){
	          return text === "Show Audit Trial" ? "Hide Audit Trial" : "Show Audit Trial";
	      });
	   });
		});
function newConfirmMsg(buttonId, buttonNames){
	var def = $.Deferred();
	
	var index;
	var theButtons = {};
	
	for (index = 0; index<buttonNames.length; ++index) {
		if(buttonNames[index] == "Yes") {
			theButtons["Yes"] = function() { 
			def.resolve(true);
			$( this ).dialog( "destroy" );
			};	
		}else if(buttonNames[index] == "No") {
			theButtons["No"] = function() { 
			def.resolve(false);
			$( this ).dialog( "destroy" );
			};
		}else if(buttonNames[index] == "Cancel") {
			theButtons["Cancel"] = function() { 
			def.resolve(false);
			$( this ).dialog( "destroy" );
			};
		} else if(buttonNames[index] == "OK") {
			theButtons["OK"] = function() { 
				def.resolve(true);
				$( this ).dialog( "destroy" );
			};
		}
	}
	$("#"+buttonId).dialog({
		title: 'Confirm', 
		autoOpen: true,
		resizable: false,
	    modal: true,
	    draggable: false,
	    position: ['top', 150],
	    my:'center',of:'center',collison:'fit',
		buttons: theButtons
	});
	$(".ui-dialog-titlebar-close").hide();
	return def.promise();
}

function show_loader(message){
	var content = "<div id=\"loading_behind\">";
	if(message==null || message == ""){
		content +="<p>Loading...</p>";
	}else{
		content +="<p>"+message+"</p>";
	}
	var imagePath = window.location.protocol+"//"+window.location.host;
	content +='<img src="'+imagePath+'/assets/images/loading.gif" />';
	content +="</div>";
	$(document.body).append(content);
}

function hide_loader(){
	if (document.getElementById("loading_behind") != null) {
		$('#loading_behind').remove();
	}
}

function showOTPDialog(buttonId, buttonNames,otp){
	
	var def = $.Deferred();
	
	var index;
	var theButtons = {};
	var limit = 3;
	for (index = 0; index<buttonNames.length; ++index) {
		if(buttonNames[index] == "Yes") {
			theButtons["Yes"] = function() { 
			def.resolve(true);
			$( this ).dialog( "destroy" );
			};	
		}else if(buttonNames[index] == "No") {
			theButtons["No"] = function() { 
			def.resolve(false);
			$( this ).dialog( "destroy" );
			};
		}else if(buttonNames[index] == "Cancel") {
			theButtons["Cancel"] = function() { 
			def.resolve(false);
			$( this ).dialog( "destroy" );
			};
		} else if(buttonNames[index] == "OK") {
			theButtons["OK"] = function() {
				var otpValue = document.getElementById("otp").value;
				if (limit == 1) {
					def.resolve(false);
					$( this ).dialog( "destroy" );
				} else if (otpValue != null && otpValue != "") {
					if (otpValue == otp) {
						def.resolve(true);
						$( this ).dialog( "destroy" );
					} else {
						limit = limit -1;
						document.getElementById('errOTP').innerHTML = "Entered OTP is wrong, maximum limit exists is  "+limit;
						document.getElementById("otp").value = "";
					}
				} else {
					document.getElementById('errOTP').innerHTML = "This field is required";
				}
			};
		}
	}
	$("#"+buttonId).dialog({
		title: 'OTP Verification', 
		autoOpen: true,
		resizable: false,
		modal: true,
		draggable: false,
		position: ['center','middle'],
		buttons: theButtons
	});
	
	$(".ui-dialog-titlebar-close").hide();
	
	return def.promise();
}

function showAuditTrial(){
	$( "#audit_trail" ).toggle( "slow" );

}