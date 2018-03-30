var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;

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


function getDashboardResearchReportLoad() {

    getDashboardResearchReport().then(function(data) {
        response = JSON.parse(data);
         
        var htmlData = "<div class='dashboard_report'>"
                    + "<div class='hd'>"
                            + "<span class='companyName'>" + response.equity.company + "</span>"
                            + "<span class='currentMarketPrice'>CMP: " + response.equity.cmp + "</span>"
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
                                    + "<div class='targetPrice'>Target: " + response.equity.targetPrice + "</div>"
                                    + "<div class='upside'>Upside: " + ((response.equity.upside != 'NA') ? Math.round(response.equity.upside * 100) / 100 + '%' : response.equity.upside) + "</div>"

                                    + "<div class='dwnldReport'>"
                                        + "<a target='blank' href='/system/api/downloadResearchReports?reportFileName=" + response.equity.report + "&vendorName=" + response.equity.vendorName + "'>Download Full Report</a>"
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
    }, function(error) {

    });
}

function getDashboardResearchReport() {

    var dasboardReportJson = window.localStorage.getItem("dasboardReportJson");
    dasboardReportJson = JSON.parse(dasboardReportJson);
    var id = dasboardReportJson.productId;
    var vendorName = dasboardReportJson.vendorName;
    var jsonData = dasboardReportJson.equitysearchjson;
    var pageNumber = dasboardReportJson.pageNumber;

    var url = "/system/api/dashboardResearchReports?type=equity&productId="+ id + "&ofPage="+ pageNumber;
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