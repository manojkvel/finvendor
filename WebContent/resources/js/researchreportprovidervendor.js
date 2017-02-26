
/**
 * @author Maninder Singh Rajpal
 * @date Jul 01, 2016
 * @emailId wonderfulmani@gmail.com
 * Copyrights: All Rights Reserved FinVendor
 */

$(document).ready(function() {

	var assetClassArray = [];
	var getAssetClassAggregators = function() {
		
		if($(this).prop('checked') == true) {
			assetClassArray.push($(this).attr('id').toLowerCase());
		} else {
			assetClassArray.splice($.inArray($(this).attr('id').toLowerCase(), assetClassArray), 1);
		}

		if(assetClassArray.length == 0) {
			$("#multipleAsset").slideUp('slow');
		}

		if(assetClassArray.length > 3) {
			alert("Sorry, You cannot select more than 3 asset class.");
			assetClassArray.splice($.inArray($(this).attr('id').toLowerCase(), assetClassArray), 1);
			$(this).prop('checked', false);
			return;
		} else {
			$(".error").hide();
		}

		if($("#" +$(this).attr('id') + '_search').html() != undefined) {
			$("#" +$(this).attr('id') + '_search').remove();
			return;
		}
		

		multipleAssetClass($(this).attr('id'));
		getRegionMapping($(this).attr('id') + 'researchcoverageregion');
		getCountryMapping($(this).attr('id') + 'researchcoveragecountry');
		getResearchSubAreaMapping($(this).attr('id'), $(this).attr('id') + '_researchsubarea');

		//console.log(assetClassArray + " : " + assetClassArray.length);
	};

	/*if(window.localStorage.getItem('assetClassArray') != undefined) {
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
	}*/

	$(".assetClass").click(getAssetClassAggregators);

	$("select[name=rdAnalystRegionofIncorp]").on('change', function() {
		getRegionCountryMapping('rdAnalystRegionofIncorp', 'rdAnalystCountryofIncorp');
	});

	$(document).on('change', 'select', function() {
		var type = $(this)[0]['id'];
		if(type.indexOf('macro_economic_analysis') != -1) {
			type = 'macro_economic_analysis';
		} else if(type.indexOf('sector_analysis') != -1) {
			type = 'sector_analysis';
		} else if(type.indexOf('industry_analysis') != -1) {
			type = 'industry_analysis';
		} else if(type.indexOf('commodity_analysis') != -1) {
			type = 'commodity_analysis';
		} else if(type.indexOf('exchange_rate_analysis') != -1) {
			type = 'exchange_rate_analysis';
		} else if(type.indexOf('interest_rate_analysis') != -1) {
			type = 'interest_rate_analysis';
		} else if(type.indexOf('equity_research') != -1) {
			type = 'equity_research';
		} else if(type.indexOf('debt_market_research') != -1) {
			type = 'debt_market_research';
		} else if(type.indexOf('index_fund_etf_research') != -1) {
			type = 'index_fund_etf_research';
		} 

		if($(this)[0]['id'] == type + 'researchcoverageregion') {
			getRegionCountryMapping(type + 'researchcoverageregion', type + 'researchcoveragecountry');
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
		$(".assetClass").prop('checked', false);
		$("#multipleAsset").slideUp('slow');
		$('.selectpicker').selectpicker('deselectAll');
		$(".selectpicker").selectpicker('refresh');
	});

});


function multipleAssetClass(assetType) {
	console.log("assetType : " + assetType);
	multipleAssetData = '';
	multipleAssetData = multipleAssetData
	+ "<div id='" + assetType + "_search'>"
	+ "<h3>"
	+ $("#" + assetType).siblings().html()
	+ "</h3>"
	+ "<ul>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "_researchsubarea' name='" + assetType + "_researchsubarea' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Research Sub Area</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "researchcoverageregion' name='" + assetType + "researchcoverageregion' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Research Coverage Region</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker select_multiple' id='" + assetType + "researchcoveragecountry' name='" + assetType + "researchcoveragecountry' multiple='multiple'>"								
	+ "</select>"
	+ "<label class='default_select'>Research Coverage Country</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "researchapplicablemonth' name='" + assetType + "researchapplicablemonth'>"								
	+ "<option value ='January'>January</option>"
	+ "<option value ='February'>February</option>"
	+ "<option value ='March'>March</option>"
	+ "<option value ='April'>April</option>"
	+ "<option value ='May'>May</option>"
	+ "<option value ='June'>June</option>"
	+ "<option value ='July'>July</option>"
	+ "<option value ='August'>August</option>"
	+ "<option value ='September'>September</option>"
	+ "<option value ='October'>October</option>"
	+ "<option value ='November'>November</option>"
	+ "<option value ='December'>December</option>"
	+ "</select>"
	+ "<label class='default_select'>Research Period - Month</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "researchapplicableyear' name='" + assetType + "researchapplicableyear'>"								
	+ "<option value ='2006'>2006</option>"
	+ "<option value ='2007'>2007</option>"
	+ "<option value ='2008'>2008</option>"
	+ "<option value ='2009'>2009</option>"
	+ "<option value ='2010'>2010</option>"
	+ "<option value ='2011'>2011</option>"
	+ "<option value ='2012'>2012</option>"
	+ "<option value ='2013'>2013</option>"
	+ "<option value ='2014'>2014</option>"
	+ "<option value ='2015'>2015</option>"
	+ "<option value ='2016'>2016</option>"
	+ "<option value ='2017'>2017</option>"
	+ "</select>"
	+ "<label class='default_select'>Research Period - Year</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "researchsupportedby' name='" + assetType + "researchsupportedby'>"								
	+ "<option value ='Any'>Any</option>"
	+ "<option value ='Upto 10'>Upto 10</option>"
	+ "<option value ='Upto 25'>Upto 25</option>"
	+ "<option value ='Upto 100'>Upto 100</option>"
	+ "<option value ='More than 100'>More than 100</option>"
	+ "</select>"
	+ "<label class='default_select'>No. of stocks/ETFs/Funds/Issuers supported</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "researchsubsriptioncostUSDperannum' name='" + assetType + "researchsubsriptioncostUSDperannum'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Subscription Cost - Annual(USD)</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "researchlaunchyear' name='" + assetType + "researchlaunchyear'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Launch Year</label>"
	+ "</li>"
	+ "<li>"
	+ "<select class='selectpicker' id='" + assetType + "researchawards' name='" + assetType + "researchawards'>"								
	+ "<option value ='Any'>Any</option>"
	+ "</select>"
	+ "<label class='default_select'>Awards</label>"
	+ "</li>"
	+ "</ul>"
	+ "</div>"
	$('#multipleAssetFields').append(multipleAssetData);
	$("#multipleAsset").slideDown('slow');
	$(".selectpicker").selectpicker('refresh');
}