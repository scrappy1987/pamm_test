"use strict";
(function () {

    angular.module("securityManager")
        .decorator("securityManager",
        [SecurityManager]);

    function SecurityManager($delegate) {

        var login = $delegate.login;
        $delegate.login = function (credentials) {
            return login(credentials);
        };

        return $delegate;
    }
}());