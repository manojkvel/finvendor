var API_TIMEOUT_SMALL = 30*1000;
var API_TIMEOUT_LARGE = 3*60*1000;

var companyProfileObj = {};
var priceAlertStatus = 'N';
var baseApiUrl = "/api/";

    var timeStampToDate = function (ts) {
        if (ts) {
            ts = new Date(ts).toString();
            ts = ts.split(' ').slice(0, 8);
            ts = /*ts[0] + " " + */ ts[1] + " " + ts[2] + ", " + ts[3]; //+ " " + ts[4];
            //console.log(ts);
            return ts;
        } else {
            return 'NA';
        }
    };

    var timeStampToDateNew = function (ts) {
        if (ts) {
            ts = new Date(ts).toString();
            ts = ts.split(' ').slice(0, 5);
            //console.log(ts);
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


            var _3YrEpsGrowthClass = "success";
            var _3YrEpsGrowthClass_Caret = "fa-caret-up";
            if(response.equity[i]._3YrEpsGrowth > 0) {
                _3YrEpsGrowthClass = "success";
                __3YrEpsGrowthClass_Caret = "fa-caret-up";
            } else {
                _3YrEpsGrowthClass = "danger";
                _3YrEpsGrowthClass_Caret = "fa-caret-down";
            }

            var upsideClass = "success";
            if(response.equity[i].upside > 0) {
                upsideClass = "success";
            } else {
                upsideClass = "danger";
            }

            /*var brokerRankGenericStarClass = "<i class='fa fa-star default'></i>";
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
            }*/

            var currency = (response.equity[i].currency) ? response.equity[i].currency : "INR";

            htmlCode = htmlCode + "<tr data-id='" + response.equity[i].productId + "' data-code='" + response.equity[i].isinCode + "' >" +
            "<td>" + 
            "<div class='company' data-toggle='tooltip' title='See all reports for " + response.equity[i].company + "'><a href='/view/company-profile.jsp?isinCode=" + response.equity[i].isinCode + "'>" + response.equity[i].company + "</a></div>" + 
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
            "<button class='stockReturn' data-toggle='tooltip' title='View Stock Return'>View</button>" +
            "</td>" +
            /*"<td>" + 
            "<div class='brokerRankLargeCap warning' data-toggle='tooltip' title='Large Cap'>" + brokerRankLargeCapStarHtml + "</div>" + 
            "<div class='brokerRankMidCap warning' data-toggle='tooltip' title='Mid Cap'>" + brokerRankMidCapStarHtml + "</div>" + 
            "<div class='brokerRankSmallCap warning' data-toggle='tooltip' title='Small Cap'>" + brokerRankSmallCapStarHtml + "</div>" +
            "</td>" +*/
            "<td>" + 
            "<div class='cmp'>" + currency + " " + parseFloat(response.equity[i].cmp).toFixed(2) + "</div>" +
            "<div class='priceDate'>" + timeStampToDate(Number(response.equity[i].priceDate)) + "</div>" + 
            "<div class='pe'>" + parseFloat(response.equity[i].pe).toFixed(2) + "</div>" + 
            "<div class='_3YrEpsGrowth " + _3YrEpsGrowthClass + "'><i class='fa " + _3YrEpsGrowthClass_Caret + "'></i> " + ((response.equity[i]._3YrEpsGrowth != 'NA') ? Math.round(response.equity[i]._3YrEpsGrowth * 100) / 100 + '%' : response.equity[i]._3YrEpsGrowth) + "</div>" +
            "</td>" +
            "<td>" + 
            "<div class='recommType " + recommTypeClass + "'>" + response.equity[i].recommType + "</div>" + 
            "<div class='targetPrice'>" + currency + " " + parseFloat(response.equity[i].targetPrice).toFixed(2) + "</div>" +
            "<div class='priceAtRecomm'>" + currency + " " + ((response.equity[i].priceAtRecomm == '') ? "N/A" : parseFloat(response.equity[i].priceAtRecomm).toFixed(2)) + "</div>" +  
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

        if(response.averageTargetPrice) {
            $("#average_target_price span").text(parseFloat(response.averageTargetPrice).toFixed(2));
        } else {
            $("#average_target_price span").text('-');
        }

        response.noOfAnalystReport = totalRecords;

        $("#average_target_price span").text(parseFloat(response.averageTargetPrice).toFixed(2));
        $("#no_of_analyst_report span").text(response.noOfAnalystReport);

        $("#total_buy_recomm span").text(parseFloat((response.totalBuyRecomm/response.noOfAnalystReport)*100).toFixed(2) + "%");

        $("#total_neutral_recomm span").text(parseFloat((response.totalNeutralRecomm/response.noOfAnalystReport)*100).toFixed(2) + "%");

        $("#total_sell_recomm span").text(parseFloat((response.totalSellRecomm/response.noOfAnalystReport)*100).toFixed(2) + "%");
        $('#research_report_content .stockReturn').on('click', {this: event}, stockReturnObj.getStockReturnData);


        setRecordStats(currentIndex, lastPageNumber);
    }

    $("#research_report_content .max_per_page select").val($("#research_report_content .max_per_page select option:first").val());

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
        $("#records_stats").html(pageNumber + " of " + lastPageNumber);
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
                isProgressLoader(false);
                $("#broker_table tbody").html("<tr><td colspan='6'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
            isProgressLoader(false);
            $("#broker_table tbody").html("<tr><td colspan='6'>We are not able to get the info, please try again later.</td></tr>");
        });
    };

    var getReport = function(e) {
        var vendorName = $(this).attr("data-vendor");
        var productId = $(this).parents('tr').attr('data-id');

        localEquitySearchJson = {
            "geo": "1"
        };
        window.localStorage.setItem("equitysearchjson", JSON.stringify(localEquitySearchJson));

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

        var url = baseApiUrl + "companyprofile/recordstat?isinCode=" + companyProfileJson.isinCode + "&perPageMaxRecords=" + perPageMaxRecords;
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
    };


