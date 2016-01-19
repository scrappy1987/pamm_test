"use strict";

/**
 * The domain.repository manages holds the context of the current work item.
 */

angular.module("pamm").service("userContext", ["$log", "$rootScope", "$q", "$state", "authService", "contextEvent",
    function ($log, $rootScope, $q, $state, authService, contextEvent) {
        var user = null;
        var selectedProject = null;
        var favouriteProjects = [];
        var lastFromState;
        var lastFromParams;

        this.login = function (username, password) {
            var deferred = $q.defer();
            authService.login(username, password).then(
                function (successResponse) {
                    user = successResponse.user;
                    deferred.resolve(user);
                },
                function (error) {
                    deferred.reject(error);
                }
            );
            return deferred.promise;
        };

        this.logout = function () {
            user = null;
            selectedProject = null;
            favouriteProjects = [];
            lastFromState = null;
            lastFromParams = null;

            authService.logout();
            $rootScope.$emit(contextEvent.CLEAR_CONTEXT);
            $log.debug("Logged out");
        };

        this.getUser = function () {
            return user;
        };

        this.setSelectedProject = function (project) {
            selectedProject = project;
        };

        this.getSelectedProject = function () {
            return selectedProject;
        };

        this.isFavouriteProject = function (project) {
            for (var i = 0; i < favouriteProjects.length; i++) {
                if (favouriteProjects[i].id == project.id) {
                    return true;
                }
            }
            return false;
        };

        this.removeFavourite = function (project) {
            _.remove(favouriteProjects, function (favouriteProject) {
                return favouriteProject.id == project.id;
            });
        };

        this.addFavourite = function (project) {
            favouriteProjects.push(project);
        };

        this.getFavouriteProjects = function () {
            return favouriteProjects;
        };

        this.returnToLastState = function () {
            $state.go(lastFromState, lastFromParams);
        };

        (function init() {
            $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
                lastFromState = fromState;
                lastFromParams = fromParams;
            });
        })();

        $log.debug("userContext: Instantiated");
    }]);
