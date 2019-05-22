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
			$("#consumer_market .tab-content #" + id + " .bd table tbody").html("<tr><td colspan='10' class='center'>No Matching Records Found</td></tr>");
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
				"<a href='/view/company-profile.jsp?isinCode=" + response.marketData[i].isinCode + "'>" + response.marketData[i].companyName + "</a>" +
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
				"<div class='percentChange " + percentChangeClass + "'><i class='fa " + percentChangeClass_Caret + "'></i> " + response.marketData[i].change + "</div>" +
			"</td>" +
			"<td>" + 
				"<div class='percentChange " + percentChangeClass + "'><i class='fa " + percentChangeClass_Caret + "'></i> " + ((percentChange != '-') ? percentChange + '%' : percentChange) + "</div>" +
			"</td>" +
			"<td>" + 
				response.marketData[i].volume +
			"</td>" +
			"</tr>";
		}

		$("#consumer_market .tab-content #" + id + " .bd table tbody").html(htmlCode);
		

		var paginationHtml = 	"<div class='paging_container'>"
								+ "<ul class='pager'>"
								 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
								 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
								 + "<li><span id='records_stats'></span></li>"
								 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
								 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
							 	+ "</ul>"
							 + "</div>";
		$("#consumer_market .tab-content #" + id).append(paginationHtml);
		$("#consumer_market .tab-content #" + id + " .pager a").on('click', getPaginationIndex);


		setRecordStats(currentIndex, lastPageNumber);
	}

	var firstPageNumber = 1;
	var pageNumber = 1;
	var lastPageNumber = 1;
	var totalRecords = 0;
	var currentIndex = 1;
	var perPageMaxRecords = 50;
	var type = '';
	var indexFilter = 'all';
	var sortBy = 'percentChange';
	var orderBy = 'desc';

	var setRecordStats = function(currentIndex, lastPageNumber) {
		if(currentIndex > lastPageNumber) {
			currentIndex = lastPageNumber;
		}
		$("#" + id + " #records_stats").html(pageNumber + " of " + lastPageNumber);
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
		loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
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
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
		}
	};

	var getLastPageResearchReport = function(currentNumber) {
		if(currentNumber != lastPageNumber) {
			pageNumber = lastPageNumber;
			currentIndex = (pageNumber - 1) * perPageMaxRecords + 1;
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
		}
	};

	var getNextPageResearchReport = function(currentNumber) {
		if(currentNumber < lastPageNumber) {
			pageNumber = currentNumber + 1;
			currentIndex = currentIndex + perPageMaxRecords;
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
		}
	};

	var getPreviousPageResearchReport = function(currentNumber) {
		if(currentNumber > 1) {
			pageNumber = currentNumber - 1;
			currentIndex = currentIndex - perPageMaxRecords;
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
		}
	};

	function loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy) {
		isProgressLoader(true);

		getRecordStats(indexFilter, type, perPageMaxRecords).then(function(stats) {
			if(stats != ""){

				stats = JSON.parse(stats);
				firstPageNumber = stats.firstPageNumber;
				lastPageNumber = stats.lastPageNumber;
				totalRecords = stats.totalRecords;
				$("#consumer_market .tab-content #" + id + " #total_records_count").html(totalRecords + " Results");
				//perPageMaxRecords = Math.ceil(totalRecords / lastPageNumber);
				console.log("pageNumber: " + pageNumber);
				setTimeout(function() {

					getMarketsApi(indexFilter, type, pageNumber, sortBy, orderBy).then(function(serverResponse) {
						//console.log(serverResponse);
						$("#consumer_market #" + id + " .paging_container").remove();
						var response = JSON.parse(serverResponse);
						
						getMarketsCommonHtml(response);
						isProgressLoader(false);

					}, function(error) {
						isProgressLoader(false);
						$("#" + id + " .bd table tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
					});
				}, 300);
			} else {
				isProgressLoader(false);
				resetPaginationCount();
				$("#consumer_market .tab-content #" + id + " #total_records_count").html('0 Results');
				$("#" + id + " .bd table tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
				$("#consumer_market #" + id + " .paging_container").empty();
			}

		}, function(error) {
			isProgressLoader(false);
			$("#" + id + " .bd table tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
			$("#consumer_market #" + id + " .paging_container").empty();
		});
	};

	var resetFilters = function(e) {
		resetPaginationCount();
		$("#consumer_market .tab-content  #market_data_output .max_per_page select").val($("#consumer_market .tab-content #market_data_output .max_per_page select option").eq(3).val());
		

		indexFilter = 'all';
		type = '';
		sortBy = 'percentChange';
		orderBy = 'desc';
		
		$('#search_by_market_index ul input[name=market_index_type]').eq(0).prop('checked', true);

		$("#market_data_all").show();
		$("#market_data_output").hide();
		setAllStocksData();
	};

	$('#sidebar-panel .sidebar-heading span').on('click', resetFilters);


	// loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords);

	function getMarketsApi(indexFilter, type, pageNumber, sortBy, orderBy) {

		var url = "/system/api/markets?indexFilter=" + indexFilter + "&type=" + type +  "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortBy + "&orderBy=" + orderBy;
        return new Promise(function(resolve, reject) {
        	var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });
            //httpRequest.timeout = API_TIMEOUT_SMALL;
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
	function getRecordStats(indexFilter, type, perPageMaxRecords) {
		var url = "/system/api/markets/recordstats?indexFilter=" + indexFilter + "&type=" + type + "&perPageMaxRecords=" + perPageMaxRecords;
		return new Promise(function(resolve, reject) {
			var httpRequest = new XMLHttpRequest({
				mozSystem: true
			});

			//httpRequest.timeout = API_TIMEOUT_SMALL;
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

	function getMarketIndexSummaryAPI() {
		var url = "/system/api/markets/index/summary?indexFilter=" + indexFilter + "&type=''";
		return new Promise(function(resolve, reject) {
			var httpRequest = new XMLHttpRequest({
				mozSystem: true
			});

			//httpRequest.timeout = API_TIMEOUT_SMALL;
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

			var price_date = timeStampToDate(Number(response.indexSummary.date));
			$(".company_details .price_date").text(price_date + " | " + timeStampToDateNew(Number(response.indexSummary.date))[4]);


			var divYield = (response.indexSummary.divYield == 0) ? response.indexSummary.divYield : response.indexSummary.divYield + "%";
			var open = (response.indexSummary.open == undefined) ? '-' : response.indexSummary.open;

			$('.last_cmp').html();
			$('.index_name').text(response.indexSummary.title);
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

			//httpRequest.timeout = API_TIMEOUT_SMALL;
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

			//httpRequest.timeout = API_TIMEOUT_SMALL;
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
			var html = "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>All Stocks</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='radio' name='market_index_type' data-name='all' data-section='' checked='checked' />"
									+ "</div>"
								+ "</div>"
							+ "</li>";
			var len = response.indexNames.length;

			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.indexNames[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='radio' name='market_index_type' data-name='" + response.indexNames[i] + "' data-section='' />"
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
		type = '';
		indexFilter = window.localStorage.getItem('indexName');
		
		if(indexFilter != undefined) {
			indexFilter = window.localStorage.getItem('indexName');
			setTimeout(function() {
				$("#search_by_market_index_ul li input[data-name = '" + indexFilter + "']").prop('checked', true);
			}, 300);
		} else {
			indexFilter = $(this).attr("data-name");
		}

		if(indexFilter == 'all') {
			$("#market_data_all").show();
			$("#market_data_output").hide();
			$("#market_data_see_all").hide();
			setAllStocksData();
		} else {
			id = "market_data_output";
			$("#market_data_all").hide();
			$("#market_data_output").show();
			$("#market_data_see_all").hide();
			setMarketIndexSummary();
			setMarketIndexAnalytics();
			loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
		}
	};

	setListSidePanel();

	function getAllWinnersData() {
		indexFilter = 'all';
		type = 'winners';
		perPageMaxRecords = 5;
		id ="market_index_winners";
		var sortBy = "percentChange";
		loadDefaultMarketsWinLooseReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
	}

	var getMarketsWinnersHtml = function(response, id) {
		var len = response.marketData.length;
		var htmlCode = '';
		var rowHtml = 	"";

		$("#consumer_market .tab-content #market_data_all #" + id + " h3").text(response.title);

		if(len === 0) {
			$("#consumer_market .tab-content #market_data_all #" + id + " table tbody").html("<tr><td colspan='10' class='center'>No Matching Records Found</td></tr>");
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
				"<a href='/view/company-profile.jsp?isinCode=" + response.marketData[i].isinCode + "'>" + response.marketData[i].companyName + "</a>" +
			"</td>" + 
			"<td>" + 
				response.marketData[i].close +
			"</td>" +
			"<td>" + 
				"<div class='percentChange " + percentChangeClass + "'><i class='fa " + percentChangeClass_Caret + "'></i> " + ((percentChange != '-') ? percentChange + '%' : percentChange) + "</div>" +
			"</td>" +
			"</tr>";
		}

		$("#consumer_market .tab-content #market_data_all #" + id + " table tbody").html(htmlCode);

		var footerHtml = "";
		footerHtml = "<tr><td colspan='3'><span>See All (+)</span></td></tr>"
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").html(footerHtml);
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").off('click', getSeeAllReport);
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").one('click', getSeeAllReport);
	
	}

	function loadDefaultMarketsWinLooseReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy) {
		isProgressLoader(true);

		getMarketsApi(indexFilter, type, pageNumber, sortBy, orderBy).then(function(serverResponse) {
			var response = JSON.parse(serverResponse);

			getMarketsWinnersHtml(response, id);
			isProgressLoader(false);

		}, function(error) {
			isProgressLoader(false);
			$("#market_data_all #" + id + " table tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
		});
	};



	function getAllLoosersData() {
		indexFilter = 'all';
		type = 'loosers';
		perPageMaxRecords = 5;
		id ="market_index_loosers";
		var sortBy = "percentChange";
		var orderBy = "asc";
		loadDefaultMarketsWinLooseReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
	}

	var getMostActiveTodayHtml = function(response, id) {
		var len = response.marketData.length;
		var htmlCode = '';
		var rowHtml = 	"";

		$("#consumer_market .tab-content #market_data_all #" + id + " h3").text(response.title);

		if(len === 0) {
			$("#consumer_market .tab-content #market_data_all #" + id + " table tbody").html("<tr><td colspan='10' class='center'>No Matching Records Found</td></tr>");
			return;
		}

		for(var i = 0; i < len; i++) {

			htmlCode = htmlCode + "<tr>" +
			"<td>" + 
				"<a href='/view/company-profile.jsp?isinCode=" + response.marketData[i].isinCode + "'>" + response.marketData[i].companyName + "</a>" +
			"</td>" + 
			"<td>" + 
				response.marketData[i].close +
			"</td>" +
			"<td>" + 
				response.marketData[i].volume +
			"</td>" +
			"</tr>";
		}

		$("#consumer_market .tab-content #market_data_all #" + id + " table tbody").html(htmlCode);

		var footerHtml = "";
		footerHtml = "<tr><td colspan='3'><span>See All (+)</span></td></tr>"
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").html(footerHtml);
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").off('click', getSeeAllReport);
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").one('click', getSeeAllReport);
	
	}

	function loadDefaultMostActiveTodayReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy) {
		isProgressLoader(true);

		getMarketsApi(indexFilter, type, pageNumber, sortBy, orderBy).then(function(serverResponse) {
			var response = JSON.parse(serverResponse);

			getMostActiveTodayHtml(response, id);
			isProgressLoader(false);

		}, function(error) {
			isProgressLoader(false);
			$("#market_data_all #market_index_active table tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
		});
	};



	function getMostActiveTodayData() {
		indexFilter = 'all';
		type = 'active';
		perPageMaxRecords = 5;
		id = "market_index_active";
		var sortBy = 'volume';
		loadDefaultMostActiveTodayReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
	}

	var get52wHighLowHtml = function(response, id) {
		var len = response.marketData.length;
		var htmlCode = '';
		var rowHtml = 	"";

		$("#consumer_market .tab-content #market_data_all #" + id + " h3").text(response.title);

		if(len === 0) {
			$("#consumer_market .tab-content #market_data_all #" + id + " table tbody").html("<tr><td colspan='10' class='center'>No Matching Records Found</td></tr>");
			return;
		}

		for(var i = 0; i < len; i++) {

			htmlCode = htmlCode + "<tr>" +
			"<td>" + 
				"<a href='/view/company-profile.jsp?isinCode=" + response.marketData[i].isinCode + "'>" + response.marketData[i].companyName + "</a>" +
			"</td>" + 
			"<td>" + 
				response.marketData[i].close +
			"</td>" +
			"</tr>";
		}

		$("#consumer_market .tab-content #market_data_all #" + id + " table tbody").html(htmlCode);

		var footerHtml = "";
		footerHtml = "<tr><td colspan='2'><span>See All (+)</span></td></tr>"
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").html(footerHtml);
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").off('click', getSeeAllReport);
		$("#consumer_market .tab-content #market_data_all #" + id + " table tfoot").one('click', getSeeAllReport);
	
	}

	var getMarketsSeeAllCommonHtml = function(response) {
		var len = response.marketData.length;
		var htmlCode = '';
		var rowHtml = 	"";
		
		$("#consumer_market #market_data_see_all .back_btn span.title").text(response.title);

		if(len === 0) {
			$("#consumer_market .tab-content #market_data_see_all table#market_see_all_data tbody").html("<tr><td colspan='10' class='center'>No Matching Records Found</td></tr>");
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
				"<a href='/view/company-profile.jsp?isinCode=" + response.marketData[i].isinCode + "'>" + response.marketData[i].companyName + "</a>" +
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
				"<div class='percentChange " + percentChangeClass + "'><i class='fa " + percentChangeClass_Caret + "'></i> " + response.marketData[i].change + "</div>" +
			"</td>" +
			"<td>" + 
				"<div class='percentChange " + percentChangeClass + "'><i class='fa " + percentChangeClass_Caret + "'></i> " + ((percentChange != '-') ? percentChange + '%' : percentChange) + "</div>" +
			"</td>" +
			"<td>" + 
				response.marketData[i].volume +
			"</td>" +
			"</tr>";
		}

		$("#consumer_market .tab-content #market_data_see_all table#market_see_all_data tbody").html(htmlCode);
		

		var paginationHtml = 	"<div class='paging_container'>"
								+ "<ul class='pager'>"
								 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
								 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
								 + "<li><span id='records_stats'></span></li>"
								 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
								 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
							 	+ "</ul>"
							 + "</div>";
		$("#consumer_market .tab-content #market_data_see_all").append(paginationHtml);
		$("#consumer_market .tab-content #market_data_see_all .pager a").on('click', getPaginationIndex);


		setRecordStats(currentIndex, lastPageNumber);
	}

	function loadDefaultSeeAllMarketReport(indexFilter, type, perPageMaxRecords, sortBy, orderBy) {
		isProgressLoader(true);

		getRecordStats(indexFilter, type, perPageMaxRecords).then(function(stats) {
			if(stats != ""){

				stats = JSON.parse(stats);
				firstPageNumber = stats.firstPageNumber;
				lastPageNumber = stats.lastPageNumber;
				totalRecords = stats.totalRecords;
				$("#consumer_market .tab-content #market_data_see_all #total_records_count").html(totalRecords + " Results");
				//perPageMaxRecords = Math.ceil(totalRecords / lastPageNumber);
				console.log("pageNumber: " + pageNumber);
				setTimeout(function() {
					getMarketsApi(indexFilter, type, pageNumber, sortBy, orderBy).then(function(serverResponse) {
						//console.log(serverResponse);
						$("#consumer_market .paging_container").remove();
						var response = JSON.parse(serverResponse);
						
						getMarketsSeeAllCommonHtml(response);
						$("#market_data_all").hide();
						$("#market_data_see_all").show();
						isProgressLoader(false);

					}, function(error) {
						isProgressLoader(false);
						$("#market_data_all").hide();
						$("#market_data_see_all").show();
						$("#market_data_see_all table#market_see_all_data tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
					});

				}, 300);
			} else {
				isProgressLoader(false);
				resetPaginationCount();
				$("#consumer_market .tab-content #market_data_see_all #total_records_count").html('0 Results');
				$("#market_data_see_all table#market_see_all_data tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
				$("#consumer_market #market_see_all_data .paging_container").empty();
			}
		}, function(error) {
			isProgressLoader(false);
			$("#market_data_see_all table#market_see_all_data tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
			$("#consumer_market #market_see_all_data .paging_container").empty();
		});
	};

	function getSeeAllReport(e) {
		indexFilter = 'all';
		type = $(this).parent('table').parent().attr('id').replace('market_index_', '');//'52wHigh';
		perPageMaxRecords = 50;
		id ="market_data_see_all";
		var sortBy = type;
		var orderBy = "desc";

		if(type == 'active') {
			sortBy = 'volume';
		} else if(type == 'loosers') {
			sortBy = 'percentChange';
			orderBy = "asc";
		} else if(type == 'winners') {
			sortBy = 'percentChange';
		}
		loadDefaultSeeAllMarketReport(indexFilter, type, perPageMaxRecords, sortBy, orderBy);
	}

	function loadDefault52wHighLowReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy) {
		isProgressLoader(true);

		getMarketsApi(indexFilter, type, pageNumber, sortBy, orderBy).then(function(serverResponse) {
			var response = JSON.parse(serverResponse);

			get52wHighLowHtml(response, id);
			isProgressLoader(false);

		}, function(error) {
			isProgressLoader(false);
			$("#market_data_all table#" + id + " tbody").html("<tr><td colspan='10' class='center'>We are not able to get the info, please try again later.</td></tr>");
		});
	};

	function get52wHighData() {
		indexFilter = 'all';
		type = '52wHigh';
		perPageMaxRecords = 5;
		id ="market_index_52wHigh";
		var sortBy = type;
		loadDefault52wHighLowReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
	}

	function get52wLowData() {
		indexFilter = 'all';
		type = '52wLow';
		perPageMaxRecords = 5;
		id ="market_index_52wLow";
		var sortBy = type;
		loadDefault52wHighLowReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
	}

	function setMarketAllIndexSummary() {
		getMarketIndexSummaryAPI().then(function(response) {
			response = JSON.parse(response);

			$("#consumer_market #market_data_see_all .index_name").text(response.indexSummary.title);
			$("#consumer_market #market_data_all .index_name").text(response.indexSummary.title);
			var price_date = timeStampToDate(Number(response.indexSummary.date));
			$("#market_data_all .company_details .price_date").text(price_date + " | " + timeStampToDateNew(Number(response.indexSummary.date))[4]);
			
		}, function(error) {

		});
	}

	function setAllStocksData() {

		setMarketAllIndexSummary();
		setMarketIndexAnalytics();
		getAllWinnersData();
		getAllLoosersData();
		getMostActiveTodayData();
		get52wHighData();
		get52wLowData();
	}

	$("#market_data_all").show();
	$("#market_data_output").hide();

	if(window.localStorage.getItem('indexName') != undefined) {
		getMarketIndexData();
		window.localStorage.removeItem('indexName');
	} else {
		setAllStocksData();
	}

	$("#market_data_see_all .back_btn a").on('click', function() {
		$("#market_data_all").show();
		$("#market_data_see_all").hide();
		setAllStocksData();
	});

	var getSortedByValue = function() {
		
		if($(this).attr('data-id') == undefined) {
			return;
		}
		
		if($(this).attr('data-id') == sortBy) {
			
			if(orderBy == "desc") {
				orderBy = "asc";
			} else if(orderBy == "asc") {
				orderBy = "desc";
			}
		} else {
			orderBy = "desc";
		}

		sortBy = $(this).attr('data-id');
		loadDefaultMarketsReport(indexFilter, type, perPageMaxRecords, id, sortBy, orderBy);
	}
	$('table thead a').on('click', getSortedByValue);

});