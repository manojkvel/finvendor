jQuery(document).ready(function() {
	// Support Details 
	$(document).on("click", ".addtotable", function (e){
		document.getElementById('jsontable1').value = "";
		var assetclass = $("#assetclass").val();
		var securitynames = $("#assetClassVendorSecurityMaps").val();
	    var datacoverageregion = $("#datacoverageregion").val();
	    var datacoveragecountry = $("#datacoveragecountry").val();
	    var datacoverageexchange = $("#datacoverageexchange").val();
	    var dataattribute = $("#dataattribute").val();
		document.getElementById("assetclass").value = assetclass;
		document.getElementById("assetClassVendorSecurityMaps").value = securitynames;
		document.getElementById("datacoverageregion").value = datacoverageregion;
		document.getElementById("datacoveragecountry").value = datacoveragecountry;
		document.getElementById("datacoverageexchange").value = datacoverageexchange;
		document.getElementById("dataattribute").value = dataattribute;
		
		if(assetclass != '-SELECT-' && securitynames != null && securitynames.length > 0  &&datacoverageregion != '-SELECT-'  && datacoverageregion != null && datacoverageregion.length >0 
				&& datacoveragecountry != '-SELECT-' &&datacoveragecountry != null && datacoveragecountry.length > 0 
				&& datacoverageexchange != '-SELECT-'   && datacoverageexchange != null && datacoverageexchange.length >0 &&
				dataattribute != null && dataattribute.length > 0){
			document.getElementById("supportceoveragevalidationforaddmore").innerHTML = '';
			
			$("#sample_1 tbody").append('<tr><td>' + assetclass + '</td><td>' + securitynames + '</td> <td>' + datacoverageregion + '</td>   <td>' + datacoveragecountry + '</td>  <td>' + datacoverageexchange + '</td>  <td>' + dataattribute + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
			var table = $('#sample_1').tableToJSON();
			
			document.getElementById('jsontable1').value = JSON.stringify(table);
		}else{
			document.getElementById("supportceoveragevalidationforaddmore").innerHTML = 'please choose mandatory fields..!';
		}
		
		
	});
	 $(document).on("click", ".deleteButton", function (e) {
    	var target = e.target;
		$(target).closest('tr').remove();
		var table = $('#sample_1').tableToJSON();
	    document.getElementById('jsontable1').value = JSON.stringify(table);
	});
	 
	 ///CSV File for Support Details Upload Code
	 $(document).on("click", ".fileupload", function (e) {
		 var fileId = $("#fileUpload").val();
		 if(fileId != null && fileId.length > 0){
			 loadUploadFiles(fileId);	 
		 }
	        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
	        if (regex.test($("#fileUpload").val().toLowerCase())) {
	            if (typeof (FileReader) != "undefined") {
	                var reader = new FileReader();
	                reader.onload = function (e) {
	                  var rows = e.target.result.split("\n");
	                    for (var i = 1; i < rows.length; i++) {
	                    	 if(rows[i].split(",")[1]){
	                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
	                    	 }
	                    }
	                	document.getElementById('jsontable1').value = JSON.stringify($('#sample_1').tableToJSON()); 
	                };
	                reader.readAsText($("#fileUpload")[0].files[0]);
	            } else {
	                alert("This browser does not support HTML5.");
	            }
	        } else {
	            alert("Please upload a valid file.");
	        }
	        
	        
	    });
	 
	// Award Details 
		$(document).on("click", ".awardaddtotable", function (e){
			document.getElementById('awardjsontable1').value = "";
			var awardassetclass = $("#awardassetclass").val();
			var assetClassVendorSecurityAwardMaps = $("#assetClassVendorSecurityAwardMaps").val();
		    var awardname = $("#awardname").val();
		    var awardsponsor = $("#awardsponsor").val();
		    var awardedyear = $("#awardedyear").val();
		    
			document.getElementById("awardassetclass").value = awardassetclass;
			document.getElementById("assetClassVendorSecurityAwardMaps").value = assetClassVendorSecurityAwardMaps;
			document.getElementById("awardname").value = awardname;
			document.getElementById("awardsponsor").value = awardsponsor;
			document.getElementById("awardedyear").value = awardedyear;
			
			if(awardassetclass != '-SELECT-' && assetClassVendorSecurityAwardMaps != null && assetClassVendorSecurityAwardMaps.length > 0  
					&& awardname != null && awardname.length >0 
					&& awardsponsor != null && awardsponsor.length > 0 && awardedyear != null && awardedyear.length >0){
				document.getElementById("awardvalidationforaddmore").innerHTML = '';
				$("#awardsample_1 tbody").append('<tr><td>' + awardassetclass + '</td><td>' + assetClassVendorSecurityAwardMaps + '</td> <td>' + awardname + '</td>   <td>' + awardsponsor + '</td>  <td>' + awardedyear + '</td>     <td><a class="awarddeleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
				var table = $('#awardsample_1').tableToJSON();
				document.getElementById('awardjsontable1').value = JSON.stringify(table);
			}else{
				document.getElementById("awardvalidationforaddmore").innerHTML = 'please choose mandatory fields..!';
			}
			
			
		});
		 $(document).on("click", ".awarddeleteButton", function (e) {
	    	var target = e.target;
			$(target).closest('tr').remove();
			var table = $('#awardsample_1').tableToJSON();
		    document.getElementById('awardjsontable1').value = JSON.stringify(table);
		});
		 
		 ///CSV File for Support Details Upload Code
		 $(document).on("click", ".fileupload", function (e) {
			 var fileId = $("#fileUpload").val();
			 if(fileId != null && fileId.length > 0){
				 loadUploadFiles(fileId);	 
			 }
		        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
		        if (regex.test($("#fileUpload").val().toLowerCase())) {
		            if (typeof (FileReader) != "undefined") {
		                var reader = new FileReader();
		                reader.onload = function (e) {
		                  var rows = e.target.result.split("\n");
		                    for (var i = 1; i < rows.length; i++) {
		                    	 if(rows[i].split(",")[1]){
		                    		 $("#awardsample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>     <td><a class="awarddeleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
		                    	 }
		                    }
		                	document.getElementById('awardjsontable1').value = JSON.stringify($('#awardsample_1').tableToJSON()); 
		                };
		                reader.readAsText($("#fileUpload")[0].files[0]);
		            } else {
		                alert("This browser does not support HTML5.");
		            }
		        } else {
		            alert("Please upload a valid file.");
		        }
		        
		        
		    });
	 
	 // Data Coverage
	 $(document).on("click", ".addtotablesupport", function (e){
			document.getElementById('jsontablesupport').value = "";
			var supportcoverageregion = $("#supportcoverageregion").val();
			var supportcoveragecountry = $("#supportcoveragecountry").val();
		    var vendorsupporttime = $("#vendorsupporttime").val();
		    var vendorcostrange = $("#vendorcostrange").val();
		    var phonenumber = $("#phonenumber").val();
		    var email = $("#email").val();
			document.getElementById("supportcoverageregion").value = supportcoverageregion;
			document.getElementById("supportcoveragecountry").value = supportcoveragecountry;
			document.getElementById("vendorsupporttime").value = vendorsupporttime;
			document.getElementById("phonenumber").value = phonenumber;
			document.getElementById("email").value = email;
			$("#samplesupport tbody").append('<tr><td>' + supportcoverageregion + '</td><td>' + supportcoveragecountry + '</td> <td>' + vendorsupporttime + '</td> <td>' + vendorcostrange + '</td>   <td>' + phonenumber + '</td>  <td>' + email + '</td>  <td><a class="deleteButtonsupport"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
			var table = $('#samplesupport').tableToJSON();
			document.getElementById('jsontablesupport').value = JSON.stringify(table);
		});
	 
	///CSV File for Support Coverage Upload Code
	 $(document).on("click", ".fileupmyoffercoverage", function (e) {
		 var fileId = $("#fileUploadmyoffercoverage").val();
		 if(fileId != null && fileId.length > 0){
			 loadUploadFiles(fileId);	 
		 }
	        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
	        if (regex.test($("#fileUploadmyoffercoverage").val().toLowerCase())) {
	            if (typeof (FileReader) != "undefined") {
	                var reader = new FileReader();
	                reader.onload = function (e) {
	                    var rows = e.target.result.split("\n");
	                    for (var i = 1; i < rows.length; i++) {
	                    	if(!rows[i].split(",")[1].match('undefined')){
	                    		$("#samplesupport tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td><td>' + rows[i].split(",")[5] + '</td>   <td><a class="deleteButtonsupport"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
	                    	}
	                    }
	                	document.getElementById('jsontablesupport').value = JSON.stringify($('#samplesupport').tableToJSON()); 
	                };
	                reader.readAsText($("#fileUploadmyoffercoverage")[0].files[0]);
	            } else {
	                alert("This browser does not support HTML5.");
	            }
	        } else {
	            alert("Please upload a valid file.");
	        }
	    });
	 
		 $(document).on("click", ".deleteButtonsupport", function (e) {
	    	var target = e.target;
			$(target).closest('tr').remove();
			var table = $('#samplesupport').tableToJSON();
		    document.getElementById('jsontablesupport').value = JSON.stringify(table);
		});
		 
		 // Data Distribution
		 $(document).on("click", ".addtotabledistribution", function (e){
				document.getElementById('jsontablevendorofferingdistribution').value = "";
				var datadistassetclass = $("#datadistassetclass").val();
				var assetClassVendorSecurityDistriMaps = $("#assetClassVendorSecurityDistriMaps").val();
			    var filename = $("#filename").val();
			    var filebriefdesc = $("#filebriefdesc").val();
			    var feedtype = $("#feedtype").val();
			    var feedsubtype = $("#feedsubtype").val();
			    var distributionmethod = $("#distributionmethod").val();
			    var frequency = $("#frequency").val();
			    var coverageregion = $("#coverageregion").val();
			    var coveragecountry = $("#coveragecountry").val();
			    var coverageexchange = $("#coverageexchange").val();
			    alert('getting all the info--:');
				document.getElementById("datadistassetclass").value = datadistassetclass;
				document.getElementById("assetClassVendorSecurityDistriMaps").value = assetClassVendorSecurityDistriMaps;
				document.getElementById("filename").value = filename;
				document.getElementById("filebriefdesc").value = filebriefdesc;
				document.getElementById("feedtype").value = feedtype;
				document.getElementById("feedsubtype").value = feedsubtype;
				document.getElementById("distributionmethod").value = distributionmethod;
				document.getElementById("frequency").value = frequency;
				document.getElementById("coverageregion").value = coverageregion;
				document.getElementById("coveragecountry").value = coveragecountry;
				document.getElementById("coverageexchange").value = coverageexchange;
				
				$("#sampledistribution tbody").append('<tr><td>' + datadistassetclass + '</td><td>' + assetClassVendorSecurityDistriMaps + '</td> <td>' + filename + '</td>   <td>' + filebriefdesc + '</td>  <td>' + feedtype + '</td> <td>' + feedsubtype + '</td> <td>' + distributionmethod + '</td> <td>' + frequency + '</td>  <td>' + coverageregion + '</td>  <td>' + coveragecountry + '</td> <td>' + coverageexchange + '</td> <td><a class="deleteButtondistribution"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
				var table = $('#sampledistribution').tableToJSON();
				document.getElementById('jsontablevendorofferingdistribution').value = JSON.stringify(table);
				alert('added sucessfully--:');
			});
			 
			///CSV File for Data Distribution Upload Code
			 $(document).on("click", ".fileupmyofferdistribution", function (e) {
				 var fileId = $("#fileUploadmyofferdistribution").val();
				 if(fileId != null && fileId.length > 0){
					 loadUploadFiles(fileId);	 
				 }
			        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
			        if (regex.test($("#fileUploadmyofferdistribution").val().toLowerCase())) {
			            if (typeof (FileReader) != "undefined") {
			                var reader = new FileReader();
			                reader.onload = function (e) {
			                    var rows = e.target.result.split("\n");
			                    for (var i = 1; i < rows.length; i++) {
			                    	if(!rows[i].split(",")[1].match('undefined')){
			                    	$("#sampledistribution tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td> <td>' + rows[i].split(",")[5] + '</td> <td>' + rows[i].split(",")[6] + '</td> <td>' + rows[i].split(",")[7] + '</td>  <td>' + rows[i].split(",")[8] + '</td> <td>' + rows[i].split(",")[9] + '</td> <td>' + rows[i].split(",")[10] + '</td> <td><a class="deleteButtondistribution"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
			                    	}
			                    }
			                	document.getElementById('jsontablevendorofferingdistribution').value = JSON.stringify($('#sampledistribution').tableToJSON());
			                };
			                reader.readAsText($("#fileUploadmyofferdistribution")[0].files[0]);
			            } else {
			                alert("This browser does not support HTML5.");
			            }
			        } else {
			            alert("Please upload a valid file.");
			        }
			    });
		 
			 $(document).on("click", ".deleteButtondistribution", function (e) {
		    	var target = e.target;
				$(target).closest('tr').remove();
				var table = $('#sampledistribution').tableToJSON();
			    document.getElementById('jsontablevendorofferingdistribution').value = JSON.stringify(table);
			});

			 /// checkboxes selection and deselect---:
			 $(".row-selectvendordashboard tr").each(function() {
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
			 
			 /// checkboxes selection and deselect---:
			 $(".row-selectvendordashrfpinbox tr").each(function() {
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

function loadSecurityTypes(assettypeId) {
	
	
		if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
			assettypeId = encode64(assettypeId);
			$.ajax({
				type: 'GET',
				url:  "loadVendorSecurityTypes?RAyuL="+assettypeId,
				cache:false,
				success : function(output){
					document.getElementById("assetClassVendorSecurityMaps").innerHTML = output;		
				},
				error : function(data, textStatus, jqXHR){
					//alert('Error: '+data+':'+textStatus);
				}
			});
		}
	}

function loadSecurityAwardTypes(assettypeId) {
	
	
	if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
		assettypeId = encode64(assettypeId);
		$.ajax({
			type: 'GET',
			url:  "loadSecurityAwardTypes?RAyuL="+assettypeId,
			cache:false,
			success : function(output){
				document.getElementById("assetClassVendorSecurityAwardMaps").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
}
function loadSecurityDistriTypes(assettypeId) {
	
	if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
		assettypeId = encode64(assettypeId);
		$.ajax({
			type: 'GET',
			url:  "loadSecurityDistriTypes?RAyuL="+assettypeId,
			cache:false,
			success : function(output){
				document.getElementById("assetClassVendorSecurityDistriMaps").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
}

function loadFocusSecurityTypes(assettypeId) {
	if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
		assettypeId = encode64(assettypeId);
		$.ajax({
			type: 'GET',
			url:  "loadFocusSecurityTypes?RAyuL="+assettypeId,
			cache:false,
			success : function(output){
				document.getElementById("assetclassVendorloadFocusSecurityTypes").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
}

function selectAll(){
	var val_array = 	values.split("-");
	if ($('#'+id).prop('checked') == true) {	    	
    	for (var i = 1; i < val_array.length; i++) {
    		$('#'+val_array[i]).prop('checked', true);
    	}
	} else {
		for (var i = 1; i < val_array.length; i++) {
    		$('#'+val_array[i]).prop('checked', false);
    	}
	}
	
}
 
function setValue(value) {
    document.getElementById('value').value = value;
}
/// Mode change for Vendor Dashboard tabs
function activeMode(tabmode){
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("personaldetails")){
		document.getElementById('change').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdiv').style.backgroundColor = '#5CE5E5';  
		document.getElementById('ancho').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('change1').style.backgroundColor = '';
		document.getElementById('interdiv1').style.backgroundColor = '';  
		document.getElementById('ancho1').style.backgroundColor = ''; 
		
		document.getElementById('change2').style.backgroundColor = '';
		document.getElementById('interdiv2').style.backgroundColor = '';  
		document.getElementById('ancho2').style.backgroundColor = '';  
	 
		
		document.getElementById('change4').style.backgroundColor = '';
		document.getElementById('interdiv4').style.backgroundColor = '';  
		document.getElementById('ancho4').style.backgroundColor = ''; 
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("supportcoverage")){
		document.getElementById('change1').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdiv1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('ancho1').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('change').style.backgroundColor = '';
		document.getElementById('interdiv').style.backgroundColor = '';  
		document.getElementById('ancho').style.backgroundColor = ''; 
		
		document.getElementById('change2').style.backgroundColor = '';
		document.getElementById('interdiv2').style.backgroundColor = '';  
		document.getElementById('ancho2').style.backgroundColor = ''; 
		 
		
		document.getElementById('change4').style.backgroundColor = '';
		document.getElementById('interdiv4').style.backgroundColor = '';  
		document.getElementById('ancho4').style.backgroundColor = ''; 
		
		  
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("awarddetails")){
		document.getElementById('change2').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdiv2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('ancho2').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('change').style.backgroundColor = '';
		document.getElementById('interdiv').style.backgroundColor = '';  
		document.getElementById('ancho').style.backgroundColor = '';
		
		document.getElementById('change1').style.backgroundColor = '';
		document.getElementById('interdiv1').style.backgroundColor = '';  
		document.getElementById('ancho1').style.backgroundColor = ''; 
		
	 
		
		document.getElementById('change4').style.backgroundColor = '';
		document.getElementById('interdiv4').style.backgroundColor = '';  
		document.getElementById('ancho4').style.backgroundColor = ''; 
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("searchdatabuyers")){
		document.getElementById('change3').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdiv3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('ancho3').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('change').style.backgroundColor = '';
		document.getElementById('interdiv').style.backgroundColor = '';  
		document.getElementById('ancho').style.backgroundColor = '';
		
		document.getElementById('change1').style.backgroundColor = '';
		document.getElementById('interdiv1').style.backgroundColor = '';  
		document.getElementById('ancho1').style.backgroundColor = ''; 
		
		document.getElementById('change2').style.backgroundColor = '';
		document.getElementById('interdiv2').style.backgroundColor = '';  
		document.getElementById('ancho2').style.backgroundColor = '';
		
		document.getElementById('change4').style.backgroundColor = '';
		document.getElementById('interdiv4').style.backgroundColor = '';  
		document.getElementById('ancho4').style.backgroundColor = ''; 
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("myrfp")){
		document.getElementById('change4').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdiv4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('ancho4').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('change').style.backgroundColor = '';
		document.getElementById('interdiv').style.backgroundColor = '';  
		document.getElementById('ancho').style.backgroundColor = '';
		
		document.getElementById('change1').style.backgroundColor = '';
		document.getElementById('interdiv1').style.backgroundColor = '';  
		document.getElementById('ancho1').style.backgroundColor = ''; 
		
		document.getElementById('change2').style.backgroundColor = '';
		document.getElementById('interdiv2').style.backgroundColor = '';  
		document.getElementById('ancho2').style.backgroundColor = '';
		
		 
	}
}
/// My Data Aggregator Vendor tab mode changes
function activeModeVendorMyofferings(tabmode){
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("vendormyofferingsdatacoverage")){
		document.getElementById('changeoffer').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivoffer').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooffer').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeoffer1').style.backgroundColor = '';
		document.getElementById('interdivoffer1').style.backgroundColor = '';  
		document.getElementById('anchooffer1').style.backgroundColor = ''; 
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("vendormyofferingsdatadistribution")){
		document.getElementById('changeoffer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooffer1').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('changeoffer').style.backgroundColor = '';
		document.getElementById('interdivoffer').style.backgroundColor = '';  
		document.getElementById('anchooffer').style.backgroundColor = ''; 
		  
	}
}

/// My Trading Application vendor tab mode changes
function activeTradingVendorMyofferings(tabmode){
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("tradingcapabilitiessupported")){
		document.getElementById('changetradingvendoroffer').style.backgroundColor = '#5CE5E5';
		document.getElementById('intertradingvendodivoffer').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchootradingvendoffer').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changetradingvendoroffer1').style.backgroundColor = '';
		document.getElementById('intertradingvendodivoffer1').style.backgroundColor = '';  
		document.getElementById('anchootradingvendoffer1').style.backgroundColor = ''; 
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("tradingsoftwaredetails")){
		document.getElementById('changetradingvendoroffer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('intertradingvendodivoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchootradingvendoffer1').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('changetradingvendoroffer').style.backgroundColor = '';
		document.getElementById('intertradingvendodivoffer').style.backgroundColor = '';  
		document.getElementById('anchootradingvendoffer').style.backgroundColor = ''; 
		  
	}
}

/// My Analytics Application vendor tab mode changes
function activeAnalyticsVendorMyofferings(tabmode){
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("analyticsfeaturessupported")){
		document.getElementById('changeAnalyticsvendoroffer').style.backgroundColor = '#5CE5E5';
		document.getElementById('interAnalyticsvendodivoffer').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooAnalyticsvendoffer').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeAnalyticsvendoroffer1').style.backgroundColor = '';
		document.getElementById('interAnalyticsvendodivoffer1').style.backgroundColor = '';  
		document.getElementById('anchooAnalyticsvendoffer1').style.backgroundColor = ''; 
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("analyticssoftwaredetails")){
		document.getElementById('changeAnalyticsvendoroffer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('interAnalyticsvendodivoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooAnalyticsvendoffer1').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('changeAnalyticsvendoroffer').style.backgroundColor = '';
		document.getElementById('interAnalyticsvendodivoffer').style.backgroundColor = '';  
		document.getElementById('anchooAnalyticsvendoffer').style.backgroundColor = ''; 
		  
	}
}

/// My Analyst Research Report Provider vendor tab mode changes
function activeVendorAnalyticsResearchMyofferings(tabmode){
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("researchcoverage")){
		document.getElementById('changeresearchreportvendoroffer').style.backgroundColor = '#5CE5E5';
		document.getElementById('interresearchreportvendodivoffer').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooresearchreportvendoffer').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeresearchreportvendoroffer1').style.backgroundColor = '';
		document.getElementById('interresearchreportvendodivoffer1').style.backgroundColor = '';  
		document.getElementById('anchooresearchreportvendoffer1').style.backgroundColor = ''; 
		
		document.getElementById('changeresearchreportvendoroffer2').style.backgroundColor = '';
		document.getElementById('interresearchreportvendodivoffer2').style.backgroundColor = '';  
		document.getElementById('anchooresearchreportvendoffer2').style.backgroundColor = ''; 
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("researchdetails")){
		document.getElementById('changeresearchreportvendoroffer').style.backgroundColor = '';
		document.getElementById('interresearchreportvendodivoffer').style.backgroundColor = '';  
		document.getElementById('anchooresearchreportvendoffer').style.backgroundColor = ''; 
		
		document.getElementById('changeresearchreportvendoroffer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('interresearchreportvendodivoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooresearchreportvendoffer1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeresearchreportvendoroffer2').style.backgroundColor = '';
		document.getElementById('interresearchreportvendodivoffer2').style.backgroundColor = '';  
		document.getElementById('anchooresearchreportvendoffer2').style.backgroundColor = ''; 
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("analystprofile")){
		document.getElementById('changeresearchreportvendoroffer').style.backgroundColor = '';
		document.getElementById('interresearchreportvendodivoffer').style.backgroundColor = '';  
		document.getElementById('anchooresearchreportvendoffer').style.backgroundColor = ''; 
		
		document.getElementById('changeresearchreportvendoroffer1').style.backgroundColor = '';
		document.getElementById('interresearchreportvendodivoffer1').style.backgroundColor = '';  
		document.getElementById('anchooresearchreportvendoffer1').style.backgroundColor = ''; 
		
		document.getElementById('changeresearchreportvendoroffer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('interresearchreportvendodivoffer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooresearchreportvendoffer2').style.backgroundColor = '#5CE5E5'; 
	}
}

///Vendor My profile information updation start here-----------------------------------------------------------------------------

////File validation while uploading image-------------------

function imageValidation() {
	var logoPath = $("#personalvencompanylogo").val();
	var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpeg|.jpg|.png| .gif)$/;
	if(logoPath !='' && logoPath.length > 0 && regex.test($("#personalvencompanylogo").val().toLowerCase())){
		document.getElementById("invalidfileformat").innerHTML = '';
	}else if(logoPath !='' && logoPath.length > 0 && !regex.test($("#personalvencompanylogo").val().toLowerCase())){
		document.getElementById("invalidfileformat").innerHTML = 'Invalid file format..!';
	}
	
}

 
/// Update code to Vendor personal Info--:
function updateVendorPersonalInfo(){
	showLoader();
	var personalvenfirstname = $("#personalvenfirstname").val();
	var personalvenlastname = $("#personalvenlastname").val();
	var personalvendesignation = $("#personalvendesignation").val();
	var personalvencompany = $("#personalvencompany").val();
	var personalvencompanyurl = $("#personalvencompanyurl").val();
	var personalvencompanyinfo = $("#personalvencompanyinfo").val();
	var personalvenprimemail = $("#personalvenprimemail").val();
	var personalvensecemail = $("#personalvensecemail").val();
	var personalvenphonenumber = $("#personalvenphonenumber").val();
	var personalvenregionofincorp = $("#personalvenregionofincorp").val();
	var personalvencountryofincorp = $("#personalvencountryofincorp").val();
	var personalvencompanylogo = $("#personalvencompanylogo").val();
	
	
	if(personalvenfirstname != '' && personalvenfirstname.length > 0 &&
			personalvendesignation != null && personalvendesignation.length > 0 &&
			personalvencompany != null && personalvencompany.length > 0 &&
			personalvencompanyurl != null && personalvencompanyurl.length > 0 && 
			personalvencompanyinfo != null && personalvencompanyinfo.length > 0 &&
			personalvenprimemail != null && personalvenprimemail.length >0 &&
			personalvenphonenumber != null && personalvenphonenumber.length > 0 &&
			personalvenregionofincorp != null && personalvenregionofincorp.length > 0 && personalvenregionofincorp != '-SELECT-' &&
			personalvencountryofincorp != null && personalvencountryofincorp.length >0 && personalvencountryofincorp != '-SELECT-' &&
			personalvencompanylogo != null && personalvencompanylogo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorPersonalTabInfo?venFirstname="+personalvenfirstname+"&venLastname="+personalvenlastname+"&venDesignation="+personalvendesignation
			                                            +"&venCompany="+personalvencompany+"&venCompanyUrl="+personalvencompanyurl+"&venCompanyInfo="+personalvencompanyinfo
			                                            +"&venPrimEmail="+personalvenprimemail+"&venSecEmail="+personalvensecemail+"&venPhoneNum="+personalvenphonenumber
			                                            +"&venRegionOfIncorp="+personalvenregionofincorp+"&venCountryOfIncorp="+personalvencountryofincorp+"&venCompanyLogo="+personalvencompanylogo,
			cache:false,
			/*contentType: 'multipart/form-data',*/
			success : function(output){
				alert('You have updated sucessfully..!');
				setTimeout(function() {
					document.getElementById("personaltabfailuremessage").innerHTML = '';
					document.getElementById("personaltabsucessmessage").innerHTML = 'You have updated sucessfully..!';
				}, 10);
				document.getElementById("vendorDetails").innerHTML = output;
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}else{
		document.getElementById("personaltabfailuremessage").innerHTML = 'Please enter all mandatory fields..!';
	}
	 
}


/// Update code to Vendor Support Coverage Info--:
function updateVendorSupportCoverageInfo(){
	var listOfSupportCoveageInfo = $("#jsontable1").val();
	if(listOfSupportCoveageInfo != '' && listOfSupportCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorSupportCoverageInfo?supportCoverageInfo="+listOfSupportCoveageInfo,
			cache:false,
			success : function(output){
				document.getElementById("supportcoveragetabsucessmessage").innerHTML = 'You have updated sucessfully..!';	
				alert('You have updated sucessfully..!');
			},
			error : function(errorMsg, textStatus, jqXHR){
				// alert('Error: '+errorMsg+':'+textStatus);
			}
		});
	}
	 
}

/// Update code to Vendor Award tab details --:
function updateVendorAwardDetails(){
	var awardassetclass = $("#awardassetclass").val();
	var assetClassVendorSecurityAwardMaps = $("#assetClassVendorSecurityAwardMaps").val();
	var awardname = $("#awardname").val();
	var awardsponsor = $("#awardsponsor").val();
	var awardedyear = $("#awardedyear").val();
	if(awardassetclass != '' && awardassetclass.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorAwardDetails?awardassetclass="+awardassetclass+"&assetClassVendorSecurityAwardMaps="+assetClassVendorSecurityAwardMaps
			+"&awardname="+awardname+"&awardsponsor="+awardsponsor+"&awardedyear="+awardedyear,
			cache:false,
			success : function(output){
				alert('You have updated sucessfully..!');
				document.getElementById("awardtabsucessmessage").innerHTML = 'You have updated sucessfully..!';		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}

	 
}

/// Update code to Vendor Search Data Buyers details --:
function updateVendorSearchDataBuyers(){
	var databuyername = $("#databuyername").val();
	var focusregion = $("#focusregion").val();
	var focusassetclass = $("#focusassetclass").val();
	var focussecuritytype = $("#focussecuritytype").val();
	if(awardassetclass != '' && awardassetclass.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorSearchDataBuyers?databuyername="+databuyername+"&focusregion="+focusregion
			+"&focusassetclass="+focusassetclass+"&focussecuritytype="+focussecuritytype,
			cache:false,
			success : function(output){
				alert('You have updated sucessfully..!');
				document.getElementById("databuyerstabsucessmessage").innerHTML = 'You have updated sucessfully..!';		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
}
///Vendor My profile information updation ends here-----------------------------------------------------------------------------



///Vendor My Offerings information updation start here-----------------------------------------------------------------------------

/// Update code to Vendor Data Coverage Info--:
function updateVendorOfferingDataCoverageInfo(){
	var listOfDataCoveageInfo = $("#jsontablesupport").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingDataCoverageInfo?dataCoverageInfo="+listOfDataCoveageInfo,
			cache:false,
			success : function(output){
				document.getElementById("myofferingsdatacoveragetabsucessmessage").innerHTML = 'You have updated sucessfully..!';	
				alert('You have updated sucessfully..!');
			},
			error : function(errorMsg, textStatus, jqXHR){
				// alert('Error: '+errorMsg+':'+textStatus);
			}
		});
	}
	 
}

///Vendor My Offerings information updation ends here-----------------------------------------------------------------------------

var id_insert= 0;
 function loadUploadFiles(fileId){
	var form = document.getElementById("selectedFiles");
    var file = fileId;
    var fileUp= file.split('\\');
    /*document.getElementById("selectedFiles").innerHTML = form.innerHTML +'<br>' +'<span class="lable_header_popupwindow">'+ fileUp[2] +'</span>' +'&nbsp;&nbsp;&nbsp;<a class="fileupload btnpopup btn-default">Upload</a>' +  '&nbsp;&nbsp;&nbsp;<a class="btnpopupremove btn-default" href="">Remove</a> '+ '<br>' ;*/
    document.getElementById("selectedFiles").innerHTML = form.innerHTML +'<div id="elements">'+ '<input type="checkbox" value="'+  id_insert    +'" id="'+  id_insert   +'" name="chkboxnames" class="lable_header_popupwindow">'+'&nbsp;&nbsp;&nbsp;&nbsp;' +' <span class="lable_header_popupwindow" id="'+id_insert+'" value="'+id_insert+'" name="spanfile">'+ fileUp[2] +'</span>' +'&nbsp;&nbsp;&nbsp;' + '<br> </div>'  ;
    file.value = "";
    id_insert=id_insert+1;
}

function loadCheckBoxes(){
	var rmvCheckBoxes = document.getElementsByName('chkboxnames');
	var spanfile = document.getElementsByName('spanfile');
	var i = 0;
	while(rmvCheckBoxes.length > 0){
		/*if(rmvCheckBoxes[i].checked)
        {
			alert('if check--:' + rmvCheckBoxes[i].checked);*/
            removeElm(rmvCheckBoxes[i]);
            removeElm(spanfile[i]);
        /*}*/
		i++;
	}
} 
function removeElm(elm){
	 elm.remove();
	}

 