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

	var getBreachAnalyticsHtml = function(response) {
		var len = response.ConsumerAnalytics.length;
		var htmlCode = '';
		var rowHtml = 	"";

		if(len === 0) {
			$("#consumer_analytics .tab-content #" + id + " #equity_analytics_table tbody").html("<tr><td colspan='6'>No Matching Records Found</td></tr>");
			return;
		}

		for(var i = 0; i < len; i++) {



			htmlCode = htmlCode + "<tr>" +
			"<td>" + 
				response.ConsumerAnalytics[i].userName +
			"</td>" + 
			"<td>" + 
				timeStampToDate(Number(response.ConsumerAnalytics[i].registrationDate)) +
			"</td>" +
			"<td>" + 
				timeStampToDate(Number(response.ConsumerAnalytics[i].lastLogin)) +
			"</td>" +
			"<td>" + 
				response.ConsumerAnalytics[i].ipAddress +
			"</td>" +
			"<td>" + 
				response.ConsumerAnalytics[i].hitCount +
			"</td>" +
			"<td>" + 
				response.ConsumerAnalytics[i].breachFlag +
			"</td>" +
			"</tr>";
		}

		$("#consumer_analytics .tab-content #" + id + " #equity_analytics_table tbody").html(htmlCode);
		$("#consumer_analytics .tab-content #" + id + " .download_report a").attr('href', '/system/api/consumeranalytics/download?type=' + type + '&subType=' + breachType);


		var paginationHtml = 	"<div class='paging_container'>"
								+ "<ul class='pager'>"
								 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
								 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
								 + "<li><span id='records_stats'></span></li>"
								 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
								 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
							 	+ "</ul>"
							 + "</div>";

		$("#consumer_analytics .tab-content #" + id + "").append(paginationHtml);
		$("#consumer_analytics .tab-content #" + id + " .pager a").on('click', getPaginationIndex);


		setRecordStats(currentIndex, lastPageNumber);
	}
		$("#consumer_analytics .tab-content .download_report").on('click', downloadReport);

	var firstPageNumber = 1;
	var pageNumber = 1;
	var lastPageNumber = 1;
	var totalRecords = 0;
	var currentIndex = 1;
	var perPageMaxRecords = 5;
	var type = 'equity';
	var breachType = 'd';
	var breachLevel = 'all';

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
		perPageMaxRecords = 5;

		$("#consumer_analytics .tab-content  #" + id + " .max_per_page select").val($("#consumer_analytics .tab-content #" + id + " .max_per_page select option:first").val());
	}
	
	var getPerPageMaxRecords = function() {
		if(perPageMaxRecords !== Number($(this).val())) {
			pageNumber = 1;
			firstPageNumber = 1;
			lastPageNumber = 1;
			currentIndex = 1;
		}
		perPageMaxRecords = Number($(this).val());
		loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords);
	}
	$('#consumer_analytics .max_per_page select').on('change', getPerPageMaxRecords);

	var getBreachLevelStatus = function() {
		breachLevel = $(this).val();
		resetPaginationCount();
		loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords);
	}
	$('#consumer_analytics .breach_level select').on('change', getBreachLevelStatus);
	
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
			loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords);
		}
	};

	var getLastPageResearchReport = function(currentNumber) {
		if(currentNumber != lastPageNumber) {
			pageNumber = lastPageNumber;
			currentIndex = (pageNumber - 1) * perPageMaxRecords + 1;
			loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords);
		}
	};

	var getNextPageResearchReport = function(currentNumber) {
		if(currentNumber < lastPageNumber) {
			pageNumber = currentNumber + 1;
			currentIndex = currentIndex + perPageMaxRecords;
			loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords);
		}
	};

	var getPreviousPageResearchReport = function(currentNumber) {
		if(currentNumber > 1) {
			pageNumber = currentNumber - 1;
			currentIndex = currentIndex - perPageMaxRecords;
			loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords);
		}
	};

	function loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords) {
		isProgressLoader(true);

		getRecordStats(type, breachType, breachLevel, perPageMaxRecords).then(function(stats) {
			stats = JSON.parse(stats);
			firstPageNumber = stats.firstPageNumber;
			lastPageNumber = stats.lastPageNumber;
			totalRecords = stats.totalRecords;
			$("#consumer_analytics .tab-content #" + id + " #total_records_count").html(totalRecords + " Results");
			//perPageMaxRecords = Math.ceil(totalRecords / lastPageNumber);
			console.log("pageNumber: " + pageNumber);
			getConsumerAnalyticsReport(type, breachType, breachLevel, pageNumber).then(function(serverResponse) {
				//console.log(serverResponse);
				$("#consumer_analytics .paging_container").remove();
				var response = JSON.parse(serverResponse);
				
				getBreachAnalyticsHtml(response);
				isProgressLoader(false);

			}, function(error) {
				isProgressLoader(false);
				$("#broker_table tbody").html("<tr><td colspan='6'>We are not able to get the info, please try again later.</td></tr>");
			});
		}, function(error) {
			isProgressLoader(false);
			$("#broker_table tbody").html("<tr><td colspan='6'>We are not able to get the info, please try again later.</td></tr>");
		});
	};

	var resetFilters = function(e) {
		resetPaginationCount();
		breachLevel = 'all';
		$("#consumer_analytics .tab-content  #" + id + " .breach_level select").val($("#consumer_analytics .tab-content #" + id + " .breach_level select option:first").val());
		$("#consumer_analytics .tab-content  #" + id + " .max_per_page select").val($("#consumer_analytics .tab-content #" + id + " .max_per_page select option:first").val());
		
		loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords);
	};

	$('#sidebar-panel .sidebar-heading span').on('click', resetFilters);


	loadDefaultBreachReport(type, breachType, breachLevel, perPageMaxRecords);

	function getConsumerAnalyticsReport(researchType, subType, breachLevel, pageNumber) {

		var url = "/system/api/consumeranalytics?type=" + researchType + "&subType=" + subType + "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&breachFlag=" + breachLevel;
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
	function getRecordStats(researchType, subType, breachLevel, perPageMaxRecords) {
		var url = "/system/api/consumeranalytics/recordstats?type=" + researchType + "&subType=" + subType + "&breachFlag=" + breachLevel + "&perPageMaxRecords=" + perPageMaxRecords;
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

	/**
     * Function to start async call to get Analytics Report
     */
	function getAnalyticsReport(researchType, subType) {
		var url = "/system/api/consumeranalytics/download?type=" + researchType + "&subType=" + subType;
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

	var downloadReport = function() {
		getAnalyticsReport(type, breachType);
	}

	
	var newIndex = 0;

	var id = "d_breach_tab";
	$(".nav-tabs li").on("click", function() {
		if($(this).index() == 0 && !$(this).hasClass('active')) {
			id = "d_breach_tab";
			newIndex = 0;
			breachType = 'd';
			resetFilters();
		} else if($(this).index() == 1 && !$(this).hasClass('active')) {
			id = "rf_breach_tab";
			newIndex = 1;
			breachType = 'rf';
			resetFilters();
		}

		$('.modal-content .nav-tabs li').removeClass('active');
		$('.modal-content .nav-tabs li').eq(newIndex).addClass('active');
		$('.modal-content .tab-content .tab-pane').removeClass('active in');
		$('.modal-content .tab-content .tab-pane').eq(newIndex).addClass('active in');
	});
});