function getCompanyProfileResearchReportLoad() {

    getCompanyProfile().then(function(data) {
        isProgressLoader(false);
        $("#company_profile .company_profile_details").show();
        response = JSON.parse(data);


        companyProfileObj['companyId'] = response.companyProfileData.companyId;
        companyProfileObj['companyName'] = response.companyProfileData.companyName;
        companyProfileObj['cmp'] = response.companyProfileData.cmp;
        companyProfileObj['ticker'] = response.companyProfileData.ticker;

        var cmp_last_change_class = "success";
        var cmp_last_change_caret = "fa-caret-up";

        var lastChange = parseFloat(response.companyProfileData.lastChangedCmpInPercentage).toFixed(2);

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

        var patValue = parseFloat(response.companyProfileData.pat).toFixed(2);
        if(patValue > 0) {
            patValue_class = "success";
            patValue_caret = "fa-caret-up";
        } else if (patValue < 0) {
            patValue_class = "danger";
            patValue_caret = "fa-caret-down";
        } else {
            patValue_class = "neutral";
            patValue_caret = "";
        }

        var revenueValue = parseFloat(response.companyProfileData.revenue).toFixed(2);
        if(revenueValue > 0) {
            revenueValue_class = "success";
            revenueValue_caret = "fa-caret-up";
        } else if (revenueValue < 0) {
            revenueValue_class = "danger";
            revenueValue_caret = "fa-caret-down";
        } else {
            revenueValue_class = "neutral";
            revenueValue_caret = "";
        }

        var cmp = (response.companyProfileData.cmp > 0) ? parseFloat(response.companyProfileData.cmp).toFixed(2) : '-';
        var mkt_cap = (response.companyProfileData.mkt_cap > 0) ? parseFloat(response.companyProfileData.mkt_cap).toFixed(2) : '-';
        var pb = (response.companyProfileData.pb > 0 && response.companyProfileData.pb != "Infinity") ? parseFloat(response.companyProfileData.pb).toFixed(2) : '-';
        var _52w_low = (response.companyProfileData._52w_low > 0) ? parseFloat(response.companyProfileData._52w_low).toFixed(2) : '-';
        var _52w_high = (response.companyProfileData._52w_high > 0) ? parseFloat(response.companyProfileData._52w_high).toFixed(2) : '-';
        var pe =  (response.companyProfileData.pe != '-') ? parseFloat(response.companyProfileData.pe).toFixed(2) : '-';
        var beta = (response.companyProfileData.beta != '-') ? parseFloat(response.companyProfileData.beta).toFixed(2) : '-';
        var dividen_yield = (response.companyProfileData.dividen_yield != '-') ? parseFloat(response.companyProfileData.dividen_yield).toFixed(2) : '-';

        $(".company_details .c_name").text(response.companyProfileData.companyName);
        $(".company_details .ind_name").text(response.companyProfileData.industry);
        $(".company_details .mcap_name").text(response.companyProfileData.mcap);

        var lastCmp = cmp + "<span class='currency'>" + response.companyProfileData.currency + "</span>" + " (<i class='fa " + cmp_last_change_caret + "'></i> "  + lastChange + "%)";
        $(".company_details .last_cmp").addClass(cmp_last_change_class);
        $(".company_details .last_cmp").html(lastCmp);

        var price_date = timeStampToDateNew(Number(response.companyProfileData.price_date))[2] + "-" + timeStampToDateNew(Number(response.companyProfileData.price_date))[1] + "-" + timeStampToDateNew(Number(response.companyProfileData.price_date))[3];
        $(".company_details .price_date").text(response.companyProfileData.price_src_code + " | " + price_date + " | " + timeStampToDateNew(Number(response.companyProfileData.price_date))[4]);

        var indainMapCurrency = (response.companyProfileData.currency == 'INR') ? ', crores' : ', millions';
        $(".market_details #mkt_cap_value .fl span").text("(" + response.companyProfileData.currency + indainMapCurrency + ")");
        $(".market_details #revenue_value .fl span").text("(" + response.companyProfileData.currency + indainMapCurrency + ")");
        $(".market_details #pat_value .fl span").text("(" + response.companyProfileData.currency + indainMapCurrency + ")");

        $(".market_details #mkt_cap_value .fr").text(mkt_cap);
        $(".market_details #pe_value .fr").text(pe);
        $(".market_details #pb_value .fr").text(pb);
        $(".market_details #div_yield_value .fr").text(dividen_yield);
        $(".market_details #beta_value .fr").text(beta);


        //$(".market_details #revenue_value .fr").addClass(revenueValue_class);
        $(".market_details #revenue_value .fr").html(revenueValue);


        $(".market_details #face_value .fr").text(response.companyProfileData.face_value);
        $(".market_details #eps_value .fr").text(response.companyProfileData.eps_ttm);
        $(".market_details #bv_value .fr").text(response.companyProfileData.bv_share);
        $(".market_details #year_l_h_value .fr").text(_52w_low + " / " + _52w_high);
        $(".market_details #roe_value .fr").text(response.companyProfileData.roe);

        //$(".market_details #pat_value .fr").addClass(patValue_class);
        $(".market_details #pat_value .fr").html(patValue);

        $("#summary_content .summary").text(response.summary);
        
        setCompanyRatingHtml(response.companyProfileData.valuationScore);
        setStockPerfHistoryHtml(response);
        setNifty50PerfHistoryHtml(response);
        setBrokerRatingHtml(response);



        companyCalendarObj.init();
        companyNewsObj.init();
        companyCorpActionObj.init();
        companyPriceHistoryObj.init();


    }, function(error) {

    });
}

setStockPerfHistoryHtml = function(json) {
    /*var json = {
        "priceHistory":{
            "stock":{
                "1W":"4.495996",
                "1M":"-8.73393",
                "3M":"15.880057",
                "6M":"-",
                "1Y":"-",
                "2Y":"-",
                "5Y":"-"
            },
            "nifty50":{
                "1W":"-0.48656002",
                "1M":"1.9056177",
                "3M":"-7.3851233",
                "6M":"1.8901348",
                "1Y":"8.18277",
                "2Y":"44.25946",
                "5Y":"-"
            }
        }
    }*/

    var stock1W = (json.priceHistory.stock['1W'] != undefined && json.priceHistory.stock['1W'] != '-') ? parseFloat(json.priceHistory.stock['1W']).toFixed(2) + "%" : '-'
    var stock1M = (json.priceHistory.stock['1M'] != undefined && json.priceHistory.stock['1M'] != '-') ? parseFloat(json.priceHistory.stock['1M']).toFixed(2) + "%" : '-'
    var stock3M = (json.priceHistory.stock['3M'] != undefined && json.priceHistory.stock['3M'] != '-') ? parseFloat(json.priceHistory.stock['3M']).toFixed(2) + "%" : '-'
    var stock6M = (json.priceHistory.stock['6M'] != undefined && json.priceHistory.stock['6M'] != '-') ? parseFloat(json.priceHistory.stock['6M']).toFixed(2) + "%" : '-'
    var stock1Y = (json.priceHistory.stock['1Y'] != undefined && json.priceHistory.stock['1Y'] != '-') ? parseFloat(json.priceHistory.stock['1Y']).toFixed(2) + "%" : '-'



    var stock1WClass = "";
    if(json.priceHistory.stock['1W'] > 0) {
        stock1WClass = "success";
    } else if(json.priceHistory.stock['1W'] < 0) {
        stock1WClass = "danger";
    }

    var stock1MClass = "";
    if(json.priceHistory.stock['1M'] > 0) {
        stock1MClass = "success";
    } else if(json.priceHistory.stock['1M'] < 0) {
        stock1MClass = "danger";
    }

    var stock3MClass = "";
    if(json.priceHistory.stock['3M'] > 0) {
        stock3MClass = "success";
    } else if(json.priceHistory.stock['3M'] < 0) {
        stock3MClass = "danger";
    }

    var stock6MClass = "";
    if(json.priceHistory.stock['6M'] > 0) {
        stock6MClass = "success";
    } else if(json.priceHistory.stock['6M'] < 0) {
        stock6MClass = "danger";
    }

    var stock1YClass = "";
    if(json.priceHistory.stock['1Y'] > 0) {
        stock1YClass = "success";
    } else if(json.priceHistory.stock['1Y'] < 0) {
        stock1YClass = "danger";
    }

    var stockHtml = "<h3>Stock Return</h3>"
                    + "<table>"
                    + "<tr>"
                    +   "<thead>"
                    +       "<th>1W</th>"
                    +       "<th>1M</th>"
                    +       "<th>3M</th>"
                    +       "<th>6M</th>"
                    +       "<th>1Y</th>"
                    +   "</thead>"
                    + "</tr>"
                    + "<tr>"
                    +       "<td class='" + stock1WClass + "'>" + stock1W + "</td>"
                    +       "<td class='" + stock1MClass + "'>" + stock1M + "</td>"
                    +       "<td class='" + stock3MClass + "'>" + stock3M + "</td>"
                    +       "<td class='" + stock6MClass + "'>" + stock6M + "</td>"
                    +       "<td class='" + stock1YClass + "'>" + stock1Y + "</td>"
                    + "</tr>"
                    + "</table>";

    $("#perf_history_container #stock").html(stockHtml);
}

