"use strict";

angular.module("pamm").service("projectRepository", ["$q", "$log", "dal",  "$rootScope", "contextEvent", "userContext", "projectDao",
    function ($q, $log, dal, $rootScope, contextEvent, userContext, projectDao) {
        var projectCache = [];

        (function init() {
            $rootScope.$on(contextEvent.CLEAR_CONTEXT, function clearContext() {
                projectCache = [];
                $log.debug("projectRepository: context cleared");
            })
        })();

        this.getProjectById = function (projectId) {
            return _.find(projectCache, function (project) {
                return project.id == projectId;
            });
        };

        this.getUserProjects = function (userId) {
            var deferred = $q.defer();

            if (userId == undefined || userId == null) {
                var user = userContext.getUser();
                userId = user.id;
            }

            if (projectCache.length == 0) {
                projectDao.getUserProjects(userId).then(function (results) {
                    projectCache = results;
                    deferred.resolve(results);
                }, function (error) {
                    deferred.reject(error);
                });
            } else {
                deferred.resolve(projectCache);
            }
            return deferred.promise;
        };

        this.saveProject = function (projectToSave) {
            var deferred = $q.defer();

            if (projectToSave.hasOwnProperty("id")) {
                projectDao.updateProject(projectToSave).then(function (updatedProject) {
                    _.remove(projectCache, function (project) {
                        return project.id == updatedProject.id;
                    });
                    projectCache.push(updatedProject);
                    deferred.resolve(updatedProject);
                }, function (error) {
                    deferred.reject(error);
                });
            } else {
                projectDao.saveProject(projectToSave).then(function (savedProject) {
                    projectCache.push(savedProject);
                    deferred.resolve(savedProject);
                }, function (error) {
                    deferred.reject(error);
                });
            }
            return deferred.promise;
        };

        this.deleteProject = function (projectToDelete) {
            var deferred = $q.defer();

            projectDao.deleteProject(projectToDelete).then(function () {
                _.remove(projectCache, {id: projectToDelete.id});
                deferred.resolve(projectCache);
            }, function (error) {
                deferred.reject(error);
            });

            return deferred.promise;
        };
    }]);

