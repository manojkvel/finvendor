jQuery(document).ready(function() {
    var API_TIMEOUT_SMALL = 30*1000;
    var API_TIMEOUT_LARGE = 3*60*1000;

	function loadDefaultEquityList() {
		getResearchReportForEquity().then(function(serverResponse) {
			//console.log(serverResponse);
			var response = JSON.parse(serverResponse);
			var htmlCode = '';
			var rowHtml = 	"";
			for(var i = 0; i < response.equity.length; i++) {
				htmlCode = htmlCode + "<tr>" +
						"<td>" + 
							"<div class='company'>" + response.equity[i].company + "</div>" + 
							"<div class='style'>" + response.equity[i].style + "</div>" + 
							"<div class='mcap'>" + response.equity[i].mcap + "</div>" + 
							"<div class='sector'>" + response.equity[i].sector + "</div>" +
						"</td>" + 
						"<td>" + 
							"<div class='broker'>" + response.equity[i].broker + "</div>" + 
							"<div class='since'>" + response.equity[i].since + "</div>" + 
							"<div class='awarded'>" + response.equity[i].awarded + "</div>" + 
							"<div class='researchedByCfa'>" + response.equity[i].researchedByCfa + "</div>" +
						"</td>" +
						"<td>" + 
							"<div class='brokerRank'>" + response.equity[i].brokerRank + "</div>" + 
							"<div class='brokerRankLargeCap'>" + response.equity[i].brokerRankLargeCap + "</div>" + 
							"<div class='brokerRankMidCap'>" + response.equity[i].brokerRankMidCap + "</div>" + 
							"<div class='brokerRankSmallCap'>" + response.equity[i].brokerRankSmallCap + "</div>" +
						"</td>" +
						"<td>" + 
							"<div class='cmp'>" + response.equity[i].cmp + "</div>" + 
							"<div class='priceDate'>" + response.equity[i].priceDate + "</div>" + 
							"<div class='pe'>" + response.equity[i].pe + "</div>" + 
							"<div class='_3YrPatGrowth'>" + response.equity[i]._3YrPatGrowth + "</div>" +
						"</td>" +
						"<td>" + 
							"<div class='recommType'>" + response.equity[i].recommType + "</div>" + 
							"<div class='targetPrice'>" + response.equity[i].targetPrice + "</div>" + 
							"<div class='priceAtRecomm'>" + response.equity[i].priceAtRecomm + "</div>" + 
							"<div class='upside'>" + response.equity[i].upside + "</div>" +
						"</td>" +
						"<td>"  +  
							"<div class='analystName'>" + response.equity[i].analystName + "</div>" + 
							"<div class='researchDate'>" + response.equity[i].researchDate + "</div>" +
							"<div class='report'><a href='" + response.equity[i].report + "' target='' data-toggle='tooltip' title='Go to report post'><i class='fa fa-file'></i></a></div>" +
						"</td>" +
						"</tr>";
			}

			$("#broker_table tbody").html(htmlCode);
		}, function(error) {
			console.log(error);
		});
	};

	loadDefaultEquityList();

	function getResearchReportForEquity() {
		var url = "/system/api/researchReports?type=equity";
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
});