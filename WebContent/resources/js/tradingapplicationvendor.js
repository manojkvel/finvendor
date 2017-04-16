
/**
 * @author Maninder Singh Rajpal
 * @date Jul 01, 2016
 * @emailId wonderfulmani@gmail.com
 * Copyrights: All Rights Reserved FinVendor
 */

$(document).ready(function() {

	var assetClassArray = [];
	var getAssetClassAggregators = function() {
		var costRange = $("select#acquisitioncostrange option").map(function() {return $(this).html();}).get();
		
		if($(this).prop('checked') == true) {
			assetClassArray.push($(this).attr('id').toLowerCase());
		} else {
			assetClassArray.splice($.inArray($(this).attr('id').toLowerCase(), assetClassArray), 1);
		}

		if(assetClassArray.length == 0) {
			$("#singleAsset").slideUp('slow');
		}

		if(assetClassArray.length > 3) {
			alert("Sorry, You cannot select more than 3 asset class.");
			console.log(assetClassArray);
			assetClassArray.splice($.inArray($(this).attr('id').toLowerCase(), assetClassArray), 1);
			console.log(assetClassArray);
			$(this).prop('checked', false);
			return;
		} else {
			$(".error").hide();
		}

		if(assetClassArray.length == 1) {
			var assetType = assetClassArray[0];
			singleAssetClass(assetType);
			$(".selectpicker").selectpicker('refresh');
			$("#singleAsset").slideDown('slow');
			$("#multipleAsset").slideUp('slow');
		}

		if(assetClassArray.length > 1) {
			multipleAssetData = '';
			for(var i=0;i<assetClassArray.length;i++) {
				multipleAssetClass(assetClassArray[i]);
				$(".selectpicker").selectpicker('refresh');
			}
			$("#singleAsset").slideUp('slow');
			$("#multipleAsset").slideDown('slow');
		}


		for(var i=0;i<assetClassArray.length;i++) {
			getRegionMapping(assetClassArray[i] + 'tcsTradeCoverageRegion');
			getSecurityTypeMapping(assetClassArray[i] + 'securityTypes');
			getCountryMapping(assetClassArray[i] + 'tcsTradeCoverageCountry');
			getExchangeMapping(assetClassArray[i] + 'tcsTradeCoverageExchange');


			$(".selectpicker").selectpicker('refresh');
		}
		console.log(assetClassArray + " : " + assetClassArray.length);
	};

	if(window.localStorage.getItem('assetClassArray') != undefined) {
		if($("#searchmultiform").is(':visible')) {
			assetClassArray = JSON.parse(window.localStorage.assetClassArray);
			console.log("LocalStorage : " + assetClassArray + " : " + assetClassArray.length);
			if(assetClassArray.length == 1) {
				var assetType = assetClassArray[0];
				singleAssetClass(assetType);
				$(".selectpicker").selectpicker('refresh');
				$("#singleAsset").slideDown('slow');
				$("#multipleAsset").slideUp('slow');
			}

			if(assetClassArray.length > 1) {
				multipleAssetData = '';
				for(var i=0;i<assetClassArray.length;i++) {
					multipleAssetClass(assetClassArray[i]);
					$(".selectpicker").selectpicker('refresh');
				}
				$("#singleAsset").slideUp('slow');
				$("#multipleAsset").slideDown('slow');
			}
			window.localStorage.clear();
		}
	}

	$(".assetClass").click(getAssetClassAggregators);

	$("select[name=vendorregionofincorp]").on('change', function() {
		getRegionCountryMapping('vendorregionofincorp', 'vendorcountryofincorp');
	});

	$(document).on('change', 'select', function() {
		var type = $(this)[0]['id'];
		if(type.indexOf('equities') != -1) {
			type = 'equities';
		} else if(type.indexOf('derivatives') != -1) {
			type = 'derivatives';
		} else if(type.indexOf('fixedincome') != -1) {
			type = 'fixedincome';
		} else if(type.indexOf('fx') != -1) {
			type = 'fx';
		} 
		if($(this)[0]['id'] == type + 'tcsTradeCoverageRegion') {
			getRegionCountryMapping(type + 'tcsTradeCoverageRegion', type + 'tcsTradeCoverageCountry');
			getCountryExchangeMapping(type + 'tcsTradeCoverageCountry', type + 'tcsTradeCoverageExchange');
		}

		if($(this)[0]['id'] == type + 'tcsTradeCoverageCountry') {
			getCountryExchangeMapping(type + 'tcsTradeCoverageCountry', type + 'tcsTradeCoverageExchange');
		}
	});

	$("select").on('change', function() {
		if($(this)[0]['id'] == 'equitiesdatacoverageregion') {
			//getRegionCountryMapping('equitiesdatacoverageregion', 'equitiesdatacoveragecountry');
		}
		//getRegionCountryMapping('vendorregionofincorp', 'vendorcountryofincorp');
	});

	$("#search_vendor").click(function(e) {
		if(assetClassArray.length == 0) {
			alert("Please select atleast 1 asset class.");
			e.preventDefault();
			return false;
		}
		window.localStorage.setItem('assetClassArray', JSON.stringify(assetClassArray));
	});

	$("#reset_vendor").on('click', function() {
		assetClassArray = [];
		$("#singleAsset").slideUp('slow');
		$("#multipleAsset").slideUp('slow');
		$('.selectpicker').selectpicker('deselectAll');
		$(".selectpicker").selectpicker('refresh');
	});

	var getCostRangeMapping = function(costRange, costRangeSelector) {
		$.each(costRange, function(key, value) {
			$("select[name=" + costRangeSelector + "]").append($("<option></option>")
                    .attr("value",key)
                    .text(value)); 
		});
	}
});

