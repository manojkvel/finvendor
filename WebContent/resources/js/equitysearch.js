jQuery(document).ready(function() {

	window.onbeforeunload = function() {
		window.localStorage.removeItem("equitysearchjson");
	};

    var API_TIMEOUT_SMALL = 30*1000;
    var API_TIMEOUT_LARGE = 3*60*1000;


	
	// Find and remove item from an array
	var addRemoveItemFromArray = function(array, item) {
		var i = array.indexOf(item);
		if(i != -1) {
			array.splice(i, 1);
		} else {
			array.push(item);
		}
		return array;
	}

	function loadDefaultEquityList(jsonData) {
		getResearchReportForEquity(jsonData).then(function(serverResponse) {
			//console.log(serverResponse);
			var response = JSON.parse(serverResponse);
			var len = response.equity.length;
			var htmlCode = '';
			var rowHtml = 	"";

			if(len === 0) {
				$("#broker_table tbody").html("<tr><td colspan='6'>No Matching Records Found</td></tr>");
				return;
			}

			for(var i = 0; i < len; i++) {

				var recommTypeClass = "label-warning";
				if(response.equity[i].recommType == "buy" || response.equity[i].recommType == "accumulate"
					 || response.equity[i].recommType == "overweight"  || response.equity[i].recommType == "bullish") {
					recommTypeClass = "label-success";
				} else if(response.equity[i].recommType == "sell" || response.equity[i].recommType == "underweight"
					 || response.equity[i].recommType == "reduce"  || response.equity[i].recommType == "bearish") {
					recommTypeClass = "label-danger";
				} else {
					recommTypeClass = "label-warning";
				}


				var _3YrPatGrowthClass = "success";
				var _3YrPatGrowthClass_Caret = "fa-caret-up";
				if(response.equity[i]._3YrPatGrowth > 0) {
					_3YrPatGrowthClass = "success";
					_3YrPatGrowthClass_Caret = "fa-caret-up";
				} else {
					_3YrPatGrowthClass = "danger";
					_3YrPatGrowthClass_Caret = "fa-caret-down";
				}

				var upsideClass = "success";
				if(response.equity[i].upside > 0) {
					upsideClass = "success";
				} else {
					upsideClass = "danger";
				}

				var borkerRankStarClass = "<i class='fa fa-star'></i>";
				var borkerRankStarHtml = '';
				if(response.equity[i].brokerRank === 5) {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass;
				} else if(response.equity[i].brokerRank === 4) {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass;
				} else if(response.equity[i].brokerRank === 3) {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass + borkerRankStarClass;
				} else if(response.equity[i].brokerRank === 2) {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass;
				} else if(response.equity[i].brokerRank === 1) {
					borkerRankStarHtml = borkerRankStarClass;
				} else {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass
				}

				htmlCode = htmlCode + "<tr>" +
						"<td>" + 
							"<div class='company' data-toggle='tooltip' title='See all reports for " + response.equity[i].company + "'>" + response.equity[i].company + "</div>" + 
							"<div class='style'>" + response.equity[i].style + "</div>" + 
							"<div class='mcap'>" + response.equity[i].mcap + "</div>" + 
							"<div class='sector'>" + response.equity[i].sector + "</div>" +
						"</td>" + 
						"<td>" + 
							"<div class='broker' data-toggle='tooltip' title='See all reports published by " + response.equity[i].broker + "'>" + response.equity[i].broker + "</div>" + 
							"<div class='since'>" + response.equity[i].since + "</div>" + 
							"<div class='awarded'>" + response.equity[i].awarded + "</div>" + 
							"<div class='researchedByCfa'>" + response.equity[i].researchedByCfa + "</div>" +
						"</td>" +
						"<td>" + 
							"<div class='brokerRank warning '>" + borkerRankStarHtml + "</div>" + 
							"<div class='brokerRankLargeCap'>" + response.equity[i].brokerRankLargeCap + "</div>" + 
							"<div class='brokerRankMidCap'>" + response.equity[i].brokerRankMidCap + "</div>" + 
							"<div class='brokerRankSmallCap'>" + response.equity[i].brokerRankSmallCap + "</div>" +
						"</td>" +
						"<td>" + 
							"<div class='cmp'> Rs. " + response.equity[i].cmp + "</div>" + 
							"<div class='priceDate'>" + response.equity[i].priceDate + "</div>" + 
							"<div class='pe'>" + response.equity[i].pe + "</div>" + 
							"<div class='_3YrPatGrowth " + _3YrPatGrowthClass + "'><i class='fa " + _3YrPatGrowthClass_Caret + "'></i> " + response.equity[i]._3YrPatGrowth + "%</div>" +
						"</td>" +
						"<td>" + 
							"<div class='recommType " + recommTypeClass + "'>" + response.equity[i].recommType + "</div>" + 
							"<div class='targetPrice'> Rs. " + response.equity[i].targetPrice + "</div>" + 
							"<div class='priceAtRecomm'>" + response.equity[i].priceAtRecomm + "</div>" + 
							"<div class='upside " + upsideClass + "'>" + response.equity[i].upside + "</div>" +
						"</td>" +
						"<td>"  +  
							"<div class='analystName'>" + response.equity[i].analystName + "</div>" + 
							"<div class='researchDate'>" + response.equity[i].researchDate + "</div>" +
							"<div class='report' target=''><a href='research-company-report.jsp?id=" + response.equity[i].companyId + "' data-toggle='tooltip' title='Go to report post'><i class='fa fa-file'></i></a></div>" +
						"</td>" +
						"</tr>";
			}

			$("#broker_table tbody").html(htmlCode);

    		$('[data-toggle="tooltip"]').tooltip();
		}, function(error) {
			console.log(error);
		});
	};



	var localEquitySearchJson = {
		"geo": "1"
	};

	window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
	loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));

	function getResearchReportForEquity(jsonData) {
		var url = "/system/api/researchReports?type=equity";
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
                        console.log(httpRequest.status + httpRequest.responseText);
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send(JSON.stringify(jsonData));
        });
	};


    /**
     * Function to start async call to get filter data.
     * @param {filterType} filterType - filter type value.
     */
	var getFilterData = function(filterType) {
		var url = "/system/api/filterdata?type=" + filterType;
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

   	/**
     * Function to set geo Type from filter data API and set response.
     */
	var setGeoData = function() {
		getFilterData('country').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].name + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='radio' name='geography_type' data-name='" + response[i].name + "' data-section='' data-value='" + response[i].country_id + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_geo ul").html(html);
			$('#search_by_geo ul input[name=geography_type]').eq(0).prop('checked', true);
			$("#search_by_geo ul input[name=geography_type]").on('change', getGeoData);
		}, function(error) {
			console.log(error);
		});
	};

	/**
     * Function to get geo Type from localstorage and get equity list.
     */
	var getGeoData = function() {
		localEquitySearchJson.geo = $("input[name=geography_type]:checked").attr('data-value');
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};


	/**
     * Function to set market capital from filter data API.
     */
	var setMarketCapitalData = function() {
		getFilterData('marketcapital').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].mcap_name + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].mcap_name + "' data-section='' data-value='" + response[i].mcap_name + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_marketcapital ul").html(html);
			$("#search_by_marketcapital ul input").on('change', getMarketCapitalData);
		}, function(error) {
			console.log(error);
		});
	};

	var MarketCapitalData = [];

    /**
     * Function to get analyst Type from localstorage and get equity list
     */
	var getMarketCapitalData = function() {
		addRemoveItemFromArray(MarketCapitalData, $(this).attr('data-value'));
		
		localEquitySearchJson.mcap = MarketCapitalData;

		if(MarketCapitalData.length === 0) {
			delete localEquitySearchJson.recommType;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};


	/**
     * Function to set style from filter data API.
     */
	var setStyleData = function() {
		getFilterData('style').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].stockClassificationName + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].stockClassificationName + "' data-section='' data-value='" + response[i].stockClassificationName + "' />"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_style ul").html(html);
			$("#search_by_style ul input").on('change', getStyleFilterData);
		}, function(error) {
			console.log(error);
		});
	};

	var styleFilterData = [];

    /**
     * Function to get style from localstorage and get equity list
     */
	var getStyleFilterData = function() {
		addRemoveItemFromArray(styleFilterData, $(this).attr('data-value'));
		
		localEquitySearchJson.style = styleFilterData;

		if(styleFilterData.length === 0) {
			delete localEquitySearchJson.style;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};


	/**
     * Function to set analyst Type from filter data API.
     */
	var setAnalystTypeFilterData = function() {
		getFilterData('analystType').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].analystType + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].analystType + "' data-section='' data-value='" + response[i].analystType + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_analystType ul").html(html);
			$("#search_by_analystType ul input").on('change', getAnalystTypeFilterData);
		}, function(error) {
			console.log(error);
		});
	};

	var analystTypeFilterData = [];

    /**
     * Function to get analyst Type from localstorage and get equity list
     */
	var getAnalystTypeFilterData = function() {
		addRemoveItemFromArray(analystTypeFilterData, $(this).attr('data-value'));
		
		localEquitySearchJson.analystType = analystTypeFilterData;

		if(analystTypeFilterData.length === 0) {
			delete localEquitySearchJson.analystType;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};


	/**
     * Function to set Research Broker from filter data API.
     */
	var setResearchBrokerData = function() {
		getFilterData('researchBroker').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].userName + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].userName + "' data-section='' data-value='" + response[i].userName + "' />"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_researchbroker ul").html(html);
			$("#search_by_researchbroker ul input").on('change', getResearchBrokerFilterData);
		}, function(error) {
			console.log(error);
		});
	};

	var researchBrokerFilterData = [];

    /**
     * Function to get Research Broker from localstorage and get equity list
     */
	var getResearchBrokerFilterData = function() {
		addRemoveItemFromArray(researchBrokerFilterData, $(this).attr('data-value'));
		
		localEquitySearchJson.researchedBroker = researchBrokerFilterData;

		if(researchBrokerFilterData.length === 0) {
			delete localEquitySearchJson.researchedBroker;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};


	/**
     * Function to set Broker Analyst Yr Of Incorp from filter data API.
     */
	var setBrokerAnalystYrOfIncorpFilterData = function() {
		getFilterData('brokerYrOfInCorp').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].brokerYrOfInCorp + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].brokerYrOfInCorp + "' data-section='' data-value='" + response[i].brokerYrOfInCorp + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_brokerAnalystYrOfIncorp ul").html(html);
			$("#search_by_brokerAnalystYrOfIncorp ul input").on('change', getBrokerAnalystYrOfIncorpFilterData);
		}, function(error) {
			console.log(error);
		});
	};

	var brokerAnalystYrOfIncorpFilterData = [];

    /**
     * Function to get Broker Analyst Yr Of Incorp from localstorage and get equity list
     */
	var getBrokerAnalystYrOfIncorpFilterData = function() {
		addRemoveItemFromArray(brokerAnalystYrOfIncorpFilterData, $(this).attr('data-value'));
		
		localEquitySearchJson.brokerYrOfInCorp = brokerAnalystYrOfIncorpFilterData;

		if(brokerAnalystYrOfIncorpFilterData.length === 0) {
			delete localEquitySearchJson.brokerYrOfInCorp;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};

	/**
     * Function to set Broker Rank from filter data API.
     */
	var setBrokerRankFilterData = function() {
		getFilterData('brokerRank').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].broker_rank + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].broker_rank + "' data-section='' data-value='" + response[i].broker_rank + "' />"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_broker_rank ul").html(html);
			$("#search_by_broker_rank ul input").on('change', getBrokerRankFilterData);
		}, function(error) {
			console.log(error);
		});
	};

	var brokerRankFilterData = [];

    /**
     * Function to get Broker Analyst Yr Of Incorp from localstorage and get equity list
     */
	var getBrokerRankFilterData = function() {
		addRemoveItemFromArray(brokerRankFilterData, $(this).attr('data-value'));
		
		localEquitySearchJson.brokerRank = brokerRankFilterData;

		if(brokerRankFilterData.length === 0) {
			delete localEquitySearchJson.brokerRank;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};

    /**
     * Function to set recommendation Type from filter data API.
     */
	var setRecommendationTypeData = function() {
		getFilterData('recommType').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].rsrchRecommType + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].rsrchRecommType + "' data-section='' data-value='" + response[i].rsrchRecommType + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_recommType ul").html(html);
			$("#search_by_recommType ul input").on('change', getRecommendationTypeData);
		}, function(error) {
			console.log(error);
		});
	};

	var recommTypeData = [];

    /**
     * Function to get recommendation Type from localstorage and get equity list
     */
	var getRecommendationTypeData = function() {
		addRemoveItemFromArray(recommTypeData, $(this).attr('data-value'));
		
		localEquitySearchJson.recommType = recommTypeData;

		if(recommTypeData.length === 0) {
			delete localEquitySearchJson.recommType;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};


    /**
     * Function to set upside from filter data API.
     */
	var setUpsideFilterData = function() {
		getFilterData('upside').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].upside + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].upside + "' data-section='' data-value='" + response[i].upside + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_upside ul").html(html);
			$("#search_by_upside ul input").on('change', getUpsideFilterData);
		}, function(error) {
			console.log(error);
		});
	};

	var upsideFilterData = [];

    /**
     * Function to get upside from localstorage and get equity list
     */
	var getUpsideFilterData = function() {
		addRemoveItemFromArray(upsideFilterData, $(this).attr('data-value'));
		
		localEquitySearchJson.upside = upsideFilterData;

		if(upsideFilterData.length === 0) {
			delete localEquitySearchJson.upside;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};


    /**
     * Function to set Others from filter data API.
     */
	var setOthersFilterData = function() {
		getFilterData('others').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.length;
			for(key in response) {
				var newProp = response[key];
				for(key in newProp) {
					html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + key + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + newProp[key] + "' data-section='' data-value='" + newProp[key] + "' />"
									+ "</div>"
								+ "</div>"
							+ "</li>"
				}
			}
			/*for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].upside + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].upside + "' data-section='' data-value='" + response[i].upside + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}*/
			$("#search_by_others ul").html(html);
			$("#search_by_others ul input").on('change', getOthersFilterData);
		}, function(error) {
			console.log(error);
		});
	};

	var othersFilterData = [];

    /**
     * Function to get Others from localstorage and get equity list
     */
	var getOthersFilterData = function() {
		addRemoveItemFromArray(othersFilterData, $(this).attr('data-value'));
		
		localEquitySearchJson.others = othersFilterData;

		if(othersFilterData.length === 0) {
			delete localEquitySearchJson.others;
		}
		
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")));
	};


    /**
     * Function to start async call to filter data API and set response.
     */
	var loadDefaultFilterData = function() {
		setGeoData();
		setMarketCapitalData();
		setStyleData();
		setAnalystTypeFilterData();
		setResearchBrokerData();
		setBrokerAnalystYrOfIncorpFilterData();
		setBrokerRankFilterData();
		setRecommendationTypeData();
		setUpsideFilterData();
		setOthersFilterData();
	}

	loadDefaultFilterData();



});