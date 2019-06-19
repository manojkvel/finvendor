jQuery(document).ready(function() {

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
            this.selectedFilterBody = {};
            this.setFilterData(); //Function to start async call to filter data API and set response.
            this.getCustomScreenerData();
        },

        clearSelection : function() {
            $(".slider_input").slider("refresh", {});

            $("#sidebar-panel #search_by_industry input").prop('checked', false);
        },

        resetFilters : function(event) {
            var classRef = event.data.this;
            classRef.clearSelection();
            $("#slider_0").slider().trigger("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#mcap_min").val(min);
                    $("#mcap_max").val(max);

                    classRef.handleSliderFilterJson('mcap', min, max);
                });
            //resetPaginationCount();
            classRef.getCustomScreenerData();
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

                var mcap = (response[i].mcap) ? parseFloat(response[i].mcap/1000).toFixed(2) : '-';


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

                var netOperatingCashFlow = (response[i].netOperatingCashFlow) ? parseFloat(response[i].netOperatingCashFlow/1000).toFixed(2) : '-';
                var totalFreeCashFlow = (response[i].totalFreeCashFlow) ? parseFloat(response[i].totalFreeCashFlow/1000).toFixed(2) : '-';

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

        updateFilterByValue: function(event) {
            var classRef = event.data.this;
            classRef.getCustomScreenerData();
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
            classRef.isProgressLoader(true);

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
                    classRef.isProgressLoader(false);
                }, function(error) {
                    console.log(error);
                    classRef.isProgressLoader(false);
                    $("#broker_table tbody").html("<tr><td colspan='9'>We are not able to get the info, please try again later.</td></tr>");
                });
            }, function(error) {
                classRef.isProgressLoader(false);
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
                            //console.log(httpRequest.status + httpRequest.responseText);
                            reject(httpRequest.responseText);
                        }
                    } else {
                    }
                };

                httpRequest.send(JSON.stringify(classRef.selectedFilterBody));
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
                            //console.log(httpRequest.status + httpRequest.responseText);
                            reject(httpRequest.responseText);
                        }
                    } else {
                    }
                };

                httpRequest.send(JSON.stringify(classRef.selectedFilterBody));
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


            $('#sidebar-panel .sidebar-heading span').on('click', {this: classRef}, classRef.resetFilters);

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
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#mcap_min").val(min);
                    $("#mcap_max").val(max);

                    classRef.handleSliderFilterJson('mcap', min, max);
                });

                z[1].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#pe_min").val(min);
                    $("#pe_max").val(max);

                    classRef.handleSliderFilterJson('pe', min, max);
                });

                z[2].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#pb_min").val(min);
                    $("#pb_max").val(max);

                    classRef.handleSliderFilterJson('pb', min, max);
                });

                z[3].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#debtToEquityRatio_min").val(min);
                    $("#debtToEquityRatio_max").val(max);

                    classRef.handleSliderFilterJson('debtToEquityRatio', min, max);
                });

                z[4].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#currentRatio_min").val(min);
                    $("#currentRatio_max").val(max);

                    classRef.handleSliderFilterJson('currentRatio', min, max);
                });

                z[5].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#netOperatingCashFlow_min").val(min);
                    $("#netOperatingCashFlow_max").val(max);

                    classRef.handleSliderFilterJson('netOperatingCashFlow', min, max);
                });

                z[6].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#roeInPercentage_min").val(min);
                    $("#roeInPercentage_max").val(max);

                    classRef.handleSliderFilterJson('roeInPercentage', min, max);
                });

                z[7].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#operatingProfitMargin_min").val(min);
                    $("#operatingProfitMargin_max").val(max);

                    classRef.handleSliderFilterJson('operatingProfitMargin', min, max);
                });

                z[8].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#patGrowthInPercentage_min").val(min);
                    $("#patGrowthInPercentage_max").val(max);

                    classRef.handleSliderFilterJson('patGrowthInPercentage', min, max);
                });

                z[9].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#epsGrowthInPercentage_min").val(min);
                    $("#epsGrowthInPercentage_max").val(max);

                    classRef.handleSliderFilterJson('epsGrowthInPercentage', min, max);
                });

                z[10].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#revenueGrowthInPercentage_min").val(min);
                    $("#revenueGrowthInPercentage_max").val(max);

                    classRef.handleSliderFilterJson('revenueGrowthInPercentage', min, max);
                });

                z[11].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#totalFreeCashFlow_min").val(min);
                    $("#totalFreeCashFlow_max").val(max);

                    classRef.handleSliderFilterJson('totalFreeCashFlow', min, max);
                });

                z[12].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#returnOnAssetInPercentage_min").val(min);
                    $("#returnOnAssetInPercentage_max").val(max);

                    classRef.handleSliderFilterJson('returnOnAssetInPercentage', min, max);
                });

                z[13].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#divYield_min").val(min);
                    $("#divYield_max").val(max);

                    classRef.handleSliderFilterJson('divYield', min, max);
                });

                z[14].on("change", function(sliderValue) {
                    var min = sliderValue.value.newValue[0];
                    var max = sliderValue.value.newValue[1];

                    $("#rotcInPercentage_min").val(min);
                    $("#rotcInPercentage_max").val(max);

                    classRef.handleSliderFilterJson('rotcInPercentage', min, max);
                });
            });

        },

        isEmpty : function(obj) {
            for(var key in obj) {
                if(obj.hasOwnProperty(key))
                    return false;
            }
            return true;
        },

        handleSliderFilterJson: function(target, min, max) {
            var classRef = this;
            var sliderName = (target.context != undefined) ? target.context.id.split('_')[0] : target;

            min = (sliderName == "mcap" || sliderName == "netOperatingCashFlow" || 
                sliderName == "totalFreeCashFlow") ? parseFloat(min * 1000).toFixed(2) : parseFloat(min).toFixed(2);
            max = (sliderName == "mcap" || sliderName == "netOperatingCashFlow" || 
                sliderName == "totalFreeCashFlow") ? parseFloat(max * 1000).toFixed(2) : parseFloat(max).toFixed(2);

            try {

                var filterData = {
                    'min' : min,
                    'max': max
                };
                classRef.selectedFilterBody[sliderName] = filterData;

                if(classRef.isEmpty(filterData)) {
                    delete classRef.selectedFilterBody[sliderName];
                }
            } 

            catch(err) {
                console.log("error in handleSliderFilter");
            }

            finally {
                classRef.getCustomScreenerData();
            }
        },

        handleMin: function(e) {
            console.log('handle min');
            var classRef = this;
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

            classRef.handleSliderFilterJson(target, min, max);

        },

        handleMax: function(e) {
            console.log('handle max');
            var classRef = this;
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

            classRef.handleSliderFilterJson(target, min, max);
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

            classRef.selectedFilterBody.industry = classRef.industryFilterData;

            if(classRef.industryFilterData.length === 0) {
                delete classRef.selectedFilterBody.industry;
            }

            classRef.getCustomScreenerData();
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
        },

        isProgressLoader : function(status) {
            if(status === true) {
                $("#progressLoader").show();
            } else {
                $("#progressLoader").hide();
            }
        }

    };

    doItYourselfObj.init();

});