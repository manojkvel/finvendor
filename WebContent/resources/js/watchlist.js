var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;

function getCompanyWatchList() {

	var url = "/system/api/companywatchlist/all";
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




jQuery(document).ready(function() {
	getCompanyWatchList().then(function(response) {
		

		var response = JSON.parse(response);
		var len = response.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("tbody").html("<tr><td colspan='6'>No Matching Records Found</td></tr>");
            return;
        }

        var cmp_last_change_class = "success";
        var cmp_last_change_caret = "fa-caret-up";

        for(var i = 0; i < len; i++) {

	        var lastChange = response[i].diffCmp;

	        if(lastChange > 0) {
	            cmp_last_change_class = "success";
	            cmp_last_change_caret = "fa-caret-up";
	        } else if (lastChange < 0) {
	            cmp_last_change_class = "danger";
	            cmp_last_change_caret = "fa-caret-down";
	        } else {
	            cmp_last_change_class = "neutral";
	            cmp_last_change_caret = "";
	        }

            

            htmlCode = htmlCode + "<tr data-id='" + response[i].companyId + "'>" +
            "<td>" + 
            "<div class='company_name' data-toggle='tooltip' title='See all reports for " + response[i].companyName + "'>" + response[i].companyName + "</div>" +
            "</td>" + 
            "<td>" + 
            "<div class='price_when_added'>" + response[i].cmp + "</div>" + 
            "</td>" + 
            "<td>" + 
            "<div class='cmp'>" + response[i].cmp + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='diffCmp'>" + response[i].diffCmp + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='diffCmp'>" + (response[i].newCmp - response[i].cmp) + " (<i class='fa " + cmp_last_change_caret + "'></i> "  + lastChange + "%)" + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='diffCmp'>" + response[i].diffCmp + "</div>" + 
            "</td>" +
            "</tr>";
        }

        $("#watchlist_table tbody").html(htmlCode);

	}, function(error) {
		var error = JSON.parse(error);
		$("tbody").html("<tr><td colspan='6'>" + error.userMessage + "</td></tr>");
	});
});