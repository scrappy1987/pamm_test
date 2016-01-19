"use strict";

angular.module("pamm").config(function ($stateProvider) {
    var $log = angular.injector(['ng']).get('$log');
    $stateProvider.state("user.login", {
        url: "/user/login",
        views: {
            "root-content@": {
                templateUrl: "feature/user/login/login.html"
            }
        }
    }).state("user.activated", {
        url: "/user/activated",
        views: {
            "root-content@": {
                templateUrl: "feature/user/login/activated.html"
            }
        }
    });

    $log.debug("Login states configured");
});
