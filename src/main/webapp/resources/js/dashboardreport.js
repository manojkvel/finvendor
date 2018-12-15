var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;

    var timeStampToDate = function (ts) {
        if (ts) {
            ts = new Date(ts).toString();
            ts = ts.split(' ').slice(0, 5);
            ts = /* ts[0] + " " + */ ts[1] + " " + ts[2] + ", " + ts[3]; // + " "
																		// +
																		// ts[4];
            // console.log(ts);
            return ts;
        } else {
            return 'NA';
        }
    };

$(".back_btn").on('click', function(){
    window.history.back();
});

function getDashboardResearchReportLoad() {

    getDashboardResearchReport().then(function(data) {
        response = JSON.parse(data);

        if(response.equity != null) {

            var cmp = parseFloat(response.equity.cmp).toFixed(2);
            var targetPrice = parseFloat(response.equity.targetPrice).toFixed(2);
            var upside = (response.equity.upside != 'NA') ? parseFloat(Math.round(response.equity.upside * 100) / 100).toFixed(2) + '%' : response.equity.upside;
         
            var htmlData = "<div class='dashboard_report'>"
                        + "<div class='hd'>"
                                + "<span class='reportName'>" + response.equity.reportName + "</span>"
                                + "<span class='currentMarketPrice'>CMP: " + cmp + "</span>"
                                + "<div>"
                                    + "<span data-toggle='tooltip' data-placement='top' title='' data-original-title='" + timeStampToDate(Number(response.equity.priceDate)) + "'>" + timeStampToDate(Number(response.equity.priceDate)) + "</span>"
                                + "</div>"
                        + "</div>"
                        + "<div class='bd'>"
                                + "<div class='reportContainer'>"
                                    + "<div class='reportDetails'>"
                                        + "<div class='reportTitle'>"
                                            + "<span class='recommType success'>" + response.equity.recommType + ":</span> " + response.equity.company
                                            + "<div class='brokerName'>by " + response.equity.analystName + "</div>"
                                        + "</div>"
                                        + "<div class='targetPrice'>Target: " + targetPrice + "</div>"
                                        + "<div class='upside'>Upside: " + upside + "</div>"

                                        + "<div class='dwnldReport'>"
                                            + "<a target='blank' href='/system/api/downloadResearchReports?productId=" + response.equity.productId + "&reportName=" + response.equity.report + "'>Download Full Report</a>"
                                        + "</div>"
                                    + "</div>"
                                    + "<span class='brokerName'>" + response.equity.broker + "</span>"
                                    + "<div class='reportDesc'>"
                                        + "<p>"
                                            + response.equity.reportDesc
                                        + "</p>"
                                    + "</div>"
                                + "</div>"
                        + "</div>"
                    + "</div>";
            
            $('#dashboard_report').html(htmlData);
            $('[data-toggle="tooltip"]').tooltip();
            $('.dwnldReport a').on('click', function() {
                sendGAevents('DownloadPdfReport', 'Download Full Report Click', 'Download Full Report');
            });
        } else {
             $('#dashboard_report').html("<div class='dashboard_report'>We are unable to download Report, please try again later</div>");
        }
    }, function(error) {
             $('#dashboard_report').html("<div class='dashboard_report'>We are unable to download Report, please try again later</div>");
    });
}

function getDashboardResearchReport() {

    var dasboardReportJson = window.localStorage.getItem("dasboardReportJson");
    dasboardReportJson = JSON.parse(dasboardReportJson);
    var id = dasboardReportJson.productId;
    var vendorName = dasboardReportJson.vendorName;
    var jsonData = dasboardReportJson.equitysearchjson;
    var pageNumber = dasboardReportJson.pageNumber;
    var perPageMaxRecords = dasboardReportJson.perPageMaxRecords;
    var sortByValue = dasboardReportJson.sortByValue;
    var orderBy = dasboardReportJson.orderBy;

    var url = "";
    if(dasboardReportJson.type == "sector") {
        url = "/system/api/sectorreports/dashboard?pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortByValue + "&orderBy=" + orderBy + "&productId="+ id;
    } else {
        url = "/system/api/dashboardResearchReports?type=equity" + "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortByValue + "&orderBy=" + orderBy + "&productId="+ id;
    }
    // var url = "/system/api/dashboardResearchReports?type=equity" + "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortByValue + "&orderBy=" + orderBy + "&productId="+ id;
    
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

        httpRequest.send(JSON.stringify(JSON.parse(jsonData)));
    });
};