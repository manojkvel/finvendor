var timeStampToDate = function (ts) {
    if (ts) {
        ts = new Date(ts).toString();
        ts = ts.split(' ').slice(0, 8);
        ts = /*ts[0] + " " + */ ts[1] + " " + ts[2] + ", " + ts[3]; //+ " " + ts[4];
        return ts;
    } else {
        return 'NA';
    }
};

var timeStampToDateNew = function (ts) {
    if (ts) {
        ts = new Date(ts).toString();
        ts = ts.split(' ').slice(0, 5);

        return ts;
    } else {
        return 'NA';
    }
};

var timeStampToDateLatest = function (ts) {
    if (ts) {
        ts = new Date(ts).toString();
        ts = ts.split(' ').slice(0, 9);
        ts = /*ts[0] + " " + */ ts[1] + " " + ts[2] + ", " + ts[3] + " | " + ts[4];

        return ts;
    } else {
        return 'NA';
    }
};

var baseApiUrl = "/system/api/cis";

/*
** Kennith Fisher Strategies Feed
*/

var kennithFisherStrategyObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 10;
        this.strategyType = "KENNITH_FISHER";
        this.title = "Kennith Fisher";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th>Company Name</th>" +
                                "<th>Annual Revenue</th>" +
                                "<th>D/E</th>" +
                                "<th>Inflation Rate</th>" +
                                "<th>M cap</th>" +
                                "<th>PSR</th>" +
                                "<th>R & D Expenditures</th>" +
                                "<th>EPS</th>" +
                                "<th>Average Net Profit Margin</th>" +
                            "</tr>" +
                        "</thead>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html("<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#strategyModal .modal-body .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);
                $("#strategyModal").show();
            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#strategyModal .modal-body tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
                $("#strategyModal").show();
            });
        }, function(error) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
            $("#strategyModal").show();
        });
    },

    getCurrentStrategyRecordStats: function() {
        var classRef = this;

        var url = baseApiUrl + "/recordstats?type=" + classRef.strategyType + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            //httpRequest.timeout = API_TIMEOUT_SMALL;
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
                        //console.log(httpRequest.status + httpRequest.responseText);
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategy: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            httpRequest.open('GET', url, true);

            httpRequest.onreadystatechange = function () {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        resolve(httpRequest.response);
                    } else {
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategyHtml : function(response) {
        var classRef = this;

        var len = response.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='9'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var annualRevenue = (response[i].annualRevenue) ? parseFloat(response[i].annualRevenue).toFixed(2) : '-';
            var de = (response[i].de) ? parseFloat(response[i].de).toFixed(2) : '-';
            var inflationRate = (response[i].inflationRate) ? parseFloat(response[i].inflationRate).toFixed(2) : '-';
            var mcap = (response[i].mcap) ? parseFloat(response[i].mcap).toFixed(2) : '-';
            var psr = (response[i].psr) ? parseFloat(response[i].psr).toFixed(2) : '-';
            var rndExpenditures = (response[i].rndExpenditures && response[i].rndExpenditures != '-') ? parseFloat(response[i].rndExpenditures).toFixed(2) : '-';
            var _1yrEpsGrowth = (response[i]._1yrEpsGrowth) ? parseFloat(response[i]._1yrEpsGrowth).toFixed(2) : '-';
            var _3YrAvgNetProfitMargin = (response[i]._3YrAvgNetProfitMargin) ? parseFloat(response[i]._3YrAvgNetProfitMargin).toFixed(2) : '-';

            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='companyName'>" + companyName + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='annualRevenue'>" + annualRevenue + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='de'>" + de + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='inflationRate'>" + inflationRate + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='mcap'>" + mcap + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='psr'>" + psr + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='rndExpenditures'>" + rndExpenditures + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='_1yrEpsGrowth'>" + _1yrEpsGrowth + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='_3YrAvgNetProfitMargin'>" + _3YrAvgNetProfitMargin + "</div>" + 
            "</td>" +
            "</tr>";
        }

        $("#strategyModal .modal-body tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#strategyModal .modal-body").append(paginationHtml);

        $('#strategyModal .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        kennithFisherStrategyObj.init();
        kennithFisherStrategyObj.getCurrentStrategyData();
    }, 

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#strategyModal #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
    },

    getPaginationIndex : function(event) {
        var classRef = event.data.this;
        var currentNode = $(this).attr('id');

        if(currentNode == 'last') {
            classRef.getLastPage();
        } else if(currentNode == 'next') {
            classRef.getNextPage();
        } else if(currentNode == 'prev') {
            classRef.getPreviousPage();
        } else if(currentNode == 'first') {
            classRef.getFirstPage();
        }
    },

    getFirstPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.firstPageNumber) {
            classRef.pageNumber = classRef.firstPageNumber;
            classRef.currentIndex = classRef.firstPageNumber;
            classRef.getCurrentStrategyData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCurrentStrategyData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    }

};


