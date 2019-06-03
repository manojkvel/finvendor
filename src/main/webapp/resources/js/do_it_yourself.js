jQuery(document).ready(function() {

    /**
     * Function to start async call to get filter data.
     */
    var getFilterData = function() {
        var url = "/system/api/customscreener/filters";
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
            var response = {
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
            };

            //response = JSON.parse(response);
            setFilterHtml(response);
        }, function(error) {

        });
    };

    var setFilterHtml = function(response) {
        var sliderHtml = '';
        var len = response.sliderData.length;
        var z = {};

        for(var i = 0; i < len; i++) {
            var sliderStep = parseFloat((response.sliderData[i].max - response.sliderData[i].min)/10).toFixed(2);
            var sliderValue = "[" + response.sliderData[i].min + "," + response.sliderData[i].max + "]";

            z[i] = response.sliderData[i].id;

            sliderHtml = sliderHtml + "<div class='widget widget-panel-default'>"
                        +   "<div class='widget-panel-heading'>"
                        +       "<h3>" + response.sliderData[i].label + "</h3>"
                        +   "</div>"
                        +   "<div class='widget-panel-body'>"
                        +       "<div id='search_by_" + response.sliderData[i].id + "' class='ui-content'>"
                        +           "<input id='slider_" + i + "' type='text' class='span2' value='' data-slider-min='" + response.sliderData[i].min + "' data-slider-max='" + response.sliderData[i].max + "' data-slider-step='" + sliderStep + "' data-slider-value='" + sliderValue + "' />"
                        +           "<div class='data_input'>" 
                        +               "<input id='" + response.sliderData[i].id + "_min' type='text' value='" + response.sliderData[i].min + "' />"
                        +               "<input id='" + response.sliderData[i].id + "_max' type='text' value='" + response.sliderData[i].max + "' />" 
                        +           "</div>"
                        +       "</div>"
                        +   "</div>"
                        + "</div>";
        }
        $("#sidebar-panel .widget-group").html(sliderHtml);

        for(var i = 0; i < len; i++) {

            var id = '#slider_' + i;
            var key = response.sliderData[i].id;

            z[i] = new Slider(id, {});
            /*var min_id = "#" + key + "_min";
            var max_id = "#" + key + "_max";

            z[i].on("change", function(sliderValue) {
                debugger
                $(min_id).val(sliderValue.newValue[0]);
                $(max_id).val(sliderValue.newValue[1]);
            });*/
        }

        z[0].on("change", function(sliderValue) {
            $("#mcap_min").val(sliderValue.newValue[0]);
            $("#mcap_max").val(sliderValue.newValue[1]);
        });

        z[1].on("change", function(sliderValue) {
            $("#pe_min").val(sliderValue.newValue[0]);
            $("#pe_max").val(sliderValue.newValue[1]);
        });

        z[2].on("change", function(sliderValue) {
            $("#pb_min").val(sliderValue.newValue[0]);
            $("#pb_max").val(sliderValue.newValue[1]);
        });

        z[3].on("change", function(sliderValue) {
            $("#de_min").val(sliderValue.newValue[0]);
            $("#de_max").val(sliderValue.newValue[1]);
        });

        z[4].on("change", function(sliderValue) {
            $("#cr_min").val(sliderValue.newValue[0]);
            $("#cr_max").val(sliderValue.newValue[1]);
        });

        z[14].on("change", function(sliderValue) {
            $("#rotc_min").val(sliderValue.newValue[0]);
            $("#rotc_max").val(sliderValue.newValue[1]);
        });

        /*

        var minSliderValue = $("#slider_14").data("slider-min");
        var maxSliderValue = $("#slider_14").data("slider-max");

        $("#rotc_min").on('change', function(e) {
           var val = this.value;
           this.value = val > maxSliderValue ? maxSliderValue : val;

           slider1.Slider();
           $("#slider_14").slider(val,"1.3");
           // $("#slider_14").slider('refresh');
        });*/

        //var slider0 = new Slider('#slider_0', {});
        //var slider1 = new Slider('#slider_1', {});
    };

    /**
     * Function to start async call to filter data API and set response.
     */
    var loadDefaultFilterData = function() {
        setFilterData();
    }

    loadDefaultFilterData();

});