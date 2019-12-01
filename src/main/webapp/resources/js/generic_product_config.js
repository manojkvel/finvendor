jQuery(document).ready(function() {

    /*
    ** Pricing for the products
    */

    var productConfigObj = {
        init: function() {
            var classRef = this;
            this.baseApiUrl = '/api/configurations';
            classRef.getGeneralConfigDetails();
        },

        getGeneralConfigDetails: function() {
            var classRef = this;
            isProgressLoader(true);

            classRef.getGeneralSystemConfigApi(classRef.userId).then(function(serverResponse){
                classRef.populateGeneralConfigHtml(serverResponse);
                isProgressLoader(false);
            }, function(error) {
                var error = JSON.parse(error);
                isProgressLoader(false);
            });
        },

        populateGeneralConfigHtml: function(response) {
            var classRef = this;
            var response = JSON.parse(response);

            var featureAccessDailyLimit = (response.data[0].featureAccessDailyLimit) ? response.data[0].featureAccessDailyLimit : 0;
            var featureAccessWeeklyLimit = (response.data[0].featureAccessWeeklyLimit) ? response.data[0].featureAccessWeeklyLimit : 0;
            var trialPeriodInDays = (response.data[0].trialPeriodInDays) ? response.data[0].trialPeriodInDays : 0;
            var trialPeriodInDays = (response.data[0].trialPeriodInDays) ? response.data[0].trialPeriodInDays : 0;
            var emailEnabled = (response.data[0].emailEnabled) ? response.data[0].emailEnabled : false;

            $("#general_config .default_config_data #featureAccessDailyLimit_data").val(featureAccessDailyLimit);
            $("#general_config .default_config_data #featureAccessWeeklyLimit_data").val(featureAccessWeeklyLimit);
            $("#general_config .default_config_data #reminderDays_data").val(response.data[0].reminderDays);
            $("#general_config .default_config_data #trialPeriodInDays_data").val(trialPeriodInDays);
            $("#general_config .default_config_data #emailEnabled_data").val(emailEnabled);


            $("#general_config #general_config_form #featureAccessDailyLimit").val(featureAccessDailyLimit);
            $("#general_config #general_config_form #featureAccessWeeklyLimit").val(featureAccessWeeklyLimit);
            $("#general_config #general_config_form #reminderDays").val(response.data[0].reminderDays);
            $("#general_config #general_config_form #trialPeriodInDays").val(trialPeriodInDays);
            $("#general_config #general_config_form select[name=emailEnabled] option[value='"+ emailEnabled +"']").attr('selected', "selected");

            $("#general_config .default_config_data .action_btn .edit").off().on('click', {this: classRef}, classRef.editGeneralConfig)

            $("#general_config #general_config_form .action_btn .save").unbind().bind('click', {this: classRef}, classRef.updateGeneralConfig);

            $("#general_config #general_config_form").hide();
            $("#general_config .default_config_data").show();

        },

        editGeneralConfig: function(event) {
            var classRef = event.data.this;
            $("#general_config .default_config_data").hide();
            $("#general_config #general_config_form").show();
        },

        updateGeneralConfig: function(event) {
            var classRef = event.data.this;

            var formData = {
                "emailEnabled": $("#general_config #general_config_form select[name=emailEnabled]").val(),
                "featureAccessDailyLimit": $("#general_config #general_config_form #featureAccessDailyLimit").val(),
                "featureAccessWeeklyLimit": $("#general_config #general_config_form #featureAccessWeeklyLimit").val(),
                "reminderDays": $("#general_config #general_config_form #reminderDays").val(),
                "trialPeriodInDays": $("#general_config #general_config_form #trialPeriodInDays").val()
            }

            classRef.updateGeneralConfigApi(formData).then(function(response) {
                
                classRef.getGeneralConfigDetails();

            }, function(error) {
                debugger
            });
        },

        /**
        * Function to start async call to update generic system config data.
        */
        updateGeneralConfigApi : function(formData) {
            var classRef = this;

            var url = classRef.baseApiUrl + "/1/configuration";
            return new Promise(function(resolve, reject) {
                var httpRequest = new XMLHttpRequest({
                    mozSystem: true
                });
                //httpRequest.timeout = API_TIMEOUT_SMALL;
                httpRequest.open('PUT', url, true);
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

                httpRequest.send(JSON.stringify(formData));
            });
        },

        /**
        * Function to start async call to get all  generic system config.
        */
        getGeneralSystemConfigApi : function(userId) {
            var classRef = this;

            return new Promise(function(resolve, reject) {

                if(userId != '') {
                    var url = classRef.baseApiUrl;
                    var httpRequest = new XMLHttpRequest({
                        mozSystem: true
                    });
                    httpRequest.open('GET', url, true);
                    httpRequest.ontimeout = function () {
                        reject("" + httpRequest.responseText);
                    };
                    httpRequest.onreadystatechange = function () {
                        if (httpRequest.readyState === XMLHttpRequest.DONE) {
                            if (httpRequest.status === 200) {
                                resolve(httpRequest.response);
                            } else {
                                reject(false);
                            }
                        } else {
                            
                        }
                    };

                    if(classRef.isFirstPage) {
                        httpRequest.send(JSON.stringify(classRef.filterJsonObj));

                    } else {
                        httpRequest.send();
                    }
                } else {
                    reject(false)
                }
            });
        }
    };

    productConfigObj.init();

});