/*
** Benjamin Graham Strategies Feed
*/

var benjaminGrahanStrategyObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 10;
        this.strategyType = "BENJAMIN_GRAHAM";
        this.title = "Benjamin Graham";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th>Company Name</th>" +
                                "<th>Total Debt</th>" +
                                "<th>Current Assets</th>" +
                                "<th>Current Liabilities</th>" +
                                "<th>EPS</th>" +
                                "<th>P/E</th>" +
                                "<th>P/B</th>" +
                                "<th>Div Yield (%)</th>" +
                            "</tr>" +
                        "</thead>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html("<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#strategyModal .modal-body .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);
                $("#strategyModal").show();
            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#strategyModal .modal-body tbody").html("<tr><td colspan='8'>We are not able to get the info, please try again later.</td></tr>");
                $("#strategyModal").show();
            });
        }, function(error) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='8'>We are not able to get the info, please try again later.</td></tr>");
            $("#strategyModal").show();
        });
    },

    getCurrentStrategyRecordStats: function() {
        var classRef = this;

        var url = baseApiUrl + "/recordstats?type=" + classRef.strategyType + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            //httpRequest.timeout = API_TIMEOUT_SMALL;
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
                        //console.log(httpRequest.status + httpRequest.responseText);
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategy: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            httpRequest.open('GET', url, true);

            httpRequest.onreadystatechange = function () {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        resolve(httpRequest.response);
                    } else {
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategyHtml : function(response) {
        var classRef = this;

        var len = response.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='8'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var totalDebt = (response[i].totalDebt) ? parseFloat(response[i].totalDebt).toFixed(2) : '-';
            var currentAssets = (response[i].currentAssets) ? parseFloat(response[i].currentAssets).toFixed(2) : '-';
            var currentLiabilities = (response[i].currentLiabilities) ? parseFloat(response[i].currentLiabilities).toFixed(2) : '-';
            var is5YrEPSGrowthPositive = (response[i].is5YrEPSGrowthPositive) ? response[i].is5YrEPSGrowthPositive : '-';
            var pe = (response[i].pe) ? parseFloat(response[i].pe).toFixed(2) : '-';
            var pb = (response[i].pb) ? parseFloat(response[i].pb).toFixed(2) : '-';
            var dividenYield = (response[i].dividenYield) ? parseFloat(response[i].dividenYield).toFixed(2) : '-';

            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='companyName'>" + companyName + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='totalDebt'>" + totalDebt + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='currentAssets'>" + currentAssets + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='currentLiabilities'>" + currentLiabilities + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='is5YrEPSGrowthPositive'>" + is5YrEPSGrowthPositive + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='pe'>" + pe + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='pe'>" + pe + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='dividenYield'>" + dividenYield + "</div>" + 
            "</td>" +
            "</tr>";
        }

        $("#strategyModal .modal-body tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#strategyModal .modal-body").append(paginationHtml);

        $('#strategyModal .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        benjaminGrahanStrategyObj.init();
        benjaminGrahanStrategyObj.getCurrentStrategyData();
    },  

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#strategyModal #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
    },

    getPaginationIndex : function(event) {
        var classRef = event.data.this;
        var currentNode = $(this).attr('id');

        if(currentNode == 'last') {
            classRef.getLastPage();
        } else if(currentNode == 'next') {
            classRef.getNextPage();
        } else if(currentNode == 'prev') {
            classRef.getPreviousPage();
        } else if(currentNode == 'first') {
            classRef.getFirstPage();
        }
    },

    getFirstPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.firstPageNumber) {
            classRef.pageNumber = classRef.firstPageNumber;
            classRef.currentIndex = classRef.firstPageNumber;
            classRef.getCurrentStrategyData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCurrentStrategyData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    }

};


