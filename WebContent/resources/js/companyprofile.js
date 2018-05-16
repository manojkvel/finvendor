var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;

var companyProfileObj = {};

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



    var getEquityListHtml = function(response) {
        var len = response.equity.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#broker_table tbody").html("<tr><td colspan='6'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

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

            var brokerRankGenericStarClass = "<i class='fa fa-star default'></i>";
            var brokerRankLargeCapStarClass = "<i class='fa fa-star'></i>";

            var brokerRankLargeCapStarHtml = '';
            if(response.equity[i].brokerRank.largeCap === "5") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass;
            } else if(response.equity[i].brokerRank.largeCap === "4") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.largeCap === "3") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.largeCap === "2") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.largeCap === "1") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else {
                brokerRankLargeCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            }


            var brokerRankMidCapStarClass = "<i class='fa fa-star'></i>";
            var brokerRankMidCapStarHtml = '';
            if(response.equity[i].brokerRank.midCap === "5") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass;
            } else if(response.equity[i].brokerRank.midCap === "4") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.midCap === "3") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.midCap === "2") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.midCap === "1") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else {
                brokerRankMidCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            }


            var brokerRankSmallCapStarClass = "<i class='fa fa-star'></i>";
            var brokerRankSmallCapStarHtml = '';
            if(response.equity[i].brokerRank.smallCap === "5") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass;
            } else if(response.equity[i].brokerRank.smallCap === "4") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.smallCap === "3") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.smallCap === "2") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equity[i].brokerRank.smallCap === "1") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else {
                brokerRankSmallCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            }

            htmlCode = htmlCode + "<tr data-id='" + response.equity[i].productId + "'>" +
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
            "<div class='brokerRankLargeCap warning' data-toggle='tooltip' title='Large Cap'>" + brokerRankLargeCapStarHtml + "</div>" + 
            "<div class='brokerRankMidCap warning' data-toggle='tooltip' title='Mid Cap'>" + brokerRankMidCapStarHtml + "</div>" + 
            "<div class='brokerRankSmallCap warning' data-toggle='tooltip' title='Small Cap'>" + brokerRankSmallCapStarHtml + "</div>" +
            "</td>" +
            "<td>" + 
            "<div class='cmp'> Rs. " + response.equity[i].cmp + "</div>" + 
            "<div class='priceDate'>" + timeStampToDate(Number(response.equity[i].priceDate)) + "</div>" + 
            "<div class='pe'>" + response.equity[i].pe + "</div>" + 
            "<div class='_3YrPatGrowth " + _3YrPatGrowthClass + "'><i class='fa " + _3YrPatGrowthClass_Caret + "'></i> " + ((response.equity[i]._3YrPatGrowth != 'NA') ? Math.round(response.equity[i]._3YrPatGrowth * 100) / 100 + '%' : response.equity[i]._3YrPatGrowth) + "</div>" +
            "</td>" +
            "<td>" + 
            "<div class='recommType " + recommTypeClass + "'>" + response.equity[i].recommType + "</div>" + 
            "<div class='targetPrice'> Rs. " + response.equity[i].targetPrice + "</div>" + 
            "<div class='priceAtRecomm'>" + ((response.equity[i].priceAtRecomm == '') ? "N/A" : response.equity[i].priceAtRecomm) + "</div>" + 
            "<div class='upside " + upsideClass + "'>" + ((response.equity[i].upside != 'NA') ? Math.round(response.equity[i].upside * 100) / 100 + '%' : response.equity[i].upside) + "</div>" +
            "</td>" +
            "<td>"  +  
            "<div class='report' target=''><a href='research-company-report.jsp' data-toggle='tooltip' title='Go to report post' data-vendor='" + response.equity[i].vendorName + "'><i class='fa fa-file'></i></a></div>" +
            "<div class='researchDate'>" + timeStampToDate(Number(response.equity[i].researchDate)) + "</div>" +
            "<div class='analystName' data-toggle='tooltip' title='" + response.equity[i].analystName + "'>" + response.equity[i].analystName + "</div>" + 
            "</td>" +
            "</tr>";
        }

        $("#research_report_content tbody").html(htmlCode);

        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#research_report_content").append(paginationHtml);

        $('[data-toggle="tooltip"]').tooltip();
        
        $('#broker_table tbody tr td .report a').on('click', getReport);
        $('#research_report_content .pager a').on('click', getPaginationIndex);

        $("#average_target_price span").text(response.averageTargetPrice);
        $("#no_of_analyst_report span").text(response.noOfAnalystReport);

        $("#total_buy_recomm span").text((response.totalBuyRecomm/response.noOfAnalystReport)*100+ "%");

        $("#total_neutral_recomm span").text((response.totalNeutralRecomm/response.noOfAnalystReport)*100+ "%");

        $("#total_sell_recomm span").text((response.totalSellRecomm/response.noOfAnalystReport)*100+ "%");


        setRecordStats(currentIndex, lastPageNumber);
    }

    var firstPageNumber = 1;
    var pageNumber = 1;
    var lastPageNumber = 1;
    var totalRecords = 1;
    var currentIndex = 1;
    var perPageMaxRecords = 5;
    var sortByValue = 'researchDate';
    var orderBy = 'desc';

    var setRecordStats = function(currentIndex, lastPageNumber) {
        if(currentIndex > lastPageNumber) {
            currentIndex = lastPageNumber;
        }
        $("#records_stats").html(currentIndex + " of " + lastPageNumber);
    }

    var resetPaginationCount = function() {
        firstPageNumber = 1;
        pageNumber = 1;
        lastPageNumber = 1;
        totalRecords = 1;
        currentIndex = 1;
    }
    
    var getPerPageMaxRecords = function() {
        if(perPageMaxRecords !== Number($(this).val())) {
            pageNumber = 1;
            firstPageNumber = 1;
            lastPageNumber = 1;
            currentIndex = 1;
        }

        perPageMaxRecords = Number($(this).val());
        console.log("perPageMaxRecords: " + perPageMaxRecords);
        loadDefaultResearchReport('equity', perPageMaxRecords);
    }
    $('#research_report_content .max_per_page select').on('change', getPerPageMaxRecords);

    var getSortedByValue = function() {
        
        if($(this).attr('data-id') == undefined) {
            return;
        }
        
        if($(this).attr('data-id') == sortByValue) {
            
            if(orderBy == "desc") {
                orderBy = "asc";
            } else if(orderBy == "asc") {
                orderBy = "desc";
            }
        } else {
            orderBy = "desc";
        }

        sortByValue = $(this).attr('data-id');
        loadDefaultResearchReport('equity', perPageMaxRecords);
    }
    $('#broker_table thead a').on('click', getSortedByValue);

    var getPaginationIndex = function() {
        var currentNode = $(this).attr('id');
        if(currentNode == 'last') {
            getLastPageResearchReport(pageNumber);
        } else if(currentNode == 'next') {
            getNextPageResearchReport(pageNumber);
        } else if(currentNode == 'prev') {
            getPreviousPageResearchReport(pageNumber);
        } else if(currentNode == 'first') {
            getFirstPageResearchReport(pageNumber);
        }
    }

    var getFirstPageResearchReport = function(currentNumber) {
        if(currentNumber != firstPageNumber) {
            pageNumber = firstPageNumber;
            currentIndex = firstPageNumber;
            loadDefaultResearchReport('equity', perPageMaxRecords);
        }
    };

    var getLastPageResearchReport = function(currentNumber) {
        if(currentNumber != lastPageNumber) {
            pageNumber = lastPageNumber;
            currentIndex = (pageNumber - 1) * perPageMaxRecords + 1;
            loadDefaultResearchReport('equity', perPageMaxRecords);
        }
    };

    var getNextPageResearchReport = function(currentNumber) {
        if(currentNumber < lastPageNumber) {
            pageNumber = currentNumber + 1;
            currentIndex = currentIndex + perPageMaxRecords;
            loadDefaultResearchReport('equity', perPageMaxRecords);
        }
    };

    var getPreviousPageResearchReport = function(currentNumber) {
        if(currentNumber > 1) {
            pageNumber = currentNumber - 1;
            currentIndex = currentIndex - perPageMaxRecords;
            loadDefaultResearchReport('equity', perPageMaxRecords);
        }
    };

    function loadDefaultResearchReport(researchType, perPageMaxRecords) {
        isProgressLoader(true);

        getCompanyRecordStats(researchType, perPageMaxRecords).then(function(stats) {
            stats = JSON.parse(stats);
            firstPageNumber = stats.firstPageNumber;
            lastPageNumber = stats.lastPageNumber;
            totalRecords = stats.totalRecords;
            $("#total_records_count").html(totalRecords + " Results");
            //perPageMaxRecords = Math.ceil(totalRecords / lastPageNumber);
            console.log("pageNumber: " + pageNumber);
            getCompanyResearchReport(researchType, pageNumber).then(function(serverResponse) {
                //console.log(serverResponse);
                $("#research_report_content .paging_container").remove();
                var response = JSON.parse(serverResponse);
                getEquityListHtml(response);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
            });
        }, function(error) {

        });
    };

    var getReport = function(e) {
        var vendorName = $(this).attr("data-vendor");
        var productId = $(this).parents('tr').attr('data-id');
        //console.log(productId);
        var dasboardReportJson = {
            equitysearchjson : window.localStorage.getItem("equitysearchjson"),
            productId : productId,
            vendorName : vendorName,
            pageNumber : pageNumber,
            perPageMaxRecords : perPageMaxRecords,
            sortByValue : sortByValue,
            orderBy : orderBy
        }
        window.localStorage.setItem('dasboardReportJson', JSON.stringify(dasboardReportJson));
    };

    /**
     * Function to start async call to get record stats
     */
    function getCompanyRecordStats(researchType, perPageMaxRecords) {

        var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

        var url = "/system/api/recordstats/reaseacharea?id=1&geo=1&isinCode=" + companyProfileJson.isinCode + "&perPageMaxRecords=" + perPageMaxRecords;
        return new Promise(function(resolve, reject) {
            var httpRequest = new XMLHttpRequest({
                mozSystem: true
            });

            httpRequest.timeout = API_TIMEOUT_SMALL;
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
                        console.log(httpRequest.status + httpRequest.responseText);
                        reject(httpRequest.responseText);
                    }
                } else {
                }
            };

            httpRequest.send();
        });
    };


