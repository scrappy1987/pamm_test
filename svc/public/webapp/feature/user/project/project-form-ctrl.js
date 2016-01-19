"use strict";

angular.module("pamm").controller("projectFormCtrl", ["$log", "$scope", "$state", "$stateParams", "$uibModal", "projectRepository", "userContext",
    function ($log, $scope, $state, $stateParams, $uibModal, projectRepository, userContext) {
        var vm = $scope;
        var membersRemovedCount = 0;

        (function init() {
            if ($stateParams.hasOwnProperty("projectId") && $stateParams.projectId != "") {
                vm.headingText = "Amend Project";
                vm.project = _.cloneDeep(projectRepository.getProjectById($stateParams.projectId));
            } else {
                vm.headingText = "Create New Project";
                vm.project = {
                    members: []
                };
            }
        })();

        vm.saveProject = function (projectForm) {
            if (projectForm.$valid) {
                var waitingDialog = $$dialog.waiting("Please wait - Creating Project");

                projectRepository.saveProject(vm.project).then(function (project) {
                    waitingDialog.close();
                    $$dialog.success("Project Saved successfully",
                        function () {
                            userContext.returnToLastState();
                        });
                }, function (error) {
                    waitingDialog.close();
                    $$dialog.error("Unable to save project due to system error");
                });
            }
        };

        vm.cancel = userContext.returnToLastState;

        vm.addMember = function () {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'feature/user/project/member-form.html',
                controller: 'memberFormCtrl',
                size: "xs",
                backdrop: 'static'
            });

            modalInstance.result.then(
                function (newMember) {
                    if (newMember.email == userContext.getUser().email) {
                        $$dialog.error("There is no need to add yourself as you are the owner of this project");
                    } else {
                        var searchResult = _.find(vm.project.members, function (member) {
                            return member.email == newMember.email
                        });

                        if (searchResult) {
                            if (searchResult.sessionStatus == "REMOVED") {
                                delete searchResult.sessionStatus;
                            } else {
                                $$dialog.error(newMember.email + " has already been added to this project team");
                            }
                        } else {
                            newMember.sessionStatus = "NEW";
                            vm.project.members.push(newMember);
                        }
                    }
                }, function dismissed() {
                }
            );
        };

        vm.removeMember = function (memberToRemove) {
            $$dialog.confirm("Are you sure that you want to delete " + memberToRemove.email + "?", "Delete",
                function () {
                    if (memberToRemove.sessionStatus == "NEW") {
                        _.remove(vm.project.members, function (member) {
                            return member.email == memberToRemove.email;
                        });
                    } else {
                        memberToRemove.sessionStatus = "REMOVED";
                        membersRemovedCount++;
                    }
                    $scope.$apply();
                });
        };

        vm.hasMembers = function () {
            return vm.project.members.length - membersRemovedCount > 0;
        };
    }]);