setNifty50PerfHistoryHtml = function(json) {
    /*var json = {
        "priceHistory":{
            "stock":{
                "1W":"4.495996",
                "1M":"-8.73393",
                "3M":"15.880057",
                "6M":"-",
                "1Y":"-",
                "2Y":"-",
                "5Y":"-"
            },
            "nifty50":{
                "1W":"-0.48656002",
                "1M":"1.9056177",
                "3M":"-7.3851233",
                "6M":"1.8901348",
                "1Y":"8.18277",
                "2Y":"44.25946",
                "5Y":"-"
            }
        }
    }*/

    var nifty501W = (json.priceHistory.nifty50['1W'] != undefined && json.priceHistory.nifty50['1W'] != '-') ? parseFloat(json.priceHistory.nifty50['1W']).toFixed(2) + "%" : '-'
    var nifty501M = (json.priceHistory.nifty50['1M'] != undefined && json.priceHistory.nifty50['1M'] != '-') ? parseFloat(json.priceHistory.nifty50['1M']).toFixed(2) + "%" : '-'
    var nifty503M = (json.priceHistory.nifty50['3M'] != undefined && json.priceHistory.nifty50['3M'] != '-') ? parseFloat(json.priceHistory.nifty50['3M']).toFixed(2) + "%" : '-'
    var nifty506M = (json.priceHistory.nifty50['6M'] != undefined && json.priceHistory.nifty50['6M'] != '-') ? parseFloat(json.priceHistory.nifty50['6M']).toFixed(2) + "%" : '-'
    var nifty501Y = (json.priceHistory.nifty50['1Y'] != undefined && json.priceHistory.nifty50['1Y'] != '-') ? parseFloat(json.priceHistory.nifty50['1Y']).toFixed(2) + "%" : '-'



    var nifty501WClass = "";
    if(json.priceHistory.nifty50['1W'] > 0) {
        nifty501WClass = "success";
    } else if(json.priceHistory.nifty50['1W'] < 0) {
        nifty501WClass = "danger";
    }

    var nifty501MClass = "";
    if(json.priceHistory.nifty50['1M'] > 0) {
        nifty501MClass = "success";
    } else if(json.priceHistory.nifty50['1M'] < 0) {
        nifty501MClass = "danger";
    }

    var nifty503MClass = "";
    if(json.priceHistory.nifty50['3M'] > 0) {
        nifty503MClass = "success";
    } else if(json.priceHistory.nifty50['3M'] < 0) {
        nifty503MClass = "danger";
    }

    var nifty506MClass = "";
    if(json.priceHistory.nifty50['6M'] > 0) {
        nifty506MClass = "success";
    } else if(json.priceHistory.nifty50['6M'] < 0) {
        nifty506MClass = "danger";
    }

    var nifty501YClass = "";
    if(json.priceHistory.nifty50['1Y'] > 0) {
        nifty501YClass = "success";
    } else if(json.priceHistory.nifty50['1Y'] < 0) {
        nifty501YClass = "danger";
    }

    var nifty50Html = "<h3>Nifty 50 Return</h3>"
                    + "<table>"
                    + "<tr>"
                    +   "<thead>"
                    +       "<th>1W</th>"
                    +       "<th>1M</th>"
                    +       "<th>3M</th>"
                    +       "<th>6M</th>"
                    +       "<th>1Y</th>"
                    +   "</thead>"
                    + "</tr>"
                    + "<tr>"
                    +       "<td class='" + nifty501WClass + "'>" + nifty501W + "</td>"
                    +       "<td class='" + nifty501MClass + "'>" + nifty501M + "</td>"
                    +       "<td class='" + nifty503MClass + "'>" + nifty503M + "</td>"
                    +       "<td class='" + nifty506MClass + "'>" + nifty506M + "</td>"
                    +       "<td class='" + nifty501YClass + "'>" + nifty501Y + "</td>"
                    + "</tr>"
                    + "</table>";

    $("#perf_history_container #nifty50").html(nifty50Html);
}

setCompanyRatingHtml = function(valuationScore) {

    var ratingImage = '';
    var ratingClass = "";

    if(valuationScore == "Very Overpriced") {
        ratingImage = "../resources/images/rating/ratingVeryOverpriced.jpg";
        ratingClass = "veryOverpriced";
    } else if(valuationScore == "Very Pleasing") {
        ratingImage = "../resources/images/rating/ratingVeryPleasing.jpg";
        ratingClass = "veryPleasing";
    } else if(valuationScore == "Overpriced") {
        ratingImage = "../resources/images/rating/ratingOverpriced.jpg";
        ratingClass = "overpriced";
    } else if(valuationScore == "Pleasing") {
        ratingImage = "../resources/images/rating/ratingPleasing.jpg";
        ratingClass = "pleasing";
    } else if(valuationScore == "Reasonable") {
        ratingImage = "../resources/images/rating/ratingReasonable.jpg";
        ratingClass = "reasonable";
    } else {
        ratingImage = "../resources/images/rating/ratingReasonable.jpg";
        ratingClass = "reasonable";
    }

    var html = "<div id='finvendor_rating'><h3>FinVendor Valuation Rating</h3><img src='" + ratingImage + "' alt='" + valuationScore + "' title='" + valuationScore + "'/><h4 class='" + ratingClass + "'>" + valuationScore + "</h4</div>";
    $("#rating_container").html(html);
}



setBrokerRatingHtml = function(response) {
    /*var response = {
        companyProfileData: {
            currency: "INR"
        },
        brokerRank: {
            totalBuyRecomm: 2, 
            totalSellRecomm: 10, 
            totalNeutralRecomm: 3, 
            averageTargetPrice: 1450, 
            upside: 20.99
        }
    };*/

    var totalRecomm = response.brokerRank.totalBuyRecomm + response.brokerRank.totalSellRecomm + response.brokerRank.totalNeutralRecomm;

    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(function() {

      var data = google.visualization.arrayToDataTable([
            ['Task', 'Broker Stock Rating'],
          [response.brokerRank.totalBuyRecomm + ' Buy Recomm.', response.brokerRank.totalBuyRecomm],
          [response.brokerRank.totalSellRecomm + ' Sell Recomm.', response.brokerRank.totalSellRecomm],
          [response.brokerRank.totalNeutralRecomm + ' Neutral Recomm.', response.brokerRank.totalNeutralRecomm]
          ]);

    var view = new google.visualization.DataView(data);
    view.setColumns([0, {
        type: 'number',
        label: data.getColumnLabel(1),
        calc: function (dt, row) {
            var value = dt.getValue(row, 1);
            if (value == 0) {
                // change value to a very small, non-zero number
                // must be much smaller than all normal values in the chart
                value = 0.000001;
            }
            return {
                v: value,
                f: dt.getFormattedValue(row, 1)
            };
        }
    }]);

    var colorsVal =  ['#5cb85c', '#d9534f', '#ef9e06'];
    var pieSliceTextVal = "percentage";
    var legendVal = {
        alignment: 'center'
    };

    if(response.brokerRank.totalBuyRecomm == 0 && response.brokerRank.totalSellRecomm == 0 && response.brokerRank.totalNeutralRecomm == 0) {
        colorsVal = ["#cccccc", "#cccccc", "#cccccc"];
        pieSliceTextVal = 'none';
    }

    var options = {
          pieHole: 0.4,
          pieSliceBorderColor: "none",
          sliceVisibilityThreshold :0,
          colors: colorsVal,
          pieSliceText: pieSliceTextVal,
          backgroundColor: "#eeeeee",
          legend: legendVal,
          enableInteractivity: false,
          tooltip: {
            trigger: "none"
          },
            chartArea: {width: 350, height: 150}
    };

      var chart = new google.visualization.PieChart(document.querySelector('#broker_stock_rating_container .broker_stock_rating_ui'));
      chart.draw(view, options);


      var averageTargetPrice = (response.brokerRank.averageTargetPrice) ? response.companyProfileData.currency + " " + parseFloat(response.brokerRank.averageTargetPrice).toFixed(2) : '-';
      var upside = (response.brokerRank.upside != '-') ? "%Upside: " + parseFloat(response.brokerRank.upside).toFixed(2) + "%": '-';

      var upsideClass = "";
      var upsideIcon = "";

      if(response.brokerRank.upside > 0) {
        upsideIcon = "fa fa-arrow-up";
        upsideClass = "success";
      } else if (response.brokerRank.upside < 0) {
        upsideIcon = "fa fa-arrow-down";
        upsideClass = "danger";
      }

      var html = "<ul>"
                //+ "<li>Consensus Target Price <p><span>" + averageTargetPrice  + "</span> <span class='upside " + upsideClass +  "'>(" + upside + " " + "<i class='" + upsideIcon + "'>" + "</i>)</span></p></li>"
                + "<li>Consensus Target Price <p><span>" + averageTargetPrice  + "</span> <span class='upside " + upsideClass +  "'>(" + upside + " " + ")</span></p></li>"
                + "</ul>"
      $("#broker_stock_rating_container .broker_stock_rating_ui").append(html);
      $("#broker_stock_rating_container .broker_stock_rating_ui").prepend("<h4>Analyst's Stock Rating<sup>*</sup></h4>");
      $("#broker_stock_rating_container .broker_stock_rating_ui").css({
        'background': '#eee',
        'margin-right': '20px',
        'overflow': 'hidden'
      });
      $("#broker_stock_rating_container").append("<p class='disclaimer'>* indicates for " + totalRecomm + " broker</p>");
  });
}


