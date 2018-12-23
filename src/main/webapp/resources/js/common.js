
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
		assetClassId = $("#" + assetClassSelector).attr("data-id");
	} else {
		if($("#vendor_my_offerings").length==1) {
			assetClassId = $($(".nav-tabs .active a").attr("href") + " select[name='" + assetClassSelector + "']").selectpicker('val');
		} else {
			assetClassId = $("select[name='" + assetClassSelector + "']").selectpicker('val');
		}
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
		analyticSolutionTypeId = $("#" + analyticalSolutionTypeSelector).attr("data-id");
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
		rcResearchAreaId = $("#" + rcResearchAreaSelector).attr("data-id");
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

    /*if(getResearchAreaMultipleListById(rcResearchAreaId)[0].toLowerCase() == ("debt market research") || getResearchAreaMultipleListById(rcResearchAreaId)[0].toLowerCase() == ("index/fund/etf research")) {
    	$("#stocksFundsIssuesCovered").val("");
    	$("#stocksFundsIssuesCovered_parent").show();
    } else {
    	$("#stocksFundsIssuesCovered_parent").hide();
    }*/

  	if($("#vendor_my_offerings").is(':visible')){ 

	    if(getResearchAreaMultipleListById(rcResearchAreaId)[0].toLowerCase() == ("equity research")) {
	    	$("#vo_price_at_recomm").parent().show();
	    	$("#vo_target_price").parent().show();
	    } else {
	    	$("#vo_price_at_recomm").parent().hide();
	    	$("#vo_target_price").parent().hide();
	    }

	    if(getResearchAreaMultipleListById(rcResearchAreaId)[0].toLowerCase() == ("sector analysis")) {
	    	$("select[name=vo_report_frequency]").parents('li').show();
	    	//$("#vo_report_frequency").parent().show();
	    } else {
	    	$("select[name=vo_report_frequency]").parents('li').hide();
	    }
  		
  	} 
}

function isProgressLoader(status) {
	if(status === true) {
		$("#progressLoader").show();
	} else {
		$("#progressLoader").hide();
	}
}

function isLoggedInUser() {
	var isLoggedInUser = $("#getLoggedInUser input[name=isLoggedInUser]").val();
	if(isLoggedInUser == '/') {
		return true;
	}

	return false;
}

function getHostUrl() {
	var http = location.protocol;
	var slashes = http.concat("//");
	var urlPrefix = slashes.concat(window.location.host).concat("/");
	return urlPrefix;
}

var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;

var timeStampToDate = function (ts) {
	if (ts) {
		ts = new Date(ts).toString();
		ts = ts.split(' ').slice(0, 5);
            ts = /*ts[0] + " " + */ ts[1] + " " + ts[2] + ", " + ts[3]; //+ " " + ts[4];
            //console.log(ts);
            return ts;
        } else {
        	return 'NA';
        }
    };

function getGenericSearchCompanyList(hintVal) {

	var url = "/system/api/companyhomepagesearch?searchKeyword=" + hintVal;
	return new Promise(function(resolve, reject) {
		var httpRequest = new XMLHttpRequest({
			mozSystem: true
		});
		httpRequest.timeout = API_TIMEOUT_SMALL;
		httpRequest.open('GET', url, true);
		httpRequest.ontimeout = function () {
			reject("" + httpRequest.responseText);
		};
		httpRequest.onreadystatechange = function () {
			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {
					resolve(httpRequest.response);
				} else {
					console.log(httpRequest.status + httpRequest.responseText);
					reject(httpRequest.responseText);
				}
			} else {
			}
		};

		httpRequest.send();
	});
};

jQuery(document).ready(function($) {

	function getHeaderSearchCompanyList(e) {

		var hintVal = $(this).val();

		if(hintVal.length >=2 && e.which == 13) {
			 $(this).click();
      		return false;
		}

		$(this).marcoPolo({
			url: '/system/api/homepage',
			param: 'searchKey',
			minChars: 2,
			formatData: function (data) {

				if(data.stock.length == 0) {
					$("input[name=txtSearchBox]").attr("disabled", "disabled");
					return;
				}
				$("input[name=txtSearchBox]").removeAttr("disabled");
				return data.stock;
			},
			formatItem: function (data, $item) {
				return data.companyName + " (" + data.ticker + ")";
			},
			onSelect: function (data, $item) {
				if(data.companyName != undefined) {
					var companyProfileJson = {
						isinCode : data.isinCode,
					}
					window.localStorage.setItem('companyProfileJson', JSON.stringify(companyProfileJson));
					this.val(data.companyName);
					this.parents('form').find(".submit-button").trigger('click');

					
					if(e.target.name == 'homepagesearch') {

						sendGAevents('HomePageSearch', 'Search Stocks onKeyDown', 'Search Stocks & its Quotes');

					} else if(e.target.name == 'searchKeyword') {

						sendGAevents('TopHeaderSearch', 'Search Stocks onKeyDown', 'Search Stocks & its Quotes');
					}
				}
			},
			required: true
		});
	};

	$("#txtSearchBox").on("keydown", getHeaderSearchCompanyList);
	$("input[name=homepagesearch]").on("keydown", getHeaderSearchCompanyList);

	function selectResearchType (e) {
		var type = $(this).attr("data-context");
		var typePlaceHolder = $(this).attr("data-placeholder");
		$("#home_page_main_search button .htmlvalue").text(type);
		$("input[name=homepagesearch]").val("");
		$("#home_page_main_search input").attr('placeholder', typePlaceHolder);
	}

	$("#home_page_main_search .ddselect a").on('click', selectResearchType);

});

