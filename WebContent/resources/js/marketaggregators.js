function getRegionValues(regionName) {
	if(regionName != '' && regionName.length > 0 && !regionName.match("-SELECT-")){
		regionName = encode64(regionName);
		$.ajax({
			type: 'GET',
			url:  "loadRegionNameTypes?SiMhRaYuL="+regionName,
			cache:false,
			success : function(output){
				document.getElementById("regionsNames").innerHTML = output;
			},
			error : function(data, textStatus, jqXHR){
				alert('Error: '+data+':'+textStatus);
			}
		});
	}
}

function loadSecurityTypes(assettypeId) {
	if(assettypeId != '' && assettypeId.length > 0 && !assettypeId.match("-SELECT-")){
		assettypeId = encode64(assettypeId);
		$.ajax({
			type: 'GET',
			url:  "loadSecurityTypes?RAyuL="+assettypeId,
			cache:false,
			success : function(output){
				document.getElementById("assetClassSecurityMaps").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
}

function loadCountryList(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryTypes?RAYVEM="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMaps").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});
	}
}

function loadCountryListInfo(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryTypesInfo?RAYVEM="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMapsinfo").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}

}
function loadCountryListMultiInfo(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryTypesInfoMulti?VeMuRaYu="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMapsinfoMulti").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}

}
function loadCountryListAssetInfo(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryListAssetInfo?RaYuLuVeMuLa="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMapsinfoAsset").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}

}
function loadCountryListAssetInfoFI(regionId) {
	if(regionId != '' && regionId.length > 0 && !regionId.match("-SELECT-")){
		regionId = encode64(regionId);
		$.ajax({
			type: 'GET',
			url:  "loadCountryListAssetInfoFI?RaYuLuVeMuLa="+regionId,
			cache:false,
			success : function(output){
				document.getElementById("regionCountryMapsinfoAssetFI").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}

}
function loadExchangeAssetListFI(countryId) {
	if(countryId != '' && countryId.length > 0 && !countryId.match("-SELECT-")){
		countryId = encode64(countryId);
		$.ajax({
			type: 'GET',
			url:  "loadExchangeAssetListFI?RaYuLU="+countryId,
			cache:false,
			success : function(output){
				document.getElementById("countryExchangeMapsAssetFI").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}

}
function loadExchangeList(countryId) {
	if(countryId != '' && countryId.length > 0 && !countryId.match("-SELECT-")){
		countryId = encode64(countryId);
		$.ajax({
			type: 'GET',
			url:  "loadExchangeTypes?RaYuLU="+countryId,
			cache:false,
			success : function(output){
				document.getElementById("countryExchangeMaps").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}

}

function loadExchangeAssetList(countryId) {
	if(countryId != '' && countryId.length > 0 && !countryId.match("-SELECT-")){
		countryId = encode64(countryId);
		$.ajax({
			type: 'GET',
			url:  "loadExchangeAssetList?VeMuLaRaYuL="+countryId,
			cache:false,
			success : function(output){
				document.getElementById("countryExchangeMapsAsset").innerHTML = output;		
			},
			error : function(data, textStatus, jqXHR){
				//alert('Error: '+data+':'+textStatus);
			}
		});

	}

}

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
			getAssetClassSecurityTypeMapping(assetClassArray[i], assetClassArray[i] + 'securitytype');
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
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "securitytype' id='" + assetType + "securitytype' multiple='multiple'>"
	+ "<c:forEach var='securityType' items='${securityTypes}'>"
	+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
	+ "</c:forEach>"
	+ "</select>"
	+ "<label class='default_select'>Security Types</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "datacoverageregion' id='" + assetType + "datacoverageregion' multiple='multiple'>"
	+ "<c:forEach var='regions' items='${regions}'>"
	+ "<option value='${regions.region_id}'>${regions.name}</option>"
	+ "</c:forEach>"
	+ "</select>"
	+ "<label class='default_select'>Data Coverage Region</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "datacoveragecountry' id='" + assetType + "datacoveragecountry' multiple='multiple'>"
	+ "<c:forEach var='countries' items='${countries}'>"
	+ "<option value='${countries.country_id}'>${countries.name}</option>"
	+ "</c:forEach>"
	+ "</select>"
	+ "<label class='default_select'>Data Coverage Country</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "datacoverageexchange' id='" + assetType + "datacoverageexchange' multiple='multiple'>"
	+ "<c:forEach var='exchanges' items='${exchanges}'>"
	+ "<option value='${exchanges.exchange_id}'>${exchanges.name}</option>"
	+ "</c:forEach>"
	+ "</select>"
	+ "<label class='default_select'>Data Coverage Exchange</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "vendoryearoperation' id='" + assetType + "vendoryearoperation' multiple='multiple'>"
	+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
	/*
	+ "<option>Date (1970)</option>"
	+ "<option>Last one week</option>"
	+ "<option>Last one month</option>"
	+ "<option>Last one year</option>"
	*/
	+ "</select>"
	+ "<label class='default_select'>Vendor Year of Operation</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "awards' id='" + assetType + "awards' multiple='multiple'>"
	+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
	/*
	+ "<c:forEach var='awards' items='${awards}'>"
	+ "<option value='${awards.award_id}'>${awards.name}</option>"
	+ "</c:forEach>"
	*/
	+ "</select>"
	+ "<label class='default_select'>Awards</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "acquisitioncostrange' id='" + assetType + "acquisitioncostrange' multiple='multiple'>"
	/*+ "<c:forEach var='costs' items='${costs}'>"
	+ "<option value='${costs.range}'>${costs.range}</option>"
	+ "</c:forEach>"*/
	+ "</select>"
	+ "<label class='default_select'>Data Acquisition Cost Range</label>"
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
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "securitytype' id='" + assetType + "securitytype' multiple='multiple'>"
	+ "<c:forEach var='securityType' items='${securityTypes}'>"
	+ "<option value='${securityType.securityTypeId}'>${securityType.name}</option>"
	+ "</c:forEach>"
	+ "</select>"
	+ "<label class='default_select'>Security Types</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "datacoverageregion' id='" + assetType + "datacoverageregion' multiple='multiple'>"
	+ "<c:forEach var='regions' items='${regions}'>"
	+ "<option value='${regions.region_id}'>${regions.name}</option>"
	+ "</c:forEach>"
	+ "</select>"
	+ "<label class='default_select'>Data Coverage Region</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "datacoveragecountry' id='" + assetType + "datacoveragecountry' multiple='multiple'>"
	+ "<c:forEach var='countries' items='${countries}'>"
	+ "<option value='${countries.country_id}'>${countries.name}</option>"
	+ "</c:forEach>"
	+ "</select>"
	+ "<label class='default_select'>Data Coverage Country</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "datacoverageexchange' id='" + assetType + "datacoverageexchange' multiple='multiple'>"
	+ "<c:forEach var='exchanges' items='${exchanges}'>"
	+ "<option value='${exchanges.exchange_id}'>${exchanges.name}</option>"
	+ "</c:forEach>"
	+ "</select>"
	+ "<label class='default_select'>Data Coverage Exchange</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "vendoryearoperation' id='" + assetType + "vendoryearoperation' multiple='multiple'>"
	+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
	/*
	+ "<option>Date (1970)</option>"
	+ "<option>Last one week</option>"
	+ "<option>Last one month</option>"
	+ "<option>Last one year</option>"
	*/
	+ "</select>"
	+ "<label class='default_select'>Vendor Year of Operation</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "awards' id='" + assetType + "awards' style='height: 53px;' multiple='multiple'>"
	+ "<option selected='selected' value ='' class='selectvalues'>ANY</option>"
	/*
	+ "<c:forEach var='awards' items='${awards}'>"
	+ "<option value='${awards.award_id}'>${awards.name}</option>"
	+ "</c:forEach>"
	*/
	+ "</select>"
	+ "<label class='default_select'>Awards</label>"
	+ "</li>"
	+ "<li><select class='selectpicker select_multiple' name='" 
	+ assetType + "acquisitioncostrange' id='" + assetType + "acquisitioncostrange' multiple='multiple'>"
	/*+ "<c:forEach var='costs' items='${costs}'>"
	+ "<option value='${costs.range}'>${costs.range}</option>"
	+ "</c:forEach>"*/
	+ "</select>"
	+ "<label class='default_select'>Data Acquisition Cost Range</label>"
	+ "</li>"
	+ "</ul>"
	$('#multipleAssetFields').append(multipleAssetData);
}