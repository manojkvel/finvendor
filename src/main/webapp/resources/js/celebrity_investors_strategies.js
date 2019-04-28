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
** Benjamin Graham Strategies Feed
*/

var benjaminGrahanStrategyObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 1;
        this.strategyType = "BENJAMIN_GRAHAM";
        this.getCurrentStrategyData();
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#benjamin_graham_strategy .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#benjamin_graham_strategy tbody").html("<tr><td colspan='7'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
                $("#benjamin_graham_strategy tbody").html("<tr><td colspan='7'>We are not able to get the info, please try again later.</td></tr>");
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
            $("#benjamin_graham_strategy tbody").html("<tr><td colspan='7'>No Matching Records Found</td></tr>");
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

        $("#benjamin_graham_strategy tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#benjamin_graham_strategy .startegy_content_table").append(paginationHtml);

        $('#benjamin_graham_strategy .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);


        $('#benjamin_graham_strategy .view_btn a').on('click', classRef.setFullScreen);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        $("#celebrity_investors_strategies ol > li").css({
            "width": "33%"
        });

        $("#celebrity_investors_strategies .startegy_content_table").hide();
        $("#celebrity_investors_strategies .view_btn").show();

        $(this).parents("li").css({
            "width": "100%",
            "transition": "1s width"
        });

        $(this).parents(".view_btn").hide();
        $(this).parents("li").find(".startegy_content_table").show();
    },  

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#benjamin_graham_strategy #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
** Kennith Fisher Strategies Feed
*/

var kennithFisherStrategyObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 1;
        this.strategyType = "KENNITH_FISHER";
        this.getCurrentStrategyData();
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#kennith_fisher_strategy .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#kennith_fisher_strategy tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
                $("#kennith_fisher_strategy tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
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
            $("#kennith_fisher_strategy tbody").html("<tr><td colspan='9'>No Matching Records Found</td></tr>");
            return;
        }
        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var annualRevenue = (response[i].annualRevenue) ? parseFloat(response[i].annualRevenue).toFixed(2) : '-';
            var de = (response[i].de) ? parseFloat(response[i].de).toFixed(2) : '-';
            var inflationRate = (response[i].inflationRate) ? parseFloat(response[i].inflationRate).toFixed(2) : '-';
            var mcap = (response[i].mcap) ? response[i].mcap : '-';
            var psr = (response[i].psr) ? parseFloat(response[i].psr).toFixed(2) : '-';
            var rndExpenditures = (response[i].rndExpenditures) ? parseFloat(response[i].rndExpenditures).toFixed(2) : '-';
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

        $("#kennith_fisher_strategy tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#kennith_fisher_strategy .startegy_content_table").append(paginationHtml);

        $('#kennith_fisher_strategy .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);

        $('#kennith_fisher_strategy .view_btn a').on('click', classRef.setFullScreen);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        $("#celebrity_investors_strategies ol > li").css({
            "width": "33%"
        });

        $("#celebrity_investors_strategies .startegy_content_table").hide();
        $("#celebrity_investors_strategies .view_btn").show();

        $(this).parents("li").css({
            "width": "100%",
            "transition": "1s width"
        });

        $(this).parents(".view_btn").hide();
        $(this).parents("li").find(".startegy_content_table").show();
    }, 

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#kennith_fisher_strategy #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
        this.perPageMaxRecords = 1;
        this.strategyType = "JAMES_SHAUGHNESSY";
        this.getCurrentStrategyData();
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#james_o_shaughessys_strategy .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#james_o_shaughessys_strategy tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
                $("#james_o_shaughessys_strategy tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
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
            $("#james_o_shaughessys_strategy tbody").html("<tr><td colspan='9'>No Matching Records Found</td></tr>");
            return;
        }
        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var cmp = (response[i].cmp) ? parseFloat(response[i].cmp).toFixed(2) : '-';
            var eps = (response[i].eps) ? parseFloat(response[i].eps).toFixed(2) : '-';
            var mcap = (response[i].mcap) ? response[i].mcap : '-';
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

        $("#james_o_shaughessys_strategy tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#james_o_shaughessys_strategy .startegy_content_table").append(paginationHtml);

        $('#james_o_shaughessys_strategy .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);

        $('#james_o_shaughessys_strategy .view_btn a').on('click', classRef.setFullScreen);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        $("#celebrity_investors_strategies ol > li").css({
            "width": "33%"
        });

        $("#celebrity_investors_strategies .startegy_content_table").hide();
        $("#celebrity_investors_strategies .view_btn").show();

        $(this).parents("li").css({
            "width": "100%",
            "transition": "1s width"
        });

        $(this).parents(".view_btn").hide();
        $(this).parents("li").find(".startegy_content_table").show();
    }, 

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#james_o_shaughessys_strategy #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
        this.perPageMaxRecords = 1;
        this.strategyType = "JOEL_GREENBLATT";
        this.getCurrentStrategyData();
    },

    getCurrentStrategyData: function() {
        var classRef = this;
        classRef.getCurrentStrategyRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCurrentStrategy().then(function(serverResponse) {
                $("#joel_greenblatts_strategy .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCurrentStrategyHtml(serverResponse);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#joel_greenblatts_strategy tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
                $("#joel_greenblatts_strategy tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
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
            $("#joel_greenblatts_strategy tbody").html("<tr><td colspan='9'>No Matching Records Found</td></tr>");
            return;
        }
        for(var i = 0; i < len; i++) {

            var companyName = (response[i].companyName) ? response[i].companyName : '-';
            var cashAndCashEquiv = (response[i].cashAndCashEquiv) ? parseFloat(response[i].cashAndCashEquiv).toFixed(2) : '-';
            var mcap = (response[i].mcap) ? response[i].mcap : '-';
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

        $("#joel_greenblatts_strategy tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#joel_greenblatts_strategy .startegy_content_table").append(paginationHtml);

        $('#joel_greenblatts_strategy .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);

        $('#joel_greenblatts_strategy .view_btn a').on('click', classRef.setFullScreen);

        classRef.setRecordStats();
    },

    setFullScreen : function(event) {
        $("#celebrity_investors_strategies ol > li").css({
            "width": "33%"
        });

        $("#celebrity_investors_strategies .startegy_content_table").hide();
        $("#celebrity_investors_strategies .view_btn").show();

        $(this).parents("li").fadeIn();

        $(this).parents(".view_btn").hide();
        $(this).parents("li").find(".startegy_content_table").fadeIn(2000, 'swing');
    }, 

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#joel_greenblatts_strategy #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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



benjaminGrahanStrategyObj.init();
kennithFisherStrategyObj.init();
jamesOshaughnessyStrategyObj.init();
joelGreenblattStrategyObj.init();