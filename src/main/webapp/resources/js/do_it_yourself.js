jQuery(document).ready(function() {


    var resetFilters = function(e) {
        clearSelection();
        //resetPaginationCount();
    };

    var clearSelection = function() {
        $(".slider_input").slider("refresh", {});
    };

    $('#sidebar-panel .sidebar-heading span').on('click', resetFilters);

    /**
     * Function to start async call to get filter data.
     */
    var getFilterData = function() {
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
    };

    var setFilterData = function() {
        getFilterData().then(function(response) {
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
            setFilterHtml(response);
        }, function(error) {

        });
    };

    var setFilterHtml = function(response) {
        doItYourselfObj.setIndustryFilterData(response).then(function(industryResponse) {

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

    };

    /**
     * Function to start async call to filter data API and set response.
     */
    var loadDefaultFilterData = function() {
        setFilterData();
    }

    loadDefaultFilterData();

    var doItYourselfObj = {
        init: function() {
            this.industryFilterData = [];
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