function getCompanyProfileResearchReportLoad() {

    getCompanyProfile().then(function(data) {
        isProgressLoader(false);
        response = JSON.parse(data);


        companyProfileObj['companyId'] = response.companyProfileData.companyId;
        companyProfileObj['companyName'] = response.companyProfileData.companyName;
        companyProfileObj['cmp'] = response.companyProfileData.cmp;

        var cmp_last_change_class = "success";
        var cmp_last_change_caret = "fa-caret-up";

        var lastChange = response.companyProfileData.lastChangedCmpInPercentage;

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

        $(".company_details .c_name").text(response.companyProfileData.companyName);
        $(".company_details .ind_name").text(response.companyProfileData.industry);
        $(".company_details .mcap_name").text(response.companyProfileData.mcap);

        var lastCmp = response.companyProfileData.cmp + " (<i class='fa " + cmp_last_change_caret + "'></i> "  + lastChange + "%)";
        $(".company_details .last_cmp").addClass(cmp_last_change_class);
        $(".company_details .last_cmp").html(lastCmp);

        $("#summary_content .summary").text(response.summary);

    }, function(error) {

    });
}


function getCompanyProfile() {
    isProgressLoader(true);

    var companyProfileJson = window.localStorage.getItem("companyProfileJson");

    companyProfileJson = JSON.parse(companyProfileJson);
    var isinCode = companyProfileJson.isinCode;

    var url = "/system/api/profile/researcharea?id=1&geo=1&isinCode=" + isinCode;
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


function getCompanyResearchReport(researchType, pageNumber) {
    isProgressLoader(true);

    var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

    var url = "/system/api/researchreport/reaseacharea?id=1&geo=1&isinCode=" + companyProfileJson.isinCode + "&type=" + researchType + "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortByValue + "&orderBy=" + orderBy;
    return new Promise(function(resolve, reject) {
        var httpRequest = new XMLHttpRequest({
            mozSystem: true
        });
        httpRequest.timeout = API_TIMEOUT_SMALL;
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
                    console.log(httpRequest.status + httpRequest.responseText);
                    reject(httpRequest.responseText);
                }
            } else {
            }
        };

        httpRequest.send();
    });
}

