var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;
var priceAlertListArray = [];
var companyProfileObj = {};

function getCompanyPriceAlertsApi() {

	var url = "/system/api/companypricealert/all";
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

function getCompanyPriceAlerts() {

	getCompanyPriceAlertsApi().then(function(response) {
		

		var response = JSON.parse(response);
		var len = response.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("tbody").html("<tr><td colspan='7'>No Matching Records Found</td></tr>");
        	$("#my_watchlist .deleteBtn").attr("disabled", "disabled");
			$('#my_watchlist #watchlist_table input[name=selectAll]').prop('checked', false);
            return;
        }

        for(var i = 0; i < len; i++) {


        var dayMinPrice = (response[i].dayMinPrice == '') ? 'NA' : "-" + response[i].dayMinPrice + "%";
        var dayMaxPrice = (response[i].dayMaxPrice == '') ? 'NA' : "+" + response[i].dayMaxPrice + "%";


        var weekMinPrice = (response[i].weekMinPrice == '') ? 'NA' : "-" + response[i].weekMinPrice + "%";
        var weekMaxPrice = (response[i].weekMaxPrice == '') ? 'NA' : "+" + response[i].weekMaxPrice + "%";

        var monthMinPrice = (response[i].monthMinPrice == '') ? 'NA' : "-" + response[i].monthMinPrice + "%";
        var monthMaxPrice = (response[i].monthMaxPrice == '') ? 'NA' : "+" + response[i].monthMaxPrice + "%";

        var noTimeFrameMinPrice = (response[i].noTimeFrameMinPrice == '') ? 'NA' : "-" + response[i].noTimeFrameMinPrice + "%";
        var noTimeFrameMaxPrice = (response[i].noTimeFrameMaxPrice == '') ? 'NA' : "+" + response[i].noTimeFrameMaxPrice + "%";



        var isResearchReport = (response[i].isResearchReport == false) ? 'fa-times' : 'fa-check';


	        
            htmlCode = htmlCode + "<tr data-id='" + response[i].companyId + "'>" +
            "<td>" + 
            "<div class='company_name' data-toggle='tooltip' title='See all reports for " + response[i].companyName + "'>" + response[i].companyName + "</div>" +
            "</td>" + 
            "<td>" + 
            "<div class='dayPrice'>" + dayMinPrice + "<br />" + dayMaxPrice + "</div>" + 
            "</td>" + 
            "<td>" + 
            "<div class='weekPrice'>"  + weekMinPrice + "<br />" + weekMaxPrice  + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='monthPrice'>" + monthMinPrice + "<br />" + monthMaxPrice + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='noTimeFramePrice'>" + noTimeFrameMinPrice + "<br />" + noTimeFrameMaxPrice + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='isResearchReport'><span class='fa " + isResearchReport + "'></span></div>" + 
            "</td>" +
            "<td>" + 
            	"<input name='priceAlert" + i + "' class='submit-button' type='checkbox' />" + 
            "</td>" +
            "</tr>";
        }

        $("#pricealert_table tbody").html(htmlCode);

        $("#my_pricealert .deleteBtn").attr("disabled", "disabled");

        $("#my_pricealert #pricealert_table tbody input[type=checkbox]").on('click', addToPriceAlertList);

	}, function(error) {
		var error = JSON.parse(error);
		$("tbody").html("<tr><td colspan='7'>" + error.userMessage + "</td></tr>");
	});
}



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

	function addToPriceAlertList() { 
		var rowId = $(this).parents('tr');
		var id = rowId.attr('data-id');

		if(!$(this).prop('checked')) {
			$('#my_pricealert #pricealert_table input[name=selectAll]').prop('checked', false);
		}
		addRemoveItemFromArray(priceAlertListArray, id);	
		if(priceAlertListArray.length != 0) {
			$("#my_pricealert .deleteBtn").removeAttr("disabled");
		} else {
			$("#my_pricealert .deleteBtn").attr("disabled", "disabled");
		}

		if(priceAlertListArray.length == 1) {
			$("#my_pricealert .editBtn").removeAttr("disabled");
		} else {
			$("#my_pricealert .editBtn").attr("disabled", "disabled");
		}

		console.log(priceAlertListArray);
	}

	function selectAllWatchList() {
		var inputList = $("#my_pricealert #pricealert_table tbody input");
		var inputSelectAll = $('#my_pricealert #pricealert_table input[name=selectAll]');

		$(inputSelectAll).prop('checked', !$(inputSelectAll).prop('checked'));

		if($('#my_pricealert #pricealert_table input[name=selectAll]').prop('checked')) {
			$('#my_pricealert #pricealert_table input[name=selectAll]').prop('checked', false);
			$(inputList).prop('checked', false);
			for(key in inputList) {
				if (!isNaN(key)) {
					addRemoveItemFromArray(priceAlertListArray, $(inputList[key]).parents('tr').attr('data-id'));
				}
			}
		} else {
			$('#my_pricealert #pricealert_table input[name=selectAll]').prop('checked', true);
			$(inputList).prop('checked', true);
			priceAlertListArray = [];
			for(key in inputList) {
				if (!isNaN(key)) {
					addRemoveItemFromArray(priceAlertListArray, $(inputList[key]).parents('tr').attr('data-id'));
				}
			}
		}

		if(priceAlertListArray.length != 0) {
			$("#my_pricealert .deleteBtn").removeAttr("disabled");
		} else {
			$("#my_pricealert .deleteBtn").attr("disabled", "disabled");
		}
		console.log(priceAlertListArray);
	}

	function deletePriceAlert() {

		var jsonObj = [];
		for(var i=0; i < priceAlertListArray.length; i++) {
			jsonObj.push({
				companyId : priceAlertListArray[i]
			});
		}

		console.log(jsonObj);


		deletePriceAlertApi(jsonObj).then(function(response) {
			priceAlertListArray = [];
			getCompanyPriceAlerts();
			$('#deletePriceAlert').modal('hide');
		}, function(error) {

		});
	}

	function deletePriceAlertApi(jsonObj) {

		var url = "/system/api/companypricealert/delete";
		
		return new Promise(function(resolve, reject) {
			var httpRequest = new XMLHttpRequest({
				mozSystem: true
			});
			httpRequest.timeout = API_TIMEOUT_SMALL;
			httpRequest.open('DELETE', url, true);
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

			httpRequest.send(JSON.stringify(jsonObj));
		});
	}

	function setPriceAlert() {
	    var dayMinPrice = $("#setPriceAlert input[name=day_min_price]").val();
	    var dayMaxPrice = $("#setPriceAlert input[name=day_max_price]").val();
	    var weekMinPrice = $("#setPriceAlert input[name=week_min_price]").val();
	    var weekMaxPrice = $("#setPriceAlert input[name=week_max_price]").val();
	    var monthMinPrice = $("#setPriceAlert input[name=month_min_price]").val();
	    var monthMaxPrice = $("#setPriceAlert input[name=month_max_price]").val();
	    var noTimeFrameMinPrice = $("#setPriceAlert input[name=noTimeFrame_min_price]").val();
	    var noTimeFrameMaxPrice = $("#setPriceAlert input[name=noTimeFrame_max_price]").val();
	    var isResearchReport = $("#setPriceAlert input[name=alert_new_research_report]").prop('checked');
	    var companyId = priceAlertListArray[0];
	    var alertJsonObj = {
	        "companyId": companyId,
	        "cmpWhenPriceAlertSet": "278",
	        "dayMinPrice": dayMinPrice,
	        "dayMaxPrice": dayMaxPrice,
	        "weekMinPrice": weekMinPrice,
	        "weekMaxPrice": weekMaxPrice,
	        "monthMinPrice": monthMinPrice,
	        "monthMaxPrice": monthMaxPrice,
	        "noTimeFrameMinPrice": noTimeFrameMinPrice,
	        "noTimeFrameMaxPrice": noTimeFrameMaxPrice,
	        "isResearchReport": isResearchReport
	    };

	    setPriceAlertAPI(alertJsonObj).then(function(response){
	        var response = JSON.parse(response);
	        $("#setPriceAlert h3").hide();
	        $("#setPriceAlert .alert").text(response.message);
	        $("#setPriceAlert .alert").removeClass("alert-danger").show();
	        isProgressLoader(false);
	    }, function(error) {
	        var response = JSON.parse(error);
	        $("#setPriceAlert h3").hide();
	        $("#setPriceAlert .alert").text(response.message);
	        $("#setPriceAlert .alert").addClass("alert-danger").show();
	        isProgressLoader(false);
	    });
	}


	function setPriceAlertAPI(alertJsonObj) {
	    
	    isProgressLoader(true);

	    var url = "/system/api/companypricealert/update";
	    var method = 'PUT';
	    
	    return new Promise(function(resolve, reject) {
	        var httpRequest = new XMLHttpRequest({
	            mozSystem: true
	        });
	        httpRequest.timeout = API_TIMEOUT_SMALL;
	        httpRequest.open(method, url, true);
	        httpRequest.setRequestHeader('Content-Type',
	                'application/json; charset=UTF-8');

	        httpRequest.ontimeout = function () {
	            reject("" + httpRequest.responseText);
	        };
	        httpRequest.onreadystatechange = function () {
	            if (httpRequest.readyState === XMLHttpRequest.DONE) {
	                if (httpRequest.status === 200 || httpRequest.status === 201) {
	                    resolve(httpRequest.response);
	                } else {
	                    console.log(httpRequest.status + httpRequest.responseText);
	                    reject(httpRequest.responseText);
	                }
	            } else {
	            }
	        };

	        httpRequest.send(JSON.stringify(alertJsonObj));
	    });
	} 

	$("#setPriceAlert button[name=set_alert_btn]").on('click', setPriceAlert);


	$("#my_pricealert .pricealert_header button.editBtn").on('click', openPriceAlertForm);

	function openPriceAlertForm() {
		$("#setPriceAlert .modal-header h3").show();
		$("#setPriceAlert .modal-header .alert").hide();
		var id = priceAlertListArray[0];
		getPriceAlertAPI(id).then(function(response) {
			isProgressLoader(false);

			var response = JSON.parse(response);

			$("#setPriceAlert input[name=day_min_price]").val(response.dayMinPrice);
			$("#setPriceAlert input[name=day_max_price]").val(response.dayMaxPrice);
			$("#setPriceAlert input[name=week_min_price]").val(response.weekMinPrice);
			$("#setPriceAlert input[name=week_max_price]").val(response.weekMaxPrice);
			$("#setPriceAlert input[name=month_min_price]").val(response.monthMinPrice);
			$("#setPriceAlert input[name=month_max_price]").val(response.monthMaxPrice);
			$("#setPriceAlert input[name=noTimeFrame_min_price]").val(response.noTimeFrameMinPrice);
			$("#setPriceAlert input[name=noTimeFrame_max_price]").val(response.noTimeFrameMaxPrice);

			(response.isResearchReport) ? $("#setPriceAlert input[name=alert_new_research_report]").prop('checked', true) : $("#setPriceAlert input[name=alert_new_research_report]").prop('checked', false);
		}, function(error) {
			isProgressLoader(false);
		});
	}

	function getPriceAlertAPI(companyId) {

		isProgressLoader(true);

		var url = "/system/api/companypricealert?companyId=" + companyId;
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
					if (httpRequest.status === 201) {
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
	}




jQuery(document).ready(function() {

	getCompanyPriceAlerts();

    $("#deletePriceAlert .deleteBtn").on('click', deletePriceAlert);
    $("#my_pricealert #pricealert_table input[name=selectAll]").on('click', selectAllWatchList);


});