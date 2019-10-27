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

var baseApiUrl = "/api/cis";


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
        this.sortByValue = 'companyName';
        this.orderBy = 'desc';
        this.strategyType = "KENNITH_FISHER";
        this.title = "Kenneth Fisher's Strategy";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th data-id='companyName'>Company Name <i class='fa fa-sort'></i></th>" +
                                "<th data-id='annualRevenue'>Annual Revenue (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='de'>D/E <i class='fa fa-sort'></i></th>" +
                                "<th data-id='inflationRate'>Inflation Rate <i class='fa fa-sort'></i></th>" +
                                "<th data-id='mcap'>M cap (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='psr'>PSR <i class='fa fa-sort'></i></th>" +
                                "<th data-id='rndExpense'>R & D Exp (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='eps'>EPS <i class='fa fa-sort'></i></th>" +
                                "<th data-id='avgNetProfitMargin'>Average Net Profit Margin <i class='fa fa-sort'></i></th>" +
                            "</tr>" +
                        "</thead>";

        this.recordsPerPage = "<div class='max_per_page'>" +
                                "<span>Records Per Page </span>" +
                                "<select>" +
                                    "<option value='5'>5</option>" +
                                    "<option value='10' selected>10</option>" +
                                    "<option value='30'>30</option>" +
                                    "<option value='50'>50</option>" +
                                    "<option value='100'>100</option>" +
                                "</select>" +
                                "<span id='total_records_count' style='padding-left: 30px;font-weight:bold;font-size: 13px;'></span>" +
                            "</div>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html(this.recordsPerPage + "<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            $("#strategyModal #total_records_count").html(classRef.totalRecords + " Results");

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

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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
            var inflationRate = (response[i].inflationRate) ? parseFloat(response[i].inflationRate).toFixed(2) + "%" : '-';
            var mcap = (response[i].mcap) ? parseFloat(response[i].mcap).toFixed(2) : '-';
            var psr = (response[i].psr) ? parseFloat(response[i].psr).toFixed(2) : '-';
            var rndExpenditures = (response[i].rndExpenditures && response[i].rndExpenditures != '-') ? parseFloat(response[i].rndExpenditures).toFixed(2) : '-';
            var _1yrEpsGrowth = (response[i]._1yrEpsGrowth) ? parseFloat(response[i]._1yrEpsGrowth).toFixed(2) : '-';
            var _3YrAvgNetProfitMargin = (response[i]._3YrAvgNetProfitMargin) ? parseFloat(response[i]._3YrAvgNetProfitMargin).toFixed(2) + "%" : '-';

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

        $('#strategyModal .pager a').off().on('click', {this: classRef}, classRef.getPaginationIndex);

        $("#strategyModal .strategy_table thead th").off().on('click', {this: classRef}, classRef.getSortedByValue);

        classRef.setRecordStats();

        $('#strategyModal .max_per_page select').off().on('change', {this: classRef}, classRef.getPerPageMaxRecords);
    },

    setFullScreen : function(event) {
        if(!isLoggedInUser()) {
            $('#strategyModal').modal('show');
            kennithFisherStrategyObj.init();
            kennithFisherStrategyObj.getCurrentStrategyData();
        } else {
            inner_login('view/celebrity-investors-strategies.jsp');
        }
    }, 

    getPerPageMaxRecords: function(event) {
        var classRef = event.data.this;

        if(classRef.perPageMaxRecords !== Number($(this).val())) {
            classRef.pageNumber = 1;
            classRef.firstPageNumber = 1;
            classRef.lastPageNumber = 1;
            classRef.currentIndex = 1;
        }

        classRef.perPageMaxRecords = Number($(this).val());
        console.log("perPageMaxRecords: " + classRef.perPageMaxRecords);
        kennithFisherStrategyObj.getCurrentStrategyData();
    },

    getSortedByValue: function(event) {
        var classRef = event.data.this;

        if($(this).attr('data-id') == undefined) {
            return;
        }
        
        if($(this).attr('data-id') == classRef.sortByValue) {
            
            if(classRef.orderBy == "desc") {
                classRef.orderBy = "asc";
            } else if(classRef.orderBy == "asc") {
                classRef.orderBy = "desc";
            }
        } else {
            classRef.orderBy = "desc";
        }

        classRef.sortByValue = $(this).attr('data-id');
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
        this.sortByValue = 'companyName';
        this.orderBy = 'desc';
        this.strategyType = "BENJAMIN_GRAHAM";
        this.title = "Benjamin Graham's Strategy";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th data-id='companyName'>Company Name <i class='fa fa-sort'></i></th>" +
                                "<th data-id='totalDebt'>Total Debt (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='currentAsset'>Current Assets (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='currentLiab'>Current Liabilities (cr) <i class='fa fa-sort'></i></th>" +
                                "<th>EPS <i class='fa fa-sort'></i></th>" +
                                "<th data-id='pe'>P/E <i class='fa fa-sort'></i></th>" +
                                "<th data-id='pb'>P/B <i class='fa fa-sort'></i></th>" +
                                "<th data-id='divYield'>Div Yield (%) <i class='fa fa-sort'></i></th>" +
                            "</tr>" +
                        "</thead>";

        this.recordsPerPage = "<div class='max_per_page'>" +
                                "<span>Records Per Page </span>" +
                                "<select>" +
                                    "<option value='5'>5</option>" +
                                    "<option value='10' selected>10</option>" +
                                    "<option value='30'>30</option>" +
                                    "<option value='50'>50</option>" +
                                    "<option value='100'>100</option>" +
                                "</select>" +
                                "<span id='total_records_count' style='padding-left: 30px;font-weight:bold;font-size: 13px;'></span>" +
                            "</div>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html(this.recordsPerPage + "<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            $("#strategyModal #total_records_count").html(classRef.totalRecords + " Results");

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

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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

        $('#strategyModal .pager a').off().on('click', {this: classRef}, classRef.getPaginationIndex);

        $("#strategyModal .strategy_table thead th").off().on('click', {this: classRef}, classRef.getSortedByValue);

        classRef.setRecordStats();

        $('#strategyModal .max_per_page select').off().on('change', {this: classRef}, classRef.getPerPageMaxRecords);
    },

    setFullScreen : function(event) {
        if(!isLoggedInUser()) {
            $('#strategyModal').modal('show');
            benjaminGrahanStrategyObj.init();
            benjaminGrahanStrategyObj.getCurrentStrategyData();
        } else {
            inner_login('view/celebrity-investors-strategies.jsp');
        }
    }, 

    getPerPageMaxRecords: function(event) {
        var classRef = event.data.this;

        if(classRef.perPageMaxRecords !== Number($(this).val())) {
            classRef.pageNumber = 1;
            classRef.firstPageNumber = 1;
            classRef.lastPageNumber = 1;
            classRef.currentIndex = 1;
        }

        classRef.perPageMaxRecords = Number($(this).val());
        console.log("perPageMaxRecords: " + classRef.perPageMaxRecords);
        benjaminGrahanStrategyObj.getCurrentStrategyData();
    },

    getSortedByValue: function(event) {
        var classRef = event.data.this;

        if($(this).attr('data-id') == undefined) {
            return;
        }
        
        if($(this).attr('data-id') == classRef.sortByValue) {
            
            if(classRef.orderBy == "desc") {
                classRef.orderBy = "asc";
            } else if(classRef.orderBy == "asc") {
                classRef.orderBy = "desc";
            }
        } else {
            classRef.orderBy = "desc";
        }

        classRef.sortByValue = $(this).attr('data-id');
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
** FinVendor Pick Strategies Feed
*/

var finvendorPickStrategyObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 10;
        this.sortByValue = 'companyName';
        this.orderBy = 'desc';
        this.strategyType = "FINVENDOR_PICK";
        this.title = "FinVendor Pick Strategy";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th data-id='companyName'>Company Name <i class='fa fa-sort'></i></th>" +
                                "<th data-id='avgRoe'>Average ROE <i class='fa fa-sort'></i></th>" +
                                "<th data-id='annualPat'>PAT (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='currentOPM'>Current Operating Profit Margin (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='currentRatio'>Current Ratio <i class='fa fa-sort'></i></th>" +
                                "<th data-id='currentRoe'>Current ROE <i class='fa fa-sort'></i></th>" +
                                "<th data-id='de'>D/E <i class='fa fa-sort'></i></th>" +
                                "<th data-id='earningYield'>Earning Yield (%) <i class='fa fa-sort'></i></th>" +
                                //"<th>EPS Growth Positive <i class='fa fa-sort'></i></th>" +
                                //"<th data-id='longTermDebt'>Long Term Debt <i class='fa fa-sort'></i></th>" +
                                "<th data-id='oneYrEpsGrowth'>EPS Growth (yr) <i class='fa fa-sort'></i></th>" +
                                //"<th data-id='outstandingShare'>Outstanding Share <i class='fa fa-sort'></i></th>" +
                                "<th data-id='peRatio'>P/E <i class='fa fa-sort'></i></th>" +
                                //"<th data-id='retainedEarning'>Retained Earning <i class='fa fa-sort'></i></th>" +
                                "<th data-id='rotc'>ROTC <i class='fa fa-sort'></i></th>" +
                                "<th data-id='salesGrowth'>Sales Growth <i class='fa fa-sort'></i></th>" +
                                //"<th data-id='totalFreeCashFlow'>Total Free Cash Flow <i class='fa fa-sort'></i></th>" +
                            "</tr>" +
                        "</thead>";

        this.recordsPerPage = "<div class='max_per_page'>" +
                                "<span>Records Per Page </span>" +
                                "<select>" +
                                    "<option value='5'>5</option>" +
                                    "<option value='10' selected>10</option>" +
                                    "<option value='30'>30</option>" +
                                    "<option value='50'>50</option>" +
                                    "<option value='100'>100</option>" +
                                "</select>" +
                                "<span id='total_records_count' style='padding-left: 30px;font-weight:bold;font-size: 13px;'></span>" +
                            "</div>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html(this.recordsPerPage + "<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;

        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            $("#strategyModal #total_records_count").html(classRef.totalRecords + " Results");

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#strategyModal .modal-body .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);
                $("#strategyModal").show();
            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#strategyModal .modal-body tbody").html("<tr><td colspan='17'>We are not able to get the info, please try again later.</td></tr>");
                $("#strategyModal").show();
            });
        }, function(error) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='17'>We are not able to get the info, please try again later.</td></tr>");
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

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='17'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var allYearAverageRoe = (response[i].allYearAverageRoe) ? parseFloat(response[i].allYearAverageRoe).toFixed(2) : '-';
            var annualPat = (response[i].annualPat) ? parseFloat(response[i].annualPat).toFixed(2) : '-';
            var currentOperatingProfitMargin = (response[i].currentOperatingProfitMargin) ? parseFloat(response[i].currentOperatingProfitMargin).toFixed(2) : '-';
            var currentRatio = (response[i].currentRatio) ? response[i].currentRatio : '-';
            var currentRoe = (response[i].currentRoe) ? parseFloat(response[i].currentRoe).toFixed(2) : '-';
            var de = (response[i].de) ? parseFloat(response[i].de).toFixed(2) : '-';
            var earningYield = (response[i].earningYield) ? parseFloat(response[i].earningYield).toFixed(2) : '-';
            var isAllYearEpsGrowthPositive = (response[i].isAllYearEpsGrowthPositive) ? parseFloat(response[i].isAllYearEpsGrowthPositive).toFixed(2) : '-';
            var longTermDebt = (response[i].longTermDebt) ? parseFloat(response[i].longTermDebt).toFixed(2) : '-';
            var oneYearEpsGrowth = (response[i].oneYearEpsGrowth) ? parseFloat(response[i].oneYearEpsGrowth).toFixed(2) : '-';
            var outstandingShare = (response[i].outstandingShare) ? parseFloat(response[i].outstandingShare).toFixed(2) : '-';
            var peRatio = (response[i].peRatio) ? parseFloat(response[i].peRatio).toFixed(2) : '-';
            var retainedEarning = (response[i].retainedEarning) ? parseFloat(response[i].retainedEarning).toFixed(2) : '-';
            var rotc = (response[i].rotc) ? parseFloat(response[i].rotc).toFixed(2) : '-';
            var salesGrowth = (response[i].salesGrowth) ? parseFloat(response[i].salesGrowth).toFixed(2) : '-';
            var totalFreeCashFlow = (response[i].totalFreeCashFlow) ? parseFloat(response[i].totalFreeCashFlow).toFixed(2) : '-';
            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='companyName'>" + companyName + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='allYearAverageRoe'>" + allYearAverageRoe + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='annualPat'>" + annualPat + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='currentOperatingProfitMargin'>" + currentOperatingProfitMargin + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='currentRatio'>" + currentRatio + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='currentRoe'>" + currentRoe + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='de'>" + de + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='earningYield'>" + earningYield + "</div>" + 
            "</td>" +
            //"<td>" + 
            //"<div class='isAllYearEpsGrowthPositive'>" + isAllYearEpsGrowthPositive + "</div>" + 
            //"</td>" +
            //"<td>" + 
            //"<div class='longTermDebt'>" + longTermDebt + "</div>" + 
            //"</td>" +
            "<td>" + 
            "<div class='oneYearEpsGrowth'>" + oneYearEpsGrowth + "</div>" + 
            "</td>" +
            //"<td>" + 
            //"<div class='outstandingShare'>" + outstandingShare + "</div>" + 
            //"</td>" +
            "<td>" + 
            "<div class='peRatio'>" + peRatio + "</div>" + 
            "</td>" +
            //"<td>" + 
            //"<div class='retainedEarning'>" + retainedEarning + "</div>" + 
            //"</td>" +
            "<td>" + 
            "<div class='rotc'>" + rotc + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='salesGrowth'>" + salesGrowth + "</div>" + 
            "</td>" +
            //"<td>" + 
            //"<div class='totalFreeCashFlow'>" + totalFreeCashFlow + "</div>" + 
            //"</td>" +
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

        $('#strategyModal .pager a').off().on('click', {this: classRef}, classRef.getPaginationIndex);

        $("#strategyModal .strategy_table thead th").off().on('click', {this: classRef}, classRef.getSortedByValue);

        classRef.setRecordStats();

        $('#strategyModal .max_per_page select').off().on('change', {this: classRef}, classRef.getPerPageMaxRecords);
    },

    setFullScreen : function(event) {
        if(!isLoggedInUser()) {
            $('#strategyModal').modal('show');
            finvendorPickStrategyObj.init();
            finvendorPickStrategyObj.getCurrentStrategyData();
        } else {
            inner_login('view/celebrity-investors-strategies.jsp');
        }
    }, 

    getPerPageMaxRecords: function(event) {
        var classRef = event.data.this;

        if(classRef.perPageMaxRecords !== Number($(this).val())) {
            classRef.pageNumber = 1;
            classRef.firstPageNumber = 1;
            classRef.lastPageNumber = 1;
            classRef.currentIndex = 1;
        }

        classRef.perPageMaxRecords = Number($(this).val());
        console.log("perPageMaxRecords: " + classRef.perPageMaxRecords);
        finvendorPickStrategyObj.getCurrentStrategyData();
    },

    getSortedByValue: function(event) {
        var classRef = event.data.this;

        if($(this).attr('data-id') == undefined) {
            return;
        }
        
        if($(this).attr('data-id') == classRef.sortByValue) {
            
            if(classRef.orderBy == "desc") {
                classRef.orderBy = "asc";
            } else if(classRef.orderBy == "asc") {
                classRef.orderBy = "desc";
            }
        } else {
            classRef.orderBy = "desc";
        }

        classRef.sortByValue = $(this).attr('data-id');
        finvendorPickStrategyObj.getCurrentStrategyData();
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
        this.sortByValue = 'companyName';
        this.orderBy = 'desc';
        this.strategyType = "MARTIN_ZWEIG";
        this.title = "Martin Zweig's Strategy";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th data-id='companyName'>Company Name <i class='fa fa-sort'></i></th>" +
                                "<th data-id='epsGrowth'>EPS Growth <i class='fa fa-sort'></i></th>" +
                                "<th data-id='de'>D/E <i class='fa fa-sort'></i></th>" +
                                "<th data-id='latestRevenueGrowth'>Latest Revenue Growth <i class='fa fa-sort'></i></th>" +
                                "<th data-id='nifty50Pe'>Nifty50 P/E <i class='fa fa-sort'></i></th>" +
                                "<th data-id='pe'>P/E <i class='fa fa-sort'></i></th>" +
                            "</tr>" +
                        "</thead>";

        this.recordsPerPage = "<div class='max_per_page'>" +
                                "<span>Records Per Page </span>" +
                                "<select>" +
                                    "<option value='5'>5</option>" +
                                    "<option value='10' selected>10</option>" +
                                    "<option value='30'>30</option>" +
                                    "<option value='50'>50</option>" +
                                    "<option value='100'>100</option>" +
                                "</select>" +
                                "<span id='total_records_count' style='padding-left: 30px;font-weight:bold;font-size: 13px;'></span>" +
                            "</div>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html(this.recordsPerPage + "<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            $("#strategyModal #total_records_count").html(classRef.totalRecords + " Results");

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#strategyModal .modal-body .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);
                $("#strategyModal").show();
            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#strategyModal .modal-body tbody").html("<tr><td colspan='6'>We are not able to get the info, please try again later.</td></tr>");
                $("#strategyModal").show();
            });
        }, function(error) {
            $("#strategyModal .modal-body tbody").html("<tr><td colspan='6'>We are not able to get the info, please try again later.</td></tr>");
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

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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
            // var yearWiseEpsGrowth = (response[i].yearWiseEpsGrowth) ? parseFloat(response[i].yearWiseEpsGrowth).toFixed(2) : '-';

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

        $('#strategyModal .pager a').off().on('click', {this: classRef}, classRef.getPaginationIndex);

        $("#strategyModal .strategy_table thead th").off().on('click', {this: classRef}, classRef.getSortedByValue);

        classRef.setRecordStats();

        $('#strategyModal .max_per_page select').off().on('change', {this: classRef}, classRef.getPerPageMaxRecords);
    },

    setFullScreen : function(event) {
        if(!isLoggedInUser()) {
            $('#strategyModal').modal('show');
            martinZweigStrategyObj.init();
            martinZweigStrategyObj.getCurrentStrategyData();
        } else {
            inner_login('view/celebrity-investors-strategies.jsp');
        }
    }, 

    getPerPageMaxRecords: function(event) {
        var classRef = event.data.this;

        if(classRef.perPageMaxRecords !== Number($(this).val())) {
            classRef.pageNumber = 1;
            classRef.firstPageNumber = 1;
            classRef.lastPageNumber = 1;
            classRef.currentIndex = 1;
        }

        classRef.perPageMaxRecords = Number($(this).val());
        console.log("perPageMaxRecords: " + classRef.perPageMaxRecords);
        martinZweigStrategyObj.getCurrentStrategyData();
    },

    getSortedByValue: function(event) {
        var classRef = event.data.this;

        if($(this).attr('data-id') == undefined) {
            return;
        }
        
        if($(this).attr('data-id') == classRef.sortByValue) {
            
            if(classRef.orderBy == "desc") {
                classRef.orderBy = "asc";
            } else if(classRef.orderBy == "asc") {
                classRef.orderBy = "desc";
            }
        } else {
            classRef.orderBy = "desc";
        }

        classRef.sortByValue = $(this).attr('data-id');
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
        this.sortByValue = 'companyName';
        this.orderBy = 'desc';
        this.strategyType = "JAMES_SHAUGHNESSY";
        this.title = "James O'Shaughnessy's Strategy";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th data-id='companyName'>Company Name <i class='fa fa-sort'></i></th>" +
                                "<th data-id='cmp'>CMP <i class='fa fa-sort'></i></th>" +
                                "<th data-id='eps'>EPS <i class='fa fa-sort'></i></th>" +
                                "<th data-id='mcap'>M Cap (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='nocf'>Net Operating CashFlow (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='pb'>P/B <i class='fa fa-sort'></i></th>" +
                                "<th data-id='revenue'>Revenue (cr) <i class='fa fa-sort'></i></th>" +
                            "</tr>" +
                        "</thead>";

        this.recordsPerPage = "<div class='max_per_page'>" +
                                "<span>Records Per Page </span>" +
                                "<select>" +
                                    "<option value='5'>5</option>" +
                                    "<option value='10' selected>10</option>" +
                                    "<option value='30'>30</option>" +
                                    "<option value='50'>50</option>" +
                                    "<option value='100'>100</option>" +
                                "</select>" +
                                "<span id='total_records_count' style='padding-left: 30px;font-weight:bold;font-size: 13px;'></span>" +
                            "</div>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html(this.recordsPerPage + "<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            $("#strategyModal #total_records_count").html(classRef.totalRecords + " Results");

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

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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

        $('#strategyModal .pager a').off().on('click', {this: classRef}, classRef.getPaginationIndex);
        
        $("#strategyModal .strategy_table thead th").off().on('click', {this: classRef}, classRef.getSortedByValue);

        classRef.setRecordStats();

        $('#strategyModal .max_per_page select').off().on('change', {this: classRef}, classRef.getPerPageMaxRecords);
    },

    setFullScreen : function(event) {
        if(!isLoggedInUser()) {
            $('#strategyModal').modal('show');
            jamesOshaughnessyStrategyObj.init();
            jamesOshaughnessyStrategyObj.getCurrentStrategyData();
        } else {
            inner_login('view/celebrity-investors-strategies.jsp');
        }
    }, 

    getPerPageMaxRecords: function(event) {
        var classRef = event.data.this;

        if(classRef.perPageMaxRecords !== Number($(this).val())) {
            classRef.pageNumber = 1;
            classRef.firstPageNumber = 1;
            classRef.lastPageNumber = 1;
            classRef.currentIndex = 1;
        }

        classRef.perPageMaxRecords = Number($(this).val());
        console.log("perPageMaxRecords: " + classRef.perPageMaxRecords);
        jamesOshaughnessyStrategyObj.getCurrentStrategyData();
    },

    getSortedByValue: function(event) {
        var classRef = event.data.this;

        if($(this).attr('data-id') == undefined) {
            return;
        }
        
        if($(this).attr('data-id') == classRef.sortByValue) {
            
            if(classRef.orderBy == "desc") {
                classRef.orderBy = "asc";
            } else if(classRef.orderBy == "asc") {
                classRef.orderBy = "desc";
            }
        } else {
            classRef.orderBy = "desc";
        }

        classRef.sortByValue = $(this).attr('data-id');
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
        this.sortByValue = 'companyName';
        this.orderBy = 'desc';
        this.strategyType = "JOEL_GREENBLATT";
        this.title = "Joel Greenblatt's Strategy";
        this.tableHeader = "<thead>" +
                            "<tr>" +
                                "<th data-id='companyName'>Company Name <i class='fa fa-sort'></i></th>" +
                                "<th data-id='cashAndCashEquiv'>Cash and Cash Equiv. (cr) <i class='fa fa-sort'></i></th>" +
                                /*"<th data-id='stockReturn'>Stock Return</th>" +*/
                                "<th data-id='mcap'>M Cap (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='ofm'>Operating Profit Margin <i class='fa fa-sort'></i></th>" +
                                "<th data-id='pat'>PAT <i class='fa fa-sort'></i></th>" +
                                "<th data-id='revenue'>Revenue (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='totalCap'>Total Capital (cr) <i class='fa fa-sort'></i></th>" +
                                "<th data-id='totalDebt'>Total Debt (cr) <i class='fa fa-sort'></i></th>" +
                            "</tr>" +
                        "</thead>";

        this.recordsPerPage = "<div class='max_per_page'>" +
                                "<span>Records Per Page </span>" +
                                "<select>" +
                                    "<option value='5'>5</option>" +
                                    "<option value='10' selected>10</option>" +
                                    "<option value='30'>30</option>" +
                                    "<option value='50'>50</option>" +
                                    "<option value='100'>100</option>" +
                                "</select>" +
                                "<span id='total_records_count' style='padding-left: 30px;font-weight:bold;font-size: 13px;'></span>" +
                            "</div>";

        $("#strategyModal .modal-title").text(this.title);
        $("#strategyModal .modal-body").html(this.recordsPerPage + "<div class='strategy_table'><table>" + this.tableHeader + "<tbody></tbody></table></div>");
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            $("#strategyModal #total_records_count").html(classRef.totalRecords + " Results");

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

        var url = baseApiUrl + "/strategies?type=" + classRef.strategyType + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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
            var operatingProfitMargin = (response[i].operatingProfitMargin) ? parseFloat(response[i].operatingProfitMargin).toFixed(2) + "%" : '-';
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
            /*"<td>" + 
            "<div class='stockReturn'><button class='stockReturn' data-toggle='tooltip' title='View Stock Return'>View</button></div>" + 
            "</td>" +*/
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

        $("#strategyModal .strategy_table thead th").off().on('click', {this: classRef}, classRef.getSortedByValue);

        classRef.setRecordStats();

        $('#strategyModal .max_per_page select').off().on('change', {this: classRef}, classRef.getPerPageMaxRecords);
        // $('#strategyModal .stockReturn').off().on('click', {this: classRef}, classRef.getStockReturnData);
    },

    setFullScreen : function(event) {
        if(!isLoggedInUser()) {
            $('#strategyModal').modal('show');
            joelGreenblattStrategyObj.init();
            joelGreenblattStrategyObj.getCurrentStrategyData();
        } else {
            inner_login('view/celebrity-investors-strategies.jsp');
        }
    }, 

    getPerPageMaxRecords: function(event) {
        var classRef = event.data.this;

        if(classRef.perPageMaxRecords !== Number($(this).val())) {
            classRef.pageNumber = 1;
            classRef.firstPageNumber = 1;
            classRef.lastPageNumber = 1;
            classRef.currentIndex = 1;
        }

        classRef.perPageMaxRecords = Number($(this).val());
        console.log("perPageMaxRecords: " + classRef.perPageMaxRecords);
        joelGreenblattStrategyObj.getCurrentStrategyData();
    },

    getSortedByValue: function(event) {
        var classRef = event.data.this;

        if($(this).attr('data-id') == undefined) {
            return;
        }
        
        if($(this).attr('data-id') == classRef.sortByValue) {
            
            if(classRef.orderBy == "desc") {
                classRef.orderBy = "asc";
            } else if(classRef.orderBy == "asc") {
                classRef.orderBy = "desc";
            }
        } else {
            classRef.orderBy = "desc";
        }

        classRef.sortByValue = $(this).attr('data-id');
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
    },

    getStockReturnApi: function(parentNode) {

        var isinCode = $(parentNode).parents("tr").attr("data-code");

        var jsonData = {
            "isinCode": isinCode
        };

        var url = "/api/stockReturns";
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

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
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send(JSON.stringify(jsonData));
        });
    },

    getStockReturnData: function(event) {
        var classRef = event.data.this;;

        if(!isLoggedInUser()) {
            var parentNode = $(event.currentTarget).parent();

            parentNode.html("<img src='../resources/images/bx_loader.gif' />");
            
            classRef.getStockReturnApi(parentNode).then(function(response) {
                var response = JSON.parse(response);
                classRef.setStockReturnHtml(parentNode, response);
            }, function(error) {
                console.log("Unable to get stock Return");
            });
        } else {
            inner_login('view/celebrity-investors-strategies.jsp');
        }
    },

    setStockReturnHtml: function(parentNode, response) {
        var stockData = response.data;
        var m3StockReturn = (stockData["3M"] != '-') ? parseFloat(stockData["3M"]).toFixed(2) + "%" : stockData["3M"];
        var m6StockReturn = (stockData["6M"] != '-') ? parseFloat(stockData["6M"]).toFixed(2) + "%" : stockData["6M"];
        var y1StockReturn = (stockData["1Y"] != '-') ? parseFloat(stockData["1Y"]).toFixed(2) + "%" : stockData["1Y"];

        var m3StockReturnClass = "success";
        if(response.data["3M"] > 0) {
            m3StockReturnClass = "success";
        } else if(response.data["3M"] < 0) {
            m3StockReturnClass = "danger";
        } else {
            m3StockReturnClass = "";
        }

        var m6StockReturnClass = "success";
        if(response.data["6M"] > 0) {
            m6StockReturnClass = "success";
        } else if(response.data["6M"] < 0) {
            m6StockReturnClass = "danger";
        } else {
            m6StockReturnClass = "";
        }

        var y1StockReturnClass = "success";
        if(response.data["1Y"] > 0) {
            y1StockReturnClass = "success";
        } else if(response.data["1Y"] < 0) {
            y1StockReturnClass = "danger";
        } else {
            y1StockReturnClass = "";
        }

        var html = "<ul>" +
        "<li class='" + m3StockReturnClass + "'><strong>" + m3StockReturn + "</strong></li>" +
        "<li class='" + m6StockReturnClass + "'><strong>" + m6StockReturn + "</strong></li>" +
        "<li class='" + y1StockReturnClass + "'><strong>" + y1StockReturn + "</strong></li>" +
        "</ul>";
        $(parentNode).empty();
        $(parentNode).html(html);
    }

};

kennithFisherStrategyObj.init();
benjaminGrahanStrategyObj.init();
finvendorPickStrategyObj.init();
martinZweigStrategyObj.init();
jamesOshaughnessyStrategyObj.init();
joelGreenblattStrategyObj.init();


$("#strategyModal button").on('click', function() {
    $("#strategyModal").hide();
    $("#strategyModal .modal-body").html('');
});

$('#kennith_fisher_strategy .view_btn button').on('click', kennithFisherStrategyObj.setFullScreen);
$('#benjamin_graham_strategy .view_btn button').on('click', benjaminGrahanStrategyObj.setFullScreen);
$("#finvendor_pick_strategy .view_btn button").on('click', finvendorPickStrategyObj.setFullScreen);
$("#marting_zweigs_strategy .view_btn button").on('click', martinZweigStrategyObj.setFullScreen);
$('#james_o_shaughessys_strategy .view_btn button').on('click', jamesOshaughnessyStrategyObj.setFullScreen);
$("#joel_greenblatts_strategy .view_btn button").on('click', joelGreenblattStrategyObj.setFullScreen);