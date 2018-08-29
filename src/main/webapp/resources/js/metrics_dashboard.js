var metricsDashboard = {
    init: function() {
        this.API_TIMEOUT_SMALL = 30*1000;
        this.type = 'all';
        this.perPageCount = [];
    },

    getMetricsApi: function(type, year, month, username, ipaddress) {
        var classRef = this;
        var url = "/system/api/metrics?type=" + type + "&year=" + year + "&month=" + month + "&username=" + username + "&ipaddress=" + ipaddress;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });
            httpRequest.timeout = classRef.API_TIMEOUT_SMALL;
            httpRequest.open('GET', url, true);
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
                        reject(httpRequest.responseText);
                    }
                }
            };

            httpRequest.send();
        });
    },

    timeStampToDate: function (ts) {
        if (ts) {
            ts = new Date(ts).toString();
            ts = ts.split(' ').slice(0, 5);
            ts = /* ts[0] + " " + */ ts[1] + " " + ts[2] + ", " + ts[3];
            return ts;
        } else {
            return 'NA';
        }
    },

    loadMetricsData: function (type, year, month, username, ipaddress) {
        var classRef = this;
        this.getMetricsApi(type, year, month, username, ipaddress).then(function(response) {
            var response = JSON.parse(response);
            var homePage = response.metrics.homePage;
            var equityResearchReport = response.metrics.equityResearchReport;
            var downloadEquityResearchReport = response.metrics.downloadEquityResearchReport;

            $("#global_dashboard .home h4").text(homePage.totalCount);
            $("#global_dashboard .equity_search h4").text(equityResearchReport.totalCount);
            $("#global_dashboard .download h4").text(downloadEquityResearchReport.totalCount);

            classRef.perPageCount.push(homePage.totalCount);
            classRef.perPageCount.push(equityResearchReport.totalCount);
            classRef.perPageCount.push(downloadEquityResearchReport.totalCount);

            classRef.loadGlobalChart();
        }, function(error) {

        });
    },

    loadHomePageReport: function(homePageReport) {

    },

    loadGlobalChart: function() {
        var classRef = this;
        var ctx = document.getElementById("myChart").getContext('2d');
 
        var piechart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ["Home", "Equity Search", "Download Report"],
                datasets: [{
                    label: '# page hits',
                    data: classRef.perPageCount,
                    backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)'
                    ],
                    borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                },
                tooltips: {
                    bodyFontSize: 25
                },
                legend: {
                    labels: {
                        fontColor: 'black',
                        fontSize: 17
                    }
                }
            }
        });
    }
}

metricsDashboard.init();
metricsDashboard.loadMetricsData(metricsDashboard.type);