function singleAssetClass(assetType) {
	console.log("assetType : " + assetType);
	$('#singleAssetFields').html('');
	var singleAssetData = "<h3>"
	+ $("#" + assetType).siblings().html()
	+ "</h3>"
	+ "<ul>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "securityTypes' name='" + assetType + "securityTypes' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Security Type</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "tcsTradeCoverageRegion' name='" + assetType + "tcsTradeCoverageRegion' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Tradable Region</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "tcsTradeCoverageCountry' name='" + assetType + "tcsTradeCoverageCountry' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Tradable Country</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "tcsTradeCoverageExchange' name='" + assetType + "tcsTradeCoverageExchange' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Tradable Exchange</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' name='" + assetType + "tcsTradingCapabilitiesType' multiple='multiple' id='" + assetType + "tcsTradingCapabilitiesType'>"
	+ "<option value ='Order placement/execution' > Order placement/execution </option>"
	+ "<option value ='Order allocation' > Order allocation </option>"
	+ "<option value ='Trade settlement' > Trade settlement </option>"
	+ "<option value ='Trade Clearance' > Trade Clearance</option>"
	+ "<option value ='Real Time Quotes' > Real Time Quotes</option>"
	+ "<option value ='Real Time Financial News' > Real Time Financial News</option>"
	+ "<option value ='Trade Analytics' > Trade Analytics</option>"
	+ "<option value ='Collateral Management' > Collateral Management</option>"
	+ "<option value ='Listed stocks/contracts' > Listed stocks/contracts</option>"
	+ "<option value ='Electronic Trading for OTC' > Electronic Trading for OTC</option>"
	+ "<option value ='Order Management' > Order Management</option>"
	+ "<option value ='Trade Confirmation' > Trade Confirmation</option>"
	+ "<option value ='Trade Reporting' > Trade Reporting</option>"
	+ "<option value ='Trading Strategy Builder' > Trading Strategy Builder</option>"
	+ "<option value ='Liquidity Aggregation' > Liquidity Aggregation</option>"
	+ "<option value ='Market Surveillance & Compliance' > Market Surveillance & Compliance</option>"
	+ "<option value ='Ability to connect to liquidity providers' > Ability to connect to liquidity providers</option>"
	+ "<option value ='Algorithmic Signal Generation' > Algorithmic Signal Generation</option>"
	+ "<option value ='Algorithmic Order Execution& Mgmt' > Algorithmic Order Execution& Mgmt</option>"
	+ "<option value ='Auto Hedging & Risk Mgmt' > Auto Hedging & Risk Mgmt</option>"
	+ "<option value ='Time Series Modelling' > Time Series Modelling</option>"
	+ "<option value ='Market Data Distribution' > Market Data Distribution</option>"
	+ "<option value ='Pricing and Rates Engine' > Pricing and Rates Engine</option>"
	+ "<option value ='Quoting & Price Distribution' > Quoting & Price Distribution</option>"
	+ "</select>"
	+ "<label class='default_select' >Trading Capabilities Type</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' name='" + assetType + "tcsTradeExecutionsType' multiple='multiple' id='" + assetType + "tcsTradeExecutionsType'>"
	+ "<option value ='Algorithmic & Program Trading' > Algorithmic & Program Trading </option>"
	+ "<option value ='Direct Market Access (DMA)' > Direct Market Access (DMA) </option>"
	+ "<option value ='FX -DMA' > FX -DMA </option>"
	+ "<option value ='Smart Order Routing (Sequential SOR)'> Smart Order Routing (Sequential SOR) </option>"

	+ "<option value ='Smart Order Routing (Multi-Posting SOR)' > Smart Order Routing (Multi-Posting SOR) </option>"
	+ "<option value ='Swap Execution Facility' > Swap Execution Facility </option>"
	+ "</select>"
	+ "<label class='default_select' >Trade Executions Type</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "tdssubsriptioncostUSDperannum' name='" + assetType + "tdssubsriptioncostUSDperannum'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Subscription Cost - Annual(USD)</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "tdslaunchyear' name='" + assetType + "tdslaunchyear'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Launch Year</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "tdsawards' name='" + assetType + "tdsawards'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Awards</label>"
	+ "</li>"
	+ "<li>"
	+ "<input type='checkbox' name='tdsDarkpoolSupported' id='tdsDarkpoolSupported' />"
	+ "<label class='default_checkbox'>Darkpool Supported?</label>"
	+ "</li>"
	+ "</ul>"
	$('#singleAssetFields').append(singleAssetData);
}

