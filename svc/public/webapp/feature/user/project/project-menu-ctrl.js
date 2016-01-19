"use strict";

angular.module("pamm").controller("projectMenuCtrl", ["$log", "userContext", "$state",
    function ($log, userContext, $state) {
        var vm = this;

        vm.hasSelectedProject = function () {
            return userContext.getSelectedProject() != undefined;
        };

        vm.manageProjects = function () {
            $state.go("user.project");
        };

        vm.newRootProject = function () {
            $state.go("user.project.edit");
        };

        vm.scheduleMeeting = function (project) {
            $state.go("user.project.meeting.edit", {projectId: userContext.getSelectedProject().id});
        };
    }]);
