jQuery(document).ready(function() {
	 $(".row-selectconsumerinvite tr").each(function() {
	        var id = $(this).attr('id');
	        var td = document.createElement(id == undefined ? 'td' : 'td');
	        var chk = document.createElement('input');
	        chk.type = 'checkbox';
	        chk.style.width = "15px";
	        td.style.width = "15px";
	        $(td).append(chk);
	        $(this).prepend(td);
	        if (id != undefined ) {
	            chk.name = id;
	            chk.style.marginLeft = "4px";
	            $(chk).attr('rel', 'select');
	            
	        }
	        else {
	            $(chk).click(function() {
	                var checked = $(this).attr('checked');
	                if (checked == 'checked') {
	                    $("input[rel=select]").attr('checked', 'checked');
	                }
	                else {
	                    $("input[rel=select]").attr('checked', null);
	                }
	            });
	        }
	    });
	
});
/// Consumer Invite An RFP tab mode changes
function consumerMarketDataNeedsInviteRFPModes(tabmode){
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("consumerwriteanrfp1")){
		document.getElementById('changeconsumerinvitemarketdataneeds1').style.backgroundColor = '#5CE5E5';
		document.getElementById('interconsumerdivinvitemarketoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchoconsumerinvitemarketoffer1').style.backgroundColor = '#5CE5E5';
		
		document.getElementById('changeconsumerinvitemarketdataneeds2').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer2').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer2').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumerinvitemarketdataneeds3').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer3').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer3').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumerinvitemarketdataneeds4').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer4').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer4').style.backgroundColor = ''; 
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("consumerrfpresponse1")){
		alert('taben-:');
		document.getElementById('changeconsumerinvitemarketdataneeds1').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer1').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer1').style.backgroundColor = '';
		
		document.getElementById('changeconsumerinvitemarketdataneeds2').style.backgroundColor = '#5CE5E5';
		document.getElementById('interconsumerdivinvitemarketoffer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchoconsumerinvitemarketoffer2').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeconsumerinvitemarketdataneeds3').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer3').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer3').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumerinvitemarketdataneeds4').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer4').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer4').style.backgroundColor = '';  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("consumershortlistedvendor1")){
		document.getElementById('changeconsumerinvitemarketdataneeds1').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer1').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer1').style.backgroundColor = '';
		
		document.getElementById('changeconsumerinvitemarketdataneeds2').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer2').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer2').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumerinvitemarketdataneeds3').style.backgroundColor = '#5CE5E5';
		document.getElementById('interconsumerdivinvitemarketoffer3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchoconsumerinvitemarketoffer3').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeconsumerinvitemarketdataneeds4').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer4').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer4').style.backgroundColor = ''; 
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("consumerfinalvendor1")){
		document.getElementById('changeconsumerinvitemarketdataneeds1').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer1').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer1').style.backgroundColor = '';
		
		document.getElementById('changeconsumerinvitemarketdataneeds2').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer2').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer2').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumerinvitemarketdataneeds3').style.backgroundColor = '';
		document.getElementById('interconsumerdivinvitemarketoffer3').style.backgroundColor = '';  
		document.getElementById('anchoconsumerinvitemarketoffer3').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumerinvitemarketdataneeds4').style.backgroundColor = '#5CE5E5';
		document.getElementById('interconsumerdivinvitemarketoffer4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchoconsumerinvitemarketoffer4').style.backgroundColor = '#5CE5E5'; 		 
	}
}

 