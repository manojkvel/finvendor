jQuery(document).ready(function() {
	// Support Details 
	$(document).on("click", ".addtotable", function (e){
		debugger;
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
			
			var newRow = '<tr><td>' + assetclass + '</td><td>' + securitynames + '</td> <td>' + datacoverageregion + '</td>   <td>' + datacoveragecountry + '</td>  <td>' + datacoverageexchange + '</td>  <td>' + dataattribute + '</td>  <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
			var tableContain = $("#sample_1 tbody").value;
			 if (!(tableContain === undefined) && tableContain.indexOf(newRow) > -1 ){
				 
						alert("Duplicate Row ");
				
			 } else{
				 $("#sample_1 tbody").append(newRow);
				 var table = $('#sample_1').tableToJSON();
				 document.getElementById('jsontable1').value = JSON.stringify(table);
				 
			 }
		}else{
			document.getElementById("supportceoveragevalidationforaddmore").innerHTML = 'Please choose mandatory fields..!';
		}
		
		
	});
	 $(document).on("click", ".deleteButton", function (e) {
    	
		
		  var r = confirm("Are you sure want to delete?");
		    if (r == true) {
				 var target = e.target;
				 var trid = $(this).closest('tr').attr('id');
				 var trids = trid.split("_");
				 var url = "deleteRecord?recordId="+trids[0]+"&recordName="+trids[1];
					$.ajax({
				 		type: 'GET',
				 		url:  url,
				 		cache:false,
				 		success : function(response){
				 			$(target).closest('tr').remove();
				 			alert("Record deleted successfully.");
				 		},
				 		error : function(data, textStatus, jqXHR){
				 			//alert('Error: '+data+':'+textStatus);
				 		}
				 	});
				 	
				// var table = $('#sample_1').tableToJSON();
			    // document.getElementById('jsontable1').value = JSON.stringify(table);
		    } else {
		        
		    }
		 
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
	                    		 $("#sample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>  <td>' + rows[i].split(",")[5] + '</td>  <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>'); 
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
	 
	
		/*/	
		
		 $(document).on("click", ".awarddeleteButton", function (e) {
	    	var target = e.target;
			$(target).closest('tr').remove();
			var table = $('#awardsample_1').tableToJSON();
		    document.getElementById('awardjsontable1').value = JSON.stringify(table);
		});
		 */
		 ///CSV File for Support Details Upload Code
		 $(document).on("click", ".fileupload", function (e) {
			 debugger;
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
		                    		 $("#awardsample_1 tbody").append('<tr><td>' + rows[i].split(",")[0] + '</td><td>' + rows[i].split(",")[1] + '</td> <td>' + rows[i].split(",")[2] + '</td>   <td>' + rows[i].split(",")[3] + '</td>  <td>' + rows[i].split(",")[4] + '</td>     <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>'); 
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
		 debugger;
		 
		    var solutionDataCoverage = $("#solutionDataCoverage").val();
		    var offeringsDataCoverage = $("#offeringsDataCoverage").val();
			var supportcoverageregion = $("#supportcoverageregion").val();
			var supportcoveragecountry = $("#supportcoveragecountry :selected").text();
		    var vendorcostrange = $("#vendorcostrange").val();
		    var phonenumber = $("#phonenumber").val();
		    var email = $("#email").val();

		 if(checkMandotrySelectValue(solutionDataCoverage) && checkMandotrySelectValue(offeringsDataCoverage) && checkMandotrySelectValue(supportcoveragecountry) && checkMandotrySelectValue(vendorcostrange) && checkNullValue(phonenumber) && checkNullValue(email)){
			 
			$.ajax({
				type: 'GET',
				url:  "addVendorDataCoverage?solutionDataCoverage="+solutionDataCoverage+"&offeringsDataCoverage="+offeringsDataCoverage+"&supportcoverageregion="+supportcoverageregion+"&supportcoveragecountry="+supportcoveragecountry+"&vendorcostrange="+vendorcostrange+"&phonenumber="+phonenumber+"&email="+email,
				cache:false,
				success : function(response){
					if(checkNullValue(response[0].recordExist))
					{
						alert(response[0].recordExist);
					}else{
				     $("#dataCoverageTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_deleteVendorDataCoverage"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].region+'</td><td>'+response[i].country+'</td><td>'+response[i].cost+'</td><td>'+response[i].phonNo+'</td><td>'+response[i].email+'</td><td onclick="deleteRecordDataCoverage(\''+response[i].id+'\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
			        }
			        $("#dataCoverageTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
					}	
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
			
			}else{
				alert('Please choose mandatory fields..!');
			}
			
		});
	 
	 

	 
	 
	 $(document).on("click", "#tdsSubmit", function (e){
		 debugger;
		 
		    var solution = $("#tsdSolution").val();
		    var assetClass = $("#tdsAssetClass").val();
			var appName = $("#tdsAppName").val();
			var appDesc = $("#tdsAppDesc").val();
		    var tradableRegions = $("#tdsTradableRegions").val();
		    var tradableMarkets = $("#tdsTradableMarkets").val();
		    var accessibility = $("#tdsAccessibility").val();
		    var suitability = $("#tdsSuitability").val();
		    var offering = $("#tdsOffering").val();
			var costType = $("#tdsCostType").val();
			var platformCCY = $("#tdsPlatformCCY").val();
		    var platformCost = $("#tdsPlatformCost").val();
		    var platformType = $("#tdsPlatformType").val();
		    var exchangeFees = $("#tdsExchangeFees").val();
		    var streNews = $("#tdsStreNews").val();
		    var chartsAvai = $("#tdsChartsAvai").val();
			var orderType = $("#tdsOrderType").val();
			var priceAlerts = $("#tdsPriceAlerts").val();
		    var watchlist = $("#tdsWatchlist").val();
		    var offeringDesc = $("#tdsOfferingDesc").val();
		    var tradingCap = $("#tdsTradingCap").val();
		    var tradeExec = $("#tdsTradeExec").val();
		    var tradeType = $("#tdsTradeType").val();
			var darkVenues = $("#tdsDarkVenues").val();
			var addOns = $("#tdsAddOns").val();
		    var opeSystem = $("#tdsOpeSystem").val();
		    var launchedYear = $("#tdsLaunchedYear").val();
		    var errorMsg = $("#addTradingSoftwareDetailsErrorMsg").text();
			
			if(errorMsg.length == 0 && checkMandotrySelectValue(solution) && checkMandotrySelectValue(assetClass) && checkNullValue(offering)){
		    
			$.ajax({
				type: 'GET',
				url:  "addTradingSoftwareDetails?solution="+solution+"&assetClass="+assetClass+"&appName="+appName+"&appDesc="+appDesc+"&tradableRegions="+tradableRegions+
				"&tradableMarkets="+tradableMarkets+"&accessibility="+accessibility+"&suitability="+suitability+"&offering="+offering+"&costType="+costType
				+"&platformCCY="+platformCCY+"&platformCost="+platformCost+"&platformType="+platformType+"&exchangeFees="+exchangeFees+"&streNews="+streNews
				+"&chartsAvai="+chartsAvai+"&orderType="+orderType+"&priceAlerts="+priceAlerts+"&watchlist="+watchlist+"&offeringDesc="+offeringDesc
				+"&tradingCap="+tradingCap+"&tradeExec="+tradeExec+"&tradeType="+tradeType+"&darkVenues="+darkVenues+"&addOns="+addOns+"&opeSystem="+opeSystem+"&launchedYear="+launchedYear,
				cache:false,
				success : function(response){
					$("#tdsOffering").val("");
				     $("#tdsTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_addTradingSoftwareDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].assetClass+'</td>td>Details</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
			       	                                                                                                                           
			        }
			        $("#tdsTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
					
			}else{
				alert("Please enter mandotry field");
			}
			
		});
	 
	 
	 $(document).on("click", "#rcSubmit", function (e){
		 debugger;
		 
		    var solution = $("#rcSolution").val();
		    var regionsCovered = $("#rcRegionsCovered").val();
			var offering = $("#rcOffering").val();
			var totalResearchAnalyst = $("#rcTotalResearchAnalyst").val();
		    var researchPreparedbyCFA = $("#rcResearchPreparedbyCFA").val();
		    var existingClientBase = $("#rcExistingClientBase").val();
			if(checkMandotrySelectValue(solution) && checkMandotrySelectValue(offering)){			
			$.ajax({
				type: 'GET',
				url:  "addResearchCoverage?solution="+solution+"&regionsCovered="+regionsCovered+"&offering="+offering+"&totalResearchAnalyst="+totalResearchAnalyst+"&researchPreparedbyCFA="+researchPreparedbyCFA+
				"&existingClientBase="+existingClientBase,
				cache:false,
				success : function(response){

				     $("#rcTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_addResearchCoverage"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].regionsCovered+'</td><td>'+response[i].totalResearchAnalyst+'</td><td>'+response[i].researchPreparedbyCFA+'</td><td>'+response[i].existingClientBase+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
			       	                                                                                                                           
			        }
			        $("#rcTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
		
			}else{
				alert("Please enter mandoatory field")
			}
			
		});

	 $(document).on("click", "#rdSubmit", function (e){
		 debugger;
		var solution= $("#rdSolution").val();
		var offering= $("#rdOffering").val();
		var researchReportName = $("#rdResearchReportName").val();
		var researchReportDesc = $("#rdResearchReportDesc").val();
		var accessibility = $("#rdAccessibility").val();
		var suitability = $("#rdSuitability").val();
		var reportCostType = $("#rdReportCostType").val();
		var reportSubscriptionCCY = $("#rdReportSubscriptionCCY").val();
		var reportSubscriptionCost = $("#rdReportSubscriptionCost").val();
		var reportSubscriptionType = $("#rdReportSubscriptionType").val();
		var reportFormat = $("#rdReportFormat").val();
		var researchApplicableYear = $("#rdResearchApplicableYear").val();
		var researchApplicableMonth = $("#rdResearchApplicableMonth").val();
		var existingUserBase = $("#rdExistingUserBase").val();
	 if(checkMandotrySelectValue(solution) && checkMandotrySelectValue(offering) && checkMandotrySelectValue(accessibility) && checkMandotrySelectValue(reportCostType)){
		$.ajax({
				type: 'GET',
				url:  "addResearchDetails?solution="+solution+"&offering="+offering+"&researchReportName="+researchReportName+"&researchReportDesc="+researchReportDesc+"&accessibility="+accessibility+
				"&suitability="+suitability+"&reportCostType="+reportCostType+"&reportSubscriptionCCY="+reportSubscriptionCCY+"&reportSubscriptionCost="+reportSubscriptionCost+"&reportSubscriptionType="+reportSubscriptionType
				+"&reportFormat="+reportFormat+"&researchApplicableYear="+researchApplicableYear+"&researchApplicableMonth="+researchApplicableMonth+"&existingUserBase="+existingUserBase,
				cache:false,
				success : function(response){

				     $("#rdTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_addResearchDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].researchReportName+'</td><td>'+response[i].researchReportDesc+'</td><td>'+response[i].accessibility+'</td><td>'+
			       	 response[i].suitability+'</td><td>'+response[i].reportCostType+'</td><td>'+response[i].reportSubscriptionCCY+'</td><td>'+response[i].reportSubscriptionCost+'</td><td>'+
			       	 response[i].reportSubscriptionType+'</td><td>'+response[i].reportFormat+'</td><td>'+response[i].researchApplicableYear+'</td><td>'+response[i].researchApplicableMonth+'</td><td>'+response[i].existingUserBase+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
			       	                                                                                                                           
			        }
			        $("#rdTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
			
			}else{
				alert("Please choose mandatory fields");
			}
			
		});

	 $(document).on("click", "#rpSubmit", function (e){
		 debugger;
		 
		    var solution = $("#apSolution").val();
		    var offering = $("#apOffering").val();
			var researchArea = $("#apResearchArea").val();
			var researchSubArea = $("#apResearchSubArea").val();
		    var analystName = $("#apAnalystName").val();
		    var researchAnalystWithCFA = $("#apResearchAnalystWithCFA").val();
		    var offeringDesc = $("#apOfferingDesc").val();
		    var analystRegionofIncorp = $("#apAnalystRegionofIncorp").val();
		    var analystCountryofIncorp = $("#apAnalystCountryofIncorp").val();
			var analystYearofExp = $("#apAnalystYearofExp").val();
			var analystAwards = $("#apAnalystAwards").val();
		   
			$.ajax({
				type: 'GET',                                                                                                                                                                               
				url:  "addAnalystProfile?solution="+solution+"&offering="+offering+"&researchArea="+researchArea+"&researchSubArea="+researchSubArea+"&analystName="+analystName+"&researchAnalystWithCFA="+researchAnalystWithCFA+"&offeringDesc="+offeringDesc+"&analystRegionofIncorp="+analystRegionofIncorp+"&analystCountryofIncorp="+analystCountryofIncorp+"&analystYearofExp="+analystYearofExp+"&analystAwards="+analystAwards,
				cache:false,
				success : function(response){

				     $("#rpTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                   
			       	 tableRecord += '<tr><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].researchArea+'</td><td>'+response[i].researchSubArea+'</td><td>'+response[i].analystName
			       	 +'</td><td>'+response[i].researchAnalystWithCFA+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].analystRegionofIncorp+'</td><td>'+response[i].analystCountryofIncorp+'</td><td>'+response[i].analystYearofExp+'</td><td>'+response[i].analystAwards+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'analystProfile\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
			       	                                                                                                                           
			        }
			        $("#rpTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
			
			
			
			// if(checkMandotrySelectValue(supportcoverageregion) && checkMandotrySelectValue(supportcoveragecountry) && checkMandotrySelectValue(vendorsupporttime) && checkMandotrySelectValue(vendorcostrange) && checkNullValue(phonenumber) && checkNullValue(email)){
				// document.getElementById("dataCoverageErrorMsg").innerHTML = '';
			if(true){	
				/*	$("#dataCoverageTable tbody").append('<tr><td>' + supportcoverageregion + '</td><td>' + supportcoveragecountry + '</td> <td>' + vendorsupporttime + '</td> <td>' + vendorcostrange + '</td>   <td>' + phonenumber + '</td>  <td>' + email + '</td>  <td><a class="deleteButtonsupport"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
				var table = $('#samplesupport').tableToJSON();
				document.getElementById('jsontablesupport').value = JSON.stringify(table);*/
			}else{
				document.getElementById("dataCoverageErrorMsg").innerHTML = 'Please choose mandatory fields..!';
			}
			
		});

	 $(document).on("click", "#asdSubmit", function (e){
		 debugger;
		    var solution = $("#asdSolution").val();
		    var offering = $("#asdOffering").val();
			var offeringDesc = $("#asdOfferingDesc").val();
			var applicationName = $("#asdApplicationName").val();
		    var applicationBriefDesc = $("#asdApplicationBriefDesc").val();
		    var accessibility = $("#asdAccessibility").val();
		    var suitability = $("#asdSuitability").val();
		    var applicationCostType = $("#asdApplicationCostType").val();
		    var applicationSubscriptionCCY = $("#asdApplicationSubscriptionCCY").val();
			var applicationSubscriptionCost = $("#asdApplicationSubscriptionCost").val();
			var applicationSubscriptionType = $("#asdApplicationSubscriptionType").val();
		    var realtimeMarketData = $("#asdRealtimeMarketData").val();
		    var customizableCalculationModels = $("#asdCustomizableCalculationModels").val();
		    var addOns = $("#asdAddOns").val();
		    var operatingSystem = $("#asdOperatingSystem").val();
		    var softwareSpecifications = $("#asdSoftwareSpecifications").val();
			var launchedYear = $("#asdLaunchedYear").val();
			var existingUserBase = $("#asdExistingUserBase").val();
		  
			$.ajax({
				type: 'GET',
				url:  "addAnalyticsSoftwareDetails?solution="+solution+"&offering="+offering+"&offeringDesc="+offeringDesc+"&applicationName="+applicationName+"&applicationBriefDesc="+applicationBriefDesc+
				"&accessibility="+accessibility+"&suitability="+suitability+"&applicationCostType="+applicationCostType+"&applicationSubscriptionCCY="+applicationSubscriptionCCY+"&applicationSubscriptionCost="+applicationSubscriptionCost
				+"&applicationSubscriptionType="+applicationSubscriptionType+"&realtimeMarketData="+realtimeMarketData+"&customizableCalculationModels="+customizableCalculationModels+"&addOns="+addOns+"&operatingSystem="+operatingSystem
				+"&softwareSpecifications="+softwareSpecifications+"&launchedYear="+launchedYear+"&existingUserBase="+existingUserBase,
				cache:false,
				success : function(response){

				     $("#asdTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_addAnalyticsSoftwareDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].applicationName+'</td><td>'+
			       	 response[i].accessibility+'</td><td>'+response[i].applicationCostType+'</td><td>'+response[i].applicationSubscriptionCCY+'</td><td>'+response[i].applicationSubscriptionCost+'</td><td>'+
			       	 response[i].applicationSubscriptionType+'</td><td>'+response[i].realtimeMarketData+'</td><td>'+response[i].operatingSystem+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
			       	/* 
			       	 tableRecord += '<tr><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].applicationName+'</td><td>'+response[i].applicationBriefDesc+'</td><td>'+
			       	 response[i].accessibility+'</td><td>'+response[i].suitability+'</td><td>'+response[i].applicationCostType+'</td><td>'+response[i].applicationSubscriptionCCY+'</td><td>'+response[i].applicationSubscriptionCost+'</td><td>'+
			       	 response[i].applicationSubscriptionType+'</td><td>'+response[i].realtimeMarketData+'</td><td>'+response[i].customizableCalculationModels+'</td><td>'+response[i].addOns+'</td><td>'+response[i].operatingSystem+'</td><td>'+
			       	 response[i].softwareSpecifications+'</td><td>'+response[i].launchedYear+'</td><td>'+response[i].existingUserBase+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'analyticsSoftwareDetails\')"><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td>';
			       	 */
			       	 
			        }
			        $("#asdTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
			
			
			
			// if(checkMandotrySelectValue(supportcoverageregion) && checkMandotrySelectValue(supportcoveragecountry) && checkMandotrySelectValue(vendorsupporttime) && checkMandotrySelectValue(vendorcostrange) && checkNullValue(phonenumber) && checkNullValue(email)){
				// document.getElementById("dataCoverageErrorMsg").innerHTML = '';
			if(true){	
				/*	$("#dataCoverageTable tbody").append('<tr><td>' + supportcoverageregion + '</td><td>' + supportcoveragecountry + '</td> <td>' + vendorsupporttime + '</td> <td>' + vendorcostrange + '</td>   <td>' + phonenumber + '</td>  <td>' + email + '</td>  <td><a class="deleteButtonsupport"> <span class="lable_header_delete">Remove</span> </a></td></tr>');
				var table = $('#samplesupport').tableToJSON();
				document.getElementById('jsontablesupport').value = JSON.stringify(table);*/
			}else{
				document.getElementById("dataCoverageErrorMsg").innerHTML = 'Please choose mandatory fields..!';
			}
			
		});
	 
	 $(document).on("click", "#tcsSubmit", function (e){
		 debugger;
		    var solution = $("#tcsSolution").val();
		    var offering = $("#tcsOffering").val();
			var tradeCoverageRegion = $("#tcsTradeCoverageRegion").val();
			var tradeCoverageCountry = $("#tcsTradeCoverageCountry").val();
		    var tradingCapabilitiesType = $("#tcsTradingCapabilitiesType").val();
		    var tradeExecutionsType = $("#tcsTradeExecutionsType").val();
		    var algorithmicTradeType = $("#tcsAlgorithmicTradeType").val();
		    var darkpoolAccess = $("#tcsDarkpoolAccess").val();

			 if(checkMandotrySelectValue(solution) && checkMandotrySelectValue(offering) && checkMandotrySelectValue(tradeCoverageRegion) && checkMandotrySelectValue(tradeCoverageCountry)){
		    
			$.ajax({
				type: 'GET',
				url:  "addTradingCapabilitiesSupported?solution="+solution+"&offering="+offering+"&tradeCoverageRegion="+tradeCoverageRegion+"&tradeCoverageCountry="+tradeCoverageCountry+"&tradingCapabilitiesType="+tradingCapabilitiesType+
				"&tradeExecutionsType="+tradeExecutionsType+"&algorithmicTradeType="+algorithmicTradeType+"&darkpoolAccess"+darkpoolAccess,
				cache:false,
				success : function(response){
					if(checkNullValue(response[0].recordExist))
					{
						alert(response[0].recordExist);
					}else{
					
				     $("#tcsTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_VendorTradingSoftwareDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].tradeCoverageRegion+'</td><td>'+response[i].tradeCoverageCountry+'</td><td>'+response[i].tradingCapabilitiesType+'</td><td>'+response[i].tradeExecutionsType+'</td><td>'+response[i].algorithmicTradeType+'</td><td>'+response[i].darkpoolAccess+'</td><td> <img src="resources/images/delete.png"></a></td>';
			       	                                                                                                                           
			        }
			        $("#tcsTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
					}	
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
			
			}else{
				alert("Please enter mandatory field");
			}
			
		});
	 
	 
	 //CSV File for Support Coverage Upload Code
	 $(document).on("click", ".fileupmyoffercoverage", function (e) {
		 debugger;
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
	                    	if(!(rows[i].split(",")[1] === 'undefined')){
	                    		
	                    		var coverageRegion = rows[i].split(",")[0];
	                    		var coverageCountry = rows[i].split(",")[1];
	                    		var supportTimings = rows[i].split(",")[2];
	                    		var costRange = rows[i].split(",")[3];
	                    		var phoneNumber = rows[i].split(",")[4];
	                    		var email = rows[i].split(",")[5];
	                    		if(checkNullValue(coverageRegion) && checkNullValue(coverageCountry) && checkNullValue(supportTimings) && checkNullValue(costRange) && validatePhoneNoFromFile(phoneNumber) && checkNullValue(email)){
	                    		     $("#samplesupport tbody").append('<tr><td>' + coverageRegion + '</td><td>' + coverageCountry + '</td> <td>' + supportTimings + '</td>   <td>' + costRange + '</td>  <td>' + phoneNumber + '</td><td>' + email + '</td>   <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>');
	                    		}else{
	                    			alert("Mandotry field required at row number: "+i);
	                    			break;
	                    		}
	                    	}else{
	                    		break;
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
			 debugger;

			 
			   var solutionDataDistribution = $("#solutionDataDistribution").val();
				var offeringDataDistribution = $("#offeringDataDistribution").val();
			    var fileDataCoverage = $("#fileDataCoverage").val();
			    var feedtype = $("#feedtype").val();
			    var feedsubtype = $("#feedsubtype").val();
			    var distributionmethod = $("#distributionmethod").val();
			    var frequency = $("#frequency").val();
			    var coverageregion = $("#coverageregion").val();
			    var coveragecountry =  $("#coveragecountry :selected").text();
			    var coverageexchange = $("#coverageexchange").val();
			    
			    if(checkMandotrySelectValue(coverageexchange) && checkMandotrySelectValue(coveragecountry) && checkMandotrySelectValue(solutionDataDistribution) && checkMandotrySelectValue(offeringDataDistribution) && checkMandotrySelectValue(frequency) && checkMandotrySelectValue(distributionmethod) && checkMandotrySelectValue(fileDataCoverage) &&  checkMandotrySelectValue(feedsubtype) && checkMandotrySelectValue(feedtype)){
		         
				$.ajax({
					type: 'GET',
					url:  "addVendorDataDistribution?solution="+solutionDataDistribution+"&offering="+offeringDataDistribution+"&fileDataCoverage="+fileDataCoverage+"&feedtype="+feedtype+"&feedsubtype="+feedsubtype+"&distributionmethod="+distributionmethod+"&frequency="+frequency+"&coverageregion="+coverageregion+"&coveragecountry="+coveragecountry+"&coverageexchange="+coverageexchange,
					cache:false,
					success : function(response){
						if(checkNullValue(response[0].recordExist))
						{
							alert(response[0].recordExist);
						}else{
						    $("#dataDistributionTable tbody").empty();	 
					        var tableRecord = "";
					        for(i =0 ; i < response.length ; i++){                                                                                    
					       	 tableRecord += '<tr id="'+response[i].id+'_deleteVendorDataDistribution"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringFiles+'</td><td>'+response[i].feedType+'</td><td>'+response[i].feedSubType+'</td><td>'+response[i].distributionMethod+'</td><td>'+response[i].frequency+'</td><td>'+response[i].region+'</td><td>'+response[i].country+'</td><td>'+response[i].exchange+'</td><td> <img src="resources/images/delete.png"></a></td>';
					       	                                                                                                                           
					        }
					        $("#dataDistributionTable tbody").append(tableRecord);
							
							alert('You have updated sucessfully..!');
						}
					},
					error : function(errorMsg, textStatus, jqXHR){
						// alert('Error: '+errorMsg+':'+textStatus);
					}
				});
			    
				}else{
					alert("Please enter mandory fields");
				}
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
			                    	if(!(rows[i].split(",")[1] === 'undefined')){
			                    		
			                    		 var assetClass = rows[i].split(",")[0];
			                    		 var securitytype = rows[i].split(",")[1];
			                    		 var fileName = rows[i].split(",")[2];
			                    		 var fileBriefDesc = rows[i].split(",")[3];
			                    		 var feedType = rows[i].split(",")[4];
			                    		 var feedSubType = rows[i].split(",")[5];
			                    		 var distributionMethod = rows[i].split(",")[6];
			                    		 var frequency = rows[i].split(",")[7];
			                    		 var dataCoverageRegion = rows[i].split(",")[8];
			                    		 var dataCoverageCountry = rows[i].split(",")[9];
			                    		 var dataCoverageExchange = rows[i].split(",")[10];
			                    		if(checkNullValue(assetClass) && checkNullValue(securitytype) && checkNullValue(fileName) && checkNullValue(fileBriefDesc) && checkNullValue(feedType) && checkNullValue(feedSubType) && checkNullValue(distributionMethod) && checkNullValue(frequency) && checkNullValue(dataCoverageRegion) && checkNullValue(dataCoverageCountry) && checkNullValue(dataCoverageExchange)){
			                    		  $("#sampledistribution tbody").append('<tr><td>' +assetClass + '</td><td>' + securitytype + '</td> <td>' + fileName + '</td>   <td>' + fileBriefDesc + '</td>  <td>' + feedType + '</td> <td>' + feedSubType + '</td> <td>' + distributionMethod + '</td> <td>' + frequency + '</td>  <td>' + dataCoverageRegion + '</td> <td>' + dataCoverageCountry + '</td> <td>' + dataCoverageExchange + '</td> <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>');
			                    		}else{

				                    		alert("Mandotry field required at row number: "+i);
			                    			break;
			                    		}
			                    	}else{
			                    		break;
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
		 
			 
			 
			 
			// Data Distribution
			 $(document).on("click", ".tradingCapabilitiesSupported", function (e){
				 debugger;
				 
				 	document.getElementById('jsontablevendorofferingdistribution').value = "";
					var assetClass = $("#assetClassTcs").val();
					var subAssetClass = $("#subAssetClassTcs").val();
				    var tradecoverageRegion = $("#tradecoverageRegionTcs").val();
				    var tradeCoverageCountry = $("#tradeCoverageCountryTcs").val();
				    var tradeCoverageExchange = $("#tradeCoverageExchangeTcs").val();
				    var tradingCapabilitiesType = $("#tradingCapabilitiesTypeTcs").val();
				    var tradeExecutionsType = $("#tradeExecutionsTypeTcs").val();
				    var algorithmicTradeType = $("#algorithmicTradeTypeTcs").val();
				    var darkpoolAccess = $("#darkpoolAccessTcs").val();
				    
					document.getElementById("assetClassTcs").value = assetClass;
					document.getElementById("subAssetClassTcs").value = subAssetClass;
					document.getElementById("tradecoverageRegionTcs").value = tradecoverageRegion;
					document.getElementById("tradeCoverageCountryTcs").value = tradeCoverageCountry;
					document.getElementById("tradeCoverageExchangeTcs").value = tradeCoverageExchange;
					document.getElementById("tradingCapabilitiesTypeTcs").value = tradingCapabilitiesType;
					document.getElementById("tradeExecutionsTypeTcs").value = tradeExecutionsType;
					document.getElementById("algorithmicTradeTypeTcs").value = algorithmicTradeType;
					document.getElementById("darkpoolAccessTcs").value = darkpoolAccess;

					
					
					if(checkMandotrySelectValue(assetClass) && checkMandotrySelectValue(subAssetClass) && checkMandotrySelectValue(tradecoverageRegion) && checkMandotrySelectValue(algorithmicTradeType) && checkMandotrySelectValue(darkpoolAccess) ){
					
					document.getElementById("vendortab3tradingappErrorMsg").innerHTML = '';
						
						$("#samplesupport tbody").append('<tr><td>' + assetClass + '</td><td>' + subAssetClass + '</td> <td>' + tradecoverageRegion + '</td>   <td>' + tradeCoverageCountry + '</td> <td>' + tradeCoverageExchange + '</td>  <td>' + tradingCapabilitiesType + '</td> <td>' + tradeExecutionsType + '</td> <td>' + algorithmicTradeType + '</td> <td>' + darkpoolAccess + '</td> <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>');
						var table = $('#samplesupport').tableToJSON();
						document.getElementById('jsontablevendorofferingdistribution').value = JSON.stringify(table);
					
					}else{
						document.getElementById("vendortab3tradingappErrorMsg").innerHTML = 'Please choose mandatory fields..!';
					}
					
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
				                    	if(!(rows[i].split(",")[1] === 'undefined')){
				                    		
				                    		 var assetClass = rows[i].split(",")[0];
				                    		 var securitytype = rows[i].split(",")[1];
				                    		 var fileName = rows[i].split(",")[2];
				                    		 var fileBriefDesc = rows[i].split(",")[3];
				                    		 var feedType = rows[i].split(",")[4];
				                    		 var feedSubType = rows[i].split(",")[5];
				                    		 var distributionMethod = rows[i].split(",")[6];
				                    		 var frequency = rows[i].split(",")[7];
				                    		 var dataCoverageRegion = rows[i].split(",")[8];
				                    		 var dataCoverageCountry = rows[i].split(",")[9];
				                    		 var dataCoverageExchange = rows[i].split(",")[10];
				                    		if(checkNullValue(assetClass) && checkNullValue(securitytype) && checkNullValue(fileName) && checkNullValue(fileBriefDesc) && checkNullValue(feedType) && checkNullValue(feedSubType) && checkNullValue(distributionMethod) && checkNullValue(frequency) && checkNullValue(dataCoverageRegion) && checkNullValue(dataCoverageCountry) && checkNullValue(dataCoverageExchange)){
				                    		  $("#sampledistribution tbody").append('<tr><td>' +assetClass + '</td><td>' + securitytype + '</td> <td>' + fileName + '</td>   <td>' + fileBriefDesc + '</td>  <td>' + feedType + '</td> <td>' + feedSubType + '</td> <td>' + distributionMethod + '</td> <td>' + frequency + '</td>  <td>' + dataCoverageRegion + '</td> <td>' + dataCoverageCountry + '</td> <td>' + dataCoverageExchange + '</td> <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>');
				                    		}else{

					                    		alert("Mandotry field required at row number: "+i);
				                    			break;
				                    		}
				                    	}else{
				                    		break;
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

			 
			 
			 // Analytics Features supported
			 $(document).on("click", ".analyticsfeaturesSupportedAddMore", function (e){
				 debugger;
					document.getElementById('jsontablesupport').value = "";
					var analyticsSolutionsType = $("#analyticsSolutionsTypeTav").val();
					var analyticsSolutionsSubType = $("#analyticsSolutionsSubTypeTav").val();
				 
					document.getElementById("analyticsSolutionsTypeTav").value = analyticsSolutionsType;
					document.getElementById("analyticsSolutionsSubTypeTav").value = analyticsSolutionsSubType;
					
					if(checkMandotrySelectValue(analyticsSolutionsType) && checkMandotrySelectValue(analyticsSolutionsSubType)){
						document.getElementById("analyticsfeaturesSupportedErrorMsg").innerHTML = '';
						
						$("#analyticsfeaturesSupportedTable tbody").append('<tr><td>' + analyticsSolutionsType + '</td><td>' + analyticsSolutionsSubType + '</td> <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>');
						var table = $('#analyticsfeaturesSupportedTable').tableToJSON();
						document.getElementById('analyticsfeaturesSupportedTable').value = JSON.stringify(table);
					}else{
						document.getElementById("analyticsfeaturesSupportedErrorMsg").innerHTML = 'Please choose mandatory fields..!';
					}
					
				});
			 
			 //CSV File for Support Coverage Upload Code
			 $(document).on("click", ".fileupmyoffercoverage", function (e) {
				 debugger;
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
			                    	if(!(rows[i].split(",")[1] === 'undefined')){
			                    		
			                    		var coverageRegion = rows[i].split(",")[0];
			                    		var coverageCountry = rows[i].split(",")[1];
			                    		var supportTimings = rows[i].split(",")[2];
			                    		var costRange = rows[i].split(",")[3];
			                    		var phoneNumber = rows[i].split(",")[4];
			                    		var email = rows[i].split(",")[5];
			                    		if(checkNullValue(coverageRegion) && checkNullValue(coverageCountry) && checkNullValue(supportTimings) && checkNullValue(costRange) && validatePhoneNoFromFile(phoneNumber) && checkNullValue(email)){
			                    		     $("#samplesupport tbody").append('<tr><td>' + coverageRegion + '</td><td>' + coverageCountry + '</td> <td>' + supportTimings + '</td>   <td>' + costRange + '</td>  <td>' + phoneNumber + '</td><td>' + email + '</td>   <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>');
			                    		}else{
			                    			alert("Mandotry field required at row number: "+i);
			                    			break;
			                    		}
			                    	}else{
			                    		break;
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
			
			 
			 
				 // Analytics Features supported
				 $(document).on("click", ".researchCoverageAddMore", function (e){
					 debugger;
						document.getElementById('jsontablesupport').value = "";
						var researchArea = $("#researchAreaRc").val();
						var regionsCovered = $("#regionsCoveredRc").val();
						var researchSubArea = $("#researchSubAreaRc").val();
						var totalResearchAnalyst = $("#totalResearchAnalystRc").val();
						var existingClientBase = $("#existingClientBaseRc").val();
						var researchPreparedbyCfa = document.getElementById("researchPreparedbyCfaRc").checked;
				
						if(checkMandotrySelectValue(researchArea) && checkMandotrySelectValue(regionsCovered)&& checkMandotrySelectValue(researchSubArea) && checkMandotrySelectValue(totalResearchAnalyst) && checkMandotrySelectValue(existingClientBase)){
							document.getElementById("researchCoverageErrorMsg").innerHTML = '';
							
							$("#researchCoverageTable tbody").append('<tr><td>' + researchArea + '</td><td>' + regionsCovered + '</td><td>' + researchSubArea + '</td><td>' + totalResearchAnalyst + '</td><td>' + researchPreparedbyCfa + '</td><td>' + existingClientBase + '</td> <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>');
							var table = $('#researchCoverageTable').tableToJSON();
							document.getElementById('researchCoverageTable').value = JSON.stringify(table);
						}else{
							document.getElementById("researchCoverageErrorMsg").innerHTML = 'Please choose mandatory fields..!';
						}
						
					});
				 
				 //CSV File for Support Coverage Upload Code
				 $(document).on("click", ".fileupmyoffercoverage", function (e) {
					 debugger;
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
				                    	if(!(rows[i].split(",")[1] === 'undefined')){
				                    		
				                    		var coverageRegion = rows[i].split(",")[0];
				                    		var coverageCountry = rows[i].split(",")[1];
				                    		var supportTimings = rows[i].split(",")[2];
				                    		var costRange = rows[i].split(",")[3];
				                    		var phoneNumber = rows[i].split(",")[4];
				                    		var email = rows[i].split(",")[5];
				                    		if(checkNullValue(coverageRegion) && checkNullValue(coverageCountry) && checkNullValue(supportTimings) && checkNullValue(costRange) && validatePhoneNoFromFile(phoneNumber) && checkNullValue(email)){
				                    		     $("#samplesupport tbody").append('<tr><td>' + coverageRegion + '</td><td>' + coverageCountry + '</td> <td>' + supportTimings + '</td>   <td>' + costRange + '</td>  <td>' + phoneNumber + '</td><td>' + email + '</td>   <td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>');
				                    		}else{
				                    			alert("Mandotry field required at row number: "+i);
				                    			break;
				                    		}
				                    	}else{
				                    		break;
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
		
		document.getElementById('change4').style.backgroundColor = '';
		document.getElementById('interdiv4').style.backgroundColor = '';  
		document.getElementById('ancho4').style.backgroundColor = ''; 
		
		listVendorAward()
		
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
		
		document.getElementById('change2').style.backgroundColor = '';
		document.getElementById('interdiv2').style.backgroundColor = '';  
		document.getElementById('ancho2').style.backgroundColor = '';
		
		 
	}
}
/// My Data Aggregator Vendor tab mode changes
function activeModeVendorMyofferings(tabmode){
	
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("vendorMyOfferingsDataDictionary")){
		
		document.getElementById('changeoffer2').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivoffer2').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooffer2').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeoffer').style.backgroundColor = '';
		document.getElementById('interdivoffer').style.backgroundColor = '';  
		document.getElementById('anchooffer').style.backgroundColor = ''; 
		
		document.getElementById('changeoffer1').style.backgroundColor = '';
		document.getElementById('interdivoffer1').style.backgroundColor = '';  
		document.getElementById('anchooffer1').style.backgroundColor = ''; 

		$("#solutionDetailList").show();
		$("#createfileDiv").hide();
		$("#createOfferingDiv").hide();
		$("#createFieldsDiv").hide()
		
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("vendormyofferingsdatacoverage")){
		document.getElementById('changeoffer').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivoffer').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooffer').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changeoffer2').style.backgroundColor = '';
		document.getElementById('interdivoffer2').style.backgroundColor = '';  
		document.getElementById('anchooffer2').style.backgroundColor = '';
		
		document.getElementById('changeoffer1').style.backgroundColor = '';
		document.getElementById('interdivoffer1').style.backgroundColor = '';  
		document.getElementById('anchooffer1').style.backgroundColor = ''; 
		listDataCoverageDetails();
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("vendormyofferingsdatadistribution")){
		document.getElementById('changeoffer1').style.backgroundColor = '#5CE5E5';
		document.getElementById('interdivoffer1').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchooffer1').style.backgroundColor = '#5CE5E5';  
		
		document.getElementById('changeoffer').style.backgroundColor = '';
		document.getElementById('interdivoffer').style.backgroundColor = '';  
		document.getElementById('anchooffer').style.backgroundColor = '';
		
		document.getElementById('changeoffer2').style.backgroundColor = '';
		document.getElementById('interdivoffer2').style.backgroundColor = '';  
		document.getElementById('anchooffer2').style.backgroundColor = '';
		listDataDistribution();  
	}
}

/// My Trading Application vendor tab mode changes
function activeTradingVendorMyofferings(tabmode){
	debugger
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("tradingcapabilitiessupported")){
		document.getElementById('changetradingvendoroffer').style.backgroundColor = '#5CE5E5';
		document.getElementById('intertradingvendodivoffer').style.backgroundColor = '#5CE5E5';  
		document.getElementById('anchootradingvendoffer').style.backgroundColor = '#5CE5E5'; 
		
		document.getElementById('changetradingvendoroffer1').style.backgroundColor = '';
		document.getElementById('intertradingvendodivoffer1').style.backgroundColor = '';  
		document.getElementById('anchootradingvendoffer1').style.backgroundColor = ''; 
		listTradingCapabilitiesSupported();
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

function validateYear(year){
	if(!(year.value.match(/^\d{4}$/))){
		alert("Please enter valid year");
		year.value="";
	}
}

function imageValidation() {
	debugger;
	var logoPath = $("#personalvencompanylogo").val();
	var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpeg|.jpg|.png| .gif)$/;
	if(logoPath !='' && logoPath.length > 0 && regex.test($("#personalvencompanylogo").val().toLowerCase())){
		document.getElementById("invalidfileformat").innerHTML = '';
	}else if(logoPath !='' && logoPath.length > 0 && !regex.test($("#personalvencompanylogo").val().toLowerCase())){
		document.getElementById("invalidfileformat").innerHTML = 'Invalid file format..!';
		return;
	}
	
	  $('#displayLogo').html('');
	  var oMyForm = new FormData();
	  oMyForm.append("file", personalvencompanylogo.files[0]);
	  if(personalvencompanylogo.size> 2048){
		  alert("File size is more than 2MB ");
		  return;
	  }
	  $.ajax({
	    url: 'uploadFile',
	    data: oMyForm,
	    dataType: 'text',
	    processData: false,
	    contentType: false,
	    type: 'POST',
	    success: function(data){
	    //  $('#result').html(data+ " uploaded by FormData!");
	    
	    //  $('#displayLogo').html(data);

	    }
	  });
	
	
}

 
/// Update code to Vendor personal Info--:
function updateVendorPersonalInfo(){
	debugger;
	showLoader();
	var personalvenfirstname = $("#personalvenfirstname").val();
	var personalvenlastname = $("#personalvenlastname").val();
	var personalvendesignation = $("#personalvendesignation").val();
	var personalvencompany = $("#personalvencompany").val();
	var personalvencompanyurl = $("#personalvencompanyurl").val();
	var personalvencompanyinfo = $("#personalvencompanyinfo").val();
	var personalvenprimemail = $("#personalvenprimemail").val();
	var personalvensecemail = $("#personalvensecemail").val();
	var personalvenphonenumber = $("#personalvenphonenumbercode").val()+"-"+$("#personalvenphonenumber").val() ;
	
	var personalvenregionofincorp = $("#personalvenregionofincorp").val();
	var personalvencountryofincorp = $("#personalvencountryofincorp").val();
	var personalvencompanylogo = $("#personalvencompanylogo").val();
	
	var support = "";
	var selected = $("input[type='radio'][name='support']:checked");
	if (selected.length > 0) {
		support = selected.val();
	}

	var weekend = $('#weekend').is(":checked");
	var publicHolidays = $('#publicHolidays').is(":checked");
	
	if(personalvenfirstname != '' && personalvenfirstname.length > 0 &&
			personalvendesignation != null && personalvendesignation.length > 0 &&
			personalvencompany != null && personalvencompany.length > 0 &&
			personalvencompanyurl != null && personalvencompanyurl.length > 0 && 
			personalvencompanyinfo != null && personalvencompanyinfo.length > 0 &&
			personalvenprimemail != null && personalvenprimemail.length >0 &&
			personalvenphonenumber != null && personalvenphonenumber.length > 0){
		
		$.ajax({
			type: 'GET',
			url:  "updateVendorPersonalTabInfo?venFirstname="+personalvenfirstname+"&venLastname="+personalvenlastname+"&venDesignation="+personalvendesignation
			                                            +"&venCompany="+personalvencompany+"&venCompanyUrl="+personalvencompanyurl+"&venCompanyInfo="+personalvencompanyinfo
			                                            +"&venPrimEmail="+personalvenprimemail+"&venSecEmail="+personalvensecemail+"&venPhoneNum="+personalvenphonenumber
			                                            +"&venRegionOfIncorp="+personalvenregionofincorp+"&venCountryOfIncorp="+personalvencountryofincorp+"&venCompanyLogo="+personalvencompanylogo
			                                            +"&support="+support+"&weekend="+weekend+"&publicHolidays="+publicHolidays,
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
	debugger;
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

         
function updateVendorOfferingDataDistributionInfo(){
	debugger;
	var listOfDataCoveageInfo = $("#jsontablevendorofferingdistribution").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingDataDistributionInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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

/*
function updateVendorOfferingDataDistribusionInfo(){
	var listOfDataCoveageInfo = $("#jsontablesupport").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingDataDistribusionInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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
*/

function updateVendorOfferingTradingCapabilitiesSupportedInfo(){
	debugger;
	var listOfDataCoveageInfo = $("#jsontablevendorofferingdistribution").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingTradingCapabilitiesSupportedInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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
function updateVendorOfferingTradingSoftwareDetailsInfo(){
	var listOfDataCoveageInfo = $("#jsontablesupport").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingTradingSoftwareDetailsInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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
function updateVendorOfferingAnalyticsFeaturesSupportedInfo(){
	var listOfDataCoveageInfo = $("#jsontablesupport").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingAnalyticsFeaturesSupportedInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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
function updateVendorOfferingAnalyticsSoftwareDetailsInfo(){
	var listOfDataCoveageInfo = $("#jsontablesupport").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingAnalyticsSoftwareDetailsInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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
function updateVendorOfferingResearchCoverageInfo(){
	var listOfDataCoveageInfo = $("#jsontablesupport").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingResearchCoverageInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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
function updateVendorOfferingResearchDetailsInfo(){
	var listOfDataCoveageInfo = $("#jsontablesupport").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingResearchDetailsInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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
function updateVendorOfferingAnalystProfileInfo(){
	var listOfDataCoveageInfo = $("#jsontablesupport").val();
	if(listOfDataCoveageInfo != '' && listOfDataCoveageInfo.length > 0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorOfferingAnalystProfileInfo?dataCoverageInfo="+listOfDataCoveageInfo,
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


function getFileTreeList(){
	debugger;
	var solutionForVendorOffering = $("#solutionForVendorOffering").val();
	var offeringName = $("#offeringName").val();
	var descriptionForVendorOffering = $("#descriptionForVendorOffering").val();
	var assetClassForVenderOffering = $("#assetClassForVenderOffering").val();
	//if(checkMandotrySelectValue(solutionForVendorOffering) && checkMandotrySelectValue(assetClassForVenderOffering) && checkNullValue(offeringName) && checkNullValue(descriptionForVendorOffering)){
	var outputVar;
	if(true){
		$.ajax({
			type: 'GET',
			url:  "createTree?solution="+solutionForVendorOffering+"&offeringName="+offeringName+"&description="+descriptionForVendorOffering+"&assetClass="+assetClassForVenderOffering,
			cache:false,
			success : function(output){
				// document.getElementById("myofferingsdatacoveragetabsucessmessage").innerHTML = 'You have updated sucessfully..!';
				debugger;
				outputVar = output;
				alert('You have updated sucessfully..!');
			},
			error : function(errorMsg, textStatus, jqXHR){
				// alert('Error: '+errorMsg+':'+textStatus);
			}
		});
	}else{
		alert("Please Enter Mandotry value");
	}
	return outputVar; 
}



///Vendor My Offerings information updation ends here-----------------------------------------------------------------------------

var id_insert= 0;
 function loadUploadFiles(fileId){
	 debugger;
	var form = document.getElementById("selectedFiles");
    var file = fileId;
    var fileUp= file.split('\\');
    /*document.getElementById("selectedFiles").innerHTML = form.innerHTML +'<br>' +'<span class="lable_header_popupwindow">'+ fileUp[2] +'</span>' +'&nbsp;&nbsp;&nbsp;<a class="fileupload btnpopup btn-default">Upload</a>' +  '&nbsp;&nbsp;&nbsp;<a class="btnpopupremove btn-default" href="">Remove</a> '+ '<br>' ;*/
    document.getElementById("selectedFiles").innerHTML = form.innerHTML +'<div id="elements">'+ '<input type="checkbox" value="'+  id_insert    +'" id="'+  id_insert   +'" name="chkboxnames" class="lable_header_popupwindow">'+'&nbsp;&nbsp;&nbsp;&nbsp;' +' <span class="lable_header_popupwindow" id="'+id_insert+'" value="'+id_insert+'" name="spanfile">'+ fileUp[2] +'</span>' +'&nbsp;&nbsp;&nbsp;' + '<br> </div>'  ;
    file.value = "";
    id_insert=id_insert+1;
}

function loadCheckBoxes(){
	debugger;
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
/*function addField() {
	var divTxt = document.getElementById('filedetails').innerHTML;
	var txtBox = "&nbsp; <font color='black'> Label name: </font><input type='text' name='fname'><br>";
	document.getElementById('filedetails').innerHTML = divTxt + txtBox;
}
 */

function addVendorSolution(){
	debugger;
	var vendorSolutionTypes = $("#vendorSolutionTypes").val();
	var fieldDescription = $("#vendorSolutionSescription").val();
	var solutionName = $("#solutionName").val();
	var errorMsg = $("#signupSolutionErrorMsg").text();
	
	if( errorMsg.length == 0 && checkMandotrySelectValue(vendorSolutionTypes) && checkNullValue(fieldDescription) && checkNullValue(solutionName)){
		$.ajax({
			type: 'POST',
			url:  "addVendorSolution?vendorSolutionTypes="+vendorSolutionTypes+"&fieldDescription="+fieldDescription+"&solutionName="+solutionName,
			cache:false,
			success : function(response){
				$("#solutionName").val("");
				$("#vendorSolutionTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		       	 tableRecord += '<tr><td>'+response[i].name+'</td><td>'+response[i].solutionType+'</td><td>'+response[i].description+'</td>  <td onclick="deleteVendorSolution(\''+response[i].id+'\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
		       	                                                                                                                           
		        }
		        $("#vendorSolutionTable tbody").append(tableRecord);
		        alert("Record is added successfully");
			},
			error : function(errorMsg, textStatus, jqXHR){
				 alert('Error: '+errorMsg+':'+textStatus);
			}
		});
	}else{
		alert("Please Enter Mandotry value");
	}
}

function listVendorSolution(){
		$.ajax({
			type: 'POST',
			url:  "listVendorSolution",
			cache:false,
			success : function(response){
				debugger;
		      
		       $("#vendorSolutionTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		       	 tableRecord += '<tr><td>'+response[i].name+'</td><td>'+response[i].solutionType+'</td><td>'+response[i].description+'</td>  <td onclick="deleteVendorSolution(\''+response[i].id+'\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
		       	                                                                                                                           
		        }
		        $("#vendorSolutionTable tbody").append(tableRecord);
			},
			error : function(errorMsg, textStatus, jqXHR){
				 alert('Error: '+errorMsg+':'+textStatus);
			}
		});
}


function deleteVendorSolution(objectVar){
		debugger;
	    $.ajax({
	        url: "deleteVendorSolution?objectVar="+objectVar,
	        type: "POST",

	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(response) {
	        
	        	$("#vendorSolutionTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		       	 tableRecord += '<tr><td>'+response[i].name+'</td><td>'+response[i].solutionType+'</td><td>'+response[i].description+'</td>  <td onclick="deleteVendorSolution(\''+response[i].id+'\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
		       	                                                                                                                           
		        }
		        $("#vendorSolutionTable tbody").append(tableRecord);	
	        	
	        alert("Record is deleted successfully");
	      
	        },
	        error: function(err){
	       	 alert("This solution is associated with offerings. Please delete offering first ");
	        }
	    });
	}

function displayOfferingFile(objectVar){
	$("#createfileDiv").show();
     $("#createOfferingDiv").hide();
     $("#createFieldsDiv").hide();
     $("#solutionDetailList").hide();
     $("#selectedId").val(objectVar);
     var URLVar = "listOfferingData";
     debugger
         	   
     $.ajax({
         url: "listOfferingData?objectVar="+objectVar,
         type: "POST",

         beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
         },
         success: function(response) {
        	 debugger;
        $("#offeringFilesTable tbody").empty();
         var tableRecord = "";
         for(i =0 ; i < response.length ; i++){                                                                                    
        	 tableRecord += '<tr id="'+response[i].id+'_deleteRecordFile"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].securityType+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
        	                                                                                                                           
         }
         $("#offeringFilesTable tbody").append(tableRecord);
         },
         error: function(err){
        	 alert("error msg: "+err);
         }
     });
 }

function displayFileFields(objectVar){
	
    $("#createfileDiv").hide();
    $("#createOfferingDiv").hide();
    $("#createFieldsDiv").show();
    $("#selectedId").val(objectVar);
    $("#solutionDetailList").hide();
    $.ajax({
        url: "listOfferingFieldData?objectVar="+objectVar,
        type: "POST",

        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(response) {
       	$("#offeringFilesFieldTable tbody").empty();	 
        var tableRecord = "";
        for(i =0 ; i < response.length ; i++){                                                                                    
       	 tableRecord += '<tr id="'+response[i].id+'_deleteFieldsFile"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].fieldIndex+'</td><td>'+response[i].fieldMaxLength+'</td><td>'+response[i].fieldFormat+'</td><td>'+response[i].fieldDataType+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
       	                                                                                                                        
        }
        $("#offeringFilesFieldTable tbody").append(tableRecord);
        },
        error: function(err){
       	 alert("error msg: "+err);
        }
    });
    
    
}
         
function addVendorOfferingInfo(){
	debugger;
	var solutionForVendorOffering = $("#selectedId").val();
	var offeringName = $("#offeringName").val();
	var descriptionForVendorOffering = $("#descriptionForVendorOffering").val();
	var assetClassForVenderOffering = $("#assetClassForVenderOffering").val();
	var launchedYear = $("#launchedYear").val();
	if(checkNullValue(offeringName)){
		$.ajax({
			type: 'GET',
			url:  "createOfferings?solution="+solutionForVendorOffering+"&offeringName="+offeringName+"&description="+descriptionForVendorOffering+"&assetClass="+assetClassForVenderOffering+"&launchedYear="+launchedYear,
			cache:false,
			success : function(response){
				
			     $("#offeringFilesTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		       	 tableRecord += '<tr><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].securityType+'</td><td>'+response[i].launchedYear+'</td><td onclick="deleleRecord(\''+response[i].id+'\',\'offeringFile\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
		       	                                                                                                                           
		        }
		        $("#vendorofferingTable tbody").append(tableRecord);

				alert('You have updated sucessfully..!');
			},
			error : function(errorMsg, textStatus, jqXHR){
				 alert('Error: '+errorMsg+':'+textStatus);
			}
		});
	}else{
		alert("Please Enter Mandotry value");
	}
	 
}

function createOfferingFile(){
	debugger;
	var fileName = $("#fileName").val();
	var description = $("#description").val();
	var securityType = $("#securityType").val();
	var selectedId = $("#selectedId").val();
	var outputVar;
	if(checkNullValue(fileName)){
		$.ajax({
			type: 'GET',
			url:  "addOfferingFiles?selectedId="+selectedId+"&fileName="+fileName+"&description="+description+"&securityType="+securityType,
			cache:false,
			success : function(response){
				 $("#offeringFilesTable tbody").empty();
		         var tableRecord = "";
		         for(i =0 ; i < response.length ; i++){                                                                                    
		        	 tableRecord += '<tr id="'+response[i].id+'_deleteRecordFile"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].securityType+'</td><td> <img src="resources/images/delete.png"></a></td>';
		        	                                                                                                                           
		         }
		         $("#offeringFilesTable tbody").append(tableRecord);
				alert('You have updated sucessfully..!');
			},
			error : function(errorMsg, textStatus, jqXHR){
				// alert('Error: '+errorMsg+':'+textStatus);
			}
		});
	}else{
		alert("Please Enter Mandotry value");
	}
	return outputVar; 
}

function addFileFields(){
	debugger;
	var fieldName = $("#fieldName").val();
	var fieldDescription = $("#fieldDescription").val();
	var fieldIndex = $("#fieldIndex").val();
	var fieldMaxLength = $("#fieldMaxLength").val();
	var fieldFormat = $("#fieldFormat").val();
	var fieldDataType = $("#fieldDataType").val();
	
	var selectedId = $("#selectedId").val();
	if(checkNullValue(fieldName)){
	var outputVar;
		$.ajax({
			type: 'GET',
			url:  "addFieldsToFile?selectedId="+selectedId+"&fieldName="+fieldName+"&description="+fieldDescription+"&fieldIndex="+fieldIndex+"&fieldMaxLength="+fieldMaxLength+"&fieldFormat="+fieldFormat+"&fieldDataType="+fieldDataType,
			cache:false,
			success : function(response){
				
                debugger;
		       	$("#offeringFilesFieldTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		       	 tableRecord += '<tr id="'+response[i].id+'_deleteFieldsFile"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].fieldIndex+'</td><td>'+response[i].fieldMaxLength+'</td><td>'+response[i].fieldFormat+'</td><td>'+response[i].fieldDataType+'</td><td> <img src="resources/images/delete.png"></a></td>';
		       	                                                                                                                        
		        }
		        $("#offeringFilesFieldTable tbody").append(tableRecord);
		        
				
				alert('You have updated sucessfully..!');
			},
			error : function(errorMsg, textStatus, jqXHR){
				// alert('Error: '+errorMsg+':'+textStatus);
			}
		});
	}else{
		alert("Please Enter Mandotry value");
	}
	return outputVar; 
}
function createOfferings(objectVar){
//	  $('#container').jstree();
	  $("#createfileDiv").hide();
	  $("#createOfferingDiv").show();
	  $("#createFieldsDiv").hide();
	  $("#solutionDetailList").hide();
	  $("#selectedId").val(objectVar);
	  
	  $.ajax({
			type: 'GET',
			url:  "createOfferings?solution="+objectVar,
			cache:false,
			success : function(response){
				
			     $("#offeringFilesTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		        	tableRecord += '<tr id="'+response[i].id+'_deleteRecordOffering"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].securityType+'</td><td>'+response[i].launchedYear+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';                                                                                                                          
		        }
		        $("#vendorofferingTable tbody").append(tableRecord);

			},
			error : function(errorMsg, textStatus, jqXHR){
				 alert('Error: '+errorMsg+':'+textStatus);
			}
		});
	  
}

function onChangeSolution(){
	debugger;
	var objectVar = $('#solutionDataCoverage option:selected').val();
	
	$.ajax({
		type: 'GET',
		url:  "createOfferings?solution="+objectVar,
		cache:false,
		success : function(response){
			userInfo = "<option value ='-SELECT-' class='selectvalues'> -SELECT- </option>";
			for(i =0 ; i < response.length ; i++){ 
    		  	userInfo += "<option value='" + response[i].id+"'>"+ response[i].name+"</option>";
    	  	}
    		$('#offeringsDataCoverage').html(userInfo);
		    

		},
		error : function(errorMsg, textStatus, jqXHR){
			 alert('Error: '+errorMsg+':'+textStatus);
		}
	});
}




function vendorTypeSolutions(vendorType){
	debugger;
	var result = "";
	$.ajax({
		type: 'GET',
		async: false,
		url:  "vendorSpecificSolutionList?vendorProvider="+vendorType,
		cache:false,
		success : function(response){
			solutionList = "<option value ='-SELECT-' class='selectvalues'> -SELECT- </option>";
			for(i =0 ; i < response.length ; i++){ 
				solutionList += "<option value='" + response[i].id+"'>"+ response[i].name+"</option>";
    	  	}
			result =  solutionList;
			
			
		},
		error : function(errorMsg, textStatus, jqXHR){
			 alert('Solution is not Created');
		}
	});
	return result;
}



function onSolutionDataDistribution(){
	debugger;
	var objectVar = $('#solutionDataDistribution option:selected').val();
	
	$.ajax({
		type: 'GET',
		url:  "createOfferings?solution="+objectVar,
		cache:false,
		success : function(response){
			userInfo = "<option value ='-SELECT-' class='selectvalues'> -SELECT- </option>";
			for(i =0 ; i < response.length ; i++){ 
    		  	userInfo += "<option value='" + response[i].id+"'>"+ response[i].name+"</option>";
    	  	}
    		$('#offeringDataDistribution').html(userInfo);
		    

		},
		error : function(errorMsg, textStatus, jqXHR){
			 // alert('Error: '+errorMsg+':'+textStatus);
		}
	});
}

function onVendorOfferingChange()
{
	debugger;
	var objectVar = $('#offeringDataDistribution option:selected').val();
	
	 $.ajax({
         url: "listOfferingData?objectVar="+objectVar,
         type: "POST",

         beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
         },
         success: function(response) {
        var userInfo = "<option value ='-SELECT-' class='selectvalues'> -SELECT- </option>";
			for(i =0 ; i < response.length ; i++){ 
 		  	userInfo += "<option value='" + response[i].id+"'>"+ response[i].name+"</option>";
			}
				$('#fileDataCoverage').html(userInfo);
		 
         },
         error: function(err){
        	 alert("error msg: "+err);
         }
     });

}




function listDataCoverageDetails(){

$.ajax({
	type: 'GET',
	url:  "addVendorDataCoverage",
	cache:false,
	success : function(response){
	     $("#dataCoverageTable tbody").empty();	 
        var tableRecord = "";
        for(i =0 ; i < response.length ; i++){                                                                                    
       	 tableRecord += '<tr id="'+response[i].id+'_deleteVendorDataCoverage"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].region+'</td><td>'+response[i].country+'</td><td>'+response[i].cost+'</td><td>'+response[i].phonNo+'</td><td>'+response[i].email+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
       	                                                                                                                           
        }
        $("#dataCoverageTable tbody").append(tableRecord);

	},
	error : function(errorMsg, textStatus, jqXHR){
		// alert('Error: '+errorMsg+':'+textStatus);
	}
});

}

function listDataDistribution(){
	 
	$.ajax({
		type: 'GET',
		url:  "addVendorDataDistribution",
		cache:false,
		success : function(response){
		     $("#dataDistributionTable tbody").empty();	 
	        var tableRecord = "";
	        for(i =0 ; i < response.length ; i++){                                                                                    
	       	 tableRecord += '<tr id="'+response[i].id+'_deleteVendorDataDistribution"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringFiles+'</td><td>'+response[i].feedType+'</td><td>'+response[i].feedSubType+'</td><td>'+response[i].distributionMethod+'</td><td>'+response[i].frequency+'</td><td>'+response[i].region+'</td><td>'+response[i].country+'</td><td>'+response[i].exchange+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
	       	                                                                                                                           
	        }
	        $("#dataDistributionTable tbody").append(tableRecord);

		},
		error : function(errorMsg, textStatus, jqXHR){
			// alert('Error: '+errorMsg+':'+textStatus);
		}
	});
 
}

function listTradingSoftwareDetails(){
	debugger;
		$.ajax({
			type: 'GET',
			url:  "addTradingSoftwareDetails",
			cache:false,
			success : function(response){

			     $("#tdsTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		       	 tableRecord += '<tr><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].assetClass+'</td>td>Details</td><td onclick="deleteRecord(\''+response[i].id+'\',\'tradingSoftwareDetails\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
		       	                                                                                                                           
		        }
		        $("#tdsTable tbody").append(tableRecord);

			},
			error : function(errorMsg, textStatus, jqXHR){
				// alert('Error: '+errorMsg+':'+textStatus);
			}
		});
		
}    


function tradingCapabilitiesSupportedOffering(){
	var solutionId = $('#tcsSolution option:selected').val();
	
	$.ajax({
		type: 'GET',
		url:  "tradingCapabilitiesSupportedOffering?solutionId="+solutionId,
		cache:false,
		success : function(response){

	        var offerings = "<option value ='-SELECT-' class='selectvalues'> -SELECT- </option>";
				for(i =0 ; i < response.length ; i++){ 
					offerings += "<option value='" + response[i].name+"'>"+ response[i].name+"</option>";
				}
					$('#tcsOffering').html(offerings);
							
		    },
		error : function(errorMsg, textStatus, jqXHR){
			// alert('Error: '+errorMsg+':'+textStatus);
		}
	});
}

function researchReportingVendorOffering(solutionId,offeringId){
	var solutionId = $('#'+solutionId+' option:selected').val();
	
	$.ajax({
		type: 'GET',
		url:  "researchReportingVendorOffering?solutionId="+solutionId,
		cache:false,
		success : function(response){

	        var offerings = "";
				for(i =0 ; i < response.length ; i++){ 
					offerings += "<option value='" + response[i].name+"'>"+ response[i].name+"</option>";
				}
					$('#'+offeringId+'').html(offerings);
							
		    },
		error : function(errorMsg, textStatus, jqXHR){
			// alert('Error: '+errorMsg+':'+textStatus);
		}
	});
}


function listTradingCapabilitiesSupported(){
$.ajax({
	type: 'GET',
	url:  "addTradingCapabilitiesSupported",
	cache:false,
	success : function(response){

	     $("#tcsTable tbody").empty();	 
        var tableRecord = "";
        for(i =0 ; i < response.length ; i++){                                                                                    
       	 // tableRecord += '<tr><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].tradeCoverageRegion+'</td><td>'+response[i].tradeCoverageCountry+'</td><td>'+response[i].tradingCapabilitiesType+'</td><td>'+response[i].tradeExecutionsType+'</td><td>'+response[i].algorithmicTradeType+'</td><td>'+response[i].darkpoolAccess+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'tradingCapabilitiesSupported\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
     	 tableRecord += '<tr id="'+response[i].id+'_VendorTradingSoftwareDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].tradeCoverageRegion+'</td><td>'+response[i].tradeCoverageCountry+'</td><td>'+response[i].tradingCapabilitiesType+'</td><td>'+response[i].tradeExecutionsType+'</td><td>'+response[i].algorithmicTradeType+'</td><td>'+response[i].darkpoolAccess+'</td><td> <img src="resources/images/delete.png"></a></td></tr>';                                                                                                                         
        }
        $("#tcsTable tbody").append(tableRecord);

	},
	error : function(errorMsg, textStatus, jqXHR){
		// alert('Error: '+errorMsg+':'+textStatus);
	}
});
}

function listAnalyticsSoftwareDetails(){
debugger;
 
	$.ajax({
		type: 'GET',
		url:  "addAnalyticsSoftwareDetails",
		cache:false,
		success : function(response){

		     $("#asdTable tbody").empty();	 
	        var tableRecord = "";
	        for(i =0 ; i < response.length ; i++){                                                                                    
	       	 tableRecord += '<tr><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].applicationName+'</td><td>'+
	       	 response[i].accessibility+'</td><td>'+response[i].applicationCostType+'</td><td>'+response[i].applicationSubscriptionCCY+'</td><td>'+response[i].applicationSubscriptionCost+'</td><td>'+
	       	 response[i].applicationSubscriptionType+'</td><td>'+response[i].realtimeMarketData+'</td><td>'+response[i].operatingSystem+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'analyticsSoftwareDetails\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
	       	/* 
	       	 tableRecord += '<tr><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].applicationName+'</td><td>'+response[i].applicationBriefDesc+'</td><td>'+
	       	 response[i].accessibility+'</td><td>'+response[i].suitability+'</td><td>'+response[i].applicationCostType+'</td><td>'+response[i].applicationSubscriptionCCY+'</td><td>'+response[i].applicationSubscriptionCost+'</td><td>'+
	       	 response[i].applicationSubscriptionType+'</td><td>'+response[i].realtimeMarketData+'</td><td>'+response[i].customizableCalculationModels+'</td><td>'+response[i].addOns+'</td><td>'+response[i].operatingSystem+'</td><td>'+
	       	 response[i].softwareSpecifications+'</td><td>'+response[i].launchedYear+'</td><td>'+response[i].existingUserBase+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'analyticsSoftwareDetails\')"><a class="deleteButton"> <span class="lable_header_delete">Remove</span> </a></td>';
	       	 */
	       	 
	        }
	        $("#asdTable tbody").append(tableRecord);

		},
		error : function(errorMsg, textStatus, jqXHR){
			// alert('Error: '+errorMsg+':'+textStatus);
		}
	});

}

function listAnalystProfile(){   
	$.ajax({
		type: 'GET',                                                                                                                                                                               
		url:  "addAnalystProfile",
		cache:false,
		success : function(response){

		     $("#rpTable tbody").empty();	 
	        var tableRecord = "";
	        for(i =0 ; i < response.length ; i++){                                                                                   
	       	 tableRecord += '<tr><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].researchArea+'</td><td>'+response[i].researchSubArea+'</td><td>'+response[i].analystName
	       	 +'</td><td>'+response[i].researchAnalystWithCFA+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].analystRegionofIncorp+'</td><td>'+response[i].analystCountryofIncorp+'</td><td>'+response[i].analystYearofExp+'</td><td>'+response[i].analystAwards+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'analystProfile\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
	       	                                                                                                                           
	        }
	        $("#rpTable tbody").append(tableRecord);

		},
		error : function(errorMsg, textStatus, jqXHR){
			// alert('Error: '+errorMsg+':'+textStatus);
		}
	});

}

function getRegion(countryId,regionId){
	debugger;
	
	var countryId = $('#'+countryId+' option:selected').val();
	$.ajax({
		type: 'GET',                                                                                                                                                                               
		url:  "getRegion?country="+countryId,
		cache:false,
		success : function(response){
			$('#'+regionId+'').val(response);
		},
		error : function(errorMsg, textStatus, jqXHR){
			// alert('Error: '+errorMsg+':'+textStatus);
		}
	});
}


function changeTabMode(comp){
	 debugger; 
	 comppnentId="";
	 try {
		 comppnentId = comp.id;
		}catch(err) {
			comppnentId="";
		}
	 if(window.location.href.indexOf("vendormyprofile") != -1){
		 $("#myProfile").attr("class", "active");
		 $("#solution").attr("class", "#");
		 $("#myOfferings").attr("class", "#");
		 $("#myStats").attr("class", "#");
		 $("#myBlog").attr("class", "#");
	 }else if(comppnentId === "myStats"){
		 $("#myStats").attr("class", "active");
		 $("#solution").attr("class", "#");
		 $("#myOfferings").attr("class", "#");
		 $("#myProfile").attr("class", "#");
		 $("#myBlog").attr("class", "#");
	 }else if(comppnentId === "myBlog"){
		 $("#myBlog").attr("class", "active");
		 $("#solution").attr("class", "#");
		 $("#myOfferings").attr("class", "#");
		 $("#myStats").attr("class", "#");
		 $("#myProfile").attr("class", "#");
	 }else if(window.location.href.indexOf("vendormyofferings") != -1){
		 $("#myOfferings").attr("class", "active");
		 $("#solution").attr("class", "#");
		 $("#myProfile").attr("class", "#");
		 $("#myStats").attr("class", "#");
		 $("#myBlog").attr("class", "#");
	 }else if(window.location.href.indexOf("vendorsolutions") != -1){
		 $("#solution").attr("class", "active");
		 $("#myProfile").attr("class", "#");
		 $("#myOfferings").attr("class", "#");
		 $("#myStats").attr("class", "#");
		 $("#myBlog").attr("class", "#");
	 }
}


//Award Details 
function addVendorAward(){
	debugger;
	
    var awardname = $("#awardname").val();
    var awardsponsor = $("#awardsponsor").val();
    var awardedyear = $("#awardedyear").val();
    var errorMsg = $("#awardnameErrorMsg").text();
    
    if(errorMsg.length == 0 && awardname != null && awardname.length >0	&& awardsponsor != null && awardsponsor.length > 0 && awardedyear != null && awardedyear.length >0){
		$.ajax({
			type: 'GET',
			url:  "updateVendorAwardDetails?awardname="+awardname+"&awardsponsor="+awardsponsor+"&awardedyear="+awardedyear,
			cache:false,
			success : function(response){
				alert('You have updated sucessfully..!');
				var awardname = $("#awardname").val("");
				   $("#awardsample_1 tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 	tableRecord += '<tr id="'+response[i].id+'_awarddetails"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].frequency+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'vendorAward\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a> </a></td>';
			        }
			        $("#awardsample_1 tbody").append(tableRecord);
				   document.getElementById("awardtabsucessmessage").innerHTML = 'You have updated sucessfully..!';		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}else{
		alert("Please enter mandatory fields");
	}
	
}


function listVendorAward(){
	debugger;
    
    	$.ajax({
			type: 'GET',
			url:  "updateVendorAwardDetails",
			cache:false,
			success : function(response){
				   $("#awardsample_1 tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 	tableRecord += '<tr id="'+response[i].id+'_awarddetails"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].frequency+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
			        }
			        $("#awardsample_1 tbody").append(tableRecord);
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
	

$("#tdsSuitability").change(function() {
			 if ($("#tdsSuitability option[value=Other]:selected").length > 0){
				 document.getElementById("tdsSuitabilityOther").style.visibility = "visible";
			 }else{
				 document.getElementById("tdsSuitabilityOther").style.visibility = "hidden";
			 }
	});


$("#asdSuitability").change(function() {
	 if ($("#asdSuitability option[value=Others]:selected").length > 0){
		 document.getElementById("asdSuitabilityOthers").style.visibility = "visible";
	 }else{
		 document.getElementById("asdSuitabilityOthers").style.visibility = "hidden";
	 }
});



$("#tdsCostType").change(function() {
	 if ($("#tdsCostType option[value='Subscription based']:selected").length > 0){
		 document.getElementById("tdsPlatformCost").readOnly  = false;
		 document.getElementById("tdsPlatformType").readOnly  = false;
	 }else{
		 document.getElementById("tdsPlatformCost").readOnly  = true;
		 document.getElementById("tdsPlatformType").readOnly  = true;
	 }
});

$("#asdApplicationCostType").change(function() {
	 if ($("#asdApplicationCostType option[value='Subscription based']:selected").length > 0){
		 document.getElementById("asdApplicationSubscriptionCost").readOnly = false;
		 document.getElementById("asdApplicationSubscriptionAnnum").readOnly = false;
	 }else{
		 document.getElementById("asdApplicationSubscriptionCost").readOnly = true;
		 document.getElementById("asdApplicationSubscriptionAnnum").readOnly = true;
	 }
});

$("#rdReportCostType").change(function() {
	 if ($("#rdReportCostType option[value='Subscription based']:selected").length > 0){
		 document.getElementById("rdReportSubscriptionCost").readOnly = false;
		 document.getElementById("rdReportSubscriptionCostAnnum").readOnly  = false;
	 }else{
		 document.getElementById("rdReportSubscriptionCost").readOnly =true;
		 document.getElementById("rdReportSubscriptionCostAnnum").readOnly =true;
	 }
});


$("#tcsTradeExecutionsType").change(function() {
	 if ($("#tcsTradeExecutionsType option[value='Algorithmic & Program Trading']:selected").length > 0){
		 document.getElementById("tcsAlgorithmicTradeType").disabled = false;
	 }else{
		 document.getElementById("tcsAlgorithmicTradeType").disabled = true;
	 }
});

$("#tcsDarkpoolAccess").change(function() {
	 if ($("#tcsDarkpoolAccess option[value='No Darkpool Access']:selected").length > 0){
		 document.getElementById("tcsSupportedDarkpoolVenues").disabled = false;
	 }else{
		 document.getElementById("tcsSupportedDarkpoolVenues").disabled = true;
	 }
});


$("#apAnalystAwards").change(function() {
	 if ($("#apAnalystAwards option[value=Other]:selected").length > 0){
		 document.getElementById("apAnalystAwardsOthers").style.visibility = "visible";
	 }else{
		 document.getElementById("apAnalystAwardsOthers").style.visibility = "hidden";
	 }
});

$("#rdSuitability").change(function() {
	 if ($("#rdSuitability option[value=Others]:selected").length > 0){
		 document.getElementById("rdSuitabilityOthers").style.visibility = "visible";
	 }else{
		 document.getElementById("rdSuitabilityOthers").style.visibility = "hidden";
	 }
});
