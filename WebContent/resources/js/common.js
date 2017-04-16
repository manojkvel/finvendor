
function getCompanyTypeList(companyTypeListSelector, awardVendorTypeSelector) {
	var companyTypeList = $("#" + companyTypeListSelector).val().split(',');
	var $option='';
	for(var id in companyTypeList ) {
		$option += "<option value='" + companyTypeList[id] + "'>" + companyTypeList[id] + "</option>";   		
	}
    $("select#" + awardVendorTypeSelector +"").empty();
    $("select[name=" + awardVendorTypeSelector + "]").append($option);	
    $("select[name=" + awardVendorTypeSelector + "]").selectpicker('refresh');
}


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
	var assetClassId = 0;
	if($("select[name=" + assetClassSelector + "]").length == 0) {
		assetClassId = $("#" + assetClassSelector).val();
	} else {
		assetClassId = $("select[name=" + assetClassSelector + "]").selectpicker('val');
	}
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
    $("select[name=" + analyticalSolutionTypeSelector + "]").selectpicker('refresh');
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
	var analyticSolutionTypeId = 0;
	if($("select[name=" + analyticalSolutionTypeSelector + "]").length == 0) {
		analyticSolutionTypeId = $("#" + analyticalSolutionTypeSelector).val();
	} else {
		analyticSolutionTypeId = $("select[name=" + analyticalSolutionTypeSelector + "]").selectpicker('val');
	}
	
	
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

function getResearchAreaMultipleListById(researchAreaId) {
	var researchAreaList = JSON.parse(window.localStorage.getItem('researchAreaList'));
	var $option=[];
	for(var i=0; i < researchAreaList.length; i++) {
		for(id in researchAreaId) {
			if(researchAreaList[i].id == researchAreaId[id]) {
				$option.push(researchAreaList[i].name);
			}
		}
	}
	return $option;
}

function getResearchAreaMapping(rcResearchAreaSelector) {
	var researchAreaList = JSON.parse(window.localStorage.getItem('researchAreaList'));
	var $option='';
	for (var val in researchAreaList) {
		$option += "<option value='" + researchAreaList[val].id + "'>" + researchAreaList[val].name + "</option>";
	}
    $("select#" + rcResearchAreaSelector +"").empty();
    $("select[name=" + rcResearchAreaSelector + "]").append($option);	
   	$("select[name=" + rcResearchAreaSelector + "]").selectpicker('refresh');
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
	var rcResearchAreaId = 0;
	if($("select[name=" + rcResearchAreaSelector + "]").length == 0) {
		rcResearchAreaId = $("#" + rcResearchAreaSelector).val();
	} else {
		rcResearchAreaId = $("select[name=" + rcResearchAreaSelector + "]").selectpicker('val');
	}
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

    if(getResearchAreaMultipleListById(rcResearchAreaId)[0].toLowerCase() == ("equity research") || getResearchAreaMultipleListById(rcResearchAreaId)[0].toLowerCase() == ("debt market research") || getResearchAreaMultipleListById(rcResearchAreaId)[0].toLowerCase() == ("index/fund/etf research")) {
    	$("#stocksFundsIssuesCovered").val("");
    	$("#stocksFundsIssuesCovered_parent").show();
    } else {
    	$("#stocksFundsIssuesCovered_parent").hide();
    }
}