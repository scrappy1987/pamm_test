"use strict";

angular.module("pamm").controller("projectCtrl", ["$log", "$state", "projectRepository", "userContext",
    function ($log, $state, projectRepository, userContext) {
        var vm = this;

        (function init() {
            vm.projects = [];
            vm.waiting = true;

            projectRepository.getUserProjects().then(function (results) {
                vm.waiting = false;
                vm.projects = results;
            }, function (error) {
                vm.waiting = false;
                $$dialog.error(" We've encountered a problem while loading your projects.  Please try again later");
                $log.error("projectCtrl - cannot load project because of " + error.message);
            })
        })();

        vm.setAsSelected = function (project) {
            userContext.setSelectedProject(project);
        };

        vm.isSelected = function (project) {
            var selectedProject = userContext.getSelectedProject();
            return (selectedProject != null && selectedProject != undefined && selectedProject.id == project.id);
        };

        vm.owner = function (project) {
            var owner = _.find(project.members, function (member) {
                return member.userId == project.ownerId;
            });
            return owner.forename + " " + owner.surname + " <" + owner.email + ">";
        };

        vm.isFavourite = function (project) {
            return userContext.isFavouriteProject(project);
        };

        vm.toggleFavourite = function (project) {
            if (userContext.isFavouriteProject(project)) {
                userContext.removeFavourite(project);
            } else {
                userContext.addFavourite(project);
            }
        };

        vm.edit = function (project) {
            $state.go("user.project.edit", {projectId: project.id});
        };

        vm.isWaiting = function() {
            return waiting;
        };
    }]);