function getCompanyProfile() {
    isProgressLoader(true);

    var companyProfileJson = window.localStorage.getItem("companyProfileJson");

    companyProfileJson = JSON.parse(companyProfileJson);
    var isinCode = companyProfileJson.isinCode;

    var url = baseApiUrl + "companyprofile?isinCode=" + isinCode;
    return new Promise(function(resolve, reject) {
        var httpRequest = new XMLHttpRequest({
            mozSystem: true
        });
        //httpRequest.timeout = API_TIMEOUT_SMALL;
        httpRequest.open('GET', url, true);

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
};


function getCompanyResearchReport(researchType, pageNumber) {
    isProgressLoader(true);

    var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

    var url = baseApiUrl + "companyprofile/researchreport?isinCode=" + companyProfileJson.isinCode + "&type=" + researchType + "&pageNumber=" + pageNumber + "&perPageMaxRecords=" + perPageMaxRecords + "&sortBy=" + sortByValue + "&orderBy=" + orderBy;
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
}

function addToMarketWatchlist() {
    addToMarketWatchlistAPI().then(function(response) {
        isProgressLoader(false);
        $("#addToWatchlist .alert").removeClass('alert-danger');
        $("#addToWatchlist .alert span").text(JSON.parse(response).message);
    }, function(error) {
        isProgressLoader(false);
        $("#addToWatchlist .alert").addClass('alert-danger');
        $("#addToWatchlist .alert span").text(JSON.parse(error).message);

    });
}
    $("#company_profile .profile_details.func_details button").eq(0).on('click', addToMarketWatchlist);

function addToMarketWatchlistAPI() {
    
    isProgressLoader(true);

    var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

    var url = baseApiUrl + "companywatchlist/create";
    return new Promise(function(resolve, reject) {
        var httpRequest = new XMLHttpRequest({
            mozSystem: true
        });
        //httpRequest.timeout = API_TIMEOUT_SMALL;
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
                    //console.log(httpRequest.status + httpRequest.responseText);
                    reject(httpRequest.responseText);
                }
            } else {
            }
        };

        httpRequest.send(JSON.stringify(companyProfileObj));
    });
}

function setPriceAlert() {
    var dayMinPrice = $("#setPriceAlert input[name=day_min_price]").val();
    var dayMaxPrice = $("#setPriceAlert input[name=day_max_price]").val();
    var weekMinPrice = $("#setPriceAlert input[name=week_min_price]").val();
    var weekMaxPrice = $("#setPriceAlert input[name=week_max_price]").val();
    var monthMinPrice = $("#setPriceAlert input[name=month_min_price]").val();
    var monthMaxPrice = $("#setPriceAlert input[name=month_max_price]").val();
    var noTimeFrameMinPrice = $("#setPriceAlert input[name=noTimeFrame_min_price]").val();
    var noTimeFrameMaxPrice = $("#setPriceAlert input[name=noTimeFrame_max_price]").val();
    var isResearchReport = $("#setPriceAlert input[name=alert_new_research_report]").prop('checked');
    var alertJsonObj = {
        "companyId": companyProfileObj.companyId,
        "companyName": companyProfileObj.companyName,
        "cmpWhenPriceAlertSet": companyProfileObj.cmp,
        "dayMinPrice": dayMinPrice,
        "dayMaxPrice": dayMaxPrice,
        "weekMinPrice": weekMinPrice,
        "weekMaxPrice": weekMaxPrice,
        "monthMinPrice": monthMinPrice,
        "monthMaxPrice": monthMaxPrice,
        "noTimeFrameMinPrice": noTimeFrameMinPrice,
        "noTimeFrameMaxPrice": noTimeFrameMaxPrice,
        "isResearchReport": isResearchReport
    };

    setPriceAlertAPI(alertJsonObj).then(function(response){
        var response = JSON.parse(response);
        $("#setPriceAlert h3").hide();
        $("#setPriceAlert .alert").text(response.message);
        $("#setPriceAlert .alert").removeClass("alert-danger").show();
        isProgressLoader(false);
    }, function(error) {
        var response = JSON.parse(error);
        $("#setPriceAlert h3").hide();
        $("#setPriceAlert .alert").text(response.message);
        $("#setPriceAlert .alert").addClass("alert-danger").show();
        isProgressLoader(false);
    });
}

    $("#setPriceAlert button[name=set_alert_btn]").on('click', setPriceAlert);

function setPriceAlertAPI(alertJsonObj) {
    
    isProgressLoader(true);

    var url = baseApiUrl + "companypricealert/create";
    var method = 'POST';

    if(priceAlertStatus == "U") {
        url = baseApiUrl + "companypricealert/update";
        method = 'PUT';
    }
    return new Promise(function(resolve, reject) {
        var httpRequest = new XMLHttpRequest({
            mozSystem: true
        });
        //httpRequest.timeout = API_TIMEOUT_SMALL;
        httpRequest.open(method, url, true);
        httpRequest.setRequestHeader('Content-Type',
                'application/json; charset=UTF-8');

        httpRequest.ontimeout = function () {
            reject("" + httpRequest.responseText);
        };
        httpRequest.onreadystatechange = function () {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200 || httpRequest.status === 201) {
                    resolve(httpRequest.response);
                } else {
                    //console.log(httpRequest.status + httpRequest.responseText);
                    reject(httpRequest.responseText);
                }
            } else {
            }
        };

        httpRequest.send(JSON.stringify(alertJsonObj));
    });
} 

function openPriceAlertForm() {
    $("#setPriceAlert .modal-header h3").show();
    $("#setPriceAlert .modal-header .alert").hide();
    
    var companyId = companyProfileObj.companyId;
    getPriceAlertAPI(companyId).then(function(response) {
        isProgressLoader(false);

        var response = JSON.parse(response);
        
        if(response.companyId != null) {
            priceAlertStatus = 'U';
        }

        $("#setPriceAlert input[name=day_min_price]").val(response.dayMinPrice);
        $("#setPriceAlert input[name=day_max_price]").val(response.dayMaxPrice);
        $("#setPriceAlert input[name=week_min_price]").val(response.weekMinPrice);
        $("#setPriceAlert input[name=week_max_price]").val(response.weekMaxPrice);
        $("#setPriceAlert input[name=month_min_price]").val(response.monthMinPrice);
        $("#setPriceAlert input[name=month_max_price]").val(response.monthMaxPrice);
        $("#setPriceAlert input[name=noTimeFrame_min_price]").val(response.noTimeFrameMinPrice);
        $("#setPriceAlert input[name=noTimeFrame_max_price]").val(response.noTimeFrameMaxPrice);
        
        (response.isResearchReport) ? $("#setPriceAlert input[name=alert_new_research_report]").prop('checked', true) : $("#setPriceAlert input[name=alert_new_research_report]").prop('checked', false);
    }, function(error) {
        isProgressLoader(false);
    });
}

