"use strict";

(function () {
    angular.module("sse", [])
        .service("sseConnectionManager",
        ["$rootScope", SSEConnectionManager]);

    function SSEConnectionManager($rootScope) {

        this.createConnection = function(username){

        };
    }
}());
