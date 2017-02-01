
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
			getRegionMapping(assetClassArray[i] + 'datacoverageregion');
			getSecurityTypeMapping(assetClassArray[i] + 'securitytype');
			getCountryMapping(assetClassArray[i] + 'datacoveragecountry');
			getExchangeMapping(assetClassArray[i] + 'datacoverageexchange');
			getCostRangeMapping(costRange, assetClassArray[i] + 'acquisitioncostrange');


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
		} else if(type.indexOf('indices') != -1) {
			type = 'indices';
		} else if(type.indexOf('ai') != -1) {
			type = 'ai';
		} else if(type.indexOf('misc') != -1) {
			type = 'misc';
		}
		if($(this)[0]['id'] == type + 'datacoverageregion') {
			getRegionCountryMapping(type + 'datacoverageregion', type + 'datacoveragecountry');
			getCountryExchangeMapping(type + 'datacoveragecountry', type + 'datacoverageexchange');
		}

		if($(this)[0]['id'] == type + 'datacoveragecountry') {
			getCountryExchangeMapping(type + 'datacoveragecountry', type + 'datacoverageexchange');
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
	+ "<select class='selectpicker select_multiple' id='" + assetType + "researchsubarea' name='" + assetType + "researchsubarea' multiple='multiple'>"								
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
	+ "<select class='selectpicker select_multiple' id='" + assetType + "researchsubarea' name='" + assetType + "researchsubarea' multiple='multiple'>"								
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
	$('#multipleAssetFields').append(multipleAssetData);
}