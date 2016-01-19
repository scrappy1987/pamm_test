"use strict";

angular.module("pamm").service("userDao", ["dal", "$log",
    function (dal, $log) {
        this.register = function (newUser) {
            return dal.http.POST("register/user", newUser);
        };

        $log.debug("dal:userDao Instantiated");
    }]);
