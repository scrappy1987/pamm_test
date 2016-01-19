"use strict";
(function () {

    var app = angular.module("app", ["ui.router", "dal", "securityManager", "repository", "util", "sse"]).run(
        function ($window, $rootScope, $log) {

        });

    //pushes token onto each http request header
    app.config(function ($httpProvider) {
        $httpProvider.interceptors.push('authInterceptor');
    });

}());
