angular.module("pamm").decorator("$log", ["$delegate", function ($delegate) {

    var log = $delegate.log;
    var info = $delegate.info;
    var debug = $delegate.debug;
    var warn = $delegate.warn;
    var error = $delegate.error;


    $delegate.log = function (message) {
        log("[LOG] " + message);
        // TODO sent to server
    };

    $delegate.info = function (message) {
        info("[INFO] " + message);
        // TODO sent to server
    };

    $delegate.warn = function (message) {
        warn("[WARN] " + message);
        // TODO sent to server
    };

    $delegate.error = function (message) {
        error("[ERROR] " + message);
        // TODO sent to server
    };

    $delegate.debug = function (message) {

        debug("[DEBUG] " + message);
        // TODO sent to server
    };


    $delegate.debug("Audit: added logging");
    return $delegate;
}]);