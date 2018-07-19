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
	};

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
			"<div class='brokerRankLargeCap warning' data-toggle='tooltip' title='Large Cap'>" + brokerRankLargeCapStarHtml + "</div>" + 
			"<div class='brokerRankMidCap warning' data-toggle='tooltip' title='Mid Cap'>" + brokerRankMidCapStarHtml + "</div>" + 
			"<div class='brokerRankSmallCap warning' data-toggle='tooltip' title='Small Cap'>" + brokerRankSmallCapStarHtml + "</div>" +
			"</td>" +
			"<td>" + 
			"<div class='cmp'> Rs. " + response.equity[i].cmp + "</div>" + 
			"<div class='priceDate'>" + timeStampToDate(Number(response.equity[i].priceDate)) + "</div>" + 
			"<div class='pe'>" + response.equity[i].pe + "</div>" + 
			"<div class='_3YrPatGrowth " + _3YrPatGrowthClass + "'><i class='fa " + _3YrPatGrowthClass_Caret + "'></i> " + ((response.equity[i]._3YrPatGrowth != 'NA') ? Math.round(response.equity[i]._3YrPatGrowth * 100) / 100 + '%' : response.equity[i]._3YrPatGrowth) + "</div>" +
			"</td>" +
			"<td>" + 
			"<div class='recommType " + recommTypeClass + "'>" + response.equity[i].recommType + "</div>" + 
			"<div class='targetPrice'> Rs. " + response.equity[i].targetPrice + "</div>" + 
			"<div class='priceAtRecomm'>" + ((response.equity[i].priceAtRecomm == '') ? "N/A" : response.equity[i].priceAtRecomm) + "</div>" + 
			"<div class='upside " + upsideClass + "'>" + ((response.equity[i].upside != 'NA') ? Math.round(response.equity[i].upside * 100) / 100 + '%' : response.equity[i].upside) + "</div>" +
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
		$('#broker_table tbody tr td .company').on('click', getCompanyProfile);
		$('#broker_table tbody tr td .report a').on('click', getReport);
		$('#fv_equity_research_report_vendor_search .pager a').on('click', getPaginationIndex);


		setRecordStats(currentIndex, lastPageNumber);
	}

	var firstPageNumber = 1;
	var pageNumber = 1;
	var lastPageNumber = 1;
	var totalRecords = 1;
	var currentIndex = 1;
	var perPageMaxRecords = 5;
	var sortByValue = 'researchDate';
	var orderBy = 'desc';

	var setRecordStats = function(currentIndex, lastPageNumber) {
		if(currentIndex > lastPageNumber) {
			currentIndex = lastPageNumber;
		}
		$("#records_stats").html(currentIndex + " of " + lastPageNumber);
	}

	var resetPaginationCount = function() {
		firstPageNumber = 1;
		pageNumber = 1;
		lastPageNumber = 1;
		totalRecords = 1;
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
	

	var getSortedByValue = function() {
		
		if($(this).attr('data-id') == undefined) {
			return;
		}
		
		if($(this).attr('data-id') == sortByValue) {
			
			if(orderBy == "desc") {
				orderBy = "asc";
			} else if(orderBy == "asc") {
				orderBy = "desc";
			}
		} else {
			orderBy = "desc";
		}

		sortByValue = $(this).attr('data-id');
		loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
	}
	$('#broker_table thead a').on('click', getSortedByValue);

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
				console.log(error);
			});
		}, function(error) {

		});
	};

	var getCompanyProfile = function(e) {
		var isinCode = $(this).parents('tr').attr('data-code');
		var companyProfileJson = {
			isinCode : isinCode,
		}
		window.localStorage.setItem('companyProfileJson', JSON.stringify(companyProfileJson));
		window.location.href = "/view/company-profile.jsp";
	};

	var getReport = function(e) {
		if(!isLoggedInUser()) {
			var vendorName = $(this).attr("data-vendor");
			var productId = $(this).parents('tr').attr('data-id');
			//console.log(productId);
			var dasboardReportJson = {
				equitysearchjson : window.localStorage.getItem("equitysearchjson"),
				productId : productId,
				vendorName : vendorName,
				pageNumber : pageNumber,
				perPageMaxRecords : perPageMaxRecords,
				sortByValue : sortByValue,
				orderBy : orderBy
			}
			window.localStorage.setItem('dasboardReportJson', JSON.stringify(dasboardReportJson));
		} else {
			e.preventDefault();
			e.stopPropagation();
			inner_login('view/equity_research_report_vendor.jsp');
		}
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

    var clearSelection = function() {
    	$("#sidebar-panel input").prop('checked', false);
    	$("#search_by_geo input").eq(0).prop('checked', true);
		$("#search_by_marketcapital ul input").eq(0).prop('checked', true);
		$("#search_by_style ul input").eq(0).prop('checked', true);
		$("#search_by_analystType ul input").eq(0).prop('checked', true);

    	marketCapitalData = [];
    	styleFilterData = [];
    	analystTypeFilterData = [];
    	researchBrokerFilterData = [];
    	brokerAnalystYrOfIncorpFilterData = [];
    	brokerRankFilterData = [];
    	recommTypeData = [];
    	upsideFilterData = [];
    	othersFilterData = [];

    	/*To clear Research Broker/Analyst*/
    	$("#search_broker").val('');
    	$("#search_by_researchbroker_ul li").show();
    };

	var localEquitySearchJson = {
		"geo": "1"
	};

	window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
	loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);

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
										+ "<input type='radio' name='geography_type' data-name='" + response[i].name + "' data-section='' data-value='" + response[i].countryId + "' />"
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
		if(!isLoggedInUser()) {
			localEquitySearchJson.geo = $("input[name=geography_type]:checked").attr('data-value');
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$('#search_by_geo ul input[name=geography_type]').eq(0).prop('checked', true);
		}
	};


	/**
     * Function to set market capital from filter data API.
     */
	var setMarketCapitalData = function() {
		getFilterData('marketcapital').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>All</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='all' data-section='' data-value='all' />"
									+ "</div>"
								+ "</div>"
							+ "</li>";

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
			$("#search_by_marketcapital ul input").eq(0).prop('checked', true);
		}, function(error) {
			console.log(error);
		});
	};

	var marketCapitalData = [];

    /**
     * Function to get analyst Type from localstorage and get equity list
     */
	var getMarketCapitalData = function() {
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(marketCapitalData, $(this).attr('data-value'));


			if($(this).attr('data-value') == 'all') {
				marketCapitalData = ['all'];
				if(checkForAllData(marketCapitalData, "#search_by_marketcapital ul input")) {
					marketCapitalData = [];
				} 
			} else {
				$("#search_by_marketcapital ul input").eq(0).prop('checked', false);
			}

			
			localEquitySearchJson.mcap = marketCapitalData;

			if(marketCapitalData.length === 0) {
				delete localEquitySearchJson.mcap;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_marketcapital ul input").prop('checked', false);
			$("#search_by_marketcapital ul input").eq(0).prop('checked', true);
		}
	};


	/**
     * Function to set style from filter data API.
     */
	var setStyleData = function() {
		getFilterData('style').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>All</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='all' data-section='' data-value='all' />"
									+ "</div>"
								+ "</div>"
							+ "</li>";

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
			$("#search_by_style ul input").eq(0).prop('checked', true);
		}, function(error) {
			console.log(error);
		});
	};

	var styleFilterData = [];

    /**
     * Function to get style from localstorage and get equity list
     */
	var getStyleFilterData = function() {
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(styleFilterData, $(this).attr('data-value'));

			if($(this).attr('data-value') == 'all') {
				styleFilterData = ['all'];
				if(checkForAllData(styleFilterData, "#search_by_style ul input")) {
					styleFilterData = [];
				} 
			} else {
				$("#search_by_style ul input").eq(0).prop('checked', false);
			}
			
			localEquitySearchJson.style = styleFilterData;

			if(styleFilterData.length === 0) {
				delete localEquitySearchJson.style;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_style ul input").prop('checked', false);
			$("#search_by_style ul input").eq(0).prop('checked', true);
		}
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
			$("#search_by_analystType ul input").eq(0).prop('checked', true);
		}, function(error) {
			console.log(error);
		});
	};

	var analystTypeFilterData = [];

    /**
     * Function to get analyst Type from localstorage and get equity list
     */
	var getAnalystTypeFilterData = function() {
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(analystTypeFilterData, $(this).attr('data-value'));
			
			localEquitySearchJson.analystType = analystTypeFilterData;

			if(analystTypeFilterData.length === 0) {
				delete localEquitySearchJson.analystType;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_analystType ul input").prop('checked', false);
			$("#search_by_analystType ul input").eq(0).prop('checked', true);
		}
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
									+ "<span>" + response[i].companyName + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].companyName + "' data-section='' data-value='" + response[i].companyName + "' />"
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
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(researchBrokerFilterData, $(this).attr('data-value'));
			
			localEquitySearchJson.researchedBroker = researchBrokerFilterData;

			if(researchBrokerFilterData.length === 0) {
				delete localEquitySearchJson.researchedBroker;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_researchbroker ul input").prop('checked', false);
			$("#search_by_researchbroker ul input").eq(0).prop('checked', true);
		}
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
			$("#search_by_brokerAnalystYrOfIncorp ul input").eq(0).prop('checked', true);
		}, function(error) {
			console.log(error);
		});
	};

	var brokerAnalystYrOfIncorpFilterData = [];

    /**
     * Function to get Broker Analyst Yr Of Incorp from localstorage and get equity list
     */
	var getBrokerAnalystYrOfIncorpFilterData = function() {
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(brokerAnalystYrOfIncorpFilterData, $(this).attr('data-value'));
			
			localEquitySearchJson.brokerYrOfInCorp = brokerAnalystYrOfIncorpFilterData;

			if(brokerAnalystYrOfIncorpFilterData.length === 0) {
				delete localEquitySearchJson.brokerYrOfInCorp;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_brokerAnalystYrOfIncorp ul input").prop('checked', false);
			$("#search_by_brokerAnalystYrOfIncorp ul input").eq(0).prop('checked', true);
		}
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
			$("#search_by_broker_rank ul input").eq(0).prop('checked', true);
		}, function(error) {
			console.log(error);
		});
	};

	var brokerRankFilterData = [];

    /**
     * Function to get Broker Analyst Yr Of Incorp from localstorage and get equity list
     */
	var getBrokerRankFilterData = function() {
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(brokerRankFilterData, $(this).attr('data-value'));
			
			localEquitySearchJson.brokerRank = brokerRankFilterData;

			if(brokerRankFilterData.length === 0) {
				delete localEquitySearchJson.brokerRank;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_broker_rank ul input").prop('checked', false);
			$("#search_by_broker_rank ul input").eq(0).prop('checked', true);
		}
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
			$("#search_by_recommType ul input").eq(0).prop('checked', true);
		}, function(error) {
			console.log(error);
		});
	};

	var recommTypeData = [];

    /**
     * Function to get recommendation Type from localstorage and get equity list
     */
	var getRecommendationTypeData = function() {
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(recommTypeData, $(this).attr('data-value'));
			
			localEquitySearchJson.recommType = recommTypeData;

			if(recommTypeData.length === 0) {
				delete localEquitySearchJson.recommType;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_recommType ul input").prop('checked', false);
			$("#search_by_recommType ul input").eq(0).prop('checked', true);
		}
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
			$("#search_by_upside ul input").eq(0).prop('checked', true);
		}, function(error) {
			console.log(error);
		});
	};

	var upsideFilterData = [];

    /**
     * Function to get upside from localstorage and get equity list
     */
	var getUpsideFilterData = function() {
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(upsideFilterData, $(this).attr('data-value'));
			
			localEquitySearchJson.upside = upsideFilterData;

			if(upsideFilterData.length === 0) {
				delete localEquitySearchJson.upside;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_upside ul input").prop('checked', false);
			$("#search_by_upside ul input").eq(0).prop('checked', true);
		}
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
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response[i].others + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response[i].others + "' data-section='' data-value='" + response[i].others + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_others ul").html(html);
			$("#search_by_others ul input").on('change', getOthersFilterData);
			$("#search_by_others ul input").eq(0).prop('checked', true);
		}, function(error) {
			console.log(error);
		});
	};

	var othersFilterData = [];

    /**
     * Function to get Others from localstorage and get equity list
     */
	var getOthersFilterData = function() {
		if(!isLoggedInUser()) {
			addRemoveItemFromArray(othersFilterData, $(this).attr('data-value'));
			
			localEquitySearchJson.others = othersFilterData;

			if(othersFilterData.length === 0) {
				delete localEquitySearchJson.others;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/equity_research_report_vendor.jsp');
			$("#search_by_others ul input").prop('checked', false);
			$("#search_by_others ul input").eq(0).prop('checked', true);
		}
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

	function isProgressLoader(status) {
		if(status === true) {
			$("#progressLoader").show();
		} else {
			$("#progressLoader").hide();
		}
	}

});