/*
** Martin Zweig Strategies Feed
*/

var martinZweigStrategyObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 10;
        this.strategyType = "MARTIN_ZWEIG";
        this.title = "Martin Zweig";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th>Company Name</th>" +
                                "<th>EPS Growth</th>" +
                                "<th>D/E</th>" +
                                "<th>Latest Revenue Growth</th>" +
                                "<th>Nifty50 P/E</th>" +
                                "<th>P/E</th>" +
                                "<th>EPS Growth per year</th>" +
                            "</tr>" +
                        "</thead>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html("<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#strategyModal .modal-body .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);
                $("#strategyModal").show();
            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#strategyModal .modal-body tbody").html("<tr><td colspan='7'>We are not able to get the info, please try again later.</td></tr>");
                $("#strategyModal").show();
            });
        }, function(error) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='7'>We are not able to get the info, please try again later.</td></tr>");
            $("#strategyModal").show();
        });
    },

    getCurrentStrategyRecordStats: function() {
        var classRef = this;

        var url = baseApiUrl + "/recordstats?type=" + classRef.strategyType + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            //httpRequest.timeout = API_TIMEOUT_SMALL;
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
                        //console.log(httpRequest.status + httpRequest.responseText);
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategy: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            httpRequest.open('GET', url, true);

            httpRequest.onreadystatechange = function () {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        resolve(httpRequest.response);
                    } else {
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategyHtml : function(response) {
        var classRef = this;
        var len = response.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='7'>No Matching Records Found</td></tr>");
            return;
        }
        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var allYearEpsGrowth = (response[i].allYearEpsGrowth) ? parseFloat(response[i].allYearEpsGrowth).toFixed(2) : '-';
            var deRatio = (response[i].deRatio) ? parseFloat(response[i].deRatio).toFixed(2) : '-';
            var latestRevenueGrowth = (response[i].latestRevenueGrowth) ? parseFloat(response[i].latestRevenueGrowth).toFixed(2) : '-';
            var nifty50Pe = (response[i].nifty50Pe) ? parseFloat(response[i].nifty50Pe).toFixed(2) : '-';
            var pe = (response[i].pe) ? parseFloat(response[i].pe).toFixed(2) : '-';
            var yearWiseEpsGrowth = (response[i].yearWiseEpsGrowth) ? parseFloat(response[i].yearWiseEpsGrowth).toFixed(2) : '-';

            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='companyName'>" + companyName + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='allYearEpsGrowth'>" + allYearEpsGrowth + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='deRatio'>" + deRatio + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='revenue'>" + latestRevenueGrowth + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='nifty50Pe'>" + nifty50Pe + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='pe'>" + pe + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='yearWiseEpsGrowth'>" + yearWiseEpsGrowth + "</div>" + 
            "</td>" +
            "</tr>";
        }

        $("#strategyModal .modal-body tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";


        $("#strategyModal .modal-body").append(paginationHtml);

        $('#strategyModal .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        martinZweigStrategyObj.init();
        martinZweigStrategyObj.getCurrentStrategyData();
    }, 

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }

        $("#strategyModal #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
    },

    getPaginationIndex : function(event) {
        var classRef = event.data.this;
        var currentNode = $(this).attr('id');

        if(currentNode == 'last') {
            classRef.getLastPage();
        } else if(currentNode == 'next') {
            classRef.getNextPage();
        } else if(currentNode == 'prev') {
            classRef.getPreviousPage();
        } else if(currentNode == 'first') {
            classRef.getFirstPage();
        }
    },

    getFirstPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.firstPageNumber) {
            classRef.pageNumber = classRef.firstPageNumber;
            classRef.currentIndex = classRef.firstPageNumber;
            classRef.getCurrentStrategyData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCurrentStrategyData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    }

};