function multipleAssetClass(assetType) {
	console.log("assetType : " + assetType);
	$('#multipleAsset #multipleAssetFields').html('');
	multipleAssetData = multipleAssetData
	+ "<h3>"
	+ $("#" + assetType).siblings().html()
	+ "</h3>"
	+ "<ul>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "securityTypes' name='" + assetType + "securityTypes' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Security Type</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "tcsTradeCoverageRegion' name='" + assetType + "tcsTradeCoverageRegion' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Tradable Region</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "tcsTradeCoverageCountry' name='" + assetType + "tcsTradeCoverageCountry' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Tradable Country</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "tcsTradeCoverageExchange' name='" + assetType + "tcsTradeCoverageExchange' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Tradable Exchange</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' name='" + assetType + "tcsTradingCapabilitiesType' multiple='multiple' id='" + assetType + "tcsTradingCapabilitiesType'>"
	+ "<option value ='Order placement/execution' > Order placement/execution </option>"
	+ "<option value ='Order allocation' > Order allocation </option>"
	+ "<option value ='Trade settlement' > Trade settlement </option>"
	+ "<option value ='Trade Clearance' > Trade Clearance</option>"
	+ "<option value ='Real Time Quotes' > Real Time Quotes</option>"
	+ "<option value ='Real Time Financial News' > Real Time Financial News</option>"
	+ "<option value ='Trade Analytics' > Trade Analytics</option>"
	+ "<option value ='Collateral Management' > Collateral Management</option>"
	+ "<option value ='Listed stocks/contracts' > Listed stocks/contracts</option>"
	+ "<option value ='Electronic Trading for OTC' > Electronic Trading for OTC</option>"
	+ "<option value ='Order Management' > Order Management</option>"
	+ "<option value ='Trade Confirmation' > Trade Confirmation</option>"
	+ "<option value ='Trade Reporting' > Trade Reporting</option>"
	+ "<option value ='Trading Strategy Builder' > Trading Strategy Builder</option>"
	+ "<option value ='Liquidity Aggregation' > Liquidity Aggregation</option>"
	+ "<option value ='Market Surveillance & Compliance' > Market Surveillance & Compliance</option>"
	+ "<option value ='Ability to connect to liquidity providers' > Ability to connect to liquidity providers</option>"
	+ "<option value ='Algorithmic Signal Generation' > Algorithmic Signal Generation</option>"
	+ "<option value ='Algorithmic Order Execution& Mgmt' > Algorithmic Order Execution& Mgmt</option>"
	+ "<option value ='Auto Hedging & Risk Mgmt' > Auto Hedging & Risk Mgmt</option>"
	+ "<option value ='Time Series Modelling' > Time Series Modelling</option>"
	+ "<option value ='Market Data Distribution' > Market Data Distribution</option>"
	+ "<option value ='Pricing and Rates Engine' > Pricing and Rates Engine</option>"
	+ "<option value ='Quoting & Price Distribution' > Quoting & Price Distribution</option>"
	+ "</select>"
	+ "<label class='default_select' >Trading Capabilities Type</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' name='" + assetType + "tcsTradeExecutionsType' multiple='multiple' id='" + assetType + "tcsTradeExecutionsType'>"
	+ "<option value ='Algorithmic & Program Trading' > Algorithmic & Program Trading </option>"
	+ "<option value ='Direct Market Access (DMA)' > Direct Market Access (DMA) </option>"
	+ "<option value ='FX -DMA' > FX -DMA </option>"
	+ "<option value ='Smart Order Routing (Sequential SOR)'> Smart Order Routing (Sequential SOR) </option>"

	+ "<option value ='Smart Order Routing (Multi-Posting SOR)' > Smart Order Routing (Multi-Posting SOR) </option>"
	+ "<option value ='Swap Execution Facility' > Swap Execution Facility </option>"
	+ "</select>"
	+ "<label class='default_select' >Trade Executions Type</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "tdssubsriptioncostUSDperannum' name='" + assetType + "tdssubsriptioncostUSDperannum'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Subscription Cost - Annual(USD)</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "tdslaunchyear' name='" + assetType + "tdslaunchyear'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Launch Year</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "tdsawards' name='" + assetType + "tdsawards'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Awards</label>"
	+ "</li>"
	+ "<li>"
	+ "<input type='checkbox' name='tdsDarkpoolSupported' id='tdsDarkpoolSupported' />"
	+ "<label class='default_checkbox'>Darkpool Supported?</label>"
	+ "</li>"
	+ "</ul>"
	$('#multipleAssetFields').append(multipleAssetData);
}