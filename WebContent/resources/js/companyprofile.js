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



    var getEquityListHtml = function(response) {
        var len = response.equityResearch.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#broker_table tbody").html("<tr><td colspan='6'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

            var recommTypeClass = "label-warning";
            if(response.equityResearch[i].recommType == "buy" || response.equityResearch[i].recommType == "accumulate"
                || response.equityResearch[i].recommType == "overweight"  || response.equityResearch[i].recommType == "bullish") {
                recommTypeClass = "label-success";
            } else if(response.equityResearch[i].recommType == "sell" || response.equityResearch[i].recommType == "underweight"
                || response.equityResearch[i].recommType == "reduce"  || response.equityResearch[i].recommType == "bearish") {
                recommTypeClass = "label-danger";
            } else {
                recommTypeClass = "label-warning";
            }


            var _3YrPatGrowthClass = "success";
            var _3YrPatGrowthClass_Caret = "fa-caret-up";
            if(response.equityResearch[i]._3YrPatGrowth > 0) {
                _3YrPatGrowthClass = "success";
                _3YrPatGrowthClass_Caret = "fa-caret-up";
            } else {
                _3YrPatGrowthClass = "danger";
                _3YrPatGrowthClass_Caret = "fa-caret-down";
            }

            var upsideClass = "success";
            if(response.equityResearch[i].upside > 0) {
                upsideClass = "success";
            } else {
                upsideClass = "danger";
            }

            var brokerRankGenericStarClass = "<i class='fa fa-star default'></i>";
            var brokerRankLargeCapStarClass = "<i class='fa fa-star'></i>";

            var brokerRankLargeCapStarHtml = '';
            if(response.equityResearch[i].brokerRank.largeCap === "5") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass;
            } else if(response.equityResearch[i].brokerRank.largeCap === "4") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.largeCap === "3") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.largeCap === "2") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.largeCap === "1") {
                brokerRankLargeCapStarHtml = brokerRankLargeCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else {
                brokerRankLargeCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            }


            var brokerRankMidCapStarClass = "<i class='fa fa-star'></i>";
            var brokerRankMidCapStarHtml = '';
            if(response.equityResearch[i].brokerRank.midCap === "5") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass;
            } else if(response.equityResearch[i].brokerRank.midCap === "4") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.midCap === "3") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.midCap === "2") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.midCap === "1") {
                brokerRankMidCapStarHtml = brokerRankMidCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else {
                brokerRankMidCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            }


            var brokerRankSmallCapStarClass = "<i class='fa fa-star'></i>";
            var brokerRankSmallCapStarHtml = '';
            if(response.equityResearch[i].brokerRank.smallCap === "5") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass;
            } else if(response.equityResearch[i].brokerRank.smallCap === "4") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.smallCap === "3") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.smallCap === "2") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else if(response.equityResearch[i].brokerRank.smallCap === "1") {
                brokerRankSmallCapStarHtml = brokerRankSmallCapStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            } else {
                brokerRankSmallCapStarHtml = brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass + brokerRankGenericStarClass;
            }

            htmlCode = htmlCode + "<tr data-id='" + response.equityResearch[i].productId + "'>" +
            "<td>" + 
            "<div class='company' data-toggle='tooltip' title='See all reports for " + response.equityResearch[i].company + "'>" + response.equityResearch[i].company + "</div>" + 
            "<div class='style'>" + response.equityResearch[i].style + "</div>" + 
            "<div class='mcap'>" + response.equityResearch[i].mcap + "</div>" + 
            "<div class='sector'>" + response.equityResearch[i].sector + "</div>" +
            "</td>" + 
            "<td>" + 
            "<div class='broker' data-toggle='tooltip' title='See all reports published by " + response.equityResearch[i].broker + "'>" + response.equityResearch[i].broker + "</div>" + 
            "<div class='since'>" + response.equityResearch[i].since + "</div>" + 
            "<div class='awarded'>" + response.equityResearch[i].awarded + "</div>" + 
            "<div class='researchedByCfa'>" + response.equityResearch[i].researchedByCfa + "</div>" +
            "</td>" +
            "<td>" + 
            "<div class='brokerRankLargeCap warning' data-toggle='tooltip' title='Large Cap'>" + brokerRankLargeCapStarHtml + "</div>" + 
            "<div class='brokerRankMidCap warning' data-toggle='tooltip' title='Mid Cap'>" + brokerRankMidCapStarHtml + "</div>" + 
            "<div class='brokerRankSmallCap warning' data-toggle='tooltip' title='Small Cap'>" + brokerRankSmallCapStarHtml + "</div>" +
            "</td>" +
            "<td>" + 
            "<div class='cmp'> Rs. " + response.equityResearch[i].cmp + "</div>" + 
            "<div class='priceDate'>" + timeStampToDate(Number(response.equityResearch[i].priceDate)) + "</div>" + 
            "<div class='pe'>" + response.equityResearch[i].pe + "</div>" + 
            "<div class='_3YrPatGrowth " + _3YrPatGrowthClass + "'><i class='fa " + _3YrPatGrowthClass_Caret + "'></i> " + ((response.equityResearch[i]._3YrPatGrowth != 'NA') ? Math.round(response.equityResearch[i]._3YrPatGrowth * 100) / 100 + '%' : response.equityResearch[i]._3YrPatGrowth) + "</div>" +
            "</td>" +
            "<td>" + 
            "<div class='recommType " + recommTypeClass + "'>" + response.equityResearch[i].recommType + "</div>" + 
            "<div class='targetPrice'> Rs. " + response.equityResearch[i].targetPrice + "</div>" + 
            "<div class='priceAtRecomm'>" + ((response.equityResearch[i].priceAtRecomm == '') ? "N/A" : response.equityResearch[i].priceAtRecomm) + "</div>" + 
            "<div class='upside " + upsideClass + "'>" + ((response.equityResearch[i].upside != 'NA') ? Math.round(response.equityResearch[i].upside * 100) / 100 + '%' : response.equityResearch[i].upside) + "</div>" +
            "</td>" +
            "<td>"  +  
            "<div class='report' target=''><a href='research-company-report.jsp' data-toggle='tooltip' title='Go to report post' data-vendor='" + response.equityResearch[i].vendorName + "'><i class='fa fa-file'></i></a></div>" +
            "<div class='researchDate'>" + timeStampToDate(Number(response.equityResearch[i].researchDate)) + "</div>" +
            "<div class='analystName' data-toggle='tooltip' title='" + response.equityResearch[i].analystName + "'>" + response.equityResearch[i].analystName + "</div>" + 
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

        getCompanyRecordStats(researchType, jsonData, perPageMaxRecords).then(function(stats) {
            stats = JSON.parse(stats);
            firstPageNumber = stats.firstPageNumber;
            lastPageNumber = stats.lastPageNumber;
            totalRecords = stats.totalRecords;
            $("#total_records_count").html(totalRecords + " Results");
            //perPageMaxRecords = Math.ceil(totalRecords / lastPageNumber);
            console.log("pageNumber: " + pageNumber);
            getCompanyResearchReport(researchType, jsonData, pageNumber).then(function(serverResponse) {
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
    function getCompanyRecordStats(researchType, jsonData, perPageMaxRecords) {

        var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

        var url = "/system/api/companyrecordstats?type=" + researchType + "&isinCode=" + companyProfileJson.isinCode + "&perPageMaxRecords=" + perPageMaxRecords;
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

            httpRequest.send(jsonData);
        });
    };


function getCompanyProfileResearchReportLoad() {

    getCompanyProfile().then(function(data) {
        isProgressLoader(false);
        response = JSON.parse(data);

        var cmp_last_change_class = "success";
        var cmp_last_change_caret = "fa-caret-up";

        var lastChange = response.companyProfileData.cmp;

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

var countryId = 1;

var jsonData = JSON.stringify({
  geo: countryId
});


function getCompanyProfile() {
    isProgressLoader(true);

    var companyProfileJson = window.localStorage.getItem("companyProfileJson");
    var jsonData = JSON.stringify({
      geo: '1'
    });

    companyProfileJson = JSON.parse(companyProfileJson);
    var isinCode = companyProfileJson.isinCode;

    var url = "/system/api/companyprofile?isinCode=" + isinCode;
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


function getCompanyResearchReport(researchType, jsonData, perPageMaxRecords) {
    isProgressLoader(true);

    var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

    var url = "/system/api/companyresearchreport?isinCode=" + companyProfileJson.isinCode + "&type=" + researchType + "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortByValue + "&orderBy=" + orderBy;
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
}

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