/*
** James O'Shaughnessy's Strategies Feed
*/

var jamesOshaughnessyStrategyObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 10;
        this.strategyType = "JAMES_SHAUGHNESSY";
        this.title = "James O'Shaughnessy's";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th>Company Name</th>" +
                                "<th>CMP</th>" +
                                "<th>EPS</th>" +
                                "<th>M Cap</th>" +
                                "<th>Net Operating CashFlow</th>" +
                                "<th>P/B</th>" +
                                "<th>Revenue</th>" +
                            "</tr>" +
                        "</thead>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html("<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#strategyModal .modal-body .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);
                $("#strategyModal").show();
            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#strategyModal .modal-body tbody").html("<tr><td colspan='7'>We are not able to get the info, please try again later.</td></tr>");
                $("#strategyModal").show();
            });
        }, function(error) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='7'>We are not able to get the info, please try again later.</td></tr>");
            $("#strategyModal").show();
        });
    },

    getCurrentStrategyRecordStats: function() {
        var classRef = this;

        var url = baseApiUrl + "/recordstats?type=" + classRef.strategyType + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            //httpRequest.timeout = API_TIMEOUT_SMALL;
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
                        //console.log(httpRequest.status + httpRequest.responseText);
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategy: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            httpRequest.open('GET', url, true);

            httpRequest.onreadystatechange = function () {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        resolve(httpRequest.response);
                    } else {
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategyHtml : function(response) {
        var classRef = this;
        var len = response.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='7'>No Matching Records Found</td></tr>");
            return;
        }
        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var cmp = (response[i].cmp) ? parseFloat(response[i].cmp).toFixed(2) : '-';
            var eps = (response[i].eps) ? parseFloat(response[i].eps).toFixed(2) : '-';
            var mcap = (response[i].mcap) ? parseFloat(response[i].mcap).toFixed(2) : '-';
            var netOperatingCashFlow = (response[i].netOperatingCashFlow) ? parseFloat(response[i].netOperatingCashFlow).toFixed(2) : '-';
            var pb = (response[i].pb) ? parseFloat(response[i].pb).toFixed(2) : '-';
            var revenue = (response[i].revenue) ? parseFloat(response[i].revenue).toFixed(2) : '-';

            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='companyName'>" + companyName + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='cmp'>" + cmp + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='eps'>" + eps + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='mcap'>" + mcap + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='netOperatingCashFlow'>" + netOperatingCashFlow + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='pb'>" + pb + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='revenue'>" + revenue + "</div>" + 
            "</td>" +
            "</tr>";
        }

        $("#strategyModal .modal-body tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#strategyModal .modal-body").append(paginationHtml);

        $('#strategyModal .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        jamesOshaughnessyStrategyObj.init();
        jamesOshaughnessyStrategyObj.getCurrentStrategyData();
    }, 

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#strategyModal #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
    },

    getPaginationIndex : function(event) {
        var classRef = event.data.this;
        var currentNode = $(this).attr('id');

        if(currentNode == 'last') {
            classRef.getLastPage();
        } else if(currentNode == 'next') {
            classRef.getNextPage();
        } else if(currentNode == 'prev') {
            classRef.getPreviousPage();
        } else if(currentNode == 'first') {
            classRef.getFirstPage();
        }
    },

    getFirstPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.firstPageNumber) {
            classRef.pageNumber = classRef.firstPageNumber;
            classRef.currentIndex = classRef.firstPageNumber;
            classRef.getCurrentStrategyData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCurrentStrategyData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    }

};



/*
** Joel Greenblatt's Strategies Feed
*/

var joelGreenblattStrategyObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 10;
        this.strategyType = "JOEL_GREENBLATT";
        this.title = "Joel Greenblatt's";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th>Company Name</th>" +
                                "<th>Cash and Cash Equiv.</th>" +
                                "<th>M Cap</th>" +
                                "<th>Operating Profit Margin</th>" +
                                "<th>PAT</th>" +
                                "<th>Revenue</th>" +
                                "<th>Total Capital</th>" +
                                "<th>Total Debt</th>" +
                            "</tr>" +
                        "</thead>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html("<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#strategyModal .modal-body .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);
                $("#strategyModal").show();
            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#strategyModal .modal-body tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
                $("#strategyModal").show();
            });
        }, function(error) {
                $("#strategyModal .modal-body tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
                $("#strategyModal").show();
        });
    },

    getCurrentStrategyRecordStats: function() {
        var classRef = this;

        var url = baseApiUrl + "/recordstats?type=" + classRef.strategyType + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            //httpRequest.timeout = API_TIMEOUT_SMALL;
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
                        //console.log(httpRequest.status + httpRequest.responseText);
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategy: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            httpRequest.open('GET', url, true);

            httpRequest.onreadystatechange = function () {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        resolve(httpRequest.response);
                    } else {
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    },

    getCurrentStrategyHtml : function(response) {
        var classRef = this;
        var len = response.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='9'>No Matching Records Found</td></tr>");
            return;
        }
        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var cashAndCashEquiv = (response[i].cashAndCashEquiv) ? parseFloat(response[i].cashAndCashEquiv).toFixed(2) : '-';
            var mcap = (response[i].mcap) ? parseFloat(response[i].mcap).toFixed(2) : '-';
            var operatingProfitMargin = (response[i].operatingProfitMargin) ? parseFloat(response[i].operatingProfitMargin).toFixed(2) : '-';
            var pat = (response[i].pat) ? parseFloat(response[i].pat).toFixed(2) : '-';
            var revenue = (response[i].revenue) ? parseFloat(response[i].revenue).toFixed(2) : '-';
            var totalCapital = (response[i].totalCapital) ? parseFloat(response[i].totalCapital).toFixed(2) : '-';
            var totalDebt = (response[i].totalDebt) ? parseFloat(response[i].totalDebt).toFixed(2) : '-';

            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='companyName'>" + companyName + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='cashAndCashEquiv'>" + cashAndCashEquiv + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='mcap'>" + mcap + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='operatingProfitMargin'>" + operatingProfitMargin + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='pat'>" + pat + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='revenue'>" + revenue + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='totalCapital'>" + totalCapital + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='totalDebt'>" + totalDebt + "</div>" + 
            "</td>" +
            "</tr>";
        }

        $("#strategyModal .modal-body tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#strategyModal .modal-body").append(paginationHtml);

        $('#strategyModal .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        joelGreenblattStrategyObj.init();
        joelGreenblattStrategyObj.getCurrentStrategyData();
    }, 

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#strategyModal #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
    },

    getPaginationIndex : function(event) {
        var classRef = event.data.this;
        var currentNode = $(this).attr('id');

        if(currentNode == 'last') {
            classRef.getLastPage();
        } else if(currentNode == 'next') {
            classRef.getNextPage();
        } else if(currentNode == 'prev') {
            classRef.getPreviousPage();
        } else if(currentNode == 'first') {
            classRef.getFirstPage();
        }
    },

    getFirstPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.firstPageNumber) {
            classRef.pageNumber = classRef.firstPageNumber;
            classRef.currentIndex = classRef.firstPageNumber;
            classRef.getCurrentStrategyData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCurrentStrategyData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCurrentStrategyData();
        }
    }

};

kennithFisherStrategyObj.init();
benjaminGrahanStrategyObj.init();
martinZweigStrategyObj.init();
jamesOshaughnessyStrategyObj.init();
joelGreenblattStrategyObj.init();


$("#strategyModal button").on('click', function() {
    $("#strategyModal").hide();
    $("#strategyModal .modal-body").html('');
});

$('#kennith_fisher_strategy .view_btn button').on('click', kennithFisherStrategyObj.setFullScreen);
$('#benjamin_graham_strategy .view_btn button').on('click', benjaminGrahanStrategyObj.setFullScreen);
$("#marting_zweigs_strategy .view_btn button").on('click', martinZweigStrategyObj.setFullScreen);
$('#james_o_shaughessys_strategy .view_btn button').on('click', jamesOshaughnessyStrategyObj.setFullScreen);
$("#joel_greenblatts_strategy .view_btn button").on('click', joelGreenblattStrategyObj.setFullScreen);