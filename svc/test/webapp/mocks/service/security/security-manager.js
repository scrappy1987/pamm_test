"use strict";

(function () {

    angular.module("securityManager", [])
        .service("securityManager",
        ["$q", "$rootScope", "$state", "$location", "$log", "$http", SecurityManager]);


    function SecurityManager($q, $rootScope, $state, $location, $log, $http) {

            var hasAuthenticated = false;
            var userCredentials;

            this.getUserCredentials = function () {
                return userCredentials;
            };

            this.setUserCredentials = function (credentials) {
                userCredentials = credentials;
            };

            this.login = function (credentials) {

                this.setUserCredentials(credentials);

                var deferred = $q.defer();
                //This is an mocked example of a returned token
                deferred.resolve({token : "mock-token"});
                return deferred.promise;

                //If valid credentials a token is returned from the authentication server
                //try {
                //    hasAuthenticated = true;
                //
                //    // replace with commented out code for real authentication
                //    // See security-manager-JWT for example of "plugging" in other mechanism
                //    //$http.post('/authenticate', credentials).then(function(response) {
                //    //    deferred.resolve(response);
                //    //}, function(response) {
                //    //    deferred.reject(response);
                //    //});
                //} catch (e) {
                //    deferred.reject(e);
                //}
                //return deferred.promise;
            };

            //$rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            //    var loginState = (toState.name === "login") || (toState.name === "register");
            //    if (!hasAuthenticated && !loginState) {
            //        $log.debug("Unauthenticated - Prevented navigation");
            //        event.preventDefault();
            //        $state.go("login");
            //    } else if (hasAuthenticated && loginState) {
            //        $log.debug("User has been authenticated - Prevented navigation to login");
            //        event.preventDefault();
            //    }
            //});
        }
}());