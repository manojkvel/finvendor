jQuery(document).ready(function() {


	window.onbeforeunload = function() {
		if(window.localStorage.getItem('searchsector')) {
			//window.localStorage.removeItem("equitysearchjson");
		} else {
			window.localStorage.removeItem("equitysearchjson");
		}
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
		var len = response.data.length;
		var htmlCode = '';
		var rowHtml = 	"";

		if(len === 0) {
			$("#broker_table tbody").html("<tr><td colspan='6'>No Matching Records Found</td></tr>");
			return;
		}

		for(var i = 0; i < len; i++) {

			var recommTypeClass = "label-warning";
			if(response.data[i].reportTone == "buy" || response.data[i].reportTone == "accumulate"
				|| response.data[i].reportTone == "overweight"  || response.data[i].reportTone == "bullish") {
				recommTypeClass = "label-success";
			} else if(response.data[i].reportTone == "sell" || response.data[i].reportTone == "underweight"
				|| response.data[i].reportTone == "reduce"  || response.data[i].reportTone == "bearish") {
				recommTypeClass = "label-danger";
			} else {
				recommTypeClass = "label-warning";
			}


			htmlCode = htmlCode + "<tr data-id='" + response.data[i].productId + "' data-code='" + response.data[i].isinCode + "'>" +
			"<td>" + 
			"<div class='company' data-toggle='tooltip'>" + response.data[i].sectorSubType + "</div>" + 
			"<div class='sector'>" + response.data[i].sectorType + "</div>" +
			"</td>" + 
			"<td>" + 
			"<div class='broker' data-toggle='tooltip'>" + response.data[i].researchedBy + "</div>" + 
			"<div class='since'>" + response.data[i].analystType + "</div>" + 
			"</td>" +
			"<td>" + 
			"<div class='recommType " + recommTypeClass + "'>" + response.data[i].reportTone + "</div>" + 
			"<div class='priceDate'>" + response.data[i].reportFrequency + "</div>" + 
			"</td>" +
			"<td>"  +  
			"<div class='report' target=''><a href='research-company-report.jsp' data-toggle='tooltip' title='Go to report post' data-vendor='" + response.data[i].reportName + "'><i class='fa fa-file'></i></a></div>" +
			"<div class='researchDate'>" + timeStampToDate(Number(response.data[i].reportDate)) + "</div>" +
			"<div class='analystName' data-toggle='tooltip' title='" + response.data[i].analystName + "'>" + response.data[i].analystName + "</div>" + 
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
	var sortByValue = 'researchedBy';
	var orderBy = 'asc';

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
			inner_login('view/sector-research.jsp');
		}
	}
	$('#fv_equity_research_report_vendor_search .max_per_page select').on('change', getPerPageMaxRecords);
	

	var getSortedByValue = function() {

		if(isLoggedInUser()) {
			inner_login('view/sector-research.jsp');
			return;
		}
		
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
			inner_login('view/sector-research.jsp');
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
			inner_login('view/sector-research.jsp');
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
			inner_login('view/sector-research.jsp');
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
			inner_login('view/sector-research.jsp');
		}
	};

	function loadDefaultEquityList(jsonData, perPageMaxRecords) {
		isProgressLoader(true);

		getRecordStats("equity", jsonData, perPageMaxRecords).then(function(stats) {
			if(stats != "") {

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
			} else {
				isProgressLoader(false);
				$("#broker_table tbody").html("<tr><td colspan='6'>We are not able to get the info, please try again later.</td></tr>");

			}
		}, function(error) {
			isProgressLoader(false);
			$("#broker_table tbody").html("<tr><td colspan='6'>We are not able to get the info, please try again later.</td></tr>");
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

			sendGAevents('Sector Research', 'Report Click', 'Report');

			var vendorName = $(this).attr("data-vendor");
			var productId = $(this).parents('tr').attr('data-id');
			//console.log(productId);
			var dasboardReportJson = {
				type: "sector",
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
			inner_login('view/sector-research.jsp');
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

    	sectorTypeFilterData = [];
    	sectorSubTypeFilterData = [];
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

	var sectorTypeFilterData = [];
	var sectorSubTypeFilterData = [];
	
	//window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));


	function updateCurrentFilter() {
		if(window.localStorage.getItem("equitysearchjson") != undefined) {
			localEquitySearchJson = {
				"geo": "1"
			};

			if(JSON.parse(window.localStorage.getItem("equitysearchjson")).sectorType != undefined) {
				sectorTypeFilterData = [];
				sectorTypeFilterData.length = 0;
				sectorTypeFilterData.push(JSON.parse(window.localStorage.getItem("equitysearchjson")).sectorType);
			}

			if(JSON.parse(window.localStorage.getItem("equitysearchjson")).sectorSubType != undefined) {
				sectorSubTypeFilterData = [];
				sectorSubTypeFilterData.length = 0;
				sectorSubTypeFilterData.push(JSON.parse(window.localStorage.getItem("equitysearchjson")).sectorSubType);
			}

			if(sectorTypeFilterData.length != 0 && sectorSubTypeFilterData.length !=0) {
				localEquitySearchJson = {
					"geo" : 1,
					"sectorType" : sectorTypeFilterData,
					"sectorSubType" : sectorSubTypeFilterData
				};
				window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
				window.localStorage.removeItem('searchsector');
				window.localStorage.removeItem("equitysearchjson");

				setTimeout(function() {
					$("#search_by_sector_type_ul li input[data-name = '" + sectorTypeFilterData + "']").prop('checked', true);
					$("#search_by_sector_subtype_ul li input[data-name = '" + sectorSubTypeFilterData + "']").prop('checked', true);
				}, 300);
			}
		}
	}


	
	updateCurrentFilter();
	window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
	loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);

	function getResearchReport(jsonData, researchType, pageNumber) {

		var url = "/system/api/sectorreports?pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortByValue + "&orderBy=" + orderBy;
        return new Promise(function(resolve, reject) {
        	var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });
            //httpRequest.timeout = API_TIMEOUT_SMALL;
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
		var url = "/system/api/sectorreports/recordstats?perPageMaxRecords=" + perPageMaxRecords;
		return new Promise(function(resolve, reject) {
			var httpRequest = new XMLHttpRequest({
				mozSystem: true
			});

			//httpRequest.timeout = API_TIMEOUT_SMALL;
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
     * Function to start async call to get filter data.
     * @param {filterType} filterType - filter type value.
     */
	var getFilterData = function(filterType) {
		var url = "/system/api/sectorreports/filtervalue?type=" + filterType;
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
			//console.log(error);
		});
	};

	/**
     * Function to get geo Type from localstorage and get equity list.
     */
	var getGeoData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Equity Research', 'Filter by Geo onClick', 'Filter by Geo');

			localEquitySearchJson.geo = $("input[name=geography_type]:checked").attr('data-value');
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/sector-research.jsp');
			$('#search_by_geo ul input[name=geography_type]').eq(0).prop('checked', true);
		}
	};


	/**
     * Function to set market capital from filter data API.
     */
	var setSectorTypeData = function() {
		getFilterData('sectorType').then(function(response) {
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

			var len = response.data.length;

			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.data[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response.data[i] + "' data-section='' data-value='" + response.data[i] + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_sector_type ul").html(html);
			$("#search_by_sector_type ul input").on('change', getSectorTypeData);
			// $("#search_by_sector_type ul input").eq(0).prop('checked', true);
		}, function(error) {
			//console.log(error);
		});
	};

	//var sectorTypeFilterData = [];

    /**
     * Function to get analyst Type from localstorage and get equity list
     */
	var getSectorTypeData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Sector Research', 'Filter by Sector Type onClick', 'Filter by SectorType');

			addRemoveItemFromArray(sectorTypeFilterData, $(this).attr('data-value'));


			if($(this).attr('data-value') == 'all') {
				sectorTypeFilterData = ['all'];
				if(checkForAllData(sectorTypeFilterData, "#search_by_sector_type ul input")) {
					sectorTypeFilterData = [];
				} 
			} else {
				$("#search_by_sector_type ul input").eq(0).prop('checked', false);
			}

			
			localEquitySearchJson.sectorType = sectorTypeFilterData;

			if(sectorTypeFilterData.length === 0) {
				delete localEquitySearchJson.sectorType;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/sector-research.jsp');
			$("#search_by_sector_type ul input").prop('checked', false);

			// $("#search_by_sector_type ul input").eq(0).prop('checked', true);
		}
	};


	/**
     * Function to set style from filter data API.
     */
	var setSectorSubTypeData = function() {
		getFilterData('sectorSubType').then(function(response) {
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

			var len = response.data.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.data[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response.data[i] + "' data-section='' data-value='" + response.data[i] + "' />"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_sector_subtype ul").html(html);
			$("#search_by_sector_subtype ul input").on('change', getSectorSubTypeData);
			// $("#search_by_sector_subtype ul input").eq(0).prop('checked', true);
		}, function(error) {
			//console.log(error);
		});
	};

	//var sectorSubTypeFilterData = [];

    /**
     * Function to get sectorSubType from localstorage and get equity list
     */
	var getSectorSubTypeData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Sector Research', 'Filter by Sector SubType onClick', 'Filter by SectorSubType');

			addRemoveItemFromArray(sectorSubTypeFilterData, $(this).attr('data-value'));

			if($(this).attr('data-value') == 'all') {
				sectorSubTypeFilterData = ['all'];
				if(checkForAllData(sectorSubTypeFilterData, "#search_by_sector_subtype ul input")) {
					sectorSubTypeFilterData = [];
				} 
			} else {
				$("#search_by_sector_subtype ul input").eq(0).prop('checked', false);
			}
			
			localEquitySearchJson.sectorSubType = sectorSubTypeFilterData;

			if(sectorSubTypeFilterData.length === 0) {
				delete localEquitySearchJson.sectorSubType;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/sector-research.jsp');
			$("#search_by_sector_subtype ul input").prop('checked', false);
			// $("#search_by_sector_subtype ul input").eq(0).prop('checked', true);
		}
	};


	/**
     * Function to set analyst Type from filter data API.
     */
	var setAnalystTypeFilterData = function() {
		getFilterData('analystType').then(function(response) {
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

			var len = response.data.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.data[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response.data[i] + "' data-section='' data-value='" + response.data[i] + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_analystType ul").html(html);
			$("#search_by_analystType ul input").on('change', getAnalystTypeFilterData);
			// $("#search_by_analystType ul input").eq(0).prop('checked', true);
		}, function(error) {
			//console.log(error);
		});
	};

	var analystTypeFilterData = [];

    /**
     * Function to get analyst Type from localstorage and get equity list
     */
	var getAnalystTypeFilterData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Sector Research', 'Filter by AnalystType onClick', 'Filter by AnalystType');

			addRemoveItemFromArray(analystTypeFilterData, $(this).attr('data-value'));


			if($(this).attr('data-value') == 'all') {
				analystTypeFilterData = ['all'];
				if(checkForAllData(analystTypeFilterData, "#search_by_analystType ul input")) {
					analystTypeFilterData = [];
				} 
			} else {
				$("#search_by_analystType ul input").eq(0).prop('checked', false);
			}

			
			localEquitySearchJson.analystType = analystTypeFilterData;

			if(analystTypeFilterData.length === 0) {
				delete localEquitySearchJson.analystType;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/sector-research.jsp');
			$("#search_by_analystType ul input").prop('checked', false);
			// $("#search_by_analystType ul input").eq(0).prop('checked', true);
		}
	};


	/**
     * Function to set Research Broker from filter data API.
     */
	var setResearchBrokerData = function() {
		getFilterData('researchedBy').then(function(response) {
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

			var len = response.data.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.data[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response.data[i] + "' data-section='' data-value='" + response.data[i] + "' />"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_researchbroker ul").html(html);
			$("#search_by_researchbroker ul input").on('change', getResearchBrokerFilterData);
		}, function(error) {
			//console.log(error);
		});
	};

	var researchBrokerFilterData = [];

    /**
     * Function to get Research Broker from localstorage and get equity list
     */
	var getResearchBrokerFilterData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Sector Research', 'Filter by ResearchBy onClick', 'Filter by ResearchBy');

			addRemoveItemFromArray(researchBrokerFilterData, $(this).attr('data-value'));


			if($(this).attr('data-value') == 'all') {
				researchBrokerFilterData = ['all'];
				if(checkForAllData(researchBrokerFilterData, "#search_by_researchbroker ul input")) {
					researchBrokerFilterData = [];
				} 
			} else {
				$("#search_by_researchbroker ul input").eq(0).prop('checked', false);
			}

			
			localEquitySearchJson.researchedBy = researchBrokerFilterData;

			if(researchBrokerFilterData.length === 0) {
				delete localEquitySearchJson.researchedBy;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/sector-research.jsp');
			$("#search_by_researchbroker ul input").prop('checked', false);
			// $("#search_by_researchbroker ul input").eq(0).prop('checked', true);
		}
	};

	/**
     * Function to set ResearchDate from filter data API.
     */
	var setResearchDateFilterData = function() {
		getFilterData('researchDate').then(function(response) {
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

			var len = response.data.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.data[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response.data[i] + "' data-section='' data-value='" + response.data[i] + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_researchDate ul").html(html);
			$("#search_by_researchDate ul input").on('change', getResearchDateFilterData);
			// $("#search_by_researchDate ul input").eq(0).prop('checked', true);
		}, function(error) {
			//console.log(error);
		});
	};

	var researchDateFilterData = [];

    /**
     * Function to get ResearchDate from localstorage and get equity list
     */
	var getResearchDateFilterData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Equity Research', 'Filter by ResearchDate onClick', 'Filter by ResearchDate');

			addRemoveItemFromArray(researchDateFilterData, $(this).attr('data-value'));


			if($(this).attr('data-value') == 'all') {
				researchDateFilterData = ['all'];
				if(checkForAllData(researchDateFilterData, "#search_by_researchDate ul input")) {
					researchDateFilterData = [];
				} 
			} else {
				$("#search_by_researchDate ul input").eq(0).prop('checked', false);
			}

			
			localEquitySearchJson.researchDate = researchDateFilterData;

			if(researchDateFilterData.length === 0) {
				delete localEquitySearchJson.researchDate;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/sector-research.jsp');
			$("#search_by_researchDate ul input").prop('checked', false);
			// $("#search_by_researchDate ul input").eq(0).prop('checked', true);
		}
	};

    /**
     * Function to set recommendation Type from filter data API.
     */
	var setReportToneFilterData = function() {
		getFilterData('reportTone').then(function(response) {
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

			var len = response.data.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.data[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response.data[i] + "' data-section='' data-value='" + response.data[i] + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_recommType ul").html(html);
			$("#search_by_recommType ul input").on('change', getReportToneFilterData);
			// $("#search_by_recommType ul input").eq(0).prop('checked', true);
		}, function(error) {
			//console.log(error);
		});
	};

	var recommTypeData = [];

    /**
     * Function to get report Tone from localstorage and get equity list
     */
	var getReportToneFilterData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Sector Research', 'Filter by ReportTone onClick', 'Filter by ReportTone');

			addRemoveItemFromArray(recommTypeData, $(this).attr('data-value'));


			if($(this).attr('data-value') == 'all') {
				recommTypeData = ['all'];
				if(checkForAllData(recommTypeData, "#search_by_recommType ul input")) {
					recommTypeData = [];
				} 
			} else {
				$("#search_by_recommType ul input").eq(0).prop('checked', false);
			}

			
			localEquitySearchJson.reportTone = recommTypeData;

			if(recommTypeData.length === 0) {
				delete localEquitySearchJson.reportTone;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/sector-research.jsp');
			$("#search_by_recommType ul input").prop('checked', false);
			// $("#search_by_recommType ul input").eq(0).prop('checked', true);
		}
	};


    /**
     * Function to set upside from filter data API.
     */
	var setReportFrequencyFilterData = function() {
		getFilterData('reportFrequency').then(function(response) {
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

			var len = response.data.length;
			for(var i = 0; i < len; i++) {
				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.data[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response.data[i] + "' data-section='' data-value='" + response.data[i] + "' />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_upside ul").html(html);
			$("#search_by_upside ul input").on('change', getReportFrequencyFilterData);
			// $("#search_by_upside ul input").eq(0).prop('checked', true);
		}, function(error) {
			//console.log(error);
		});
	};

	var upsideFilterData = [];

    /**
     * Function to get report Frequency from localstorage and get equity list
     */
	var getReportFrequencyFilterData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Sector Research', 'Filter by ReportFrequency onClick', 'Filter by ReportFrequency');

			addRemoveItemFromArray(upsideFilterData, $(this).attr('data-value'));


			if($(this).attr('data-value') == 'all') {
				upsideFilterData = ['all'];
				if(checkForAllData(upsideFilterData, "#search_by_upside ul input")) {
					upsideFilterData = [];
				} 
			} else {
				$("#search_by_upside ul input").eq(0).prop('checked', false);
			}

			
			localEquitySearchJson.reportFrequency = upsideFilterData;

			if(upsideFilterData.length === 0) {
				delete localEquitySearchJson.reportFrequency;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);
		} else {
			inner_login('view/sector-research.jsp');
			$("#search_by_upside ul input").prop('checked', false);
			// $("#search_by_upside ul input").eq(0).prop('checked', true);
		}
	};


    /**
     * Function to set Others from filter data API.
     */
	var setOthersFilterData = function() {
		getFilterData('others').then(function(response) {
			response = JSON.parse(response);
			//console.log(response);
			var html = "";
			var disabled = "disabled";

			var len = response.data.length;
			for(var i = 0; i < len; i++) {
				if(response.data[i] == "Research Reports by CFA") {
					disabled = "";
				} else {
					disabled = "disabled";
				}

				html = html + "<li>"
								+ "<div class='row'>"
									+ "<div class='col-xs-9'>"
									+ "<span>" + response.data[i] + "</span>"
									+ "</div>"
									+ "<div class='col-xs-3'>"
										+ "<input type='checkbox' data-name='" + response.data[i] + "' data-section='' data-value='" + response.data[i] + "' " + disabled + " />"
										+ "<label for='geo-india'></label>"
									+ "</div>"
								+ "</div>"
							+ "</li>"
			}
			$("#search_by_others ul").html(html);
			$("#search_by_others ul input").on('change', getOthersFilterData);
			// $("#search_by_others ul input").eq(0).prop('checked', true);
		}, function(error) {
			//console.log(error);
		});
	};

	var othersFilterData = [];

    /**
     * Function to get Others from localstorage and get equity list
     */
	var getOthersFilterData = function() {
		if(!isLoggedInUser()) {

			sendGAevents('Sector Research', 'Filter by Others onClick', 'Filter by Others');

			addRemoveItemFromArray(othersFilterData, $(this).attr('data-value'));


			if($(this).attr('data-value') == 'all') {
				othersFilterData = ['all'];
				if(checkForAllData(othersFilterData, "#search_by_others ul input")) {
					othersFilterData = [];
				} 
			} else {
				$("#search_by_others ul input").eq(0).prop('checked', false);
			}

			
			localEquitySearchJson.others = othersFilterData;

			if(othersFilterData.length === 0) {
				delete localEquitySearchJson.others;
			}
			
			resetPaginationCount();
			window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));
			loadDefaultEquityList(JSON.parse(window.localStorage.getItem("equitysearchjson")), perPageMaxRecords);

		} else {
			inner_login('view/sector-research.jsp');
			$("#search_by_others ul input").prop('checked', false);
			// $("#search_by_others ul input").eq(0).prop('checked', true);
		}
	};


    /**
     * Function to start async call to filter data API and set response.
     */
	var loadDefaultFilterData = function() {
		setGeoData();
		setSectorTypeData();
		setSectorSubTypeData();
		setAnalystTypeFilterData();
		setResearchBrokerData();
		setResearchDateFilterData();
		setReportToneFilterData()
		setReportFrequencyFilterData();
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