function validateDate(date) {
	var currentDate = moment().format('L');

	var localDate = moment(date, 'DD/MM/YYYY');
	localDate =  moment(localDate).format("MM/DD/YYYY");
	localDate = new Date(localDate);

	var today = new Date(currentDate);

	if(localDate <= today) {
		return true;
	}
	return false;
}

function getStockMarquee() {
	var jsonBody = {
		"indexNames":["Nifty 50"]
	};

	getMarqueeApi(jsonBody).then(function(response) {
		var response = JSON.parse(response);

		var stockDataLength = response.data.stockData.length;

		$("#marquee_container .marquee.stock_price .hd .title").text(response.data.stockTitle);
		$("#marquee_container .marquee.stock_price .hd .last_updated_date").text(timeStampToDate(Number(response.data.stockDate)));

		var html = "";

		for(var i = 0; i < stockDataLength; i++) {

			var len = response.data.stockData[i].stocks.length;

			html = html + "<li>"
						+ "<h4 class='title'>" + response.data.stockData[i].title + ": </h4>"
						+ "</li>";

			for(var j = 0; j < len; j++) {

				var percentChange = parseFloat(response.data.stockData[i].stocks[j].percentChange).toFixed(2);

				var percentChangeClass = "success";
				var percentChangeClass_Caret = "fa-caret-up";
				if(percentChange > 0) {
					percentChangeClass = "success";
					percentChangeClass_Caret = "fa-caret-up";
				} else {
					percentChangeClass = "danger";
					percentChangeClass_Caret = "fa-caret-down";
				}

				html = html + "<li>" 
							+ "<a href='/view/company-profile.jsp?isinCode=" + response.data.stockData[i].stocks[j].isinCode + "'>"  
							+ response.data.stockData[i].stocks[j].companyName 
							+ " <span>" + response.data.stockData[i].stocks[j].close + "</span>"
							+ " <span class='percentChange " + percentChangeClass + "'><i class='fa " + percentChangeClass_Caret + "'></i> " + response.data.stockData[i].stocks[j].change + "</span>"
							+ " <span class='percentChange " + percentChangeClass + "'>(" + ((percentChange != '-') ? percentChange + '%' : percentChange) + ")</span>"
							+ "</a>"
							+ "</li>";

			}
		}

		$("#marquee_container .marquee.stock_price .bd").html("<marquee behavior='scroll' direction='left' onmouseover='this.stop();' onmouseout='this.start();'><ul>" + html + "</ul></marquee>");

	}, function(error) {

	});
}

function getIndexMarquee() {
	getMarqueeApi().then(function(response) {
		var response = JSON.parse(response);

		var len = response.data.indexes.length;

		$("#marquee_container .marquee.index_price .hd .title").text(response.data.title);
		$("#marquee_container .marquee.index_price .hd .last_updated_date").text(timeStampToDate(Number(response.data.indexDate)));

		var html = "";

		for(var j = 0; j < len; j++) {

			var percentChange = response.data.indexes[j].percentChange;

			if(percentChange == '-') {
				percentChange == response.data.indexes[j].percentChange;
			} else {
				percentChange = parseFloat(response.data.indexes[j].percentChange).toFixed(2);
			}

			var percentChangeClass = "success";
			var percentChangeClass_Caret = "fa-caret-up";
			if(percentChange > 0) {
				percentChangeClass = "success";
				percentChangeClass_Caret = "fa-caret-up";
			} else {
				percentChangeClass = "danger";
				percentChangeClass_Caret = "fa-caret-down";
			}

			html = html + "<li>" 
			+ "<a href='/view/markets.jsp' data-indexName='" + response.data.indexes[j].indexName + "'>"  
			+ response.data.indexes[j].indexName 
			+ " <span>" + response.data.indexes[j].closing + "</span>"
			+ " <span class='percentChange " + percentChangeClass + "'><i class='fa " + percentChangeClass_Caret + "'></i> " + response.data.indexes[j].pointChange + "</span>"
			+ " <span class='percentChange " + percentChangeClass + "'>(" + ((percentChange != '-') ? percentChange + '%' : percentChange) + ")</span>"
			+ "</a>"
			+ "</li>";

		}
		$("#marquee_container .marquee.index_price .bd").html("<marquee behavior='scroll' direction='left' onmouseover='this.stop();' onmouseout='this.start();'><ul>" + html + "</ul></marquee>");
		$("#marquee_container .marquee.index_price ul a").on('click', getMarketIndexData);
		
	}, function(error) {
		
	});
}

function getMarketIndexData() {
	localStorage.setItem('indexName', $(this).attr('data-indexName'));
}


function getMarqueeApi(jsonBody) {

	var url = "/system/api/markets/marquee/index";
	return new Promise(function(resolve, reject) {
		var httpRequest = new XMLHttpRequest({
			mozSystem: true
		});
		httpRequest.timeout = API_TIMEOUT_SMALL;
		httpRequest.open('POST', url, true);
		httpRequest.setRequestHeader('Content-Type',
			'application/json; charset=UTF-8');
		httpRequest.ontimeout = function () {
			reject("" + httpRequest.responseText);
		};
		httpRequest.onreadystatechange = function () {
			if (httpRequest.readyState === XMLHttpRequest.DONE) {
				if (httpRequest.status === 200) {
					resolve(httpRequest.response);
				} else {
                        //console.log(httpRequest.status + httpRequest.responseText);
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send(JSON.stringify(jsonBody));
        });
};