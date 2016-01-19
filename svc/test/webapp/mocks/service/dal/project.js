"use strict";

(function () {
    /**
     * Example Mock Project DAO - Mock DAO should not be in the real directory.  Next version of seed will use external configuration
     */
    angular.module("dal")
        .decorator("dal",
        ["$delegate", "$q", "$log", "$http", ProjectDal]);

    function ProjectDal ($delegate, $q, $log, $http) {
        var mockProjectId = 100;
        var mockProjects =
            [{
                id: 1,
                title: "My Noddy Project",
                summary: "This is just a test entry",
                info: "It is Really Really Noddy",
                status: "DRAFT"
            }, {
                id: 2,
                title: "Clarus - Project Review Management",
                summary: "Manage project meetings and document reviews",
                status: "Submitted"
            }, {
                id: 3,
                title: "Data Access Layer",
                summary: "High Level Design for WDL",
                status: "In Collabration"
            }];

        /**
         * This is where the $http REST goes
         * @param criteria
         * @returns {*}
         */
        $delegate.getProject = function (criteria) {
            var deferred = $q.defer();
            if (criteria == undefined || criteria == null) {
                deferred.resolve(_.cloneDeep(mockProjects));
            } else if (criteria.hasOwnProperty('id')) {
                if (mockProjects[criteria.id] != undefined) {
                    deferred.resolve(_.find(mockProjects, function (project) {
                        return project.id = criteria.id;
                    }));
                } else {
                    deferred.reject("No Project Found");
                }
            } else {
                deferred.reject("Unrecognised criteria");
            }

            return deferred.promise;
        };

        /**
         * This is where the $http REST goes
         * @param project
         * @returns {*}
         */
        $delegate.saveProject = function (project) {
            var deferred = $q.defer();
            if (!project.hasOwnProperty("id")) {
                project.id = mockProjectId++;
                project.status = "DRAFT";
                mockProjects.push(project);
            }
            deferred.resolve(project);
            return deferred.promise;
        };

        /**
         *  This is where the $http REST goes
         * @param projectToDelete
         * @returns {*}
         */
        $delegate.deleteProject = function (projectToDelete) {
            var deferred = $q.defer();
            _.remove(mockProjects, {
                id: projectToDelete.id
            });
            deferred.resolve(mockProjects);
            return deferred.promise;
        };


        $log.debug("DAL:Project Instantiated");
        // Returns the decorated DAL service back to angular
        return $delegate;
    }
}());
