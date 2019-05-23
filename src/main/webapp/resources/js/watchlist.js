var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;
var marketListArray = [];
var companyProfileObj = {};

function getCompanyWatchListApi() {

	var url = "/system/api/companywatchlist/all";
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
					console.log(httpRequest.status + httpRequest.responseText);
					reject(httpRequest.responseText);
				}
			} else {
			}
		};

		httpRequest.send();
	});
};

function getCompanyWatchList() {

	getCompanyWatchListApi().then(function(response) {
		

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

	        var priceWhenAdded = parseFloat(response[i].cmp).toFixed(2);
	        var cmp = parseFloat(response[i].newCmp).toFixed(2);
	        var percentageChangeSinceAdded = parseFloat(response[i].percentageChangeSinceAdded).toFixed(2);
	        var todaysChange = parseFloat(response[i].todaysChange).toFixed(2);
	        var todaysChangeInPercentage = parseFloat(response[i].todaysChangeInPercentage).toFixed(2);


			var percentageChangeSinceAdded_class = "success";
	        var percentageChangeSinceAdded_caret = "fa-caret-up";
	        if(percentageChangeSinceAdded > 0) {
	            percentageChangeSinceAdded_class = "success";
	            percentageChangeSinceAdded_caret = "fa-caret-up";
	        } else if (percentageChangeSinceAdded < 0) {
	            percentageChangeSinceAdded_class = "danger";
	            percentageChangeSinceAdded_caret = "fa-caret-down";
	        } else {
	            percentageChangeSinceAdded_class = "neutral";
	            percentageChangeSinceAdded_caret = "";
	        }

	        var todaysChange_class = "success";
	        var todaysChange_caret = "fa-caret-up";
	        if(todaysChange > 0) {
	            todaysChange_class = "success";
	            todaysChange_caret = "fa-caret-up";
	        } else if (todaysChange < 0) {
	            todaysChange_class = "danger";
	            todaysChange_caret = "fa-caret-down";
	        } else {
	            todaysChange_class = "neutral";
	            todaysChange_caret = "";
	        }

			
			var todaysChangeInPercentage_class = "success";
	        var todaysChangeInPercentage_caret = "fa-caret-up";
	        if(todaysChangeInPercentage > 0) {
	            todaysChangeInPercentage_class = "success";
	            todaysChangeInPercentage_caret = "fa-caret-up";
	        } else if (todaysChangeInPercentage < 0) {
	            todaysChangeInPercentage_class = "danger";
	            todaysChangeInPercentage_caret = "fa-caret-down";
	        } else {
	            todaysChangeInPercentage_class = "neutral";
	            todaysChangeInPercentage_caret = "";
	        }

            

            htmlCode = htmlCode + "<tr data-id='" + response[i].companyId + "'>" +
            "<td>" + 
            "<div class='company_name' data-toggle='tooltip' title='See all reports for " + response[i].companyName + "'>" + response[i].companyName + "</div>" +
            "</td>" + 
            "<td>" + 
            "<div class='price_when_added'>" + priceWhenAdded + "</div>" + 
            "</td>" + 
            "<td>" + 
            "<div class='cmp'>" + cmp + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='percentageChangeSinceAdded " + percentageChangeSinceAdded_class + "'>" + "<i class='fa " + percentageChangeSinceAdded_caret + "'></i>" + percentageChangeSinceAdded + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='todaysChange " + todaysChange_class + "'>" + "<i class='fa " + todaysChange_caret + "'></i>" + todaysChange + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='todaysChangeInPercentage " + todaysChangeInPercentage_class + "'>" + "<i class='fa " + todaysChangeInPercentage_caret + "'></i>"  + todaysChangeInPercentage + "</div>" + 
            "</td>" +
            "<td>" + 
            	"<input name='watchlist" + i + "' class='submit-button' type='checkbox' />" + 
            "</td>" +
            "</tr>";
        }

        $("#watchlist_table tbody").html(htmlCode);

        $("#my_watchlist .deleteBtn").attr("disabled", "disabled");

        $("#my_watchlist #watchlist_table tbody input[type=checkbox]").on('click', addToWatchList);

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

	function addToWatchList() { 
		var rowId = $(this).parents('tr');
		var id = rowId.attr('data-id');

		if(!$(this).prop('checked')) {
			$('#my_watchlist #watchlist_table input[name=selectAll]').prop('checked', false);
		}
		addRemoveItemFromArray(marketListArray, id);	
		if(marketListArray.length != 0) {
			$("#my_watchlist .deleteBtn").removeAttr("disabled");
		} else {
			$("#my_watchlist .deleteBtn").attr("disabled", "disabled");
		}	
		console.log(marketListArray);
	}

	function selectAllWatchList() {
		var inputList = $("#my_watchlist #watchlist_table tbody input");
		var inputSelectAll = $('#my_watchlist #watchlist_table input[name=selectAll]');

		$(inputSelectAll).prop('checked', !$(inputSelectAll).prop('checked'));

		if($('#my_watchlist #watchlist_table input[name=selectAll]').prop('checked')) {
			$('#my_watchlist #watchlist_table input[name=selectAll]').prop('checked', false);
			$(inputList).prop('checked', false);
			for(key in inputList) {
				if (!isNaN(key)) {
					addRemoveItemFromArray(marketListArray, $(inputList[key]).parents('tr').attr('data-id'));
				}
			}
		} else {
			$('#my_watchlist #watchlist_table input[name=selectAll]').prop('checked', true);
			$(inputList).prop('checked', true);
			marketListArray = [];
			for(key in inputList) {
				if (!isNaN(key)) {
					addRemoveItemFromArray(marketListArray, $(inputList[key]).parents('tr').attr('data-id'));
				}
			}
		}

		if(marketListArray.length != 0) {
			$("#my_watchlist .deleteBtn").removeAttr("disabled");
		} else {
			$("#my_watchlist .deleteBtn").attr("disabled", "disabled");
		}
		console.log(marketListArray);
	}

	function deleteWatchList() {

		var jsonObj = [];
		for(var i=0; i < marketListArray.length; i++) {
			jsonObj.push({
				companyId : marketListArray[i]
			});
		}

		console.log(jsonObj);


		deleteWatchListApi(jsonObj).then(function(response) {
			marketListArray = [];
			getCompanyWatchList();
			$('#deleteWatchlist').modal('hide');
		}, function(error) {

		});
	}

	function deleteWatchListApi(jsonObj) {

		var url = "/system/api/companywatchlist/delete";
		
		return new Promise(function(resolve, reject) {
			var httpRequest = new XMLHttpRequest({
				mozSystem: true
			});
			//httpRequest.timeout = API_TIMEOUT_SMALL;
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


	function searchCompanyToAddWatchList(e) {

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
				return data.searchOutput;
			},
			formatItem: function (data, $item) {
				return data.companyName + " (" + data.ticker + ")";
			},
			onSelect: function (data, $item) {
				if(data.companyName != undefined) {
					var companyProfileJson = {
						companyId : data.companyId,
						companyName : data.companyName,
						cmp : data.cmp,
					}
					window.localStorage.setItem('companyProfileJson', JSON.stringify(companyProfileJson));
					this.val(data.companyName);
				}
			},
			required: true
		});
	};

	$("#watchlist_search #watchListSearchBox").on("keydown", searchCompanyToAddWatchList);

	$("#watchlist_search form").submit(function(e){
		e.preventDefault(e);
	});

function addToMarketWatchlist() {

	var searchVal = $("#watchlist_search #watchListSearchBox").val();
	if(searchVal == '') {
		return false;
	}

    addToMarketWatchlistAPI().then(function(response) {
        isProgressLoader(false);
        $("#addToWatchlist .alert").removeClass('alert-danger');
        $("#addToWatchlist .alert span").text(JSON.parse(response).message);
        getCompanyWatchList();
    }, function(error) {
        isProgressLoader(false);
        $("#addToWatchlist .alert").addClass('alert-danger');
        $("#addToWatchlist .alert span").text(JSON.parse(error).message
	);

    });

    $("#watchlist_search #watchListSearchBox").val('');
    window.localStorage.removeItem('companyProfileJson');
}

	function addToMarketWatchlistAPI() {
    
	    isProgressLoader(true);

	    var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

        companyProfileObj['companyId'] = companyProfileJson.companyId;
        companyProfileObj['companyName'] = companyProfileJson.companyName;
        companyProfileObj['cmp'] = companyProfileJson.cmp;

	    var url = "/system/api/companywatchlist/create";
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
	                if (httpRequest.status === 201) {
	                    resolve(httpRequest.response);
	                } else {
	                    console.log(httpRequest.status + httpRequest.responseText);
	                    reject(httpRequest.responseText);
	                }
	            } else {
	            }
	        };

	        httpRequest.send(JSON.stringify(companyProfileObj));
	    });
	}
	$("#watchlist_search input[name=watchListSearchBoxSubmit]").on("click", addToMarketWatchlist);



jQuery(document).ready(function() {

	getCompanyWatchList();

    $("#deleteWatchlist .deleteBtn").on('click', deleteWatchList);
    $("#my_watchlist #watchlist_table input[name=selectAll]").on('click', selectAllWatchList);


});