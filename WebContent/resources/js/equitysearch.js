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

				var borkerRankStarClass = "<i class='fa fa-star'></i>";
				var borkerRankStarHtml = '';
				if(response.equity[i].brokerRank === 5) {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass;
				} else if(response.equity[i].brokerRank === 4) {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass;
				} else if(response.equity[i].brokerRank === 3) {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass + borkerRankStarClass;
				} else if(response.equity[i].brokerRank === 2) {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass;
				} else if(response.equity[i].brokerRank === 1) {
					borkerRankStarHtml = borkerRankStarClass;
				} else {
					borkerRankStarHtml = borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass + borkerRankStarClass
				}

				htmlCode = htmlCode + "<tr>" +
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
							"<div class='brokerRank warning '>" + borkerRankStarHtml + "</div>" + 
							"<div class='brokerRankLargeCap'>" + response.equity[i].brokerRankLargeCap + "</div>" + 
							"<div class='brokerRankMidCap'>" + response.equity[i].brokerRankMidCap + "</div>" + 
							"<div class='brokerRankSmallCap'>" + response.equity[i].brokerRankSmallCap + "</div>" +
						"</td>" +
						"<td>" + 
							"<div class='cmp'> Rs. " + response.equity[i].cmp + "</div>" + 
							"<div class='priceDate'>" + response.equity[i].priceDate + "</div>" + 
							"<div class='pe'>" + response.equity[i].pe + "</div>" + 
							"<div class='_3YrPatGrowth " + _3YrPatGrowthClass + "'><i class='fa " + _3YrPatGrowthClass_Caret + "'></i> " + response.equity[i]._3YrPatGrowth + "%</div>" +
						"</td>" +
						"<td>" + 
							"<div class='recommType " + recommTypeClass + "'>" + response.equity[i].recommType + "</div>" + 
							"<div class='targetPrice'> Rs. " + response.equity[i].targetPrice + "</div>" + 
							"<div class='priceAtRecomm'>" + response.equity[i].priceAtRecomm + "</div>" + 
							"<div class='upside " + upsideClass + "'>" + response.equity[i].upside + "</div>" +
						"</td>" +
						"<td>"  +  
							"<div class='analystName'>" + response.equity[i].analystName + "</div>" + 
							"<div class='researchDate'>" + response.equity[i].researchDate + "</div>" +
							"<div class='report' target=''><a href='research-company-report.jsp?id=" + response.equity[i].companyId + "' data-toggle='tooltip' title='Go to report post'><i class='fa fa-file'></i></a></div>" +
						"</td>" +
						"</tr>";
			}

			$("#broker_table tbody").html(htmlCode);

    		$('[data-toggle="tooltip"]').tooltip();
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