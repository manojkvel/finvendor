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
		if($(this).prop('checked') == true) {
			assetClassArray.push($(this).attr('id').toLowerCase());
		} else {
			assetClassArray.splice($.inArray($(this).attr('id').toLowerCase(), assetClassArray), 1);
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

		if(assetClassArray.length == 0) {
			$("#singleAsset").slideUp('slow');
		}

		if(assetClassArray.length == 1) {
			var assetType = assetClassArray[0];
			singleAssetClass(assetType);
			$("#singleAsset").slideDown('slow');
			$("#multipleAsset").slideUp('slow');
		}

		if(assetClassArray.length > 1) {
			multipleAssetData = '';
			for(var i=0;i<assetClassArray.length;i++) {
				multipleAssetClass(assetClassArray[i]);
			}
			$("#singleAsset").slideUp('slow');
			$("#multipleAsset").slideDown('slow');
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
				$("#singleAsset").slideDown('slow');
				$("#multipleAsset").slideUp('slow');
			}

			if(assetClassArray.length > 1) {
				multipleAssetData = '';
				for(var i=0;i<assetClassArray.length;i++) {
					multipleAssetClass(assetClassArray[i]);
				}
				$("#singleAsset").slideUp('slow');
				$("#multipleAsset").slideDown('slow');
			}
			window.localStorage.clear();
		}
	}

	$(".assetClass").click(getAssetClassAggregators);

	$("#search_vendor").click(function(e) {
		if(assetClassArray.length == 0) {
			alert("Please select atleast 1 asset class.");
			e.preventDefault();
			return false;
		}
		window.localStorage.setItem('assetClassArray', JSON.stringify(assetClassArray));
	});

	$("#reset_vendor").click(function() {
		assetClassArray = [];
		$("#singleAsset").slideUp('slow');
		$("#multipleAsset").slideUp('slow');
	});
});