$("#company_profile .profile_details.func_details button").eq(1).on('click', openPriceAlertForm);

function getPriceAlertAPI(companyId) {
    
    isProgressLoader(true);

    var url = baseApiUrl + "companypricealert?companyId=" + companyId;
    return new Promise(function(resolve, reject) {
        var httpRequest = new XMLHttpRequest({
            mozSystem: true
        });
        //httpRequest.timeout = API_TIMEOUT_SMALL;
        httpRequest.open('GET', url, true);

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
}

function getCompanyEarningsPreviewAPI(periodType, isinCode) {
    
    isProgressLoader(true);

    var url = baseApiUrl + "companyprofile/earningpreview?type=" + periodType + "&isin=" + isinCode;
    return new Promise(function(resolve, reject) {
        var httpRequest = new XMLHttpRequest({
            mozSystem: true
        });
        //httpRequest.timeout = API_TIMEOUT_SMALL;
        httpRequest.open('GET', url, true);

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
}

setQuarterlyCompanyEarningsPreview();
setYearlyCompanyEarningsPreview();

function setQuarterlyCompanyEarningsPreview() {

    var periodType = "quarterly";
    var isinCode = JSON.parse(window.localStorage.getItem("companyProfileJson")).isinCode;// = "INE117A01022";


    getCompanyEarningsPreviewAPI(periodType, isinCode).then(function(response) {
        /*var response = {
          "earningPreviewResult": [
          {
              "period": "1519842600000",
              "revenue": "2525.5",
              "operatingProfitMargin": "6.07",
              "profitAfterTax": "102.5",
              "eps": "4.84"
          },
          {
              "period": "1527791400000",
              "revenue": "2712.7",
              "operatingProfitMargin": "5.91",
              "profitAfterTax": "102.2",
              "eps": "4.82"
          },
          {
              "period": "1535740200000",
              "revenue": "2543.9",
              "operatingProfitMargin": "7.29",
              "profitAfterTax": "108.3",
              "eps": "5.11"
          },
          {
              "period": "1543602600000",
              "revenue": "1966.3",
              "operatingProfitMargin": "9.74",
              "profitAfterTax": "197.9",
              "eps": "9.34"
          }
        ]};*/

          var earningPreviewSorted = JSON.parse(response).earningPreviewResult;

          var length = earningPreviewSorted.length;
          if(length == 0) {
            $("#quarterly_fin_statement .quarterly_fin_content").html("No Earning Preview Available");
            return false;
          }

          earningPreviewSorted.sort(function(a, b){return a.period - b.period});

          // console.log(earningPreviewSorted);

          var currency = "INR";
          var mapCurrency = (currency == 'INR') ? ', Crores' : ', Millions';

          var html = "";
          var quarterlyHeader = '';
          var tableHead = '';
          var quarterlyRevenue = '';
          var quarterlyOperatingProfitMargin = '';
          var quarterlyPAT = '';
          var quarterlyEPS = '';

          for(var i = 0; i < length; i++) {
            var month = timeStampToDateNew(Number(earningPreviewSorted[i].period))[1];
            var yr = timeStampToDateNew(Number(earningPreviewSorted[i].period))[3];
            quarterlyHeader = quarterlyHeader + "<th>" + month + "'" + yr + "</th>";
        }

        tableHead = "<th>(" + currency + mapCurrency + ")</th>" + quarterlyHeader;

        var prevRevenue = '';
        var diffRevenue = '';

        for(var i = 0; i < length; i++) {
            var revenue = (earningPreviewSorted[i].revenue != undefined && earningPreviewSorted[i].revenue != '-' && earningPreviewSorted[i].revenue != 'N/A') ? parseFloat(earningPreviewSorted[i].revenue).toFixed(1) : '-';

            if(prevRevenue != '' && revenue != '-') {
                diffRevenue = "(" + parseFloat((revenue - prevRevenue)*100/ prevRevenue).toFixed(1) + "%)";
            } else {
                diffRevenue = ''
            }
            prevRevenue = revenue;

            // console.log("diffRevenue:: " + diffRevenue);
            quarterlyRevenue = quarterlyRevenue + "<td>" + revenue + " " + diffRevenue + "</td>";
        }

        quarterlyRevenue = "<tr><td>Revenue</td>" + quarterlyRevenue + "</tr>";

        for(var i = 0; i < length; i++) {
            var operatingProfitMargin = (earningPreviewSorted[i].operatingProfitMargin != undefined && earningPreviewSorted[i].operatingProfitMargin != '-' && earningPreviewSorted[i].operatingProfitMargin != 'N/A') ? parseFloat(earningPreviewSorted[i].operatingProfitMargin).toFixed(1) : '-';
            quarterlyOperatingProfitMargin = quarterlyOperatingProfitMargin + "<td>" + operatingProfitMargin + "</td>";
        }

        quarterlyOperatingProfitMargin = "<tr><td>Operating Profit Margin(%)</td>" + quarterlyOperatingProfitMargin + "</tr>";

        var prevProfitAfterTax = '';
        var diffProfitAfterTax = '';

        for(var i = 0; i < length; i++) {
            var profitAfterTax = (earningPreviewSorted[i].profitAfterTax != undefined && earningPreviewSorted[i].profitAfterTax != '-' && earningPreviewSorted[i].profitAfterTax != 'N/A') ? parseFloat(earningPreviewSorted[i].profitAfterTax).toFixed(1) : '-';
            if(prevProfitAfterTax != '' && profitAfterTax != '-') {
                diffProfitAfterTax = "(" + parseFloat((profitAfterTax - prevProfitAfterTax)*100/ prevProfitAfterTax).toFixed(1) + "%)";
            } else {
                diffProfitAfterTax = ''
            }
            prevProfitAfterTax = profitAfterTax;

            // console.log("diffProfitAfterTax:: " + diffProfitAfterTax);
            quarterlyPAT = quarterlyPAT + "<td>" + profitAfterTax + " " + diffProfitAfterTax + "</td>";
        }

        quarterlyPAT = "<tr><td>Profit After Tax (PAT)</td>" + quarterlyPAT + "</tr>";

        var prevEPS = '';
        var diffEPS = '';

        for(var i = 0; i < length; i++) {
            var eps = (earningPreviewSorted[i].eps != undefined && earningPreviewSorted[i].eps != '-' && earningPreviewSorted[i].eps != 'N/A') ? parseFloat(earningPreviewSorted[i].eps).toFixed(1) : '-';
            if(prevEPS != '' && eps != '-') {
                diffEPS = "(" + parseFloat((eps - prevEPS)*100/ prevEPS).toFixed(1) + "%)";
            } else {
                diffEPS = ''
            }
            prevEPS = eps;

            // console.log("diffEPS:: " + diffEPS);
            quarterlyEPS = quarterlyEPS + "<td>" + eps + " " + diffEPS + "</td>";
        }

        quarterlyEPS = "<tr><td>EPS (Basic)</td>" + quarterlyEPS + "</tr>";

        html = "<table>"
        + "<thead>"
        +   tableHead
        +  "</thead>"
        +  "<tbody>"
        + quarterlyRevenue
        + quarterlyOperatingProfitMargin
        + quarterlyPAT
        + quarterlyEPS
        +  "</tbody>"
        + "</table>";

        $("#quarterly_fin_statement .quarterly_fin_content").html(html);
    }, function(error) {
        $("#quarterly_fin_statement .quarterly_fin_content").html("No Earning Preview Available");
    });
}

function setYearlyCompanyEarningsPreview() {

    var periodType = "yearly";
    var isinCode = JSON.parse(window.localStorage.getItem("companyProfileJson")).isinCode;


    getCompanyEarningsPreviewAPI(periodType, isinCode).then(function(response) {
        /*var response = {
          "earningPreviewResult": [
          {
              "period": "1393612200000",
              "revenue": "7703.2",
              "operatingProfitMargin": "6.72",
              "profitAfterTax": "228.5",
              "eps": "10.78",
              "netOperatingCashFlow": "479",
              "roe": "8.13"
          },
          {
              "period": "1425148200000",
              "revenue": "8092.8",
              "operatingProfitMargin": "7.88",
              "profitAfterTax": "299.9",
              "eps": "14.15",
              "netOperatingCashFlow": "381.7",
              "roe": "9.97"
          },
          {
              "period": "1456770600000",
              "revenue": "8597",
              "operatingProfitMargin": "8.14",
              "profitAfterTax": "376.3",
              "eps": "17.76",
              "netOperatingCashFlow": "862.6",
              "roe": "11.46"
          },
          {
              "period": "1488306600000",
              "revenue": "9046.3",
              "operatingProfitMargin": "8.2",
              "profitAfterTax": "420",
              "eps": "19.82",
              "netOperatingCashFlow": "799.8",
              "roe": "11.64"
          },
          {
              "period": "1519842600000",
              "revenue": "6690.1",
              "operatingProfitMargin": "5.46",
              "profitAfterTax": "510.9",
              "eps": "24.11",
              "netOperatingCashFlow": "N/A",
              "roe": "12.75"
          }
        ]};*/


          var earningPreviewSorted = JSON.parse(response).earningPreviewResult;


          var length = earningPreviewSorted.length;
          if(length == 0) {
            $("#yearly_fin_statement .yearly_fin_content").html("No Earning Preview Available");
            return false;
          }

          earningPreviewSorted.sort(function(a, b){return a.period - b.period});

          // console.log(earningPreviewSorted);
          var currency = "INR";
          var mapCurrency = (currency == 'INR') ? ', Crores' : ', Millions';

          var html = "";
          var yearlyHeader = '';
          var tableHead = '';
          var yearlyRevenue = '';
          var yearlyOperatingProfitMargin = '';
          var yearlyPAT = '';
          var yearlyEPS = '';
          var yearlyNetCashFlow = '';
          var yearlyROE = '';

          for(var i = 0; i < length; i++) {
            var month = timeStampToDateNew(Number(earningPreviewSorted[i].period))[1];
            var yr = timeStampToDateNew(Number(earningPreviewSorted[i].period))[3];
            yearlyHeader = yearlyHeader + "<th>" + month + "'" + yr + "</th>";
        }

        tableHead = "<th>(" + currency + mapCurrency + ")</th>" + yearlyHeader;

        var prevRevenue = '';
        var diffRevenue = '';

        for(var i = 0; i < length; i++) {
            var revenue = (earningPreviewSorted[i].revenue != undefined && earningPreviewSorted[i].revenue != '-' && earningPreviewSorted[i].revenue != 'N/A') ? parseFloat(earningPreviewSorted[i].revenue).toFixed(1) : '-';
            
            if(prevRevenue != '' && revenue != '-') {
                diffRevenue = "(" + parseFloat((revenue - prevRevenue)*100/ prevRevenue).toFixed(1) + "%)";
            } else {
                diffRevenue = ''
            }
            prevRevenue = revenue;

            // console.log("diffRevenue:: " + diffRevenue);
            yearlyRevenue = yearlyRevenue + "<td>" + revenue + " " + diffRevenue + "</td>";
        }

        yearlyRevenue = "<tr><td>Revenue</td>" + yearlyRevenue + "</tr>";

        for(var i = 0; i < length; i++) {
            var operatingProfitMargin = (earningPreviewSorted[i].operatingProfitMargin != undefined && earningPreviewSorted[i].operatingProfitMargin != '-' && earningPreviewSorted[i].operatingProfitMargin != 'N/A') ? parseFloat(earningPreviewSorted[i].operatingProfitMargin).toFixed(1) : '-';
            yearlyOperatingProfitMargin = yearlyOperatingProfitMargin + "<td>" + operatingProfitMargin + "</td>";
        }

        yearlyOperatingProfitMargin = "<tr><td>Operating Profit Margin(%)</td>" + yearlyOperatingProfitMargin + "</tr>";

        var prevProfitAfterTax = '';
        var diffProfitAfterTax = '';

        for(var i = 0; i < length; i++) {
            var profitAfterTax = (earningPreviewSorted[i].profitAfterTax != undefined && earningPreviewSorted[i].profitAfterTax != '-' && earningPreviewSorted[i].profitAfterTax != 'N/A') ? parseFloat(earningPreviewSorted[i].profitAfterTax).toFixed(1) : '-';
            if(prevProfitAfterTax != '' && profitAfterTax != '-') {
                diffProfitAfterTax = "(" + parseFloat((profitAfterTax - prevProfitAfterTax)*100/ prevProfitAfterTax).toFixed(1) + "%)";
            } else {
                diffProfitAfterTax = ''
            }
            prevProfitAfterTax = profitAfterTax;

            // console.log("diffProfitAfterTax:: " + diffProfitAfterTax);
            yearlyPAT = yearlyPAT + "<td>" + profitAfterTax + " " + diffProfitAfterTax + "</td>";
        }

        yearlyPAT = "<tr><td>Profit After Tax (PAT)</td>" + yearlyPAT + "</tr>";

        var prevEPS = '';
        var diffEPS = '';

        for(var i = 0; i < length; i++) {
            var eps = (earningPreviewSorted[i].eps != undefined && earningPreviewSorted[i].eps != '-' && earningPreviewSorted[i].eps != 'N/A') ? parseFloat(earningPreviewSorted[i].eps).toFixed(1) : '-';
            if(prevEPS != '' && eps != '-') {
                diffEPS = "(" + parseFloat((eps - prevEPS)*100/ prevEPS).toFixed(1) + "%)";
            } else {
                diffEPS = ''
            }
            prevEPS = eps;

            // console.log("diffEPS:: " + diffEPS);
            yearlyEPS = yearlyEPS + "<td>" + eps + " " + diffEPS + "</td>";
        }

        yearlyEPS = "<tr><td>EPS (Basic)</td>" + yearlyEPS + "</tr>";

        var prevNetCashFlow = '';
        var diffNetCashFlow = '';

        for(var i = 0; i < length; i++) {
            var netOperatingCashFlow = (earningPreviewSorted[i].netOperatingCashFlow != undefined && earningPreviewSorted[i].netOperatingCashFlow != '-' && earningPreviewSorted[i].netOperatingCashFlow != 'N/A') ? parseFloat(earningPreviewSorted[i].netOperatingCashFlow).toFixed(1) : '-';
            if(prevNetCashFlow != '' && netOperatingCashFlow != '-') {
                diffNetCashFlow = "(" + parseFloat((netOperatingCashFlow - prevNetCashFlow)*100/ prevNetCashFlow).toFixed(1) + "%)";
            } else {
                diffNetCashFlow = ''
            }
            prevNetCashFlow = netOperatingCashFlow;

            // console.log("diffNetCashFlow:: " + diffNetCashFlow);
            yearlyNetCashFlow = yearlyNetCashFlow + "<td>" + netOperatingCashFlow + " " + diffNetCashFlow + "</td>";
        }

        yearlyNetCashFlow = "<tr><td>Net Operating CashFlow</td>" + yearlyNetCashFlow + "</tr>";

        var prevROE = '';
        var diffROE = '';

        for(var i = 0; i < length; i++) {
            var roe = (earningPreviewSorted[i].roe != undefined && earningPreviewSorted[i].roe != '-') ? parseFloat(earningPreviewSorted[i].roe).toFixed(1) : '-';
            if(prevROE != '' && roe != '-') {
                diffROE = "(" + parseFloat((roe - prevROE)*100/ prevROE).toFixed(1) + "%)";
            } else {
                diffROE = ''
            }
            prevROE = roe;

            // console.log("diffROE:: " + diffROE);
            yearlyROE = yearlyROE + "<td>" + roe + " " + diffROE + "</td>";
        }

        yearlyROE = "<tr><td>ROE</td>" + yearlyROE + "</tr>";

        html = "<table>"
        + "<thead>"
        +   tableHead
        +  "</thead>"
        +  "<tbody>"
        + yearlyRevenue
        + yearlyOperatingProfitMargin
        + yearlyPAT
        + yearlyEPS
        + yearlyNetCashFlow
        + yearlyROE
        +  "</tbody>"
        + "</table>";

        $("#yearly_fin_statement .yearly_fin_content").html(html);
    }, function(error) {
        $("#yearly_fin_statement .yearly_fin_content").html("No Earning Preview Available");
    });
}

/*
** company News Feed
*/

var companyNewsObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 5;
        this.sortByValue = 'broadcastDate';
        this.orderBy = 'desc';
        this.ticker = companyProfileObj.ticker;

        this.getCompanyNewsData();
    },

    getCompanyNewsData: function() {
        var classRef = this;
        classRef.getCompanyNewsRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCompanyNewsAPI().then(function(serverResponse) {
                $("#news_feed_content .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCompanyNewsHtml(serverResponse);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#news_feed_content tbody").html("<tr><td colspan='2'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
                $("#news_feed_content tbody").html("<tr><td colspan='2'>We are not able to get the info, please try again later.</td></tr>");
        });
    },

    getCompanyNewsRecordStats: function() {
        var classRef = this;
        var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

        var url = baseApiUrl + "companyprofile/companynews/recordstat?ticker=" + classRef.ticker + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
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

    getCompanyNewsAPI: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = baseApiUrl + "companyprofile/companynews?ticker=" + classRef.ticker + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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

    getCompanyNewsHtml : function(response) {
        var classRef = this;
        var companyNewsList = response.companyNews;
        var len = companyNewsList.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#news_feed_content tbody").html("<tr><td colspan='2'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

            var broadcastDate = (companyNewsList[i].broadcastDate != '0') ? timeStampToDateNew(Number(companyNewsList[i].broadcastDate))[2] + "-" + timeStampToDateNew(Number(companyNewsList[i].broadcastDate))[1] + "-" + timeStampToDateNew(Number(companyNewsList[i].broadcastDate))[3] + " " + timeStampToDateNew(Number(companyNewsList[i].broadcastDate))[4] : '-';


            if($(window).width() < 768) {
                broadcastDate = (companyNewsList[i].broadcastDate != '0') ? timeStampToDateNew(Number(companyNewsList[i].broadcastDate))[2] + "-" + timeStampToDateNew(Number(companyNewsList[i].broadcastDate))[1] + "-" + timeStampToDateNew(Number(companyNewsList[i].broadcastDate))[3] : '-';
            }

            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='date'>" + broadcastDate + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='subject'>" + companyNewsList[i].subject + "</div>" +
            "</td>" +
            "</tr>";
        }

        $("#news_feed_content tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#news_feed_content").append(paginationHtml);

        $('#news_feed_content .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);


        classRef.setRecordStats();
    },

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#news_feed_content #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
            classRef.getCompanyNewsData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCompanyNewsData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCompanyNewsData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCompanyNewsData();
        }
    }

};

/*
** company calendar Feed
*/

var companyCalendarObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 5;
        this.sortByValue = 'boardMeetinDate';
        this.orderBy = 'desc';
        this.ticker = companyProfileObj.ticker;
        this.baseApiUrl = baseApiUrl + "companyprofile";

        this.getCompanyCalendarData();
    },

    getCompanyCalendarData: function() {
        var classRef = this;
        classRef.getCompanyCalendarRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCompanyCalendarAPI().then(function(serverResponse) {
                $("#results_calendar_content .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCompanyCalendarHtml(serverResponse);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#results_calendar_content tbody").html("<tr><td colspan='2'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
                $("#results_calendar_content tbody").html("<tr><td colspan='2'>We are not able to get the info, please try again later.</td></tr>");
        });
    },

    getCompanyCalendarRecordStats: function() {
        var classRef = this;
        var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

        var url = classRef.baseApiUrl + "/calendar/recordstat?ticker=" + classRef.ticker + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
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

    getCompanyCalendarAPI: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = classRef.baseApiUrl + "/calendar?ticker=" + classRef.ticker + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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

    getCompanyCalendarHtml : function(response) {
        var classRef = this;
        var companyCalendarList = response.calendar;
        var len = companyCalendarList.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#results_calendar_content tbody").html("<tr><td colspan='2'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

            var boardMeetinDate = (companyCalendarList[i].boardMeetinDate != '0') ? timeStampToDateNew(Number(companyCalendarList[i].boardMeetinDate))[2] + "-" + timeStampToDateNew(Number(companyCalendarList[i].boardMeetinDate))[1] + "-" + timeStampToDateNew(Number(companyCalendarList[i].boardMeetinDate))[3] : '-';


            if($(window).width() < 768) {
                boardMeetinDate = (companyCalendarList[i].boardMeetinDate != '0') ? timeStampToDateNew(Number(companyCalendarList[i].boardMeetinDate))[2] + "-" + timeStampToDateNew(Number(companyCalendarList[i].boardMeetinDate))[1] + "-" + timeStampToDateNew(Number(companyCalendarList[i].boardMeetinDate))[3] : '-';
            } else {

            }

            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='date'>" + boardMeetinDate+ "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='purpose'>" + companyCalendarList[i].purpose + "</div>" +
            "</td>" +
            "</tr>";
        }

        $("#results_calendar_content tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#results_calendar_content").append(paginationHtml);

        $('#results_calendar_content .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);


        classRef.setRecordStats();
    },

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#results_calendar_content #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
            classRef.getCompanyCalendarData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCompanyCalendarData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCompanyCalendarData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCompanyCalendarData();
        }
    }

};

