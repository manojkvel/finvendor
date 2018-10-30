jQuery(document).ready(function() {
	var API_TIMEOUT_SMALL = 30*1000;


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

    var timeStampToDateNew = function (ts) {
    	if (ts) {
    		ts = new Date(ts).toString();
    		ts = ts.split(' ').slice(0, 5);
            //console.log(ts);
            return ts;
        } else {
        	return 'NA';
        }
    };

	var checkForAllData = function(filterData, element) {
		var arr = $(element);
		for(key in arr) {
			if (!isNaN(key)) {
				addRemoveItemFromArray(filterData, $(arr[key]).attr('data-value'));
			}
		}

		$(element).prop('checked', !$(element).prop('checked'));
		if($(element).prop('checked')) {
			$(element).prop('checked', false);
			return true;
		} else {
			$(element).prop('checked', true);
		}

		return false;
	};

	var getMarketsCommonHtml = function(response) {
		var len = response.marketData.length;
		var htmlCode = '';
		var rowHtml = 	"";

		if(len === 0) {
			$("#consumer_market .tab-content #market_data_output table#market_index_data tbody").html("<tr><td colspan='10'>No Matching Records Found</td></tr>");
			return;
		}

		for(var i = 0; i < len; i++) {

			var percentChange = parseFloat(response.marketData[i].percentChange).toFixed(2);

			var percentChangeClass = "success";
			var percentChangeClass_Caret = "fa-caret-up";
			if(percentChange > 0) {
				percentChangeClass = "success";
				percentChangeClass_Caret = "fa-caret-up";
			} else {
				percentChangeClass = "danger";
				percentChangeClass_Caret = "fa-caret-down";
			}



			htmlCode = htmlCode + "<tr>" +
			"<td>" + 
				response.marketData[i].companyName +
			"</td>" + 
			"<td>" + 
				response.marketData[i].high +
			"</td>" +
			"<td>" + 
				response.marketData[i].low +
			"</td>" +
			"<td>" + 
				response.marketData[i].close +
			"</td>" +
			"<td>" + 
				response.marketData[i].prevColse +
			"</td>" +
			"<td>" + 
				response.marketData[i]._52wHigh +
			"</td>" +
			"<td>" + 
				response.marketData[i]._52wLow +
			"</td>" +
			"<td>" + 
				response.marketData[i].change +
			"</td>" +
			"<td>" + 
				"<div class='percentChange " + percentChangeClass + "'><i class='fa " + percentChangeClass_Caret + "'></i> " + ((percentChange != '-') ? percentChange + '%' : percentChange) + "</div>" +
			"</td>" +
			"<td>" + 
				response.marketData[i].volume +
			"</td>" +
			"</tr>";
		}

		$("#consumer_market .tab-content #market_data_output table#market_index_data tbody").html(htmlCode);
		

		var paginationHtml = 	"<div class='paging_container'>"
								+ "<ul class='pager'>"
								 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
								 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
								 + "<li><span id='records_stats'></span></li>"
								 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
								 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
							 	+ "</ul>"
							 + "</div>";
		$("#consumer_market .tab-content #market_data_output").append(paginationHtml);
		$("#consumer_market .tab-content #market_data_output .pager a").on('click', getPaginationIndex);


		setRecordStats(currentIndex, lastPageNumber);
	}

	var firstPageNumber = 1;
	var pageNumber = 1;
	var lastPageNumber = 1;
	var totalRecords = 0;
	var currentIndex = 1;
	var perPageMaxRecords = 50;
	var type = 'equity';
	var indexFilter = 'all';

	var setRecordStats = function(currentIndex, lastPageNumber) {
		if(currentIndex > lastPageNumber) {
			currentIndex = lastPageNumber;
		}
		$("#records_stats").html(pageNumber + " of " + lastPageNumber);
	}

	var resetPaginationCount = function() {
		firstPageNumber = 1;
		pageNumber = 1;
		lastPageNumber = 1;
		totalRecords = 0;
		currentIndex = 1;
		perPageMaxRecords = 50;

		$("#consumer_market .tab-content  #market_data_output .max_per_page select").val($("#consumer_market .tab-content #market_data_output .max_per_page select option").eq(3).val());
	}
	
	var getPerPageMaxRecords = function() {
		if(perPageMaxRecords !== Number($(this).val())) {
			pageNumber = 1;
			firstPageNumber = 1;
			lastPageNumber = 1;
			currentIndex = 1;
		}
		perPageMaxRecords = Number($(this).val());
		loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);
	}
	$('#consumer_market .max_per_page select').on('change', getPerPageMaxRecords);

	
	var getPaginationIndex = function() {
		var currentNode = $(this).attr('id');
		if(currentNode == 'last') {
			getLastPageResearchReport(pageNumber);
		} else if(currentNode == 'next') {
			getNextPageResearchReport(pageNumber);
		} else if(currentNode == 'prev') {
			getPreviousPageResearchReport(pageNumber);
		} else if(currentNode == 'first') {
			getFirstPageResearchReport(pageNumber);
		}
	}

	var getFirstPageResearchReport = function(currentNumber) {
		if(currentNumber != firstPageNumber) {
			pageNumber = firstPageNumber;
			currentIndex = firstPageNumber;
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);
		}
	};

	var getLastPageResearchReport = function(currentNumber) {
		if(currentNumber != lastPageNumber) {
			pageNumber = lastPageNumber;
			currentIndex = (pageNumber - 1) * perPageMaxRecords + 1;
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);
		}
	};

	var getNextPageResearchReport = function(currentNumber) {
		if(currentNumber < lastPageNumber) {
			pageNumber = currentNumber + 1;
			currentIndex = currentIndex + perPageMaxRecords;
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);
		}
	};

	var getPreviousPageResearchReport = function(currentNumber) {
		if(currentNumber > 1) {
			pageNumber = currentNumber - 1;
			currentIndex = currentIndex - perPageMaxRecords;
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);
		}
	};

	function loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords) {
		isProgressLoader(true);

		getRecordStats(indexFilter, perPageMaxRecords).then(function(stats) {
			if(stats != ""){

				stats = JSON.parse(stats);
				firstPageNumber = stats.firstPageNumber;
				lastPageNumber = stats.lastPageNumber;
				totalRecords = stats.totalRecords;
				$("#consumer_market .tab-content #market_data_output #total_records_count").html(totalRecords + " Results");
				//perPageMaxRecords = Math.ceil(totalRecords / lastPageNumber);
				console.log("pageNumber: " + pageNumber);
				getMarketsApi(indexFilter, type, pageNumber).then(function(serverResponse) {
					//console.log(serverResponse);
					$("#consumer_market .paging_container").remove();
					var response = JSON.parse(serverResponse);
					
					getMarketsCommonHtml(response);
					isProgressLoader(false);

				}, function(error) {
					isProgressLoader(false);
					$("#market_data_output table#market_index_data tbody").html("<tr><td colspan='10'>We are not able to get the info, please try again later.</td></tr>");
				});
			} else {
				isProgressLoader(false);
				resetPaginationCount();
				$("#consumer_market .tab-content #market_data_output #total_records_count").html('0 Results');
				$("#market_data_output table#market_index_data tbody").html("<tr><td colspan='10'>We are not able to get the info, please try again later.</td></tr>");
				$("#consumer_market .paging_container").empty();
			}
		}, function(error) {
			isProgressLoader(false);
			$("#market_data_output table#market_index_data tbody").html("<tr><td colspan='10'>We are not able to get the info, please try again later.</td></tr>");
			$("#consumer_market .paging_container").empty();
		});
	};

	var resetFilters = function(e) {
		resetPaginationCount();
		$("#consumer_market .tab-content  #market_data_output .max_per_page select").val($("#consumer_market .tab-content #market_data_output .max_per_page select option").eq(3).val());
		
		loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);
	};

	$('#sidebar-panel .sidebar-heading span').on('click', resetFilters);


	loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);

	function getMarketsApi(indexFilter, type, pageNumber) {

		var url = "/system/api/markets?indexFilter=" + indexFilter + "&type=" + type +  "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords;
        return new Promise(function(resolve, reject) {
        	var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });
            httpRequest.timeout = API_TIMEOUT_SMALL;
            httpRequest.open('GET', url, true);
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

            httpRequest.send();
        });
	};

	/**
     * Function to start async call to get record stats
     */
	function getRecordStats(indexFilter, perPageMaxRecords) {
		var url = "/system/api/markets/recordstats?indexFilter=" + indexFilter + "&perPageMaxRecords=" + perPageMaxRecords;
		return new Promise(function(resolve, reject) {
			var httpRequest = new XMLHttpRequest({
				mozSystem: true
			});

			httpRequest.timeout = API_TIMEOUT_SMALL;
			httpRequest.open('GET', url, true);
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

			httpRequest.send();
		});
	}

	
	/*var newIndex = 0;

	var id = "market_all_stocks";
	$(".nav-tabs li").on("click", function() {
		
		if($(this).children().attr('href').substring(1) == 'market_all_stocks' && !$(this).hasClass('active')) {
			id = "market_all_stocks";
			newIndex = 0;
			indexFilter = 'all';
			resetFilters();
		} else if($(this).children().attr('href').substring(1) == 'market_sensex' && !$(this).hasClass('active')) {
			id = "market_sensex";
			newIndex = 1;
			type='';
			indexFilter = 'sensex';
			resetFilters();
		} else if($(this).children().attr('href').substring(1) == 'market_nifty_50' && !$(this).hasClass('active')) {
			id = "market_nifty_50";
			newIndex = 1;
			type='';
			indexFilter = 'nifty50';
			resetFilters();
		} else if($(this).children().attr('href').substring(1) == 'market_nifty_mid_cap' && !$(this).hasClass('active')) {
			id = "market_nifty_mid_cap";
			newIndex = 1;
			type='';
			indexFilter = 'niftyMidCap';
			resetFilters();
		} else if($(this).children().attr('href').substring(1) == 'market_nifty_small_cap' && !$(this).hasClass('active')) {
			id = "market_nifty_small_cap";
			newIndex = 1;
			type='';
			indexFilter = 'niftySmallCap';
			resetFilters();
		} else if($(this).children().attr('href').substring(1) == 'market_nifty_500' && !$(this).hasClass('active')) {
			id = "market_nifty_500";
			newIndex = 1;
			type='';
			indexFilter = 'nifty500';
			resetFilters();
		}

		$('.modal-content .nav-tabs li').removeClass('active');
		$('.modal-content .nav-tabs li').eq(newIndex).addClass('active');
		$('.modal-content .tab-content .tab-pane').removeClass('active in');
		$('.modal-content .tab-content .tab-pane').eq(newIndex).addClass('active in');
	});*/

	function getMarketIndexSummaryAPI() {
		var url = "/system/api/markets/index/summary?indexFilter=" + indexFilter + "&type=''";
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
						reject(httpRequest.responseText);
					}
				} else {
				}
			};

			httpRequest.send();
		});
	};

	function setMarketIndexSummary() {
		getMarketIndexSummaryAPI().then(function(response) {
			response = JSON.parse(response);
			console.log(response);

			var closing = response.indexSummary.closing;

			var cmp_last_change_class = "success";
			var cmp_pointChange_caret = "fa-caret-up";

			var pointChange = response.indexSummary.pointChange;

			if(pointChange > 0) {
				cmp_pointChange_class = "success";
				cmp_pointChange_caret = "fa-caret-up";
			} else if (pointChange < 0) {
				cmp_pointChange_class = "danger";
				cmp_pointChange_caret = "fa-caret-down";
			} else {
				cmp_pointChange_class = "neutral";
				cmp_pointChange_caret = "";
			}
			var percentChange = '0';
			if(response.indexSummary.percentChange != '-') {
				percentChange = response.indexSummary.percentChange
			}
			 

			var lastCmp = closing + " <em class='" + cmp_pointChange_class + "'><i class='fa " + cmp_pointChange_caret + "'></i> " + pointChange +  "</em> <cite>(" + percentChange + "%)</cite>";
			
			$(".company_details .last_cmp").html(lastCmp);

			var price_date = timeStampToDate(response.indexSummary.date);
			$(".company_details .price_date").text(price_date + " | " + timeStampToDateNew(Number(response.indexSummary.date))[4]);


			var divYield = (response.indexSummary.divYield == 0) ? response.indexSummary.divYield : response.indexSummary.divYield + "%";
			var open = (response.indexSummary.open == undefined) ? '-' : response.indexSummary.open;

			$('.last_cmp').html();
			$('.index_name').text(indexFilter);
			$("#day_open_value .fr").html(open);
			$("#day_h_l_value .fr").html(response.indexSummary.high + " / " + response.indexSummary.low);
			$("#pe_value .fr").html(response.indexSummary.pe);
			$("#pb_value .fr").html(response.indexSummary.pb);
			$("#div_yield_value .fr").html(divYield);
			
			
		}, function(error) {

		});
	}



	function getMarketIndexAnalyticsAPI() {
		var url = "/system/api/markets/analytics?indexFilter=" + indexFilter + "&type=''";
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
						reject(httpRequest.responseText);
					}
				} else {
				}
			};

			httpRequest.send();
		});
	};

	function setMarketIndexAnalytics() {
		getMarketIndexAnalyticsAPI().then(function(response) {
			var response = JSON.parse(response);
			$("#market_index_analytics .success").text(response.constituentAnalytics.gainer);
			$("#market_index_analytics .danger").text(response.constituentAnalytics.looser);
			$("#market_index_analytics .neutral").text(response.constituentAnalytics.unchanged);

		}, function(error) {

		});
	}

	function getSidePanelListAPI() {
		var url = "/system/api/markets/index/names";
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
						reject(httpRequest.responseText);
					}
				} else {
				}
			};

			httpRequest.send();
		});
	};

	var setListSidePanel = function() {
		getSidePanelListAPI().then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = '';
			var len = response.indexNames.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.indexNames[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='radio' name='market_index_type' data-name='" + response.indexNames[i] + "' data-section='' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_market_index ul").html(html);
			//$('#search_by_market_index ul input[name=market_index_type]').eq(0).prop('checked', true);
			$("#search_by_market_index ul input[name=market_index_type]").on('change', getMarketIndexData);
		}, function(error) {

		});
	};

	var getMarketIndexData = function() {
		resetPaginationCount();
		indexFilter = $(this).attr("data-name");

		setMarketIndexSummary();
		setMarketIndexAnalytics();
		loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);
	};

	setListSidePanel();
});