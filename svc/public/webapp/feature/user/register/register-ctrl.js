"use strict";

angular.module("pamm").controller("userRegisterCtrl", ["$state", "$log", "userDao", "cacheManager",
    function ($state, $log, userDao, cacheManager) {
        var vm = this;
        var REG_CACHE = "userRegisterCtrl";
        var REG_DETAILS = "vm.details";
        var REG_CONFIRM = "vm.confirm";

        (function init() {
            vm.registerError = false;
            vm.$$dataType = $$dataType;
            vm.details = cacheManager.get(REG_CACHE).get(REG_DETAILS);
            vm.confirm = cacheManager.get(REG_CACHE).get(REG_CONFIRM);

            cacheManager.get(REG_CACHE).removeAll();
        })();

        vm.register = function (form) {
            if (form.$valid) {
                vm.registerError = false;

                var waitingDialog = $$dialog.waiting("Please wait - Registration in progress");
                var successfulRegistration = function () {
                    waitingDialog.close();
                    $$dialog.success("Registration is successful. A validation link has been sent to your email",
                        function () {
                            $state.go("user.login");
                        })
                };

                var failedRegistration = function (error) {
                    vm.registerError = true;
                    waitingDialog.close();

                    // TODO check error code and display appropriate message
                    $$dialog.error("Registration is unsuccessful");
                };

                userDao.register(vm.details).then(successfulRegistration, failedRegistration);
            }
        };

        vm.cancel = function () {
            $state.go("user.login");
        };

        vm.showTermsOfUse = function () {
            cacheManager.get(REG_CACHE).put(REG_DETAILS, vm.details);
            cacheManager.get(REG_CACHE).put(REG_CONFIRM, vm.confirm);
            $state.go("user.termsofuse");
        };

        $log.debug("userRegisterCtrl: instantiated");
    }]);