function addToMarketWatchlist() {
    addToMarketWatchlistAPI().then(function(response) {
        isProgressLoader(false);
        $("#addToWatchlist .alert").removeClass('alert-danger');
        $("#addToWatchlist .alert span").text(JSON.parse(response).messge);
    }, function(error) {
        isProgressLoader(false);
        $("#addToWatchlist .alert").addClass('alert-danger');
        $("#addToWatchlist .alert span").text(JSON.parse(error).messge);

    });
}

function addToMarketWatchlistAPI() {
    
    isProgressLoader(true);

    var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));
    companyProfileObj['userName'] = 'amit_vendor';

    var url = "/system/api/watchlist/researcharea?id=1";
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
                if (httpRequest.status === 201) {
                    resolve(httpRequest.response);
                } else {
                    console.log(httpRequest.status + httpRequest.responseText);
                    reject(httpRequest.responseText);
                }
            } else {
            }
        };

        httpRequest.send(JSON.stringify(companyProfileObj));
    });
}

$("#company_profile .profile_details.func_details button").eq(0).on('click', addToMarketWatchlist);

function setPriceAlert() {
    var dayMinPrice = $("#setPriceAlert input[name=day_min_price]").val();
    var dayMaxPrice = $("#setPriceAlert input[name=day_max_price]").val();
    var weekMinPrice = $("#setPriceAlert input[name=week_min_price]").val();
    var weekMaxPrice = $("#setPriceAlert input[name=week_max_price]").val();
    var monthMinPrice = $("#setPriceAlert input[name=month_min_price]").val();
    var monthMaxPrice = $("#setPriceAlert input[name=month_max_price]").val();
    var isResearchReport = $("#setPriceAlert input[name=alert_new_research_report]").prop('checked');
    var alertJsonObj = {
        "companyId": companyProfileObj.companyId,
        "companyName": companyProfileObj.companyName,
        "userName": "test_vendor",
        "dayMinPrice": dayMinPrice,
        "dayMaxPrice": dayMaxPrice,
        "weekMinPrice": weekMinPrice,
        "weekMaxPrice": weekMaxPrice,
        "monthMinPrice": monthMinPrice,
        "monthMaxPrice": monthMaxPrice,
        "isResearchReport": isResearchReport
    };

    setPriceAlertAPI(alertJsonObj).then(function(resolve){

    }, function(error) {

    });
}


