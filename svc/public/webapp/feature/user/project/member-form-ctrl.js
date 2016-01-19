"use strict";

angular.module("pamm").controller("memberFormCtrl", ["$scope", "$uibModalInstance", "$log",
    function ($scope, $uibModalInstance, $log) {
        var vm = $scope;

        vm.member = {
            role: "MEMBER"
        };

        vm.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        vm.addMemberToProject = function (form) {
            if (form.$valid) {
                $uibModalInstance.close(vm.member);
            }
        };
    }]);

