"use strict";
(function () {
    angular
        .module("securityManager")
        .service("authInterceptor",
        ["$window", AuthInterceptorService]);

    /**
     * Adds token to each HTTP request
     * Token currently kept in HTML5 session storage but can be changed to another mechanism
     * This token is added to each request in app.js config
     */
    function AuthInterceptorService($window) {
        this.request = function (config) {
            if ($window.sessionStorage.token) {
                config.headers.Authorization = 'Bearer ' + $window.sessionStorage.token;
            }
            return config;
        }
    }
}());