/*
** company corp action Feed
*/

var companyCorpActionObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 5;
        this.sortByValue = 'recordDate';
        this.orderBy = 'desc';
        this.ticker = companyProfileObj.ticker;
        this.baseApiUrl = baseApiUrl + "companyprofile";

        this.getCompanyCorpActionData();
    },

    getCompanyCorpActionData: function() {
        var classRef = this;
        classRef.getCompanyCorpActionRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCompanyCorpActionAPI().then(function(serverResponse) {
                $("#corp_action_content .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCompanyCorpActionHtml(serverResponse);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#corp_action_content tbody").html("<tr><td colspan='4'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
                $("#corp_action_content tbody").html("<tr><td colspan='4'>We are not able to get the info, please try again later.</td></tr>");
        });
    },

    getCompanyCorpActionRecordStats: function() {
        var classRef = this;
        var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

        var url = classRef.baseApiUrl + "/corpaction/recordstat?ticker=" + classRef.ticker + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
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

    getCompanyCorpActionAPI: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = classRef.baseApiUrl + "/corpaction?ticker=" + classRef.ticker + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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

    getCompanyCorpActionHtml : function(response) {
        var classRef = this;
        var companyCorpActionList = response.corpAction;
        var len = companyCorpActionList.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#corp_action_content tbody").html("<tr><td colspan='4'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

            var recordDate = (companyCorpActionList[i].recordDate != '0') ? timeStampToDateNew(Number(companyCorpActionList[i].recordDate))[2] + "-" + timeStampToDateNew(Number(companyCorpActionList[i].recordDate))[1] + "-" + timeStampToDateNew(Number(companyCorpActionList[i].recordDate))[3] : '-';
            var exDate = (companyCorpActionList[i].exDate != '0') ? timeStampToDateNew(Number(companyCorpActionList[i].exDate))[2] + "-" + timeStampToDateNew(Number(companyCorpActionList[i].exDate))[1] + "-" + timeStampToDateNew(Number(companyCorpActionList[i].exDate))[3] : '-';

            var faceValue = (companyCorpActionList[i].faceValue != '0') ? companyCorpActionList[i].faceValue : '-';

            htmlCode = htmlCode + "<tr>" +
            "<td>" +
            "<div class='date'>" + recordDate + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='faceValue'>" + faceValue + "</div>" +
            "</td>" +
            "<td>" + 
            "<div class='exDate'>" + exDate + "</div>" +
            "</td>" +
            "<td>" + 
            "<div class='purpose'>" + companyCorpActionList[i].purpose + "</div>" +
            "</td>" +
            "</tr>";
        }

        $("#corp_action_content tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#corp_action_content").append(paginationHtml);

        $('#corp_action_content .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);


        classRef.setRecordStats();
    },

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#corp_action_content #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
            classRef.getCompanyCorpActionData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCompanyCorpActionData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCompanyCorpActionData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCompanyCorpActionData();
        }
    }

};

