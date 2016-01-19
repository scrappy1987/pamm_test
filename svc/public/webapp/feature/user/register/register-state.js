"use strict";

angular.module("pamm").config(function ($stateProvider) {
    var $log = angular.injector(['ng']).get('$log');
    $stateProvider.state("user.register", {
        url: "/user/register",
        params: {
            credentials: null
        },
        views: {
            "root-content@": {
                templateUrl: "feature/user/register/register.html"
            }
        }
    }).state("user.termsofuse", {
        url: "/termsofuse",
        data: {baseState: "user.register"},
        params: {
            credentials: null

        },
        views: {
            "root-content@": {
                templateUrl: "feature/common/terms-of-use/terms-of-use.html"
            }
        }
    });
    $log.debug("Register routes configured");
});
