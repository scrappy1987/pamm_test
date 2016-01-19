angular.module("pamm").controller("userCtrl", ["$rootScope", "$state", "$log", "userContext",
    function ($rootScope, $state, $log, userContext) {
        var vm = this;

        (function init() {
            vm.aboutVisible = false;
            vm.user = userContext.getUser();
        })();

        vm.logout = function () {
            $$dialog.confirm("Are you sure that you want to logout?", "Logout", function () {
                userContext.logout();
                $state.go("user.login");
            });
        };

        vm.showAbout = function () {
            alert("Not Implemented");
        };


        vm.isAtHome = function () {
            return $state.is("user.home");
        };

        vm.isAtProjects = function () {
            return $state.is("user.project");
        };

        vm.navigateToProjects = function () {
            $state.go("user.project");
        };

        vm.navigateToHome = function () {
            $state.go("user.home");
        };

        vm.getSelectedProject = function () {
            return userContext.getSelectedProject();
        };

        vm.hasSelectedProject = function () {
            return userContext.getSelectedProject() != undefined;
        };
    }]);
