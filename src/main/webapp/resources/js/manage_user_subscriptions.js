jQuery(document).ready(function() {

    /*
    ** Pricing for the products
    */

    var manageSubscriptionsObj = {
        init: function() {
            var classRef = this;
            this.getAllSubscriptionsDetails();
        },

        getAllSubscriptionsDetails: function() {
            var classRef = this;
            isProgressLoader(true);

            classRef.getAllSubscriptionApi(classRef.userId).then(function(response){
                classRef.populateSubscriptionsHtml(response);
            }, function(error) {
                var error = JSON.parse(error);
                $("tbody").html("<tr><td colspan='9'>No Records Found</td></tr>");
            });
        },

        populateSubscriptionsHtml: function(response) {
            var response = JSON.parse(response);
            response = response.data;
            var len = response.length;
            var htmlCode = '';
            var rowHtml =   "";

            if(len === 0) {
                $("tbody").html("<tr><td colspan='9'>No Records Found</td></tr>");
                $("#manage_user_subscriptions .deleteBtn").attr("disabled", "disabled");
                $('#manage_user_subscriptions #user_subscriptions_table input[name=selectAll]').prop('checked', false);
                return;
            }

            for(var i = 0; i < len; i++) {

                htmlCode = htmlCode + "<tr data-id='" + response[i].subscriptionId + "'>" +
                "<td>" + 
                "<div class='userName'>" + response[i].userName + "</div>" +
                "</td>" + 
                "<td>" + 
                "<div class='subscriptionType'>" + response[i].subscriptionType + "</div>" + 
                "</td>" + 
                "<td>" + 
                "<div class='paymentMode'>" + response[i].paymentMode + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='transactionDate'>" + response[i].transactionDate + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='transactionRefNumber'>" + response[i].transactionRefNumber + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='bankName'>" + response[i].bankName + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='bankHolderName'>" + response[i].bankHolderName + "</div>" + 
                "</td>" +
                "<td>" + 
                "<div class='amountTransferred'>" + response[i].amountTransferred + "</div>" + 
                "</td>" +
                "<td>" + 
                    "<input name='subscriptions" + i + "' class='submit-button' type='checkbox' />" + 
                "</td>" +
                "</tr>";
            }

            $("#user_subscriptions_table tbody").html(htmlCode);

            $("#manage_user_subscriptions .deleteBtn").attr("disabled", "disabled");

            //$("#manage_user_subscriptions #user_subscriptions_table tbody input[type=checkbox]").on('click', updateUserSubscription);
        },

        updateUserSubscription: function() {

        },

        /**
        * Function to start async call to get filter data.
        */
        postBankFormApi : function() {
            var classRef = this;

            var url = "/api/users/" + classRef.bankFormData.userId + "/subscriptions";
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

                httpRequest.send(JSON.stringify(classRef.bankFormData));
            });
        },

        /**
        * Function to start async call to get all user subscribed data.
        */
        getAllSubscriptionApi : function(userId) {
            var classRef = this;

            return new Promise(function(resolve, reject) {

                if(userId != '') {
                    var url = "/api/subscriptions";
                    var httpRequest = new XMLHttpRequest({
                        mozSystem: true
                    });
                    //httpRequest.timeout = API_TIMEOUT_SMALL;
                    httpRequest.open('GET', url, true);
                    httpRequest.setRequestHeader('Content-Type',
                    'application/json');
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

                    httpRequest.send();
                } else {
                    reject(false)
                }
            });
        }

    };

    manageSubscriptionsObj.init();

});