/*
** company price history Feed
*/

var companyPriceHistoryObj = {
    init: function() {
        this.firstPageNumber = 1;
        this.pageNumber = 1;
        this.lastPageNumber = 1;
        this.totalRecords = 1;
        this.currentIndex = 1;
        this.perPageMaxRecords = 5;
        this.sortByValue = 'priceDate';
        this.orderBy = 'desc';
        var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));
        this.isinCode = companyProfileJson.isinCode;
        this.baseApiUrl = baseApiUrl + "companyprofile";

        this.getCompanyPriceHistoryData();
    },

    getCompanyPriceHistoryData: function() {
        var classRef = this;
        classRef.getCompanyPriceHistoryRecordStats().then(function(stats) {
            stats = JSON.parse(stats);
            classRef.firstPageNumber = stats.firstPageNumber;
            classRef.lastPageNumber = stats.lastPageNumber;
            classRef.totalRecords = stats.totalRecords;

            classRef.getCompanyPriceHistoryAPI().then(function(serverResponse) {
                $("#price_history_content .paging_container").remove();
                serverResponse = JSON.parse(serverResponse);
                classRef.getCompanyPriceHistoryHtml(serverResponse);
                isProgressLoader(false);

            }, function(error) {
                console.log(error);
                isProgressLoader(false);
                $("#price_history_content tbody").html("<tr><td colspan='7'>We are not able to get the info, please try again later.</td></tr>");
            });
        }, function(error) {
                $("#price_history_content tbody").html("<tr><td colspan='7'>We are not able to get the info, please try again later.</td></tr>");
        });
    },

    getCompanyPriceHistoryRecordStats: function() {
        var classRef = this;
        var companyProfileJson = JSON.parse(window.localStorage.getItem("companyProfileJson"));

        var url = classRef.baseApiUrl + "/pricehistory/recordstat?isin=" + classRef.isinCode + "&perPageMaxRecords=" + classRef.perPageMaxRecords;
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

    getCompanyPriceHistoryAPI: function() {
        var classRef = this;

        isProgressLoader(true);

        var url = classRef.baseApiUrl + "/pricehistory?isin=" + classRef.isinCode + "&pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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

    getCompanyPriceHistoryHtml : function(response) {
        var classRef = this;
        var companyPriceHistoryList = response.priceHistory;
        var len = companyPriceHistoryList.length;
        var htmlCode = '';
        var rowHtml =   "";

        if(len === 0) {
            $("#price_history_content tbody").html("<tr><td colspan='7'>No Matching Records Found</td></tr>");
            return;
        }

        for(var i = 0; i < len; i++) {

            var highPrice = (companyPriceHistoryList[i].highPrice) ? parseFloat(companyPriceHistoryList[i].highPrice).toFixed(2) : '-';
            var lowPrice = (companyPriceHistoryList[i].lowPrice) ? parseFloat(companyPriceHistoryList[i].lowPrice).toFixed(2) : '-';
            var closePrice = (companyPriceHistoryList[i].closePrice) ? parseFloat(companyPriceHistoryList[i].closePrice).toFixed(2) : '-';
            var openPrice = (companyPriceHistoryList[i].openPrice) ? parseFloat(companyPriceHistoryList[i].openPrice).toFixed(2) : '-';
            var lastTracePrice = (companyPriceHistoryList[i].lastTracePrice) ? parseFloat(companyPriceHistoryList[i].lastTracePrice).toFixed(2) : '-';
            var priceDate = timeStampToDateNew(Number(companyPriceHistoryList[i].priceDate))[2] + "-" + timeStampToDateNew(Number(companyPriceHistoryList[i].priceDate))[1] + "-" + timeStampToDateNew(Number(companyPriceHistoryList[i].priceDate))[3];

            htmlCode = htmlCode + "<tr>" +
            "<td>" + 
            "<div class='date'>" + priceDate + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='highPrice'>" + highPrice + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='lowPrice'>" + lowPrice + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='closePrice'>" + closePrice + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='openPrice'>" + openPrice + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='lastTradedPrice'>" + lastTracePrice + "</div>" + 
            "</td>" +
            "<td>" + 
            "<div class='source'>" + companyPriceHistoryList[i].priceSourceCode + "</div>" + 
            "</td>" +
            "</tr>";
        }

        $("#price_history_content tbody").html(htmlCode);


        var paginationHtml =    "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

        $("#price_history_content").append(paginationHtml);

        $('#price_history_content .pager a').on('click', {this: classRef}, classRef.getPaginationIndex);


        classRef.setRecordStats();
    },

    setRecordStats : function() {
        var classRef = this;

        if(classRef.currentIndex > classRef.lastPageNumber) {
            classRef.currentIndex = classRef.lastPageNumber;
        }
        $("#price_history_content #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
            classRef.getCompanyPriceHistoryData();
        }
    },

    getLastPage : function() {
        var classRef = this;

        if(classRef.pageNumber != classRef.lastPageNumber) {
            classRef.pageNumber = classRef.lastPageNumber;
            classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
            classRef.getCompanyPriceHistoryData();
        }
    },

    getNextPage : function() {
        var classRef = this;

        if(classRef.pageNumber < classRef.lastPageNumber) {
            classRef.pageNumber = classRef.pageNumber + 1;
            classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
            classRef.getCompanyPriceHistoryData();
        }
    },

    getPreviousPage : function() {
        var classRef = this;

        if(classRef.pageNumber > 1) {
            classRef.pageNumber = classRef.pageNumber - 1;
            classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
            classRef.getCompanyPriceHistoryData();
        }
    }

};

var stockReturnObj = {
    init: function() {
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
        if(!isLoggedInUser()) {
            var parentNode = $(event.currentTarget).parent();

            parentNode.html("<img src='../resources/images/bx_loader.gif' />");


            stockReturnObj.getStockReturnApi(parentNode).then(function(response) {
                var response = JSON.parse(response);
                stockReturnObj.setStockReturnHtml(parentNode, response);
            }, function(error) {
                console.log("Unable to get stock Return");
            });
        } else {
            inner_login('view/equity_research_report_vendor.jsp');
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
    loadDefaultResearchReport('equity', perPageMaxRecords);

    $('#financials_content .nav-tabs li.active').addClass('subHeaderActive');
    $('#financials_content .nav-tabs').on('click', 'li', function(){
        $('#financials_content .nav-tabs li').removeClass('subHeaderActive');
        $(this).addClass('subHeaderActive');

        if($(this).children().attr('href') == "#yearly_fin_statement") {
            $("#quarterly_fin_statement").removeClass("in active");
            $("#yearly_fin_statement").addClass("in active");
        } else {
            $("#quarterly_fin_statement").addClass("in active");
            $("#yearly_fin_statement").removeClass("in active");
        }
    });

   var wrap = $(".subheader");

   $( window ).scroll(function() {
        var screenWidth = $(window).width();
        if(screenWidth < 768) {
            if ($(this).scrollTop() > 910) {
                wrap.addClass("fix-subheader");
            } else {
                wrap.removeClass("fix-subheader");
            }
        } else {
            if ($(this).scrollTop() > 400) {
                wrap.addClass("fix-subheader");
            } else {
                wrap.removeClass("fix-subheader");
            }
        }

    });
});