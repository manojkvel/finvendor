jQuery(document).ready(function() {
 
	$("#vendor_profile #tab1 #top-card #edit-details").on("click", function() {
		progressLoader(false);
		$("#personal_details").slideDown();
		$("#top-card").slideUp();
		getCountryRegionMapping('personalvencountryofincorp', 'personalvenregionofincorp');
	});

	$("#vendor_profile #tab1 .save").on("click", function() {
		if(!validateSpanElements('personal_details')) {
			progressLoader(false);
			return false;
		}
		if(!updateVendorPersonalInfo()) {
			progressLoader(false);
			return false;
		}

		$("#personal_details").slideUp();
		$("#top-card").slideDown();
	});

	$("#vendor_profile .nav-tabs .awards_details").on("click", function() {
		listVendorAward();
	});

	$("#vendor_profile #tab1 .next").on("click", function() {
		activeMode('awarddetails');
		$("#personal_details").slideUp();
		$("#top-card").slideDown();
	});


	$("#vendor_profile #tab3 #awards_top_card #edit-details").on("click", function() {
		openVendorAwardForm();
	});

	$("#vendor_profile #tab3 #award_details .save").on("click", function() {
		if(!validateSpanElements('award_details')) {
			progressLoader(false);
			return false;
		}
		if(!addVendorAward()) {
			progressLoader(false);
			return false;
		}
	});

	$("#myofferings1, #myofferings2, #myofferings3, #myofferings4").on("click", function() {
		activeModeVendorMyofferings(this.hash);
	});

	$("#myofferings1").on("click", function() {
		listDataAggregatorOffering();
	});

	$("#data_aggregator_top_card .add_more").on("click", function() {
		openDataAggregratorForm();
	});

	$("#data_aggregator .product_info h3").on("click", function() {
		$("#data_aggregator .product_info ul").slideToggle();
		$("#data_aggregator .product_info h3 span").toggleClass("fa-chevron-up");
		$("#data_aggregator .product_info h3 span").toggleClass("fa-chevron-down");
	});

	$("#data_aggregator .data_coverage_info h3").on("click", function() {
		$("#data_aggregator .data_coverage_info ul").slideToggle();
		$("#data_aggregator .data_coverage_info h3 span").toggleClass("fa-chevron-up");
		$("#data_aggregator .data_coverage_info h3 span").toggleClass("fa-chevron-down");
	});

	$("#data_aggregator .data_distribution_info h3").on("click", function() {
		$("#data_aggregator .data_distribution_info ul").slideToggle();
		$("#data_aggregator .data_distribution_info h3 span").toggleClass("fa-chevron-up");
	});

	$("#data_aggregator .save").on("click", function() {
		var id = $('#data_aggregator #productId').val();
		if(!addDataAggregatorOffering(id)) {
			progressLoader(false);
			return false;
		}
	});

	$("#data_aggregator .next").on("click", function() {
		resetDataAggregratorForm();
	});

	$("#trading_application .product_info h3").on("click", function() {
		$("#trading_application .product_info ul").slideToggle();
		$("#trading_application .product_info h3 span").toggleClass("fa-chevron-up");
		$("#trading_application .product_info h3 span").toggleClass("fa-chevron-down");
	});

	$("#trading_application .software_details_info h3").on("click", function() {
		$("#trading_application .software_details_info ul").slideToggle();
		$("#trading_application .software_details_info h3 span").toggleClass("fa-chevron-up");
		$("#trading_application .software_details_info h3 span").toggleClass("fa-chevron-down");
	});

	$("#trading_application .trading_capability_info h3").on("click", function() {
		$("#trading_application .trading_capability_info ul").slideToggle();
		$("#trading_application .trading_capability_info h3 span").toggleClass("fa-chevron-up");
	});



	$("#myofferings2").on("click", function() {
		listTradingApplicationsOffering();
	});

	$("#trading_application_top_card .add_more").on("click", function() {
		openTradingApplicationForm();
	});

	$("#trading_application .save").on("click", function() {
		var id = $('#trading_application #productId').val();
		if(!addTradingApplicationsOffering(id)) {
			progressLoader(false);
			return false;
		}
	});

	$("#trading_application .next").on("click", function() {
		resetTradingApplicationForm();
	});


	$("#analytic_application .product_info h3").on("click", function() {
		$("#analytic_application .product_info ul").slideToggle();
		$("#analytic_application .product_info h3 span").toggleClass("fa-chevron-up");
		$("#analytic_application .product_info h3 span").toggleClass("fa-chevron-down");
	});

	$("#analytic_application .software_details_info h3").on("click", function() {
		$("#analytic_application .software_details_info ul").slideToggle();
		$("#analytic_application .software_details_info h3 span").toggleClass("fa-chevron-up");
		$("#analytic_application .software_details_info h3 span").toggleClass("fa-chevron-down");
	});


	$("#myofferings3").on("click", function() {
		listAnalyticsApplicationsOffering();
	});

	$("#analytic_application_top_card .add_more").on("click", function() {
		openAnalyticApplicationForm();
	});

	$("#analytic_application .save").on("click", function() {
		var id = $('#analytic_application #productId').val();
		if(!addAnalyticApplicationsOffering(id)) {
			progressLoader(false);
			return false;
		}
	});

	$("#analytic_application .next").on("click", function() {
		resetAnalyticApplicationForm();
	});




	$("#research_application .product_info h3").on("click", function() {
		$("#research_application .product_info ul").slideToggle();
		$("#research_application .product_info h3 span").toggleClass("fa-chevron-up");
		$("#research_application .product_info h3 span").toggleClass("fa-chevron-down");
	});

	$("#research_application .coverage_info h3").on("click", function() {
		$("#research_application .coverage_info ul").slideToggle();
		$("#research_application .coverage_info h3 span").toggleClass("fa-chevron-up");
		$("#research_application .coverage_info h3 span").toggleClass("fa-chevron-down");
	});

	$("#research_application .research_details h3").on("click", function() {
		$("#research_application .research_details ul").slideToggle();
		$("#research_application .research_details h3 span").toggleClass("fa-chevron-up");
		$("#research_application .research_details h3 span").toggleClass("fa-chevron-down");
	});

	$("#research_application .analyst_profile_info h3").on("click", function() {
		$("#research_application .analyst_profile_info ul").slideToggle();
		$("#research_application .analyst_profile_info h3 span").toggleClass("fa-chevron-up");
		$("#research_application .analyst_profile_info h3 span").toggleClass("fa-chevron-down");
	});


	$("#myofferings4").on("click", function() {
		listResearchReportsOffering();
	});

	$("#research_application_top_card .add_more").on("click", function() {
		openResearchApplicationForm();
	});

	$("#research_application .save").on("click", function() {
		var id = $('#research_application #productId').val();
		if(!addResearchReportsOffering(id)) {
			progressLoader(false);
			return false;
		}
	});

	$("#research_application .next").on("click", function() {
		resetResearchApplicationForm();
	});



	$("#award_details #awardedyear").on('blur', function() {
		var year = $("#award_details #awardedyear").val().trim();
		validateYear(year);
	});


	$(document).on("click", "#awards_top_card .award-list .delete_btn", function (e) {
    	
		
		  var r = confirm("Are you sure want to delete?");
		    if (r == true) {
				 var target = e.target;
				 var trid = $(this).closest('.award-list').attr('id');
				 var trids = trid.split("_");
				 var url = "deleteRecord?recordId="+trids[0]+"&recordName="+trids[1];
					$.ajax({
				 		type: 'GET',
				 		url:  url,
				 		cache:false,
				 		success : function(response){
				 			$(target).closest('.award-list').remove();
				 			alert("Record deleted successfully.");
				 		},
				 		error : function(data, textStatus, jqXHR){
				 			//alert('Error: '+data+':'+textStatus);
				 		}
				 	});
		    } else {
		        
		    }
		 
	});

	var isEdit = false;

	$(document).on("click", "#data_aggregator_top_card .data_aggregator_list .edit_btn", function (e) {
			var id = $(this).closest('.data_aggregator_list').attr('id');
			isEdit = true;
			fetchDataAggregatorOffering(id);		 
	});

	$(document).on("click", "#data_aggregator_top_card .data_aggregator_list .delete_btn", function (e) {
    	
		
		var r = confirm("Are you sure want to delete?");
		if (r == true) {
			var id = $(this).closest('.data_aggregator_list').attr('id');
			deleteDataAggregatorOffering(id);

		}		 
	});

	$("#data_aggregator #launchedYear").on('blur', function() {
		var year = $("#data_aggregator #launchedYear").val().trim();
		validateYear(year);
	});

	$("select[name=assetClassId]").on('change', function() {
		getAssetClassSecurityTypeMapping('assetClassId', 'securityTypes');
	});

	$("select[name=coverageRegion]").on('change', function() {
		getRegionCountryMapping('coverageRegion', 'coverageCountry');
		getCountryExchangeMapping('coverageCountry', 'coverageExchange');
	});

	$("select[name=coverageCountry]").on('change', function() {
		getCountryExchangeMapping('coverageCountry', 'coverageExchange');
	});

	$(document).on("click", "#trading_application_top_card .trading_application_list .edit_btn", function (e) {
			var id = $(this).closest('.trading_application_list').attr('id');
			isEdit = true;
			fetchTradingApplicationsOffering(id);		 
	});

	$(document).on("click", "#trading_application_top_card .trading_application_list .delete_btn", function (e) {
    	
		
		var r = confirm("Are you sure want to delete?");
		if (r == true) {
			var id = $(this).closest('.trading_application_list').attr('id');
			deleteTradingApplicationsOffering(id);

		}		 
	});

	/// list Trading Application offering:
	listTradeApplicationOffering = function() {
		progressLoader(false);
		activeModeVendorMyofferings("#tab2");
	}

	$(document).on("click", "#analytic_application_top_card .analytic_application_list .edit_btn", function (e) {
			var id = $(this).closest('.analytic_application_list').attr('id');
			isEdit = true;
			fetchAnalyticsApplicationsOffering(id);		 
	});

	$(document).on("click", "#analytic_application_top_card .analytic_application_list .delete_btn", function (e) {
    	
		
		var r = confirm("Are you sure want to delete?");
		if (r == true) {
			var id = $(this).closest('.analytic_application_list').attr('id');
			deleteAnalyticsApplicationsOffering(id);

		}		 
	});

	/// list Annalytics Application offering:
	listAnalyticsApplicationOffering = function() {
		progressLoader(false);
		activeModeVendorMyofferings("#tab3");
	}

	var isEdit = false;

	$(document).on("click", "#research_application_top_card .research_application_list .edit_btn", function (e) {
			var id = $(this).closest('.research_application_list').attr('id');
			isEdit = true;
			fetchResearchReportsOffering(id);		 
	});

	$(document).on("click", "#research_application_top_card .research_application_list .delete_btn", function (e) {
    	
		
		var r = confirm("Are you sure want to delete?");
		if (r == true) {
			var id = $(this).closest('.research_application_list').attr('id');
			deleteResearchReportsOffering(id);

		}		 
	});

	/// list Research Report Providers offering:
	listResearchReportProviderOffering = function() {
		progressLoader(false);
		activeModeVendorMyofferings("#tab4");
	}

	openDataAggregratorForm = function() {
		resetDataAggregratorForm();
		$("#data_aggregator").slideDown();
		$("#data_aggregator_top_card").slideUp();
 
		if(!isEdit) {
			getAssetClassMapping('assetClassId');
			getRegionMapping('coverageRegion');
			getAssetClassSecurityTypeMapping('assetClassId', 'securityTypes');
			getRegionCountryMapping('coverageRegion', 'coverageCountry');
			getCountryExchangeMapping('coverageCountry', 'coverageExchange');
		}
	}

	closeDataAggregratorForm = function() {
		resetDataAggregratorForm();
		$("#data_aggregator").slideUp();
		$("#data_aggregator_top_card").slideDown();
	}

	resetDataAggregratorForm = function() {
		$("#data_aggregator .alert").hide();
		$("#data_aggregator_form").trigger('reset');
		$('.selectpicker').selectpicker('refresh');
		$('select[name=securityTypes]').empty();
		$('select[name=coverageCountry]').empty();
		$('select[name=coverageExchange]').empty();
		$(".error_field").removeClass("error_field");
	}

	/// fetch Data Aggregator offering--:
	fetchDataAggregatorOffering = function(id) {
		$("#data_aggregator_top_card").hide();
		$("#data_aggregator .alert").hide();
		$(".error_field").removeClass("error_field");
		progressLoader(true);

		var ids = id.split("_");
		var productId = ids[0];
		if(productId.length <= 0) {
			alert('Product Id is missing');
			return;
		}

		var data = {
			"productId" : productId
		}


		$.ajax({
			type: 'GET',
			url:  "fetchDataAggregatorOffering",
			data: data,
			cache:false,
			success : function(response){
				progressLoader(false);
				$("#data_aggregator").slideDown();
				$("#data_aggregator_top_card").slideUp();
				$("#data_aggregator #productId").val(response.productId);
				$("#data_aggregator #productName").val(response.productName);
				$("#data_aggregator #productDescription").val(response.productDescription);
				$("#data_aggregator #launchedYear").val(response.launchedYear);

				getAssetClassMapping('assetClassId');
				$("select[name=assetClassId]").selectpicker('val', response.assetClassCode.split(','));
				
				getAssetClassSecurityTypeMapping('assetClassId', 'securityTypes');
				$("select[name=securityTypes]").selectpicker('val', response.securityTypes.split(','));

				getRegionMapping('coverageRegion');
				$("select[name=coverageRegion]").selectpicker('val', response.coverageRegion.split(','));
				
				getRegionCountryMapping('coverageRegion', 'coverageCountry');
				$("select[name=coverageCountry]").selectpicker('val', response.coverageCountry.split(','));
				
				getCountryExchangeMapping('coverageCountry', 'coverageExchange');
				$("select[name=coverageExchange]").selectpicker('val', response.coverageExchange.split(','));
				
				$("#data_aggregator #costRange").val(response.costRange);
				$("#data_aggregator #email").val(response.email);
				$("#data_aggregator #phoneNumber").val(response.phoneNumber);

				$("select[name=feedType]").selectpicker('val', response.feedType.split(','));
				$("select[name=feedSubType]").selectpicker('val', response.feedSubType.split(','));
				$("select[name=frequency]").selectpicker('val', response.frequency.split(','));
				$("select[name=distributionMethod]").selectpicker('val', response.distributionMethod.split(','));
			},
			error : function(data, textStatus, jqXHR){
				progressLoader(false);
			}
		});
	}

	/// delete Data Aggregator offering--:
	deleteDataAggregatorOffering = function(id) {
		//progressLoader(true);
		var trids = id.split("_");

		var productId = trids[0];
		if(productId.length <= 0) {
			alert('Product Id is missing');
			return;
		}

		var data = {
			"productId" : productId
		}

		$.ajax({
			type: 'POST',
			url:  "deleteDataAggregatorOffering",
			data: data,
			cache:false,
			success : function(output){
				//progressLoader(false);
				$('#' + id).remove();
			},
			error : function(data, textStatus, jqXHR){
				//progressLoader(false);
			}
		});
	}

	/// list Data Aggregator offering:
	listDataAggregatorOffering = function() {
		isEdit = false;
		progressLoader(true);
		$("#data_aggregator").slideUp();
		$("#data_aggregator_top_card").hide();

		$.ajax({
			type: 'GET',
			url:  "listDataAggregatorOffering",
			cache:false,
			success : function(response) {
				var totalCount = response.length;
				if(totalCount === 0) {
					progressLoader(false);
					openDataAggregratorForm();
					return;
				}

				$('#data_aggregator_top_card .data_aggregator_info').empty();
				var listDataAggregatorOfferingHTML = '';
				for(var i=0; i < totalCount; i++) {
					listDataAggregatorOfferingHTML += "<div class='data_aggregator_list list' id='" + response[i].productId  + "_id'>" +
							"<h3>" + response[i].productName  + "</h3>" +
							"<h4>" + response[i].assetClassDescription  + " | " + getRegionMultipleListById(response[i].coverageRegion) + " | " + response[i].launchedYear  + "</h4>" +
							"<p>" + response[i].productDescription  + "</p>" +
							"<div class='action_btn'>" +
								"<a class='btn delete_btn'>Delete</a>" +
								"<a class='btn edit_btn'>Edit</a>" +
							"</div>" +
						"</div>";
				}
				progressLoader(false);
				$('#data_aggregator_top_card .data_aggregator_info').html(listDataAggregatorOfferingHTML);
				$("#data_aggregator_top_card").show().slideDown();
			},
			error : function(data, textStatus, jqXHR){
				progressLoader(false);
				$("#data_aggregator .alert").removeClass("alert-success").addClass("alert-danger").text('Please try again after sometime').show();
			}
		});
	}

	/// add Data Aggregator offering--:
	addDataAggregatorOffering = function(id){
		progressLoader(true);
		$("#data_aggregator_top_card").hide().slideUp();

		var productId = null;

		if(isEdit) {
			productId = id;
			if(productId.length <= 0) {
				alert('Product Id is missing');
				return;
			}
		}

		var productName = $("#data_aggregator #productName").val().trim();
		var productDescription = $("#data_aggregator #productDescription").val().trim();
		var launchedYear = $("#data_aggregator #launchedYear").val().trim();
		var assetClassId = $("#data_aggregator #assetClassId").val();
		var securityTypes = $("#data_aggregator #securityTypes").selectpicker('val');

		var coverageRegion = $("#data_aggregator #coverageRegion").selectpicker('val');
		var coverageCountry = $("#data_aggregator #coverageCountry").selectpicker('val');
		var coverageExchange = $("#data_aggregator #coverageExchange").selectpicker('val');
		var costRange = $("#data_aggregator #costRange").val().trim();
		var email = $("#data_aggregator #email").val().trim();
		var phoneNumber = $("#data_aggregator #phoneNumber").val().trim();

		var feedType = $("#data_aggregator #feedType").selectpicker('val');
		var feedSubType = $("#data_aggregator #feedSubType").selectpicker('val');
		var frequency = $("#data_aggregator #frequency").selectpicker('val');
		var distributionMethod = $("#data_aggregator #distributionMethod").selectpicker('val');

		if(productName != '') {
			$("#data_aggregator #productName").removeClass("error_field");
		} else {
			$("#data_aggregator #productName").addClass("error_field");
			//return false;
		}

		if(productDescription != '') {
			$("#data_aggregator #productDescription").removeClass("error_field");
		} else {
			$("#data_aggregator #productDescription").addClass("error_field");
			//return false;
		}

		if(assetClassId != null) {
			 $("#data_aggregator #assetClassId").parent().find("button").removeClass("error_field");
		} else {
			 $("#data_aggregator #assetClassId").parent().find("button").addClass("error_field");
			//return false;
		}

		if(securityTypes != null) {
			$("#data_aggregator #securityTypes").parent().find("button").removeClass("error_field");
		} else {
			$("#data_aggregator #securityTypes").parent().find("button").addClass("error_field");
			//return false;
		}

		if(coverageRegion != null) {
			coverageRegion = coverageRegion.join();
			$("#data_aggregator #coverageRegion").parent().find("button").removeClass("error_field");
		} else {
			$("#data_aggregator #coverageRegion").parent().find("button").addClass("error_field");
			//return false;
		}

		if(coverageCountry != null) {
			coverageCountry = coverageCountry.join();
			$("#data_aggregator #coverageCountry").parent().find("button").removeClass("error_field");
		} else {
			$("#data_aggregator #coverageCountry").parent().find("button").addClass("error_field");
			//return false;
		}

		if(coverageExchange != null) {
			coverageExchange = coverageExchange.join();
			$("#data_aggregator #coverageExchange").parent().find("button").removeClass("error_field");
		} else {
			$("#data_aggregator #coverageExchange").parent().find("button").addClass("error_field");
			//return false;
		}

		if(costRange != '') {
			 $("#data_aggregator #costRange").removeClass("error_field");
		} else {
			 $("#data_aggregator #costRange").addClass("error_field");
			//return false;
		}

		if(email != '') {
			if(isValidEmailAddress(email)) {
				$("#data_aggregator #email").removeClass("error_field");
			} else {
				$("#data_aggregator #email").addClass("error_field");
				$("#data_aggregator .alert").removeClass("alert-success").addClass("alert-danger").text('Please enter valid email id').show();
				return false;
			}
		}

		if(feedType != null) {
			feedType = feedType.join();
		}
		
		if(feedSubType != null) {
			feedSubType = feedSubType.join();
		}
		
		if(frequency != null) {
			frequency = frequency.join();
		}
		
		if(distributionMethod != null) {
			distributionMethod = distributionMethod.join();
		}

		if( productName != '' && productDescription != '' &&
			assetClassId != null && securityTypes != null && 
			coverageRegion != null && coverageCountry != null && 
			coverageExchange != null &&
			costRange != ''){

			var data = {
				"productId" : productId,
				"productName" : productName,
				"productDescription" : productDescription,
				"assetClassId" : assetClassId,
				"securityTypes" : (securityTypes != null)? ',' + securityTypes + ',' : '',
				"launchedYear" : launchedYear,
				"coverageRegion" : (coverageRegion != null) ? ',' + coverageRegion  + ',': '',
				"coverageCountry" : (coverageCountry != null) ? ',' + coverageCountry + ',' : '',
				"coverageExchange" : (coverageExchange != null) ? ',' + coverageExchange + ',' : '',
				"phoneNumber" : phoneNumber,
				"email" : email,
				"costRange" : costRange,
				"feedType" : (feedType != null) ? ',' + feedType + ',' : '',
				"feedSubType" : (feedSubType != null) ? ',' + feedSubType + ',' : '',
				"frequency" :(frequency != null) ? ',' + frequency + ',' : '',
				"distributionMethod" : (distributionMethod != null) ? ',' + distributionMethod + ',' : ''
			}

			$.ajax({
				type: 'POST',
				url:  "addDataAggregatorOffering",
				data: data,
				cache:false,
				success : function(output){
					$("#data_aggregator_form").trigger('reset');
					$('.selectpicker').selectpicker('refresh');
					progressLoader(false);
					$("#data_aggregator .alert").removeClass("alert-success").removeClass("alert-danger").text('').hide();

					listDataAggregatorOffering();
					$("#data_aggregator").slideUp();
				},
				error : function(data, textStatus, jqXHR){
					progressLoader(false);
				}
			});

		} else {
			$("#data_aggregator .alert").removeClass("alert-success").addClass("alert-danger").text('Please enter mandatory fields').show();
		}

	}



	$("#trading_application #launchedYear").on('blur', function() {
		var year = $("#trading_application #launchedYear").val().trim();
		validateYear(year);
	});

	$("select[name=tcsTradeCoverageRegion]").on('change', function() {
		getRegionCountryMapping('tcsTradeCoverageRegion', 'tcsTradeCoverageCountry');
		getCountryExchangeMapping('tcsTradeCoverageCountry', 'tcsTradableMarkets');
	});

	$("select[name=tcsTradeCoverageCountry]").on('change', function() {
		getCountryExchangeMapping('tcsTradeCoverageCountry', 'tcsTradableMarkets');
	});


	openTradingApplicationForm = function() {
		resetTradingApplicationForm();
		$("#trading_application").slideDown();
		$("#trading_application_top_card").slideUp();
 
		if(!isEdit) {
			getAssetClassMapping('assetClassId');
			getAssetClassSecurityTypeMapping('assetClassId', 'securityTypes');
			getRegionMapping('tcsTradeCoverageRegion');
			getRegionCountryMapping('tcsTradeCoverageRegion', 'tcsTradeCoverageCountry');
			getCountryExchangeMapping('tcsTradeCoverageCountry', 'tcsTradableMarkets');
		}
	}

	closeTradingApplicationForm = function() {
		resetTradingApplicationForm();
		$("#trading_application").slideUp();
		$("#trading_application_top_card").slideDown();
	}

	resetTradingApplicationForm = function() {
		$("#trading_application .alert").hide();
		$("#trading_application_form").trigger('reset');
		$('.selectpicker').selectpicker('refresh');
		$('select[name=securityTypes]').empty();
		$(".error_field").removeClass("error_field");
	}


	/// fetch Trading Application offering--:
	fetchTradingApplicationsOffering = function(id) {
		$("#trading_application_top_card").hide();
		$("#trading_application .alert").hide();
		$(".error_field").removeClass("error_field");
		progressLoader(true);

		var ids = id.split("_");
		var productId = ids[0];
		if(productId.length <= 0) {
			alert('Product Id is missing');
			return;
		}

		var data = {
			"productId" : productId
		}


		$.ajax({
			type: 'GET',
			url:  "fetchTradingApplicationsOffering",
			data: data,
			cache:false,
			success : function(response){
				progressLoader(false);
				$("#trading_application").slideDown();
				$("#trading_application_top_card").slideUp();
				$("#trading_application #productId").val(response.productId);
				$("#trading_application #productName").val(response.productName);
				$("#trading_application #productDescription").val(response.productDescription);
				$("#trading_application #launchedYear").val(response.launchedYear);

				getAssetClassMapping('assetClassId');
				$("select[name=assetClassId]").selectpicker('val', response.assetClassCode.split(','));
				
				getAssetClassSecurityTypeMapping('assetClassId', 'securityTypes');
				$("select[name=securityTypes]").selectpicker('val', response.securityTypes.split(','));

				$("#trading_application #tdsAccessibility").selectpicker('val', response.accessbility.split(','));
				$("#trading_application #tdsSuitability1").selectpicker('val', response.suitability.split(','));
				$("#trading_application #tdsCostType").selectpicker('val', response.costType.split(','));
				$("#trading_application #tdsPlatformCCY").selectpicker('val', response.platformCcy.split(','));
				
				tdsCostTypeSelector();

				if(response.platformCostPm != 0.0) {
					$("#trading_application #tdsPlatformCost").val(response.platformCostPm);
				}

				if(response.platformCostPy != 0.0) {
					$("#trading_application #tdsPlatformType").val(response.platformCostPy);
				}
				
				$("#trading_application #tdsOrderType").selectpicker('val', response.orderType.split(','));
				$("#trading_application #tdsAddSoftwareSpecifications").val(response.softSpecification);
				$("#trading_application #tdsAddOns").val(response.addOns);
				$("#trading_application #tdsOpeSystem").selectpicker('val', response.operatingSystem.split(','));
				$("#trading_application #tdsClientBase").val(response.clientBase);
				$("#trading_application #tdsPriceAlerts").prop("checked",(response.priceAlerts == 'Y') ? true : false);
				$("#trading_application #tdsWatchlist").prop("checked",(response.watchlist == 'Y') ? true : false);
				$("#trading_application #tdsStreNews").prop("checked",(response.streamingNews == 'Y') ? true : false);
				$("#trading_application #tdsChartsAvai").prop("checked",(response.tradeUsingCharts == 'Y') ? true : false);

				getRegionMapping('tcsTradeCoverageRegion');
				$("select[name=tcsTradeCoverageRegion]").selectpicker('val', response.tradRegion.split(','));

				getRegionCountryMapping('tcsTradeCoverageRegion', 'tcsTradeCoverageCountry');
				$("select[name=tcsTradeCoverageCountry]").selectpicker('val', response.tradCountry.split(','));

				getCountryExchangeMapping('tcsTradeCoverageCountry', 'tcsTradableMarkets');
				$("select[name=tcsTradableMarkets]").selectpicker('val', response.tradExchange.split(','));


				$("#trading_application #tcsTradingCapabilitiesType").selectpicker('val', response.tradCapabType.split(','));
				$("#trading_application #tcsTradeExecutionsType").selectpicker('val', response.tradExecType.split(','));
				$("#trading_application #tcsAlgorithmicTradeType").selectpicker('val', response.algoTradeType.split(','));
				$("#trading_application #tcsDarkpoolAccess").selectpicker('val', response.darkpoolAccess.split(','));
				$("#trading_application #tcsSupportedDarkpoolVenues").selectpicker('val', response.darkpoolVenues.split(','));
			},
			error : function(data, textStatus, jqXHR){
				progressLoader(false);
			}
		});
	}

	/// delete Trading Application offering--:
	deleteTradingApplicationsOffering = function(id) {
		//progressLoader(true);
		var trids = id.split("_");

		var productId = trids[0];
		if(productId.length <= 0) {
			alert('Product Id is missing');
			return;
		}

		var data = {
			"productId" : productId
		}

		$.ajax({
			type: 'POST',
			url:  "deleteTradingApplicationsOffering",
			data: data,
			cache:false,
			success : function(output){
				//progressLoader(false);
				$('#' + id).remove();
			},
			error : function(data, textStatus, jqXHR){
				//progressLoader(false);
			}
		});
	}

	/// list Trading Application offering:
	listTradingApplicationsOffering = function() {
		isEdit = false;
		progressLoader(true);
		$("#trading_application").slideUp();
		$("#trading_application_top_card").hide();

		$.ajax({
			type: 'GET',
			url:  "listTradingApplicationsOffering",
			cache:false,
			success : function(response) {
				var totalCount = response.length;
				if(totalCount === 0) {
					progressLoader(false);
					openTradingApplicationForm();
					return;
				}

				$('#trading_application_top_card .trading_application_info').empty();
				var listTradingApplicationsOfferingHTML = '';
				for(var i=0; i < totalCount; i++) {
					listTradingApplicationsOfferingHTML += "<div class='trading_application_list list' id='" + response[i].productId  + "_id'>" +
							"<h3>" + response[i].productName  + "</h3>" +
							"<h4>" + response[i].assetClassDescription  + " | " + getRegionMultipleListById(response[i].tradRegion) + " | " + response[i].launchedYear  + "</h4>" +
							"<p>" + response[i].productDescription  + "</p>" +
							"<div class='action_btn'>" +
								"<a class='btn delete_btn'>Delete</a>" +
								"<a class='btn edit_btn'>Edit</a>" +
							"</div>" +
						"</div>";
				}
				progressLoader(false);
				$('#trading_application_top_card .trading_application_info').html(listTradingApplicationsOfferingHTML);
				$("#trading_application_top_card").show().slideDown();
			},
			error : function(data, textStatus, jqXHR){
				progressLoader(false);
				$("#trading_application .alert").removeClass("alert-success").addClass("alert-danger").text('Please try again after sometime').show();
			}
		});
	}

	/// add Trading Application offering--:
	addTradingApplicationsOffering = function(id) {
		progressLoader(true);
		$("#trading_application_top_card").hide().slideUp();

		var productId = null;

		if(isEdit) {
			productId = id;
			if(productId.length <= 0) {
				alert('Product Id is missing');
				return;
			}
		}

		var productName = $("#trading_application #productName").val().trim();
		var productDescription = $("#trading_application #productDescription").val().trim();
		var launchedYear = $("#trading_application #launchedYear").val().trim();
		var assetClassId = $("#trading_application #assetClassId").val();
		var securityTypes = $("#trading_application #securityTypes").selectpicker('val');

		var tdsAccessibility = $("#trading_application #tdsAccessibility").selectpicker('val');
		var tdsSuitability = $("#trading_application #tdsSuitability1").selectpicker('val');
		var tdsCostType = $("#trading_application #tdsCostType").selectpicker('val');
		var tdsPlatformCCY = $("#trading_application #tdsPlatformCCY").selectpicker('val');
		var tdsPlatformCost = $("#trading_application #tdsPlatformCost").val().trim();
		var tdsPlatformType = $("#trading_application #tdsPlatformType").val().trim();
		var tdsOrderType = $("#trading_application #tdsOrderType").selectpicker('val');

		var tdsAddSoftwareSpecifications = $("#trading_application #tdsAddSoftwareSpecifications").val().trim();
		var tdsAddOns = $("#trading_application #tdsAddOns").val().trim();
		var tdsOpeSystem = $("#trading_application #tdsOpeSystem").selectpicker('val');
		var tdsClientBase = $("#trading_application #tdsClientBase").val().trim();

		var tdsPriceAlerts = $("#trading_application #tdsPriceAlerts").prop("checked");
		var tdsWatchlist = $("#trading_application #tdsWatchlist").prop("checked");
		var tdsStreNews = $("#trading_application #tdsStreNews").prop("checked");
		var tdsChartsAvai = $("#trading_application #tdsChartsAvai").prop("checked");

		var tcsTradeCoverageRegion = $("#trading_application #tcsTradeCoverageRegion").selectpicker('val');
		var tcsTradeCoverageCountry = $("#trading_application #tcsTradeCoverageCountry").selectpicker('val');
		var tcsTradableMarkets = $("#trading_application #tcsTradableMarkets").selectpicker('val');
		var tcsTradingCapabilitiesType = $("#trading_application #tcsTradingCapabilitiesType").selectpicker('val');
		var tcsTradeExecutionsType = $("#trading_application #tcsTradeExecutionsType").selectpicker('val');
		var tcsAlgorithmicTradeType = $("#trading_application #tcsAlgorithmicTradeType").selectpicker('val');
		var tcsDarkpoolAccess = $("#trading_application #tcsDarkpoolAccess").selectpicker('val');
		var tcsSupportedDarkpoolVenues = $("#trading_application #tcsSupportedDarkpoolVenues").selectpicker('val');

		if(productName != '') {
			$("#trading_application #productName").removeClass("error_field");
		} else {
			$("#trading_application #productName").addClass("error_field");
			//return false;
		}

		if(productDescription != '') {
			$("#trading_application #productDescription").removeClass("error_field");
		} else {
			$("#trading_application #productDescription").addClass("error_field");
			//return false;
		}

		if(launchedYear != '') {
			$("#trading_application #launchedYear").removeClass("error_field");
		} else {
			$("#trading_application #launchedYear").addClass("error_field");
			//return false;
		}

		if(assetClassId != null) {
			 $("#trading_application #assetClassId").parent().find("button").removeClass("error_field");
		} else {
			 $("#trading_application #assetClassId").parent().find("button").addClass("error_field");
			//return false;
		}

		if(securityTypes != null) {
			$("#trading_application #securityTypes").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #securityTypes").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tcsTradeCoverageRegion != null) {
			tcsTradeCoverageRegion = tcsTradeCoverageRegion.join();
			$("#trading_application #tcsTradeCoverageRegion").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tcsTradeCoverageRegion").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tcsTradeCoverageCountry != null) {
			tcsTradeCoverageCountry = tcsTradeCoverageCountry.join();
			$("#trading_application #tcsTradeCoverageCountry").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tcsTradeCoverageCountry").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tdsAccessibility != null) {
			tdsAccessibility = tdsAccessibility.join();
			$("#trading_application #tdsAccessibility").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tdsAccessibility").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tdsCostType != null) {
			$("#trading_application #tdsCostType").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tdsCostType").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tdsAddSoftwareSpecifications != '') {
			$("#trading_application #tdsAddSoftwareSpecifications").removeClass("error_field");
		} else {
			$("#trading_application #tdsAddSoftwareSpecifications").addClass("error_field");
			//return false;
		}

		if(tdsPriceAlerts) {
			tdsPriceAlerts = 'Y';
		} else {
			tdsPriceAlerts = '';
		}

		if(tdsWatchlist) {
			tdsWatchlist = 'Y';
		} else {
			tdsWatchlist = '';
		}

		if(tdsStreNews) {
			tdsStreNews = 'Y';
		} else {
			tdsStreNews = '';
		}

		if(tdsChartsAvai) {
			tdsChartsAvai = 'Y';
		} else {
			tdsChartsAvai = '';
		}

		if(tcsTradingCapabilitiesType != null) {
			tcsTradingCapabilitiesType = tcsTradingCapabilitiesType.join();
			$("#trading_application #tcsTradingCapabilitiesType").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tcsTradingCapabilitiesType").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tcsTradeExecutionsType != null) {
			tcsTradeExecutionsType = tcsTradeExecutionsType.join();
			$("#trading_application #tcsTradeExecutionsType").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tcsTradeExecutionsType").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tcsAlgorithmicTradeType != null) {
			tcsAlgorithmicTradeType = tcsAlgorithmicTradeType.join();
			$("#trading_application #tcsAlgorithmicTradeType").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tcsAlgorithmicTradeType").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tcsDarkpoolAccess != null) {
			tcsDarkpoolAccess = tcsDarkpoolAccess.join();
			$("#trading_application #tcsDarkpoolAccess").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tcsDarkpoolAccess").parent().find("button").addClass("error_field");
			//return false;
		}

		if(tcsSupportedDarkpoolVenues != null) {
			tcsSupportedDarkpoolVenues = tcsSupportedDarkpoolVenues.join();
			$("#trading_application #tcsSupportedDarkpoolVenues").parent().find("button").removeClass("error_field");
		} else {
			$("#trading_application #tcsSupportedDarkpoolVenues").parent().find("button").addClass("error_field");
			//return false;
		}


		if( productName != '' && productDescription != '' && launchedYear != '' &&
			assetClassId != null && securityTypes != null && 
			tcsTradeCoverageRegion != null && 
			tcsTradeCoverageCountry != null && tcsTradableMarkets != null &&
			tcsTradingCapabilitiesType != null && tcsTradeExecutionsType != null &&
			tcsAlgorithmicTradeType != null && tcsDarkpoolAccess != null &&
			tcsSupportedDarkpoolVenues != null){

			var data = {
				"productId" : productId,
				"productName" : productName,
				"productDescription" : productDescription,
				"assetClassId" : assetClassId,
				"securityTypes" : (securityTypes != null)? ',' + securityTypes + ',': '',
				"launchedYear" : launchedYear,
				"accessbility" : (tdsAccessibility != null) ? ',' + tdsAccessibility + ',' : '',
				"suitability" : (tdsSuitability != null) ? ',' + tdsSuitability + ',' : '',
				"costType" : tdsCostType,
				"platformCcy" : (tdsPlatformCCY != null) ? ',' + tdsPlatformCCY + ',' : '',
				"platformCostPm" : (tdsPlatformCost != '') ? tdsPlatformCost : '0.0',
				"platformCostPy" : (tdsPlatformType != '') ? tdsPlatformType : '0.0',
				"orderType" : (tdsOrderType != null) ? ',' + tdsOrderType + ',' : '',
				"softSpecification" : tdsAddSoftwareSpecifications,
				"addOns" : tdsAddOns,
				"operatingSystem" : (tdsOpeSystem != null) ? ',' + tdsOpeSystem + ',' : '',
				"clientBase" : tdsClientBase,
				"priceAlerts" : tdsPriceAlerts,
				"watchlist" : tdsWatchlist,
				"streamingNews" : tdsStreNews,
				"tradeUsingCharts" : tdsChartsAvai,
				"tradRegion" : (tcsTradeCoverageRegion != null) ? ',' + tcsTradeCoverageRegion + ',' : '',
				"tradCountry" : (tcsTradeCoverageCountry != null) ? ',' + tcsTradeCoverageCountry + ',' : '',
				"tradExchange" : (tcsTradableMarkets != null) ? ',' + tcsTradableMarkets + ',' : '',
				"tradCapabType" : (tcsTradingCapabilitiesType != null) ? ',' + tcsTradingCapabilitiesType + ',' : '',
				"tradExecType" : (tcsTradeExecutionsType != null) ? ',' + tcsTradeExecutionsType + ',' : '',
				"algoTradeType" : (tcsAlgorithmicTradeType != null) ? ',' + tcsAlgorithmicTradeType + ',' : '',
				"darkpoolAccess" : (tcsDarkpoolAccess != null) ? ',' + tcsDarkpoolAccess + ',' : '',
				"darkpoolVenues" : (tcsSupportedDarkpoolVenues != null) ? ',' + tcsSupportedDarkpoolVenues + ',' : ''
			}

			$.ajax({
				type: 'POST',
				url:  "addTradingApplicationsOffering",
				data: data,
				cache:false,
				success : function(output){
					$("#trading_application_form").trigger('reset');
					$('.selectpicker').selectpicker('refresh');
					progressLoader(false);
					$("#trading_application .alert").removeClass("alert-success").removeClass("alert-danger").text('').hide();

					listTradingApplicationsOffering();
					$("#trading_application").slideUp();
				},
				error : function(data, textStatus, jqXHR){
					progressLoader(false);
				}
			});

		} else {
			$("#trading_application .alert").removeClass("alert-success").addClass("alert-danger").text('Please enter mandatory fields').show();
		}

	}



	$("#analytic_application #launchedYear").on('blur', function() {
		var year = $("#analytic_application #launchedYear").val().trim();
		validateYear(year);
	});

	getAnalyticalSolutionTypeMapping('analyticsSolutionType');

	$("select[name=analyticsSolutionType]").on('change', function() {
		getAnalyticalSolutionSubTypeMapping('analyticsSolutionType', 'analyticsSolutionSubType');
	});

	openAnalyticApplicationForm = function() {
		resetAnalyticApplicationForm();
		$("#analytic_application").slideDown();
		$("#analytic_application_top_card").slideUp();
 
		if(!isEdit) {
			getAnalyticalSolutionTypeMapping('analyticsSolutionType');
			getAnalyticalSolutionSubTypeMapping('analyticsSolutionType', 'analyticsSolutionSubType');
		}
	}

	closeAnalyticApplicationForm = function() {
		resetTradingApplicationForm();
		$("#analytic_application").slideUp();
		$("#analytic_application_top_card").slideDown();
	}

	resetAnalyticApplicationForm = function() {
		$("#analytic_application .alert").hide();
		$("#analytic_application_form").trigger('reset');
		$('.selectpicker').selectpicker('refresh');
		$('select[name=securityTypes]').empty();
		$(".error_field").removeClass("error_field");
	}


	/// fetch Analytic Application offering--:
	fetchAnalyticsApplicationsOffering = function(id) {
		$("#analytic_application_top_card").hide();
		$("#analytic_application .alert").hide();
		$(".error_field").removeClass("error_field");
		progressLoader(true);

		var ids = id.split("_");
		var productId = ids[0];
		if(productId.length <= 0) {
			alert('Product Id is missing');
			return;
		}

		var data = {
			"productId" : productId
		}


		$.ajax({
			type: 'GET',
			url:  "fetchAnalyticsApplicationsOffering",
			data: data,
			cache:false,
			success : function(response){
				progressLoader(false);
				$("#analytic_application").slideDown();
				$("#analytic_application_top_card").slideUp();
				$("#analytic_application #productId").val(response.productId);
				$("#analytic_application #productName").val(response.productName);
				$("#analytic_application #productDescription").val(response.productDescription);
				$("#analytic_application #launchedYear").val(response.launchedYear);

				getAnalyticalSolutionTypeMapping('analyticsSolutionType');
				$("select[name=analyticsSolutionType]").selectpicker('val', response.analyticalSolutionTypeCode);
				
				getAnalyticalSolutionSubTypeMapping('analyticsSolutionType', 'analyticsSolutionSubType');
				$("select[name=analyticsSolutionSubType]").selectpicker('val', response.analyticalSolutionSubTypes.split(','));

				$("#analytic_application #asdAccessibility").selectpicker('val', response.accessbility.split(','));
				$("#analytic_application #asdSuitability1").selectpicker('val', response.suitability.split(','));
				$("#analytic_application #asdApplicationCostType").selectpicker('val', response.costType.split(','));
				
				asdApplicationCostTypeSelector();

				if(response.subCostPm != 0) {
					$("#analytic_application #asdApplicationSubscriptionCost").val(response.subCostPm);
				}

				if(response.subCostPy != 0) {
					$("#analytic_application #asdApplicationSubscriptionAnnum").val(response.subCostPy);
				}

				$("#analytic_application #asdSoftwareSpecifications").val(response.softSpecification);
				$("#analytic_application #asdAddOns").val(response.addOns);
				$("#analytic_application #asdOperatingSystem").selectpicker('val', response.operatingSystem.split(','));
				$("#analytic_application #asdExistingUserBase").val(response.userBase);
				$("#analytic_application #asdCustomizableCalculationModels").prop("checked",(response.customizableCalcModel == 'Y') ? true : false);
				$("#analytic_application #asdRealtimeMarketData").prop("checked",(response.realTimeMarketData == 'Y') ? true : false);

			},
			error : function(data, textStatus, jqXHR){
				progressLoader(false);
			}
		});
	}

	/// delete Analytic Application offering--:
	deleteAnalyticsApplicationsOffering = function(id) {
		//progressLoader(true);
		var trids = id.split("_");

		var productId = trids[0];
		if(productId.length <= 0) {
			alert('Product Id is missing');
			return;
		}

		var data = {
			"productId" : productId
		}

		$.ajax({
			type: 'POST',
			url:  "deleteAnalyticsApplicationsOffering",
			data: data,
			cache:false,
			success : function(output){
				//progressLoader(false);
				$('#' + id).remove();
			},
			error : function(data, textStatus, jqXHR){
				//progressLoader(false);
			}
		});
	}

	/// list Analytic Application offering:
	listAnalyticsApplicationsOffering = function() {
		isEdit = false;
		progressLoader(true);
		$("#analytic_application").slideUp();
		$("#analytic_application_top_card").hide();

		$.ajax({
			type: 'GET',
			url:  "listAnalyticsApplicationsOffering",
			cache:false,
			success : function(response) {
				var totalCount = response.length;
				if(totalCount === 0) {
					progressLoader(false);
					openAnalyticApplicationForm();
					return;
				}

				$('#analytic_application_top_card .analytic_application_info').empty();
				var listAnalyticsApplicationsOfferingHTML = '';
				for(var i=0; i < totalCount; i++) {
					listAnalyticsApplicationsOfferingHTML += "<div class='analytic_application_list list' id='" + response[i].productId  + "_id'>" +
							"<h3>" + response[i].productName  + "</h3>" +
							"<h4>" + response[i].analyticalSolutionTypeDescription  + " | " + response[i].accessbility.replace(/(^,)|(,$)/g, "") + " | " + response[i].launchedYear  + "</h4>" +
							"<p>" + response[i].productDescription  + "</p>" +
							"<div class='action_btn'>" +
								"<a class='btn delete_btn'>Delete</a>" +
								"<a class='btn edit_btn'>Edit</a>" +
							"</div>" +
						"</div>";
				}
				progressLoader(false);
				$('#analytic_application_top_card .analytic_application_info').html(listAnalyticsApplicationsOfferingHTML);
				$("#analytic_application_top_card").show().slideDown();
			},
			error : function(data, textStatus, jqXHR){
				progressLoader(false);
				$("#analytic_application .alert").removeClass("alert-success").addClass("alert-danger").text('Please try again after sometime').show();
			}
		});
	}

	/// add Analytic Application offering--:
	addAnalyticApplicationsOffering = function(id) {
		progressLoader(true);
		$("#analytic_application_top_card").hide().slideUp();

		var productId = null;

		if(isEdit) {
			productId = id;
			if(productId.length <= 0) {
				alert('Product Id is missing');
				return;
			}
		}

		var productName = $("#analytic_application #productName").val().trim();
		var productDescription = $("#analytic_application #productDescription").val().trim();
		var launchedYear = $("#analytic_application #launchedYear").val().trim();
		var analyticsSolutionType = $("#analytic_application #analyticsSolutionType").val();
		var analyticsSolutionSubType = $("#analytic_application #analyticsSolutionSubType").selectpicker('val');

		var asdAccessibility = $("#analytic_application #asdAccessibility").selectpicker('val');
		var asdSuitability = $("#analytic_application #asdSuitability1").selectpicker('val');
		var asdCostType = $("#analytic_application #asdApplicationCostType").selectpicker('val');
		
		var asdApplicationSubscriptionCost = $("#analytic_application #asdApplicationSubscriptionCost").val().trim();
		var asdApplicationSubscriptionAnnum = $("#analytic_application #asdApplicationSubscriptionAnnum").val().trim();
		
		var asdAddOns = $("#analytic_application #asdAddOns").val();
		var asdOperatingSystem = $("#analytic_application #asdOperatingSystem").selectpicker('val');
		var asdAddSoftwareSpecifications = $("#analytic_application #asdSoftwareSpecifications").val().trim();

		var asdExistingUserBase = $("#analytic_application #asdExistingUserBase").val();
		var asdCustomizableCalculationModels = $("#analytic_application #asdCustomizableCalculationModels").prop("checked");
		var asdRealtimeMarketData = $("#analytic_application #asdRealtimeMarketData").prop("checked");

		
		if(productName != '') {
			$("#analytic_application #productName").removeClass("error_field");
		} else {
			$("#analytic_application #productName").addClass("error_field");
			//return false;
		}

		if(productDescription != '') {
			$("#analytic_application #productDescription").removeClass("error_field");
		} else {
			$("#analytic_application #productDescription").addClass("error_field");
			//return false;
		}

		if(launchedYear != '') {
			$("#analytic_application #launchedYear").removeClass("error_field");
		} else {
			$("#analytic_application #launchedYear").addClass("error_field");
			//return false;
		}

		if(analyticsSolutionType != null) {
			 $("#analytic_application #analyticsSolutionType").parent().find("button").removeClass("error_field");
		} else {
			 $("#analytic_application #analyticsSolutionType").parent().find("button").addClass("error_field");
			//return false;
		}

		if(analyticsSolutionSubType != null) {
			$("#analytic_application #analyticsSolutionSubType").parent().find("button").removeClass("error_field");
		} else {
			$("#analytic_application #analyticsSolutionSubType").parent().find("button").addClass("error_field");
			//return false;
		}

		if(asdAccessibility != null) {
			asdAccessibility = asdAccessibility.join();
			$("#analytic_application #asdAccessibility").parent().find("button").removeClass("error_field");
		} else {
			$("#analytic_application #asdAccessibility").parent().find("button").addClass("error_field");
			//return false;
		}

		if(asdCostType != null) {
			$("#analytic_application #asdApplicationCostType").parent().find("button").removeClass("error_field");
		} else {
			$("#analytic_application #asdApplicationCostType").parent().find("button").addClass("error_field");
			//return false;
		}

		if(asdAddSoftwareSpecifications != '') {
			$("#analytic_application #asdSoftwareSpecifications").removeClass("error_field");
		} else {
			$("#analytic_application #asdSoftwareSpecifications").addClass("error_field");
			//return false;
		}

		if(asdCustomizableCalculationModels) {
			asdCustomizableCalculationModels = 'Y';
		} else {
			asdCustomizableCalculationModels = '';
		}

		if(asdRealtimeMarketData) {
			asdRealtimeMarketData = 'Y';
		} else {
			asdRealtimeMarketData = '';
		}


		if( productName != '' && productDescription != '' && launchedYear != '' &&
			analyticsSolutionType != null && analyticsSolutionSubType != null && 
			asdAccessibility != null && asdCostType != null && 
			asdSoftwareSpecifications != null){

			var data = {
				"productId" : productId,
				"productName" : productName,
				"productDescription" : productDescription,
				"analyticsSolutionTypeId" : analyticsSolutionType,
				"analyticsSolutionSubTypes" : (analyticsSolutionSubType != null)? ',' + analyticsSolutionSubType + ',' : '',
				"launchedYear" : launchedYear,
				"accessbility" : (asdAccessibility != null) ? ',' + asdAccessibility + ',' : '',
				"suitability" : (asdSuitability != null) ? ',' + asdSuitability + ',' : '',
				"costType" : asdCostType,
				"subCostPm" : (asdApplicationSubscriptionCost != '') ? asdApplicationSubscriptionCost : '0.0',
				"subCostPy" : (asdApplicationSubscriptionAnnum != '') ? asdApplicationSubscriptionAnnum : '0.0',
				"softSpecification" : asdAddSoftwareSpecifications,
				"addOns" : asdAddOns,
				"operatingSystem" : (asdOperatingSystem != null) ? ',' + asdOperatingSystem + ',' : '',
				"userBase" : asdExistingUserBase,
				"customizableCalcModel" : asdCustomizableCalculationModels,
				"realTimeMarketData" : asdRealtimeMarketData
			}

			$.ajax({
				type: 'POST',
				url:  "addAnalyticsApplicationsOffering",
				data: data,
				cache:false,
				success : function(output){
					$("#analytic_application_form").trigger('reset');
					$('.selectpicker').selectpicker('refresh');
					progressLoader(false);
					$("#analytic_application .alert").removeClass("alert-success").removeClass("alert-danger").text('').hide();

					listAnalyticsApplicationsOffering();
					$("#analytic_application").slideUp();
				},
				error : function(data, textStatus, jqXHR){
					progressLoader(false);
				}
			});

		} else {
			$("#analytic_application .alert").removeClass("alert-success").addClass("alert-danger").text('Please enter mandatory fields').show();
		}

	}

	

	$("#research_application #launchedYear").on('blur', function() {
		var year = $("#research_application #launchedYear").val().trim();
		validateYear(year);
	});

	getResearchAreaMapping('rcResearchArea');

	$("select[name=rcResearchArea]").on('change', function() {
		getResearchSubAreaMapping('rcResearchArea', 'rcResearchSubArea');
	});

	$("select[name=rdAnalystRegionofIncorp]").on('change', function() {
		getRegionCountryMapping('rdAnalystRegionofIncorp', 'rdAnalystCountryofIncorp');
	});

	openResearchApplicationForm = function() {
		resetResearchApplicationForm();
		$("#research_application").slideDown();
		$("#research_application_top_card").slideUp();
 
		if(!isEdit) {
			getResearchAreaMapping('rcResearchArea');
			getResearchSubAreaMapping('rcResearchArea', 'rcResearchSubArea');
			getRegionMapping('rdAnalystRegionofIncorp');
			getRegionCountryMapping('rdAnalystRegionofIncorp', 'rdAnalystCountryofIncorp');
		}
	}

	closeResearchApplicationForm = function() {
		resetResearchApplicationForm();
		$("#research_application").slideUp();
		$("#research_application_top_card").slideDown();
	}

	resetResearchApplicationForm = function() {
		$("#research_application .alert").hide();
		$("#research_application_form").trigger('reset');
		$('.selectpicker').selectpicker('refresh');
		$('select[name=securityTypes]').empty();
		$(".error_field").removeClass("error_field");
	}

	/// fetch Research Application offering--:
	fetchResearchReportsOffering = function(id) {
		$("#research_application_top_card").hide();
		$("#research_application .alert").hide();
		$(".error_field").removeClass("error_field");
		progressLoader(true);

		var ids = id.split("_");
		var productId = ids[0];
		if(productId.length <= 0) {
			alert('Product Id is missing');
			return;
		}

		var data = {
			"productId" : productId
		}


		$.ajax({
			type: 'GET',
			url:  "fetchResearchReportsOffering",
			data: data,
			cache:false,
			success : function(response){
				progressLoader(false);
				$("#research_application").slideDown();
				$("#research_application_top_card").slideUp();
				$("#research_application #productId").val(response.productId);
				$("#research_application #productName").val(response.productName);
				$("#research_application #productDescription").val(response.productDescription);
				$("#research_application #launchedYear").val(response.launchedYear);

				getResearchAreaMapping("rcResearchArea");
				$("#research_application #rcResearchArea").selectpicker('val', response.researchArea);
				
				getResearchSubAreaMapping('rcResearchArea', 'rcResearchSubArea');
				$("select[name=rcResearchSubArea]").selectpicker('val', response.researchSubArea.split(','));

				$("#research_application #rcRegionsCovered").val(response.regionsCovered);
				$("#research_application #rcTotalResearchAnalyst").val(response.totalAnalyst);
				$("#research_application #rcExistingClientBase").val(response.existingClientBase);

				$("#research_application #rdAccessibility").selectpicker('val', response.accessbility.split(','));
				$("#research_application #rdSuitability").selectpicker('val', response.suitability.split(','));
				$("#research_application #rdReportCostType").selectpicker('val', response.costType.split(','));
				
				rdReportCostTypeSelector();
				if(response.subCostPm != 0) {
					$("#research_application #rdSubsriptionCostUSDpermonth").val(response.subCostPm);
				}

				if(response.subCostPy != 0) {
					$("#research_application #rdSubsriptionCostUSDperannum").val(response.subCostPy);
				}

				$("#research_application #rdReportFormat").selectpicker('val', response.repFormat.split(','));
				$("#research_application #rdResearchApplicableMonth").selectpicker('val', response.resPeriodMon);
				$("#research_application #rdResearchApplicableYear").selectpicker('val', response.resPeriodYear);

				$("#research_application #rdAnalystName").val(response.analystName);

				getRegionMapping('rdAnalystRegionofIncorp');
				$("#research_application #rdAnalystRegionofIncorp").selectpicker('val', response.analystRegion);
				getRegionCountryMapping('rdAnalystRegionofIncorp', 'rdAnalystCountryofIncorp');
				$("select[name=rdAnalystCountryofIncorp]").selectpicker('val', response.analystCountry);
				

				$("#research_application #rdAnalystYearofExp").selectpicker('val', response.analystYearOfExp);
				$("#research_application #rdAnalystAwards").selectpicker('val', response.analystAwards);
				$("#research_application #rdResearchAnalystWithCFA").prop("checked",(response.anaystCfaCharter == 'Y') ? true : false);

			},
			error : function(data, textStatus, jqXHR){
				progressLoader(false);
			}
		});
	}

	/// delete Analytic Application offering--:
	deleteResearchReportsOffering = function(id) {
		//progressLoader(true);
		var trids = id.split("_");

		var productId = trids[0];
		if(productId.length <= 0) {
			alert('Product Id is missing');
			return;
		}

		var data = {
			"productId" : productId
		}

		$.ajax({
			type: 'POST',
			url:  "deleteResearchReportsOffering",
			data: data,
			cache:false,
			success : function(output){
				//progressLoader(false);
				$('#' + id).remove();
			},
			error : function(data, textStatus, jqXHR){
				//progressLoader(false);
			}
		});
	}

	/// list Analytic Application offering:
	listResearchReportsOffering = function() {
		isEdit = false;
		progressLoader(true);
		$("#research_application").slideUp();
		$("#research_application_top_card").hide();

		$.ajax({
			type: 'GET',
			url:  "listResearchReportsOffering",
			cache:false,
			success : function(response) {
				var totalCount = response.length;
				if(totalCount === 0) {
					progressLoader(false);
					openResearchApplicationForm();
					return;
				}

				$('#research_application_top_card .research_application_info').empty();
				var listResearchReportsOfferingHTML = '';
				for(var i=0; i < totalCount; i++) {
					listResearchReportsOfferingHTML += "<div class='research_application_list list' id='" + response[i].productId  + "_id'>" +
							"<h3>" + response[i].productName  + "</h3>" +
							"<h4>" + response[i].researchAreaDescription + " | " + response[i].regionsCovered + " | " + response[i].launchedYear  + "</h4>" +
							"<p>" + response[i].productDescription  + "</p>" +
							"<div class='action_btn'>" +
								"<a class='btn delete_btn'>Delete</a>" +
								"<a class='btn edit_btn'>Edit</a>" +
							"</div>" +
						"</div>";
				}
				progressLoader(false);
				$('#research_application_top_card .research_application_info').html(listResearchReportsOfferingHTML);
				$("#research_application_top_card").show().slideDown();
			},
			error : function(data, textStatus, jqXHR){
				progressLoader(false);
				$("#research_application .alert").removeClass("alert-success").addClass("alert-danger").text('Please try again after sometime').show();
			}
		});
	}

	/// add Research Application offering--:
	addResearchReportsOffering = function(id) {
		progressLoader(true);
		$("#research_application_top_card").hide().slideUp();

		var productId = null;

		if(isEdit) {
			productId = id;
			if(productId.length <= 0) {
				alert('Product Id is missing');
				return;
			}
		}

		var productName = $("#research_application #productName").val().trim();
		var productDescription = $("#research_application #productDescription").val().trim();
		var launchedYear = $("#research_application #launchedYear").val().trim();
		var rcResearchArea = $("#research_application #rcResearchArea").val();
		var rcResearchSubArea = $("#research_application #rcResearchSubArea").selectpicker('val');

		var regionsCovered = $("#research_application #rcRegionsCovered").selectpicker('val');
		var totalResearchAnalyst = $("#research_application #rcTotalResearchAnalyst").val().trim();
		var existingClientBase = $("#research_application #rcExistingClientBase").val();

		var accessibility = $("#research_application #rdAccessibility").val();
		var suitability = $("#research_application #rdSuitability").selectpicker('val');
		var reportCostType = $("#research_application #rdReportCostType").selectpicker('val');
		
		var costPerMonth = $("#research_application #rdSubsriptionCostUSDpermonth").val().trim();
		var costPerAnnum = $("#research_application #rdSubsriptionCostUSDperannum").val().trim();
		
		var reportFormat = $("#research_application #rdReportFormat").selectpicker('val');
		var researchPeriodMonth = $("#research_application #rdResearchApplicableMonth").val();
		var researchPeriodYear = $("#research_application #rdResearchApplicableYear").val();

		var analystName = $("#research_application #rdAnalystName").val().trim();
		var analystRegion = $("#research_application #rdAnalystRegionofIncorp").selectpicker('val');
		var analystCountry = $("#research_application #rdAnalystCountryofIncorp").selectpicker('val');
		var anaystYearOfExperience = $("#research_application #rdAnalystYearofExp").val();
		var analystAwards = $("#research_application #rdAnalystAwards").val();
		var analystCfaCharter = $("#research_application #rdResearchAnalystWithCFA").prop("checked");

		
		if(productName != '') {
			$("#research_application #productName").removeClass("error_field");
		} else {
			$("#research_application #productName").addClass("error_field");
			//return false;
		}

		if(productDescription != '') {
			$("#research_application #productDescription").removeClass("error_field");
		} else {
			$("#research_application #productDescription").addClass("error_field");
			//return false;
		}

		if(launchedYear != '') {
			$("#research_application #launchedYear").removeClass("error_field");
		} else {
			$("#research_application #launchedYear").addClass("error_field");
			//return false;
		}

		if(rcResearchArea != null) {
			 $("#research_application #rcResearchArea").parent().find("button").removeClass("error_field");
		} else {
			 $("#research_application #rcResearchArea").parent().find("button").addClass("error_field");
			//return false;
		}

		if(rcResearchSubArea != null) {
			$("#research_application #rcResearchSubArea").parent().find("button").removeClass("error_field");
		} else {
			$("#research_application #rcResearchSubArea").parent().find("button").addClass("error_field");
			//return false;
		}

		if(regionsCovered != null) {
			 $("#research_application #rcRegionsCovered").parent().find("button").removeClass("error_field");
		} else {
			 $("#research_application #rcRegionsCovered").parent().find("button").addClass("error_field");
			//return false;
		}

		if(accessibility != null) {
			$("#research_application #rdAccessibility").parent().find("button").removeClass("error_field");
		} else {
			$("#research_application #rdAccessibility").parent().find("button").addClass("error_field");
			//return false;
		}

		if(reportCostType != null) {
			$("#research_application #rdReportCostType").parent().find("button").removeClass("error_field");
		} else {
			$("#research_application #rdReportCostType").parent().find("button").addClass("error_field");
			//return false;
		}


		if(reportFormat != null) {
			$("#research_application #rdReportFormat").removeClass("error_field");
		} else {
			$("#research_application #rdReportFormat").addClass("error_field");
			//return false;
		}

		if(researchPeriodYear != null) {
			$("#research_application #rdResearchApplicableYear").parent().find("button").removeClass("error_field");
		} else {
			$("#research_application #rdResearchApplicableYear").parent().find("button").addClass("error_field");
			//return false;
		}

		if(analystCfaCharter) {
			analystCfaCharter = 'Y';
		} else {
			analystCfaCharter = '';
		}


		if( productName != '' && productDescription != '' && launchedYear != '' &&
			rcResearchArea != null && rcResearchSubArea != null &&
			accessibility != null && reportCostType != '' &&
			reportFormat != null && researchPeriodYear != null){

			var data = {
				"productId" : productId,
				"researchReportName" : productName,
				"researchReportDescription" : productDescription,
				"researchAreaId" : rcResearchArea,
				"researchSubAreas" : (rcResearchSubArea != null)? ',' + rcResearchSubArea + ',' : '',
				"launchedYear" : launchedYear,
				"regionsCovered" : regionsCovered,
				"totalResearchAnalyst" : totalResearchAnalyst,
				"existingClientBase" : existingClientBase,
				"accessibility" : accessibility,
				"suitability" : (suitability != null) ? ',' + suitability + ',' : '',
				"reportCostType" : reportCostType,
				"costPerMonth" : (costPerMonth != '') ? costPerMonth : '0.0',
				"costPerAnnum" : (costPerAnnum != '') ? costPerAnnum : '0.0',
				"reportFormat" : (reportFormat != null) ? ',' + reportFormat + ',' : '',
				"researchPeriodMonth" : researchPeriodMonth,
				"researchPeriodYear" : researchPeriodYear,
				"analystName" : analystName,
				"analystRegion" : analystRegion,
				"analystCountry" : analystCountry,
				"anaystYearOfExperience" : anaystYearOfExperience,
				"analystAwards" : analystAwards,
				"analystCfaCharter" : analystCfaCharter
			}

			$.ajax({
				type: 'POST',
				url:  "addResearchReportsOffering",
				data: data,
				cache:false,
				success : function(output){
					$("#research_application_form").trigger('reset');
					$('.selectpicker').selectpicker('refresh');
					progressLoader(false);
					$("#research_application .alert").removeClass("alert-success").removeClass("alert-danger").text('').hide();

					listResearchReportsOffering();
					$("#research_application").slideUp();
				},
				error : function(data, textStatus, jqXHR){
					progressLoader(false);
				}
			});

		} else {
			$("#research_application .alert").removeClass("alert-success").addClass("alert-danger").text('Please enter mandatory fields').show();
		}

	}



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
			var coverageexchange = $("#coverageexchange").val();
			//var supportcoveragecountry = $("#supportcoveragecountry :selected").text();
			var supportcoveragecountry = $("#supportcoveragecountry").val();
		    var vendorcostrange = $("#vendorcostrange").val();
		    var phonenumber = $("#phonenumber").val();
		    var email = $("#email").val();

		 if(checkMandotrySelectValue(solutionDataCoverage) && checkMandotrySelectValue(offeringsDataCoverage) && checkMandotrySelectValue(supportcoveragecountry) && checkMandotrySelectValue(vendorcostrange) && checkNullValue(phonenumber) && checkNullValue(email)){
			 
			$.ajax({
				type: 'GET',
				url:  "addVendorDataCoverage?solutionDataCoverage="+solutionDataCoverage+"&offeringsDataCoverage="+offeringsDataCoverage+"&supportcoverageregion="+supportcoverageregion+"&supportcoveragecountry="+supportcoveragecountry+"&coverageexchange="+coverageexchange+"&vendorcostrange="+vendorcostrange+"&phonenumber="+phonenumber+"&email="+email,
				cache:false,
				success : function(response){
					if(checkNullValue(response[0].recordExist))
					{
						alert(response[0].recordExist);
					}else{
				     $("#dataCoverageTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_deleteVendorDataCoverage"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].region+'</td><td>'+response[i].country+'</td><td>'+response[i].coverageexchange+'</td><td>'+response[i].cost+'</td><td>'+response[i].phonNo+'</td><td>'+response[i].email+'</td><td onclick="deleteRecordDataCoverage(\''+response[i].id+'\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
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
		    
				var solnText = $("#tsdSolution option:selected").text();
				if(checkExistingRecordVendors('tdsTable',0,1,solnText,offering))
	  				   return;
			
			     $("#tdsOffering").val("");

			  var uri ="addTradingSoftwareDetails?solution="+solution+"&assetClass="+assetClass+"&appName="+appName+"&appDesc="+appDesc+"&tradableRegions="+tradableRegions+
					"&tradableMarkets="+tradableMarkets+"&accessibility="+accessibility+"&suitability="+suitability+"&offering="+offering+"&costType="+costType
					+"&platformCCY="+platformCCY+"&platformCost="+platformCost+"&platformType="+platformType+"&exchangeFees="+exchangeFees+"&streNews="+streNews
					+"&chartsAvai="+chartsAvai+"&orderType="+orderType+"&priceAlerts="+priceAlerts+"&watchlist="+watchlist+"&offeringDesc="+offeringDesc
					+"&tradingCap="+tradingCap+"&tradeExec="+tradeExec+"&tradeType="+tradeType+"&darkVenues="+darkVenues+"&addOns="+addOns+"&opeSystem="+opeSystem+"&launchedYear="+launchedYear;
			    
				
			$.ajax({
				type: 'GET',
				url: encodeURI(uri),
				cache:false,
				success : function(response){
					$("#tdsOffering").val("");
				     $("#tdsTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			        	tableRecord += '<tr id="'+response[i].id+'_addTradingSoftwareDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].assetClass+'</td><td>'+response[i].accessibility+'</td><td>'+response[i].addOns+'</td><td>'+response[i].appDesc+'</td><td>'+response[i].appName+'</td><td>'+response[i].costType+'</td><td>'+response[i].launchedYear+'</td><td>'+response[i].opeSystem+'</td><td>'+response[i].tradeType+'</td><td>'+response[i].platformCCY+'</td><td>'+response[i].suitability+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
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
		   
		    var existingClientBase = $("#rcExistingClientBase").val();
			var totalResearchAnalyst = $("#rcTotalResearchAnalyst").val();
		    var researchPreparedbyCFA = $("#rcResearchPreparedbyCFA").val();
		    var existingClientBase = $("#rcExistingClientBase").val();
		    
		    var offeringDesc = $("#rcOfferingDesc").val();
		    var researchArea = $("#rcResearchArea").val();
		    var researchSubArea = $("#rcResearchSubArea").val();
		    
			if(checkMandotrySelectValue(solution) && checkNullValue(offering) && checkMandotrySelectValue(researchSubArea)){
				var solnText = $("#rcSolution option:selected").text();
				if(checkExistingRecordVendors('rcTable',0,1,solnText,offering))
      				   return;
			
			$("#rcOffering").val("");
			$.ajax({
				type: 'GET',
				url:  "addResearchCoverage?solution="+solution+"&regionsCovered="+regionsCovered+"&offering="+offering+"&totalResearchAnalyst="+totalResearchAnalyst+
				"&existingClientBase="+existingClientBase+"&offeringDesc="+offeringDesc+"&researchArea="+researchArea+"&researchSubArea="+researchSubArea,
				cache:false,
				success : function(response){

				     $("#rcTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			        	tableRecord += '<tr id="'+response[i].id+'_addResearchCoverage"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].regionsCovered+'</td><td>'+response[i].totalResearchAnalyst+'</td><td>'+response[i].existingClientBase+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].researchArea+'</td><td>'+response[i].researchSubArea+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>'; 
			       	                                                                                                                           
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
		var reportSubscriptionCost = $("#rdReportSubscriptionCost").val();
		var reportSubscriptionType = $("#rdReportSubscriptionType").val();
		var reportFormat = $("#rdReportFormat").val();
		var researchApplicableYear = $("#rdResearchApplicableYear").val();
		var researchApplicableMonth = $("#rdResearchApplicableMonth").val();
		var existingUserBase = false;

		
		var subsriptionCostUSDpermonth = $("#rdSubsriptionCostUSDpermonth").val();
		var subsriptionCostUSDperannum = $("#rdSubsriptionCostUSDperannum").val();
		
		if(checkMandotrySelectValue(solution) && checkMandotrySelectValue(offering) && checkMandotrySelectValue(accessibility) && checkMandotrySelectValue(reportCostType)){

			var solnText = $("#rdSolution option:selected").text();
			var offeringtxt = $("#rdOffering option:selected").text();
			if(checkExistingRecordVendors('rdTable',0,1,solnText,offeringtxt))
  				   return;
		
		     $("#rdOffering").val("");
			
			$.ajax({
				type: 'GET',
				url:  "addResearchDetails?solution="+solution+"&offering="+offering+"&researchReportName="+researchReportName+"&researchReportDesc="+researchReportDesc+"&accessibility="+accessibility+
				"&suitability="+suitability+"&reportCostType="+reportCostType+"&subsriptionCostUSDperannum="+subsriptionCostUSDperannum+"&reportSubscriptionCost="+reportSubscriptionCost+"&subsriptionCostUSDpermonth="+subsriptionCostUSDpermonth
				+"&reportFormat="+reportFormat+"&researchApplicableYear="+researchApplicableYear+"&researchApplicableMonth="+researchApplicableMonth,
				cache:false,
				success : function(response){

				     $("#rdTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			        	tableRecord += '<tr id="'+response[i].id+'_addResearchDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].researchReportName+'</td><td>'+response[i].researchReportDesc+'</td><td>'+response[i].accessibility+'</td><td>'+
				       	 response[i].suitability+'</td><td>'+response[i].reportCostType+'</td><td>'+response[i].reportFormat+'</td><td>'+response[i].researchApplicableMonth+','+response[i].researchApplicableYear+'</td><td>'+response[i].subsriptionCostUSDpermonth+'</td><td>'+response[i].subsriptionCostUSDperannum+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
			       	    
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
		    
		    var researchAnalystWithCFA = false;
			if ($('#apResearchAnalystWithCFA').is(":checked"))
			{
				researchAnalystWithCFA=true;
			}

		    var analystRegionofIncorp = $("#apAnalystRegionofIncorp").val();
		    var analystCountryofIncorp = $("#apAnalystCountryofIncorp").val();
			var analystYearofExp = $("#apAnalystYearofExp").val();
			var analystAwards = $("#apAnalystAwards").val();
			
			if(checkMandotrySelectValue(solution) && checkMandotrySelectValue(offering) && checkNullValue(analystName)){
				var solnText = $("#apSolution option:selected").text();
				var offeringtxt = $("#apOffering option:selected").text();
				if(checkExistingRecordVendors('rpTable',0,1,solnText,offeringtxt))
	  				   return;
			
			     $("#apOffering").val("");
			
				
			$.ajax({
				type: 'GET',                                                                                                                                                                               
				url:  "addAnalystProfile?solution="+solution+"&offering="+offering+"&researchArea="+researchArea+"&researchSubArea="+researchSubArea+"&analystName="+analystName+"&researchAnalystWithCFA="+researchAnalystWithCFA+"&analystRegionofIncorp="+analystRegionofIncorp+"&analystCountryofIncorp="+analystCountryofIncorp+"&analystYearofExp="+analystYearofExp+"&analystAwards="+analystAwards,
				cache:false,
				success : function(response){

				     $("#rpTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                   
			        	tableRecord += '<tr id="'+response[i].id+'_addAnalystProfile"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].analystName
				       	 +'</td><td>'+response[i].researchAnalystWithCFA+'</td><td>'+response[i].analystRegionofIncorp+'</td><td>'+response[i].analystCountryofIncorp+'</td><td>'+response[i].analystYearofExp+'</td><td>'+response[i].analystAwards+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'analystProfile\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
				       	 	                                                                                                                           
			        }
			        $("#rpTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
			
			}else{
				alert("Please enter mandatory field");
			}
			
		});

	 $(document).on("click", "#asdSubmit", function (e){
		 debugger;
		    var solution = $("#asdSolution").val();
		    var offering = $("#asdOffering").val();
		    var analyticsSolutionsSubType = $("#adsAnalyticsSolutionsSubType").val();
		 	var offeringDesc = $("#asdOfferingDesc").val();
			var applicationName = $("#asdApplicationName").val();
		    var applicationBriefDesc = $("#asdApplicationBriefDesc").val();
		    var accessibility = $("#asdAccessibility").val();
		    var suitability = $("#asdSuitability").val();
		    var applicationCostType = $("#asdApplicationCostType").val();
		    var applicationSubscriptionCCY = $("#asdApplicationSubscriptionAnnum").val();
			var applicationSubscriptionCost = $("#asdApplicationSubscriptionCost").val();
			var applicationSubscriptionType = $("#asdApplicationSubscriptionType").val();
		    var realtimeMarketData = $("#asdRealtimeMarketData").val();
		    
		    var realtimeMarketData = false;
			if ($('#asdRealtimeMarketData').is(":checked"))
			{
				realtimeMarketData=true;
			}
		    
		    var customizableCalculationModels = false;
			if ($('#asdCustomizableCalculationModels').is(":checked"))
			{
				customizableCalculationModels=true;
			}
		    
		    var addOns = $("#asdAddOns").val();
		    var operatingSystem = $("#asdOperatingSystem").val();
		    var softwareSpecifications = $("#asdSoftwareSpecifications").val();
			var launchedYear = $("#asdLaunchedYear").val();
			var existingUserBase = $("#asdExistingUserBase").val();
			 if(checkMandotrySelectValue(solution) && checkMandotrySelectValue(offering) && checkMandotrySelectValue(accessibility) && checkMandotrySelectValue(asdApplicationCostType)){
				 
					var solnText = $("#asdSolution option:selected").text();
					var offeringtxt = $("#asdOffering option:selected").text();
					if(checkExistingRecordVendors('asdTable',0,1,solnText,offeringtxt))
		  				   return;
				
				     $("#asdOffering").val("");
				 
			$.ajax({
				type: 'GET',
				url:  "addAnalyticsSoftwareDetails?solution="+solution+"&offering="+offering+"&offeringDesc="+offeringDesc+"&applicationName="+applicationName+"&applicationBriefDesc="+applicationBriefDesc+
				"&accessibility="+accessibility+"&suitability="+suitability+"&applicationCostType="+applicationCostType+"&applicationSubscriptionCCY="+applicationSubscriptionCCY+"&applicationSubscriptionCost="+applicationSubscriptionCost
				+"&applicationSubscriptionType="+applicationSubscriptionType+"&realtimeMarketData="+realtimeMarketData+"&customizableCalculationModels="+customizableCalculationModels+"&addOns="+addOns+"&operatingSystem="+operatingSystem
				+"&softwareSpecifications="+softwareSpecifications+"&analyticsSolutionsSubType="+analyticsSolutionsSubType+"&launchedYear="+launchedYear+"&existingUserBase="+existingUserBase,
				
				cache:false,
				success : function(response){

				     $("#asdTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_addAnalyticsSoftwareDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].applicationName+'</td><td>'+
			       	 response[i].accessibility+'</td><td>'+response[i].applicationCostType+'</td><td>'+response[i].applicationSubscriptionCCY+'</td><td>'+response[i].applicationSubscriptionCost+'</td><td>'+response[i].operatingSystem+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
			       	 
			        }
			        $("#asdTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
				},
				error : function(errorMsg, textStatus, jqXHR){
					// alert('Error: '+errorMsg+':'+textStatus);
				}
			});
			
			}else{
				alert("Please enter mandatory fields");
			}
			
		});
	 
	 $(document).on("click", "#tcsSubmit", function (e){
		 debugger;
		    var solution = $("#tcsSolution").val();
		    var offering = $("#tcsOffering").val();
			var tradeCoverageRegion = $("#tcsTradeCoverageRegion").val();
			var tradeCoverageCountry = $("#tcsTradeCoverageCountry").val();
			var tradableMarkets = $("#tcsTradableMarkets").val();
		    var tradingCapabilitiesType = $("#tcsTradingCapabilitiesType").val();
		    var tradeExecutionsType = $("#tcsTradeExecutionsType").val();
		    var algorithmicTradeType = $("#tcsAlgorithmicTradeType").val();
		    var darkpoolAccess = $("#tcsDarkpoolAccess").val();
		    var supportedDarkpoolVenues = $("#tcsSupportedDarkpoolVenues").val();
		    if(checkMandotrySelectValue(supportedDarkpoolVenues)) 
		    	darkpoolAccess=darkpoolAccess+"-"+supportedDarkpoolVenues;
			 if(checkMandotrySelectValue(solution) && checkMandotrySelectValue(offering) && checkMandotrySelectValue(tradeCoverageRegion) && checkMandotrySelectValue(tradeCoverageCountry)){
		    
					var solnText = $("#tcsSolution option:selected").text();
					var offeringtxt = $("#tcsOffering option:selected").text();
					if(checkExistingRecordVendors('tcsTable',0,1,solnText,offeringtxt))
		  				   return;
				
				     $("#tcsOffering").val("");
				 
			$.ajax({
				type: 'GET',
				url:  "addTradingCapabilitiesSupported?solution="+solution+"&offering="+offering+"&tradeCoverageRegion="+tradeCoverageRegion+"&tradeCoverageCountry="+tradeCoverageCountry+"&tradableMarkets="+tradableMarkets+"&tradingCapabilitiesType="+tradingCapabilitiesType+
				"&tradeExecutionsType="+tradeExecutionsType+"&algorithmicTradeType="+algorithmicTradeType+"&darkpoolAccess="+darkpoolAccess,
				cache:false,
				success : function(response){
				     $("#tcsTable tbody").empty();	 
			        var tableRecord = "";
			        for(i =0 ; i < response.length ; i++){                                                                                    
			       	 tableRecord += '<tr id="'+response[i].id+'_addTradingCapabilitiesSupported"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].tradeCoverageRegion+'</td><td>'+response[i].tradeCoverageCountry+'</td><td>'+response[i].tradableMarkets+'</td><td>'+response[i].tradingCapabilitiesType+'</td><td>'+response[i].tradeExecutionsType+'</td><td>'+response[i].algorithmicTradeType+'</td><td>'+response[i].darkpoolAccess+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
			       	                                                                                                                           
			        }
			        $("#tcsTable tbody").append(tableRecord);
	
					alert('You have updated sucessfully..!');
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
		 var fileId = $("#fileUploadmyoffercoverage").val();
		 if(fileId != null && fileId.length > 0){
			 loadUploadFiles(fileId);	 
		 } else {
		 	alert("Please choose file to upload");
		 	return;
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
			    
			    
			    if(checkMandotrySelectValue(solutionDataDistribution) && checkMandotrySelectValue(offeringDataDistribution) && checkMandotrySelectValue(frequency) && checkMandotrySelectValue(distributionmethod) && checkMandotrySelectValue(fileDataCoverage) &&  checkMandotrySelectValue(feedsubtype) && checkMandotrySelectValue(feedtype)){
		         
				$.ajax({
					type: 'GET',
					url:  "addVendorDataDistribution?solution="+solutionDataDistribution+"&offering="+offeringDataDistribution+"&fileDataCoverage="+fileDataCoverage+"&feedtype="+feedtype+"&feedsubtype="+feedsubtype+"&distributionmethod="+distributionmethod+"&frequency="+frequency,
					cache:false,
					success : function(response){
						if(checkNullValue(response[0].recordExist))
						{
							alert(response[0].recordExist);
						}else{
						    $("#dataDistributionTable tbody").empty();	 
					        var tableRecord = "";
					        for(i =0 ; i < response.length ; i++){                                                                                    
					       	 tableRecord += '<tr id="'+response[i].id+'_deleteVendorDataDistribution"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringFiles+'</td><td>'+response[i].feedType+'</td><td>'+response[i].feedSubType+'</td><td>'+response[i].distributionMethod+'</td><td>'+response[i].frequency+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
					       	                                                                                                                           
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
			 /*$(document).on("click", ".fileupmyoffercoverage", function (e) {
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
				});*/
			
			 
			 
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
				 /*$(document).on("click", ".fileupmyoffercoverage", function (e) {
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
					});*/
				
		
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







function listResearchDetails(){
	 debugger;
	
	$.ajax({
			type: 'GET',
			url:  "addResearchDetails",
			cache:false,
			success : function(response){

			     $("#rdTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		        	tableRecord += '<tr id="'+response[i].id+'_addResearchDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].researchReportName+'</td><td>'+response[i].researchReportDesc+'</td><td>'+response[i].accessibility+'</td><td>'+
			       	 response[i].suitability+'</td><td>'+response[i].reportCostType+'</td><td>'+response[i].reportFormat+'</td><td>'+response[i].researchApplicableMonth+','+response[i].researchApplicableYear+'</td><td>'+response[i].subsriptionCostUSDpermonth+'</td><td>'+response[i].subsriptionCostUSDperannum+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
		       	                                                                                                                           
		        }
		        $("#rdTable tbody").append(tableRecord);

			},
			error : function(errorMsg, textStatus, jqXHR){
				// alert('Error: '+errorMsg+':'+textStatus);
			}
		});
}

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

		
		
		
	}else if(tabmode != '' && tabmode.length > 0 && tabmode.match("supportcoverage")){

		
		  
	} else if(tabmode != '' && tabmode.length > 0 && tabmode.match("awarddetails")){
		$(".nav-tabs li").removeClass("active");
		$(".nav-tabs .awards_details").parent().addClass("active");
		listVendorAward();
		
	} else if(tabmode != '' && tabmode.length > 0 && tabmode.match("searchdatabuyers")){

	} else if(tabmode != '' && tabmode.length > 0 && tabmode.match("myrfp")){
		
		 
	} else if(tabmode != '' && tabmode.length > 0 && tabmode.match("tradingApplication")){
		$(".nav-tabs li").removeClass("active");
		$(".nav-tabs #myofferings2").parent().addClass("active");
		//listVendorDataAggregator();
	}
}
/// My Data Aggregator Vendor tab mode changes
function activeModeVendorMyofferings(tabmode){
	//$(".nav-tabs li").removeClass("active");
	//$(".nav-tabs " + tabmode).parent().addClass("active");
	if(tabmode != '' && tabmode.length > 0 && tabmode.match("tab1")){
		$(tabmode).show();
		$("#tab2").hide();
		$("#tab3").hide();
		$("#tab4").hide();
	} else if(tabmode != '' && tabmode.length > 0 && tabmode.match("tab2")){
		$("#tab1").hide();
		$(tabmode).show();
		$("#tab3").hide();
		$("#tab4").hide();
	} else if(tabmode != '' && tabmode.length > 0 && tabmode.match("tab3")){
		$("#tab1").hide();
		$("#tab2").hide();
		$(tabmode).show();
		$("#tab4").hide();
	} else if(tabmode != '' && tabmode.length > 0 && tabmode.match("tab4")){
		$("#tab1").hide();
		$("#tab2").hide();
		$("#tab3").hide();
		$(tabmode).show();
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
	debugger;
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
		listResearchDetails();		  
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
		listAnalystProfile();
	}
}

///Vendor My profile information updation start here-----------------------------------------------------------------------------

////File validation while uploading image-------------------

function validateYear(year){
	if(year != '' && !(year.match(/^\d{4}$/))){
		$(".generic_message .alert").addClass("alert-danger").text("Please enter valid year").show();
	} else if(year.length === 4) {
		$(".generic_message .alert").removeClass("alert-danger").text("").hide();
	}
}

function imageValidation() {
	//debugger;
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
	    
	      $('#displayLogo').html(data);
	      $('#profile-picture').html(data);

	    }
	  });
	
	
}

 
/// Update code to Vendor personal Info--:
function updateVendorPersonalInfo(){
	progressLoader(true);
	var personalvenfirstname = $("#personalvenfirstname").val();
	var personalvenlastname = $("#personalvenlastname").val();
	var personalvendesignation = $("#personalvendesignation").val();
	var personalvencompany = $("#personalvencompany").val();
	var personalvencompanyurl = $("#personalvencompanyurl").val();
	var personalvencompanyinfo = $("#personalvencompanyinfo").val();
	var personalvenprimemail = $("#personalvenprimemail").val();
	var personalvensecemail = $("#personalvensecemail").val();
	var personalvenphonenumbercode = $("#personalvenphonenumbercode").val();
	var personalvenphonenumber = $("#personalvenphonenumber").val();
	
	var personalvenregionofincorp = $("#personalvenregionofincorp").val();
	var personalvencountryofincorp = $("#personalvencountryofincorp").val();
	var personalvencompanylogo = $("ul li #personalvencompanylogo").val();
	
	var support = "";
	var selected = $("input[type='radio'][name='support']:checked");
	if (selected.length > 0) {
		support = selected.val();
	}

	var weekend = $('#weekend').is(":checked");
	var publicHolidays = $('#publicHolidays').is(":checked");

	if(personalvenfirstname != '') {
		$("#personal_details #personalvenfirstname").removeClass("error_field");
	} else {
		$("#personal_details #personalvenfirstname").addClass("error_field");
	}

	if(personalvendesignation != '') {
		$("#personal_details #personalvendesignation").removeClass("error_field");
	} else {
		$("#personal_details #personalvendesignation").addClass("error_field");
	}

	if(personalvencompany != '') {
		$("#personal_details #personalvencompany").removeClass("error_field");
	} else {
		$("#personal_details #personalvencompany").addClass("error_field");
	}

	if(personalvencompanyurl != '') {
		$("#personal_details #personalvencompanyurl").removeClass("error_field");
	} else {
		$("#personal_details #personalvencompanyurl").addClass("error_field");
	}

	if(personalvencompanyinfo != '') {
		$("#personal_details #personalvencompanyinfo").removeClass("error_field");
	} else {
		$("#personal_details #personalvencompanyinfo").addClass("error_field");
	}

	if(personalvenprimemail != '') {
		$("#personal_details #personalvenprimemail").removeClass("error_field");
	} else {
		$("#personal_details #personalvenprimemail").addClass("error_field");
	}

	if(personalvenphonenumbercode != '') {
		$("#personal_details #personalvenphonenumbercode").removeClass("error_field");
	} else {
		$("#personal_details #personalvenphonenumbercode").addClass("error_field");
	}

	if(personalvenphonenumber != '' && personalvenphonenumber.length >= 10) {
		$("#personal_details #personalvenphonenumber").removeClass("error_field");
	} else {
		$("#personal_details #personalvenphonenumber").addClass("error_field");
	}
	
	if(personalvenfirstname != '' && personalvenfirstname.length > 0 &&
			personalvendesignation != null && personalvendesignation.length > 0 &&
			personalvencompany != null && personalvencompany.length > 0 &&
			personalvencompanyurl != null && personalvencompanyurl.length > 0 && 
			personalvencompanyinfo != null && personalvencompanyinfo.length > 0 &&
			personalvenprimemail != null && personalvenprimemail.length >0 &&
			personalvenphonenumber != null && personalvenphonenumber.length >= 10){
		var url = "updateVendorPersonalTabInfo?venFirstname="+personalvenfirstname+"&venLastname="+personalvenlastname+"&venDesignation="+personalvendesignation
			                                            +"&venCompany="+personalvencompany+"&venCompanyUrl="+personalvencompanyurl+"&venCompanyInfo="+personalvencompanyinfo
			                                            +"&venPrimEmail="+personalvenprimemail+"&venSecEmail="+personalvensecemail+"&venPhoneNum="+personalvenphonenumbercode + "-" + personalvenphonenumber
			                                            +"&venRegionOfIncorp="+personalvenregionofincorp+"&venCountryOfIncorp="+personalvencountryofincorp+"&venCompanyLogo="+personalvencompanylogo
			                                            +"&support="+support+"&weekend="+weekend+"&publicHolidays="+publicHolidays;
		$.ajax({
			type: 'GET',
			url:  url,
			cache:false,
			/*contentType: 'multipart/form-data',*/
			success : function(output){
				progressLoader(false);
				setTimeout(function() {
					$("#personal_details .generic_message .alert").removeClass("alert-danger").addClass("alert-success").text('').hide();

					$("#personal_details").slideUp();
					$("#top-card").slideDown();
					$(".profile-card .full-name").html(personalvenfirstname + " " + personalvenlastname);
					$(".profile-card .headline").html(personalvendesignation + " at " + personalvencompany);
					$(".profile-card .contacts").html(personalvenprimemail + " | " + personalvenphonenumbercode + " " + personalvenphonenumber);
					$(".profile-card .company-details .url").html(personalvencompanyurl);
					$(".profile-card .company-details .info").html(personalvencompanyinfo);

					$(".profile-card .profile-picture img").attr('src', $("#displayLogo img").attr("src"));
				}, 10);
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
				progressLoader(false);
			}
		});
	}else{
		$("#personal_details .generic_message .alert").addClass("alert-danger").text('Please enter all mandatory fields..!').show();
		return false;
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
		alert("Please Enter Mandatory value");
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
	if(checkNullValue(offeringName) && checkNullValue(launchedYear)){
		if(checkExistingRecordSingleColumn('vendorofferingTable',0,offeringName))
			return
	
	$("#offeringName").val("");	
		
		$.ajax({
			type: 'GET',
			url:  "createOfferings?solution="+solutionForVendorOffering+"&offeringName="+offeringName+"&description="+descriptionForVendorOffering+"&assetClass="+assetClassForVenderOffering+"&launchedYear="+launchedYear,
			cache:false,
			success : function(response){
				
			     $("#vendorofferingTable tbody").empty();	 
		        var tableRecord = "";
		        for(i =0 ; i < response.length ; i++){                                                                                    
		       	 tableRecord += '<tr id="'+response[i].id+'__deleteRecordOffering"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].securityType+'</td><td>'+response[i].launchedYear+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
		       	                                                                                                                           
		        }
		        $("#vendorofferingTable tbody").append(tableRecord);

				alert('You have updated sucessfully..!');
				location.reload();
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
		
		if(checkExistingRecordSingleColumn('offeringFilesTable',0,fileName))
			return
	
	$("#fileName").val("");
		
		$.ajax({
			type: 'GET',
			url:  "addOfferingFiles?selectedId="+selectedId+"&fileName="+fileName+"&description="+description+"&securityType="+securityType,
			cache:false,
			success : function(response){
				 $("#offeringFilesTable tbody").empty();
		         var tableRecord = "";
		         for(i =0 ; i < response.length ; i++){                                                                                    
		        	 tableRecord += '<tr id="'+response[i].id+'_deleteRecordFile"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].securityType+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
		        	                                                                                                                           
		         }
		         $("#offeringFilesTable tbody").append(tableRecord);
				alert('You have updated sucessfully..!');
				location.reload();
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
		
		if(checkExistingRecordSingleColumn('offeringFilesFieldTable',0,fieldName))
			return
	
	$("#fieldName").val("");
		
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
		       	 tableRecord += '<tr id="'+response[i].id+'_deleteFieldsFile"><td>'+response[i].name+'</td><td>'+response[i].description+'</td><td>'+response[i].fieldIndex+'</td><td>'+response[i].fieldMaxLength+'</td><td>'+response[i].fieldFormat+'</td><td>'+response[i].fieldDataType+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
		       	                                                                                                                        
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
				
			     $("#vendorofferingTable tbody").empty();	 
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
       	 tableRecord += '<tr id="'+response[i].id+'_deleteVendorDataCoverage"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].region+'</td><td>'+response[i].country+'</td><td>'+response[i].coverageexchange+'</td><td>'+response[i].cost+'</td><td>'+response[i].phonNo+'</td><td>'+response[i].email+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
       	                                                                                                                           
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
	       	 tableRecord += '<tr id="'+response[i].id+'_deleteVendorDataDistribution"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringFiles+'</td><td>'+response[i].feedType+'</td><td>'+response[i].feedSubType+'</td><td>'+response[i].distributionMethod+'</td><td>'+response[i].frequency+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
	       	                                                                                                                           
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
		        	tableRecord += '<tr id="'+response[i].id+'_addTradingSoftwareDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].assetClass+'</td><td>'+response[i].accessibility+'</td><td>'+response[i].addOns+'</td><td>'+response[i].appDesc+'</td><td>'+response[i].appName+'</td><td>'+response[i].costType+'</td><td>'+response[i].launchedYear+'</td><td>'+response[i].opeSystem+'</td><td>'+response[i].tradeType+'</td><td>'+response[i].platformCCY+'</td><td>'+response[i].suitability+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
		       	                                                                                                                           
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
     	 tableRecord += '<tr id="'+response[i].id+'_addTradingCapabilitiesSupported"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].tradeCoverageRegion+'</td><td>'+response[i].tradeCoverageCountry+'</td><td>'+response[i].tradableMarkets+'</td><td>'+response[i].tradingCapabilitiesType+'</td><td>'+response[i].tradeExecutionsType+'</td><td>'+response[i].algorithmicTradeType+'</td><td>'+response[i].darkpoolAccess+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';                                                                                                                         
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
	       	 tableRecord += '<tr id="'+response[i].id+'_addAnalyticsSoftwareDetails"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].applicationName+'</td><td>'+
	       	 response[i].accessibility+'</td><td>'+response[i].applicationCostType+'</td><td>'+response[i].applicationSubscriptionCCY+'</td><td>'+response[i].applicationSubscriptionCost+'</td><td>'+
	       	 response[i].operatingSystem+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
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
	       	 tableRecord += '<tr id="'+response[i].id+'_addAnalystProfile"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].analystName
	       	 +'</td><td>'+response[i].researchAnalystWithCFA+'</td><td>'+response[i].analystRegionofIncorp+'</td><td>'+response[i].analystCountryofIncorp+'</td><td>'+response[i].analystYearofExp+'</td><td>'+response[i].analystAwards+'</td><td onclick="deleteRecord(\''+response[i].id+'\',\'analystProfile\')"><a class="deleteButton"> <img src="resources/images/delete.png"></a></td>';
	       	                                                                                                                           
	        }
	        $("#rpTable tbody").append(tableRecord);

		},
		error : function(errorMsg, textStatus, jqXHR){
			// alert('Error: '+errorMsg+':'+textStatus);
		}
	});

}

/*function getRegion(countryId,regionId){

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

function getCountryListMultiInfo(countryId, regionId) {

	var regionId = $("select[name=" + regionId + "]").selectpicker('val');

	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryTypesInfoMulti?VeMuRaYu="+regionId,
			cache:false,
			success : function(response){
				$("select[name=" + countryId + "]").empty()
				$("select[name=" + countryId + "]").append(response);	
				$("select[name=" + countryId + "]").selectpicker('refresh');
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}
}

function getExchangeList(countryId) {
	if(countryId != '' && countryId.length > 0){
		countryId = encode64(countryId);
		$.ajax({
			type: 'GET',
			url:  "loadExchangeAssetList?RaYuLU="+countryId,
			cache:false,
			success : function(response){
				$("select[name=coverageExchange]").empty()
				$("select[name=coverageExchange]").append(response);	
				$("select[name=coverageExchange]").selectpicker('refresh');	
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}

}*/

function getAssetClassList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=AssetClass",
		cache:false,
		success : function(response){
			window.localStorage.setItem('assetClassList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getAssetClassMapping(assetClassSelector) {
	var assetClassList = JSON.parse(window.localStorage.getItem('assetClassList'));
	var $option='';
	for (var val in assetClassList) {
		$option += "<option value='" + assetClassList[val].id + "'>" + assetClassList[val].name + "</option>";
	}
    $("select#" + assetClassSelector +"").empty();
    $("select[name=" + assetClassSelector + "]").append($option);	
    $("select[name=" + assetClassSelector + "]").selectpicker('refresh');
}

function getAssetClassMultipleListById(assetclassId) {
	var assetClassList = JSON.parse(window.localStorage.getItem('assetClassList'));
	var $option=[];
	for(var i=0; i < assetClassList.length; i++) {
		for(id in assetclassId) {
			if(assetClassList[i].id == assetclassId[id]) {
				$option.push(assetClassList[i].name);
			}
		}
	}
	return $option;
}

function getSecurityTypeList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=SecurityType",
		cache:false,
		success : function(response){
			window.localStorage.setItem('securityTypeList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getSecurityTypeMapping(securityTypeSelector) {
	var securityTypeList = JSON.parse(window.localStorage.getItem('securityTypeList'));
	var $option='';
	for (var val in securityTypeList) {
		$option += "<option value='" + securityTypeList[val].id + "'>" + securityTypeList[val].name + "</option>";
	}
    $("select#" + securityTypeSelector +"").empty();
    $("select[name=" + securityTypeSelector + "]").append($option);	
    $("select[name=" + securityTypeSelector + "]").selectpicker('refresh');
}

function getAssetClassSecurityTypeMapping(assetClassSelector, securityTypeSelector) {
	var securityTypeList = JSON.parse(window.localStorage.getItem('securityTypeList'));
	var assetClassId = $("select[name=" + assetClassSelector + "]").selectpicker('val');
	var $option='';
	for (var val in securityTypeList) {
		for(var id in assetClassId) {
			if (securityTypeList[val].parentId == assetClassId) {
				$option += "<option value='" + securityTypeList[val].id + "'>" + securityTypeList[val].name + "</option>";
			}    		
		}
	}
    $("select#" + securityTypeSelector +"").empty();
    $("select[name=" + securityTypeSelector + "]").append($option);	
    $("select[name=" + securityTypeSelector + "]").selectpicker('refresh');
}

function getRegionList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=Region",
		cache:false,
		success : function(response){
			window.localStorage.setItem('regionList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getRegionMapping(regionSelector) {
	var regionList = JSON.parse(window.localStorage.getItem('regionList'));
	var $option='';
	for (var val in regionList) {
		$option += "<option value='" + regionList[val].id + "'>" + regionList[val].name + "</option>";
	}
    $("select#" + regionSelector +"").empty();
    $("select[name=" + regionSelector + "]").append($option);	
    $("select[name=" + regionSelector + "]").selectpicker('refresh');
}

function getRegionMultipleListById(regionId) {
	var regionList = JSON.parse(window.localStorage.getItem('regionList'));
	var $option=[];
	for(var i=0; i < regionList.length; i++) {
		for(id in regionId) {
			if(regionList[i].id == regionId[id]) {
				$option.push(regionList[i].name);
			}
		}
	}
	return $option;
}

function getCountryList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=Country",
		cache:false,
		success : function(response){
			window.localStorage.setItem('countryList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getCountryMapping(countrySelector) {
	var countryList = JSON.parse(window.localStorage.getItem('countryList'));
	var $option='';
	for (var val in countryList) {
		$option += "<option value='" + countryList[val].id + "'>" + countryList[val].name + "</option>";
	}
    $("select#" + countrySelector +"").empty();
    $("select[name=" + countrySelector + "]").append($option);	
    $("select[name=" + countrySelector + "]").selectpicker('refresh');
}

function getRegionCountryMapping(regionSelector, countrySelector) {
	var countryList = JSON.parse(window.localStorage.getItem('countryList'));
	var regionId = $("select[name=" + regionSelector + "]").selectpicker('val');
	var $option='';
	for (var val in countryList) {
		for(var id in regionId) {
			if (countryList[val].parentId == regionId[id]) {
				$option += "<option value='" + countryList[val].id + "'>" + countryList[val].name + "</option>";
			}    		
		}
	}
    $("select#" + countrySelector +"").empty();
    $("select[name=" + countrySelector + "]").append($option);	
    $("select[name=" + countrySelector + "]").selectpicker('refresh');
}

function getCountryRegionMapping(countrySelector, regionSelector) {
	var countryList = JSON.parse(window.localStorage.getItem('countryList'));
	var regionList = JSON.parse(window.localStorage.getItem('regionList'));
	var countryId = $("select[name=" + countrySelector + "]").selectpicker('val');
	var $option='';
	for (var val in regionList) {
		for(var id in countryList) {
			if (countryId == countryList[id].id && regionList[val].id == countryList[id].parentId) {
				$option += "<option value='" + regionList[val].id + "'>" + regionList[val].name + "</option>";
			}    		
		}
	}
    $("select#" + regionSelector +"").empty();
    $("select[name=" + regionSelector + "]").append($option);	
    $("select[name=" + regionSelector + "]").selectpicker('refresh');
}

function getExchangeList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=Exchange",
		cache:false,
		success : function(response){
			window.localStorage.setItem('exchangeList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getExchangeMapping(exchangeSelector) {
	var exchangeList = JSON.parse(window.localStorage.getItem('exchangeList'));
	var $option='';
	for (var val in exchangeList) {
		$option += "<option value='" + exchangeList[val].id + "'>" + exchangeList[val].name + "</option>";
	}
    $("select#" + exchangeSelector +"").empty();
    $("select[name=" + exchangeSelector + "]").append($option);	
    $("select[name=" + exchangeSelector + "]").selectpicker('refresh');
}

function getCountryExchangeMapping(countrySelector, exchangeSelector) {
	var exchangeList = JSON.parse(window.localStorage.getItem('exchangeList'));
	var countryId = $("select[name=" + countrySelector + "]").selectpicker('val');
	var $option='';
    for (var val in exchangeList) {
    	for(var id in countryId) {
	        if (exchangeList[val].parentId == countryId[id]) {
	        	$option += "<option value='" + exchangeList[val].id + "'>" + exchangeList[val].name + "</option>";
	        }    		
    	}
    }
    $("select#" + exchangeSelector + "").empty();
    $("select[name="+ exchangeSelector +"]").append($option);	
    $("select[name="+ exchangeSelector +"]").selectpicker('refresh');
}

function getAnalyticalSolutionTypeList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=AnalyticalSolutionType",
		cache:false,
		success : function(response) {
			window.localStorage.setItem('analyticalSolutionTypeList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR) {
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getAnalyticalSolutionTypeMapping(analyticalSolutionTypeSelector) {
	var analyticalSolutionTypeList = JSON.parse(window.localStorage.getItem('analyticalSolutionTypeList'));
	var $option='';
	for (var val in analyticalSolutionTypeList) {
		$option += "<option value='" + analyticalSolutionTypeList[val].id + "'>" + analyticalSolutionTypeList[val].name + "</option>";
	}
    $("select#" + analyticalSolutionTypeSelector +"").empty();
    $("select[name=" + analyticalSolutionTypeSelector + "]").append($option);	
    $//("select[name=" + analyticalSolutionTypeSelector + "]").selectpicker('refresh');
}

function getAnalyticalSolutionSubTypeList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=AnalyticalSolutionSubType",
		cache:false,
		success : function(response) {
			window.localStorage.setItem('analyticalSolutionSubTypeList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR) {
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getAnalyticalSolutionSubTypeMapping(analyticalSolutionTypeSelector, analyticalSolutionSubTypeSelector) {
	var analyticalSolutionSubTypeList = JSON.parse(window.localStorage.getItem('analyticalSolutionSubTypeList'));
	var analyticSolutionTypeId = $("select[name=" + analyticalSolutionTypeSelector + "]").selectpicker('val');
	var $option='';
    for (var val in analyticalSolutionSubTypeList) {
    	for(var id in analyticSolutionTypeId) {
	        if (analyticalSolutionSubTypeList[val].parentId == analyticSolutionTypeId[id]) {
	        	$option += "<option value='" + analyticalSolutionSubTypeList[val].id + "'>" + analyticalSolutionSubTypeList[val].name + "</option>";
	        }    		
    	}
    }
    $("select#" + analyticalSolutionSubTypeSelector +"").empty();
    $("select[name=" + analyticalSolutionSubTypeSelector + "]").append($option);	
    $("select[name=" + analyticalSolutionSubTypeSelector + "]").selectpicker('refresh');
}

function getResearchAreaList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=ResearchArea",
		cache:false,
		success : function(response) {
			window.localStorage.setItem('researchAreaList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR) {
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getResearchAreaMapping(rcResearchAreaSelector) {
	var researchAreaList = JSON.parse(window.localStorage.getItem('researchAreaList'));
	var $option='';
	for (var val in researchAreaList) {
		$option += "<option value='" + researchAreaList[val].id + "'>" + researchAreaList[val].name + "</option>";
	}
    $("select#" + rcResearchAreaSelector +"").empty();
    $("select[name=" + rcResearchAreaSelector + "]").append($option);	
//    $("select[name=" + rcResearchAreaSelector + "]").selectpicker('refresh');
}

function getResearchSubAreaList() {
	$.ajax({
		type: 'GET',
		url:  "getJsonReferenceData?referenceDataType=ResearchSubArea",
		cache:false,
		success : function(response) {
			window.localStorage.setItem('researchSubAreaList', JSON.stringify(response));
		},
		error : function(data, textStatus, jqXHR) {
				//alert('Error: '+data+':'+textStatus);
			}
		});
}

function getResearchSubAreaMapping(rcResearchAreaSelector, rcResearchSubAreaSelector) {
	var researchSubAreaList = JSON.parse(window.localStorage.getItem('researchSubAreaList'));
	var rcResearchAreaId = $("select[name=" + rcResearchAreaSelector + "]").selectpicker('val');
	var $option='';
    for (var val in researchSubAreaList) {
    	for(var id in rcResearchAreaId) {
	        if (researchSubAreaList[val].parentId == rcResearchAreaId[id]) {
	        	$option += "<option value='" + researchSubAreaList[val].id + "'>" + researchSubAreaList[val].name + "</option>";
	        }    		
    	}
    }
    $("select#" + rcResearchSubAreaSelector +"").empty();
    $("select[name=" + rcResearchSubAreaSelector + "]").append($option);	
    $("select[name=" + rcResearchSubAreaSelector + "]").selectpicker('refresh');
}

function changeTabMode(comp){
	 //debugger; 
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
		 
	 }else if(window.location.href.indexOf("vendorMyStats") != -1){
		 $("#myStats").attr("class", "active");
		 $("#solution").attr("class", "#");
		 $("#myOfferings").attr("class", "#");
		 $("#myProfile").attr("class", "#");
		 $("#myBlog").attr("class", "#");
	 }else if(window.location.href.indexOf("vendorMyBlogs") != -1){
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




function listResearchCoverage(){
	 debugger;
	 			
		$.ajax({
			type: 'GET',
			url:  "addResearchCoverage",
			cache:false,
			success : function(response){
				$("#rcTable tbody").empty();	 
			    var tableRecord = "";
			    for(i =0 ; i < response.length ; i++){                                                                                    
		        	 tableRecord += '<tr id="'+response[i].id+'_addResearchCoverage"><td>'+response[i].solution+'</td><td>'+response[i].offering+'</td><td>'+response[i].regionsCovered+'</td><td>'+response[i].totalResearchAnalyst+'</td><td>'+response[i].existingClientBase+'</td><td>'+response[i].offeringDesc+'</td><td>'+response[i].researchArea+'</td><td>'+response[i].researchSubArea+'</td><td><a class="deleteButton"> <img src="resources/images/delete.png"></a></td></tr>';
		        }
		        $("#rcTable tbody").append(tableRecord);
			},
			error : function(errorMsg, textStatus, jqXHR){
				// alert('Error: '+errorMsg+':'+textStatus);
			}
		});
}



//Add Vendor Award Details 
addVendorAward = function() {
	
    var awardname = $("#awardname").val().trim();
    var awardsponsor = $("#awardsponsor").val().trim();
    var awardedyear = $("#awardedyear").val().trim();
    
    var awardVendorType = $("#awardVendorType").selectpicker('val');
    var awardAssetclass = $("#awardAssetclass").selectpicker('val');

    if(awardname != '') {
    	$("#awardname").removeClass("error_field");
    } else {
    	$("#awardname").addClass("error_field");
    }

    if(awardsponsor != '') {
    	$("#awardsponsor").removeClass("error_field");
    } else {
    	$("#awardsponsor").addClass("error_field");
    }

    if(awardedyear.length == 4) {
    	$("#awardedyear").removeClass("error_field");
    } else {
    	$("#awardedyear").addClass("error_field");
    }
    
    if( awardname != ''	&& awardsponsor != '' && 
    	awardedyear.length === 4 ){
    	
    	if(checkExistingRecordVendors("awardsample_1",0,2,awardname,awardedyear))
    		return;
    	$("#awardname").val("");

    	var data = {
    		"awardname" : awardname,
    		"awardsponsor" : awardsponsor,
    		"awardedyear" : awardedyear
    	}
    	
		$.ajax({
			type: 'GET',
			url:  "updateVendorAwardDetails?awardname="+awardname+"&awardsponsor="+awardsponsor+"&awardedyear="+awardedyear+"&awardVendorType="+awardVendorType+"&awardAssetclass="+awardAssetclass,
			cache:false,
			success : function(response){
				var awardname = $("#awardname").val("");
				$("#awardsponsor").val("");
				$("#awardedyear").val("");
				$("#awards_top_card .awards_info").empty();	 
				var tableRecord = "";
				for(i =0 ; i < response.length ; i++){                                                                                    
					tableRecord += "<div class='award-list' id='" + response[i].id + "_awarddetails'>" +
										'<h3>'+response[i].name+'</h3>' +
										'<h4>'+response[i].description + ' | ' + response[i].awardVendorType +'</h4>' +
										'<h5>'+response[i].frequency+'</h5>' +
										"<a class='btn delete_btn'>Delete</a>" +
										'</div>'
				}
				$("#awards_top_card .awards_info").append(tableRecord);

				$("#award_details").slideUp();
				$("#awards_top_card").slideDown();
				$("#award_details .generic_message .alert").removeClass("alert-danger").addClass("alert-success").text('').hide();	
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	} else {
		$("#award_details .alert").removeClass("alert-success").addClass("alert-danger").text('Please enter mandatory fields').show();
	}
	
}

function checkExisitngAward(awardname,awardyear){
   var status = true;
	var url = ajaxUrl + "?param="+ awardname+","+awardyear+"&actionComponent="+actionComponent ;
	$.ajax({
		type: 'POST',
		url: url,
		async: false,
		cache: false,
		success: function(output) {
			if(output.lenght > 5)
			alert(output);
			status = false;
		}
	});
	return status;
}

openVendorAwardForm = function() {
	resetVendorAwardForm();
	$("#award_details").slideDown();
	$("#awards_top_card").slideUp();
}

resetVendorAwardForm = function() {
	$("#award_details .alert").hide();
	$("#award_details").trigger('reset');
	$('.selectpicker').selectpicker('refresh');
	$(".error_field").removeClass("error_field");
}


listVendorAward = function(){
	$("#award_details").slideUp();
	$("#awards_top_card").hide();
	progressLoader(true);

	$.ajax({
		type: 'GET',
		url:  "updateVendorAwardDetails",
		cache:false,
		success : function(response) {
			var totalCount = response.length;

			if(totalCount === 0) {
				progressLoader(false);
				openVendorAwardForm();
				return false;
			}

			$("#awards_top_card .awards_info").empty();	 
			var tableRecord = "";
			for(i =0 ; i < totalCount ; i++){                                                                                    
				tableRecord += "<div class='award-list' id='" + response[i].id + "_awarddetails'>" +
				'<h3>'+response[i].name+'</h3>' +
				'<h4>'+response[i].description + ' | ' + response[i].awardVendorType +'</h4>' +
				'<h5>'+response[i].frequency+'</h5>' +
				"<a class='btn delete_btn'>Delete</a>" +
				'</div>'
			}
			progressLoader(false);
			$("#awards_top_card .awards_info").append(tableRecord);
			$("#award_details").slideUp();
			$("#awards_top_card").slideDown();
		},
		error : function(data, textStatus, jqXHR){
			progressLoader(false);
			$("#data_aggregator .alert").removeClass("alert-success").addClass("alert-danger").text('Please try again after sometime').show();
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
	tdsCostTypeSelector();
});

function tdsCostTypeSelector() {
	if ($("#tdsCostType option[value='Subscription based']:selected").length > 0){
		document.getElementById("tdsPlatformCost").readOnly  = false;
		document.getElementById("tdsPlatformType").readOnly  = false;
	}else{
		document.getElementById("tdsPlatformCost").readOnly  = true;
		document.getElementById("tdsPlatformType").readOnly  = true;
		$("#tdsPlatformCost").val("");
		$("#tdsPlatformType").val("");
	}
}

$("#asdApplicationCostType").change(function() {
	 asdApplicationCostTypeSelector();
});

function asdApplicationCostTypeSelector() {
	if ($("#analytic_application #asdApplicationCostType option[value='Subscription based']:selected").length > 0){
		document.getElementById("asdApplicationSubscriptionCost").readOnly = false;
		document.getElementById("asdApplicationSubscriptionAnnum").readOnly = false;
	} else{
		document.getElementById("asdApplicationSubscriptionCost").readOnly = true;
		document.getElementById("asdApplicationSubscriptionAnnum").readOnly = true;
		$("#asdApplicationSubscriptionCost").val("");
		$("#asdApplicationSubscriptionAnnum").val("");
	}
}

$("#rdReportCostType").change(function() {
	rdReportCostTypeSelector();
});

function rdReportCostTypeSelector() {
	if ($("#rdReportCostType option[value='Subscription based']:selected").length > 0){
		document.getElementById("rdSubsriptionCostUSDpermonth").readOnly = false;
		document.getElementById("rdSubsriptionCostUSDperannum").readOnly  = false;
	}else{
		document.getElementById("rdSubsriptionCostUSDpermonth").readOnly =true;
		document.getElementById("rdSubsriptionCostUSDperannum").readOnly =true;
		$("#rdSubsriptionCostUSDpermonth").val("");
		$("#rdSubsriptionCostUSDperannum").val("");
	}
}


$("#tcsTradeExecutionsType").change(function() {
	 if ($("#tcsTradeExecutionsType option[value='Algorithmic & Program Trading']:selected").length > 0){
		 document.getElementById("tcsAlgorithmicTradeType").disabled = false;
	 }else{
		 document.getElementById("tcsAlgorithmicTradeType").disabled = true;
	 }
});

$("#tcsDarkpoolAccess").change(function() {
	 if ($("#tcsDarkpoolAccess option[value='No Darkpool Access']:selected").length > 0){
		 document.getElementById("tcsSupportedDarkpoolVenues").disabled = true;
	 }else{
		 document.getElementById("tcsSupportedDarkpoolVenues").disabled = false;
	 }
});


$("#apAnalystAwards").change(function() {
	 if ($("#apAnalystAwards option[value=Other]:selected").length > 0){
		 document.getElementById("apAnalystAwardsOthers").style.visibility = "visible";
	 }else{
		 document.getElementById("apAnalystAwardsOthers").style.visibility = "hidden";
	 }
});

/*$("#rdSuitability").change(function() {
	 if ($("#rdSuitability option[value=Others]:selected").length > 0){
		 document.getElementById("rdSuitabilityOthers").style.visibility = "visible";
	 }else{
		 document.getElementById("rdSuitabilityOthers").style.visibility = "hidden";
	 }
});*/


$("#awardVendorType").change(function() {
	 if ($("#awardVendorType option[value='Data Aggregator vendor']:selected").length > 0 || $("#awardVendorType option[value='Trading Application vendor']:selected").length > 0){
		 $("#assetClassDiv").show();
		 $("#analyticsSolutionsTypeDiv").hide();
		 $("#researchAreaDiv").hide();

	 }else if ($("#awardVendorType option[value='Analytics Application vendor']:selected").length > 0){
		 $("#assetClassDiv").hide();
		 $("#analyticsSolutionsTypeDiv").show();
		 $("#researchAreaDiv").hide();
	 }else if ($("#awardVendorType option[value='Research Reporting vendor']:selected").length > 0){
		 $("#assetClassDiv").hide();
		 $("#analyticsSolutionsTypeDiv").hide();
		 $("#researchAreaDiv").show();
	 }
});


function checkExistingRecordVendors(tableName,c1,c2,p1,p2){
	var status = false;
$("#"+tableName+" tr").each(function() {
	  var param1 = $(this).find('td:eq('+c1+')').text();
	  var param2 = $(this).find('td:eq('+c2+')').text();
	  
	  if(param1.trim() ==p1.trim() && param2.trim() ==p2.trim()){
		  alert("Record Already exist");
		  status = true;
		  return false;
	  }
	});
  return status;
}


function checkExistingRecordSingleColumn(tableName,c1,p1){
	var status = false;
$("#"+tableName+" tr").each(function() {
	  var param1 = $(this).find('td:eq('+c1+')').text();
	  
	  if(param1.trim() ==p1.trim()){
		  alert("Record Already exist");
		  status = true;
		  return false;
	  }
	});
  return status;
}


function progressLoader(isLoading) {
	if(isLoading) {
		$("#spinner").show();
	} else {
		$("#spinner").hide();
	}
}

function isValidEmailAddress(emailAddress) {
    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
    return pattern.test(emailAddress);
}