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

	var getEquityListHtml = function(response) {
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


			var _3YrEpsGrowthClass = "success";
			var _3YrEpsGrowthClass_Caret = "fa-caret-up";
			if(response.equity[i]._3YrEpsGrowth > 0) {
				_3YrEpsGrowthClass = "success";
				_3YrEpsGrowthClass_Caret = "fa-caret-up";
			} else {
				_3YrEpsGrowthClass = "danger";
				_3YrEpsGrowthClass_Caret = "fa-caret-down";
			}

			var upsideClass = "success";
			if(response.equity[i].upside > 0) {
				upsideClass = "success";
			} else {
				upsideClass = "danger";
			}

			var brokerRankGenericStarClass = "<i class='fa fa-star default'></i>";
			var brokerRankLargeCapStarClass = "<i class='fa fa-star'></i>";

			var brokerRankLargeCapStarHtml = '';
			if(response.equity[i].brokerRank.largeCap === "5") {
				brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass;
			} else if(response.equity[i].brokerRank.largeCap === "4") {
				brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.largeCap === "3") {
				brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.largeCap === "2") {
				brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.largeCap === "1") {
				brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else {
				brokerRankLargeCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			}


			var brokerRankMidCapStarClass = "<i class='fa fa-star'></i>";
			var brokerRankMidCapStarHtml = '';
			if(response.equity[i].brokerRank.midCap === "5") {
				brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass;
			} else if(response.equity[i].brokerRank.midCap === "4") {
				brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.midCap === "3") {
				brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.midCap === "2") {
				brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.midCap === "1") {
				brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else {
				brokerRankMidCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			}


			var brokerRankSmallCapStarClass = "<i class='fa fa-star'></i>";
			var brokerRankSmallCapStarHtml = '';
			if(response.equity[i].brokerRank.smallCap === "5") {
				brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass;
			} else if(response.equity[i].brokerRank.smallCap === "4") {
				brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.smallCap === "3") {
				brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.smallCap === "2") {
				brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else if(response.equity[i].brokerRank.smallCap === "1") {
				brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			} else {
				brokerRankSmallCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
			}

			htmlCode = htmlCode + "<tr data-id='" + response.equity[i].productId + "' data-code='" + response.equity[i].isinCode + "'>" +
			"<td>" + 
			"<div class='company' data-toggle='tooltip' title='See all reports for " + response.equity[i].company + "'><a href='/view/company-profile.jsp?isinCode=" + response.equity[i].isinCode + "'>" + response.equity[i].company + "</a></div>" + 
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
			"<div class='brokerRankLargeCap warning' data-toggle='tooltip' title='Large Cap'>" + brokerRankLargeCapStarHtml + "</div>" + 
			"<div class='brokerRankMidCap warning' data-toggle='tooltip' title='Mid Cap'>" + brokerRankMidCapStarHtml + "</div>" + 
			"<div class='brokerRankSmallCap warning' data-toggle='tooltip' title='Small Cap'>" + brokerRankSmallCapStarHtml + "</div>" +
			"</td>" +
			"<td>" + 
			"<div class='cmp'> Rs. " + parseFloat(response.equity[i].cmp).toFixed(2) + "</div>" + 
			"<div class='priceDate'>" + timeStampToDate(Number(response.equity[i].priceDate)) + "</div>" + 
			"<div class='pe'>" + parseFloat(response.equity[i].pe).toFixed(2) + "</div>" + 
			"<div class='_3YrEpsGrowth " + _3YrEpsGrowthClass + "'><i class='fa " + _3YrEpsGrowthClass_Caret + "'></i> " + ((response.equity[i]._3YrEpsGrowth != 'NA') ? parseFloat(Math.round(response.equity[i]._3YrEpsGrowth * 100) / 100).toFixed(2) + '%' : response.equity[i]._3YrEpsGrowth) + "</div>" +
			"</td>" +
			"<td>" + 
			"<div class='recommType " + recommTypeClass + "'>" + response.equity[i].recommType + "</div>" + 
			"<div class='targetPrice'> Rs. " + parseFloat(response.equity[i].targetPrice).toFixed(2) + "</div>" + 
			"<div class='priceAtRecomm'>" + ((response.equity[i].priceAtRecomm == '') ? "N/A" : parseFloat(response.equity[i].priceAtRecomm)).toFixed(2) + "</div>" + 
			"<div class='upside " + upsideClass + "'>" + ((response.equity[i].upside != 'NA') ? parseFloat(Math.round(response.equity[i].upside * 100) / 100).toFixed(2) + '%' : response.equity[i].upside) + "</div>" +
			"</td>" +
			"<td>"  +  
			"<div class='report' target=''><a href='research-company-report.jsp' data-toggle='tooltip' title='Go to report post' data-vendor='" + response.equity[i].vendorName + "'><i class='fa fa-file'></i></a></div>" +
			"<div class='researchDate'>" + timeStampToDate(Number(response.equity[i].researchDate)) + "</div>" +
			"<div class='analystName' data-toggle='tooltip' title='" + response.equity[i].analystName + "'>" + response.equity[i].analystName + "</div>" + 
			"</td>" +
			"</tr>";
		}

		$("#broker_table tbody").html(htmlCode);

		var paginationHtml = 	"<div class='paging_container'>"
								+ "<ul class='pager'>"
								 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
								 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
								 + "<li><span id='records_stats'></span></li>"
								 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
								 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
							 	+ "</ul>"
							 + "</div>";

		$("#fv_equity_research_report_vendor_search").append(paginationHtml);

		$('[data-toggle="tooltip"]').tooltip();
		//$('#broker_table tbody tr td .company').on('click', getCompanyProfile);
		$('#broker_table tbody tr td .report a').on('click', getReport);
		$('#fv_equity_research_report_vendor_search .pager a').on('click', getPaginationIndex);


		setRecordStats(currentIndex, lastPageNumber);
	}

	$("#fv_equity_research_report_vendor_search .max_per_page select").val($("#fv_equity_research_report_vendor_search .max_per_page select option:first").val());

	var firstPageNumber = 1;
	var pageNumber = 1;
	var lastPageNumber = 1;
	var totalRecords = 0;
	var currentIndex = 1;
	var perPageMaxRecords = 5;
	var sortByValue = 'researchDate';
	var orderBy = 'desc';

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
	}
	
	var getPerPageMaxRecords = function() {
		if(!isLoggedInUser()) {
			if(perPageMaxRecords !== Number($(this).val())) {
				pageNumber = 1;
				firstPageNumber = 1;
				lastPageNumber = 1;
				currentIndex = 1;
			}
			perPageMaxRecords = Number($(this).val());
			// console.log("perPageMaxRecords: " + perPageMaxRecords);
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
		}
	}
	$('#fv_equity_research_report_vendor_search .max_per_page select').on('change', getPerPageMaxRecords);
	
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
		if(!isLoggedInUser()) {
			if(currentNumber != firstPageNumber) {
				pageNumber = firstPageNumber;
				currentIndex = firstPageNumber;
				loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
			}
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
		}
	};

	var getLastPageResearchReport = function(currentNumber) {
		if(!isLoggedInUser()) {
			if(currentNumber != lastPageNumber) {
				pageNumber = lastPageNumber;
				currentIndex = (pageNumber - 1) * perPageMaxRecords + 1;
				loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
			}
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
		}
	};

	var getNextPageResearchReport = function(currentNumber) {
		if(!isLoggedInUser()) {
			if(currentNumber < lastPageNumber) {
				pageNumber = currentNumber + 1;
				currentIndex = currentIndex + perPageMaxRecords;
				loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
			}
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
		}
	};

	var getPreviousPageResearchReport = function(currentNumber) {
		if(!isLoggedInUser()) {
			if(currentNumber > 1) {
				pageNumber = currentNumber - 1;
				currentIndex = currentIndex - perPageMaxRecords;
				loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
			}
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
		}
	};

	function loadDefaultEquityList(jsonData, perPageMaxRecords) {
		isProgressLoader(true);

		getRecordStats("equity", jsonData, perPageMaxRecords).then(function(stats) {
			stats = JSON.parse(stats);
			firstPageNumber = stats.firstPageNumber;
			lastPageNumber = stats.lastPageNumber;
			totalRecords = stats.totalRecords;
			$("#total_records_count").html(totalRecords + " Results");
			//perPageMaxRecords = Math.ceil(totalRecords / lastPageNumber);
			console.log("pageNumber: " + pageNumber);
			getResearchReport(jsonData, "equity", pageNumber).then(function(serverResponse) {
				//console.log(serverResponse);
				$("#fv_equity_research_report_vendor_search .paging_container").remove();
				var response = JSON.parse(serverResponse);
				getEquityListHtml(response);
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
		clearSelection();
		resetPaginationCount();
		localEquitySearchJson = {
			"geo": "1"
		};
		window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
	};
	$('#sidebar-panel .sidebar-heading span').on('click', resetFilters);

	function getResearchReport(jsonData, researchType, pageNumber) {

		var url = "/system/api/researchReports?type=" + researchType + "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortByValue + "&orderBy=" + orderBy;
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

            httpRequest.send(JSON.stringify(jsonData));
        });
	};

	/**
     * Function to start async call to get record stats
     */
	function getRecordStats(researchType, jsonData, perPageMaxRecords) {
		var url = "/system/api/researchReports/recordStats?type=" + researchType + "&perPageMaxRecords=" + perPageMaxRecords;
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

			httpRequest.send(JSON.stringify(jsonData));
		});
	};
}):