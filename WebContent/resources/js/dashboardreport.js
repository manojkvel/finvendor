var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;

function getDashboardResearchReportLoad(id) {
    getDashboardResearchReport(id).then(function(data) {
        response = JSON.parse(data);
        
        var htmlData = '';
        htmlData = "<h2>" + response.companyName + " | " + response.reportName + "</h2>" +
                "<p>" + response.reportSummary + "</p>" +
                "<p>Download Report <a href='/system/api/downloadResearchReports?reportFileName=" + response.reportFileName + "' target='blank'>here</a></p>";

                

        $('#dashboard_report').html(htmlData);
    }, function(error) {

    });
}

function getDashboardResearchReport(id) {
    var url = "/system/api/dashboardResearchReports?type=equity&companyId="+ id;
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