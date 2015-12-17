jQuery(document).ready(function() {
	// Consumer My Business Needs  -----> 1 tab (Market Data Needs)
	$(document).on("click", ".consumeraddtotable", function (e){
		document.getElementById('consumerjsontable1').value = "";
		var assetclass = $("#consumerassetclass").val();
		var securitynames = $("#assetClassConsumerSecurityMaps").val();
	    var datacoverageregion = $("#consumerdatacoverageregion").val();
	    var datacoveragecountry = $("#consumerdatacoveragecountry").val();
	    var datacoverageexchange = $("#consumerdatacoverageexchange").val();
	    var dataattribute = $("#consumerdataattribute").val();
		document.getElementById("consumerassetclass").value = assetclass;
		document.getElementById("assetClassConsumerSecurityMaps").value = securitynames;
		document.getElementById("consumerdatacoverageregion").value = datacoverageregion;
		document.getElementById("consumerdatacoveragecountry").value = datacoveragecountry;
		document.getElementById("consumerdatacoverageexchange").value = datacoverageexchange;
		document.getElementById("consumerdataattribute").value = dataattribute;
		
		$("#sample_1 tbody").append('<tr><td>' + assetclass + '</td><td>' + securitynames + '</td> <td>' + datacoverageregion + '</td>   <td>' + datacoveragecountry + '</td>  <td>' + datacoverageexchange + '</td>  <td>' + dataattribute + '</td>  <td><a class="consumerdeleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
		var table = $('#sample_1').tableToJSON();
		document.getElementById('consumerjsontable1').value = JSON.stringify(table);
	});
	 $(document).on("click", ".consumerdeleteButton", function (e) {
    	var target = e.target;
		$(target).closest('tr').remove();
		var table = $('#sample_1').tableToJSON();
	    document.getElementById('consumerjsontable1').value = JSON.stringify(table);
	});
	 ///CSV File for Consumer My Business Needs Upload Code   -----> 1 tab (Market Data Needs)
	 $(document).on("click", ".consumerfileupload", function (e) {
	        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
	        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
	            if (typeof (FileReader) != "undefined") {
	                var reader = new FileReader();
	                reader.onload = function (e) {
	                  var rows = e.target.result.split("\n");
	                    for (var i = 1; i < rows.length; i++) {
	                    	 if(!rows[i].split(",")[1].match('undefined')){
	                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
	                    	 }
	                    }
	                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
	                };
	                reader.readAsText($("#consumerfileUpload")[0].files[0]);
	            } else {
	                alert("This browser does not support HTML5.");
	            }
	        } else {
	            alert("Please upload a valid file.");
	        }
	    });
	 
	 
	// Consumer My Business Needs  -----> 2 tab (Trading Application Needs)
		$(document).on("click", ".tradeapplicationconsumeraddmore", function (e){
			document.getElementById('tradeapplicationconsujsontable').value = "";
			var tradeassetclass = $("#tradeassetclass").val();
			var tradesubassetclass = $("#tradesubassetclass").val();
		    var tradecapabilitytype = $("#tradecapabilitytype").val();
		    var tradeexecutiontype = $("#tradeexecutiontype").val();
		    var tradedatacoverageregion = $("#tradedatacoverageregion").val();
		    var tradedatacoveragecountry = $("#tradedatacoveragecountry").val();
		    var tradedatacoverageexchange = $("#tradedatacoverageexchange").val();
		    var trademydarkpoolvenues = $("#trademydarkpoolvenues").val();
		    var tradeordertype = $("#tradeordertype").val();
		    var tradeaccessibility = $("#tradeaccessibility").val();
		    var tradesuitability = $("#tradesuitability").val();
		    var tradesoftwaretype = $("#tradesoftwaretype").val();
		    var tradeexistinguserbase = $("#tradeexistinguserbase").val();
		    var tradecostrange = $("#tradecostrange").val();
		    
			document.getElementById("tradeassetclass").value = tradeassetclass;
			document.getElementById("tradesubassetclass").value = tradesubassetclass;
			document.getElementById("tradecapabilitytype").value = tradecapabilitytype;
			document.getElementById("tradeexecutiontype").value = tradeexecutiontype;
			document.getElementById("tradedatacoverageregion").value = tradedatacoverageregion;
			document.getElementById("tradedatacoveragecountry").value = tradedatacoveragecountry;
			document.getElementById("tradedatacoverageexchange").value = tradedatacoverageexchange;
			document.getElementById("trademydarkpoolvenues").value = trademydarkpoolvenues;
			document.getElementById("tradeordertype").value = tradeordertype;
			document.getElementById("tradeaccessibility").value = tradeaccessibility;
			document.getElementById("tradesuitability").value = tradesuitability;
			document.getElementById("tradesoftwaretype").value = tradesoftwaretype;
			document.getElementById("tradeexistinguserbase").value = tradeexistinguserbase;
			document.getElementById("tradecostrange").value = tradecostrange;
			
			$("#sample_1tradeapplicationconsu tbody").append('<tr><td>' + tradeassetclass + '</td><td>' + tradesubassetclass + '</td> <td>' + tradecapabilitytype + '</td>   <td>' + tradeexecutiontype + '</td>  <td>' + tradedatacoverageregion + '</td>  <td>' + tradedatacoveragecountry + '</td>  <td>' + tradedatacoverageexchange + '</td>  <td>' + trademydarkpoolvenues + '</td>  <td>' + tradeordertype + '</td>  <td>' + tradeaccessibility + '</td>  <td>' + tradesuitability + '</td>  <td>' + tradesoftwaretype + '</td>  <td>' + tradeexistinguserbase + '</td>  <td>' + tradecostrange + '</td>   <td><a class="tradeapplicationconsumerdelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
			var table = $('#sample_1tradeapplicationconsu').tableToJSON();
			document.getElementById('tradeapplicationconsujsontable').value = JSON.stringify(table);
		});
		 $(document).on("click", ".tradeapplicationconsumerdelete", function (e) {
	    	var target = e.target;
			$(target).closest('tr').remove();
			var table = $('#sample_1tradeapplicationconsu').tableToJSON();
		    document.getElementById('tradeapplicationconsujsontable').value = JSON.stringify(table);
		});
		 ///CSV File for Consumer My Business Needs Upload Code   -----> 2 tab (Trading Application Needs)
		 $(document).on("click", ".consumerfileupload", function (e) {
		        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
		        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
		            if (typeof (FileReader) != "undefined") {
		                var reader = new FileReader();
		                reader.onload = function (e) {
		                  var rows = e.target.result.split("\n");
		                    for (var i = 1; i < rows.length; i++) {
		                    	 if(!rows[i].split(",")[1].match('undefined')){
		                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
		                    	 }
		                    }
		                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
		                };
		                reader.readAsText($("#consumerfileUpload")[0].files[0]);
		            } else {
		                alert("This browser does not support HTML5.");
		            }
		        } else {
		            alert("Please upload a valid file.");
		        }
		    });
		 
		// Consumer My Business Needs  -----> 3 tab (Analytics Application Needs)
			$(document).on("click", ".analyticsApplicationAdd", function (e){
				document.getElementById('analyticsApplicationAddJsontable').value = "";
				var analyticssolutiontype = $("#analyticssolutiontype").val();
				var analysolutionsubtype = $("#analysolutionsubtype").val();
			    var customizablecal = $("#customizablecal").val();
			    var realtimemarket = $("#realtimemarket").val();
			    var analyaccessibility = $("#analyaccessibility").val();
			    var analysuitability = $("#analysuitability").val();
			    var analysoftwaretype = $("#analysoftwaretype").val();
			    var analycostrange = $("#analycostrange").val();
			    var analyexistuserbase = $("#analyexistuserbase").val();
			    
				document.getElementById("analyticssolutiontype").value = analyticssolutiontype;
				document.getElementById("analysolutionsubtype").value = analysolutionsubtype;
				document.getElementById("customizablecal").value = customizablecal;
				document.getElementById("realtimemarket").value = realtimemarket;
				document.getElementById("analyaccessibility").value = analyaccessibility;
				document.getElementById("analysuitability").value = analysuitability;
				document.getElementById("analysoftwaretype").value = analysoftwaretype;
				document.getElementById("analycostrange").value = analycostrange;
				document.getElementById("analyexistuserbase").value = analyexistuserbase;
				
				$("#sample_1analyticsApplic tbody").append('<tr><td>' + analyticssolutiontype + '</td><td>' + analysolutionsubtype + '</td> <td>' + customizablecal + '</td>   <td>' + realtimemarket + '</td>  <td>' + analyaccessibility + '</td><td>' + analysuitability + '</td>  <td>' + analysoftwaretype + '</td>  <td>' + analycostrange + '</td><td>' + analyexistuserbase + '</td>  <td><a class="analyticsApplicationDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
				var table = $('#sample_1analyticsApplic').tableToJSON();
				document.getElementById('analyticsApplicationAddJsontable').value = JSON.stringify(table);
			});
			 $(document).on("click", ".analyticsApplicationDelete", function (e) {
		    	var target = e.target;
				$(target).closest('tr').remove();
				var table = $('#sample_1analyticsApplic').tableToJSON();
			    document.getElementById('analyticsApplicationAddJsontable').value = JSON.stringify(table);
			});
			 ///CSV File for Consumer My Business Needs Upload Code   -----> 3 tab (Analytics Application Needs)
			 $(document).on("click", ".consumerfileupload", function (e) {
			        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
			        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
			            if (typeof (FileReader) != "undefined") {
			                var reader = new FileReader();
			                reader.onload = function (e) {
			                  var rows = e.target.result.split("\n");
			                    for (var i = 1; i < rows.length; i++) {
			                    	 if(!rows[i].split(",")[1].match('undefined')){
			                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
			                    	 }
			                    }
			                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
			                };
			                reader.readAsText($("#consumerfileUpload")[0].files[0]);
			            } else {
			                alert("This browser does not support HTML5.");
			            }
			        } else {
			            alert("Please upload a valid file.");
			        }
			    });
			 
			// Consumer My Business Needs  -----> 4 tab (Research Report Needs)
				$(document).on("click", ".analyticsApplicationAdd", function (e){
					document.getElementById('analyticsApplicationAddJsontable').value = "";
					var analyticssolutiontype = $("#analyticssolutiontype").val();
					var analysolutionsubtype = $("#analysolutionsubtype").val();
				    var customizablecal = $("#customizablecal").val();
				    var realtimemarket = $("#realtimemarket").val();
				    var analyaccessibility = $("#analyaccessibility").val();
				    var analysuitability = $("#analysuitability").val();
				    var analysoftwaretype = $("#analysoftwaretype").val();
				    var analycostrange = $("#analycostrange").val();
				    var analyexistuserbase = $("#analyexistuserbase").val();
				    
					document.getElementById("analyticssolutiontype").value = analyticssolutiontype;
					document.getElementById("analysolutionsubtype").value = analysolutionsubtype;
					document.getElementById("customizablecal").value = customizablecal;
					document.getElementById("realtimemarket").value = realtimemarket;
					document.getElementById("analyaccessibility").value = analyaccessibility;
					document.getElementById("analysuitability").value = analysuitability;
					document.getElementById("analysoftwaretype").value = analysoftwaretype;
					document.getElementById("analycostrange").value = analycostrange;
					document.getElementById("analyexistuserbase").value = analyexistuserbase;
					
					$("#sample_1analyticsApplic tbody").append('<tr><td>' + analyticssolutiontype + '</td><td>' + analysolutionsubtype + '</td> <td>' + customizablecal + '</td>   <td>' + realtimemarket + '</td>  <td>' + analyaccessibility + '</td><td>' + analysuitability + '</td>  <td>' + analysoftwaretype + '</td>  <td>' + analycostrange + '</td><td>' + analyexistuserbase + '</td>  <td><a class="analyticsApplicationDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
					var table = $('#sample_1analyticsApplic').tableToJSON();
					document.getElementById('analyticsApplicationAddJsontable').value = JSON.stringify(table);
				});
				 $(document).on("click", ".analyticsApplicationDelete", function (e) {
			    	var target = e.target;
					$(target).closest('tr').remove();
					var table = $('#sample_1analyticsApplic').tableToJSON();
				    document.getElementById('analyticsApplicationAddJsontable').value = JSON.stringify(table);
				});
				 ///CSV File for Consumer My Business Needs Upload Code   -----> 4 tab (Research Report Needs)
				 $(document).on("click", ".consumerfileupload", function (e) {
				        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
				        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
				            if (typeof (FileReader) != "undefined") {
				                var reader = new FileReader();
				                reader.onload = function (e) {
				                  var rows = e.target.result.split("\n");
				                    for (var i = 1; i < rows.length; i++) {
				                    	 if(!rows[i].split(",")[1].match('undefined')){
				                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
				                    	 }
				                    }
				                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
				                };
				                reader.readAsText($("#consumerfileUpload")[0].files[0]);
				            } else {
				                alert("This browser does not support HTML5.");
				            }
				        } else {
				            alert("Please upload a valid file.");
				        }
				    });
				 

				// Consumer My Vendor Preference  -----> 1 tab (Market Data Needs)
				$(document).on("click", ".myVendorPreferMarkerDataUpload", function (e){
					document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = "";
					var myvenprefercoverageregion = $("#myvenprefercoverageregion").val();
					var myvenprefercoveragecountry = $("#myvenprefercoveragecountry").val();
				    var myvenpreferyearoperation = $("#myvenpreferyearoperation").val();
				    var myvenprefersupportregion = $("#myvenprefersupportregion").val();
				    var myvenprefersupporttime = $("#myvenprefersupporttime").val();
				    var myvenpreferawards = $("#myvenpreferawards").val();
				    
				    
					document.getElementById("myvenprefercoverageregion").value = myvenprefercoverageregion;
					document.getElementById("myvenprefercoveragecountry").value = myvenprefercoveragecountry;
					document.getElementById("myvenpreferyearoperation").value = myvenpreferyearoperation;
					document.getElementById("myvenprefersupportregion").value = myvenprefersupportregion;
					document.getElementById("myvenprefersupporttime").value = myvenprefersupporttime;
					document.getElementById("myvenpreferawards").value = myvenpreferawards;
					
					$("#sample_1myVenPreferenceMarketData tbody").append('<tr><td>' + myvenprefercoverageregion + '</td><td>' + myvenprefercoveragecountry + '</td> <td>' + myvenpreferyearoperation + '</td>   <td>' + myvenprefersupportregion + '</td>  <td>' + myvenprefersupporttime + '</td><td>' + myvenpreferawards + '</td>   <td><a class="myVendorPreferenceMarketDataDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
					var table = $('#sample_1myVenPreferenceMarketData').tableToJSON();
					document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
				});
				 $(document).on("click", ".myVendorPreferenceMarketDataDelete", function (e) {
			    	var target = e.target;
					$(target).closest('tr').remove();
					var table = $('#sample_1myVenPreferenceMarketData').tableToJSON();
				    document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
				});
				 ///CSV File for Consumer My Vendor Preference Upload Code   -----> 1 tab (Market Data Needs)
				 $(document).on("click", ".consumerfileupload", function (e) {
				        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
				        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
				            if (typeof (FileReader) != "undefined") {
				                var reader = new FileReader();
				                reader.onload = function (e) {
				                  var rows = e.target.result.split("\n");
				                    for (var i = 1; i < rows.length; i++) {
				                    	 if(!rows[i].split(",")[1].match('undefined')){
				                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
				                    	 }
				                    }
				                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
				                };
				                reader.readAsText($("#consumerfileUpload")[0].files[0]);
				            } else {
				                alert("This browser does not support HTML5.");
				            }
				        } else {
				            alert("Please upload a valid file.");
				        }
				    });
				 
					// Consumer My Vendor Preference  -----> 2 tab (Trading Application Needs)
					$(document).on("click", ".myVendorPreferTradngApplicationUpload", function (e){
						document.getElementById('myVendorPreferenceTradingApplicationAddJsontable').value = "";
						var myvenTradingcoverageregion = $("#myvenTradingcoverageregion").val();
						var myvenTradingcoveragecountry = $("#myvenTradingcoveragecountry").val();
					    var myvenTradingyearoperation = $("#myvenTradingyearoperation").val();
					    var myvenTradingsupportregion = $("#myvenTradingsupportregion").val();
					    var myvenTradingsupporttime = $("#myvenTradingsupporttime").val();
					    var myvenTradingawards = $("#myvenTradingawards").val();
						document.getElementById("myvenTradingcoverageregion").value = myvenTradingcoverageregion;
						document.getElementById("myvenTradingcoveragecountry").value = myvenTradingcoveragecountry;
						document.getElementById("myvenTradingyearoperation").value = myvenTradingyearoperation;
						document.getElementById("myvenTradingsupportregion").value = myvenTradingsupportregion;
						document.getElementById("myvenTradingsupporttime").value = myvenTradingsupporttime;
						document.getElementById("myvenTradingawards").value = myvenTradingawards;
						
						$("#sample_1myVenPreferenceTradingApplication tbody").append('<tr><td>' + myvenTradingcoverageregion + '</td><td>' + myvenTradingcoveragecountry + '</td> <td>' + myvenTradingyearoperation + '</td>   <td>' + myvenTradingsupportregion + '</td>  <td>' + myvenTradingsupporttime + '</td><td>' + myvenTradingawards + '</td>   <td><a class="myVendorPreferenceTradingApplicationDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
						var table = $('#sample_1myVenPreferenceTradingApplication').tableToJSON();
						document.getElementById('myVendorPreferenceTradingApplicationAddJsontable').value = JSON.stringify(table);
					});
					 $(document).on("click", ".myVendorPreferenceTradingApplicationDelete", function (e) {
				    	var target = e.target;
						$(target).closest('tr').remove();
						var table = $('#sample_1myVenPreferenceTradingApplication').tableToJSON();
					    document.getElementById('myVendorPreferenceTradingApplicationAddJsontable').value = JSON.stringify(table);
					});
					 ///CSV File for Consumer My Vendor Preference Upload Code   -----> 2 tab (Trading Application Needs)
					 $(document).on("click", ".consumerfileupload", function (e) {
					        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
					        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
					            if (typeof (FileReader) != "undefined") {
					                var reader = new FileReader();
					                reader.onload = function (e) {
					                  var rows = e.target.result.split("\n");
					                    for (var i = 1; i < rows.length; i++) {
					                    	 if(!rows[i].split(",")[1].match('undefined')){
					                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
					                    	 }
					                    }
					                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
					                };
					                reader.readAsText($("#consumerfileUpload")[0].files[0]);
					            } else {
					                alert("This browser does not support HTML5.");
					            }
					        } else {
					            alert("Please upload a valid file.");
					        }
					    });
					 
	 
					// Consumer My Vendor Preference  -----> 3 tab (Analytics Application Needs)
						$(document).on("click", ".myVendorPreferAnalyticsApplicationUpload", function (e){
							document.getElementById('myVendorPreferenceAnalyticsAppliAddJsontable').value = "";
							
							var myvenAnalAppliccoverageregion = $("#myvenAnalAppliccoverageregion").val();
							var myvenAnalAppliccoveragecountry = $("#myvenAnalAppliccoveragecountry").val();
						    var myvenAnalApplicyearoperation = $("#myvenAnalApplicyearoperation").val();
						    var myvenAnalApplicsupportregion = $("#myvenAnalApplicsupportregion").val();
						    var myvenAnalApplicsupporttime = $("#myvenAnalApplicsupporttime").val();
						    var myvenAnalApplicawards = $("#myvenAnalApplicawards").val();
						    
							document.getElementById("myvenAnalAppliccoverageregion").value = myvenAnalAppliccoverageregion;
							document.getElementById("myvenAnalAppliccoveragecountry").value = myvenAnalAppliccoveragecountry;
							document.getElementById("myvenAnalApplicyearoperation").value = myvenAnalApplicyearoperation;
							document.getElementById("myvenAnalApplicsupportregion").value = myvenAnalApplicsupportregion;
							document.getElementById("myvenAnalApplicsupporttime").value = myvenAnalApplicsupporttime;
							document.getElementById("myvenAnalApplicawards").value = myvenAnalApplicawards;
							
							$("#sample_1myVenPreferenceAnalyticsApplication tbody").append('<tr><td>' + myvenAnalAppliccoverageregion + '</td><td>' + myvenAnalAppliccoveragecountry + '</td> <td>' + myvenAnalApplicyearoperation + '</td>   <td>' + myvenAnalApplicsupportregion + '</td>  <td>' + myvenAnalApplicsupporttime + '</td><td>' + myvenAnalApplicawards + '</td>   <td><a class="myVendorPreferenceAnalyticsApppliDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
							var table = $('#sample_1myVenPreferenceAnalyticsApplication').tableToJSON();
							document.getElementById('myVendorPreferenceAnalyticsAppliAddJsontable').value = JSON.stringify(table);
						});
						 $(document).on("click", ".myVendorPreferenceAnalyticsApppliDelete", function (e) {
					    	var target = e.target;
							$(target).closest('tr').remove();
							var table = $('#sample_1myVenPreferenceAnalyticsApplication').tableToJSON();
						    document.getElementById('myVendorPreferenceAnalyticsAppliAddJsontable').value = JSON.stringify(table);
						});
						 ///CSV File for Consumer My Vendor Preference Upload Code   -----> 3 tab (Analytics Application Needs)
						 $(document).on("click", ".consumerfileupload", function (e) {
						        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
						        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
						            if (typeof (FileReader) != "undefined") {
						                var reader = new FileReader();
						                reader.onload = function (e) {
						                  var rows = e.target.result.split("\n");
						                    for (var i = 1; i < rows.length; i++) {
						                    	 if(!rows[i].split(",")[1].match('undefined')){
						                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
						                    	 }
						                    }
						                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
						                };
						                reader.readAsText($("#consumerfileUpload")[0].files[0]);
						            } else {
						                alert("This browser does not support HTML5.");
						            }
						        } else {
						            alert("Please upload a valid file.");
						        }
						    });
						 
						// Consumer My Vendor Preference  -----> 4 tab (Research Report Needs)
							$(document).on("click", ".myVendorPreferResearchReportUpload", function (e){
								document.getElementById('myVendorPreferenceResearchReportAddJsontable').value = "";
								
								var myVendoResearchRepocoverageregion = $("#myVendoResearchRepocoverageregion").val();
								var myVendoResearchRepocoveragecountry = $("#myVendoResearchRepocoveragecountry").val();
							    var myVendoResearchRepoyearoperation = $("#myVendoResearchRepoyearoperation").val();
							    var myVendoResearchReposupportregion = $("#myVendoResearchReposupportregion").val();
							    var myVendoResearchReposupporttime = $("#myVendoResearchReposupporttime").val();
							    var myVendoResearchRepoawards = $("#myVendoResearchRepoawards").val();
							    
								document.getElementById("myVendoResearchRepocoverageregion").value = myVendoResearchRepocoverageregion;
								document.getElementById("myVendoResearchRepocoveragecountry").value = myVendoResearchRepocoveragecountry;
								document.getElementById("myVendoResearchRepoyearoperation").value = myVendoResearchRepoyearoperation;
								document.getElementById("myVendoResearchReposupportregion").value = myVendoResearchReposupportregion;
								document.getElementById("myVendoResearchReposupporttime").value = myVendoResearchReposupporttime;
								document.getElementById("myVendoResearchRepoawards").value = myVendoResearchRepoawards;
								
								$("#sample_1myVenPreferenceResearchReport tbody").append('<tr><td>' + myVendoResearchRepocoverageregion + '</td><td>' + myVendoResearchRepocoveragecountry + '</td> <td>' + myVendoResearchRepoyearoperation + '</td>   <td>' + myVendoResearchReposupportregion + '</td>  <td>' + myVendoResearchReposupporttime + '</td><td>' + myVendoResearchRepoawards + '</td>   <td><a class="myVendorPreferenceResearchReportDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
								var table = $('#sample_1myVenPreferenceResearchReport').tableToJSON();
								document.getElementById('myVendorPreferenceResearchReportAddJsontable').value = JSON.stringify(table);
							});
							 $(document).on("click", ".myVendorPreferenceResearchReportDelete", function (e) {
						    	var target = e.target;
								$(target).closest('tr').remove();
								var table = $('#sample_1myVenPreferenceResearchReport').tableToJSON();
							    document.getElementById('myVendorPreferenceResearchReportAddJsontable').value = JSON.stringify(table);
							});
							 ///CSV File for Consumer My Vendor Preference Upload Code   -----> 4 tab (Research Report Needs)
							 $(document).on("click", ".consumerfileupload", function (e) {
							        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
							        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
							            if (typeof (FileReader) != "undefined") {
							                var reader = new FileReader();
							                reader.onload = function (e) {
							                  var rows = e.target.result.split("\n");
							                    for (var i = 1; i < rows.length; i++) {
							                    	 if(!rows[i].split(",")[1].match('undefined')){
							                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
							                    	 }
							                    }
							                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
							                };
							                reader.readAsText($("#consumerfileUpload")[0].files[0]);
							            } else {
							                alert("This browser does not support HTML5.");
							            }
							        } else {
							            alert("Please upload a valid file.");
							        }
							    });
							 
							// Consumer Delivery & Cost Preference  -----> 1 tab (Market Data Needs)
								$(document).on("click", ".myVendorCostPreferMarkerDataUpload", function (e){
									document.getElementById('myVendorCostPreferenceMarketDataAddJsontable').value = "";
									var costpreferenceassetclass = $("#costpreferenceassetclass").val();
									var assetClassCostPreferenceSecurityDistriMaps = $("#assetClassCostPreferenceSecurityDistriMaps").val();
								    var costpreferencedeliverymethod = $("#costpreferencedeliverymethod").val();
								    var costpreferencefeedtype = $("#costpreferencefeedtype").val();
								    var costpreferencecotrange = $("#costpreferencecotrange").val();
								    
								    
									document.getElementById("costpreferenceassetclass").value = costpreferenceassetclass;
									document.getElementById("assetClassCostPreferenceSecurityDistriMaps").value = assetClassCostPreferenceSecurityDistriMaps;
									document.getElementById("costpreferencedeliverymethod").value = costpreferencedeliverymethod;
									document.getElementById("costpreferencefeedtype").value = costpreferencefeedtype;
									document.getElementById("costpreferencecotrange").value = costpreferencecotrange;
									
									$("#sample_1myVenCostPreferenceMarketData tbody").append('<tr><td>' + costpreferenceassetclass + '</td><td>' + assetClassCostPreferenceSecurityDistriMaps + '</td> <td>' + costpreferencedeliverymethod + '</td>   <td>' + costpreferencefeedtype + '</td>  <td>' + costpreferencecotrange + '</td>  <td><a class="myVendorCostPreferenceMarketDataDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
									var table = $('#sample_1myVenCostPreferenceMarketData').tableToJSON();
									document.getElementById('myVendorCostPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
								});
								 $(document).on("click", ".myVendorCostPreferenceMarketDataDelete", function (e) {
							    	var target = e.target;
									$(target).closest('tr').remove();
									var table = $('#sample_1myVenCostPreferenceMarketData').tableToJSON();
								    document.getElementById('myVendorCostPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
								});
								 ///CSV File for Consumer My Vendor Preference Upload Code   -----> 1 tab (Market Data Needs)
								 $(document).on("click", ".consumerfileupload", function (e) {
								        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
								        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
								            if (typeof (FileReader) != "undefined") {
								                var reader = new FileReader();
								                reader.onload = function (e) {
								                  var rows = e.target.result.split("\n");
								                    for (var i = 1; i < rows.length; i++) {
								                    	 if(!rows[i].split(",")[1].match('undefined')){
								                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
								                    	 }
								                    }
								                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
								                };
								                reader.readAsText($("#consumerfileUpload")[0].files[0]);
								            } else {
								                alert("This browser does not support HTML5.");
								            }
								        } else {
								            alert("Please upload a valid file.");
								        }
								    });
								 
								// Consumer Delivery & Cost Preference  -----> 2 tab (Trading Application Needs)
									$(document).on("click", ".myVendorPreferMarkerDataUpload", function (e){
										document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = "";
										var myvenprefercoverageregion = $("#myvenprefercoverageregion").val();
										var myvenprefercoveragecountry = $("#myvenprefercoveragecountry").val();
									    var myvenpreferyearoperation = $("#myvenpreferyearoperation").val();
									    var myvenprefersupportregion = $("#myvenprefersupportregion").val();
									    var myvenprefersupporttime = $("#myvenprefersupporttime").val();
									    var myvenpreferawards = $("#myvenpreferawards").val();
									    
									    
										document.getElementById("myvenprefercoverageregion").value = myvenprefercoverageregion;
										document.getElementById("myvenprefercoveragecountry").value = myvenprefercoveragecountry;
										document.getElementById("myvenpreferyearoperation").value = myvenpreferyearoperation;
										document.getElementById("myvenprefersupportregion").value = myvenprefersupportregion;
										document.getElementById("myvenprefersupporttime").value = myvenprefersupporttime;
										document.getElementById("myvenpreferawards").value = myvenpreferawards;
										
										$("#sample_1myVenPreferenceMarketData tbody").append('<tr><td>' + myvenprefercoverageregion + '</td><td>' + myvenprefercoveragecountry + '</td> <td>' + myvenpreferyearoperation + '</td>   <td>' + myvenprefersupportregion + '</td>  <td>' + myvenprefersupporttime + '</td><td>' + myvenpreferawards + '</td>   <td><a class="myVendorPreferenceMarketDataDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
										var table = $('#sample_1myVenPreferenceMarketData').tableToJSON();
										document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
									});
									 $(document).on("click", ".myVendorPreferenceMarketDataDelete", function (e) {
								    	var target = e.target;
										$(target).closest('tr').remove();
										var table = $('#sample_1myVenPreferenceMarketData').tableToJSON();
									    document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
									});
									 ///CSV File for Consumer Delivery & Cost Preference Upload Code   -----> 2 tab (Trading Application Needs)
									 $(document).on("click", ".consumerfileupload", function (e) {
									        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
									        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
									            if (typeof (FileReader) != "undefined") {
									                var reader = new FileReader();
									                reader.onload = function (e) {
									                  var rows = e.target.result.split("\n");
									                    for (var i = 1; i < rows.length; i++) {
									                    	 if(!rows[i].split(",")[1].match('undefined')){
									                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
									                    	 }
									                    }
									                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
									                };
									                reader.readAsText($("#consumerfileUpload")[0].files[0]);
									            } else {
									                alert("This browser does not support HTML5.");
									            }
									        } else {
									            alert("Please upload a valid file.");
									        }
									    });
									// Consumer Delivery & Cost Preference  -----> 3 tab (Analytics Application Needs)
										$(document).on("click", ".myVendorPreferMarkerDataUpload", function (e){
											document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = "";
											var myvenprefercoverageregion = $("#myvenprefercoverageregion").val();
											var myvenprefercoveragecountry = $("#myvenprefercoveragecountry").val();
										    var myvenpreferyearoperation = $("#myvenpreferyearoperation").val();
										    var myvenprefersupportregion = $("#myvenprefersupportregion").val();
										    var myvenprefersupporttime = $("#myvenprefersupporttime").val();
										    var myvenpreferawards = $("#myvenpreferawards").val();
										    
										    
											document.getElementById("myvenprefercoverageregion").value = myvenprefercoverageregion;
											document.getElementById("myvenprefercoveragecountry").value = myvenprefercoveragecountry;
											document.getElementById("myvenpreferyearoperation").value = myvenpreferyearoperation;
											document.getElementById("myvenprefersupportregion").value = myvenprefersupportregion;
											document.getElementById("myvenprefersupporttime").value = myvenprefersupporttime;
											document.getElementById("myvenpreferawards").value = myvenpreferawards;
											
											$("#sample_1myVenPreferenceMarketData tbody").append('<tr><td>' + myvenprefercoverageregion + '</td><td>' + myvenprefercoveragecountry + '</td> <td>' + myvenpreferyearoperation + '</td>   <td>' + myvenprefersupportregion + '</td>  <td>' + myvenprefersupporttime + '</td><td>' + myvenpreferawards + '</td>   <td><a class="myVendorPreferenceMarketDataDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
											var table = $('#sample_1myVenPreferenceMarketData').tableToJSON();
											document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
										});
										 $(document).on("click", ".myVendorPreferenceMarketDataDelete", function (e) {
									    	var target = e.target;
											$(target).closest('tr').remove();
											var table = $('#sample_1myVenPreferenceMarketData').tableToJSON();
										    document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
										});
										 ///CSV File for Consumer Delivery & Cost Preference Upload Code   -----> 3 tab (Analytics Application Needs)
										 $(document).on("click", ".consumerfileupload", function (e) {
										        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
										        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
										            if (typeof (FileReader) != "undefined") {
										                var reader = new FileReader();
										                reader.onload = function (e) {
										                  var rows = e.target.result.split("\n");
										                    for (var i = 1; i < rows.length; i++) {
										                    	 if(!rows[i].split(",")[1].match('undefined')){
										                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
										                    	 }
										                    }
										                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
										                };
										                reader.readAsText($("#consumerfileUpload")[0].files[0]);
										            } else {
										                alert("This browser does not support HTML5.");
										            }
										        } else {
										            alert("Please upload a valid file.");
										        }
										    });
										// Consumer Delivery & Cost Preference  -----> 4 tab (Research Report Needs)
											$(document).on("click", ".myVendorPreferMarkerDataUpload", function (e){
												document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = "";
												var myvenprefercoverageregion = $("#myvenprefercoverageregion").val();
												var myvenprefercoveragecountry = $("#myvenprefercoveragecountry").val();
											    var myvenpreferyearoperation = $("#myvenpreferyearoperation").val();
											    var myvenprefersupportregion = $("#myvenprefersupportregion").val();
											    var myvenprefersupporttime = $("#myvenprefersupporttime").val();
											    var myvenpreferawards = $("#myvenpreferawards").val();
											    
											    
												document.getElementById("myvenprefercoverageregion").value = myvenprefercoverageregion;
												document.getElementById("myvenprefercoveragecountry").value = myvenprefercoveragecountry;
												document.getElementById("myvenpreferyearoperation").value = myvenpreferyearoperation;
												document.getElementById("myvenprefersupportregion").value = myvenprefersupportregion;
												document.getElementById("myvenprefersupporttime").value = myvenprefersupporttime;
												document.getElementById("myvenpreferawards").value = myvenpreferawards;
												
												$("#sample_1myVenPreferenceMarketData tbody").append('<tr><td>' + myvenprefercoverageregion + '</td><td>' + myvenprefercoveragecountry + '</td> <td>' + myvenpreferyearoperation + '</td>   <td>' + myvenprefersupportregion + '</td>  <td>' + myvenprefersupporttime + '</td><td>' + myvenpreferawards + '</td>   <td><a class="myVendorPreferenceMarketDataDelete"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
												var table = $('#sample_1myVenPreferenceMarketData').tableToJSON();
												document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
											});
											 $(document).on("click", ".myVendorPreferenceMarketDataDelete", function (e) {
										    	var target = e.target;
												$(target).closest('tr').remove();
												var table = $('#sample_1myVenPreferenceMarketData').tableToJSON();
											    document.getElementById('myVendorPreferenceMarketDataAddJsontable').value = JSON.stringify(table);
											});
											 ///CSV File for Consumer Delivery & Cost Preference Upload Code   -----> 4 tab (Research Report Needs)
											 $(document).on("click", ".consumerfileupload", function (e) {
											        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
											        if (regex.test($("#consumerfileUpload").val().toLowerCase())) {
											            if (typeof (FileReader) != "undefined") {
											                var reader = new FileReader();
											                reader.onload = function (e) {
											                  var rows = e.target.result.split("\n");
											                    for (var i = 1; i < rows.length; i++) {
											                    	 if(!rows[i].split(",")[1].match('undefined')){
											                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td></tr>'); 
											                    	 }
											                    }
											                	document.getElementById('consumerjsontable1').value = JSON.stringify($('#sample_1').tableToJSON());  
											                };
											                reader.readAsText($("#consumerfileUpload")[0].files[0]);
											            } else {
											                alert("This browser does not support HTML5.");
											            }
											        } else {
											            alert("Please upload a valid file.");
											        }
											    });
											 
											 
											 
	 
	 // My vendor preference
	 $(document).on("click", ".consumermyvendorpreferenceaddtotable", function (e){
			document.getElementById('consumermyvendorpreferencejsontable1').value = "";
			var consumervendortype = $("#consumervendortype").val();
			var consumerregionincorp = $("#consumerregionincorp").val();
		    var consumercountryincorp = $("#consumercountryincorp").val();
		    var consumeryearoperation = $("#consumeryearoperation").val();
		    var consumersupportcoverageregion = $("#consumersupportcoverageregion").val();
		    var consumersupportcoveragetime = $("#consumersupportcoveragetime").val();
		    var consumerawards = $("#consumerawards").val();
		    
			document.getElementById("consumervendortype").value = consumervendortype;
			document.getElementById("consumerregionincorp").value = consumerregionincorp;
			document.getElementById("consumercountryincorp").value = consumercountryincorp;
			document.getElementById("consumeryearoperation").value = consumeryearoperation;
			document.getElementById("consumersupportcoverageregion").value = consumersupportcoverageregion;
			document.getElementById("consumersupportcoveragetime").value = consumersupportcoveragetime;
			document.getElementById("consumerawards").value = consumerawards;
			
			$("#myvendorpreferencesample_1 tbody").append('<tr><td>' + consumervendortype + '</td><td>' + consumerregionincorp + '</td> <td>' + consumercountryincorp + '</td>   <td>' + consumeryearoperation + '</td>  <td>' + consumersupportcoverageregion + '</td> <td>' + consumersupportcoveragetime + '</td> <td>' + consumerawards + '</td>  <td><a class="deleteButtonconsumerpreference"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
			var table = $('#myvendorpreferencesample_1').tableToJSON();
			document.getElementById('consumermyvendorpreferencejsontable1').value = JSON.stringify(table);
		});
	 
	///CSV File for Support Coverage Upload Code
	 $(document).on("click", ".consumermyvendorfileupload", function (e) {
	        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
	        if (regex.test($("#consumermyvendorfileUpload").val().toLowerCase())) {
	            if (typeof (FileReader) != "undefined") {
	                var reader = new FileReader();
	                reader.onload = function (e) {
	                    var rows = e.target.result.split("\n");
	                    for (var i = 1; i < rows.length; i++) {
	                    	if(!rows[i].split(",")[1].match('undefined')){
	                    		$("#myvendorpreferencesample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>   <td><a class="deleteButtonconsumerpreference"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
	                    	}
	                    }
	                	document.getElementById('consumermyvendorpreferencejsontable1').value = JSON.stringify($('#myvendorpreferencesample_1').tableToJSON()); 
	                };
	                reader.readAsText($("#consumermyvendorfileUpload")[0].files[0]);
	            } else {
	                alert("This browser does not support HTML5.");
	            }
	        } else {
	            alert("Please upload a valid file.");
	        }
	    });
	 
		 $(document).on("click", ".deleteButtonconsumerpreference", function (e) {
	    	var target = e.target;
			$(target).closest('tr').remove();
			var table = $('#myvendorpreferencesample_1').tableToJSON();
		    document.getElementById('consumermyvendorpreferencejsontable1').value = JSON.stringify(table);
		});
		 
		 
		 
		 // Data Distribution
		 $(document).on("click", ".addtotabledistribution", function (e){
				document.getElementById('jsontablesupport').value = "";
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
				
				$("#sampledistributionconsumer tbody").append('<tr><td>' + datadistassetclass + '</td><td>' + assetClassVendorSecurityDistriMaps + '</td> <td>' + filename + '</td>   <td>' + filebriefdesc + '</td>  <td>' + feedtype + '</td> <td>' + feedsubtype + '</td> <td>' + distributionmethod + '</td> <td>' + frequency + '</td>  <td>' + coverageregion + '</td>  <td>' + coveragecountry + '</td> <td>' + coverageexchange + '</td> <td><a class="deleteButtondistribution"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
				var table = $('#sampledistributionconsumer').tableToJSON();
				document.getElementById('jsontabledistribution').value = JSON.stringify(table);
			});
			 
			///CSV File for Data Distribution Upload Code
			 $(document).on("click", ".fileupmyofferdistribution", function (e) {
			        var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt|.xlsx)$/;
			        if (regex.test($("#fileUploadmyofferdistribution").val().toLowerCase())) {
			            if (typeof (FileReader) != "undefined") {
			                var reader = new FileReader();
			                reader.onload = function (e) {
			                	
			                    var rows = e.target.result.split("\n");
			                    for (var i = 1; i < rows.length; i++) {
			                    	if(!rows[i].split(",")[1].match('undefined')){
			                    	$("#sampledistributionconsumer tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td> <td>' + rows[i].split(",")[5] + '</td> <td>' + rows[i].split(",")[6] + '</td> <td>' + rows[i].split(",")[7] + '</td>  <td>' + rows[i].split(",")[8] + '</td> <td>' + rows[i].split(",")[9] + '</td> <td>' + rows[i].split(",")[10] + '</td> <td><a class="deleteButtondistribution"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
			                    	}
			                    }
			                	document.getElementById('jsontabledistribution').value = JSON.stringify($('#sampledistributionconsumer').tableToJSON());
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
			    document.getElementById('jsontabledistribution').value = JSON.stringify(table);
			});

			 /// checkboxes selection and deselect---:
			 $(".row-selectconsumerdashboard tr").each(function() {
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

function loadConsumerSecurityTypes(assettypeId) {
		if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
			assettypeId = encode64(assettypeId);
			$.ajax({
				type: 'GET',
				url:  "loadConsumerSecurityTypes?RAyuL="+assettypeId,
				cache:false,
				success : function(output){
					document.getElementById("assetClassConsumerSecurityMaps").innerHTML = output;		
				},
				error : function(data, textStatus, jqXHR){
					//alert('Error: '+data+':'+textStatus);
				}
			});
		}
	}
function loadConsumerDataDeliverySecurityTypes(assettypeId) {
	if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
		assettypeId = encode64(assettypeId);
		$.ajax({
			type: 'GET',
			url:  "loadConsumerDataDeliverySecurityTypes?RAyuL="+assettypeId,
			cache:false,
			success : function(output){
				document.getElementById("assetClassConsumerDataDeliverySecurityMaps").innerHTML = output;		
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

function loadCostPreferenceSecurityTypes(assettypeId) {
	if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
		assettypeId = encode64(assettypeId);
		$.ajax({
			type: 'GET',
			url:  "loadCostPreferenceSecurityTypes?RAyuL="+assettypeId,
			cache:false,
			success : function(output){
				document.getElementById("assetClassCostPreferenceSecurityDistriMaps").innerHTML = output;		
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

function openWindow() {
    var i, l, options = [{
       value: 'first',
       text: 'First'
    }, {
       value: 'second',
       text: 'Second'
    }],
    newWindow = window.open("", null, "height=600,width=900,status=yes,toolbar=yes,menubar=yes,location=yes");  

    newWindow.document.write("<select onchange='window.opener.setValue(this.value);'>");
    for(i=0,l=options.length; i<l; i++) {
        newWindow.document.write("<option value='"+options[i].value+"'>");  
        newWindow.document.write(options[i].text);  
        newWindow.document.write("</option>");
    }
    newWindow.document.write("</select>");
    newWindow.document.write("Vendor Fixed Income Questionnaire");
    
}

function setValue(value) {
    document.getElementById('value').value = value;
}

function consumerMyProfileActiveMode(tabmode){
	
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("companydetails")){
		document.getElementById('columnconsumer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		
		
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("mybusinessneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = '';
		
		document.getElementById('columnconsumer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		  
	}
	//My business needs sub tabs
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("marketdataneeds") && !tabmode.match("mypreferencemarketdataneeds") && !tabmode.match("costpreferencemarketdataneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = '';
		
		document.getElementById('columnconsumer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("tradingapplicationneeds") && !tabmode.match("mypreferencetradingapplicationneeds") && !tabmode.match("costpreferenceapplicationneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = '';
		
		document.getElementById('columnconsumer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '#5CE5E5';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("analyticsapplicationneeds") && !tabmode.match("mypreferenceanalyticsapplicationneeds") && !tabmode.match("costpreferenceanalyticsapplicationneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = '';
		
		document.getElementById('columnconsumer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("researchreportneeds") && !tabmode.match("mypreferenceresearchreportneeds")  && !tabmode.match("costpreferencesearchreportneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = '';
		
		document.getElementById('columnconsumer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("myvendorpreference")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		
		document.getElementById('columnconsumer3').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		  
	}
	// My Vendor Preference sub tabs---: 
	 else if(tabmode != '' && tabmode.length > 0 && tabmode.match("mypreferencemarketdataneeds")){
		
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		
		document.getElementById('columnconsumer3').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		  
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("mypreferencetradingapplicationneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		
		document.getElementById('columnconsumer3').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		  
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '#5CE5E5';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("mypreferenceanalyticsapplicationneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		
		document.getElementById('columnconsumer3').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("mypreferenceresearchreportneeds")){

		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		
		document.getElementById('columnconsumer3').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('columnconsumer4').style.backgroundColor = '';
		document.getElementById('headerconsumer4').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '#5CE5E5';
		  
	} 
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("datadeliverycostpreference")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference1').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermyvencostpreference1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagcostpreference1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmyvencostpreference2').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference3').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmyvencostpreference4').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference4').style.backgroundColor = '';
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("costpreferencemarketdataneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference1').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermyvencostpreference1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagcostpreference1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmyvencostpreference2').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference3').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmyvencostpreference4').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference4').style.backgroundColor = '';
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("costpreferenceapplicationneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference1').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmyvencostpreference2').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermyvencostpreference2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagcostpreference2').style.backgroundColor = '#5CE5E5';
		
		document.getElementById('columnmyvencostpreference3').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmyvencostpreference4').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference4').style.backgroundColor = '';
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("costpreferenceanalyticsapplicationneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference1').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmyvencostpreference2').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference3').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermyvencostpreference3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagcostpreference3').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmyvencostpreference4').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference4').style.backgroundColor = '';
		  
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("costpreferencesearchreportneeds")){
		document.getElementById('columnconsumer1').style.backgroundColor = '';
		document.getElementById('headerconsumer1').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer1').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer2').style.backgroundColor = '';
		document.getElementById('headerconsumer2').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer2').style.backgroundColor = '';
		
		document.getElementById('columnconsumer3').style.backgroundColor = '';
		document.getElementById('headerconsumer3').style.backgroundColor = '';  
		document.getElementById('anchortagconsumer3').style.backgroundColor = ''; 
		
		document.getElementById('columnconsumer4').style.backgroundColor = '#5CE5E5';
		document.getElementById('headerconsumer4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagconsumer4').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('columnmarketmybusiness1').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness2').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmybusiness3').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmybusiness4').style.backgroundColor = '';
		document.getElementById('headermarketmybusiness4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmybusiness4').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference1').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference2').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmarketmyvendorpreference3').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmarketmyvendorpreference4').style.backgroundColor = '';
		document.getElementById('headermarketmyvendorpreference4').style.backgroundColor = '';  
		document.getElementById('anchortagmarketmyvendorpreference4').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference1').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference1').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference1').style.backgroundColor = ''; 
		
		document.getElementById('columnmyvencostpreference2').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference2').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference2').style.backgroundColor = '';
		
		document.getElementById('columnmyvencostpreference3').style.backgroundColor = '';
		document.getElementById('headermyvencostpreference3').style.backgroundColor = '';  
		document.getElementById('anchortagcostpreference3').style.backgroundColor = ''; 
		
		document.getElementById('columnmyvencostpreference4').style.backgroundColor = '#5CE5E5';
		document.getElementById('headermyvencostpreference4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchortagcostpreference4').style.backgroundColor = '#5CE5E5';
		  
	}
	
}

/// Consumer My Offer tab mode changes
function activeCosumerModeMyOffer(tabmode){
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("consumermyoffermarketdataneeds")){
		document.getElementById('changeconsumermarketoffer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivconsumermarketoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchoconsumermarketoffer1').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeconsumermarketoffer2').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer2').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer2').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer3').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer3').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer3').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer4').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer4').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer4').style.backgroundColor = ''; 
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("consumermyoffertradingapplicationneeds")){
		document.getElementById('changeconsumermarketoffer1').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer1').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer1').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivconsumermarketoffer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchoconsumermarketoffer2').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeconsumermarketoffer3').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer3').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer3').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer4').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer4').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer4').style.backgroundColor = ''; 
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("consumermyofferanalyticsapplicationneeds")){
		document.getElementById('changeconsumermarketoffer1').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer1').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer1').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer2').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer2').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer2').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer3').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivconsumermarketoffer3').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchoconsumermarketoffer3').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeconsumermarketoffer4').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer4').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer4').style.backgroundColor = ''; 
	}
	else if(tabmode != '' && tabmode.length > 0 && tabmode.match("consumermyofferresearchreportneeds")){
		document.getElementById('changeconsumermarketoffer1').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer1').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer1').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer2').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer2').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer2').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer3').style.backgroundColor = '';
		document.getElementById('interdivconsumermarketoffer3').style.backgroundColor = '';  
		document.getElementById('anchoconsumermarketoffer3').style.backgroundColor = ''; 
		
		document.getElementById('changeconsumermarketoffer4').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivconsumermarketoffer4').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchoconsumermarketoffer4').style.backgroundColor = '#5CE5E5'; 		 
	}
}

 