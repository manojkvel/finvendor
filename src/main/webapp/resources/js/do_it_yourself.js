jQuery(document).ready(function() {


    var resetFilters = function(e) {
        clearSelection();
        //resetPaginationCount();
    };

    var clearSelection = function() {
        $(".slider_input").slider("refresh", {});

        $("#sidebar-panel #search_by_industry input").prop('checked', false);
    };

    $('#sidebar-panel .sidebar-heading span').on('click', resetFilters);


    var doItYourselfObj = {
        init: function() {
            this.firstPageNumber = 1;
            this.pageNumber = 1;
            this.lastPageNumber = 1;
            this.totalRecords = 1;
            this.currentIndex = 1;
            this.perPageMaxRecords = 10;
            this.sortByValue = 'mcap';
            this.orderBy = 'desc';
            this.industryFilterData = [];
            this.setFilterData(); //Function to start async call to filter data API and set response.
            this.getCustomScreenerData();
        },

        getCustomScreenerHtml: function(response) {
            var classRef = this;
            var len = response.length;
            var htmlCode = '';
            var rowHtml =   "";

            if(len === 0) {
                $("#broker_table tbody").html("<tr><td colspan='6'>No Matching Records Found</td></tr>");
                return;
            }

            for(var i = 0; i < len; i++) {

                response[i].isinCode = "INE002A01018";

                var mcap = (response[i].mcap) ? parseFloat(response[i].mcap).toFixed(2) : '-';


                var pe = (response[i].pe) ? parseFloat(response[i].pe).toFixed(2) : '-';
                var pb = (response[i].pb) ? parseFloat(response[i].pb).toFixed(2) : '-';
                var divYield = (response[i].divYield) ? parseFloat(response[i].divYield).toFixed(2) : '-';

                var operatingProfitMargin = (response[i].operatingProfitMargin) ? parseFloat(response[i].operatingProfitMargin).toFixed(2) : '-';
                var debtToEquityRatio = (response[i].debtToEquityRatio) ? parseFloat(response[i].debtToEquityRatio).toFixed(2) : '-';
                var currentRatio = (response[i].currentRatio) ? parseFloat(response[i].currentRatio).toFixed(2) : '-';

                var roeInPercentage = (response[i].roeInPercentage) ? parseFloat(response[i].roeInPercentage).toFixed(2) : '-';
                var returnOnAssetInPercentage = (response[i].returnOnAssetInPercentage) ? parseFloat(response[i].returnOnAssetInPercentage).toFixed(2) : '-';
                var rotcInPercentage = (response[i].rotcInPercentage) ? parseFloat(response[i].rotcInPercentage).toFixed(2) : '-';

                var epsGrowthInPercentage = (response[i].epsGrowthInPercentage) ? parseFloat(response[i].epsGrowthInPercentage).toFixed(2) : '-';
                var patGrowthInPercentage = (response[i].patGrowthInPercentage) ? parseFloat(response[i].patGrowthInPercentage).toFixed(2) : '-';
                var revenueGrowthInPercentage = (response[i].revenueGrowthInPercentage) ? parseFloat(response[i].revenueGrowthInPercentage).toFixed(2) : '-';

                var netOperatingCashFlow = (response[i].netOperatingCashFlow) ? parseFloat(response[i].netOperatingCashFlow).toFixed(2) : '-';
                var totalFreeCashFlow = (response[i].totalFreeCashFlow) ? parseFloat(response[i].totalFreeCashFlow).toFixed(2) : '-';

                htmlCode = htmlCode + "<tr data-code='" + response[i].isinCode + "'>" +
                    "<td>" + 
                        "<div class='company' data-toggle='tooltip' title='See all reports for " + response[i].companyName + "'><a href='/view/company-profile.jsp?isinCode=" + response[i].isinCode + "'>" + response[i].companyName + "</a></div>" + 
                        "<div class='industry'>" + response[i].industry + "</div>" + 
                        "<div class='mcap'>" + mcap + "</div>" + 
                    "</td>" + 
                    "<td>" + 
                        "<div class='pe'>" + pe + "</div>" + 
                        "<div class='pb'>" + pb + "</div>" + 
                        "<div class='divYield'>" + divYield + "</div>" +
                    "</td>" +
                    "<td>" + 
                        "<div class='operatingProfitMargin'>" + operatingProfitMargin + "</div>" + 
                        "<div class='debtToEquityRatio'>" + debtToEquityRatio + "</div>" + 
                        "<div class='currentRatio'>" + currentRatio + "</div>" +
                    "</td>" +
                    "<td>" + 
                        "<div class='roeInPercentage'>" + roeInPercentage + "</div>" + 
                        "<div class='returnOnAssetInPercentage'>" + returnOnAssetInPercentage + "</div>" + 
                        "<div class='rotcInPercentage'>" + rotcInPercentage + "</div>" +
                    "</td>" +
                    "<td>" + 
                        "<div class='epsGrowthInPercentage'>" + epsGrowthInPercentage + "</div>" + 
                        "<div class='patGrowthInPercentage'>" + patGrowthInPercentage + "</div>" + 
                        "<div class='revenueGrowthInPercentage'>" + revenueGrowthInPercentage + "</div>" +
                    "</td>" +
                    "<td>" + 
                        "<div class='netOperatingCashFlow'>" + netOperatingCashFlow + "</div>" + 
                        "<div class='totalFreeCashFlow'>" + totalFreeCashFlow + "</div>" +
                    "</td>" +
                "</tr>";
            }

            $("#broker_table tbody").html(htmlCode);

            var paginationHtml = "<div class='paging_container'>"
                                + "<ul class='pager'>"
                                 + "<li><a data-toggle='tooltip' title='First' id='first' href='javascript:void(0)''><<</a></li>"
                                 + "<li><a data-toggle='tooltip' title='Previous' id='prev' href='javascript:void(0)'><</a></li>"
                                 + "<li><span id='records_stats'></span></li>"
                                 + "<li><a data-toggle='tooltip' title='Next' id='next' href='javascript:void(0)'>></a></li>"
                                 + "<li><a data-toggle='tooltip' title='Last' id='last' href='javascript:void(0)'>>></a></li>"
                                + "</ul>"
                             + "</div>";

            $("#fv_custom_screener_search").append(paginationHtml);

            $('#fv_custom_screener_search .pager a').off().on('click', {this: classRef}, classRef.getPaginationIndex);

            $("#broker_table thead th a").off().on('click', {this: classRef}, classRef.getSortedByValue);

            classRef.setRecordStats();

            $('#fv_custom_screener_search .max_per_page select').off().on('change', {this: classRef}, classRef.getPerPageMaxRecords);
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
            classRef.getCustomScreenerData();
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
            classRef.getCustomScreenerData();
        },

        setRecordStats : function() {
            var classRef = this;

            if(classRef.currentIndex > classRef.lastPageNumber) {
                classRef.currentIndex = classRef.lastPageNumber;
            }

            $("#fv_custom_screener_search #records_stats").html(classRef.pageNumber + " of " + classRef.lastPageNumber);
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
                classRef.getCustomScreenerData();
            }
        },

        getLastPage : function() {
            var classRef = this;

            if(classRef.pageNumber != classRef.lastPageNumber) {
                classRef.pageNumber = classRef.lastPageNumber;
                classRef.currentIndex = (classRef.pageNumber - 1) * classRef.perPageMaxRecords + 1;
                classRef.getCustomScreenerData();
            }
        },

        getNextPage : function() {
            var classRef = this;

            if(classRef.pageNumber < classRef.lastPageNumber) {
                classRef.pageNumber = classRef.pageNumber + 1;
                classRef.currentIndex = classRef.currentIndex + classRef.perPageMaxRecords;
                classRef.getCustomScreenerData();
            }
        },

        getPreviousPage : function() {
            var classRef = this;

            if(classRef.pageNumber > 1) {
                classRef.pageNumber = classRef.pageNumber - 1;
                classRef.currentIndex = classRef.currentIndex - classRef.perPageMaxRecords;
                classRef.getCustomScreenerData();
            }
        },

        getCustomScreenerData: function() {
            var classRef = this;

            classRef.getRecordStatsApi().then(function(stats) {
                stats = JSON.parse(stats);
                classRef.firstPageNumber = stats.firstPageNumber;
                classRef.lastPageNumber = stats.lastPageNumber;
                classRef.totalRecords = stats.totalRecords;

                $("#fv_custom_screener_search #total_records_count").html(classRef.totalRecords + " Results");

                classRef.getCustomScreenerApi().then(function(serverResponse) {
                    $("#fv_custom_screener_search .paging_container").remove();
                    serverResponse = JSON.parse(serverResponse);
                    classRef.getCustomScreenerHtml(serverResponse);
                    isProgressLoader(false);
                }, function(error) {
                    console.log(error);
                    isProgressLoader(false);
                    $("#broker_table tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
                });
            }, function(error) {
                $("#broker_table tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
            });
        },

        /**
         * Function to start async call to get filter data.
         */
        getCustomScreenerApi : function() {
            var classRef = this;

            var url = "/system/api/customscreeners?pageNumber=" + classRef.pageNumber + "&perPageMaxRecords=" + classRef.perPageMaxRecords + "&sortBy=" + classRef.sortByValue + "&orderBy=" + classRef.orderBy;
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
        },

        /**
         * Function to start async call to get filter data.
         */
        getRecordStatsApi : function() {
            var classRef = this;

            var url = "/system/api/customscreeners/recordstats?perPageMaxRecords=" + classRef.perPageMaxRecords;
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
        },

        /**
         * Function to start async call to get filter data.
         */
        getFilterData : function() {
            var url = "/system/api/customscreeners/filters";
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
        },

        setFilterData : function() {
            var classRef = this;

            classRef.getFilterData().then(function(response) {
                /*var response = {
                    'listData':[
                        {
                            'label': 'Industry',
                            'value':['a','b']
                        }
                    ],
                    'sliderData':[
                        {
                            'id': 'mcap',
                            'label': 'Market Capitalisation',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'pe',
                            'label': 'P/E(Trailing)',
                            'min': '1',
                            'max': '20'
                        },
                        {
                            'id': 'pb',
                            'label': 'Price to Book value',
                            'min': '0.1',
                            'max': '1.5'
                        },
                        {
                            'id': 'de',
                            'label': 'Debt to Equity Ratio',
                            'min': '100',
                            'max': '400'
                        },
                        {
                            'id': 'cr',
                            'label': 'Current Ratio',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'npcf',
                            'label': 'Net Operating Cash Flow',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'roe',
                            'label': 'ROE (AVERAGE 3 YR)',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'opm',
                            'label': 'Operating profit margin',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'pat',
                            'label': 'PAT Growth (avr 3 yrs)',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'eps',
                            'label': 'EPS growth (avr 3 yrs)',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'revenue',
                            'label': 'Revenue growth (avr 3 yrs)',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'tfcf',
                            'label': 'Total Free Cash Flow',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'ra',
                            'label': 'Return on assets',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'dy',
                            'label': 'Dividend Yield',
                            'min': '1',
                            'max': '2'
                        },
                        {
                            'id': 'rotc',
                            'label': 'Return on Total Capital',
                            'min': '1',
                            'max': '2'
                        }
                    ]
                };*/

                response = JSON.parse(response);
                classRef.setFilterHtml(response);
            }, function(error) {

            });
        },

        setFilterHtml : function(response) {
            var classRef = this;

            classRef.setIndustryFilterData(response).then(function(industryResponse) {

                var sliderHtml = '';
                var len = response.sliderData.length;
                var z = {};

                for(var i = 0; i < len; i++) {
                    var min = 0,
                        max = 0;

                    if(response.sliderData[i].id == "mcap" || response.sliderData[i].id == "netOperatingCashFlow"
                        || response.sliderData[i].id == "totalFreeCashFlow") {
                        min = Math.floor(response.sliderData[i].min/1000);
                        max = Math.ceil(response.sliderData[i].max/1000);
                    } else {
                        min = Math.floor(response.sliderData[i].min);
                        max = Math.ceil(response.sliderData[i].max);
                    }
                    var sliderStep = parseFloat((max - min)/10).toFixed(2);
                    var sliderValue = "[" + min + "," + max + "]";

                    z[i] = response.sliderData[i].id;

                    sliderHtml = sliderHtml + "<div class='widget widget-panel-default'>"
                                +   "<div class='widget-panel-heading'>"
                                +       "<h3>" + response.sliderData[i].label + "</h3>"
                                +   "</div>"
                                +   "<div class='widget-panel-body'>"
                                +       "<div id='search_by_" + response.sliderData[i].id + "' class='ui-content'>"
                                +           "<input id='slider_" + i + "' type='text' class='slider_input' value='' data-slider-min='" + min + "' data-slider-max='" + max + "' data-slider-step='" + sliderStep + "' data-slider-value='" + sliderValue + "' />"
                                +           "<div class='data_input'>" 
                                +               "<input id='" + response.sliderData[i].id + "_min' type='text' value='" + min + "' />"
                                +               "<input id='" + response.sliderData[i].id + "_max' type='text' value='" + max + "' />" 
                                +           "</div>"
                                +       "</div>"
                                +   "</div>"
                                + "</div>";
                }
                $("#sidebar-panel .widget-group").append(sliderHtml);

                for(var i = 0; i < len; i++) {

                    var id = '#slider_' + i;

                    z[i] = $(id).slider();
                }



                $(".data_input input").on('change', function(e) {
                    if(($(e.currentTarget).attr('id')).indexOf('_min') > 0) {
                        doItYourselfObj.handleMin(e);
                    } else if(($(e.currentTarget).attr('id')).indexOf('_max')) {
                        doItYourselfObj.handleMax(e);
                    }
                });


                z[0].on("change", function(sliderValue) {
                    $("#mcap_min").val(sliderValue.value.newValue[0]);
                    $("#mcap_max").val(sliderValue.value.newValue[1]);
                });

                z[1].on("change", function(sliderValue) {
                    $("#pe_min").val(sliderValue.value.newValue[0]);
                    $("#pe_max").val(sliderValue.value.newValue[1]);
                });

                z[2].on("change", function(sliderValue) {
                    $("#pb_min").val(sliderValue.value.newValue[0]);
                    $("#pb_max").val(sliderValue.value.newValue[1]);
                });

                z[3].on("change", function(sliderValue) {
                    $("#debtToEquityRatio_min").val(sliderValue.value.newValue[0]);
                    $("#debtToEquityRatio_max").val(sliderValue.value.newValue[1]);
                });

                z[4].on("change", function(sliderValue) {
                    $("#currentRatio_min").val(sliderValue.value.newValue[0]);
                    $("#currentRatio_max").val(sliderValue.value.newValue[1]);
                });

                z[5].on("change", function(sliderValue) {
                    $("#netOperatingCashFlow_min").val(sliderValue.value.newValue[0]);
                    $("#netOperatingCashFlow_max").val(sliderValue.value.newValue[1]);
                });

                z[6].on("change", function(sliderValue) {
                    $("#roeInPercentage_min").val(sliderValue.value.newValue[0]);
                    $("#roeInPercentage_max").val(sliderValue.value.newValue[1]);
                });

                z[7].on("change", function(sliderValue) {
                    $("#operatingProfitMargin_min").val(sliderValue.value.newValue[0]);
                    $("#operatingProfitMargin_max").val(sliderValue.value.newValue[1]);
                });

                z[8].on("change", function(sliderValue) {
                    $("#patGrowthInPercentage_min").val(sliderValue.value.newValue[0]);
                    $("#patGrowthInPercentage_max").val(sliderValue.value.newValue[1]);
                });

                z[9].on("change", function(sliderValue) {
                    $("#epsGrowthInPercentage_min").val(sliderValue.value.newValue[0]);
                    $("#epsGrowthInPercentage_max").val(sliderValue.value.newValue[1]);
                });

                z[10].on("change", function(sliderValue) {
                    $("#revenueGrowthInPercentage_min").val(sliderValue.value.newValue[0]);
                    $("#revenueGrowthInPercentage_max").val(sliderValue.value.newValue[1]);
                });

                z[11].on("change", function(sliderValue) {
                    $("#totalFreeCashFlow_min").val(sliderValue.value.newValue[0]);
                    $("#totalFreeCashFlow_max").val(sliderValue.value.newValue[1]);
                });

                z[12].on("change", function(sliderValue) {
                    $("#returnOnAssetInPercentage_min").val(sliderValue.value.newValue[0]);
                    $("#returnOnAssetInPercentage_max").val(sliderValue.value.newValue[1]);
                });

                z[13].on("change", function(sliderValue) {
                    $("#divYield_min").val(sliderValue.value.newValue[0]);
                    $("#divYield_max").val(sliderValue.value.newValue[1]);
                });

                z[14].on("change", function(sliderValue) {
                    $("#rotcInPercentage_min").val(sliderValue.value.newValue[0]);
                    $("#rotcInPercentage_max").val(sliderValue.value.newValue[1]);
                });
            });

        },

        handleMin: function(e) {
            console.log('handle min');
            var target = $(e.currentTarget);
            var elemId = '#' + target.parent().siblings('input').attr('id');
            var currentValue = target.val();
            var minSliderValue = $(elemId).data("slider-min");
            var maxSliderValue = $(elemId).data("slider-max");

            var min = parseFloat(currentValue) < minSliderValue ? minSliderValue 
                    : parseFloat(currentValue) > maxSliderValue ? maxSliderValue 
                    : parseFloat(currentValue);

            if(min > e.target.nextElementSibling.value) {
                min = minSliderValue;
            }
            min = (isNaN(min)) ? minSliderValue : min;
            target.val(min);

            var max = $(elemId).data('slider').getValue()[1];
            max = parseFloat(max) > maxSliderValue ? maxSliderValue : parseFloat(max);
            max = (isNaN(max)) ? maxSliderValue : max;

            var value = JSON.parse("[" + min + "," + max + "]");
            $(elemId).slider('setValue',value);
        },

        handleMax: function(e) {
            console.log('handle max');
            var target = $(e.currentTarget);
            var elemId = '#' + target.parent().siblings('input').attr('id');
            var currentValue = target.val();
            var minSliderValue = $(elemId).data("slider-min");
            var maxSliderValue = $(elemId).data("slider-max");

            var max = parseFloat(currentValue) > maxSliderValue ? maxSliderValue 
                    : parseFloat(currentValue) < minSliderValue ? minSliderValue 
                    : parseFloat(currentValue);

            if(max < target.siblings().val()) {
                max = maxSliderValue;
            }
            max = (isNaN(max)) ? maxSliderValue : max;
            target.val(max);

            var min = $(elemId).data('slider').getValue()[0];
            min = parseFloat(min) > maxSliderValue ? minSliderValue : parseFloat(min);
            min = (isNaN(min)) ? minSliderValue : min;


            var value = JSON.parse("[" + min + "," + max + "]");
            $(elemId).slider('setValue',value);
        },

        /**
        * Function to set Industry from filter data API.
        */
        setIndustryFilterData: function(response) {
            var classRef = this;

            return new Promise(function(resolve, reject) {
                response = response.industryData[0].value;
                
                //console.log(response);
                var html = "<li>"
                                    + "<div class='row'>"
                                        + "<div class='col-xs-9'>"
                                        + "<span>All</span>"
                                        + "</div>"
                                        + "<div class='col-xs-3'>"
                                            + "<input type='checkbox' data-name='all' data-section='' data-value='all' />"
                                        + "</div>"
                                    + "</div>"
                                + "</li>";

                var len = response.length;
                for(var i = 0; i < len; i++) {
                    html = html + "<li>"
                                    + "<div class='row'>"
                                        + "<div class='col-xs-9'>"
                                        + "<span>" + response[i] + "</span>"
                                        + "</div>"
                                        + "<div class='col-xs-3'>"
                                            + "<input type='checkbox' data-name='" + response[i] + "' data-section='' data-value='" + response[i] + "' />"
                                            + "<label for='industry'></label>"
                                        + "</div>"
                                    + "</div>"
                                + "</li>"
                }
                $("#search_by_industry ul").html(html);
                $("#search_by_industry ul input").on('change', {this: classRef},  classRef.getIndustryFilterData);

                resolve(true);
            });
                // $("#search_by_industry ul input").eq(0).prop('checked', true);
        },

        /**
         * Function to get Others from localstorage and get equity list
         */
        getIndustryFilterData : function(event) {
            var classRef = event.data.this;

            classRef.addRemoveItemFromArray(classRef.industryFilterData, $(this).attr('data-value'));


            if($(this).attr('data-value') == 'all') {
                classRef.industryFilterData = ['all'];
                if(classRef.checkForAllData(classRef.industryFilterData, "#search_by_industry ul input")) {
                    classRef.industryFilterData = [];
                } 
            } else {
                $("#search_by_industry ul input").eq(0).prop('checked', false);
            }
        },

        // Find and remove item from an array
        addRemoveItemFromArray : function(array, item) {
            var i = array.indexOf(item);
            if(i != -1) {
                array.splice(i, 1);
            } else {
                array.push(item);
            }
            return array;
        },

        checkForAllData : function(filterData, element) {
            var classRef = this;

            var arr = $(element);
            for(key in arr) {
                if (!isNaN(key)) {
                    classRef.addRemoveItemFromArray(filterData, $(arr[key]).attr('data-value'));
                }
            }

            $(element).prop('checked', !$(element).prop('checked'));
            if($(element).prop('checked')) {
                $(element).prop('checked', false);
                return true;
            } else {
                $(element).prop('checked', true);
            }

            return false;
        }

    };

    doItYourselfObj.init();

});