"use strict";

(function () {
    angular.module("dal", [])
        .service("dal",
        ["$log", Dal]);

    function Dal ($log) {
        $log.debug("DAL Instantiated");
    }
}());