function setPriceAlertAPI(alertJsonObj) {
    
    isProgressLoader(true);

    var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));
    companyProfileObj['userName'] = 'amit_vendor';



    var url = "/system/api/alert/researcharea?id=1";
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
                if (httpRequest.status === 201) {
                    resolve(httpRequest.response);
                } else {
                    console.log(httpRequest.status + httpRequest.responseText);
                    reject(httpRequest.responseText);
                }
            } else {
            }
        };

        httpRequest.send(JSON.stringify(alertJsonObj));
    });
} 

$("#setPriceAlert button[name=set_alert_btn]").on('click', setPriceAlert);


jQuery(document).ready(function() {

    getTabbedContent= function () {
        $("#company_profile .subheader ul li").removeClass('active');
        $("#company_profile .subheader ul li").removeClass('active');
        $(this).parent().addClass('active');

        var enableSubContainer = $(this).attr("data-tab");
        $("#company_profile .subheader_content > div").hide();
        $("#company_profile .subheader_content div#" + enableSubContainer).show();
        //alert(this.hash.substr(1, this.hash.length));

        if(enableSubContainer == "research_report_content") {
            loadDefaultResearchReport('equity', perPageMaxRecords);
        }
    };

    $("#company_profile .subheader ul li a").on('click', getTabbedContent);
});