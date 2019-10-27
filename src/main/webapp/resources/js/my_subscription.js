jQuery(document).ready(function() {
    var baseApiUrl = "/api/cis";

    /*
    ** My Subscription for the products
    */

    var mySubscriptionObj = {
        init: function() {
            var classRef = this;
            this.userId = $("input[name='loggedInUser']").val();
            this.pricingLookUp();
        },

        pricingLookUp: function() {
            var classRef = this;
            isProgressLoader(true);

            classRef.getUserSubscriptionApi(classRef.userId).then(function(response) {
            	response = JSON.parse(response);

            	$("#subscription_type").html("Current Subscription Type: <span>" + response.data.subscriptionType + "</span>");
            }, function(error) {
            });
        },

        /**
        * Function to start async call to get user subscribed data.
        */
        getUserSubscriptionApi : function(userId) {
            var classRef = this;

            return new Promise(function(resolve, reject) {

                if(userId != '') {
                    var url = "/api/users/" + userId + "/subscriptions";
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

    mySubscriptionObj.init();
    $("#subscribe .subscription_details button").on('click', function() {
    	window.location.href = "/view/pricing.jsp";
    });

});